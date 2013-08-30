/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KColor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specifies an RGB color. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColor#getRed <em>Red</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColor#getGreen <em>Green</em>}</li>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KColor#getBlue <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColor()
 * @model
 * @generated
 */
public interface KColor extends EObject {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Red</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the red component of a color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColor_Red()
     * @model default="0" required="true"
     * @generated
     */
    int getRed();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColor#getRed <em>Red</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(int value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Green</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the green component of a color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColor_Green()
     * @model default="0" required="true"
     * @generated
     */
    int getGreen();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColor#getGreen <em>Green</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(int value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blue</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the blue component of a color
     * <!-- end-model-doc -->
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(int)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKColor_Blue()
     * @model default="0" required="true"
     * @generated
     */
    int getBlue();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KColor#getBlue <em>Blue</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(int value);

} // KColor
