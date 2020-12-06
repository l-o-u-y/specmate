package com.specmate.cause_effect_patterns.parse.wrapper;

public class LeafTreeNode extends MatchResultTreeNode {

	private String content;
	private String id;
	private boolean isVerb;

	public LeafTreeNode(String content) {
		this.content = content;
	}

	public LeafTreeNode(String content, String id, boolean isVerb) {
		this.content = content;
		this.id = id;
		this.isVerb = isVerb;
	}

	public String getContent() {
		return this.content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
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

	public boolean isVerb() {
		return isVerb;
	}

	public void setVerb(boolean isVerb) {
		this.isVerb = isVerb;
	}

}
