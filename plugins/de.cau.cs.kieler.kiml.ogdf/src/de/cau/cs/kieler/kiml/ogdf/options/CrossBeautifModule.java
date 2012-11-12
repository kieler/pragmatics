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
 * Definition of crossing beautifier modules for mixed model layout.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum CrossBeautifModule {
    
    /** no beautification of crossings. */
    NONE,
    /** beautification of crossings using grid doubling. */
    DOUBLE_GRID,
    /** beautification of crossings using local stretch. */
    LOCAL_STRETCH;

}
