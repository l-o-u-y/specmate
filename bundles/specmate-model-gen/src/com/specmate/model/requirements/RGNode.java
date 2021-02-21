/**
 */
package com.specmate.model.requirements;

import org.eclipse.emf.common.util.EList;

import com.specmate.model.base.IModelNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CEG Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGNode#getType <em>Type</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGNode#getComponent <em>Component</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGNode#getWords <em>Words</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGNode#isTemporary <em>Temporary</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGNode()
 * @model annotation="http://specmate.com/form_meta disabled1='name' disabled2='description'"
 * @generated
 */
public interface RGNode extends IModelNode {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.specmate.model.requirements.NodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.specmate.model.requirements.NodeType
	 * @see #setType(NodeType)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGNode_Type()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Type' longDesc='The type of a node' required='true' type='singleSelection' values='[\"NONE\", \"AND\", \"OR\"]' position='3'"
	 * @generated
	 */
	NodeType getType();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGNode#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.specmate.model.requirements.NodeType
	 * @see #getType()
	 * @generated
	 */
	void setType(NodeType value);

	/**
	 * Returns the value of the '<em><b>Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' attribute.
	 * @see #setComponent(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGNode_Component()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Component' longDesc='The component of a node' required='true' type='text' position='1' allowedPattern='^[^,;|]*$'"
	 * @generated
	 */
	String getComponent();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGNode#getComponent <em>Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' attribute.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(String value);

	/**
	 * Returns the value of the '<em><b>Words</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGWord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Words</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGNode_Words()
	 * @model
	 * @generated
	 */
	EList<RGWord> getWords();

	/**
	 * Returns the value of the '<em><b>Temporary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temporary</em>' attribute.
	 * @see #setTemporary(boolean)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGNode_Temporary()
	 * @model
	 * @generated
	 */
	boolean isTemporary();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGNode#isTemporary <em>Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporary</em>' attribute.
	 * @see #isTemporary()
	 * @generated
	 */
	void setTemporary(boolean value);

} // RGNode
