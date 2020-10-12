/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.IDescribed;
import com.specmate.model.base.IID;
import com.specmate.model.base.IRecycled;

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
 * @generated
 */
public interface RGObject extends IID, IDescribed, IRecycled {
	/**
	 * Returns the value of the '<em><b>Original Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Text</em>' attribute.
	 * @see #setOriginalText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_OriginalText()
	 * @model
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
	 * @model
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
	 * @model
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
