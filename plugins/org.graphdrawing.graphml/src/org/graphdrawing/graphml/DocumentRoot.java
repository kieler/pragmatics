/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getDefault <em>Default</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getEdge <em>Edge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getGraphml <em>Graphml</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getHyperedge <em>Hyperedge</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getKey <em>Key</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getNode <em>Node</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.DocumentRoot#getPort <em>Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference.
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
     * @return the value of the '<em>Data</em>' containment reference.
     * @see #setData(DataType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Data()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    DataType getData();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getData <em>Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data</em>' containment reference.
     * @see #getData()
     * @generated
     */
    void setData(DataType value);

    /**
     * Returns the value of the '<em><b>Default</b></em>' containment reference.
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
     *                         The (optional) &lt;default&gt; child of &lt;key&gt; gives 
     *                         the default value for the corresponding function. 
     *            Occurence: &lt;key&gt;. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Default</em>' containment reference.
     * @see #setDefault(DefaultType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Default()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='default' namespace='##targetNamespace'"
     * @generated
     */
    DefaultType getDefault();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getDefault <em>Default</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' containment reference.
     * @see #getDefault()
     * @generated
     */
    void setDefault(DefaultType value);

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
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Desc()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
     * @generated
     */
    String getDesc();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getDesc <em>Desc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Desc</em>' attribute.
     * @see #getDesc()
     * @generated
     */
    void setDesc(String value);

    /**
     * Returns the value of the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *            Description: Describes an edge in the &lt;graph&gt; which contains this
     *                         &lt;edge&gt;.
     *            Occurence: &lt;graph&gt;. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Edge</em>' containment reference.
     * @see #setEdge(EdgeType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Edge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace'"
     * @generated
     */
    EdgeType getEdge();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getEdge <em>Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge</em>' containment reference.
     * @see #getEdge()
     * @generated
     */
    void setEdge(EdgeType value);

    /**
     * Returns the value of the '<em><b>Endpoint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *            Description: The list of &lt;endpoints&gt; within a hyperedge
     *                         points to the nodes contained in this hyperedge.
     *            Occurence: &lt;hyperedge&gt;. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Endpoint</em>' containment reference.
     * @see #setEndpoint(EndpointType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Endpoint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='endpoint' namespace='##targetNamespace'"
     * @generated
     */
    EndpointType getEndpoint();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getEndpoint <em>Endpoint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Endpoint</em>' containment reference.
     * @see #getEndpoint()
     * @generated
     */
    void setEndpoint(EndpointType value);

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
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Graph()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
     * @generated
     */
    GraphType getGraph();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getGraph <em>Graph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graph</em>' containment reference.
     * @see #getGraph()
     * @generated
     */
    void setGraph(GraphType value);

    /**
     * Returns the value of the '<em><b>Graphml</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *            Description: &lt;graphml&gt; is the root element of each GraphML 
     *                       document.
     *            Occurence: root. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Graphml</em>' containment reference.
     * @see #setGraphml(GraphmlType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Graphml()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='graphml' namespace='##targetNamespace'"
     * @generated
     */
    GraphmlType getGraphml();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getGraphml <em>Graphml</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graphml</em>' containment reference.
     * @see #getGraphml()
     * @generated
     */
    void setGraphml(GraphmlType value);

    /**
     * Returns the value of the '<em><b>Hyperedge</b></em>' containment reference.
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
     * @return the value of the '<em>Hyperedge</em>' containment reference.
     * @see #setHyperedge(HyperedgeType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Hyperedge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='hyperedge' namespace='##targetNamespace'"
     * @generated
     */
    HyperedgeType getHyperedge();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getHyperedge <em>Hyperedge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hyperedge</em>' containment reference.
     * @see #getHyperedge()
     * @generated
     */
    void setHyperedge(HyperedgeType value);

    /**
     * Returns the value of the '<em><b>Key</b></em>' containment reference.
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
     *            Occurence: &lt;graphml&gt;. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Key</em>' containment reference.
     * @see #setKey(KeyType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Key()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='key' namespace='##targetNamespace'"
     * @generated
     */
    KeyType getKey();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getKey <em>Key</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' containment reference.
     * @see #getKey()
     * @generated
     */
    void setKey(KeyType value);

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
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Locator()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='locator' namespace='##targetNamespace'"
     * @generated
     */
    LocatorType getLocator();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getLocator <em>Locator</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Locator</em>' containment reference.
     * @see #getLocator()
     * @generated
     */
    void setLocator(LocatorType value);

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *            Description: Describes one node in the &lt;graph&gt;
     *                         containing this &lt;node&gt;.
     *            Occurence: &lt;graph&gt;. 
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Node</em>' containment reference.
     * @see #setNode(NodeType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Node()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace'"
     * @generated
     */
    NodeType getNode();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getNode <em>Node</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' containment reference.
     * @see #getNode()
     * @generated
     */
    void setNode(NodeType value);

    /**
     * Returns the value of the '<em><b>Port</b></em>' containment reference.
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
     * @return the value of the '<em>Port</em>' containment reference.
     * @see #setPort(PortType)
     * @see org.graphdrawing.graphml.GraphMLPackage#getDocumentRoot_Port()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='port' namespace='##targetNamespace'"
     * @generated
     */
    PortType getPort();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.DocumentRoot#getPort <em>Port</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Port</em>' containment reference.
     * @see #getPort()
     * @generated
     */
    void setPort(PortType value);

} // DocumentRoot
