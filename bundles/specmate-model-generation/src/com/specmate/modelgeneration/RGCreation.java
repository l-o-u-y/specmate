package com.specmate.modelgeneration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import com.specmate.model.base.IContentElement;
import com.specmate.model.base.IModelConnection;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.model.support.util.SpecmateEcoreUtil;

/**
 * Class creates Node and Edges for a RG-Graphs
 *
 * @author Lena Ouyang
 *
 */
public class RGCreation extends Creation<RGModel, RGNode, RGConnection> {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Create a new object and add it to the RGModel
	 *
	 * @param model
	 * @param originalText
	 * @return the created object
	 */
	public RGObject createObject(RGModel model, String originalText) {
		RGObject obj = RequirementsFactory.eINSTANCE.createRGObject();
		obj.setId(SpecmateEcoreUtil.getIdForChild());
		obj.setOriginalText(originalText);
		model.getContents().add(obj);
		model.getObjects().add(obj);
		return obj;
	}

	/**
	 * Create a new object and add it to a specific index of the RGModel
	 *
	 * @param model
	 * @param processedText
	 * @param index
	 * @return the created object
	 */
	public RGObject createObject(RGModel model, String processedText, int index) {
		RGObject obj = RequirementsFactory.eINSTANCE.createRGObject();
		obj.setId(SpecmateEcoreUtil.getIdForChild());
		obj.setProcessedText(processedText);
		model.getContents().add(obj);
		model.getObjects().add(index, obj);
		return obj;
	}

	/**
	 * Find a chunk based on id and return it when found
	 *
	 * @param model
	 * @param id
	 * @return the found chunk or null
	 */
	public RGObject findObject(RGModel model, String id) {
		RGObject obj = null;
		for (RGObject c : model.getObjects()) {
				if (c.getId().equals(id)) {
					obj = (RGObject) c;
			}
		}
		return obj;
	}

	/**
	 * Create a new node and add it to the RGModel
	 *
	 * @param model
	 * @param component
	 * @param temporary
	 * @param x
	 * @param y
	 * @param type
	 * @return the created node
	 */
	public RGNode createNode(RGModel model, String component, boolean temporary, int x, int y, NodeType type) {
		RGNode node = RequirementsFactory.eINSTANCE.createRGNode();
		node.setId(SpecmateEcoreUtil.getIdForChild());
		// node.setName("New RGNode " + dateFormat.format(new Date()));

		component = this.processWord(component);
		node.setName(component);
		component = component.toLowerCase();
		node.setComponent(component);
		node.setTemporary(temporary);
		node.setY(y);
		node.setX(x);
		node.setType(type);
		model.getContents().add(node);
		return node;
	}

	/**
	 * Create a new connection to the RGModel
	 *
	 * @param model
	 * @param nodeFrom
	 * @param nodeTo
	 * @param type
	 * @param negate
	 * @param label
	 * @return
	 */
	public RGConnection createConnection(RGModel model, RGNode nodeFrom, RGNode nodeTo, RGConnectionType type,
			boolean negate, String label) {
		Optional<IModelConnection> optCon = nodeFrom.getOutgoingConnections().stream()
				.filter(conn -> conn.getTarget() == nodeTo).findFirst();
		if (optCon.isPresent()) {
			return (RGConnection) optCon.get();
		}
		RGConnection con = RequirementsFactory.eINSTANCE.createRGConnection();
		con.setId(SpecmateEcoreUtil.getIdForChild());
		con.setSource(nodeFrom);
		con.setTarget(nodeTo);
		con.setNegate(negate);
		con.setName("New Connection " + dateFormat.format(new Date()));
		con.setType(RGConnectionType.COMPOSITION);
		con.setType(type);
		con.setLabel(label);
		model.getContents().add(con);
		return con;
	}

