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
 * Definition of the "quality vs. speed" option for the FMMM layout algorithm.
 * 
 * @author mri
 */
public enum QualityVsSpeed {
    
    /** best quality, worst speed. */
    GORGEOUSANDEFFICIENT,
    /** medium quality, medium speed. */
    BEAUTIFULANDFAST,
    /** worst quality, best speed. */
    NICEANDINCREDIBLESPEED;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case GORGEOUSANDEFFICIENT:
            return OgdfServer.GORGEOUS_AND_EFFICIENT;
        case NICEANDINCREDIBLESPEED:
            return OgdfServer.NICE_AND_INCREDIBLE_SPEED;
        case BEAUTIFULANDFAST:
        default:
            return OgdfServer.BEAUTIFUL_AND_FAST;
        }
    }
    
}
