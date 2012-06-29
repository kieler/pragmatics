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

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A basic graph data structure for various graph theory algorithms. This implementation of the
 * basic graph model is based on {@code LinkedHashSet}s for the management of nodes and edges, thus
 * performing almost all operations on graphs in constant time. Note that all information regarding
 * faces are not changed dynamically in this implementation. Any algorithm using face information
 * frequently while still changing the graph should use a different graph implementation.The purpose
 * of this basic structure is to be used in general graph theory algorithms. It does not provide any
 * information concerning \the drawing of a graph (x/y coordinates etc.).
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.IGraphElement {@code IGraphElement}
 * 
 * @author ocl
 * @author pdo
 * @author cku
 * @author pkl
 */
public class PGraph extends PNode {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = -7340177117233615855L;

    // ======================== Attributes ====================================

    /**
     * Map mapping node types to the colors used to represent those types when writing debug output
     * graphs. The colors are given as strings of the form "#RGB", where each component is given as
     * a two-digit hexadecimal value.
     */
    public static final Map<NodeType, String> NODE_TYPE_COLORS = new EnumMap<NodeType, String>(
            NodeType.class);

    // Initialize the node-type-to-color-map
    static {
        NODE_TYPE_COLORS.put(NodeType.NORMAL, "#000000");
    }

    /** Set of nodes in the graph. */
    private final LinkedHashSet<PNode> nodes;

    /** The list of edges in the graph. */
    private final LinkedHashSet<PEdge> edges;

    /** The list of faces in the graph. */
    private final LinkedHashSet<PFace> faces;

    /** The next free index for nodes. */
    private int nodeIndex;

    /** The next free index for edges. */
    private int edgeIndex;

    /** The next free index for faces. */
    private int faceIndex;

    /** Remember if the faces have to be regenerated. */
    private boolean changedFaces;

    private PFace externalFace;

