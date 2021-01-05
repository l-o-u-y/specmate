package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;

import com.specmate.cause_effect_patterns.parse.wrapper.BinaryMatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.LeafTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode;
import com.specmate.cause_effect_patterns.parse.wrapper.MatchResultTreeNode.RuleType;
import com.specmate.cause_effect_patterns.parse.wrapper.NegationTreeNode;
import com.specmate.model.base.IContentElement;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphEdge;
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

	public synchronized Graph buildRGGraph(BinaryMatchResultTreeNode root, RGModel model) {
		// Inner Node counter for consecutive models
		EList<IContentElement> list = model.getContents();
		List<Integer> ints = list.stream().filter(c -> c instanceof RGNode).map(n -> (RGNode) n)
		.filter(n -> n.getComponent().contains("Inner Node "))
		.map(n -> n.getComponent().split(" ")[3])
		.map(s -> Integer.parseInt(s)).sorted().collect(Collectors.toList());
		if (ints.size() == 0) {
			currentGraph = new Graph();
		} else {
			currentGraph = new Graph(ints.get(ints.size() - 1));
		}
		buildRGNode(root);

		Graph result = currentGraph;
		return result;
	}

	private synchronized void connectRGNodes(NodeWrapper parent, NodeWrapper child, MatchResultTreeNode node) {
		for (GraphNode p : parent.positiveNodes) {
			for (GraphNode c : child.positiveNodes) {
				// do not connect verb to verb unless its a condition node
				if (p.isExclusive() && c.isExclusive() && !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				// TODO MA // c.isExclusive() && 
				if (c.isExclusive() && c.getParentEdges().size() > 0
						&& !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), false);

			}
			for (GraphNode c : child.negativeNodes) {
				if (p.isExclusive() && c.isExclusive() && !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				if (c.isExclusive() && c.getParentEdges().size() > 0
						&& !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				p.connectTo(c, getConnectionType(node), true);
			}
		}
		for (GraphNode p : parent.negativeNodes) {
			for (GraphNode c : child.positiveNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				if (p.isExclusive() && c.isExclusive() && !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				if (c.isExclusive() && c.getParentEdges().size() > 0
						&& !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				p.connectTo(c, getConnectionType(node), true);
			}
			for (GraphNode c : child.negativeNodes) {
				// sets child GraphNode type to child RGNode type
				c.setType(parent.childType);
				if (p.isExclusive() && c.isExclusive() && !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				if (c.isExclusive() && c.getParentEdges().size() > 0
						&& !getConnectionType(node).equals(RGConnectionType.CONDITION)) {
					continue;
				}
				p.connectTo(c, getConnectionType(node), false);
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

	private synchronized NodeWrapper buildRGNode(MatchResultTreeNode node) {
		if (node.getType() == null) {
		} else {
			switch (node.getType()) {
			case TMP: {
				buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
				return buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
			}
			case CONDITION: {
				final NodeWrapper cause = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				final NodeWrapper effect = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

				cause.positiveNodes.addAll(cause.negativeNodes.stream().filter(n -> n.getParentEdges().size()>0 || n.getChildEdges().size()>0).collect(Collectors.toList()));
				cause.negativeNodes.removeAll(cause.negativeNodes.stream().filter(n -> n.getParentEdges().size()>0 || n.getChildEdges().size()>0).collect(Collectors.toList()));
				effect.positiveNodes.addAll(effect.negativeNodes.stream().filter(n -> n.getParentEdges().size()>0 || n.getChildEdges().size()>0).collect(Collectors.toList()));
				effect.negativeNodes.removeAll(effect.negativeNodes.stream().filter(n -> n.getParentEdges().size()>0 || n.getChildEdges().size()>0).collect(Collectors.toList()));
				effect.childType = cause.childType;

				connectRGNodes(cause, effect, node);
				return effect;
			}

			case LIMITED_CONDITION: {
				final NodeWrapper limit = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				final NodeWrapper condition = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

				// TODO MA
				System.err.println("Handling for NodeType " + node.getType() + " not implemented yet");
				break;
			}
			case COMPOSITION:
			case INHERITANCE: {
				NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

				final NodeWrapper label;
				if (((BinaryMatchResultTreeNode) node).getLabel() != null) {
					label = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel());
				} else {
					label = new NodeWrapper(currentGraph.createInnerNode(NodeType.NONE), NodeType.NONE, false);
				}
				label.setExclusive();

				NodeWrapper all = new NodeWrapper();
				all.childType = second.childType;
				all.addLabelNodes(first);
				all.addLabelNodes(second);	
//				if (node.getType().equals(RuleType.INHERITANCE)) {
//					all.addLabelNodes(first);
//				} else {
//					all.addLabelNodes(second);	
//				}
				all.addLabelNodes(label);
				first.transform();
				second.transform();

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
				return all;
			}

			case ACTION: {
				final NodeWrapper subj = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				final NodeWrapper obj = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
				final NodeWrapper verb = buildRGNode(((BinaryMatchResultTreeNode) node).getLabel());
				verb.setExclusive();

				NodeWrapper all = new NodeWrapper();
				all.childType = obj.childType;

				verb.addUnconnectedLabelNodes(obj);
				connectRGNodes(verb, obj, node);
				verb.addLabelNodes(obj);
				connectRGNodes(subj, verb, node);
				
				all.addLabelNodes(verb);
				return all;
			}

			case REMOVE: {
				final NodeWrapper old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
				for (GraphNode o : old.negativeNodes) {
					o.setMarkedForDeletion(true);
				}
				for (GraphNode o : old.positiveNodes) {
					o.setMarkedForDeletion(true);
				}
				return old;
			}
			case REPLACE: {
				final NodeWrapper old = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
				for (GraphNode o : old.negativeNodes) {
					o.setMarkedForDeletion(true);
				}
				for (GraphNode o : old.positiveNodes) {
					o.setMarkedForDeletion(true);
				}
				final NodeWrapper neww = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				connectRGNodes(old, neww, node);
				return old;
			}

			case CONJUNCTION_AND:
			case CONJUNCTION_NOR:
			case CONJUNCTION_OR: {
				final NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				final NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());
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
				final NodeWrapper first = buildRGNode(((BinaryMatchResultTreeNode) node).getFirstArgument());
				final NodeWrapper second = buildRGNode(((BinaryMatchResultTreeNode) node).getSecondArgument());

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
				final NodeWrapper first = buildRGNode(((NegationTreeNode) node).getClause());
				first.swap();
				for (GraphNode n : Stream.concat(
						first.negativeNodes.stream(), first.positiveNodes.stream()
						).collect(Collectors.toList()) ) {
					for (GraphEdge e : Stream.concat(
							n.getChildEdges().stream(), n.getParentEdges().stream()
							).collect(Collectors.toList()) ) {
						e.setNegated(!e.isNegated());
					}
					
				}
				
				return first;
			}

			default:
				break;
			}
		}

		// LeafTreeNode
		String text = ((LeafTreeNode) node).getContent();
		// if the text is null, return an empty wrapper (no node)
		// if the text is empty, return a node with empty string
		// empty text is a placeholder for subjects in passive action relationships
		// the node with empty text will not get added to the model in the latter step
		if (text == null) {
			return new NodeWrapper();
		}

		final GraphNode n = currentGraph.createNode(text, NodeType.NONE);
		n.setPositions(((LeafTreeNode) node).getPositions());
		n.setExclusive(((LeafTreeNode) node).isVerb());
		return new NodeWrapper(n, n.getType(), false);
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
		
		public void addUnconnectedLabelNodes(NodeWrapper wrapper) {
			addLabelNodes(wrapper, false);
		}

		public void addLabelNodes(NodeWrapper wrapper) {
			addLabelNodes(wrapper, true);			
		}
		private void addLabelNodes(NodeWrapper wrapper, boolean all) {
			for (GraphNode n : wrapper.negativeNodes) {
				if (n.isExclusive()) {
					if (all || (n.getChildEdges().size()==0 && n.getParentEdges().size()==0)) {
						if (this.negativeNodes.contains(n)) continue;
						this.negativeNodes.add(n);	
					}
				}
			}
			for (GraphNode n : wrapper.positiveNodes) {
				if (n.isExclusive()) {
					if (all || (n.getChildEdges().size()==0 && n.getParentEdges().size()==0)) {
						if (this.negativeNodes.contains(n)) continue;
						this.positiveNodes.add(n);
					}					
				}
			}
		}

		public NodeWrapper transform() {
			ArrayList<GraphNode> tmpPos = new ArrayList<GraphNode>();
			ArrayList<GraphNode> tmpNeg = new ArrayList<GraphNode>();

			for (GraphNode n : this.positiveNodes) {
				RGConnectionType type = n.getChildEdges().size() == 0
						? n.getParentEdges().size() == 0 ? null : n.getParentEdges().get(0).getType()
						: n.getChildEdges().get(0).getType();
				if (type == null) {
					tmpPos.add(n);
					continue;
				}
				switch (type) {
				case COMPOSITION:
					if (n.getChildEdges().size() > 0) {
						tmpPos.add(n.getChildEdges().get(0).getTo());
					}
//					if (n.getParentEdges().size() > 0) {
//						tmpPos.add(n.getParentEdges().get(0).getFrom());
//					}
					break;
				case INHERITANCE:
				case ACTION:
					if (n.getChildEdges().size() > 0) {
						tmpPos.add(n.getChildEdges().get(0).getTo());
					}
					break;

				default:
					break;
				}
			}
			for (GraphNode n : this.negativeNodes) {
				RGConnectionType type = n.getChildEdges().size() == 0 ? null : n.getChildEdges().get(0).getType();
				if (type == null) {
					tmpNeg.add(n);
					continue;
				}
				switch (type) {
				case COMPOSITION:
					if (n.getParentEdges().size() > 0) {
						tmpNeg.add(n.getChildEdges().get(0).getTo());
					}
//					if (n.getParentEdges().size() > 0) {
//						tmpPos.add(n.getParentEdges().get(0).getFrom());
//					}
					break;
				case INHERITANCE:
				case ACTION:
					if (n.getParentEdges().size() > 0) {
						tmpNeg.add(n.getChildEdges().get(0).getTo());
					}
					break;

				default:
					break;
				}
			}
			this.negativeNodes = tmpNeg;
			this.positiveNodes = tmpPos;
			return this;
		}

		public void setExclusive() {
			for (GraphNode n : this.positiveNodes) {
				n.setExclusive(true);
			}
			for (GraphNode n : this.negativeNodes) {
				n.setExclusive(true);
			}
		}
		
		@Override
		public String toString() {
			String s = "";
			for (GraphNode n : this.negativeNodes) {
				s += "(neg) " + n.getPrimaryText() + "; ";
			}
			for (GraphNode n : this.positiveNodes) {
				s += n.getPrimaryText() + "; ";
			}
			// TODO Auto-generated method stub
			return s;
		}
	}
}
