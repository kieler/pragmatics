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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * A graph analysis that computes the number of edge-node crossings. It assumes
 * that the edge bend points describe polylines (splines are not supported).
 * 
 * @author mri
 */
public class NodeCrossingsAnalysis implements IAnalysis {

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
        return false;
    }

    /**
     * Computes the number of crossings between edges from the first and the
     * second node.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return the number of crossings
     */
    private int computeNumberOfCrossings(final KNode node1, final KNode node2) {
        int numberOfCrossings = 0;
        for (KEdge edge : node1.getOutgoingEdges()) {
            KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
            KPoint p1 = edgeLayout.getSourcePoint();
            for (KPoint p2 : edgeLayout.getBendPoints()) {
                numberOfCrossings += hasIntersection(p1, p2, node2) ? 1 : 0;
                p1 = p2;
            }
            KPoint p2 = edgeLayout.getTargetPoint();
            numberOfCrossings += hasIntersection(p1, p2, node2) ? 1 : 0;
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

        int numberOfCrossings = 0;
        List<KNode> nodes = new LinkedList<KNode>();
        nodes.add(parentNode);
        while (nodes.size() > 0) {
            // pop first element
            KNode node = nodes.remove(0);
            // compute intersections of all edge segements with all nodes on
            // the same hierarchy
            for (KNode node1 : node.getChildren()) {
                for (KNode node2 : node.getChildren()) {
                    // count crossings between edges of the first node and the
                    // second node
                    numberOfCrossings += computeNumberOfCrossings(node1, node2);
                }
            }
            nodes.addAll(node.getChildren());
        }

        progressMonitor.done();
        return numberOfCrossings;
    }
}
