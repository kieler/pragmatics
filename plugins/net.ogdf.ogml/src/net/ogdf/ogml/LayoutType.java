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
 * A representation of the model object '<em><b>Layout Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       the layout-element is encapsulated into style-templates, styles and constraints
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.LayoutType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.LayoutType#getStyleTemplates <em>Style Templates</em>}</li>
 *   <li>{@link net.ogdf.ogml.LayoutType#getStyles <em>Styles</em>}</li>
 *   <li>{@link net.ogdf.ogml.LayoutType#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getLayoutType()
 * @model extendedMetaData="name='layout.type' kind='elementOnly'"
 * @generated
 */
public interface LayoutType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getLayoutType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Style Templates</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style Templates</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style Templates</em>' containment reference.
     * @see #setStyleTemplates(StyleTemplatesType)
     * @see net.ogdf.ogml.OgmlPackage#getLayoutType_StyleTemplates()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='styleTemplates' namespace='##targetNamespace'"
     * @generated
     */
    StyleTemplatesType getStyleTemplates();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LayoutType#getStyleTemplates <em>Style Templates</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style Templates</em>' containment reference.
     * @see #getStyleTemplates()
     * @generated
     */
    void setStyleTemplates(StyleTemplatesType value);

    /**
     * Returns the value of the '<em><b>Styles</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Styles</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Styles</em>' containment reference.
     * @see #setStyles(StylesType)
     * @see net.ogdf.ogml.OgmlPackage#getLayoutType_Styles()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='styles' namespace='##targetNamespace'"
     * @generated
     */
    StylesType getStyles();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LayoutType#getStyles <em>Styles</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Styles</em>' containment reference.
     * @see #getStyles()
     * @generated
     */
    void setStyles(StylesType value);

    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Constraints</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference.
     * @see #setConstraints(ConstraintsType)
     * @see net.ogdf.ogml.OgmlPackage#getLayoutType_Constraints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='constraints' namespace='##targetNamespace'"
     * @generated
     */
    ConstraintsType getConstraints();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LayoutType#getConstraints <em>Constraints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Constraints</em>' containment reference.
     * @see #getConstraints()
     * @generated
     */
    void setConstraints(ConstraintsType value);

} // LayoutType
