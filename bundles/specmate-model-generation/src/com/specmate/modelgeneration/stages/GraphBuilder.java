package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.maltparser.core.exception.MaltChainedException;
import org.maltparser.core.syntaxgraph.node.ComparableNode;

import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode.RuleType;
import com.specmate.cause_effect_patterns.parse.wrapper.NegationTreeNode;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGNode;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphNode;
import com.specmate.modelgeneration.stages.processors.ConditionVariableNode;

public class GraphBuilder {

	private Graph currentGraph;

	public synchronized Graph buildCEGGraph(BinaryMatchResultTreeNode root) {
		List<MatchResultTreeNode> clauses = getClauses(root);
		MatchResultTreeNode effect = clauses.remove(clauses.size() - 1);
		List<MatchResultTreeNode> causes = clauses;

		currentGraph = new Graph();
		DirectCause cause = resolveCauses(causes);
		resolveEffect(cause, effect);

		Graph result = currentGraph;
		currentGraph = null;
		return result;
	}

	public synchronized Graph buildRGGraph(BinaryMatchResultTreeNode root) {
		currentGraph = new Graph();
		buildRGNode(root);

		Graph result = currentGraph;
		return result;
	}

	public synchronized void connectRGNodes(RGNodes parent, RGNodes child, MatchResultTreeNode node) {
		final RGNodes modifiedChild = new RGNodes();
		modifiedChild.positiveNodes.addAll(child.positiveNodes.stream()
				.filter(e -> e.getParentEdges().stream()
						.filter(f -> f.getType().equals(RGConnectionType.ACTION)
								|| f.getType().equals(RGConnectionType.COMPOSITION)
								|| f.getType().equals(RGConnectionType.INHERITANCE))
						.collect(Collectors.toList()).size() == 0)
				.collect(Collectors.toList()));

		modifiedChild.negativeNodes.addAll(child.negativeNodes.stream()
				.filter(e -> e.getParentEdges().stream()
						.filter(f -> f.getType().equals(RGConnectionType.ACTION)
								|| f.getType().equals(RGConnectionType.COMPOSITION)
								|| f.getType().equals(RGConnectionType.INHERITANCE))
						.collect(Collectors.toList()).size() == 0)
				.collect(Collectors.toList()));
		
		
		
		for (GraphNode p : parent.positiveNodes) {
			// sets parent GraphNode type to child RGNode type
			p.setType(child.nodeType);
			String label = getLabel(node).equals("null;-1") ? null : getLabel(node).split(";")[0];
			if (!p.getPrimaryText().equals("")) {
				for (GraphNode c : modifiedChild.positiveNodes) {
					p.connectTo(c, getConnectionType(node), false, label);
				}
				for (GraphNode c : modifiedChild.negativeNodes) {
					p.connectTo(c, getConnectionType(node), true, label);
				}
			}
		}
	}

	public synchronized String getLabel(MatchResultTreeNode node) {
		if (node == null) {
			return null;
		} else if (node != null && node instanceof BinaryMatchResultTreeNode) {
			return ((BinaryMatchResultTreeNode) node).getLabel();
		}
		return null;
	}
	
	private synchronized RGNodes createNodeFromLabel(MatchResultTreeNode node) {
		final GraphNode v = currentGraph.createInnerNode(NodeType.AND);
		v.setPrimaryText(getLabel(node).split(";")[0]);
		v.setId(getLabel(node).split(";")[1]);
		v.setExclusive(true);
		
		return new RGNodes(v, NodeType.AND, false);
	}

	public synchronized RGConnectionType getConnectionType(MatchResultTreeNode node) {
		if (node == null) {
			return null;
		} else if (node.getType().equals(RuleType.ACTION)) {
			return RGConnectionType.ACTION;
		} else if (node.getType().equals(RuleType.COMPOSITION)) {
			return RGConnectionType.COMPOSITION;
		} else if (node.getType().equals(RuleType.INHERITANCE)) {
			return RGConnectionType.INHERITANCE;
		} else if (node.getType().equals(RuleType.REPLACE)) {
			return RGConnectionType.REPLACE;
		} else if (node.getType().equals(RuleType.CONDITION)) {
			return RGConnectionType.CONDITION;
		} else {
			System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");
			return null;
		}
	}

