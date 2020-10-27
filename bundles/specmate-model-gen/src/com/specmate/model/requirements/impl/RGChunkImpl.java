/**
 */
package com.specmate.model.requirements.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import com.specmate.model.base.BasePackage;
import com.specmate.model.base.IDescribed;
import com.specmate.model.base.INamed;
import com.specmate.model.base.IRecycled;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.RequirementsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>RG Chunk</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#isRecycled <em>Recycled</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#isHasRecycledChildren <em>Has Recycled Children</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getText <em>Text</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getNode <em>Node</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getIncomingChunks <em>Incoming Chunks</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getOutgoingChunks <em>Outgoing Chunks</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link com.specmate.model.requirements.impl.RGChunkImpl#isRemoved <em>Removed</em>}</li>
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
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

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
	public String getName() {
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__NAME, BasePackage.Literals.INAMED__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__NAME, BasePackage.Literals.INAMED__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__DESCRIPTION, BasePackage.Literals.IDESCRIBED__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRecycled() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_CHUNK__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRecycled(boolean newRecycled) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__RECYCLED, BasePackage.Literals.IRECYCLED__RECYCLED, newRecycled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHasRecycledChildren() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHasRecycledChildren(boolean newHasRecycledChildren) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN, BasePackage.Literals.IRECYCLED__HAS_RECYCLED_CHILDREN, newHasRecycledChildren);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText() {
		return (String)eDynamicGet(RequirementsPackage.RG_CHUNK__TEXT, RequirementsPackage.Literals.RG_CHUNK__TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(String newText) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__TEXT, RequirementsPackage.Literals.RG_CHUNK__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RGNode getNode() {
		return (RGNode)eDynamicGet(RequirementsPackage.RG_CHUNK__NODE, RequirementsPackage.Literals.RG_CHUNK__NODE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGNode basicGetNode() {
		return (RGNode)eDynamicGet(RequirementsPackage.RG_CHUNK__NODE, RequirementsPackage.Literals.RG_CHUNK__NODE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNode(RGNode newNode) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__NODE, RequirementsPackage.Literals.RG_CHUNK__NODE, newNode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGChunk> getIncomingChunks() {
		return (EList<RGChunk>)eDynamicGet(RequirementsPackage.RG_CHUNK__INCOMING_CHUNKS, RequirementsPackage.Literals.RG_CHUNK__INCOMING_CHUNKS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGChunk> getOutgoingChunks() {
		return (EList<RGChunk>)eDynamicGet(RequirementsPackage.RG_CHUNK__OUTGOING_CHUNKS, RequirementsPackage.Literals.RG_CHUNK__OUTGOING_CHUNKS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<RGObject> getObjects() {
		return (EList<RGObject>)eDynamicGet(RequirementsPackage.RG_CHUNK__OBJECTS, RequirementsPackage.Literals.RG_CHUNK__OBJECTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRemoved() {
		return (Boolean)eDynamicGet(RequirementsPackage.RG_CHUNK__REMOVED, RequirementsPackage.Literals.RG_CHUNK__REMOVED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRemoved(boolean newRemoved) {
		eDynamicSet(RequirementsPackage.RG_CHUNK__REMOVED, RequirementsPackage.Literals.RG_CHUNK__REMOVED, newRemoved);
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
			case RequirementsPackage.RG_CHUNK__NAME:
				return getName();
			case RequirementsPackage.RG_CHUNK__DESCRIPTION:
				return getDescription();
			case RequirementsPackage.RG_CHUNK__RECYCLED:
				return isRecycled();
			case RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren();
			case RequirementsPackage.RG_CHUNK__TEXT:
				return getText();
			case RequirementsPackage.RG_CHUNK__NODE:
				if (resolve) return getNode();
				return basicGetNode();
			case RequirementsPackage.RG_CHUNK__INCOMING_CHUNKS:
				return getIncomingChunks();
			case RequirementsPackage.RG_CHUNK__OUTGOING_CHUNKS:
				return getOutgoingChunks();
			case RequirementsPackage.RG_CHUNK__OBJECTS:
				return getObjects();
			case RequirementsPackage.RG_CHUNK__REMOVED:
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
			case RequirementsPackage.RG_CHUNK__ID:
				setId((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__NAME:
				setName((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__RECYCLED:
				setRecycled((Boolean)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren((Boolean)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__TEXT:
				setText((String)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__NODE:
				setNode((RGNode)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__INCOMING_CHUNKS:
				getIncomingChunks().clear();
				getIncomingChunks().addAll((Collection<? extends RGChunk>)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__OUTGOING_CHUNKS:
				getOutgoingChunks().clear();
				getOutgoingChunks().addAll((Collection<? extends RGChunk>)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__OBJECTS:
				getObjects().clear();
				getObjects().addAll((Collection<? extends RGObject>)newValue);
				return;
			case RequirementsPackage.RG_CHUNK__REMOVED:
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
			case RequirementsPackage.RG_CHUNK__ID:
				setId(ID_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__RECYCLED:
				setRecycled(RECYCLED_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN:
				setHasRecycledChildren(HAS_RECYCLED_CHILDREN_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case RequirementsPackage.RG_CHUNK__NODE:
				setNode((RGNode)null);
				return;
			case RequirementsPackage.RG_CHUNK__INCOMING_CHUNKS:
				getIncomingChunks().clear();
				return;
			case RequirementsPackage.RG_CHUNK__OUTGOING_CHUNKS:
				getOutgoingChunks().clear();
				return;
			case RequirementsPackage.RG_CHUNK__OBJECTS:
				getObjects().clear();
				return;
			case RequirementsPackage.RG_CHUNK__REMOVED:
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
			case RequirementsPackage.RG_CHUNK__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case RequirementsPackage.RG_CHUNK__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case RequirementsPackage.RG_CHUNK__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case RequirementsPackage.RG_CHUNK__RECYCLED:
				return isRecycled() != RECYCLED_EDEFAULT;
			case RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN:
				return isHasRecycledChildren() != HAS_RECYCLED_CHILDREN_EDEFAULT;
			case RequirementsPackage.RG_CHUNK__TEXT:
				return TEXT_EDEFAULT == null ? getText() != null : !TEXT_EDEFAULT.equals(getText());
			case RequirementsPackage.RG_CHUNK__NODE:
				return basicGetNode() != null;
			case RequirementsPackage.RG_CHUNK__INCOMING_CHUNKS:
				return !getIncomingChunks().isEmpty();
			case RequirementsPackage.RG_CHUNK__OUTGOING_CHUNKS:
				return !getOutgoingChunks().isEmpty();
			case RequirementsPackage.RG_CHUNK__OBJECTS:
				return !getObjects().isEmpty();
			case RequirementsPackage.RG_CHUNK__REMOVED:
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
				case RequirementsPackage.RG_CHUNK__NAME: return BasePackage.INAMED__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_CHUNK__DESCRIPTION: return BasePackage.IDESCRIBED__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (derivedFeatureID) {
				case RequirementsPackage.RG_CHUNK__RECYCLED: return BasePackage.IRECYCLED__RECYCLED;
				case RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN: return BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN;
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
				case BasePackage.INAMED__NAME: return RequirementsPackage.RG_CHUNK__NAME;
				default: return -1;
			}
		}
		if (baseClass == IDescribed.class) {
			switch (baseFeatureID) {
				case BasePackage.IDESCRIBED__DESCRIPTION: return RequirementsPackage.RG_CHUNK__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == IRecycled.class) {
			switch (baseFeatureID) {
				case BasePackage.IRECYCLED__RECYCLED: return RequirementsPackage.RG_CHUNK__RECYCLED;
				case BasePackage.IRECYCLED__HAS_RECYCLED_CHILDREN: return RequirementsPackage.RG_CHUNK__HAS_RECYCLED_CHILDREN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RGChunkImpl
