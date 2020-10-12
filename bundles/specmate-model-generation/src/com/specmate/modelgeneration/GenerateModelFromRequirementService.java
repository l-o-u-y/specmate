package com.specmate.modelgeneration;

import java.net.URISyntaxException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.config.api.IConfigService;
import com.specmate.emfrest.api.IRestService;
import com.specmate.emfrest.api.RestServiceBase;
import com.specmate.metrics.ICounter;
import com.specmate.metrics.IMetricsService;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.base.ISpecmateModelObject;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.RGModel;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.NLPUtil;
import com.specmate.rest.RestClient;
import com.specmate.rest.RestResult;
import com.specmate.xtext.XTextException;


/**
 * Service to create automatic a CEGModel from a requirement
 *
 * @author Andreas Wehrle
 *
 */
@Component(immediate = true, service = IRestService.class)
public class GenerateModelFromRequirementService extends RestServiceBase {

	INLPService tagger;
	private LogService logService;
	private IConfigService configService;
	private IMetricsService metricsService;
	private ICounter modelGenCounter;

	@Activate
	public void activate() throws SpecmateException {
		modelGenCounter = metricsService.createCounter("model_generation_counter", "Total number of generated models");
	}

	@Override
	public String getServiceName() {
		return "generateModel";
	}

	@Override
	public boolean canPost(Object object2, Object object) {
		return object2 instanceof CEGModel || object2 instanceof RGModel;
	}

	@Override
	public RestResult<?> post(Object parent, Object child, String token) {
		// TODO MA misc: make CEG and RG Model inherit from Model then polymorph
		ISpecmateModelObject model;
		if (parent instanceof CEGModel) {
			model = (CEGModel) parent;
		}
		else { // if (parent instanceof RGModel) {
			model = (RGModel) parent;
			((RGModel)model).getModelMapping().clear();
		}

		model.getContents().clear(); // Delete Contents
		try {
			logService.log(LogService.LOG_INFO, "Model Generation STARTED");
			model = generateModelFromDescription(model);
			logService.log(LogService.LOG_INFO, "Model Generation FINISHED");
			modelGenCounter.inc();
		} catch (SpecmateException e) {
			logService.log(LogService.LOG_ERROR,
					"Model Generation failed with following error:\n" + e.getMessage());
			return new RestResult<>(Response.Status.INTERNAL_SERVER_ERROR);
		}

		if (parent instanceof RGModel) {
			model = (RGModel) parent;
			model.getContents().clear(); // Delete Contents
			try {
				logService.log(LogService.LOG_INFO, "Model Generation STARTED");
				model = generateModelFromDescription(model);
				logService.log(LogService.LOG_INFO, "Model Generation FINISHED");
				modelGenCounter.inc();
			} catch (SpecmateException e) {
				logService.log(LogService.LOG_ERROR,
						"Model Generation failed with following error:\n" + e.getMessage());
				return new RestResult<>(Response.Status.INTERNAL_SERVER_ERROR);
			}
		}


		return new RestResult<>(Response.Status.OK);
	}

	/**
	 * Add the nodes and connections to the model extracted from the text
	 *
	 * @param model CEGModel
	 * @return
	 * @throws XTextException
	 * @throws URISyntaxException
	 */
	private ISpecmateModelObject generateModelFromDescription(ISpecmateModelObject model) throws SpecmateException {
		String text;

		if (model instanceof CEGModel) {
			text = ((CEGModel) model).getModelRequirements();
		}
		else { // if (parent instanceof RGModel) {
			text = ((RGModel) model).getModelRequirements();
		}

		if (text == null || StringUtils.isEmpty(text)) {
			return model;
		}
		// text = new PersonalPronounsReplacer(tagger).replacePronouns(text);
		ELanguage lang = NLPUtil.detectLanguage(text);

		if (model instanceof CEGModel) {
			ICEGFromRequirementGenerator generator;

			if (lang == ELanguage.PSEUDO) {
				generator = new GenerateCEGModelFromPseudoCode();
			} else {
				generator = new PatternbasedCEGGenerator(lang, tagger, configService, logService);
			}

			// Fixes some issues with the dkpro/spacy backoff.
			text = text.replaceAll("[^,.!? ](?=[,.!?])", "$0 ").replaceAll("\\s+", " ");
			
			try {
				generator.createModel((CEGModel)model, text);
			} catch (SpecmateException e) {
				// Generation Backof
				logService.log(LogService.LOG_INFO,
						"NLP model generation failed with the following error: \"" + e.getMessage() + "\"");
				logService.log(LogService.LOG_INFO, "Backing off to rule based generation...");
			}
		}
		else { // if (parent instanceof RGModel) {
			// TODO MA misc: ggf. just have a separate input field in FE
			if (text.matches("(.*)https:\\/\\/github.com\\/(.*)\\/(.*)\\/issues\\/(\\d*)(.*)")) {
				String apiUrl = text.replaceAll("(.*)https:\\/\\/github.com\\/(.*)\\/(.*)\\/issues\\/(\\d*)(.*)",
						"https://api.github.com/repos/$2/$3/issues/$4");
				try {
					text = this.getGithubIssue(apiUrl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			IRGFromRequirementGenerator generator;
			generator = new PatternbasedRGGenerator(ELanguage.EN, tagger, this.configService, this.logService);

			try {
				generator.createModel((RGModel)model, text);
			} catch (SpecmateException e) {
				this.logService.log(LogService.LOG_INFO,
						"NLP model generation failed with the following error: \"" + e.getMessage() + "\"");
			}
		}

		return model;
	}


	   private String getGithubIssue(String urlToRead) throws Exception {
			RestClient restClient = new RestClient(urlToRead, 5000, logService);
			try (restClient) {
				RestResult<JSONObject> result = restClient.get("");
				if (result.getResponse().getStatus() == Status.OK.getStatusCode()) {
					result.getResponse().close();
					JSONObject payload = result.getPayload();
					String text = (String)payload.get("body");
					return text;

				} else {
					result.getResponse().close();
					throw new SpecmateInternalException(ErrorCode.NO_SUCH_SERVICE,
							"Could not access Github Issue. Description could not be loaded.");
				}
			}
	   }

	@Reference
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Reference
	void setNlptagging(INLPService tagger) {
		this.tagger = tagger;
	}

	/** Service reference for config service */
	@Reference
	public void setConfigurationService(IConfigService configService) {
		this.configService = configService;
	}

	@Reference
	public void setMetricsService(IMetricsService metricsService) {
		this.metricsService = metricsService;
	}
}
