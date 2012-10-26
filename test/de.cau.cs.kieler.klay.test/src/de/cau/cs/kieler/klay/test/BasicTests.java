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

import static org.junit.Assert.*; // SUPPRESS CHECKSTYLE AvoidStarImport

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * A basic test class that tests if nodes overlaps and if nodes and edges overlap.
 * 
 * @author wah
 * @author msp
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
        TestPath[] testPaths = {
            new TestPath("random", false, true, TestPath.Type.KGRAPH)
        };
        return testPaths;
    }
    
    /**
     * Add all children and grandchildren contained in the given parent node to the list.
     * 
     * @param parentNode a parent node
     * @param nodeList the list of gathered nodes
     */
    private static List<KNode> gatherNodes(final KNode parentNode, final List<KNode> nodeList) {
        for (KNode child : parentNode.getChildren()) {
            nodeList.add(child);
            gatherNodes(child, nodeList);
        }
        return nodeList;
    }

    /**
     * A Junit method to test if two nodes overlaps.
     */
    @Test
    public void testNodesOverlaps() {
        // gather all nodes
        List<KNode> nodeList = gatherNodes(graphObject.getKnode(), new LinkedList<KNode>());
        
        ListIterator<KNode> nodeIter1 = nodeList.listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            KShapeLayout nodeLayout1 = node1.getData(KShapeLayout.class);
            ListIterator<KNode> nodeIter2 = nodeList.listIterator(nodeIter1.nextIndex());
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                if (!(KimlUtil.isDescendant(node1, node2) || KimlUtil.isDescendant(node2, node1))) {
                    KShapeLayout nodeLayout2 = node2.getData(KShapeLayout.class);
    
                    assertFalse(hasNodeToNodeOverlaps(nodeLayout1, nodeLayout2));
                }
            }
        }
    }

    /**
     * A Junit method to test if a node and edge overlaps.
     */
    @Test
    public void testNodeEdgeOverlaps() {
        // gather all nodes
        List<KNode> nodeList = gatherNodes(graphObject.getKnode(), new LinkedList<KNode>());
        
        ListIterator<KNode> nodeIter1 = nodeList.listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            ListIterator<KNode> nodeIter2 = nodeList.listIterator();
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                if (!(node1 == node2 || KimlUtil.isDescendant(node1, node2))) {
                    
                    assertFalse(hasNodeEdgeOverlaps(node1, node2));
                }
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
     * Check if there are overlaps between the second node and edges from the first node.
     * 
     * @param node1
     *            the first node
     * @param node2
     *            the second node
     * @return true if overlaps and false otherwise
     */
    private boolean hasNodeEdgeOverlaps(final KNode node1, final KNode node2) {
        KShapeLayout node2Layout = node2.getData(KShapeLayout.class);
        KVector node2Pos = node2Layout.createVector();
        KimlUtil.toAbsolute(node2Pos, node2.getParent());
        for (KEdge edge : node1.getOutgoingEdges()) {
            if (edge.getTarget() != node2) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                KVectorChain vectorChain = edgeLayout.createVectorChain();
                // approximate spline if required
                if (edgeLayout.getProperty(LayoutOptions.EDGE_ROUTING) == EdgeRouting.SPLINES) {
                    vectorChain = KielerMath.approximateSpline(vectorChain);
                }
                // transform to absolute coordinates
                KNode referenceNode = edge.getSource();
                if (!KimlUtil.isDescendant(edge.getTarget(), referenceNode)) {
                    referenceNode = referenceNode.getParent();
                }
                KVector offset = new KVector();
                KimlUtil.toAbsolute(offset, referenceNode);
                
                ListIterator<KVector> pointIter = vectorChain.listIterator();
                KVector p1 = pointIter.next().add(offset);
                while (pointIter.hasNext()) {
                    KVector p2 = pointIter.next().add(offset);
                    if (hasNodeEdgeIntersection(p1, p2, node2Pos, node2Layout.getWidth(),
                            node2Layout.getHeight())) {
                        return true;
                    }
                    p1 = p2;
                }
            }
        }
        return false;
    }

    /**
     * Returns whether the line segment intersects the nodes bounding box.
     * 
     * @param p1 the start point of the line segment
     * @param p2 the end point of the line segment
     * @param nodePos node position
     * @param width node width
     * @param height node height
     * @return true if the line segment intersects the nodes bounding box
     */
    private static boolean hasNodeEdgeIntersection(final KVector p1, final KVector p2,
            final KVector nodePos, final float width, final float height) {
        int p1OutCode = computeOutCode(p1, nodePos, width, height);
        int p2OutCode = computeOutCode(p2, nodePos, width, height);

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
            if ((outcode & LEFT) > 0) {
                return hasIntersection(p1, p2, nodePos.x, nodePos.y, nodePos.x, nodePos.y + height);
            } else if ((outcode & RIGHT) > 0) {
                return hasIntersection(p1, p2, nodePos.x + width, nodePos.y, nodePos.x + width,
                        nodePos.y + height);
            } else if ((outcode & TOP) > 0) {
                return hasIntersection(p1, p2, nodePos.x, nodePos.y, nodePos.x + width, nodePos.y);
            } else /* if ((p1OutCode & BOTTOM) > 0) */ {
                return hasIntersection(p1, p2, nodePos.x, nodePos.y + height, nodePos.x + width,
                        nodePos.y + height);
            }
        }
    }

    /**
     * Returns whether two line segments have an intersection.
     * 
     * @param p1 start point of the first line segment
     * @param p2 end point of the first line segement
     * @param x1 the x-coordinate of the start point of the second line segment
     * @param y1 the y-coordinate of the start point of the second line segment
     * @param x2 the x-coordinate of the end point of the second line segment
     * @param y2 the y-coordinate of the end point of the second line segment
     * @return true if the line segments intersect else false
     */
    private static boolean hasIntersection(final KVector p1, final KVector p2, final double x1,
            final double y1, final double x2, final double y2) {
        double s = (y2 - y1) * (p2.x - p1.x) - (x2 - x1) * (p2.y - p1.y);
        // are the line segments parallel?
        if (s == 0) {
            return false;
        }
        double a1 = (x2 - x1) * (p1.y - y1) - (y2 - y1) * (p1.x - x1);
        double a2 = (p2.x - p1.x) * (p1.y - y1) - (p2.y - p1.y) * (p1.x - x1);
        double t1 = a1 / s;
        double t2 = a2 / s;
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
     * @param point the point
     * @param nodePos node position
     * @param width node width
     * @param height node height
     * @return the outcode
     */
    private static int computeOutCode(final KVector point, final KVector nodePos, final float width,
            final float height) {
        int code = 0;
        if (point.y > nodePos.y + height) {
            code |= TOP;
        } else if (point.y < nodePos.y) {
            code |= BOTTOM;
        }
        if (point.x > nodePos.x + width) {
            code |= RIGHT;
        } else if (point.x < nodePos.x) {
            code |= LEFT;
        }
        return code;
    }

    /**
     * Computes the opposite outcode.
     * 
     * @param outcode the outcode
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
