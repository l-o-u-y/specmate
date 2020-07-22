package com.specmate.modelgeneration.stages;

import java.util.HashMap;

import com.specmate.model.base.IModelNode;
import com.specmate.model.base.ISpecmateModelObject;
import com.specmate.model.requirements.CEGConnection;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.CEGNode;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.modelgeneration.CEGCreation;
import com.specmate.modelgeneration.Creation;
import com.specmate.modelgeneration.RGCreation;
import com.specmate.modelgeneration.stages.graph.Graph;
import com.specmate.modelgeneration.stages.graph.GraphEdge;
import com.specmate.modelgeneration.stages.graph.GraphNode;
import com.specmate.nlp.api.ELanguage;

public class GraphLayouter {
	private static final int XSTART = 225;
	private static final int YSTART = 225;

	private static final int XOFFSET = 300;
	private static final int YOFFSET = 150;

	private final ELanguage lang;
	private final Creation creation;

	public GraphLayouter(ELanguage language, CEGCreation creation) {
		lang = language;
		this.creation = creation;
	}
	public GraphLayouter(ELanguage language, RGCreation creation) {
		lang = language;
		this.creation = creation;
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

	public ISpecmateModelObject createModel(Graph graph) {
		ISpecmateModelObject model;
		if (creation instanceof CEGCreation) {
			model = RequirementsFactory.eINSTANCE.createCEGModel();
		} else {
			model = RequirementsFactory.eINSTANCE.createRGModel();
		}
		
		int graphDepth = graph.getDepth();
		int[] positionTable = new int[graphDepth + 1];

		HashMap<GraphNode, IModelNode> nodeMap = new HashMap<GraphNode, IModelNode>();
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
				String modifier = node.getModifier();
				
				n = ((RGCreation)creation).createNodeIfNotExist((RGModel)model, component, modifier, x, y, node.getType());
			}
			nodeMap.put(node, n);
			positionTable[xIndex]++;
		}

		for (GraphEdge edge : graph.edges) {
			IModelNode from = nodeMap.get(edge.getFrom());
			IModelNode to = nodeMap.get(edge.getTo());
			if (creation instanceof CEGCreation) {
				((CEGCreation)creation).createConnection((CEGModel)model, (CEGNode)from, (CEGNode)to, edge.isNegated());
			} else {
				((RGCreation)creation).createConnection((RGModel)model, (RGNode)from, (RGNode)to, edge.isNegated());
			}
		}
		return model;

	}

}
