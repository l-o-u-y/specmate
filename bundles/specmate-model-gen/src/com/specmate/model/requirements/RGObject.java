/**
 */
package com.specmate.model.requirements;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGObject#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getChunk <em>Chunk</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface RGObject extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Original Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Text</em>' attribute.
	 * @see #setOriginalText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_OriginalText()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Original Word' longDesc='' required='true' type='text' position='1'"
	 * @generated
	 */
	String getOriginalText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGObject#getOriginalText <em>Original Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Text</em>' attribute.
	 * @see #getOriginalText()
	 * @generated
	 */
	void setOriginalText(String value);

	/**
	 * Returns the value of the '<em><b>Processed Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Processed Text</em>' attribute.
	 * @see #setProcessedText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_ProcessedText()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Processed Word' longDesc='' required='false' type='text' position='2'"
	 * @generated
	 */
	String getProcessedText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGObject#getProcessedText <em>Processed Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processed Text</em>' attribute.
	 * @see #getProcessedText()
	 * @generated
	 */
	void setProcessedText(String value);

	/**
	 * Returns the value of the '<em><b>Chunk</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chunk</em>' reference.
	 * @see #setChunk(RGChunk)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Chunk()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Corresponding Chunk' longDesc='' required='false' type='object' position='3'"
	 * @generated
	 */
	RGChunk getChunk();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGObject#getChunk <em>Chunk</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chunk</em>' reference.
	 * @see #getChunk()
	 * @generated
	 */
	void setChunk(RGChunk value);

} // RGObject
