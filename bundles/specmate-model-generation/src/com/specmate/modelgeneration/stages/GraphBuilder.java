package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode.RuleType;
import com.specmate.cause_effect_patterns.parse.wrapper.NegationTreeNode;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;
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
		NodeWrapper cause = resolveCauses(causes);
		resolveEffect(cause, effect);

		Graph result = currentGraph;
		currentGraph = null;
		return result;
	}

	public synchronized Graph buildRGGraph(BinaryMatchResultTreeNode root) {
		currentGraph = new Graph();
		buildRGNode(root, false);

		Graph result = currentGraph;
		return result;
	}

	private synchronized void connectRGNodes(NodeWrapper parent, NodeWrapper child, MatchResultTreeNode node) {
		for (GraphNode p : parent.positiveNodes) {
			String label = null;
			for (GraphNode c : child.positiveNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), false, label);
			}
			for (GraphNode c : child.negativeNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), true, label);
			}
		}
		for (GraphNode p : parent.negativeNodes) {
			String label = null;
			for (GraphNode c : child.positiveNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), true, label);// TODO MA hmmm flip?
			}
			for (GraphNode c : child.negativeNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), false, label);
			}
		}
	}

	private synchronized RGConnectionType getConnectionType(MatchResultTreeNode node) {
		if (node == null) {
			return RGConnectionType.CONDITION;
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

	private synchronized boolean isConnected(GraphNode node) {
		// what does connected mean?
		// composition: has childEdge of type COMPOSITION
		// inheritance: has parentEdge of type INHERITANCE
		// action: has parentEdge of type ACTION
		// remove: nothing
		// replace: nothing
		if (node.getChildEdges().stream().filter(e -> e.getType().equals(RGConnectionType.COMPOSITION)).count() > 0) {
			return true;
		}
		if (node.getParentEdges().stream().filter(
				e -> e.getType().equals(RGConnectionType.ACTION) || e.getType().equals(RGConnectionType.INHERITANCE))
				.count() > 0) {
			return true;
		}
		return false;
	}

	private synchronized NodeWrapper buildRGNode(MatchResultTreeNode node, boolean returnVerb) {
		if (node.getType() == null) {
		} else {
			switch (node.getType()) {
			case TMP: {
				buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), returnVerb);
				return buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(), returnVerb);
			}
			case CONDITION: {
				final NodeWrapper cause = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(), true);
				final NodeWrapper effect = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), true);

				effect.childType = cause.childType;

				connectRGNodes(cause, effect, node);
				return effect;
			}

//			case CONDITION_VARIABLE: {
//				return buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
//			}
//			case VERB_OBJECT: {
//				final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
//				return verb;
//			}

			case LIMITED_CONDITION: {
				final NodeWrapper limit = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(),
						returnVerb);
				final NodeWrapper condition = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(),
						returnVerb);

				System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");
				break;
			}
//			case VERB_PREPOSITION: {
//				System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");
//				break;
//			}

			case COMPOSITION:
			case INHERITANCE: {
				final NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(), false);
				final NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), false);

//				for (GraphNode n : Stream.concat(first.positiveNodes.stream(), first.negativeNodes.stream()).collect(Collectors.toList())) {
//					final NodeWrapper f = new NodeWrapper(n, first.childType, first.negativeNodes.contains(n));
//					for (GraphNode m : Stream.concat(second.positiveNodes.stream(), second.negativeNodes.stream()).collect(Collectors.toList())) {
//						final NodeWrapper s = new NodeWrapper(m, second.childType, second.negativeNodes.contains(n));
//
				final NodeWrapper label;
				if (((BinaryMatchResultTreeNode) node).getLabel() != null) {
					label = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel(), false);
				} else {
					label = new NodeWrapper(currentGraph.createInnerNode(NodeType.NONE), NodeType.NONE, false);
				}

				label.setExclusive();
				if (node.getType().equals(RuleType.INHERITANCE)) {
					// first = parent
					// second = child
					// child -> parent
					connectRGNodes(first, label, node);
					connectRGNodes(label, second, node);
				} else { // COMPOSITION
					// first = child
					// second = parent
					// parent -> child
					connectRGNodes(second, label, node);
					connectRGNodes(label, first, node);
				}
//					}
//				}
				if (returnVerb) {
					return label;
				}
				return second;
			}

			case ACTION: {
				MatchResultTreeNode s = ((BinaryMatchResultTreeNode) node).getFirstArgument();
				final NodeWrapper obj = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), false);

				NodeWrapper verbs = new NodeWrapper();

				if (s instanceof LeafTreeNode && ((LeafTreeNode) s).getId().contentEquals("-1")) {
					final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel(), false);
					verb.setExclusive();
					connectRGNodes(verb, obj, node);
					return verb;

