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

/**
 * Definition of layout directions. To be accessed using {@link LayoutOptions#DIRECTION}.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating yellow 2013-01-09 review KI-32 by ckru, chsch
 * @author msp
 */
public enum Direction {

    /** undefined layout direction. */
    UNDEFINED,
    /** rightward horizontal layout. */
    RIGHT,
    /** leftward horizontal layout. */
    LEFT,
    /** downward vertical layout. */
    DOWN,
    /** upward vertical layout. */
    UP;
    
    
    /**
     * Checks if this layout direction is horizontal. (that is, left or right) An undefined layout
     * direction is not horizontal.
     * 
     * @return {@code trure} if the layout direction is horizontal.
     */
    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }

    /**
     * Checks if this layout direction is vertical. (that is, up or down) An undefined layout
     * direction is not vertical.
     * 
     * @return {@code trure} if the layout direction is vertical.
     */
    public boolean isVertical() {
        return this == UP || this == DOWN;
    }
    
    /**
     * @return the opposite direction of {@code this}. For instance, if this is {@link #LEFT},
     *         return {@link #RIGHT}.
     */
    public Direction opposite() {
        switch (this) {
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        default:
            return UNDEFINED;
        }
    }
}
