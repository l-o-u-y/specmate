/**
 */
package com.specmate.model.testspecification.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import com.specmate.model.base.BasePackage;
import com.specmate.model.base.IDescribed;
import com.specmate.model.base.INamed;
import com.specmate.model.base.IPositionable;
import com.specmate.model.base.IRecycled;
import com.specmate.model.testspecification.TestParameter;
import com.specmate.model.testspecification.TestStep;
import com.specmate.model.testspecification.TestspecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#isRecycled <em>Recycled</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#isHasRecycledChildren <em>Has Recycled Children</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getExpectedOutcome <em>Expected Outcome</em>}</li>
 *   <li>{@link com.specmate.model.testspecification.impl.TestStepImpl#getReferencedTestParameters <em>Referenced Test Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestStepImpl extends CDOObjectImpl implements TestStep {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isRecycled() <em>Recycled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecycled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECYCLED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isHasRecycledChildren() <em>Has Recycled Children</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasRecycledChildren()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_RECYCLED_CHILDREN_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final int POSITION_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getExpectedOutcome() <em>Expected Outcome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpectedOutcome()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPECTED_OUTCOME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestspecificationPackage.Literals.TEST_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(TestspecificationPackage.TEST_STEP__ID, BasePackage.Literals.IID__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__ID, BasePackage.Literals.IID__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(TestspecificationPackage.TEST_STEP__NAME, BasePackage.Literals.INAMED__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__NAME, BasePackage.Literals.INAMED__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(TestspecificationPackage.TEST_STEP__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRecycled() {
		return (Boolean)eDynamicGet(TestspecificationPackage.TEST_STEP__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRecycled(boolean newRecycled) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, newRecycled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHasRecycledChildren() {
		return (Boolean)eDynamicGet(TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHasRecycledChildren(boolean newHasRecycledChildren) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, newHasRecycledChildren);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getPosition() {
		return (Integer)eDynamicGet(TestspecificationPackage.TEST_STEP__POSITION, BasePackage.Literals.IPOSITIONABLE__POSITION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPosition(int newPosition) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__POSITION, BasePackage.Literals.IPOSITIONABLE__POSITION, newPosition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExpectedOutcome() {
		return (String)eDynamicGet(TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME, TestspecificationPackage.Literals.TEST_STEP__EXPECTED_OUTCOME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExpectedOutcome(String newExpectedOutcome) {
		eDynamicSet(TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME, TestspecificationPackage.Literals.TEST_STEP__EXPECTED_OUTCOME, newExpectedOutcome);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<TestParameter> getReferencedTestParameters() {
		return (EList<TestParameter>)eDynamicGet(TestspecificationPackage.TEST_STEP__REFERENCED_TEST_PARAMETERS, TestspecificationPackage.Literals.TEST_STEP__REFERENCED_TEST_PARAMETERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestspecificationPackage.TEST_STEP__ID:
				return getId();
			case TestspecificationPackage.TEST_STEP__NAME:
				return getName();
			case TestspecificationPackage.TEST_STEP__DESCRIPTION:
				return getDescription();
			case TestspecificationPackage.TEST_STEP__RECYCLED:
				return isRecycled();
			case TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren();
			case TestspecificationPackage.TEST_STEP__POSITION:
				return getPosition();
			case TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME:
				return getExpectedOutcome();
			case TestspecificationPackage.TEST_STEP__REFERENCED_TEST_PARAMETERS:
				return getReferencedTestParameters();
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
			case TestspecificationPackage.TEST_STEP__ID:
				setId((String)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__NAME:
				setName((String)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__RECYCLED:
				setRecycled((Boolean)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren((Boolean)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__POSITION:
				setPosition((Integer)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME:
				setExpectedOutcome((String)newValue);
				return;
			case TestspecificationPackage.TEST_STEP__REFERENCED_TEST_PARAMETERS:
				getReferencedTestParameters().clear();
				getReferencedTestParameters().addAll((Collection<? extends TestParameter>)newValue);
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
			case TestspecificationPackage.TEST_STEP__ID:
				setId(ID_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__RECYCLED:
				setRecycled(RECYCLED_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren(HAS_RECYCLED_CHILDREN_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME:
				setExpectedOutcome(EXPECTED_OUTCOME_EDEFAULT);
				return;
			case TestspecificationPackage.TEST_STEP__REFERENCED_TEST_PARAMETERS:
				getReferencedTestParameters().clear();
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
			case TestspecificationPackage.TEST_STEP__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case TestspecificationPackage.TEST_STEP__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case TestspecificationPackage.TEST_STEP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case TestspecificationPackage.TEST_STEP__RECYCLED:
				return isRecycled() != RECYCLED_EDEFAULT;
			case TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren() != HAS_RECYCLED_CHILDREN_EDEFAULT;
			case TestspecificationPackage.TEST_STEP__POSITION:
				return getPosition() != POSITION_EDEFAULT;
			case TestspecificationPackage.TEST_STEP__EXPECTED_OUTCOME:
				return EXPECTED_OUTCOME_EDEFAULT == null ? getExpectedOutcome() != null : !EXPECTED_OUTCOME_EDEFAULT.equals(getExpectedOutcome());
			case TestspecificationPackage.TEST_STEP__REFERENCED_TEST_PARAMETERS:
				return !getReferencedTestParameters().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == INamed.class) {
			switch (derivedFeatureID) {
				case TestspecificationPackage.TEST_STEP__NAME: return BasePackage.INAMED__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (derivedFeatureID) {
				case TestspecificationPackage.TEST_STEP__DESCRIPTION: return BasePackage.IDESCRIBED__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (derivedFeatureID) {
				case TestspecificationPackage.TEST_STEP__RECYCLED: return BasePackage.IRECYCLED__RECYCLED;
				case TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN: return BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == IPositionable.class) {
			switch (derivedFeatureID) {
				case TestspecificationPackage.TEST_STEP__POSITION: return BasePackage.IPOSITIONABLE__POSITION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == INamed.class) {
			switch (baseFeatureID) {
				case BasePackage.INAMED__NAME: return TestspecificationPackage.TEST_STEP__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (baseFeatureID) {
				case BasePackage.IDESCRIBED__DESCRIPTION: return TestspecificationPackage.TEST_STEP__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (baseFeatureID) {
				case BasePackage.IRECYCLED__RECYCLED: return TestspecificationPackage.TEST_STEP__RECYCLED;
				case BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN: return TestspecificationPackage.TEST_STEP__HAS_RECYCLED_CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == IPositionable.class) {
			switch (baseFeatureID) {
				case BasePackage.IPOSITIONABLE__POSITION: return TestspecificationPackage.TEST_STEP__POSITION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TestStepImpl
