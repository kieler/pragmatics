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

import com.google.common.collect.Lists;

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

        // Decompose faces into rectangles
        transformExternalFace();

        transformInternalFaces();

        getMonitor().done();
    }

    private void transformInternalFaces() {
        boolean wantsFinish = false;
        PFace face = null;
        while (true) {
            wantsFinish = true;
            for (PFace checkFace : graph.getFaces()) {

                // do a pre-selection, note: the new external face is already in rect shape.
                if (checkFace.isInRectShape(false)) {
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
                // if (next.hasProperties()
                // && next.getProperty(Properties.RECT_SHAPE_CUT_EDGE) != null
                // && next.getProperty(Properties.RECT_SHAPE_NEXT) == currentEdge) {
                // use the opposite direction.
                // currentEdge = next.getProperty(Properties.RECT_SHAPE_CUT_EDGE).getSecond();
                // } else {
                currentEdge = next;
                // }
            } while (currentEdge != startEdge);
        }
    }

    /**
     * @param face
     * @return
     */
    private PEdge determineStartEdge(final PFace face) {
        // arbitrary edge, but with longest path to the front.
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

        // Choose as startEdge a edge is has the longest path to its front.
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
     * @param face
     * @param next
     */
    private void setEdgeProperties(final PFace face, final boolean isExternal) {

        Pair<PNode, Pair<PEdge, PEdge>> directionPair = face.determineDirection(this.orthogonal,
                isExternal);
        PNode corner = directionPair.getFirst();
        PEdge startEdge = directionPair.getSecond().getFirst();

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
        do {
            currentEdge.setProperty(Properties.RECT_SHAPE_CORNER, corner);

            Pair<PEdge, OrthogonalAngle> newEdgeWithAngle = face.nextEdgeWithAngle(corner,
                    currentEdge, this.orthogonal.getAngles(corner), false);
            next = newEdgeWithAngle.getFirst();
            currentEdge.setProperty(Properties.RECT_SHAPE_NEXT, next);

            int edgeTurn = determineTurn(newEdgeWithAngle, isExternal);
            currentEdge.setProperty(Properties.RECT_SHAPE_TURN, edgeTurn);

            currentEdge = next;
            corner = next.getOppositeNode(corner);

        } while (currentEdge != startEdge);
    }

    private int determineTurn(final Pair<PEdge, OrthogonalAngle> newEdgeWithAngle,
            final boolean isExternal) {
        int edgeTurn = 0;
        switch (newEdgeWithAngle.getSecond()) {
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
     * Transforms the external face, if it is not in rectangular shape. The idea is to put a
     * rectangular around the external face and let the new rectangular be the external face.
     * Connect the old external face with the new one and one can treat the old external face like a
     * internal face.
     */
    private void transformExternalFace() {

        PFace face = graph.getExternalFace(false);
        if (face.isInRectShape(true)) {
            return;
        }

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
        out: for (PFace pface : graph.getFaces()) {
            for (PEdge edge : faceSides) {
                if (!pface.isAdjacent(edge)) {
                    continue out;
                }
            }
            if (pface.getAdjacentEdgeCount() == EXTERNAL_EDGE_COUNT) {
                graph.setExternalFace(pface);
                break;
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

        front.setProperty(Properties.RECT_SHAPE_NEXT, virtualEdge);
        front.setProperty(Properties.RECT_SHAPE_TURN, 0);

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
