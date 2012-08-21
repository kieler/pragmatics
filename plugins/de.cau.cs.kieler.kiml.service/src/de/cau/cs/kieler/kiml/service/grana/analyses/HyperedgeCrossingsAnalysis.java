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
import java.awt.geom.Point2D;
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
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class HyperedgeCrossingsAnalysis implements IAnalysis {
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Hyperedge crossings analysis", 1);
        
        // Collect all edge segments, merge them and count crossings
        List<Line2D.Double> edgeSegments = collectEdgeSegments(parentNode);
        
        mergeEdgeSegments(edgeSegments);
        
        int crossings = countCrossings(edgeSegments.toArray(new Line2D.Double[0]));
        
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
    private List<Line2D.Double> collectEdgeSegments(final KNode parentNode) {
        List<Line2D.Double> segments = new LinkedList<Line2D.Double>(); 
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
                    
                    Line2D.Double segment = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
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
    private void mergeEdgeSegments(final List<Line2D.Double> segments) {
        // Iterate over the lines in the list
        ListIterator<Line2D.Double> iterator1 = segments.listIterator();
        while (iterator1.hasNext()) {
            Line2D.Double line1 = iterator1.next();
            
            // If we have reached the end of the list, stop
            if (!iterator1.hasNext()) {
                break;
            }
            
            // If the current segment has already been removed, continue
            if (line1.x1 == line1.x2 && line1.y1 == line1.y2) {
                continue;
            }
            
            // Go through the rest of the line segments, looking for one to merge with this segment
            ListIterator<Line2D.Double> iterator2 = segments.listIterator(iterator1.nextIndex());
            while (iterator2.hasNext()) {
                Line2D.Double line2 = iterator2.next();
                
                // If the current segment has already been removed, continue
                if (line2.x1 == line2.x2 && line2.y1 == line2.y2) {
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
        ListIterator<Line2D.Double> iterator = segments.listIterator();
        while (iterator.hasNext()) {
            Line2D.Double line = iterator.next();
            
            if (line.x1 == line.x2 && line.y1 == line.y2) {
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
    private boolean canBeMerged(final Line2D.Double line1, final Line2D.Double line2) {
        // The lines have to intersect
        if (!line1.intersectsLine(line2)) {
            return false;
        }
        
        // The lines have to be parallel, which means that the distance between line1 and
        // both points defining line2 have to be equal
        if (line1.ptLineDist(line2.x1, line2.y1) != line1.ptLineDist(line2.x2, line2.y2)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Merges the two lines by updating the first line. The second line is left untouched.
     * 
     * @param line1 the first line, which the second line is merged into.
     * @param line2 the second line.
     */
    private void mergeSegments(final Line2D.Double line1, final Line2D.Double line2) {
        Point2D.Double start = new Point2D.Double(line1.x1, line1.y1);
        Point2D.Double end = new Point2D.Double(line1.x2, line1.y2);
        
        // If the lines are vertical, we use the points with the lowest and highest
        // y coordinate as the merged line's end points. Otherwise, we use the points
        // with the lowest and highest x coordinate
        if (line1.x1 == line1.x2) {
            // Vertical
            if (line1.y2 < start.y) {
                start.x = line1.x2;
                start.y = line1.y2;
            }
            
            if (line2.y1 < start.y) {
                start.x = line2.x1;
                start.y = line2.y1;
            }
            
            if (line2.y2 < start.y) {
                start.x = line2.x2;
                start.y = line2.y2;
            }
            
            if (line1.y2 > end.y) {
                end.x = line1.x2;
                end.y = line1.y2;
            }
            
            if (line2.y1 < end.y) {
                end.x = line2.x1;
                end.y = line2.y1;
            }
            
            if (line2.y2 < end.y) {
                end.x = line2.x2;
                end.y = line2.y2;
            }
        } else {
            // Horizontal
            if (line1.x2 < start.x) {
                start.x = line1.x2;
                start.y = line1.y2;
            }
            
            if (line2.x1 < start.x) {
                start.x = line2.x1;
                start.y = line2.y1;
            }
            
            if (line2.x2 < start.x) {
                start.x = line2.x2;
                start.y = line2.y2;
            }
            
            if (line1.x2 > end.x) {
                end.x = line1.x2;
                end.y = line1.y2;
            }
            
            if (line2.x1 < end.x) {
                end.x = line2.x1;
                end.y = line2.y1;
            }
            
            if (line2.x2 < end.x) {
                end.x = line2.x2;
                end.y = line2.y2;
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
    private int countCrossings(final Line2D.Double[] segments) {
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
    
}
