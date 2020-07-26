package com.specmate.modelgeneration.stages.graph;

import com.specmate.model.requirements.RGConnectionType;

public class GraphEdge {
	private final boolean negated;
	private final GraphNode from;
	private final GraphNode to;
	private final RGConnectionType type;

	public GraphEdge(GraphNode from, GraphNode to) {
		this(from, to, false);
	}

	public GraphEdge(GraphNode from, GraphNode to, boolean isNegated) {
		negated = isNegated;
		this.type = null;
		this.from = from;
		this.to = to;
	}
	
	public GraphEdge(GraphNode from, GraphNode to, RGConnectionType type, boolean isNegated) {
		negated = isNegated;
		this.type = type;
		this.from = from;
		this.to = to;
	}

	public RGConnectionType getType() {
		return type;
	}

	public boolean isNegated() {
		return negated;
	}

	public GraphNode getFrom() {
		return from;
	}

	public GraphNode getTo() {
		return to;
	}
}
