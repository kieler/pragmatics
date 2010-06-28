/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;graph&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getNode <em>Node</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getEdge <em>Edge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getHyperedge <em>Hyperedge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault <em>Edgedefault</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids <em>Parse Edgeids</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdges <em>Parse Edges</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxindegree <em>Parse Maxindegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxoutdegree <em>Parse Maxoutdegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids <em>Parse Nodeids</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodes <em>Parse Nodes</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphType#getParseOrder <em>Parse Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType()
 * @model extendedMetaData="name='graph.type' kind='elementOnly'"
 * @generated
 */
public interface GraphType extends EObject {
	/**
	 * Returns the value of the '<em><b>Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: Provides human-readable descriptions for the GraphML
	 *                         element containing this &lt;desc&gt; as its first child.
	 *            Occurence:   &lt;key&gt;, &lt;graphml&gt;, &lt;graph&gt;, 
	 *                         &lt;node&gt;, &lt;port&gt;, &lt;edge&gt;, &lt;hyperedge&gt;, and
	 *                         &lt;endpoint&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Desc</em>' attribute.
	 * @see #setDesc(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Desc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDesc();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getDesc <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Desc</em>' attribute.
	 * @see #getDesc()
	 * @generated
	 */
	void setDesc(String value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.DataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: In GraphML there may be data-functions attached
	 *                         to graphs, nodes, ports, edges, hyperedges and
	 *                         endpoint and to the whole collection of 
	 *                         graphs described by the content of &lt;graphml&gt;. 
	 *                         These functions are declared by &lt;key&gt; elements
	 *                         (children of &lt;graphml&gt;) and defined by &lt;data&gt;
	 *                         elements.
	 *            Occurence: &lt;graphml&gt;, &lt;graph&gt;, &lt;node&gt;, &lt;port&gt;, 
	 *                       &lt;edge&gt;, &lt;hyperedge&gt;, and &lt;endpoint&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Data()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<DataType> getData();

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.NodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: Describes one node in the &lt;graph&gt;
	 *                         containing this &lt;node&gt;.
	 *            Occurence: &lt;graph&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Node()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<NodeType> getNode();

	/**
	 * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.EdgeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: Describes an edge in the &lt;graph&gt; which contains this
	 *                         &lt;edge&gt;.
	 *            Occurence: &lt;graph&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Edge</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Edge()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<EdgeType> getEdge();

	/**
	 * Returns the value of the '<em><b>Hyperedge</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.HyperedgeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: While edges describe relations between two nodes, 
	 *                         a hyperedge describes a relation between an arbitrary
	 *                         number of nodes.
	 *            Occurence: &lt;graph&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hyperedge</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Hyperedge()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='hyperedge' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<HyperedgeType> getHyperedge();

	/**
	 * Returns the value of the '<em><b>Locator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: Graphs and nodes are declared by the elements
	 *                         &lt;graph&gt; and &lt;node&gt;, respectively. The optional
	 *                         &lt;locator&gt;-child of these elements point to 
	 *                         their definition. (If there is no &lt;locator&gt;-child
	 *                         the graphs/nodes are defined by their content).
	 *            Occurence:   &lt;graph&gt;, and &lt;node&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Locator</em>' containment reference.
	 * @see #setLocator(LocatorType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Locator()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='locator' namespace='##targetNamespace'"
	 * @generated
	 */
	LocatorType getLocator();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getLocator <em>Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locator</em>' containment reference.
	 * @see #getLocator()
	 * @generated
	 */
	void setLocator(LocatorType value);

	/**
	 * Returns the value of the '<em><b>Edgedefault</b></em>' attribute.
	 * The literals are from the enumeration {@link org.graphdrawing.graphml.xmlns.GraphEdgedefaultType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         describes whether edges of this graph are considered
	 *                         as directed or undirected per default (unless 
	 *                         specified by the attribute directed of &lt;edge&gt;).
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Edgedefault</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @see #isSetEdgedefault()
	 * @see #unsetEdgedefault()
	 * @see #setEdgedefault(GraphEdgedefaultType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Edgedefault()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='edgedefault'"
	 * @generated
	 */
	GraphEdgedefaultType getEdgedefault();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault <em>Edgedefault</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edgedefault</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgedefaultType
	 * @see #isSetEdgedefault()
	 * @see #unsetEdgedefault()
	 * @see #getEdgedefault()
	 * @generated
	 */
	void setEdgedefault(GraphEdgedefaultType value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault <em>Edgedefault</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEdgedefault()
	 * @see #getEdgedefault()
	 * @see #setEdgedefault(GraphEdgedefaultType)
	 * @generated
	 */
	void unsetEdgedefault();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getEdgedefault <em>Edgedefault</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Edgedefault</em>' attribute is set.
	 * @see #unsetEdgedefault()
	 * @see #getEdgedefault()
	 * @see #setEdgedefault(GraphEdgedefaultType)
	 * @generated
	 */
	boolean isSetEdgedefault();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   identifies this graph .
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Parse Edgeids</b></em>' attribute.
	 * The literals are from the enumeration {@link org.graphdrawing.graphml.xmlns.GraphEdgeidsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Edgeids</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Edgeids</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @see #isSetParseEdgeids()
	 * @see #unsetParseEdgeids()
	 * @see #setParseEdgeids(GraphEdgeidsType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseEdgeids()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='parse.edgeids'"
	 * @generated
	 */
	GraphEdgeidsType getParseEdgeids();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids <em>Parse Edgeids</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Edgeids</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphEdgeidsType
	 * @see #isSetParseEdgeids()
	 * @see #unsetParseEdgeids()
	 * @see #getParseEdgeids()
	 * @generated
	 */
	void setParseEdgeids(GraphEdgeidsType value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids <em>Parse Edgeids</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetParseEdgeids()
	 * @see #getParseEdgeids()
	 * @see #setParseEdgeids(GraphEdgeidsType)
	 * @generated
	 */
	void unsetParseEdgeids();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdgeids <em>Parse Edgeids</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Parse Edgeids</em>' attribute is set.
	 * @see #unsetParseEdgeids()
	 * @see #getParseEdgeids()
	 * @see #setParseEdgeids(GraphEdgeidsType)
	 * @generated
	 */
	boolean isSetParseEdgeids();

	/**
	 * Returns the value of the '<em><b>Parse Edges</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Edges</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Edges</em>' attribute.
	 * @see #setParseEdges(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseEdges()
	 * @model dataType="org.graphdrawing.graphml.xmlns.GraphEdgesType"
	 *        extendedMetaData="kind='attribute' name='parse.edges'"
	 * @generated
	 */
	BigInteger getParseEdges();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseEdges <em>Parse Edges</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Edges</em>' attribute.
	 * @see #getParseEdges()
	 * @generated
	 */
	void setParseEdges(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Parse Maxindegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Maxindegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Maxindegree</em>' attribute.
	 * @see #setParseMaxindegree(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseMaxindegree()
	 * @model dataType="org.graphdrawing.graphml.xmlns.GraphMaxindegreeType"
	 *        extendedMetaData="kind='attribute' name='parse.maxindegree'"
	 * @generated
	 */
	BigInteger getParseMaxindegree();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxindegree <em>Parse Maxindegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Maxindegree</em>' attribute.
	 * @see #getParseMaxindegree()
	 * @generated
	 */
	void setParseMaxindegree(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Parse Maxoutdegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Maxoutdegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Maxoutdegree</em>' attribute.
	 * @see #setParseMaxoutdegree(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseMaxoutdegree()
	 * @model dataType="org.graphdrawing.graphml.xmlns.GraphMaxoutdegreeType"
	 *        extendedMetaData="kind='attribute' name='parse.maxoutdegree'"
	 * @generated
	 */
	BigInteger getParseMaxoutdegree();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseMaxoutdegree <em>Parse Maxoutdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Maxoutdegree</em>' attribute.
	 * @see #getParseMaxoutdegree()
	 * @generated
	 */
	void setParseMaxoutdegree(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Parse Nodeids</b></em>' attribute.
	 * The literals are from the enumeration {@link org.graphdrawing.graphml.xmlns.GraphNodeidsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Nodeids</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Nodeids</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @see #isSetParseNodeids()
	 * @see #unsetParseNodeids()
	 * @see #setParseNodeids(GraphNodeidsType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseNodeids()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='parse.nodeids'"
	 * @generated
	 */
	GraphNodeidsType getParseNodeids();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids <em>Parse Nodeids</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Nodeids</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphNodeidsType
	 * @see #isSetParseNodeids()
	 * @see #unsetParseNodeids()
	 * @see #getParseNodeids()
	 * @generated
	 */
	void setParseNodeids(GraphNodeidsType value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids <em>Parse Nodeids</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetParseNodeids()
	 * @see #getParseNodeids()
	 * @see #setParseNodeids(GraphNodeidsType)
	 * @generated
	 */
	void unsetParseNodeids();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodeids <em>Parse Nodeids</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Parse Nodeids</em>' attribute is set.
	 * @see #unsetParseNodeids()
	 * @see #getParseNodeids()
	 * @see #setParseNodeids(GraphNodeidsType)
	 * @generated
	 */
	boolean isSetParseNodeids();

	/**
	 * Returns the value of the '<em><b>Parse Nodes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Nodes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Nodes</em>' attribute.
	 * @see #setParseNodes(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseNodes()
	 * @model dataType="org.graphdrawing.graphml.xmlns.GraphNodesType"
	 *        extendedMetaData="kind='attribute' name='parse.nodes'"
	 * @generated
	 */
	BigInteger getParseNodes();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseNodes <em>Parse Nodes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Nodes</em>' attribute.
	 * @see #getParseNodes()
	 * @generated
	 */
	void setParseNodes(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Parse Order</b></em>' attribute.
	 * The literals are from the enumeration {@link org.graphdrawing.graphml.xmlns.GraphOrderType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Order</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @see #isSetParseOrder()
	 * @see #unsetParseOrder()
	 * @see #setParseOrder(GraphOrderType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphType_ParseOrder()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='parse.order'"
	 * @generated
	 */
	GraphOrderType getParseOrder();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseOrder <em>Parse Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Order</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.GraphOrderType
	 * @see #isSetParseOrder()
	 * @see #unsetParseOrder()
	 * @see #getParseOrder()
	 * @generated
	 */
	void setParseOrder(GraphOrderType value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseOrder <em>Parse Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetParseOrder()
	 * @see #getParseOrder()
	 * @see #setParseOrder(GraphOrderType)
	 * @generated
	 */
	void unsetParseOrder();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.GraphType#getParseOrder <em>Parse Order</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Parse Order</em>' attribute is set.
	 * @see #unsetParseOrder()
	 * @see #getParseOrder()
	 * @see #setParseOrder(GraphOrderType)
	 * @generated
	 */
	boolean isSetParseOrder();

} // GraphType
