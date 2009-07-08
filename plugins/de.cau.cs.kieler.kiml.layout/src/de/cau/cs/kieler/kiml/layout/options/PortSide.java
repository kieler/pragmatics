/******************************************************************************
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
 */
package de.cau.cs.kieler.kiml.layout.options;

/**
 * Definition of port sides on a node.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public enum PortSide {
    
    /** the side is undefined */
    UNDEFINED,
    /** top side */
    NORTH,
    /** right side */
    EAST,
    /** bottom side */
    SOUTH,
    /** left side */
    WEST;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static PortSide valueOf(int i) {
        return values()[i];
    }
    
    /**
     * Returns the next side in clockwise order.
     * 
     * @return the next side in clockwise order
     */
    public PortSide right() {
        switch (this) {
        case NORTH:
            return EAST;
        case EAST:
            return SOUTH;
        case SOUTH:
            return WEST;
        case WEST:
            return NORTH;
        default:
            return UNDEFINED;
        }
    }
    
    /**
     * Returns the next side in counter-clockwise order.
     * 
     * @return the next side in counter-clockwise order
     */
    public PortSide left() {
        switch (this) {
        case NORTH:
            return WEST;
        case EAST:
            return NORTH;
        case SOUTH:
            return EAST;
        case WEST:
            return SOUTH;
        default:
            return UNDEFINED;
        }
    }
    
    /**
     * Returns the opposed side.
     * 
     * @return the opposed side
     */
    public PortSide opposed() {
        switch (this) {
        case NORTH:
            return SOUTH;
        case EAST:
            return WEST;
        case SOUTH:
            return NORTH;
        case WEST:
            return EAST;
        default:
            return UNDEFINED;
        }
    }
    
}
