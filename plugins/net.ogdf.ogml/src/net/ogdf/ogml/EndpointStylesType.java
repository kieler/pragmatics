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
 * A representation of the literals of the enumeration '<em><b>Endpoint Styles Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage#getEndpointStylesType()
 * @model extendedMetaData="name='endpointStyles.type'"
 * @generated
 */
public enum EndpointStylesType implements Enumerator {
    /**
     * The '<em><b>Arrow</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ARROW_VALUE
     * @generated
     * @ordered
     */
    ARROW(0, "arrow", "arrow"),

    /**
     * The '<em><b>Circle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CIRCLE_VALUE
     * @generated
     * @ordered
     */
    CIRCLE(1, "circle", "circle"),

    /**
     * The '<em><b>Half Circle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HALF_CIRCLE_VALUE
     * @generated
     * @ordered
     */
    HALF_CIRCLE(2, "halfCircle", "halfCircle"),

    /**
     * The '<em><b>Filled Circle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_CIRCLE_VALUE
     * @generated
     * @ordered
     */
    FILLED_CIRCLE(3, "filledCircle", "filledCircle"),

    /**
     * The '<em><b>Filled Half Circle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_CIRCLE_VALUE
     * @generated
     * @ordered
     */
    FILLED_HALF_CIRCLE(4, "filledHalfCircle", "filledHalfCircle"),

    /**
     * The '<em><b>Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BOX_VALUE
     * @generated
     * @ordered
     */
    BOX(5, "box", "box"),

    /**
     * The '<em><b>Half Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HALF_BOX_VALUE
     * @generated
     * @ordered
     */
    HALF_BOX(6, "halfBox", "halfBox"),

    /**
     * The '<em><b>Filled Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_BOX_VALUE
     * @generated
     * @ordered
     */
    FILLED_BOX(7, "filledBox", "filledBox"),

    /**
     * The '<em><b>Filled Half Box</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_BOX_VALUE
     * @generated
     * @ordered
     */
    FILLED_HALF_BOX(8, "filledHalfBox", "filledHalfBox"),

    /**
     * The '<em><b>Rhomb</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RHOMB_VALUE
     * @generated
     * @ordered
     */
    RHOMB(9, "rhomb", "rhomb"),

    /**
     * The '<em><b>Half Rhomb</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HALF_RHOMB_VALUE
     * @generated
     * @ordered
     */
    HALF_RHOMB(10, "halfRhomb", "halfRhomb"),

    /**
     * The '<em><b>Filled Rhomb</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_RHOMB_VALUE
     * @generated
     * @ordered
     */
    FILLED_RHOMB(11, "filledRhomb", "filledRhomb"),

    /**
     * The '<em><b>Filled Half Rhomb</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_RHOMB_VALUE
     * @generated
     * @ordered
     */
    FILLED_HALF_RHOMB(12, "filledHalfRhomb", "filledHalfRhomb"),

    /**
     * The '<em><b>Diamond</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIAMOND_VALUE
     * @generated
     * @ordered
     */
    DIAMOND(13, "diamond", "diamond"),

    /**
     * The '<em><b>Half Diamond</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HALF_DIAMOND_VALUE
     * @generated
     * @ordered
     */
    HALF_DIAMOND(14, "halfDiamond", "halfDiamond"),

    /**
     * The '<em><b>Filled Diamond</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_DIAMOND_VALUE
     * @generated
     * @ordered
     */
    FILLED_DIAMOND(15, "filledDiamond", "filledDiamond"),

    /**
     * The '<em><b>Filled Half Diamond</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_DIAMOND_VALUE
     * @generated
     * @ordered
     */
    FILLED_HALF_DIAMOND(16, "filledHalfDiamond", "filledHalfDiamond"),

    /**
     * The '<em><b>Slash</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SLASH_VALUE
     * @generated
     * @ordered
     */
    SLASH(17, "slash", "slash"),

    /**
     * The '<em><b>Double Slash</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE_SLASH_VALUE
     * @generated
     * @ordered
     */
    DOUBLE_SLASH(18, "doubleSlash", "doubleSlash"),

    /**
     * The '<em><b>Solid</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SOLID_VALUE
     * @generated
     * @ordered
     */
    SOLID(19, "solid", "solid"),

