/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.graphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Edge Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.graphs.GraphsPackage#getEdgeType()
 * @model
 * @generated
 */
public enum EdgeType implements Enumerator {
    /**
     * The '<em><b>Node2 Node</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NODE2_NODE_VALUE
     * @generated
     * @ordered
     */
    NODE2_NODE(0, "Node2Node", "Node2Node"),

    /**
     * The '<em><b>Node2 Port</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NODE2_PORT_VALUE
     * @generated
     * @ordered
     */
    NODE2_PORT(1, "Node2Port", "Node2Port"),

    /**
     * The '<em><b>Port2 Node</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PORT2_NODE_VALUE
     * @generated
     * @ordered
     */
    PORT2_NODE(2, "Port2Node", "Port2Node"),

    /**
     * The '<em><b>Port2 Port</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PORT2_PORT_VALUE
     * @generated
     * @ordered
     */
    PORT2_PORT(3, "Port2Port", "Port2Port");

    /**
     * The '<em><b>Node2 Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Node2 Node</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NODE2_NODE
     * @model name="Node2Node"
     * @generated
     * @ordered
     */
    public static final int NODE2_NODE_VALUE = 0;

    /**
     * The '<em><b>Node2 Port</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Node2 Port</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NODE2_PORT
     * @model name="Node2Port"
     * @generated
     * @ordered
     */
    public static final int NODE2_PORT_VALUE = 1;

    /**
     * The '<em><b>Port2 Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Port2 Node</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PORT2_NODE
     * @model name="Port2Node"
     * @generated
     * @ordered
     */
    public static final int PORT2_NODE_VALUE = 2;

    /**
     * The '<em><b>Port2 Port</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Port2 Port</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PORT2_PORT
     * @model name="Port2Port"
     * @generated
     * @ordered
     */
    public static final int PORT2_PORT_VALUE = 3;

    /**
     * An array of all the '<em><b>Edge Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final EdgeType[] VALUES_ARRAY =
        new EdgeType[] {
            NODE2_NODE,
            NODE2_PORT,
            PORT2_NODE,
            PORT2_PORT,
        };

    /**
     * A public read-only list of all the '<em><b>Edge Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<EdgeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Edge Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EdgeType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EdgeType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EdgeType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EdgeType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EdgeType get(int value) {
        switch (value) {
            case NODE2_NODE_VALUE: return NODE2_NODE;
            case NODE2_PORT_VALUE: return NODE2_PORT;
            case PORT2_NODE_VALUE: return PORT2_NODE;
            case PORT2_PORT_VALUE: return PORT2_PORT;
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
    private EdgeType(int value, String name, String literal) {
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
    
} //EdgeType
