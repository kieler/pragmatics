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
import java.util.Collection;
import java.util.LinkedList;

import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.IPort;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;

/**
 * An edge in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 */
public class PEdge extends PGraphElement implements IEdge, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 6297150511478367448L;

    // ======================== Attributes =========================================================

    /** Specifies if the edge is directed or undirected. */
    private boolean isDirected;

    /** The source port of the edge. */
    private IPort sourcePort;

    /** The target port of the edge. */
    private IPort targetPort;

    /** The face at the left side. */
    private IFace leftFace;

    /** The face at the right side. */
    private IFace rightFace;

    // ======================== Constructor ========================================================

    /**
     * Creates an edge that connects the source and target nodes.
     * 
     * @param id
     *            the id assigned the the edge
     * @param parent
     *            the graph the edge belongs to
     * @param srcnode
     *            the source node of the edge
     * @param appendSrc
     *            specifies if the edge will be appended to the adjacency list of the source node
     * @param dstnode
     *            the target node of the edge
     * @param appendDst
     *            specifies if the edge will be appended to the adjacency list of the target node
     * @param directed
     *            specifies if the edge is directed or undirected
     * @throws InconsistentGraphModelException
     *             if there are no free ports on one of the nodes
     */
    PEdge(final int id, final IGraph parent, final INode srcnode, final boolean appendSrc,
            final INode dstnode, final boolean appendDst, final boolean directed)
            throws InconsistentGraphModelException {
        super(id, parent);
        if (!(srcnode.getAdjacencyList() instanceof PAdjacencyList)
                || !(dstnode.getAdjacencyList() instanceof PAdjacencyList)) {
            throw new InconsistentGraphModelException(
                    "Attempted to create edge between nodes of incompatible type.");
        }
        this.sourcePort = ((PAdjacencyList) srcnode.getAdjacencyList()).linkEdge(this, appendSrc);
        this.targetPort = ((PAdjacencyList) dstnode.getAdjacencyList()).linkEdge(this, appendDst);
        this.isDirected = directed;
    }

    /**
     * Creates an edge that connects the source and target ports.
     * 
     * @param id
     *            the id assigned the the edge
     * @param parent
     *            the graph the edge belongs to
     * @param srcport
     *            the source port of the edge
     * @param dstport
     *            the target port of the edge
     * @param directed
     *            specifies if the edge is directed or undirected
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    PEdge(final int id, final IGraph parent, final IPort srcport, final IPort dstport,
            final boolean directed) throws InconsistentGraphModelException {
        super(id, parent);
        if (!(srcport instanceof PPort) || !(dstport instanceof PPort)) {
            throw new InconsistentGraphModelException(
                    "Attempted to create edge between ports of incompatible type.");
        }
        ((PPort) this.sourcePort).linkEdge(this);
        ((PPort) this.targetPort).linkEdge(this);
        this.isDirected = directed;
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public boolean isDirected() {
        return this.isDirected;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<INode> getNodes() {
        Collection<INode> nodes = new LinkedList<INode>();
        nodes.add(this.sourcePort.getNode());
        nodes.add(this.targetPort.getNode());
        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    public INode getSourceNode() {
        return this.sourcePort.getNode();
    }

    /**
     * {@inheritDoc}
     */
    public IPort getSourcePort() {
        return this.sourcePort;
    }

    /**
     * Set the source port of the edge.
     * 
     * @param src
     *            the new source port
     */
    void setSource(final IPort src) {
        this.sourcePort = src;
    }

    /**
     * {@inheritDoc}
     */
    public INode getTargetNode() {
        return this.targetPort.getNode();
    }

    /**
     * {@inheritDoc}
     */
    public IPort getTargetPort() {
        return this.targetPort;
    }

    /**
     * Set the target port of the edge.
     * 
     * @param dst
     *            the new target port
     */
    void setTarget(final IPort dst) {
        this.targetPort = dst;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IFace> getFaces() {
        Collection<IFace> faces = new LinkedList<IFace>();
        faces.add(this.leftFace);
        faces.add(this.rightFace);
        return faces;
    }

    /**
     * {@inheritDoc}
     */
    public IFace getRightFace() throws InconsistentGraphModelException {
        if (!(this.getParent() instanceof PGraph)) {
            throw new InconsistentGraphModelException(
                    "Attempted to get Face in graph of incompatible type.");
        }
        if (((PGraph) this.getParent()).getChangedFaces()) {
            ((PGraph) this.getParent()).generateFaces();
        }
        return rightFace;
    }

    /**
     * Set the face on the right side of the edge.
     * 
     * @param face
     *            the right face
     */
    void setRightFace(final IFace face) {
        this.rightFace = face;
    }

    /**
     * {@inheritDoc}
     */
    public IFace getLeftFace() throws InconsistentGraphModelException {
        if (!(this.getParent() instanceof PGraph)) {
            throw new InconsistentGraphModelException(
                    "Attempted to get Face in graph of incompatible type.");
        }
        if (((PGraph) this.getParent()).getChangedFaces()) {
            ((PGraph) this.getParent()).generateFaces();
        }
        return leftFace;
    }

    /**
     * Set the face on the left side of the edge.
     * 
     * @param face
     *            the left face
     */
    void setLeftFace(final IFace face) {
        this.leftFace = face;
    }

    // ======================== Miscellaneous Functions ============================================

    /**
     * {@inheritDoc}
     */
    public void move(final INode from, final INode to) throws InconsistentGraphModelException {
        this.move(from, to, true);
    }

    /**
     * {@inheritDoc}
     */
    public void move(final INode from, final INode to, final boolean append)
            throws InconsistentGraphModelException {
        // Check for graph consistency
        if (!(from instanceof PNode) || !(to instanceof PNode)) {
            throw new InconsistentGraphModelException(
                    "Attempted to move between nodes of incompatible type.");
        }
        if (!(this.getParent() instanceof PGraph)) {
            throw new InconsistentGraphModelException(
                    "Attempted to get move between nodes in graph of incompatible type.");
        }

        // Reset references
        if (from == this.sourcePort.getNode()) {
            this.sourcePort = ((PAdjacencyList) to.getAdjacencyList()).linkEdge(this, append);
        } else if (from == this.targetPort.getNode()) {
            this.targetPort = ((PAdjacencyList) to.getAdjacencyList()).linkEdge(this, append);
        } else {
            throw new InconsistentGraphModelException("Attempted to move unlinked edge.");
        }
        ((PAdjacencyList) from.getAdjacencyList()).unlinkEdge(this);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    /**
     * {@inheritDoc}
     */
    public void move(final IPort from, final IPort to) throws InconsistentGraphModelException {
        if (!(from instanceof PPort) || !(to instanceof PPort)) {
            throw new InconsistentGraphModelException(
                    "Attempted to move between ports of incompatible type.");
        }
        if (from == this.sourcePort) {
            this.sourcePort = to;
        } else if (from == this.targetPort) {
            this.targetPort = to;
        } else {
            throw new InconsistentGraphModelException("Attempted to move unlinked edge.");
        }
        ((PPort) from).unlinkEdge(this);
        ((PPort) to).linkEdge(this);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    @Override
    public String toString() {
        String res = "Edge (" + this.getID() + ") : ";
        res += "( " + this.sourcePort.getNode().getID() + ") => ("
                + this.targetPort.getNode().getID() + ")";
        return res;
    }

}
