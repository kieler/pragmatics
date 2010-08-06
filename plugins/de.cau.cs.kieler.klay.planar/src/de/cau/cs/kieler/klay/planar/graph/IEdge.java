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

import java.util.Collection;

/**
 * An edge in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 */
public interface IEdge extends IGraphElement {

    /**
     * Check if the edge is a directed or an undirected edge.
     * 
     * @return true if the edge is directed, false if undirected
     */
    boolean isDirected();

    /**
     * Get the {@code INode}s that are connected by this edge. This will return a {@code Collection}
     * containing at most two {@code INode}s, specifically the source and the target node of this
     * edge. This method is provided because in some cases (especially in undirected graphs) it may
     * be convenient to get the nodes of an edge without specifying if the node is source or target.
     * 
     * @return a {@code Collection} containing the nodes this edge connects
     */
    Collection<INode> getNodes();

    /**
     * Get the {@code INode} in which this edge originates. In undirected graphs, one of the two
     * connected {@code INode}s is returned.
     * 
     * @return the source node
     */
    INode getSource();

    /**
     * Get the {@code INode} this edge points to. In undirected graphs, the {@code INode} that is
     * not returned by {@code getSourceNode()} is returned.
     * 
     * @return the target node
     */
    INode getTarget();

    /**
     * Get the {@code IFace}s that are formed by this edge. This will return a {@code Collection}
     * containing at most two {@code IFace}s, specifically the left and right face of this edge.
     * 
     * @return a {@code Collection} containing the faces formed by this edge
     */
    Collection<IFace> getFaces();

    /**
     * Get the {@code IFace} on the right side of this edge.
     * 
     * @return the right face
     */
    IFace getRightFace();

    /**
     * Get the {@code IFace} on the left side of this edge.
     * 
     * @return the left face
     */
    IFace getLeftFace();

    // ======================== Miscellaneous Functions ============================================

    /**
     * Move the edge from one node to another. This will remove the edge from the adjacency list of
     * the first node, and will add itself to the adjacency list of the second node. The source and
     * target of the edge will be set accordingly.
     * 
     * @param from
     *            the node from which this edge will be removed
     * @param to
     *            the node to which this edge will be moved
     */
    void move(INode from, INode to);

}