	private String processWord(String string) {
		if (string == null)
			return "";
		String s = string;
		// remove a, the
		if (string.startsWith("a ") || string.startsWith("A ")) {
			s = string.substring(2);
		} else if (string.startsWith("an ") || string.startsWith("An ")) {
			s = string.substring(3);
		} else if (string.startsWith("the ") || string.startsWith("The ")) {
			s = string.substring(4);
		}
		return s.trim();
	}

	/**
	 * Create a new node if it does not exist in the list. Otherwise return the
	 * existing node. Nodes are only compared by name and type
	 *
	 * @param model
	 * @param component
	 * @param x
	 * @param y
	 * @param type
	 * @return new or existing node
	 */
	public RGNode createNodeIfNotExist(RGModel model, String component, int x, int y, NodeType type) {
		component = this.processWord(component);
		EList<IContentElement> list = model.getContents();

		for (IContentElement rgNode : list) {
			if (rgNode instanceof RGNode) {
				if (((RGNode) rgNode).getComponent().equals(component.toLowerCase())
						&& (((RGNode) rgNode).getType().equals(NodeType.NONE)
								|| ((RGNode) rgNode).getType().equals(type))
						&& !((RGNode) rgNode).isTemporary()) {
					((RGNode) rgNode).setType(type);
					return (RGNode) rgNode;
				}
			}
		}
		return createNode(model, component, false, x, y, type);
	}

	public RGNode copyNodeToModel(RGModel model, RGNode node) {
		EList<IContentElement> list = model.getContents();

		for (IContentElement rgNode : list) {
			if (rgNode instanceof RGNode) {
				if (((RGNode) rgNode).getId().equals(node.getId())) {
					return (RGNode) rgNode;
				}
			}
		}
		RGNode n = createNode(model, node.getComponent(), node.isTemporary(), (int) node.getX(), (int) node.getY(),
				node.getType());
		n.setId(node.getId());
		return n;
	}

	/**
	 * Find the old node that exists in the list. Otherwise return null
	 *
	 * @param model
	 * @param component
	 * @param type
	 * @return old node or null
	 */
	public RGNode findOldNode(RGModel model, RGNode node) {
		String component = node.getComponent();
		NodeType type = node.getType();
		component = this.processWord(component);
		component = component.toLowerCase();
		EList<IContentElement> list = model.getContents();

		for (IContentElement rgNode : list) {
			if (rgNode instanceof RGNode) {
				if (((RGNode) rgNode).getComponent().equals(component) && ((RGNode) rgNode).getType().equals(type)
						&& !((RGNode) rgNode).isTemporary()) {
					return (RGNode) rgNode;
				}
			}
		}
		return null;
	}

	public void removeConnection(RGModel model, RGConnection connection) {
		if (connection == null)
			return;
		EList<IContentElement> list = model.getContents();
		list.remove(connection);
		connection.getSource().getOutgoingConnections().remove(connection);
		connection.getTarget().getIncomingConnections().remove(connection);
	}

