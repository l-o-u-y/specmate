package com.specmate.cause_effect_patterns.parse.wrapper;

public class BinaryMatchResultTreeNode extends MatchResultTreeNode {
	private MatchResultTreeNode left;
	private MatchResultTreeNode right;
	private LeafTreeNode label;
	private RuleType type;

	public BinaryMatchResultTreeNode(MatchResultTreeNode left, MatchResultTreeNode right, RuleType type) {
		this(left, right, type, null);
	}

	public BinaryMatchResultTreeNode(MatchResultTreeNode left, MatchResultTreeNode right, RuleType type, LeafTreeNode label) {
		this.left = left;
		this.right = right;
		this.type = type;
		this.label = label;
	}

	public MatchResultTreeNode getFirstArgument() {
		return this.left;
	}

	public void setFirstArgument(MatchResultTreeNode node) {
		this.left = node;
	}

	public MatchResultTreeNode getSecondArgument() {
		return this.right;
	}

	public void setSecondArgument(MatchResultTreeNode node) {
		this.right = node;
	}

	protected void setType(RuleType type) {
		this.type = type;
	}
	
	public void clearLabel() {
		this.label = null;
	}
	public void setLabel(LeafTreeNode label) {
		this.label = label;
	}

	public void leftSwap() {
		BinaryMatchResultTreeNode left = (BinaryMatchResultTreeNode) getFirstArgument();
		MatchResultTreeNode right = getSecondArgument();

		MatchResultTreeNode childLeft = left.getFirstArgument();
		MatchResultTreeNode childRight = left.getSecondArgument();
		LeafTreeNode label = this.label;

		// Swap Types so the one with higher precedents gets shifted down
		RuleType tmp = this.type;
		this.type = left.getType();
		left.setType(tmp);

		this.left = childLeft;
		this.right = left;
		left.left = childRight;
		left.right = right;
		this.label = left.label;
		left.label = label;

	}

	public void rightSwap() {
		MatchResultTreeNode left = getFirstArgument();
		BinaryMatchResultTreeNode right = (BinaryMatchResultTreeNode) getSecondArgument();

		MatchResultTreeNode childLeft = right.getFirstArgument();
		MatchResultTreeNode childRight = right.getSecondArgument();
		LeafTreeNode label = this.label;

		RuleType tmp = this.type;
		this.type = right.getType();
		right.setType(tmp);

		this.left = right;
		this.right = childRight;
		right.left = left;
		right.right = childLeft;
		this.label = right.label;
		right.label = label;
	}

	@Override
	public RuleType getType() {
		return type;
	}

	public LeafTreeNode getLabel() {
		return label;
	}

	@Override
	public void acceptVisitor(MatchTreeVisitor visitor) {
		visitor.visit(this);
	}

}
