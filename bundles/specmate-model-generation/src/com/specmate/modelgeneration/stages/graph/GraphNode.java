package com.specmate.modelgeneration.stages.graph;

import java.util.List;
import java.util.Vector;

import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnectionType;

public class GraphNode {
	private String condition;
	private String variable;
	private String component;
	private String id;
	private boolean deleted;
	private NodeType type;
	private Graph graph;

	private List<GraphEdge> parentEdges;
	private List<GraphEdge> childEdges;

	GraphNode(Graph graph) {
		this(graph, NodeType.AND);
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

	public String getCondition() {
		return condition;
	}

	public String getVariable() {
		return variable;
	}

	public String getComponent() {
		return component;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public void setComponent(String component) {
		this.component = component;
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
		if(isRoot()) {
			return 0;
		}

		int maxDepth = parentEdges.get(0).getFrom().getDepth();
		for(GraphEdge edge: parentEdges) {
			int depth =  edge.getFrom().getDepth();
			maxDepth = Math.max(maxDepth, depth);
		}

		return 1 + maxDepth;
	}

	public int getHeight() {
		if(isLeaf()) {
			return getDepth();
		}

		int minHeight = childEdges.get(0).getTo().getHeight();
		for(GraphEdge edge: childEdges) {
			int depth =  edge.getTo().getHeight();
			minHeight = Math.min(minHeight, depth);
		}
		return minHeight - 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
