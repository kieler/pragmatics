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
 * A representation of the model object '<em><b>Structure Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * <!-- something missing -->
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.StructureType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.StructureType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.StructureType#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.StructureType#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.StructureType#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getStructureType()
 * @model extendedMetaData="name='structure.type' kind='elementOnly'"
 * @generated
 */
public interface StructureType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getStructureType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='group:0'"
     * @generated
     */
    FeatureMap getGroup();

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
     * @see net.ogdf.ogml.OgmlPackage#getStructureType_Data()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NodeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStructureType_Node()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<NodeType> getNode();

    /**
     * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EdgeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStructureType_Edge()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<EdgeType> getEdge();

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
     * @see net.ogdf.ogml.OgmlPackage#getStructureType_Label()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<LabelType> getLabel();

} // StructureType
