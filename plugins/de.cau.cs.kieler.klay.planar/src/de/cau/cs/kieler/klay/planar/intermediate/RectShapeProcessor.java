/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * Brings the faces of the graph in rectangular shape. Meaning that after this step there are exact
 * 4 bends per face, all in right or left order, depending from the start point.
 * 
 * @author pkl
 */
public class RectShapeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** Around a face in ccw direction is four times right. */
    private static final int CCW_DIRECTION = 4;

    /** The external face has at this point exact 5 adjacent edges. */
    private static final int EXTERNAL_EDGE_COUNT = 6;

    private static final int SIDE_NUMBER = 4;

    private static final int BOTTOM_SIDE_INDEX = 3;

    private PGraph graph;

    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Rectangular shaping", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        // Add a node for every bend in the orthogonal representation
        List<PEdge> edges = new LinkedList<PEdge>();
        edges.addAll(pgraph.getEdges());

        for (PEdge edge : edges) {
            addBendDummies(edge);
        }

        determineFaceDirections();

        // Decompose faces into rectangles
        if (this.graph.getExternalFace().isInRectShape()) {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.FALSE);
        } else {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.TRUE);
            transformExternalFace();
            determineFaceDirections();
        }

        transformInternalFaces();

        getMonitor().done();
    }

    private void transformInternalFaces() {
        boolean wantsFinish = false;
        PFace face = null;
        while (true) {
            wantsFinish = true;

            Iterable<PFace> graphFaces = this.graph.getFaces();
            determineFaceDirections();

            for (PFace checkFace : graphFaces) {

                // do a pre-selection, note: the new external face is already in rect shape.
                if (checkFace.isInRectShape()) {
                    continue;
                }
                wantsFinish = false;
                face = checkFace;
                break;
            }
            if (wantsFinish) {
                break;
            }
            // step 1 and 2
            setEdgeProperties(face, false);

            // step 3
            determineFronts(face);

            // step 4
            // for each edge e, such that turn(e) = -1 (i.e., e and next(e) form a right turn),
            // insert a vertex project(e) along edge front(e), and add edge extend(e) =
            // (corner(e),
            // project(e)). ...

            PEdge startEdge = determineStartEdge(face);
            PEdge currentEdge = startEdge;
            PEdge next = null;
            do {
                if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1) {
                    addArtificial(currentEdge, false);
                }
                next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
                currentEdge = next;
            } while (currentEdge != startEdge);
        }
    }

    /**
     * Determines an arbitrary edge, but with longest path to the front.
     * 
     * @param face
     * @return
     */
    private PEdge determineStartEdge(final PFace face) {

        PEdge startEdge = null;
        PEdge currentEdge = null;
        Iterator<PEdge> edgeIt = face.adjacentEdges().iterator();
        while (edgeIt.hasNext()) {
            currentEdge = edgeIt.next();
            if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1) {
                // ensures that no cut edge is used as startEdge
                startEdge = currentEdge;
                break;
            }
        }
        if (startEdge == null) {
            throw new InconsistentGraphModelException("RectShapeProcessor: "
                    + "startEdge is not defined!");
        }

        // Choose as startEdge a edge that has the longest path to its front.
        // This is needed to ensure the counter clockwise order while adding dummies.
        PEdge startFront = startEdge.getProperty(Properties.RECT_SHAPE_FRONT);
        int maxPathLength = startEdge.getProperty(Properties.RECT_SHAPE_PATH_LENGTH);
        do {
            currentEdge = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
            if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1) {
                PEdge front = currentEdge.getProperty(Properties.RECT_SHAPE_FRONT);
                int newPathLength = currentEdge.getProperty(Properties.RECT_SHAPE_PATH_LENGTH)
                        .intValue();
                if (front == startFront && newPathLength > maxPathLength) {
                    startEdge = currentEdge;
                    maxPathLength = newPathLength;
                }
            }
        } while (currentEdge != startEdge);

        // arbitrary edge.

        return startEdge;
    }

    /**
     * Runs around the graph and search for the front of every edge that has a rect shape turn of 1.
     * 
     * @param face
     */
    private void determineFronts(final PFace face) {
        PEdge startEdge;
        PEdge currentEdge;
        int turn;
        int pathLength;
        boolean wantsNewFront;
        // step 3
        // for each edge e, find the first edge e' following e counterclockwise, such that
        // the sum of the turn value for all the edges between (included) and e' (excluded)
        // is equal to 1, and set front(e) = e'.
        for (PEdge edge : face.adjacentEdges()) {
            if (edge.getProperty(Properties.RECT_SHAPE_TURN).intValue() != 1) {
                continue;
            }
            startEdge = edge;
            currentEdge = edge;
            turn = 0;
            pathLength = 0;
            wantsNewFront = false;
            do {
                pathLength++;
                turn += currentEdge.getProperty(Properties.RECT_SHAPE_TURN);
                currentEdge = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
                if (turn == -1) {
                    startEdge.setProperty(Properties.RECT_SHAPE_FRONT, currentEdge);
                    startEdge.setProperty(Properties.RECT_SHAPE_PATH_LENGTH,
                            Integer.valueOf(pathLength));
                    wantsNewFront = true;
                    break;
                }
            } while (currentEdge != startEdge);
            if (!wantsNewFront) {
                startEdge.setProperty(Properties.RECT_SHAPE_FRONT, null);
            }
        }

    }

    /**
     * Sets the next edge, turn and corner of every edge.
     * 
     * @param face
     * @param next
     */
    private void setEdgeProperties(final PFace face, final boolean isExternal) {

        Pair<PNode, PEdge> startWithCorner = face.getProperty(Properties.FACE_DIRECTION);
        if (startWithCorner == null) {
            throw new InconsistentGraphModelException(
                    "To use this method, the property FACE_DIRECTION has to be defined!");
        }

        PEdge startEdge = startWithCorner.getSecond();
        // If isExternal go clockwise around the face adjacent edges.
        PNode corner = isExternal ? startEdge.getOppositeNode(startWithCorner.getFirst())
                : startWithCorner.getFirst();

        List<PEdge> path = Lists.newLinkedList();
        PEdge currentEdge = startEdge;
        PEdge next = null;
        // step 1
        // for each edge e of f, let next(e) be the edge following e when traversing
        // the boundary of f counterclockwise, and let corner(e) be the common vertex
        // of e and next(e).
        // step 2
        // for each e of f, we set turn(e) = +1 if e and next(e) form a left turn,
        // turn(e) = 0 if e and next(e) are aligned,
        // and turn(e) = -1 if e and next(e) form a right turn.
        boolean wantsCCW = true;
        do {
            currentEdge.setProperty(Properties.RECT_SHAPE_CORNER, corner);

            if (face.isCutvertex(corner)) {
                // special case, if a node is passed more than once.
                int ccwPath = face.calcPathLength(corner, currentEdge, this.orthogonal, true);
                int cwPath = face.calcPathLength(corner, currentEdge, this.orthogonal, false);
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
                if (isExternal) {
                    pair = face.nextCWEdgeWithAngle(corner, currentEdge,
                            this.orthogonal.getAngles(corner), true);
                    if (path.contains(pair.getFirst())) {
                        pair = face.nextCCWEdgeWithAngle(corner, currentEdge,
                                this.orthogonal.getAngles(corner), true);
                    }
                } else {
                    pair = face.nextCCWEdgeWithAngle(corner, currentEdge,
                            this.orthogonal.getAngles(corner), true);
                    if (path.contains(pair.getFirst())) {
                        pair = face.nextCWEdgeWithAngle(corner, currentEdge,
                                this.orthogonal.getAngles(corner), true);
                    }
                }
            } else {
                pair = face.nextCWEdgeWithAngle(corner, currentEdge,
                        this.orthogonal.getAngles(corner), true);
                if (path.contains(pair.getFirst())) {
                    pair = face.nextCCWEdgeWithAngle(corner, currentEdge,
                            this.orthogonal.getAngles(corner), true);
                }
                wantsCCW = true;
            }
            next = pair.getFirst();
            currentEdge.setProperty(Properties.RECT_SHAPE_NEXT, next);

            int edgeTurn = determineTurn(pair.getSecond(), isExternal);
            currentEdge.setProperty(Properties.RECT_SHAPE_TURN, edgeTurn);

            currentEdge = next;
            corner = next.getOppositeNode(corner);

        } while (currentEdge != startEdge);
    }

    private int determineTurn(final OrthogonalAngle angle, final boolean isExternal) {
        int edgeTurn = 0;
        switch (angle) {
        case LEFT:
            edgeTurn = isExternal ? -1 : 1;
            break;
        case STRAIGHT:
            edgeTurn = 0;
            break;
        case RIGHT:
            edgeTurn = isExternal ? 1 : -1;
            break;
        default:
            // TODO Think about full angles.
            new InconsistentGraphModelException(
                    "RectShapeProcessor: full angle is not suppported at method determineTurn");
        }
        return edgeTurn;
    }

    /**
     * Runs counter clockwise around every face and store at each found new face a startEdge and a
     * corner in counterclockwise direction.
     * 
     */
    public void determineFaceDirections() {

        Set<PFace> visitedFaces = Sets.newHashSet();

        List<PFace> completedFaces = Lists.newArrayList();

        PFace externalFace = this.graph.getExternalFace();
        PFace currentFace = externalFace;

        PEdge startEdge = null;
        PEdge currentEdge = null;
        PNode currentNode = null;

        // Determine startEdge and succeeding corner of the external face.
        Pair<PNode, PEdge> startWithCorner = determineCCWDirection(currentFace);
        currentFace.setProperty(Properties.FACE_DIRECTION, startWithCorner);
        visitedFaces.add(currentFace);

        while (currentFace != null) {

            startWithCorner = currentFace.getProperty(Properties.FACE_DIRECTION);
            currentNode = startWithCorner.getFirst();
            currentEdge = startWithCorner.getSecond();
            startEdge = currentEdge;
            PFace foundFace;
            do {
                foundFace = null;
                if (currentEdge.getLeftFace() != currentFace
                        && !visitedFaces.contains(currentEdge.getLeftFace())) {
                    foundFace = currentEdge.getLeftFace();
                } else if (currentEdge.getRightFace() != currentFace
                        && !visitedFaces.contains(currentEdge.getRightFace())) {
                    foundFace = currentEdge.getRightFace();
                }
                if (foundFace != null) {
                    if (currentFace == externalFace) {
                        // same direction
                        foundFace.setProperty(Properties.FACE_DIRECTION, new Pair<PNode, PEdge>(
                                currentNode, currentEdge));
                    } else {
                        // opposite direction
                        foundFace.setProperty(Properties.FACE_DIRECTION, new Pair<PNode, PEdge>(
                                currentEdge.getOppositeNode(currentNode), currentEdge));
                    }
                    visitedFaces.add(foundFace);
                }

                // next edge and corner.
                Pair<PEdge, OrthogonalAngle> nextEdgeWithAngle = currentFace.nextCCWEdgeWithAngle(
                        currentNode, currentEdge, this.orthogonal.getAngles(currentNode), true);
                currentEdge = nextEdgeWithAngle.getFirst();
                currentNode = currentEdge.getOppositeNode(currentNode);

            } while (currentEdge != startEdge);

            if (visitedFaces.size() == this.graph.getFaceCount()) {
                // finish
                return;
            }

            completedFaces.add(currentFace);
            // choose next face
            currentFace = null;
            for (PFace visitedFace : visitedFaces) {
                if (completedFaces.contains(visitedFace)) {
                    continue;
                }
                currentFace = visitedFace;
                break;
            }
        }
    }

    /**
     * @param face
     * @return
     */
    private Pair<PNode, PEdge> determineCCWDirection(final PFace face) {
        PEdge startEdge = face.adjacentEdges().iterator().next();
        Pair<Integer, Integer> plAndDirection;
        if (!face.containsCutvertex()) {
            plAndDirection = face.calcPathWithDirection(startEdge.getSource(), startEdge,
                    this.orthogonal, true);
            if (plAndDirection.getSecond().intValue() == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
            }
            return new Pair<PNode, PEdge>(startEdge.getTarget(), startEdge);
        }

        // handle face with cutvertex
        Pair<Integer, Integer> plAndDirection1 = face.calcPathWithDirection(startEdge.getSource(),
                startEdge, this.orthogonal, true);
        Pair<Integer, Integer> plAndDirection2 = face.calcPathWithDirection(startEdge.getSource(),
                startEdge, this.orthogonal, false);
        if (plAndDirection1.getFirst().intValue() > plAndDirection2.getFirst().intValue()) {
            if (plAndDirection1.getSecond().intValue() == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
            }
        } else {
            if (plAndDirection2.getSecond().intValue() == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
            }
        }

        plAndDirection1 = face.calcPathWithDirection(startEdge.getTarget(), startEdge,
                this.orthogonal, true);
        plAndDirection2 = face.calcPathWithDirection(startEdge.getTarget(), startEdge,
                this.orthogonal, false);
        if (plAndDirection1.getFirst().intValue() > plAndDirection2.getFirst().intValue()) {
            if (plAndDirection1.getSecond().intValue() == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getTarget(), startEdge);
            }
        } else {
            if (plAndDirection2.getSecond().intValue() == CCW_DIRECTION) {
                return new Pair<PNode, PEdge>(startEdge.getTarget(), startEdge);
            }
        }
        throw new InconsistentGraphModelException(
                "RectShapeProcessor, determineCCWDirection: should not happen!");

        // check for cutvertex
        // int firstLength = face.calcLengthWithDirection(startEdge.getSource(), startEdge,
        // this.orthogonal,
        // true);
        // int firstLength = face.calcLengthWithDirection(startEdge.getSource(), startEdge,
        // this.orthogonal,
        // false);

        //
        // int secondLength = face.calcPathLength(startEdge.getTarget(), startEdge, this.orthogonal,
        // true);
        // if (firstLength < secondLength) {
        // } else if (firstLength > secondLength) {
        // return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
        // } else {
        // if (face.calcPathWithDirection(startEdge.getSource(), startEdge, this.orthogonal, true))
        // {
        // return new Pair<PNode, PEdge>(startEdge.getSource(), startEdge);
        // } else {
        // return new Pair<PNode, PEdge>(startEdge.getTarget(), startEdge);
        // }
        // }
    }

    /**
     * Transforms the external face, if it is not in rectangular shape. The idea is to put a
     * rectangular around the external face and let the new rectangular be the external face.
     * Connect the old external face with the new one and one can treat the old external face like a
     * internal face.
     */
    private void transformExternalFace() {

        PFace face = this.graph.getExternalFace();

        setEdgeProperties(face, true);

        // add rectangle to the graph.
        PNode[] rectNodes = new PNode[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            rectNodes[i] = this.graph.addNode();
            rectNodes[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));
        }

        PEdge[] faceSides = new PEdge[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            faceSides[i] = this.graph.addEdge(rectNodes[i], rectNodes[(i + 1) % SIDE_NUMBER]);
            faceSides[i]
                    .setProperty(Properties.RECT_SHAPE_CORNER, rectNodes[(i + 1) % SIDE_NUMBER]);
            faceSides[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));
            this.orthogonal.setBends(faceSides[i], new OrthogonalAngle[i]);
        }

        for (int i = 0; i < SIDE_NUMBER; i++) {

            Iterator<PEdge> it = rectNodes[i].adjacentEdges().iterator();

            List<Pair<PEdge, OrthogonalAngle>> list = Lists.newLinkedList();
            if (i == 0) {
                list.add(new Pair<PEdge, OrthogonalAngle>(it.next(), OrthogonalAngle.RIGHT));
                list.add(new Pair<PEdge, OrthogonalAngle>(it.next(), OrthogonalAngle.LEFT));
            } else {
                list.add(new Pair<PEdge, OrthogonalAngle>(it.next(), OrthogonalAngle.LEFT));
                list.add(new Pair<PEdge, OrthogonalAngle>(it.next(), OrthogonalAngle.RIGHT));
            }
            this.orthogonal.setAngles(rectNodes[i], list);
        }

        // 0 left, 1 top, 2 right, 3 bottom.
        int sideIndex = 0;

        // arbitrary startEdge
        PEdge startEdge = face.adjacentEdges().iterator().next();
        PEdge currentEdge = startEdge;
        PEdge next = null;

        do {
            int edgeturn = currentEdge.getProperty(Properties.RECT_SHAPE_TURN);
            next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
            currentEdge.setProperty(Properties.RECT_SHAPE_SIDE_INDEX, Integer.valueOf(sideIndex));

            switch (edgeturn) {
            // left
            case -1:
                sideIndex = (sideIndex + 1) % SIDE_NUMBER;
                break;
            // straight
            case 0:
                // sideIndex remains constant.
                break;
            // right
            case 1:
                sideIndex = sideIndex > 0 ? sideIndex - 1 : BOTTOM_SIDE_INDEX;
                break;
            default:
                throw new InconsistentGraphModelException("Full-edges aren't supported yet!");
            }
            currentEdge = next;
        } while (currentEdge != startEdge);

        // add two edge make the external face to an internal one.
        int usedSide = -1;
        int countAdded = 0;
        do {
            int index = currentEdge.getProperty(Properties.RECT_SHAPE_SIDE_INDEX).intValue();
            if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == -1
                    && currentEdge.getProperty(Properties.RECT_SHAPE_FRONT) == null
                    && index != usedSide) {
                currentEdge.setProperty(Properties.RECT_SHAPE_FRONT, faceSides[index]);
                usedSide = index;
                countAdded++;
                addArtificial(currentEdge, true);
                if (countAdded == 2) {
                    break;
                }
            }

            next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
            currentEdge = next;
        } while (currentEdge != startEdge);

        // Set new external face.
        out: for (PFace pface : this.graph.getFaces()) {
            if (pface.getAdjacentEdgeCount() == EXTERNAL_EDGE_COUNT) {
                for (PEdge edge : faceSides) {
                    if (!pface.isAdjacent(edge)) {
                        continue out;
                    }
                }
                this.graph.setExternalFace(pface);
            }
        }
    }

    /**
     * 
     * @param edge
     * @param isExternal
     */
    private void addArtificial(final PEdge edge, final boolean isExternal) {
        PNode corner = edge.getProperty(Properties.RECT_SHAPE_CORNER);
        PEdge front = edge.getProperty(Properties.RECT_SHAPE_FRONT);
        PNode frontCorner = front.getProperty(Properties.RECT_SHAPE_CORNER);
        // add new node and new edge and set the orthogonal representation!
        Pair<PNode, PEdge> virtualPair = null;
        if (isExternal) {
            virtualPair = this.graph.addNode(front, front.getOppositeNode(frontCorner));
        } else {
            virtualPair = this.graph.addNode(front, frontCorner);
        }
        PNode projectE = virtualPair.getFirst();
        PEdge virtualEdge = virtualPair.getSecond();
        // add rect shape dummy property to determine later the dummy elements.
        projectE.setProperty(Properties.RECT_SHAPE_DUMMY, true);
        PEdge newEdge = this.graph.addEdge(corner, projectE);
        // if (isExternal) {
        // newEdge.setProperty(Properties.RECT_SHAPE_CUT_EDGE, new Pair<PNode, PEdge>(null, null));
        // }
        newEdge.setProperty(Properties.RECT_SHAPE_DUMMY, true);
        front.setProperty(Properties.RECT_SHAPE_CORNER, projectE);

        virtualEdge
                .setProperty(Properties.RECT_SHAPE_CORNER, virtualEdge.getOppositeNode(projectE));
        virtualEdge.setProperty(Properties.RECT_SHAPE_NEXT,
                front.getProperty(Properties.RECT_SHAPE_NEXT));
        virtualEdge.setProperty(Properties.RECT_SHAPE_TURN,
                front.getProperty(Properties.RECT_SHAPE_TURN));
        virtualEdge.setProperty(Properties.RECT_SHAPE_FRONT,
                front.getProperty(Properties.RECT_SHAPE_FRONT));

        front.setProperty(Properties.RECT_SHAPE_NEXT, virtualEdge);
        front.setProperty(Properties.RECT_SHAPE_TURN, 0);
        front.setProperty(Properties.RECT_SHAPE_FRONT, null);

        // Fix embedding and/or angles of startNode
        fixStartNode(edge, corner, newEdge, false);

        // Fix embedding and/or angles of virtual node
        fixVirtualNode(front, virtualEdge, projectE, newEdge);

        // Fix embedding and/or angles of ancestor node
        // There is nothing to do, because the angles don't change and the embedding is
        // done implicitly by the graph.addEdge(...).

        // Fix embedding and/or angles of successor node
        fixSuccessorNode(virtualEdge.getOppositeNode(projectE), front.getOppositeNode(projectE),
                virtualEdge);
    }

    /**
     * Sets the correct edge to the successorNode angles of the added virtual node.
     * 
     * @param successorNode
     *            the node which has a wrong angle edge.
     * @param ancestorNode
     *            the node that is wrong in the angle data.
     * @param virtualEdge
     *            the new edge, that has to be set for the edge (ancestorNode - successorNode).
     */
    private void fixSuccessorNode(final PNode successorNode, final PNode ancestorNode,
            final PEdge virtualEdge) {
        Pair<PEdge, OrthogonalAngle> chosenPair = null;
        for (Pair<PEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(successorNode)) {
            if (pair.getFirst().getTarget() == ancestorNode
                    || pair.getFirst().getSource() == ancestorNode) {
                chosenPair = pair;
                chosenPair.setFirst(virtualEdge);
                return;
            }
        }
    }

    /**
     * Fixes node embedding and node angles of the corner node.
     * 
     * @param startEdge
     *            the currentEdge incoming in the corner.
     * @param corner
     *            the node with which the projectE is connect over newEdge.
     * @param newEdge
     *            the new edge between corner and projectE that has to be inserted.
     */
    private void fixStartNode(final PEdge startEdge, final PNode corner, final PEdge newEdge,
            final boolean isExternal) {
        // fix node embedding: is implicit done by graph.addEdge(..)

        // fix angles
        List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(corner);

        if (isExternal) {
            // wanted order: startEdge, newEdge, successorEdge
            corner.moveToStart(newEdge);
            corner.moveToStart(startEdge);
            angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.LEFT));
            for (int i = 0; i < angles.size(); i++) {
                if (angles.get(i).getFirst() == startEdge) {
                    angles.get(i).setSecond(OrthogonalAngle.STRAIGHT);
                } else {
                    angles.get(i).setSecond(OrthogonalAngle.LEFT);
                }
            }
        } else {
            // wanted order: startEdge, successorEdge, newEdge
            corner.moveToStart(startEdge);
            corner.moveToEnd(newEdge);
            angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.STRAIGHT));
            angles.get(0).setSecond(OrthogonalAngle.LEFT);
            angles.get(1).setSecond(OrthogonalAngle.LEFT);
        }

        corner.orderAngles();

    }

    private void fixVirtualNode(final PEdge lastPathEdge, final PEdge virtualEdge,
            final PNode virtualNode, final PEdge newEdge) {
        List<Pair<PEdge, OrthogonalAngle>> list = Lists.newLinkedList();

        // invariant: the node has exact 3 adjacent edges
        virtualNode.moveToStart(newEdge);
        virtualNode.moveToEnd(virtualEdge);

        list.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.LEFT));
        list.add(new Pair<PEdge, OrthogonalAngle>(lastPathEdge, OrthogonalAngle.STRAIGHT));
        list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));

        this.orthogonal.setAngles(virtualNode, list);
        this.orthogonal.setBends(newEdge, new OrthogonalAngle[0]);
        this.orthogonal.setBends(virtualEdge, new OrthogonalAngle[0]);

    }

    private void addBendDummies(final PEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        List<Pair<PEdge, OrthogonalAngle>> list;
        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<PNode, PEdge> pair = this.graph.addNode(edge);
            pair.getFirst().setProperty(Properties.BENDPOINT, bends[i]);
            PEdge newedge = pair.getSecond();
            OrthogonalAngle b1 = bends[i];
            OrthogonalAngle b2 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
            list.add(new Pair<PEdge, OrthogonalAngle>(edge, b1));
            list.add(new Pair<PEdge, OrthogonalAngle>(newedge, b2));
            this.orthogonal.setAngles(pair.getFirst(), list);
            this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
            for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal
                    .getAngles(newedge.getTarget())) {
                if (entry.getFirst() == edge) {
                    entry.setFirst(newedge);
                }
            }
        }
        this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
    }

}
