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

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.graphs.Node#getNodeLabel <em>Node Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.graphs.Node#getIsHypernode <em>Is Hypernode</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.graphs.GraphsPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends KNode {
    /**
     * Returns the value of the '<em><b>Node Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Label</em>' attribute.
     * @see #setNodeLabel(String)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getNode_NodeLabel()
     * @model required="true"
     * @generated
     */
    String getNodeLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Node#getNodeLabel <em>Node Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Label</em>' attribute.
     * @see #getNodeLabel()
     * @generated
     */
    void setNodeLabel(String value);

    /**
     * Returns the value of the '<em><b>Is Hypernode</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Hypernode</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Hypernode</em>' attribute.
     * @see #setIsHypernode(Boolean)
     * @see de.cau.cs.kieler.graphs.GraphsPackage#getNode_IsHypernode()
     * @model default="false" required="true"
     * @generated
     */
    Boolean getIsHypernode();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.graphs.Node#getIsHypernode <em>Is Hypernode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Hypernode</em>' attribute.
     * @see #getIsHypernode()
     * @generated
     */
    void setIsHypernode(Boolean value);

} // Node