//						for (GraphNode n : Stream.concat(obj.positiveNodes.stream(), obj.negativeNodes.stream()).collect(Collectors.toList())) {
//							final NodeWrapper o = new NodeWrapper(n, obj.childType, obj.negativeNodes.contains(n));
//							
//							final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel());
//							verb.setExclusive();
//							verbs.addAllNodes(verb);
//							connectRGNodes(verb, o, node);
//						}

				} else {
					final NodeWrapper subj = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(), false);
					final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel(), false);
					verb.setExclusive();
					connectRGNodes(subj, verb, node);
					connectRGNodes(verb, obj, node);
					return verb;

//					for (GraphNode n : Stream.concat(subj.positiveNodes.stream(), subj.negativeNodes.stream()).collect(Collectors.toList())) {
//						final NodeWrapper su = new NodeWrapper(n, subj.childType, subj.negativeNodes.contains(n));
//						for (GraphNode m : Stream.concat(obj.positiveNodes.stream(), obj.negativeNodes.stream()).collect(Collectors.toList())) {
//							final NodeWrapper o = new NodeWrapper(m, obj.childType, obj.negativeNodes.contains(n));
//
//							final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel());
//							verb.setExclusive();
//							verbs.addAllNodes(verb);
//							connectRGNodes(su, verb, node);
//							connectRGNodes(verb, o, node);
//						}
//					}
				}

//				return verbs;
			}

			case REMOVE: {
				final NodeWrapper old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), false);
				for (GraphNode o : old.negativeNodes) {
					o.setMarkedForDeletion(true);
				}
				for (GraphNode o : old.positiveNodes) {
					o.setMarkedForDeletion(true);
				}
				return old;
			}
			case REPLACE: {
				final NodeWrapper old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(), false);
				for (GraphNode o : old.negativeNodes) {
					o.setMarkedForDeletion(true);
				}
				for (GraphNode o : old.positiveNodes) {
					o.setMarkedForDeletion(true);
				}
				final NodeWrapper neww = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(), false);
				connectRGNodes(old, neww, node);
				return old;
			}

			case CONJUNCTION_AND:
			case CONJUNCTION_NOR:
			case CONJUNCTION_OR: {
				final NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(),
						returnVerb);
				final NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(),
						returnVerb);

