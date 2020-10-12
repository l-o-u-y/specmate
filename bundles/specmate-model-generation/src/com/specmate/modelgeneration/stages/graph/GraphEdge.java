package com.specmate.modelgeneration.stages.graph;

import com.specmate.model.requirements.RGConnectionType;

public class GraphEdge {
	private boolean negated;
	private final GraphNode from;
	private final GraphNode to;
	private RGConnectionType type;
	private final String label;

	public GraphEdge(GraphNode from, GraphNode to) {
		this(from, to, false);
	}

	public GraphEdge(GraphNode from, GraphNode to, boolean isNegated) {
		this(from, to, null, isNegated);
	}

	public GraphEdge(GraphNode from, GraphNode to, RGConnectionType type, boolean isNegated) {
		this(from, to, type, isNegated, "");
	}

	public GraphEdge(GraphNode from, GraphNode to, RGConnectionType type, boolean isNegated, String label) {
		this.label = label;
		negated = isNegated;
		this.type = type;
		this.from = from;
		this.to = to;
	}

	public RGConnectionType getType() {
		return type;
	}
	public String getLabel() {
		return label;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setNegated(boolean neg) {
		negated = neg;
	}

	public void setType(RGConnectionType typ) {
		type = typ;
	}

	public GraphNode getFrom() {
		return from;
	}

	public GraphNode getTo() {
		return to;
	}
}
