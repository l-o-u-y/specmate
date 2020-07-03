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
import com.specmate.cause_effect_patterns.parse.matcher.MatchRule;
import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultWrapper;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultWrapper.SubtreeNames;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeBuilder;
import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.config.api.IConfigService;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.modelgeneration.stages.GraphBuilder;
import com.specmate.modelgeneration.stages.GraphLayouter;
import com.specmate.modelgeneration.stages.MatcherPostProcesser;
import com.specmate.modelgeneration.stages.RuleMatcher;
import com.specmate.modelgeneration.stages.TextPreProcessor;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.NLPUtil;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class PatternbasedRGGenerator implements IRGFromRequirementGenerator {
	private INLPService tagger;
	private RGCreation creation;
	private ELanguage lang;
	private final TextPreProcessor preProcessor;
	private final RuleMatcher matcher;
	private final LogService log;
	
	private static final int XSTART  = 225;
	private static final int YSTART  = 225;
	
	private static final int XOFFSET = 300;
	private static final int YOFFSET = 150;
	
	
	public PatternbasedRGGenerator(ELanguage lang, INLPService tagger, IConfigService configService,
			LogService logService) throws SpecmateException {
		this.tagger = tagger;
		this.creation = new RGCreation();
		this.lang = lang;
		matcher = new RuleMatcher(this.tagger, configService, lang);
		preProcessor = new TextPreProcessor(lang, tagger);
		log = logService;
		// loadRessources();
	}
	
	public RGModel createModel(RGModel originalModel, String input) throws SpecmateException {
		log.log(LogService.LOG_INFO, "Textinput: " + input);
		boolean generatedSomething = false;
		List<String> texts = preProcessor.preProcess(input);
		LinkedList<RGNode> nodes = new LinkedList<RGNode>();

		for (String text : texts) {
			log.log(LogService.LOG_INFO, "Text Pre Processing: " + text);
			JCas tagResult = this.tagger.processText(text, this.lang);
			
			HashSet<String> nouns = new HashSet<String>();
			JCasUtil.select(tagResult, Token.class).forEach(p -> {
				if (p.getPosValue().equals("NN") || p.getPosValue().equals("NNP") ) {
					nouns.add(p.getCoveredText());
				}
			});

			System.out.println(NLPUtil.printPOSTags(tagResult));
			System.out.println(NLPUtil.printChunks(tagResult));
			System.out.println(NLPUtil.printParse(tagResult));
			System.out.println(NLPUtil.printDependencies(tagResult));
			
			if (nouns.size() > 0) {
				generatedSomething = true;
				int i = 0;
				for (String noun : nouns) {
					this.creation.createNodeIfNotExist(nodes, originalModel, noun, "", 100 * (i), 100 * (i), NodeType.AND);
					i++;
				}
			}

			final List<MatchResult> results = matcher.matchText(text);
			
			for(MatchResult result: results) {
				if(!result.isSuccessfulMatch()) {
					continue;
				}
				MatchResultWrapper res = new MatchResultWrapper(result);

				if (res.isInheritance()) {
					RGNode parent = this.creation.createNodeIfNotExist(nodes, originalModel, 
							res.result.getSubmatch(SubtreeNames.PARENT).getMatchTree().getRepresentationString(true)
							, "", 0, 0, NodeType.AND);
					RGNode child = this.creation.createNodeIfNotExist(nodes, originalModel, 
							res.result.getSubmatch(SubtreeNames.CHILD).getMatchTree().getRepresentationString(true)
							, "", 0, 0, NodeType.AND);
					this.creation.createConnection(originalModel, parent, child, RGConnectionType.INHERITANCE, false);
				} else if(res.isComposition()) {
					generatedSomething = true;
					RGNode parent = this.creation.createNodeIfNotExist(nodes, originalModel, 
							res.result.getSubmatch(SubtreeNames.PARENT).getMatchTree().getRepresentationString(true)
							, "", 0, 0, NodeType.AND);
					RGNode child = this.creation.createNodeIfNotExist(nodes, originalModel, 
							res.result.getSubmatch(SubtreeNames.CHILD).getMatchTree().getRepresentationString(true)
							, "", 0, 0, NodeType.AND);
					this.creation.createConnection(originalModel, parent, child, RGConnectionType.COMPOSITION, false);
				} else if(res.isAction()) {
					/*
					i++;
					RGNode parent = this.creation.createNodeIfNotExist(nodes, model, var+" "+(i), "", 100 * (i), 100 * (i), NodeType.OR);
					i++;
					RGNode child = this.creation.createNodeIfNotExist(nodes, model, var+" "+(i), "", 100 * (i), 100 * (i), NodeType.OR);
					this.creation.createConnection(model, parent, child, RGConnectionType.COMPOSITION, false);
					*/
				}
				
				
			}
			if(!generatedSomething) {
				throw new SpecmateInternalException(ErrorCode.NLP, "No Relationship Pair Found.");
			}
		}
		return originalModel;
			
//			final List<MatchResult> results = matcher.matchText(text);
//
//			final MatchTreeBuilder builder = new MatchTreeBuilder();
//
//			// Convert all successful match results into an intermediate representation
//			final List<MatchResultTreeNode> trees = results.stream().filter(MatchResult::isSuccessfulMatch)
//					.map(builder::buildTree).filter(Optional::isPresent).map(Optional::get)
//					.collect(Collectors.toList());
//
//			final MatcherPostProcesser matchPostProcesser = new MatcherPostProcesser(lang);
//			GraphBuilder graphBuilder = new GraphBuilder();
//			GraphLayouter graphLayouter = new GraphLayouter(lang, creation);
//
//			for (MatchResultTreeNode tree : trees) {
//				try {
//					matchPostProcesser.process(tree);
//					/* while (tree.getType().isLimitedCondition()) {
//						tree = ((BinaryMatchResultTreeNode) tree).getSecondArgument();
//
//					}*/
//
//					// TODO
//					if (!tree.getType().isComposition() && !tree.getType().isInheritance()) {
//						continue;
//					}
//
//					Graph graph = graphBuilder.buildRGGraph((BinaryMatchResultTreeNode) tree);
//					RGModel model = (RGModel)graphLayouter.createModel(graph);
//					candidates.add(Pair.of(text, model));
//				} catch (Throwable t) {
//					log.log(LogService.LOG_DEBUG,
//							"Error occured processing the dependency parse tree: " + t.getMessage(), t);
//				}
//			}
//
//		}
//		if (candidates.isEmpty()) {
//			throw new SpecmateInternalException(ErrorCode.NLP, "No Cause-Effect Pair Found.");
//		}
//
//		// Sort by model size (bigger models are better) and number of commas (more is
//		// better) and length of the input texts
//		// (shorter texts are better)
//		candidates.sort((p1, p2) -> {
//			RGModel m1 = p1.getRight();
//			RGModel m2 = p2.getRight();
//			int c = Integer.compare(m2.getContents().size(), m1.getContents().size());
//			if (c != 0) {
//				return c;
//			}
//			String t1 = p1.getLeft();
//			String t2 = p2.getLeft();
//			c = Integer.compare(StringUtils.countMatches(t2, ","), StringUtils.countMatches(t1, ","));
//			if (c != 0) {
//				return c;
//			}
//			return Integer.compare(t1.length(), t2.length());
//
//		});
//		originalModel.getContents().addAll(candidates.get(0).getRight().getContents());
//		return originalModel;
	}

	private RGNode addNode(RGModel model, LinkedList<RGNode> nodes, String component, String modifier, int[] posTable, int offset, NodeType type) {
		int posX = XSTART + offset * XOFFSET;
		int posY = YSTART + posTable[offset] * YOFFSET;
		
		int oldSize = nodes.size();
		RGNode node = this.creation.createNodeIfNotExist(nodes, model, component, modifier, posX, posY, type);
		if(nodes.size() != oldSize) {
			nodes.add(node);
			posTable[offset]++;
		}
		return node;
	}
	
	private String capitalize(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1); 
	}
	
	
	
}
