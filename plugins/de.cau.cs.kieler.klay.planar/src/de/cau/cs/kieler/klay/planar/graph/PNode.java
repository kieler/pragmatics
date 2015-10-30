/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.FilteredIterable;
import de.cau.cs.kieler.klay.planar.util.IFunction;
import de.cau.cs.kieler.klay.planar.util.MappedIterable;

/**
 * A node in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 * @author pkl
 * @kieler.rating proposed yellow by pkl
 * 
 */
public class PNode extends PShape {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 1943479208604255107L;

    /**
     * An enum that defines the general type of a node. Most graph nodes will be {@code NORMAL}
     * nodes.
     * 
     * @author ocl
     * @author pkl
     */
    public enum NodeType {
        /**
         * Normal graph nodes. Most nodes are of this type.
         */
        NORMAL,

        /**
         * A node that represents a face, normally used in network graphs.
         */
        FACE,

        /**
         * Compound nodes. Compound nodes are nodes that again represent graphs. They contain nodes
         * and edges themselves, and may even contain other compound nodes.
         */
        COMPOUND
    }

    // ======================== Attributes ===================================================

    /** The type of the node. */
    private NodeType type;

    /** The list of edges adjacent to the node. */
    private LinkedList<PEdge> edges;

    // ======================== Constructor ===================================================

