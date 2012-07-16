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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * @author pkl
 */
public class GridDrawingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * 
     */
    private static final int BOTTOM_SIDE = 3;

    private GridRepresentation grid;

    private PGraph graph;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Draw grid", 1);
        this.graph = pgraph;
        PFace externalFace = this.graph.getExternalFace(false);
        List<PEdge>[] sides = externalFace.getProperty(Properties.FACE_SIDES);

        // Determine size of the grid.
        // It is enough to count vertical and horizontal direction once.

        int gridWidth = 0;
        for (PEdge edge : sides[1]) {
            gridWidth += edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        int gridHeight = 0;
        for (PEdge edge : sides[0]) {
            gridHeight += edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        // From index to real numbers
        this.grid = new GridRepresentation(gridWidth + 1, gridHeight + 1);

        fillGrid();

        getMonitor().done();
    }

    private void fillGrid() {

        PEdge currentEdge = null;
        PNode currentNode = null;

        boolean found = false;
        PFace externalFace = this.graph.getExternalFace(false);
        List<PEdge>[] sides = externalFace.getProperty(Properties.FACE_SIDES);
        // filter startNode, the node that lies on the left side and on the bottom side,
        // meaning the leftmost and lower most node!
        out: for (PEdge leftSideEdge : sides[0]) {
            for (PEdge bottomSideEdge : sides[BOTTOM_SIDE]) {
                found = leftSideEdge.getSource() == bottomSideEdge.getSource()
                        || leftSideEdge.getSource() == bottomSideEdge.getTarget();
                if (found) {
                    currentNode = leftSideEdge.getSource();
                    currentEdge = leftSideEdge;
                    break out;
                }
                found = leftSideEdge.getTarget() == bottomSideEdge.getSource()
                        || leftSideEdge.getTarget() == bottomSideEdge.getTarget();
                if (found) {
                    currentNode = leftSideEdge.getTarget();
                    currentEdge = leftSideEdge;
                    break out;
                }
            }
        }

        int gridX = 0;
        int gridY = 0;

        grid.set(gridX, gridY, currentNode);

        // start filling the grid
        // beginning with the external face sides.

        // Store the visited target-nodes / faces from the current face/node.
        // Is needed to check if a edge already exists to the target.
        List<PEdge> visitedEdges = Lists.newArrayList();
        // visitedEdges.add(currentEdge);

        PFace currentFace = externalFace;
        Map<PFace, Pair<PEdge, Integer>> knownFaces = Maps.newHashMap();
        knownFaces.put(currentFace, new Pair<PEdge, Integer>(currentEdge, 0));

        List<PFace> completedFaces = Lists.newArrayList();

        int sideIndex = 0;

        while (currentFace != null) {

            found = true;

            // work all sides
            List<PEdge>[] faceSides = currentFace.getProperty(Properties.FACE_SIDES);
            while (found) {

                found = false;

                // determine currentNode of the faceSide.
                currentNode = currentEdge.getOppositeNode(currentNode);
                Integer value = currentEdge.getProperty(Properties.RELATIVE_LENGTH);

                // determine the correct grid field. Remember, we walk clockwise around
                // the face.
                if (sideIndex == 0) {
                    gridY += value;
                } else if (sideIndex == 1) {
                    gridX += value;
                } else if (sideIndex == 2) {
                    gridY -= value;
                } else {
                    // sideIndex has to be 3
                    gridX -= value;
                }

                // add node of face to the grid.
                grid.set(gridX, gridY, currentNode);
                visitedEdges.add(currentEdge);
                // choose next edge
                out: for (int i = 0; i < faceSides.length; i++) {
                    // start at the current sideIndex and walk around until edge is found
                    for (PEdge edge : faceSides[(i + sideIndex) % faceSides.length]) {
                        if (edge != currentEdge && !visitedEdges.contains(edge)) {
                            if (edge.getTarget() == currentNode || edge.getSource() == currentNode) {
                                found = true;
                                if (changedSide(faceSides, currentEdge, edge)) {
                                    sideIndex = (i + sideIndex) % faceSides.length;
                                }
                                currentEdge = edge;

                                // added new known faces.
                                // check if knownFaces does not contain a face.

                                if (!knownFaces.containsKey(edge.getLeftFace())) {
                                    if (edge.getLeftFace() != externalFace
                                            && edge.getRightFace() != externalFace)
                                        knownFaces.put(edge.getLeftFace(),
                                                new Pair<PEdge, Integer>(edge, (sideIndex + 2)
                                                        % faceSides.length));
                                    else {
                                        knownFaces.put(edge.getLeftFace(),
                                                new Pair<PEdge, Integer>(edge, sideIndex));

                                    }
                                } else if (!knownFaces.containsKey(edge.getRightFace())) {
                                    if (edge.getLeftFace() != externalFace
                                            && edge.getRightFace() != externalFace)
                                        knownFaces.put(edge.getRightFace(),
                                                new Pair<PEdge, Integer>(edge, (sideIndex + 2)
                                                        % faceSides.length));
                                    else {
                                        knownFaces.put(edge.getRightFace(),
                                                new Pair<PEdge, Integer>(edge, (sideIndex)));
                                    }
                                }

                                break out;
                            }
                        }
                    }
                }
            } // end of while
            visitedEdges.clear();
            completedFaces.add(currentFace);

            currentFace = null;

            // choose the next known uncompleted face
            out: for (Entry<PFace, Pair<PEdge, Integer>> knownFace : knownFaces.entrySet()) {
                if (completedFaces.contains(knownFace.getKey())) {
                    continue;
                }

                currentFace = knownFace.getKey();
                Pair<PEdge, Integer> pair = knownFace.getValue();
                currentEdge = pair.getFirst();
                sideIndex = pair.getSecond();
                // FIXME use the side of the currentEdge, to make it more performant,
                // instead of iterating over all grid items.

                PNode checkNode = null;
                boolean tempFound = false;
                // calculate the grid startIndices and the currentNode with the currentEdge.

                for (int x = 0; x < this.grid.getWidth(); x++) {
                    for (int y = 0; y < this.grid.getHeight(); y++) {
                        if (grid.get(x, y) == currentEdge.getSource()) {
                            currentNode = currentEdge.getSource();
                            tempFound = true;
                        } else if (grid.get(x, y) == currentEdge.getTarget()) {
                            currentNode = currentEdge.getTarget();
                            tempFound = true;
                        }

                        if (tempFound) {
                            // If currentNode is part of an other edge with other node, that is in
                            // cw direction further than the chosen node, take the further one.

                            checkNode = currentEdge.getOppositeNode(currentNode);
                            Integer edgeLength = currentEdge
                                    .getProperty(Properties.RELATIVE_LENGTH);

                            // Depending on the face side search to get the node before, you
                            // have to subtract or add edge length. If the currentNode is already
                            // the
                            // in clockwise order before use the currentNode.
                            if (sideIndex == 0) {
                                int checkY = y + edgeLength;
                                if (this.grid.getHeight() >= checkY
                                        && (grid.get(x, checkY) == checkNode)) {
                                    gridX = x;
                                    gridY = y;
                                } else {
                                    // checknode has to be the node before this node in cw.
                                    currentNode = checkNode;
                                    gridX = x;
                                    gridY = y - edgeLength;
                                }

                            } else if (sideIndex == 1) {
                                int checkX = x + edgeLength;
                                if (this.grid.getWidth() >= checkX
                                        && this.grid.get(checkX, y) == checkNode) {
                                    gridX = x;
                                    gridY = y;
                                } else {
                                    currentNode = checkNode;
                                    gridX = x - edgeLength;
                                    gridY = y;
                                }
                            } else if (sideIndex == 2) {
                                int checkY = y - edgeLength;
                                if (0 <= checkY && (grid.get(x, checkY) == checkNode)) {
                                    gridX = x;
                                    gridY = y;
                                } else {
                                    currentNode = checkNode;
                                    gridX = x;
                                    gridY = y + edgeLength;
                                }
                            } else {
                                // sideIndex == 3
                                int checkX = x - edgeLength;
                                if (0 <= checkX && (grid.get(checkX, y) == checkNode)) {
                                    gridX = x;
                                    gridY = y;
                                } else {
                                    currentNode = checkNode;
                                    gridX = x + edgeLength;
                                    gridY = y;
                                }
                            }
                            break out;
                        }
                    }
                }
            }
        }
        graph.setProperty(Properties.GRID_REPRESENTATION, grid);
    }

    /**
     * Checks if two edges lay on different sides.
     * 
     * @param faceSides
     * @param previousEdge
     * @param currentEdge
     * @return true if they lay on different sides, otherwise false is returned
     */
    private boolean changedSide(final List<PEdge>[] faceSides, final PEdge previousEdge,
            final PEdge currentEdge) {
        for (List<PEdge> edgeList : faceSides) {
            if (edgeList.contains(previousEdge)) {
                if (edgeList.contains(currentEdge)) {
                    return false;
                }
                return true;
            }
        }
        throw new InconsistentGraphModelException(
                "The faces have to contain previous and current edge");
    }
}
