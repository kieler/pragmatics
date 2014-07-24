/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.util;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Queue;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CShape;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.codaflow.properties.InternalCodaflowProperties;

/**
 * @author uru
 */
public final class CodaflowUtil {

    private CodaflowUtil() {
    }

    /**
     * Copies the position and size information from {@code k} to {@code c}.
     * 
     * @param c
     *            the shape for which to set position and size
     * @param k
     *            the {@link KShapeLayoutData} element holding the original information.
     */
    public static void setPosAndSize(final CShape c, final KShapeLayout k) {
        c.getPos().x = k.getXpos();
        c.getPos().y = k.getYpos();
        c.getSize().x = k.getWidth();
        c.getSize().y = k.getHeight();

        // insets
        KInsets insets = k.getInsets();
        if (insets == null) {
            insets = KLayoutDataFactory.eINSTANCE.createKInsets();
            k.setInsets(insets);
        }
        c.getInsets().left = insets.getLeft();
        c.getInsets().right = insets.getRight();
        c.getInsets().top = insets.getTop();
        c.getInsets().bottom = insets.getBottom();

        // margins
        Margins margins = k.getProperty(LayoutOptions.MARGINS);
        c.getMargins().left = margins.left;
        c.getMargins().right = margins.right;
        c.getMargins().top = margins.top;
        c.getMargins().bottom = margins.bottom;
    }
    
    /**
     * Copies the position and size information from {@code e} to {@code c}. Additionally, converts
     * e's relative position into an absolute position.
     * 
     * @param c
     *            the shape for which to set position and size
     * @param e
     *            the element holding the original information.
     * @param parent
     *            the parent node that is used to calculate the absolute position.
     */
    public static void setPosAndSizeAbsolute(final CShape c, final KGraphElement e,
            final KNode parent) {

        KShapeLayout layout = e.getData(KShapeLayout.class);
        setPosAndSize(c, layout);

        // convert to absolute position by adding parent's absolute position
        KVector absolute = KimlUtil.toAbsolute(layout.createVector(), parent);
        c.getPos().x = absolute.x;
        c.getPos().y = absolute.y;
    }

    /**
     * Possible intersections are checked within this order: NORTH, EAST, SOUTH, WEST. The first
     * intersection that is found is returned, i.e. if the line intersects the rectangle at the WEST
     * and and the EAST side, only the EAST side intersection is returned.
     * 
     * Alongside the intersection point, the side of the intersection is returned in form of the
     * {@link PortSide} enum.
     * 
     * @param line
     *            a line
     * @param rectangle
     *            a rectangle
     * 
     * @return the <b>first</b> intersection point between the passed line and rectangle that is
     *         found. {@code null} if no intersection could be found.
     */
    public static Pair<KVector, PortSide> getIntersectionPoint(final Line2D.Double line,
            final Rectangle2D rectangle) {

        KVector res = null;
        
        // top
        res = getLineIntersectionPoint(line, new Line2D.Double(rectangle.getMinX(), rectangle.getMinY(),
                        rectangle.getMaxX(), rectangle.getMinY()));
        if (res != null) {
            return Pair.of(res, PortSide.NORTH);
        }

        // right
        res = getLineIntersectionPoint(line, new Line2D.Double(rectangle.getMaxX(), rectangle.getMinY(),
                        rectangle.getMaxX(), rectangle.getMaxY()));
        if (res != null) {
            return Pair.of(res, PortSide.EAST);
        }

        // bottom
        res = getLineIntersectionPoint(line, new Line2D.Double(rectangle.getMinX(), rectangle.getMaxY(),
                        rectangle.getMaxX(), rectangle.getMaxY()));
        if (res != null) {
            return Pair.of(res, PortSide.SOUTH);
        }

        // left
        res = getLineIntersectionPoint(line, new Line2D.Double(rectangle.getMinX(), rectangle.getMinY(),
                        rectangle.getMinX(), rectangle.getMaxY()));
        if (res != null) {
            return Pair.of(res, PortSide.WEST);
        }
        
        return null;
    }

    private static double square(final double x) {
        return x * x;
    }

    private static double dist2(final KVector v1, final KVector v2) {
        return square(v1.x - v2.x) + square(v1.y - v2.y);
    }

    private static double pointToSegmentDistanceSquared(final KVector p, final KVector v,
            final KVector w) {

        double ld = dist2(v, w);
        if (ld == 0) {
            return dist2(p, v);
        }
        double t = ((p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y)) / ld;
        if (t < 0) {
            return dist2(p, v);
        }
        if (t > 1) {
            return dist2(p, w);
        }
        KVector vw = new KVector(v.x + t * (w.x - v.x), v.y + t * (w.y - v.y));
        return p.distance(vw);
    }

