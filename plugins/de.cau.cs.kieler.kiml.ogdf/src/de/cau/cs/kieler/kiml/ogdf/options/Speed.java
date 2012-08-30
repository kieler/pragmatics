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
 * Definition of the speed option for the Davidson-Harel layouter.
 * 
 * @author mri
 */
public enum Speed {
    
    /** fast speed. */
    FAST,
    /** medium speed. */
    MEDIUM,
    /** high quality. */
    HQ;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case FAST:
            return OgdfServer.SPEED_FAST;
        case HQ:
            return OgdfServer.SPEED_HQ;
        case MEDIUM:
        default:
            return OgdfServer.SPEED_MEDIUM;
        }
    }
    
}
