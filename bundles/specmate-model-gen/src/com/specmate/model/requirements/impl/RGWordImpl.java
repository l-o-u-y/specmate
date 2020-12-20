/**
 */
package com.specmate.model.requirements.impl;

import com.specmate.model.base.BasePackage;
import com.specmate.model.base.IDescribed;
import com.specmate.model.base.INamed;
import com.specmate.model.base.IRecycled;

import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGWord;
import com.specmate.model.requirements.RequirementsPackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Word</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#isRecycled <em>Recycled</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#isHasRecycledChildren <em>Has Recycled Children</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getPosTag <em>Pos Tag</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getNode <em>Node</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGWordImpl#isRemoved <em>Removed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGWordImpl extends CDOObjectImpl implements RGWord {
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
	 * The default value of the '{@link #getOriginalText() <em>Original Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalText()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_TEXT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getProcessedText() <em>Processed Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessedText()
	 * @generated
	 * @ordered
	 */
	protected static final String PROCESSED_TEXT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPosTag() <em>Pos Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosTag()
	 * @generated
	 * @ordered
	 */
	protected static final String POS_TAG_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isRemoved() <em>Removed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRemoved()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REMOVED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGWordImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RG_WORD;
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
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__ID, BasePackage.Literals.IID__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(RequirementsPackage.RG_WORD__ID, BasePackage.Literals.IID__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__NAME, BasePackage.Literals.INAMED__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(RequirementsPackage.RG_WORD__NAME, BasePackage.Literals.INAMED__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(RequirementsPackage.RG_WORD__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRecycled() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_WORD__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRecycled(boolean newRecycled) {
		eDynamicSet(RequirementsPackage.RG_WORD__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, newRecycled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHasRecycledChildren() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHasRecycledChildren(boolean newHasRecycledChildren) {
		eDynamicSet(RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, newHasRecycledChildren);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginalText() {
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__ORIGINAL_TEXT, RequirementsPackage.Literals.RG_WORD__ORIGINAL_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginalText(String newOriginalText) {
		eDynamicSet(RequirementsPackage.RG_WORD__ORIGINAL_TEXT, RequirementsPackage.Literals.RG_WORD__ORIGINAL_TEXT, newOriginalText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProcessedText() {
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__PROCESSED_TEXT, RequirementsPackage.Literals.RG_WORD__PROCESSED_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProcessedText(String newProcessedText) {
		eDynamicSet(RequirementsPackage.RG_WORD__PROCESSED_TEXT, RequirementsPackage.Literals.RG_WORD__PROCESSED_TEXT, newProcessedText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPosTag() {
		return (String)eDynamicGet(RequirementsPackage.RG_WORD__POS_TAG, RequirementsPackage.Literals.RG_WORD__POS_TAG, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPosTag(String newPosTag) {
		eDynamicSet(RequirementsPackage.RG_WORD__POS_TAG, RequirementsPackage.Literals.RG_WORD__POS_TAG, newPosTag);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RGNode getNode() {
		return (RGNode)eDynamicGet(RequirementsPackage.RG_WORD__NODE, RequirementsPackage.Literals.RG_WORD__NODE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGNode basicGetNode() {
		return (RGNode)eDynamicGet(RequirementsPackage.RG_WORD__NODE, RequirementsPackage.Literals.RG_WORD__NODE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNode(RGNode newNode) {
		eDynamicSet(RequirementsPackage.RG_WORD__NODE, RequirementsPackage.Literals.RG_WORD__NODE, newNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGWord> getIncoming() {
		return (EList<RGWord>)eDynamicGet(RequirementsPackage.RG_WORD__INCOMING, RequirementsPackage.Literals.RG_WORD__INCOMING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGWord> getOutgoing() {
		return (EList<RGWord>)eDynamicGet(RequirementsPackage.RG_WORD__OUTGOING, RequirementsPackage.Literals.RG_WORD__OUTGOING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRemoved() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_WORD__REMOVED, RequirementsPackage.Literals.RG_WORD__REMOVED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRemoved(boolean newRemoved) {
		eDynamicSet(RequirementsPackage.RG_WORD__REMOVED, RequirementsPackage.Literals.RG_WORD__REMOVED, newRemoved);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RG_WORD__ID:
				return getId();
			case RequirementsPackage.RG_WORD__NAME:
				return getName();
			case RequirementsPackage.RG_WORD__DESCRIPTION:
				return getDescription();
			case RequirementsPackage.RG_WORD__RECYCLED:
				return isRecycled();
			case RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren();
			case RequirementsPackage.RG_WORD__ORIGINAL_TEXT:
				return getOriginalText();
			case RequirementsPackage.RG_WORD__PROCESSED_TEXT:
				return getProcessedText();
			case RequirementsPackage.RG_WORD__POS_TAG:
				return getPosTag();
			case RequirementsPackage.RG_WORD__NODE:
				if (resolve) return getNode();
				return basicGetNode();
			case RequirementsPackage.RG_WORD__INCOMING:
				return getIncoming();
			case RequirementsPackage.RG_WORD__OUTGOING:
				return getOutgoing();
			case RequirementsPackage.RG_WORD__REMOVED:
				return isRemoved();
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
			case RequirementsPackage.RG_WORD__ID:
				setId((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__NAME:
				setName((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__RECYCLED:
				setRecycled((Boolean)newValue);
				return;
			case RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren((Boolean)newValue);
				return;
			case RequirementsPackage.RG_WORD__ORIGINAL_TEXT:
				setOriginalText((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__PROCESSED_TEXT:
				setProcessedText((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__POS_TAG:
				setPosTag((String)newValue);
				return;
			case RequirementsPackage.RG_WORD__NODE:
				setNode((RGNode)newValue);
				return;
			case RequirementsPackage.RG_WORD__INCOMING:
				getIncoming().clear();
				getIncoming().addAll((Collection<? extends RGWord>)newValue);
				return;
			case RequirementsPackage.RG_WORD__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends RGWord>)newValue);
				return;
			case RequirementsPackage.RG_WORD__REMOVED:
				setRemoved((Boolean)newValue);
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
			case RequirementsPackage.RG_WORD__ID:
				setId(ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__RECYCLED:
				setRecycled(RECYCLED_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren(HAS_RECYCLED_CHILDREN_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__ORIGINAL_TEXT:
				setOriginalText(ORIGINAL_TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__PROCESSED_TEXT:
				setProcessedText(PROCESSED_TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__POS_TAG:
				setPosTag(POS_TAG_EDEFAULT);
				return;
			case RequirementsPackage.RG_WORD__NODE:
				setNode((RGNode)null);
				return;
			case RequirementsPackage.RG_WORD__INCOMING:
				getIncoming().clear();
				return;
			case RequirementsPackage.RG_WORD__OUTGOING:
				getOutgoing().clear();
				return;
			case RequirementsPackage.RG_WORD__REMOVED:
				setRemoved(REMOVED_EDEFAULT);
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
			case RequirementsPackage.RG_WORD__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case RequirementsPackage.RG_WORD__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case RequirementsPackage.RG_WORD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case RequirementsPackage.RG_WORD__RECYCLED:
				return isRecycled() != RECYCLED_EDEFAULT;
			case RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren() != HAS_RECYCLED_CHILDREN_EDEFAULT;
			case RequirementsPackage.RG_WORD__ORIGINAL_TEXT:
				return ORIGINAL_TEXT_EDEFAULT == null ? getOriginalText() != null : !ORIGINAL_TEXT_EDEFAULT.equals(getOriginalText());
			case RequirementsPackage.RG_WORD__PROCESSED_TEXT:
				return PROCESSED_TEXT_EDEFAULT == null ? getProcessedText() != null : !PROCESSED_TEXT_EDEFAULT.equals(getProcessedText());
			case RequirementsPackage.RG_WORD__POS_TAG:
				return POS_TAG_EDEFAULT == null ? getPosTag() != null : !POS_TAG_EDEFAULT.equals(getPosTag());
			case RequirementsPackage.RG_WORD__NODE:
				return basicGetNode() != null;
			case RequirementsPackage.RG_WORD__INCOMING:
				return !getIncoming().isEmpty();
			case RequirementsPackage.RG_WORD__OUTGOING:
				return !getOutgoing().isEmpty();
			case RequirementsPackage.RG_WORD__REMOVED:
				return isRemoved() != REMOVED_EDEFAULT;
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
				case RequirementsPackage.RG_WORD__NAME: return BasePackage.INAMED__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_WORD__DESCRIPTION: return BasePackage.IDESCRIBED__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_WORD__RECYCLED: return BasePackage.IRECYCLED__RECYCLED;
				case RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN: return BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN;
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
				case BasePackage.INAMED__NAME: return RequirementsPackage.RG_WORD__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (baseFeatureID) {
				case BasePackage.IDESCRIBED__DESCRIPTION: return RequirementsPackage.RG_WORD__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (baseFeatureID) {
				case BasePackage.IRECYCLED__RECYCLED: return RequirementsPackage.RG_WORD__RECYCLED;
				case BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN: return RequirementsPackage.RG_WORD__HAS_RECYCLED_CHILDREN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RGWordImpl
