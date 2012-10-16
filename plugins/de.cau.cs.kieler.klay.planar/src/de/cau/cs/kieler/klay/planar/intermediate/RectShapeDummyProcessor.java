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
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * 
 * Brings the faces of the graph in rectangular shape. Meaning that after this step there are exact
 * 4 bends per face, all in right or left order, depending from the start point.
 * 
 * @author pkl
 */
public class RectShapeDummyProcessor extends AbstractAlgorithm implements ILayoutProcessor {

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
        PFace externalFace = this.graph.getExternalFace();

        // Calculates a start edge and its corner in counter clockwise direction.
        Pair<PNode, PEdge> startWithCorner = externalFace.determineCCWDirection();

        // Swaps corner in clockwise direction.
        startWithCorner.setFirst(startWithCorner.getSecond().getOppositeNode(
                startWithCorner.getFirst()));
        externalFace.setProperty(Properties.FACE_DIRECTION, startWithCorner);

        // Decompose faces into rectangles and transform external and internal face separately.
        if (externalFace.isInRectShape()) {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.FALSE);
        } else {
            this.graph.setProperty(Properties.RECT_SHAPE_TRANS_EXTERNAL, Boolean.TRUE);
            transformExternalFace();
        }

        transformInternalFaces();

        getMonitor().done();
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

        determineFronts(face);

        // add rectangle to the graph.
        PNode[] rectNodes = new PNode[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            rectNodes[i] = this.graph.addNode();
            rectNodes[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.TRUE);
        }

        // adds the rectangle around the graph
        PEdge[] faceSides = new PEdge[SIDE_NUMBER];
        for (int i = 0; i < SIDE_NUMBER; i++) {
            faceSides[i] = this.graph.addEdge(rectNodes[i], rectNodes[(i + 1) % SIDE_NUMBER]);
            RectShapeEdgeProperties edgeProps = new RectShapeEdgeProperties();
            edgeProps.setCorner(rectNodes[(i + 1) % SIDE_NUMBER]);
            faceSides[i].setProperty(Properties.RECT_SHAPE_PROPERTIES, edgeProps);
            faceSides[i].setProperty(Properties.RECT_SHAPE_DUMMY, Boolean.TRUE);
            this.orthogonal.setBends(faceSides[i], new OrthogonalAngle[i]);
        }

        // sets the orthogonal representation of the dummy rectangle
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

        // determine the face side for setting the edge to the correct edge on the external
        // rectangle.
        // TODO merge this step and the below one
        do {
            int edgeturn = properties.getTurn();
            next = properties.getNext();
            properties.setSideIndex(sideIndex);
            switch (edgeturn) {
            // left
            case 1:
                sideIndex = (sideIndex + 1) % SIDE_NUMBER;
                break;
            // straight
            case 0:
                // sideIndex remains constant.
                break;
            // right
            case -1:
                sideIndex = sideIndex > 0 ? sideIndex - 1 : BOTTOM_SIDE_INDEX;
                break;
            case 2:
                sideIndex = (sideIndex + 2) % SIDE_NUMBER;
                break;
            default:
                throw new InconsistentGraphModelException(
                        "RectShapeDummyProcessor, trans ext: Unknown edge turn!");
            }

            properties = PUtil.getProperties(next, currentNode);
            currentEdge = next;
            currentNode = properties.getCorner();

        } while (currentEdge != startEdge || currentNode != startNode);

        // determines the start edge. It must be the first edge in clockwise direction
        // with undefined front corresponding to a given side.
        // calc start edge

        // add two edge make the external face to an internal one.
        List<Integer> usedSides = Lists.newLinkedList();
        int countAdded = 0;

        RectShapeEdgeProperties edgeProperties = PUtil.getProperties(currentEdge, null);

        startNode = edgeProperties.getCorner();

        // Adds exact two edges that connects the new rectangular external face with the
        // old external face.
        do {
            int index = edgeProperties.getSideIndex();
            int turn = edgeProperties.getTurn();
            if ((turn == 1 || turn == 2)
                    && edgeProperties.getFront() == RectShapeEdgeProperties.EMPTY_FRONT) {

                boolean wantsDummies = true;
                for (Integer si : usedSides) {
                    if (si.intValue() == index) {
                        wantsDummies = false;
                    }
                }
                if (wantsDummies) {
                    edgeProperties.setFront(faceSides[index]);
                    usedSides.add(Integer.valueOf(index));

                    addArtificial(currentEdge, edgeProperties, true, null);
                    countAdded++;
                    if (countAdded == 2) {
                        break;
                    }
                }
            }
            next = edgeProperties.getNext();
            currentEdge = next;
            edgeProperties = PUtil.getProperties(currentEdge, currentNode);
            currentNode = edgeProperties.getCorner();
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
     * Transforms the all internal face to a rectangular shape. Therefore each edge gets some
     * properties. Each face is traversed ounter clockwise, then if we find an edge that form a left
     * angle to its nextedge the face is not in rectangular shape. The corner is the node that is
     * adjacent to the nextedge and the current one. Then a left angle corner gets a new dummy edge
     * to the front of the corner. This is done for all left angle corners of the face so that in
     * addition the face is in rectangular shape. This bases on the compaction steps of the book
     * Graph Drawing: Algorithms for the Visualization of Graphs by Battista et al.
     */
    private void transformInternalFaces() {

        Iterable<PFace> graphFaces = this.graph.getFaces();

        List<PFace> notRectFaces = Lists.newLinkedList();

        // Check whether a face is not in rect shape, if so, start the process,
        // if all edges are in rect shape, we are finished!
        for (PFace checkFace : graphFaces) {

            // Calcs a startedge and its corner in counter clockwise direction.
            Pair<PNode, PEdge> startWithCorner = checkFace.determineCCWDirection();
            checkFace.setProperty(Properties.FACE_DIRECTION, startWithCorner);

            // The External face needs no processing. For the special case there are a rectangle
            // with exact two faces and only the rectangle edge.
            if (checkFace == graph.getExternalFace()) {
                // Swap start direction since the external face is passed clockwise.
                startWithCorner.setFirst(startWithCorner.getSecond().getOppositeNode(
                        startWithCorner.getFirst()));
                checkFace.setProperty(Properties.FACE_DIRECTION, startWithCorner);
                continue;
            }

            // Do a pre-selection, note: the new external face is already in rect shape.
            if (!checkFace.isInRectShape()) {
                notRectFaces.add(checkFace);
            }
        }

        for (PFace face : notRectFaces) {

            // step 1 and 2
            setEdgeProperties(face, false);

            // step 3
            determineFronts(face);

            // step 4
            // for each edge e, such that turn(e) = 1 (i.e., e and next(e) form a left turn), or
            // turn(e) = 2 such that there is a full turn,
            // insert a vertex project(e) (dummy) along edge front(e)

            Pair<PEdge, RectShapeEdgeProperties> start = determineStart(face);
            PEdge startEdge = start.getFirst();
            PEdge currentEdge = startEdge;
            RectShapeEdgeProperties edgeProperties = start.getSecond();
            PEdge next = edgeProperties.getNext();
            PNode corner = edgeProperties.getCorner();
            PNode startNode = corner;

            do {
                if ((edgeProperties.getTurn() == 1 || edgeProperties.getTurn() == 2)
                        && edgeProperties.getFront() != RectShapeEdgeProperties.EMPTY_FRONT) {
                    addArtificial(currentEdge, edgeProperties, false, face.adjacentEdges());
                }
                next = edgeProperties.getNext();
                currentEdge = next;
                edgeProperties = PUtil.getProperties(currentEdge, corner);
                corner = edgeProperties.getCorner();
            } while (currentEdge != startEdge || corner != startNode);
        }

        for (PFace f : graph.getFaces()) {
            Pair<PNode, PEdge> startWithCorner = f.getProperty(Properties.FACE_DIRECTION);
            if (startWithCorner == null) {
                Pair<PNode, PEdge> props = f.determineCCWDirection();
                f.setProperty(Properties.FACE_DIRECTION, props);
            }
        }
    }

    /**
     * Determines the edge with the longest edge path to its front. This avoid edge crossings at the
     * insertion of dummy vertices.
     * 
     * @param face
     *            the surrounding face, for which the startEdge is calculated.
     * @return the edge and the properties in clockwise direction.
     */
    private Pair<PEdge, RectShapeEdgeProperties> determineStart(final PFace face) {
        int currentLongest = 0;
        PEdge resultEdge = null;
        RectShapeEdgeProperties resultProps = null;

        for (PEdge edge : face.adjacentEdges()) {
            RectShapeEdgeProperties edgeProperties = edge
                    .getProperty(Properties.RECT_SHAPE_PROPERTIES);
            if (edgeProperties == null) {
                RectShapeEdgeProperties cutEdgeProps1 = PUtil.getProperties(edge, edge.getSource());
                if (cutEdgeProps1.getPathLength() > currentLongest) {
                    resultEdge = edge;
                    resultProps = cutEdgeProps1;
                    currentLongest = cutEdgeProps1.getPathLength();
                }

                RectShapeEdgeProperties cutEdgeProps2 = PUtil.getProperties(edge, edge.getTarget());
                if (cutEdgeProps2.getPathLength() > currentLongest) {
                    resultEdge = edge;
                    resultProps = cutEdgeProps2;
                    currentLongest = cutEdgeProps2.getPathLength();
                }

            } else {
                if (edgeProperties.getPathLength() > currentLongest) {
                    resultEdge = edge;
                    resultProps = edgeProperties;
                    currentLongest = edgeProperties.getPathLength();
                }
            }
        }
        return new Pair<PEdge, RectShapeEdgeProperties>(resultEdge, resultProps);
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
            if (edgeProperties == null) {
                // Handling cut edges that contains two direction properties.
                Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = edge
                        .getProperty(Properties.RECT_SHAPE_CUTEDGE);
                determineFront(edge, cutEdgeProps.getFirst());
                determineFront(edge, cutEdgeProps.getSecond());
            } else {
                // Check for normal edge.
                determineFront(edge, edgeProperties);
            }
        }

    }

    /**
     * determines the front for a single edge with its edgeProperties.
     * 
     * @param edge
     *            for which the front has to be determined.
     * @param startEdgeProperties
     *            the properties of the edge.
     */
    private void determineFront(final PEdge edge, final RectShapeEdgeProperties startEdgeProperties) {
        if (startEdgeProperties.getTurn() == -1 || startEdgeProperties.getTurn() == 0) {
            startEdgeProperties.setFront(RectShapeEdgeProperties.EMPTY_FRONT);
            return;
        }

        PEdge startEdge = edge;
        RectShapeEdgeProperties edgeProperties = startEdgeProperties;
        PNode startNode = startEdgeProperties.getCorner();
        PNode corner = startNode;
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
            edgeProperties = PUtil.getProperties(currentEdge, corner);
            corner = edgeProperties.getCorner();
        } while (currentEdge != startEdge || corner != startNode);
        if (!wantsNewFront) {
            startEdgeProperties.setFront(RectShapeEdgeProperties.EMPTY_FRONT);
        }
    }

    /**
     * Sets the next edge, turn and corner of every edge.
     * 
     * @param face
     * @param next
     */
    private void setEdgeProperties(final PFace face, final boolean isExternal) {

        clearOldProperties(face);

        Pair<PNode, PEdge> startWithCorner = face.getProperty(Properties.FACE_DIRECTION);
        if (startWithCorner == null) {
            throw new InconsistentGraphModelException(
                    "To use this method, the property FACE_DIRECTION has to be defined!");
        }

        PEdge startEdge = startWithCorner.getSecond();
        PNode startNode = startWithCorner.getFirst();
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

        // Initialize the first edge properties.
        RectShapeEdgeProperties properties = PUtil.getProperties(currentEdge, null);

        int counter = 0;
        boolean finish = false;

        // Iterate around the face edges and set the edge properties.
        do {

            Pair<PEdge, OrthogonalAngle> pair = face.nextAdjacentElement(currentEdge, corner);

            // Add properties to current edge
            properties.setCorner(corner);

            next = pair.getFirst();
            path.add(new Pair<PEdge, PEdge>(next, currentEdge));
            properties.setNext(next);

            int edgeTurn = determineTurn(pair.getSecond(), isExternal);
            properties.setTurn(edgeTurn);

            properties = PUtil.getProperties(next, corner);
            properties.setPreviousEdge(currentEdge);
            currentEdge = next;
            corner = next.getOppositeNode(corner);

            // Workaround to handle a fullangle corner at the start of the iteration.
            // There we go two times more along the path.
            if (counter > 0) {
                counter++;
            }
            if (currentEdge == startEdge && startNode == corner) {
                path.clear();
                counter = 1;
            }

            // runs at least twice along a full angle node.
            if (counter == 3) {
                finish = true;
            }

        } while (!finish);
    }

    /**
     * Removes the old rect shape properties.
     * 
     * @param face
     */
    private void clearOldProperties(final PFace face) {
        for (PEdge edge : face.adjacentEdges()) {
            edge.setProperty(Properties.RECT_SHAPE_CUTEDGE, null);
            edge.setProperty(Properties.RECT_SHAPE_PROPERTIES, null);
        }

    }

    private int determineTurn(final OrthogonalAngle angle, final boolean isExternal) {
        int edgeTurn = 0;
        switch (angle) {
        case LEFT:
            edgeTurn = 1;
            break;
        case STRAIGHT:
            edgeTurn = 0;
            break;
        case RIGHT:
            edgeTurn = -1;
            break;
        case FULL:
            edgeTurn = 2;
            break;
        default:
            throw new InconsistentGraphModelException(
                    "RectShapeProcessor, determineTurn: unknown angle index!");
        }
        return edgeTurn;
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
            final boolean isExternal, Iterable<PEdge> faceEdges) {
        PNode corner = edgeProperties.getCorner();
        PEdge front = edgeProperties.getFront();
        RectShapeEdgeProperties frontProperties = front
                .getProperty(Properties.RECT_SHAPE_PROPERTIES);
        RectShapeEdgeProperties backDirectionProps = null;
        if (frontProperties == null) {
            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProp = front
                    .getProperty(Properties.RECT_SHAPE_CUTEDGE);
            frontProperties = cutEdgeProp.getFirst();
            backDirectionProps = cutEdgeProp.getSecond();
            if (frontProperties.getNext() == edgeProperties.getPreviousFront()) {
                frontProperties = cutEdgeProp.getSecond();
                backDirectionProps = cutEdgeProp.getFirst();
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

        newEdge.setProperty(Properties.RECT_SHAPE_DUMMY, true);
        frontProperties.setCorner(projectE);

        // if the front is a cutedge the virtual edge next to the front needs to be a cutedge, too
        updateFrontProperties(front, frontProperties, backDirectionProps, projectE, virtualEdge);

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
     * Updates the edge properties of the involved edges. Special focus is on the handling of
     * cutedges. The front and the virtual edge properties are updated as well as the next of the
     * previous edge and the previous of the next edge.
     * 
     * @param front
     *            front edge
     * @param frontProperties
     *            front properties in the direction the path is traversed
     * @param backDirectionProps
     *            back direction in the counter direction of the traversed path
     * @param projectE
     *            the inserted dummy node
     * @param virtualEdge
     *            the virtual inserted edge, for which all edge properties have to be determined
     */
    private void updateFrontProperties(final PEdge front,
            final RectShapeEdgeProperties frontProperties,
            final RectShapeEdgeProperties backDirectionProps, final PNode projectE,
            final PEdge virtualEdge) {
        if (backDirectionProps != null) {

            // handle front direction
            RectShapeEdgeProperties vEdgeProperties1 = new RectShapeEdgeProperties();
            vEdgeProperties1.setCorner(virtualEdge.getOppositeNode(projectE));
            vEdgeProperties1.setNext(frontProperties.getNext());
            vEdgeProperties1.setTurn(frontProperties.getTurn());
            vEdgeProperties1.setFront(frontProperties.getFront());
            vEdgeProperties1.setPreviousEdge(front);
            frontProperties.setNext(virtualEdge);
            frontProperties.setTurn(0);
            frontProperties.setFront(null);

            // for(PEdge edge : faceEdges){
            // TODO hier front und prefront abgleichen und dann umsetzen auf die virtual edge
            // auch fuer die andere direction machen sowie für ohne backDirectionProps..
            // }

            // update previous of next edge
            PEdge nextEdge = vEdgeProperties1.getNext();
            RectShapeEdgeProperties nextProps = PUtil.getProperties(nextEdge,
                    virtualEdge.getOppositeNode(projectE));
            nextProps.setPreviousEdge(virtualEdge);

            // handle back front direction
            RectShapeEdgeProperties vEdgeProperties2 = new RectShapeEdgeProperties();
            vEdgeProperties2.setCorner(projectE);
            vEdgeProperties2.setNext(front);
            vEdgeProperties2.setTurn(0);
            vEdgeProperties2.setFront(null);
            vEdgeProperties2.setPreviousEdge(backDirectionProps.getPreviousEdge());

            // update next of previous edge
            PEdge previousEdge = backDirectionProps.getPreviousEdge();

            // determine edge in correct direction and set its next to virtual node instead of front
            RectShapeEdgeProperties prevProps = PUtil.getProperties(previousEdge,
                    virtualEdge.getOppositeNode(projectE));
            if (prevProps.getNext() == front) {
                prevProps.setNext(virtualEdge);
            } else {
                prevProps = PUtil.getProperties(previousEdge, prevProps.getCorner());
                prevProps.setNext(virtualEdge);
            }

            backDirectionProps.setPreviousEdge(virtualEdge);

            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProp = new Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>(
                    vEdgeProperties1, vEdgeProperties2);
            virtualEdge.setProperty(Properties.RECT_SHAPE_CUTEDGE, cutEdgeProp);

        } else {
            RectShapeEdgeProperties vEdgeProperties = new RectShapeEdgeProperties();
            vEdgeProperties.setCorner(virtualEdge.getOppositeNode(projectE));
            vEdgeProperties.setNext(frontProperties.getNext());
            vEdgeProperties.setTurn(frontProperties.getTurn());
            vEdgeProperties.setFront(frontProperties.getFront());
            virtualEdge.setProperty(Properties.RECT_SHAPE_PROPERTIES, vEdgeProperties);
            frontProperties.setNext(virtualEdge);
            frontProperties.setTurn(0);
            frontProperties.setFront(null);
        }
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
