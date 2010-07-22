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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

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

    /** A list of all edges of the node. */
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
    public Iterable<IEdge> getAllEdges() {
        return this.edges;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> getIncomingEdges() {
        final INode instance = this;
        return new de.cau.cs.kieler.core.util.FilteredIterator.Iterable<IEdge>(this.edges,
                new ICondition<IEdge>() {
                    public boolean evaluate(final IEdge object) {
                        return (object.getTarget() == instance);
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> getOutgoingEdges() {
        final INode instance = this;
        return new de.cau.cs.kieler.core.util.FilteredIterator.Iterable<IEdge>(this.edges,
                new ICondition<IEdge>() {
                    public boolean evaluate(final IEdge object) {
                        return (object.getSource() == instance);
                    }
                });
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
    public List<IEdge> getEdgeList() {
        return this.edges;
    }

    /**
     * Connect an edge to this node.
     * 
     * @param edge
     *            the edge to add
     */
    void linkEdge(final IEdge edge) {
        this.edges.add(edge);
    }

    /**
     * Connect an edge to this node. The node is either appended or prepended to the adjacency list,
     * depending on the parameter.
     * 
     * @param edge
     *            the edge to add
     * @param append
     *            append edge to list if true, prepend if false
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
     * only the reference to it in this node.
     * 
     * @param edge
     *            the edge to remove
     * @throws InconsistentGraphModelException
     *             if the given edge {@code e} is not connected to this node
     */
    void unlinkEdge(final IEdge edge) throws InconsistentGraphModelException {
        // TODO this requires linear time!
        // Check for graph consistency
        if (!this.edges.contains(edge)) {
            throw new InconsistentGraphModelException(
                    "Attempted to remove non-existent edge reference in node");
        }
        this.edges.remove(edge);
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<INode> getAdjacentNodes() {
        // Create anonymous iterator that wraps the edges
        return new Iterable<INode>() {
            public Iterator<INode> iterator() {
                return new Iterator<INode>() {
                    private final Iterator<IEdge> es = edges.iterator();

                    public boolean hasNext() {
                        return es.hasNext();
                    }

                    public INode next() {
                        try {
                            return getAdjacentNode(es.next());
                        } catch (InconsistentGraphModelException e) {
                            return null;
                        }
                    }

                    public void remove() {
                        es.remove();
                    }
                };
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdjacent(final INode node) {
        // TODO this requires linear time
        for (IEdge e : this.edges) {
            if (e.getSource() == this && e.getTarget() == node) {
                return true;
            }
            if (e.getTarget() == this && e.getSource() == node) {
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
        // TODO this requires linear time
        for (IEdge e : this.edges) {
            if (this.getAdjacentNode(e) == node) {
                return e;
            }
        }
        throw new InconsistentGraphModelException("Attempted to get edge from non-adjacent node");
    }

    // ======================== Modify Adjacency List ==============================================

    /**
     * {@inheritDoc}
     */
    public void reverseAdjacencyList() {
        LinkedList<IEdge> newlist = new LinkedList<IEdge>();
        for (IEdge edge : this.edges) {
            newlist.addFirst(edge);
        }
        this.edges = newlist;
    }

    /**
     * {@inheritDoc}
     */
    public void sortAdjacencyList(final Comparator<IEdge> comp) {
        Collections.sort(this.edges, comp);
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
        if (!(node instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to merge node of incompatible type.");
        }
        LinkedList<IEdge> edgelist = new LinkedList<IEdge>();
        for (IEdge edge : node.getAllEdges()) {
            if (append) {
                edgelist.addLast(edge);
            } else {
                edgelist.addFirst(edge);
            }
        }
        for (IEdge edge : edgelist) {
            edge.move(node, this, append);
        }
        this.getParent().removeNode(node);
        this.copyProperties((PNode) node);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    @Override
    public String toString() {
        String res = "Node (" + this.getID() + "):\n";
        for (IEdge edge : this.edges) {
            res += "\t" + ((PEdge) edge).toString() + "\n";
        }
        return res;
    }

}
