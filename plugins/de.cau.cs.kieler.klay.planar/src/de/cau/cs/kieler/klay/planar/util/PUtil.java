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

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.p3compact.RectShapeEdgeProperties;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Util class for the orthogonalization layouter klay.planar, which contains general functions that
 * are used in several parts of the algorithm.
 * 
 * @author pkl
 */
public class PUtil {

    /**
     * Adds a {@link KVectorChain} of bendpoints to the edge bendpoints. Additionally orders them to
     * their correct positions.
     * 
     * @param edge
     *            the edge to that the bendpoints shell added.
     * @param newBendPoints
     *            the new bendpoints to add.
     * @param exceptionNode
     *            during the planarization, there is the dummy node on the road, this has to be
     *            ignored.
     * @param grid
     *            the grid, that contains the edge and the exception node.
     */
    public static void addBendsToEdge(final PEdge edge, final KVectorChain newBendPoints,
            final PNode exceptionNode, final GridRepresentation grid) {
        edge.getBendPoints().addAll(newBendPoints);

        if (edge.getBendPoints().size() > 1) {

            int[] sourceCoordinates = grid.search(edge.getSource());

            double startX = (double) sourceCoordinates[0];
            double startY = (double) sourceCoordinates[1];

            KVectorChain myBendPoints = new KVectorChain(edge.getBendPoints());
            edge.removeBendPoints();

            double value = 0.0;
            double end = 0.0;

            KVector foundVec = null;
            boolean found = false;
            while (!myBendPoints.isEmpty()) {
                // Search the next bendpoint
                out: for (KVector vec : myBendPoints) {
                    found = false;
                    if (vec.x == startX) {
                        found = true;
                        if (vec.y < startY) {
                            value = vec.y;
                            end = startY;
                        } else {
                            value = startY;
                            end = vec.y;
                        }

                        value++;
                        while (value < end) {
                            PNode pNode = grid.get((int) startX, (int) value);
                            if (pNode != null && pNode != exceptionNode) {
                                // there is another node.
                                continue out;
                            }
                            value++;
                        }

                    } else if (vec.y == startY) {
                        found = true;
                        if (vec.x < startX) {
                            value = vec.x;
                            end = startX;
                        } else {
                            value = startX;
                            end = vec.x;
                        }

                        value++;
                        while (value < end) {
                            PNode pNode = grid.get((int) value, (int) startY);
                            if (pNode != null && (exceptionNode == null || pNode != exceptionNode)) {
                                // there is another node.
                                continue out;
                            }
                            value++;
                        }

                    }
                    if (found) {
                        foundVec = vec;
                        break;
                    }
                }
                if (!found) {
                    // TODO Assertion found has to be true here. Otherwise the graphmodel is
                    // inconsistent.
                }
                // If not continued add vec to bend data.
                startX = foundVec.x;
                startY = foundVec.y;
                edge.getBendPoints().add(foundVec);
                myBendPoints.remove(foundVec);
            }
        }
    }

    /**
     * Filters the {@link RectShapeEdgeProperties} of an edges. A cutedge is passed twice, so that
     * there are two property container. The correct one is determined by the given corner, if no
     * corner is given, the first property container is taken. If there are no properties, new
     * properties are added to the edge.
     * 
     * @param edge
     *            , for which we need to filter the properties container.
     * @param corner
     *            , <code>null</code> allowed, if it the edge is a cutedge, used to filter the
     *            correct properties container.
     * @return the {@link RectShapeEdgeProperties} property object of the given edge.
     */
    public static RectShapeEdgeProperties getProperties(final PEdge edge, final PNode corner) {
        RectShapeEdgeProperties edgeProperties = edge.getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (edgeProperties == null) {
            Pair<RectShapeEdgeProperties, RectShapeEdgeProperties> cutEdgeProps = edge
                    .getProperty(Properties.RECT_SHAPE_CUTEDGE);
            if (cutEdgeProps != null) {
                if (cutEdgeProps.getSecond() == null) {
                    // Create new second propert
                    edgeProperties = new RectShapeEdgeProperties();
                    cutEdgeProps.setSecond(edgeProperties);
                    edge.setProperty(Properties.RECT_SHAPE_CUTEDGE, cutEdgeProps);
                    return edgeProperties;
                } else {
                    if (corner == null) {
                        return cutEdgeProps.getFirst();
                    }
                    if (cutEdgeProps.getFirst().getCorner() == corner) {
                        return cutEdgeProps.getSecond();
                    } else {
                        return cutEdgeProps.getFirst();
                    }
                }
            }

            // Else Create new properties.
            edgeProperties = new RectShapeEdgeProperties();

            if (edge.getLeftFace() == edge.getRightFace()) {
                cutEdgeProps = new Pair<RectShapeEdgeProperties, RectShapeEdgeProperties>(
                        edgeProperties, null);
                cutEdgeProps.setFirst(edgeProperties);
                edge.setProperty(Properties.RECT_SHAPE_CUTEDGE, cutEdgeProps);
            } else {
                edge.setProperty(Properties.RECT_SHAPE_PROPERTIES, edgeProperties);
            }

        }

        return edgeProperties;
    }

}
