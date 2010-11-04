/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph Style Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       declaration of the default style-templates for nodes, edges and labels
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.GraphStyleType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.GraphStyleType#getDefaultEdgeTemplate <em>Default Edge Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.GraphStyleType#getDefaultLabelTemplate <em>Default Label Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.GraphStyleType#getDefaultNodeTemplate <em>Default Node Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getGraphStyleType()
 * @model extendedMetaData="name='graphStyle.type' kind='elementOnly'"
 * @generated
 */
public interface GraphStyleType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getGraphStyleType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Default Edge Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Edge Template</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Edge Template</em>' attribute.
     * @see #setDefaultEdgeTemplate(String)
     * @see net.ogdf.ogml.OgmlPackage#getGraphStyleType_DefaultEdgeTemplate()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='defaultEdgeTemplate' namespace='##targetNamespace'"
     * @generated
     */
    String getDefaultEdgeTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.GraphStyleType#getDefaultEdgeTemplate <em>Default Edge Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Edge Template</em>' attribute.
     * @see #getDefaultEdgeTemplate()
     * @generated
     */
    void setDefaultEdgeTemplate(String value);

    /**
     * Returns the value of the '<em><b>Default Label Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Label Template</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Label Template</em>' attribute.
     * @see #setDefaultLabelTemplate(String)
     * @see net.ogdf.ogml.OgmlPackage#getGraphStyleType_DefaultLabelTemplate()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='defaultLabelTemplate' namespace='##targetNamespace'"
     * @generated
     */
    String getDefaultLabelTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.GraphStyleType#getDefaultLabelTemplate <em>Default Label Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Label Template</em>' attribute.
     * @see #getDefaultLabelTemplate()
     * @generated
     */
    void setDefaultLabelTemplate(String value);

    /**
     * Returns the value of the '<em><b>Default Node Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Node Template</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Node Template</em>' attribute.
     * @see #setDefaultNodeTemplate(String)
     * @see net.ogdf.ogml.OgmlPackage#getGraphStyleType_DefaultNodeTemplate()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='defaultNodeTemplate' namespace='##targetNamespace'"
     * @generated
     */
    String getDefaultNodeTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.GraphStyleType#getDefaultNodeTemplate <em>Default Node Template</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Node Template</em>' attribute.
     * @see #getDefaultNodeTemplate()
     * @generated
     */
    void setDefaultNodeTemplate(String value);

} // GraphStyleType
