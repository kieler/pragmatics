/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.krendering;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * References an already defined rendering to make redefining unneccessary. 
 * Be careful to generate no cycles (will cause stack overflow). 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.core.krendering.KRenderingRef#getRendering <em>Rendering</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRenderingRef()
 * @model
 * @generated
 */
public interface KRenderingRef extends KRendering {
    /**
     * Returns the value of the '<em><b>Rendering</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rendering</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the referenced rendering
     * <!-- end-model-doc -->
     * @return the value of the '<em>Rendering</em>' reference.
     * @see #setRendering(KRendering)
     * @see de.cau.cs.kieler.core.krendering.KRenderingPackage#getKRenderingRef_Rendering()
     * @model required="true"
     * @generated
     */
    KRendering getRendering();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.core.krendering.KRenderingRef#getRendering <em>Rendering</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rendering</em>' reference.
     * @see #getRendering()
     * @generated
     */
    void setRendering(KRendering value);

} // KRenderingRef
