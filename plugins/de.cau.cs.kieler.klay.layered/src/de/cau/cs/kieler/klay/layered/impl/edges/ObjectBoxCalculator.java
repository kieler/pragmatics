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
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
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

    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;

    // contains nodes from which long edges are starting
    private LinkedList<Line2D.Double> allEdges;

    private LinkedList<Rectangle2D.Double> allNodes;

    private LayeredGraph layeredGraph;

    private static final int EDGE_PRECISION = 4;

    private static final float DUMMY_NODE_DEBUG_SIZE = 10;

    private static final int BOX_RESIZE_STEPSIZE = 3;

    /** the DebugCanvas to use for debug-drawings. **/
    private DebugCanvas debugCanvas;

    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final BezierSpline spline) {

        for (BezierCurve curve : spline.getCurves()) {
            KVector start = curve.start;

            // the EDGE_PRECISION defines how many points are caught ... the more .. the more
            // precise
            // there's always the start point missing in pts
            int n = EDGE_PRECISION - 1;
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), n);

            allEdges.add(new Line2D.Double(start.x, start.y, pts[0].x, pts[0].y));
            for (int i = 0; i < n - 1; i++) {
                allEdges.add(new Line2D.Double(pts[i].x, pts[i].y, pts[i + 1].x, pts[i + 1].y));
            }

        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addEdge(final LEdge edge) {
        allEdges.add(new Line2D.Double(edge.getSource().getNode().getPos().x
                + edge.getSource().getPos().x, edge.getSource().getNode().getPos().y
                + edge.getSource().getPos().y, edge.getTarget().getNode().getPos().x
                + edge.getTarget().getPos().x, edge.getTarget().getNode().getPos().y
                + edge.getTarget().getPos().y));
        return true;
    }

    /**
     * * check for any intersection object.
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
    private static Object intersectsWithAny(final Rectangle2D box, final Object currently,
            final LinkedList<Line2D.Double> edges, final LinkedList<Rectangle2D.Double> nodes) {
        for (Rectangle2D.Double node : nodes) {
            if (node != currently && node.intersects(box)) {
                return node;
            }
        }
        for (Line2D.Double line : edges) {
            if (line != currently && line.intersects(box)) {
                return line;
            }
        }
        return null;
    }

    /**
     * Calculate the coordinate on a line between two nodes
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
     * {@inheritDoc}
     */
    public LinkedList<Rectangle2D.Double> getBoxes(final LEdge edge) {

        LPort currentTarget = edge.getTarget();
        LPort currentSource = edge.getSource();
        int minBoxHeight = Math.max(2 + 2 + 1, (int) spacing / (2 + 2 + 1));

        LinkedList<Rectangle2D.Double> globBarray = new LinkedList<Rectangle2D.Double>();

        if (debugCanvas != null) {
            debugCanvas.clear();
        }

        int defaultboxwidth = (int) Math.max(2 + 2 + 1, (int) spacing / ((2 + 2 + 1) * 2));

        // where are we currently
        float reachedx = (float) currentSource.getPos().x + currentSource.getNode().getPos().x;


        // remember against which Nodes we we running in last iteration to NOT cross them
        Rectangle2D.Double runAgainstNodeTop = null;
        Rectangle2D.Double runAgainstNodeBottom = null;
        
        // wander along the edge
        do {
            Rectangle2D.Double newBox = null, prevBox = null;
            float targetx = (float) currentTarget.getPos().x + currentTarget.getNode().getPos().x;

            // only take care of edges that can hit us
            LinkedList<Line2D.Double> edges = new LinkedList<Line2D.Double>();
            for (Line2D.Double oneEdge : allEdges) {
                if (!(oneEdge.getX1() < reachedx) && !(oneEdge.getX2() > targetx)) {
                    edges.add(oneEdge);
                }
            }

            // and of course only take care of nods that can be in our way
            LinkedList<Rectangle2D.Double> nodes = new LinkedList<Rectangle2D.Double>();
            for (Rectangle2D.Double oneNode : allNodes) {
                if (!(oneNode.getX() < reachedx)
                        && !(oneNode.getX() + oneNode.getWidth() > targetx)) {
                    nodes.add(oneNode);
                }
            }


            while (reachedx < targetx) {

                // when there won't fit two boxes anymore, enlarge current box to be the last one
                double newBoxWidth = (targetx - reachedx < 2 * defaultboxwidth) ? targetx
                        - reachedx : defaultboxwidth;

                // a good starting point for the boxes is ON the direct line between source and
                // target
                double starty = pointOnLine(currentSource, currentTarget, reachedx);

                double endy = pointOnLine(currentSource, currentTarget, reachedx + newBoxWidth);

                // box would be upside down? swap...
                if (starty > endy) {
                    double tmp = starty;
                    starty = endy;
                    endy = tmp;
                }

                // put box above center of direct line
                if (Math.abs(endy - starty) < minBoxHeight) {
                    double diff = (minBoxHeight - Math.abs(endy - starty)) / 2;
                    starty -= diff;
                    endy += diff;
                }

                double newBoxHeight = Math.max(minBoxHeight, endy - starty);

                newBox = new Rectangle2D.Double(reachedx, starty - (endy - starty), newBoxWidth,
                        newBoxHeight);
                

                // enlarge two boxes independently from each other, one to the top, one to the bottom
                Rectangle2D.Double tempBox = new Rectangle2D.Double(reachedx, Math.max(0, newBox.y
                        + newBox.height), newBoxWidth, newBoxHeight);
                

                // due to a far from perfect dummy-node placement it might happen that we cross
                // nodes, to prevent this we move new boxes over/below the nodes we ran against the
                // last iteration
                if (runAgainstNodeBottom != null
                        && newBox.y <= runAgainstNodeBottom.y + runAgainstNodeBottom.height) {
                    
                    // there is a box below us, so go further to the top (y--)
                    newBox.y = runAgainstNodeBottom.y - (newBox.height + BOX_RESIZE_STEPSIZE);
                    tempBox.y = newBox.y + newBox.height;
                    drawOnDebug(new Line2D.Double(0, 0, newBox.x, newBox.x), DebugCanvas.Color.RED, false);
                    drawOnDebug(new Line2D.Double(newBox.x, 0, newBox.x, newBox.x), DebugCanvas.Color.RED, false);
                    /*
                     * drawOnDebug(new Rectangle2D.Double(runAgainstNodeBottom.x - 2,
                     * runAgainstNodeBottom.y - 2, runAgainstNodeBottom.width + 4,
                     * runAgainstNodeBottom.height + 4), DebugCanvas.Color.GREEN, true);
                     */
                    runAgainstNodeBottom = null;
                } else if (runAgainstNodeTop != null && newBox.y >= runAgainstNodeTop.y) {
                    
                    // there is a box over us, so go further to the bottom (y++)
                    newBox.y = runAgainstNodeTop.y + runAgainstNodeTop.height + BOX_RESIZE_STEPSIZE;
                    tempBox.y = newBox.y + newBox.height; // the 2nd box is always below the first one
                    /*
                     * drawOnDebug(new Rectangle2D.Double(runAgainstNodeTop.x - 2,
                     * runAgainstNodeTop.y - 2, runAgainstNodeTop.width + 4,
                     * runAgainstNodeTop.height + 4), DebugCanvas.Color.YELLOW, true);
                     */
                    runAgainstNodeTop = null;
                }

                drawOnDebug(newBox.clone(), DebugCanvas.Color.GREEN, true);                
                drawOnDebug(tempBox.clone(), DebugCanvas.Color.ORANGE, true);

                // show initial position
                // drawOnDebug(newBox, DebugCanvas.Color.ORANGE, false);
                // drawOnDebug(tempBox, DebugCanvas.Color.RED, false);

                // stuff we intersect with right from the start is ignored to enlarge the boxes
                // the path we follow will (hopefully) cause the next boxes to fit better
                Object previntersects = intersectsWithAny(newBox, null, edges, nodes);
                if (previntersects instanceof Rectangle2D) { // any line we cross at startup, we
                    // propably should be crossing, but no nodes
                    previntersects = null;
                }

                // enlarge boxes from bottom to top
                while (newBox.y > 0
                        && intersectsWithAny(newBox, previntersects, edges, nodes) == null) {
                    newBox.y -= BOX_RESIZE_STEPSIZE;
                    newBox.height += BOX_RESIZE_STEPSIZE;
                }

                // we got one step too far
                // node/edge above box
                Object runagainst = intersectsWithAny(newBox, null, edges, nodes);
                if (runagainst != null) {
                    // show us the bad boy we ran into
                    drawOnDebug(runagainst, DebugCanvas.Color.RED, true);
                    if (runagainst instanceof Rectangle2D) {
                        Rectangle2D.Double hitbox = (Rectangle2D.Double) runagainst;
                        double oldy = newBox.y;

                        newBox.y = hitbox.y + hitbox.height + BOX_RESIZE_STEPSIZE;
                        newBox.y = (Math.ceil(newBox.y / BOX_RESIZE_STEPSIZE) + 1)
                                * BOX_RESIZE_STEPSIZE;
                        newBox.height -= (newBox.y - oldy);

                        runAgainstNodeBottom = hitbox;
                    } else { // run against a another edge
                        // one step back
                        newBox.y += 2 * BOX_RESIZE_STEPSIZE;
                        newBox.height -= 2 * BOX_RESIZE_STEPSIZE;

                        // we ran against a line? go one step further
                        // newBox.y -= BOX_RESIZE_STEPSIZE;
                        // newBox.height += BOX_RESIZE_STEPSIZE;
                        runAgainstNodeBottom = null;
                    }
                }

                // from top downwards to bottom
                previntersects = intersectsWithAny(tempBox, null, edges, nodes);
                if (previntersects instanceof Rectangle2D) { // any line we cross at startup, we
                    // propably should be crossing
                    previntersects = null;
                }
                while (tempBox.y + tempBox.height < layeredGraph.getSize().y
                        && intersectsWithAny(tempBox, previntersects, edges, nodes) == null) {
                    tempBox.height += BOX_RESIZE_STEPSIZE;
                }

                // we got one step too far again
                runagainst = intersectsWithAny(tempBox, null, edges, nodes);
                if (runagainst != null) {
                    // show bad box
                    drawOnDebug(runagainst, DebugCanvas.Color.RED, true);
                    if (runagainst instanceof Rectangle2D) {
                        /*
                         * newBox.height -= 10; newBox.height = Math.ceil(newBox.height);
                         */
                        Rectangle2D.Double hitbox = (Rectangle2D.Double) runagainst;
                        // System.out.println(tempBox.height);
                        tempBox.height = (hitbox.y - BOX_RESIZE_STEPSIZE) - tempBox.y;
                        double newd = (Math.floor((tempBox.y + tempBox.height)
                                / BOX_RESIZE_STEPSIZE) - 1)
                                * BOX_RESIZE_STEPSIZE;
                        tempBox.height = (newd - tempBox.y);

                        runAgainstNodeTop = hitbox;
                    } else { // run against a another edge
                        // one step back
                        newBox.height -= 2 * BOX_RESIZE_STEPSIZE;

                        // we ran against a line? go one step further
                        // tempBox.height += BOX_RESIZE_STEPSIZE;
                        runAgainstNodeTop = null;
                    }
                }

                drawOnDebug(tempBox, DebugCanvas.Color.GRAY, false);

                // the actual new box shall be the size of BOTH boxes joint
                newBox.y = Math.min(newBox.y, tempBox.y);
                newBox.height = Math.max(newBox.height, Math.abs(tempBox.y + tempBox.height
                        - newBox.y));

                // ensure that the new box is intersecting the previous box
                if (prevBox != null) {
                    // the old box is "below" the new Box
                    if (prevBox.y >= newBox.y + newBox.height) {
                        newBox.height = prevBox.y + minBoxHeight * 2 - newBox.y;
                    }

                    // or "over" the new box
                    if (prevBox.y + prevBox.height <= newBox.y) {
                        double diff = newBox.y - ((prevBox.y + prevBox.height) - minBoxHeight * 2);
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
                prevBox = newBox;
                reachedx += newBox.width;

                // and draw the new box
                drawOnDebug(newBox, DebugCanvas.Color.BLUE, false);
            }

            drawOnDebug(new Line2D.Double((currentSource.getNode().getPos().x + currentSource
                    .getPos().x), (currentSource.getNode().getPos().y + currentSource.getPos().y),
                    (currentTarget.getNode().getPos().x + currentTarget.getPos().x), (currentTarget
                            .getNode().getPos().y + currentTarget.getPos().y)),
                    DebugCanvas.Color.YELLOW, false);

            drawOnDebug(new Ellipse2D.Float((currentTarget.getNode().getPos().x + currentTarget
                    .getPos().x), (currentTarget.getNode().getPos().y + currentTarget.getPos().y),
                    DUMMY_NODE_DEBUG_SIZE, DUMMY_NODE_DEBUG_SIZE), DebugCanvas.Color.CYAN, true);

            currentSource = currentTarget;
            for (LPort iterPort : currentTarget.getNode().getPorts(PortType.OUTPUT)) {
                for (LEdge iterEdge : iterPort.getEdges()) {
                    if (iterEdge.getOrigin() != null) {
                        currentTarget = iterEdge.getTarget();
                        break;
                    }
                }
                break;
            }

        } while (currentSource.getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE);
        if (debugCanvas != null) {
            debugCanvas.drawBuffer();
        }
        return globBarray;
    }

    private boolean drawOnDebug(final Object o, final DebugCanvas.Color c, final boolean fill) {
        if (debugCanvas != null) {
            if (o instanceof Rectangle2D) {
                Rectangle2D rec = (Rectangle2D) o;
                if (fill) {
                    debugCanvas.drawFilledRectangle((float) rec.getX(), (float) rec.getY(),
                            (float) rec.getWidth(), (float) rec.getHeight(), c);
                } else {
                    debugCanvas.drawRectangle((float) rec.getX(), (float) rec.getY(), (float) rec
                            .getWidth(), (float) rec.getHeight(), c);
                }
                return true;
            } else if (o instanceof Line2D) {
                Line2D line = (Line2D) o;
                debugCanvas.drawLine((float) line.getX1(), (float) line.getY1(), (float) line
                        .getX2(), (float) line.getY2(), c);
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

    /*
     * private void drawFilledEllipse(final Ellipse2D object, final DebugCanvas.Color c) {
     * drawOnDebug(object, c, true); }
     * 
     * private void drawEllipse(final Ellipse2D object, final DebugCanvas.Color c) {
     * drawOnDebug(object, c, false); }
     * 
     * private void drawFilledRectangle(final Rectangle2D object, final DebugCanvas.Color c) {
     * drawOnDebug(object, c, true); }
     * 
     * private void drawRectangle(final Rectangle2D object, final DebugCanvas.Color c) {
     * drawOnDebug(object, c, false); }
     * 
     * private void drawLine(final Line2D object, final DebugCanvas.Color c) { drawOnDebug(object,
     * c, false); }
     */
    /**
     * {@inheritDoc}
     */
    public LinkedList<Line2D.Double> getLines(final LinkedList<Rectangle2D.Double> boxes) {
        // LinkedList<Line2D.Float> larray = new Line2D.Float[boxes.length];
        LinkedList<Line2D.Double> larray = new LinkedList<Line2D.Double>();

        if (boxes.size() == 0) {
            return larray;
        }

        // @TODO no null values anymore ! larray.size = boxes.size - 1
        // first and last element are null
        // larray.add(null);

        // remember first box, then iterate over all boxes and write the corresponding lines
        Rectangle2D.Double oldBox = boxes.getFirst();
        for (Rectangle2D.Double box : boxes) {
            if (!box.equals(boxes.getFirst())) {
                if (oldBox != null && box != null) {
                    Rectangle2D foo = box.createIntersection(oldBox);
                    Line2D.Double line = new Line2D.Double(foo.getX(), foo.getY(), foo.getX(), foo
                            .getY()
                            + foo.getHeight());
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

    private void addNode(final LNode node) {
        allNodes.add(new Rectangle2D.Double(node.getPos().x, node.getPos().y, node.getSize().x,
                node.getSize().y));
    }

    /**
     * {@inheritDoc}
     */
    public void initialize(final LayeredGraph lg) {
        this.layeredGraph = lg;
        allEdges = new LinkedList<Line2D.Double>();
        allNodes = new LinkedList<Rectangle2D.Double>();
        // System.out.println("initialize()");

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    // nodes.add(node);
                    addNode(node);
                    for (LPort port : node.getPorts(PortType.OUTPUT)) {
                        for (LEdge edge : port.getEdges()) {
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE) {
                                // longEdges.add(edge);
                            } else {
                                addEdge(edge);
                            }
                        }
                    }
                } else {
                    // dummyNodes.add(node);
                }
            }
        }
    }

}