	public synchronized RGNodes buildRGNode(MatchResultTreeNode node) {
		if (node.getType() == null) {
			// LeafNode
		} else if (node.getType().equals(RuleType.CONDITION)) {
			final RGNodes cause = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes effect = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			connectRGNodes(cause, effect, node);
			return effect;

		} else if (node.getType().equals(RuleType.CONDITION_VARIABLE)) {
			// condition
			return buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
		}  else if (node.getType().equals(RuleType.LIMITED_CONDITION)) {
			final RGNodes limit = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes condition = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");

		}  else if (node.getType().equals(RuleType.VERB_OBJECT)) {
			final RGNodes verb = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			return verb; 
		}  else if (node.getType().equals(RuleType.VERB_PREPOSITION)) {
			
			System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");
			
		} else if (node.getType().equals(RuleType.COMPOSITION) || node.getType().equals(RuleType.INHERITANCE)) {
			final RGNodes first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			final RGNodes label = createNodeFromLabel(node);
			
			if (node.getType().equals(RuleType.INHERITANCE)) {
				connectRGNodes(first, label, node);
				connectRGNodes(label, second, node);
			} else {
				connectRGNodes(second, label, node);
				connectRGNodes(label, first, node);
			}

			return second;

		} else if (node.getType().equals(RuleType.ACTION)) {
			// Pre: S -> V
			// Post: O -> V
			final RGNodes subj = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes obj = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			final RGNodes verb = createNodeFromLabel(node);
			
			connectRGNodes(subj, verb, node);
			connectRGNodes(verb, obj, node);
			return verb;
			
//			// filter nodes; we only want nodes without parents
//			// no parents means a conjunction with the same subject: A does 1 and 2
//			// a parent means a conjunction with a different subject: A does 1 and B does 2 
//			final RGNodes modifiedNoun = new RGNodes();
//			modifiedNoun.positiveNodes.addAll(noun.positiveNodes.stream()
//					.filter(e -> e.getParentEdges().stream()
//							.filter(f -> !f.getType().equals(RGConnectionType.REMOVE)
//									&& !f.getType().equals(RGConnectionType.REPLACE))
//							.collect(Collectors.toList()).size() == 0)
//					.collect(Collectors.toList()));
//
//			modifiedNoun.negativeNodes.addAll(noun.negativeNodes.stream()
//					.filter(e -> e.getParentEdges().stream()
//							.filter(f -> !f.getType().equals(RGConnectionType.REMOVE)
//									&& !f.getType().equals(RGConnectionType.REPLACE))
//							.collect(Collectors.toList()).size() == 0)
//					.collect(Collectors.toList()));
//			
//			if (node.getType().equals(RuleType.ACTION_PRE)) {
//				connectRGNodes(modifiedNoun, verb, node);
//			} else {
//				connectRGNodes(verb, modifiedNoun, node);
//			}
//
//			return verb;

		} else if (node.getType().equals(RuleType.REMOVE)) {
			final RGNodes old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
			for (GraphNode o : old.negativeNodes) {
				o.setMarkedForDeletion(true);
			}
			for (GraphNode o : old.positiveNodes) {
				o.setMarkedForDeletion(true);
			}
			return old;

		} else if (node.getType().equals(RuleType.REPLACE)) {
			final RGNodes old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
			for (GraphNode o : old.negativeNodes) {
				o.setMarkedForDeletion(true);
			}
			for (GraphNode o : old.positiveNodes) {
				o.setMarkedForDeletion(true);
			}
			final RGNodes neww = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			connectRGNodes(old, neww, node);
			return old;
		}

		else if (node.getType().equals(RuleType.CONJUNCTION_AND)) {
			final RGNodes first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			return new RGNodes(first, second, NodeType.AND);
		} else if (node.getType().equals(RuleType.CONJUNCTION_OR)) {
			final RGNodes first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			return new RGNodes(first, second, NodeType.OR);
		} else if (node.getType().equals(RuleType.CONJUNCTION_NOR)) {
			final RGNodes first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

			return new RGNodes(first, second, NodeType.AND).swap();
		} else if (node.getType().equals(RuleType.CONJUNCTION_XOR)) {
			final RGNodes first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			final RGNodes second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
			final RGNodes parent2 = new RGNodes(currentGraph.createInnerNode(NodeType.AND), NodeType.AND, false);
			final RGNodes parent3 = new RGNodes(currentGraph.createInnerNode(NodeType.AND), NodeType.AND, false);
			connectRGNodes(parent2, first, null);
			connectRGNodes(parent3, second, null);
			first.swap();
			second.swap();
			connectRGNodes(parent2, second, null);
			connectRGNodes(parent3, first, null);
			return new RGNodes(parent2, parent3, NodeType.OR);
		} else if (node.getType().equals(RuleType.NEGATION)) {
			final RGNodes first = buildRGNode(((NegationTreeNode) node).getClause());

			return first.swap();
		}
		

		// if (node instanceof LeafTreeNode) {
		String text = ((LeafTreeNode) node).getContent();

		// TODO MA misc: maybe parse "no xx" and ¡°xx or yy¡±
		/*
		 * if (text.startsWith("no") && text.matches("no\\s(.*)")) { // ex: no items
		 * final GraphNode n = currentGraph.createInnerNode(NodeType.AND);
		 * n.setComponent(text.replaceAll("no\\s(.*)", "$1"));
		 * 
		 * return new RGNodes(n, n.getType(), true); } else if
		 * (text.matches("(.*)\\sor\\s([^\\s]*)\\s(.*)")) { //ex: only one or two items
		 * final GraphNode f = currentGraph.createInnerNode(NodeType.AND);
		 * f.setComponent(text.replaceAll("(.*)\\sor\\s([^\\s]*)\\s(.*)", "$1 $3"));
		 * RGNodes first = new RGNodes(f, NodeType.AND, false);
		 * 
		 * final GraphNode s = currentGraph.createInnerNode(NodeType.AND);
		 * s.setComponent(text.replaceAll("(.*)\\sor\\s([^\\s]*)\\s(.*)", "$2 $3"));
		 * RGNodes second = new RGNodes(s, NodeType.AND, false);
		 * 
		 * return new RGNodes(first, second, NodeType.OR); } else {
		 */
		final GraphNode n = currentGraph.createInnerNode(NodeType.AND);
		n.setPrimaryText(text);
		n.setId(((LeafTreeNode) node).getId());
		return new RGNodes(n, n.getType(), false);
		// }
	}