    /**
     * The '<em><b>Line</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LINE_VALUE
     * @generated
     * @ordered
     */
    LINE(20, "line", "line"),

    /**
     * The '<em><b>None</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(21, "none", "none"),

    /**
     * The '<em><b>Smurf</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SMURF_VALUE
     * @generated
     * @ordered
     */
    SMURF(22, "smurf", "smurf"),

    /**
     * The '<em><b>Image</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IMAGE_VALUE
     * @generated
     * @ordered
     */
    IMAGE(23, "image", "image");

    /**
     * The '<em><b>Arrow</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Arrow</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ARROW
     * @model name="arrow"
     * @generated
     * @ordered
     */
    public static final int ARROW_VALUE = 0;

    /**
     * The '<em><b>Circle</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Circle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CIRCLE
     * @model name="circle"
     * @generated
     * @ordered
     */
    public static final int CIRCLE_VALUE = 1;

    /**
     * The '<em><b>Half Circle</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Half Circle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HALF_CIRCLE
     * @model name="halfCircle"
     * @generated
     * @ordered
     */
    public static final int HALF_CIRCLE_VALUE = 2;

    /**
     * The '<em><b>Filled Circle</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Circle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_CIRCLE
     * @model name="filledCircle"
     * @generated
     * @ordered
     */
    public static final int FILLED_CIRCLE_VALUE = 3;

    /**
     * The '<em><b>Filled Half Circle</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Half Circle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_CIRCLE
     * @model name="filledHalfCircle"
     * @generated
     * @ordered
     */
    public static final int FILLED_HALF_CIRCLE_VALUE = 4;

    /**
     * The '<em><b>Box</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Box</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BOX
     * @model name="box"
     * @generated
     * @ordered
     */
    public static final int BOX_VALUE = 5;

    /**
     * The '<em><b>Half Box</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Half Box</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HALF_BOX
     * @model name="halfBox"
     * @generated
     * @ordered
     */
    public static final int HALF_BOX_VALUE = 6;

    /**
     * The '<em><b>Filled Box</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Box</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_BOX
     * @model name="filledBox"
     * @generated
     * @ordered
     */
    public static final int FILLED_BOX_VALUE = 7;

    /**
     * The '<em><b>Filled Half Box</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Half Box</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_BOX
     * @model name="filledHalfBox"
     * @generated
     * @ordered
     */
    public static final int FILLED_HALF_BOX_VALUE = 8;

    /**
     * The '<em><b>Rhomb</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Rhomb</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RHOMB
     * @model name="rhomb"
     * @generated
     * @ordered
     */
    public static final int RHOMB_VALUE = 9;

    /**
     * The '<em><b>Half Rhomb</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Half Rhomb</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HALF_RHOMB
     * @model name="halfRhomb"
     * @generated
     * @ordered
     */
    public static final int HALF_RHOMB_VALUE = 10;

    /**
     * The '<em><b>Filled Rhomb</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Rhomb</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_RHOMB
     * @model name="filledRhomb"
     * @generated
     * @ordered
     */
    public static final int FILLED_RHOMB_VALUE = 11;

    /**
     * The '<em><b>Filled Half Rhomb</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Half Rhomb</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_RHOMB
     * @model name="filledHalfRhomb"
     * @generated
     * @ordered
     */
    public static final int FILLED_HALF_RHOMB_VALUE = 12;

    /**
     * The '<em><b>Diamond</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Diamond</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DIAMOND
     * @model name="diamond"
     * @generated
     * @ordered
     */
    public static final int DIAMOND_VALUE = 13;

    /**
     * The '<em><b>Half Diamond</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Half Diamond</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HALF_DIAMOND
     * @model name="halfDiamond"
     * @generated
     * @ordered
     */
    public static final int HALF_DIAMOND_VALUE = 14;

    /**
     * The '<em><b>Filled Diamond</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Diamond</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_DIAMOND
     * @model name="filledDiamond"
     * @generated
     * @ordered
     */
    public static final int FILLED_DIAMOND_VALUE = 15;

