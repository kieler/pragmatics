/*
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
 */
package de.cau.cs.kieler.klodd.hierarchical;

/**
 * Definition of possible interaction levels.
 *
 * @author msp
 */
public enum InteractionLevel {
    
    /** no user interaction. */
    NONE,
    /** user positioning is considered for cycle breaking. */
    CYCLES,
    /** user positioning is considered for cycle breaking and layering. */
    LAYERS,
    /** user positioning is considered for node ordering. */
    ORDERING,
    /** full user interaction. */
    FULL;

}
