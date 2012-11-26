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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.intermediate.RectShapeEdgeProperties;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Util class for the orthogonalization layouter klay.planar, which contains general functions that
 * are used in several parts of the algorithm.
 * 
 * @author pkl
 */
public final class PUtil {

    private PUtil() {
        // Should not be called.
    }

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

            int[] sourceCoordinates = grid.getPosition(edge.getSource());

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
     * @param preCurrConnector
     *            , <code>null</code> allowed, if it the edge is a cutedge, used to filter the
     *            correct properties container. This node is the one between the previous edge and
     *            the current given edge. Note: This is not the corner in cw or ccw direction.
     * @return the {@link RectShapeEdgeProperties} property object of the given edge.
     */
    public static RectShapeEdgeProperties getProperties(final PEdge edge,
            final PNode preCurrConnector) {
        RectShapeEdgeProperties edgeProperties = edge.getProperty(Properties.RECT_SHAPE_PROPERTIES);
        if (edgeProperties != null) {
            return edgeProperties;
        }

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
                if (preCurrConnector == null) {
                    return cutEdgeProps.getFirst();
                }
                if (cutEdgeProps.getFirst().getCorner() == preCurrConnector) {
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

        return edgeProperties;
    }

    // ======================== Debug Files ==========================================
    /**
     * Returns the path for debug output graphs.
     * 
     * @return the path for debug output graphs, without trailing separator.
     */
    public static String getDebugOutputPath() {
        String path = System.getProperty("user.home");
        if (path.endsWith(File.separator)) {
            path += "tmp" + File.separator + "klay";
        } else {
            path += File.separator + "tmp" + File.separator + "klay";
        }

        return path;
    }

    /**
     * Returns the beginning of the file name used for debug output graphs while layouting the given
     * layered graph. This will look something like {@code "143293-"}.
     * 
     * @param graph
     *            the graph to return the base debug file name for.
     * @return the base debug file name for the given graph.
     */
    public static String getDebugOutputFileBaseName(final PGraph graph) {
        return Integer.toString(graph.hashCode() & ((1 << (Integer.SIZE / 2)) - 1)) + "-";
    }

    /**
     * Removes the old temp directory.
     */
    public static void clearTmpDir() {
        String path = PUtil.getDebugOutputPath();
        for (File innerFile : new File(path).listFiles()) {
            try {
                innerFile.delete();
            } catch (SecurityException e) {
                // nothing to do.
            }
        }

    }

    /**
     * Creates a writer for the given graph. The file name to be written to is assembled from the
     * graph's hash code and the slot index. Writes the graph in a dot output file.
     * 
     * @param graph
     *            the stored graph
     * 
     * @param slotIndex
     *            the slot before whose execution the graph is written
     * 
     * @param directed
     *            if the output graph should be stored as directed graph
     * 
     */
    public static void storeGraph(final PGraph graph, final int slotIndex, final boolean directed) {
        try {
            String path = PUtil.getDebugOutputPath();
            new File(path).mkdirs();

            String debugFileName = PUtil.getDebugOutputFileBaseName(graph) + "fulldebug-slot"
                    + String.format("%1$02d", slotIndex);

            graph.writeDotGraph(new FileWriter(new File(path + File.separator + debugFileName
                    + ".dot")), directed);
        } catch (IOException e) {
            // nothing to do
        } catch (SecurityException se) {
            // nothing to do
        }
    }

    // ======================== General Algorithms==========================================

    /**
     * computes a breadth-first-search. Starting with the source node, moving along the edges and
     * put every found node to the result.
     * 
     * @param graph
     *            the graph on which the bfs should be done
     * @param startNode
     *            the root node of the bfs
     * @return a sorted listed from source to the furthermost apart node.
     */
    public static List<PNode> bfsNodes(final PGraph graph, final PNode startNode) {
        List<PNode> result = new LinkedList<PNode>();

        // need to save the already visited nodes.
        List<PNode> visited = new LinkedList<PNode>();

        Queue<PNode> queue = new LinkedList<PNode>();
        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            PNode node = queue.remove();
            result.add(node);
            for (PNode child : node.adjacentNodes()) {
                if (!visited.contains(child)) {
                    queue.add(child);
                    visited.add(child);
                }
            }

        }
        return result;
    }

    /**
     * Counts the shared edges of two faces.
     * 
     * @param firstFace
     *            the one face
     * @param secondFace
     *            the other face
     * @return the number of shared edges.
     */
    public static int countSharedEdges(final PFace firstFace, final PFace secondFace) {
        int count = 0;
        for (PEdge edge : firstFace.adjacentEdges()) {
            if (secondFace.isAdjacent(edge)) {
                count++;
            }
        }
        return count;
    }

}
