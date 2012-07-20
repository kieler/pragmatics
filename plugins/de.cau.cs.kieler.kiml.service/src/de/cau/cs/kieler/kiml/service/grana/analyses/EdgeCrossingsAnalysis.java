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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A graph analysis that computes the number of edge crossings. It assumes that
 * the edge bend points describe polylines. Returns a four-component result
 * {@code (int min, float avg, int max, int sum)}.
 * 
 * @author mri
 * @author cds
 * @author msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeCrossingsAnalysis implements IAnalysis {
    
    /**
     * Identifier of the edge crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.edgeCrossings";

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
    private static boolean hasIntersection(final KVector p1, final KVector p2,
            final KVector q1, final KVector q2) {
        double s = (q2.y - q1.y) * (p2.x - p1.x) - (q2.x - q1.x) * (p2.y - p1.y);
        // are the line segments parallel?
        if (s == 0) {
            return false;
        }
        double a1 = (q2.x - q1.x) * (p1.y - q1.y) - (q2.y - q1.y) * (p1.x - q1.x);
        double a2 = (p2.x - p1.x) * (p1.y - q1.y) - (p2.y - p1.y) * (p1.x - q1.x);
        double t1 = a1 / s;
        double t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
        return 0 < t1 && t1 < 1 && 0 < t2 && t2 < 1;
    }

    /**
     * Computes the number of crossings between two vector chains.
     * 
     * @param chain1 the first vector chain
     * @param chain2 the second vector chain
     * @return the number of crossings
     */
    private static int computeNumberOfCrossings(final KVectorChain chain1, final KVectorChain chain2) {
        int numberOfCrossings = 0;
        Iterator<KVector> points1 = chain1.iterator();
        KVector p1 = points1.next();
        while (points1.hasNext()) {
            KVector p2 = points1.next();
            numberOfCrossings += computeNumberOfCrossings(p1, p2, chain2);
            p1 = p2;
        }
        return numberOfCrossings;
    }
    
    /**
     * Computes the number of crossings of a line and a vector chain.
     * 
     * @param p1 start point of the line
     * @param p2 end point of the line
     * @param chain2 a vector chain
     * @return the number of crossings
     */
    private static int computeNumberOfCrossings(final KVector p1, final KVector p2,
            final KVectorChain chain2) {
        int numberOfCrossings = 0;
        Iterator<KVector> points2 = chain2.iterator();
        KVector q1 = points2.next();
        while (points2.hasNext()) {
            KVector q2 = points2.next();
            numberOfCrossings += hasIntersection(p1, p2, q1, q2) ? 1 : 0;
            q1 = q2;
        }
        return numberOfCrossings;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge Crossings analysis", 1);
        
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        // collect all edges and translate their coordinates to absolute
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        List<KEdge> edges = new ArrayList<KEdge>();
        List<KVectorChain> chains = new ArrayList<KVectorChain>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            // poll the first element
            KNode node = nodeQueue.poll();
            
            // collect the outgoing edges
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                    continue;
                }
                KVectorChain chain = edge.getData(KEdgeLayout.class).createVectorChain();
                
                // translate the bend point coordinates to absolute
                KNode parent = node;
                if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
                    parent = node.getParent();
                }
                KVector referencePoint = new KVector();
                KimlUtil.toAbsolute(referencePoint, parent);
                chain.translate(referencePoint);
                
                // transform spline control points to approximated bend points
                if (edge.getData(KEdgeLayout.class).getProperty(LayoutOptions.EDGE_ROUTING)
                        == EdgeRouting.SPLINES) {
                    chain = KielerMath.appoximateSpline(chain);
                }
                
                edges.add(edge);
                chains.add(chain);
            }
            
            // enqueue the child nodes
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        // count the number of crossings between all edges of the compound graph
        int edgeCount = edges.size();
        int[] crossings = new int[edgeCount];
        for (int i = 0; i < edgeCount; i++) {
            KEdge edge1 = edges.get(i);
            KVectorChain chain1 = chains.get(i);
            KNode source1 = edge1.getSource();
            KNode target1 = edge1.getTarget();
            KPort sourcePort1 = edge1.getSourcePort();
            KPort targetPort1 = edge1.getTargetPort();
            KShapeLayout sourceLayout1 = source1.getData(KShapeLayout.class);
            KShapeLayout targetLayout1 = target1.getData(KShapeLayout.class);
            for (int j = i + 1; j < edgeCount; j++) {
                KEdge edge2 = edges.get(j);
                KNode source2 = edge2.getSource();
                KNode target2 = edge2.getTarget();
                KPort sourcePort2 = edge2.getSourcePort();
                KPort targetPort2 = edge2.getTargetPort();
                
                boolean samePort = false;
                samePort |= sourcePort1 != null
                        && (sourcePort1 == sourcePort2 || sourcePort1 == targetPort2);
                samePort |= targetPort1 != null
                        && (targetPort1 == targetPort2 || targetPort1 == sourcePort2);
                samePort |= sourceLayout1.getProperty(LayoutOptions.HYPERNODE)
                        && (source1 == source2 || source1 == target2);
                samePort |= targetLayout1.getProperty(LayoutOptions.HYPERNODE)
                        && (target1 == target2 || target1 == source2);
                if (!samePort) {
                    KVectorChain chain2 = chains.get(j);
                    int c = computeNumberOfCrossings(chain1, chain2);
                    crossings[i] += c;
                    crossings[j] += c;
                }
            }
        }

        // determine minimum, maximum, sum, and average value
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;
        float avg = 0.0f;
        for (int i = 0; i < edgeCount; i++) {
            sum += crossings[i];
            min = Math.min(min, crossings[i]);
            max = Math.max(max, crossings[i]);
        }
        
        if (edgeCount > 0) {
            avg = (float) sum / edgeCount;
        } else {
            min = 0;
        }
        sum /= 2;

        progressMonitor.done();
        return new Object[] {min, avg, max, sum};
    }
}
