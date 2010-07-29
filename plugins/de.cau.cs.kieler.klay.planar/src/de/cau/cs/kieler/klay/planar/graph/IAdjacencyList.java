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

import de.cau.cs.kieler.klay.planar.util.IFunction;

/**
 * The list of edges on a node in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IAdjacencyListComponent {@code IAdjacencyListComponent}
 * 
 * @author ocl
 */
public interface IAdjacencyList extends IAdjacencyListComponent {

    /**
     * An enum that defines how the adjacency list is constrained to accept edges.
     * 
     * @author ocl
     */
    enum AdjacencyListType {
        /** The order and number of edges is free, the node has no constraints. */
        FREE,

        /** The order of the edges in the list is free, but the number is fixed. */
        GROUP,

        /** The order of edges in the adjacency list is fixed, but may be reversed. */
        MIRROR,

        /** The order of edges in the adjacency list is fixed. */
        ORDERED
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the type of the adjacency list.
     * 
     * @return the type of the adjacency list
     */
    AdjacencyListType getType();

    /**
     * Add a port to the adjacency list. The new port is appended to the end of the list and then
     * returned for further use.
     * 
     * @return the newly created port
     */
    IPort addPort();

    /**
     * Add a port to the adjacency list. The port can be either appended or prepended to the list
     * depending on the given parameter. The new port is then returned for further use.
     * 
     * @param append
     *            specifies if the port will be appended or prepended to the list
     * @return the newly created port
     */
    IPort addPort(boolean append);

    /**
     * Add a new sublist to the constraint tree with the given constraint type. The new list is
     * appended to the end of the list and then returned for further use.
     * 
     * @param t
     *            the type of constraint the list uses
     * @return the newly created adjacency list
     */
    IAdjacencyList addList(AdjacencyListType t);

    /**
     * Add a new sublist to the constraint tree with the given constraint type. The list can be
     * either appended or prepended to the current list depending on the given parameter. The new
     * list is then returned for further use.
     * 
     * @param t
     *            the type of constraint the list uses
     * @param append
     *            specifies if the list will be appended or prepended to the list
     * @return the newly created adjacency list
     */
    IAdjacencyList addList(AdjacencyListType t, boolean append);

    /**
     * Get the number of edges in the adjacency list.
     * 
     * @return the number of actually connected edges
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    int getEdgeCount() throws InconsistentGraphModelException;

    /**
     * Get the number of allowed edges in the adjacency list. For free lists, the maximal number is
     * equal to the current number of edges.
     * 
     * @return the maximum number of edges
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    int getPortCount() throws InconsistentGraphModelException;

    /**
     * Check if a node is adjacent to an edge in the adjacency list.
     * 
     * @param node
     *            the node to check for
     * @return true if the node {@code n} is adjacent to the node of this adjacency list
     */
    boolean isAdjacent(INode node);

    /**
     * Get an adjacent node specified by the connecting edge. This returns the node directly
     * adjacent to the node of the adjacency list that is connected by the given edge {@code e}.
     * 
     * @param edge
     *            the edge that connects the nodes
     * @return the adjacent node of the node of this list regarding edge {@code e}
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not part of the adjacency list
     */
    INode getAdjacentNode(IEdge edge) throws InconsistentGraphModelException;

    /**
     * Get the edge to an adjacent node. This returns the edge that connects the node of this
     * adjacency list with the given node {@code n}.
     * 
     * @param node
     *            the adjacent node
     * @return the edge that connects the node of the list to {@code n}
     * @throws InconsistentGraphModelException
     *             if the given node {@code n} is not adjacent to an edge in the list
     */
    IEdge getEdge(INode node) throws InconsistentGraphModelException;

    // ======================== Miscellaneous Functions ============================================

    /**
     * Mirror the adjacency list.
     */
    void mirror();

    /**
     * Mirror the adjacency list. This mirrors either the top level of the constraint tree, or the
     * whole list.
     * 
     * @param deep
     *            true to mirror the whole list, false for top level only
     */
    void mirror(boolean deep);

    /**
     * Sort the adjacency list using a function. The function associates every component in the
     * adjacency list with an integer value. The adjacency list is sorted in ascending order by that
     * value using a bucket sort.
     * 
     * @param func
     *            the function that assigns a value to a port
     */
    void sort(IFunction<IAdjacencyListComponent, Integer> func);

    /**
     * Sort the adjacency list using a function. The function associates every component in the
     * adjacency list with an integer value. The adjacency list is sorted in ascending order by that
     * value using a bucket sort. This sorts either the top level of the constraint tree, or the
     * whole list.
     * 
     * @param func
     *            the function that assigns a value to a port
     * @param deep
     *            true to sort the whole list, false for top level only
     */
    void sort(IFunction<IAdjacencyListComponent, Integer> func, boolean deep);

    // ======================== Iterators ==========================================================

    /**
     * Iterate over all ports in the adjacency list.
     * 
     * @return iterable object containing all ports
     */
    Iterable<IPort> ports();

    /**
     * Iterate over all ports that have no edge attached.
     * 
     * @return iterable object containing free ports
     */
    Iterable<IPort> freePorts();

    /**
     * Iterate over all edges that are part of the adjacency list.
     * 
     * @return iterable object containing all edges in the list
     */
    Iterable<IEdge> edges();

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

    /**
     * Iterate over all nodes connected to edges in the adjacency list.
     * 
     * @return iterable object containing adjacent nodes
     */
    Iterable<INode> adjacentNodes();

    /**
     * Iterate over all adjacency lists this list is composed of.
     * 
     * @return iterable object containing child lists
     */
    Iterable<IAdjacencyListComponent> sublists();

}
