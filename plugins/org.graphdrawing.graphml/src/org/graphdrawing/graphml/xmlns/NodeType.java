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
 * A representation of the model object '<em><b>Node Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;node&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getPort <em>Port</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getParseIndegree <em>Parse Indegree</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.NodeType#getParseOutdegree <em>Parse Outdegree</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType()
 * @model extendedMetaData="name='node.type' kind='elementOnly'"
 * @generated
 */
public interface NodeType extends EObject {
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Desc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDesc();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getDesc <em>Desc</em>}' attribute.
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Group()
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Data()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<DataType> getData();

	/**
	 * Returns the value of the '<em><b>Port</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.PortType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *            Description: Nodes may be structured by ports; thus edges
	 *                         are not only attached to a node but to a certain
	 *                         port in this node.
	 *            Occurence: &lt;node&gt;, &lt;port&gt;. 
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Port()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='port' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<PortType> getPort();

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           Description: Describes one graph in this document.
	 *           Occurence: &lt;graphml&gt;, &lt;node&gt;, &lt;edge&gt;, &lt;hyperedge&gt;.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Graph</em>' containment reference.
	 * @see #setGraph(GraphType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Graph()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
	 * @generated
	 */
	GraphType getGraph();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getGraph <em>Graph</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' containment reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(GraphType value);

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Locator()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='locator' namespace='##targetNamespace'"
	 * @generated
	 */
	LocatorType getLocator();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getLocator <em>Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locator</em>' containment reference.
	 * @see #getLocator()
	 * @generated
	 */
	void setLocator(LocatorType value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                  identifies this node.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Parse Indegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Indegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Indegree</em>' attribute.
	 * @see #setParseIndegree(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_ParseIndegree()
	 * @model dataType="org.graphdrawing.graphml.xmlns.NodeIndegreeType"
	 *        extendedMetaData="kind='attribute' name='parse.indegree'"
	 * @generated
	 */
	BigInteger getParseIndegree();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getParseIndegree <em>Parse Indegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Indegree</em>' attribute.
	 * @see #getParseIndegree()
	 * @generated
	 */
	void setParseIndegree(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Parse Outdegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parse Outdegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parse Outdegree</em>' attribute.
	 * @see #setParseOutdegree(BigInteger)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getNodeType_ParseOutdegree()
	 * @model dataType="org.graphdrawing.graphml.xmlns.NodeOutdegreeType"
	 *        extendedMetaData="kind='attribute' name='parse.outdegree'"
	 * @generated
	 */
	BigInteger getParseOutdegree();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.NodeType#getParseOutdegree <em>Parse Outdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parse Outdegree</em>' attribute.
	 * @see #getParseOutdegree()
	 * @generated
	 */
	void setParseOutdegree(BigInteger value);

} // NodeType
