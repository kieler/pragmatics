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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisOptions;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A graph analysis that computes the number of edge crossings. It assumes that
 * the edge bend points describe polylines. Returns a five-component result
 * {@code (int min, float avg, int max, int sum, float onefold)}. Where 
 * onefold is the ratio of edges that cross at least once and all edges.
 * 
 * @author mri
 * @author cds
 * @author msp
 * @author uru
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class EdgeCrossingsAnalysis implements IAnalysis {
    
    /**
     * Identifier of the edge crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.edgeCrossings";
    
    /** tolerance for double equality. */
    private static final double TOLERANCE = 1e-4;

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
        if (Math.abs(s) < TOLERANCE) {
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
    public Object doAnalysis(final ElkNode parentNode,
            final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge Crossings analysis", 1);
        
        boolean hierarchy = parentNode.getProperty(AnalysisOptions.ANALYZE_HIERARCHY);
        
        // collect all edges and translate their coordinates to absolute
        LinkedList<ElkNode> nodeQueue = new LinkedList<>();
        List<ElkEdge> edges = new ArrayList<>();
        List<KVectorChain> chains = new ArrayList<>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            // poll the first element
            ElkNode node = nodeQueue.poll();
            
            // collect the outgoing edges
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                if (!hierarchy && edge.isHierarchical()) {
                    continue;
                }
                
                ElkEdgeSection section = ElkGraphUtil.firstEdgeSection(edge, false, false);
                KVectorChain chain = ElkUtil.createVectorChain(section);
                
                // translate the bend point coordinates to absolute
                ElkNode referenceNode = edge.getContainingNode();
                KVector referencePoint = new KVector();
                ElkUtil.toAbsolute(referencePoint, referenceNode);
                chain.offset(referencePoint);
                
                // transform spline control points to approximated bend points
                if (edge.getProperty(CoreOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
                    chain = ElkMath.approximateBezierSpline(chain);
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
            ElkEdge edge1 = edges.get(i);
            KVectorChain chain1 = chains.get(i);
            ElkNode source1 = ElkGraphUtil.getSourceNode(edge1);
            ElkNode target1 = ElkGraphUtil.getTargetNode(edge1);
            ElkPort sourcePort1 = ElkGraphUtil.getSourcePort(edge1);
            ElkPort targetPort1 = ElkGraphUtil.getTargetPort(edge1);
            
            for (int j = i + 1; j < edgeCount; j++) {
                ElkEdge edge2 = edges.get(j);
                ElkNode source2 = ElkGraphUtil.getSourceNode(edge2);
                ElkNode target2 = ElkGraphUtil.getTargetNode(edge2);
                ElkPort sourcePort2 = ElkGraphUtil.getSourcePort(edge2);
                ElkPort targetPort2 = ElkGraphUtil.getTargetPort(edge2);
                
                boolean samePort = false;
                samePort |= sourcePort1 != null
                        && (sourcePort1.equals(sourcePort2) || sourcePort1.equals(targetPort2));
                samePort |= targetPort1 != null
                        && (targetPort1.equals(targetPort2) || targetPort1.equals(sourcePort2));
                samePort |= source1.getProperty(CoreOptions.HYPERNODE)
                        && (source1.equals(source2) || source1.equals(target2));
                samePort |= target1.getProperty(CoreOptions.HYPERNODE)
                        && (target1.equals(target2) || target1.equals(source2));
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
        
        // count edges that cross at least once
        int onefold = 0;
        for (int i = 0; i < edgeCount; i++) {
            if (crossings[i] > 0) {
                onefold++;
            }
        }
        // normalize
        float onefoldNorm = onefold / (float) edgeCount; 

        progressMonitor.done();
        return new Object[] {min, avg, max, sum, onefoldNorm};
    }
}
