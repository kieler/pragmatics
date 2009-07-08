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

import de.cau.cs.kieler.core.kgraph.KGraphData;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout Data</b></em>'. Layout
 * data can be attached to any
 * {@linkplain de.cau.cs.kieler.core.kgraph.KGraphElement graph element} and may
 * contain layout options to customize the behavior of layout algorithms.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKLayoutData()
 * @model abstract="true"
 * @generated
 */
public interface KLayoutData extends KGraphData {
    /**
     * Returns the value of the '<em><b>Options</b></em>' containment reference list.
     * The list contents are of type {@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption}.
     * <!-- begin-user-doc -->
     * <p>
     * Layout options specify how a layout algorithm shall handle the related
     * graph element or its children. Options are defined in
     * {@link de.cau.cs.kieler.kiml.layout.options.LayoutOptions LayoutOptions}
     * and consist either of a key-value pair for valued options or of an
     * unvalued {@code KOption} for boolean options, in which case the default
     * is implicitly 'false'.
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Options</em>' containment reference list.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage#getKLayoutData_Options()
     * @model containment="true"
     * @generated
     */
    EList<KOption> getOptions();

    /**
     * <!-- begin-user-doc -->
     * Retrieves the first option of given key in the list of layout options.
     * @return layout option for the given key, or null if there is none 
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    KOption getOption(String key);

} // KLayoutData
