/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graphml Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;graphml&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphmlType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphmlType#getKey <em>Key</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphmlType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphmlType#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.GraphmlType#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType()
 * @model extendedMetaData="name='graphml.type' kind='elementOnly'"
 * @generated
 */
public interface GraphmlType extends EObject {
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType_Desc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDesc();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.GraphmlType#getDesc <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Desc</em>' attribute.
	 * @see #getDesc()
	 * @generated
	 */
	void setDesc(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.KeyType}.
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
	 * @return the value of the '<em>Key</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType_Key()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='key' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<KeyType> getKey();

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:2'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' containment reference list.
	 * The list contents are of type {@link org.graphdrawing.graphml.xmlns.GraphType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           Description: Describes one graph in this document.
	 *           Occurence: &lt;graphml&gt;, &lt;node&gt;, &lt;edge&gt;, &lt;hyperedge&gt;.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Graph</em>' containment reference list.
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType_Graph()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace' group='#group:2'"
	 * @generated
	 */
	EList<GraphType> getGraph();

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getGraphmlType_Data()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='#group:2'"
	 * @generated
	 */
	EList<DataType> getData();

} // GraphmlType
