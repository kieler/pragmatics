/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Definition of port sides on a node. Besides defining the actual available port sides, this enumeration
 * also defines convenience methods as well as constants with all possible port side combinations. The
 * latter are named in a clockwise manner, starting at the northern side.
 * 
 * @author msp
 * @author cds
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating yellow 2013-01-09 review KI-32 by ckru, chsch
 */
public enum PortSide {
    
    /** the side is undefined. */
    UNDEFINED,
    /** top side. */
    NORTH,
    /** right side. */
    EAST,
    /** bottom side. */
    SOUTH,
    /** left side. */
    WEST;
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Port Side Combinations

    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NONE = Collections.unmodifiableSet(
            EnumSet.noneOf(PortSide.class));
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH = Sets.immutableEnumSet(
            PortSide.NORTH);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_EAST = Sets.immutableEnumSet(
            PortSide.EAST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_SOUTH = Sets.immutableEnumSet(
            PortSide.SOUTH);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_WEST = Sets.immutableEnumSet(
            PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_SOUTH = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.SOUTH);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_EAST_WEST = Sets.immutableEnumSet(
            PortSide.EAST, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_WEST = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_EAST = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.EAST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_SOUTH_WEST = Sets.immutableEnumSet(
            PortSide.SOUTH, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_EAST_SOUTH = Sets.immutableEnumSet(
            PortSide.EAST, PortSide.SOUTH);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_EAST_WEST = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.EAST, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_EAST_SOUTH_WEST = Sets.immutableEnumSet(
            PortSide.EAST, PortSide.SOUTH, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_SOUTH_WEST = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.SOUTH, PortSide.WEST);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_EAST_SOUTH = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.EAST, PortSide.SOUTH);
    /** Immutable set containing the given port sides. */
    public static final Set<PortSide> SIDES_NORTH_EAST_SOUTH_WEST = Sets.immutableEnumSet(
            PortSide.NORTH, PortSide.EAST, PortSide.SOUTH, PortSide.WEST);
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Convenience Methods
    
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
    
    /**
     * Get the port side that corresponds to the given direction.
     * 
     * @param direction a direction
     * @return the corresponding port side
     */
    public static PortSide fromDirection(final Direction direction) {
        switch (direction) {
        case UP:
            return NORTH;
        case RIGHT:
            return EAST;
        case DOWN:
            return SOUTH;
        case LEFT:
            return WEST;
        default:
            return UNDEFINED;
        }
    }
    
}
