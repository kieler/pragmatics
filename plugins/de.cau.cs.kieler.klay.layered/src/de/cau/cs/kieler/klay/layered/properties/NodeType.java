/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.properties;

/**
 * Definition of node types used in the layered approach.
 * 
 * @author msp
 * @author cds
 * @author ima
 */
public enum NodeType {
    /** a node representing an external port. */
    EXTERNAL_PORT,
    /** a normal node is created from a node of the original graph. */
    NORMAL,
    /** a dummy node created to split a long edge. */
    LONG_EDGE,
    /** a dummy node created to cope with ports at the northern or southern side. */
    NORTH_SOUTH_PORT,
    /** a dummy node created as upper border node to represent a subgraph node. */
    UPPER_COMPOUND_BORDER,
    /** a dummy node created as lower border node to represent a subgraph node. */
    LOWER_COMPOUND_BORDER,
    /**
     * a dummy node created to represent a port connected with an incoming edge. Serving as one of
     * the upper border nodes.
     */
    UPPER_COMPOUND_PORT,
    /**
     * a dummy node created to represent a port connected with an outgoing edge. Serving as one of
     * the lower border nodes.
     */
    LOWER_COMPOUND_PORT,
    /**
     * a dummy node created to be part of a linear segment used to draw the sides of a compound
     * node.
     */
    COMPOUND_SIDE,
    /**
     * a dummy node created to allow for constraints of the node ordering phase for compound graphs.
     * Starts the compound graph's section in the layer.
     */
    UPPER_COMPOUND_SIDE,
    /**
     * a dummy node created to allow for constraints of the node ordering phase for compound graphs.
     * Ends the compound graph's section in the layer.
     */
    LOWER_COMPOUND_SIDE;

}