	/**
	 * Returns a list consisting of all causes and the effect as last element [cause
	 * 1, cause 2, ..., cause n, effect]
	 */
	private List<MatchResultTreeNode> getClauses(BinaryMatchResultTreeNode root) {

		Vector<MatchResultTreeNode> worklist = new Vector<MatchResultTreeNode>();
		worklist.add(root);
		MatchResultTreeNode effect = root;
		Vector<MatchResultTreeNode> result = new Vector<MatchResultTreeNode>();

		while (!worklist.isEmpty()) {
			MatchResultTreeNode current = worklist.remove(0);
			if (current.getType().isCondition()) {
				MatchResultTreeNode left = ((BinaryMatchResultTreeNode) current).getFirstArgument();
				MatchResultTreeNode right = ((BinaryMatchResultTreeNode) current).getSecondArgument();
				worklist.add(left);
				worklist.add(right);
				if (current == effect) {
					effect = right;
				}
			} else {
				if (current != effect) {
					result.add(current);
				}
			}
		}
		result.add(effect);
		return result;
	}

	private DirectCause resolveCauses(List<MatchResultTreeNode> causes) {
		final DirectCause result = new DirectCause();
		for (int i = 0; i < causes.size(); i++) {
			final MatchResultTreeNode cause = causes.get(i);
			// Resolve Single Cause
			final DirectCause dirCause = resolveCause(cause);

			final int dirCauseCauseSize = dirCause.positiveCauses.size() + dirCause.negativeCauses.size();

			// If there are multiple causes
			if (causes.size() > 1 && dirCauseCauseSize > 1) {
				// Generate Fake Effect Nodes
				final GraphNode node = currentGraph.createInnerNode(dirCause.effectType);
				fullyConnect(dirCause, node);
				// Add Fake Effect Node to DirectCause Positive Causes
				result.positiveCauses.add(node);
			} else {
				// Add Resolved Positive/Negative Causes to DirectCause
				result.effectType = dirCause.effectType;
				result.negativeCauses.addAll(dirCause.negativeCauses);
				result.positiveCauses.addAll(dirCause.positiveCauses);
			}
		}

		return result;
	}

