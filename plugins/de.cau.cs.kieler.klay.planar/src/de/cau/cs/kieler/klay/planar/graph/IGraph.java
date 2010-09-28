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

import de.cau.cs.kieler.core.util.Pair;

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

    // ======================== Graph ==============================================================

    /**
     * Resets the IDs of all nodes, edges and faces in the graph. This guarantees that every ID is
     * at least {@code 0} and at most the number of respective items contained it the graph.
     */
    void reindex();

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
     */
    Pair<INode, IEdge> addNode(IEdge edge);

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
     */
    Pair<INode, IEdge> addNode(IEdge edge, NodeType type);

    /**
     * Remove a node from the graph. The node will be remove, together with all references to it in
     * other nodes as well as all edges connected to the node.
     * 
     * @param node
     *            the node to remove
     */
    void removeNode(INode node);

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
     */
    IEdge addEdge(INode source, INode target);

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
     */
    IEdge addEdge(INode source, INode target, boolean directed);

    /**
     * Remove an edge from the graph. The edge will be removed together will all references to the
     * edge in neighboring nodes.
     * 
     * @param edge
     *            the edge to remove
     */
    void removeEdge(IEdge edge);

    // ======================== Faces ==============================================================

    /**
     * Get all faces in this graph. This provides an {@code Iterable} object to gain access to all
     * faces that are part of the graph.
     * 
     * @return {@code Iterable} containing all graph faces
     */
    Iterable<IFace> getFaces();

    /**
     * Get the number of faces in the graph.
     * 
     * @return the number of faces
     */
    int getFaceCount();

}
