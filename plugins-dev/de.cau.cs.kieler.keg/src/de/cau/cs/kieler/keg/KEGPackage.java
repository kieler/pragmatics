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
 * @see de.cau.cs.kieler.keg.KEGFactory
 * @model kind="package"
 * @generated
 */
public interface KEGPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "keg";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KEG";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "keg";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KEGPackage eINSTANCE = de.cau.cs.kieler.keg.impl.KEGPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.impl.NodeImpl
     * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getNode()
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
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__LABELS = KGraphPackage.KNODE__LABELS;

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
     * The feature id for the '<em><b>Node Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__NODE_LABEL = KGraphPackage.KNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Hypernode</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE__HYPERNODE = KGraphPackage.KNODE_FEATURE_COUNT + 1;

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
     * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getEdge()
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
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__LABELS = KGraphPackage.KEDGE__LABELS;

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
     * The feature id for the '<em><b>Head Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__HEAD_LABEL = KGraphPackage.KEDGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Mid Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__MID_LABEL = KGraphPackage.KEDGE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tail Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TAIL_LABEL = KGraphPackage.KEDGE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Directed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__DIRECTED = KGraphPackage.KEDGE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE__TYPE = KGraphPackage.KEDGE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = KGraphPackage.KEDGE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.keg.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.keg.impl.PortImpl
     * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getPort()
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
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__LABELS = KGraphPackage.KPORT__LABELS;

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
     * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getEdgeType()
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
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Node#isHypernode <em>Hypernode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hypernode</em>'.
     * @see de.cau.cs.kieler.keg.Node#isHypernode()
     * @see #getNode()
     * @generated
     */
    EAttribute getNode_Hypernode();

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
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getHeadLabel <em>Head Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Head Label</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getHeadLabel()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_HeadLabel();

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
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#getTailLabel <em>Tail Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tail Label</em>'.
     * @see de.cau.cs.kieler.keg.Edge#getTailLabel()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_TailLabel();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.keg.Edge#isDirected <em>Directed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Directed</em>'.
     * @see de.cau.cs.kieler.keg.Edge#isDirected()
     * @see #getEdge()
     * @generated
     */
    EAttribute getEdge_Directed();

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
    KEGFactory getKEGFactory();

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
         * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getNode()
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
         * The meta object literal for the '<em><b>Hypernode</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NODE__HYPERNODE = eINSTANCE.getNode_Hypernode();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.keg.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.keg.impl.EdgeImpl
         * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Head Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__HEAD_LABEL = eINSTANCE.getEdge_HeadLabel();

        /**
         * The meta object literal for the '<em><b>Mid Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__MID_LABEL = eINSTANCE.getEdge_MidLabel();

        /**
         * The meta object literal for the '<em><b>Tail Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__TAIL_LABEL = eINSTANCE.getEdge_TailLabel();

        /**
         * The meta object literal for the '<em><b>Directed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EDGE__DIRECTED = eINSTANCE.getEdge_Directed();

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
         * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getPort()
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
         * @see de.cau.cs.kieler.keg.impl.KEGPackageImpl#getEdgeType()
         * @generated
         */
        EEnum EDGE_TYPE = eINSTANCE.getEdgeType();

    }

} //KEGPackage
