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
 * A face in the interface for a general graph data structure.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraph {@code IGraph}
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author cku
 * @author ocl
 */
public interface IFace extends IGraphElement {
    // TODO guarantee edge and node order
    // TODO is left/right face correct according to source/target?

    // ======================== Getters and Setters ================================================

    /**
     * Get the number of nodes adjacent to the face.
     * 
     * @return the number of adjacent nodes
     */
    int getAdjacentNodeCount();

    /**
     * Check if a face is adjacent to this face (i.e. an edge exists that connects the two faces).
     * 
     * @param face
     *            the face to check for
     * @return true if {@code face} is adjacent to this face
     */
    boolean isAdjacent(IFace face);

    /**
     * Get an adjacent face specified by the connecting edge. This returns the face directly
     * adjacent to this face, connected by the given edge {@code edge}.
     * 
     * @param edge
     *            the edge that connects the faces
     * @return the adjacent face of this face regarding {@code e}
     */
    IFace getAdjacentFace(IEdge edge);

    /**
     * Get the edge to an adjacent face. This returns the edge that connects this face with the
     * given face {@code face}.
     * 
     * @param face
     *            the adjacent face
     * @return the edge that connects this face to {@code face}
     */
    IEdge getEdge(IFace face);

    // ======================== Iterators ==========================================================

    /**
     * Get the {@code PNode}s which describe this face. Returns an {@code Iterable} object
     * containing all {@code INode}s that lay on the border of this face.
     * 
     * @return the surrounding nodes
     */
    Iterable<INode> adjacentNodes();

    /**
     * Get the {@code IEgde}s surrounding this edge. Returns an {@code Iterable} object containing
     * all {@code IEdge}s that define the border of this edge.
     * 
     * @return iterable object containing the surrounding edges
     */
    Iterable<IEdge> adjacentEdges();

    /**
     * Get the {@code IFace}s surrounding this face. Returns an {@code Iterable} object containing
     * all {@code IFace}s adjacent to this face.
     * 
     * @return iterable object containing all adjacent faces
     */
    Iterable<IFace> adjacentFaces();

}
