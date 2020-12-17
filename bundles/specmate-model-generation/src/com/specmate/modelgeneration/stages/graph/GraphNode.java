package com.specmate.modelgeneration.stages.graph;

import java.util.List;
import java.util.Vector;

import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;

public class GraphNode {
	private String secondaryText;
	private String primaryText;
	private List<String> ids;
	private boolean markedForDeletion;
	private NodeType type;
	private Graph graph;
	private boolean isExclusive = false;

	private List<GraphEdge> parentEdges;
	private List<GraphEdge> childEdges;

	GraphNode(Graph graph) {
		this(graph, NodeType.NONE);
	}

	GraphNode(Graph graph, NodeType type) {
		this.type = type;
		this.graph = graph;
		childEdges = new Vector<GraphEdge>();
		parentEdges = new Vector<GraphEdge>();
	}

	public List<GraphEdge> getChildEdges() {
		return childEdges;
	}

	public List<GraphEdge> getParentEdges() {
		return parentEdges;
	}

	public void removeEdge(GraphEdge edge) {
		parentEdges.remove(edge);
		childEdges.remove(edge);
	}

	public void connectTo(GraphNode node, boolean negateEdge) {
		GraphEdge edge = new GraphEdge(this, node, negateEdge);
		childEdges.add(edge);
		node.parentEdges.add(edge);
		graph.edges.add(edge);
	}

	public void connectTo(GraphNode node, RGConnectionType type, boolean negateEdge, String label) {
		GraphEdge edge = new GraphEdge(this, node, type, negateEdge, label);
		childEdges.add(edge);
		node.parentEdges.add(edge);
		graph.edges.add(edge);
	}

	public NodeType getType() {
		return type;
	}

	public String getSecondaryText() {
		return secondaryText;
	}

	public String getPrimaryText() {
		return primaryText;
	}

	public void setSecondaryText(String text) {
		this.secondaryText = text;
	}

	public void setPrimaryText(String text) {
		this.primaryText = text;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public boolean isRoot() {
		return parentEdges.isEmpty();
	}

	public boolean isLeaf() {
		return childEdges.isEmpty();
	}

	int getDepth() {
		if (isRoot()) {
			return 0;
		}

		int maxDepth = parentEdges.get(0).getFrom().getDepth();
		for (GraphEdge edge : parentEdges) {
			int depth = edge.getFrom().getDepth();
			maxDepth = Math.max(maxDepth, depth);
		}

		return 1 + maxDepth;
	}

	public int getHeight() {
		if (isLeaf()) {
			return getDepth();
		}

		int minHeight = childEdges.get(0).getTo().getHeight();
		for (GraphEdge edge : childEdges) {
			int depth = edge.getTo().getHeight();
			minHeight = Math.min(minHeight, depth);
		}
		return minHeight - 1;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public boolean isMarkedForDeletion() {
		return markedForDeletion;
	}

	public void setMarkedForDeletion(boolean markedForDeletion) {
		this.markedForDeletion = markedForDeletion;
	}

	public boolean isExclusive() {
		return isExclusive;
	}

	public void setExclusive(boolean isExclusive) {
		this.isExclusive = isExclusive;
	}
}
