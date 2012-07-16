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
package de.cau.cs.kieler.klay.layered.p3order;

/**
 * Definition of available crossing minimization strategies for the layered layouter.
 * 
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public enum CrossingMinimizationStrategy {

    /** A heuristic that sweeps through the layers trying to minimize the crossings locally. */
    LAYER_SWEEP,
    /** Allow user interaction by considering the previous node positioning. */
    INTERACTIVE;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static CrossingMinimizationStrategy valueOf(final int i) {
        return values()[i];
    }

}
