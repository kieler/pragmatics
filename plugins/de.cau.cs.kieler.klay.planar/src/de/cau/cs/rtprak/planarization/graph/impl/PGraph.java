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
package de.cau.cs.rtprak.planarization.graph.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.rtprak.planarization.graph.IEdge;
import de.cau.cs.rtprak.planarization.graph.IFace;
import de.cau.cs.rtprak.planarization.graph.IGraph;
import de.cau.cs.rtprak.planarization.graph.INode;
import de.cau.cs.rtprak.planarization.graph.InconsistentGraphModelException;

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
public class PGraph extends PNode implements IGraph, Serializable {

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
    public PGraph() {
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

    /**
     * Creates a new graph based on a {@code KGraph}.
     * 
     * @param kgraph
     *            the {@code KGraph}
     * @throws InconsistentGraphModelException
     *             if the given graph is not consistent
     */
    public PGraph(final KNode kgraph) throws InconsistentGraphModelException {
        this(-1, (PGraph) null, kgraph);
    }

    /**
     * Creates a new graph based on a {@code KGraph} as a compound node of graph {@code p}. All
     * edges and nodes in the new graph gets data with the identifier {@code TOKGRAPH} assigned,
     * that holds the corresponding {@code KGraphElement} of the node or edge. Using this data, the
     * graph can easily be reverted to the {@code KGraph} it originates from.
     * 
     * @param id
     *            the id assigned to the graph
     * @param parent
     *            the parent graph containing the compound node
     * @param kgraph
     *            the {@code KGraph}
     * @throws InconsistentGraphModelException
     *             if the given graph is not consistent
     */
    PGraph(final int id, final PGraph parent, final KNode kgraph)
            throws InconsistentGraphModelException {
        this(id, parent);
        HashMap<KNode, PNode> map = new HashMap<KNode, PNode>(kgraph.getChildren().size() * 2);

        // Adding Nodes
        for (KNode knode : kgraph.getChildren()) {
            PNode node = null;
            if (knode.getChildren().size() > 0) {
                node = new PGraph(this.nodeIndex++, this, knode);
            } else {
                node = new PNode(this.nodeIndex++, this, NodeType.NORMAL);
            }
            this.nodes.add(node);
            node.setProperty(IGraph.TOKGRAPH, knode);
            map.put(knode, node);
        }

        // Adding Edges
        for (KNode knode : kgraph.getChildren()) {
            for (KEdge kedge : knode.getOutgoingEdges()) {
                if (!map.containsKey(kedge.getSource()) || !map.containsKey(kedge.getTarget())) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                PNode source = map.get(kedge.getSource());
                PNode target = map.get(kedge.getTarget());
                PEdge edge = new PEdge(this.edgeIndex++, this, source, target);
                this.edges.add(edge);
                source.linkEdge(edge);
                target.linkEdge(edge);
                edge.setProperty(IGraph.TOKGRAPH, kedge);
            }
        }

        // Adding Faces
        this.generateFaces();
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

    @Override
    public void merge(final INode node, final boolean append)
            throws InconsistentGraphModelException {
        super.merge(node, append);
        if (node.getType() != NodeType.COMPOUND) {
            return;
        }
        if (!(node instanceof PGraph)) {
            throw new InconsistentGraphModelException(
                    "Merged node is marked as compound node, but is not a subgraph.");
        }
        for (INode n : ((PGraph) node).getNodes()) {
            if (this.nodes.contains(n)) {
                throw new InconsistentGraphModelException(
                        "Attempted to add node that is already part of the graph.");
            }
            this.nodes.add(n);
        }
        for (IEdge e : ((PGraph) node).getEdges()) {
            if (this.edges.contains(e)) {
                throw new InconsistentGraphModelException(
                        "Attempted to add edge that is already part of the graph.");
            }
            this.edges.add(e);
        }
        this.reindex();
        this.changedFaces = true;
    }

    /**
     * {@inheritDoc}
     */
    public void mergeGraph(final IGraph graph) throws InconsistentGraphModelException {
        for (INode node : graph.getNodes()) {
            if (this.nodes.contains(node)) {
                throw new InconsistentGraphModelException(
                        "Attempted to add node that is already part of the graph.");
            }
            this.nodes.add(node);
        }
        for (IEdge edge : graph.getEdges()) {
            if (this.edges.contains(edge)) {
                throw new InconsistentGraphModelException(
                        "Attempted to add edge that is already part of the graph.");
            }
            this.edges.add(edge);
        }
        this.reindex();
        this.changedFaces = true;
    }

    /**
     * {@inheritDoc}
     */
    public IGraph createDualGraph() throws InconsistentGraphModelException {
        HashMap<IFace, PNode> map = new HashMap<IFace, PNode>(this.faces.size() * 2);
        PGraph dual = new PGraph();

        if (this.changedFaces) {
            this.generateFaces();
        }

        // Add the dual nodes from graph faces
        for (IFace face : this.faces) {
            PNode node = (PNode) dual.addNode();
            node.setID(face.getID());
            ((PFace) face).setProperty(IGraph.TODUALGRAPH, node);
            map.put(face, node);
        }
        // Build the edges based on the neighboring faces
        for (IEdge edge : this.edges) {
            if (this.faces.size() > 1) {
                IEdge e = dual.addEdge(map.get(edge.getLeftFace()), map.get(edge.getRightFace()));
                ((PEdge) edge).setProperty(IGraph.TODUALGRAPH, e);
            }
        }
        return dual;
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
    public INode addNode(final IEdge edge) throws InconsistentGraphModelException {
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
    public INode addNode(final IEdge edge, final NodeType type)
            throws InconsistentGraphModelException {
        // Check for graph consistency
        if (!this.edges.contains(edge)) {
            throw new InconsistentGraphModelException("Attempted to remove non-existent edge.");
        }
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to add node on edge of incompatible type.");
        }
        if (!(edge.getLeftFace() instanceof PFace && edge.getRightFace() instanceof PFace)) {
            throw new InconsistentGraphModelException(
                    "Attempted to add node on edge of incompatible type.");
        }
        if (this.changedFaces) {
            this.generateFaces();
        }

        // Create node
        PNode node = (PNode) this.addNode(type);

        // Move old edge
        PNode target = (PNode) edge.getTarget();
        ((PEdge) edge).setTarget(node);
        node.linkEdge(edge);

        // Add new edge
        PEdge newedge = (PEdge) this.addEdge(node, target);

        // Update references in target node
        LinkedList<IEdge> tomove = new LinkedList<IEdge>();
        boolean found = false;
        for (IEdge e : target.getAllEdges()) {
            if (found) {
                tomove.add(e);
            } else if (e == edge) {
                found = true;
            }
        }
        target.unlinkEdge(edge);
        for (IEdge e : tomove) {
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

        return node;
    }

    /**
     * {@inheritDoc}
     */
    public void removeNode(final INode node) throws InconsistentGraphModelException {
        // Check for graph consistency
        if (!this.nodes.contains(node)) {
            throw new InconsistentGraphModelException("Attempted to remove non-existent node.");
        }
        // Remove node
        this.nodes.remove(node);

        // Remove all edges
        for (Iterator<IEdge> es = node.getAllEdges().iterator(); es.hasNext();) {
            // Check for graph consistency (again)
            IEdge e = es.next();
            if (!this.edges.contains(e)) {
                throw new InconsistentGraphModelException("Attempted to remove non-existent edge.");
            }
            if (!(node.getAdjacentNode(e) instanceof PNode)) {
                throw new InconsistentGraphModelException(
                        "Attempted to remove edge of incompatible type.");
            }
            ((PNode) node.getAdjacentNode(e)).unlinkEdge(e);
            this.edges.remove(e);
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
     * 
     * @throws InconsistentGraphModelException
     *             if the graph does not contain source node or target node of the edge or if either
     *             node is not a {@code PNode}
     */
    public IEdge addEdge(final INode source, final INode target)
            throws InconsistentGraphModelException {
        return this.addEdge(source, true, target, true);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws InconsistentGraphModelException
     *             if the graph does not contain source node or target node of the edge or if either
     *             node is not a {@code PNode}
     */
    public IEdge addEdge(final INode source, final boolean appendSource, final INode target,
            final boolean appendTarget) throws InconsistentGraphModelException {
        // Check for graph consistency
        if (!this.nodes.contains(source) || !this.nodes.contains(target)) {
            throw new InconsistentGraphModelException(
                    "Attempted to link non-existent nodes by an edge.");
        }
        if (!(source instanceof PNode && target instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to connect nodes of incompatible type.");
        }

        IEdge e = new PEdge(this.edgeIndex++, this, source, target);
        this.edges.add(e);
        ((PNode) source).linkEdge(e, appendSource);
        ((PNode) target).linkEdge(e, appendTarget);
        this.changedFaces = true;
        return e;
    }

    /**
     * {@inheritDoc}
     */
    public IEdge addUndirectedEdge(final INode node1, final INode node2)
            throws InconsistentGraphModelException {
        return this.addEdge(node1, node2);
    }

    /**
     * {@inheritDoc}
     */
    public IEdge addUndirectedEdge(final INode node1, final boolean appendNode1, final INode node2,
            final boolean appendNode2) throws InconsistentGraphModelException {
        return this.addEdge(node1, appendNode1, node2, appendNode2);
    }

    /**
     * {@inheritDoc}
     */
    public void removeEdge(final IEdge edge) throws InconsistentGraphModelException {
        // Check for graph consistency
        if (!this.edges.contains(edge)) {
            throw new InconsistentGraphModelException("Attempted to remove non-existent edge.");
        }
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to remove edge of incompatible type.");
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
     * 
     * @throws InconsistentGraphModelException
     *             if the faces can not be generated correctly
     */
    public Iterable<IFace> getFaces() throws InconsistentGraphModelException {
        if (this.changedFaces) {
            this.generateFaces();
        }
        return this.faces;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws InconsistentGraphModelException
     *             if the faces can not be generated correctly
     */
    public int getFaceCount() throws InconsistentGraphModelException {
        if (this.changedFaces) {
            this.generateFaces();
        }
        return this.faces.size();
    }

    /**
     * Check if the graph model has changed since the last the last time the faces have been
     * generated.
     * 
     * @return true if the graph has changed
     */
    boolean getChangedFaces() {
        return this.changedFaces;
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
     * 
     * @throws InconsistentGraphModelException
     *             if the graph turns out to be inconsistent
     */
    void generateFaces() throws InconsistentGraphModelException {
        // Reset flag here to avoid infinite loops
        this.changedFaces = false;
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
        Iterator<IEdge> iter = node.getAllEdges().iterator();
        IEdge current = null;
        while (iter.hasNext() && current != edge) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            // Get the next edge on the node
            return iter.next();
        } else {
            // Reached the last node, get the first
            return node.getAllEdges().iterator().next();
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
        Iterator<IEdge> iter = node.getAllEdges().iterator();
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
