package com.specmate.modelgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	public RGNode createNode(RGModel model, String component, boolean deleted, int x, int y, NodeType type) {
		component = this.processWord(component);
		component = component.toLowerCase();
		RGNode node = RequirementsFactory.eINSTANCE.createRGNode();
		node.setId(SpecmateEcoreUtil.getIdForChild());
		node.setName("New RGNode " + dateFormat.format(new Date()));
		node.setComponent(component);
		node.setDeleted(deleted);
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
				if (((RGNode) rgNode).getComponent().equals(component) && ((RGNode) rgNode).getType().equals(type)) {
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
						&& !((RGNode) rgNode).isDeleted()) {
					return (RGNode) rgNode;
				}
			}
		}
		return null;
	}

	public RGNode findReplaceNode(RGModel model, RGNode node) {
		return null;
	}

	/**
	 * Remove the connection between two nodes
	 *
	 * @param model
	 * @param nodeFrom
	 * @param nodeTo
	 * @return void
	 */
	public void replaceConnection(RGModel model, RGNode nodeFrom, RGNode nodeToOld, RGNode nodeToTmp) {
		RGNode nodeToNew = null;
		EList<IContentElement> list = model.getContents();

		// no need to find REMOVE connection tmp --> tmp
		// find REPLACE connection tmp --> new
		RGConnection replaceCon = null;
		for (IModelConnection c : nodeToTmp.getOutgoingConnections()) {
			if (c instanceof RGConnection) {
				if (((RGConnection) c).getType().equals(RGConnectionType.REPLACE)) {
					nodeToNew = ((RGNode) ((RGConnection) c).getTarget());
					replaceCon = (RGConnection) c;
					break;
				}
			}
		}

		// if replace connection found, remove
		if (replaceCon != null) {
			list.remove(replaceCon);
			nodeToTmp.getIncomingConnections().remove(replaceCon);
			nodeToTmp.getOutgoingConnections().remove(replaceCon);
			if (nodeToNew != null) {
				nodeToNew.getIncomingConnections().remove(replaceCon);
			}
		}

		// find connection parent --> old
		RGConnection con = null;
		for (IContentElement rgCon : list) {
			if (rgCon instanceof RGConnection) {
				if (((RGConnection) rgCon).getSource().equals(nodeFrom)
						&& ((RGConnection) rgCon).getTarget().equals(nodeToOld)) {
					con = (RGConnection) rgCon;
					break;
				}
			}
		}
		if (con != null) {
			if (nodeToNew == null) {
				// if delete -> remove connection
				list.remove(con);
				con.getSource().getOutgoingConnections().remove(con);
				nodeToOld.getIncomingConnections().remove(con);
			} else {
				// set target to new -> parent --> new
				con.setTarget(nodeToNew);
				nodeToOld.getIncomingConnections().remove(con);
				nodeToNew.getIncomingConnections().add(con);
			}
		} else {
			if (nodeToNew != null) {
				createConnection(model, nodeFrom, nodeToNew, false);
			}
		}

		// find connections xx --> old
		List<IModelConnection> consOld = nodeToOld.getIncomingConnections();

		// TODO MA what about outgoing connections
		// if no connections xx --> old -> delete node fully
		if (consOld.size() == 0) {
			if (nodeToOld.getOutgoingConnections().size() > 0) {
				// clean up outgoing connections
				for (IModelConnection c : nodeToOld.getOutgoingConnections()) {
					if (nodeToNew == null) {
						list.remove(c);
						((RGConnection) c).getTarget().getIncomingConnections().remove(c);
					} else {
						((RGConnection) c).setSource(nodeToNew);
					}
				}
			}

			list.remove(nodeToOld);
			// also update references with chunks
			for (RGChunk c : nodeToOld.getChunks()) {
				c.setNode(nodeToNew);
			}
			if (nodeToNew != null) {
				nodeToNew.getChunks().addAll(nodeToOld.getChunks());
			}
		}


		// update references with chunks
		for (RGChunk c : nodeToTmp.getChunks()) {
			c.setNode(nodeToNew);
		}
		if (nodeToNew != null) {
			nodeToNew.getChunks().addAll(nodeToTmp.getChunks());
		}
	}

	// TODO MA
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
			// TODO MA make path relative
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
