/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.ElkRectangle;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edge-node overlaps. It assumes that the edge bend
 * points describe polylines (splines are not supported).<br/>
 * The algorithm used is based on the Cohen-Sutherland algorithm.
 */
public class NodeEdgeOverlapsAnalysis implements IAnalysis {

    /**
     * Identifier of the node-edge overlaps analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.nodeEdgeOverlaps";

    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        progressMonitor.begin("Node Crossings analysis", 1);

        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);

        int overlaps = 0;
        LinkedList<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.add(parentNode);
        while (nodeQueue.size() > 0) {
            // get first element
            ElkNode node = nodeQueue.pop();
            // compute intersections of all edge segments with all nodes on the same hierarchy
            for (ElkNode child : node.getChildren()) {
                overlaps += computeNumberOfOverlaps(child, node.getContainedEdges());
            }

            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }

        progressMonitor.done();
        return overlaps;
    }

    /** outcode constants. */
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    /**
     * Compute the outcode for the point and rectangle that is the node's bounding box. The outcode
     * belongs to the Cohen-Sutherland algorithm and is shown in the following illustration:<br>
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
    private static int computeOutCode(final KVector point, final ElkNode node) {
        int code = 0;
        if (point.y > node.getY() + node.getHeight()) {
            code |= BOTTOM;
        } else if (point.y < node.getY()) {
            code |= TOP;
        }

        if (point.x > node.getX() + node.getWidth()) {
            code |= RIGHT;
        } else if (point.x < node.getX()) {
            code |= LEFT;
        }
        return code;
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
     * @param belongsToNode
     *            whether the line segment is connected to the given node
     * @return true if the line segment intersects the nodes bounding box
     */
    private static boolean hasIntersection(final KVector p1, final KVector p2, final ElkNode node,
            final boolean belongsToNode) {

        int p1OutCode = computeOutCode(p1, node);
        int p2OutCode = computeOutCode(p2, node);

        if (p1OutCode == 0 || p2OutCode == 0) {
            // the line segment has start- or endpoint inside the rectangle
            return !belongsToNode;

        } else if ((p1OutCode & p2OutCode) > 0) {
            // the line segment lies on one side of the rectangle
            return false;

        } else {
            ElkRectangle nodeRect = new ElkRectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
            return ElkMath.intersects(nodeRect, p1, p2);
        }
    }

    /**
     * Computes the number of overlaps between the given edges and the node.
     * 
     * @param node
     *            the node
     * @param edges
     *            the set of edges
     * @return the number of overlaps
     */
    private int computeNumberOfOverlaps(final ElkNode node, final List<ElkEdge> edges) {
        int overlaps = 0;
        for (ElkEdge edge : edges) {
            sectionLoop: for (ElkEdgeSection section : edge.getSections()) {
                // Find out whether this section connects to our node
                boolean connectsToNode = false;
                if (section.getIncomingShape() != null) {
                    connectsToNode |= ElkGraphUtil.connectableShapeToNode(section.getIncomingShape()) == node;
                }

                if (section.getOutgoingShape() != null) {
                    connectsToNode |= ElkGraphUtil.connectableShapeToNode(section.getOutgoingShape()) == node;
                }

                // Walk the vector chain
                KVectorChain chain = ElkUtil.createVectorChain(section);
                Iterator<KVector> iter = chain.iterator();
                KVector last = iter.next();

                while (iter.hasNext()) {
                    KVector next = iter.next();

                    if (hasIntersection(last, next, node, connectsToNode)) {
                        ++overlaps;
                        continue sectionLoop;
                    }

                    last = next;
                }
            }
        }
        return overlaps;
    }
}
