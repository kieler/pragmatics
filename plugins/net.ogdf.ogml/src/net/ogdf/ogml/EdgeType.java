/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       The edge.type-tag provides declarations for edges and hyperedges.
 *       An edge has an required identifier "id".
 *       It is possible to spcify an unbounded number of labels as children of the edge.
 *       Also it is possible to define an unbounded number of data-elements.
 *       An edge is defined by a non-empty set of sources and targets. The type of the described edge is implicit given by the number of these elements. One source and one target specify a directed edge. An edge that contains two sourced is an       undirected edge, and two target-elements specify a bidirectional edge.
 *       If there are other combinations of sources/targets the edge is mentioned to be an hyperedge.
 *       Sources and Targets are declared by their referenced id. Feasible references are node- and edge-ids.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.EdgeType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getSource <em>Source</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getTarget <em>Target</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getLabel1 <em>Label1</em>}</li>
 *   <li>{@link net.ogdf.ogml.EdgeType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getEdgeType()
 * @model extendedMetaData="name='edge.type' kind='elementOnly'"
 * @generated
 */
public interface EdgeType extends EObject {
    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.DataType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Label()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace'"
     * @generated
     */
    EList<LabelType> getLabel();

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
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='group:2'"
     * @generated
     */
    FeatureMap getGroup();

    /**
     * Returns the value of the '<em><b>Source</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.SourceTargetType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Source()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='source' namespace='##targetNamespace' group='group:2'"
     * @generated
     */
    EList<SourceTargetType> getSource();

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.SourceTargetType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Target()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='target' namespace='##targetNamespace' group='group:2'"
     * @generated
     */
    EList<SourceTargetType> getTarget();

    /**
     * Returns the value of the '<em><b>Label1</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label1</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label1</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Label1()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace'"
     * @generated
     */
    EList<LabelType> getLabel1();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see net.ogdf.ogml.OgmlPackage#getEdgeType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.EdgeType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // EdgeType
