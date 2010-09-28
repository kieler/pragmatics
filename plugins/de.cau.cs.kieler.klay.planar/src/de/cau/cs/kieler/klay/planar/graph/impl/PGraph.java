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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.IncompatibleGraphTypeException;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

/**
 * A basic graph data structure for various graph theory algorithms. This implementation of the
 * basic graph model is based on {@code LinkedHashSet}s for the management of nodes and edges, thus
 * performing almost all operations on graphs in constant time. Note that all information regarding
 * faces are not changed dynamically in this implementation. Any algorithm using face information
 * frequently while still changing the graph should use a different graph implementation.
 * 
 * @author ocl
 * @author pdo
 * @author cku
 */
class PGraph extends PNode implements IGraph, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = -7340177117233615855L;

    // ======================== Attributes =========================================================

    /** Set of nodes in the graph. */
    private final LinkedHashSet<INode> nodes;

    /** The list of edges in the graph. */
    private final LinkedHashSet<IEdge> edges;

    /** The list of faces in the graph. */
    private final LinkedHashSet<IFace> faces;

    /** The next free index for nodes. */
    private int nodeIndex;

    /** The next free index for edges. */
    private int edgeIndex;

    /** The next free index for faces. */
    private int faceIndex;

    /** Remember if the faces have to be regenerated. */
    private boolean changedFaces;

    // ======================== Constructor ========================================================

    /**
     * Default Constructor. Creates an empty graph.
     */
    PGraph() {
        this(-1, (PGraph) null);
    }

    /**
     * Create a new graph as compound node of graph {@code p}.
     * 
     * @param id
     *            the id assigned to the graph
     * @param parent
     *            the parent graph containing the compound node
     */
    PGraph(final int id, final PGraph parent) {
        super(id, parent, NodeType.COMPOUND);
        this.nodes = new LinkedHashSet<INode>();
        this.edges = new LinkedHashSet<IEdge>();
        this.faces = new LinkedHashSet<IFace>();
        this.changedFaces = true;
        this.nodeIndex = 0;
        this.edgeIndex = 0;
        this.faceIndex = 0;
    }

    // ======================== Graph ==============================================================

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        this.nodeIndex = 0;
        for (INode n : this.nodes) {
            ((PNode) n).setID(this.nodeIndex++);
        }
        this.edgeIndex = 0;
        for (IEdge e : this.edges) {
            ((PEdge) e).setID(this.edgeIndex++);
        }
        this.faceIndex = 0;
        for (IFace f : this.faces) {
            ((PFace) f).setID(this.faceIndex++);
        }
    }

    // ======================== Nodes ==============================================================

    /**
     * {@inheritDoc}
     */
    public Iterable<INode> getNodes() {
        return this.nodes;
    }

    /**
     * {@inheritDoc}
     */
    public int getNodeCount() {
        return this.nodes.size();
    }

    /**
     * {@inheritDoc}
     */
    public INode addNode() {
        return this.addNode(NodeType.NORMAL);
    }

    /**
     * {@inheritDoc}
     */
    public Pair<INode, IEdge> addNode(final IEdge edge) {
        return this.addNode(edge, NodeType.NORMAL);
    }

    /**
     * {@inheritDoc}
     */
    public INode addNode(final NodeType type) {
        PNode node;
        if (type == NodeType.COMPOUND) {
            node = new PGraph(this.nodeIndex++, this);
            this.nodes.add(node);
        } else {
            node = new PNode(this.nodeIndex++, this, type);
            this.nodes.add(node);
        }
        this.changedFaces = true;
        return node;
    }

    /**
     * {@inheritDoc}
     */
    public Pair<INode, IEdge> addNode(final IEdge edge, final NodeType type) {
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!(edge.getLeftFace() instanceof PFace && edge.getRightFace() instanceof PFace)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.edges.contains(edge)) {
            throw new IllegalArgumentException("The edge (" + edge.getID()
                    + ") is not part of the graph.");
        }
        this.generateFaces();

        // Remember target node
        INode target = edge.getTarget();

        // Remember all edges in target adjacency list after the edge
        LinkedList<IEdge> move = new LinkedList<IEdge>();
        boolean found = false;
        for (IEdge e : target.adjacentEdges()) {
            if (found) {
                move.addLast(e);
            } else if (e == edge) {
                found = true;
            }
        }

        // Create node, move edge and create new edge
        PNode node = (PNode) this.addNode(type);
        edge.move(target, node);
        PEdge newedge = (PEdge) this.addEdge(node, target, edge.isDirected());

        // Move remembered edges to end of adjacency list (so new edge is at correct position)
        for (IEdge e : move) {
            e.move(target, target);
        }

        // Update references in faces
        this.changedFaces = false;
        newedge.setLeftFace(edge.getLeftFace());
        newedge.setRightFace(edge.getRightFace());
        ((PFace) edge.getLeftFace()).addNode(node);
        ((PFace) edge.getLeftFace()).addEdge(newedge);
        ((PFace) edge.getRightFace()).addNode(node);
        ((PFace) edge.getRightFace()).addEdge(newedge);

        return new Pair<INode, IEdge>(node, newedge);
    }

    /**
     * {@inheritDoc}
     */
    public void removeNode(final INode node) {
        // Check for graph consistency
        if (!this.nodes.contains(node)) {
            throw new IllegalArgumentException("The node (" + node.getID()
                    + ") is not part of the graph.");
        }
        // Remove node
        this.nodes.remove(node);

        // Remove all edges
        for (Iterator<IEdge> es = node.adjacentEdges().iterator(); es.hasNext();) {
            IEdge edge = es.next();

            // Check for graph consistency (again)
            if (!(node.getAdjacentNode(edge) instanceof PNode)) {
                throw new IncompatibleGraphTypeException();
            } else if (!this.edges.contains(edge)) {
                throw new InconsistentGraphModelException("The edge (" + edge.getID()
                        + ") is not part of the graph.");
            }

            ((PNode) node.getAdjacentNode(edge)).unlinkEdge(edge);
            this.edges.remove(edge);
            es.remove();
        }
        this.changedFaces = true;
    }

    // ======================== Edges ==============================================================

    /**
     * {@inheritDoc}
     */
    public Iterable<IEdge> getEdges() {
        return this.edges;
    }

    /**
     * {@inheritDoc}
     */
    public int getEdgeCount() {
        return this.edges.size();
    }

    /**
     * {@inheritDoc}
     */
    public IEdge addEdge(final INode source, final INode target) {
        return this.addEdge(source, target, true);
    }

    /**
     * {@inheritDoc}
     */
    public IEdge addEdge(final INode source, final INode target, final boolean directed) {
        // Check for graph consistency
        if (!(source instanceof PNode && target instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.nodes.contains(source)) {
            throw new IllegalArgumentException("The node (" + source.getID()
                    + ") is not part of the graph.");
        } else if (!this.nodes.contains(target)) {
            throw new IllegalArgumentException("The node (" + target.getID()
                    + ") is not part of the graph.");
        }

        IEdge edge = new PEdge(this.edgeIndex++, this, source, target, directed);
        ((PNode) source).linkEdge(edge);
        ((PNode) target).linkEdge(edge);
        this.edges.add(edge);
        this.changedFaces = true;
        return edge;
    }

    /**
     * {@inheritDoc}
     */
    public void removeEdge(final IEdge edge) {
        // Check for graph consistency
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.edges.contains(edge)) {
            throw new IllegalArgumentException("The edge (" + edge.getID()
                    + ") is not part of the graph.");
        }

        // Remove edge and references
        this.edges.remove(edge);
        ((PNode) edge.getSource()).unlinkEdge(edge);
        ((PNode) edge.getTarget()).unlinkEdge(edge);
        this.changedFaces = true;
    }

    // ======================== Faces ==============================================================

    /**
     * {@inheritDoc} Note that in this implementation of the basic graph model, the faces are not
     * dynamically changed. This is necessary to guarantee constant time performance for most
     * operations. If the graph has changed since the last method call, the faces have to be
     * regenerated which may take some time.
     */
    public Iterable<IFace> getFaces() {
        this.generateFaces();
        return this.faces;
    }

    /**
     * {@inheritDoc}
     */
    public int getFaceCount() {
        this.generateFaces();
        return this.faces.size();
    }

    /**
     * Set the flag that indicates that the graph model has changed.
     */
    void setChangedFaces() {
        this.changedFaces = true;
    }

    /**
     * Generate the faces on this graph. Since the faces in the graph model are not dynamically
     * changed, a call to this method is necessary whenever face-related information is required
     * after the graph has changed. This method will then regenerate all faces in the graph. Note
     * that this will not run in constant time, so your algorithm heavily relies on face
     * information, this graph structure may not be good for you.
     */
    void generateFaces() {

        // Reset changed faces flag
        if (!this.changedFaces) {
            return;
        } else {
            this.changedFaces = false;
        }

        // Clear old face data
        this.faces.clear();
        this.faceIndex = 0;
        for (IEdge edge : this.edges) {
            ((PEdge) edge).setLeftFace(null);
            ((PEdge) edge).setRightFace(null);
        }

        // Generate new face data
        for (IEdge edge : this.edges) {
            // Check for left face
            if (edge.getLeftFace() == null) {

                // Generate Face
                PFace face = new PFace(this.faceIndex++, this);
                this.faces.add(face);

                INode start = edge.getSource();
                INode nextNode = start;
                IEdge nextEdge = edge;

                // special case if there is just 1 edge in graph
                if (this.getEdgeCount() == 1) {
                    face.addNode(nextNode);
                    face.addEdge(nextEdge);
                }

                do {
                    if (nextNode == nextEdge.getSource()) {
                        ((PEdge) nextEdge).setLeftFace(face);
                    } else if (nextNode == nextEdge.getTarget()) {
                        ((PEdge) nextEdge).setRightFace(face);
                    }

                    // don't add 1 edge 2 times
                    if (nextEdge != getNextCClockwiseEdge(nextNode, nextEdge)) {
                        face.addNode(nextNode);
                        face.addEdge(nextEdge);
                    }

                    nextNode = nextNode.getAdjacentNode(nextEdge);
                    nextEdge = getNextClockwiseEdge(nextNode, nextEdge);
                } while (nextNode != start);

            }

            // Check for right face
            if (edge.getRightFace() == null) {
                PFace face = new PFace(this.faceIndex++, this);
                this.faces.add(face);

                INode start = edge.getSource();
                INode nextNode = start;
                IEdge nextEdge = edge;

                // special case if there is just 1 edge in graph
                if (this.getEdgeCount() == 1) {
                    face.addNode(nextNode);
                    face.addEdge(nextEdge);
                }

                do {
                    if (nextNode == nextEdge.getSource()) {
                        ((PEdge) nextEdge).setRightFace(face);
                    } else if (nextNode == nextEdge.getTarget()) {
                        ((PEdge) nextEdge).setLeftFace(face);
                    }

                    // don't add 1 edge 2 times
                    if (nextEdge != getNextClockwiseEdge(nextNode, nextEdge)) {
                        face.addNode(nextNode);
                        face.addEdge(nextEdge);
                    }

                    nextNode = nextNode.getAdjacentNode(nextEdge);
                    nextEdge = getNextCClockwiseEdge(nextNode, nextEdge);

                } while (nextNode != start);
            }
        }
    }

    /**
     * Find the next edge on a node.
     * 
     * @param node
     *            the node containing the edges
     * @param edge
     *            the current edge
     * @return the next edge
     */
    private IEdge getNextClockwiseEdge(final INode node, final IEdge edge) {
        Iterator<IEdge> iter = node.adjacentEdges().iterator();
        IEdge current = null;
        while (iter.hasNext() && current != edge) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            // Get the next edge on the node
            return iter.next();
        } else {
            // Reached the last node, get the first
            return node.adjacentEdges().iterator().next();
        }
    }

    /**
     * Find the previous edge on a node.
     * 
     * @param node
     *            the node containing the edges
     * @param edge
     *            the current edge
     * @return the previous edge
     */
    private IEdge getNextCClockwiseEdge(final INode node, final IEdge edge) {
        Iterator<IEdge> iter = node.adjacentEdges().iterator();
        IEdge current = null;
        IEdge previous = null;
        while (iter.hasNext() && current != edge) {
            previous = current;
            current = iter.next();
        }

        // Node has just one edge
        if (!iter.hasNext() && previous == null) {
            previous = edge;
            return previous;
        }

        if (previous != null) {
            // Get the previous edge on the node
            return previous;
        } else {
            // Stopped at first node, go to last
            while (iter.hasNext()) {
                previous = iter.next();
            }
            return previous;
        }
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Nodes:\n";
        for (INode node : this.nodes) {
            res += "\t" + ((PNode) node).toString() + "\n";
        }
        return res;
    }

}
