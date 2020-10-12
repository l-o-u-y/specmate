/**
 */
package com.specmate.model.testspecification.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import com.specmate.model.administration.AdministrationPackage;
import com.specmate.model.administration.impl.AdministrationPackageImpl;
import com.specmate.model.base.BasePackage;
import com.specmate.model.base.impl.BasePackageImpl;
import com.specmate.model.batch.BatchPackage;
import com.specmate.model.batch.impl.BatchPackageImpl;
import com.specmate.model.export.ExportPackage;
import com.specmate.model.export.impl.ExportPackageImpl;
import com.specmate.model.history.HistoryPackage;
import com.specmate.model.history.impl.HistoryPackageImpl;
import com.specmate.model.processes.ProcessesPackage;
import com.specmate.model.processes.impl.ProcessesPackageImpl;
import com.specmate.model.requirements.RequirementsPackage;
import com.specmate.model.requirements.impl.RequirementsPackageImpl;
import com.specmate.model.testspecification.ParameterAssignment;
import com.specmate.model.testspecification.ParameterType;
import com.specmate.model.testspecification.TestCase;
import com.specmate.model.testspecification.TestParameter;
import com.specmate.model.testspecification.TestProcedure;
import com.specmate.model.testspecification.TestSpecification;
import com.specmate.model.testspecification.TestStep;
import com.specmate.model.testspecification.TestspecificationFactory;
import com.specmate.model.testspecification.TestspecificationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestspecificationPackageImpl extends EPackageImpl implements TestspecificationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testProcedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameterTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.specmate.model.testspecification.TestspecificationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestspecificationPackageImpl() {
		super(eNS_URI, TestspecificationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link TestspecificationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestspecificationPackage init() {
		if (isInited) return (TestspecificationPackage)EPackage.Registry.INSTANCE.getEPackage(TestspecificationPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTestspecificationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TestspecificationPackageImpl theTestspecificationPackage = registeredTestspecificationPackage instanceof TestspecificationPackageImpl ? (TestspecificationPackageImpl)registeredTestspecificationPackage : new TestspecificationPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);
		BasePackageImpl theBasePackage = (BasePackageImpl)(registeredPackage instanceof BasePackageImpl ? registeredPackage : BasePackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(RequirementsPackage.eNS_URI);
		RequirementsPackageImpl theRequirementsPackage = (RequirementsPackageImpl)(registeredPackage instanceof RequirementsPackageImpl ? registeredPackage : RequirementsPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ProcessesPackage.eNS_URI);
		ProcessesPackageImpl theProcessesPackage = (ProcessesPackageImpl)(registeredPackage instanceof ProcessesPackageImpl ? registeredPackage : ProcessesPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(HistoryPackage.eNS_URI);
		HistoryPackageImpl theHistoryPackage = (HistoryPackageImpl)(registeredPackage instanceof HistoryPackageImpl ? registeredPackage : HistoryPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(AdministrationPackage.eNS_URI);
		AdministrationPackageImpl theAdministrationPackage = (AdministrationPackageImpl)(registeredPackage instanceof AdministrationPackageImpl ? registeredPackage : AdministrationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BatchPackage.eNS_URI);
		BatchPackageImpl theBatchPackage = (BatchPackageImpl)(registeredPackage instanceof BatchPackageImpl ? registeredPackage : BatchPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExportPackage.eNS_URI);
		ExportPackageImpl theExportPackage = (ExportPackageImpl)(registeredPackage instanceof ExportPackageImpl ? registeredPackage : ExportPackage.eINSTANCE);

		// Create package meta-data objects
		theTestspecificationPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theRequirementsPackage.createPackageContents();
		theProcessesPackage.createPackageContents();
		theHistoryPackage.createPackageContents();
		theAdministrationPackage.createPackageContents();
		theBatchPackage.createPackageContents();
		theExportPackage.createPackageContents();

		// Initialize created meta-data
		theTestspecificationPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theRequirementsPackage.initializePackageContents();
		theProcessesPackage.initializePackageContents();
		theHistoryPackage.initializePackageContents();
		theAdministrationPackage.initializePackageContents();
		theBatchPackage.initializePackageContents();
		theExportPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestspecificationPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestspecificationPackage.eNS_URI, theTestspecificationPackage);
		return theTestspecificationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestSpecification() {
		return testSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestParameter() {
		return testParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestParameter_Type() {
		return (EAttribute)testParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestParameter_Assignments() {
		return (EReference)testParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestCase() {
		return testCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestCase_Consistent() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameterAssignment() {
		return parameterAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParameterAssignment_Parameter() {
		return (EReference)parameterAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterAssignment_Value() {
		return (EAttribute)parameterAssignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterAssignment_Condition() {
		return (EAttribute)parameterAssignmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestProcedure() {
		return testProcedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestProcedure_IsRegressionTest() {
		return (EAttribute)testProcedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestStep() {
		return testStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestStep_ExpectedOutcome() {
		return (EAttribute)testStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTestStep_ReferencedTestParameters() {
		return (EReference)testStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getParameterType() {
		return parameterTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestspecificationFactory getTestspecificationFactory() {
		return (TestspecificationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testSpecificationEClass = createEClass(TEST_SPECIFICATION);

		testParameterEClass = createEClass(TEST_PARAMETER);
		createEAttribute(testParameterEClass, TEST_PARAMETER__TYPE);
		createEReference(testParameterEClass, TEST_PARAMETER__ASSIGNMENTS);

		testCaseEClass = createEClass(TEST_CASE);
		createEAttribute(testCaseEClass, TEST_CASE__CONSISTENT);

		parameterAssignmentEClass = createEClass(PARAMETER_ASSIGNMENT);
		createEReference(parameterAssignmentEClass, PARAMETER_ASSIGNMENT__PARAMETER);
		createEAttribute(parameterAssignmentEClass, PARAMETER_ASSIGNMENT__VALUE);
		createEAttribute(parameterAssignmentEClass, PARAMETER_ASSIGNMENT__CONDITION);

		testProcedureEClass = createEClass(TEST_PROCEDURE);
		createEAttribute(testProcedureEClass, TEST_PROCEDURE__IS_REGRESSION_TEST);

		testStepEClass = createEClass(TEST_STEP);
		createEAttribute(testStepEClass, TEST_STEP__EXPECTED_OUTCOME);
		createEReference(testStepEClass, TEST_STEP__REFERENCED_TEST_PARAMETERS);

		// Create enums
		parameterTypeEEnum = createEEnum(PARAMETER_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		BasePackage theBasePackage = (BasePackage)EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		testSpecificationEClass.getESuperTypes().add(theBasePackage.getIContainer());
		testParameterEClass.getESuperTypes().add(theBasePackage.getIContentElement());
		testCaseEClass.getESuperTypes().add(theBasePackage.getIContainer());
		testCaseEClass.getESuperTypes().add(theBasePackage.getIPositionable());
		parameterAssignmentEClass.getESuperTypes().add(theBasePackage.getIContentElement());
		testProcedureEClass.getESuperTypes().add(theBasePackage.getIContainer());
		testProcedureEClass.getESuperTypes().add(theBasePackage.getIExternal());
		testStepEClass.getESuperTypes().add(theBasePackage.getIContentElement());
		testStepEClass.getESuperTypes().add(theBasePackage.getIPositionable());

		// Initialize classes, features, and operations; add parameters
		initEClass(testSpecificationEClass, TestSpecification.class, "TestSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testParameterEClass, TestParameter.class, "TestParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestParameter_Type(), this.getParameterType(), "type", null, 0, 1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestParameter_Assignments(), this.getParameterAssignment(), this.getParameterAssignment_Parameter(), "assignments", null, 0, -1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCase_Consistent(), ecorePackage.getEBoolean(), "consistent", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterAssignmentEClass, ParameterAssignment.class, "ParameterAssignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterAssignment_Parameter(), this.getTestParameter(), this.getTestParameter_Assignments(), "parameter", null, 0, 1, ParameterAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterAssignment_Value(), ecorePackage.getEString(), "value", null, 0, 1, ParameterAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterAssignment_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, ParameterAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testProcedureEClass, TestProcedure.class, "TestProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestProcedure_IsRegressionTest(), ecorePackage.getEBoolean(), "isRegressionTest", null, 0, 1, TestProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testStepEClass, TestStep.class, "TestStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestStep_ExpectedOutcome(), ecorePackage.getEString(), "expectedOutcome", null, 0, 1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestStep_ReferencedTestParameters(), this.getTestParameter(), null, "referencedTestParameters", null, 0, -1, TestStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(parameterTypeEEnum, ParameterType.class, "ParameterType");
		addEEnumLiteral(parameterTypeEEnum, ParameterType.INPUT);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.OUTPUT);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://specmate.com/form_meta
		createForm_metaAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://specmate.com/form_meta</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createForm_metaAnnotations() {
		String source = "http://specmate.com/form_meta";
		addAnnotation
		  (getTestProcedure_IsRegressionTest(),
		   source,
		   new String[] {
			   "shortDesc", "Regression Test",
			   "type", "checkbox",
			   "position", "3",
			   "longDesc", ""
		   });
	}

} //TestspecificationPackageImpl
