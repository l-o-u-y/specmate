/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.IContentElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGWord#getPosition <em>Position</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getPosTag <em>Pos Tag</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getNode <em>Node</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGWord#isRemoved <em>Removed</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord()
 * @model
 * @generated
 */
public interface RGWord extends IContentElement {
	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(int)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_Position()
	 * @model
	 * @generated
	 */
	int getPosition();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(int value);

	/**
	 * Returns the value of the '<em><b>Original Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Text</em>' attribute.
	 * @see #setOriginalText(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_OriginalText()
	 * @model
	 * @generated
	 */
	String getOriginalText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#getOriginalText <em>Original Text</em>}' attribute.
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
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_ProcessedText()
	 * @model
	 * @generated
	 */
	String getProcessedText();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#getProcessedText <em>Processed Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Processed Text</em>' attribute.
	 * @see #getProcessedText()
	 * @generated
	 */
	void setProcessedText(String value);

	/**
	 * Returns the value of the '<em><b>Pos Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pos Tag</em>' attribute.
	 * @see #setPosTag(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_PosTag()
	 * @model
	 * @generated
	 */
	String getPosTag();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#getPosTag <em>Pos Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pos Tag</em>' attribute.
	 * @see #getPosTag()
	 * @generated
	 */
	void setPosTag(String value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(RGNode)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_Node()
	 * @model
	 * @generated
	 */
	RGNode getNode();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(RGNode value);

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGWord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_Incoming()
	 * @model
	 * @generated
	 */
	EList<RGWord> getIncoming();

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGWord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_Outgoing()
	 * @model
	 * @generated
	 */
	EList<RGWord> getOutgoing();

	/**
	 * Returns the value of the '<em><b>Removed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Removed</em>' attribute.
	 * @see #setRemoved(boolean)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGWord_Removed()
	 * @model
	 * @generated
	 */
	boolean isRemoved();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGWord#isRemoved <em>Removed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Removed</em>' attribute.
	 * @see #isRemoved()
	 * @generated
	 */
	void setRemoved(boolean value);

} // RGObject
