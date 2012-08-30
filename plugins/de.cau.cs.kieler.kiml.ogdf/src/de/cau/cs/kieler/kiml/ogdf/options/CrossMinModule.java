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
package de.cau.cs.kieler.kiml.ogdf.options;

import net.ogdf.bin.OgdfServer;

/**
 * Enumeration of available crossing minimization modules for Sugiyama layout.
 * 
 * @author msp
 */
public enum CrossMinModule {
    
    /** barycenter crossing minimization module. */
    BARYCENTER,
    /** greedy insert crossing minimization module. */
    GREEDY_INSERT,
    /** greedy switch crossing minimization module. */
    GREEDY_SWITCH,
    /** median crossing minimization module. */
    MEDIAN,
    /** sifting crossing minimization module. */
    SIFTING;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case GREEDY_INSERT:
            return OgdfServer.CROSS_MIN_GREEDY_INSERT;
        case GREEDY_SWITCH:
            return OgdfServer.CROSS_MIN_GREEDY_SWITCH;
        case MEDIAN:
            return OgdfServer.CROSS_MIN_MEDIAN;
        case SIFTING:
            return OgdfServer.CROSS_MIN_SIFTING;
        case BARYCENTER:
        default:
            return OgdfServer.CROSS_MIN_BARYCENTER;
        }
    }

}
