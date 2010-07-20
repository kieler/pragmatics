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
package de.cau.cs.kieler.klay.layered.impl.edges;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.modules.IBoxCalculator;

/**
 * @author car
 * 
 */
public class ObjectBoxCalculator extends AbstractAlgorithm implements IBoxCalculator {

    /** Minimal spacing between objects. */
    private float spacing;

    /** All known Edges inside the current graph. */
    private LinkedList<Line2D.Double> allEdges;

    /** All known Nodes inside the current graph. */
    private LinkedList<Rectangle2D.Double> allNodes;

    /** All known dummy Nodes inside the current graph. */
    private LinkedList<Rectangle2D.Double> allDummyNodes;

    /** Map from any Spline/Edge to it's corresponding Lines. */
    private HashMap<Object, LinkedList<Line2D.Double>> edgeToLine;

    /** Map from any line to it's corresponding Spline/Edge. */
    private HashMap<Line2D.Double, Object> lineToEdge;

    /** The graph to work on. */
    private LayeredGraph layeredGraph;

    /** How many points to calculate when approximating a spline. */
    private static final int EDGE_PRECISION = 4;

    /** How big shall dummy nodes be drawn. */
    private static final float DUMMY_NODE_DEBUG_SIZE = 10;

    /** How big are the steps in which boxes are resized. */
    private static final int BOX_RESIZE_STEPSIZE = 3;

    /** Minimal width of an box. */
    private static final int MINIMAL_BOX_WIDTH = 5;

    /** Division factor to calculate box widths. */
    private static final int BOX_WIDTH_DIVISION_FACTOR = 2;

