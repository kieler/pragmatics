/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
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
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2013-01-09 review KI-32 by ckru, chsch
 */
public enum GraphFeature {

    /** Edges connecting a node with itself. */
    SELF_LOOPS("Edges connecting a node with itself."),
    /** Self-loops routed through a node instead of around it. */
    INSIDE_SELF_LOOPS("Self-loops routed through a node instead of around it."),
    /** Multiple edges with the same source and target node. */
    MULTI_EDGES("Multiple edges with the same source and target node."),
    /** Labels that are associated with edges. */
    EDGE_LABELS("Labels that are associated with edges."),
    /** Edges are connected to nodes over ports. */
    PORTS("Edges are connected to nodes over ports."),
    /**
     * Edges that connect nodes from different hierarchy levels and are incident to compound nodes.
     * @see LayoutOptions#LAYOUT_HIERARCHY
     */
    COMPOUND("Edges that connect nodes from different hierarchy levels"
            + "and are incident to compound nodes."),
    /** Edges that connect nodes from different clusters, but not the cluster parent nodes. */
    CLUSTERS("Edges that connect nodes from different clusters, but not the cluster parent nodes."),
    /** Multiple connected components. */
    DISCONNECTED("Multiple connected components.");
    
    /** The description of the graph feature. */
    private String description;
    
    /**
     * Create a new graph feature with no description.
     */
    private GraphFeature() {
        description = "";
    }
    
    /**
     * Creates a new graph feature with the given description.
     * 
     * @param description
     *            the description of the graph feature.
     */
    private GraphFeature(final String description) {
        this.description = description;
    }

    /**
     * Returns the description of the graph feature.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    

}