    // ======================== Constructor ======================================

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
        this.nodes = new LinkedHashSet<PNode>();
        this.edges = new LinkedHashSet<PEdge>();
        this.faces = new LinkedHashSet<PFace>();
        this.changedFaces = true;
        this.nodeIndex = 0;
        this.edgeIndex = 0;
        this.faceIndex = 0;
    }

    // ======================== Graph =========================================

    /**
     * Resets the IDs of all nodes, edges and faces in the graph. This guarantees that every ID is
     * at least {@code 0} and at most the number of respective items contained it the graph.
     */
    public void reindex() {
        this.nodeIndex = 0;
        for (PNode n : this.nodes) {
            ((PNode) n).id = this.nodeIndex++;
        }
        this.edgeIndex = 0;
        for (PEdge e : this.edges) {
            ((PEdge) e).id = this.edgeIndex++;
        }
        this.faceIndex = 0;
        for (PFace f : this.faces) {
            ((PFace) f).id = this.faceIndex++;
        }
    }

    // ======================== Nodes ===========================================

    /**
     * Get all nodes in this graph. This provides an {@code Iterable} object to gain access to all
     * {@code PNode}s that are part of the graph.
     * 
     * @return {@code Iterable} containing all graph nodes
     */
    public Collection<PNode> getNodes() {
        return this.nodes;
    }

    /**
     * Get the number of nodes in the graph.
     * 
     * @return the number of nodes
     */
    public int getNodeCount() {
        return this.nodes.size();
    }

    /**
     * Add a new node to the graph. This adds an empty node to the graph, that is not connected with
     * any other nodes in the graph.
     * 
     * @return the new node in the graph
     */
    public PNode addNode() {
        return addNode(NodeType.NORMAL);
    }

    /**
     * Add a new node to the graph. This adds a node on the given {@code PEdge} to the graph. The
     * edge will be split into two edges and all references to the new node in neighboring edges and
     * faces is set correctly.
     * 
     * @param edge
     *            the edge to split up by the node
     * @return the new node in the graph
     */
    public Pair<PNode, PEdge> addNode(final PEdge edge) {
        return addNode(edge, NodeType.NORMAL);
    }

    /**
     * Add a new node of a specific type to the graph. This adds an empty node of the given type to
     * the graph, that is not connected with any other nodes in the graph.
     * 
     * @param type
     *            the type of the node
     * @return the new node in the graph
     */
    public PNode addNode(final NodeType type) {
        PNode node;
        if (type == NodeType.COMPOUND) {
            node = new PGraph(this.nodeIndex++, this);
            this.nodes.add(node);
        } else if (type == NodeType.FACE) {
            node = new PNode(this.nodeIndex++, this, type);
            this.nodes.add(node);
        } else {
            node = new PNode(this.nodeIndex++, this, type);
            this.nodes.add(node);
        }
        this.changedFaces = true;
        return node;
    }

    /**
     * Add a new node to the graph. This adds a node of a specific type on the given {@code PEdge}
     * to the graph. The edge will be split into two edges and all references to the new node in
     * neighboring edges and faces will be set correctly.
     * 
     * @param edge
     *            the edge to split up by the node
     * @param type
     *            the type of the node
     * @return the new node in the graph
     */
    public Pair<PNode, PEdge> addNode(final PEdge edge, final NodeType type) {
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!(edge.getLeftFace() instanceof PFace && edge.getRightFace() instanceof PFace)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.edges.contains(edge)) {
            throw new IllegalArgumentException("The edge (" + edge.id
                    + ") is not part of the graph.");
        }
        generateFaces();

        // Remember target node
        PNode target = edge.getTarget();

        // Remember all edges in target adjacency list after the edge
        LinkedList<PEdge> move = new LinkedList<PEdge>();
        boolean found = false;
        for (PEdge e : target.adjacentEdges()) {
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

        // Move remembered edges to end of adjacency list (so new edge is at
        // correct position)
        for (PEdge e : move) {
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

        return new Pair<PNode, PEdge>(node, newedge);
    }

    /**
     * Remove a node from the graph. The node is been removed, together with all references to it in
     * other nodes as well as all edges connected to the node.
     * 
     * @param node
     *            the node to remove
     */
    public void removeNode(final PNode node) {
        // Check for graph consistency
        if (!this.nodes.contains(node)) {
            throw new IllegalArgumentException("The node (" + node.id
                    + ") is not part of the graph.");
        }
        // Remove node
        this.nodes.remove(node);

        // Remove all edges
        for (Iterator<PEdge> es = node.adjacentEdges().iterator(); es.hasNext();) {
            PEdge edge = es.next();

            // Check for graph consistency (again)
            if (!(node.getAdjacentNode(edge) instanceof PNode)) {
                throw new IncompatibleGraphTypeException();
            } else if (!this.edges.contains(edge)) {
                throw new InconsistentGraphModelException("The edge (" + edge.id
                        + ") is not part of the graph.");
            }

            ((PNode) node.getAdjacentNode(edge)).unlinkEdge(edge);
            this.edges.remove(edge);
            es.remove();
        }
        this.changedFaces = true;
    }

    // ======================== Edges ====================================

    /**
     * Get all edges in this graph. This provides an {@code Collection} object to gain access to all
     * edges that are part of the graph.
     * 
     * @return {@code Collection} containing all graph edges
     */
    public Collection<PEdge> getEdges() {
        return this.edges;
    }

    /**
     * Get the number of edges in the graph.
     * 
     * @return the number of edges
     */
    public int getEdgeCount() {
        return this.edges.size();
    }

    /**
     * Add a directed edge to the graph. A new, directed edge will be inserted into the graph,
     * connecting the two nodes {@code source} and {@code target}. This checks if the two given
     * nodes are actually part of the graph and throws an exception otherwise. The newly created
     * edge object is returned for further use.
     * 
     * @param source
     *            the source node of the edge
     * @param target
     *            the target node of the edge
     * @return the edge that was just added
     */
    public PEdge addEdge(final PNode source, final PNode target) {
        return this.addEdge(source, target, true);
    }

    /**
     * Add an edge to the graph. A new edge will be inserted into the graph, connecting the two
     * nodes {@code source} and {@code target}. The node can either be directed or undirected. This
     * checks if the two given nodes are actually part of the graph and throws an exception
     * otherwise. The newly created edge object is returned for further use.
     * 
     * @param source
     *            the source node of the edge
     * @param target
     *            the target node of the edge
     * @param directed
     *            specifies if the edge is directed or undirected
     * @return the edge that was just added
     */
    public PEdge addEdge(final PNode source, final PNode target, final boolean directed) {
        // Check for graph consistency
        if (!(source instanceof PNode && target instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.nodes.contains(source)) {
            throw new IllegalArgumentException("The node (" + source.id
                    + ") is not part of the graph.");
        } else if (!this.nodes.contains(target)) {
            throw new IllegalArgumentException("The node (" + target.id
                    + ") is not part of the graph.");
        }

        PEdge edge = new PEdge(this.edgeIndex++, this, source, target, directed);
        ((PNode) source).linkEdge(edge);
        ((PNode) target).linkEdge(edge);
        this.edges.add(edge);
        this.changedFaces = true;
        return edge;
    }

    /**
     * Changes the source or/and target of a edge.
     * 
     * @param edge
     *            , selected PEdge
     * @param source
     *            , new source node, set {@code null} if no new source is wanted.
     * @param target
     *            , new target node, set {@code null} if no new target is wanted.
     */
    public void changeEdge(final PEdge edge, final PNode source, final PNode target) {
        if (source != null) {
            edge.getSource().unlinkEdge(edge);
            edge.setSource(source);
            source.linkEdge(edge);
        }
        if (target != null) {
            edge.getTarget().unlinkEdge(edge);
            edge.setTarget(target);
            target.linkEdge(edge);
        }
        this.edges.add(edge);
        this.changedFaces = true;
    }

    /**
     * Remove an edge from the graph. The edge will be removed together will all references to the
     * edge in neighboring nodes.
     * 
     * @param edge
     *            the edge to remove
     */
    public void removeEdge(final PEdge edge) {
        // Check for graph consistency
        if (!(edge.getSource() instanceof PNode && edge.getTarget() instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!this.edges.contains(edge)) {
            throw new IllegalArgumentException("The edge (" + edge.id
                    + ") is not part of the graph.");
        }

        // Remove edge and references
        this.edges.remove(edge);
        ((PNode) edge.getSource()).unlinkEdge(edge);
        ((PNode) edge.getTarget()).unlinkEdge(edge);
        this.changedFaces = true;
    }

    // ======================== Faces ====================================

    /**
     * Get all faces in this graph. This provides an {@code Iterable} object to gain access to all
     * faces that are part of the graph. Note that in this implementation of the basic graph model,
     * the faces are not dynamically changed. This is necessary to guarantee constant time
     * performance for most operations. If the graph has changed since the last method call, the
     * faces have to be regenerated which may take some time.
     * 
     * @return {@code Iterable} containing all graph faces
     */
    public Iterable<PFace> getFaces() {
        if (this.changedFaces) {
            generateFaces();
            getExternalFace(true);
        }
        return this.faces;
    }

    /**
     * Get the number of faces in the graph.
     * 
     * @return the number of faces
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

        if (!changedFaces) {
            return;
        }
        // Reset changed faces flag
        this.changedFaces = false;

        // Clear old face data
        this.faces.clear();
        this.faceIndex = 0;
        for (PEdge edge : this.edges) {
            ((PEdge) edge).setLeftFace(null);
            ((PEdge) edge).setRightFace(null);
        }

        // Generate new face data
        for (PEdge edge : this.edges) {
            // Check for left face
            if (edge.getLeftFace() == null) {

                // Generate Face
                PFace face = new PFace(this.faceIndex++, this);
                this.faces.add(face);

                PNode start = edge.getSource();
                PNode nextNode = start;
                PEdge nextEdge = edge;

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

                PNode start = edge.getSource();
                PNode nextNode = start;
                PEdge nextEdge = edge;

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
    private PEdge getNextClockwiseEdge(final PNode node, final PEdge edge) {
        Iterator<PEdge> iter = node.adjacentEdges().iterator();
        PEdge current = null;
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
    private PEdge getNextCClockwiseEdge(final PNode node, final PEdge edge) {
        Iterator<PEdge> iter = node.adjacentEdges().iterator();
        PEdge current = null;
        PEdge previous = null;
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

    // ======================== Miscellaneous Functions
    // ============================================

    @Override
    public String toString() {
        String res = "Nodes:\n";
        for (PNode node : this.nodes) {
            res += "\t" + ((PNode) node).toString() + "\n";
        }
        return res;
    }

    /**
     * Outputs a representation of this graph in dot format to the given writer. The following
     * conventions are used:
     * <ul>
     * <li>Standard nodes are drawn as rectangles.</li>
     * <li>Dummy nodes are drawn as ellipses.</li>
     * <li>Nodes have a color that depends on their node type. (yellow for {@code LONG_EDGE},
     * turquoise for {@code ODD_PORT_SIDE}, dark blue for {@code NORTH_SOUTH_PORT})</li>
     * </ul>
     * 
     * @param writer
     *            the writer to output the graph to. An attempt is made to close the writer when
     *            finished.
     * @param directed
     *            if the output graph should be directed.
     * @throws IOException
     *             if anything goes wrong with the writer.
     */
    public void writeDotGraph(final Writer writer, final boolean directed) throws IOException {
        // Begin the graph
        writer.write(directed ? "digraph {\n" : "graph {\n");
        // graph options
        writer.write("    rankdir=LR;\n");
        // Write the nodes and edges

        if (nodes.isEmpty()) {
            return;
        }

        // Start a subgraph for the graph
        // writer.write("    {\n");
        // writer.write("        rank=same;\n");
        for (PNode node : nodes) {

            // Output the node name
            writer.write("        " + node.hashCode());

            // Options time!
            StringBuffer options = new StringBuffer();

            // Label
            options.append("label=\"");
            options.append("N" + node.id + " ");
            options.append("\",");

            // Node type
            if (node.getType().equals(NodeType.NORMAL)) {
                options.append("shape=circle,");
            } else if (node.getType().equals(NodeType.FACE)) {
                options.append("shape=box,");
            } else {
                options.append("shape=circle,style=filled,");

                String color = NODE_TYPE_COLORS.get(node.getProperty(Properties.NODE_TYPE));
                if (color != null) {
                    options.append("color=\"" + color + "\",");
                }
            }

            // Print options, if any
            options.deleteCharAt(options.length() - 1);
            if (options.length() > 0) {
                writer.write("[" + options + "]");
            }

            // End the node line
            writer.write(";\n");
        }

        // Write the edges
        for (PNode node : nodes) {
            // Go through all edges and output those that have this node as
            // their source
            for (PEdge edge : node.outgoingEdges()) {
                writer.write("    " + node.hashCode() + (directed ? "->" : " -- ")
                        + edge.getTarget().hashCode());
                writer.write(";\n");
            }
        }

        // writer.write("    }\n");

        // Close the graph. And the writer.
        writer.write("}\n");
        writer.close();

    }

    /**
     * Sets the face with the most adjacent nodes as externalFace.
     * 
     * @param wantsReCal
     *            , uses this to trigger explicit a new externalFace calculation.
     * @return the externalFace
     */
    public PFace getExternalFace(final boolean wantsReCal) {
        if (this.externalFace == null || wantsReCal) {
            Iterator<PFace> it = this.getFaces().iterator();
            if (it.hasNext()) {
                this.externalFace = it.next();
                while (it.hasNext()) {
                    PFace face = it.next();
                    if (face.getAdjacentNodeCount() > this.externalFace.getAdjacentNodeCount()) {
                        this.externalFace = face;
                    }
                }
            } else {
                throw new IllegalStateException();
            }
        }
        return this.externalFace;
    }

    // do not use this at final version!
    @Deprecated
    public void setExternalFace(PFace externalFace) {
        this.externalFace = externalFace;
    }

}
