package com.specmate.modelgeneration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import com.specmate.model.base.IContentElement;
import com.specmate.model.base.IModelConnection;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGWord;
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
import com.specmate.modelgeneration.stages.processors.NodeBuilder;
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

	public PatternbasedRGGenerator(ELanguage lang, INLPService tagger, IConfigService configService,
			LogService logService) throws SpecmateException {
		this.tagger = tagger;
		this.creation = new RGCreation();
		this.lang = lang;
		matcher = new RuleMatcher(this.tagger, configService, lang);
		matcher.loadRGRessources();
		preProcessor = new TextPreProcessor(lang, tagger);
		log = logService;
	}

	public RGModel createModelWithMapping(String original, String processed, JCas tagResult) {
		RGModel model = RequirementsFactory.eINSTANCE.createRGModel();
		DiffMatchPatch textMatcher = new DiffMatchPatch();
		textMatcher.Diff_EditCost = 7; // approx this value will result in whole words differences
		List<RGWord> rgWords = model.getWords();

		/* save original text words */
		// split text by space, and save each word individually
		String[] textArray = original.split(" ");
		for (String t : textArray) {
			if (!t.isEmpty()) {
				creation.createWord(model, t);
			}
		}
		if (!textArray[textArray.length - 1].equals(".")) {
			creation.createWord(model, ".");
		}

		/* get diff then save processed text words */
		// get list of differences
		LinkedList<Diff> diffs = textMatcher.diff_main(original, processed);
		textMatcher.diff_cleanupEfficiency(diffs); // whole words
		int j = 0; // rgWords counter
		// iterate over differences
		for (int i = 0; i < diffs.size(); i++) {
			Diff diff = diffs.get(i);
			String[] diffTextArray = trimSpace(diff.text).split(" ");
			// if last RGWord / if input text doesnt end with .
			if (rgWords.size() <= j) {
				break;
			}
			RGWord word = rgWords.get(j);

			// if equal, copy words
			if (diff.operation.equals(Operation.EQUAL)) {
				for (String diffText : diffTextArray) {
					word = rgWords.get(j);
					word.setProcessedText(diffText);
					j++;
				}
			} else if (diff.operation.equals(Operation.DELETE)) {
				Diff next = diffs.get(i + 1);
				String[] nextTextArray = trimSpace(next.text).split(" ");

				// delete + insert = replace
				if (next.operation.equals(Operation.INSERT) && diffTextArray.length == nextTextArray.length) {
					// replace words with new words
					// only if #insertedWords == #deletedWords
					for (int k = 0; k < diffTextArray.length; k++) {
						if (nextTextArray.length > k) {
							word = rgWords.get(j);
							word.setProcessedText(nextTextArray[k]);
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
				for (int k = 0; k < diffTextArray.length; k++) {
					creation.createWord(model, diffTextArray[k], j);
					j++;
				}
			}
		}

		/* save word positions */
		int i = 0; // rgWords counter

		Iterable<Token> iterable = JCasUtil.select(tagResult, Token.class);
		for (Token p : iterable) {
			String word = p.getCoveredText();
			String posTag = p.getPosValue();
			if (i >= rgWords.size())
				return model;
			while (i < rgWords.size() && 
					(rgWords.get(i).getNode()!=null ||
							rgWords.get(i).getProcessedText()==null ||
							!rgWords.get(i).getProcessedText().equals(word))) {
				i++;
			}
			if (i < rgWords.size() && rgWords.get(i).getProcessedText() != null && word.equals(rgWords.get(i).getProcessedText())) {
				rgWords.get(i).setId(p.getEnd() + "");
				rgWords.get(i).setPosTag(posTag);
			}

			i++;
		}
		return model;
	}

	public RGModel createModel(RGModel originalModel, String input) throws SpecmateException {
		EObject parent = originalModel.eContainer();
		return createModel(originalModel, parent, input);
	}

	// TODO MA misc: multiple texts
	private RGModel createModel(RGModel originalModel, EObject parent, String input) throws SpecmateException {

		// Fixes some issues with the dkpro/spacy backoff.
		input = input.replaceAll("[^,.!?: ](?=[,.!?:])", "$0 ");
		input = input.replaceAll("\n", " \n ");
		input = input.replaceAll("  ", " ");
		input = input.replaceAll("  ", " ");

		log.log(LogService.LOG_INFO, "================");
		log.log(LogService.LOG_INFO, "Original Text: " + input);

		List<String> texts = preProcessor.preProcess(input);
		List<Pair<String, RGModel>> candidates = new ArrayList<>();

		for (String text : texts) {

			log.log(LogService.LOG_INFO, "Preprocessed Text: " + text);
			JCas tagResult = this.tagger.processText(text, this.lang);

			RGModel prevModel = RequirementsFactory.eINSTANCE.createRGModel();
			if (parent instanceof RGModel) {

				createModel(prevModel, parent.eContainer(), ((RGModel) parent).getModelRequirements());
			}
			RGModel curModel = createModelWithMapping(input, text, tagResult);
			RGModel model = RequirementsFactory.eINSTANCE.createRGModel();
			copyModelContents(model, prevModel);
			copyModelContents(model, curModel);

//			System.out.println(NLPUtil.printPOSTags(tagResult));
//			System.out.println(NLPUtil.printDependencies(tagResult));

			createModelContent(text, model, candidates);

		}

		Pair<String, RGModel> best = findBestCandidate(candidates);

		if (best != null) {
			cutModelContents(originalModel, best.getRight());
		}
		
//		printModelMapping(originalModel);

		cleanupText(originalModel);

		// we needed to have word.id == position in text so we could assign nodes
		// since we already assigned nodes we can randomize the word.ids
		// we also need to do that to ensure there won't be any duplicates
		for (RGWord c : originalModel.getWords()) {
			c.setId(SpecmateEcoreUtil.getIdForChild());
			c.setName(SpecmateEcoreUtil.getIdForChild());
		}

		return originalModel;

	}

	private void createModelContent(String text, RGModel model, List<Pair<String, RGModel>> candidates)
			throws SpecmateException {

		final List<MatchResult> results = matcher.matchText(text, true);
		final MatchTreeBuilder builder = new MatchTreeBuilder();

		// Convert all successful match results into an intermediate representation
		final List<MatchResultTreeNode> trees = results.stream().filter(MatchResult::isSuccessfulMatch)
				.map(builder::buildTree).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		final MatcherPostProcesser matchPostProcesser = new MatcherPostProcesser(lang, new NodeBuilder());
		GraphBuilder graphBuilder = new GraphBuilder();
		RGGraphLayouter graphLayouter = new RGGraphLayouter(lang, creation, log);

		for (MatchResultTreeNode tree : trees) {
			try {
				// TODO MA maybe we dont need to reorder
				matchPostProcesser.process(tree);

				while (tree.getType().isLimitedCondition()) {
					tree = ((BinaryMatchResultTreeNode) tree).getSecondArgument();

				}

				if (tree.getType() == null) {
				} else if (tree.getType().isComposition() || tree.getType().isInheritance() || tree.getType().isAction()
						|| tree.getType().isUpdate() || tree.getType().isCondition()
						|| tree.getType().isConjunction()) {
					Graph graph = graphBuilder.buildRGGraph((BinaryMatchResultTreeNode) tree);
					model = (RGModel) graphLayouter.createModel(graph, model);
					candidates.add(Pair.of(text, model));
				}
			} catch (Throwable t) {
				t.printStackTrace();
				log.log(LogService.LOG_DEBUG, "Error occured processing the dependency parse tree: " + t.getMessage(),
						t);
			}
		}
	}

	private void cleanupText(RGModel originalModel) {
		Set<RGNode> delNodes = new HashSet<RGNode>();
		Set<RGNode> nodes = new HashSet<RGNode>();
		int index = 0;
		// remove text parts that should be removed
		List<RGWord> words = originalModel.getWords().stream().collect(Collectors.toList());
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).getOriginalText() != null
					&& (words.get(i).getOriginalText().equals(".") || words.get(i).getOriginalText().equals(";"))) {
				// sentence has nodes + all or all except 1 (+ 1 label) are deleted
				if (nodes.size() > 0 && nodes.size() <= delNodes.size() + 2) {
					for (int j = index; j < i + 1; j++) {
						RGWord word = words.get(j);
						// rmv from container
							originalModel.getContents().remove(word);
							originalModel.getWords().remove(word);
							RGNode node = word.getNode();
							for (RGWord c : word.getIncoming()) {
								c.getOutgoing().remove(word);
							}
							for (RGWord c : word.getOutgoing()) {
								c.getIncoming().remove(word);
							}
							if (node != null) {
								// rmv from word (bidirectional)
								node.getWords().remove(word);
								word.setNode(null);
							}

					}
				}
				index = i + 1;
				delNodes.clear();
				nodes.clear();
			} else if (words.get(i).getNode() != null) {
				if (words.get(i).isRemoved() && !delNodes.contains(words.get(i).getNode())) {
					delNodes.add(words.get(i).getNode());
				}
				if (!nodes.contains(words.get(i).getNode())) {
					nodes.add(words.get(i).getNode());
				}
			}
		}

		// remove residuals (nodes with no corresponding words + connections)
		List<RGNode> removeNodes = originalModel.getContents().stream().filter(c -> c instanceof RGNode)
				.map(c -> (RGNode) c).filter(c -> c.getWords().size() == 0).collect(Collectors.toList());

		for (RGNode node : removeNodes) {
			// rmv from container
			originalModel.getContents().remove(node);
			originalModel.getContents().removeAll(node.getIncomingConnections());
			originalModel.getContents().removeAll(node.getOutgoingConnections());
			for (IModelConnection c : node.getIncomingConnections()) {
				c.getSource().getOutgoingConnections().remove(c);
			}
			for (IModelConnection c : node.getOutgoingConnections()) {
				c.getTarget().getIncomingConnections().remove(c);
			}
			node.getIncomingConnections().retainAll(new ArrayList<RGConnection>());
			node.getOutgoingConnections().retainAll(new ArrayList<RGConnection>());
		}

		List<RGConnection> removeConnections = originalModel.getContents().stream()
				.filter(c -> c instanceof RGConnection).map(c -> (RGConnection) c)
				.filter(c -> !originalModel.getContents().contains(c.getTarget())
						|| !originalModel.getContents().contains(c.getSource()))
				.collect(Collectors.toList());

		for (RGConnection connection : removeConnections) {
			creation.removeConnection(originalModel, connection);
		}

	}

	private void printModelMapping(RGModel originalModel) {
		for (RGWord r : originalModel.getWords()) {
			String tmp = r.getOriginalText() + "; ";

			if (r.getProcessedText() != null) {
				tmp = tmp + r.getProcessedText() + "; ";

			} else {
				tmp = tmp + "(no associated processed text); ";
			}

				tmp = tmp +  r.getId() + "; ";
				EList<IContentElement> list = originalModel.getContents();
				for (IContentElement rgNode : list) {
					if (rgNode instanceof RGNode) {
						if (((RGNode) rgNode).equals(r.getNode())) {
							tmp = tmp + ((RGNode) rgNode).getComponent() + "; ";
							break;
						}
					}
				}
			
			System.out.println(tmp);
		}

	}

	private String trimSpace(String input) {
		return input.replaceAll("[ ]*((.|\\n)*)[ ]*", "$1");
	}

	// Helper function to copy model contents from source to target model
	// We need this because adding model content to the target removes it from the
	// source
	private void copyModelContents(RGModel target, RGModel source) {
		for (RGWord w : source.getWords()) {
			
			RGWord word = creation.createWord(target, w.getOriginalText());
			word.setProcessedText(w.getProcessedText());
			word.setId(w.getId());
			word.setPosTag(w.getPosTag());
		}

		for (RGWord w : source.getWords()) {
			
			RGWord word = creation.findWord(target, w.getId());
			for (RGWord iw : w.getIncoming()) {
				RGWord incomingWord = creation.findWord(target, iw.getId());
				if (!word.getIncoming().contains(incomingWord)) {
					word.getIncoming().add(incomingWord);
				}
				if (!incomingWord.getOutgoing().contains(word)) {
					incomingWord.getOutgoing().add(word);
				}
			}

			for (RGWord ow : w.getOutgoing()) {
				RGWord outgoingWord = creation.findWord(target, ow.getId());
				if (!word.getOutgoing().contains(outgoingWord)) {
					word.getOutgoing().add(outgoingWord);
				}
				if (!outgoingWord.getIncoming().contains(word)) {
					outgoingWord.getIncoming().add(word);
				}
			}
		}
		
		for (IContentElement e : source.getContents()) {
			if (e instanceof RGNode) {
				RGNode node = creation.copyNodeToModel(target, ((RGNode) e));
				node.setTemporary(((RGNode) e).isTemporary());

				for (RGWord w : ((RGNode) e).getWords()) {
					RGWord word = creation.findWord(target, w.getId());
					if (word != null) {
						node.getWords().add(word);
						word.setNode(node);
					} else {
						System.out.println(node.getComponent());
						System.err.println("Attempted to set word  but no word was found");
					}

				}

			}
		}
		for (IContentElement e : source.getContents()) {
			if (e instanceof RGConnection) {
				RGNode nodeFrom = creation.copyNodeToModel(target, (RGNode) ((RGConnection) e).getSource());
				RGNode nodeTo = creation.copyNodeToModel(target, (RGNode) ((RGConnection) e).getTarget());
				creation.createConnection(target, nodeFrom, nodeTo, ((RGConnection) e).getType(),
						((RGConnection) e).isNegate(), ((RGConnection) e).getLabel());
			}
		}

	}

	private void cutModelContents(RGModel target, RGModel source) {
		target.getContents().addAll(source.getContents());
		target.getWords().addAll(source.getWords());
	}

	private Pair<String, RGModel> findBestCandidate(List<Pair<String, RGModel>> candidates) {
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
			return null;
		}

		return candidates.get(0);

	}

}
