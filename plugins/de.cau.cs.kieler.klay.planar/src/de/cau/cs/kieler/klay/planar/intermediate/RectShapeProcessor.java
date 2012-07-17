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

    /**
     * 
     */
    private static final int FACE_SIDE_NUMBER = 4;

    private PGraph graph;

    private OrthogonalRepresentation orthogonal;

    private final int index3 = 3;

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
        addVirtuals();
        this.graph.getFaces();
        getMonitor().done();
    }

    /**
     * 
     */
    private void addVirtuals() {

        transformExternalFace();

        // do a preselection
        List<PFace> noRectShapeFaces = Lists.newLinkedList();
        for (PFace face : graph.getFaces()) {
            // the external face is processed separately.
            if (face == graph.getExternalFace(false) || face.isInRectShape()) {
                continue;
            }
            noRectShapeFaces.add(face);
        }

        for (PFace face : noRectShapeFaces) {

            // step 1 and 2
            setEdgeProperties(face, false);

            // step 3
            determineFronts(face);

            // step 4
            // for each edge e, such that turn(e) = -1 (i.e., e and next(e) form a right turn),
            // insert a vertex project(e) along edge front(e), and add edge extend(e) =
            // (corner(e),
            // project(e)). ...

            // startEdge wählen, dazu den längsten pfad zu der front wählen!
            // arbitrary edge, but with longest path to the front.
            //
            Iterator<PEdge> edgeIt = face.adjacentEdges().iterator();
            PEdge currentEdge = null;
            PEdge startEdge = null;
            while (edgeIt.hasNext()) {
                currentEdge = edgeIt.next();
                if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1) {
                    startEdge = currentEdge;
                    break;
                }
            }
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
            currentEdge = startEdge;
            do {
                if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1) {
                    addArtificial(currentEdge, false);
                }
                currentEdge = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
            } while (currentEdge != startEdge);
        }
    }

    /**
     * @param face
     */
    private void determineFronts(final PFace face) {
        PEdge startEdge;
        PEdge currentEdge;
        PEdge previousEdge = null;
        PEdge next = null;
        PNode corner;
        int turn;
        int pathLength;
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
            do {
                pathLength++;

                if (previousEdge == null) {
                    turn += currentEdge.getProperty(Properties.RECT_SHAPE_TURN);
                    next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);

                    // special case
                    if (next.hasProperties()
                            && next.getProperty(Properties.RECT_SHAPE_SPECIALEDGE) != null) {
                        previousEdge = currentEdge;
                    }
                } else {
                    // special case
                    if (previousEdge.getSource() == currentEdge.getTarget()
                            || previousEdge.getSource() == currentEdge.getTarget()) {
                        corner = currentEdge.getSource();
                    } else {
                        corner = currentEdge.getTarget();
                    }
                    Pair<PEdge, OrthogonalAngle> nextEdgeWithAngle = face.nextEdgeWithAngle(corner,
                            currentEdge, this.orthogonal.getAngles(corner));
                    turn += determineTurn(nextEdgeWithAngle, false);
                    next = nextEdgeWithAngle.getFirst();
                    previousEdge = null;
                }

                if (turn == -1) {
                    startEdge.setProperty(Properties.RECT_SHAPE_FRONT, next);
                    startEdge.setProperty(Properties.RECT_SHAPE_PATH_LENGTH,
                            Integer.valueOf(pathLength));
                    break;
                }
                currentEdge = next;
            } while (currentEdge != startEdge);
        }

    }

    /**
     * @param face
     * @param next
     */
    private void setEdgeProperties(final PFace face, final boolean isExternal) {

        Pair<PNode, Pair<PEdge, PEdge>> dir = determineDirection(face);
        PNode corner = dir.getFirst();
        PEdge currentEdge = dir.getSecond().getFirst();
        PEdge next = dir.getSecond().getSecond();
        PEdge startEdge = currentEdge;

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
                    currentEdge, this.orthogonal.getAngles(corner));
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
            new InconsistentGraphModelException("ladida, du bist doof! :-)");
        }
        return edgeTurn;
    }

    /**
     * @return
     */
    private Pair<PNode, Pair<PEdge, PEdge>> determineDirection(final PFace face) {
        // determine ccw direction, therefore we use the embedding of the node angles which
        // are in counterclockwise order and we need a node with at least 3 adjacent edges.
        PNode corner = null;
        PEdge currentEdge = null;
        PEdge next = null;
        for (PNode node : face.adjacentNodes()) {
            if (node.getAdjacentEdgeCount() > 2) {
                corner = node;
                break;
            }
        }

        // determine ccw direction.
        List<Pair<PEdge, OrthogonalAngle>> directionAngles = orthogonal.getAngles(corner);
        int currentIndex = -1;
        for (int i = 0; i < directionAngles.size(); i++) {
            PEdge newEdge = directionAngles.get(i).getFirst();
            if (face.isAdjacent(newEdge)) {
                if (currentEdge == null) {
                    currentEdge = newEdge;
                    currentIndex = i;
                } else {
                    // check if the chosen edge is the successor of currentEdge
                    if (((currentIndex + 1) % directionAngles.size()) == i) {
                        next = currentEdge;
                        currentEdge = newEdge;
                    } else {
                        next = newEdge;
                    }
                    break;
                }
            }
        }
        return new Pair<PNode, Pair<PEdge, PEdge>>(corner,
                new Pair<PEdge, PEdge>(currentEdge, next));
    }

    /**
     * 
     */
    private void transformExternalFace() {

        PFace face = graph.getExternalFace(false);
        if (face.isInRectShape()) {
            return;
        }

        setEdgeProperties(face, false);

        determineFronts(face);

        // step 4
        // for each edge e, such that turn(e) = -1 (i.e., e and next(e) form a right turn),
        // insert a vertex project(e) along edge front(e), and add edge extend(e) =
        // (corner(e),
        // project(e)). ...

        // add rectangle to the graph.

        PNode[] rectNodes = new PNode[FACE_SIDE_NUMBER];
        rectNodes[0] = this.graph.addNode();
        rectNodes[0].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        rectNodes[1] = this.graph.addNode();
        rectNodes[1].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        rectNodes[2] = this.graph.addNode();
        rectNodes[2].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        rectNodes[3] = this.graph.addNode();
        rectNodes[3].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        // TODO make loop of this.
        PEdge[] faceSides = new PEdge[FACE_SIDE_NUMBER];
        faceSides[0] = this.graph.addEdge(rectNodes[0], rectNodes[1]);
        faceSides[0].setProperty(Properties.RECT_SHAPE_CORNER, rectNodes[1]);
        faceSides[0].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        faceSides[1] = this.graph.addEdge(rectNodes[1], rectNodes[2]);
        faceSides[1].setProperty(Properties.RECT_SHAPE_CORNER, rectNodes[2]);
        faceSides[1].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        faceSides[2] = this.graph.addEdge(rectNodes[2], rectNodes[3]);
        faceSides[2].setProperty(Properties.RECT_SHAPE_CORNER, rectNodes[3]);
        faceSides[2].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        faceSides[3] = this.graph.addEdge(rectNodes[3], rectNodes[0]);
        faceSides[3].setProperty(Properties.RECT_SHAPE_CORNER, rectNodes[0]);
        faceSides[3].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.valueOf(true));

        for (int i = 0; i < rectNodes.length; i++) {

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
        this.orthogonal.setBends(faceSides[0], new OrthogonalAngle[0]);
        this.orthogonal.setBends(faceSides[1], new OrthogonalAngle[0]);
        this.orthogonal.setBends(faceSides[2], new OrthogonalAngle[0]);
        this.orthogonal.setBends(faceSides[3], new OrthogonalAngle[0]);

        // 0 left, 1 top, 2 right, 3 bottom.
        int sideIndex = 0;

        // arbitrary startEdge
        PEdge startEdge = face.adjacentEdges().iterator().next();
        PEdge currentEdge = startEdge;
        PEdge next = null;

        do {
            int edgeturn = currentEdge.getProperty(Properties.RECT_SHAPE_TURN);
            next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);

            switch (edgeturn) {
            // left
            case 1:
                sideIndex = (sideIndex + 1) % FACE_SIDE_NUMBER;
                break;
            // straight
            case 0:
                // sideIndex remains constant.
                break;
            // right
            case -1:
                sideIndex = sideIndex - 1 >= 0 ? sideIndex - 1 : FACE_SIDE_NUMBER;
                break;
            default:
                throw new InconsistentGraphModelException("Du bist doof!");
            }
            currentEdge.setProperty(Properties.RECT_SHAPE_SIDE_INDEX, Integer.valueOf(sideIndex));
            currentEdge = next;
        } while (currentEdge != startEdge);

        do {
            int index = currentEdge.getProperty(Properties.RECT_SHAPE_SIDE_INDEX).intValue();
            if (currentEdge.getProperty(Properties.RECT_SHAPE_TURN).intValue() == 1
                    && currentEdge.getProperty(Properties.RECT_SHAPE_FRONT) == null) {
                currentEdge.setProperty(Properties.RECT_SHAPE_FRONT, faceSides[(index + 1)
                        % FACE_SIDE_NUMBER]);
                addArtificial(currentEdge, true);
                break;
            }

            next = currentEdge.getProperty(Properties.RECT_SHAPE_NEXT);
            currentEdge = next;
        } while (currentEdge != startEdge);

        for (PFace pface : graph.getFaces()) {
            if (pface.isAdjacent(faceSides[0]) && pface.getAdjacentEdgeCount() == 5) {
                graph.setExternalFace(pface);
            }
        }

    }

    private void addArtificial(final PEdge edge, final boolean isExternal) {
        PNode corner = edge.getProperty(Properties.RECT_SHAPE_CORNER);
        PEdge front = edge.getProperty(Properties.RECT_SHAPE_FRONT);
        PNode frontCorner = front.getProperty(Properties.RECT_SHAPE_CORNER);
        // add new node and new edge and set the orthogonal representation!
        Pair<PNode, PEdge> virtualPair = this.graph.addNode(front,
                isExternal ? front.getOppositeNode(frontCorner) : frontCorner);
        PNode projectE = virtualPair.getFirst();
        PEdge virtualEdge = virtualPair.getSecond();
        // add rect shape dummy property to determine later the dummy elements.
        projectE.setProperty(Properties.RECT_SHAPE_DUMMY, true);

        PEdge newEdge = this.graph.addEdge(corner, projectE);
        newEdge.setProperty(Properties.RECT_SHAPE_SPECIALEDGE, Boolean.TRUE);
        newEdge.setProperty(Properties.RECT_SHAPE_DUMMY, true);
        front.setProperty(Properties.RECT_SHAPE_CORNER, projectE);

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

        // wanted order: startEdge, successorEdge, newEdge
        corner.moveToStart(startEdge);
        corner.moveToEnd(newEdge);

        // add new node
        angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.STRAIGHT));
        corner.orderAngles();

        angles.get(0).setSecond(OrthogonalAngle.LEFT);
        angles.get(1).setSecond(OrthogonalAngle.LEFT);

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
