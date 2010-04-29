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
 * A layer in a layered graph. A layer contains a list of nodes, which are
 * drawn in one column.
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "L_" + getIndex() + nodes.toString();
    }
    
    /**
     * Returns the size of the layer, that is the height of the stacked nodes
     * and the maximal width of the nodes.
     * 
     * @return the size of the layer
     */
    public Coord getSize() {
        return size;
    }

    /**
     * Returns the list of nodes. The order of nodes in this list corresponds to
     * the order in which they are drawn inside the layer: the first node is
     * drawn topmost. This order is affected during crossing minimization.
     * 
     * @return the nodes of the layer
     */
    public List<LNode> getNodes() {
        return nodes;
    }
    
    /**
     * Returns the layered graph that owns this layer.
     * 
     * @return the owner
     */
    public LayeredGraph getGraph() {
        return owner;
    }
    
    /**
     * Returns the index of this layer in the global list of layers. Note that
     * this method has linear running time in the number of layers, so use it
     * with caution.
     * 
     * @return the index of this layer
     */
    public int getIndex() {
        return owner.getLayers().indexOf(this);
    }

}