    /**
     * The '<em><b>Filled Half Diamond</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Filled Half Diamond</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILLED_HALF_DIAMOND
     * @model name="filledHalfDiamond"
     * @generated
     * @ordered
     */
    public static final int FILLED_HALF_DIAMOND_VALUE = 16;

    /**
     * The '<em><b>Slash</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Slash</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SLASH
     * @model name="slash"
     * @generated
     * @ordered
     */
    public static final int SLASH_VALUE = 17;

    /**
     * The '<em><b>Double Slash</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Double Slash</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOUBLE_SLASH
     * @model name="doubleSlash"
     * @generated
     * @ordered
     */
    public static final int DOUBLE_SLASH_VALUE = 18;

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
    public static final int SOLID_VALUE = 19;

    /**
     * The '<em><b>Line</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Line</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LINE
     * @model name="line"
     * @generated
     * @ordered
     */
    public static final int LINE_VALUE = 20;

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
    public static final int NONE_VALUE = 21;

    /**
     * The '<em><b>Smurf</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Smurf</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SMURF
     * @model name="smurf"
     * @generated
     * @ordered
     */
    public static final int SMURF_VALUE = 22;

    /**
     * The '<em><b>Image</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Image</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #IMAGE
     * @model name="image"
     * @generated
     * @ordered
     */
    public static final int IMAGE_VALUE = 23;

    /**
     * An array of all the '<em><b>Endpoint Styles Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final EndpointStylesType[] VALUES_ARRAY =
        new EndpointStylesType[] {
            ARROW,
            CIRCLE,
            HALF_CIRCLE,
            FILLED_CIRCLE,
            FILLED_HALF_CIRCLE,
            BOX,
            HALF_BOX,
            FILLED_BOX,
            FILLED_HALF_BOX,
            RHOMB,
            HALF_RHOMB,
            FILLED_RHOMB,
            FILLED_HALF_RHOMB,
            DIAMOND,
            HALF_DIAMOND,
            FILLED_DIAMOND,
            FILLED_HALF_DIAMOND,
            SLASH,
            DOUBLE_SLASH,
            SOLID,
            LINE,
            NONE,
            SMURF,
            IMAGE,
        };

    /**
     * A public read-only list of all the '<em><b>Endpoint Styles Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<EndpointStylesType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Endpoint Styles Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EndpointStylesType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EndpointStylesType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Endpoint Styles Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EndpointStylesType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EndpointStylesType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Endpoint Styles Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EndpointStylesType get(int value) {
        switch (value) {
            case ARROW_VALUE: return ARROW;
            case CIRCLE_VALUE: return CIRCLE;
            case HALF_CIRCLE_VALUE: return HALF_CIRCLE;
            case FILLED_CIRCLE_VALUE: return FILLED_CIRCLE;
            case FILLED_HALF_CIRCLE_VALUE: return FILLED_HALF_CIRCLE;
            case BOX_VALUE: return BOX;
            case HALF_BOX_VALUE: return HALF_BOX;
            case FILLED_BOX_VALUE: return FILLED_BOX;
            case FILLED_HALF_BOX_VALUE: return FILLED_HALF_BOX;
            case RHOMB_VALUE: return RHOMB;
            case HALF_RHOMB_VALUE: return HALF_RHOMB;
            case FILLED_RHOMB_VALUE: return FILLED_RHOMB;
            case FILLED_HALF_RHOMB_VALUE: return FILLED_HALF_RHOMB;
            case DIAMOND_VALUE: return DIAMOND;
            case HALF_DIAMOND_VALUE: return HALF_DIAMOND;
            case FILLED_DIAMOND_VALUE: return FILLED_DIAMOND;
            case FILLED_HALF_DIAMOND_VALUE: return FILLED_HALF_DIAMOND;
            case SLASH_VALUE: return SLASH;
            case DOUBLE_SLASH_VALUE: return DOUBLE_SLASH;
            case SOLID_VALUE: return SOLID;
            case LINE_VALUE: return LINE;
            case NONE_VALUE: return NONE;
            case SMURF_VALUE: return SMURF;
            case IMAGE_VALUE: return IMAGE;
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
    private EndpointStylesType(int value, String name, String literal) {
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
    
} //EndpointStylesType
