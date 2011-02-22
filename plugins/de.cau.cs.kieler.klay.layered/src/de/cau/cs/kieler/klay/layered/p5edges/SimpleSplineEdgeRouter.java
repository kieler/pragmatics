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
package de.cau.cs.kieler.klay.layered.p5edges;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Implements a naive way of routing the edges with splines. Uses the dummy nodes as reference
 * points for a spline calculation. This results in the bend points actually lying on the curve,
 * being the dummy nodes.
 * 
 * @author uru
 * 
 */
public class SimpleSplineEdgeRouter extends AbstractAlgorithm implements ILayoutPhase {

    /** minimal spacing between objects. */
    private float spacing;

    /** spline generator. */
    private ISplineGenerator splineGen = new SimpleSplineGenerator();

    /** label placer. */
    private ILabelPlacer labelPlacer = new SimpleLabelPlacer();

    /** amounts of points treated the same way. */
    private static final int HIGH_LIMIT = 7;
    private static final int MID_LIMIT = 5;

    /** corresponding offsets of used dummy node positions. */
    private static final int SMALL_OFFSET = 2;
    private static final int BIG_OFFSET = 3;

    /** how strong is the first and last ctr point scaled down? new size is 1/VERTICAL_CHANGE. */
    private static final int VERTICAL_CHANGE = 4;

    /** at least this many points are needed to handle the spline. */
    private static final int MINIMAL_POINTS_HANDLES = 4;

