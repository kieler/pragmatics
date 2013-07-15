/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.impl;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.EdgeType;
import de.cau.cs.kieler.keg.KEGFactory;
import de.cau.cs.kieler.keg.KEGPackage;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KEGPackageImpl extends EPackageImpl implements KEGPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass portEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum edgeTypeEEnum = null;

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
     * @see de.cau.cs.kieler.keg.KEGPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private KEGPackageImpl() {
        super(eNS_URI, KEGFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link KEGPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static KEGPackage init() {
        if (isInited) return (KEGPackage)EPackage.Registry.INSTANCE.getEPackage(KEGPackage.eNS_URI);

        // Obtain or create and register package
        KEGPackageImpl theKEGPackage = (KEGPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KEGPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KEGPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        KGraphPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theKEGPackage.createPackageContents();

        // Initialize created meta-data
        theKEGPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theKEGPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(KEGPackage.eNS_URI, theKEGPackage);
        return theKEGPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNode() {
        return nodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNode_NodeLabel() {
        return (EAttribute)nodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNode_Hypernode() {
        return (EAttribute)nodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdge() {
        return edgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdge_HeadLabel() {
        return (EAttribute)edgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdge_MidLabel() {
        return (EAttribute)edgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdge_TailLabel() {
        return (EAttribute)edgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdge_Directed() {
        return (EAttribute)edgeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdge_Type() {
        return (EAttribute)edgeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPort() {
        return portEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPort_PortLabel() {
        return (EAttribute)portEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getEdgeType() {
        return edgeTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KEGFactory getKEGFactory() {
        return (KEGFactory)getEFactoryInstance();
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
        nodeEClass = createEClass(NODE);
        createEAttribute(nodeEClass, NODE__NODE_LABEL);
        createEAttribute(nodeEClass, NODE__HYPERNODE);

        edgeEClass = createEClass(EDGE);
        createEAttribute(edgeEClass, EDGE__HEAD_LABEL);
        createEAttribute(edgeEClass, EDGE__MID_LABEL);
        createEAttribute(edgeEClass, EDGE__TAIL_LABEL);
        createEAttribute(edgeEClass, EDGE__DIRECTED);
        createEAttribute(edgeEClass, EDGE__TYPE);

        portEClass = createEClass(PORT);
        createEAttribute(portEClass, PORT__PORT_LABEL);

        // Create enums
        edgeTypeEEnum = createEEnum(EDGE_TYPE);
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
        nodeEClass.getESuperTypes().add(theKGraphPackage.getKNode());
        edgeEClass.getESuperTypes().add(theKGraphPackage.getKEdge());
        portEClass.getESuperTypes().add(theKGraphPackage.getKPort());

        // Initialize classes and features; add operations and parameters
        initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNode_NodeLabel(), theEcorePackage.getEString(), "nodeLabel", null, 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNode_Hypernode(), theEcorePackage.getEBoolean(), "hypernode", "false", 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEdge_HeadLabel(), theEcorePackage.getEString(), "headLabel", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdge_MidLabel(), theEcorePackage.getEString(), "midLabel", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdge_TailLabel(), theEcorePackage.getEString(), "tailLabel", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdge_Directed(), theEcorePackage.getEBoolean(), "directed", "false", 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdge_Type(), this.getEdgeType(), "type", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(portEClass, Port.class, "Port", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPort_PortLabel(), theEcorePackage.getEString(), "portLabel", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(edgeTypeEEnum, EdgeType.class, "EdgeType");
        addEEnumLiteral(edgeTypeEEnum, EdgeType.NODE2_NODE);
        addEEnumLiteral(edgeTypeEEnum, EdgeType.NODE2_PORT);
        addEEnumLiteral(edgeTypeEEnum, EdgeType.PORT2_NODE);
        addEEnumLiteral(edgeTypeEEnum, EdgeType.PORT2_PORT);

        // Create resource
        createResource(eNS_URI);
    }

} //KEGPackageImpl
