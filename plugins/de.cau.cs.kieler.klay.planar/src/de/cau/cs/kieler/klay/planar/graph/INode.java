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

import java.util.Comparator;
import java.util.List;

/**
 * A node in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface INode extends IGraphElement {

    // ======================== Node Types =========================================================

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

    // ======================== Adjacency List =====================================================

    /**
     * Get all edges that are connected to this node. This returns an {@code Iterable} object
     * containing all edges that are connected to this node. This contains outgoing edges as well as
     * incoming edges, so this method should preferably used in undirected graphs.
     * 
     * @return all edges
     */
    Iterable<IEdge> getAllEdges();

    /**
     * Get all edges that point to this node. This returns an {@code Iterable} object containing all
     * edges whose target node is this node, therefore being 'incoming' edges to this node.
     * 
     * @return all incoming edges
     */
    Iterable<IEdge> getIncomingEdges();

    /**
     * Get all edges that originate in this node. This returns an {@code Iterable} object containing
     * all edges whose source node is this node, therefore being 'outgoing' edges from this node.
     * 
     * @return all outgoing edges
     */
    Iterable<IEdge> getOutgoingEdges();

    /**
     * Get the number of edges connected to this node.
     * 
     * @return the number of edges
     */
    int getAdjacentEdgeCount();

    /**
     * Get the list of all edges connected to this node. This method exists additionally to {@code
     * getEdges()}, to be able to modify the adjacency list of a node directly (e.g. sort, revert).
     * Note that this method should be used with care, since it may cause graph inconsistencies if
     * edges are added or deleted from the list.
     * 
     * @return the list of edges
     */
    List<IEdge> getEdgeList();

    /**
     * Get all adjacent nodes. This returns an {@code Iterable} object containing all node that are
     * directly connected to this node by an edge.
     * 
     * @return all adjacent nodes of this node
     */
    Iterable<INode> getAdjacentNodes();

    /**
     * Check if a node is adjacent to this node.
     * 
     * @param node
     *            the node to check for
     * @return true if the node {@code n} is adjacent to this node
     */
    boolean isAdjacent(INode node);

    /**
     * Get an adjacent node specified by the connecting edge. This returns the node directly
     * adjacent to this node that is connected to this node by the given edge {@code e}.
     * 
     * @param edge
     *            the edge that connects the nodes
     * @return the adjacent node of this node regarding edge {@code e}
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not connected to this node
     */
    INode getAdjacentNode(IEdge edge) throws InconsistentGraphModelException;

    /**
     * Get the edge to an adjacent node. This returns the edge that connects this node with the
     * given node {@code n}.
     * 
     * @param node
     *            the adjacent node
     * @return the edge that connects this node to {@code n}
     * @throws InconsistentGraphModelException
     *             if the given node {@code n} is not adjacent to this node
     */
    IEdge getEdge(INode node) throws InconsistentGraphModelException;

    // ======================== Modify Adjacency List ==============================================

    /**
     * Reverse the order of the adjacency list of this node.
     */
    void reverseAdjacencyList();

    /**
     * Sort the adjacency list using a comparator.
     * 
     * @param comp
     *            the comparator to use for sorting
     */
    void sortAdjacencyList(Comparator<IEdge> comp);

    // ======================== Miscellaneous Functions ============================================

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
