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
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Definition of orientation of a layouter.
 * 
 * @author mri
 */
public enum Orientation {
    /** top-to-bottom. */
    TOP_TO_BOTTOM,
    /** bottom-to-top. */
    BOTTOM_TO_TOP,
    /** left-to-right. */
    LEFT_TO_RIGHT,
    /** right-to-left. */
    RIGHT_TO_LEFT;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static Orientation valueOf(final int i) {
        return values()[i];
    }
}
