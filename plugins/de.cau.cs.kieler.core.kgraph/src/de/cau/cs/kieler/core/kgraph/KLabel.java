/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.core.kgraph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label</b></em>'. Each label
 * must be assigned a parent graph element and a text string.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KLabel#getText <em>Text</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.kgraph.KLabel#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKLabel()
 * @model
 * @generated
 */
public interface KLabel extends KGraphElement {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKLabel_Text()
     * @model required="true"
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KLabel#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * Each label is related to a specific graph element, such as a node,
     * an edge, or a port.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(KGraphElement)
     * @see de.cau.cs.kieler.core.kgraph.KGraphPackage#getKLabel_Parent()
     * @model required="true"
     * @generated
     */
    KGraphElement getParent();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.kgraph.KLabel#getParent <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(KGraphElement value);

} // KLabel
