package com.specmate.cause_effect_patterns.parse.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.specmate.cause_effect_patterns.parse.matcher.MatchResult;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode.RuleType;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;

public class MatchTreeBuilder {
	private static class SubtreeNames {
		public static final String CONDITIONAL = "Conditional";
		public static final String LIMIT = "Limit";
		public static final String CAUSE = "Cause";
		public static final String EFFECT = "Effect";
		public static final String PART_A = "PartA";
		public static final String PART_B = "PartB";
		public static final String HEAD = "Head";
		public static final String CONDITION = "Condition";
		public static final String VARIABLE = "Variable";
		public static final String VERB = "Verb";
		public static final String OBJECT = "Object";
		public static final String PREPOSITION = "Preposition";
		public static final String PARENT = "Parent";
		public static final String CHILD = "Child";
		public static final String NEW = "New";
		public static final String OLD = "Old";
		public static final String LABEL = "Label";
		public static final String KEEP = "Keep";
		public static final String KEEP2 = "Keep2";
	}

	private static class RuleNames {
		public static final String TMP = "TMP";
		public static final String INHERITANCE = "Inheritance";
		public static final String COMPOSITION = "Composition";
		public static final String ACTION = "Action";
		public static final String PASSIVE = "Passive";
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
		boolean subMatches = result.hasSubmatch(SubtreeNames.KEEP);
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
		boolean subMatches = result.hasSubmatch(SubtreeNames.LABEL);
		return name && subMatches;
	}

