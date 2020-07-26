package com.specmate.modelgeneration;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.emf.common.util.EList;

import com.specmate.model.base.IContentElement;
import com.specmate.model.base.IModelConnection;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.NodeType;
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
	 * @param modifier
	 * @param x
	 * @param y
	 * @param type
	 * @return the created node
	 */
	public RGNode createNode(RGModel model, String component, String modifier, int x, int y, NodeType type) {
		component = this.processWord(component);
		component = component.toLowerCase();
		RGNode node = RequirementsFactory.eINSTANCE.createRGNode();
		node.setId(SpecmateEcoreUtil.getIdForChild());
		node.setName("New RGNode " + dateFormat.format(new Date()));
		node.setComponent(component);
		node.setModifier(modifier);
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
	 * @param negate
	 * @return
	 */
	public RGConnection createConnection(RGModel model, RGNode nodeFrom, RGNode nodeTo, RGConnectionType type,
			boolean negate) {
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
		con.setType(type);
		model.getContents().add(con);
		return con;
	}

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
	 * @param list
	 * @param model
	 * @param component
	 * @param modifier
	 * @param x
	 * @param y
	 * @param type
	 * @return new or existing node
	 */
	public RGNode createNodeIfNotExist(RGModel model, String component, String modifier, int x,
			int y, NodeType type) {
		component = this.processWord(component);
		component = component.toLowerCase();
		EList<IContentElement> list = model.getContents();
		
		for (IContentElement rgNode : list) {
			if (((RGNode)rgNode).getComponent().equals(component) && ((RGNode)rgNode).getType().equals(type)) {
				return (RGNode)rgNode;
			}
		}
		RGNode node = createNode(model, component, modifier, x, y, type);
		return node;
	}

	// TODO MA
	// contains words that deviate from the xsl and should be considered abstract
	public static final String[] blacklist = {"we"};
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
			// TODO make path relative
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
}