	/**
	 * Replace the connection between two nodes
	 *
	 * @param model
	 * @param parentNode
	 * @param oldNode
	 * @param tmpNode
	 * @return void
	 */
	public void replaceConnection(RGModel model, RGNode parentNode, RGNode oldNode, RGNode tmpNode) {
		EList<IContentElement> list = model.getContents();

		RGNode actualParentNode = null;
		List<RGConnection> parentNodeConnections = parentNode != null
				? parentNode.getIncomingConnections().stream()
						.filter(c -> !((RGConnection) c).getType().equals(RGConnectionType.CONDITION)
								&& !((RGConnection) c).getType().equals(RGConnectionType.REMOVE)
								&& !((RGConnection) c).getType().equals(RGConnectionType.REPLACE))
						.map(c -> (RGConnection) c).collect(Collectors.toList())
				: new ArrayList<RGConnection>();
		if (parentNode != null && parentNodeConnections.size() >= 1) {
			actualParentNode = (RGNode) parentNodeConnections.get(0).getSource();
		}

		RGConnection replacementCon = null;
		RGNode replacementNode = null;

		// find REPLACE connection tmp --> new
		Optional<RGConnection> r = tmpNode.getOutgoingConnections().stream().map(c -> (RGConnection) c)
				.filter(c -> c.getType().equals(RGConnectionType.REPLACE)).findFirst();

		if (!r.isEmpty()) {
			// if tmp --> new
			// replacementCon = tmp --> new
			// replacementNode = new
			replacementCon = r.get();
			replacementNode = (RGNode) replacementCon.getTarget();
		} else {
			// DELETE operation
			// replacementNode = new node
			replacementNode = createNode(model, tmpNode.getComponent(), true, 0, 0, tmpNode.getType());
		}
		// High lvl algorithm
		/*
		 * 1. find chunks c with o node 
		 * 2. (optional) filter chunks c: p == x.incoming x == c.incoming 
		 * 3.1 for chunk c: replace o node with n node/null 
		 * 3.2 for chunk c: save connected p and x 
		 * 4. for node o: replace p --> o connection with p --> n connection/remove 
		 * 5. for node o: replace o --> x connection with n --> x connection/remove
		 */

		// 1. find chunks c with o node 
		List<RGObject> objs = oldNode.getObjects().stream().collect(Collectors.toList());// model.getObjects().stream()
				//.filter(c -> c.getNode() != null && c.getNode().equals(oldNode)).collect(Collectors.toList());
		List<RGConnection> incomingConnections = oldNode.getIncomingConnections().stream().map(c -> (RGConnection) c)
				.collect(Collectors.toList());
		List<RGConnection> outgoingConnections = oldNode.getOutgoingConnections().stream().map(c -> (RGConnection) c)
				.collect(Collectors.toList());
		boolean negateConnection = false;
		if (r.isEmpty()) {// && incomingConnections.size() == 1 && outgoingConnections.size() == 0) {
			negateConnection = true;
		}

		// 2. (optional) filter chunks c: p == x.incoming x == c.incoming 
		if (actualParentNode != null) {
			ArrayList<RGObject> aggregator = new ArrayList<RGObject>();
			for (RGObject c : objs) {
				boolean include = false;
				for (RGObject x : c.getIncoming()) {
					// TODO MA prob dont need this
					if (x.getNode() != null) {
						System.out.println(x.getNode().getComponent());
						System.out.println(x.getNode().getId());
					}
					if (x.getNode() != null && x.getNode().equals(actualParentNode)) {
						include = true;
					}
					//
					for (RGObject p : x.getIncoming()) {
						if (p.getNode() != null) {
							System.out.println(p.getNode().getComponent());
							System.out.println(p.getNode().getId());
						}
						if (p.getNode() != null && p.getNode().equals(actualParentNode)) {
							include = true;
						}
					}
				}
				if (include) {
					aggregator.add(c);
				}

			}
			objs.retainAll(aggregator);
		}
//		p == x.incoming ; x == c.incoming

		Set<RGNode> parentNodes = new HashSet<RGNode>();
		Set<RGNode> childNodes = new HashSet<RGNode>();

		// 3.1 for chunk c: replace o node with n node/null 
		// 3.2 for chunk c: save connected p and x 
		for (RGObject obj : objs) {
			// 1
			obj.setNode(replacementNode);
			replacementNode.getObjects().add(obj);
			oldNode.getObjects().remove(obj);

//			String m = "^(?i)((a )|(an )|(the ))?(?-i)(.*)";
//			String ct = obj.getOriginalText().trim();
//			String nt = replacementNode.getName();
//			String tmp = ct.replaceAll(m, "$1" + nt);
//			if (replacementCon == null) {
//				tmp = tmp.replaceAll(m, "no $5");
//			}
//			obj.setOriginalText(tmp);

			// 2
			for (RGObject parentChunk : obj.getIncoming()) {
				parentNodes.add(parentChunk.getNode());
			}
			for (RGObject childChunk : obj.getOutgoing()) {
				childNodes.add(childChunk.getNode());
			}
		}

		// 4. for node o: replace p --> o connection with p --> n connection/remove
		for (RGConnection c : incomingConnections) {
			if (replacementNode != null) {
				if (parentNodes.contains(c.getSource())) {
					c.setTarget(replacementNode);
					oldNode.getIncomingConnections().remove(c);
					if (negateConnection) {
						c.setNegate(!c.isNegate());
					}
				}
			} else {
				if (parentNodes.contains(c.getSource())) {
					removeConnection(model, c);
				}
			}
		}

		// 5. for node o: replace o --> x connection with n --> x connection/remove
		for (RGConnection c : outgoingConnections) {
			if (replacementNode != null) {
				if (childNodes.contains(c.getTarget())) {
					c.setSource(replacementNode);
					oldNode.getOutgoingConnections().remove(c);
				}
			} else {
				if (childNodes.contains(c.getTarget())) {
					removeConnection(model, c);
				}
			}
		}

		// assign chunk of tmp node to replacement node instead
		for (RGObject c : tmpNode.getObjects()) {
			c.setNode(replacementNode);
			replacementNode.getObjects().add(c);
			if (replacementCon == null) {
				c.setRemoved(true);
			}
			System.out.println(c.getOriginalText());
		}
		tmpNode.getObjects().retainAll(new ArrayList<>());

		// if delete operation
		if (replacementCon == null) {
			EList<RGObject> replacementChunks = replacementNode.getObjects();
			for (RGObject c : replacementChunks) {
				// set chunk as removed
				System.out.println(c.getOriginalText());
				c.setRemoved(true);
				EList<RGObject> childChunks = c.getOutgoing();
				EList<RGObject> parentChunks = c.getIncoming();
				System.out.println("------------");
				// set child chunks as removed
				for (RGObject cc : childChunks) {
					System.out.println(cc.getOriginalText());
					cc.setRemoved(true);
				}
				System.out.println("------------");
				for (RGObject pc : parentChunks) {
					// if only 1 parent -> set parent chunk as removed
					System.out.println(pc.getOriginalText());
					if (pc.getOutgoing().size() == 1 && pc.getIncoming().size() == 0) {
						System.out.println(pc.getOriginalText());
						pc.setRemoved(true);
					}
				}
				System.out.println("------------");
			}
		}

		// remove new parentNode
		if (parentNode != null) {
			for (RGObject c : parentNode.getObjects()) {
				c.setRemoved(true);
			}
		}

		// remove residuals
		removeConnection(model, replacementCon);
		if (oldNode.getIncomingConnections().size() == 0 && oldNode.getOutgoingConnections().size() == 0) {
			list.remove(oldNode);
		}
	}
	// TODO model update with contradictions -> take latter version
	// TODO model update with same text -> don't duplicate label
	
	public void addNegationNode(RGModel model, RGNode negatedNode) {
		// TODO add POS tags to RGObjects
		// TODO switch negatedNode.parent
		// case negative
			// case verb -> negate verb + add objects
			// default: add 'no' object before negated node
		// case positive
			// case verb -> find negation + change text to " "
			// default -> find negation
	}

	@Override
	@Deprecated
	public RGNode createNode(RGModel model, String component, String condition, int x, int y, NodeType type) {
		return createNode(model, component, false, x, y, type);
	}

	@Override
	@Deprecated
	public RGConnection createConnection(RGModel model, RGNode nodeFrom, RGNode nodeTo, boolean negate) {
		return createConnection(model, nodeFrom, nodeTo, RGConnectionType.COMPOSITION, false, "");
	}

	@Override
	@Deprecated
	public RGNode createNodeIfNotExist(RGModel model, String component, String condition, int x, int y, NodeType type) {
		return createNodeIfNotExist(model, component, x, y, type);
	}

	@Override
	public RGModel createModel() {
		return RequirementsFactory.eINSTANCE.createRGModel();
	}
}
