/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.ui.layout.layoutoptions;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutOptionsPackageImpl extends EPackageImpl implements LayoutOptionsPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutOptionStyleEClass = null;

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
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private LayoutOptionsPackageImpl() {
        super(eNS_URI, LayoutOptionsFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link LayoutOptionsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static LayoutOptionsPackage init() {
        if (isInited) return (LayoutOptionsPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutOptionsPackage.eNS_URI);

        // Obtain or create and register package
        LayoutOptionsPackageImpl theLayoutOptionsPackage = (LayoutOptionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LayoutOptionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LayoutOptionsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        KLayoutDataPackage.eINSTANCE.eClass();
        NotationPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theLayoutOptionsPackage.createPackageContents();

        // Initialize created meta-data
        theLayoutOptionsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theLayoutOptionsPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(LayoutOptionsPackage.eNS_URI, theLayoutOptionsPackage);
        return theLayoutOptionsPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutOptionStyle() {
        return layoutOptionStyleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutOptionStyle_Options() {
        return (EReference)layoutOptionStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutOptionsFactory getLayoutOptionsFactory() {
        return (LayoutOptionsFactory)getEFactoryInstance();
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
        layoutOptionStyleEClass = createEClass(LAYOUT_OPTION_STYLE);
        createEReference(layoutOptionStyleEClass, LAYOUT_OPTION_STYLE__OPTIONS);
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
        NotationPackage theNotationPackage = (NotationPackage)EPackage.Registry.INSTANCE.getEPackage(NotationPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        KLayoutDataPackage theKLayoutDataPackage = (KLayoutDataPackage)EPackage.Registry.INSTANCE.getEPackage(KLayoutDataPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        layoutOptionStyleEClass.getESuperTypes().add(theNotationPackage.getStyle());
        layoutOptionStyleEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(layoutOptionStyleEClass, LayoutOptionStyle.class, "LayoutOptionStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLayoutOptionStyle_Options(), theKLayoutDataPackage.getKOption(), null, "options", null, 0, -1, LayoutOptionStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //LayoutOptionsPackageImpl
