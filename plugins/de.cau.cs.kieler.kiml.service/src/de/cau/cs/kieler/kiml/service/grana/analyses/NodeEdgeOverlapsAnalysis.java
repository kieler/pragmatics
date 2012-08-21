/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.service.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edge-node overlaps. It assumes
 * that the edge bend points describe polylines (splines are not supported).<br>
 * The algorithm used is based on the Cohen-Sutherland algorithm.
 * 
 * @author mri
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class NodeEdgeOverlapsAnalysis implements IAnalysis {
    
    /**
     * Identifier of the node-edge overlaps analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.nodeEdgeOverlaps";

    /**
     * Returns whether two line segments have an intersection.
     * 
     * @param p1
     *            start point of the first line segment
     * @param p2
     *            end point of the first line segement
     * @param x1
     *            the x-coordinate of the start point of the second line segment
     * @param y1
     *            the y-coordinate of the start point of the second line segment
     * @param x2
     *            the x-coordinate of the end point of the second line segment
     * @param y2
     *            the y-coordinate of the end point of the second line segment
     * @return true if the line segments intersect else false
     */
    private static boolean hasIntersection(final KPoint p1, final KPoint p2,
            final float x1, final float y1, final float x2, final float y2) {
        float s =
                (y2 - y1) * (p2.getX() - p1.getX()) - (x2 - x1)
                        * (p2.getY() - p1.getY());
        // are the line segments parallel?
        if (s == 0) {
            return false;
        }
        float a1 = (x2 - x1) * (p1.getY() - y1) - (y2 - y1) * (p1.getX() - x1);
        float a2 =
                (p2.getX() - p1.getX()) * (p1.getY() - y1)
                        - (p2.getY() - p1.getY()) * (p1.getX() - x1);
        float t1 = a1 / s;
        float t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
        return 0.0f < t1 && t1 < 1 && 0 < t2 && t2 < 1;
    }

    /** outcode constants. */
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    /**
     * Compute the outcode for the point and rectangle that is the nodes
     * bounding box.<br>
     * The outcode belongs to the Cohen-Sutherland algorithm and is shown in the
     * following illustration:<br>
     * <br>
     * <code>
     * 1001|1000|1010<br>
     * ----|----|----<br>
     * 0001|0000|0010<br>
     * ----|----|----<br>
     * 0101|0100|0110<br>
     * </code> <br>
     * The box around the 0000 entry represents the rectangle.
     * 
     * @param point
     *            the point
     * @param node
     *            the node
     * @return the outcode
     */
    private static int computeOutCode(final KPoint point, final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        int code = 0;
        if (point.getY() > nodeLayout.getYpos() + nodeLayout.getHeight()) {
            code |= TOP;
        } else if (point.getY() < nodeLayout.getYpos()) {
            code |= BOTTOM;
        }
        if (point.getX() > nodeLayout.getXpos() + nodeLayout.getWidth()) {
            code |= RIGHT;
        } else if (point.getX() < nodeLayout.getXpos()) {
            code |= LEFT;
        }
        return code;
    }

    /**
     * Computes the opposite outcode.
     * 
     * @param outcode
     *            the outcode
     * @return the opposite outcode
     */
    private static int computeOppositeOutCode(final int outcode) {
        int oppOutcode = 0;
        if ((outcode & LEFT) > 0) {
            oppOutcode |= RIGHT;
        } else if ((outcode & RIGHT) > 0) {
            oppOutcode |= LEFT;
        }
        if ((outcode & TOP) > 0) {
            oppOutcode |= BOTTOM;
        } else if ((outcode & BOTTOM) > 0) {
            oppOutcode |= TOP;
        }
        return oppOutcode;
    }

    /**
     * Returns whether the line segment intersects the nodes bounding box.
     * 
     * @param p1
     *            the start point of the line segment
     * @param p2
     *            the end point of the line segment
     * @param node
     *            the node
     * @return true if the line segment intersects the nodes bounding box
     */
    private static boolean hasIntersection(final KPoint p1, final KPoint p2,
            final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        int p1OutCode = computeOutCode(p1, node);
        int p2OutCode = computeOutCode(p2, node);

        if (p1OutCode == 0 || p2OutCode == 0) {
            // the line segment has start- or endpoint inside the rectangle
            return true;
        } else if ((p1OutCode & p2OutCode) > 0) {
            // the line segment lies on one side of the rectangle
            return false;
        } else if (p1OutCode == computeOppositeOutCode(p2OutCode)) {
            // the start- and endpoint of the line segment lie on opposite sides
            return true;
        } else {
            int outcode = p1OutCode != TOP && p1OutCode > BOTTOM ? p2OutCode : p1OutCode;
            float xpos = nodeLayout.getXpos();
            float ypos = nodeLayout.getYpos();
            float width = nodeLayout.getWidth();
            float height = nodeLayout.getHeight();
            if ((outcode & LEFT) > 0) {
                return hasIntersection(p1, p2, xpos, ypos, xpos, ypos + height);
            } else if ((outcode & RIGHT) > 0) {
                return hasIntersection(p1, p2, xpos + width, ypos, xpos + width, ypos + height);
            } else if ((outcode & TOP) > 0) {
                return hasIntersection(p1, p2, xpos, ypos, xpos + width, ypos);
            } else /* if ((p1OutCode & BOTTOM) > 0) */ {
                return hasIntersection(p1, p2, xpos, ypos + height, xpos + width, ypos + height);
            }
        }
    }

    /**
     * Computes the number of overlaps between the second node and edges from the first node.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return the number of overlaps
     */
    private int computeNumberOfOverlaps(final KNode node1, final KNode node2) {
        if (node1 == node2) {
            return 0;
        }
        int overlaps = 0;
        edgeLoop: for (KEdge edge : node1.getOutgoingEdges()) {
            if (edge.getTarget() == node2) {
                continue;
            }
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint p1 = edgeLayout.getSourcePoint();
            for (KPoint p2 : edgeLayout.getBendPoints()) {
                if (hasIntersection(p1, p2, node2)) {
                    ++overlaps;
                    continue edgeLoop;
                }
                p1 = p2;
            }
            KPoint p2 = edgeLayout.getTargetPoint();
            if (hasIntersection(p1, p2, node2)) {
                ++overlaps;
            }
        }
        return overlaps;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Node Crossings analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        int overlaps = 0;
        List<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // get first element
            KNode node = nodeQueue.remove(0);
            // compute intersections of all edge segments with all nodes on the same hierarchy
            for (KNode node1 : node.getChildren()) {
                for (KNode node2 : node.getChildren()) {
                    // count overlaps between the second node and edges of the first node
                    overlaps += computeNumberOfOverlaps(node1, node2);
                }
            }
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }

        progressMonitor.done();
        return overlaps;
    }
}
