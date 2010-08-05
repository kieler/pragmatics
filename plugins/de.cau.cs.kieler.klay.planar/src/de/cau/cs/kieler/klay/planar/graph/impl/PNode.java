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
import java.util.LinkedList;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IEmbeddingConstraint;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.IEmbeddingConstraint.ConstraintType;
import de.cau.cs.kieler.klay.planar.util.FilteredIterable;
import de.cau.cs.kieler.klay.planar.util.IFunction;
import de.cau.cs.kieler.klay.planar.util.MappedIterable;

/**
 * A node in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 */
class PNode extends PGraphElement implements INode, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 1943479208604255107L;

    // ======================== Attributes =========================================================

    /** The type of the node. */
    private NodeType type;

    /** The embedding constraint applied to this node. */
    private IEmbeddingConstraint constraint;

    /** The list of edges adjacent to the node. */
    private LinkedList<IEdge> edges;

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
        this.constraint = null;
        this.edges = new LinkedList<IEdge>();
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
    public int getAdjacentEdgeCount() {
        return this.edges.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdjacent(final INode node) {
        for (IEdge e : this.edges) {
            if (e.getSource() == this && e.getTarget() == node) {
                return true;
            } else if (e.getTarget() == this && e.getSource() == node) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public INode getAdjacentNode(final IEdge edge) throws InconsistentGraphModelException {
        if (edge.getSource() == this) {
            return edge.getTarget();
        } else if (edge.getTarget() == this) {
            return edge.getSource();
        } else {
            throw new InconsistentGraphModelException(
                    "Attempted to get adjacent node from unconnected edge");
        }
    }

    /**
     * {@inheritDoc}
     */
    public IEdge getEdge(final INode node) throws InconsistentGraphModelException {
        for (IEdge e : this.edges) {
            if (this.getAdjacentNode(e) == node) {
                return e;
            }
        }
        throw new InconsistentGraphModelException("Attempted to get edge from non-adjacent node");
    }

    /**
     * Connect an edge to this node. The parameter {@code append} determines if the edge is added at
     * the start or the end of the adjacency list.
     * 
     * @param edge
     *            the edge to add
     * @param append
     *            append edge to list if true, prepend if false
     * @return the port the edge was linked to
     */
    void linkEdge(final IEdge edge, final boolean append) {
        if (append) {
            this.edges.addLast(edge);
        } else {
            this.edges.addFirst(edge);
        }
    }

    /**
     * Remove an edge from this node. Note that this does not remove the edge from the graph, but
     * only the reference to it in this component.
     * 
     * @param edge
     *            the edge to remove
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not connected to this node
     */
    void unlinkEdge(final IEdge edge) throws InconsistentGraphModelException {
        this.edges.remove(edge);
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasEmbeddingConstraint() {
        return this.constraint == null;
    }

    /**
     * {@inheritDoc}
     */
    public IEmbeddingConstraint getEmbeddingConstraint() {
        return this.constraint;
    }

    /**
     * {@inheritDoc}
     */
    public IEmbeddingConstraint applyEmbeddingConstraint(final ConstraintType constraintType) {
        this.constraint = new PConstraintTreeNode(constraintType);
        return this.constraint;
    }

    // ======================== Iterators ==========================================================

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> adjacentEdges() {
        return this.edges;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<INode> adjacentNodes() {
        return new MappedIterable<IEdge, INode>(this.edges, new IFunction<IEdge, INode>() {
            public INode evaluate(final IEdge element) {
                try {
                    return getAdjacentNode(element);
                } catch (InconsistentGraphModelException e) {
                    return null;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> incomingEdges() {
        final INode instance = this;
        return new FilteredIterable<IEdge>(this.edges, new ICondition<IEdge>() {
            public boolean evaluate(final IEdge object) {
                return object.isDirected() && object.getTarget() == instance;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> outgoingEdges() {
        final INode instance = this;
        return new FilteredIterable<IEdge>(this.edges, new ICondition<IEdge>() {
            public boolean evaluate(final IEdge object) {
                return object.isDirected() && object.getSource() == instance;
            }
        });
    }

    // ======================== Miscellaneous Functions ============================================

    /**
     * {@inheritDoc}
     */
    public void mirror() {
        LinkedList<IEdge> reversed = new LinkedList<IEdge>();
        for (IEdge edge : this.edges) {
            reversed.addFirst(edge);
        }
        this.edges = reversed;
    }

    /**
     * {@inheritDoc}
     */
    public void sort(final IFunction<IEdge, Integer> func) {
        this.sort(func, true);
    }

    /**
     * {@inheritDoc}
     */
    // Unchecked cast used to create array of linked lists
    @SuppressWarnings("unchecked")
    public void sort(final IFunction<IEdge, Integer> func, final boolean ascending) {
        if (this.edges.size() <= 1) {
            return;
        }

        // Determine minimum and maximum values
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (IEdge edge : this.edges) {
            int value = func.evaluate(edge);
            min = Math.min(value, min);
            max = Math.max(value, max);
        }

        // Sort by Bucket Sort
        LinkedList<IEdge>[] buckets = new LinkedList[max - min + 1];
        for (IEdge edge : this.edges) {
            int value = func.evaluate(edge) - min;
            if (buckets[value] == null) {
                buckets[value] = new LinkedList<IEdge>();
            }
            buckets[value].add(edge);
        }
        this.edges.clear();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && !buckets[i].isEmpty()) {
                if (ascending) {
                    this.edges.addAll(buckets[i]);
                } else {
                    this.edges.addAll(0, buckets[i]);
                }
            }
        }
    }

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

        if (!(node instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to merge node of incompatible type.");
        }

        // Create temporary list to avoid breaking the iterator
        LinkedList<IEdge> toadd = new LinkedList<IEdge>();
        for (IEdge edge : node.adjacentEdges()) {
            if (append) {
                toadd.addLast(edge);
            } else {
                toadd.addFirst(edge);
            }
        }
        for (IEdge edge : toadd) {
            edge.move(node, this, append);
        }

        this.getParent().removeNode(node);
        this.copyProperties((PNode) node);
    }

    @Override
    public String toString() {
        String res = "Node (" + this.getID() + "):\n";
        for (IEdge edge : this.edges) {
            res += "\t" + edge.toString() + "\n";
        }
        return res;
    }

}
