/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Line Style Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.LineStyleType#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.LineStyleType#getStyle <em>Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.LineStyleType#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getLineStyleType()
 * @model extendedMetaData="name='lineStyle_._type' kind='empty'"
 * @generated
 */
public interface LineStyleType extends EObject {
    /**
     * Returns the value of the '<em><b>Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Color</em>' attribute.
     * @see #setColor(String)
     * @see net.ogdf.ogml.OgmlPackage#getLineStyleType_Color()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='color' namespace='##targetNamespace'"
     * @generated
     */
    String getColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LineStyleType#getColor <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.LineStyleTypeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #setStyle(LineStyleTypeType)
     * @see net.ogdf.ogml.OgmlPackage#getLineStyleType_Style()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='style' namespace='##targetNamespace'"
     * @generated
     */
    LineStyleTypeType getStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LineStyleType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #getStyle()
     * @generated
     */
    void setStyle(LineStyleTypeType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.LineStyleType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetStyle()
     * @see #getStyle()
     * @see #setStyle(LineStyleTypeType)
     * @generated
     */
    void unsetStyle();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.LineStyleType#getStyle <em>Style</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Style</em>' attribute is set.
     * @see #unsetStyle()
     * @see #getStyle()
     * @see #setStyle(LineStyleTypeType)
     * @generated
     */
    boolean isSetStyle();

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(String)
     * @see net.ogdf.ogml.OgmlPackage#getLineStyleType_Width()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='width' namespace='##targetNamespace'"
     * @generated
     */
    String getWidth();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.LineStyleType#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(String value);

} // LineStyleType
