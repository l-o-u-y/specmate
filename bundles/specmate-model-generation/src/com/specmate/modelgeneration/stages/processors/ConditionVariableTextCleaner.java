package com.specmate.modelgeneration.stages.processors;

import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeVisitor;
import com.specmate.cause_effect_patterns.parse.wrapper.NegationTreeNode;

<<<<<<< HEAD
public class ConditionVariableTextCleaner  extends MatchTreeVisitor {
=======
public class ConditionVariableTextCleaner extends MatchTreeVisitor {
>>>>>>> 57ef2e5ae2c1191ec3c48124c825c0268d979cfd

	@Override
	public void visit(BinaryMatchResultTreeNode node) {
		node.getFirstArgument().acceptVisitor(this);
		node.getSecondArgument().acceptVisitor(this);
	}

	@Override
	public void visit(LeafTreeNode node) {
<<<<<<< HEAD
		if(node instanceof ConditionVariableNode) {
			ConditionVariableNode cvNode = (ConditionVariableNode) node;
			String condition = cvNode.getCondition();
			if(condition.startsWith("If") || condition.startsWith("if")) {
				condition = condition.replaceFirst("(I|i)f", "").trim();
			}
=======
		if (node instanceof ConditionVariableNode) {
			ConditionVariableNode cvNode = (ConditionVariableNode) node;
			String condition = cvNode.getCondition();
			if (condition.startsWith("If") || condition.startsWith("if")) {
				condition = condition.replaceFirst("(I|i)f", "").trim();
			}
			if (condition.startsWith("then")) {
				condition = condition.replaceFirst("then", "").trim();
			}
>>>>>>> 57ef2e5ae2c1191ec3c48124c825c0268d979cfd
			cvNode.setCondition(condition);
		}

	}

	@Override
	public void visit(NegationTreeNode node) {
		node.getClause().acceptVisitor(this);
	}

}
