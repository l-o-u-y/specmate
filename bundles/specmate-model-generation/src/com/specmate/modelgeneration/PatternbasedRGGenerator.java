package com.specmate.modelgeneration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.osgi.service.log.LogService;

import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeBuilder;
import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.config.api.IConfigService;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.modelgeneration.stages.GraphBuilder;
import com.specmate.modelgeneration.stages.GraphLayouter;
import com.specmate.modelgeneration.stages.MatcherPostProcesser;
import com.specmate.modelgeneration.stages.RuleMatcher;
import com.specmate.modelgeneration.stages.TextPreProcessor;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.NodeType;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.NLPUtil;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;

public class PatternbasedRGGenerator implements IRGFromRequirementGenerator {
	private INLPService tagger;
	private RGCreation creation;
	private ELanguage lang;
	private final TextPreProcessor preProcessor;
	private final RuleMatcher matcher;
	private final LogService log;

//	private static final int XSTART = 225;
//	private static final int YSTART = 225;
//
//	private static final int XOFFSET = 300;
//	private static final int YOFFSET = 150;

	public PatternbasedRGGenerator(ELanguage lang, INLPService tagger, IConfigService configService,
			LogService logService) throws SpecmateException {
		this.tagger = tagger;
		this.creation = new RGCreation();
		this.lang = lang;
		matcher = new RuleMatcher(this.tagger, configService, lang);
		preProcessor = new TextPreProcessor(lang, tagger);
		log = logService;
	}
	
	// TODO MA multiple texts
	public RGModel createModel(RGModel originalModel, String input) throws SpecmateException {
		log.log(LogService.LOG_INFO, "================");
		log.log(LogService.LOG_INFO, "Textinput: " + input);
		boolean generatedSomething = false;
		List<String> texts = preProcessor.preProcess(input);
		List<Pair<String, RGModel>> candidates = new ArrayList<>();

		for (String text : texts) {
			log.log(LogService.LOG_INFO, "Text Pre Processing: " + text);
			JCas tagResult = this.tagger.processText(text, this.lang);
			RGModel model = RequirementsFactory.eINSTANCE.createRGModel();

			HashSet<String> nouns = new HashSet<String>();
			JCasUtil.select(tagResult, Chunk.class).forEach(p -> {
				if (p.getChunkValue().equals("NP")) {
					nouns.add(p.getCoveredText());
				}
			});

			System.out.println(NLPUtil.printPOSTags(tagResult));
			System.out.println(NLPUtil.printChunks(tagResult));
			System.out.println(NLPUtil.printParse(tagResult));
			System.out.println(NLPUtil.printDependencies(tagResult));

			// TODO MA
//			if (nouns.size() > 0) {
				generatedSomething = true;
//				int i = 1;
//				for (String noun : nouns) {
//					// don't add nouns with abstract rating of 3 or lower "In an effort to..."
//					if (this.creation.isConcrete(noun)) {
//						this.creation.createNodeIfNotExist(model, noun, "", 100 * (i), 100 * (i),
//								NodeType.AND);
//						i++;
//					}
//				}
//			}

			final List<MatchResult> results = matcher.matchText(text, true);
			final MatchTreeBuilder builder = new MatchTreeBuilder();

			for (MatchResult result : results) {
				System.out.println(result.getRuleName());
			}
			// Convert all successful match results into an intermediate representation
			final List<MatchResultTreeNode> trees = results.stream().filter(MatchResult::isSuccessfulMatch)
					.map(builder::buildTree).filter(Optional::isPresent).map(Optional::get)
					.collect(Collectors.toList());

			 final MatcherPostProcesser matchPostProcesser = new MatcherPostProcesser(lang);
			 GraphBuilder graphBuilder = new GraphBuilder();
			 GraphLayouter graphLayouter = new GraphLayouter(lang, creation);


			for (MatchResultTreeNode tree : trees) {
				try {
					 matchPostProcesser.process(tree);
					if (tree.getType().isComposition() || tree.getType().isInheritance() || tree.getType().isAction()) {
						Graph graph = graphBuilder.buildRGGraph((BinaryMatchResultTreeNode) tree);
						model = (RGModel) graphLayouter.createModel(graph, model);
						candidates.add(Pair.of(text, model));
					}
				} catch (Throwable t) {
					t.printStackTrace();
					log.log(LogService.LOG_DEBUG,
							"Error occured processing the dependency parse tree: " + t.getMessage(), t);
				}
			}

		}

		candidates.sort((p1, p2) -> {
			RGModel m1 = p1.getRight();
			RGModel m2 = p2.getRight();
			int c = Integer.compare(m2.getContents().size(), m1.getContents().size());
			if (c != 0) {
				return c;
			}
			String t1 = p1.getLeft();
			String t2 = p2.getLeft();
			c = Integer.compare(StringUtils.countMatches(t2, ","), StringUtils.countMatches(t1, ","));
			if (c != 0) {
				return c;
			}
			return Integer.compare(t1.length(), t2.length());

		});

		if (!generatedSomething) {
			throw new SpecmateInternalException(ErrorCode.NLP, "No Relationship Pair Found.");
		}
		if (candidates.isEmpty()) {
			return originalModel;
		}

		originalModel.getContents().addAll(candidates.get(0).getRight().getContents());
		return originalModel;

	}

}