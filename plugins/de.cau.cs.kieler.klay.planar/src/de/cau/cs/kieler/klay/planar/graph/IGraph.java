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

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.util.Property;

/**
 * Interface for a basic graph data structure. The purpose of this basic structure is to be used in
 * general graph theory algorithms. It does not provide any information concerning the drawing of a
 * graph (x/y coordinates etc.).
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface IGraph extends INode {

    // ======================== Properties =========================================================

    /** Property to convert an {@code IGraphElement} to its corresponding {@code KGraphElement}. */
    Property<KGraphElement> TOKGRAPH = new Property<KGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.tokgraph");

    /** Property to get the element in the dual graph for any {@code IGraphElement}. */
    Property<IGraphElement> TODUALGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.todualgraph");

    // ======================== Graph ==============================================================

    /**
     * Resets the IDs of all nodes, edges and faces in the graph. This guarantees that every ID is
     * at least {@code 0} and at most the number of respective items contained it the graph.
     */
    void reindex();

    /**
     * Generate the dual graph of this graph. The dual graph is a graph, that has a node for every
     * face in the original graph, and edges between neighboring faces.
     * 
     * @return the dual graph to this graph
     * @throws InconsistentGraphModelException
     *             if the given graph turns out to be inconsistent
     */
    IGraph createDualGraph() throws InconsistentGraphModelException;

    // ======================== Nodes ==============================================================

    /**
     * Get all nodes in this graph. This provides an {@code Iterable} object to gain access to all
     * {@code INode}s that are part of the graph.
     * 
     * @return {@code Iterable} containing all graph nodes
     */
    Iterable<INode> getNodes();

    /**
     * Get the number of nodes in the graph.
     * 
     * @return the number of nodes
     */
    int getNodeCount();

    /**
     * Add a new node to the graph. This adds an empty node to the graph, that is not connected with
     * any other nodes in the graph.
     * 
     * @return the new node in the graph
     */
    INode addNode();

    /**
     * Add a new node to the graph. This adds a node on the given {@code IEdge} to the graph. The
     * edge will be split into two edges and all references to the new node in neighboring edges and
     * faces will be set correctly.
     * 
     * @param edge
     *            the edge to split up by the node
     * @return the new node in the graph
     * @throws InconsistentGraphModelException
     *             if the edge is not part of the graph
     */
    INode addNode(IEdge edge) throws InconsistentGraphModelException;

    /**
     * Add a new node of a specific type to the graph. This adds an empty node of the given type to
     * the graph, that is not connected with any other nodes in the graph.
     * 
     * @param type
     *            the type of the node
     * @return the new node in the graph
     */
    INode addNode(NodeType type);

    /**
     * Add a new node to the graph. This adds a node of a specific type on the given {@code IEdge}
     * to the graph. The edge will be split into two edges and all references to the new node in
     * neighboring edges and faces will be set correctly.
     * 
     * @param edge
     *            the edge to split up by the node
     * @param type
     *            the type of the node
     * @return the new node in the graph
     * @throws InconsistentGraphModelException
     *             if the edge is not part of the graph
     */
    INode addNode(IEdge edge, NodeType type) throws InconsistentGraphModelException;

    /**
     * Remove a node from the graph. The node will be remove, together with all references to it in
     * other nodes as well as all edges connected to the node.
     * 
     * @param node
     *            the node to remove
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the specified node
     */
    void removeNode(INode node) throws InconsistentGraphModelException;

    // ======================== Edges ==============================================================

    /**
     * Get all edges in this graph. This provides an {@code Iterable} object to gain access to all
     * edges that are part of the graph.
     * 
     * @return {@code Iterable} containing all graph edges
     */
    Iterable<IEdge> getEdges();

    /**
     * Get the number of edges in the graph.
     * 
     * @return the number of edges
     */
    int getEdgeCount();

    /**
     * Add a directed edge to the graph. A new, directed edge will be inserted into the graph,
     * connecting the two nodes {@code source} and {@code target}. This checks if the two given
     * nodes are actually part of the graph and throws an exception otherwise. The newly created
     * edge object is returned for further use.
     * 
     * @param source
     *            the source node of the edge
     * @param target
     *            the target node of the edge
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the source node or target node of the edge, or if
     *             there are no free ports to host the edge
     */
    IEdge addEdge(INode source, INode target) throws InconsistentGraphModelException;

    /**
     * Add an edge to the graph. A new edge will be inserted into the graph, connecting the two
     * nodes {@code source} and {@code target}. The node can either be directed or undirected. This
     * checks if the two given nodes are actually part of the graph and throws an exception
     * otherwise. The newly created edge object is returned for further use.
     * 
     * @param source
     *            the source node of the edge
     * @param target
     *            the target node of the edge
     * @param directed
     *            specifies if the edge is directed or undirected
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the source node or target node of the edge, or if
     *             there are no free ports to host the edge
     */
    IEdge addEdge(INode source, INode target, boolean directed)
            throws InconsistentGraphModelException;

    /**
     * Add a directed edge to the graph. A new, directed edge will be inserted into the graph,
     * connecting the two nodes {@code source} and {@code target}. This checks if the two given
     * nodes are actually part of the graph and throws an exception otherwise. The newly created
     * edge object is returned for further use. The additional parameters {@code appendSource} and
     * {@code appendTarget} specify if the edge is appended or prepended to the adjacency list of
     * {@code source} and {@code target} respectively.
     * 
     * @param source
     *            the source node of the edge
     * @param appendSource
     *            true to append the edge to the source node, false to prepend the edge
     * @param target
     *            the target node of the edge
     * @param appendTarget
     *            true to append the edge to the target node, false to prepend the edge
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the source node or target node of the edge, or if
     *             there are no free ports to host the edge
     */
    IEdge addEdge(INode source, boolean appendSource, INode target, boolean appendTarget)
            throws InconsistentGraphModelException;

    /**
     * Add an edge to the graph. A new edge will be inserted into the graph, connecting the two
     * nodes {@code source} and {@code target}. The node can be either directed or undirected. This
     * checks if the two given nodes are actually part of the graph and throws an exception
     * otherwise. The newly created edge object is returned for further use. The additional
     * parameters {@code appendSource} and {@code appendTarget} specify if the edge is appended or
     * prepended to the adjacency list of {@code source} and {@code target} respectively.
     * 
     * @param source
     *            the source node of the edge
     * @param appendSource
     *            true to append the edge to the source node, false to prepend the edge
     * @param target
     *            the target node of the edge
     * @param appendTarget
     *            true to append the edge to the target node, false to prepend the edge
     * @param directed
     *            specifies if the edge is directed or undirected
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the source node or target node of the edge, or if
     *             there are no free ports to host the edge
     */
    IEdge addEdge(INode source, boolean appendSource, INode target, boolean appendTarget,
            boolean directed) throws InconsistentGraphModelException;

    /**
     * Remove an edge from the graph. The edge will be removed together will all references to the
     * edge in neighboring nodes.
     * 
     * @param edge
     *            the edge to remove
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the specified edge
     */
    void removeEdge(IEdge edge) throws InconsistentGraphModelException;

    // ======================== Faces ==============================================================

    /**
     * Get all faces in this graph. This provides an {@code Iterable} object to gain access to all
     * faces that are part of the graph.
     * 
     * @return {@code Iterable} containing all graph faces
     * @throws InconsistentGraphModelException
     *             if the graph turns out to be inconsistent
     */
    Iterable<IFace> getFaces() throws InconsistentGraphModelException;

    /**
     * Get the number of faces in the graph.
     * 
     * @return the number of faces
     * @throws InconsistentGraphModelException
     *             if the graph turns out to be inconsistent
     */
    int getFaceCount() throws InconsistentGraphModelException;

}
