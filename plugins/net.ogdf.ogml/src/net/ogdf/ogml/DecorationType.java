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
 * A representation of the literals of the enumeration '<em><b>Decoration Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage#getDecorationType()
 * @model extendedMetaData="name='decoration.type'"
 * @generated
 */
public enum DecorationType implements Enumerator {
    /**
     * The '<em><b>Underline</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNDERLINE_VALUE
     * @generated
     * @ordered
     */
    UNDERLINE(0, "underline", "underline"),

    /**
     * The '<em><b>Overline</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OVERLINE_VALUE
     * @generated
     * @ordered
     */
    OVERLINE(1, "overline", "overline"),

    /**
     * The '<em><b>Blink</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BLINK_VALUE
     * @generated
     * @ordered
     */
    BLINK(2, "blink", "blink"),

    /**
     * The '<em><b>Line Through</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LINE_THROUGH_VALUE
     * @generated
     * @ordered
     */
    LINE_THROUGH(3, "lineThrough", "line-through"),

    /**
     * The '<em><b>None</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(4, "none", "none");

    /**
     * The '<em><b>Underline</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Underline</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UNDERLINE
     * @model name="underline"
     * @generated
     * @ordered
     */
    public static final int UNDERLINE_VALUE = 0;

    /**
     * The '<em><b>Overline</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Overline</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OVERLINE
     * @model name="overline"
     * @generated
     * @ordered
     */
    public static final int OVERLINE_VALUE = 1;

    /**
     * The '<em><b>Blink</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Blink</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BLINK
     * @model name="blink"
     * @generated
     * @ordered
     */
    public static final int BLINK_VALUE = 2;

    /**
     * The '<em><b>Line Through</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Line Through</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LINE_THROUGH
     * @model name="lineThrough" literal="line-through"
     * @generated
     * @ordered
     */
    public static final int LINE_THROUGH_VALUE = 3;

    /**
     * The '<em><b>None</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NONE
     * @model name="none"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 4;

    /**
     * An array of all the '<em><b>Decoration Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final DecorationType[] VALUES_ARRAY =
        new DecorationType[] {
            UNDERLINE,
            OVERLINE,
            BLINK,
            LINE_THROUGH,
            NONE,
        };

    /**
     * A public read-only list of all the '<em><b>Decoration Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<DecorationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Decoration Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DecorationType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DecorationType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Decoration Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DecorationType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DecorationType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Decoration Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DecorationType get(int value) {
        switch (value) {
            case UNDERLINE_VALUE: return UNDERLINE;
            case OVERLINE_VALUE: return OVERLINE;
            case BLINK_VALUE: return BLINK;
            case LINE_THROUGH_VALUE: return LINE_THROUGH;
            case NONE_VALUE: return NONE;
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
    private DecorationType(int value, String name, String literal) {
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
    
} //DecorationType
