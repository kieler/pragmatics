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
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

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

    /** Generated Version UID for Serialization. */
    private static final long serialVersionUID = 595562864080000947L;

    /** Around a face in ccw direction is four times right. */
    private static final int CCW_DIRECTION = 4;

    // ======================== Attributes =========================================================

    /** The nodes of this face. */
    private final HashSet<PNode> nodes;

    /** The edges of this face. */
    private final HashSet<PEdge> edges;

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
        return this.edges.contains(edge);
    }

    /**
     * Checks if a given node is in the surrounding node set of the face.
     * 
     * @param node
     *            , for which it has to search for.
     * @return true if found otherwise false.
     */
    public boolean isAdjacent(final PNode node) {
        return this.nodes.contains(node);
    }

    /**
     * Check if a face is adjacent to this face (i.e. an edge exists that connects the two faces).
     * 
     * @param face
     *            the face to check for
     * @return true if {@code face} is adjacent to this face
     */
    public boolean isAdjacent(final PFace face) {
        for (PEdge e : this.edges) {
            if (e.getRightFace() == face || e.getLeftFace() == face) {
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
    public Iterable<PEdge> getEdges(final PFace face) {
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
    public void addEdge(final PEdge edge) {
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
        OrthogonalRepresentation ortho = getParent().getProperty(Properties.ORTHO_REPRESENTATION);
        if (ortho == null) {
            throw new InconsistentGraphModelException(
                    "PFace, isInRectShape: to use this method, a orthogonal representation is needed!");
        }
        Pair<PNode, PEdge> startWithCorner = getProperty(Properties.FACE_DIRECTION);
        if (startWithCorner == null) {
            throw new InconsistentGraphModelException("PFace, isInRectShape: to use this method, "
                    + "the property FACE_DIRECTION has to be defined!");
        }

        PEdge startEdge = startWithCorner.getSecond();

        // run counter clockwise if internal, clockwise if external face.
        PNode startNode = startWithCorner.getFirst();
        PNode corner = startNode;
        PEdge currentEdge = startEdge;
        boolean isRect = true;

        OrthogonalAngle currentAngle = null;
        OrthogonalAngle checkAngle = null;

        do {
            // Find next edge in the path.
            Pair<PEdge, OrthogonalAngle> pair = nextAdjacentElement(currentEdge, corner);
            currentEdge = pair.getFirst();
            checkAngle = pair.getSecond();
            // If a full angles is on the path, we can immediately stop the calculation
            // the face is not in rect shape.
            if (checkAngle == OrthogonalAngle.FULL) {
                return false;
            }

            corner = currentEdge.getOppositeNode(corner);
            if (currentAngle != null) {
                // if an angle between two face edges change, the face is not in rectangular shape.
                if (checkAngle != OrthogonalAngle.STRAIGHT && checkAngle != currentAngle) {
                    isRect = false;
                    break;
                }
            } else {
                if (checkAngle != OrthogonalAngle.STRAIGHT) {
                    currentAngle = checkAngle;
                }
            }

        } while (startEdge != currentEdge || startNode != corner);

        return isRect;
    }

    /**
     * Determines a startEdge and a startNode that is counterclockwise the next node to the edge.
     * 
     * @return tuple of corner and edge, in ccw direction.
     */
    public Pair<PNode, PEdge> determineCCWDirection() {
        List<PEdge> cutEdges = getCutEdges();

        if (cutEdges.isEmpty()) {
            int calculatedDirection = -1;
            PEdge startEdge = adjacentEdges().iterator().next();
            calculatedDirection = calcPathWithDirection(startEdge.getSource(), startEdge);
            if (calculatedDirection == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
            }
            return new Pair<PNode, PEdge>(startEdge.getTarget(), startEdge);
        }

        // Taking a cut edge, brings always the correct direction, no matter if the source or target
        // is chosen, since it is passed twice at a walkaround of a face.
        PEdge first = cutEdges.get(0);
        return new Pair<PNode, PEdge>(first.getSource(), first);
    }

    /**
     * Checks for a wanted direction (ccw or cw ) whether a startEdge with successor startNode is in
     * the wanted direction.
     * 
     * @param startNode
     *            the checked node, the node shell be successor of the edge in the direction.
     * @param startEdge
     *            the checked edge.
     * @return the calculated direction.
     */
    private int calcPathWithDirection(final PNode startNode, final PEdge startEdge) {

        PEdge currentEdge = startEdge;
        PNode corner = startNode;
        Pair<PEdge, OrthogonalAngle> pair;
        int direction = 0;
        do {
            pair = nextAdjacentElement(currentEdge, corner);
            currentEdge = pair.getFirst();
            corner = currentEdge.getOppositeNode(corner);
            switch (pair.getSecond()) {
            case LEFT:
                direction--;
                break;
            case STRAIGHT:
                // nothing to do
                break;
            case RIGHT:
                direction++;
                break;
            case FULL:
                direction += 2;
                break;
            }
        } while (startNode != corner || startEdge != currentEdge);
        return direction;
    }

    /**
     * Gets the next edge with angle around the face. If it is an external face, the clockwise edge
     * is used, if it is an internal face the counterclockwise next edge is used. A multi adjacent
     * node, is a node that has more than 2 same adjacent edges with the face. Then it is not easy
     * to decide which edge is the next one, but than hold: For internal likewise external faces the
     * next edge is the clockwise. Additionally the angle between that edge and the current edge is
     * calculated.
     * 
     * @param currentEdge
     *            the current edge in the path.
     * @param corner
     *            the current node in the path.
     * @return a edge with its orthogonal angle to the previous edge.
     */
    public Pair<PEdge, OrthogonalAngle> nextAdjacentElement(final PEdge currentEdge,
            final PNode corner) {

        Pair<PEdge, OrthogonalAngle> pair = null;
        if (getParent().getExternalFace() == this) {
            // On a external face we need to go clockwise.
            pair = nextCWEdgeWithAngle(corner, currentEdge);
        } else {
            // Internal faces need a ccw circulation.
            boolean isMultiAdjacentNode = isMultiNode(corner);
            if (isMultiAdjacentNode) {
                // If there is an multinode, this has to be passed cw.
                pair = nextCWEdgeWithAngle(corner, currentEdge);
            } else {
                pair = nextCCWEdgeWithAngle(corner, currentEdge);
            }
        }
        return pair;
    }

    /**
     * A multi node has more than two incident edges that are also adjacent to the face. If a face
     * is adjacent to a cutEdge, it contains always a multinode.
     * 
     * @param currentNode
     *            check node.
     * @return true if the node is adjacent to more than two nodes, otherwise false.
     */
    public boolean isMultiNode(final PNode currentNode) {
        int count = 0;
        for (PEdge edge : currentNode.adjacentEdges()) {
            if (isAdjacent(edge)) {
                count++;
            }
        }
        // cutvertex can consists of 4 edges or 3 edges, then with a cut edge.
        return count > 2;
    }

    /**
     * Collects all cutedges of the face.
     * 
     * @return List of cutedges.
     */
    public List<PEdge> getCutEdges() {
        List<PEdge> result = Lists.newLinkedList();
        for (PEdge edge : this.adjacentEdges()) {
            if (edge.getLeftFace() == this && edge.getRightFace() == this) {
                result.add(edge);
            }
        }
        return result;
    }

    /**
     * Get the next counter clockwise edge adjacent to a given node from an edge. Returns
     * {@code null} if the given edge is not adjacent to the node, and the given edge if it is the
     * only one adjacent to the node and the angle between the next edge and the start edge.
     * 
     * @param node
     *            the node between start edge and next edge.
     * @param startEdge
     *            the start edge
     * @return the next edge after the given start edge adjacent to node and with the counter
     *         clockwise angle.
     */
    public Pair<PEdge, OrthogonalAngle> nextCCWEdgeWithAngle(final PNode node, final PEdge startEdge) {

        OrthogonalRepresentation ortho = getParent().getProperty(Properties.ORTHO_REPRESENTATION);
        List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(node);
        if (angles.size() == 1) {
            return angles.get(0);
        }

        int directionCounter = 0;
        int startIndex = 0;
        int targetIndex = 0;

        // get start edge index.
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                startIndex = i;
                break;
            }
        }

        // take first matching index to get the next ccw edge
        int currentIndex = (startIndex + 1) % angles.size();
        int previousIndex = startIndex;

        while (currentIndex != startIndex) {
            if (isAdjacent(angles.get(currentIndex).getFirst())) {
                targetIndex = currentIndex;
                directionCounter += angles.get(previousIndex).getSecond().ordinal() + 1;
                break;
            } else {
                directionCounter += angles.get(previousIndex).getSecond().ordinal() + 1;
                previousIndex = currentIndex;
                currentIndex = (currentIndex + 1) % angles.size();
            }
        }

        return new Pair<PEdge, OrthogonalAngle>(angles.get(targetIndex).getFirst(),
                OrthogonalAngle.map(directionCounter - 1));
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
     * @return the next edge after the given start edge adjacent to node and with the counter
     *         clockwise angle.
     */
    public Pair<PEdge, OrthogonalAngle> nextCWEdgeWithAngle(final PNode node, final PEdge startEdge) {
        OrthogonalRepresentation ortho = getParent().getProperty(Properties.ORTHO_REPRESENTATION);
        List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(node);

        if (angles.size() == 1) {
            return angles.get(0);
        }

        int directionCounter = 0;
        int startIndex = 0;
        int targetIndex = 0;
        // get start edge index.
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                startIndex = i;
                break;
            }
        }

        // take last matching index.
        int currentIndex = (startIndex + 1) % angles.size();
        while (currentIndex != startIndex) {
            if (isAdjacent(angles.get(currentIndex).getFirst())) {
                targetIndex = currentIndex;
            }
            currentIndex = (currentIndex + 1) % angles.size();
        }

        // if an edge of another face has detected, we have to sum over all angles until
        // a face-edge is reached.
        while (targetIndex != currentIndex) {
            directionCounter += angles.get(currentIndex).getSecond().ordinal() + 1;
            currentIndex = (currentIndex + 1) % angles.size();
        }

        return new Pair<PEdge, OrthogonalAngle>(angles.get(targetIndex).getFirst(),
                OrthogonalAngle.map(directionCounter - 1));
    }

}