//				first.positiveNodes.retainAll(
//						first.positiveNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
//				first.negativeNodes.retainAll(
//						first.negativeNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
//				second.positiveNodes.retainAll(
//						second.positiveNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
//				second.negativeNodes.retainAll(
//						second.negativeNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));

				// AND = AND; OR = OR; NOR = AND + swap()
				NodeType type = node.getType().equals(RuleType.CONJUNCTION_OR) ? NodeType.OR : NodeType.AND;
				NodeWrapper nodes;

				// if and/or or or/and combination, add inner node instead
				System.out.println(type);
				System.out.println(first.childType);
				System.out.println(second.childType);
				if ((!first.childType.equals(NodeType.NONE) && !first.childType.equals(type))
						|| (!second.childType.equals(NodeType.NONE) && !second.childType.equals(type))) {
					NodeWrapper f = first;
					NodeWrapper s = second;
					if ((!first.childType.equals(NodeType.NONE) && !first.childType.equals(type))) {
						f = new NodeWrapper(currentGraph.createInnerNode(first.childType), first.childType, false);
						connectRGNodes(first, f, null);
					}
					if ((!second.childType.equals(NodeType.NONE) && !second.childType.equals(type))) {
						s = new NodeWrapper(currentGraph.createInnerNode(second.childType), second.childType, false);
						connectRGNodes(second, s, null);
					}

					nodes = new NodeWrapper();
					nodes.addAllNodes(f);
					nodes.addAllNodes(s);
					nodes.childType = type;
				} else {
					nodes = new NodeWrapper(first, second, type);
				}

				if (node.getType().equals(RuleType.CONJUNCTION_NOR)) {
					nodes.swap();
				}

				return nodes;
			}

			case CONJUNCTION_XOR: {
				final NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument(),
						returnVerb);
				final NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument(),
						returnVerb);

				first.positiveNodes.retainAll(
						first.positiveNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
				first.negativeNodes.retainAll(
						first.negativeNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
				second.positiveNodes.retainAll(
						second.positiveNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));
				second.negativeNodes.retainAll(
						second.negativeNodes.stream().filter(n -> !isConnected(n)).collect(Collectors.toList()));

				final NodeWrapper parent2 = new NodeWrapper(currentGraph.createInnerNode(NodeType.AND), NodeType.AND,
						false);
				final NodeWrapper parent3 = new NodeWrapper(currentGraph.createInnerNode(NodeType.AND), NodeType.AND,
						false);
				connectRGNodes(parent2, first, null);
				connectRGNodes(parent3, second, null);
				first.swap();
				second.swap();
				connectRGNodes(parent2, second, null);
				connectRGNodes(parent3, first, null);
				return new NodeWrapper(parent2, parent3, NodeType.OR);
			}

			case NEGATION: {
				final NodeWrapper first = buildRGNode(((NegationTreeNode) node).getClause(), returnVerb);
				return first.swap();
			}

			default:
				break;
			}
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
		final GraphNode n = currentGraph.createNode(text, NodeType.NONE);
		n.setId(((LeafTreeNode) node).getId());
		return new NodeWrapper(n, n.getType(), false);
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

	private NodeWrapper resolveCauses(List<MatchResultTreeNode> causes) {
		final NodeWrapper result = new NodeWrapper();
		for (int i = 0; i < causes.size(); i++) {
			final MatchResultTreeNode cause = causes.get(i);
			// Resolve Single Cause
			final NodeWrapper dirCause = resolveCause(cause);

			final int dirCauseCauseSize = dirCause.positiveNodes.size() + dirCause.negativeNodes.size();

			// If there are multiple causes
			if (causes.size() > 1 && dirCauseCauseSize > 1) {
				// Generate Fake Effect Nodes
				final GraphNode node = currentGraph.createInnerNode(dirCause.childType);
				fullyConnect(dirCause, node);
				// Add Fake Effect Node to DirectCause Positive Causes
				result.positiveNodes.add(node);
			} else {
				// Add Resolved Positive/Negative Causes to DirectCause
				result.childType = dirCause.childType;
				result.negativeNodes.addAll(dirCause.negativeNodes);
				result.positiveNodes.addAll(dirCause.positiveNodes);
			}
		}

		return result;
	}

	private NodeWrapper resolveCause(MatchResultTreeNode cause) {
		final NodeWrapper result = new NodeWrapper();

		if (cause.getType().isConjunction()) {
			BinaryMatchResultTreeNode biCause = (BinaryMatchResultTreeNode) cause;
			final List<MatchResultTreeNode> worklist = new LinkedList<MatchResultTreeNode>(
					Arrays.asList(biCause.getFirstArgument(), biCause.getSecondArgument()));
			final Vector<NodeWrapper> arguments = new Vector<NodeWrapper>();

			final RuleType type = cause.getType();
			while (!worklist.isEmpty()) {
				final MatchResultTreeNode wrap = worklist.remove(0);
				final RuleType typeWrap = wrap.getType();
				if (type.equals(typeWrap)) {
					worklist.add(((BinaryMatchResultTreeNode) wrap).getFirstArgument());
					worklist.add(((BinaryMatchResultTreeNode) wrap).getSecondArgument());
				} else {
					final NodeWrapper argument = resolveCause(wrap);
					final int argCauseCount = argument.positiveNodes.size() + argument.negativeNodes.size();
					if (argCauseCount > 1) {
						// Create Inner Node
						final GraphNode node = currentGraph.createInnerNode(argument.childType);
						fullyConnect(argument, node);
						argument.positiveNodes.clear();
						argument.negativeNodes.clear();
						argument.positiveNodes.add(node);
					}
					arguments.add(argument);
				}
			}

			final NodeWrapper merge = NodeWrapper.flattenNodes(arguments.toArray(new NodeWrapper[arguments.size()]));

			if (cause.getType().isXorConjunction()) {
				// Create inner XOR Nodes use them as new positive set
				// Create XOR Node
				// Directly connect all other nodes normally
				// Connect one node negated
				// Add XOR Node to positiveCauses

				for (GraphNode pNode : merge.positiveNodes) {
					GraphNode xorNode = currentGraph.createInnerNode(NodeType.AND);
					xorConnect(merge, xorNode, pNode);
					result.positiveNodes.add(xorNode);
				}

				for (GraphNode nNode : merge.negativeNodes) {
					GraphNode xorNode = currentGraph.createInnerNode(NodeType.AND);
					xorConnect(merge, xorNode, nNode);
					result.positiveNodes.add(xorNode);
				}
				result.childType = NodeType.OR;
			} else if (cause.getType().isNorConjunction()) {
				// Swap positive & negative causes, then AND
				// XXX Technically NOR is not associative so a nested NOR would turn up with a
				// not equivalent result, however since nested NORs usually don't happen we
				// ignore this case.
				final List<GraphNode> tmp = merge.negativeNodes;
				merge.negativeNodes = merge.positiveNodes;
				merge.positiveNodes = tmp;
			} else if (cause.getType().isOrConjunction()) {
				result.childType = NodeType.OR;
				// Swap result type, then AND
			}
			// Default / AND
			// Combine positive & negative causes of dA & dB in result
			result.addAllNodes(merge);
		} else if (cause.getType().isNegation()) {
			NegationTreeNode negNode = (NegationTreeNode) cause;
			final NodeWrapper dHead = resolveCause(negNode.getClause());
			result.positiveNodes = dHead.negativeNodes;
			result.negativeNodes = dHead.positiveNodes;
		} else {
			// Create Direct Node
			ConditionVariableNode cvNode = (ConditionVariableNode) cause;

			GraphNode node = currentGraph.createNode(cvNode.getSecondary(), cvNode.getPrimary(), NodeType.AND);
			result.positiveNodes.add(node);
		}
		return result;
	}

	private void xorConnect(NodeWrapper dirCause, GraphNode node, GraphNode invertedNode) {
		for (final GraphNode pCause : dirCause.positiveNodes) {
			boolean inverted = false;
			if (pCause.equals(invertedNode)) {
				inverted = true;
			}
			pCause.connectTo(node, inverted);
		}
		for (final GraphNode nCause : dirCause.negativeNodes) {
			boolean inverted = true;
			if (nCause.equals(invertedNode)) {
				inverted = false;
			}

			nCause.connectTo(node, inverted);
		}
	}

	private void resolveEffect(NodeWrapper cause, MatchResultTreeNode effect) {
		if (effect.getType().isConjunction()) {
			// Resolve Conjunctions
			resolveEffect(cause, ((BinaryMatchResultTreeNode) effect).getFirstArgument());
			resolveEffect(cause, ((BinaryMatchResultTreeNode) effect).getSecondArgument());
		} else if (effect.getType().isNegation()) {
			// Resolve Negations
			resolveEffect(cause.swapPosNegNodes(), ((NegationTreeNode) effect).getClause());
		} else {
			ConditionVariableNode cvNode = (ConditionVariableNode) effect;
			GraphNode node = currentGraph.createNode(cvNode.getSecondary(), cvNode.getPrimary(), cause.childType);
			fullyConnect(cause, node);
		}
	}

	private void fullyConnect(NodeWrapper dirCause, GraphNode node) {
		for (final GraphNode pCause : dirCause.positiveNodes) {
			pCause.connectTo(node, false);
		}
		for (final GraphNode nCause : dirCause.negativeNodes) {
			nCause.connectTo(node, true);
		}
	}

	private static class NodeWrapper {
		public List<GraphNode> positiveNodes;
		public List<GraphNode> negativeNodes;
		public NodeType childType;

		public NodeWrapper() {
			positiveNodes = new Vector<GraphNode>();
			negativeNodes = new Vector<GraphNode>();
			childType = NodeType.NONE;
		}

		public void addAllNodes(NodeWrapper node) {
			negativeNodes.addAll(node.negativeNodes);
			positiveNodes.addAll(node.positiveNodes);
		}

		public static NodeWrapper flattenNodes(NodeWrapper... nodes) {
			final NodeWrapper result = new NodeWrapper();
			for (final NodeWrapper subCause : nodes) {
				result.addAllNodes(subCause);
			}
			return result;
		}

		public NodeWrapper swapPosNegNodes() {
			final NodeWrapper result = new NodeWrapper();
			result.childType = childType == NodeType.AND ? NodeType.OR : NodeType.AND;
			result.negativeNodes = positiveNodes;
			result.positiveNodes = negativeNodes;
			return result;
		}

		public NodeWrapper(GraphNode node, NodeType type, boolean negated) {
			positiveNodes = new ArrayList<GraphNode>();
			negativeNodes = new ArrayList<GraphNode>();
			if (!negated) {
				addPositiveNode(node);
			} else {
				addNegativeNode(node);
			}
			childType = type;
		}

		public NodeWrapper(NodeWrapper first, NodeWrapper second, NodeType type) {
			positiveNodes = new ArrayList<GraphNode>();
			negativeNodes = new ArrayList<GraphNode>();
			this.positiveNodes.addAll(first.positiveNodes);
			this.positiveNodes.addAll(second.positiveNodes);
			this.negativeNodes.addAll(first.negativeNodes);
			this.negativeNodes.addAll(second.negativeNodes);
			childType = type;
		}

		public NodeWrapper swap() {
			List<GraphNode> tmp = this.negativeNodes;
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

		public void setExclusive() {
			for (GraphNode n : this.positiveNodes) {
				n.setExclusive(true);
			}
			for (GraphNode n : this.negativeNodes) {
				n.setExclusive(true);
			}
		}
	}
}
