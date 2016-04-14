/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf.options;

import org.eclipse.elk.core.options.Direction;

/**
 * Definition of orientation of a layouter.
 * 
 * @author mri
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
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
     * Create an OGDF orientation from a layout direction.
     * 
     * @param direction a layout direction
     * @return the corresponding orientation
     */
    public static Orientation fromDirection(final Direction direction) {
        switch (direction) {
        case LEFT:
            return RIGHT_TO_LEFT;
        case UP:
            return TOP_TO_BOTTOM;
        case DOWN:
            return BOTTOM_TO_TOP;
        default:
            return LEFT_TO_RIGHT;
        }
    }
    
}
