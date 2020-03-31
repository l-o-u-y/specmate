package com.specmate.modelgeneration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeBuilder;
import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.config.api.IConfigService;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.requirements.CEGModel;
import com.specmate.modelgeneration.stages.GraphBuilder;
import com.specmate.modelgeneration.stages.GraphLayouter;
import com.specmate.modelgeneration.stages.MatcherPostProcesser;
import com.specmate.modelgeneration.stages.RuleMatcher;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.nlp.api.ELanguage;
import com.specmate.nlp.api.INLPService;
import com.specmate.nlp.util.EnglishSentenceUnfolder;
import com.specmate.nlp.util.GermanSentenceUnfolder;
import com.specmate.nlp.util.SentenceUnfolderBase;

public class PatternbasedCEGGenerator implements ICEGFromRequirementGenerator {
	private final INLPService tagger;
	private final CEGCreation creation;
	private final ELanguage lang;
	private final RuleMatcher matcher;

	public PatternbasedCEGGenerator(ELanguage lang, INLPService tagger, IConfigService configService)
			throws SpecmateException {
		this.tagger = tagger;
		creation = new CEGCreation();
		this.lang = lang;
		matcher = new RuleMatcher(this.tagger, configService, lang);
	}

	private String preprocessData(String text) throws SpecmateException {
		SentenceUnfolderBase unfolder;
		if (lang == ELanguage.DE) {
			unfolder = new GermanSentenceUnfolder();
		} else {
			unfolder = new EnglishSentenceUnfolder();
		}
		return unfolder.unfold(tagger, text, lang);
	}

	@Override
	public CEGModel createModel(CEGModel model, String text) throws SpecmateException {
		text = preprocessData(text);
		final List<MatchResult> results = matcher.matchText(text);

		final MatchTreeBuilder builder = new MatchTreeBuilder();

		// Convert all successful match results into an intermediate representation
		final List<MatchResultTreeNode> trees = results.stream()
				.filter(MatchResult::isSuccessfulMatch)
				.map(builder::buildTree)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		final MatcherPostProcesser matchPostProcesser = new MatcherPostProcesser(lang);
		GraphBuilder  graphBuilder = new GraphBuilder();
		GraphLayouter graphLayouter = new GraphLayouter(lang, creation);

		boolean generatedSomething = false;
		for (MatchResultTreeNode tree : trees) {
			matchPostProcesser.process(tree);
			while (tree.getType().isLimitedCondition()) {
				tree = ((BinaryMatchResultTreeNode) tree).getSecondArgument();

			}

			if (!tree.getType().isCondition()) {
				continue;
			}
			generatedSomething = true;

			Graph graph = graphBuilder.buildGraph((BinaryMatchResultTreeNode) tree);
			graphLayouter.createModel(graph, model);
		}
		if (!generatedSomething) {
			throw new SpecmateInternalException(ErrorCode.NLP, "No Cause-Effect Pair Found.");
		}

		return model;
	}
}
