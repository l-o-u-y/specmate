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
import com.specmate.model.requirements.RGWord;
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
	 * Create a new word and add it to the RGModel
	 *
	 * @param model
	 * @param originalText
	 * @return the created word
	 */
	public RGWord createWord(RGModel model, String originalText) {
		RGWord w = RequirementsFactory.eINSTANCE.createRGWord();
		w.setId(SpecmateEcoreUtil.getIdForChild());
		w.setOriginalText(originalText);
		model.getContents().add(w);
		model.getWords().add(w);
		return w;
	}

	/**
	 * Create a new word and add it to a specific index of the RGModel
	 *
	 * @param model
	 * @param processedText
	 * @param index
	 * @return the created word
	 */
	public RGWord createWord(RGModel model, String processedText, int index) {
		RGWord w = RequirementsFactory.eINSTANCE.createRGWord();
		w.setId(SpecmateEcoreUtil.getIdForChild());
		w.setProcessedText(processedText);
		w.setPosTag("");
		model.getContents().add(w);
		model.getWords().add(index, w);
		return w;
	}

	/**
	 * Find a word based on id and return it when found
	 *
	 * @param model
	 * @param id
	 * @return the found word or null
	 */
	public RGWord findWord(RGModel model, String id) {
		RGWord word = null;
		for (RGWord w : model.getWords()) {
				if (w.getId().equals(id)) {
					word = (RGWord) w;
			}
		}
		return word;
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
		node.setName("New RGNode " + dateFormat.format(new Date()));

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
		EList<IContentElement> list = model.getContents();

		for (IContentElement rgNode : list) {
			if (rgNode instanceof RGNode) {
				if (compare(((RGNode) rgNode).getComponent(), component)
						&& (((RGNode) rgNode).getType().equals(NodeType.NONE)
								|| ((RGNode) rgNode).getType().equals(type))
						&& !((RGNode) rgNode).isTemporary()) {
					((RGNode) rgNode).setType(type);
					if (!((RGNode) rgNode).getComponent().substring(0, 1).equals(component.substring(0, 1))) {
						((RGNode) rgNode).setComponent(component.substring(0, 1).toLowerCase() + component.substring(1));
					}
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
		EList<IContentElement> list = model.getContents();

		for (IContentElement rgNode : list) {
			if (rgNode instanceof RGNode) {
				if (compare(((RGNode) rgNode).getComponent(), component)
						&& ((RGNode) rgNode).getType().equals(type)
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
		 * 1. find words w with o node 
		 * 2. (optional) filter words w: p == x.incoming x == w.incoming 
		 * 3.1 for words w: replace o node with n node/null 
		 * 3.2 for words w: save connected p and x 
		 * 4. for node o: replace p --> o connection with p --> n connection/remove 
		 * 5. for node o: replace o --> x connection with n --> x connection/remove
		 */

		// 1. find words w with o node 
		List<RGWord> words = oldNode.getWords().stream().collect(Collectors.toList());
		List<RGConnection> incomingConnections = oldNode.getIncomingConnections().stream().map(c -> (RGConnection) c)
				.collect(Collectors.toList());
		List<RGConnection> outgoingConnections = oldNode.getOutgoingConnections().stream().map(c -> (RGConnection) c)
				.collect(Collectors.toList());
		boolean negateConnection = false;
		if (r.isEmpty()) {
			negateConnection = true;
		}

		// 2. (optional) filter words w: p == x.incoming x == w.incoming 
		if (actualParentNode != null) {
			ArrayList<RGWord> aggregator = new ArrayList<RGWord>();
			for (RGWord w : words) {
				boolean include = false;
				for (RGWord x : w.getIncoming()) {
					for (RGWord p : x.getIncoming()) {
						if (p.getNode() != null && p.getNode().equals(actualParentNode)) {
							include = true;
						}
					}
				}
				if (include) {
					aggregator.add(w);
				}

			}
			words.retainAll(aggregator);
		}
//		p == x.incoming ; x == w.incoming

		Set<RGNode> parentNodes = new HashSet<RGNode>();
		Set<RGNode> childNodes = new HashSet<RGNode>();

		// 3.1 for words w: replace o node with n node/null 
		// 3.2 for words w: save connected p and x 
		for (RGWord word : words) {
			// 2
			for (RGWord parentWord : word.getIncoming()) {
				if (negateConnection && !parentNodes.contains(parentWord.getNode())) {
					addNegationNode(model, parentWord.getNode(), oldNode, true);	
				}
				parentNodes.add(parentWord.getNode());
			}
			for (RGWord childWord : word.getOutgoing()) {
				childNodes.add(childWord.getNode());
			}
			
			// 1
			word.setNode(replacementNode);
			replacementNode.getWords().add(word);
			oldNode.getWords().remove(word);
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

		// assign word of tmp node to replacement node instead
		for (RGWord w : tmpNode.getWords()) {
			w.setNode(replacementNode);
			replacementNode.getWords().add(w);
			if (replacementCon == null) {
				w.setRemoved(true);
			}
		}
		tmpNode.getWords().retainAll(new ArrayList<>());

		// if delete operation
		if (replacementCon == null) {
			EList<RGWord> replacementWords = replacementNode.getWords();
			for (RGWord w : replacementWords) {
				// set word as removed
				w.setRemoved(true);
				EList<RGWord> childWords = w.getOutgoing();
				EList<RGWord> parentWords = w.getIncoming();
				// set child words as removed
				for (RGWord cw : childWords) {
					cw.setRemoved(true);
				}
				for (RGWord pw : parentWords) {
					// if only 1 parent -> set parent words as removed
					if (pw.getOutgoing().size() == 1 && pw.getIncoming().size() == 0) {
						pw.setRemoved(true);
					}
				}
			}
		}

		// remove new parentNode
		if (parentNode != null) {
			for (RGWord w : parentNode.getWords()) {
				w.setRemoved(true);
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
	
	public void addNegationNode(RGModel model, RGNode parentNode, RGNode negatedNode, boolean negated) {
		RGWord verb = null;
		RGWord det = null;
		for (RGWord parentWord : parentNode.getWords()) {
			if (parentWord.getPosTag().contains("VB")) {
				verb = parentWord;
				break;
			}
		}
		int verbIndex = model.getWords().indexOf(verb);
		// ex.) wants to do
		if (verbIndex >= 2 && model.getWords().get(verbIndex - 2).getPosTag().contains("VB")) {
			verb = model.getWords().get(verbIndex - 2);
			verbIndex = model.getWords().indexOf(verb);
		} else if (verbIndex >= 1 && model.getWords().get(verbIndex - 1).getPosTag().contains("MD")) {
			verb = model.getWords().get(verbIndex - 1);
		}

		int nodeIndex = model.getWords().indexOf(negatedNode.getWords().get(0));
		if (model.getWords().get(nodeIndex - 1).getPosTag().equals("DT")) {
			det = model.getWords().get(nodeIndex - 1);
		}

		if (negated) {
			if (verb != null) {
				// TODO MA more advanced?
				String text = verb.getProcessedText();
				String negText = "do";
				if (text.endsWith("s")) {
					verb.setProcessedText(text.substring(0, text.length() - 1));
					negText = "does";
				}
				RGWord not = createWord(model, "not", verbIndex);
				not.setOriginalText("not");
				not.setPosTag("RB");
				if (!verb.getProcessedText().equals("will")) {
					RGWord does = createWord(model, negText, verbIndex);
					does.setOriginalText(negText);
					does.setPosTag("VB");	
				}
				System.out.println("Replaced verb with 'do not' + verb");
				return;
			} else if (det != null) {
				if (det.getProcessedText().equals("a") ||
						det.getProcessedText().equals("an") ||
						det.getProcessedText().equals("the")) {
					det.setProcessedText("no");
					det.setOriginalText("no");
					System.out.println("Replaced determiner with 'no'");
					return;
				} else {
					System.err.println("Determiner found but was " + det.getProcessedText());
				}
			} else {
				RGWord neg = createWord(model, "no", model.getWords().indexOf(negatedNode.getWords().get(0)));
				neg.setOriginalText("no");
				neg.setPosTag("DT");
				System.out.println("Added determiner with 'no'");
				return;
			}
		} else {
			if (verb != null) {
				RGWord n = model.getWords().get(verbIndex - 1);
				RGWord v = model.getWords().get(verbIndex - 2);
				if (( n.getProcessedText().equals("n't") || n.getProcessedText().equals("not") ) && 
						( v.getProcessedText().equals("does") || v.getProcessedText().equals("do") )) {
					if (v.getProcessedText().equals("does")) {
						verb.setProcessedText(verb.getProcessedText()+"s");
						verb.setOriginalText(verb.getProcessedText()+"s");
					}
					model.getWords().remove(n);
					model.getContents().remove(n);
					model.getWords().remove(v);
					model.getContents().remove(v);
					System.out.println("Removed 'do not' from verb");
					return;
				}
			}
			// else
			if (det != null) {
				if (det.getProcessedText().equals("no")) {
					det.setProcessedText("a");
					det.setOriginalText("a");
					System.out.println("Replaced determiner 'no' with 'a'");
					return;
				} else {
					System.err.println("Determiner found but was " + det.getProcessedText());
				}
			}
			System.err.println("Couldn't negate properly");
		}
	}

	/**
	 * Compares two Strings case sensitive except for the first letter
	 * in case one of the words is at the beginning of a sentence
	 *
	 * @param a
	 * @param b
	 * @return boolean
	 */
	private boolean compare(String a, String b) {
		String a2 = a.isEmpty() ? "" : a.substring(0, 1).toLowerCase() + a.substring(1);
		String b2 = b.isEmpty()? "" : b.substring(0, 1).toLowerCase() + b.substring(1);
		return a2.equals(b2);
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
