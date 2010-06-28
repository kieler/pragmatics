/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Endpoint Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;endpoint&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EndpointType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EndpointType#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EndpointType#getNode <em>Node</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EndpointType#getPort <em>Port</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EndpointType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType()
 * @model extendedMetaData="name='endpoint.type' kind='elementOnly'"
 * @generated
 */
public interface EndpointType extends EObject {
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType_Desc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDesc();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getDesc <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Desc</em>' attribute.
	 * @see #getDesc()
	 * @generated
	 */
	void setDesc(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   identifies this &lt;endpoint&gt; .
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  
	 *             points to the id of the node, to which this endpoint is connected. 
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Node</em>' attribute.
	 * @see #setNode(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType_Node()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='node'"
	 * @generated
	 */
	String getNode();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getNode <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' attribute.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 points to the name of the port, to which this endpoint is 
	 *                  connected .
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType_Port()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='port'"
	 * @generated
	 */
	String getPort();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"undir"</code>.
	 * The literals are from the enumeration {@link org.graphdrawing.graphml.xmlns.EndpointTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                defines the direction on this endpoint (undirected per default).
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(EndpointTypeType)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEndpointType_Type()
	 * @model default="undir" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	EndpointTypeType getType();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.graphdrawing.graphml.xmlns.EndpointTypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(EndpointTypeType value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(EndpointTypeType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.EndpointType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(EndpointTypeType)
	 * @generated
	 */
	boolean isSetType();

} // EndpointType
