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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Calculates for each face of the graph structure the sides. Meaning, all edges around a face
 * 
 * @author pkl
 */
public class FaceSidesProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    /** The number of faces. */
    private static final int FACE_SIDES_NUMBER = 4;

    /**
     * {@inheritDoc} This method iterates over all faces of the graph and stores them in the left,
     * top, right and bottom side. This can be used, to determine how long an edge has to be. Meaning
     * the opposite edges have to have the same length. This works because of the rectangular shape
     * of the input faces. Attention: This works only for graphs with rectangular face shapes.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void process(final PGraph graph) {

        List<PFace> visitedFaces = Lists.newArrayList();

        List<PFace> completedFaces = Lists.newArrayList();

        // helper structure to get the corner - the stored edges with its found corners.
        Map<PEdge, PNode> edgesAndCorners = Maps.newHashMap();

        PFace externalFace = graph.getExternalFace();
        PFace currentFace = externalFace;

        int sideIndex = 0;

        Pair<PNode, PEdge> startWithCorner = currentFace.getProperty(Properties.FACE_DIRECTION);

        PEdge startEdge = startWithCorner.getSecond();
        PEdge currentEdge = startEdge;

        PNode startNode = startEdge.getSource();
        PNode corner = startNode;

        while (currentFace != null) {

            // 0 for left, 1 for top, 2 for right, 3 for bottom.
            List[] faceSides = new ArrayList[FACE_SIDES_NUMBER];

            for (int i = 0; i < faceSides.length; i++) {
                faceSides[i] = new ArrayList<PEdge>();
            }

            // starts with the startEdge and runs around the chosen face until the startEdge has
            // found. Then all edges of the face are a part of a face-side.
            do {
                // first get the current edge to determine the direction of the next edge,
                // if the next edge a face edge handle edge convenient,
                Pair<PEdge, OrthogonalAngle> pair = currentFace.nextCWEdgeWithAngle(corner,
                        currentEdge);
                currentEdge = pair.getFirst();

                corner = currentEdge.getOppositeNode(corner);
                edgesAndCorners.put(currentEdge, corner);

                // determine side index. only left or straight angles are allowed because of the
                // rectangular shape of the faces. A left angle changes the side.
                if (pair.getSecond().ordinal() == 0) {
                    sideIndex = (sideIndex + 1) % FACE_SIDES_NUMBER;
                }
                
                if(pair.getSecond().ordinal() == 2){
                    //error TODO make assertion with useful description!
                    int i = 0;
                }
                
                faceSides[sideIndex].add(currentEdge);
            } while (currentEdge != startEdge || corner != startNode);

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
                                // same side and same corner
                                corner = edgesAndCorners.get(currentEdge);
                                sideIndex = i;
                            } else {
                                corner = currentEdge.getOppositeNode(edgesAndCorners
                                        .get(currentEdge));
                                // opposite side and corner
                                sideIndex = (i + 2) % FACE_SIDES_NUMBER;
                            }
                            startNode = corner;
                            break out;
                        }
                    }
                }
                completedFaces.add(visitedFace);
            }
        }
    }
    
    //TODO introduce a check, if all faces contains at least one edge onto every face side.

}
