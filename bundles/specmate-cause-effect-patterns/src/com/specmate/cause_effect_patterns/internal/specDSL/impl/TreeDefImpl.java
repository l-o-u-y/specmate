/**
 * generated by Xtext 2.17.1
 */
package com.specmate.cause_effect_patterns.internal.specDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.specmate.cause_effect_patterns.internal.specDSL.SpecDSLPackage;
import com.specmate.cause_effect_patterns.internal.specDSL.TreeDef;
import com.specmate.cause_effect_patterns.internal.specDSL.TreeTag;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tree
 * Def</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.specmate.cause_effect_patterns.specDSL.impl.TreeDefImpl#getTrees
 * <em>Trees</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TreeDefImpl extends AbstractElementImpl implements TreeDef {
	/**
	 * The cached value of the '{@link #getTrees() <em>Trees</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTrees()
	 * @generated
	 * @ordered
	 */
	protected EList<TreeTag> trees;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TreeDefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecDSLPackage.Literals.TREE_DEF;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<TreeTag> getTrees() {
		if (trees == null) {
			trees = new EObjectContainmentEList<TreeTag>(TreeTag.class, this, SpecDSLPackage.TREE_DEF__TREES);
		}
		return trees;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case SpecDSLPackage.TREE_DEF__TREES:
			return ((InternalEList<?>) getTrees()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case SpecDSLPackage.TREE_DEF__TREES:
			return getTrees();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case SpecDSLPackage.TREE_DEF__TREES:
			getTrees().clear();
			getTrees().addAll((Collection<? extends TreeTag>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case SpecDSLPackage.TREE_DEF__TREES:
			getTrees().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case SpecDSLPackage.TREE_DEF__TREES:
			return trees != null && !trees.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TreeDefImpl
