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
    public Iterable<INode> getNodes() {
        return this.nodes;
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
     * {@inheritDoc}
     */
    public Iterable<IEdge> getEdges() {
        return this.edges;
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
