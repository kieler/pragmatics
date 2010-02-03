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
package de.cau.cs.kieler.kiml.layout.options;

/**
 * Definition of edge routing styles.
 * 
 * @kieler.rating 2010-02-03 proposed yellow msp
 * @author msp
 */
public enum EdgeRouting {

    /** undefined edge routing. */
    UNDEFINED,
    /** polyline edge routing. */
    POLYLINE,
    /** orthogonal edge routing. */
    ORTHOGONAL,
    /** splines edge routing. */
    SPLINES;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static EdgeRouting valueOf(final int i) {
        return values()[i];
    }
    
}
