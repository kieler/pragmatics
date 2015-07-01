/*
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
package de.cau.cs.kieler.kiml.ogdf.options;

import de.cau.cs.kieler.kiml.options.Direction;

/**
 * Definition of OGDF-style direction.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum OgdfDirection {

    /** the 'south' direction. */
    SOUTH,
    /** the 'north' direction. */
    NORTH,
    /** the 'west' direction. */
    WEST,
    /** the 'east' direction. */
    EAST;
    
    /**
     * Create an OGDF direction from a KIELER direction.
     * 
     * @param direction a KIELER direction
     * @return an OGDF direction
     */
    public static OgdfDirection fromKielerDirection(final Direction direction) {
        switch (direction) {
        case DOWN:
            return SOUTH;
        case LEFT:
            return WEST;
        case RIGHT:
            return EAST;
        default:
            return NORTH;
        }
    }

}
