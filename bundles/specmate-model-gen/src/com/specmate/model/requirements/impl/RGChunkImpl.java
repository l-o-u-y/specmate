/**
 */
package com.specmate.model.requirements.impl;

import com.specmate.model.base.BasePackage;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RequirementsPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Chunk</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getChunkText <em>Chunk Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getChunkId <em>Chunk Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getNodeId <em>Node Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#isVisited <em>Visited</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RGChunkImpl extends CDOObjectImpl implements RGChunk {
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
	 * The default value of the '{@link #getChunkText() <em>Chunk Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChunkText()
	 * @generated
	 * @ordered
	 */
	protected static final String CHUNK_TEXT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getChunkId() <em>Chunk Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChunkId()
	 * @generated
	 * @ordered
	 */
	protected static final int CHUNK_ID_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getNodeId() <em>Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeId()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isVisited() <em>Visited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVisited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VISITED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGChunkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementsPackage.Literals.RG_CHUNK;
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
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__ID, BasePackage.Literals.IID__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__ID, BasePackage.Literals.IID__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getChunkText() {
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__CHUNK_TEXT, RequirementsPackage.Literals.RG_CHUNK__CHUNK_TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChunkText(String newChunkText) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__CHUNK_TEXT, RequirementsPackage.Literals.RG_CHUNK__CHUNK_TEXT, newChunkText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getChunkId() {
		return (Integer)eDynamicGet(RequirementsPackage.RG_CHUNK__CHUNK_ID, RequirementsPackage.Literals.RG_CHUNK__CHUNK_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChunkId(int newChunkId) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__CHUNK_ID, RequirementsPackage.Literals.RG_CHUNK__CHUNK_ID, newChunkId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNodeId() {
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__NODE_ID, RequirementsPackage.Literals.RG_CHUNK__NODE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNodeId(String newNodeId) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__NODE_ID, RequirementsPackage.Literals.RG_CHUNK__NODE_ID, newNodeId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isVisited() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_CHUNK__VISITED, RequirementsPackage.Literals.RG_CHUNK__VISITED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisited(boolean newVisited) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__VISITED, RequirementsPackage.Literals.RG_CHUNK__VISITED, newVisited);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementsPackage.RG_CHUNK__ID:
				return getId();
			case RequirementsPackage.RG_CHUNK__CHUNK_TEXT:
				return getChunkText();
			case RequirementsPackage.RG_CHUNK__CHUNK_ID:
				return getChunkId();
			case RequirementsPackage.RG_CHUNK__NODE_ID:
				return getNodeId();
			case RequirementsPackage.RG_CHUNK__VISITED:
				return isVisited();
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
			case RequirementsPackage.RG_CHUNK__ID:
				setId((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__CHUNK_TEXT:
				setChunkText((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__CHUNK_ID:
				setChunkId((Integer)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__NODE_ID:
				setNodeId((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__VISITED:
				setVisited((Boolean)newValue);
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
			case RequirementsPackage.RG_CHUNK__ID:
				setId(ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__CHUNK_TEXT:
				setChunkText(CHUNK_TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__CHUNK_ID:
				setChunkId(CHUNK_ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__NODE_ID:
				setNodeId(NODE_ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__VISITED:
				setVisited(VISITED_EDEFAULT);
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
			case RequirementsPackage.RG_CHUNK__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case RequirementsPackage.RG_CHUNK__CHUNK_TEXT:
				return CHUNK_TEXT_EDEFAULT == null ? getChunkText() != null : !CHUNK_TEXT_EDEFAULT.equals(getChunkText());
			case RequirementsPackage.RG_CHUNK__CHUNK_ID:
				return getChunkId() != CHUNK_ID_EDEFAULT;
			case RequirementsPackage.RG_CHUNK__NODE_ID:
				return NODE_ID_EDEFAULT == null ? getNodeId() != null : !NODE_ID_EDEFAULT.equals(getNodeId());
			case RequirementsPackage.RG_CHUNK__VISITED:
				return isVisited() != VISITED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //RGChunkImpl