	private boolean isPassiveAction(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.ACTION) &&
				result.hasRuleName() && result.getRuleName().contains(RuleNames.PASSIVE);
		boolean subMatches = result.hasSubmatch(SubtreeNames.LABEL) && ! result.hasSubmatch(SubtreeNames.PARENT);
		return name && subMatches;
	}

	private boolean isUpdate(MatchResult result) {
		return isRemove(result) || isReplace(result);
	}

	private boolean isReplace(MatchResult result) {
		boolean name = result.hasRuleName() && result.getRuleName().contains(RuleNames.REPLACE);
		boolean subMatches = result.hasSubmatch(SubtreeNames.OLD) && result.hasSubmatch(SubtreeNames.NEW);
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

	private boolean isConditionVariable(MatchResult result) {
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
		} else if (isConditionVariable(result)) {
			return SubtreeNames.VARIABLE;
		} else if (isVerbObject(result)) {
			return SubtreeNames.VERB;
		} else if (isVerbPreposition(result)) {
			return SubtreeNames.VERB;
		}

		else if (isTmp(result)) {
			return SubtreeNames.KEEP;
		} else if (isInheritance(result)) {
			return SubtreeNames.PARENT;
		} else if (isComposition(result)) {
			return SubtreeNames.CHILD;
		} else if (isAction(result)) {
			return SubtreeNames.PARENT;
		} else if (isReplace(result)) {
			return SubtreeNames.OLD;
		} else if (isRemove(result)) {
			return SubtreeNames.OLD;
		}

		return null;
	}
	private Optional<MatchResultTreeNode> getFirstArgument(MatchResult result) {
		return getFirstArgument(result, false);
	}
	public Optional<MatchResultTreeNode> getFirstArgument(MatchResult result, boolean isLabel) {
		String name = getFirstArgumentName(result);
		if (name != null && result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name), isLabel);
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
		} else if (isConditionVariable(result)) {
			return SubtreeNames.CONDITION;
		} else if (isVerbObject(result)) {
			return SubtreeNames.OBJECT;
		} else if (isVerbPreposition(result)) {
			return SubtreeNames.PREPOSITION;
		}

		else if (isTmp(result)) {
			return SubtreeNames.KEEP2;
		} else if (isInheritance(result)) {
			return SubtreeNames.CHILD;
		} else if (isComposition(result)) {
			return SubtreeNames.PARENT;
		} else if (isAction(result)) {
			return SubtreeNames.CHILD;
		} else if (isUpdate(result)) {
			return SubtreeNames.NEW;
		} else if (isReplace(result)) {
			return SubtreeNames.NEW;
		}
		return null;
	}

	private Optional<MatchResultTreeNode> getSecondArgument(MatchResult result) {
		return getSecondArgument(result, false);
	}
	public Optional<MatchResultTreeNode> getSecondArgument(MatchResult result, boolean isLabel) {
		String name = getSecondArgumentName(result);
		if (name != null && result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name), isLabel);
		}
		return Optional.empty();
	}
	public Optional<MatchResultTreeNode> getThirdArgument(MatchResult result) {
		String name = SubtreeNames.PARENT;
		if (result.getSubmatch(name) != null) {
			return buildTree(result.getSubmatch(name), false);
		}
		return Optional.empty();
	}

	public Optional<MatchResultTreeNode> getLabel(MatchResult result) {
		if (result.getSubmatch(SubtreeNames.LABEL) != null) {
			return buildTree(result.getSubmatch(SubtreeNames.LABEL), true);
		}
		return Optional.empty();
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
		} else if (isConditionVariable(result)) {
			return RuleType.CONDITION_VARIABLE;
		} else if (isVerbPreposition(result)) {
			return RuleType.VERB_PREPOSITION;
		}

		else if (isTmp(result)) {
			return RuleType.TMP;
		} else if (isInheritance(result)) {
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

	public MatchResultTreeNode parseLabelTree(MatchResultTreeNode root, BinaryMatchResultTreeNode node) {
		if (root instanceof BinaryMatchResultTreeNode) {
			switch (root.getType()) {
			case TMP:
				((BinaryMatchResultTreeNode) root)
						.setFirstArgument(parseLabelTree(((BinaryMatchResultTreeNode) root).getFirstArgument(), node));
				return root;
			case ACTION:
				System.err.println("Found a action node while parsing label tree");
				break;
			case COMPOSITION:
			case INHERITANCE:
				System.err.println("Found a composition/inheritance node while parsing label tree");
				break;
			case LIMITED_CONDITION:
				System.err.println("Found a limited condition node while parsing label tree");
				break;
			case CONDITION:
				((BinaryMatchResultTreeNode) root).setSecondArgument(
						parseLabelTree(((BinaryMatchResultTreeNode) root).getSecondArgument(), node));
				return root;
			case CONJUNCTION_AND:
			case CONJUNCTION_NOR:
			case CONJUNCTION_OR:
			case CONJUNCTION_XOR:
				((BinaryMatchResultTreeNode) root)
						.setFirstArgument(parseLabelTree(((BinaryMatchResultTreeNode) root).getFirstArgument(), node));
				return root;
			case REMOVE:
				System.err.println("Found a remove node while parsing label tree");
				break;
			case REPLACE:
				System.err.println("Found a replace node while parsing label tree");
				break;
//			case VERB_OBJECT:
//			case VERB_PREPOSITION:
//			case CONDITION_VARIABLE:
//			case NEGATION:

			default:
			}
		} else if (root instanceof NegationTreeNode) {
			((NegationTreeNode) root).setClause(parseLabelTree(((NegationTreeNode) root).getClause(), node));
			return root;
		} else if (root instanceof LeafTreeNode) {
			node.setLabel((LeafTreeNode) root);
			return node;
		}
		System.err.println("Return node without doing anything");
		return node;
	}
	public Optional<MatchResultTreeNode> buildTree(MatchResult result) {
		return buildTree(result, false);
	}
	private Optional<MatchResultTreeNode> buildTree(MatchResult result, boolean isLabel) {
		if (!result.isSuccessfulMatch()) {
			return Optional.empty();
		}

		if (isTmp(result)) {
			MatchResultTreeNode left = getFirstArgument(result, isLabel).get();
			MatchResultTreeNode right = getSecondArgument(result, isLabel).isPresent() ? getSecondArgument(result, isLabel).get() : null;
			if (right == null) {
				return Optional.of(left);
			}
			return Optional.of(new BinaryMatchResultTreeNode(left, right, getType(result)));
		}

		// Unary
		if (isNegation(result)) {
			MatchResultTreeNode clause = getFirstArgument(result).get();
			return Optional.of(new NegationTreeNode(clause));
		}

		// Binary
		if (isConditionVariable(result) || isVerbObject(result) || isVerbPreposition(result) || isConjunction(result)
				|| isCondition(result) || isLimitedCondition(result) || isComposition(result)
				|| isInheritance(result)) {
			boolean label = isConjunction(result) ? isLabel : false;
			MatchResultTreeNode left = getFirstArgument(result, label).get();
			MatchResultTreeNode right = getSecondArgument(result, label).get();

			MatchResultTreeNode labelTree = getLabel(result).isPresent() ? getLabel(result).get() : null;
			if (labelTree != null
					&& (labelTree instanceof BinaryMatchResultTreeNode || labelTree instanceof NegationTreeNode)) {
				BinaryMatchResultTreeNode tmp = new BinaryMatchResultTreeNode(left, right, getType(result));
				return Optional.of(parseLabelTree(labelTree, tmp));
			} else { // if (labelTree instanceof LeafTreeNode)
				return Optional
						.of(new BinaryMatchResultTreeNode(left, right, getType(result), ((LeafTreeNode) labelTree)));
			}
		}

		if (isAction(result)) {
			MatchResultTreeNode obj = getSecondArgument(result).isPresent() ? getSecondArgument(result).get() 
					: new LeafTreeNode(null, null, false);
			MatchResultTreeNode verb = getLabel(result).isPresent()? getLabel(result).get() : null;
			MatchResultTreeNode subj = getFirstArgument(result).isPresent() ? getFirstArgument(result).get()
					: isPassiveAction(result) ? new LeafTreeNode("", null, false) : new LeafTreeNode(null, null, false);
			MatchResultTreeNode tmp2;
			if (verb instanceof BinaryMatchResultTreeNode || verb instanceof NegationTreeNode) {
				BinaryMatchResultTreeNode tmp = new BinaryMatchResultTreeNode(subj, obj, getType(result));
				tmp2 = parseLabelTree(verb, tmp);
			} else { // if (labelTree instanceof LeafTreeNode)
				tmp2 = new BinaryMatchResultTreeNode(subj, obj, getType(result), ((LeafTreeNode) verb));
			}
			
			return Optional.of(tmp2);
		}
		if (isUpdate(result)) {
			if (isReplace(result)) {
				MatchResultTreeNode oldNode = getFirstArgument(result).get();
				MatchResultTreeNode newNode = getSecondArgument(result).get();
				
				if (getThirdArgument(result).isPresent()) {
					// if has parent subree, insert inheritance node
					return Optional.of(new BinaryMatchResultTreeNode(getThirdArgument(result).get(), 
							new BinaryMatchResultTreeNode(newNode, oldNode, getType(result))
							, RuleType.INHERITANCE));
				}

				return Optional.of(new BinaryMatchResultTreeNode(newNode, oldNode, getType(result)));
			} else if (isRemove(result)) {
				MatchResultTreeNode oldNode = getFirstArgument(result).get();
				LeafTreeNode tmp = new LeafTreeNode("", null, false);
				return Optional.of(new BinaryMatchResultTreeNode(tmp, oldNode, getType(result)));
			}
		}

		// Just Text
		
		List<Integer> ids = new ArrayList<Integer>();
		for (Token t : result.getMatchTree().getHeads()) {
			addIds(result, ids, t);
		}

		for (Token token: result.getMatchTree().getDependencies().keySet()) {
			addIds(result, ids, token);
		}
	
		LeafTreeNode leaf = new LeafTreeNode(result.getMatchTree().getRepresentationString(false),
				ids, isLabel);

		return Optional.of(leaf);
	}
	
	private void addIds(MatchResult result, List<Integer> ids, Token token) {
		ids.add(token.getEnd());
		if (result.getMatchTree().getDependencyNode(token)==null)
			return;
		for (Dependency d : result.getMatchTree().getDependencyNode(token)) {
			addIds(result, ids, d.getDependent());
		}
	}
}
