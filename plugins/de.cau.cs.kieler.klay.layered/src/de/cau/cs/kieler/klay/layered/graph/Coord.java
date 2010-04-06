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
package de.cau.cs.kieler.klay.layered.graph;

/**
 * A coordinate vector may be used to specify a position or a size.
 *
 * @author msp
 */
public class Coord {
    
    // CHECKSTYLEOFF VisibilityModifier
    
    /** the x coordinate value. */
    public float x;
    /** the y coordinate value. */
    public float y;
    
    /**
     * Creates a coordinate vector with x and y both zero.
     */
    public Coord() {
    }
    
    /**
     * Creates a coordinate vector with initialized values.
     * 
     * @param thex the x coordinate value
     * @param they the y coordinate value
     */
    public Coord(final float thex, final float they) {
        this.x = thex;
        this.y = they;
    }

}
