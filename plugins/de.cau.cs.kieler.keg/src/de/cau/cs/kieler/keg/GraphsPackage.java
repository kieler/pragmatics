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
 *
 * $Id$
 */
package de.cau.cs.kieler.keg;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.keg.GraphsFactory
 * @model kind="package"
 * @generated
 */
public interface GraphsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "graphs";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/graphs";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "graphs";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    GraphsPackage eINSTANCE = de.cau.cs.kieler.keg.impl.GraphsPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.impl.NodeImpl
     * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getNode()
     * @generated
     */
    int NODE = 0;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__DATA = KGraphPackage.KNODE__DATA;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__CHILDREN = KGraphPackage.KNODE__CHILDREN;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__PARENT = KGraphPackage.KNODE__PARENT;

    /**
     * The feature id for the '<em><b>Ports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__PORTS = KGraphPackage.KNODE__PORTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__OUTGOING_EDGES = KGraphPackage.KNODE__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__INCOMING_EDGES = KGraphPackage.KNODE__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__LABEL = KGraphPackage.KNODE__LABEL;

    /**
     * The feature id for the '<em><b>Node Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__NODE_LABEL = KGraphPackage.KNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Is Hypernode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__IS_HYPERNODE = KGraphPackage.KNODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_FEATURE_COUNT = KGraphPackage.KNODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.impl.EdgeImpl <em>Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.impl.EdgeImpl
     * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getEdge()
     * @generated
     */
    int EDGE = 1;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__DATA = KGraphPackage.KEDGE__DATA;

    /**
     * The feature id for the '<em><b>Source</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__SOURCE = KGraphPackage.KEDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TARGET = KGraphPackage.KEDGE__TARGET;

    /**
     * The feature id for the '<em><b>Source Port</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__SOURCE_PORT = KGraphPackage.KEDGE__SOURCE_PORT;

    /**
     * The feature id for the '<em><b>Target Port</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TARGET_PORT = KGraphPackage.KEDGE__TARGET_PORT;

    /**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__LABELS = KGraphPackage.KEDGE__LABELS;

    /**
     * The feature id for the '<em><b>Head Label1</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__HEAD_LABEL1 = KGraphPackage.KEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Head Label2</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__HEAD_LABEL2 = KGraphPackage.KEDGE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Mid Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__MID_LABEL = KGraphPackage.KEDGE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Tail Label1</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TAIL_LABEL1 = KGraphPackage.KEDGE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Tail Label2</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TAIL_LABEL2 = KGraphPackage.KEDGE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Is Directed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__IS_DIRECTED = KGraphPackage.KEDGE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TYPE = KGraphPackage.KEDGE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = KGraphPackage.KEDGE_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.impl.PortImpl
     * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getPort()
     * @generated
     */
    int PORT = 2;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__DATA = KGraphPackage.KPORT__DATA;

    /**
     * The feature id for the '<em><b>Node</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__NODE = KGraphPackage.KPORT__NODE;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__EDGES = KGraphPackage.KPORT__EDGES;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__LABEL = KGraphPackage.KPORT__LABEL;

    /**
     * The feature id for the '<em><b>Port Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__PORT_LABEL = KGraphPackage.KPORT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Port</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_FEATURE_COUNT = KGraphPackage.KPORT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.EdgeType <em>Edge Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.EdgeType
     * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getEdgeType()
     * @generated
     */
    int EDGE_TYPE = 3;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.keg.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see de.cau.cs.kieler.keg.Node
     * @generated
     */
    EClass getNode();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Node#getNodeLabel <em>Node Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Node Label</em>'.
     * @see de.cau.cs.kieler.keg.Node#getNodeLabel()
     * @see #getNode()
     * @generated
     */
    EAttribute getNode_NodeLabel();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Node#isIsHypernode <em>Is Hypernode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Hypernode</em>'.
     * @see de.cau.cs.kieler.keg.Node#isIsHypernode()
     * @see #getNode()
     * @generated
     */
    EAttribute getNode_IsHypernode();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.keg.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge</em>'.
     * @see de.cau.cs.kieler.keg.Edge
     * @generated
     */
    EClass getEdge();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getHeadLabel1 <em>Head Label1</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Head Label1</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getHeadLabel1()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_HeadLabel1();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getHeadLabel2 <em>Head Label2</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Head Label2</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getHeadLabel2()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_HeadLabel2();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getMidLabel <em>Mid Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mid Label</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getMidLabel()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_MidLabel();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getTailLabel1 <em>Tail Label1</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tail Label1</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getTailLabel1()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_TailLabel1();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getTailLabel2 <em>Tail Label2</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tail Label2</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getTailLabel2()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_TailLabel2();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#isIsDirected <em>Is Directed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is Directed</em>'.
     * @see de.cau.cs.kieler.keg.Edge#isIsDirected()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_IsDirected();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getType()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_Type();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.keg.Port <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Port</em>'.
     * @see de.cau.cs.kieler.keg.Port
     * @generated
     */
    EClass getPort();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Port#getPortLabel <em>Port Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Port Label</em>'.
     * @see de.cau.cs.kieler.keg.Port#getPortLabel()
     * @see #getPort()
     * @generated
     */
    EAttribute getPort_PortLabel();

    /**
     * Returns the meta object for enum '{@link de.cau.cs.kieler.keg.EdgeType <em>Edge Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Edge Type</em>'.
     * @see de.cau.cs.kieler.keg.EdgeType
     * @generated
     */
    EEnum getEdgeType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    GraphsFactory getGraphsFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.keg.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.keg.impl.NodeImpl
         * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getNode()
         * @generated
         */
        EClass NODE = eINSTANCE.getNode();

        /**
         * The meta object literal for the '<em><b>Node Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE__NODE_LABEL = eINSTANCE.getNode_NodeLabel();

        /**
         * The meta object literal for the '<em><b>Is Hypernode</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE__IS_HYPERNODE = eINSTANCE.getNode_IsHypernode();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.keg.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.keg.impl.EdgeImpl
         * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Head Label1</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__HEAD_LABEL1 = eINSTANCE.getEdge_HeadLabel1();

        /**
         * The meta object literal for the '<em><b>Head Label2</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__HEAD_LABEL2 = eINSTANCE.getEdge_HeadLabel2();

        /**
         * The meta object literal for the '<em><b>Mid Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__MID_LABEL = eINSTANCE.getEdge_MidLabel();

        /**
         * The meta object literal for the '<em><b>Tail Label1</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__TAIL_LABEL1 = eINSTANCE.getEdge_TailLabel1();

        /**
         * The meta object literal for the '<em><b>Tail Label2</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__TAIL_LABEL2 = eINSTANCE.getEdge_TailLabel2();

        /**
         * The meta object literal for the '<em><b>Is Directed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__IS_DIRECTED = eINSTANCE.getEdge_IsDirected();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__TYPE = eINSTANCE.getEdge_Type();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.keg.impl.PortImpl <em>Port</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.keg.impl.PortImpl
         * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getPort()
         * @generated
         */
        EClass PORT = eINSTANCE.getPort();

        /**
         * The meta object literal for the '<em><b>Port Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PORT__PORT_LABEL = eINSTANCE.getPort_PortLabel();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.keg.EdgeType <em>Edge Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.keg.EdgeType
         * @see de.cau.cs.kieler.keg.impl.GraphsPackageImpl#getEdgeType()
         * @generated
         */
        EEnum EDGE_TYPE = eINSTANCE.getEdgeType();

    }

} //GraphsPackage
