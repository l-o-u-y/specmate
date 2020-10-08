package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.osgi.service.log.LogService;

import com.specmate.model.base.IModelConnection;
import com.specmate.model.base.IModelNode;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.CEGNode;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.modelgeneration.CEGCreation;
import com.specmate.modelgeneration.Creation;
import com.specmate.modelgeneration.RGCreation;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphEdge;
import com.specmate.modelgeneration.stages.graph.GraphNode;
import com.specmate.nlp.api.ELanguage;

public class GraphLayouter<T, S, U> {
	private static final int XSTART = 225;
	private static final int YSTART = 225;

	private static final int XOFFSET = 300;
	private static final int YOFFSET = 150;

	private final ELanguage lang;
	private final Creation<T, S, U> creation;
	private final LogService log;

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

	private String innerVariableString() {
		if (lang == ELanguage.DE) {
			return "Innerer Knoten";
		}
		return "Inner Node";
	}

	private String innerConditionString() {
		if (lang == ELanguage.DE) {
			return "Ist erfüllt";
		}
		return "Is fulfilled";
	}

	public T createModel(Graph graph) {
		T model = creation.createModel();
		return createModel(graph, model);
	}
	public T createModel(Graph graph, T model) {
		
		int graphDepth = graph.getDepth();
		int[] positionTable = new int[graphDepth + 1];

		HashMap<GraphNode, IModelNode> nodeMap = new HashMap<GraphNode, IModelNode>();
		List<GraphNode> deletedNodes = new ArrayList<GraphNode>(); 
		for (GraphNode node : graph.nodes) {
			int xIndex = node.getHeight();
			int yIndex = positionTable[xIndex];

			int x = XSTART + xIndex * XOFFSET;
			int y = YSTART + yIndex * YOFFSET;

			IModelNode n;
			if (creation instanceof CEGCreation) {

				String condition = node.getCondition();
				String variable = node.getVariable();

				if (graph.isInnerNode(node)) {
					condition = innerConditionString();
					variable = innerVariableString() + " " + xIndex + " - " + yIndex;
				}
				
				n = ((CEGCreation)creation).createNode((CEGModel)model, variable, condition, x, y, node.getType());
			} else {

				String component = node.getComponent();
				
				n = ((RGCreation)creation).createNodeIfNotExist((RGModel)model, component, node.isDeleted(), x, y, node.getType());
				
				for (RGObject m : ((RGModel)model).getModelMapping()) {
					if (m.getChunk() != null) {
						if (m.getChunk().getId().equals(node.getId())) {
							if (m.getChunk().getNode() == null) {
								m.getChunk().setNode((RGNode)n);
								((RGNode)n).getChunks().add(m.getChunk());
							}
						}
					}
				}
				
				if (node.isDeleted()) {
					deletedNodes.add(node);
				}
			}
			nodeMap.put(node, n);
			positionTable[xIndex]++;
		}
		
		// if we have deleted nodes
		for (GraphNode dNode : deletedNodes) {
			// check if del node has parent connections
			List<GraphEdge> edgesToDeletedNode = new ArrayList<GraphEdge>(); 
			for (GraphEdge e : graph.edges) {
				if (e.getTo().equals(dNode)) {
					edgesToDeletedNode.add(e);
				}
			}
			// if del node has parent connections
			if (edgesToDeletedNode.size() > 0) {
				// do nothing
				break;
			}
			// if del node has no parent connections
			// -> del has connection to all
			dNode.connectTo(dNode, false);
		}

		for (GraphEdge edge : graph.edges) {
			IModelNode from = nodeMap.get(edge.getFrom());
			IModelNode to = nodeMap.get(edge.getTo());
			if (creation instanceof CEGCreation) {
				((CEGCreation)creation).createConnection((CEGModel)model, (CEGNode)from, (CEGNode)to, edge.isNegated());
			} else {
				// Note: replace edge is inserted before old connection edge so this works
				// delete old connection
				if (((RGNode)to).isDeleted()) {
					RGNode old = ((RGCreation)creation).findOldNode((RGModel)model, (RGNode)to);
					if (old != null) {
						if (from.equals(to)) {
							// if from equals to
							// -> to didnt have any connections
							// -> update all connections
							List<RGNode> n = new ArrayList<RGNode>(); 
							for (IModelConnection c : old.getIncomingConnections()) {
								n.add((RGNode)c.getSource());
							}
							for (RGNode node : n) {
								((RGCreation)creation).replaceConnection((RGModel)model, node, old, (RGNode)to);
							}
						} else {
							((RGCreation)creation).replaceConnection((RGModel)model, (RGNode)from, old, (RGNode)to);
						}
					}
				} else {
					((RGCreation)creation).createConnection((RGModel)model, (RGNode)from, (RGNode)to, edge.getType(), edge.isNegated(), edge.getLabel());
				}
				
				RGChunk fromChunk = null;
				RGChunk toChunk = null;
				for (RGChunk c : ((RGNode)from).getChunks()) {
					if (c.getId().equals(edge.getFrom().getId())) {
						fromChunk = c;
					}
				}

				for (RGChunk c : ((RGNode)to).getChunks()) {
					if (c.getId().equals(edge.getTo().getId())) {
						toChunk = c;
					}
				}
				if (fromChunk != null && toChunk != null) {
					fromChunk.getOutgoingChunks().add(toChunk);
					toChunk.getIncomingChunks().add(fromChunk);
				} else {
					log.log(LogService.LOG_ERROR,
							"This case should never happen. Chunks not found");
				}
			}
		}
		return model;

	}

}
