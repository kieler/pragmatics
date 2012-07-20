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
package de.cau.cs.kieler.kiml.options;

/**
 * Definition of the edge types.
 * 
 * @kieler.design 2011-03-14 reviewed by cmot, cds
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @author mri
 */
public enum EdgeType {
    
    /** no special type. */
    NONE,
    /** the edge represents an association. */
    ASSOCIATION,
    /** the edge represents a generalization. */
    GENERALIZATION,
    /** the edge represents a dependency. */
    DEPENDENCY;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static EdgeType valueOf(final int i) {
        return values()[i];
    }
    
}