    /**
     * {@inheritDoc}
     */
    public void execute(final LayeredGraph layeredGraph) {
        getMonitor().begin("Simple spline routing", 1);
        spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        
        // contains nodes from which long edges are starting
        LinkedList<LEdge> longEdges = new LinkedList<LEdge>();
        LinkedList<LongEdge> realLongEdges = new LinkedList<LongEdge>();
        LinkedList<LEdge> shortEdges = new LinkedList<LEdge>();
        // set horizontal positioning for each layer
        double xpos = 0.0f;
        List<LLabel> consideredLabelsInLayerSize = new LinkedList<LLabel>();
        for (Layer layer : layeredGraph.getLayers()) {
            layer.placeNodes(xpos);
            for (LNode node : layer.getNodes()) {
                // filter out start points of long edges
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    for (LPort port : node.getPorts(PortType.OUTPUT)) {
                        for (LEdge edge : port.getEdges()) {
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                                    == Properties.NodeType.LONG_EDGE) {
                                longEdges.add(edge);
                                realLongEdges.add(new LongEdge(edge));
                            } else {
                                shortEdges.add(edge);
                            }
                        }
                    }
                }
            }
            xpos += layer.getSize().x + spacing;
            LLabel longestLabelHere = labelPlacer.longestLabel(layer);
            if (!consideredLabelsInLayerSize.contains(longestLabelHere)) {
                xpos += longestLabelHere.getSize().x;
                consideredLabelsInLayerSize.add(longestLabelHere);
            }
        }
        layeredGraph.getSize().x = xpos - spacing;

        // get user defined minimal angle for straigt edges heading in and out nodes.
        int minimalAngle = layeredGraph.getProperty(Properties.MIN_EDGE_ANGLE);
        // check all short edges
        if (minimalAngle != 0) {
            for (LEdge edge : shortEdges) {
                LPort start = edge.getSource();
                LNode startNode = start.getNode();
                LPort end = edge.getTarget();
                LNode endNode = end.getNode();
                KVector startVec = new KVector(startNode.getPos().x + start.getPos().x, startNode
                        .getPos().y
                        + start.getPos().y);
                KVector endVec = new KVector(endNode.getPos().x + end.getPos().x,
                        endNode.getPos().y + end.getPos().y);

                // it is enough to check one vector, as the angle at the other node is the same
                KVector startToEnd = KVector.sub(endVec, startVec);
                double degrees = startToEnd.toDegrees();

                // if the minimalAngle criteria is not met, create a short spline
                if ((degrees < minimalAngle || degrees > (KVector.FULL_CIRCLE / 2) - minimalAngle)) {
                    BezierSpline spline = splineGen.generateShortSpline(startVec, endVec);
                    for (KVector v : spline.getInnerPoints()) {
                        if (getMonitor().isCanceled()) {
                            break;
                        }
                        edge.getBendPoints().add(v);
                    }
                }

            }
        }
        // handle every long edge
        for (LongEdge longEdge : realLongEdges) {
            // initialize this long edge
            longEdge.initialize();
            // get angles for heading out and heading in
            KVector startAngle = longEdge.getStartTangent();
            KVector endAngle = longEdge.getEndTangent().negate();

            BezierSpline spline = splineGen.generateSpline(longEdge.getPoints(), startAngle,
                    endAngle);
            // apply optimizations for NaiveApproach
            spline = optimizeSpline(spline);

            if (spline != null) {
                // add calculated bend points
                for (KVector v : spline.getInnerPoints()) {
                    if (getMonitor().isCanceled()) {
                        break;
                    }
                    longEdge.getEdge().getBendPoints().add(v);
                }
            }

        }

        // place labels
        labelPlacer.placeLabels(layeredGraph);
        
        getMonitor().done();
    }

    /**
     * Initial try to improve naive splines by post processing.
     * 
     * We try to reduce the funny curves by removing some control points and therefore "smoothing"
     * the curve.
     * 
     * @param spline
     * @return
     */
    private BezierSpline optimizeSpline(final BezierSpline spline) {
        if (spline.getBasePoints().length >= MINIMAL_POINTS_HANDLES && isLongStraightSpline(spline)) {

            KVector[] basePoints = spline.getBasePoints();
            int n = basePoints.length - 1;

            int offset = (n >= HIGH_LIMIT) ? BIG_OFFSET : SMALL_OFFSET;
            if (n < MID_LIMIT) {
                offset = 1;
            }
            KVector start = spline.getStartPoint();
            KVector end = spline.getEndPoint();

            LinkedList<KVector> newPoints = new LinkedList<KVector>();
            newPoints.add(start);

            if (n >= MID_LIMIT) {
                KVector intermediateLeft = basePoints[1].clone();
                intermediateLeft.y += (start.y - intermediateLeft.y) / VERTICAL_CHANGE;
                newPoints.add(intermediateLeft);
            }

            KVector bendLeft = null;
            if (n >= MID_LIMIT) {
                bendLeft = basePoints[offset].clone();
                newPoints.add(bendLeft);
            }
            KVector bendRight = basePoints[n - offset].clone();
            newPoints.add(bendRight);

            if (n >= MID_LIMIT) {
                KVector intermediateRight = basePoints[n - 1].clone();
                intermediateRight.y += (end.y - intermediateRight.y) / VERTICAL_CHANGE;
                newPoints.add(intermediateRight);
            }
            newPoints.add(end);

            KVector test = null;
            if (bendLeft == null) {
                test = bendRight.clone();
            }
            KVector startTangent = KVector.sub((bendLeft == null) ? test : bendLeft, start)
                    .normalize();
            KVector endTangent = KVector.sub(bendRight, end).normalize().negate();

            return splineGen.generateSpline(newPoints, startTangent, endTangent);

        } else {
            return spline;
        }
    }

    /**
     * Test whether this is a spline with a straight path between the dummy nodes.
     * 
     * @param spline
     * @return
     */
    private boolean isLongStraightSpline(final BezierSpline spline) {
        KVector start = spline.getStartPoint();
        KVector end = spline.getEndPoint();
        Integer y = null;
        for (KVector pt : spline.getBasePoints()) {
            if (pt != start && pt != end) {
                if (y == null) {
                    y = (int) pt.y;
                } else {
                    if (!((int) pt.y == (int) y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
