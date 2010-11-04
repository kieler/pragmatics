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
 * A representation of the model object '<em><b>Styles Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.StylesType#getGraphStyle <em>Graph Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.StylesType#getGroup <em>Group</em>}</li>
 *   <li>{@link net.ogdf.ogml.StylesType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.StylesType#getNodeStyle <em>Node Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.StylesType#getEdgeStyle <em>Edge Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.StylesType#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getStylesType()
 * @model extendedMetaData="name='styles.type' kind='elementOnly'"
 * @generated
 */
public interface StylesType extends EObject {
    /**
     * Returns the value of the '<em><b>Graph Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Graph Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Graph Style</em>' containment reference.
     * @see #setGraphStyle(GraphStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_GraphStyle()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='graphStyle' namespace='##targetNamespace'"
     * @generated
     */
    GraphStyleType getGraphStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.StylesType#getGraphStyle <em>Graph Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graph Style</em>' containment reference.
     * @see #getGraphStyle()
     * @generated
     */
    void setGraphStyle(GraphStyleType value);

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
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_Group()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='group:1'"
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
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_Data()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace' group='group:1'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Node Style</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.NodeLayoutType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Style</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Style</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_NodeStyle()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='nodeStyle' namespace='##targetNamespace' group='group:1'"
     * @generated
     */
    EList<NodeLayoutType> getNodeStyle();

    /**
     * Returns the value of the '<em><b>Edge Style</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.EdgeLayoutType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Style</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Style</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_EdgeStyle()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edgeStyle' namespace='##targetNamespace' group='group:1'"
     * @generated
     */
    EList<EdgeLayoutType> getEdgeStyle();

    /**
     * Returns the value of the '<em><b>Label Style</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.LabelLayoutType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Style</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getStylesType_LabelStyle()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='labelStyle' namespace='##targetNamespace' group='group:1'"
     * @generated
     */
    EList<LabelLayoutType> getLabelStyle();

} // StylesType
