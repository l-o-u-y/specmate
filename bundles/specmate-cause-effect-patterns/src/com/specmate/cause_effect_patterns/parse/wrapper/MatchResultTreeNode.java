package com.specmate.cause_effect_patterns.parse.wrapper;

public abstract class MatchResultTreeNode {
	public static enum RuleType {
		LIMITED_CONDITION, CONDITION, CONJUNCTION_AND, CONJUNCTION_OR, CONJUNCTION_NOR, CONJUNCTION_XOR, NEGATION,
		CONDITION_VARIABLE, VERB_OBJECT, VERB_PREPOSITION, COMPOSITION, INHERITANCE, ACTION, REPLACE, REMOVE;

		public boolean isComposition() {
			return equals(COMPOSITION);
		}
		
		public boolean isInheritance() {
			return equals(INHERITANCE);
		}

		public boolean isAction() {
			return equals(ACTION);
		}
		
		public boolean isUpdate() {
			return isRemove() || isReplace();
		}

		public boolean isReplace() {
			return equals(REPLACE);
		}
		public boolean isRemove() {
			return equals(REMOVE);
		}

		public boolean isCondition() {
			return equals(CONDITION);
		}

		public boolean isLimitedCondition() {
			return equals(LIMITED_CONDITION);
		}

		public boolean isConjunction() {
			return equals(CONJUNCTION_AND) || equals(CONJUNCTION_OR) || equals(CONJUNCTION_NOR)
					|| equals(CONJUNCTION_XOR);
		}

		public boolean isNegation() {
			return equals(NEGATION);
		}

		public boolean isXorConjunction() {
			return equals(CONJUNCTION_XOR);
		}

		public boolean isNorConjunction() {
			return equals(CONJUNCTION_NOR);
		}

		public boolean isOrConjunction() {
			return equals(CONJUNCTION_OR);
		}

		public int getPriority() {
			switch (this) {
			// TODO MA subtree order
			// smaller value = closer to root
			// larger value = closer to leaf node
			case LIMITED_CONDITION:
				return 1;
			case CONDITION:
				return 2;
			case COMPOSITION:
				return 3;
			case INHERITANCE:
				return 4;
			case REPLACE:
				return 5;
			case REMOVE:
				return 5;
			case ACTION:
				return 6;
			case CONJUNCTION_XOR:
				return 8;
			case CONJUNCTION_NOR:
				return 9;
			case CONJUNCTION_OR:
				return 10;
			case CONJUNCTION_AND:
				return 11;
			case CONDITION_VARIABLE:
				return 12;
			case VERB_OBJECT:
				return 13;
			case VERB_PREPOSITION:
				return 14;
			default: // Negation should not be moved
				return -1;
			}
		}
	}

	public abstract RuleType getType();

	public abstract void acceptVisitor(MatchTreeVisitor visitor);
}
