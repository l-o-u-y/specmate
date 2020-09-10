/**
 */
package com.specmate.model.requirements;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Chunk</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getChunkText <em>Chunk Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#getNodeId <em>Node Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGChunk#isVisited <em>Visited</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface RGChunk extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Chunk Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chunk Text</em>' attribute.
	 * @see #setChunkText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_ChunkText()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Chunk Text' longDesc='' required='true' value='' type='text' position='1'"
	 * @generated
	 */
	String getChunkText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGChunk#getChunkText <em>Chunk Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chunk Text</em>' attribute.
	 * @see #getChunkText()
	 * @generated
	 */
	void setChunkText(String value);

	/**
	 * Returns the value of the '<em><b>Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Id</em>' attribute.
	 * @see #setNodeId(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_NodeId()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Node Id of Graph' longDesc='' required='false' type='text' position='2'"
	 * @generated
	 */
	String getNodeId();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGChunk#getNodeId <em>Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Id</em>' attribute.
	 * @see #getNodeId()
	 * @generated
	 */
	void setNodeId(String value);

	/**
	 * Returns the value of the '<em><b>Visited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visited</em>' attribute.
	 * @see #setVisited(boolean)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGChunk_Visited()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Visited Flag' longDesc='' required='true' type='checkbox' value='false' position='3'"
	 * @generated
	 */
	boolean isVisited();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGChunk#isVisited <em>Visited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visited</em>' attribute.
	 * @see #isVisited()
	 * @generated
	 */
	void setVisited(boolean value);

} // RGChunk
