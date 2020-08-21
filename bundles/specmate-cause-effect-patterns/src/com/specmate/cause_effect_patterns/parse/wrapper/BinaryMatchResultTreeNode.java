package com.specmate.cause_effect_patterns.parse.wrapper;

public class BinaryMatchResultTreeNode extends MatchResultTreeNode {
	private MatchResultTreeNode left;
	private MatchResultTreeNode right;
	private String label;
	private RuleType type;

	public BinaryMatchResultTreeNode(MatchResultTreeNode left, MatchResultTreeNode right, RuleType type) {
		this(left, right, type, "");
	}

	public BinaryMatchResultTreeNode(MatchResultTreeNode left, MatchResultTreeNode right, RuleType type, String label) {
		this.left = left;
		this.right = right;
		this.type = type;
		this.label = label;
	}
	
	public MatchResultTreeNode getFirstArgument() {
		return this.left;
	}
	
	public void setFirstArguement(MatchResultTreeNode node) {
		this.left = node;
	}
	
	public MatchResultTreeNode getSecondArgument() {
		return this.right;
	}
	
	public void setSecondArguement(MatchResultTreeNode node) {
		this.right = node;
	}
	
	protected void setType(RuleType type) {
		this.type = type;
	}
	
	public void leftSwap() {		
		BinaryMatchResultTreeNode left = (BinaryMatchResultTreeNode) getFirstArgument();
		MatchResultTreeNode right = getSecondArgument();
		
		MatchResultTreeNode childLeft = left.getFirstArgument();
		MatchResultTreeNode childRight = left.getSecondArgument();
		
		//Swap Types so the one with higher precedents gets shifted down
		RuleType tmp = this.type;
		this.type = left.getType();
		left.setType(tmp);
		
		// TODO MA If it's composition, connect leftleft with right
		if (this.getType().equals(RuleType.COMPOSITION)) {
			// this.left = left;
			this.right = childRight;
			left.right = right;
		// else connect leftright with right
		} else {
			this.left  = childLeft;
			this.right = left;
			left.left  = childRight;
			left.right = right;
		}
		
	}

	public void rightSwap() {
		MatchResultTreeNode left = getFirstArgument();
		BinaryMatchResultTreeNode right = (BinaryMatchResultTreeNode) getSecondArgument();
		
		MatchResultTreeNode childLeft = right.getFirstArgument();
		MatchResultTreeNode childRight = right.getSecondArgument();
		
		RuleType tmp = this.type;
		this.type = right.getType();
		right.setType(tmp);
		
		// TODO MA might need cases
		this.left  = right;
		this.right = childRight;
		right.left  = left;
		right.right = childLeft;
	}

	@Override
	public RuleType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public void acceptVisitor(MatchTreeVisitor visitor) {
		visitor.visit(this);
	}
}
