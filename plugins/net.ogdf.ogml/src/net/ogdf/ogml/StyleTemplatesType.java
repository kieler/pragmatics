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
 * A representation of the model object '<em><b>Style Templates Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.StyleTemplatesType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.StyleTemplatesType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.StyleTemplatesType#getNodeStyleTemplate <em>Node Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.StyleTemplatesType#getEdgeStyleTemplate <em>Edge Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.StyleTemplatesType#getLabelStyleTemplate <em>Label Style Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType()
 * @model extendedMetaData="name='styleTemplates.type' kind='elementOnly'"
 * @generated
 */
public interface StyleTemplatesType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType_Group()
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
     * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType_Data()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Node Style Template</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NodeStyleTemplateType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Style Template</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Style Template</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType_NodeStyleTemplate()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='nodeStyleTemplate' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<NodeStyleTemplateType> getNodeStyleTemplate();

    /**
     * Returns the value of the '<em><b>Edge Style Template</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EdgeStyleTemplateType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Style Template</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Style Template</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType_EdgeStyleTemplate()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edgeStyleTemplate' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<EdgeStyleTemplateType> getEdgeStyleTemplate();

    /**
     * Returns the value of the '<em><b>Label Style Template</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelStyleTemplateType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Style Template</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style Template</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStyleTemplatesType_LabelStyleTemplate()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='labelStyleTemplate' namespace='##targetNamespace' group='group:0'"
     * @generated
     */
    EList<LabelStyleTemplateType> getLabelStyleTemplate();

} // StyleTemplatesType
