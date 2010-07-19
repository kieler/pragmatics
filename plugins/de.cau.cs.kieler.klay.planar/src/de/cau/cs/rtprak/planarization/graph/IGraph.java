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
package de.cau.cs.rtprak.planarization.graph;

/**
 * Interface for a basic graph data structure. The purpose of this basic structure is to be used in
 * general graph theory algorithms. It does not provide any information concerning the drawing of a
 * graph (x/y coordinates etc.).
 * 
 * @see de.cau.cs.rtprak.planarization.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface IGraph extends INode {

    // ======================== Data Identifiers ===================================================

    /** Data Identifier: Converts an {@code IGraphElement} to a {@code KGraphElement}. */
    String TOKGRAPH = "IGraphToKGraph";

    /** Data Identifier: Converts an {@code IGraphElement} to its element in the dual graph. */
    String TODUALGRAPH = "IGraphToDualGraph";

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
     *             if the graph does not contain the source node or target node of the edge
     */
    IEdge addEdge(INode source, INode target) throws InconsistentGraphModelException;

    /**
     * Add a directed edge to the graph. A new, directed edge will be inserted into the graph,
     * connecting the two nodes {@code source} anc {@code target}. This checks if the two given
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
     * @param appondTarget
     *            true to append the edge to the target node, false to prepend the edge
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain the source node or target node of the edge
     */
    IEdge addEdge(INode source, boolean appendSource, INode target, boolean appondTarget)
            throws InconsistentGraphModelException;

    /**
     * Add an undirected edge to the graph. A new, undirected edge will be inserted into the graph,
     * connecting the two nodes {@code node1} and {@code node2}. This checks if the two given nodes
     * are actually part of the graph and throws an exception otherwise. The newly created edge
     * object is returned for further use. The additional parameters {@code appendSource} and
     * {@code appendTarget} specify if the edge is appended or prepended to the adjacency list of
     * {@code source} and {@code target} respectively.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain one of the two given nodes
     */
    IEdge addUndirectedEdge(INode node1, INode node2) throws InconsistentGraphModelException;

    /**
     * Add an undirected edge to the graph. A new, undirected edge will be inserted into the graph,
     * connecting the two nodes {@code node1} and {@code node2}. This checks if the two given nodes
     * are actually part of the graph and throws an exception otherwise. The newly created edge
     * object is returned for further use.
     * 
     * @param node1
     *            the first node
     * @param appendNode1
     *            true to append the edge to the first node, false to prepend the edge
     * @param node2
     *            the second node
     * @param appendNode2
     *            true to append the edge to the second node, false to prepend the edge
     * @return the edge that was just added
     * @throws InconsistentGraphModelException
     *             if the graph does not contain one of the two given nodes
     */
    IEdge addUndirectedEdge(INode node1, boolean appendNode1, INode node2, boolean appendNode2)
            throws InconsistentGraphModelException;

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
