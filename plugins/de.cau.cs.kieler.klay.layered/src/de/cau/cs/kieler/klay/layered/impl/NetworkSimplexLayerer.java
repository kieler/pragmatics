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

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.ILayerer;

/**
 * @author pdo
 */
public class NetworkSimplexLayerer extends AbstractAlgorithm implements ILayerer {

    // ====================== Attributes ======================================

    /** the layered graph all methods in this class operate on. */
    private LayeredGraph layerGraph;

    /** A collection containing all nodes in the graph to layer. */
    private Collection<LNode> layerNodes;

    /** A collection containing all edges in the graph. */
    private Collection<LEdge> layerEdges;

    /**
     * The layer each node is currently assigned to. Note that during layerer execution, the lowest
     * layer is not necessary the zeroth layer. To fulfill this characteristic, a final call of
     * {@code normalize()} has to be performed.
     */
    private int[] layer;

    private int[] revLayer;

    private boolean[] treeNode;

    private LinkedHashSet<LEdge> treeEdges;

    private LinkedHashSet<LEdge> nonTreeEdges;

    private LinkedList<LNode> roots;

    private LinkedList<LNode> sinks;

    private int[] inDegree;

    private int[] outDegree;

    /** The cut value of every edge. */
    private int[] cutvalue;

    private int[] tightness;

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
     * @param nodes
     *            the nodes in the graph
     * @param edges
     *            the edges in the graph
     */
    private void initialize(final Collection<LNode> nodes, final Collection<LEdge> edges) {

        int numNodes = nodes.size();
        int numEdges = edges.size();

        // re-index nodes and edges
        int counter = 0;
        for (LNode node : nodes) {
            node.id = counter++;
        }
        counter = 0;
        for (LEdge edge : edges) {
            edge.id = counter++;
        }
        // initialize node attributes
        if (layer == null || layer.length < numNodes) {
            layer = new int[numNodes];
            revLayer = new int[numNodes];
            Arrays.fill(revLayer, numNodes);
            treeNode = new boolean[numNodes];
            treeEdges = new LinkedHashSet<LEdge>(numNodes);
            nonTreeEdges = new LinkedHashSet<LEdge>(numNodes);
        } else {
            Arrays.fill(layer, 0);
            Arrays.fill(revLayer, numNodes);
            Arrays.fill(treeNode, false);
            treeEdges.clear();
            nonTreeEdges.clear();
        }
        if (roots == null) {
            roots = new LinkedList<LNode>();
            sinks = new LinkedList<LNode>();
        } else {
            roots.clear();
            sinks.clear();
        }
        // initialize edge attributes
        if (cutvalue == null || cutvalue.length < numEdges) {
            cutvalue = new int[numEdges];
            tightness = new int[numEdges];
            inDegree = new int[numEdges];
            outDegree = new int[numEdges];
        } else {
            Arrays.fill(inDegree, 0);
            Arrays.fill(outDegree, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void layer(final Collection<LNode> nodes, final LayeredGraph layeredGraph) {

        getMonitor().begin("network simplex layering", 1);
        if (nodes.size() < 1) {
            getMonitor().done();
            return;
        }

        // initialize attributes
        layerGraph = layeredGraph;
        layerNodes = nodes;
        layerEdges = getEdges(nodes);
        initialize(nodes, layerEdges);

        // determine feasible tree
        feasibleTree();
        LEdge e = null;
        while ((e = leaveEdge()) != null) {
            // current layering is not optimal
            exchange(e, enterEdge());
        }
        normalize();
        balance();

        getMonitor().done();
    }

    private void feasibleTree() {
        // TODO
    }

    private void initLayering() {

        for (LNode node : roots) {
            layerDFS(node, false);
        }
        // TODO first optimization : pull roots closer to its adjacent nodes, if possible
    }

    private LEdge tightTree() {

        LEdge minimalSlack = null;
        // TODO
        return null;
    }

    private void tightness() {
        // TODO determine minimal span of all edges
    }

    private void cutvalues() {
        // TODO
    }

    private void moveLeafs() {

        for (LNode node : roots) {
            layer[node.id] += minimalSpan(node, PortType.OUTPUT) - 1;
        }
        for (LNode node : sinks) {
            layer[node.id] -= minimalSpan(node, PortType.INPUT) - 1;
        }
    }

    // TODO update treeNode attribute whenever the tree changes

    /**
     * Helper method for the network simplex layerer. It returns a tree edge with a negative cut
     * value or {@code null}, if no such edge exists, meaning that the current layer assignment of
     * all nodes is optimal. Note, that this method returns any edge with a negative cut value. A
     * special preference to an edge with lowest value will not be given.
     * 
     * @return a tree edge with negative cut value or {@code null}, if no such edge exists
     */
    private LEdge leaveEdge() {

        for (LEdge edge : treeEdges) {
            if (cutvalue[edge.id] < 0) {
                return edge;
            }
        }
        return null;
    }

    private LEdge enterEdge() {
        // TODO
        return null;
    }

    private void exchange(final LEdge leave, final LEdge enter) {

        // update tree
        treeEdges.remove(leave);
        nonTreeEdges.add(leave);
        treeEdges.add(enter);
        nonTreeEdges.remove(enter);
        // TODO update cut values
    }

    /**
     * Helper method for the network simplex layerer. It normalizes the layering, i.e. determines
     * the lowest layer assigned to a node and shifts all nodes layers up or down accordingly. After
     * termination, the lowest layer assigned to a node will be zeroth (and therefore first) layer.
     */
    private void normalize() {

        // determine lowest assigned layer
        int lowest = Integer.MAX_VALUE;
        for (LNode node : roots) {
            if (layer[node.id] < lowest) {
                lowest = layer[node.id];
            }
        }
        // normalize
        for (LNode node : layerNodes) {
            layer[node.id] -= lowest;
        }
    }

    private void balance() {
        // TODO
    }

    /**
     * Helper method for the network simplex layerer. It determines all edges connecting the nodes
     * in the graph and returns them as a {@code LinkedList}. Furthermore, it determines the number
     * of incoming and outgoing edges of each node ({@code inDegree}, respectively {@code outDegree}
     * ) and identifies all sinks and root nodes in the graph and adds them to {@code sinks},
     * respectively {@code roots}.
     * 
     * @param nodes
     *            a {@link Collection} containing all nodes of the graph
     * @return a {@link LinkedList} containing all edges of the graph
     */
    private Collection<LEdge> getEdges(final Collection<LNode> nodes) {

        LinkedList<LEdge> edges = new LinkedList<LEdge>();
        for (LNode node : nodes) {
            for (LPort port : node.getPorts()) {
                if (port.getType() == PortType.OUTPUT) {
                    edges.addAll(port.getEdges());
                    outDegree[node.id] += port.getEdges().size();
                } else if (port.getType() == PortType.INPUT) {
                    inDegree[node.id] += port.getEdges().size();
                }
                // undefined ports are neglected
            }
            if (outDegree[node.id] == 0) {
                sinks.add(node);
            }
            if (inDegree[node.id] == 0) {
                roots.add(node);
            }
        }
        return edges;
    }

    /**
     * Helper method for the network simplex layerer. It determines an (initial) feasible layering
     * for the given graph by traversing it by a modified depth-first-search arranging the nodes to
     * the layer representing their height in the DFS-tree. Dependently of the chosen mode indicated
     * by {@code reverse}, this method traverses the graph beginning from either its root nodes, if
     * {@code reverse = false}, or sinks, if {@code reverse = true}, which means that all nodes will
     * be placed as close as possible to either the root or sink nodes.
     * 
     * @param node
     * @param reverse
     */
    private void layerDFS(final LNode node, final boolean reverse) {

        LNode target = null;
        if (reverse) {
            for (LPort port : node.getPorts(PortType.INPUT)) {
                for (LEdge edge : port.getEdges()) {
                    target = edge.getSource().getNode();
                    // FIXME not correct this way ???
                    layer[target.id] = Math.min(layer[target.id], layer[node.id] - 1);
                    layerDFS(target, reverse);
                }
            }
        } else {
            for (LPort port : node.getPorts(PortType.OUTPUT)) {
                for (LEdge edge : port.getEdges()) {
                    target = edge.getTarget().getNode();
                    // FIXME not correct this way ???
                    layer[target.id] = Math.max(layer[target.id], layer[node.id] + 1);
                    layerDFS(target, reverse);
                }
            }
        }
    }

    /**
     * Helper method for the network simplex layerer. It determines the length of the currently
     * shortest incoming respectively outgoing edge of the input node.
     * 
     * @param node
     * @param direction
     * @return
     */
    private int minimalSpan(final LNode node, final PortType direction) {

        int minSpan = Integer.MAX_VALUE;
        int currentSpan;
        for (LPort port : node.getPorts(direction)) {
            for (LEdge edge : port.getEdges()) {
                currentSpan = layer[edge.getTarget().id] - layer[edge.getSource().id];
                if (currentSpan < minSpan) {
                    minSpan = currentSpan;
                }
            }
        }
        return minSpan;
    }

}
