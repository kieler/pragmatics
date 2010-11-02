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
package de.cau.cs.kieler.keg;

import de.cau.cs.kieler.core.kgraph.KPort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.keg.Port#getPortLabel <em>Port Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.keg.GraphsPackage#getPort()
 * @model
 * @generated
 */
public interface Port extends KPort {
    /**
     * Returns the value of the '<em><b>Port Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Port Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Port Label</em>' attribute.
     * @see #setPortLabel(String)
     * @see de.cau.cs.kieler.keg.GraphsPackage#getPort_PortLabel()
     * @model
     * @generated
     */
    String getPortLabel();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.keg.Port#getPortLabel <em>Port Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Port Label</em>' attribute.
     * @see #getPortLabel()
     * @generated
     */
    void setPortLabel(String value);

} // Port
