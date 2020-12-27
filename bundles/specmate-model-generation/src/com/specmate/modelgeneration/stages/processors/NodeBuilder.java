package com.specmate.modelgeneration.stages.processors;

import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchTreeVisitor;
import com.specmate.cause_effect_patterns.parse.wrapper.NegationTreeNode;

public class NodeBuilder {

	public MatchResultTreeNode buildNodes(MatchResultTreeNode root) {
		NodeVisitor visitor = new NodeVisitor();
		root.acceptVisitor(visitor);

		MatchResultTreeNode newRoot = root;
		if (visitor.replacementNode != null) {
			newRoot = visitor.replacementNode;
		}
		return newRoot;
	}

	private class NodeVisitor extends MatchTreeVisitor {
		public LeafTreeNode replacementNode;

		@Override
		public void visit(BinaryMatchResultTreeNode node) {
			node.getFirstArgument().acceptVisitor(this);
			if (replacementNode != null) {
				node.setFirstArgument(replacementNode);
				replacementNode = null;
			}

			node.getSecondArgument().acceptVisitor(this);
			if (replacementNode != null) {
				node.setSecondArgument(replacementNode);
				replacementNode = null;
			}
		}

		@Override
		public void visit(LeafTreeNode node) {
			replacementNode = new LeafTreeNode(node.getContent(), node.getPositions(), node.isVerb());
		}

		@Override
		public void visit(NegationTreeNode node) {
			node.getClause().acceptVisitor(this);
			if (replacementNode != null) {
				node.setClause(replacementNode);
				replacementNode = null;
			}
		}

	}
}
