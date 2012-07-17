/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes;

/**
 * Definition of the available node placement strategies for the layered layout approach.
 *
 * @author jjc
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public enum NodePlacementStrategy {
    
    /** Node placement implementation that aligns long edges using linear segments. */
    LINEAR_SEGMENTS,
    /** Node placement which groups nodes to blocks which result in straight edges. */
    BRANDES_KOEPF;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static NodePlacementStrategy valueOf(final int i) {
        return values()[i];
    }

}
