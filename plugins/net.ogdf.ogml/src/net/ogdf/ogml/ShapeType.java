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
 * A representation of the literals of the enumeration '<em><b>Shape Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage#getShapeType()
 * @model extendedMetaData="name='shape.type'"
 * @generated
 */
public enum ShapeType implements Enumerator {
    /**
     * The '<em><b>Rect</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RECT_VALUE
     * @generated
     * @ordered
     */
    RECT(0, "rect", "rect"),

    /**
     * The '<em><b>Triangle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TRIANGLE_VALUE
     * @generated
     * @ordered
     */
    TRIANGLE(1, "triangle", "triangle"),

    /**
     * The '<em><b>Circle</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CIRCLE_VALUE
     * @generated
     * @ordered
     */
    CIRCLE(2, "circle", "circle"),

    /**
     * The '<em><b>Ellipse</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ELLIPSE_VALUE
     * @generated
     * @ordered
     */
    ELLIPSE(3, "ellipse", "ellipse"),

    /**
     * The '<em><b>Hexagon</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HEXAGON_VALUE
     * @generated
     * @ordered
     */
    HEXAGON(4, "hexagon", "hexagon"),

    /**
     * The '<em><b>Rhomb</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RHOMB_VALUE
     * @generated
     * @ordered
     */
    RHOMB(5, "rhomb", "rhomb"),

    /**
     * The '<em><b>Trapeze</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TRAPEZE_VALUE
     * @generated
     * @ordered
     */
    TRAPEZE(6, "trapeze", "trapeze"),

    /**
     * The '<em><b>Up Trapeze</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UP_TRAPEZE_VALUE
     * @generated
     * @ordered
     */
    UP_TRAPEZE(7, "upTrapeze", "upTrapeze"),

    /**
     * The '<em><b>LParallelogram</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LPARALLELOGRAM_VALUE
     * @generated
     * @ordered
     */
    LPARALLELOGRAM(8, "lParallelogram", "lParallelogram"),

    /**
     * The '<em><b>RParallelogram</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RPARALLELOGRAM_VALUE
     * @generated
     * @ordered
     */
    RPARALLELOGRAM(9, "rParallelogram", "rParallelogram"),

    /**
     * The '<em><b>Pentagon</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PENTAGON_VALUE
     * @generated
     * @ordered
     */
    PENTAGON(10, "pentagon", "pentagon"),

    /**
     * The '<em><b>Octagon</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OCTAGON_VALUE
     * @generated
     * @ordered
     */
    OCTAGON(11, "octagon", "octagon"),

    /**
     * The '<em><b>Uml Class</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UML_CLASS_VALUE
     * @generated
     * @ordered
     */
    UML_CLASS(12, "umlClass", "umlClass"),

    /**
     * The '<em><b>Image</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IMAGE_VALUE
     * @generated
     * @ordered
     */
    IMAGE(13, "image", "image");

    /**
     * The '<em><b>Rect</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Rect</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RECT
     * @model name="rect"
     * @generated
     * @ordered
     */
    public static final int RECT_VALUE = 0;

    /**
     * The '<em><b>Triangle</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Triangle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TRIANGLE
     * @model name="triangle"
     * @generated
     * @ordered
     */
    public static final int TRIANGLE_VALUE = 1;

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
    public static final int CIRCLE_VALUE = 2;

    /**
     * The '<em><b>Ellipse</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ellipse</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ELLIPSE
     * @model name="ellipse"
     * @generated
     * @ordered
     */
    public static final int ELLIPSE_VALUE = 3;

    /**
     * The '<em><b>Hexagon</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Hexagon</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HEXAGON
     * @model name="hexagon"
     * @generated
     * @ordered
     */
    public static final int HEXAGON_VALUE = 4;

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
    public static final int RHOMB_VALUE = 5;

    /**
     * The '<em><b>Trapeze</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Trapeze</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TRAPEZE
     * @model name="trapeze"
     * @generated
     * @ordered
     */
    public static final int TRAPEZE_VALUE = 6;

    /**
     * The '<em><b>Up Trapeze</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Up Trapeze</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UP_TRAPEZE
     * @model name="upTrapeze"
     * @generated
     * @ordered
     */
    public static final int UP_TRAPEZE_VALUE = 7;

    /**
     * The '<em><b>LParallelogram</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LParallelogram</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LPARALLELOGRAM
     * @model name="lParallelogram"
     * @generated
     * @ordered
     */
    public static final int LPARALLELOGRAM_VALUE = 8;

    /**
     * The '<em><b>RParallelogram</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>RParallelogram</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RPARALLELOGRAM
     * @model name="rParallelogram"
     * @generated
     * @ordered
     */
    public static final int RPARALLELOGRAM_VALUE = 9;

    /**
     * The '<em><b>Pentagon</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Pentagon</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PENTAGON
     * @model name="pentagon"
     * @generated
     * @ordered
     */
    public static final int PENTAGON_VALUE = 10;

    /**
     * The '<em><b>Octagon</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Octagon</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OCTAGON
     * @model name="octagon"
     * @generated
     * @ordered
     */
    public static final int OCTAGON_VALUE = 11;

    /**
     * The '<em><b>Uml Class</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Uml Class</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UML_CLASS
     * @model name="umlClass"
     * @generated
     * @ordered
     */
    public static final int UML_CLASS_VALUE = 12;

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
    public static final int IMAGE_VALUE = 13;

    /**
     * An array of all the '<em><b>Shape Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ShapeType[] VALUES_ARRAY =
        new ShapeType[] {
            RECT,
            TRIANGLE,
            CIRCLE,
            ELLIPSE,
            HEXAGON,
            RHOMB,
            TRAPEZE,
            UP_TRAPEZE,
            LPARALLELOGRAM,
            RPARALLELOGRAM,
            PENTAGON,
            OCTAGON,
            UML_CLASS,
            IMAGE,
        };

    /**
     * A public read-only list of all the '<em><b>Shape Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<ShapeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Shape Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ShapeType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ShapeType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Shape Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ShapeType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ShapeType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Shape Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ShapeType get(int value) {
        switch (value) {
            case RECT_VALUE: return RECT;
            case TRIANGLE_VALUE: return TRIANGLE;
            case CIRCLE_VALUE: return CIRCLE;
            case ELLIPSE_VALUE: return ELLIPSE;
            case HEXAGON_VALUE: return HEXAGON;
            case RHOMB_VALUE: return RHOMB;
            case TRAPEZE_VALUE: return TRAPEZE;
            case UP_TRAPEZE_VALUE: return UP_TRAPEZE;
            case LPARALLELOGRAM_VALUE: return LPARALLELOGRAM;
            case RPARALLELOGRAM_VALUE: return RPARALLELOGRAM;
            case PENTAGON_VALUE: return PENTAGON;
            case OCTAGON_VALUE: return OCTAGON;
            case UML_CLASS_VALUE: return UML_CLASS;
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
    private ShapeType(int value, String name, String literal) {
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
    
} //ShapeType
