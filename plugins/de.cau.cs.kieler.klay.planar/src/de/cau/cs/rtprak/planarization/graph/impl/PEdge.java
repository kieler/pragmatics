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
import java.util.Collection;
import java.util.LinkedList;

import de.cau.cs.rtprak.planarization.graph.IEdge;
import de.cau.cs.rtprak.planarization.graph.IFace;
import de.cau.cs.rtprak.planarization.graph.IGraph;
import de.cau.cs.rtprak.planarization.graph.INode;
import de.cau.cs.rtprak.planarization.graph.InconsistentGraphModelException;

/**
 * An edge in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.rtprak.planarization.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 */
public class PEdge extends PGraphElement implements IEdge, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 6297150511478367448L;

    // ======================== Attributes =========================================================

    /** The source node of an edge. */
    private INode source;

    /** The target node of an edge. */
    private INode target;

    /** The face at the left side. */
    private IFace leftFace;

    /** The face at the right side. */
    private IFace rightFace;

    // ======================== Constructor ========================================================

    /**
     * Creates an edge that connects the source and target nodes.
     * 
     * @param id
     *            the id assigned to the edge
     * @param parent
     *            the graph the edge belongs to
     * @param srcnode
     *            the source node of the edge
     * @param dstnode
     *            the target node of the edge
     */
    PEdge(final int id, final IGraph parent, final INode srcnode, final INode dstnode) {
        super(id, parent);
        this.source = srcnode;
        this.target = dstnode;
    }

    // ======================== Getters and Setters ================================================

    /**
     * {@inheritDoc}
     */
    public Collection<INode> getNodes() {
        Collection<INode> nodes = new LinkedList<INode>();
        nodes.add(source);
        nodes.add(source);
        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    public INode getSource() {
        return this.source;
    }

    /**
     * Set the source node of the edge.
     * 
     * @param src
     *            the new source node
     */
    void setSource(final INode src) {
        this.source = src;
    }

    /**
     * {@inheritDoc}
     */
    public INode getTarget() {
        return this.target;
    }

    /**
     * Set the target node of the edge.
     * 
     * @param dst
     *            the new target node
     */
    void setTarget(final INode dst) {
        this.target = dst;
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
        if (from == this.source) {
            this.source = to;
        } else if (from == this.target) {
            this.target = to;
        } else {
            throw new InconsistentGraphModelException("Attempted to move unlinked edge.");
        }
        ((PNode) from).unlinkEdge(this);
        ((PNode) to).linkEdge(this, append);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    @Override
    public String toString() {
        String res = "Edge (" + this.getID() + ") : ";
        res += "( " + this.source.getID() + ") => (" + this.target.getID() + ")";
        return res;
    }

}
