/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Font Stretch Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage#getFontStretchType()
 * @model extendedMetaData="name='fontStretch.type'"
 * @generated
 */
public enum FontStretchType implements Enumerator {
    /**
     * The '<em><b>Normal</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NORMAL_VALUE
     * @generated
     * @ordered
     */
    NORMAL(0, "normal", "normal"),

    /**
     * The '<em><b>Wider</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WIDER_VALUE
     * @generated
     * @ordered
     */
    WIDER(1, "wider", "wider"),

    /**
     * The '<em><b>Narrower</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NARROWER_VALUE
     * @generated
     * @ordered
     */
    NARROWER(2, "narrower", "narrower"),

    /**
     * The '<em><b>Ultra Condensed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ULTRA_CONDENSED_VALUE
     * @generated
     * @ordered
     */
    ULTRA_CONDENSED(3, "ultraCondensed", "ultra-condensed"),

    /**
     * The '<em><b>Extra Condensed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXTRA_CONDENSED_VALUE
     * @generated
     * @ordered
     */
    EXTRA_CONDENSED(4, "extraCondensed", "extra-condensed"),

    /**
     * The '<em><b>Condensed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONDENSED_VALUE
     * @generated
     * @ordered
     */
    CONDENSED(5, "condensed", "condensed"),

    /**
     * The '<em><b>Semi Condensed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SEMI_CONDENSED_VALUE
     * @generated
     * @ordered
     */
    SEMI_CONDENSED(6, "semiCondensed", "semi-condensed"),

    /**
     * The '<em><b>Semi Expanded</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SEMI_EXPANDED_VALUE
     * @generated
     * @ordered
     */
    SEMI_EXPANDED(7, "semiExpanded", "semi-expanded"),

    /**
     * The '<em><b>Expanded</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXPANDED_VALUE
     * @generated
     * @ordered
     */
    EXPANDED(8, "expanded", "expanded"),

    /**
     * The '<em><b>Extra Expanded</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXTRA_EXPANDED_VALUE
     * @generated
     * @ordered
     */
    EXTRA_EXPANDED(9, "extraExpanded", "extra-expanded"),

    /**
     * The '<em><b>Ultra Expanded</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ULTRA_EXPANDED_VALUE
     * @generated
     * @ordered
     */
    ULTRA_EXPANDED(10, "ultraExpanded", "ultra-expanded");

    /**
     * The '<em><b>Normal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Normal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NORMAL
     * @model name="normal"
     * @generated
     * @ordered
     */
    public static final int NORMAL_VALUE = 0;

    /**
     * The '<em><b>Wider</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Wider</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #WIDER
     * @model name="wider"
     * @generated
     * @ordered
     */
    public static final int WIDER_VALUE = 1;

    /**
     * The '<em><b>Narrower</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Narrower</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NARROWER
     * @model name="narrower"
     * @generated
     * @ordered
     */
    public static final int NARROWER_VALUE = 2;

    /**
     * The '<em><b>Ultra Condensed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ultra Condensed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ULTRA_CONDENSED
     * @model name="ultraCondensed" literal="ultra-condensed"
     * @generated
     * @ordered
     */
    public static final int ULTRA_CONDENSED_VALUE = 3;

    /**
     * The '<em><b>Extra Condensed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Extra Condensed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXTRA_CONDENSED
     * @model name="extraCondensed" literal="extra-condensed"
     * @generated
     * @ordered
     */
    public static final int EXTRA_CONDENSED_VALUE = 4;

    /**
     * The '<em><b>Condensed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Condensed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONDENSED
     * @model name="condensed"
     * @generated
     * @ordered
     */
    public static final int CONDENSED_VALUE = 5;

    /**
     * The '<em><b>Semi Condensed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Semi Condensed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SEMI_CONDENSED
     * @model name="semiCondensed" literal="semi-condensed"
     * @generated
     * @ordered
     */
    public static final int SEMI_CONDENSED_VALUE = 6;

    /**
     * The '<em><b>Semi Expanded</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Semi Expanded</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SEMI_EXPANDED
     * @model name="semiExpanded" literal="semi-expanded"
     * @generated
     * @ordered
     */
    public static final int SEMI_EXPANDED_VALUE = 7;

    /**
     * The '<em><b>Expanded</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Expanded</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXPANDED
     * @model name="expanded"
     * @generated
     * @ordered
     */
    public static final int EXPANDED_VALUE = 8;

    /**
     * The '<em><b>Extra Expanded</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Extra Expanded</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXTRA_EXPANDED
     * @model name="extraExpanded" literal="extra-expanded"
     * @generated
     * @ordered
     */
    public static final int EXTRA_EXPANDED_VALUE = 9;

    /**
     * The '<em><b>Ultra Expanded</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ultra Expanded</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ULTRA_EXPANDED
     * @model name="ultraExpanded" literal="ultra-expanded"
     * @generated
     * @ordered
     */
    public static final int ULTRA_EXPANDED_VALUE = 10;

    /**
     * An array of all the '<em><b>Font Stretch Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final FontStretchType[] VALUES_ARRAY =
        new FontStretchType[] {
            NORMAL,
            WIDER,
            NARROWER,
            ULTRA_CONDENSED,
            EXTRA_CONDENSED,
            CONDENSED,
            SEMI_CONDENSED,
            SEMI_EXPANDED,
            EXPANDED,
            EXTRA_EXPANDED,
            ULTRA_EXPANDED,
        };

    /**
     * A public read-only list of all the '<em><b>Font Stretch Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<FontStretchType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Font Stretch Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FontStretchType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FontStretchType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Font Stretch Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FontStretchType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FontStretchType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Font Stretch Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static FontStretchType get(int value) {
        switch (value) {
            case NORMAL_VALUE: return NORMAL;
            case WIDER_VALUE: return WIDER;
            case NARROWER_VALUE: return NARROWER;
            case ULTRA_CONDENSED_VALUE: return ULTRA_CONDENSED;
            case EXTRA_CONDENSED_VALUE: return EXTRA_CONDENSED;
            case CONDENSED_VALUE: return CONDENSED;
            case SEMI_CONDENSED_VALUE: return SEMI_CONDENSED;
            case SEMI_EXPANDED_VALUE: return SEMI_EXPANDED;
            case EXPANDED_VALUE: return EXPANDED;
            case EXTRA_EXPANDED_VALUE: return EXTRA_EXPANDED;
            case ULTRA_EXPANDED_VALUE: return ULTRA_EXPANDED;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private FontStretchType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //FontStretchType
