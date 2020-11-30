package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.osgi.service.log.LogService;

import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.modelgeneration.RGCreation;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphEdge;
import com.specmate.modelgeneration.stages.graph.GraphNode;
import com.specmate.nlp.api.ELanguage;

public class RGGraphLayouter extends GraphLayouter<RGModel, RGNode, RGConnection> {

	public RGGraphLayouter(ELanguage language, RGCreation creation) {
		super(language, creation);
	}

	public RGGraphLayouter(ELanguage language, RGCreation creation, LogService logService) {
		super(language, creation, logService);
	}

	public RGModel createModel(Graph graph, RGModel model) {

		RGCreation rgCreation = (RGCreation) creation;
		int graphDepth = graph.getDepth();
		int[] positionTable = new int[graphDepth + 1];

		HashMap<GraphNode, RGNode> nodeMap = new HashMap<GraphNode, RGNode>();
		List<GraphNode> markedNodes = new ArrayList<GraphNode>();
		for (GraphNode node : graph.nodes) {
			int xIndex = node.getHeight();
			int yIndex = positionTable[xIndex];

			int x = XSTART + xIndex * XOFFSET;
			int y = YSTART + yIndex * YOFFSET;

			RGNode n;
			String component = node.getPrimaryText();

			if (node.isMarkedForDeletion()) {
				markedNodes.add(node);
				n = rgCreation.createNode(model, component, node.isMarkedForDeletion(), x, y, node.getType());
			} else {
				if (node.isExclusive()) {
					n = rgCreation.createNode(model, component, node.isMarkedForDeletion(), x, y, node.getType());
				} else {
					n = rgCreation.createNodeIfNotExist(model, component, x, y, node.getType());
				}
			}

			// assign chunks based on chunk and node id (position)
			for (RGObject m : model.getModelMapping()) {
				if (m.getChunk() != null) {
					if (m.getChunk().getId().equals(node.getId())) {
						if (m.getChunk().getNode() == null) {
//							System.out.println(m.getChunk().getText());
							m.getChunk().setNode(n);
							n.getChunks().add(m.getChunk());
						}
					}
				}
			}
			nodeMap.put(node, n);
			positionTable[xIndex]++;
		}

		// if we have nodes marked for deletion
		for (GraphNode markedNode : markedNodes) {
			// find connections parent --> marked
			List<GraphEdge> edgesToDeletedNode = new ArrayList<GraphEdge>();
			for (GraphEdge e : graph.edges) {
				if (e.getTo().equals(markedNode)) {
					edgesToDeletedNode.add(e);
				}
			}
			// if marked node has parent connections
			if (edgesToDeletedNode.size() > 0) {
				// do nothing
				// this means that the marked node should only be DELETEd/REPLACEd from the
				// parent
			} else {
				// if marked node has no parent connections
				// this means that the marked node should be DELETEd/REPLACEd from all parents
				// connect to self, so that we can use later
				markedNode.connectTo(markedNode, false);
			}
		}

		for (GraphEdge edge : graph.edges) {
			RGNode from = nodeMap.get(edge.getFrom());
			RGNode to = nodeMap.get(edge.getTo());

			// connect chunks
			RGChunk fromChunk = null;
			RGChunk toChunk = null;
			for (RGChunk c : from.getChunks()) {
				if (c.getId().equals(edge.getFrom().getId())) {
					fromChunk = c;
				}
			}
			for (RGChunk c : to.getChunks()) {
				if (c.getId().equals(edge.getTo().getId())) {
					toChunk = c;
				}
			}

			if (fromChunk != null && toChunk != null) {
				fromChunk.getOutgoingChunks().add(toChunk);
				toChunk.getIncomingChunks().add(fromChunk);
			} else {
				System.out.println("From content: " + from.getComponent());
				System.out.println("From content: " + to.getComponent());
				log.log(LogService.LOG_ERROR, "This case should never happen. Chunks not found");
				System.out.println();

			}
			
			// Note: this works because DELETE/REPLACE edge is inserted before this
			// connection edge
			// if connection xx -> tmp
			if (to.isTemporary()) {
				RGNode old = rgCreation.findOldNode(model, to);
				if (old != null) {
					if (from.equals(to)) {
						// tmp --> tmp means, node should be DELETEd/REPLACEd from all parents of old
						rgCreation.replaceConnection(model, null, old, to);
					} else {
						// only DELETE/REPLACE from parent "from"
						rgCreation.replaceConnection(model, from, old, to);
					}
				}
			} else {
				rgCreation.createConnection(model, from, to, edge.getType(), edge.isNegated(), edge.getLabel());
			}
		}
		return model;

	}

}
