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

import java.util.Collection;
import java.util.LinkedList;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;

/**
 * An edge in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author cku
 * @author pkl
 */
public class PEdge extends PGraphElement {

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 6297150511478367448L;

    // ======================== Attributes =========================================================

    /** Specifies if the edge is directed or undirected. */
    private boolean isDirected;

    /** The source node of the edge. */
    private PNode source;

    /** The target node of the edge. */
    private PNode target;

    /** The face at the left side. */
    private PFace leftFace;

    /** The face at the right side. */
    private PFace rightFace;

    /** the bend points. */
    private KVectorChain bendPoints = new KVectorChain();

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
    PEdge(final int id, final PGraph parent, final PNode srcnode, final PNode dstnode,
            final boolean directed) {
        super(id, parent);
        this.source = srcnode;
        this.target = dstnode;
        this.isDirected = directed;
    }

    // ======================== Getters and Setters ================================================

    /**
     * Check if the edge is a directed or an undirected edge.
     * 
     * @return true if the edge is directed, false if undirected
     */
    public boolean isDirected() {
        return this.isDirected;
    }

    /**
     * Get the {@code PNode}s that are connected by this edge. This will return a {@code Collection}
     * containing at most two {@code PNode}s, specifically the source and the target node of this
     * edge. This method is provided because in some cases (especially in undirected graphs) it may
     * be convenient to get the nodes of an edge without specifying if the node is source or target.
     * 
     * @return a {@code Collection} containing the nodes this edge connects
     */
    public Collection<PNode> getNodes() {
        Collection<PNode> nodes = new LinkedList<PNode>();
        nodes.add(this.source);
        nodes.add(this.target);
        return nodes;
    }

    /**
     * Get the {@code PNode} in which this edge originates. In undirected graphs, one of the two
     * connected {@code PNode}s is returned.
     * 
     * @return the source node
     */
    public PNode getSource() {
        return this.source;
    }

    /**
     * Set the source node of the edge.
     * 
     * @param src
     *            the new source node
     */
    void setSource(final PNode src) {
        this.source = src;
    }

    /**
     * Get the {@code PNode} this edge points to. In undirected graphs, the {@code PNode} that is
     * not returned by {@code getSourceNode()} is returned.
     * 
     * @return the target node
     */
    public PNode getTarget() {
        return this.target;
    }

    /**
     * Set the target node of the edge.
     * 
     * @param dst
     *            the new target node
     */
    void setTarget(final PNode dst) {
        this.target = dst;
    }

    /**
     * Get the {@code IFace}s that are formed by this edge. This will return a {@code Collection}
     * containing at most two {@code IFace}s, specifically the left and right face of this edge.
     * 
     * @return a {@code Collection} containing the faces formed by this edge
     */
    public Collection<PFace> getFaces() {
        Collection<PFace> faces = new LinkedList<PFace>();
        faces.add(this.leftFace);
        faces.add(this.rightFace);
        return faces;
    }

    /**
     * Get the {@code IFace} on the right side of this edge.
     * 
     * @return the right face
     */
    public PFace getRightFace() {
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
    void setRightFace(final PFace face) {
        this.rightFace = face;
    }

    /**
     * Get the {@code IFace} on the left side of this edge.
     * 
     * @return the left face
     */
    public PFace getLeftFace() {
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
    void setLeftFace(final PFace face) {
        this.leftFace = face;
    }

    /**
     * Returns the docking point at the source node.
     * 
     * @return the source docking point
     */
    public KVector getSourcePoint() {
        KVector v = target.getPosition().differenceCreate(source.getPosition());
        clipVector(v, source.getSize().x, source.getSize().y);
        return v.add(source.getPosition());
    }

    /**
     * Returns the docking point at the target node.
     * 
     * @return the target docking point
     */
    public KVector getTargetPoint() {
        KVector v = source.getPosition().differenceCreate(target.getPosition());
        clipVector(v, target.getSize().x, target.getSize().y);
        return v.add(target.getPosition());
    }

    /**
     * Returns the list of bend points, which is initially empty.
     * 
     * @return the bend points
     */
    public KVectorChain getBendPoints() {
        return bendPoints;
    }

    /**
     * Clip the given vector to a rectangular box of given size.
     * 
     * @param v
     *            vector relative to the center of the box
     * @param width
     *            width of the rectangular box
     * @param height
     *            height of the rectangular box
     */
    private static void clipVector(final KVector v, final double width, final double height) {
        double wh = width / 2, hh = height / 2;
        double absx = Math.abs(v.x), absy = Math.abs(v.y);
        double xscale = 1, yscale = 1;
        if (absx > wh) {
            xscale = wh / absx;
        }
        if (absy > hh) {
            yscale = hh / absy;
        }
        v.scale(Math.min(xscale, yscale));
    }

    // ======================== Miscellaneous Functions ============================================

    /**
     * Move the edge from one node to another. This will remove the edge from the adjacency list of
     * the first node, and will add itself to the adjacency list of the second node. The source and
     * target of the edge will be set accordingly.
     * 
     * @param from
     *            the node from which this edge will be removed
     * @param to
     *            the node to which this edge will be moved
     */
    public void move(final PNode from, final PNode to) {
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
        String res = "Edge (" + this.id + ") : ";
        res += "( " + this.source.id + ") => (" + this.target.id + ")";
        return res;
    }

    /**
     * Gives the opposite node of the edge, meaning if the param node is source then it gives
     * target-node otherwise source-node.
     * 
     * @param node
     *            PNode
     * @return PNode
     */
    public PNode getOppositeNode(final PNode node) {
        return node == source ? target : source;
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
     * Gives the node that connects this edge and a given edge.
     * 
     * @param edge
     * @return common node.
     */
    public PNode getCommonNode(final PEdge edge) {
        if (getNodes().contains(edge.source)) {
            return edge.source;
        } else if (getNodes().contains(edge.target)) {
            return edge.target;
        }
        return null;
    }

    /**
     * @param node
     * @return
     */
    public boolean isConnected(PNode node) {
        return node == source || node == target;
    }

}
