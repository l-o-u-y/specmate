package com.specmate.cause_effect_patterns.parse.wrapper;

import java.util.ArrayList;
import java.util.List;

public class LeafTreeNode extends MatchResultTreeNode {

	private String content;
	private List<Integer> positions;
	private boolean isVerb;

	public LeafTreeNode(String content) {
		this.content = content;
	}

	public LeafTreeNode(String content, List<Integer> positions, boolean isVerb) {
		this.content = content;
		this.positions = positions == null ? new ArrayList<Integer>(): positions;
		this.isVerb = isVerb;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public List<Integer> getPositions() {
		return this.positions;
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

	@Override
	public String toString() {
		String s = this.content + " - " + this.isVerb + " - ";
		for (int i : this.getPositions()) {
			s += i + "; ";
		}
		return s;
	}
}
