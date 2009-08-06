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
package de.cau.cs.kieler.kiml.ui.layout.layoutoptions;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;

import org.eclipse.emf.common.util.EList;

import org.eclipse.gmf.runtime.notation.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout Option Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage#getLayoutOptionStyle()
 * @model
 * @generated
 */
public interface LayoutOptionStyle extends Style {
    /**
     * Returns the value of the '<em><b>Options</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Options</em>' containment reference list.
     * @see de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage#getLayoutOptionStyle_Options()
     * @model containment="true"
     * @generated
     */
    EList<KOption> getOptions();

} // LayoutOptionStyle
