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
package de.cau.cs.kieler.klay.layered.components;

/**
 * The nine sectors in a {@link ComponentGroup}.
 * 
 * @author cds
 */
enum ComponentGroupSector {
    /**
     * The north western corner. Must only contain at most one component.
     */
    NORTH_WEST,
    
    /**
     * The northern sector.
     */
    NORTH,

    /**
     * The north eastern corner. Must only contain at most one component.
     */
    NORTH_EAST,
    
    /**
     * The western sector.
     */
    WEST,
    
    /**
     * The center sector.
     */
    CENTER,
    
    /**
     * The eastern sector.
     */
    EAST,

    /**
     * The south western corner. Must only contain at most one component.
     */
    SOUTH_WEST,
    
    /**
     * The southern sector.
     */
    SOUTH,

    /**
     * The south eastern corner. Must only contain at most one component.
     */
    SOUTH_EAST;
}
