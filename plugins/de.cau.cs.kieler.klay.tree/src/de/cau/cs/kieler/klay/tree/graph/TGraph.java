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
package de.cau.cs.kieler.klay.tree.graph;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;

/**
 * A graph for the T layouter.
 * 
 * @author sor
 * @author sgu
 */
public class TGraph extends MapPropertyHolder {
    
    /** the serial version UID. */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    /** All nodes of this graph. */
    private LinkedList<TNode> nodes = new LinkedList<TNode>();
    /** All edges of this graph. */
    private LinkedList<TEdge> edges = new LinkedList<TEdge>();
    /** All labels of this graph. */
    private LinkedList<TLabel> labels = new LinkedList<TLabel>();
        
    TGraph() {
        this.nodes = new LinkedList<TNode>();
        this.edges = new LinkedList<TEdge>();
        this.labels = new LinkedList<TLabel>();
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

    /**
     * Returns the list of labels for this graph.
     * 
     * @return the labels
     */
    public List<TLabel> getLabels() {
        return labels;
    }
    
    // TODO: adding label to node
	// TODO adding id to node
    public TNode addNode(TNode tnode) {
        tnode = new TNode(0, this);
        nodes.add(tnode);
        return tnode;
    }
    
    public TEdge addEdge(TNode source, TNode target) {
        TEdge tedge = new TEdge(source, target);
        edges.add(tedge);
        return tedge;
    }
}
