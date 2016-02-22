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
package de.cau.cs.kieler.kiml.grana.analyses;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.LayoutOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A special edge crossings analysis for hyperedges. Works best with data flow diagrams.
 * 
 * <p>Edges that are directly or indirectly connected with the same set of ports are regarded as
 * belonging to the same hyperedge. Crossings between edges that belong to the same hyperedge are
 * not counted by this analysis.</p>
 * 
 * @author msp
 * @author cds
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class HyperedgeCrossingsAnalysis implements IAnalysis {
    
    /** tolerance for double equality. */
    private static final double TOLERANCE = 1e-4;
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Hyperedge crossings analysis", 1);
        
        // Collect all hyperedge segments, merge them and count crossings
        Hyperedge[] hyperedges = collectHyperedges(parentNode);
        
        for (Hyperedge he : hyperedges) {
            collectEdgeSegments(he);
            mergeEdgeSegments(he);
        }
        
        int crossings = countCrossings(hyperedges);
        
        progressMonitor.done();
        return new Object[] { crossings, hyperedges.length };
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    // Edge Collection
    
    /**
     * Collect all hyperedges of the given graph.
     * 
     * @param parentNode the parent node of the graph
     * @return the collected hyperedges
     */
    private Hyperedge[] collectHyperedges(final KNode parentNode) {
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        
        // collect all edges and translate their coordinates to absolute
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        List<KVectorChain> chains = new ArrayList<KVectorChain>();
        Set<Hyperedge> hyperedges = Sets.newHashSet();
        Map<KPort, Hyperedge> port2HyperedgeMap = Maps.newHashMap();
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
                chain.offset(referencePoint);
                
                // transform spline control points to approximated bend points
                if (edge.getData(KEdgeLayout.class).getProperty(LayoutOptions.EDGE_ROUTING)
                        == EdgeRouting.SPLINES) {
                    chain = ElkMath.approximateBezierSpline(chain);
                }
                
                chains.add(chain);
                KPort sourcePort = edge.getSourcePort();
                KPort targetPort = edge.getTargetPort();
                Hyperedge sourceHE = port2HyperedgeMap.get(sourcePort);
                Hyperedge targetHE = port2HyperedgeMap.get(targetPort);
                if (sourceHE == null && targetHE == null) {
                    Hyperedge hyperedge = new Hyperedge();
                    hyperedge.chains.add(chain);
                    hyperedges.add(hyperedge);
                    hyperedge.ports.add(sourcePort);
                    port2HyperedgeMap.put(sourcePort, hyperedge);
                    hyperedge.ports.add(targetPort);
                    port2HyperedgeMap.put(targetPort, hyperedge);
                } else if (sourceHE == null) {
                    targetHE.chains.add(chain);
                    targetHE.ports.add(sourcePort);
                    port2HyperedgeMap.put(sourcePort, targetHE);
                } else if (targetHE == null) {
                    sourceHE.chains.add(chain);
                    sourceHE.ports.add(targetPort);
                    port2HyperedgeMap.put(targetPort, sourceHE);
                } else if (sourceHE == targetHE) {
                    sourceHE.chains.add(chain);
                } else {
                    sourceHE.chains.add(chain);
                    hyperedges.remove(targetHE);
                    for (KPort p : targetHE.ports) {
                        port2HyperedgeMap.put(p, sourceHE);
                    }
                    sourceHE.chains.addAll(targetHE.chains);
                    sourceHE.ports.addAll(targetHE.ports);
                }
            }
            
            // enqueue the child nodes
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        return hyperedges.toArray(new Hyperedge[hyperedges.size()]);
    }
    
    /**
     * Go through all the edges in the given hyperedge and build a list of their segments.
     * 
     * @param hyperedge a hyperedge
     */
    private void collectEdgeSegments(final Hyperedge hyperedge) {
        for (KVectorChain chain : hyperedge.chains) {
            // Transform the vector chain into a list of line segments
            KVector p1 = chain.getFirst();
            ListIterator<KVector> pointIter = chain.listIterator(1);
            while (pointIter.hasNext()) {
                KVector p2 = pointIter.next();
                
                Line segment = new Line(p1.x, p1.y, p2.x, p2.y);
                hyperedge.segments.add(segment);
                
                p1 = p2;
            }
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Edge Merging
    
    /**
     * Merge segments of the given hyperedge, if possible.
     * 
     * @param hyperedge a hyperedge
     */
    private void mergeEdgeSegments(final Hyperedge hyperedge) {
        // Iterate over the lines in the list
        ListIterator<Line> iterator1 = hyperedge.segments.listIterator();
        while (iterator1.hasNext()) {
            Line line1 = iterator1.next();
            
            // If we have reached the end of the list, stop
            if (!iterator1.hasNext()) {
                break;
            }
            
            // If the current segment has already been removed, continue
            if (Math.abs(line1.x1 - line1.x2) < TOLERANCE
                    && Math.abs(line1.y1 - line1.y2) < TOLERANCE) {
                continue;
            }
            
            // Go through the rest of the line segments, looking for one to merge with this segment
            ListIterator<Line> iterator2 = hyperedge.segments.listIterator(iterator1.nextIndex());
            while (iterator2.hasNext()) {
                Line line2 = iterator2.next();
                
                // If the current segment has already been removed, continue
                if (Math.abs(line2.x1 - line2.x2) < TOLERANCE
                        && Math.abs(line2.y1 - line2.y2) < TOLERANCE) {
                    continue;
                }
                
                if (canBeMerged(line1, line2)) {
                    mergeSegments(line1, line2);
                    
                    // Mark the second line for removal
                    line2.x1 = 0.0;
                    line2.y1 = 0.0;
                    line2.x2 = 0.0;
                    line2.y2 = 0.0;
                }
            }
        }
        
        // Iterate over the segments again and remove the ones that are to be removed
        ListIterator<Line> iterator = hyperedge.segments.listIterator();
        while (iterator.hasNext()) {
            Line line = iterator.next();
            
            if (Math.abs(line.x1 - line.x2) < TOLERANCE
                    && Math.abs(line.y1 - line.y2) < TOLERANCE) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Check if the two lines can be merged. Two lines can be merged if the two lines
     * overlap and if the infinitely extended lines defined by the two lines are equal.
     * 
     * @param line1 the first line.
     * @param line2 the second line.
     * @return {@code true} if they can be merged, {@code false} otherwise.
     */
    private boolean canBeMerged(final Line line1, final Line line2) {
        // The lines have to intersect
        if (!line1.intersectsLine(line2)) {
            return false;
        }
        
        // The lines have to be parallel
        double s = (line2.y2 - line2.y1) * (line1.x2 - line1.x1)
                - (line2.x2 - line2.x1) * (line1.y2 - line1.y1);
        return Math.abs(s) < TOLERANCE;
    }
    
    /**
     * Merge the two lines by updating the first line. The second line is left untouched.
     * The lines are assumed to be parallel.
     * 
     * @param line1 the first line, which the second line is merged into.
     * @param line2 the second line.
     */
    private void mergeSegments(final Line line1, final Line line2) {
        KVector start = new KVector(Integer.MAX_VALUE, Integer.MAX_VALUE);
        KVector end = new KVector(Integer.MIN_VALUE, Integer.MIN_VALUE);
        KVector[] points = new KVector[] { line1.getV1(), line1.getV2(), line2.getV1(), line2.getV2() };
        
        if (Math.abs(line1.x2 - line1.x1) >= Math.abs(line1.y2 - line1.y1)) {
            // The line is arranged horizontally
            for (KVector p : points) {
                if (p.x < start.x) {
                    start = p;
                }
                if (p.x > end.x) {
                    end = p;
                }
            }
        } else {
           // The line is arranged vertically
            for (KVector p : points) {
                if (p.y < start.y) {
                    start = p;
                }
                if (p.y > end.y) {
                    end = p;
                }
            }
        }
        
        // Set new start and end points
        line1.x1 = start.x;
        line1.y1 = start.y;
        line1.x2 = end.x;
        line1.y2 = end.y;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Cross Counting
    
    /**
     * Count all crossings between lines in the given array. This method assumes that
     * segments have already been merged.
     * 
     * @param hyperedges array of hyperedges
     * @return the number of segment crossings
     */
    private int countCrossings(final Hyperedge[] hyperedges) {
        int crossings = 0;
        
        for (int i = 0; i < hyperedges.length; i++) {
            for (int j = i + 1; j < hyperedges.length; j++) {
                for (Line line1 : hyperedges[i].segments) {
                    for (Line line2 : hyperedges[j].segments) {
                        if (line1.intersectsLine(line2)) {
                            crossings++;
                        }
                    }
                }
            }
        }
        
        return crossings;
    }
    
    /**
     * Hyperedge class.
     */
    private static class Hyperedge {
        /** the edges that are part of this hyperedge, represented by vector chains. */
        private final List<KVectorChain> chains = Lists.newLinkedList();
        /** the ports to which the edges of this hyperedge are connected. */
        private final List<KPort> ports = Lists.newLinkedList();
        /** the line segments derived from the vector chains. */
        private final List<Line> segments = Lists.newLinkedList();
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Chains: " + chains.toString() + ", Ports: " + ports.toString();
        }
    }
    
    /**
     * A specialized line class.
     */
    @SuppressWarnings("serial")
    private static class Line extends Line2D.Double {
        
        /**
         * Constructs and initializes a line from the specified coordinates.
         * 
         * @param x1 the X coordinate of the start point
         * @param y1 the Y coordinate of the start point
         * @param x2 the X coordinate of the end point
         * @param y2 the Y coordinate of the end point
         * @since 1.2
         */
        public Line(final double x1, final double y1, final double x2, final double y2) {
            super(x1, y1, x2, y2);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "(" + x1 + "," + y1 + ")->(" + x2 + "," + y2 + ")";
        }
        
        /**
         * Create a vector with the first point.
         * 
         * @return the first point
         */
        public KVector getV1() {
            return new KVector(x1, y1);
        }
        
        /**
         * Create a vector with the second point.
         * 
         * @return the second point
         */
        public KVector getV2() {
            return new KVector(x2, y2);
        }
        
    }
    
}
