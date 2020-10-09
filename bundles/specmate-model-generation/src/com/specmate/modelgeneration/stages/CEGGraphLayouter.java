package com.specmate.modelgeneration.stages;

import java.util.HashMap;

import org.osgi.service.log.LogService;

import com.specmate.model.base.IModelNode;
import com.specmate.model.requirements.CEGConnection;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.CEGNode;
import com.specmate.modelgeneration.CEGCreation;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphEdge;
import com.specmate.modelgeneration.stages.graph.GraphNode;
import com.specmate.nlp.api.ELanguage;

public class CEGGraphLayouter extends GraphLayouter<CEGModel, CEGNode, CEGConnection> {

	public CEGGraphLayouter(ELanguage language, CEGCreation creation) {
		super(language, creation);
	}
	public CEGGraphLayouter(ELanguage language, CEGCreation creation, LogService logService) {
		super(language, creation, logService);
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
	public CEGModel createModel(Graph graph, CEGModel model) {
		
		int graphDepth = graph.getDepth();
		int[] positionTable = new int[graphDepth + 1];

		HashMap<GraphNode, IModelNode> nodeMap = new HashMap<GraphNode, IModelNode>();
		for (GraphNode node : graph.nodes) {
			int xIndex = node.getHeight();
			int yIndex = positionTable[xIndex];

			int x = XSTART + xIndex * XOFFSET;
			int y = YSTART + yIndex * YOFFSET;

			IModelNode n;

				String condition = node.getCondition();
				String variable = node.getVariable();

				if (graph.isInnerNode(node)) {
					condition = innerConditionString();
					variable = innerVariableString() + " " + xIndex + " - " + yIndex;
				}
				
				n = ((CEGCreation)creation).createNode((CEGModel)model, variable, condition, x, y, node.getType());
			
			nodeMap.put(node, n);
			positionTable[xIndex]++;
		}
		

		for (GraphEdge edge : graph.edges) {
			IModelNode from = nodeMap.get(edge.getFrom());
			IModelNode to = nodeMap.get(edge.getTo());
				((CEGCreation)creation).createConnection((CEGModel)model, (CEGNode)from, (CEGNode)to, edge.isNegated());
		}

		return model;

	}

}
