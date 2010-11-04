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
 * A representation of the literals of the enumeration '<em><b>Line Style Type Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * <!-- something missing -->
 *     
 * <!-- end-model-doc -->
 * @see net.ogdf.ogml.OgmlPackage#getLineStyleTypeType()
 * @model extendedMetaData="name='lineStyleType.type'"
 * @generated
 */
public enum LineStyleTypeType implements Enumerator {
    /**
     * The '<em><b>Solid</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SOLID_VALUE
     * @generated
     * @ordered
     */
    SOLID(0, "solid", "solid"),

    /**
     * The '<em><b>Dotted</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOTTED_VALUE
     * @generated
     * @ordered
     */
    DOTTED(1, "dotted", "dotted"),

    /**
     * The '<em><b>Dashed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DASHED_VALUE
     * @generated
     * @ordered
     */
    DASHED(2, "dashed", "dashed"),

    /**
     * The '<em><b>Double</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE_VALUE
     * @generated
     * @ordered
     */
    DOUBLE(3, "double", "double"),

    /**
     * The '<em><b>Triple</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TRIPLE_VALUE
     * @generated
     * @ordered
     */
    TRIPLE(4, "triple", "triple"),

    /**
     * The '<em><b>Groove</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GROOVE_VALUE
     * @generated
     * @ordered
     */
    GROOVE(5, "groove", "groove"),

    /**
     * The '<em><b>Ridge</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIDGE_VALUE
     * @generated
     * @ordered
     */
    RIDGE(6, "ridge", "ridge"),

    /**
     * The '<em><b>Inset</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INSET_VALUE
     * @generated
     * @ordered
     */
    INSET(7, "inset", "inset"),

    /**
     * The '<em><b>Outset</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OUTSET_VALUE
     * @generated
     * @ordered
     */
    OUTSET(8, "outset", "outset"),

    /**
     * The '<em><b>None</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(9, "none", "none");

    /**
     * The '<em><b>Solid</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Solid</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SOLID
     * @model name="solid"
     * @generated
     * @ordered
     */
    public static final int SOLID_VALUE = 0;

    /**
     * The '<em><b>Dotted</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dotted</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOTTED
     * @model name="dotted"
     * @generated
     * @ordered
     */
    public static final int DOTTED_VALUE = 1;

    /**
     * The '<em><b>Dashed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dashed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DASHED
     * @model name="dashed"
     * @generated
     * @ordered
     */
    public static final int DASHED_VALUE = 2;

    /**
     * The '<em><b>Double</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Double</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOUBLE
     * @model name="double"
     * @generated
     * @ordered
     */
    public static final int DOUBLE_VALUE = 3;

    /**
     * The '<em><b>Triple</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Triple</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TRIPLE
     * @model name="triple"
     * @generated
     * @ordered
     */
    public static final int TRIPLE_VALUE = 4;

    /**
     * The '<em><b>Groove</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Groove</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GROOVE
     * @model name="groove"
     * @generated
     * @ordered
     */
    public static final int GROOVE_VALUE = 5;

    /**
     * The '<em><b>Ridge</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ridge</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RIDGE
     * @model name="ridge"
     * @generated
     * @ordered
     */
    public static final int RIDGE_VALUE = 6;

    /**
     * The '<em><b>Inset</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Inset</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INSET
     * @model name="inset"
     * @generated
     * @ordered
     */
    public static final int INSET_VALUE = 7;

    /**
     * The '<em><b>Outset</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Outset</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OUTSET
     * @model name="outset"
     * @generated
     * @ordered
     */
    public static final int OUTSET_VALUE = 8;

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
    public static final int NONE_VALUE = 9;

    /**
     * An array of all the '<em><b>Line Style Type Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LineStyleTypeType[] VALUES_ARRAY =
        new LineStyleTypeType[] {
            SOLID,
            DOTTED,
            DASHED,
            DOUBLE,
            TRIPLE,
            GROOVE,
            RIDGE,
            INSET,
            OUTSET,
            NONE,
        };

    /**
     * A public read-only list of all the '<em><b>Line Style Type Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LineStyleTypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Line Style Type Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyleTypeType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineStyleTypeType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style Type Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyleTypeType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LineStyleTypeType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style Type Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LineStyleTypeType get(int value) {
        switch (value) {
            case SOLID_VALUE: return SOLID;
            case DOTTED_VALUE: return DOTTED;
            case DASHED_VALUE: return DASHED;
            case DOUBLE_VALUE: return DOUBLE;
            case TRIPLE_VALUE: return TRIPLE;
            case GROOVE_VALUE: return GROOVE;
            case RIDGE_VALUE: return RIDGE;
            case INSET_VALUE: return INSET;
            case OUTSET_VALUE: return OUTSET;
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
    private LineStyleTypeType(int value, String name, String literal) {
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
    
} //LineStyleTypeType
