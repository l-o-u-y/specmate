package com.specmate.cause_effect_patterns.parse.wrapper;

import java.util.ArrayList;
import java.util.List;

public class LeafTreeNode extends MatchResultTreeNode {

	private String content;
	private List<String> ids;
	private boolean isVerb;

	public LeafTreeNode(String content) {
		this.content = content;
	}

	public LeafTreeNode(String content, List<String> ids, boolean isVerb) {
		this.content = content;
		this.ids = ids == null ? new ArrayList<String>(): ids;
		this.isVerb = isVerb;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public List<String> getIds() {
		return this.ids;
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
