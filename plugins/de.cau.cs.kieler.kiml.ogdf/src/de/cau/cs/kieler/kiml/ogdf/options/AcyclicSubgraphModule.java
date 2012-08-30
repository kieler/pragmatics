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
 * Enumeration of available acyclic subgraph modules for Sugiyama layout.
 * 
 * @author msp
 */
public enum AcyclicSubgraphModule {
    
    /** depth-first-search based acyclic subgraph module. */
    DFS,
    /** greedy algorithm for the acyclic subgraph problem. */
    GREEDY;
    
    /**
     * Return a server parameter for the enumeration value.
     * 
     * @return a server parameter
     */
    public int toServerParam() {
        switch (this) {
        case GREEDY:
            return OgdfServer.ACYCLIC_SUBGRAPH_GREEDY;
        case DFS:
        default:
            return OgdfServer.ACYCLIC_SUBGRAPH_DFS;
        }
    }

}
