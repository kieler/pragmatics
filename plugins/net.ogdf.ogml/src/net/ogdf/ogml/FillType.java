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
 * A representation of the model object '<em><b>Fill Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.FillType#getColor <em>Color</em>}</li>
 *   <li>{@link net.ogdf.ogml.FillType#getPattern <em>Pattern</em>}</li>
 *   <li>{@link net.ogdf.ogml.FillType#getPatternColor <em>Pattern Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getFillType()
 * @model extendedMetaData="name='fill_._type' kind='empty'"
 * @generated
 */
public interface FillType extends EObject {
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
     * @see net.ogdf.ogml.OgmlPackage#getFillType_Color()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='color' namespace='##targetNamespace'"
     * @generated
     */
    String getColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FillType#getColor <em>Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(String value);

    /**
     * Returns the value of the '<em><b>Pattern</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.PatternType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern</em>' attribute.
     * @see net.ogdf.ogml.PatternType
     * @see #isSetPattern()
     * @see #unsetPattern()
     * @see #setPattern(PatternType)
     * @see net.ogdf.ogml.OgmlPackage#getFillType_Pattern()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='pattern' namespace='##targetNamespace'"
     * @generated
     */
    PatternType getPattern();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FillType#getPattern <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern</em>' attribute.
     * @see net.ogdf.ogml.PatternType
     * @see #isSetPattern()
     * @see #unsetPattern()
     * @see #getPattern()
     * @generated
     */
    void setPattern(PatternType value);

    /**
     * Unsets the value of the '{@link net.ogdf.ogml.FillType#getPattern <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPattern()
     * @see #getPattern()
     * @see #setPattern(PatternType)
     * @generated
     */
    void unsetPattern();

    /**
     * Returns whether the value of the '{@link net.ogdf.ogml.FillType#getPattern <em>Pattern</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Pattern</em>' attribute is set.
     * @see #unsetPattern()
     * @see #getPattern()
     * @see #setPattern(PatternType)
     * @generated
     */
    boolean isSetPattern();

    /**
     * Returns the value of the '<em><b>Pattern Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Color</em>' attribute.
     * @see #setPatternColor(String)
     * @see net.ogdf.ogml.OgmlPackage#getFillType_PatternColor()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='patternColor' namespace='##targetNamespace'"
     * @generated
     */
    String getPatternColor();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.FillType#getPatternColor <em>Pattern Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Color</em>' attribute.
     * @see #getPatternColor()
     * @generated
     */
    void setPatternColor(String value);

} // FillType
