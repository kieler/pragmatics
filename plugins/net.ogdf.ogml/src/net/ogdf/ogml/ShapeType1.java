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
 * A representation of the model object '<em><b>Shape Type1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.ShapeType1#getHeight <em>Height</em>}</li>
 *   <li>{@link net.ogdf.ogml.ShapeType1#getType <em>Type</em>}</li>
 *   <li>{@link net.ogdf.ogml.ShapeType1#getUri <em>Uri</em>}</li>
 *   <li>{@link net.ogdf.ogml.ShapeType1#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getShapeType1()
 * @model extendedMetaData="name='shape_._type' kind='empty'"
 * @generated
 */
public interface ShapeType1 extends EObject {
    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(BigInteger)
     * @see net.ogdf.ogml.OgmlPackage#getShapeType1_Height()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='height' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getHeight();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.ShapeType1#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(BigInteger value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.ShapeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see net.ogdf.ogml.ShapeType
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(ShapeType)
     * @see net.ogdf.ogml.OgmlPackage#getShapeType1_Type()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
     * @generated
     */
    ShapeType getType();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.ShapeType1#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see net.ogdf.ogml.ShapeType
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(ShapeType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.ShapeType1#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetType()
     * @see #getType()
     * @see #setType(ShapeType)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.ShapeType1#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(ShapeType)
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
     * @see net.ogdf.ogml.OgmlPackage#getShapeType1_Uri()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
     *        extendedMetaData="kind='attribute' name='uri' namespace='##targetNamespace'"
     * @generated
     */
    String getUri();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.ShapeType1#getUri <em>Uri</em>}' attribute.
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
     * @see net.ogdf.ogml.OgmlPackage#getShapeType1_Width()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='width' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getWidth();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.ShapeType1#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(BigInteger value);

} // ShapeType1
