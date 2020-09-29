/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.IDescribed;
import com.specmate.model.base.IID;
import com.specmate.model.base.IRecycled;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Chunk</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getText <em>Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getNode <em>Node</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getIncomingChunks <em>Incoming Chunks</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getOutgoingChunks <em>Outgoing Chunks</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getObjects <em>Objects</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk()
 * @model
 * @generated
 */
public interface RGChunk extends IID, IDescribed, IRecycled {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGChunk#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(RGNode)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_Node()
	 * @model
	 * @generated
	 */
	RGNode getNode();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGChunk#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(RGNode value);

	/**
	 * Returns the value of the '<em><b>Incoming Chunks</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGChunk}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Chunks</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_IncomingChunks()
	 * @model
	 * @generated
	 */
	EList<RGChunk> getIncomingChunks();

	/**
	 * Returns the value of the '<em><b>Outgoing Chunks</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGChunk}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Chunks</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_OutgoingChunks()
	 * @model
	 * @generated
	 */
	EList<RGChunk> getOutgoingChunks();

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_Objects()
	 * @model
	 * @generated
	 */
	EList<RGObject> getObjects();

} // RGChunk
