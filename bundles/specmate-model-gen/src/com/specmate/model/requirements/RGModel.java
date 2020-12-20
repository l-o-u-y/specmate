/**
 */
package com.specmate.model.requirements;

import com.specmate.model.base.IContentElement;
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
 *   <li>{@link com.specmate.model.requirements.RGModel#getWords <em>Words</em>}</li>
 * </ul>
 *
 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel()
 * @model
 * @generated
 */
public interface RGModel extends ISpecmateModelObject, IContentElement {

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
	 * Returns the value of the '<em><b>Words</b></em>' reference list.
	 * The list contents are of type {@link com.specmate.model.requirements.RGWord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Words</em>' reference list.
	 * @see com.specmate.model.requirements.RequirementsPackage#getRGModel_Words()
	 * @model
	 * @generated
	 */
	EList<RGWord> getWords();

} // RGModel
