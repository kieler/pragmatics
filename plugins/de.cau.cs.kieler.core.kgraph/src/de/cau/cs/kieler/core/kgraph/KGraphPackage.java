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
package de.cau.cs.kieler.core.kgraph;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see de.cau.cs.kieler.core.kgraph.KGraphFactory
 * @model kind="package"
 * @generated
 */
public interface KGraphPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "kgraph";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KGraph";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "kgraph";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KGraphPackage eINSTANCE = de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.impl.KGraphElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphElementImpl
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKGraphElement()
     * @generated
     */
    int KGRAPH_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRAPH_ELEMENT__EANNOTATIONS = EcorePackage.EMODEL_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRAPH_ELEMENT__DATA = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRAPH_ELEMENT_FEATURE_COUNT = EcorePackage.EMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.KGraphData <em>Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.KGraphData
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKGraphData()
     * @generated
     */
    int KGRAPH_DATA = 1;

    /**
     * The number of structural features of the '<em>Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KGRAPH_DATA_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.impl.KNodeImpl <em>KNode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.impl.KNodeImpl
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKNode()
     * @generated
     */
    int KNODE = 2;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__EANNOTATIONS = KGRAPH_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__DATA = KGRAPH_ELEMENT__DATA;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__CHILDREN = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__PARENT = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Ports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__PORTS = KGRAPH_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__OUTGOING_EDGES = KGRAPH_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__INCOMING_EDGES = KGRAPH_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE__LABEL = KGRAPH_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>KNode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KNODE_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl <em>KEdge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKEdge()
     * @generated
     */
    int KEDGE = 3;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__EANNOTATIONS = KGRAPH_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__DATA = KGRAPH_ELEMENT__DATA;

    /**
     * The feature id for the '<em><b>Source</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__SOURCE = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__TARGET = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Source Port</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__SOURCE_PORT = KGRAPH_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Port</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__TARGET_PORT = KGRAPH_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Labels</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE__LABELS = KGRAPH_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>KEdge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KEDGE_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.impl.KPortImpl <em>KPort</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.impl.KPortImpl
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKPort()
     * @generated
     */
    int KPORT = 4;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT__EANNOTATIONS = KGRAPH_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT__DATA = KGRAPH_ELEMENT__DATA;

    /**
     * The feature id for the '<em><b>Node</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT__NODE = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT__EDGES = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT__LABEL = KGRAPH_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>KPort</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KPORT_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.core.kgraph.impl.KLabelImpl <em>KLabel</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.core.kgraph.impl.KLabelImpl
     * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKLabel()
     * @generated
     */
    int KLABEL = 5;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLABEL__EANNOTATIONS = KGRAPH_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLABEL__DATA = KGRAPH_ELEMENT__DATA;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLABEL__TEXT = KGRAPH_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLABEL__PARENT = KGRAPH_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>KLabel</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int KLABEL_FEATURE_COUNT = KGRAPH_ELEMENT_FEATURE_COUNT + 2;

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KGraphElement <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KGraphElement
     * @generated
     */
    EClass getKGraphElement();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.kgraph.KGraphElement#getData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Data</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KGraphElement#getData()
     * @see #getKGraphElement()
     * @generated
     */
    EReference getKGraphElement_Data();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KGraphData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Data</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KGraphData
     * @generated
     */
    EClass getKGraphData();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KNode <em>KNode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KNode</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode
     * @generated
     */
    EClass getKNode();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.kgraph.KNode#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getChildren()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_Children();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.core.kgraph.KNode#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getParent()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_Parent();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.kgraph.KNode#getPorts <em>Ports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Ports</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getPorts()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_Ports();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.kgraph.KNode#getOutgoingEdges <em>Outgoing Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Outgoing Edges</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getOutgoingEdges()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_OutgoingEdges();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.core.kgraph.KNode#getIncomingEdges <em>Incoming Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getIncomingEdges()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_IncomingEdges();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.kgraph.KNode#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KNode#getLabel()
     * @see #getKNode()
     * @generated
     */
    EReference getKNode_Label();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KEdge <em>KEdge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KEdge</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge
     * @generated
     */
    EClass getKEdge();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.core.kgraph.KEdge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge#getSource()
     * @see #getKEdge()
     * @generated
     */
    EReference getKEdge_Source();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.core.kgraph.KEdge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge#getTarget()
     * @see #getKEdge()
     * @generated
     */
    EReference getKEdge_Target();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.core.kgraph.KEdge#getSourcePort <em>Source Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source Port</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge#getSourcePort()
     * @see #getKEdge()
     * @generated
     */
    EReference getKEdge_SourcePort();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.core.kgraph.KEdge#getTargetPort <em>Target Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target Port</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge#getTargetPort()
     * @see #getKEdge()
     * @generated
     */
    EReference getKEdge_TargetPort();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.core.kgraph.KEdge#getLabels <em>Labels</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Labels</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KEdge#getLabels()
     * @see #getKEdge()
     * @generated
     */
    EReference getKEdge_Labels();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KPort <em>KPort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPort</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KPort
     * @generated
     */
    EClass getKPort();

    /**
     * Returns the meta object for the container reference '{@link de.cau.cs.kieler.core.kgraph.KPort#getNode <em>Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Node</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KPort#getNode()
     * @see #getKPort()
     * @generated
     */
    EReference getKPort_Node();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.core.kgraph.KPort#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KPort#getEdges()
     * @see #getKPort()
     * @generated
     */
    EReference getKPort_Edges();

    /**
     * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.core.kgraph.KPort#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KPort#getLabel()
     * @see #getKPort()
     * @generated
     */
    EReference getKPort_Label();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.core.kgraph.KLabel <em>KLabel</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>KLabel</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KLabel
     * @generated
     */
    EClass getKLabel();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.core.kgraph.KLabel#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KLabel#getText()
     * @see #getKLabel()
     * @generated
     */
    EAttribute getKLabel_Text();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.core.kgraph.KLabel#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parent</em>'.
     * @see de.cau.cs.kieler.core.kgraph.KLabel#getParent()
     * @see #getKLabel()
     * @generated
     */
    EReference getKLabel_Parent();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    KGraphFactory getKGraphFactory();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.impl.KGraphElementImpl <em>Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphElementImpl
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKGraphElement()
         * @generated
         */
        EClass KGRAPH_ELEMENT = eINSTANCE.getKGraphElement();

        /**
         * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KGRAPH_ELEMENT__DATA = eINSTANCE.getKGraphElement_Data();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.KGraphData <em>Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.KGraphData
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKGraphData()
         * @generated
         */
        EClass KGRAPH_DATA = eINSTANCE.getKGraphData();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.impl.KNodeImpl <em>KNode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.impl.KNodeImpl
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKNode()
         * @generated
         */
        EClass KNODE = eINSTANCE.getKNode();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__CHILDREN = eINSTANCE.getKNode_Children();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__PARENT = eINSTANCE.getKNode_Parent();

        /**
         * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__PORTS = eINSTANCE.getKNode_Ports();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__OUTGOING_EDGES = eINSTANCE.getKNode_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Incoming Edges</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__INCOMING_EDGES = eINSTANCE.getKNode_IncomingEdges();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KNODE__LABEL = eINSTANCE.getKNode_Label();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl <em>KEdge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.impl.KEdgeImpl
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKEdge()
         * @generated
         */
        EClass KEDGE = eINSTANCE.getKEdge();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE__SOURCE = eINSTANCE.getKEdge_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE__TARGET = eINSTANCE.getKEdge_Target();

        /**
         * The meta object literal for the '<em><b>Source Port</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE__SOURCE_PORT = eINSTANCE.getKEdge_SourcePort();

        /**
         * The meta object literal for the '<em><b>Target Port</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE__TARGET_PORT = eINSTANCE.getKEdge_TargetPort();

        /**
         * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KEDGE__LABELS = eINSTANCE.getKEdge_Labels();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.impl.KPortImpl <em>KPort</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.impl.KPortImpl
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKPort()
         * @generated
         */
        EClass KPORT = eINSTANCE.getKPort();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPORT__NODE = eINSTANCE.getKPort_Node();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPORT__EDGES = eINSTANCE.getKPort_Edges();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KPORT__LABEL = eINSTANCE.getKPort_Label();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.core.kgraph.impl.KLabelImpl <em>KLabel</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.core.kgraph.impl.KLabelImpl
         * @see de.cau.cs.kieler.core.kgraph.impl.KGraphPackageImpl#getKLabel()
         * @generated
         */
        EClass KLABEL = eINSTANCE.getKLabel();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute KLABEL__TEXT = eINSTANCE.getKLabel_Text();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference KLABEL__PARENT = eINSTANCE.getKLabel_Parent();

    }

} //KGraphPackage
