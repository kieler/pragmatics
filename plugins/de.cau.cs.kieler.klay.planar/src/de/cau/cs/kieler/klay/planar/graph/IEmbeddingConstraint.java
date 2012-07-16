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
 * Provides an interface for composing embedding constraints. This uses the composite design
 * pattern, to create a constraint tree. A constraint tree is a tree structure, whose leaves each
 * hold an edge on the constrained graph node. The inner nodes of the constraint tree are of a
 * certain type which specifies to what extend the order of the child nodes may be modified so that
 * it is still valid.
 * 
 * @author ocl
 */
public interface IEmbeddingConstraint {

    /**
     * The type of an inner node in the constraint tree. The type of a node specifies to what extend
     * the order of the child nodes may be modified so that is still valid.
     * 
     * @author ocl
     */
    public enum ConstraintType {

        /** Group nodes have no specific order and may freely be modified. */
        GROUP,

        /** Mirror nodes have a fixed order, which may still be reversed. */
        MIRROR,

        /** Ordered nodes have a fixed order and may not be modified at all. */
        ORDERED
    }

    /**
     * An inner node in the constraint tree.
     * 
     * @author ocl
     */
    public interface IConstraintTreeNode extends IEmbeddingConstraint {

        /**
         * Get the type of the inner node.
         * 
         * @return the constraint type
         */
        ConstraintType getType();

        /**
         * Iterate over the child nodes in the tree.
         * 
         * @return iterable object containing the child nodes
         */
        Iterable<IEmbeddingConstraint> getChildren();

        /**
         * Add a child node with a certain constraint type to the tree.
         * 
         * @param type
         *            the type of the new inner node
         * @return the newly created node
         */
        IConstraintTreeNode addNode(ConstraintType type);

        /**
         * Add a leaf to the constraint tree.
         * 
         * @param edge
         *            the edge the leaf object will hold
         * @return the newly created node
         */
        IConstraintTreeLeaf addLeaf(PEdge edge);

    }

    /**
     * A leaf in the constraint tree. Each leaf in the tree will correspond to exactly one edge
     * adjacent to the node this embedding constraint applies to.
     * 
     * @author ocl
     */
    public interface IConstraintTreeLeaf extends IEmbeddingConstraint {

        /**
         * Get the edge associated with the leaf.
         * 
         * @return the associated edge
         */
        PEdge getEdge();

    }

}
