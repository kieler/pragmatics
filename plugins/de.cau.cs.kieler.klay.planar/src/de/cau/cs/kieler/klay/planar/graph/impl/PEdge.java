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
import de.cau.cs.kieler.klay.planar.graph.IncompatibleGraphTypeException;

/**
 * An edge in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.impl.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 */
class PEdge extends PGraphElement implements IEdge, Serializable {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 6297150511478367448L;

    // ======================== Attributes =========================================================

    /** Specifies if the edge is directed or undirected. */
    private boolean isDirected;

    /** The source node of the edge. */
    private INode source;

    /** The target node of the edge. */
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
     *            the id assigned the the edge
     * @param parent
     *            the graph the edge belongs to
     * @param srcnode
     *            the source node of the edge
     * @param dstnode
     *            the target node of the edge
     * @param directed
     *            specifies if the edge is directed or undirected
     */
    PEdge(final int id, final IGraph parent, final INode srcnode, final INode dstnode,
            final boolean directed) {
        super(id, parent);
        this.source = srcnode;
        this.target = dstnode;
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
        nodes.add(this.source);
        nodes.add(this.target);
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
    public Collection<IFace> getFaces() {
        Collection<IFace> faces = new LinkedList<IFace>();
        faces.add(this.leftFace);
        faces.add(this.rightFace);
        return faces;
    }

    /**
     * {@inheritDoc}
     */
    public IFace getRightFace() {
        if (!(this.getParent() instanceof PGraph)) {
            throw new IncompatibleGraphTypeException();
        }
        ((PGraph) this.getParent()).generateFaces();
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
    public IFace getLeftFace() {
        if (!(this.getParent() instanceof PGraph)) {
            throw new IncompatibleGraphTypeException();
        }
        ((PGraph) this.getParent()).generateFaces();
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
    public void move(final INode from, final INode to) {
        // Check for graph consistency
        if (!(from instanceof PNode) || !(to instanceof PNode)) {
            throw new IncompatibleGraphTypeException();
        } else if (!(this.getParent() instanceof PGraph)) {
            throw new IncompatibleGraphTypeException();
        }

        // Reset references
        if (from == this.source) {
            this.source = to;
            ((PNode) to).linkEdge(this);
        } else if (from == this.target) {
            this.target = to;
            ((PNode) to).linkEdge(this);
        } else {
            throw new IllegalArgumentException("The edge is not connected to the source node.");
        }
        ((PNode) from).unlinkEdge(this);
        ((PGraph) this.getParent()).setChangedFaces();
    }

    @Override
    public String toString() {
        String res = "Edge (" + this.getID() + ") : ";
        res += "( " + this.source.getID() + ") => (" + this.target.getID() + ")";
        return res;
    }

}
