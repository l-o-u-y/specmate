package com.specmate.cause_effect_patterns.parse.wrapper;

import java.util.Collection;
import java.util.Optional;

import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode.RuleType;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class MatchTreeBuilder {
	private static class SubtreeNames {
		public static final String CONDITIONAL = "Conditional";
		public static final String LIMIT = "Limit";
		public static final String CAUSE = "Cause";
		public static final String EFFECT = "Effect";
		public static final String PART_A = "PartA";
		public static final String PART_B = "PartB";
		// public static final String TMP = "TMP";
		public static final String HEAD = "Head";
		public static final String CONDITION = "Condition";
		public static final String VARIABLE = "Variable";
		public static final String VERB = "Verb";
		public static final String OBJECT = "Object";
		public static final String PREPOSITION = "Preposition";
		public static final String SOURCE = "Source";
		public static final String TARGET = "Target";
		public static final String ACTION = "Action";
		public static final String PARENT = "Parent";
		public static final String CHILD = "Child";
		public static final String NEW = "New";
		public static final String OLD = "Old";
		public static final String LABEL = "Label";
	}

	private static class RuleNames {
		public static final String TMP = "TMP";
		public static final String INHERITANCE = "Inheritance";
		public static final String COMPOSITION = "Composition";
		public static final String ACTION = "Action";
		public static final String UPDATE = "Update";
		public static final String REPLACE = "Replace";
		public static final String REMOVE = "Remove";
		public static final String LIMITED_CONDITION = "LimitedCondition";
		public static final String CONDITION = "Condition";
		public static final String CONJUNCTION = "Conjunction";
		public static final String NEGATION = "Negation";
		public static final String CONDITION_VARIABLE = "CondVar";
		public static final String VERB_OBJECT = "VerbObject";
		public static final String VERB_PREPOSITION = "VerbPreposition";
		public static final String XOR = "_XOR";
		public static final String NOR = "_NOR";
		public static final String OR = "_OR";
		public static final String AND = "_AND";
	}
	
	private boolean isTmp(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.TMP);
		boolean subMatches = result.hasSubmatch(SubtreeNames.HEAD);
		return name && subMatches;
	}

	private boolean isInheritance(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.INHERITANCE);
		boolean subMatches = result.hasSubmatch(SubtreeNames.PARENT) && result.hasSubmatch(SubtreeNames.CHILD);
		return name && subMatches;
	}

	private boolean isComposition(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.COMPOSITION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.PARENT) && result.hasSubmatch(SubtreeNames.CHILD);
		return name && subMatches;
	}

	private boolean isAction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.ACTION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.TARGET) && result.hasSubmatch(SubtreeNames.ACTION);
		return name && subMatches;
	}

	private boolean isUpdate(MatchResult result) {
		return isRemove(result) || isReplace(result);
	}

	private boolean isReplace(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.REPLACE);
		boolean subMatches = result.hasSubmatch(SubtreeNames.OLD) &&
				result.hasSubmatch(SubtreeNames.NEW);
		return name && subMatches;
	}
	private boolean isRemove(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.REMOVE);
		boolean subMatches = // result.hasSubmatch(SubtreeNames.PARENT) &&
				result.hasSubmatch(SubtreeNames.OLD);
		return name && subMatches;
	}

	private boolean isCondition(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.CONDITION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.CAUSE) && result.hasSubmatch(SubtreeNames.EFFECT);
		return name && subMatches;
	}

	private boolean isLimitedCondition(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.LIMITED_CONDITION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.LIMIT) && result.hasSubmatch(SubtreeNames.CONDITIONAL);
		return name && subMatches;
	}

	private boolean isConjunction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.CONJUNCTION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.PART_A) && result.hasSubmatch(SubtreeNames.PART_B);
		return name && subMatches;
	}

	private boolean isAndConjunction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.AND);
		return isConjunction(result) && name;
	}

	private boolean isOrConjunction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.OR);
		return isConjunction(result) && name;
	}

	private boolean isXorConjunction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.XOR);
		return isConjunction(result) && name;
	}

	private boolean isNorConjunction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.NOR);
		return isConjunction(result) && name;
	}

	private boolean isNegation(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.NEGATION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.HEAD);
		return name && subMatches;
	}

	private boolean isConditionVarible(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.CONDITION_VARIABLE);
		boolean subMatches = result.hasSubmatch(SubtreeNames.VARIABLE) && result.hasSubmatch(SubtreeNames.CONDITION);
		return name && subMatches;
	}

	private boolean isVerbObject(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.VERB_OBJECT);
		boolean subMatches = result.hasSubmatch(SubtreeNames.VERB) && result.hasSubmatch(SubtreeNames.OBJECT);
		return name && subMatches;
	}

	private boolean isVerbPreposition(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.VERB_PREPOSITION);
		boolean subMatches = result.hasSubmatch(SubtreeNames.VERB) && result.hasSubmatch(SubtreeNames.PREPOSITION);
		return name && subMatches;
	}

	private String getFirstArgumentName(MatchResult result) {
		if (isLimitedCondition(result)) {
			return SubtreeNames.LIMIT;
		} else if (isCondition(result)) {
			return SubtreeNames.CAUSE;
		} else if (isConjunction(result)) {
			return SubtreeNames.PART_A;
		} else if (isNegation(result)) {
			return SubtreeNames.HEAD;
		} else if (isConditionVarible(result)) {
			return SubtreeNames.VARIABLE;
		} else if (isVerbObject(result)) {
			return SubtreeNames.VERB;
		} else if (isVerbPreposition(result)) {
			return SubtreeNames.VERB;
		}

		// TODO MA subtree matchers
		else if (isTmp(result)) {
			return SubtreeNames.HEAD;
		} else if (isInheritance(result)) {
			return SubtreeNames.PARENT;
		} else if (isComposition(result)) {
			return SubtreeNames.CHILD;
		} else if (isAction(result)) {
			return SubtreeNames.SOURCE;
		} else if (isReplace(result)) {
			return SubtreeNames.OLD;
		} else if (isRemove(result)) {
			return SubtreeNames.OLD;
		}

		return null;
	}

	public Optional<MatchResultTreeNode> getFirstArgument(MatchResult result) {
		String name = getFirstArgumentName(result);
		if (name != null && result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name));
		}
		return Optional.empty();
	}

	private String getSecondArgumentName(MatchResult result) {
		if (isLimitedCondition(result)) {
			return SubtreeNames.CONDITIONAL;
		} else if (isCondition(result)) {
			return SubtreeNames.EFFECT;
		} else if (isConjunction(result)) {
			return SubtreeNames.PART_B;
		} else if (isConditionVarible(result)) {
			return SubtreeNames.CONDITION;
		} else if (isVerbObject(result)) {
			return SubtreeNames.OBJECT;
		} else if (isVerbPreposition(result)) {
			return SubtreeNames.PREPOSITION;
		}

		// TODO MA subtree matchers
		else if (isInheritance(result)) {
			return SubtreeNames.CHILD;
		} else if (isComposition(result)) {
			return SubtreeNames.PARENT;
		} else if (isAction(result)) {
			return SubtreeNames.TARGET;
		} else if (isUpdate(result)) {
			return SubtreeNames.NEW;
		} else if (isReplace(result)) {
			return SubtreeNames.NEW;
		}
		return null;
	}

	public Optional<MatchResultTreeNode> getSecondArgument(MatchResult result) {
		String name = getSecondArgumentName(result);
		if (name != null && result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name));
		}
		return Optional.empty();
	}

	private String getThirdArgumentName(MatchResult result) {
		if (isAction(result)) {
			return SubtreeNames.ACTION;
		}

		return null;
	}


	public Optional<MatchResultTreeNode> getThirdArgument(MatchResult result) {
		String name = getThirdArgumentName(result);
		if (name != null && result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name));
		}
		return Optional.empty();
	}

	public String getLabel(MatchResult result) {
		String name = getThirdArgumentName(result);
		String text = null;
		if (name != null && result.getSubmatch(name) != null) {
			Collection<Token> tokens = result.getSubmatch(name).getMatchTree().getHeads();
			if (tokens.size() == 0) {

			} else {
				for (Token t: tokens) {
					text = t.getCoveredText();
					break;
				}
			}

		}
		name = SubtreeNames.LABEL;

		if (result.getSubmatch(name) != null) {
			if (text == null) {
				text = "";
			} else {
				text = text + " ";
			}
			Collection<Token> tokens = result.getSubmatch(name).getMatchTree().getHeads();
			if (tokens.size() == 0) {

			} else {
				for (Token t: tokens) {
					text = text + t.getCoveredText();
					break;
				}
			}

		}
		return text;
	}

	public RuleType getType(MatchResult result) {
		if (isLimitedCondition(result)) {
			return RuleType.LIMITED_CONDITION;
		} else if (isCondition(result)) {
			return RuleType.CONDITION;
		} else if (isAndConjunction(result)) {
			return RuleType.CONJUNCTION_AND;
		} else if (isOrConjunction(result)) {
			return RuleType.CONJUNCTION_OR;
		} else if (isXorConjunction(result)) {
			return RuleType.CONJUNCTION_XOR;
		} else if (isNorConjunction(result)) {
			return RuleType.CONJUNCTION_NOR;
		} else if (isNegation(result)) {
			return RuleType.NEGATION;
		} else if (isVerbObject(result)) {
			return RuleType.VERB_OBJECT;
		} else if (isConditionVarible(result)) {
			return RuleType.CONDITION_VARIABLE;
		} else if (isVerbPreposition(result)) {
			return RuleType.VERB_PREPOSITION;
		}


		// TODO MA subtree matchers
		else if (isInheritance(result)) {
			return RuleType.INHERITANCE;
		} else if (isComposition(result)) {
			return RuleType.COMPOSITION;
		} else if (isAction(result)) {
			return RuleType.ACTION;
		} else if (isReplace(result)) {
			return RuleType.REPLACE;
		} else if (isRemove(result)) {
			return RuleType.REMOVE;
		}

		return null;
	}

	public Optional<MatchResultTreeNode> buildTree(MatchResult result) {
		if (!result.isSuccessfulMatch()) {
			return Optional.empty();
		}

		if (isTmp(result)) {
			String name = getFirstArgumentName(result);
			if (name != null) {
				return buildTree(result.getSubmatch(name));
			}
		}

		// Unary
		if (isNegation(result)) {
			MatchResultTreeNode clause = getFirstArgument(result).get();
			return Optional.of(new NegationTreeNode(clause));
		}

		// Binary
		if (isConditionVarible(result) || isVerbObject(result) || isVerbPreposition(result) || isConjunction(result)
				|| isCondition(result) || isLimitedCondition(result)

				|| isComposition(result) || isInheritance(result)) {
			MatchResultTreeNode left = getFirstArgument(result).get();
			MatchResultTreeNode right = getSecondArgument(result).get();

			String label = getLabel(result);
			return Optional.of(new BinaryMatchResultTreeNode(left, right, getType(result), label));
		}

		if (isAction(result)) {
			MatchResultTreeNode right = getSecondArgument(result).get();
			MatchResultTreeNode left = new LeafTreeNode("", "");
			// no source -> self node
			if (getFirstArgument(result).isPresent()) {
				left = getFirstArgument(result).get();
			}

			String label = getLabel(result);

			return Optional.of(new BinaryMatchResultTreeNode(left, right, getType(result), label));
		}
		if (isUpdate(result)) {
			if (isReplace(result)) {
				MatchResultTreeNode oldNode = getFirstArgument(result).get();
				MatchResultTreeNode newNode = getSecondArgument(result).get();

				return Optional.of(new BinaryMatchResultTreeNode(newNode, oldNode, getType(result)));
			} else if (isRemove(result)) {
				MatchResultTreeNode oldNode = getFirstArgument(result).get();
				LeafTreeNode tmp = new LeafTreeNode("", "");
				return Optional.of(new BinaryMatchResultTreeNode(tmp, oldNode, getType(result)));
			}
		}

		// Just Text
		LeafTreeNode leaf = new LeafTreeNode(
				result.getMatchTree().getRepresentationString(false), 
				((Token)result.getMatchTree().getHeads().toArray()[0]).getEnd() + ""
				);

		return Optional.of(leaf);
	}
}
