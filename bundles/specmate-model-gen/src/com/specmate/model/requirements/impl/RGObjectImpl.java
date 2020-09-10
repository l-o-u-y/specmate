/**
 */
package com.specmate.model.requirements.impl;

import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getOriginalText <em>Original Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getProcessedText <em>Processed Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGObjectImpl#getChunk <em>Chunk</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGObjectImpl extends CDOObjectImpl implements RGObject {
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
			case RequirementsPackage.RG_OBJECT__ORIGINAL_TEXT:
				return ORIGINAL_TEXT_EDEFAULT == null ? getOriginalText() != null : !ORIGINAL_TEXT_EDEFAULT.equals(getOriginalText());
			case RequirementsPackage.RG_OBJECT__PROCESSED_TEXT:
				return PROCESSED_TEXT_EDEFAULT == null ? getProcessedText() != null : !PROCESSED_TEXT_EDEFAULT.equals(getProcessedText());
			case RequirementsPackage.RG_OBJECT__CHUNK:
				return basicGetChunk() != null;
		}
		return super.eIsSet(featureID);
	}

} //RGObjectImpl
