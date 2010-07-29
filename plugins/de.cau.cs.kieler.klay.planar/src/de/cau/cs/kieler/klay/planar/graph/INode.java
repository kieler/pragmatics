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
package de.cau.cs.kieler.klay.planar.graph;

/**
 * A node in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface INode extends IGraphElement {

    /**
     * An enum that defines the general type of a node. Most graph nodes will be {@code NORMAL}
     * nodes.
     * 
     * @author ocl
     */
    public enum NodeType {
        /**
         * Normal graph nodes. Most nodes are of this type.
         */
        NORMAL,

        /**
         * Hyper nodes. Hyper nodes are used in hyper graphs, to connect more than two normal nodes
         * with one edge.
         */
        HYPER,

        /**
         * Compound nodes. Compound nodes are nodes that again represent graphs. They contain nodes
         * and edges themselves, and may even contain other compound nodes.
         */
        COMPOUND,

        /**
         * Algorithm-specific dummy or virtual nodes. Various graph algorithm temporarily insert
         * dummy nodes for different calculations.
         */
        OTHER
    }

    /**
     * Get the type of the node.
     * 
     * @see de.cau.cs.kieler.klay.planar.graph.INode.NodeType {@code Types}
     * 
     * @return the node type
     */
    NodeType getType();

    /**
     * Get the object representing the adjacency list of this node.
     * 
     * @return the adjacency list object
     */
    IAdjacencyList getAdjacencyList();

    /**
     * Merge the node with another node. This will merge two nodes in the graph. All edges of the
     * given node will be added to this node.
     * 
     * @param node
     *            the node to merge this node with
     * @throws InconsistentGraphModelException
     *             if the node is not part of the graph
     */
    void merge(INode node) throws InconsistentGraphModelException;

    /**
     * Merge the node with another node. This will merge two nodes in the graph. All edges of the
     * given node will be added to this node. The additional parameter {@code append} determines if
     * the adjacency list of the merged node will be appended or prepended to the adjacency list of
     * this node.
     * 
     * @param node
     *            the node to merge this node with
     * @param append
     *            the direction of the addition. True means the adjacency list of the second node
     *            will be appended to the adjacency list of the first node.
     * @throws InconsistentGraphModelException
     *             if the node is not part of the graph
     */
    void merge(INode node, boolean append) throws InconsistentGraphModelException;

}