	private DirectCause resolveCause(MatchResultTreeNode cause) {
		final DirectCause result = new DirectCause();

		if (cause.getType().isConjunction()) {
			BinaryMatchResultTreeNode biCause = (BinaryMatchResultTreeNode) cause;
			final List<MatchResultTreeNode> worklist = new LinkedList<MatchResultTreeNode>(
					Arrays.asList(biCause.getFirstArgument(), biCause.getSecondArgument()));
			final Vector<DirectCause> arguments = new Vector<DirectCause>();

			final RuleType type = cause.getType();
			while (!worklist.isEmpty()) {
				final MatchResultTreeNode wrap = worklist.remove(0);
				final RuleType typeWrap = wrap.getType();
				if (type.equals(typeWrap)) {
					worklist.add(((BinaryMatchResultTreeNode) wrap).getFirstArgument());
					worklist.add(((BinaryMatchResultTreeNode) wrap).getSecondArgument());
				} else {
					final DirectCause argument = resolveCause(wrap);
					final int argCauseCount = argument.positiveCauses.size() + argument.negativeCauses.size();
					if (argCauseCount > 1) {
						// Create Inner Node
						final GraphNode node = currentGraph.createInnerNode(argument.effectType);
						fullyConnect(argument, node);
						argument.positiveCauses.clear();
						argument.negativeCauses.clear();
						argument.positiveCauses.add(node);
					}
					arguments.add(argument);
				}
			}

			final DirectCause merge = DirectCause.mergeCauses(arguments.toArray(new DirectCause[arguments.size()]));

			if (cause.getType().isXorConjunction()) {
				// Create inner XOR Nodes use them as new positive set
				// Create XOR Node
				// Directly connect all other nodes normally
				// Connect one node negated
				// Add XOR Node to positiveCauses

				for (GraphNode pNode : merge.positiveCauses) {
					GraphNode xorNode = currentGraph.createInnerNode(NodeType.AND);
					xorConnect(merge, xorNode, pNode);
					result.positiveCauses.add(xorNode);
				}

				for (GraphNode nNode : merge.negativeCauses) {
					GraphNode xorNode = currentGraph.createInnerNode(NodeType.AND);
					xorConnect(merge, xorNode, nNode);
					result.positiveCauses.add(xorNode);
				}
				result.effectType = NodeType.OR;
			} else if (cause.getType().isNorConjunction()) {
				// Swap positive & negative causes, then AND
				// XXX Technically NOR is not associative so a nested NOR would turn up with a
				// not equivalent result, however since nested NORs usually don't happen we
				// ignore this case.
				final List<GraphNode> tmp = merge.negativeCauses;
				merge.negativeCauses = merge.positiveCauses;
				merge.positiveCauses = tmp;
			} else if (cause.getType().isOrConjunction()) {
				result.effectType = NodeType.OR;
				// Swap result type, then AND
			}
			// Default / AND
			// Combine positive & negative causes of dA & dB in result
			result.addCauses(merge);
		} else if (cause.getType().isNegation()) {
			NegationTreeNode negNode = (NegationTreeNode) cause;
			final DirectCause dHead = resolveCause(negNode.getClause());
			result.positiveCauses = dHead.negativeCauses;
			result.negativeCauses = dHead.positiveCauses;
		} else {
			// Create Direct Node
			ConditionVariableNode cvNode = (ConditionVariableNode) cause;

			GraphNode node = currentGraph.createNode(cvNode.getSecondary(), cvNode.getPrimary(), NodeType.AND);
			result.positiveCauses.add(node);
		}
		return result;
	}

	private void xorConnect(DirectCause dirCause, GraphNode node, GraphNode invertedNode) {
		for (final GraphNode pCause : dirCause.positiveCauses) {
			boolean inverted = false;
			if (pCause.equals(invertedNode)) {
				inverted = true;
			}
			pCause.connectTo(node, inverted);
		}
		for (final GraphNode nCause : dirCause.negativeCauses) {
			boolean inverted = true;
			if (nCause.equals(invertedNode)) {
				inverted = false;
			}

			nCause.connectTo(node, inverted);
		}
	}

