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
 * A representation of the model object '<em><b>Point</b></em>'. A point has
 * an x and a y coordinate.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getX <em>X</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKPoint()
 * @model
 * @generated
 */
public interface KPoint extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKPoint_X()
     * @model default="0.0f"
     * @generated
     */
    float getX();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(float value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * The default value is <code>"0.0f"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(float)
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKPoint_Y()
     * @model default="0.0f"
     * @generated
     */
    float getY();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(float value);

} // KPoint
