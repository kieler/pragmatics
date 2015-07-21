/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A graph for the T layouter. It consists of node, edges and labels.
 * 
 * @author sor
 * @author sgu
 */
public class TGraph extends MapPropertyHolder {

    /** the serial version UID. */
    private static final long serialVersionUID = 1L;

    /** All nodes of this graph. */
    private LinkedList<TNode> nodes = new LinkedList<TNode>();
    /** All edges of this graph. */
    private LinkedList<TEdge> edges = new LinkedList<TEdge>();
   
    /**
     * Default constructor that creates an empty graph.
     * 
     */
    public TGraph() {
        this.nodes = new LinkedList<TNode>();
        this.edges = new LinkedList<TEdge>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String tmp = null;
        for (TNode tNode : nodes) {
            tmp += tNode.toString() + "\n";
        }
        for (TEdge tEdge : edges) {
            tmp += tEdge.toString() + "\n";
        }
        return tmp;
    }

    /**
     * Returns the list of edges for this graph.
     * 
     * @return the edges
     */
    public List<TEdge> getEdges() {
        return edges;
    }

    /**
     * Returns the list of nodes for this graph.
     * 
     * @return the nodes
     */
    public List<TNode> getNodes() {
        return nodes;
    }

}
