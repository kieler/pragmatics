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
 */
package de.cau.cs.kieler.keg;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.Node#getNodeLabel <em>Node Label</em>}</li>
 *   <li>{@link de.cau.cs.kieler.keg.Node#isHypernode <em>Hypernode</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.keg.KEGPackage#getNode()
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
     * @see de.cau.cs.kieler.keg.KEGPackage#getNode_NodeLabel()
     * @model required="true"
     * @generated
     */
    String getNodeLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Node#getNodeLabel <em>Node Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Label</em>' attribute.
     * @see #getNodeLabel()
     * @generated
     */
    void setNodeLabel(String value);

    /**
     * Returns the value of the '<em><b>Hypernode</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Hypernode</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Hypernode</em>' attribute.
     * @see #setHypernode(boolean)
     * @see de.cau.cs.kieler.keg.KEGPackage#getNode_Hypernode()
     * @model default="false" required="true"
     * @generated
     */
    boolean isHypernode();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Node#isHypernode <em>Hypernode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hypernode</em>' attribute.
     * @see #isHypernode()
     * @generated
     */
    void setHypernode(boolean value);

} // Node
