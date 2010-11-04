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
 * A representation of the model object '<em><b>Font Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.FontType#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getFamily <em>Family</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getSize <em>Size</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getStretch <em>Stretch</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getStyle <em>Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getVariant <em>Variant</em>}</li>
 *   <li>{@link net.ogdf.ogml.FontType#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getFontType()
 * @model extendedMetaData="name='font.type' kind='empty'"
 * @generated
 */
public interface FontType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Color()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='color' namespace='##targetNamespace'"
     * @generated
     */
    String getColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getColor <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(String value);

    /**
     * Returns the value of the '<em><b>Family</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Family</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Family</em>' attribute.
     * @see #setFamily(String)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Family()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='family' namespace='##targetNamespace'"
     * @generated
     */
    String getFamily();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getFamily <em>Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Family</em>' attribute.
     * @see #getFamily()
     * @generated
     */
    void setFamily(String value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Size</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(BigInteger)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Size()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='size' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getSize();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(BigInteger value);

    /**
     * Returns the value of the '<em><b>Stretch</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontStretchType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stretch</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stretch</em>' attribute.
     * @see net.ogdf.ogml.FontStretchType
     * @see #isSetStretch()
     * @see #unsetStretch()
     * @see #setStretch(FontStretchType)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Stretch()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='stretch' namespace='##targetNamespace'"
     * @generated
     */
    FontStretchType getStretch();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getStretch <em>Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Stretch</em>' attribute.
     * @see net.ogdf.ogml.FontStretchType
     * @see #isSetStretch()
     * @see #unsetStretch()
     * @see #getStretch()
     * @generated
     */
    void setStretch(FontStretchType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.FontType#getStretch <em>Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetStretch()
     * @see #getStretch()
     * @see #setStretch(FontStretchType)
     * @generated
     */
    void unsetStretch();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.FontType#getStretch <em>Stretch</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Stretch</em>' attribute is set.
     * @see #unsetStretch()
     * @see #getStretch()
     * @see #setStretch(FontStretchType)
     * @generated
     */
    boolean isSetStretch();

    /**
     * Returns the value of the '<em><b>Style</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontStyleType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.FontStyleType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #setStyle(FontStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Style()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='style' namespace='##targetNamespace'"
     * @generated
     */
    FontStyleType getStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style</em>' attribute.
     * @see net.ogdf.ogml.FontStyleType
     * @see #isSetStyle()
     * @see #unsetStyle()
     * @see #getStyle()
     * @generated
     */
    void setStyle(FontStyleType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.FontType#getStyle <em>Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetStyle()
     * @see #getStyle()
     * @see #setStyle(FontStyleType)
     * @generated
     */
    void unsetStyle();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.FontType#getStyle <em>Style</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Style</em>' attribute is set.
     * @see #unsetStyle()
     * @see #getStyle()
     * @see #setStyle(FontStyleType)
     * @generated
     */
    boolean isSetStyle();

    /**
     * Returns the value of the '<em><b>Variant</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontVariantType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variant</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variant</em>' attribute.
     * @see net.ogdf.ogml.FontVariantType
     * @see #isSetVariant()
     * @see #unsetVariant()
     * @see #setVariant(FontVariantType)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Variant()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='variant' namespace='##targetNamespace'"
     * @generated
     */
    FontVariantType getVariant();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getVariant <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variant</em>' attribute.
     * @see net.ogdf.ogml.FontVariantType
     * @see #isSetVariant()
     * @see #unsetVariant()
     * @see #getVariant()
     * @generated
     */
    void setVariant(FontVariantType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.FontType#getVariant <em>Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetVariant()
     * @see #getVariant()
     * @see #setVariant(FontVariantType)
     * @generated
     */
    void unsetVariant();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.FontType#getVariant <em>Variant</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Variant</em>' attribute is set.
     * @see #unsetVariant()
     * @see #getVariant()
     * @see #setVariant(FontVariantType)
     * @generated
     */
    boolean isSetVariant();

    /**
     * Returns the value of the '<em><b>Weight</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontWeightType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Weight</em>' attribute.
     * @see net.ogdf.ogml.FontWeightType
     * @see #isSetWeight()
     * @see #unsetWeight()
     * @see #setWeight(FontWeightType)
     * @see net.ogdf.ogml.OgmlPackage#getFontType_Weight()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='weight' namespace='##targetNamespace'"
     * @generated
     */
    FontWeightType getWeight();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FontType#getWeight <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Weight</em>' attribute.
     * @see net.ogdf.ogml.FontWeightType
     * @see #isSetWeight()
     * @see #unsetWeight()
     * @see #getWeight()
     * @generated
     */
    void setWeight(FontWeightType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.FontType#getWeight <em>Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetWeight()
     * @see #getWeight()
     * @see #setWeight(FontWeightType)
     * @generated
     */
    void unsetWeight();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.FontType#getWeight <em>Weight</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Weight</em>' attribute is set.
     * @see #unsetWeight()
     * @see #getWeight()
     * @see #setWeight(FontWeightType)
     * @generated
     */
    boolean isSetWeight();

} // FontType
