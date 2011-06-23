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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * A special edge crossings analysis for hyperedges. Works best with data flow diagrams.
 * 
 * @author msp
 */
public class HyperedgeCrossingsAnalysis implements IAnalysis {

    /** a straight line segment. */
    private class Segment {
        private KVector p1, p2;
        @Override
        public String toString() {
            return p1.toString() + " -- " + p2.toString();
        }
    }
    
    /** a hyperedge. */
    private class Hyperedge {
        private List<KGraphElement> ports = new LinkedList<KGraphElement>();
        private List<Segment> segments;
    }
    
    /**
     * Returns whether two line segments have an intersection.
     * 
     * @param s1 first line segment
     * @param s2 second line segment
     * @return true if the lines have an intersection
     */
    private static boolean hasIntersection(final Segment s1, final Segment s2) {
        double s = (s2.p2.y - s2.p1.y) * (s1.p2.x - s1.p1.x)
                - (s2.p2.x - s2.p1.x) * (s1.p2.y - s1.p1.y);
        // are the line segments parallel?
        if (s == 0) {
            if (s1.p1.x == s1.p2.x && s2.p1.x == s2.p2.x && s1.p1.x == s2.p1.x) {
                double min1 = Math.min(s1.p1.y, s1.p2.y);
                double max1 = Math.max(s1.p1.y, s1.p2.y);
                double min2 = Math.min(s2.p1.y, s2.p2.y);
                double max2 = Math.max(s2.p1.y, s2.p2.y);
                return min1 <= max2 && max1 > min2;
            }
            if (s1.p1.y == s1.p2.y && s2.p1.y == s2.p2.y && s1.p1.y == s2.p1.y) {
                double min1 = Math.min(s1.p1.x, s1.p2.x);
                double max1 = Math.max(s1.p1.x, s1.p2.x);
                double min2 = Math.min(s2.p1.x, s2.p2.x);
                double max2 = Math.max(s2.p1.x, s2.p2.x);
                return min1 <= max2 && max1 > min2;
            }
            return false;
        }
        double a1 = (s2.p2.x - s2.p1.x) * (s1.p1.y - s2.p1.y)
                - (s2.p2.y - s2.p1.y) * (s1.p1.x - s2.p1.x);
        double a2 = (s1.p2.x - s1.p1.x) * (s1.p1.y - s2.p1.y)
                - (s1.p2.y - s1.p1.y) * (s1.p1.x - s2.p1.x);
        double t1 = a1 / s;
        double t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
        return 0 <= t1 && t1 < 1 && 0 <= t2 && t2 < 1;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Edge Crossings analysis", 1);
        
        // collect all hyperedges and translate their coordinates to absolute
        Map<KGraphElement, Hyperedge> hyperedgeMap = new HashMap<KGraphElement, Hyperedge>();
        Set<Hyperedge> hedgeSet = new HashSet<Hyperedge>();
        LinkedList<KNode> nodeQueue = new LinkedList<KNode>();
        nodeQueue.offer(parentNode);
        while (!nodeQueue.isEmpty()) {
            // poll the first element
            KNode node = nodeQueue.poll();
            // collect the outgoing edges
            for (KEdge edge : node.getOutgoingEdges()) {
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
                
                // transform the vector chain into a list of line segments
                List<Segment> segments = new LinkedList<Segment>();
                KVector p1 = chain.getFirst();
                ListIterator<KVector> pointIter = chain.listIterator(1);
                while (pointIter.hasNext()) {
                    KVector p2 = pointIter.next();
                    Segment segment = new Segment();
                    segment.p1 = p1;
                    segment.p2 = p2;
                    segments.add(segment);
                    p1 = p2;
                }
                
                // look for ports and hypernodes at source and target
                KGraphElement sourceRef = null, targetRef = null;
                Hyperedge sourceHyper = null, targetHyper = null;
                if (edge.getSourcePort() != null) {
                    sourceRef = edge.getSourcePort();
                } else if (edge.getSource().getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.HYPERNODE)) {
                    sourceRef = edge.getSource();
                }
                if (edge.getTargetPort() != null) {
                    targetRef = edge.getTargetPort();
                } else if (edge.getTarget().getData(KShapeLayout.class)
                        .getProperty(LayoutOptions.HYPERNODE)) {
                    targetRef = edge.getTarget();
                }
                
                // create a new hypernode or merge the segments with an existing one
                if (sourceRef == null && targetRef == null) {
                    Hyperedge hyperedge = new Hyperedge();
                    hyperedge.segments = segments;
                    hedgeSet.add(hyperedge);
                } else if (sourceRef == null) {
                    targetHyper = hyperedgeMap.get(targetRef);
                    if (targetHyper == null) {
                        targetHyper = new Hyperedge();
                        targetHyper.ports.add(targetRef);
                        targetHyper.segments = segments;
                        hyperedgeMap.put(targetRef, targetHyper);
                    } else {
                        mergeSegments(targetHyper.segments, segments);
                    }
                } else if (targetRef == null) {
                    sourceHyper = hyperedgeMap.get(sourceRef);
                    if (sourceHyper == null) {
                        sourceHyper = new Hyperedge();
                        sourceHyper.ports.add(sourceRef);
                        sourceHyper.segments = segments;
                        hyperedgeMap.put(sourceRef, sourceHyper);
                    } else {
                        mergeSegments(sourceHyper.segments, segments);
                    }
                } else {
                    sourceHyper = hyperedgeMap.get(sourceRef);
                    targetHyper = hyperedgeMap.get(targetRef);
                    if (sourceHyper == null && targetHyper == null) {
                        Hyperedge hyperedge = new Hyperedge();
                        hyperedge.ports.add(sourceRef);
                        hyperedge.ports.add(targetRef);
                        hyperedge.segments = segments;
                        hyperedgeMap.put(sourceRef, hyperedge);
                        hyperedgeMap.put(targetRef, hyperedge);
                    } else if (sourceHyper == null) {
                        targetHyper.ports.add(sourceRef);
                        mergeSegments(targetHyper.segments, segments);
                        hyperedgeMap.put(sourceRef, targetHyper);
                    } else if (targetHyper == null) {
                        sourceHyper.ports.add(targetRef);
                        mergeSegments(sourceHyper.segments, segments);
                        hyperedgeMap.put(targetRef, sourceHyper);
                    } else if (sourceHyper == targetHyper) {
                        mergeSegments(sourceHyper.segments, segments);
                    } else {
                        sourceHyper.ports.addAll(targetHyper.ports);
                        mergeSegments(sourceHyper.segments, targetHyper.segments);
                        mergeSegments(sourceHyper.segments, segments);
                        for (KGraphElement port : targetHyper.ports) {
                            hyperedgeMap.put(port, sourceHyper);
                        }
                    }
                }
            }
            // enqueue the child nodes
            nodeQueue.addAll(node.getChildren());
        }
        
