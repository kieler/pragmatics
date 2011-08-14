/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataFactory;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServiceDataPackageImpl extends EPackageImpl implements ServiceDataPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass serviceDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutAlgorithmEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass categoryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass knownOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass supportedDiagramEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass remoteEnumEClass = null;

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
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ServiceDataPackageImpl() {
        super(eNS_URI, ServiceDataFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ServiceDataPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ServiceDataPackage init() {
        if (isInited) return (ServiceDataPackage)EPackage.Registry.INSTANCE.getEPackage(ServiceDataPackage.eNS_URI);

        // Obtain or create and register package
        ServiceDataPackageImpl theServiceDataPackage = (ServiceDataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ServiceDataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ServiceDataPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theServiceDataPackage.createPackageContents();

        // Initialize created meta-data
        theServiceDataPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theServiceDataPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ServiceDataPackage.eNS_URI, theServiceDataPackage);
        return theServiceDataPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getServiceData() {
        return serviceDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceData_LayoutAlgorithms() {
        return (EReference)serviceDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceData_LayoutTypes() {
        return (EReference)serviceDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceData_LayoutOptions() {
        return (EReference)serviceDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceData_Categories() {
        return (EReference)serviceDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getServiceData_Version() {
        return (EAttribute)serviceDataEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutAlgorithm() {
        return layoutAlgorithmEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutAlgorithm_Id() {
        return (EAttribute)layoutAlgorithmEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutAlgorithm_Name() {
        return (EAttribute)layoutAlgorithmEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutAlgorithm_Description() {
        return (EAttribute)layoutAlgorithmEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutAlgorithm_KnownOptions() {
        return (EReference)layoutAlgorithmEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutAlgorithm_SupportedDiagrams() {
        return (EReference)layoutAlgorithmEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutAlgorithm_Category() {
        return (EReference)layoutAlgorithmEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutAlgorithm_Type() {
        return (EReference)layoutAlgorithmEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutAlgorithm_Version() {
        return (EAttribute)layoutAlgorithmEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutAlgorithm_PreviewImagePath() {
        return (EAttribute)layoutAlgorithmEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutType() {
        return layoutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutType_Id() {
        return (EAttribute)layoutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutType_Name() {
        return (EAttribute)layoutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutType_Description() {
        return (EAttribute)layoutTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutOption() {
        return layoutOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Id() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Type() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Name() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Description() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_AppliesTo() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Default() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Advanced() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutOption_RemoteEnum() {
        return (EReference)layoutOptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLayoutOption_Implementation() {
        return (EAttribute)layoutOptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getCategory() {
        return categoryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCategory_Id() {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCategory_Name() {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKnownOption() {
        return knownOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKnownOption_Default() {
        return (EAttribute)knownOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKnownOption_Option() {
        return (EReference)knownOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSupportedDiagram() {
        return supportedDiagramEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSupportedDiagram_Type() {
        return (EAttribute)supportedDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSupportedDiagram_Priority() {
        return (EAttribute)supportedDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRemoteEnum() {
        return remoteEnumEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getRemoteEnum_Values() {
        return (EAttribute)remoteEnumEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataFactory getServiceDataFactory() {
        return (ServiceDataFactory)getEFactoryInstance();
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
        serviceDataEClass = createEClass(SERVICE_DATA);
        createEReference(serviceDataEClass, SERVICE_DATA__LAYOUT_ALGORITHMS);
        createEReference(serviceDataEClass, SERVICE_DATA__LAYOUT_TYPES);
        createEReference(serviceDataEClass, SERVICE_DATA__LAYOUT_OPTIONS);
        createEReference(serviceDataEClass, SERVICE_DATA__CATEGORIES);
        createEAttribute(serviceDataEClass, SERVICE_DATA__VERSION);

        layoutAlgorithmEClass = createEClass(LAYOUT_ALGORITHM);
        createEAttribute(layoutAlgorithmEClass, LAYOUT_ALGORITHM__ID);
        createEAttribute(layoutAlgorithmEClass, LAYOUT_ALGORITHM__NAME);
        createEAttribute(layoutAlgorithmEClass, LAYOUT_ALGORITHM__DESCRIPTION);
        createEReference(layoutAlgorithmEClass, LAYOUT_ALGORITHM__KNOWN_OPTIONS);
        createEReference(layoutAlgorithmEClass, LAYOUT_ALGORITHM__SUPPORTED_DIAGRAMS);
        createEReference(layoutAlgorithmEClass, LAYOUT_ALGORITHM__CATEGORY);
        createEReference(layoutAlgorithmEClass, LAYOUT_ALGORITHM__TYPE);
        createEAttribute(layoutAlgorithmEClass, LAYOUT_ALGORITHM__VERSION);
        createEAttribute(layoutAlgorithmEClass, LAYOUT_ALGORITHM__PREVIEW_IMAGE_PATH);

        layoutTypeEClass = createEClass(LAYOUT_TYPE);
        createEAttribute(layoutTypeEClass, LAYOUT_TYPE__ID);
        createEAttribute(layoutTypeEClass, LAYOUT_TYPE__NAME);
        createEAttribute(layoutTypeEClass, LAYOUT_TYPE__DESCRIPTION);

        layoutOptionEClass = createEClass(LAYOUT_OPTION);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__ID);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__TYPE);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__NAME);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__DESCRIPTION);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__APPLIES_TO);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__DEFAULT);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__ADVANCED);
        createEReference(layoutOptionEClass, LAYOUT_OPTION__REMOTE_ENUM);
        createEAttribute(layoutOptionEClass, LAYOUT_OPTION__IMPLEMENTATION);

        categoryEClass = createEClass(CATEGORY);
        createEAttribute(categoryEClass, CATEGORY__ID);
        createEAttribute(categoryEClass, CATEGORY__NAME);

        knownOptionEClass = createEClass(KNOWN_OPTION);
        createEAttribute(knownOptionEClass, KNOWN_OPTION__DEFAULT);
        createEReference(knownOptionEClass, KNOWN_OPTION__OPTION);

        supportedDiagramEClass = createEClass(SUPPORTED_DIAGRAM);
        createEAttribute(supportedDiagramEClass, SUPPORTED_DIAGRAM__TYPE);
        createEAttribute(supportedDiagramEClass, SUPPORTED_DIAGRAM__PRIORITY);

        remoteEnumEClass = createEClass(REMOTE_ENUM);
        createEAttribute(remoteEnumEClass, REMOTE_ENUM__VALUES);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(serviceDataEClass, ServiceData.class, "ServiceData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getServiceData_LayoutAlgorithms(), this.getLayoutAlgorithm(), null, "layoutAlgorithms", null, 0, -1, ServiceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getServiceData_LayoutTypes(), this.getLayoutType(), null, "layoutTypes", null, 0, -1, ServiceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getServiceData_LayoutOptions(), this.getLayoutOption(), null, "layoutOptions", null, 0, -1, ServiceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getServiceData_Categories(), this.getCategory(), null, "categories", null, 0, -1, ServiceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getServiceData_Version(), ecorePackage.getEString(), "version", null, 0, 1, ServiceData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutAlgorithmEClass, LayoutAlgorithm.class, "LayoutAlgorithm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayoutAlgorithm_Id(), ecorePackage.getEString(), "id", null, 1, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutAlgorithm_Name(), ecorePackage.getEString(), "name", null, 1, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutAlgorithm_Description(), ecorePackage.getEString(), "description", null, 0, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutAlgorithm_KnownOptions(), this.getKnownOption(), null, "knownOptions", null, 0, -1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutAlgorithm_SupportedDiagrams(), this.getSupportedDiagram(), null, "supportedDiagrams", null, 0, -1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutAlgorithm_Category(), this.getCategory(), null, "category", null, 0, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutAlgorithm_Type(), this.getLayoutType(), null, "type", null, 0, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutAlgorithm_Version(), ecorePackage.getEString(), "version", null, 0, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutAlgorithm_PreviewImagePath(), ecorePackage.getEString(), "previewImagePath", null, 0, 1, LayoutAlgorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutTypeEClass, LayoutType.class, "LayoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayoutType_Id(), ecorePackage.getEString(), "id", null, 1, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutType_Name(), ecorePackage.getEString(), "name", null, 1, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutType_Description(), ecorePackage.getEString(), "description", null, 0, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutOptionEClass, LayoutOption.class, "LayoutOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayoutOption_Id(), ecorePackage.getEString(), "id", null, 1, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Type(), ecorePackage.getEString(), "type", null, 1, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Name(), ecorePackage.getEString(), "name", null, 1, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Description(), ecorePackage.getEString(), "description", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_AppliesTo(), ecorePackage.getEString(), "appliesTo", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Default(), ecorePackage.getEString(), "default", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Advanced(), ecorePackage.getEBoolean(), "advanced", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutOption_RemoteEnum(), this.getRemoteEnum(), null, "remoteEnum", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayoutOption_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, LayoutOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCategory_Id(), ecorePackage.getEString(), "id", null, 1, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCategory_Name(), ecorePackage.getEString(), "name", null, 1, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(knownOptionEClass, KnownOption.class, "KnownOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKnownOption_Default(), ecorePackage.getEString(), "default", null, 0, 1, KnownOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKnownOption_Option(), this.getLayoutOption(), null, "option", null, 1, 1, KnownOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(supportedDiagramEClass, SupportedDiagram.class, "SupportedDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSupportedDiagram_Type(), ecorePackage.getEString(), "type", null, 1, 1, SupportedDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSupportedDiagram_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, SupportedDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(remoteEnumEClass, RemoteEnum.class, "RemoteEnum", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRemoteEnum_Values(), ecorePackage.getEString(), "values", null, 0, -1, RemoteEnum.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //ServiceDataPackageImpl
