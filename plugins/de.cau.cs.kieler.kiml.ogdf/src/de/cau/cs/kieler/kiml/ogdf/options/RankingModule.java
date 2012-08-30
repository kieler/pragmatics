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
 * Enumeration of available ranking modules.
 * 
 * @author msp
 */
public enum RankingModule {
    
    /** the longest path ranking module. */
    LONGEST_PATH,
    /** the Coffman-Graham ranking module. */
    COFFMAN_GRAHAM,
    /** the optimal ranking module. */
    OPTIMAL;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case COFFMAN_GRAHAM:
            return OgdfServer.RANKING_COFFMAN_GRAHAM;
        case OPTIMAL:
            return OgdfServer.RANKING_OPTIMAL;
        case LONGEST_PATH:
        default:
            return OgdfServer.RANKING_LONGEST_PATH;
        }
    }

}
