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

import de.cau.cs.kieler.klay.planar.graph.IAdjacencyList;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.IAdjacencyList.AdjacencyListType;

/**
 * A node in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 */
public class PNode extends PGraphElement implements INode, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 1943479208604255107L;

    // ======================== Attributes =========================================================

    /** The type of the node. */
    private NodeType type;

    /** The adjacency list of the node. */
    private IAdjacencyList adjlist;

    // ======================== Constructor ========================================================

    /**
     * Default Constructor. Creates a new normal node.
     * 
     * @param id
     *            the id assigned to the node
     * @param parent
     *            the parent graph
     */
    PNode(final int id, final IGraph parent) {
        this(id, parent, NodeType.NORMAL);
    }

    /**
     * Create a new node of type {@code t}.
     * 
     * @param id
     *            the id assigned to the node
     * @param parent
     *            the parent graph
     * @param t
     *            the type of the node
     */
    PNode(final int id, final IGraph parent, final NodeType t) {
        super(id, parent);
        this.type = t;
        this.adjlist = new PAdjacencyList(this, null, AdjacencyListType.FREE);
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public NodeType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    public IAdjacencyList getAdjacencyList() {
        return this.adjlist;
    }

    // ======================== Miscellaneous Functions ============================================

    /**
     * {@inheritDoc}
     */
    public void merge(final INode node) throws InconsistentGraphModelException {
        this.merge(node, true);
    }

    /**
     * {@inheritDoc}
     */
    public void merge(final INode node, final boolean append)
            throws InconsistentGraphModelException {

        if (!(this.getParent() instanceof PGraph)) {
            throw new InconsistentGraphModelException(
                    "Attempted to merge nodes in graph of incompatible type.");
        }
        if (!(node instanceof PNode) || !(node.getAdjacencyList() instanceof PAdjacencyList)) {
            throw new InconsistentGraphModelException(
                    "Attempted to merge node of incompatible type.");
        }

        ((PAdjacencyList) this.adjlist).merge(node.getAdjacencyList(), append);
        this.getParent().removeNode(node);
        this.copyProperties((PNode) node);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    @Override
    public String toString() {
        return "Node (" + this.getID() + "):\n" + this.adjlist.toString();
    }

}
