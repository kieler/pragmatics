/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;port&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.PortType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.PortType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.PortType#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.PortType#getPort <em>Port</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.PortType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.GraphMLPackage#getPortType()
 * @model extendedMetaData="name='port.type' kind='elementOnly'"
 * @generated
 */
public interface PortType extends EObject {
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
     * @see org.graphdrawing.graphml.GraphMLPackage#getPortType_Desc()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
     * @generated
     */
    String getDesc();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.PortType#getDesc <em>Desc</em>}' attribute.
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
     * @see org.graphdrawing.graphml.GraphMLPackage#getPortType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='group:1'"
     * @generated
     */
    FeatureMap getGroup();

    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference list.
     * The list contents are of type {@link org.graphdrawing.graphml.DataType}.
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
     * @see org.graphdrawing.graphml.GraphMLPackage#getPortType_Data()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='#group:1'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Port</b></em>' containment reference list.
     * The list contents are of type {@link org.graphdrawing.graphml.PortType}.
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
     * @see org.graphdrawing.graphml.GraphMLPackage#getPortType_Port()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='port' namespace='##targetNamespace' group='#group:1'"
     * @generated
     */
    EList<PortType> getPort();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *                    identifies this port, within the node it is contained in.
     *           
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.graphdrawing.graphml.GraphMLPackage#getPortType_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.graphdrawing.graphml.PortType#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // PortType
