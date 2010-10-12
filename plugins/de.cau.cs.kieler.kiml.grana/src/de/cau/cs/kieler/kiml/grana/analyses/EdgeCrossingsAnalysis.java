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
import java.util.ListIterator;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;

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
     *            end point of the first line segment
     * @param q1
     *            start point of the second line segment
     * @param q2
     *            end point of the second line segment
     * @return true if the lines have an intersection
     */
    private static boolean hasIntersection(final KPoint p1, final KPoint p2,
            final KPoint q1, final KPoint q2) {
        float s = (q2.getY() - q1.getY()) * (p2.getX() - p1.getX())
                - (q2.getX() - q1.getX()) * (p2.getY() - p1.getY());
        // are the line segments parallel?
        if (s == 0) {
            return false;
        }
        float a1 = (q2.getX() - q1.getX()) * (p1.getY() - q1.getY())
                - (q2.getY() - q1.getY()) * (p1.getX() - q1.getX());
        float a2 = (p2.getX() - p1.getX()) * (p1.getY() - q1.getY())
                - (p2.getY() - p1.getY()) * (p1.getX() - q1.getX());
        float t1 = a1 / s;
        float t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
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
    private int computeNumberOfCrossings(final KEdge edge1, final KEdge edge2) {
        int numberOfCrossings = 0;
        KEdgeLayout edge1Layout = edge1.getData(KEdgeLayout.class);
        KEdgeLayout edge2Layout = edge2.getData(KEdgeLayout.class);
        KPoint p1 = edge1Layout.getSourcePoint();
        for (KPoint p2 : edge1Layout.getBendPoints()) {
            KPoint q1 = edge2Layout.getSourcePoint();
            for (KPoint q2 : edge2Layout.getBendPoints()) {
                numberOfCrossings += hasIntersection(p1, p2, q1, q2) ? 1 : 0;
                q1 = q2;
            }
            // target point has to be handled separately
            KPoint q2 = edge2Layout.getTargetPoint();

            numberOfCrossings += hasIntersection(p1, p2, q1, q2) ? 1 : 0;

            p1 = p2;
        }
        // target point has to be handled separately
        KPoint p2 = edge1Layout.getTargetPoint();

        KPoint q1 = edge2Layout.getSourcePoint();
        for (KPoint q2 : edge2Layout.getBendPoints()) {
            numberOfCrossings += hasIntersection(p1, p2, q1, q2) ? 1 : 0;
            q1 = q2;
        }
        // target point has to be handled separately
        KPoint q2 = edge2Layout.getTargetPoint();
        numberOfCrossings += hasIntersection(p1, p2, q1, q2) ? 1 : 0;
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
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        List<KEdge> edges = new LinkedList<KEdge>();
        nodeQueue.offer(parentNode);
        while (!nodeQueue.isEmpty()) {
            // poll the first element
            KNode node = nodeQueue.poll();
            // collect the outgoing edges
            edges.addAll(node.getOutgoingEdges());
            // enqueue the child nodes
            nodeQueue.addAll(node.getChildren());
        }
        
        // count the number of crossings between all edges of the compound graph
        ListIterator<KEdge> iter1 = edges.listIterator();
        while (iter1.hasNext()) {
            KEdge edge1 = iter1.next();
            ListIterator<KEdge> iter2 = edges.listIterator(iter1.nextIndex());
            while (iter2.hasNext()) {
                KEdge edge2 = iter2.next();
                numberOfCrossings += computeNumberOfCrossings(edge1, edge2);
            }
        }

        progressMonitor.done();
        return numberOfCrossings;
    }
}
