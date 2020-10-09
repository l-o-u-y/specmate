package com.specmate.modelgeneration.stages;

import org.osgi.service.log.LogService;

import com.specmate.modelgeneration.Creation;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.nlp.api.ELanguage;

public abstract class GraphLayouter<T, S, U> {
	protected static final int XSTART = 225;
	protected static final int YSTART = 225;

	protected static final int XOFFSET = 300;
	protected static final int YOFFSET = 150;

	protected final ELanguage lang;
	protected final Creation<T, S, U> creation;
	protected final LogService log;

	public GraphLayouter(ELanguage language, Creation<T, S, U> creation) {
		lang = language;
		this.creation = creation;
		log = null;
	}
	public GraphLayouter(ELanguage language, Creation<T, S, U> creation, LogService logService) {
		lang = language;
		this.creation = creation;
		log = logService;
	}


	public T createModel(Graph graph) {
		T model = creation.createModel();
		return createModel(graph, model);
	}
	public abstract T createModel(Graph graph, T model);

}
