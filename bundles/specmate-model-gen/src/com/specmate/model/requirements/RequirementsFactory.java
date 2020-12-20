/**
 */
package com.specmate.model.requirements;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.specmate.model.requirements.RequirementsPackage
 * @generated
 */
public interface RequirementsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RequirementsFactory eINSTANCE = com.specmate.model.requirements.impl.RequirementsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement</em>'.
	 * @generated
	 */
	Requirement createRequirement();

	/**
	 * Returns a new object of class '<em>CEG Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CEG Model</em>'.
	 * @generated
	 */
	CEGModel createCEGModel();

	/**
	 * Returns a new object of class '<em>CEG Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CEG Node</em>'.
	 * @generated
	 */
	CEGNode createCEGNode();

	/**
	 * Returns a new object of class '<em>CEG Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CEG Connection</em>'.
	 * @generated
	 */
	CEGConnection createCEGConnection();

	/**
	 * Returns a new object of class '<em>RG Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RG Model</em>'.
	 * @generated
	 */
	RGModel createRGModel();

	/**
	 * Returns a new object of class '<em>RG Word</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RG Word</em>'.
	 * @generated
	 */
	RGWord createRGWord();

	/**
	 * Returns a new object of class '<em>RG Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RG Node</em>'.
	 * @generated
	 */
	RGNode createRGNode();

	/**
	 * Returns a new object of class '<em>RG Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>RG Connection</em>'.
	 * @generated
	 */
	RGConnection createRGConnection();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RequirementsPackage getRequirementsPackage();

} //RequirementsFactory
