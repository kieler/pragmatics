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

import java.util.LinkedList;

import de.cau.cs.kieler.klay.planar.graph.IEmbeddingConstraint.IConstraintTreeNode;

/**
 * An inner node in a constraint tree for embedding constraints.
 * 
 * @author ocl
 */
class PConstraintTreeNode implements IConstraintTreeNode {

    // ======================== Attributes =========================================================

    /** The type of embedding constraint of the node. */
    private ConstraintType constraintType;

    /** The list of children in the constraint tree. */
    private LinkedList<IEmbeddingConstraint> children;

    // ======================== Constructor ========================================================

    /**
     * Create a new inner node for an embedding constraint tree.
     * 
     * @param type
     *            the type of embedding constraint the node represents
     */
    PConstraintTreeNode(final ConstraintType type) {
        this.constraintType = type;
        this.children = new LinkedList<IEmbeddingConstraint>();
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public ConstraintType getType() {
        return this.constraintType;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEmbeddingConstraint> getChildren() {
        return this.children;
    }

    /**
     * {@inheritDoc}
     */
    public IConstraintTreeNode addNode(final ConstraintType type) {
        IConstraintTreeNode node = new PConstraintTreeNode(type);
        this.children.add(node);
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public IConstraintTreeLeaf addLeaf(final PEdge edge) {
        IConstraintTreeLeaf leaf = new PConstraintTreeLeaf(edge);
        this.children.add(leaf);
        return leaf;
    }

}
