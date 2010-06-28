/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;edge&gt; element.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getDesc <em>Desc</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getData <em>Data</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getGraph <em>Graph</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#isDirected <em>Directed</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getSource <em>Source</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getSourceport <em>Sourceport</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getTarget <em>Target</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.EdgeType#getTargetport <em>Targetport</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType()
 * @model extendedMetaData="name='edge.type' kind='elementOnly'"
 * @generated
 */
public interface EdgeType extends EObject {
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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Desc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='desc' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDesc();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getDesc <em>Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Desc</em>' attribute.
	 * @see #getDesc()
	 * @generated
	 */
	void setDesc(String value);

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Data()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataType> getData();

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Graph()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
	 * @generated
	 */
	GraphType getGraph();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getGraph <em>Graph</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph</em>' containment reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(GraphType value);

	/**
	 * Returns the value of the '<em><b>Directed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 overwrites the edgedefault attribute of &lt;graph&gt; .
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Directed</em>' attribute.
	 * @see #isSetDirected()
	 * @see #unsetDirected()
	 * @see #setDirected(boolean)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Directed()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='directed'"
	 * @generated
	 */
	boolean isDirected();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#isDirected <em>Directed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Directed</em>' attribute.
	 * @see #isSetDirected()
	 * @see #unsetDirected()
	 * @see #isDirected()
	 * @generated
	 */
	void setDirected(boolean value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#isDirected <em>Directed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDirected()
	 * @see #isDirected()
	 * @see #setDirected(boolean)
	 * @generated
	 */
	void unsetDirected();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#isDirected <em>Directed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Directed</em>' attribute is set.
	 * @see #unsetDirected()
	 * @see #isDirected()
	 * @see #setDirected(boolean)
	 * @generated
	 */
	boolean isSetDirected();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                 identifies this edge .
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   points to the id attribute of the source &lt;node&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Source()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='source'"
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

	/**
	 * Returns the value of the '<em><b>Sourceport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                  points to the name attribute of the source &lt;port&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sourceport</em>' attribute.
	 * @see #setSourceport(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Sourceport()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='sourceport'"
	 * @generated
	 */
	String getSourceport();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getSourceport <em>Sourceport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sourceport</em>' attribute.
	 * @see #getSourceport()
	 * @generated
	 */
	void setSourceport(String value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                    points to the id attribute of the target &lt;node&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Target()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='target'"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

	/**
	 * Returns the value of the '<em><b>Targetport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                     points to the name attribute of the target &lt;port&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Targetport</em>' attribute.
	 * @see #setTargetport(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getEdgeType_Targetport()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='targetport'"
	 * @generated
	 */
	String getTargetport();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.EdgeType#getTargetport <em>Targetport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Targetport</em>' attribute.
	 * @see #getTargetport()
	 * @generated
	 */
	void setTargetport(String value);

} // EdgeType
