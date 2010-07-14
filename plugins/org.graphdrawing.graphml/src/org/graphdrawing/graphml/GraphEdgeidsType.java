/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Graph Edgeids Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *       Simple type for the parse.edgeids attribute of &lt;graph&gt;.
 *       graph.edgeids.type is final, that is, it may not be extended
 *                           or restricted.
 *       graph.edgeids.type is a restriction of xs:string
 *       Allowed values: (no restriction).
 *     
 * <!-- end-model-doc -->
 * @see org.graphdrawing.graphml.GraphMLPackage#getGraphEdgeidsType()
 * @model extendedMetaData="name='graph.edgeids.type'"
 * @generated
 */
public enum GraphEdgeidsType implements Enumerator {
    /**
     * The '<em><b>Canonical</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CANONICAL_VALUE
     * @generated
     * @ordered
     */
    CANONICAL(0, "canonical", "canonical"),

    /**
     * The '<em><b>Free</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FREE_VALUE
     * @generated
     * @ordered
     */
    FREE(1, "free", "free");

    /**
     * The '<em><b>Canonical</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Canonical</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CANONICAL
     * @model name="canonical"
     * @generated
     * @ordered
     */
    public static final int CANONICAL_VALUE = 0;

    /**
     * The '<em><b>Free</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Free</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FREE
     * @model name="free"
     * @generated
     * @ordered
     */
    public static final int FREE_VALUE = 1;

    /**
     * An array of all the '<em><b>Graph Edgeids Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final GraphEdgeidsType[] VALUES_ARRAY =
        new GraphEdgeidsType[] {
            CANONICAL,
            FREE,
        };

    /**
     * A public read-only list of all the '<em><b>Graph Edgeids Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<GraphEdgeidsType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Graph Edgeids Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphEdgeidsType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            GraphEdgeidsType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Graph Edgeids Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphEdgeidsType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            GraphEdgeidsType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Graph Edgeids Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphEdgeidsType get(int value) {
        switch (value) {
            case CANONICAL_VALUE: return CANONICAL;
            case FREE_VALUE: return FREE;
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
    private GraphEdgeidsType(int value, String name, String literal) {
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
    
} //GraphEdgeidsType
