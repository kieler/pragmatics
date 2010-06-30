/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.kiml.layout.klayoutdata.impl;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KLayoutDataPackageImpl extends EPackageImpl implements KLayoutDataPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kLayoutDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kShapeLayoutEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kEdgeLayoutEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kPointEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kStringOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kIntOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kBooleanOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kFloatOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kObjectOptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass kInsetsEClass = null;

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
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private KLayoutDataPackageImpl() {
        super(eNS_URI, KLayoutDataFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link KLayoutDataPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static KLayoutDataPackage init() {
        if (isInited) return (KLayoutDataPackage)EPackage.Registry.INSTANCE.getEPackage(KLayoutDataPackage.eNS_URI);

        // Obtain or create and register package
        KLayoutDataPackageImpl theKLayoutDataPackage = (KLayoutDataPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KLayoutDataPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KLayoutDataPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        KGraphPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theKLayoutDataPackage.createPackageContents();

        // Initialize created meta-data
        theKLayoutDataPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theKLayoutDataPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(KLayoutDataPackage.eNS_URI, theKLayoutDataPackage);
        return theKLayoutDataPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKLayoutData() {
        return kLayoutDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKLayoutData_Options() {
        return (EReference)kLayoutDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKShapeLayout() {
        return kShapeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKShapeLayout_Xpos() {
        return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKShapeLayout_Ypos() {
        return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKShapeLayout_Width() {
        return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKShapeLayout_Height() {
        return (EAttribute)kShapeLayoutEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKEdgeLayout() {
        return kEdgeLayoutEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKEdgeLayout_BendPoints() {
        return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKEdgeLayout_SourcePoint() {
        return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKEdgeLayout_TargetPoint() {
        return (EReference)kEdgeLayoutEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKPoint() {
        return kPointEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKPoint_X() {
        return (EAttribute)kPointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKPoint_Y() {
        return (EAttribute)kPointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKOption() {
        return kOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKOption_Key() {
        return (EAttribute)kOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKOption_Default() {
        return (EAttribute)kOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKStringOption() {
        return kStringOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKStringOption_Value() {
        return (EAttribute)kStringOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKIntOption() {
        return kIntOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKIntOption_Value() {
        return (EAttribute)kIntOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKBooleanOption() {
        return kBooleanOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKBooleanOption_Value() {
        return (EAttribute)kBooleanOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKFloatOption() {
        return kFloatOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKFloatOption_Value() {
        return (EAttribute)kFloatOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKObjectOption() {
        return kObjectOptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKObjectOption_Value() {
        return (EReference)kObjectOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKInsets() {
        return kInsetsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKInsets_Top() {
        return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKInsets_Bottom() {
        return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKInsets_Left() {
        return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKInsets_Right() {
        return (EAttribute)kInsetsEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLayoutDataFactory getKLayoutDataFactory() {
        return (KLayoutDataFactory)getEFactoryInstance();
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
        kLayoutDataEClass = createEClass(KLAYOUT_DATA);
        createEReference(kLayoutDataEClass, KLAYOUT_DATA__OPTIONS);

        kShapeLayoutEClass = createEClass(KSHAPE_LAYOUT);
        createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__XPOS);
        createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__YPOS);
        createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__WIDTH);
        createEAttribute(kShapeLayoutEClass, KSHAPE_LAYOUT__HEIGHT);

        kEdgeLayoutEClass = createEClass(KEDGE_LAYOUT);
        createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__BEND_POINTS);
        createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__SOURCE_POINT);
        createEReference(kEdgeLayoutEClass, KEDGE_LAYOUT__TARGET_POINT);

        kPointEClass = createEClass(KPOINT);
        createEAttribute(kPointEClass, KPOINT__X);
        createEAttribute(kPointEClass, KPOINT__Y);

        kOptionEClass = createEClass(KOPTION);
        createEAttribute(kOptionEClass, KOPTION__KEY);
        createEAttribute(kOptionEClass, KOPTION__DEFAULT);

        kStringOptionEClass = createEClass(KSTRING_OPTION);
        createEAttribute(kStringOptionEClass, KSTRING_OPTION__VALUE);

        kIntOptionEClass = createEClass(KINT_OPTION);
        createEAttribute(kIntOptionEClass, KINT_OPTION__VALUE);

        kBooleanOptionEClass = createEClass(KBOOLEAN_OPTION);
        createEAttribute(kBooleanOptionEClass, KBOOLEAN_OPTION__VALUE);

        kFloatOptionEClass = createEClass(KFLOAT_OPTION);
        createEAttribute(kFloatOptionEClass, KFLOAT_OPTION__VALUE);

        kObjectOptionEClass = createEClass(KOBJECT_OPTION);
        createEReference(kObjectOptionEClass, KOBJECT_OPTION__VALUE);

        kInsetsEClass = createEClass(KINSETS);
        createEAttribute(kInsetsEClass, KINSETS__TOP);
        createEAttribute(kInsetsEClass, KINSETS__BOTTOM);
        createEAttribute(kInsetsEClass, KINSETS__LEFT);
        createEAttribute(kInsetsEClass, KINSETS__RIGHT);
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
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        kLayoutDataEClass.getESuperTypes().add(theKGraphPackage.getKGraphData());
        kShapeLayoutEClass.getESuperTypes().add(this.getKLayoutData());
        kEdgeLayoutEClass.getESuperTypes().add(this.getKLayoutData());
        kStringOptionEClass.getESuperTypes().add(this.getKOption());
        kIntOptionEClass.getESuperTypes().add(this.getKOption());
        kBooleanOptionEClass.getESuperTypes().add(this.getKOption());
        kFloatOptionEClass.getESuperTypes().add(this.getKOption());
        kObjectOptionEClass.getESuperTypes().add(this.getKOption());

        // Initialize classes and features; add operations and parameters
        initEClass(kLayoutDataEClass, KLayoutData.class, "KLayoutData", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKLayoutData_Options(), this.getKOption(), null, "options", null, 0, -1, KLayoutData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(kLayoutDataEClass, this.getKOption(), "getOption", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(kShapeLayoutEClass, KShapeLayout.class, "KShapeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKShapeLayout_Xpos(), ecorePackage.getEFloat(), "xpos", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKShapeLayout_Ypos(), ecorePackage.getEFloat(), "ypos", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKShapeLayout_Width(), ecorePackage.getEFloat(), "width", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKShapeLayout_Height(), ecorePackage.getEFloat(), "height", "0.0f", 0, 1, KShapeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kEdgeLayoutEClass, KEdgeLayout.class, "KEdgeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKEdgeLayout_BendPoints(), this.getKPoint(), null, "bendPoints", null, 0, -1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKEdgeLayout_SourcePoint(), this.getKPoint(), null, "sourcePoint", null, 1, 1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getKEdgeLayout_TargetPoint(), this.getKPoint(), null, "targetPoint", null, 1, 1, KEdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kPointEClass, KPoint.class, "KPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKPoint_X(), ecorePackage.getEFloat(), "x", "0.0f", 0, 1, KPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKPoint_Y(), ecorePackage.getEFloat(), "y", "0.0f", 0, 1, KPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kOptionEClass, KOption.class, "KOption", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKOption_Key(), ecorePackage.getEString(), "key", null, 0, 1, KOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKOption_Default(), ecorePackage.getEBoolean(), "default", null, 0, 1, KOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kStringOptionEClass, KStringOption.class, "KStringOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKStringOption_Value(), ecorePackage.getEString(), "value", "", 0, 1, KStringOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kIntOptionEClass, KIntOption.class, "KIntOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKIntOption_Value(), ecorePackage.getEInt(), "value", null, 0, 1, KIntOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kBooleanOptionEClass, KBooleanOption.class, "KBooleanOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKBooleanOption_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, KBooleanOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kFloatOptionEClass, KFloatOption.class, "KFloatOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKFloatOption_Value(), ecorePackage.getEFloat(), "value", null, 0, 1, KFloatOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kObjectOptionEClass, KObjectOption.class, "KObjectOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKObjectOption_Value(), theEcorePackage.getEObject(), null, "value", null, 1, 1, KObjectOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(kInsetsEClass, KInsets.class, "KInsets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKInsets_Top(), ecorePackage.getEFloat(), "top", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKInsets_Bottom(), ecorePackage.getEFloat(), "bottom", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKInsets_Left(), ecorePackage.getEFloat(), "left", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKInsets_Right(), ecorePackage.getEFloat(), "right", null, 0, 1, KInsets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //KLayoutDataPackageImpl
