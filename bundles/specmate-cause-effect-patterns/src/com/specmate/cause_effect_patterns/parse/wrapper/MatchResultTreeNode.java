package com.specmate.cause_effect_patterns.parse.wrapper;

public abstract class MatchResultTreeNode {
	public static enum RuleType {
		LIMITED_CONDITION, CONDITION, CONJUNCTION_AND, CONJUNCTION_OR, CONJUNCTION_NOR, CONJUNCTION_XOR, NEGATION,
		CONDITION_VARIABLE, VERB_OBJECT, VERB_PREPOSITION, COMPOSITION, INHERITANCE, ACTION, UPDATE;

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
			return equals(UPDATE);
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
			// TODO MA order
			case INHERITANCE:
				return 2;
			case COMPOSITION:
				return 1;
//			case ACTION:
//				return 3;
			case LIMITED_CONDITION:
				return 4;
			case CONDITION:
				return 5;
			case CONJUNCTION_XOR:
				return 6;
			case CONJUNCTION_NOR:
				return 7;
			case CONJUNCTION_OR:
				return 8;
			case CONJUNCTION_AND:
				return 9;
			case CONDITION_VARIABLE:
				return 10;
			case VERB_OBJECT:
				return 11;
			case VERB_PREPOSITION:
				return 12;
			default: // Negation should not be moved
				return -1;
			}
		}
	}

	public abstract RuleType getType();

	public abstract void acceptVisitor(MatchTreeVisitor visitor);
}
