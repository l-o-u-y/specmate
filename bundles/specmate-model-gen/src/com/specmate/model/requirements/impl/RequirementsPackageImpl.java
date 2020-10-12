/**
 */
package com.specmate.model.requirements.impl;

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
import com.specmate.model.requirements.CEGConnection;
import com.specmate.model.requirements.CEGModel;
import com.specmate.model.requirements.CEGNode;
import com.specmate.model.requirements.NodeType;
import com.specmate.model.requirements.RGChunk;
import com.specmate.model.requirements.RGConnection;
import com.specmate.model.requirements.RGConnectionType;
import com.specmate.model.requirements.RGModel;
import com.specmate.model.requirements.RGNode;
import com.specmate.model.requirements.RGObject;
import com.specmate.model.requirements.Requirement;
import com.specmate.model.requirements.RequirementsFactory;
import com.specmate.model.requirements.RequirementsPackage;
import com.specmate.model.testspecification.TestspecificationPackage;
import com.specmate.model.testspecification.impl.TestspecificationPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementsPackageImpl extends EPackageImpl implements RequirementsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cegModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cegNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cegConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rgModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rgObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rgChunkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rgNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rgConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum nodeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum rgConnectionTypeEEnum = null;

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
	 * @see com.specmate.model.requirements.RequirementsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RequirementsPackageImpl() {
		super(eNS_URI, RequirementsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RequirementsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RequirementsPackage init() {
		if (isInited) return (RequirementsPackage)EPackage.Registry.INSTANCE.getEPackage(RequirementsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredRequirementsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		RequirementsPackageImpl theRequirementsPackage = registeredRequirementsPackage instanceof RequirementsPackageImpl ? (RequirementsPackageImpl)registeredRequirementsPackage : new RequirementsPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);
		BasePackageImpl theBasePackage = (BasePackageImpl)(registeredPackage instanceof BasePackageImpl ? registeredPackage : BasePackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TestspecificationPackage.eNS_URI);
		TestspecificationPackageImpl theTestspecificationPackage = (TestspecificationPackageImpl)(registeredPackage instanceof TestspecificationPackageImpl ? registeredPackage : TestspecificationPackage.eINSTANCE);
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
		theRequirementsPackage.createPackageContents();
		theBasePackage.createPackageContents();
		theTestspecificationPackage.createPackageContents();
		theProcessesPackage.createPackageContents();
		theHistoryPackage.createPackageContents();
		theAdministrationPackage.createPackageContents();
		theBatchPackage.createPackageContents();
		theExportPackage.createPackageContents();

		// Initialize created meta-data
		theRequirementsPackage.initializePackageContents();
		theBasePackage.initializePackageContents();
		theTestspecificationPackage.initializePackageContents();
		theProcessesPackage.initializePackageContents();
		theHistoryPackage.initializePackageContents();
		theAdministrationPackage.initializePackageContents();
		theBatchPackage.initializePackageContents();
		theExportPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRequirementsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RequirementsPackage.eNS_URI, theRequirementsPackage);
		return theRequirementsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_NumberOfTests() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_Tac() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_ImplementingUnit() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_ImplementingBOTeam() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_ImplementingITTeam() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_PlannedRelease() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_Status() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_IsRegressionRequirement() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_Platform() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCEGModel() {
		return cegModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCEGModel_ModelRequirements() {
		return (EAttribute)cegModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCEGNode() {
		return cegNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCEGNode_Type() {
		return (EAttribute)cegNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCEGNode_Variable() {
		return (EAttribute)cegNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCEGNode_Condition() {
		return (EAttribute)cegNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCEGConnection() {
		return cegConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCEGConnection_Negate() {
		return (EAttribute)cegConnectionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRGModel() {
		return rgModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGModel_ModelRequirements() {
		return (EAttribute)rgModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGModel_ModelMapping() {
		return (EReference)rgModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGModel_Chunks() {
		return (EReference)rgModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRGObject() {
		return rgObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGObject_OriginalText() {
		return (EAttribute)rgObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGObject_ProcessedText() {
		return (EAttribute)rgObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGObject_Chunk() {
		return (EReference)rgObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRGChunk() {
		return rgChunkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGChunk_Text() {
		return (EAttribute)rgChunkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGChunk_Node() {
		return (EReference)rgChunkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGChunk_IncomingChunks() {
		return (EReference)rgChunkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGChunk_OutgoingChunks() {
		return (EReference)rgChunkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGChunk_Objects() {
		return (EReference)rgChunkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRGNode() {
		return rgNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGNode_Type() {
		return (EAttribute)rgNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGNode_Component() {
		return (EAttribute)rgNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRGNode_Chunks() {
		return (EReference)rgNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGNode_Temporary() {
		return (EAttribute)rgNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRGConnection() {
		return rgConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGConnection_Negate() {
		return (EAttribute)rgConnectionEClass.getEStructuralFeatures().get(0);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGConnection_Type() {
		return (EAttribute)rgConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRGConnection_Label() {
		return (EAttribute)rgConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNodeType() {
		return nodeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getRGConnectionType() {
		return rgConnectionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequirementsFactory getRequirementsFactory() {
		return (RequirementsFactory)getEFactoryInstance();
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
		requirementEClass = createEClass(REQUIREMENT);
		createEAttribute(requirementEClass, REQUIREMENT__NUMBER_OF_TESTS);
		createEAttribute(requirementEClass, REQUIREMENT__TAC);
		createEAttribute(requirementEClass, REQUIREMENT__IMPLEMENTING_UNIT);
		createEAttribute(requirementEClass, REQUIREMENT__IMPLEMENTING_BO_TEAM);
		createEAttribute(requirementEClass, REQUIREMENT__IMPLEMENTING_IT_TEAM);
		createEAttribute(requirementEClass, REQUIREMENT__PLANNED_RELEASE);
		createEAttribute(requirementEClass, REQUIREMENT__STATUS);
		createEAttribute(requirementEClass, REQUIREMENT__IS_REGRESSION_REQUIREMENT);
		createEAttribute(requirementEClass, REQUIREMENT__PLATFORM);

		cegModelEClass = createEClass(CEG_MODEL);
		createEAttribute(cegModelEClass, CEG_MODEL__MODEL_REQUIREMENTS);

		rgModelEClass = createEClass(RG_MODEL);
		createEAttribute(rgModelEClass, RG_MODEL__MODEL_REQUIREMENTS);
		createEReference(rgModelEClass, RG_MODEL__MODEL_MAPPING);
		createEReference(rgModelEClass, RG_MODEL__CHUNKS);

		rgObjectEClass = createEClass(RG_OBJECT);
		createEAttribute(rgObjectEClass, RG_OBJECT__ORIGINAL_TEXT);
		createEAttribute(rgObjectEClass, RG_OBJECT__PROCESSED_TEXT);
		createEReference(rgObjectEClass, RG_OBJECT__CHUNK);

		rgChunkEClass = createEClass(RG_CHUNK);
		createEAttribute(rgChunkEClass, RG_CHUNK__TEXT);
		createEReference(rgChunkEClass, RG_CHUNK__NODE);
		createEReference(rgChunkEClass, RG_CHUNK__INCOMING_CHUNKS);
		createEReference(rgChunkEClass, RG_CHUNK__OUTGOING_CHUNKS);
		createEReference(rgChunkEClass, RG_CHUNK__OBJECTS);

		rgNodeEClass = createEClass(RG_NODE);
		createEAttribute(rgNodeEClass, RG_NODE__TYPE);
		createEAttribute(rgNodeEClass, RG_NODE__COMPONENT);
		createEReference(rgNodeEClass, RG_NODE__CHUNKS);
		createEAttribute(rgNodeEClass, RG_NODE__TEMPORARY);

		cegNodeEClass = createEClass(CEG_NODE);
		createEAttribute(cegNodeEClass, CEG_NODE__TYPE);
		createEAttribute(cegNodeEClass, CEG_NODE__VARIABLE);
		createEAttribute(cegNodeEClass, CEG_NODE__CONDITION);

		cegConnectionEClass = createEClass(CEG_CONNECTION);
		createEAttribute(cegConnectionEClass, CEG_CONNECTION__NEGATE);

		rgConnectionEClass = createEClass(RG_CONNECTION);
		createEAttribute(rgConnectionEClass, RG_CONNECTION__NEGATE);
		createEAttribute(rgConnectionEClass, RG_CONNECTION__TYPE);
		createEAttribute(rgConnectionEClass, RG_CONNECTION__LABEL);

		// Create enums
		nodeTypeEEnum = createEEnum(NODE_TYPE);
		rgConnectionTypeEEnum = createEEnum(RG_CONNECTION_TYPE);
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
		requirementEClass.getESuperTypes().add(theBasePackage.getISpecmateModelObject());
		requirementEClass.getESuperTypes().add(theBasePackage.getIExternal());
		cegModelEClass.getESuperTypes().add(theBasePackage.getISpecmateModelObject());
		rgModelEClass.getESuperTypes().add(theBasePackage.getISpecmateModelObject());
		rgObjectEClass.getESuperTypes().add(theBasePackage.getIID());
		rgObjectEClass.getESuperTypes().add(theBasePackage.getIDescribed());
		rgObjectEClass.getESuperTypes().add(theBasePackage.getIRecycled());
		rgChunkEClass.getESuperTypes().add(theBasePackage.getIID());
		rgChunkEClass.getESuperTypes().add(theBasePackage.getIDescribed());
		rgChunkEClass.getESuperTypes().add(theBasePackage.getIRecycled());
		rgNodeEClass.getESuperTypes().add(theBasePackage.getIModelNode());
		cegNodeEClass.getESuperTypes().add(theBasePackage.getIModelNode());
		cegConnectionEClass.getESuperTypes().add(theBasePackage.getIModelConnection());
		rgConnectionEClass.getESuperTypes().add(theBasePackage.getIModelConnection());

		// Initialize classes, features, and operations; add parameters
		initEClass(requirementEClass, Requirement.class, "Requirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequirement_NumberOfTests(), ecorePackage.getEInt(), "numberOfTests", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_Tac(), ecorePackage.getEString(), "tac", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_ImplementingUnit(), ecorePackage.getEString(), "implementingUnit", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_ImplementingBOTeam(), ecorePackage.getEString(), "implementingBOTeam", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_ImplementingITTeam(), ecorePackage.getEString(), "implementingITTeam", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_PlannedRelease(), ecorePackage.getEString(), "plannedRelease", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_Status(), ecorePackage.getEString(), "status", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_IsRegressionRequirement(), ecorePackage.getEBoolean(), "isRegressionRequirement", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_Platform(), ecorePackage.getEString(), "platform", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cegModelEClass, CEGModel.class, "CEGModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCEGModel_ModelRequirements(), ecorePackage.getEString(), "modelRequirements", null, 0, 1, CEGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rgModelEClass, RGModel.class, "RGModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRGModel_ModelRequirements(), ecorePackage.getEString(), "modelRequirements", null, 0, 1, RGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGModel_ModelMapping(), this.getRGObject(), null, "modelMapping", null, 0, -1, RGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGModel_Chunks(), this.getRGChunk(), null, "chunks", null, 0, -1, RGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rgObjectEClass, RGObject.class, "RGObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRGObject_OriginalText(), ecorePackage.getEString(), "originalText", null, 0, 1, RGObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRGObject_ProcessedText(), ecorePackage.getEString(), "processedText", null, 0, 1, RGObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGObject_Chunk(), this.getRGChunk(), null, "chunk", null, 0, 1, RGObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rgChunkEClass, RGChunk.class, "RGChunk", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRGChunk_Text(), ecorePackage.getEString(), "text", null, 0, 1, RGChunk.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGChunk_Node(), this.getRGNode(), null, "node", null, 0, 1, RGChunk.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGChunk_IncomingChunks(), this.getRGChunk(), null, "incomingChunks", null, 0, -1, RGChunk.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGChunk_OutgoingChunks(), this.getRGChunk(), null, "outgoingChunks", null, 0, -1, RGChunk.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGChunk_Objects(), this.getRGObject(), null, "objects", null, 0, -1, RGChunk.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rgNodeEClass, RGNode.class, "RGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRGNode_Type(), this.getNodeType(), "type", null, 0, 1, RGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRGNode_Component(), ecorePackage.getEString(), "component", null, 0, 1, RGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRGNode_Chunks(), this.getRGChunk(), null, "chunks", null, 0, -1, RGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRGNode_Temporary(), ecorePackage.getEBoolean(), "temporary", null, 0, 1, RGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cegNodeEClass, CEGNode.class, "CEGNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCEGNode_Type(), this.getNodeType(), "type", null, 0, 1, CEGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCEGNode_Variable(), ecorePackage.getEString(), "variable", null, 0, 1, CEGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCEGNode_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, CEGNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cegConnectionEClass, CEGConnection.class, "CEGConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCEGConnection_Negate(), ecorePackage.getEBoolean(), "negate", null, 0, 1, CEGConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rgConnectionEClass, RGConnection.class, "RGConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRGConnection_Negate(), ecorePackage.getEBoolean(), "negate", null, 0, 1, RGConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRGConnection_Type(), this.getRGConnectionType(), "type", null, 0, 1, RGConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRGConnection_Label(), ecorePackage.getEString(), "label", null, 0, 1, RGConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(nodeTypeEEnum, NodeType.class, "NodeType");
		addEEnumLiteral(nodeTypeEEnum, NodeType.AND);
		addEEnumLiteral(nodeTypeEEnum, NodeType.OR);

		initEEnum(rgConnectionTypeEEnum, RGConnectionType.class, "RGConnectionType");
		addEEnumLiteral(rgConnectionTypeEEnum, RGConnectionType.INHERITANCE);
		addEEnumLiteral(rgConnectionTypeEEnum, RGConnectionType.COMPOSITION);
		addEEnumLiteral(rgConnectionTypeEEnum, RGConnectionType.ACTION);
		addEEnumLiteral(rgConnectionTypeEEnum, RGConnectionType.REPLACE);
		addEEnumLiteral(rgConnectionTypeEEnum, RGConnectionType.REMOVE);

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
		  (getCEGModel_ModelRequirements(),
		   source,
		   new String[] {
			   "shortDesc", "Model Requirements",
			   "longDesc", "",
			   "required", "false",
			   "type", "longText",
			   "rows", "5",
			   "position", "110"
		   });
		addAnnotation
		  (getRGModel_ModelRequirements(),
		   source,
		   new String[] {
			   "shortDesc", "Model Requirements",
			   "longDesc", "",
			   "required", "false",
			   "type", "longText",
			   "rows", "5",
			   "position", "110"
		   });
		addAnnotation
		  (rgNodeEClass,
		   source,
		   new String[] {
			   "disabled1", "name",
			   "disabled2", "description"
		   });
		addAnnotation
		  (getRGNode_Type(),
		   source,
		   new String[] {
			   "shortDesc", "Type",
			   "longDesc", "The type of a node",
			   "required", "true",
			   "type", "singleSelection",
			   "values", "[\"AND\", \"OR\"]",
			   "position", "3"
		   });
		addAnnotation
		  (getRGNode_Component(),
		   source,
		   new String[] {
			   "shortDesc", "Component",
			   "longDesc", "The component of a node",
			   "required", "true",
			   "type", "text",
			   "position", "1",
			   "allowedPattern", "^[^,;|]*$"
		   });
		addAnnotation
		  (cegNodeEClass,
		   source,
		   new String[] {
			   "disabled1", "name",
			   "disabled2", "description"
		   });
		addAnnotation
		  (getCEGNode_Type(),
		   source,
		   new String[] {
			   "shortDesc", "Type",
			   "longDesc", "The type of a node",
			   "required", "true",
			   "type", "singleSelection",
			   "values", "[\"AND\", \"OR\"]",
			   "position", "3"
		   });
		addAnnotation
		  (getCEGNode_Variable(),
		   source,
		   new String[] {
			   "shortDesc", "Variable",
			   "longDesc", "The variable of a node",
			   "required", "true",
			   "type", "text",
			   "position", "1",
			   "allowedPattern", "^[^,;|]*$"
		   });
		addAnnotation
		  (getCEGNode_Condition(),
		   source,
		   new String[] {
			   "shortDesc", "Condition",
			   "longDesc", "The condition the variable has to fulfil",
			   "required", "true",
			   "type", "text",
			   "position", "2"
		   });
		addAnnotation
		  (getCEGConnection_Negate(),
		   source,
		   new String[] {
			   "shortDesc", "Negate",
			   "longDesc", "Negation of this connection",
			   "type", "checkbox",
			   "position", "1"
		   });
		addAnnotation
		  (getRGConnection_Negate(),
		   source,
		   new String[] {
			   "shortDesc", "Negate",
			   "longDesc", "Negation of this connection",
			   "type", "checkbox",
			   "position", "1"
		   });
		addAnnotation
		  (getRGConnection_Type(),
		   source,
		   new String[] {
			   "shortDesc", "Type",
			   "longDesc", "The type of a connection",
			   "required", "true",
			   "type", "singleSelection",
			   "values", "[\"Inheritance\", \"Composition\", \"Action\"]",
			   "position", "2"
		   });
		addAnnotation
		  (getRGConnection_Label(),
		   source,
		   new String[] {
			   "shortDesc", "Label",
			   "longDesc", "The label of a connection",
			   "required", "false",
			   "type", "text",
			   "position", "3"
		   });
	}

} //RequirementsPackageImpl
