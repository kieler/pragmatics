/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.compound;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.PortType;

/**
 *
 * @author msp
 */
public class CrossHierarchyEdge {
    
    /** the edge used in the layered graph to compute a layout. */
    private LEdge ledge;
    /** the layered graph in which the layout was computed. */
    private LGraph lgraph;
    /** the corresponding compound node. */
    private LNode parentNode;
    /** the flow direction: input or output. */
    private PortType type;
    
    /**
     * Create a cross-hierarchy edge segment.
     * 
     * @param ledge the edge used in the layered graph to compute a layout
     * @param lgraph the layered graph in which the layout is computed
     * @param parentNode the corresponding compound node
     * @param type the flow direction: input or output
     */
    CrossHierarchyEdge(final LEdge ledge, final LGraph lgraph, final LNode parentNode,
            final PortType type) {
        this.ledge = ledge;
        this.lgraph = lgraph;
        this.parentNode = parentNode;
        this.type = type;
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return type.toString() + ":" + ledge.toString();
    }

    /**
     * Return the edge.
     * @return the edge
     */
    public LEdge getEdge() {
        return ledge;
    }

    /**
     * Return the graph.
     * @return the graph
     */
    public LGraph getGraph() {
        return lgraph;
    }

    /**
     * Return the parent node.
     * @return the parent node
     */
    public LNode getParentNode() {
        return parentNode;
    }

    /**
     * Return the port type.
     * @return the port type
     */
    public PortType getPortType() {
        return type;
    }
    
}
