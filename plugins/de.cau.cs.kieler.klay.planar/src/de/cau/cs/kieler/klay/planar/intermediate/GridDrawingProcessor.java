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
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>a layered graph; nodes are placed; edges are routed.</dd>
 * <dt>Postcondition:</dt>
 * <dd>there are no dummy nodes of type {@link NodeType#DUMMY}.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 4.</dd>
 * </dl>
 * 
 * @author pkl
 */
public class GridDrawingProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * 
     */
    private static final int BOTTOM_SIDE = 3;

    // TODO think about a own class for that grid, including gridHeight and gridWidth.
    private PNode[][] grid;

    private PGraph graph;

    public static int gridHeight;

    public static int gridWidth;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Draw grid", 1);
        this.graph = pgraph;
        PFace externalFace = this.graph.getExternalFace(false);
        List<PEdge>[] sides = externalFace.getProperty(Properties.FACE_SIDES);

        // determine size of the grid.
        // it is enough to count one vertical and horizontal direction.

        gridWidth = 0;
        for (PEdge edge : sides[1]) {
            gridWidth += edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        gridHeight = 0;
        for (PEdge edge : sides[0]) {
            gridHeight += edge.getProperty(Properties.RELATIVE_LENGTH);
        }

        // from index to real numbers
        gridWidth++;
        gridHeight++;

        grid = new PNode[gridWidth][gridHeight];

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

        grid[gridX][gridY] = currentNode;

        // start filling the grid
        // beginning with the external face sides.

        // Store the visited target-nodes / faces from the current face/node.
        // Is needed to check if a edge already exists to the target.
        List<PEdge> visitedEdges = Lists.newArrayList();
        visitedEdges.add(currentEdge);

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
                currentNode = (currentEdge.getTarget() == currentNode) ? currentEdge.getSource()
                        : currentEdge.getTarget();
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
                grid[gridX][gridY] = currentNode;
                visitedEdges.add(currentEdge);
                // choose next edge
                out: for (int i = 0; i < faceSides.length; i++) {
                    // start at the current sideIndex and walk around until edge is found
                    for (PEdge edge : faceSides[(i + sideIndex) % faceSides.length]) {
                        if (edge != currentEdge && !visitedEdges.contains(edge)) {
                            if (edge.getTarget() == currentNode || edge.getSource() == currentNode) {
                                currentEdge = edge;
                                // added new knwon faces.
                                // check if knownFaces does not contain a face.

                                if (!knownFaces.containsKey(edge.getLeftFace())) {
                                    knownFaces.put(edge.getLeftFace(), new Pair<PEdge, Integer>(
                                            edge, sideIndex));
                                } else if (!knownFaces.containsKey(edge.getRightFace())) {
                                    knownFaces.put(edge.getRightFace(), new Pair<PEdge, Integer>(
                                            edge, sideIndex));
                                }

                                found = true;
                                // TODO I guess this is not meaningful, if at each
                                // found changes the side, that is not correct!
                                sideIndex = (i + sideIndex) % faceSides.length;
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

                for (int x = 0; x < GridDrawingProcessor.gridWidth; x++) {
                    for (int y = 0; y < GridDrawingProcessor.gridHeight; y++) {
                        // TODO is that really useful
                        if (grid[x][y] == currentEdge.getSource()) {
                            currentNode = currentEdge.getSource();
                            tempFound = true;
                        } else if (grid[x][y] == currentEdge.getTarget()) {
                            currentNode = currentEdge.getTarget();
                            tempFound = true;
                        }
                        if (tempFound) {
                            // if currentNode is part of an other edge with other node, that is in
                            // cw direction further than the chosen node, take the further one.
                            checkNode = currentEdge.getTarget() == currentNode ? currentEdge
                                    .getSource() : currentEdge.getTarget();
                            Integer edgeLength = currentEdge
                                    .getProperty(Properties.RELATIVE_LENGTH);
                            if (sideIndex == 0) {
                                // TODO use grid width and height
                                if (y >= edgeLength) {
                                    // HHHHHHHHHHHHHHHHHEEEEEEEEEERRRRRRRRRREE wrong side?
                                    if (grid[x][y - edgeLength] == checkNode) {
                                        gridX = x;
                                        gridY = y - edgeLength;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                } else {
                                    // sideIndex == 3
                                    if (grid[x + edgeLength][y] == checkNode) {
                                        gridX = x + edgeLength;
                                        gridY = y;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                }
                            } else if (sideIndex == 1) {
                                if (x >= edgeLength) {
                                    if (grid[x - edgeLength][y] == checkNode) {
                                        gridX = x - edgeLength;
                                        gridY = y;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                } else {
                                    // sideIndex == 0
                                    if (grid[x][y - edgeLength] == checkNode) {
                                        gridX = x;
                                        gridY = y - edgeLength;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                }
                            } else if (sideIndex == 2) {
                                if (y <= edgeLength) {
                                    if (grid[x][y + edgeLength] == checkNode) {
                                        gridX = x;
                                        gridY = y + edgeLength;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                } else {
                                    // sideIndex == 1
                                    if (grid[x - edgeLength][y] == checkNode) {
                                        gridX = x - edgeLength;
                                        gridY = y;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                }
                            } else if (sideIndex == 3) {
                                if (y <= edgeLength) {
                                    if (grid[x + edgeLength][y] == checkNode) {
                                        gridX = x + edgeLength;
                                        gridY = y;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                } else {
                                    // sideIndex == 2
                                    if (grid[x][y + edgeLength] == checkNode) {
                                        gridX = x;
                                        gridY = y + edgeLength;
                                        currentNode = checkNode;
                                    } else {
                                        gridX = x;
                                        gridY = y;
                                    }
                                }
                            } else {
                                gridX = x;
                                gridY = y;
                            }
                            break out;
                        }
                    }
                }
            }
        }
        graph.setProperty(Properties.GRID_DRAWING, grid);
    }
    // first implement the external face,
    // then go along all neighbor edge and place it in the grid.

    // now it should be possible to use the sides so as | e1 | e2 | e3 |
}
