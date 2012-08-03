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
package de.cau.cs.kieler.klay.planar.util;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Util class for the orthogonalization layouter klay.planar, which contains general functions that
 * are used in several parts of the algorithm.
 * 
 * @author pkl
 */
public class PUtil {

    private static final int FACE_SIDES_NUMBER = 4;

    /**
     * This method iterates over all faces of the graph and stores them in the left, top, right and
     * bottom side. This can be used, to determine how long a edge has to be. Meaning the opposite
     * edges have to have the same length. This works because of the rectangular shape of the input
     * faces. Attention: This works only for graphs with rectangular face-shapes...
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void defineFaceSideEdges(final PGraph graph) {

        List<PFace> visitedFaces = Lists.newArrayList();

        List<PFace> completedFaces = Lists.newArrayList();

        PFace externalFace = graph.getExternalFace();
        OrthogonalRepresentation ortho = graph.getProperty(Properties.ORTHO_REPRESENTATION);
        PFace currentFace = externalFace;

        // Choose a arbitrary edge of the face.
        // It is enough to store the edge on the face sides in arbitrary order,
        // here are no dependency to other faces stored. E.g. it is possible, that
        // e1 at f1 is on the left but the same edge e1 at f2 on the top.
        // FIXED, now if e right of face1 and secondly adjacent to face2
        // it must be the opposite site of face2 (here left).
        PEdge startEdge = currentFace.adjacentEdges().iterator().next();

        int sideIndex = 0;

        // The currently processed edge, starting with a arbitrary chosen one.
        PEdge currentEdge = startEdge;

        while (currentFace != null) {

            // 0 for left, 1 for top, 2 for right, 3 for bottom.
            List[] faceSides = new ArrayList[FACE_SIDES_NUMBER];

            for (int i = 0; i < faceSides.length; i++) {
                faceSides[i] = new ArrayList<PEdge>();
            }

            PNode currentNode = null;
            // at the start of a face, previousNode is null and we have to determine the
            // clockwise order, otherwise the faceSide indices aren't fit.
            currentNode = findCWNextNode(startEdge, currentFace, ortho);

            // starts with the startEdge and runs around the chosen face until the startEdge has
            // found. Then all edges of the face are a part of a face-side.
            do {

                List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(currentNode);
                // first get the current edge to determine the direction of the next edge,
                // if the next edge a face edge handle edge convenient,
                int currentIndex = -1;

                // find the currentEdge and store the index.
                for (int i = 0; i < angles.size(); i++) {
                    if (angles.get(i).getFirst() == currentEdge) {
                        currentIndex = i;
                        break;
                    }
                }

                int directionCounter = 0;
                boolean otherface = false;
                Pair<PEdge, OrthogonalAngle> pair = null;
                // Goes around a node and checks if a face-edge is straight to an other
                // face-edge or if there is a face-side change. Thereby have to regard the other
                // node-edges and that's why there is a direction-counter (1 for left, 2 for
                // straight and 3 for right), although left and right are both count as knee. That
                // is possible, because of the rectangular face-shape.

                while (true) {
                    // clockwise direction
                    currentIndex = currentIndex  > 0 ? currentIndex - 1 : angles.size() - 1;
                    pair = angles.get(currentIndex);

                    if (!otherface && currentFace.isAdjacent(pair.getFirst())) {
                        currentEdge = pair.getFirst();
                        sideIndex = determineIndex(sideIndex, angles.get(currentIndex).getSecond()
                                .ordinal() + 1);
                        faceSides[sideIndex].add(currentEdge);
                        // hasFound
                        break;
                    } else {
                        otherface = true;
                        // look at the direction of the previous edge to determine the direction
                        directionCounter += angles.get(currentIndex).getSecond().ordinal() + 1;
                        if (currentFace.isAdjacent(pair.getFirst())) {
                            currentEdge = pair.getFirst();
                            if (directionCounter != 2) {
                                sideIndex = determineIndex(sideIndex, directionCounter);
                            }
                            faceSides[sideIndex].add(currentEdge);
                            // hasFound
                            break;
                        }
                    }
                }
                currentNode = currentEdge.getOppositeNode(currentNode);

            } while (currentEdge != startEdge);

            // put face-sides to the current face.
            currentFace.setProperty(Properties.FACE_SIDES, faceSides);
            visitedFaces.add(currentFace);

            // choose next face
            currentFace = null;
            out: for (PFace visitedFace : visitedFaces) {
                if (completedFaces.contains(visitedFace)) {
                    continue;
                }
                List<PEdge>[] sides = visitedFace.getProperty(Properties.FACE_SIDES);
                for (int i = 0; i < sides.length; i++) {
                    for (PEdge edge : sides[i]) {
                        if (edge.getRightFace() != visitedFace
                                && !visitedFaces.contains(edge.getRightFace())) {
                            currentFace = edge.getRightFace();
                        } else if (edge.getLeftFace() != visitedFace
                                && !visitedFaces.contains(edge.getLeftFace())) {
                            currentFace = edge.getLeftFace();
                        }
                        if (currentFace != null) {
                            currentEdge = edge;
                            startEdge = currentEdge;
                            if (visitedFace == externalFace) {
                                // same side
                                sideIndex = i;
                            } else {
                                // opposite side
                                sideIndex = (i + 2) % FACE_SIDES_NUMBER;
                            }
                            break out;
                        }
                    }
                }
                completedFaces.add(visitedFace);
            }
        }
    }

    /**
     * @param sideIndex
     * @param pair
     * @return
     */
    private static int determineIndex(final int sideIndex, final int direction) {
        switch (direction) {
        // left
        case 1:
            return sideIndex > 0 ? sideIndex - 1 : FACE_SIDES_NUMBER - 1;
            // right
        case 2:
            return sideIndex;
            // straight
        case 3:
            return (sideIndex + 1) % FACE_SIDES_NUMBER;
            // full
        case 4:
            return (sideIndex + 2) % FACE_SIDES_NUMBER;
        default:
            throw new InconsistentGraphModelException(/* text */);
        }
    }

    
    /**
     * Searches for the next clockwise node of the face by determining a node of the currentEdge. If
     * a previousNode is known, it is easy to determine the next node. Then one can choose the other
     * node of a edge. If the previous node is not known, it is a bit tricky.
     * 
     * @return
     * 
     */
    public static PNode findCWNextNode(final PEdge startEdge, final PFace currentFace,
            final OrthogonalRepresentation ortho) {

        // Go in the target direction. Use target as starting point,
        // if the run doesn't work use instead target source point.

        // at the beginning, we use the target-node of the start-edge as resulting node.
        PNode currentNode = startEdge.getTarget();

        Pair<Integer, PEdge> anglePair = null;

        PEdge currentEdge = startEdge;

        int direction = 0;

        // search until next corner is found.
        do {
            anglePair = determineAngleDirection(ortho.getAngles(currentNode), currentFace,
                    currentEdge);
            direction = anglePair.getFirst();
            currentEdge = anglePair.getSecond();

            if (direction == OrthogonalAngle.RIGHT.ordinal()) {
                // if next edge is right of the edge use the other startEdge node.
                return startEdge.getSource();
            } else if (direction == OrthogonalAngle.LEFT.ordinal()) {
                // otherwise use source-edge
                return startEdge.getTarget();
            }
            currentNode = currentEdge.getSource() == currentNode ? currentEdge.getTarget()
                    : currentEdge.getSource();
        } while (direction == OrthogonalAngle.STRAIGHT.ordinal());
        return null;
    }