    /**
     * Default Constructor. Creates a new normal node.
     * 
     * @param id
     *            the id assigned to the node
     * @param parent
     *            the parent graph
     */
    PNode(final int id, final PGraph parent) {
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
    PNode(final int id, final PGraph parent, final NodeType t) {
        super(id, parent);
        this.type = t;
        this.setEdges(new LinkedList<PEdge>());
    }

    // ======================== Getters and Setters ==============================

    /**
     * Get the type of the node.
     * 
     * @see de.cau.cs.kieler.klay.planar.graph.PNode.NodeType {@code Types}
     * 
     * @return the node type
     */
    public NodeType getType() {
        return this.type;
    }

    /**
     * Get the number of edges in the adjacency list of the node.
     * 
     * @return the number of connected edges
     */
    public int getAdjacentEdgeCount() {
        return this.edges.size();
    }

    /**
     * Check if a node is adjacent to this node (i.e. an edge exists that connects the two nodes).
     * 
     * @param node
     *            the node to check for
     * @return true if {@code node} is adjacent to this node
     */
    public boolean isAdjacent(final PNode node) {
        for (PEdge e : this.edges) {
            if (e.getSource() == this && e.getTarget() == node) {
                return true;
            } else if (e.getTarget() == this && e.getSource() == node) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an adjacent node specified by the connecting edge. This returns the node directly
     * adjacent to this node, connected by the given edge {@code edge}.
     * 
     * @param edge
     *            the edge that connects the nodes
     * @return the adjacent node of this node regarding edge {@code edge}
     * 
     * @throws IllegalArgumentException
     *             if the parameter edge isn't connected to the node.
     */
    public PNode getAdjacentNode(final PEdge edge) {
        if (edge.getSource() == this) {
            return edge.getTarget();
        } else if (edge.getTarget() == this) {
            return edge.getSource();
        } else {
            throw new IllegalArgumentException("The edge (" + edge.id
                    + ") is not connected to the node (" + this.id + ").");
        }
    }

    /**
     * Get the edge to an adjacent node. This returns the edge that connects this node with the
     * given node {@code node}.
     * 
     * @param node
     *            the adjacent node
     * @return the edge that connects this node to {@code node}
     * 
     * @throws IllegalArgumentException
     *             if there is no edge to the parameter node
     */
    public PEdge getEdge(final PNode node) {
        for (PEdge e : this.edges) {
            if (this.getAdjacentNode(e) == node) {
                return e;
            }
        }
        throw new IllegalArgumentException("The node (" + node.id
                + ") is not adjacent to the node (" + this.id + ").");
    }

    /**
     * Gives the edge for a edgeId.
     * 
     * @param id
     *            , edgeId
     * @return {@link PEdge}, returns the found edge.
     * @throws IllegalArgumentException
     *             if there is no edge for the given id.
     */
    public PEdge getEdge(final int id) {
        for (PEdge e : this.edges) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("The edge with id " + this.id + " does not exist.");
    }

    /**
     * Connect an edge to this node. The parameter {@code append} determines if the edge is added at
     * the end of the adjacency list.
     * 
     * @param edge
     *            the edge to add
     */
    public void linkEdge(final PEdge edge) {
        this.edges.add(edge);
    }

    /**
     * Remove an edge from this node. Note that this does not remove the edge from the graph, but
     * only the reference to it in this component.
     * 
     * @param edge
     *            the edge to remove
     */
    public void unlinkEdge(final PEdge edge) {
        this.edges.remove(edge);
    }

    // ======================== Iterators =================================================

    /**
     * Iterate over all edges that are part of the adjacency list.
     * 
     * @return iterable object containing all edges in the list
     */
    public Iterable<PEdge> adjacentEdges() {
        return this.edges;
    }

    /**
     * Iterate over all nodes adjacent to this node.
     * 
     * @return iterable object containing adjacent nodes
     */
    public Iterable<PNode> adjacentNodes() {
        return new MappedIterable<PEdge, PNode>(this.edges, new IFunction<PEdge, PNode>() {
            public PNode evaluate(final PEdge element) {
                return getAdjacentNode(element);
            }
        });
    }

    /**
     * Iterate over all incoming edges that are part of the adjacency list. Incoming edges are all
     * edges whose target is the node this list belongs to. Undirected edge are neither incoming nor
     * outgoing edges.
     * 
     * @return iterable object containing incoming edges
     */
    public Iterable<PEdge> incomingEdges() {
        final PNode instance = this;
        return new FilteredIterable<PEdge>(this.edges, new Predicate<PEdge>() {
            public boolean apply(final PEdge object) {
                return object.isDirected() && object.getTarget() == instance;
            }
        });
    }

    /**
     * Iterate over all outgoing edges that are part of the adjacency list. Incoming edges are all
     * edges whose source is the node this list belongs to. Undirected edge are neither incoming nor
     * outgoing edges.
     * 
     * @return {@link Iterable}, iterable object containing outgoing edges
     */
    public Iterable<PEdge> outgoingEdges() {
        final PNode instance = this;
        return new FilteredIterable<PEdge>(this.edges, new Predicate<PEdge>() {
            public boolean apply(final PEdge object) {
                return object.isDirected() && object.getSource() == instance;
            }
        });
    }

    // ======================== Miscellaneous Functions ==========================================

    /**
     * Mirror the adjacency list.
     */
    public void mirror() {
        LinkedList<PEdge> reversed = new LinkedList<PEdge>();
        for (PEdge edge : this.edges) {
            reversed.addFirst(edge);
        }
        this.setEdges(reversed);
    }

    /**
     * Sort the adjacency list using a function. The function associates every edge in the adjacency
     * list with an integer value. The adjacency list is sorted in ascending order by that value
     * using a bucket sort.
     * 
     * @param func
     *            the function that assigns a value to a port
     */
    public void sort(final IFunction<PEdge, Integer> func) {
        this.sort(func, true);
    }

    /**
     * Sort the adjacency list using a function. The function associates every edge in the adjacency
     * list with an integer value. The adjacency list can be sorted either in ascending or
     * descending order of the function values.
     * 
     * @param func
     *            the function that assigns a value to a port
     * @param ascending
     *            specifies if the list is sorted in ascending or descending order
     */
    public void sort(final IFunction<PEdge, Integer> func, final boolean ascending) {
        if (this.edges.size() <= 1) {
            return;
        }

        // Determine minimum and maximum values
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (PEdge edge : this.edges) {
            int value = func.evaluate(edge);
            min = Math.min(value, min);
            max = Math.max(value, max);
        }

        // Sort by Bucket Sort
        @SuppressWarnings("unchecked")
        LinkedList<PEdge>[] buckets = new LinkedList[max - min + 1];
        for (PEdge edge : this.edges) {
            int value = func.evaluate(edge) - min;
            if (buckets[value] == null) {
                buckets[value] = new LinkedList<PEdge>();
            }
            buckets[value].add(edge);
        }
        this.getEdges().clear();
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
     * Move an edge in the adjacency list to the beginning of the list.
     * 
     * @param edge
     *            the edge to move
     */
    public void moveToStart(final PEdge edge) {
        int count = 0;
        Iterator<PEdge> iter = this.edges.iterator();
        while (iter.hasNext()) {
            if (iter.next() == edge) {
                iter.remove();
                count++;
            }
        }
        while (count > 0) {
            count--;
            this.edges.addFirst(edge);
        }
    }

    /**
     * Move an edge in the adjacency list to the end of the list.
     * 
     * @param edge
     *            the edge to move
     */
    public void moveToEnd(final PEdge edge) {
        int count = 0;
        Iterator<PEdge> iter = this.edges.iterator();
        while (iter.hasNext()) {
            if (iter.next() == edge) {
                iter.remove();
                count++;
            }
        }
        while (count > 0) {
            count--;
            this.edges.addLast(edge);
        }
    }

    @Override
    public String toString() {
        String res = "Node (" + this.id + "):\n";
        for (PEdge edge : this.edges) {
            res += "\t" + edge.toString() + "\n";
        }
        return res;
    }

    /**
     * @return the edges
     */
    public Collection<PEdge> getEdges() {
        return edges;
    }

    /**
     * @param edges
     *            the edges to set
     */
    public void setEdges(final LinkedList<PEdge> edges) {
        this.edges = edges;
    }

    /**
     * Checks if the properties are not null and not empty.
     * 
     * @return true if it is not empty otherwise false.
     */
    public boolean hasProperties() {
        return super.getAllProperties() != null && !super.getAllProperties().isEmpty();
    }

    /**
     * Sets the original position of a node with the given x and y parameters.
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public void setPostion(final Float x, final Float y) {
        if (hasProperties() && getProperty(Properties.ORIGIN) instanceof KNode) {
            KShapeLayout nodeLayout = ((KNode) getProperty(Properties.ORIGIN))
                    .getData(KShapeLayout.class);
            if (x != null) {
                nodeLayout.setXpos(x);
            }
            if (y != null) {
                nodeLayout.setYpos(y);
            }
        }
    }

    /**
     * Unlinks all edges.
     */
    public void unlinkAll() {
        this.edges.clear();
    }

    /**
     * links all edges of the collection.
     * 
     * @param linkEdges
     *            to be linkable edges
     */
    public void linkEdges(final Collection<PEdge> linkEdges) {
        this.edges.addAll(linkEdges);
    }

    /**
     * Orders the angles like the edge order of the node.
     */
    public void orderAngles() {
        OrthogonalRepresentation ortho = getParent().getProperty(Properties.ORTHO_REPRESENTATION);
        if (ortho == null) {
            throw new InconsistentGraphModelException(
                    "To use this method, a orthogonal representation is needed!");
        }
        List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(this);
        List<Pair<PEdge, OrthogonalAngle>> orderedAngles = Lists.newLinkedList();
        for (PEdge edge : adjacentEdges()) {
            for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                if (edge == pair.getFirst()) {
                    orderedAngles.add(pair);
                }
            }
        }
        angles.clear();
        angles.addAll(orderedAngles);
    }
}
