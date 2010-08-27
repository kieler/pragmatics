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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A graph analysis that computes the number of edge crossings. It assumes that
 * the edge bend points describe polylines (splines are not supported).
 * 
 * @author mri
 */
public class EdgeCrossingsAnalysis implements IAnalysis {

    /**
     * Returns whether two line segments have an intersection.
     * 
     * @param p1
     *            start point of the first line segment
     * @param p2
     *            end point of the first line segement
     * @param q1
     *            start point of the second line segment
     * @param q2
     *            end point of the second line segement
     * @return true if the lines have an intersection
     */
    private static boolean hasIntersection(final KPoint p1, final KPoint p2,
            final KPoint q1, final KPoint q2) {
        float s =
                (q2.getY() - q1.getY()) * (p2.getX() - p1.getX())
                        - (q2.getX() - q1.getX()) * (p2.getY() - p1.getY());
        // are the line segements parallel?
        if (s == 0) {
            return false;
        }
        float a1 =
                (q2.getX() - q1.getX()) * (p1.getY() - q1.getY())
                        - (q2.getY() - q1.getY()) * (p1.getX() - q1.getX());
        float a2 =
                (p2.getX() - p1.getX()) * (p1.getY() - q1.getY())
                        - (p2.getY() - p1.getY()) * (p1.getX() - q1.getX());
        float t1 = a1 / s;
        float t2 = a2 / s;
        // the line segements intersect when t1 and t2 lie in the interval (0,1)
        return 0.0f < t1 && t1 < 1 && 0 < t2 && t2 < 1;
    }

    /**
     * Computes the number of crossings between edges from the first and edges
     * from the second node.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return the number of crossings
     */
    private int computeNumberOfCrossings(final KNode node1, final KNode node2) {
        int numberOfCrossings = 0;
        for (KEdge edge1 : node1.getOutgoingEdges()) {
            for (KEdge edge2 : node2.getOutgoingEdges()) {
                if (edge1 != edge2) {
                    KEdgeLayout edge1Layout =
                            KimlUtil.getEdgeLayout(edge1);
                    KEdgeLayout edge2Layout =
                            KimlUtil.getEdgeLayout(edge2);
                    KPoint p1 = edge1Layout.getSourcePoint();
                    for (KPoint p2 : edge1Layout.getBendPoints()) {
                        KPoint q1 = edge2Layout.getSourcePoint();
                        for (KPoint q2 : edge2Layout.getBendPoints()) {
                            numberOfCrossings +=
                                    hasIntersection(p1, p2, q1, q2) ? 1 : 0;
                            q1 = q2;
                        }
                        // target point has to be handled separately
                        KPoint q2 = edge2Layout.getTargetPoint();

                        numberOfCrossings +=
                                hasIntersection(p1, p2, q1, q2) ? 1 : 0;

                        p1 = p2;
                    }
                    // target point has to be handled separately
                    KPoint p2 = edge1Layout.getTargetPoint();

                    KPoint q1 = edge2Layout.getSourcePoint();
                    for (KPoint q2 : edge2Layout.getBendPoints()) {
                        numberOfCrossings +=
                                hasIntersection(p1, p2, q1, q2) ? 1 : 0;
                        q1 = q2;
                    }
                    // target point has to be handled separately
                    KPoint q2 = edge2Layout.getTargetPoint();
                    numberOfCrossings +=
                            hasIntersection(p1, p2, q1, q2) ? 1 : 0;
                }
            }
        }
        return numberOfCrossings;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        progressMonitor.begin("Edge Crossings analysis", 1);
        int numberOfCrossings = 0;
        List<KNode> nodes = new LinkedList<KNode>();
        nodes.add(parentNode);
        while (nodes.size() > 0) {
            // pop first element
            KNode node = nodes.remove(0);
            // compute intersections of all edge segements with all other edge
            // segments on
            // the same hierarchy
            Queue<KNode> children = new LinkedList<KNode>(node.getChildren());
            while (!children.isEmpty()) {
                KNode node1 = children.remove();
                for (KNode node2 : children) {
                    // count crossings between edges of both nodes
                    numberOfCrossings += computeNumberOfCrossings(node1, node2);
                }
            }
            nodes.addAll(node.getChildren());
        }

        progressMonitor.done();
        return numberOfCrossings;
    }
}
