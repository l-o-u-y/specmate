package com.specmate.modelgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.emf.common.util.EList;

import com.specmate.model.base.IContentElement;
import com.specmate.model.base.IModelConnection;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.model.support.util.SpecmateEcoreUtil;

/**
 * Class creates Node and Edges for a CEG-Graphs
 *
 * @author Andreas Wehrle
 *
 */
public class RGCreation extends Creation<RGModel, RGNode, RGConnection> {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Create a new node and add it to the RGModel
	 *
	 * @param model
	 * @param component
	 * @param x
	 * @param y
	 * @param type
	 * @return the created node
	 */
	public RGNode createNode(RGModel model, String component, boolean temporary, int x, int y, NodeType type) {
		component = this.processWord(component);
		component = component.toLowerCase();
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
		RGConnection conn = createConnection(model, nodeFrom, nodeTo, type, negate);
		conn.setLabel(label);
		return conn;
	}

	/**
	 * Create a new connection to the RGModel
	 *
	 * @param model
	 * @param nodeFrom
	 * @param nodeTo
	 * @param type
	 * @param negate
	 * @return
	 */
	public RGConnection createConnection(RGModel model, RGNode nodeFrom, RGNode nodeTo, RGConnectionType type,
			boolean negate) {
		RGConnection conn = createConnection(model, nodeFrom, nodeTo, negate);
		conn.setType(type);
		return conn;
	}

	/**
	 * Create a new connection to the RGModel
	 *
	 * @param model
	 * @param nodeFrom
	 * @param nodeTo
	 * @param negate
	 * @return
	 */
	public RGConnection createConnection(RGModel model, RGNode nodeFrom, RGNode nodeTo, boolean negate) {
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
		model.getContents().add(con);
		return con;
	}

