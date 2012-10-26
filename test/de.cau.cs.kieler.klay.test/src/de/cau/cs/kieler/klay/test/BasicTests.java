/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test;

import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Test;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * A basic test class that tests if nodes overlaps and if nodes and edges overlap.
 * 
 * @author wah
 */
public class BasicTests extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject that contains both, a File and a KNode.
    private GraphTestObject graphObject;

    /**
     * Instantiates a new KlayTestExample test and set the graphObject to the current graph to test.
     * 
     * @param testObject the test object
     */
    public BasicTests(final GraphTestObject testObject) {
        graphObject = testObject;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath("keg/testtest", false, true) };
        return testPaths;
    }

    /**
     * A Junit Method to test if two nodes overlaps.
     */
    @Test
    public void testNodesOverlaps() {
        // get the Knode from the current graph object
        KNode parentNode = graphObject.getKnode();
        ListIterator<KNode> nodeIter1 = parentNode.getChildren().listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            KShapeLayout nodeLayout1 = node1.getData(KShapeLayout.class);
            ListIterator<KNode> nodeIter2 = parentNode.getChildren().listIterator(
                    nodeIter1.nextIndex());
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                KShapeLayout nodeLayout2 = node2.getData(KShapeLayout.class);

                assertTrue(!hasNodeToNodeOverlaps(nodeLayout1, nodeLayout2));
            }
        }
    }

    /**
     * A Junit Method to test if a node and Edge overlaps.
     */
    @Test
    public void testNodeEdgeOverlaps() {
        KNode parentNode = graphObject.getKnode();
        ListIterator<KNode> nodeIter1 = parentNode.getChildren().listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            ListIterator<KNode> nodeIter2 = parentNode.getChildren().listIterator(
                    nodeIter1.nextIndex());
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                assertTrue(!hasNodeEdgeOverlaps(node1, node2));
            }
        }
    }

    /**
     * Returns whether two KShapeLayouts have an intersection.
     * 
     * @param layout1
     *            the KShapeLayout of the first Node
     * @param layout2
     *            the KShapeLayout of the first Node
     * @return true if the nodes overlaps and false otherwise
     */
    private static boolean hasNodeToNodeOverlaps(final KShapeLayout layout1,
            final KShapeLayout layout2) {
        float x1 = Math.max(layout1.getXpos(), layout2.getXpos());
        float x2 = Math.min(layout1.getXpos() + layout1.getWidth(),
                layout2.getXpos() + layout2.getWidth());
        float y1 = Math.max(layout1.getYpos(), layout2.getYpos());
        float y2 = Math.min(layout1.getYpos() + layout1.getHeight(),
                layout2.getYpos() + layout2.getHeight());
        return (x2 >= x1 && y2 >= y1);
    }

    /**
     * Check if there is overlaps between the second node and edges from the first node.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return true if overlaps and false otherwise
     */
    private boolean hasNodeEdgeOverlaps(final KNode node1, final KNode node2) {
        if (node1 == node2) {
            return false;
        }
        for (KEdge edge : node1.getOutgoingEdges()) {
            if (edge.getTarget() == node2) {
                continue;
            }
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            KPoint p1 = edgeLayout.getSourcePoint();
            for (KPoint p2 : edgeLayout.getBendPoints()) {
                if (hasNodeEdgeIntersection(p1, p2, node2)) {
                    return true;
                }
                p1 = p2;
            }
            KPoint p2 = edgeLayout.getTargetPoint();
            if (hasNodeEdgeIntersection(p1, p2, node2)) {
                return true;
            }
        }
        return false;
    }

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
    private static boolean hasNodeEdgeIntersection(final KPoint p1, final KPoint p2,
            final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        int p1OutCode = computeOutCode(p1, node);
        int p2OutCode = computeOutCode(p2, node);

        if (p1OutCode == 0 || p2OutCode == 0) {
            // the line segment has start- or endpoint inside the rectangle
            return true;
        } else if ((p1OutCode & p2OutCode) > 0) {
            // the line segment lies on one side of the rectangle
            return false;
        } else if (p1OutCode == computeOppositeOutCode(p2OutCode)) {
            // the start- and endpoint of the line segment lie on opposite sides
            return true;
        } else {
            int outcode = p1OutCode != TOP && p1OutCode > BOTTOM ? p2OutCode : p1OutCode;
            float xpos = nodeLayout.getXpos();
            float ypos = nodeLayout.getYpos();
            float width = nodeLayout.getWidth();
            float height = nodeLayout.getHeight();
            if ((outcode & LEFT) > 0) {
                return hasIntersection(p1, p2, xpos, ypos, xpos, ypos + height);
            } else if ((outcode & RIGHT) > 0) {
                return hasIntersection(p1, p2, xpos + width, ypos, xpos + width, ypos + height);
            } else if ((outcode & TOP) > 0) {
                return hasIntersection(p1, p2, xpos, ypos, xpos + width, ypos);
            } else /* if ((p1OutCode & BOTTOM) > 0) */ {
                return hasIntersection(p1, p2, xpos, ypos + height, xpos + width, ypos + height);
            }
        }
    }

    /**
     * Returns whether two line segments have an intersection.
     * 
     * @param p1
     *            start point of the first line segment
     * @param p2
     *            end point of the first line segement
     * @param x1
     *            the x-coordinate of the start point of the second line segment
     * @param y1
     *            the y-coordinate of the start point of the second line segment
     * @param x2
     *            the x-coordinate of the end point of the second line segment
     * @param y2
     *            the y-coordinate of the end point of the second line segment
     * @return true if the line segments intersect else false
     */
    private static boolean hasIntersection(final KPoint p1, final KPoint p2, final float x1,
            final float y1, final float x2, final float y2) {
        float s = (y2 - y1) * (p2.getX() - p1.getX()) - (x2 - x1) * (p2.getY() - p1.getY());
        // are the line segments parallel?
        if (s == 0) {
            return false;
        }
        float a1 = (x2 - x1) * (p1.getY() - y1) - (y2 - y1) * (p1.getX() - x1);
        float a2 = (p2.getX() - p1.getX()) * (p1.getY() - y1) - (p2.getY() - p1.getY())
                * (p1.getX() - x1);
        float t1 = a1 / s;
        float t2 = a2 / s;
        // the line segments intersect when t1 and t2 lie in the interval (0,1)
        return 0.0f < t1 && t1 < 1 && 0 < t2 && t2 < 1;
    }

    /** outcode constants. */
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    /**
     * Compute the outcode for the point and rectangle that is the nodes bounding box.<br>
     * The outcode belongs to the Cohen-Sutherland algorithm and is shown in the following
     * illustration:<br>
     * <br>
     * <code>
     * 1001|1000|1010<br>
     * ----|----|----<br>
     * 0001|0000|0010<br>
     * ----|----|----<br>
     * 0101|0100|0110<br>
     * </code> <br>
     * The box around the 0000 entry represents the rectangle.
     * 
     * @param point
     *            the point
     * @param node
     *            the node
     * @return the outcode
     */
    private static int computeOutCode(final KPoint point, final KNode node) {
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        int code = 0;
        if (point.getY() > nodeLayout.getYpos() + nodeLayout.getHeight()) {
            code |= TOP;
        } else if (point.getY() < nodeLayout.getYpos()) {
            code |= BOTTOM;
        }
        if (point.getX() > nodeLayout.getXpos() + nodeLayout.getWidth()) {
            code |= RIGHT;
        } else if (point.getX() < nodeLayout.getXpos()) {
            code |= LEFT;
        }
        return code;
    }

    /**
     * Computes the opposite outcode.
     * 
     * @param outcode
     *            the outcode
     * @return the opposite outcode
     */
    private static int computeOppositeOutCode(final int outcode) {
        int oppOutcode = 0;
        if ((outcode & LEFT) > 0) {
            oppOutcode |= RIGHT;
        } else if ((outcode & RIGHT) > 0) {
            oppOutcode |= LEFT;
        }
        if ((outcode & TOP) > 0) {
            oppOutcode |= BOTTOM;
        } else if ((outcode & BOTTOM) > 0) {
            oppOutcode |= TOP;
        }
        return oppOutcode;
    }
    
}
