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
package de.cau.cs.kieler.klay.layered.p1cycles;

/**
 * Definition of available cycle breaking strategies for the layered layouter.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum CycleBreakingStrategy {

    /** Applies a greedy heuristic to minimize the number of reversed edges. */
    GREEDY,
    /** Reacts on user interaction by respecting initial node positions. */
    INTERACTIVE;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static CycleBreakingStrategy valueOf(final int i) {
        return values()[i];
    }

}
