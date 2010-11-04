/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Style Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.SourceStyleType#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceStyleType#getType <em>Type</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceStyleType#getUri <em>Uri</em>}</li>
 *   <li>{@link net.ogdf.ogml.SourceStyleType#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getSourceStyleType()
 * @model extendedMetaData="name='sourceStyle_._type' kind='empty'"
 * @generated
 */
public interface SourceStyleType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getSourceStyleType_Color()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='color' namespace='##targetNamespace'"
     * @generated
     */
    String getColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceStyleType#getColor <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.EndpointStylesType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see net.ogdf.ogml.EndpointStylesType
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(EndpointStylesType)
     * @see net.ogdf.ogml.OgmlPackage#getSourceStyleType_Type()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
     * @generated
     */
    EndpointStylesType getType();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceStyleType#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see net.ogdf.ogml.EndpointStylesType
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(EndpointStylesType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.SourceStyleType#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetType()
     * @see #getType()
     * @see #setType(EndpointStylesType)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.SourceStyleType#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(EndpointStylesType)
     * @generated
     */
    boolean isSetType();

    /**
     * Returns the value of the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri</em>' attribute.
     * @see #setUri(String)
     * @see net.ogdf.ogml.OgmlPackage#getSourceStyleType_Uri()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
     *        extendedMetaData="kind='attribute' name='uri' namespace='##targetNamespace'"
     * @generated
     */
    String getUri();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceStyleType#getUri <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uri</em>' attribute.
     * @see #getUri()
     * @generated
     */
    void setUri(String value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(BigInteger)
     * @see net.ogdf.ogml.OgmlPackage#getSourceStyleType_Width()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='width' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getWidth();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.SourceStyleType#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(BigInteger value);

} // SourceStyleType
