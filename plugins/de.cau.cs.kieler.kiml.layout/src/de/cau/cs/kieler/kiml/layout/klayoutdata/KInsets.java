/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.layout.klayoutdata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Insets</b></em>'. Insets describe
 * the minimal amount of space that is required between the children of a node
 * and the node's boundary.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getTop <em>Top</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getBottom <em>Bottom</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getLeft <em>Left</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKInsets()
 * @model
 * @generated
 */
public interface KInsets extends EObject {
    /**
     * Returns the value of the '<em><b>Top</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * This is the minimal distance to the top side of a parent node.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Top</em>' attribute.
     * @see #setTop(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKInsets_Top()
     * @model
     * @generated
     */
    float getTop();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getTop <em>Top</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Top</em>' attribute.
     * @see #getTop()
     * @generated
     */
    void setTop(float value);

    /**
     * Returns the value of the '<em><b>Bottom</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * This is the minimal distance to the bottom side of a parent node.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bottom</em>' attribute.
     * @see #setBottom(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKInsets_Bottom()
     * @model
     * @generated
     */
    float getBottom();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getBottom <em>Bottom</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bottom</em>' attribute.
     * @see #getBottom()
     * @generated
     */
    void setBottom(float value);

    /**
     * Returns the value of the '<em><b>Left</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * This is the minimal distance to the left side of a parent node.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left</em>' attribute.
     * @see #setLeft(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKInsets_Left()
     * @model
     * @generated
     */
    float getLeft();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getLeft <em>Left</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left</em>' attribute.
     * @see #getLeft()
     * @generated
     */
    void setLeft(float value);

    /**
     * Returns the value of the '<em><b>Right</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * This is the minimal distance to the right side of a parent node.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right</em>' attribute.
     * @see #setRight(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKInsets_Right()
     * @model
     * @generated
     */
    float getRight();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets#getRight <em>Right</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right</em>' attribute.
     * @see #getRight()
     * @generated
     */
    void setRight(float value);

} // KInsets
