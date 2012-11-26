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
import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
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
public class FaceSidesProcessor implements ILayoutProcessor {

    /** The number of faces. */
    private static final int FACE_SIDES_NUMBER = 4;

    /**
     * {@inheritDoc} This method iterates over all faces of the graph and stores them in the left,
     * top, right and bottom side. This can be used, to determine how long an edge has to be.
     * Meaning the opposite edges have to have the same length. This works because of the
     * rectangular shape of the input faces. Attention: This works only for graphs with rectangular
     * face shapes.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void process(final PGraph graph, final IKielerProgressMonitor monitor) {
        monitor.begin("Face side processing", 1);
        
        Set<VisitEntry> visitedFaces = Sets.newHashSet();

        Set<PFace> completedFaces = Sets.newHashSet();

        PFace externalFace = graph.getExternalFace();
        PFace currentFace = externalFace;

        int sideIndex = 0;

        Pair<PNode, PEdge> startWithCorner = currentFace.getProperty(Properties.FACE_DIRECTION);

        PEdge currentEdge = startWithCorner.getSecond();

        PNode corner = currentEdge.getOppositeNode(startWithCorner.getFirst());

        visitedFaces.add(new VisitEntry(currentFace, currentEdge, corner, sideIndex));

        while (currentFace != null) {
            // 0 for left, 1 for top, 2 for right, 3 for bottom.
            List[] faceSides = new ArrayList[FACE_SIDES_NUMBER];

            for (int i = 0; i < faceSides.length; i++) {
                faceSides[i] = new ArrayList<PEdge>();
            }

            PEdge startEdge = currentEdge;
            PNode startNode = corner;

            do {
                Pair<PEdge, OrthogonalAngle> pair = currentFace.nextCWEdgeWithAngle(corner,
                        currentEdge);
                currentEdge = pair.getFirst();
                corner = currentEdge.getOppositeNode(corner);

                // determine side index. only left or straight angles are allowed because of the
                // rectangular shape of the faces. A left angle changes the side.
                if (pair.getSecond().ordinal() == OrthogonalAngle.LEFT.ordinal()) {
                    sideIndex = (sideIndex + 1) % FACE_SIDES_NUMBER;
                } else if (pair.getSecond().ordinal() == OrthogonalAngle.RIGHT.ordinal()) {
                    sideIndex = (sideIndex > 0) ? (sideIndex - 1) : FACE_SIDES_NUMBER - 1;
                } else if (pair.getSecond().ordinal() == OrthogonalAngle.FULL.ordinal()) {
                    sideIndex = (sideIndex + 2) % FACE_SIDES_NUMBER;
                }

                PFace lf = currentEdge.getLeftFace();
                PFace rf = currentEdge.getRightFace();

                if (lf != currentFace) {
                    boolean wantsAdd = true;
                    for (VisitEntry ve : visitedFaces) {
                        if (ve.face == lf) {
                            wantsAdd = false;
                            break;
                        }
                    }
                    if (wantsAdd) {
                        if (currentFace == externalFace) {
                            // add properties with same corner and same sideIndex.
                            visitedFaces.add(new VisitEntry(lf, currentEdge, corner, sideIndex));
                        } else {
                            // add properties with opposite edge corner and opposite side.
                            visitedFaces.add(new VisitEntry(lf, currentEdge, currentEdge
                                    .getOppositeNode(corner), (sideIndex + 2) % FACE_SIDES_NUMBER));
                        }
                    }
                } else {
                    // rf is not equal currentFace
                    boolean wantsAdd = true;
                    for (VisitEntry ve : visitedFaces) {
                        if (ve.face == rf) {
                            wantsAdd = false;
                            break;
                        }
                    }

                    if (wantsAdd) {
                        if (currentFace == externalFace) {
                            // add properties with same corner and same sideIndex.
                            visitedFaces.add(new VisitEntry(rf, currentEdge, corner, sideIndex));
                        } else {
                            // add properties with opposite edge corner and opposite side.
                            visitedFaces.add(new VisitEntry(rf, currentEdge, currentEdge
                                    .getOppositeNode(corner), (sideIndex + 2) % FACE_SIDES_NUMBER));
                        }
                    }
                }
                faceSides[sideIndex].add(currentEdge);
            } while (currentEdge != startEdge || corner != startNode);

            currentFace.setProperty(Properties.FACE_SIDES, faceSides);
            completedFaces.add(currentFace);

            // Next face.
            currentFace = null;
            for (VisitEntry vf : visitedFaces) {
                if (!completedFaces.contains(vf.face)) {
                    currentFace = vf.face;
                    currentEdge = vf.startEdge;
                    corner = vf.corner;
                    sideIndex = vf.sideIndex;
                    break;
                }
            }
        }
        
        monitor.done();
    }

    /**
     * Container to store properties of visited entries during the face walk through.
     * 
     * @author pkl
     */
    private class VisitEntry {

        /** Current face, for which the properties are stored. */
        private PFace face;

        /** Beginning edge of iterating over the face. */
        private PEdge startEdge;

        /** Corner in cw direction. */
        private PNode corner;

        /** The side on which the startEdge should lie. */
        private int sideIndex;

        /**
         * @param face
         *            the key face.
         * @param startEdge
         *            adjacent face edge.
         * @param corner
         *            incident to the startedge in cw order.
         * @param sideIndex
         *            the current face side index.
         */
        public VisitEntry(final PFace face, final PEdge startEdge, final PNode corner,
                final int sideIndex) {
            this.face = face;
            this.startEdge = startEdge;
            this.corner = corner;
            this.sideIndex = sideIndex;
        }

    }

}