        // count the number of crossings between all edge segments of the compound graph
        hedgeSet.addAll(hyperedgeMap.values());
        Hyperedge[] hyperedges = hedgeSet.toArray(new Hyperedge[hedgeSet.size()]);
        int crossings = 0;
        for (int i = 0; i < hyperedges.length; i++) {
            for (int j = i + 1; j < hyperedges.length; j++) {
                for (Segment seg1 : hyperedges[i].segments) {
                    for (Segment seg2 : hyperedges[j].segments) {
                        if (hasIntersection(seg1, seg2)) {
                            crossings++;
                        }
                    }
                }
            }
        }

        progressMonitor.done();
        return crossings;
    }
    
    /**
     * Merge the segments of the second list into the first list, discarding overlapping ones.
     * 
     * @param list1 the list into which all segments are merged
     * @param list2 the list from which new segments are taken
     */
    private void mergeSegments(final List<Segment> list1, final List<Segment> list2) {
        for (Segment segment2 : list2) {
            boolean canAdd = true;
            ListIterator<Segment> list1Iter = list1.listIterator();
            while (list1Iter.hasNext()) {
                Segment segment1 = list1Iter.next();
                if (covers(segment1, segment2)) {
                    canAdd = false;
                    break;
                } else if (covers(segment2, segment1)) {
                    list1Iter.remove();
                    break;
                }
            }
            if (canAdd) {
                list1.add(segment2);
            }
        }
    }
    
    /**
     * Determine whether the first segment completely covers the second one. Currently
     * only horizontal and vertical segments are supported.
     * 
     * @param s1 first segment
     * @param s2 second segment
     * @return true if s1 covers s2
     */
    private boolean covers(final Segment s1, final Segment s2) {
        if (s1.p1.x == s1.p2.x && s2.p1.x == s2.p2.x && s1.p1.x == s2.p1.x) {
            double min1 = Math.min(s1.p1.y, s1.p2.y);
            double max1 = Math.max(s1.p1.y, s1.p2.y);
            double min2 = Math.min(s2.p1.y, s2.p2.y);
            double max2 = Math.max(s2.p1.y, s2.p2.y);
            return min1 <= min2 && max1 >= max2;
        }
        if (s1.p1.y == s1.p2.y && s2.p1.y == s2.p2.y && s1.p1.y == s2.p1.y) {
            double min1 = Math.min(s1.p1.x, s1.p2.x);
            double max1 = Math.max(s1.p1.x, s1.p2.x);
            double min2 = Math.min(s2.p1.x, s2.p2.x);
            double max2 = Math.max(s2.p1.x, s2.p2.x);
            return min1 <= min2 && max1 >= max2;
        }
        return false;
    }
    
}
