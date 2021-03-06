/**
 */
package com.specmate.model.requirements.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import com.specmate.model.base.impl.IModelNodeImpl;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGWord;
import com.specmate.model.requirements.RequirementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGNodeImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGNodeImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGNodeImpl#getWords <em>Words</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGNodeImpl#isTemporary <em>Temporary</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGNodeImpl extends IModelNodeImpl implements RGNode {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final NodeType TYPE_EDEFAULT = NodeType.NONE;

	/**
	 * The default value of the '{@link #getComponent() <em>Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isTemporary() <em>Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTemporary()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TEMPORARY_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RG_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeType getType() {
		return (NodeType)eDynamicGet(RequirementsPackage.RG_NODE__TYPE, RequirementsPackage.Literals.RG_NODE__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(NodeType newType) {
		eDynamicSet(RequirementsPackage.RG_NODE__TYPE, RequirementsPackage.Literals.RG_NODE__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getComponent() {
		return (String)eDynamicGet(RequirementsPackage.RG_NODE__COMPONENT, RequirementsPackage.Literals.RG_NODE__COMPONENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComponent(String newComponent) {
		eDynamicSet(RequirementsPackage.RG_NODE__COMPONENT, RequirementsPackage.Literals.RG_NODE__COMPONENT, newComponent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGWord> getWords() {
		return (EList<RGWord>)eDynamicGet(RequirementsPackage.RG_NODE__WORDS, RequirementsPackage.Literals.RG_NODE__WORDS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTemporary() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_NODE__TEMPORARY, RequirementsPackage.Literals.RG_NODE__TEMPORARY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTemporary(boolean newTemporary) {
		eDynamicSet(RequirementsPackage.RG_NODE__TEMPORARY, RequirementsPackage.Literals.RG_NODE__TEMPORARY, newTemporary);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RG_NODE__TYPE:
				return getType();
			case RequirementsPackage.RG_NODE__COMPONENT:
				return getComponent();
			case RequirementsPackage.RG_NODE__WORDS:
				return getWords();
			case RequirementsPackage.RG_NODE__TEMPORARY:
				return isTemporary();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RequirementsPackage.RG_NODE__TYPE:
				setType((NodeType)newValue);
				return;
			case RequirementsPackage.RG_NODE__COMPONENT:
				setComponent((String)newValue);
				return;
			case RequirementsPackage.RG_NODE__WORDS:
				getWords().clear();
				getWords().addAll((Collection<? extends RGWord>)newValue);
				return;
			case RequirementsPackage.RG_NODE__TEMPORARY:
				setTemporary((Boolean)newValue);
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
			case RequirementsPackage.RG_NODE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RequirementsPackage.RG_NODE__COMPONENT:
				setComponent(COMPONENT_EDEFAULT);
				return;
			case RequirementsPackage.RG_NODE__WORDS:
				getWords().clear();
				return;
			case RequirementsPackage.RG_NODE__TEMPORARY:
				setTemporary(TEMPORARY_EDEFAULT);
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
			case RequirementsPackage.RG_NODE__TYPE:
				return getType() != TYPE_EDEFAULT;
			case RequirementsPackage.RG_NODE__COMPONENT:
				return COMPONENT_EDEFAULT == null ? getComponent() != null : !COMPONENT_EDEFAULT.equals(getComponent());
			case RequirementsPackage.RG_NODE__WORDS:
				return !getWords().isEmpty();
			case RequirementsPackage.RG_NODE__TEMPORARY:
				return isTemporary() != TEMPORARY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //RGNodeImpl
