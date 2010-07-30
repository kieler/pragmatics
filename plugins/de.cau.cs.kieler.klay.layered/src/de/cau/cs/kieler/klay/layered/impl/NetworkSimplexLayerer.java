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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;

/**
 * 
 * @author pdo
 */
public class NetworkSimplexLayerer extends AbstractAlgorithm implements ILayerer {

    // ====================== Attributes ======================================

    /** the layered graph all methods in this class operate on. */
    private LayeredGraph layerGraph;

    /**
     * A collection containing all nodes in the graph to layer.
     * 
     * @see java.util.Collection Collection
     */
    private Collection<LNode> layerNodes;

    /**
     * The height of all nodes in the layered graph and therefore, it determines which layer they
     * are assigned to.
     */
    private int[] height;

    /** the minimal spacing between objects. */
    private float spacing;

    /** The cut value of every edge. */
    private int[] cutvalue;

    /**
     * The longest path in the graph from a root node to a sink. Therefore, it defines, how many
     * layers are at least necessary to layer the entire graph, which is {@code longestPath + 1}.
     */
    private int longestPath;

    // ====================== Constructor =====================================

    /**
     * Default Constructor for {@link NetworkSimplexLayerer}. It creates a new instance of this
     * class.
     */
    public NetworkSimplexLayerer() {
    }

    // ====================== Methods =====================================

    /**
     * Helper method for the network simplex layerer. It instantiates all necessary attributes of
     * this class and initializes them with their default values. If the attributes already exist
     * (i.e. they were created by a previous function call) and if their size fits for the nodes of
     * the current graph, then the old instances will be reused as far as possible to save memory
     * space.
     * 
     * @param numNodes
     *            the number of nodes in the graph
     * @param numEdges
     *            the number of edges in the graph
     */
    private void initialize(final Collection<LNode> nodes, final Collection<LEdge> edges) {
        
        // re-index nodes and edges
        int counter = 0;
        for (LNode node : nodes) {
            node.id = counter;
        }
        counter = 0;
        for (LEdge edge : edges) {
            edge.id = counter;
        }
        
        // initialize node attributes
        if (height == null || height.length < nodes.size()) {
            height = new int[nodes.size()];
        } else {
            Arrays.fill(height, -1);
        }
        
        // initialize edge attributes
        if (cutvalue == null || cutvalue.length < edges.size()) {
            cutvalue = new int[edges.size()];
        }
         
        longestPath = 0;
    }

    
    /**
     * {@inheritDoc}
     */
    public void layer(final Collection<LNode> nodes, final LayeredGraph layeredGraph) {

        // initialize attributes
        layerGraph = layeredGraph;
        layerNodes = nodes;
        initialize(nodes, getEdges(nodes));

        // determine feasible tree
        feasibleTree();
        LEdge e = null;
        while ((e = leaveEdge()) != null) {
            // current layering is not optimal
            exchange(e, enterEdge());
        }
        normalize();
        balance();
    }

    private void feasibleTree() {
        // TODO
    }

    private void initialRank() {
        // TODO
    }

    private int tightTree() {
        // TODO
        return 0;
    }

    private LEdge minimalSlack() {
        // TODO
        return null;
    }

    private void initCutvalues() {
        // TODO
    }

    /**
     * Helper method for the layerer. It returns a tree edge with minimal cut value or {@code null},
     * if no such exists, meaning that the current layer assignment of all nodes is optimal. Note,
     * that any edge with a negative cut value might be returned. A preference for edges with the
     * lowest cut value is not made.
     * 
     * @return a tree edge with minimal cut value or {@code null}, if no such exists
     */
    private LEdge leaveEdge() {
        // TODO
        return null;
    }

    private LEdge enterEdge() {
        // TODO
        return null;
    }

    private void exchange(final LEdge leave, final LEdge enter) {
        // TODO
    }

    private void normalize() {
        // TODO
    }

    private void balance() {
        // TODO
    }

    
    
    private Collection<LEdge> getEdges(final Collection<LNode> nodes) {
        
        LinkedList<LEdge> edges = new LinkedList<LEdge>();
        for (LNode node : nodes) {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                edges.addAll(port.getEdges());
            }
        }
        return edges;
    }

    
}
