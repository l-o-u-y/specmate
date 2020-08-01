/**
 */
package com.specmate.model.requirements.impl;

import com.specmate.model.base.impl.IModelConnectionImpl;

import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RequirementsPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CEG Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGConnectionImpl#isNegate <em>Negate</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGConnectionImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGConnectionImpl#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGConnectionImpl extends IModelConnectionImpl implements RGConnection {
	/**
	 * The default value of the '{@link #isNegate() <em>Negate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEGATE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final RGConnectionType TYPE_EDEFAULT = RGConnectionType.INHERITANCE;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RG_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNegate() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_CONNECTION__NEGATE, RequirementsPackage.Literals.RG_CONNECTION__NEGATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNegate(boolean newNegate) {
		eDynamicSet(RequirementsPackage.RG_CONNECTION__NEGATE, RequirementsPackage.Literals.RG_CONNECTION__NEGATE, newNegate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RGConnectionType getType() {
		return (RGConnectionType)eDynamicGet(RequirementsPackage.RG_CONNECTION__TYPE, RequirementsPackage.Literals.RG_CONNECTION__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(RGConnectionType newType) {
		eDynamicSet(RequirementsPackage.RG_CONNECTION__TYPE, RequirementsPackage.Literals.RG_CONNECTION__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return (String)eDynamicGet(RequirementsPackage.RG_CONNECTION__LABEL, RequirementsPackage.Literals.RG_CONNECTION__LABEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		eDynamicSet(RequirementsPackage.RG_CONNECTION__LABEL, RequirementsPackage.Literals.RG_CONNECTION__LABEL, newLabel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RG_CONNECTION__NEGATE:
				return isNegate();
			case RequirementsPackage.RG_CONNECTION__TYPE:
				return getType();
			case RequirementsPackage.RG_CONNECTION__LABEL:
				return getLabel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RequirementsPackage.RG_CONNECTION__NEGATE:
				setNegate((Boolean)newValue);
				return;
			case RequirementsPackage.RG_CONNECTION__TYPE:
				setType((RGConnectionType)newValue);
				return;
			case RequirementsPackage.RG_CONNECTION__LABEL:
				setLabel((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RequirementsPackage.RG_CONNECTION__NEGATE:
				setNegate(NEGATE_EDEFAULT);
				return;
			case RequirementsPackage.RG_CONNECTION__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RequirementsPackage.RG_CONNECTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RequirementsPackage.RG_CONNECTION__NEGATE:
				return isNegate() != NEGATE_EDEFAULT;
			case RequirementsPackage.RG_CONNECTION__TYPE:
				return getType() != TYPE_EDEFAULT;
			case RequirementsPackage.RG_CONNECTION__LABEL:
				return LABEL_EDEFAULT == null ? getLabel() != null : !LABEL_EDEFAULT.equals(getLabel());
		}
		return super.eIsSet(featureID);
	}

} //RGConnectionImpl
