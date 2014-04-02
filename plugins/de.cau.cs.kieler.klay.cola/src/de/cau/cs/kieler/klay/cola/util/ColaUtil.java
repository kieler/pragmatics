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
package de.cau.cs.kieler.klay.cola.util;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CShape;

/**
 * @author uru
 */
public final class ColaUtil {

    private ColaUtil() {
    }

    public static Set<CEdge> findMinimalFAS(final List<CNode> nodes) {
        return new MinimalFeedbackArcSetProcessor().process(nodes);
    }
    
    /**
     * @param graph
     *            the graph for which to find the strongly connected components.
     * @return a set containing all strongly connected components of the graph.
     */
    public static Set<Set<CNode>> findStronglyConnectedComponents(final CGraph graph) {
        return new TarjanAlg(graph).getStronglyConnectedComponents();
    }

    /**
     * Performs Tarjan's algorithm to find strongly connected components.
     */
    private static class TarjanAlg {

        private Map<CNode, Integer> indexMap = Maps.newHashMap();
        private Map<CNode, Integer> lowlinkMap = Maps.newHashMap();
        private int index = 0;
        private Stack<CNode> stack = new Stack<CNode>();
        private Set<Set<CNode>> sccs = Sets.newHashSet();

        public TarjanAlg(final CGraph graph) {
            for (CNode n : graph.getChildren()) {
                if (!indexMap.containsKey(n)) {
                    strongConnect(n);
                }
            }
        }

        public Set<Set<CNode>> getStronglyConnectedComponents() {
            return sccs;
        }

        private void strongConnect(final CNode node) {
            indexMap.put(node, index);
            lowlinkMap.put(node, index);
            index++;
            stack.push(node);

            // successors of current node
            for (CEdge e : node.getOutgoingEdges()) {
                CNode target = e.getTarget();

                // ignore cross hierarchy edges
                if (!node.getParent().equals(target.getParent())) {
                    continue;
                }

                if (!indexMap.containsKey(target)) {
                    // successor not been visited
                    strongConnect(target);
                    lowlinkMap.put(node, Math.min(lowlinkMap.get(node), lowlinkMap.get(target)));
                } else if (stack.contains(target)) {
                    // successor is in the current scc
                    lowlinkMap.put(node, Math.min(lowlinkMap.get(node), indexMap.get(target)));
                }
            }

            // if current node is a root node, generate scc
            if (lowlinkMap.get(node) == indexMap.get(node)) {
                Set<CNode> scc = Sets.newHashSet();
                CNode sn = null;
                do {
                    sn = stack.pop();
                    scc.add(sn);
                } while (sn != node);
                sccs.add(scc);
            }
        }
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
     *         found.
     */
    public static Pair<KVector, PortSide> getIntersectionPoint(final Line2D line,
            final Rectangle2D rectangle) {

        // get slope and x offset of the line
        double slope = (line.getY2() - line.getY1()) / (line.getX2() - line.getX1());
        double n = line.getY1() - (slope * line.getX1());

        // top
        double lineTopX = (rectangle.getMinY() - n) / slope;
        if (isWithinRange(lineTopX, line.getX1(), line.getX2())
                && isWithinRange(lineTopX, rectangle.getMinX(), rectangle.getMaxX())) {
            return Pair.of(new KVector(lineTopX, rectangle.getMinY()), PortSide.NORTH);
        }

        // right
        double lineRightY = (rectangle.getMaxX() * slope) + n;
        if (isWithinRange(lineRightY, line.getY1(), line.getY2())
                && isWithinRange(lineRightY, rectangle.getMinY(), rectangle.getMaxY())) {
            return Pair.of(new KVector(rectangle.getMaxX(), lineRightY), PortSide.EAST);
        }

        // bottom
        double lineBottomX = ((rectangle.getMaxY() - n) / slope);
        if (isWithinRange(lineBottomX, line.getX1(), line.getX2())
                && isWithinRange(lineBottomX, rectangle.getMinX(), rectangle.getMaxX())) {
            // intersection at bottom
            return Pair.of(new KVector(lineBottomX, rectangle.getMaxY()), PortSide.SOUTH);
        }

        // left
        double lineLeftY = (rectangle.getMinX() * slope) + n;
        if (isWithinRange(lineLeftY, line.getY1(), line.getY2())
                && isWithinRange(lineLeftY, rectangle.getMinY(), rectangle.getMaxY())) {
            return Pair.of(new KVector(rectangle.getMinX(), lineLeftY), PortSide.WEST);
        }

        return null;
    }
    
    /**
     * Checks if the value is between the two bounds. Assures that the bounds form a closed
     * interval, ie if necessary the passed bounds are swapped.
     */
    private static boolean isWithinRange(final double value, final double bound1, final double bound2) {
        if (bound1 > bound2) {
            return value > bound2 && value < bound1;
        } else {
            return value > bound1 && value < bound2;
        }
    }
    
}