    /** The DebugCanvas to use for debug-drawings. **/
    private DebugCanvas debugCanvas;
    
    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final BezierSpline spline) {

        for (BezierCurve curve : spline.getCurves()) {
            KVector start = curve.start;

            // there's always the start point missing in pts
            int n = EDGE_PRECISION - 1;
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), n);
            Line2D.Double newEdge = new Line2D.Double(start.x, start.y, pts[0].x, pts[0].y);
            allEdges.add(newEdge);
            lineToEdge.put(newEdge, spline);
            LinkedList<Line2D.Double> list = new LinkedList<Line2D.Double>();
            list.add(newEdge);
            for (int i = 0; i < n - 1; i++) {
                newEdge = new Line2D.Double(pts[i].x, pts[i].y, pts[i + 1].x, pts[i + 1].y);
                allEdges.add(newEdge);
                lineToEdge.put(newEdge, spline);
                list.add(newEdge);
            }
            edgeToLine.put(spline, list);

        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final LEdge edge) {
        Line2D.Double newEdge = new Line2D.Double(edge.getSource().getNode().getPos().x
                + edge.getSource().getPos().x, edge.getSource().getNode().getPos().y
                + edge.getSource().getPos().y, edge.getTarget().getNode().getPos().x
                + edge.getTarget().getPos().x, edge.getTarget().getNode().getPos().y
                + edge.getTarget().getPos().y);
        allEdges.add(newEdge);
        lineToEdge.put(newEdge, edge);
        LinkedList<Line2D.Double> list = new LinkedList<Line2D.Double>();
        list.add(newEdge);
        edgeToLine.put(edge, list);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public LinkedList<Rectangle2D.Double> getBoxes(final LEdge edge) {

        LPort currentTarget = edge.getTarget();
        LPort currentSource = edge.getSource();
        int minBoxHeight = Math.max(MINIMAL_BOX_WIDTH, (int) spacing / BOX_WIDTH_DIVISION_FACTOR);

        LinkedList<Rectangle2D.Double> globBarray = new LinkedList<Rectangle2D.Double>();

        if (debugCanvas != null) {
            debugCanvas.clear();
        }

        int defaultboxwidth = (int) Math.max(2 + 2 + 1, (int) spacing / BOX_WIDTH_DIVISION_FACTOR);

        // where are we currently
        double reachedx = currentSource.getPos().x + currentSource.getNode().getPos().x;

        Rectangle2D.Double newBox = null, previousBox = null;

        LinkedList<Line2D.Double> ignoredEdges = new LinkedList<Line2D.Double>();
        LinkedList<Line2D.Double> edges = new LinkedList<Line2D.Double>();
        LinkedList<Rectangle2D.Double> nodes = new LinkedList<Rectangle2D.Double>();

        // wander along the edge
        do {
            double targetx = currentTarget.getPos().x + currentTarget.getNode().getPos().x;

            // only take care of edges that can hit us
            edges.clear();
            for (Line2D.Double oneEdge : allEdges) {
                if (!(oneEdge.getX2() < reachedx || oneEdge.getX1() > targetx)) {
                    edges.add(oneEdge);
                }
            }

            // and of course only take care of nods that can be in our way
            nodes.clear();
            for (Rectangle2D.Double oneNode : allNodes) {
                if (!(oneNode.getX() + oneNode.getWidth() < reachedx || oneNode.getX() > targetx)) {
                    nodes.add(oneNode);
                }
            }

            // clear ignored Edges for every new node->nextNode segment
            ignoredEdges.clear();

            // add all intersecting Lines between current source and target to ignore list
            addAllIntersectingLines(currentSource, currentTarget, edges, ignoredEdges);

            drawOnDebug(
                    new Line2D.Double(
                            (currentSource.getNode().getPos().x + currentSource.getPos().x),
                            (currentSource.getNode().getPos().y + currentSource.getPos().y),
                            (currentTarget.getNode().getPos().x + currentTarget.getPos().x),
                            (currentTarget.getNode().getPos().y + currentTarget.getPos().y)),
                    DebugCanvas.Color.YELLOW, false);

            if (currentTarget.getNode().getProperty(Properties.NODE_TYPE)
                    == Properties.NodeType.LONG_EDGE) {
                drawOnDebug(
                        new Ellipse2D.Double(
                                (currentTarget.getNode().getPos().x + currentTarget.getPos().x
                                        - DUMMY_NODE_DEBUG_SIZE / 2),
                                (currentTarget.getNode().getPos().y + currentTarget.getPos().y
                                        - DUMMY_NODE_DEBUG_SIZE / 2),
                                DUMMY_NODE_DEBUG_SIZE, DUMMY_NODE_DEBUG_SIZE),
                        DebugCanvas.Color.CYAN, true);

            }

            while (reachedx < targetx) {
                // when there won't fit two boxes anymore, enlarge current box to be the last one
                double newBoxWidth = (targetx - reachedx < 2 * defaultboxwidth) ? targetx
                        - reachedx : defaultboxwidth;

                // a good starting point is ON the direct line between source and target
                double newLowY = pointOnLine(currentSource, currentTarget, reachedx);
                double newHighY = pointOnLine(currentSource, currentTarget, reachedx + newBoxWidth);

                // box would be upside down? swap...
                if (newLowY > newHighY) {
                    double tmp = newLowY;
                    newLowY = newHighY;
                    newHighY = tmp;
                }

                // put box on center of direct line
                if (Math.abs(newHighY - newLowY) < minBoxHeight) {
                    double diff = (minBoxHeight - Math.abs(newHighY - newLowY)) / 2;
                    newLowY -= diff;
                    newHighY += diff;
                }

                double newBoxHeight = Math.max(minBoxHeight, newHighY - newLowY);

                // create the new box
                newBox = new Rectangle2D.Double(reachedx, newLowY, newBoxWidth, newBoxHeight);

                // do not start on a node!!
                Rectangle2D.Double onNode = intersectsWithNode(newBox, nodes);

                // if we are on a node, there has to be a previous box because initial boxsize is
                // smaller than object spacing
                if (onNode != null && previousBox != null) {
                    if (previousBox.y < newBox.y) {
                        newBox = floorBox(newBox, onNode);
                        newBox.y -= newBoxHeight; // reserve space for the other box that comes
                    } else if (previousBox.y > newBox.y) {
                        newBox = ceilBox(newBox, onNode);
                    }
                }

                // show our initial position
                drawOnDebug(newBox.clone(), DebugCanvas.Color.GREEN, true);

                // remember on which lines we were starting... maybe have to cross them
                for (Line2D.Double intLine : allIntersectingLines(newBox, edges)) {
                    Object key = lineToEdge.get(intLine);
                    LinkedList<Line2D.Double> list = edgeToLine.get(key);
                    for (Line2D.Double line : list) {
                        ignoredEdges.add(line);
                    }
                }

                // find an lower boundary
                double lowerBound = 0;
                for (Rectangle2D.Double dummyNode : allDummyNodes) {
                    if (dummyNode.x >= newBox.getX()
                            && dummyNode.x < newBox.getX() + newBox.getWidth()) {
                        if (dummyNode.y > lowerBound && dummyNode.y < newBox.getY()) {
                            lowerBound = dummyNode.y;
                        }
                    }
                }

                // enlarge boxes from bottom to top
                Object runagainst = intersectsWithAny(newBox, ignoredEdges, edges, nodes);
                while (newBox.y > lowerBound && runagainst == null) {
                    newBox.y -= BOX_RESIZE_STEPSIZE;
                    newBox.height += BOX_RESIZE_STEPSIZE;
                    runagainst = intersectsWithAny(newBox, ignoredEdges, edges, nodes);
                }

                if (runagainst != null) { // there is a node/edge above the box
                    drawOnDebug(runagainst, DebugCanvas.Color.RED, true); // show us the bad boy
                    if (runagainst instanceof Rectangle2D) {
                        newBox = ceilBox(newBox, (Rectangle2D.Double) runagainst);
                    } else { // run against a another edge
                        newBox.y += 2 * BOX_RESIZE_STEPSIZE;
                        newBox.height -= 2 * BOX_RESIZE_STEPSIZE;
                    }
                } else {
                    newBox.height += newBox.y;
                    newBox.y = lowerBound;
                }

                drawOnDebug(newBox.clone(), DebugCanvas.Color.GRAY, false);

                // remember on which lines we were starting
                ignoredEdges.addAll(allIntersectingLines(newBox, edges));

                // find an upper boundary
                double upperBound = layeredGraph.getSize().y;
                for (Rectangle2D.Double dummyNode : allDummyNodes) {
                    if (dummyNode.x >= newBox.getX()
                            && dummyNode.x < newBox.getX() + newBox.getWidth()) {
                        if (dummyNode.y < upperBound
                                && dummyNode.y > newBox.getY() + newBox.getHeight()) {
                            upperBound = dummyNode.y;
                        }
                    }
                }

                // enlarge boxes from bottom to top
                runagainst = intersectsWithAny(newBox, ignoredEdges, edges, nodes);
                while (newBox.y + newBox.height < upperBound && runagainst == null) {
                    newBox.height += BOX_RESIZE_STEPSIZE;
                    runagainst = intersectsWithAny(newBox, ignoredEdges, edges, nodes);
                }

                if (runagainst != null) {
                    // show bad boy
                    drawOnDebug(runagainst, DebugCanvas.Color.RED, true);
                    if (runagainst instanceof Rectangle2D) {
                        newBox = floorBox(newBox, (Rectangle2D.Double) runagainst);
                    } else { // run against a another edge
                        // one step back
                        newBox.height -= 2 * BOX_RESIZE_STEPSIZE;
                    }
                } else {
                    newBox.height = upperBound - newBox.y;
                }

                drawOnDebug(newBox, DebugCanvas.Color.ORANGE, false);

                // ensure that the new box is intersecting the previous box
                if (previousBox != null) {
                    // the old box is "below" the new Box
                    if (previousBox.y >= newBox.y + newBox.height) {
                        newBox.height = previousBox.y + minBoxHeight * 2 - newBox.y;
                    }

                    // or "over" the new box
                    if (previousBox.y + previousBox.height <= newBox.y) {
                        double diff = newBox.y
                                - ((previousBox.y + previousBox.height) - minBoxHeight * 2);
                        newBox.y -= diff;
                        newBox.height += diff;
                    }
                }

                // the first box has to cover the complete first node
                if (globBarray.isEmpty()) {
                    if (newBox.y > currentSource.getNode().getPos().y) {
                        double diff = newBox.y - currentSource.getNode().getPos().y;
                        newBox.y -= diff;
                        newBox.height += diff;
                    }
                    newBox.height = Math.max(newBox.height, currentSource.getNode().getSize().y);
                }

                // the last box has to cover the last node
                if (reachedx + newBox.width > targetx - 1) {
                    if (newBox.y > currentTarget.getNode().getPos().y) {
                        double diff = newBox.y - currentTarget.getNode().getPos().y;
                        newBox.y -= diff;
                        newBox.height += diff;
                    }
                    newBox.height = Math.max(newBox.height, currentTarget.getNode().getSize().y);
                }

                // add the new box
                globBarray.add(newBox);
                previousBox = newBox;
                reachedx += newBox.width;

                // and draw the new box
                drawOnDebug(newBox, DebugCanvas.Color.BLUE, false);
            }

            currentSource = currentTarget;
            for (LPort iterPort : currentTarget.getNode().getPorts(PortType.OUTPUT)) {
                for (LEdge iterEdge : iterPort.getEdges()) {
                    if (iterEdge.getProperty(Properties.ORIGIN) != null) {
                        currentTarget = iterEdge.getTarget();
                        break;
                    }
                }
                break;
            }

        } while (currentSource.getNode().getProperty(Properties.NODE_TYPE)
                == Properties.NodeType.LONG_EDGE);
        if (debugCanvas != null) {
            debugCanvas.drawBuffer();
        }
        return globBarray;
    }

    /**
     * take a given box and snap it to a grid with BOX_RESIZE_STEPSIZE stepsize.
     * 
     * @param box
     * @param node
     * @return the resized box.
     */
    private static Rectangle2D.Double ceilBox(final Rectangle2D.Double box,
            final Rectangle2D.Double node) {
        double oldy = box.y;

        box.y = node.y + node.height + BOX_RESIZE_STEPSIZE;
        box.y = (Math.ceil(box.y / BOX_RESIZE_STEPSIZE) + 1) * BOX_RESIZE_STEPSIZE;
        box.height -= (box.y - oldy);

        return box;
    }

    /**
     * take a given box and snap it to a grid with BOX_RESIZE_STEPSIZE stepsize.
     * 
     * @param box
     * @param node
     * @return the resized box.
     */
    private static Rectangle2D.Double floorBox(final Rectangle2D.Double box,
            final Rectangle2D.Double node) {
        box.height = (node.y - BOX_RESIZE_STEPSIZE) - box.y;
        double newd = (Math.floor((box.y + box.height) / BOX_RESIZE_STEPSIZE) - 1)
                * BOX_RESIZE_STEPSIZE;
        box.height = (newd - box.y);

        return box;
    }

    /**
     * Draw an Object on the debugCanvas.
     * 
     * @param o
     *            the object to draw
     * @param c
     *            the color to use
     * @param fill
     *            draw if filled or not
     * @return true if it could be painted
     */
    private boolean drawOnDebug(final Object o, final DebugCanvas.Color c, final boolean fill) {
        if (debugCanvas != null) {
            if (o instanceof Rectangle2D) {
                Rectangle2D rec = (Rectangle2D) o;
                if (fill) {
                    debugCanvas.drawFilledRectangle((float) rec.getX(), (float) rec.getY(),
                            (float) rec.getWidth(), (float) rec.getHeight(), c);
                } else {
                    debugCanvas.drawRectangle((float) rec.getX(), (float) rec.getY(),
                            (float) rec.getWidth(), (float) rec.getHeight(), c);
                }
                return true;
            } else if (o instanceof Line2D) {
                Line2D line = (Line2D) o;
                debugCanvas.drawLine((float) line.getX1(), (float) line.getY1(),
                        (float) line.getX2(), (float) line.getY2(), c);
                return true;
            } else if (o instanceof Ellipse2D) {
                Ellipse2D ellipse = (Ellipse2D) o;
                if (fill) {
                    debugCanvas.drawFilledEllipse((float) ellipse.getX(), (float) ellipse.getY(),
                            (float) ellipse.getWidth(), (float) ellipse.getHeight(), c);
                } else {
                    debugCanvas.drawEllipse((float) ellipse.getX(), (float) ellipse.getY(),
                            (float) ellipse.getWidth(), (float) ellipse.getHeight(), c);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public LinkedList<Line2D.Double> getLines(final LinkedList<Rectangle2D.Double> boxes) {
        LinkedList<Line2D.Double> larray = new LinkedList<Line2D.Double>();

        if (boxes.size() == 0) {
            return larray;
        }

        // remember first box, then iterate over all boxes and compute the corresponding lines
        Rectangle2D.Double oldBox = boxes.getFirst();
        for (Rectangle2D.Double box : boxes) {
            if (!box.equals(boxes.getFirst())) {
                if (oldBox != null && box != null) {
                    Rectangle2D intersectBox = box.createIntersection(oldBox);
                    Line2D.Double line = new Line2D.Double(intersectBox.getX(),
                            intersectBox.getY(), intersectBox.getX(), intersectBox.getY()
                                    + intersectBox.getHeight());
                    larray.add(line);
                    drawOnDebug(line, DebugCanvas.Color.GREEN, false);
                }
            }

            oldBox = box;
        }
        if (debugCanvas != null) {
            debugCanvas.drawBuffer();
        }

        return larray;
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(final LayeredGraph graph, final DebugCanvas dc) {
        this.debugCanvas = dc;
        initialize(graph);
    }

    /**
     * {@inheritDoc}
     */
    public void addNode(final LNode node) {
        allNodes.add(new Rectangle2D.Double(node.getPos().x, node.getPos().y, node.getSize().x,
                node.getSize().y));
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(final LayeredGraph lg) {
        spacing = lg.getProperty(Properties.OBJ_SPACING);
        layeredGraph = lg;
        allEdges = new LinkedList<Line2D.Double>();
        allNodes = new LinkedList<Rectangle2D.Double>();
        allDummyNodes = new LinkedList<Rectangle2D.Double>();
        edgeToLine = new HashMap<Object, LinkedList<Line2D.Double>>();
        lineToEdge = new HashMap<Line2D.Double, Object>();

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                // filter out start points of long edges
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    // for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    // for (LEdge edge : port.getEdges()) {
                    // addEdge(edge);
                    // }
                    // }
                    addNode(node);
                } else {
                    allDummyNodes.add(new Rectangle2D.Double(node.getPos().x, node.getPos().y, node
                            .getSize().x, node.getSize().y));
                    if (node.getPos().y > layeredGraph.getSize().y) {
                        layeredGraph.getSize().y = node.getPos().y;
                    }
                }
            }
        }

    }

    /**
     * check for any intersection object.
     * 
     * @param box
     *            the box to check
     * @param currently
     *            ignore a given object that intersects on startup
     * @param edges
     *            list of edges
     * @param nodes
     *            list of nodes
     * @return the object which intersects
     */
    private static Object intersectsWithAny(final Rectangle2D.Double box,
            final LinkedList<Line2D.Double> ignoredEdges, final LinkedList<Line2D.Double> edges,
            final LinkedList<Rectangle2D.Double> nodes) {
        for (Rectangle2D.Double node : nodes) {
            if (node.intersects(box)) {
                return node;
            }
        }
        for (Line2D.Double line : edges) {
            if (!ignoredEdges.contains(line) && line.intersects(box)) {

                return line;
            }
        }
        return null;
    }

    /**
     * compute all intersecting edges for a box.
     * 
     * @param box
     *            the box
     * @param edges
     *            the edges to check
     * @return a list with all intersecting edges
     */
    private static LinkedList<Line2D.Double> allIntersectingLines(final Rectangle2D.Double box,
            final LinkedList<Line2D.Double> edges) {
        LinkedList<Line2D.Double> ret = new LinkedList<Line2D.Double>();
        for (Line2D.Double line : edges) {
            if (line.intersects(box)) {
                ret.add(line);
            }
        }
        return ret;
    }

    /**
     * compute all intersecting edges for a line.
     * 
     * @param crossline
     *            the line to check
     * @param edges
     *            all edges
     * @return a list containing all edges that cross
     */
    private static LinkedList<Line2D.Double> allIntersectingLines(final Line2D.Double crossline,
            final LinkedList<Line2D.Double> edges) {
        LinkedList<Line2D.Double> ret = new LinkedList<Line2D.Double>();
        for (Line2D.Double line : edges) {
            if (line.intersectsLine(crossline)) {
                ret.add(line);
            }
        }
        return ret;
    }

    /**
     * checks if a box intersects with a node.
     * 
     * @param box
     *            the box to check
     * @param nodes
     *            a list with all nodes
     * @return the intersecting node, null if none
     */
    private static Rectangle2D.Double intersectsWithNode(final Rectangle2D.Double box,
            final LinkedList<Rectangle2D.Double> nodes) {
        for (Rectangle2D.Double node : nodes) {
            if (node.intersects(box)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Calculate the coordinate on a line between two nodes.
     * 
     * @param src
     *            the source-node
     * @param dst
     *            the destination-node
     * @param x
     *            the position you want to know the y-coordinate for
     * @return the point on the line at position x
     */
    private static double pointOnLine(final LPort src, final LPort dst, final double x) {
        // this is basically the line-equation for this direct path between source and target Ports
        double starty = (src.getPos().y + src.getNode().getPos().y)
                + (((dst.getPos().y + dst.getNode().getPos().y) - (src.getPos().y + src.getNode()
                        .getPos().y)) * (x - (src.getPos().x + src.getNode().getPos().x)))
                / ((dst.getPos().x + dst.getNode().getPos().x) - (src.getPos().x + src.getNode()
                        .getPos().x));
        return starty;
    }

    /**
     * Add all intersecting lines on path between source and target to the ignored list.
     */
    private void addAllIntersectingLines(final LPort currentSource, final LPort currentTarget,
            final LinkedList<Line2D.Double> edges, final LinkedList<Line2D.Double> ignoredEdges) {
        for (Line2D.Double intLine : allIntersectingLines(new Line2D.Double((currentSource
                .getNode().getPos().x + currentSource.getPos().x), (currentSource.getNode()
                .getPos().y + currentSource.getPos().y),
                (currentTarget.getNode().getPos().x + currentTarget.getPos().x), (currentTarget
                        .getNode().getPos().y + currentTarget.getPos().y)), edges)) {
            Object key = lineToEdge.get(intLine);
            LinkedList<Line2D.Double> list = edgeToLine.get(key);
            for (Line2D.Double line : list) {
                ignoredEdges.add(line);
            }
        }

    }

}
