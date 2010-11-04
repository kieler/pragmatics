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
 * A representation of the model object '<em><b>Text Type</b></em>'.
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
 *   <li>{@link net.ogdf.ogml.TextType#getAlignment <em>Alignment</em>}</li>
 *   <li>{@link net.ogdf.ogml.TextType#getDecoration <em>Decoration</em>}</li>
 *   <li>{@link net.ogdf.ogml.TextType#getRotation <em>Rotation</em>}</li>
 *   <li>{@link net.ogdf.ogml.TextType#getTransform <em>Transform</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getTextType()
 * @model extendedMetaData="name='text.type' kind='empty'"
 * @generated
 */
public interface TextType extends EObject {
    /**
     * Returns the value of the '<em><b>Alignment</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.AlignmentType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Alignment</em>' attribute.
     * @see net.ogdf.ogml.AlignmentType
     * @see #isSetAlignment()
     * @see #unsetAlignment()
     * @see #setAlignment(AlignmentType)
     * @see net.ogdf.ogml.OgmlPackage#getTextType_Alignment()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='alignment' namespace='##targetNamespace'"
     * @generated
     */
    AlignmentType getAlignment();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.TextType#getAlignment <em>Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Alignment</em>' attribute.
     * @see net.ogdf.ogml.AlignmentType
     * @see #isSetAlignment()
     * @see #unsetAlignment()
     * @see #getAlignment()
     * @generated
     */
    void setAlignment(AlignmentType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.TextType#getAlignment <em>Alignment</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetAlignment()
     * @see #getAlignment()
     * @see #setAlignment(AlignmentType)
     * @generated
     */
    void unsetAlignment();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.TextType#getAlignment <em>Alignment</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Alignment</em>' attribute is set.
     * @see #unsetAlignment()
     * @see #getAlignment()
     * @see #setAlignment(AlignmentType)
     * @generated
     */
    boolean isSetAlignment();

    /**
     * Returns the value of the '<em><b>Decoration</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.DecorationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decoration</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Decoration</em>' attribute.
     * @see net.ogdf.ogml.DecorationType
     * @see #isSetDecoration()
     * @see #unsetDecoration()
     * @see #setDecoration(DecorationType)
     * @see net.ogdf.ogml.OgmlPackage#getTextType_Decoration()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='decoration' namespace='##targetNamespace'"
     * @generated
     */
    DecorationType getDecoration();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.TextType#getDecoration <em>Decoration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Decoration</em>' attribute.
     * @see net.ogdf.ogml.DecorationType
     * @see #isSetDecoration()
     * @see #unsetDecoration()
     * @see #getDecoration()
     * @generated
     */
    void setDecoration(DecorationType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.TextType#getDecoration <em>Decoration</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDecoration()
     * @see #getDecoration()
     * @see #setDecoration(DecorationType)
     * @generated
     */
    void unsetDecoration();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.TextType#getDecoration <em>Decoration</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Decoration</em>' attribute is set.
     * @see #unsetDecoration()
     * @see #getDecoration()
     * @see #setDecoration(DecorationType)
     * @generated
     */
    boolean isSetDecoration();

    /**
     * Returns the value of the '<em><b>Rotation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rotation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rotation</em>' attribute.
     * @see #setRotation(BigInteger)
     * @see net.ogdf.ogml.OgmlPackage#getTextType_Rotation()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='rotation' namespace='##targetNamespace'"
     * @generated
     */
    BigInteger getRotation();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.TextType#getRotation <em>Rotation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rotation</em>' attribute.
     * @see #getRotation()
     * @generated
     */
    void setRotation(BigInteger value);

    /**
     * Returns the value of the '<em><b>Transform</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.TransformType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transform</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transform</em>' attribute.
     * @see net.ogdf.ogml.TransformType
     * @see #isSetTransform()
     * @see #unsetTransform()
     * @see #setTransform(TransformType)
     * @see net.ogdf.ogml.OgmlPackage#getTextType_Transform()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='transform' namespace='##targetNamespace'"
     * @generated
     */
    TransformType getTransform();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.TextType#getTransform <em>Transform</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transform</em>' attribute.
     * @see net.ogdf.ogml.TransformType
     * @see #isSetTransform()
     * @see #unsetTransform()
     * @see #getTransform()
     * @generated
     */
    void setTransform(TransformType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.TextType#getTransform <em>Transform</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTransform()
     * @see #getTransform()
     * @see #setTransform(TransformType)
     * @generated
     */
    void unsetTransform();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.TextType#getTransform <em>Transform</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Transform</em>' attribute is set.
     * @see #unsetTransform()
     * @see #getTransform()
     * @see #setTransform(TransformType)
     * @generated
     */
    boolean isSetTransform();

} // TextType
