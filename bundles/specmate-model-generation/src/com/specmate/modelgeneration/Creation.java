package com.specmate.modelgeneration;

import com.specmate.model.requirements.NodeType;

public abstract class Creation<T, S, U> {
	public abstract S createNode(T model, String variable, String condition, int x, int y, NodeType type);

	public abstract U createConnection(T model, S nodeFrom, S nodeTo, boolean negate);

	public abstract S createNodeIfNotExist(T model, String variable, String condition, int x, int y, NodeType type);

	public abstract T createModel();
}
