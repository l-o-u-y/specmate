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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osgi.service.log.LogService;

import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeBuilder;
import com.specmate.common.exception.SpecmateException;
import com.specmate.config.api.IConfigService;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.model.support.util.SpecmateEcoreUtil;
import com.specmate.modelgeneration.mapper.DiffMatchPatch;
import com.specmate.modelgeneration.mapper.DiffMatchPatch.Diff;
import com.specmate.modelgeneration.mapper.DiffMatchPatch.Operation;
import com.specmate.modelgeneration.stages.GraphBuilder;
import com.specmate.modelgeneration.stages.MatcherPostProcesser;
import com.specmate.modelgeneration.stages.RGGraphLayouter;
import com.specmate.modelgeneration.stages.RuleMatcher;
import com.specmate.modelgeneration.stages.TextPreProcessor;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.NLPUtil;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
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
	
	private String trimSpace(String input) {
		return input.replaceAll("[ ]*((.|\\n)*)[ ]*", "$1");
	}
	
	public RGModel createModelWithMapping(String original, String processed, JCas tagResult) {
		RGModel model = RequirementsFactory.eINSTANCE.createRGModel();
		DiffMatchPatch textMatcher = new DiffMatchPatch();
		textMatcher.Diff_EditCost = 7; // approx this value will result in whole words differences
		EList<RGObject> rgObjects = model.getModelMapping();

		/* save original text words */
		// split text by space, and save each word individually
		String[] textArray = original.split(" ");
		for (String t:textArray) {
			if (!t.isEmpty()) {
				RGObject tmp = RequirementsFactory.eINSTANCE.createRGObject();
				tmp.setId(SpecmateEcoreUtil.getIdForChild());
				tmp.setOriginalText(t);
				rgObjects.add(tmp);
			}
		}

		/* get diff then save processed text words */
		// get list of differences
		LinkedList<Diff> diffs = textMatcher.diff_main(original, processed);
		textMatcher.diff_cleanupEfficiency(diffs); // whole words
		int j = 0; // rgObjects counter
		// iterate over differences
		for (int i = 0; i < diffs.size(); i++) {
			Diff diff = diffs.get(i);
			String[] diffTextArray = trimSpace(diff.text).split(" ");
			// if last chunk / if input text doesnt end with .
			if (rgObjects.size() <= j) {
				break;
			}
			RGObject object = rgObjects.get(j);
			
			// if equal, copy words
			if (diff.operation.equals(Operation.EQUAL)) {
				for (String diffText:diffTextArray) {
					object = rgObjects.get(j);
					object.setProcessedText(diffText);
					j++;
				}
			} else if (diff.operation.equals(Operation.DELETE)) {
				Diff next = diffs.get(i+1);
				String[] nextTextArray = trimSpace(next.text).split(" ");
				
				// delete + insert = replace
				if (next.operation.equals(Operation.INSERT)) {
					// replace words with new words
					// this only works if #insertedWords <= #deletedWords
					for (int k=0; k<diffTextArray.length; k++) {
						if (nextTextArray.length > k) {
							object = rgObjects.get(j);
							object.setProcessedText(nextTextArray[k]);
						}
						// when all new words are added, skip
						j++;
					}
					i++;
				} else {
					// is actual delete, skip
					j = j + diffTextArray.length;
				}
			} else if (diffs.get(i).operation.equals(Operation.INSERT)) {
				//TODO MA TextGenerator: sometimes we do need insert. e.g. insert period after list item
				log.log(LogService.LOG_ERROR,
						"This case should never happen. Something went wrong. Diff: " + 
						diffs.get(i).toString());
			
			}
		}
		
		/* save text chunks */
		int i = 0; //rgObjects counter

		Iterable<Token> iterable = JCasUtil.select(tagResult, Token.class);
		for (Token p:iterable) {
			String chunkText = trimSpace(p.getCoveredText());
			String[] chunkTextArray = chunkText.split(" ");
			RGChunk c = RequirementsFactory.eINSTANCE.createRGChunk();
			c.setId(p.getEnd()  + "");
			c.setText(chunkText);
			model.getChunks().add(c);
			EList<RGObject> chunkObjects = c.getObjects();
			
			j = 0; // counter for chunkTextArray
			// for punctuation or replacements
//			System.out.println("===================");
//			System.out.println(chunkText);
//			System.out.println(i);
			if (i >= rgObjects.size()) return model;
			System.out.println(rgObjects.get(i).getOriginalText());
			System.out.println(rgObjects.get(i).getProcessedText());
			System.out.println(j);
			System.out.println(chunkTextArray[j]);
			while (i < rgObjects.size() && rgObjects.get(i).getProcessedText() == null || 
					!chunkTextArray[j].equals(rgObjects.get(i).getProcessedText())) {
				i++;
//				System.out.println(i);
//				System.out.println(rgObjects.get(i).getOriginalText());
//				System.out.println(rgObjects.get(i).getProcessedText());
			}
			while (i < rgObjects.size() && rgObjects.get(i).getProcessedText() != null && 
					j < chunkTextArray.length &&
					chunkTextArray[j].equals(rgObjects.get(i).getProcessedText())) {
				rgObjects.get(i).setChunk(c);
				chunkObjects.add(rgObjects.get(i));
				i++;
				j++;
			}
		}
		return model;
	}
	
	public void addNounsToCreation(RGModel model, JCas tagResult) {
		HashSet<String> nouns = new HashSet<String>();
		Iterable<Chunk> iterable = JCasUtil.select(tagResult, Chunk.class);
		for (Chunk p:iterable) {
			if (p.getChunkValue().equals("NP")) {
				nouns.add(p.getCoveredText());
			}
		}

		if (nouns.size() > 0) {
			int i = 1;
			for (String noun : nouns) {
				// don't add nouns with abstract rating of 3 or lower "In an effort to..."
				if (this.creation.isConcrete(noun)) {
					this.creation.createNodeIfNotExist(model, noun, "", 100 * (i), 100 * (i),
							NodeType.AND);
					i++;
				}
			}
		}
	}
	

	public RGModel createModel(RGModel originalModel, String input) throws SpecmateException {
		EObject parent = originalModel.eContainer();
		return createModel(originalModel, parent, input);
	}
	// TODO MA misc: multiple texts
	public RGModel createModel(RGModel originalModel, EObject parent, String input) throws SpecmateException {

		// Fixes some issues with the dkpro/spacy backoff.
		input = input.replaceAll("[^,.!?: ](?=[,.!?:])", "$0 ");
		input = input.replaceAll("\n", " \n ");
		input = input.replaceAll("  ", " ");
		input = input.replaceAll("  ", " ");
		
		log.log(LogService.LOG_INFO, "================");
		log.log(LogService.LOG_INFO, "Textinput: " + input);
		
		List<String> texts = preProcessor.preProcess(input);
		List<Pair<String, RGModel>> candidates = new ArrayList<>();

		for (String text : texts) {
			
			log.log(LogService.LOG_INFO, "Text Pre Processing: " + text);
			JCas tagResult = this.tagger.processText(text, this.lang);
			
			RGModel prevModel = RequirementsFactory.eINSTANCE.createRGModel();
			if (parent instanceof RGModel) {

				createModel(prevModel, parent.eContainer(), ((RGModel) parent).getModelRequirements());
			}
			RGModel curModel = createModelWithMapping(input, text, tagResult);
			RGModel model = RequirementsFactory.eINSTANCE.createRGModel();
			model.getContents().addAll(prevModel.getContents());
			model.getModelMapping().addAll(prevModel.getModelMapping());
			model.getChunks().addAll(prevModel.getChunks());
			model.getModelMapping().addAll(curModel.getModelMapping());
			model.getChunks().addAll(curModel.getChunks());
			
			// TODO MA nouns: add to creation
			// addNounsToCreation(model, tagResult);

			final List<MatchResult> results = matcher.matchText(text, true);
			final MatchTreeBuilder builder = new MatchTreeBuilder();
			
			System.out.println(NLPUtil.printPOSTags(tagResult));
//			System.out.println(NLPUtil.printChunks(tagResult));
//			System.out.println(NLPUtil.printParse(tagResult));
			System.out.println(NLPUtil.printDependencies(tagResult));
			for (MatchResult result : results) {
				System.out.println(result.getRuleName());
			}
			
			// Convert all successful match results into an intermediate representation
			final List<MatchResultTreeNode> trees = results.stream().filter(MatchResult::isSuccessfulMatch)
					.map(builder::buildTree).filter(Optional::isPresent).map(Optional::get)
					.collect(Collectors.toList());

			 final MatcherPostProcesser matchPostProcesser = new MatcherPostProcesser(lang);
			 GraphBuilder graphBuilder = new GraphBuilder();
			 RGGraphLayouter graphLayouter = new RGGraphLayouter(lang, creation, log);

			for (MatchResultTreeNode tree : trees) {
				try {
					 matchPostProcesser.process(tree);
					if (tree.getType().isComposition() || 
							tree.getType().isInheritance() || 
							tree.getType().isAction() ||
//							tree.getType().isConjunction() ||
//							tree.getType().isNorConjunction() ||
//							tree.getType().isOrConjunction() ||
//							tree.getType().isNegation() ||
							tree.getType().isUpdate()
							) {
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

		if (candidates.isEmpty()) {
			return originalModel;
		}

		originalModel.getContents().addAll(candidates.get(0).getRight().getContents());
		originalModel.getModelMapping().addAll(candidates.get(0).getRight().getModelMapping());
		originalModel.getChunks().addAll(candidates.get(0).getRight().getChunks());
		for (RGChunk c : originalModel.getChunks()) {
			c.setId(SpecmateEcoreUtil.getIdForChild());
		}
		
//		for (RGObject r : originalModel.getModelMapping()) {
//			String tmp = r.getOriginalText() + "; ";
//					
//			if (r.getProcessedText() != null) {
//				tmp = tmp + r.getProcessedText() + "; ";
//				
//			} else {
//				tmp = tmp + "(no associated processed text); ";
//			}
//			
//			if (r.getChunk() != null) {
//				tmp = tmp + r.getChunk().getText() + "; " +
//						r.getChunk().getId() + "; ";
//				EList<IContentElement> list = originalModel.getContents();
//				for (IContentElement rgNode : list) {
//					if (rgNode instanceof RGNode) {
//						if (((RGNode)rgNode).equals(r.getChunk().getNode())) {
//							tmp = tmp + ((RGNode)rgNode).getComponent() + "; ";
//							break;
//						}
//					}
//				}
//			}
//			else {
//				tmp = tmp + "(no associated chunk); ";
//			}
//			System.out.println(tmp);
//		}
		return originalModel;

	}

}
