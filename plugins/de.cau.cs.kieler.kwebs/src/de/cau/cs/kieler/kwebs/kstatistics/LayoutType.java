/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Layout Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The type of the layout statistic.
 * <!-- end-model-doc -->
 * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getLayoutType()
 * @model
 * @generated
 */
public enum LayoutType implements Enumerator {
    /**
     * The '<em><b>Local Layout</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOCAL_LAYOUT_VALUE
     * @generated
     * @ordered
     */
    LOCAL_LAYOUT(0, "LocalLayout", "LocalLayout"),

    /**
     * The '<em><b>Remote Layout</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #REMOTE_LAYOUT_VALUE
     * @generated
     * @ordered
     */
    REMOTE_LAYOUT(1, "RemoteLayout", "RemoteLayout");

    /**
     * The '<em><b>Local Layout</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Local Layout</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The value representing a local layout statistic.
     * <!-- end-model-doc -->
     * @see #LOCAL_LAYOUT
     * @model name="LocalLayout"
     * @generated
     * @ordered
     */
    public static final int LOCAL_LAYOUT_VALUE = 0;

    /**
     * The '<em><b>Remote Layout</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Remote Layout</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The value representing a remote layout statistic.
     * <!-- end-model-doc -->
     * @see #REMOTE_LAYOUT
     * @model name="RemoteLayout"
     * @generated
     * @ordered
     */
    public static final int REMOTE_LAYOUT_VALUE = 1;

    /**
     * An array of all the '<em><b>Layout Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LayoutType[] VALUES_ARRAY =
        new LayoutType[] {
            LOCAL_LAYOUT,
            REMOTE_LAYOUT,
        };

    /**
     * A public read-only list of all the '<em><b>Layout Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LayoutType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Layout Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LayoutType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LayoutType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LayoutType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LayoutType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LayoutType get(int value) {
        switch (value) {
            case LOCAL_LAYOUT_VALUE: return LOCAL_LAYOUT;
            case REMOTE_LAYOUT_VALUE: return REMOTE_LAYOUT;
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
    private LayoutType(int value, String name, String literal) {
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
    
} //LayoutType
