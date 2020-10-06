/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.ISpecmateModelObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RG Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.specmate.model.requirements.RGModel#getModelRequirements <em>Model Requirements</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGModel#getModelMapping <em>Model Mapping</em>}</li>
 *   <li>{@link com.specmate.model.requirements.RGModel#getChunks <em>Chunks</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel()
 * @model
 * @generated
 */
public interface RGModel extends ISpecmateModelObject {

	/**
	 * Returns the value of the '<em><b>Model Requirements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Requirements</em>' attribute.
	 * @see #setModelRequirements(String)
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel_ModelRequirements()
	 * @model annotation="http://specmate.com/form_meta shortDesc='Model Requirements' longDesc='' required='false' type='longText' rows='5' position='110'"
	 * @generated
	 */
	String getModelRequirements();

	/**
	 * Sets the value of the '{@link com.specmate.model.requirements.RGModel#getModelRequirements <em>Model Requirements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Requirements</em>' attribute.
	 * @see #getModelRequirements()
	 * @generated
	 */
	void setModelRequirements(String value);

	/**
	 * Returns the value of the '<em><b>Model Mapping</b></em>' containment reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Mapping</em>' containment reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel_ModelMapping()
	 * @model containment="true"
	 * @generated
	 */
	EList<RGObject> getModelMapping();

	/**
	 * Returns the value of the '<em><b>Chunks</b></em>' containment reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGChunk}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chunks</em>' containment reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel_Chunks()
	 * @model containment="true"
	 * @generated
	 */
	EList<RGChunk> getChunks();

} // RGModel