	private void resolveEffect(DirectCause cause, MatchResultTreeNode effect) {
		if (effect.getType().isConjunction()) {
			// Resolve Conjunctions
			resolveEffect(cause, ((BinaryMatchResultTreeNode) effect).getFirstArgument());
			resolveEffect(cause, ((BinaryMatchResultTreeNode) effect).getSecondArgument());
		} else if (effect.getType().isNegation()) {
			// Resolve Negations
			resolveEffect(cause.swapPosNegCauses(), ((NegationTreeNode) effect).getClause());
		} else {
			ConditionVariableNode cvNode = (ConditionVariableNode) effect;
			GraphNode node = currentGraph.createNode(cvNode.getSecondary(), cvNode.getPrimary(), cause.effectType);
			fullyConnect(cause, node);
		}
	}

	private void fullyConnect(DirectCause dirCause, GraphNode node) {
		for (final GraphNode pCause : dirCause.positiveCauses) {
			pCause.connectTo(node, false);
		}
		for (final GraphNode nCause : dirCause.negativeCauses) {
			nCause.connectTo(node, true);
		}
	}

	private static class DirectCause {
		public List<GraphNode> positiveCauses;
		public List<GraphNode> negativeCauses;
		public NodeType effectType;

		public DirectCause() {
			positiveCauses = new Vector<GraphNode>();
			negativeCauses = new Vector<GraphNode>();
			effectType = NodeType.AND;
		}

		public void addCauses(DirectCause cause) {
			negativeCauses.addAll(cause.negativeCauses);
			positiveCauses.addAll(cause.positiveCauses);
		}

		public static DirectCause mergeCauses(DirectCause... causes) {
			final DirectCause result = new DirectCause();
			for (final DirectCause subCause : causes) {
				result.addCauses(subCause);
			}
			return result;
		}

		public DirectCause swapPosNegCauses() {
			final DirectCause result = new DirectCause();
			result.effectType = effectType == NodeType.AND ? NodeType.OR : NodeType.AND;
			result.negativeCauses = positiveCauses;
			result.positiveCauses = negativeCauses;
			return result;
		}
	}

	private class RGNodes {
		public ArrayList<GraphNode> positiveNodes;
		public ArrayList<GraphNode> negativeNodes;
		public NodeType nodeType;

		public RGNodes() {
			positiveNodes = new ArrayList<GraphNode>();
			negativeNodes = new ArrayList<GraphNode>();
			nodeType = NodeType.AND;
		}

		public RGNodes(GraphNode node, NodeType type, boolean negated) {
			positiveNodes = new ArrayList<GraphNode>();
			negativeNodes = new ArrayList<GraphNode>();
			if (!negated) {
				addPositiveNode(node);
			} else {
				addNegativeNode(node);
			}
			nodeType = type;
		}

		public RGNodes(RGNodes first, RGNodes second, NodeType type) {
			positiveNodes = new ArrayList<GraphNode>();
			negativeNodes = new ArrayList<GraphNode>();
			this.positiveNodes.addAll(first.positiveNodes);
			this.positiveNodes.addAll(second.positiveNodes);
			this.negativeNodes.addAll(first.negativeNodes);
			this.negativeNodes.addAll(second.negativeNodes);
			nodeType = type;
		}

		public RGNodes swap() {
			ArrayList<GraphNode> tmp = this.negativeNodes;
			this.negativeNodes = this.positiveNodes;
			this.positiveNodes = tmp;
			return this;
		}

		public void addPositiveNode(GraphNode node) {
			positiveNodes.add(node);
		}

		public void addNegativeNode(GraphNode node) {
			negativeNodes.add(node);
		}

		// public void addNodes(RGNodes nodes) {
		// positiveNodes.addAll(nodes.positiveNodes);
		// negativeNodes.addAll(nodes.negativeNodes);
		// }

		// public void setNodeType(NodeType type) {
		// nodeType = type;
		// }
	}
}
