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

import net.ogdf.bin.OgdfServer;

/**
 * Definition of the costs option for the Davidson-Harel layouter.
 * 
 * @author mri
 */
public enum Costs {
    
    /** standard costs. */
    STANDARD,
    /** repulse costs. */
    REPULSE,
    /** planar costs. */
    PLANAR;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case REPULSE:
            return OgdfServer.COSTS_REPULSE;
        case PLANAR:
            return OgdfServer.COSTS_PLANAR;
        case STANDARD:
        default:
            return OgdfServer.COSTS_STANDARD;
        }
    }
    
}
