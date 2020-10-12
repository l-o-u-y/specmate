/**
 */
package com.specmate.model.requirements.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import com.specmate.model.base.BasePackage;
import com.specmate.model.base.IDescribed;
import com.specmate.model.base.IRecycled;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#isRecycled <em>Recycled</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#isHasRecycledChildren <em>Has Recycled Children</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getChunk <em>Chunk</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGObjectImpl extends CDOObjectImpl implements RGObject {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RG_OBJECT;
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
		return (String)eDynamicGet(RequirementsPackage.RG_OBJECT__ID, BasePackage.Literals.IID__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__ID, BasePackage.Literals.IID__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(RequirementsPackage.RG_OBJECT__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRecycled() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_OBJECT__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRecycled(boolean newRecycled) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, newRecycled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHasRecycledChildren() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHasRecycledChildren(boolean newHasRecycledChildren) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, newHasRecycledChildren);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginalText() {
		return (String)eDynamicGet(RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT, RequirementsPackage.Literals.RG_OBJECT__ORIGINAL_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginalText(String newOriginalText) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT, RequirementsPackage.Literals.RG_OBJECT__ORIGINAL_TEXT, newOriginalText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProcessedText() {
		return (String)eDynamicGet(RequirementsPackage.RG_OBJECT__PROCESSED_TEXT, RequirementsPackage.Literals.RG_OBJECT__PROCESSED_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProcessedText(String newProcessedText) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__PROCESSED_TEXT, RequirementsPackage.Literals.RG_OBJECT__PROCESSED_TEXT, newProcessedText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RGChunk getChunk() {
		return (RGChunk)eDynamicGet(RequirementsPackage.RG_OBJECT__CHUNK, RequirementsPackage.Literals.RG_OBJECT__CHUNK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGChunk basicGetChunk() {
		return (RGChunk)eDynamicGet(RequirementsPackage.RG_OBJECT__CHUNK, RequirementsPackage.Literals.RG_OBJECT__CHUNK, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChunk(RGChunk newChunk) {
		eDynamicSet(RequirementsPackage.RG_OBJECT__CHUNK, RequirementsPackage.Literals.RG_OBJECT__CHUNK, newChunk);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RG_OBJECT__ID:
				return getId();
			case RequirementsPackage.RG_OBJECT__DESCRIPTION:
				return getDescription();
			case RequirementsPackage.RG_OBJECT__RECYCLED:
				return isRecycled();
			case RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren();
			case RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT:
				return getOriginalText();
			case RequirementsPackage.RG_OBJECT__PROCESSED_TEXT:
				return getProcessedText();
			case RequirementsPackage.RG_OBJECT__CHUNK:
				if (resolve) return getChunk();
				return basicGetChunk();
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
			case RequirementsPackage.RG_OBJECT__ID:
				setId((String)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__RECYCLED:
				setRecycled((Boolean)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren((Boolean)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT:
				setOriginalText((String)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__PROCESSED_TEXT:
				setProcessedText((String)newValue);
				return;
			case RequirementsPackage.RG_OBJECT__CHUNK:
				setChunk((RGChunk)newValue);
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
			case RequirementsPackage.RG_OBJECT__ID:
				setId(ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__RECYCLED:
				setRecycled(RECYCLED_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren(HAS_RECYCLED_CHILDREN_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT:
				setOriginalText(ORIGINAL_TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__PROCESSED_TEXT:
				setProcessedText(PROCESSED_TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_OBJECT__CHUNK:
				setChunk((RGChunk)null);
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
			case RequirementsPackage.RG_OBJECT__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case RequirementsPackage.RG_OBJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case RequirementsPackage.RG_OBJECT__RECYCLED:
				return isRecycled() != RECYCLED_EDEFAULT;
			case RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren() != HAS_RECYCLED_CHILDREN_EDEFAULT;
			case RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT:
				return ORIGINAL_TEXT_EDEFAULT == null ? getOriginalText() != null : !ORIGINAL_TEXT_EDEFAULT.equals(getOriginalText());
			case RequirementsPackage.RG_OBJECT__PROCESSED_TEXT:
				return PROCESSED_TEXT_EDEFAULT == null ? getProcessedText() != null : !PROCESSED_TEXT_EDEFAULT.equals(getProcessedText());
			case RequirementsPackage.RG_OBJECT__CHUNK:
				return basicGetChunk() != null;
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
		if (baseClass == IDescribed.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_OBJECT__DESCRIPTION: return BasePackage.IDESCRIBED__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_OBJECT__RECYCLED: return BasePackage.IRECYCLED__RECYCLED;
				case RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN: return BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN;
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
		if (baseClass == IDescribed.class) {
			switch (baseFeatureID) {
				case BasePackage.IDESCRIBED__DESCRIPTION: return RequirementsPackage.RG_OBJECT__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (baseFeatureID) {
				case BasePackage.IRECYCLED__RECYCLED: return RequirementsPackage.RG_OBJECT__RECYCLED;
				case BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN: return RequirementsPackage.RG_OBJECT__HAS_RECYCLED_CHILDREN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RGObjectImpl