	private String processWord(String string) {
		if (string == null)
			return "";
		String s = string;
		// remove a, the
		if (string.startsWith("a ")) {
			s = string.substring(2);
		} else if (string.startsWith("an ")) {
			s = string.substring(3);
		} else if (string.startsWith("the ")) {
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
		return createNode(model, component, false, x, y, type);
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
		if (connection == null) return;
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

		RGConnection replacementCon = null;
		RGNode replacementNode = null;

		// find REPLACE connection tmp --> new
		Optional<RGConnection> r = tmpNode.getOutgoingConnections().stream().map(c -> (RGConnection)c)
				.filter(c -> c.getType().equals(RGConnectionType.REPLACE) ).findFirst();

		if (!r.isEmpty()) {
			replacementCon = r.get();
			replacementNode = (RGNode) replacementCon.getTarget();
		} 
		else {
			replacementNode = createNode(model, tmpNode.getComponent(), true, 0, 0, tmpNode.getType());
		}
		// High lvl algorithm
		/*
		1. find chunks c with o node
		2. (optional) filter chunks c: p == c.incoming
		3.1 for chunk c: replace o node with n node/null
		3.2 for chunk c: save connected p and x
		4. for node o: replace p --> o connection with p --> n connection/remove
		5. for node o: replace o --> x connection with n --> x connection/remove
		*/

		// 1
		List<RGChunk> chunks = model.getContents().stream()
				.filter(c -> c instanceof RGChunk).map(c -> (RGChunk)c)
				.filter(c -> c.getNode() != null && c.getNode().equals(oldNode)).collect(Collectors.toList());
		List<RGConnection> incomingConnections = oldNode.getIncomingConnections().stream().map(c -> (RGConnection)c).collect(Collectors.toList());
		List<RGConnection> outgoingConnections = oldNode.getOutgoingConnections().stream().map(c -> (RGConnection)c).collect(Collectors.toList());
		boolean negateConnection = false;
		if (r.isEmpty() && incomingConnections.size() == 1 && outgoingConnections.size() == 0) {
			negateConnection = true;
		}
		
		
		// 2
		if (parentNode != null) {
			chunks.stream().filter(c -> 
				c.getIncomingChunks().stream()
				.filter(i -> i.getNode().equals(parentNode))
				.collect(Collectors.toList()).size() > 0)
			.collect(Collectors.toList());
		}

		Set<RGNode> parentNodes = new HashSet<RGNode>();
		Set<RGNode> childNodes = new HashSet<RGNode>();
		
		// 3
		for (RGChunk chunk : chunks) {
			// 1
			chunk.setNode(replacementNode);
			replacementNode.getChunks().add(chunk);
			oldNode.getChunks().remove(chunk);
			// 2
			for (RGChunk parentChunk : chunk.getIncomingChunks()) {
				parentNodes.add(parentChunk.getNode());
			}
			for (RGChunk childChunk : chunk.getOutgoingChunks()) {
				childNodes.add(childChunk.getNode());
			}
		}
		
		// 4
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

		// 5
		for (RGConnection c : outgoingConnections ) {
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
		for (RGChunk c : tmpNode.getChunks()) {
			c.setNode(replacementNode);
			replacementNode.getChunks().add(c);
			if (replacementCon == null) {
				c.setRemoved(true);
			}
		}
		tmpNode.getChunks().retainAll(new ArrayList<>());
		
		// if delete operation
		if (replacementCon == null) {
			EList<RGChunk> replacementChunks = replacementNode.getChunks();
			for (RGChunk c : replacementChunks) {
				// set chunk as removed
				System.out.println(c.getText());
				c.setRemoved(true);
				EList<RGChunk> childChunks = c.getOutgoingChunks();
				EList<RGChunk> parentChunks = c.getIncomingChunks();
				System.out.println("------------");
				// set child chunks as removed
				for (RGChunk cc : childChunks) {
					System.out.println(cc.getText());
					cc.setRemoved(true);
				}
				System.out.println("------------");
				for (RGChunk pc : parentChunks) {
					// if only 1 parent -> set parent chunk as removed
					System.out.println(pc.getText());
					if (pc.getOutgoingChunks().size() == 1 && pc.getIncomingChunks().size() == 0) {
						System.out.println(pc.getText());
						pc.setRemoved(true);
					}
				}
				System.out.println("------------");
			}
		}

		// remove residuals
		removeConnection(model, replacementCon);
		if (oldNode.getIncomingConnections().size() == 0 && oldNode.getOutgoingConnections().size() == 0) {
			list.remove(oldNode);			
		}
		
	}

	// TODO MA nouns: concreteness rating
	// contains words that deviate from the xsl and should be considered abstract
	public static final String[] blacklist = { "we" };
	// contains words that deviate from the xsl and should be considered concrete
	public static final String[] whitelist = {};

	/**
	 * Checks whether the noun is concrete or abstract based on concreteness rating
	 * from an excel file
	 *
	 * @param noun
	 * @return boolean if noun is concrete
	 */
	public boolean isConcrete(String noun) {
		noun = this.processWord(noun);
		if (Arrays.asList(blacklist).contains(noun)) {
			return false;
		}
		if (Arrays.asList(whitelist).contains(noun)) {
			return true;
		}

		// obtaining input bytes from a file
		FileInputStream fis;
		try {
			// TODO MA misc: make path to concreteness xls relative
			String path = "C:\\Users\\Lena\\Desktop\\Masterarbeit\\delta-descriptions\\papers\\Concreteness_ratings_Brysbaert_et_al_BRM.xls";
			fis = new FileInputStream(new File(path));

			// creating workbook instance that refers to .xls file
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			// creating a Sheet object to retrieve the object
			HSSFSheet sheet = wb.getSheetAt(0);
			// evaluating cell type
			for (Row row : sheet) // iteration over row using for each loop
			{
				// get first column = noun
				Cell cell = row.getCell(0);
				// check if noun found
				if (cell.getStringCellValue().equals(noun)) {
					// get rating
					Cell rating = row.getCell(2);
					// we say rating of 3 or higher = concrete
					if (rating.getNumericCellValue() <= 3) {
						System.out.println("Abstract noun: " + noun + " - " + rating.getNumericCellValue());
					}
					return rating.getNumericCellValue() > 3;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// default: if word can't be found we say it's concrete
		return true;
	}

	@Override
	public RGNode createNode(RGModel model, String component, String condition, int x, int y, NodeType type) {
		return createNode(model, component, false, x, y, type);
	}

	@Override
	public RGNode createNodeIfNotExist(RGModel model, String component, String condition, int x, int y, NodeType type) {
		return createNodeIfNotExist(model, component, x, y, type);
	}

	@Override
	public RGModel createModel() {
		return RequirementsFactory.eINSTANCE.createRGModel();
	}
}
