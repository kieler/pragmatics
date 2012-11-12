/*
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
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Enumeration of available crossing minimization modules for Sugiyama layout.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum CrossMinModule {
    
    /** barycenter crossing minimization module. */
    BARYCENTER,
    /** greedy insert crossing minimization module. */
    GREEDY_INSERT,
    /** greedy switch crossing minimization module. */
    GREEDY_SWITCH,
    /** median crossing minimization module. */
    MEDIAN,
    /** sifting crossing minimization module. */
    SIFTING;

}
