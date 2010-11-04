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
 * A representation of the model object '<em><b>Label Style Template Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.LabelStyleTemplateType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.LabelStyleTemplateType#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.LabelStyleTemplateType#getText <em>Text</em>}</li>
 *   <li>{@link net.ogdf.ogml.LabelStyleTemplateType#getFont <em>Font</em>}</li>
 *   <li>{@link net.ogdf.ogml.LabelStyleTemplateType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType()
 * @model extendedMetaData="name='labelStyleTemplate.type' kind='elementOnly'"
 * @generated
 */
public interface LabelStyleTemplateType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Template</em>' containment reference.
     * @see #setTemplate(TemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType_Template()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='template' namespace='##targetNamespace'"
     * @generated
     */
    TemplateType getTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LabelStyleTemplateType#getTemplate <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Template</em>' containment reference.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(TemplateType value);

    /**
     * Returns the value of the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' containment reference.
     * @see #setText(TextType)
     * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType_Text()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
     * @generated
     */
    TextType getText();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LabelStyleTemplateType#getText <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' containment reference.
     * @see #getText()
     * @generated
     */
    void setText(TextType value);

    /**
     * Returns the value of the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font</em>' containment reference.
     * @see #setFont(FontType)
     * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType_Font()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='font' namespace='##targetNamespace'"
     * @generated
     */
    FontType getFont();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LabelStyleTemplateType#getFont <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font</em>' containment reference.
     * @see #getFont()
     * @generated
     */
    void setFont(FontType value);

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
     * @see net.ogdf.ogml.OgmlPackage#getLabelStyleTemplateType_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LabelStyleTemplateType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // LabelStyleTemplateType
