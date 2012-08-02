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
                    "PFace, isInRectShape: to use this method, a orthogonal representation is needed!");
        }
        Pair<PNode, PEdge> startWithCorner = getProperty(Properties.FACE_DIRECTION);
        if (startWithCorner == null) {
            throw new InconsistentGraphModelException(
                    "PFace, isInRectShape: to use this method, the property FACE_DIRECTION has to be defined!");
        }

        PEdge startEdge = startWithCorner.getSecond();

        // run counter clockwise
        List<PEdge> path = Lists.newLinkedList();
        PNode currentNode = startWithCorner.getFirst();
        PEdge currentEdge = startEdge;
        path.add(currentEdge);
        boolean isRect = true;

        OrthogonalAngle currentAngle = null;
        OrthogonalAngle checkAngle = null;
        boolean wantsCCW = true;
        do {
            if (isCutvertex(currentNode)) {
                // special case, if a node is passed more than once.
                int ccwPath = calcPathLength(currentNode, currentEdge, ortho, true);
                int cwPath = calcPathLength(currentNode, currentEdge, ortho, false);
                if (ccwPath < cwPath) {
                    wantsCCW = false;
                }
                // check correct direction!
                // run around the path and if path count == face edge count is everything fine.
                // run with both variants along the face side check if the ccw one has longer path
                // if not wantsCCW is false
            }
            Pair<PEdge, OrthogonalAngle> pair = null;
            if (wantsCCW) {
                pair = nextCCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        true);
                if (path.contains(pair.getFirst())) {
                    pair = nextCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
            } else {
                pair = nextCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        true);
                if (path.contains(pair.getFirst())) {
                    pair = nextCCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
                wantsCCW = true;
            }

            currentEdge = pair.getFirst();
            path.add(currentEdge);
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
     * Checks for a wanted direction if a startEdge with successor startNode is in the wanted
     * direction.
     * 
     * @param startNode
     *            the checked node, the node shell be successor of the edge in the direction.
     * @param startEdge
     *            the checked edge.
     * @param ortho
     *            {@link OrthogonalRepresentation} of the graph.
     * @param wantsCCW
     *            true for counter clockwise direction, false for clockwise direction.
     * @return True, if it is in the wanted direction. False if it is the other direction.
     */
    public Pair<Integer, Integer> calcPathWithDirection(final PNode startNode,
            final PEdge startEdge, final OrthogonalRepresentation ortho, final boolean wantsCCW) {

        List<PEdge> path = Lists.newLinkedList();
        PEdge currentEdge = startEdge;
        PNode currentNode = startNode;
        Pair<PEdge, OrthogonalAngle> pair;
        int direction = 0;
        do {
            if (wantsCCW) {
                pair = nextCCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        true);
                if (path.contains(pair.getFirst())) {
                    pair = nextCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
            } else {
                pair = nextCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        false);
                if (path.contains(pair.getFirst())) {
                    pair = nextCCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
            }

            currentEdge = pair.getFirst();
            path.add(currentEdge);
            currentNode = currentEdge.getOppositeNode(currentNode);
            switch (pair.getSecond()) {
            case LEFT:
                if (wantsCCW) {
                    direction--;
                } else {
                    direction++;
                }
                break;
            case STRAIGHT: // nothing to do
                break;
            case RIGHT:
                if (wantsCCW) {
                    direction++;
                } else {
                    direction--;
                }
                break;
            case FULL:
                throw new InconsistentGraphModelException(
                        "PFace, isInDirection: full angles aren't supported yet!");
            }
        } while (startEdge != currentEdge);

        return new Pair<Integer, Integer>(Integer.valueOf(path.size()), Integer.valueOf(direction));

    }

    /**
     * @param startNode
     *            the node between start edge and next edge.
     * @param startEdge
     *            the start edge
     * @param ortho
     *            {@link OrthogonalRepresentation}
     * @param wantsCCW
     * @return
     */
    public int calcPathLength(final PNode startNode, final PEdge startEdge,
            final OrthogonalRepresentation ortho, final boolean wantsCCW) {

        List<PEdge> path = Lists.newLinkedList();
        PEdge currentEdge = startEdge;
        PNode currentNode = startNode;
        Pair<PEdge, OrthogonalAngle> pair;
        do {
            if (wantsCCW) {
                pair = nextCCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        true);
                if (path.contains(pair.getFirst())) {
                    pair = nextCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
            } else {
                pair = nextCWEdgeWithAngle(currentNode, currentEdge, ortho.getAngles(currentNode),
                        false);
                if (path.contains(pair.getFirst())) {
                    pair = nextCCWEdgeWithAngle(currentNode, currentEdge,
                            ortho.getAngles(currentNode), true);
                }
            }

            currentEdge = pair.getFirst();
            path.add(currentEdge);
            currentNode = currentEdge.getOppositeNode(currentNode);

        } while (startEdge != currentEdge);

        return path.size();
    }

    /**
     * @param currentNode
     * @return
     */
    public boolean isCutvertex(final PNode currentNode) {
        // at first handle exact 4 node edges.
        if (currentNode.getAdjacentEdgeCount() != 4) {
            return false;
        }
        int count = 0;
        for (PEdge edge : currentNode.adjacentEdges()) {
            if (isAdjacent(edge)) {
                count++;
            }
        }
        // TODO cutvertex can also consists of 3 edges, then with a cut edge.
        return count == 4;

    }

    public boolean containsCutvertex() {
        for (PNode node : this.adjacentNodes()) {
            if (isCutvertex(node)) {
                return true;
            }
        }
        return false;
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
     * @param angles
     *            angles of the node.
     * @param wantsCCWAngle
     *            the next counter clockwise element otherwise the last found counter clockwise
     *            element, that is the clockwise element.
     * @return the next edge after the given start edge adjacent to node and with the counter
     *         clockwise angle.
     */
    public Pair<PEdge, OrthogonalAngle> nextCCWEdgeWithAngle(final PNode node,
            final PEdge startEdge, final List<Pair<PEdge, OrthogonalAngle>> angles,
            final boolean wantsCCWAngle) {

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

        if (!wantsCCWAngle) {
            directionCounter = 4 - directionCounter;
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
     * @param angles
     *            angles of the node.
     * @param wantsCCWAngle
     *            the next counter clockwise element otherwise the last found counter clockwise
     *            element, that is the clockwise element.
     * @return the next edge after the given start edge adjacent to node and with the counter
     *         clockwise angle.
     */
    public Pair<PEdge, OrthogonalAngle> nextCWEdgeWithAngle(final PNode node,
            final PEdge startEdge, final List<Pair<PEdge, OrthogonalAngle>> angles,
            final boolean wantsCCWAngle) {

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

        // if a edge of an other face has detected, we have to sum over all angles until
        // a face-edge is reached.
        while (targetIndex != currentIndex) {
            directionCounter += angles.get(currentIndex).getSecond().ordinal() + 1;
            currentIndex = (currentIndex + 1) % angles.size();
        }
        
        if (!wantsCCWAngle) {
            directionCounter = 4 - directionCounter;
        }
        return new Pair<PEdge, OrthogonalAngle>(angles.get(targetIndex).getFirst(),
                OrthogonalAngle.map(directionCounter - 1));
    }

}
