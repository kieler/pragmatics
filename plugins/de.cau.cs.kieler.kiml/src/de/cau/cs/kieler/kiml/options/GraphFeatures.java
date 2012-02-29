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
package de.cau.cs.kieler.kiml.options;

/**
 * Graph features used for automatic recognition of the suitability of layout algorithms.
 *
 * @author msp
 */
public enum GraphFeatures {

    /**
     * Edges connecting a node with itself.
     */
    SELF_LOOPS,
    /**
     * Multiple edges with the same source and target node.
     */
    MULTI_EDGES,
    /**
     * Labels that are associated with edges.
     */
    EDGE_LABELS,
    /**
     * Edges are connected to nodes over ports.
     */
    PORTS,
    /**
     * Edges that connect nodes from different hierarchy levels and are incident to compound nodes.
     * @see LayoutOptions#LAYOUT_HIERARCHY
     */
    COMPOUND,
    /**
     * Edges that connect nodes from different clusters, but not the cluster parent nodes.
     */
    CLUSTERS;
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static GraphFeatures valueOf(final int i) {
        return values()[i];
    }

}
