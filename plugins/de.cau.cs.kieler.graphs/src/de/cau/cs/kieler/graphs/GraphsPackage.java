/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.graphs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see de.cau.cs.kieler.graphs.GraphsFactory
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
	String eNS_URI = "http://cs.cau.de/Graphs";

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
	GraphsPackage eINSTANCE = de.cau.cs.kieler.graphs.impl.GraphsPackageImpl.init();

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.graphs.impl.GraphImpl <em>Graph</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.graphs.impl.GraphImpl
     * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getGraph()
     * @generated
     */
	int GRAPH = 0;

	/**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GRAPH__NODES = 0;

	/**
     * The feature id for the '<em><b>Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GRAPH__EDGES = 1;

	/**
     * The number of structural features of the '<em>Graph</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GRAPH_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.graphs.impl.NodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.graphs.impl.NodeImpl
     * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getNode()
     * @generated
     */
	int NODE = 1;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NODE__OUTGOING = 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NODE__INCOMING = 1;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NODE__LABEL = 2;

	/**
     * The number of structural features of the '<em>Node</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NODE_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.graphs.impl.EdgeImpl <em>Edge</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.graphs.impl.EdgeImpl
     * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getEdge()
     * @generated
     */
	int EDGE = 2;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EDGE__SOURCE = 0;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EDGE__TARGET = 1;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EDGE__LABEL = 2;

	/**
     * The number of structural features of the '<em>Edge</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EDGE_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link de.cau.cs.kieler.graphs.impl.CompoundNodeImpl <em>Compound Node</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.graphs.impl.CompoundNodeImpl
     * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getCompoundNode()
     * @generated
     */
	int COMPOUND_NODE = 3;

	/**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE__NODES = GRAPH__NODES;

	/**
     * The feature id for the '<em><b>Edges</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE__EDGES = GRAPH__EDGES;

	/**
     * The feature id for the '<em><b>Outgoing</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE__OUTGOING = GRAPH_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Incoming</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE__INCOMING = GRAPH_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE__LABEL = GRAPH_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Compound Node</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_NODE_FEATURE_COUNT = GRAPH_FEATURE_COUNT + 3;


	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.graphs.Graph <em>Graph</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Graph</em>'.
     * @see de.cau.cs.kieler.graphs.Graph
     * @generated
     */
	EClass getGraph();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.graphs.Graph#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Nodes</em>'.
     * @see de.cau.cs.kieler.graphs.Graph#getNodes()
     * @see #getGraph()
     * @generated
     */
	EReference getGraph_Nodes();

	/**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.graphs.Graph#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Edges</em>'.
     * @see de.cau.cs.kieler.graphs.Graph#getEdges()
     * @see #getGraph()
     * @generated
     */
	EReference getGraph_Edges();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.graphs.Node <em>Node</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see de.cau.cs.kieler.graphs.Node
     * @generated
     */
	EClass getNode();

	/**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.graphs.Node#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing</em>'.
     * @see de.cau.cs.kieler.graphs.Node#getOutgoing()
     * @see #getNode()
     * @generated
     */
	EReference getNode_Outgoing();

	/**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.graphs.Node#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming</em>'.
     * @see de.cau.cs.kieler.graphs.Node#getIncoming()
     * @see #getNode()
     * @generated
     */
	EReference getNode_Incoming();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.graphs.Node#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see de.cau.cs.kieler.graphs.Node#getLabel()
     * @see #getNode()
     * @generated
     */
	EAttribute getNode_Label();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.graphs.Edge <em>Edge</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Edge</em>'.
     * @see de.cau.cs.kieler.graphs.Edge
     * @generated
     */
	EClass getEdge();

	/**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.graphs.Edge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see de.cau.cs.kieler.graphs.Edge#getSource()
     * @see #getEdge()
     * @generated
     */
	EReference getEdge_Source();

	/**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.graphs.Edge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.cau.cs.kieler.graphs.Edge#getTarget()
     * @see #getEdge()
     * @generated
     */
	EReference getEdge_Target();

	/**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.graphs.Edge#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see de.cau.cs.kieler.graphs.Edge#getLabel()
     * @see #getEdge()
     * @generated
     */
	EAttribute getEdge_Label();

	/**
     * Returns the meta object for class '{@link de.cau.cs.kieler.graphs.CompoundNode <em>Compound Node</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound Node</em>'.
     * @see de.cau.cs.kieler.graphs.CompoundNode
     * @generated
     */
	EClass getCompoundNode();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.graphs.impl.GraphImpl <em>Graph</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.graphs.impl.GraphImpl
         * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getGraph()
         * @generated
         */
		EClass GRAPH = eINSTANCE.getGraph();

		/**
         * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GRAPH__NODES = eINSTANCE.getGraph_Nodes();

		/**
         * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GRAPH__EDGES = eINSTANCE.getGraph_Edges();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.graphs.impl.NodeImpl <em>Node</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.graphs.impl.NodeImpl
         * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getNode()
         * @generated
         */
		EClass NODE = eINSTANCE.getNode();

		/**
         * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference NODE__OUTGOING = eINSTANCE.getNode_Outgoing();

		/**
         * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference NODE__INCOMING = eINSTANCE.getNode_Incoming();

		/**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute NODE__LABEL = eINSTANCE.getNode_Label();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.graphs.impl.EdgeImpl <em>Edge</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.graphs.impl.EdgeImpl
         * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getEdge()
         * @generated
         */
		EClass EDGE = eINSTANCE.getEdge();

		/**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

		/**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EDGE__LABEL = eINSTANCE.getEdge_Label();

		/**
         * The meta object literal for the '{@link de.cau.cs.kieler.graphs.impl.CompoundNodeImpl <em>Compound Node</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.graphs.impl.CompoundNodeImpl
         * @see de.cau.cs.kieler.graphs.impl.GraphsPackageImpl#getCompoundNode()
         * @generated
         */
		EClass COMPOUND_NODE = eINSTANCE.getCompoundNode();

	}

} //GraphsPackage
