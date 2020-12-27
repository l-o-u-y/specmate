/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.IModelConnection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGConnection#isNegate <em>Negate</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGConnection#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGConnection()
 * @model
 * @generated
 */
public interface RGConnection extends IModelConnection {
	/**
	 * Returns the value of the '<em><b>Negate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Negate</em>' attribute.
	 * @see #setNegate(boolean)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGConnection_Negate()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Negate' longDesc='Negation of this connection' type='checkbox' position='1'"
	 * @generated
	 */
	boolean isNegate();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGConnection#isNegate <em>Negate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Negate</em>' attribute.
	 * @see #isNegate()
	 * @generated
	 */
	void setNegate(boolean value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.specmate.model.requirements.RGConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.specmate.model.requirements.RGConnectionType
	 * @see #setType(RGConnectionType)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGConnection_Type()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Type' longDesc='The type of a connection' required='true' type='singleSelection' values='[\"Inheritance\", \"Composition\", \"Action\", \"Condition\"]' position='2'"
	 * @generated
	 */
	RGConnectionType getType();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGConnection#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.specmate.model.requirements.RGConnectionType
	 * @see #getType()
	 * @generated
	 */
	void setType(RGConnectionType value);

} // RGConnection
