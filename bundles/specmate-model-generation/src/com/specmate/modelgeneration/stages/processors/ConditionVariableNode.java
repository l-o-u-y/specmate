package com.specmate.modelgeneration.stages.processors;

import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;

public class ConditionVariableNode extends LeafTreeNode {
	private String primaryText;
	private String secondaryText;

	public ConditionVariableNode(String sec, String prim) {
		super(prim + (sec.equals("") ? "" : " " + sec));
		this.primaryText = prim;
		this.secondaryText = sec;
	}

	public String getSecondary() {
		return secondaryText;
	}

	public String getPrimary() {
		return primaryText;
	}

	public void setSecondary(String newSec) {
		if(newSec == null) {
			throw new NullPointerException("Secondary text can not be null");
		}
		secondaryText = newSec;
	}

	public void setPrimary(String newPrim) {
		if(newPrim == null) {
			throw new NullPointerException("Primary text can not be null");
		}
		primaryText = newPrim;
	}

	@Override
	public RuleType getType() {
		return RuleType.CONDITION_VARIABLE; // null; // RuleType.LEAF;
	}
}
