package com.specmate.modelgeneration.stages;

import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.modelgeneration.stages.processors.ConditionVariableTextCleaner;
import com.specmate.modelgeneration.stages.processors.JoinedConditionSplitter;
import com.specmate.modelgeneration.stages.processors.NodeBuilder;
import com.specmate.modelgeneration.stages.processors.OperationOrderFixer;
import com.specmate.nlp.api.ELanguage;

public class MatcherPostProcesser {

	private final OperationOrderFixer orderFixer;
	private final JoinedConditionSplitter conditionSplitter;
	protected NodeBuilder nodeBuilder;
	private final ConditionVariableTextCleaner cvCleaner;

	public MatcherPostProcesser(ELanguage lang, NodeBuilder nodeBuilder) {
		conditionSplitter = new JoinedConditionSplitter(lang);
		orderFixer = new OperationOrderFixer();
		cvCleaner = new ConditionVariableTextCleaner();
		this.nodeBuilder = nodeBuilder;
	}

	public MatchResultTreeNode process(MatchResultTreeNode node) {
		node = nodeBuilder.buildNodes(node);
		node = conditionSplitter.splitNodes(node);
		node.acceptVisitor(orderFixer);
		node.acceptVisitor(cvCleaner);
		return node;
	}
}
