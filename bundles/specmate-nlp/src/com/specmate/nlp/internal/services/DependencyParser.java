package com.specmate.nlp.internal.services;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response.Status;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.log.LogService;

import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.model.administration.ErrorCode;
import com.specmate.rest.RestClient;
import com.specmate.rest.RestResult;

import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.DependencyFlavor;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.ROOT;
import de.tudarmstadt.ukp.dkpro.core.maltparser.MaltParser;

public class DependencyParser extends MaltParser {

	public static final String PARAM_LANGUAGE = ComponentParameters.PARAM_LANGUAGE;

	// Variables for REST Call (Spacy API)
	public static final String PARAM_SPACY_URL = "__SPACY_URL__";
	@ConfigurationParameter(name = PARAM_SPACY_URL, mandatory = false, defaultValue = "http://127.0.0.1:80")
	protected String SPACY_API_BASE_URL = "http://127.0.0.1:80";

	public static final String PARAM_PARSE_MODE = "__PARSE_MODE__";
	public static final String MODE_SPACY = "SPACY";
	public static final String MODE_MALT = "MALT";
	@ConfigurationParameter(name = PARAM_PARSE_MODE, mandatory = false, defaultValue = MODE_MALT)
	protected String mode = MODE_MALT;

	private static final int TIMEOUT = 5000;
	private LogService logService;
	private RestClient restClient;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		if (mode.equals(MODE_MALT)) {
			super.process(jcas);
			return;
		}

		try {
			spacyProcess(jcas);
		} catch (SpecmateInternalException e) {
			e.printStackTrace();
			// Backoff to Malt
			System.err.println("Backoff to Malt");
			super.process(jcas);
		}
	}

	private void spacyProcess(JCas jcas) throws SpecmateInternalException {
		String text = jcas.getDocumentText();

		// Call Spacy API
		JSONObject result = null;

		// Iterate over all sentences
		for (Sentence curSentence : select(jcas, Sentence.class)) {

			result = accessSpacyAPI(curSentence.getCoveredText());

			// Generate list of tokens for current sentence (Tokens generated by DKPro)
			List<Token> tokens = selectCovered(Token.class, curSentence);

			// We first need to check whether DKPro and Spacy generate the same tokens. For
			// this purpose, we compare both sets of tokens.
			// allWords = all tokens that have been generated by Spacy.
			JSONArray allWords = result.getJSONArray("words");

			// Search for all dependencies in which the current token is included.
			// allArcs = all dependencies that have been generated by Spacy.
			JSONArray allArcs = result.getJSONArray("arcs");

			// Identify the root of the dependency tree
			Set<Integer> noRoots = identifyRoot(allArcs);

			// We can only generate the dependency tree if both methods generated the
			// same set of tokens.
			try {
				if (checkTokenization(tokens, allWords)) {
					// Iterate over all dependencies
					for (int j = 0; j < allArcs.length(); j++) {
						JSONObject currentArc = allArcs.getJSONObject(j);

						// Get description of current dependency
						int start = currentArc.getInt("start");
						int end = currentArc.getInt("end");
						String label = currentArc.getString("label");
						String dir = currentArc.getString("dir");

						// Check whether the token is the "start" of dependency
						// if yes, we create a corresponding Dependency and add it to the JCas
						Dependency dep = new Dependency(jcas);
						dep.setDependencyType(label);
						dep.setFlavor(DependencyFlavor.BASIC);

						// Is the current token a dependent or governor?
						// The token is a dependent if the arrow of the dependency points to the token
						// and vice versa.
						if (dir.equals("left")) {
							dep.setDependent(tokens.get(start));
							dep.setGovernor(tokens.get(end));
						} else if (dir.equals("right")) {
							dep.setGovernor(tokens.get(start));
							dep.setDependent(tokens.get(end));
						}
						dep.setBegin(dep.getDependent().getBegin());
						dep.setEnd(dep.getDependent().getEnd());
						dep.addToIndexes();
					}

					for (int i = 0; i < allWords.length(); i++) {
						// Identify which tokens are (not) roots.
						// Using a double negative makes the computation easier.
						if (!noRoots.contains(i)) {
							Dependency dep = new ROOT(jcas);
							dep.setDependencyType("ROOT");
							dep.setFlavor(DependencyFlavor.BASIC);
							dep.setGovernor(tokens.get(i));
							dep.setDependent(tokens.get(i));
							dep.setBegin(dep.getDependent().getBegin());
							dep.setEnd(dep.getDependent().getEnd());
							dep.addToIndexes();
						}
					}
				}
			} catch (JSONException e) {
				throw new SpecmateInternalException(ErrorCode.INTERNAL_PROBLEM, e);
			}
		}
	}

	private boolean checkTokenization(List<Token> dkProTokens, JSONArray spacyTokens) throws SpecmateInternalException {
		// 1. Quantitative Comparison: Did both approaches create the same amount of
		// tokens?
		if (dkProTokens.size() != spacyTokens.length()) {
			throw new SpecmateInternalException(ErrorCode.SPACY,
					"DKPro and Spacy generate different set of tokens. Dependency tree can not be created.");
		}

		// 2. Qualitative Comparison: Compare the content of all tokens.
		for (int i = 0; i < dkProTokens.size(); i++) {
			String dkToken = dkProTokens.get(i).getCoveredText();
			String spacyToken = spacyTokens.getJSONObject(i).getString("text");
			if (!dkToken.equals(spacyToken)) {
				throw new SpecmateInternalException(ErrorCode.SPACY,
						"DKPro and Spacy generate different set of tokens. Dependency tree can not be created.");
			}
		}

		return true;
	}

	private JSONObject accessSpacyAPI(String requirement) throws SpecmateInternalException {
		restClient = new RestClient(SPACY_API_BASE_URL, TIMEOUT, logService);

		// Set model parameters
		JSONObject request = new JSONObject();
		request.put("text", requirement);
		request.put("model", language);
		request.put("collapse_punctuation", 0);
		request.put("collapse_phrases", 0);

		RestResult<JSONObject> result = restClient.post("/dep", request);
		if (result.getResponse().getStatus() == Status.OK.getStatusCode()) {
			result.getResponse().close();
			return result.getPayload();
		} else {
			result.getResponse().close();
			throw new SpecmateInternalException(ErrorCode.SPACY,
					"Could not access Spacy API. Dependencies could not be loaded.");
		}
	}

	private static Set<Integer> identifyRoot(JSONArray allDep) {
		HashSet<Integer> noRoots = new HashSet<Integer>();
		for (int i = 0; i < allDep.length(); i++) {
			JSONObject arc = allDep.getJSONObject(i);
			int start = arc.getInt("start");
			int end = arc.getInt("end");
			String dir = arc.getString("dir");

			if (dir.equals("right")) {
				noRoots.add(end);
			} else {
				noRoots.add(start);
			}
		}
		return noRoots;
	}
}
