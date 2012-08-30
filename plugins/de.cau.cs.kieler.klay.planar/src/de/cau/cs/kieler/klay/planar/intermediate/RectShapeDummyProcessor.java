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
import de.cau.cs.kieler.klay.planar.p3compact.RectShapeEdgeProperties;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * Brings the faces of the graph in rectangular shape. Meaning that after this step there are exact
 * 4 bends per face, all in right or left order, depending from the start point.
 * 
 * @author pkl
 */
public class RectShapeDummyProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** Around a face in ccw direction is four times right. */
    private static final int CCW_DIRECTION = 4;

    /** The external face has at this point exact 5 adjacent edges. */
    private static final int EXTERNAL_EDGE_COUNT = 6;

    /** The number of face sides. */
    private static final int SIDE_NUMBER = 4;

    /** Left is 0, top is 1, right is 2 and bottom is 3. */
    private static final int BOTTOM_SIDE_INDEX = 3;

    /** The processed graph. */
    private PGraph graph;

    /** The orthogonal representation of that graph. */
    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Rectangular shaping", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        determineFaceDirections();

        // Decompose faces into rectangles and transform external and internal face separately.
        if (this.graph.getExternalFace().isInRectShape()) {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.FALSE);
        } else {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.TRUE);
            transformExternalFace();
        }

        transformInternalFaces();

        getMonitor().done();
    }

    /**
     * Transforms the all internal face to a rectangular shape. Therefore each edge gets some
     * properties. Assuming we go counter clockwise around each face, then if we found a edge that
     * form a left angle to its nextedge the face is not in rectangular shape. The corner is the
     * node that is adjacent to the nextedge and the current one. Then a left angle corner gets a
     * new dummy edge to the front of the corner. This is done for all left angle corners of the
     * face so that in addition the face is in rectangular shape. Doing this steps until there are
     * no non rect faces.
     */
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
            // for each edge e, such that turn(e) = 1 (i.e., e and next(e) form a left turn),
            // insert a vertex project(e) (dummy) along edge front(e)

            PEdge startEdge = determineStartEdge(face);
            PEdge currentEdge = startEdge;
            PEdge next = null;
            RectShapeEdgeProperties edgeProperties = currentEdge
                    .getProperty(Properties.RECT_SHAPE_PROPERTIES);
            PNode corner = edgeProperties.getCorner();
            PNode startNode = corner;

            do {
                if (edgeProperties == null) {
                    // Edge with two edge properties!
                    edgeProperties = currentEdge.getProperty(Properties.RECT_SHAPE_CUTEDGE)
                            .getFirst();
                }
                if (edgeProperties.getTurn() == 1 || edgeProperties.getTurn() == 2) {
                    addArtificial(currentEdge, edgeProperties, false);
                }
                next = edgeProperties.getNext();
                currentEdge = next;
                edgeProperties = currentEdge.getProperty(Properties.RECT_SHAPE_PROPERTIES);
                corner = edgeProperties.getCorner();
            } while (currentEdge != startEdge || corner != startNode);
        }
    }

    /**
     * Determines an arbitrary edge, but with longest path to the front. Since we need to find to a
     * front the first edge in ccw order and this is the edge with the longest path to it. The first
     * edge in ccw order is needed since at the inserting step the front is split into two edges and
     * this works only correct when we start with the ccw first edge with the front.
     * 
     * @param face
     *            the surrounding face, for which the startEdge is calculated.
     * @return the edge that is to a arbitrary front the counter clockwise first edge.
     */
    private PEdge determineStartEdge(final PFace face) {

        PEdge startEdge = null;
        PEdge currentEdge = null;
        Iterator<PEdge> edgeIt = face.adjacentEdges().iterator();

        // As a reference edge we take one that has a turn of 1.
        while (edgeIt.hasNext()) {
            currentEdge = edgeIt.next();

            RectShapeEdgeProperties edgeProperties = currentEdge
                    .getProperty(Properties.RECT_SHAPE_PROPERTIES);
            if (edgeProperties == null) {
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = currentEdge
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                RectShapeEdgeProperties firstProps = cutEdgeProps.getFirst();
                if (firstProps.getTurn() == 2) {
                    edgeProperties = firstProps;
                } else {
                    edgeProperties = cutEdgeProps.getSecond();
                }
            }

            if (edgeProperties.getTurn() == 1 || edgeProperties.getTurn() == 2) {
                // ensures that no cut vertex is used as startEdge
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
        RectShapeEdgeProperties startEdgeProperties = currentEdge
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (startEdgeProperties == null) {
            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = currentEdge
                    .getProperty(Properties.RECT_SHAPE_CUTEDGE);
            RectShapeEdgeProperties firstProps = cutEdgeProps.getFirst();
            if (firstProps.getTurn() == 2) {
                startEdgeProperties = firstProps;
            } else {
                startEdgeProperties = cutEdgeProps.getSecond();
            }
        }
        PEdge startFront = startEdgeProperties.getFront();
        int maxPathLength = startEdgeProperties.getPathLength();
        PNode startNode = startEdgeProperties.getCorner();
        PNode corner = startNode;
        RectShapeEdgeProperties properties = currentEdge
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        PEdge resultEdge = startEdge;
        do {
            if (properties == null) {
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = currentEdge
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                RectShapeEdgeProperties firstProps = cutEdgeProps.getFirst();
                if (firstProps.getTurn() == 2) {
                    properties = firstProps;
                } else {
                    properties = cutEdgeProps.getSecond();
                }
            }
            // TODO Think about the correction of that, turn means full angle but the other
            // properties can contain a a left (1) angle. shell both be tested or what????
            if (properties.getTurn() == 1 || properties.getTurn() == 2) {
                PEdge front = properties.getFront();
                int newPathLength = properties.getPathLength();
                if (front == startFront && newPathLength > maxPathLength) {
                    resultEdge = currentEdge;
                    maxPathLength = newPathLength;
                }
            }
            currentEdge = properties.getNext();
            properties = currentEdge.getProperty(Properties.RECT_SHAPE_PROPERTIES);
            corner = properties.getCorner();
        } while (currentEdge != startEdge || corner != startNode);

        return resultEdge;
    }

    /**
     * Runs around the graph and search for the front of every edge that has a rect shape turn of 1.
     * 
     * @param face
     */
    private void determineFronts(final PFace face) {

        // step 3
        // for each edge e, find the first edge e' following e counterclockwise, such that
        // the sum of the turn value for all the edges between (included) and e' (excluded)
        // is equal to 1, and set front(e) = e'.
        for (PEdge edge : face.adjacentEdges()) {

            RectShapeEdgeProperties edgeProperties = edge
                    .getProperty(Properties.RECT_SHAPE_PROPERTIES);
            // Check for normal edge.
            if (edgeProperties != null) {
                if (edgeProperties.getTurn() == -1) {
                    continue;
                }
            } else {
                // Handling cut edges
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = edge
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                RectShapeEdgeProperties firstProps = cutEdgeProps.getFirst();
                if (firstProps.getTurn() == 2) {
                    edgeProperties = firstProps;
                } else {
                    edgeProperties = cutEdgeProps.getSecond();
                }
            }
            PEdge startEdge;
            startEdge = edge;
            RectShapeEdgeProperties startEdgeProperties = edgeProperties;
            PNode startNode = startEdgeProperties.getCorner();
            PNode corner;
            PEdge currentEdge = startEdge;
            PEdge previousEdge = null;
            int turn = 0;
            int pathLength = 0;
            boolean wantsNewFront = false;
            do {
                pathLength++;
                turn += edgeProperties.getTurn();
                previousEdge = currentEdge;
                currentEdge = edgeProperties.getNext();
                if (turn == -1) {
                    startEdgeProperties.setPreviousFront(previousEdge);
                    startEdgeProperties.setFront(currentEdge);
                    startEdgeProperties.setPathLength(pathLength);
                    wantsNewFront = true;
                    break;
                }
                edgeProperties = currentEdge.getProperty(Properties.RECT_SHAPE_PROPERTIES);
                if (edgeProperties == null) {
                    Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = currentEdge
                            .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                    RectShapeEdgeProperties firstProps = cutEdgeProps.getFirst();
                    if (firstProps.getPreviousEdge() == previousEdge) {
                        edgeProperties = firstProps;
                    } else {
                        edgeProperties = cutEdgeProps.getSecond();
                    }
                }
                corner = edgeProperties.getCorner();
            } while (currentEdge != startEdge || startNode != corner);
            if (!wantsNewFront) {
                startEdgeProperties.setFront(null);
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
        // If isExternal go clockwise around the face adjacent edges, since if the new external face
        // is added to the graph, the old external face is surrounded clockwise and the new one ccw.
        PNode startNode = isExternal ? startEdge.getOppositeNode(startWithCorner.getFirst())
                : startWithCorner.getFirst();
        PNode corner = startNode;
        List<Pair<PEdge, PEdge>> path = Lists.newLinkedList();
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
        boolean isMultiAdjacentNode = false;

        // Initialize the first edge properties.
        RectShapeEdgeProperties properties = new RectShapeEdgeProperties();

        if (currentEdge.getLeftFace() == currentEdge.getRightFace()) {
            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = currentEdge
                    .getProperty(Properties.RECT_SHAPE_CUTEDGE);
            if (cutEdgeProps == null) {
                cutEdgeProps = new Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>(
                        properties, null);
                cutEdgeProps.setFirst(properties);
                currentEdge.setProperty(Properties.RECT_SHAPE_CUTEDGE, cutEdgeProps);
            } else {
                cutEdgeProps.setSecond(properties);
            }
        } else {
            currentEdge.setProperty(Properties.RECT_SHAPE_PROPERTIES, properties);
        }

        int counter = 0;
        boolean finish = false;
        // Iterate around the face edges and set the edge properties.
        do {

            if (face.isCutvertex(corner)) {
                isMultiAdjacentNode = true;
                // special case, if a node is passed more than twice.
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

            // Find next edge in the path.
            Pair<PEdge, OrthogonalAngle> pair = null;
            if (wantsCCW && !isExternal) {
                pair = face.nextCCWEdgeWithAngle(corner, currentEdge,
                        this.orthogonal.getAngles(corner), true);
            } else {
                pair = face.nextCWEdgeWithAngle(corner, currentEdge,
                        this.orthogonal.getAngles(corner), true);
            }
            if (isMultiAdjacentNode) {
                for (Pair<PEdge, PEdge> currPre : path) {
                    if (currPre.getFirst() == pair.getFirst() && currPre.getSecond() == currentEdge) {
                        if (wantsCCW && !isExternal) {
                            pair = face.nextCWEdgeWithAngle(corner, currentEdge,
                                    this.orthogonal.getAngles(corner), true);
                        } else {
                            pair = face.nextCCWEdgeWithAngle(corner, currentEdge,
                                    this.orthogonal.getAngles(corner), true);
                        }
                        break;
                    }
                }
                isMultiAdjacentNode = false;
            }
            if (!wantsCCW) {
                wantsCCW = true;
            }

            // Add properties to current edge
            properties.setCorner(corner);

            next = pair.getFirst();
            properties.setNext(next);

            int edgeTurn = determineTurn(pair.getSecond(), isExternal);
            properties.setTurn(edgeTurn);

            properties = next.getProperty(Properties.RECT_SHAPE_PROPERTIES);
            // Prepare next edge with new rectangular shape properties.
            if (properties == null) {
                // Determining a cutedge.
                if (next.getLeftFace() == next.getRightFace()) {
                    Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = next
                            .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                    if (cutEdgeProps == null) {
                        cutEdgeProps = new Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>(
                                properties, null);
                        properties = new RectShapeEdgeProperties();
                        cutEdgeProps.setFirst(properties);
                        next.setProperty(Properties.RECT_SHAPE_CUTEDGE, cutEdgeProps);
                    } else {
                        if (cutEdgeProps.getFirst().getPreviousEdge() == null) {
                            properties = cutEdgeProps.getFirst();
                        } else {
                            if (cutEdgeProps.getFirst().getPreviousEdge() == currentEdge) {
                                properties = cutEdgeProps.getFirst();
                            } else {
                                properties = cutEdgeProps.getSecond();
                                if (properties == null) {
                                    properties = new RectShapeEdgeProperties();
                                    cutEdgeProps.setSecond(properties);
                                }
                            }
                        }
                    }
                } else {
                    properties = new RectShapeEdgeProperties();
                    next.setProperty(Properties.RECT_SHAPE_PROPERTIES, properties);
                }
            }

            properties.setPreviousEdge(currentEdge);
            currentEdge = next;
            corner = next.getOppositeNode(corner);

            // Workaround to handle a fullangle corner at the start of the iteration.
            // There we go two times more along the path.
            if (counter > 0) {
                counter++;
            }
            if (currentEdge == startEdge && startNode == corner) {
                counter++;
            }
            if (counter == 3) {
                finish = true;
            }
        } while (!finish);
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
        case FULL:
            edgeTurn = 2;
        default:
            new InconsistentGraphModelException(
                    "RectShapeProcessor, determineTurn: unknown angle index!");
        }
        return edgeTurn;
    }

    /**
     * Runs counter clockwise around every face and store at each found new face a startEdge and a
     * corner in counterclockwise direction to determine the counterclockwise direction.
     * 
     */
    public void determineFaceDirections() {

        Set<PFace> visitedFaces = Sets.newHashSet();

        List<PFace> completedFaces = Lists.newArrayList();

        PFace externalFace = this.graph.getExternalFace();
        PFace currentFace = externalFace;

        PEdge startEdge = null;
        PEdge currentEdge = null;

        PNode startNode = null;
        PNode currentNode = null;

        // Determine startEdge and succeeding corner of the external face.
        Pair<PNode, PEdge> startWithCorner = determineCCWDirection(currentFace);
        currentFace.setProperty(Properties.FACE_DIRECTION, startWithCorner);
        visitedFaces.add(currentFace);

        while (currentFace != null) {

            startWithCorner = currentFace.getProperty(Properties.FACE_DIRECTION);
            startNode = startWithCorner.getFirst();
            currentNode = startNode;
            currentEdge = startWithCorner.getSecond();
            startEdge = currentEdge;
            PFace foundFace;
            do {
                foundFace = null;
                // checks if the faces adjacent to an edge are are known, if not calculate
                // a new startnode, startedge for it.
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

            } while (currentNode != startNode || startEdge != currentEdge);
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
     * Determines a startEdge and a startNode that is counterclockwise the next node to the edge.
     * 
     * @param face
     * @return
     */
    private Pair<PNode, PEdge> determineCCWDirection(final PFace face) {
        PEdge startEdge = face.adjacentEdges().iterator().next();
        Pair<Integer, Integer> plAndDirection;
        // TODO containscutvertex is to much, we need only to check for a node that is more than
        // two times connected to the face.
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
    }

    /**
     * Transforms the external face, if it is not in rectangular shape. The idea is to put a
     * rectangular around the external face and let the new rectangular be the external face.
     * Connect the old external face with the new one and one can treat the old external face like a
     * internal face. Therefore each edge gets some properties. Assuming we go clockwise around each
     * face, then if we found a edge that form a right angle to its nextedge the face is not in
     * rectangular shape. The corner is the node that is adjacent to the nextedge and the current
     * one. Then a right angle corner gets a new dummy edge to the front of the corner. This is done
     * for all left angle corners of the face so that in addition the face is in rectangular shape.
     */
    private void transformExternalFace() {

        PFace face = this.graph.getExternalFace();

        setEdgeProperties(face, true);

        // add rectangle to the graph.
        PNode[] rectNodes = new PNode[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            rectNodes[i] = this.graph.addNode();
            rectNodes[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.TRUE);
        }

        PEdge[] faceSides = new PEdge[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            faceSides[i] = this.graph.addEdge(rectNodes[i], rectNodes[(i + 1) % SIDE_NUMBER]);
            RectShapeEdgeProperties edgeProps = new RectShapeEdgeProperties();
            edgeProps.setCorner(rectNodes[(i + 1) % SIDE_NUMBER]);
            faceSides[i].setProperty(Properties.RECT_SHAPE_PROPERTIES, edgeProps);
            faceSides[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.TRUE);
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

        RectShapeEdgeProperties properties = currentEdge
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (properties == null) {
            properties = currentEdge.getProperty(Properties.RECT_SHAPE_CUTEDGE).getFirst();
        }

        PNode startNode = properties.getCorner();
        PNode currentNode = startNode;

        PEdge next = null;

        do {
            int edgeturn = properties.getTurn();
            next = properties.getNext();
            properties.setSideIndex(sideIndex);

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
            case 2:
                sideIndex = (sideIndex + 2) % SIDE_NUMBER;
                break;
            default:
                throw new InconsistentGraphModelException(
                        "RectShapeDummyProcessor, trans ext: Unknown edge turn!");
            }

            properties = next.getProperty(Properties.RECT_SHAPE_PROPERTIES);
            if (properties == null) {
                // Handling cut edges.
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = next
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                if (cutEdgeProps.getFirst().getPreviousEdge() == currentEdge) {
                    properties = cutEdgeProps.getFirst();
                } else {
                    properties = cutEdgeProps.getSecond();
                }
            }
            currentEdge = next;
            currentNode = properties.getCorner();

        } while (currentEdge != startEdge || currentNode != startNode);

        // add two edge make the external face to an internal one.
        int usedSide = -1;
        int countAdded = 0;

        RectShapeEdgeProperties edgeProperties = currentEdge
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (edgeProperties == null) {
            edgeProperties = currentEdge.getProperty(Properties.RECT_SHAPE_CUTEDGE).getFirst();
        }

        startNode = edgeProperties.getCorner();

        do {
            int index = edgeProperties.getSideIndex();
            int turn = edgeProperties.getTurn();
            if ((turn == -1 || turn == 2) && edgeProperties.getFront() == null && index != usedSide) {

                edgeProperties.setFront(faceSides[index]);
                usedSide = index;

                addArtificial(currentEdge, edgeProperties, true);
                countAdded++;
                if (countAdded == 2) {
                    break;
                }
            }
            next = edgeProperties.getNext();
            edgeProperties = next.getProperty(Properties.RECT_SHAPE_PROPERTIES);
            if (edgeProperties == null) {
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = next
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                if (cutEdgeProps.getFirst().getPreviousEdge() == currentEdge) {
                    edgeProperties = cutEdgeProps.getFirst();
                } else {
                    edgeProperties = cutEdgeProps.getSecond();
                }
            }
            currentNode = edgeProperties.getCorner();
            currentEdge = next;
        } while (currentEdge != startEdge || currentNode != startNode);

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
     * Inserts an artificial node to the front of the given edge and adds a edge from the edges
     * corner to the new node. Updates all elements including orthogonal representation.
     * 
     * @param edge
     *            currentEdge
     * @param edgeProperties
     *            of that edge, cutedges have two edge properties.
     * @param isExternal
     */
    private void addArtificial(final PEdge edge, final RectShapeEdgeProperties edgeProperties,
            final boolean isExternal) {
        PNode corner = edgeProperties.getCorner();
        PEdge front = edgeProperties.getFront();
        RectShapeEdgeProperties frontProperties = front
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (frontProperties == null) {
            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProp = front
                    .getProperty(Properties.RECT_SHAPE_CUTEDGE);
            frontProperties = cutEdgeProp.getFirst();
            if (frontProperties.getNext() == edgeProperties.getPreviousFront()) {
                frontProperties = cutEdgeProp.getSecond();
            }
        }
        PNode frontCorner = frontProperties.getCorner();
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
        frontProperties.setCorner(projectE);

        RectShapeEdgeProperties vEdgeProperties = new RectShapeEdgeProperties();
        vEdgeProperties.setCorner(virtualEdge.getOppositeNode(projectE));
        vEdgeProperties.setNext(frontProperties.getNext());
        vEdgeProperties.setTurn(frontProperties.getTurn());
        vEdgeProperties.setFront(frontProperties.getFront());
        virtualEdge.setProperty(Properties.RECT_SHAPE_PROPERTIES, vEdgeProperties);
        frontProperties.setNext(virtualEdge);
        frontProperties.setTurn(0);
        frontProperties.setFront(null);

        // Fix embedding and/or angles of startNode
        fixStartNode(edge, corner, newEdge);

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
    private void fixStartNode(final PEdge startEdge, final PNode corner, final PEdge newEdge) {
        // fix node embedding: is implicit done by graph.addEdge(..)

        // fix angles
        List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(corner);

        boolean containsFullAngle = false;
        // Check if angles contains full edge.
        for (Pair<PEdge, OrthogonalAngle> pair : angles) {
            if (pair.getSecond() == OrthogonalAngle.FULL) {
                containsFullAngle = true;
                break;
            }
        }
        if (containsFullAngle) {
            angles.get(0).setSecond(OrthogonalAngle.STRAIGHT);
            angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.STRAIGHT));
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

}