    private static Pair<Integer, PEdge> determineAngleDirection(
            final List<Pair<PEdge, OrthogonalAngle>> angles, final PFace currentFace,
            final PEdge startEdge) {

        int previousIndex = 0;
        int currentIndex = 0;

        // get edge index.
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                currentIndex = i;
                break;
            }
        }

        int directionCounter = 0;
        // if a edge of an other face has detected, we have to sum over all angles until
        // a face-edge is reached.
        boolean containsForeignEdge = false;
        Pair<PEdge, OrthogonalAngle> pair = null;
        // determine the directions of the next corner face-edge
        do {
            previousIndex = currentIndex;
            currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
            pair = angles.get(currentIndex);

            if (!containsForeignEdge && currentFace.isAdjacent(pair.getFirst())) {
                // hasFound
                directionCounter = angles.get(previousIndex).getSecond().ordinal();
                break;
            } else {
                containsForeignEdge = true;
                // look at the direction of the previous edge to determine the direction
                directionCounter += angles.get(previousIndex).getSecond().ordinal() + 1;

                if (currentFace.isAdjacent(pair.getFirst())) {
                    // hasFound
                    break;
                }
            }
        } while (true);

        Pair<Integer, PEdge> result = new Pair<Integer, PEdge>();
        result.setFirst(containsForeignEdge ? directionCounter - 1 : directionCounter);
        result.setSecond(pair.getFirst());
        return result;
    }

}
