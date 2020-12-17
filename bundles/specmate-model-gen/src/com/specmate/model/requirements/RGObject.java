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
 *   <li>{@link com.specmate.model.requirements.RGObject#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getNode <em>Node</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getSiblings <em>Siblings</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGObject#isRemoved <em>Removed</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject()
 * @model
 * @generated
 */
public interface RGObject extends IContentElement {
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
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(RGNode)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Node()
	 * @model
	 * @generated
	 */
	RGNode getNode();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGObject#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(RGNode value);

	/**
	 * Returns the value of the '<em><b>Siblings</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Siblings</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Siblings()
	 * @model
	 * @generated
	 */
	EList<RGObject> getSiblings();

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Incoming()
	 * @model
	 * @generated
	 */
	EList<RGObject> getIncoming();

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Outgoing()
	 * @model
	 * @generated
	 */
	EList<RGObject> getOutgoing();

	/**
	 * Returns the value of the '<em><b>Removed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Removed</em>' attribute.
	 * @see #setRemoved(boolean)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGObject_Removed()
	 * @model
	 * @generated
	 */
	boolean isRemoved();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGObject#isRemoved <em>Removed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Removed</em>' attribute.
	 * @see #isRemoved()
	 * @generated
	 */
	void setRemoved(boolean value);

} // RGObject
