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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.IFunction;
import de.cau.cs.kieler.klay.planar.util.MappedIterable;

/**
 * A face in a basic graph data structure for various graph theory algorithms.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author cku
 * @author ocl
 * @author pkl
 */
public class PFace extends PGraphElement {
    // TODO guarantee edge and node order
    // TODO is left/right face correct according to source/target?

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 595562864080000947L;

    // ======================== Attributes =========================================================

    /** The nodes of this face. */
    private HashSet<PNode> nodes;

    /** The edges of this face. */
    private HashSet<PEdge> edges;

    // ======================== Constructor ========================================================

    /**
     * Create a new Face.
     * 
     * @param id
     *            the id assigned to the face
     * @param parent
     *            the graph the face belongs to
     */
    PFace(final int id, final PGraph parent) {
        super(id, parent);
        this.edges = new HashSet<PEdge>();
        this.nodes = new HashSet<PNode>();
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the number of nodes adjacent to the face.
     * 
     * @return the number of adjacent nodes
     */
    public int getAdjacentNodeCount() {
        return this.nodes.size();
    }

    /**
     * Get the number of edges adjacent to the face.
     * 
     * @return the number of adjacent nodes
     */
    public int getAdjacentEdgeCount() {
        return this.edges.size();
    }

    /**
     * Checks if a given edge is in the surrounding edge set of the face.
     * 
     * @param edge
     *            , for which it has to search for.
     * @return true if found otherwise false.
     */
    public boolean isAdjacent(final PEdge edge) {
        for (PEdge e : this.edges) {
            if (e == edge) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a face is adjacent to this face (i.e. an edge exists that connects the two faces).
     * 
     * @param face
     *            the face to check for
     * @return true if {@code face} is adjacent to this face
     */
    public boolean isAdjacent(final PFace face) { // TODO O(n)
        for (PEdge e : this.edges) {
            if (e.getLeftFace() == this && e.getRightFace() == face) {
                return true;
            } else if (e.getRightFace() == this && e.getLeftFace() == face) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an adjacent face specified by the connecting edge. This returns the face directly
     * adjacent to this face, connected by the given edge {@code edge}.
     * 
     * @param edge
     *            the edge that connects the faces
     * @return the adjacent face of this face regarding {@code e}
     */
    public PFace getAdjacentFace(final PEdge edge) {
        if (edge.getLeftFace() == this) {
            return edge.getRightFace();
        } else if (edge.getRightFace() == this) {
            return edge.getLeftFace();
        } else {
            throw new IllegalArgumentException("The edge (" + edge.id
                    + ") is not connected to the face (" + this.id + ").");
        }
    }

    /**
     * Get the edges to an adjacent face. This returns the edges that separates this face with the
     * given face {@code face}. A face can be separated to another face by several edges.
     * 
     * @param face
     *            the adjacent face
     * @return the edges that separates this face with {@code face}
     */
    public Iterable<PEdge> getEdges(final PFace face) { // TODO O(n)
        List<PEdge> list = new LinkedList<PEdge>();
        for (PEdge e : this.edges) {
            if (this.getAdjacentFace(e) == face) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Add a new node to the face.
     * 
     * @param node
     *            the new node to add
     */
    void addNode(final PNode node) {
        this.nodes.add(node);
    }

    /**
     * Add a new edge to the face.
     * 
     * @param edge
     *            the new edge to add
     */
    void addEdge(final PEdge edge) {
        this.edges.add(edge);
    }

    // ======================== Iterators ==========================================================

    /**
     * Get the {@code PNode}s which describe this face. Returns an {@code Iterable} object
     * containing all {@code PNode}s that lay on the border of this face.
     * 
     * @return the surrounding nodes
     */
    public Iterable<PNode> adjacentNodes() {
        return this.nodes;
    }

    /**
     * Get the {@code PFace}s surrounding this face. Returns an {@code Iterable} object containing
     * all {@code PFace}s adjacent to this face.
     * 
     * @return iterable object containing all adjacent faces
     */
    public Iterable<PEdge> adjacentEdges() {
        return this.edges;
    }

    /**
     * Get the {@code IEgde}s surrounding this edge. Returns an {@code Iterable} object containing
     * all {@code PEdge}s that define the border of this edge.
     * 
     * @return iterable object containing the surrounding edges
     */
    public Iterable<PFace> adjacentFaces() {
        return new MappedIterable<PEdge, PFace>(this.edges, new IFunction<PEdge, PFace>() {
            public PFace evaluate(final PEdge element) {
                return getAdjacentFace(element);
            }
        });
    }

    // ======================== Miscellaneous Functions ============================================

    @Override
    public String toString() {
        String res = "Face (" + this.id + "):\n";
        for (PEdge edge : this.edges) {
            res += "\t" + ((PEdge) edge).toString() + "\n";
        }
        return res;
    }

    /**
     * Checks if a face is in rectangular shape. That means that all consecutive edges are straight
     * to each other or have always the same angle.
     * 
     * @return true if it is in rectangular shape, otherwise false.
     */
    public boolean isInRectShape() {
        OrthogonalRepresentation ortho = this.getParent().getProperty(
                Properties.ORTHO_REPRESENTATION);
        if (ortho == null) {
            throw new InconsistentGraphModelException(
                    "To use this method, a orthogonal representation is needed!");
        }

        Iterator<PEdge> edgeIt = this.adjacentEdges().iterator();
        PEdge startEdge = edgeIt.next();
        PEdge currentEdge = startEdge;
        PNode currentNode = currentEdge.getSource();
        boolean isRect = true;

        OrthogonalAngle currentAngle = null;
        OrthogonalAngle checkAngle = null;
        do {
            Pair<PEdge, OrthogonalAngle> pair = nextEdgeWithAngle(currentNode, currentEdge,
                    ortho.getAngles(currentNode));
            currentEdge = pair.getFirst();
            checkAngle = pair.getSecond();
            currentNode = currentEdge.getOppositeNode(currentNode);
            if (currentAngle != null) {
                // if a angle between two face edges change, the face is not in rectangular shape.
                if (checkAngle != OrthogonalAngle.STRAIGHT && checkAngle != currentAngle) {
                    isRect = false;
                    break;
                }
            } else {
                if (checkAngle != OrthogonalAngle.STRAIGHT) {
                    currentAngle = checkAngle;
                }
            }

        } while (startEdge != currentEdge);

        return isRect;
    }

    /**
     * Get the next edge adjacent to a given node from an edge. Returns {@code null} if the given
     * edge is not adjacent to the node, and the given edge if it is the only one adjacent to the
     * node and the angle between the next edge and the start edge.
     * 
     * @param node
     *            the node between start edge and next edge.
     * @param startEdge
     *            the start edge
     * @param angles
     *            angles of the node.
     * @return the next edge after the given start edge adjacent to node and a ccw angle.
     */
    public Pair<PEdge, OrthogonalAngle> nextEdgeWithAngle(final PNode node, final PEdge startEdge,
            final List<Pair<PEdge, OrthogonalAngle>> angles) {

        int previousIndex = 0;
        int currentIndex = 0;
        int directionCounter = 0;

        // get start edge index.
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                currentIndex = i;
                break;
            }
        }

        // if a edge of an other face has detected, we have to sum over all angles until
        // a face-edge is reached.
        boolean containsForeignEdge = false;
        Pair<PEdge, OrthogonalAngle> pair = null;

        // determine the directions of the next corner face-edge
        do {
            previousIndex = currentIndex;
            currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
            pair = angles.get(currentIndex);

            if (!containsForeignEdge && isAdjacent(pair.getFirst())) {
                // hasFound
                directionCounter = angles.get(previousIndex).getSecond().ordinal();
                break;
            } else {
                containsForeignEdge = true;
                // look at the direction of the previous edge to determine the direction
                directionCounter += angles.get(previousIndex).getSecond().ordinal() + 1;
            }
        } while (!isAdjacent(pair.getFirst()));
        return new Pair<PEdge, OrthogonalAngle>(pair.getFirst(),
                OrthogonalAngle.map(containsForeignEdge ? directionCounter - 1 : directionCounter));
    }

}
