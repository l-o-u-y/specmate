package com.specmate.modelgeneration.stages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.log.LogService;

import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGWord;
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

			if (node.getPrimaryText().isBlank()) {
				continue;
			}
			if (node.isMarkedForDeletion()) {
				markedNodes.add(node);
				n = rgCreation.createNode(model, component, node.isMarkedForDeletion(), x, y, node.getType());
			} else {
				if (node.isExclusive()) {
					// TODO MA also with other node texts if connections same
					n = rgCreation.isNewGraphNode(model, node);
					if (n == null) {
						n = rgCreation.createNode(model, component, node.isMarkedForDeletion(), x, y, node.getType());
					} else {
					}
				} else {
					n = rgCreation.createNodeIfNotExist(model, component, x, y, node.getType());
				}
			}

			// assign words based on word id and node id (position)
			for (RGWord m : model.getWords()) {
				if (node.getPositions() == null && n.getComponent().contains("Inner Node")) { // inner nodes
					// take any parent or child node of the inner node
					List<GraphNode> connectedNodes = new ArrayList<GraphNode>();
					connectedNodes.addAll(node.getParentEdges().stream().map(c -> (GraphNode)c.getFrom()).collect(Collectors.toList()));
					connectedNodes.addAll(node.getChildEdges().stream().map(c -> (GraphNode)c.getTo()).collect(Collectors.toList()));
					// do it this way to get it sorted by input text order
					List<Integer> connectedNodeIds = new ArrayList<Integer>();
					for (GraphNode cn : connectedNodes) {
						connectedNodeIds.addAll(cn.getPositions());
					}
					RGWord connectedWord = model.getWords().stream().filter(o -> connectedNodeIds.contains(o.getPosition())).findFirst().get();
					int indexOfConnectedWord = model.getWords().indexOf(connectedWord);
					// and insert the inner node word at the index -> inserts it before the "." and makes it part of the sentence 
					RGWord tmp = rgCreation.createWord(model, n.getComponent(), indexOfConnectedWord);
					tmp.setNode(n);
					n.getWords().add(tmp);
					node.setPositions(List.of(tmp.getPosition()));
					break;
				}
				for (Integer pos : node.getPositions()) {
					if (m.getPosition() == pos) {
						if (m.getNode() == null) {
							m.setNode(n);
							n.getWords().add(m);
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
			if (from == null || to == null) {
				continue;
			}

			// connect words
			List<RGWord> fromWords = new ArrayList<RGWord>();
			List<RGWord> toWords = new ArrayList<RGWord>();
			for (RGWord c : from.getWords()) {
				for (Integer id : edge.getFrom().getPositions()) {
					if (c.getPosition() == id) {
						fromWords.add(c);
					}
				}
			}
			for (RGWord c : to.getWords()) {
				for (Integer id : edge.getTo().getPositions()) {
					if (c.getPosition() == id) {
						toWords.add(c);
					}
				}
			}

			if (!fromWords.isEmpty() && !toWords.isEmpty()) {
				for (RGWord toWord : toWords) {
					for (RGWord fromWord: fromWords) {
						fromWord.getOutgoing().add(toWord);
						toWord.getIncoming().add(fromWord);
					}
				}
			} else {
				System.out.println("From content: " + from.getComponent());
				System.out.println("To content: " + to.getComponent());
				log.log(LogService.LOG_ERROR, "This case should never happen. Words not found");
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
				rgCreation.createConnection(model, from, to, edge.getType(), edge.isNegated());
			}
		}
		return model;

	}

}