    /**
     * As opposed to standard geometry functions, e.g., AWT's Line2D function, this method does not
     * consider the line to be of infinite length.
     * 
     * @param p
     *            the point
     * @param v
     *            the start point of the line segment
     * @param w
     *            the end point of the line segment
     * @return the shortest distance between {@code p} and the line segment.
     */
    public static double pointToSegmentDistance(final KVector p, final KVector v, final KVector w) {
        return Math.sqrt(pointToSegmentDistanceSquared(p, v, w));
    }
    
    /**
     * Determines the intersection point of two lines, if it exists.
     * 
     * Method taken from this <a href=
     * "http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect"
     * >Stackoverflow Post</a>.
     * 
     * @param l1
     *            first line with start and end point.
     * @param l2
     *            second line with start and end point.
     * @return The intersection point if it exists, {@code null} otherwise. Note that for (partly)
     *         overlapping lines the center point of the overlapping segment is returned.
     */
    public static KVector getLineIntersectionPoint(final Line2D.Double l1, final Line2D.Double l2) {

        // first line with vector
        final KVector p = new KVector(l1.x1, l1.y1);
        final KVector r = new KVector(l1.x2, l1.y2).sub(p);

        // second line as vectors
        final KVector q = new KVector(l2.x1, l2.y1);
        final KVector s = new KVector(l2.x2, l2.y2).sub(q);

        // t = (q - p) x s / (r x s)
        // u = (q − p) × r / (r × s)
        final double rxs = crossProduct(r, s);
        final KVector qsp = q.clone().sub(p);

        final double t = crossProduct(qsp, s) / rxs;
        final double u = crossProduct(qsp, r) / rxs;

        // Case 4: If r × s ≠ 0 and 0 ≤ t ≤ 1 and 0 ≤ u ≤ 1, the two line segments meet at the point
        // p + t r = q + u s.
        if (rxs != 0 && t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            final KVector intersection = new KVector(p.x + t * r.x, p.y + t * r.y);
            return intersection;
        }
        
        // Case 1: If r × s = 0 and (q − p) × r = 0, then the two lines are collinear.
        // If in addition, either 0 ≤ (q − p) · r ≤ r · r or 0 ≤ (p − q) · s ≤ s · s,
        // then the two lines are overlapping.
        if (rxs == 0 && crossProduct(qsp, r) == 0) {
            // collinear, return the center of the overlapping segment
            // SUPPRESS CHECKSTYLE NEXT 10 MagicNumber
            final double qspr = qsp.dotProduct(r);
            if (0 <= qspr && qspr <= r.dotProduct(r)) {
                // q + 0.5 ((p + r) - q)
                return q.clone().add(p.clone().add(r).sub(q).scale(0.5));
            }
            final double psqs = p.clone().sub(q).dotProduct(s);
            if (0 <= psqs && psqs <= s.dotProduct(s)) {
                // p + 0.5 ((q + s) - p)
                return p.clone().add(q.clone().add(s).sub(p).scale(0.5));
            }
        }

        // other cases default to null
        return null;
    }

    private static double crossProduct(final KVector u, final KVector v) {
        return u.x * v.y - u.y * v.x;
    }
    
    /**
     * Marks all nodes that are part of a tree within the specified graph
     * with the {@link InternalCodaflowProperties#PART_OF_TREE} property.
     * 
     * @param graph
     *            the graph.
     */
    public static void markTrees(final CGraph graph) {
 
        final int n = graph.getChildren().size();
        final int[] degree = new int[n];
        Queue<CNode> degOneNodes = Lists.newLinkedList();
        CNode[] treeNodes = new CNode[n];
        
        
        // determine the degree of all nodes in the graph
        int index = 0;
        for (CNode node : graph.getChildren()) {
            node.id = index++; 
            for (CEdge e : node.getConnectedEdges()) {
                if (e.getSource().equals(e.getTarget())) {
                    // ignore self loops
                    continue;
                }
                degree[node.id] += 1;
            }
            
            if (degree[node.id] == 1) {
                degOneNodes.add(node);
            }
        }
        
        // collect all nodes that are part of a tree by 
        // consecutively pruning degree 1 nodes
        while (!degOneNodes.isEmpty()) {
           CNode node = degOneNodes.poll();
           treeNodes[node.id] = node;
           // detach incoming edge or outgoing edge
           // (it is guaranteed to be only one edge
           for (CEdge e : node.getIncomingEdges()) {
               index = e.getSource().id;
               degree[index]--;
               if (degree[index] == 1) {
                  degOneNodes.add(e.getSource()); 
               }
           }
           // detach outgoing edges
           for (CEdge e : node.getOutgoingEdges()) {
               index = e.getTarget().id;
               degree[index]--;
               if (degree[index] == 1) {
                   degOneNodes.add(e.getTarget());
               }
           }
        }

        // all nodes in the 'treeNodes' set are part of trees now

        // mark
        for (int i = 0; i < treeNodes.length; i++) {
            CNode node = treeNodes[i];
            if (node != null) {
                node.setProperty(InternalCodaflowProperties.PART_OF_TREE, true);
            }
        }

    }
}
