package com.specmate.cause_effect_patterns.parse.wrapper;


public class LeafTreeNode extends MatchResultTreeNode {

	private String content;
	private int id;
	public LeafTreeNode(String content) {
		this.content = content;
	}
	public LeafTreeNode(String content, int id) {
		this.content = content;
		this.id = id;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public RuleType getType() {
		return null;
	}

	@Override
	public void acceptVisitor(MatchTreeVisitor visitor) {
		visitor.visit(this);
	}

}
