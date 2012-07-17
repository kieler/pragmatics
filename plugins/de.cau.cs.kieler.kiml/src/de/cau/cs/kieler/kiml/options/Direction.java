/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kiml.options;

/**
 * Definition of layout directions.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating 2012-07-10 proposed yellow msp
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
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static Direction valueOf(final int i) {
        return values()[i];
    }
    
}
