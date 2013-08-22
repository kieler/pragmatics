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

import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
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
 * A special edge crossings analysis for hyperedges. Works best with data flow diagrams.
 * 
 * <p>There is one case of crossings which the algorithm fails to count. When an edge
 * intersects another edge without joining it, but with the intersection point being a
 * bend point, the crossing isn't counted. This is usually not a problem for orthogonally
 * routed diagrams, but may be one for others. I haven't fixed that yet because there's
 * no easy obvious way to do so, and the case is sufficiently rare.</p>
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
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Hyperedge crossings analysis", 1);
        
        // Collect all edge segments, merge them and count crossings
        List<Line> edgeSegments = collectEdgeSegments(parentNode);
        
        mergeEdgeSegments(edgeSegments);
        
        int crossings = countCrossings(edgeSegments.toArray(new Line[edgeSegments.size()]));
        
        progressMonitor.done();
        return crossings;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Edge Collection
    
    /**
     * Goes through all the edges in the given node and its children and builds
     * a list of their segments.
     * 
     * @param parentNode the node.
     * @return 
     */
    private List<Line> collectEdgeSegments(final KNode parentNode) {
        List<Line> segments = new LinkedList<Line>(); 
        boolean hierarchy = parentNode.getData(KShapeLayout.class).getProperty(
                AnalysisOptions.ANALYZE_HIERARCHY);
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.addAll(parentNode.getChildren());
        
        while (!nodeQueue.isEmpty()) {
            KNode node = nodeQueue.poll();
            
            // Iterate over the node's outgoing edges
            for (KEdge edge : node.getOutgoingEdges()) {
                if (!hierarchy && edge.getTarget().getParent() != parentNode) {
                    continue;
                }
                KVectorChain chain = edge.getData(KEdgeLayout.class).createVectorChain();
                
                // Translate the bend point coordinates to absolute
                KNode parent = node;
                if (!KimlUtil.isDescendant(edge.getTarget(), parent)) {
                    parent = node.getParent();
                }
                KVector referencePoint = new KVector();
                KimlUtil.toAbsolute(referencePoint, parent);
                chain.translate(referencePoint);
                
                // Transform spline control points to approximated bend points
                if (edge.getData(KEdgeLayout.class).getProperty(LayoutOptions.EDGE_ROUTING)
                        == EdgeRouting.SPLINES) {
                    
                    chain = KielerMath.approximateSpline(chain);
                }
                
                // Transform the vector chain into a list of line segments
                KVector p1 = chain.getFirst();
                ListIterator<KVector> pointIter = chain.listIterator(1);
                while (pointIter.hasNext()) {
                    KVector p2 = pointIter.next();
                    
                    Line segment = new Line(p1.x, p1.y, p2.x, p2.y);
                    segments.add(segment);
                    
                    p1 = p2;
                }
            }
            
            // Enqueue the child nodes
            if (hierarchy) {
                nodeQueue.addAll(node.getChildren());
            }
        }
        
        return segments;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Edge Merging
    
    /**
     * Merges segments of the given list, if possible.
     * 
     * @param segments the list of segments to merge. The list will usually contain less
     *                 segments than before after this method is finished.
     */
    private void mergeEdgeSegments(final List<Line> segments) {
        // Iterate over the lines in the list
        ListIterator<Line> iterator1 = segments.listIterator();
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
            ListIterator<Line> iterator2 = segments.listIterator(iterator1.nextIndex());
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
        ListIterator<Line> iterator = segments.listIterator();
        while (iterator.hasNext()) {
            Line line = iterator.next();
            
            if (Math.abs(line.x1 - line.x2) < TOLERANCE
                    && Math.abs(line.y1 - line.y2) < TOLERANCE) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Checks if the two lines can be merged. Two lines can be merged if the two lines
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
     * Merges the two lines by updating the first line. The second line is left untouched.
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
     * Counts all crossings between lines in the given array. This method assumes that
     * segments have already been merged. If they have not, the result may be too high.
     * 
     * @param segments the array of segments.
     * @return the number of segment crossings.
     */
    private int countCrossings(final Line[] segments) {
        int crossings = 0;
        
        for (int i = 0; i < segments.length; i++) {
            for (int j = i + 1; j < segments.length; j++) {
                // Find out if the two cross each other. However, not every crossing
                // is counted. We take the usual definition for crossings ("two lines
                // cross if they have a common point.") and extend it with the following
                // exception: "Two lines cross if they have a common point that is not
                // an end point of either of them."
                
                if (segments[i].intersectsLine(segments[j])) {
                    if (!(segments[i].ptLineDist(segments[j].x1, segments[j].y1) == 0.0
                            || segments[i].ptLineDist(segments[j].x2, segments[j].y2) == 0.0
                            || segments[j].ptLineDist(segments[i].x1, segments[i].y1) == 0.0
                            || segments[j].ptLineDist(segments[i].x2, segments[i].y2) == 0.0))
                    {
                        crossings++; 
                    }
                }
            }
        }
        
        return crossings;
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
