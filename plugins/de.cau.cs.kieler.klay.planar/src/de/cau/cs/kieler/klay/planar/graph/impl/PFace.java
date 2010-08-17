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
package de.cau.cs.kieler.klay.planar.graph.impl;

import java.io.Serializable;
import java.util.HashSet;

import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.util.IFunction;
import de.cau.cs.kieler.klay.planar.util.MappedIterable;

/**
 * A face in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author cku
 * @author ocl
 */
class PFace extends PGraphElement implements IFace, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 595562864080000947L;

    // ======================== Attributes =========================================================

    /** The nodes of this face. */
    private HashSet<INode> nodes;

    /** The edges of this face. */
    private HashSet<IEdge> edges;

    // ======================== Constructor ========================================================

    /**
     * Create a new Face.
     * 
     * @param id
     *            the id assigned to the face
     * @param parent
     *            the graph the face belongs to
     */
    PFace(final int id, final IGraph parent) {
        super(id, parent);
        this.edges = new HashSet<IEdge>();
        this.nodes = new HashSet<INode>();
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public int getAdjacentNodeCount() {
        return this.nodes.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdjacent(final IFace face) { // TODO O(n)
        for (IEdge e : this.edges) {
            if (e.getLeftFace() == this && e.getRightFace() == face) {
                return true;
            } else if (e.getRightFace() == this && e.getLeftFace() == face) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public IFace getAdjacentFace(final IEdge edge) {
        if (edge.getLeftFace() == this) {
            return edge.getRightFace();
        } else if (edge.getRightFace() == this) {
            return edge.getLeftFace();
        } else {
            throw new IllegalArgumentException("The edge (" + edge.getID()
                    + ") is not connected to the face (" + this.getID() + ").");
        }
    }

    /**
     * {@inheritDoc}
     */
    public IEdge getEdge(final IFace face) { // TODO O(n)
        for (IEdge e : this.edges) {
            if (this.getAdjacentFace(e) == face) {
                return e;
            }
        }
        throw new IllegalArgumentException("The face (" + face.getID()
                + ") is not adjacent to the face (" + this.getID() + ").");
    }

    /**
     * Add a new node to the face.
     * 
     * @param node
     *            the new node to add
     */
    void addNode(final INode node) {
        this.nodes.add(node);
    }

    /**
     * Add a new edge to the face.
     * 
     * @param edge
     *            the new edge to add
     */
    void addEdge(final IEdge edge) {
        this.edges.add(edge);
    }

    // ======================== Iterators ==========================================================

    /**
     * {@inheritDoc}
     */
    public Iterable<INode> adjacentNodes() {
        return this.nodes;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> adjacentEdges() {
        return this.edges;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IFace> adjacentFaces() {
        return new MappedIterable<IEdge, IFace>(this.edges, new IFunction<IEdge, IFace>() {
            public IFace evaluate(final IEdge element) {
                return getAdjacentFace(element);
            }
        });
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Face (" + this.getID() + "):\n";
        for (IEdge edge : this.edges) {
            res += "\t" + ((PEdge) edge).toString() + "\n";
        }
        return res;
    }

}
