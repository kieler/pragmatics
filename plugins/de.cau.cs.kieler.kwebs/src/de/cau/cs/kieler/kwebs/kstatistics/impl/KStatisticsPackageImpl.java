/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics.impl;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import de.cau.cs.kieler.kwebs.kstatistics.KStatistics;
import de.cau.cs.kieler.kwebs.kstatistics.KStatisticsFactory;
import de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage;

import de.cau.cs.kieler.kwebs.kstatistics.LayoutType;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KStatisticsPackageImpl extends EPackageImpl implements KStatisticsPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kStatisticsEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum layoutTypeEEnum = null;

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
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private KStatisticsPackageImpl() {
        super(eNS_URI, KStatisticsFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link KStatisticsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static KStatisticsPackage init() {
        if (isInited) return (KStatisticsPackage)EPackage.Registry.INSTANCE.getEPackage(KStatisticsPackage.eNS_URI);

        // Obtain or create and register package
        KStatisticsPackageImpl theKStatisticsPackage = (KStatisticsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KStatisticsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KStatisticsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        KGraphPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theKStatisticsPackage.createPackageContents();

        // Initialize created meta-data
        theKStatisticsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theKStatisticsPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(KStatisticsPackage.eNS_URI, theKStatisticsPackage);
        return theKStatisticsPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKStatistics() {
        return kStatisticsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Type() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Bytes() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Compression() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Edges() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Nodes() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Ports() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_Labels() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_TimeTotal() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_TimeNetwork() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_TimeLayout() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_TimeLocalSupplemental() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStatistics_TimeRemoteSupplemental() {
        return (EAttribute)kStatisticsEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getLayoutType() {
        return layoutTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStatisticsFactory getKStatisticsFactory() {
        return (KStatisticsFactory)getEFactoryInstance();
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
        kStatisticsEClass = createEClass(KSTATISTICS);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TYPE);
        createEAttribute(kStatisticsEClass, KSTATISTICS__BYTES);
        createEAttribute(kStatisticsEClass, KSTATISTICS__COMPRESSION);
        createEAttribute(kStatisticsEClass, KSTATISTICS__EDGES);
        createEAttribute(kStatisticsEClass, KSTATISTICS__NODES);
        createEAttribute(kStatisticsEClass, KSTATISTICS__PORTS);
        createEAttribute(kStatisticsEClass, KSTATISTICS__LABELS);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TIME_TOTAL);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TIME_NETWORK);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TIME_LAYOUT);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TIME_LOCAL_SUPPLEMENTAL);
        createEAttribute(kStatisticsEClass, KSTATISTICS__TIME_REMOTE_SUPPLEMENTAL);

        // Create enums
        layoutTypeEEnum = createEEnum(LAYOUT_TYPE);
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
        KGraphPackage theKGraphPackage = (KGraphPackage)EPackage.Registry.INSTANCE.getEPackage(KGraphPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        kStatisticsEClass.getESuperTypes().add(theKGraphPackage.getKGraphData());

        // Initialize classes and features; add operations and parameters
        initEClass(kStatisticsEClass, KStatistics.class, "KStatistics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKStatistics_Type(), this.getLayoutType(), "type", "", 1, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Bytes(), ecorePackage.getEInt(), "bytes", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Compression(), ecorePackage.getEBoolean(), "compression", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Edges(), ecorePackage.getEInt(), "edges", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Nodes(), ecorePackage.getEInt(), "nodes", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Ports(), ecorePackage.getEInt(), "ports", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_Labels(), ecorePackage.getEInt(), "labels", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_TimeTotal(), ecorePackage.getEDouble(), "timeTotal", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_TimeNetwork(), ecorePackage.getEDouble(), "timeNetwork", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_TimeLayout(), ecorePackage.getEDouble(), "timeLayout", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_TimeLocalSupplemental(), ecorePackage.getEDouble(), "timeLocalSupplemental", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKStatistics_TimeRemoteSupplemental(), ecorePackage.getEDouble(), "timeRemoteSupplemental", null, 0, 1, KStatistics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEInt(), "getElementCount", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getTotalSpeed", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getLayoutSpeed", 1, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getNetworkPart", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getLayoutPart", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getSupplementalPart", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getLocalSupplementalPart", 0, 1, IS_UNIQUE, IS_ORDERED);

        addEOperation(kStatisticsEClass, ecorePackage.getEDouble(), "getRemoteSupplementalPart", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(layoutTypeEEnum, LayoutType.class, "LayoutType");
        addEEnumLiteral(layoutTypeEEnum, LayoutType.LOCAL_LAYOUT);
        addEEnumLiteral(layoutTypeEEnum, LayoutType.REMOTE_LAYOUT);

        // Create resource
        createResource(eNS_URI);
    }

} //KStatisticsPackageImpl
