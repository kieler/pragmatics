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
 * A representation of the literals of the enumeration '<em><b>Graph Order Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *       Simple type for the parse.order attribute of &lt;graph&gt;.
 *       graph.order.type is final, that is, it may not be extended
 *                           or restricted.
 *       graph.order.type is a restriction of xs:NMTOKEN
 *       Allowed values: free, nodesfirst, adjacencylist.
 *     
 * <!-- end-model-doc -->
 * @see org.graphdrawing.graphml.GraphMLPackage#getGraphOrderType()
 * @model extendedMetaData="name='graph.order.type'"
 * @generated
 */
public enum GraphOrderType implements Enumerator {
    /**
     * The '<em><b>Free</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FREE_VALUE
     * @generated
     * @ordered
     */
    FREE(0, "free", "free"),

    /**
     * The '<em><b>Nodesfirst</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NODESFIRST_VALUE
     * @generated
     * @ordered
     */
    NODESFIRST(1, "nodesfirst", "nodesfirst"),

    /**
     * The '<em><b>Adjacencylist</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ADJACENCYLIST_VALUE
     * @generated
     * @ordered
     */
    ADJACENCYLIST(2, "adjacencylist", "adjacencylist");

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
    public static final int FREE_VALUE = 0;

    /**
     * The '<em><b>Nodesfirst</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Nodesfirst</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NODESFIRST
     * @model name="nodesfirst"
     * @generated
     * @ordered
     */
    public static final int NODESFIRST_VALUE = 1;

    /**
     * The '<em><b>Adjacencylist</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Adjacencylist</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ADJACENCYLIST
     * @model name="adjacencylist"
     * @generated
     * @ordered
     */
    public static final int ADJACENCYLIST_VALUE = 2;

    /**
     * An array of all the '<em><b>Graph Order Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final GraphOrderType[] VALUES_ARRAY =
        new GraphOrderType[] {
            FREE,
            NODESFIRST,
            ADJACENCYLIST,
        };

    /**
     * A public read-only list of all the '<em><b>Graph Order Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<GraphOrderType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Graph Order Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphOrderType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            GraphOrderType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Graph Order Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphOrderType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            GraphOrderType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Graph Order Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphOrderType get(int value) {
        switch (value) {
            case FREE_VALUE: return FREE;
            case NODESFIRST_VALUE: return NODESFIRST;
            case ADJACENCYLIST_VALUE: return ADJACENCYLIST;
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
    private GraphOrderType(int value, String name, String literal) {
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
    
} //GraphOrderType
