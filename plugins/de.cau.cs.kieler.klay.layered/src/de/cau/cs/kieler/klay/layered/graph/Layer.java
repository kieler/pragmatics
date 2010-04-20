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
package de.cau.cs.kieler.klay.layered.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * A layer in a layered graph.
 *
 * @author msp
 */
public class Layer extends LGraphElement {

    /** the owning layered graph. */
    private LayeredGraph owner;

    /** the size of the layer as drawn horizontally. */
    private Coord size = new Coord();
    /** the nodes of the layer. */
    private List<LNode> nodes = new LinkedList<LNode>();
    
    /**
     * Creates a layer for the given layered graph.
     * 
     * @param graph the owning layered graph
     */
    public Layer(final LayeredGraph graph) {
        this.owner = graph;
    }
    
    /**
     * @return the size
     */
    public Coord getSize() {
        return size;
    }

    /**
     * @return the nodes
     */
    public List<LNode> getNodes() {
        return nodes;
    }
    
    /**
     * @return the owner
     */
    public LayeredGraph getGraph() {
        return owner;
    }
    
    /**
     * @return the index of this layer
     */
    public int getIndex() {
        return owner.getLayers().indexOf(this);
    }

}
