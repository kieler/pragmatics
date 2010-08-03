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

import de.cau.cs.kieler.klay.planar.graph.IEmbeddingConstraint.ConstraintType;
import de.cau.cs.kieler.klay.planar.util.IFunction;

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
        COMPOUND
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the type of the node.
     * 
     * @see de.cau.cs.kieler.klay.planar.graph.INode.NodeType {@code Types}
     * 
     * @return the node type
     */
    NodeType getType();

    /**
     * Get the number of edges in the adjacency list of the node.
     * 
     * @return the number of connected edges
     */
    int getAdjacentEdgeCount();

    /**
     * Check if a node is adjacent to this node (i.e. an edge exists that connects the two nodes).
     * 
     * @param node
     *            the node to check for
     * @return true if the node {@code n} is adjacent to this node
     */
    boolean isAdjacent(INode node);

    /**
     * Get an adjacent node specified by the connecting edge. This returns the node directly
     * adjacent to this node, connected by the given edge {@code e}.
     * 
     * @param edge
     *            the edge that connects the nodes
     * @return the adjacent node of this node regarding edge {@code e}
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not part of the adjacency list
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
     *             if the given node {@code n} is not adjacent to an edge in the list
     */
    IEdge getEdge(INode node) throws InconsistentGraphModelException;

    /**
     * Check if the node has any embedding constraints.
     * 
     * @return true if any constraints were applied to the node
     */
    boolean hasEmbeddingConstraint();

    /**
     * Get the root of the constraint tree applied to this node. Returns null if no embedding
     * constraints were applied to this node.
     * 
     * @return the embeddig constraint tree root
     */
    IEmbeddingConstraint getEmbeddingConstraint();

    /**
     * Add an embedding constraint to the node. An empty constraint tree will be created for the
     * node, with the root node of the given type. The added constraint will then be returned for
     * further use. Any previous embedding constraints on this node will be lost, since a node can
     * has only one embedding constraint at a time.
     * 
     * @param constraintType
     *            the type of embedding constraint.
     * @return the embedding constraint tree root
     */
    IEmbeddingConstraint applyEmbeddingConstraint(ConstraintType constraintType);

    // ======================== Iterators ==========================================================

    /**
     * Iterate over all edges that are part of the adjacency list.
     * 
     * @return iterable object containing all edges in the list
     */
    Iterable<IEdge> adjacentEdges();

    /**
     * Iterate over all nodes adjacent to this node.
     * 
     * @return iterable object containing adjacent nodes
     */
    Iterable<INode> adjacentNodes();

    /**
     * Iterate over all incoming edges that are part of the adjacency list. Incoming edges are all
     * edges whose target is the node this list belongs to. Undirected edge are neither incoming nor
     * outgoing edges.
     * 
     * @return iterable object containing incoming edges
     */
    Iterable<IEdge> incomingEdges();

    /**
     * Iterate over all outgoing edges that are part of the adjacency list. Incoming edges are all
     * edges whose source is the node this list belongs to. Undirected edge are neither incoming nor
     * outgoing edges.
     * 
     * @return iterable object containing outgoing edges
     */
    Iterable<IEdge> outgoingEdges();

    // ======================== Miscellaneous Functions ============================================

    /**
     * Mirror the adjacency list.
     */
    void mirror();

    /**
     * Sort the adjacency list using a function. The function associates every edge in the adjacency
     * list with an integer value. The adjacency list is sorted in ascending order by that value
     * using a bucket sort.
     * 
     * @param func
     *            the function that assigns a value to a port
     */
    void sort(IFunction<IEdge, Integer> func);

    /**
     * Sort the adjacency list using a function. The function associates every edge in the adjacency
     * list with an integer value. The adjacency list can be sorted either in ascending or
     * descending order of the function values.
     * 
     * @param func
     *            the function that assigns a value to a port
     * @param ascending
     *            specifies if the list is sorted in ascending or descending order
     */
    void sort(IFunction<IEdge, Integer> func, boolean ascending);

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
