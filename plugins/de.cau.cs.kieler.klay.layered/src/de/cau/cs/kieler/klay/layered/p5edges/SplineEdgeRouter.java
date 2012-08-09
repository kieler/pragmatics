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
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.CubicSplineInterpolator;
import de.cau.cs.kieler.core.math.ISplineInterpolator;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Implements a naive way of routing the edges with splines. Uses the dummy nodes as reference
 * points for a spline calculation. This results in the bend points actually lying on the curve,
 * being the dummy nodes.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>the graph has a proper layering with
 *     assigned node and port positions; the size of each layer is
 *     correctly set</dd>
 *   <dt>Postcondition:</dt><dd>each node is assigned a horizontal coordinate;
 *     the bend points of each edge are set; the width of the whole graph is set</dd>
 * </dl>
 * 
 * @author uru
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class SplineEdgeRouter extends AbstractAlgorithm implements ILayoutPhase {

    /** factor for layer spacing. */
    private static final double LAYER_SPACE_FAC = 0.2;
    
    /** the minimal angle that short edges must have (for smaller angles a spline is created). */
    private static final double MINIMAL_ANGLE = Math.PI / 3;   // SUPPRESS CHECKSTYLE MagicNumber
    
    /** amounts of points treated the same way. */
    private static final int HIGH_LIMIT = 7;
    private static final int MID_LIMIT = 5;

    /** corresponding offsets of used dummy node positions. */
    private static final int SMALL_OFFSET = 2;
    private static final int BIG_OFFSET = 3;

    /** how strong is the first and last ctrl point scaled down? new size is 1/VERTICAL_CHANGE. */
    private static final double VERTICAL_CHANGE = 4;

    /** at least this many points are needed to handle the spline. */
    private static final int MINIMAL_POINTS_HANDLES = 4;

    /** factor with which a control point is moved closer to the start/end point. */
    private static final double SMOOTHNESS_FACTOR = 0.3d;
    
    /**
     * how long may the distance between the first/second control point and the start/end point be,
     * considering the distance between start and end.
     */
    private static final double MAX_DISTANCE = 0.75d;
    
    /** the module for spline interpolation. */
    private ISplineInterpolator interpolator = new CubicSplineInterpolator();

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph) {
        getMonitor().begin("Spline edge routing", 1);
        double spacing = layeredGraph.getProperty(Properties.OBJ_SPACING);
        float edgeSpaceFac = layeredGraph.getProperty(Properties.EDGE_SPACING_FACTOR);
        
        double xpos = 0.0, layerSpacing = 0.0;
        for (Layer layer : layeredGraph) {
            // set horizontal coordinates for all nodes of the layer
            layer.placeNodes(xpos);
            
            double maxVertDiff = 0;
            for (LNode node : layer) {
                // count the maximal vertical difference of output edges
                for (LPort port : node.getPorts(PortType.OUTPUT)) {
                    double sourcePos = port.getNode().getPosition().y
                            + port.getPosition().y + port.getAnchor().y;
                    for (LPort targetPort : port.getSuccessorPorts()) {
                        if (targetPort.getNode().getLayer() != node.getLayer()) {
                            double targetPos = targetPort.getNode().getPosition().y
                                    + targetPort.getPosition().y + targetPort.getAnchor().y;
                            maxVertDiff = KielerMath.maxd(maxVertDiff,
                                    targetPos - sourcePos, sourcePos - targetPos);
                        }
                    }
                }
            }
            
            // determine placement of next layer based on the maximal vertical difference
            layerSpacing = spacing + LAYER_SPACE_FAC * edgeSpaceFac * maxVertDiff;
            xpos += layer.getSize().x + layerSpacing;
        }
        layeredGraph.getSize().x = xpos - layerSpacing;

        // process all edges
        for (Layer layer : layeredGraph) {
            for (LNode node : layer) {
                if (node.getProperty(Properties.NODE_TYPE) != NodeType.LONG_EDGE) {
                    for (LPort port : node.getPorts()) {
                        for (LEdge edge : port.getOutgoingEdges()) {
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                                    == NodeType.LONG_EDGE) {
                                processLongEdge(edge);
                            } else {
                                processShortEdge(edge);
                            }
                        }
                    }
                }
            }
        }
        
        getMonitor().done();
    }
    
    /**
     * Process a short edge.
     * 
     * @param edge an edge
     */
    private void processShortEdge(final LEdge edge) {
        LPort start = edge.getSource();
        LPort end = edge.getTarget();
        KVector startVec = start.getAbsoluteAnchor();
        KVector endVec = end.getAbsoluteAnchor();

        // it is enough to check one vector, as the angle at the other node is the same
        KVector startToEnd = KVector.diff(endVec, startVec);
        double radians = startToEnd.toRadians();

        // if the minimalAngle criteria is not met, create a short spline
        if ((radians < MINIMAL_ANGLE || radians > Math.PI - MINIMAL_ANGLE)) {
            BezierSpline spline = generateShortSpline(startVec, endVec);
            for (KVector v : spline.getInnerPoints()) {
                edge.getBendPoints().add(v);
            }
        }
    }
    
    /**
     * Process a long edge.
     * 
     * @param edge the first short edge belonging to a long edge
     */
    private void processLongEdge(final LEdge edge) {
        LEdge intermediateEdge = edge;
        KVectorChain points = new KVectorChain();
        points.add(edge.getSource().getAbsoluteAnchor());
        KVector startTangent = edge.getTarget().getAbsoluteAnchor()
                .sub(points.getFirst()).normalize();
        
        // run along the edge till end point is found
        do {
            LPort targetPort = intermediateEdge.getTarget();
            for (LPort iterPort : targetPort.getNode().getPorts()) {
                if (iterPort.getOutgoingEdges().size() > 0) {
                    intermediateEdge = iterPort.getOutgoingEdges().get(0);
                    break;
                }
            }
            points.add(targetPort.getAbsoluteAnchor());
        } while (intermediateEdge.getTarget().getNode().getProperty(Properties.NODE_TYPE) 
                == NodeType.LONG_EDGE);

        points.add(intermediateEdge.getTarget().getAbsoluteAnchor());
        KVector endTangent = intermediateEdge.getSource().getAbsoluteAnchor()
                .sub(points.getLast()).normalize();
        
        BezierSpline spline = generateSpline(points, startTangent, endTangent.negate());
        
        // apply optimizations for NaiveApproach
        spline = optimizeSpline(spline);

        // add calculated bend points
        for (KVector v : spline.getInnerPoints()) {
            edge.getBendPoints().add(v);
        }
    }

    /**
     * Initial try to improve naive splines by post processing.
     * We try to reduce the funny curves by removing some control points and therefore "smoothing"
     * the curve.
     * 
     * @param spline a spline
     * @return a new optimized spline
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
            KVector startTangent = KVector.diff((bendLeft == null) ? test : bendLeft, start)
                    .normalize();
            KVector endTangent = KVector.diff(bendRight, end).normalize().negate();

            return generateSpline(newPoints, startTangent, endTangent);

        } else {
            return spline;
        }
    }

    /**
     * Test whether this is a spline with a straight path between the dummy nodes.
     * 
     * @param spline a spline
     * @return true if it is a long straight spline
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
    
    /**
     * Generates a simple piecewise bezier curve for given points.
     * 
     * @param pArray
     *            array with points which should be lay on the spline
     * @param vectorQ
     *            outgoing tangent vector of the initial node
     * @param vectorS
     *            incoming tangent vector of the final node
     * @return created spline
     */
    public BezierSpline generateSpline(final LinkedList<KVector> pArray, final KVector vectorQ,
            final KVector vectorS) {

        BezierSpline spline;
        if (vectorQ == null || vectorS == null) {
            spline = interpolator.interpolatePoints(pArray);
        } else {
            spline = interpolator.interpolatePoints(pArray, vectorQ, vectorS, true);
        }

        // as the generated spline can contain loops etc, we try to remove those, by adjusting the
        // control points
        if (pArray.size() > 2) {
            removeFunnyCycles(spline);
        }

        return spline;
    }

    /**
     * Twiddles some control points, as there may be situations where they are inappropriate.
     * Function ensures that C1 derive ability is preserved.
     * 
     * @param spline
     */
    private void removeFunnyCycles(final BezierSpline spline) {
        ListIterator<BezierCurve> listIt = spline.getCurves().listIterator();

        while (listIt.hasNext()) {
            BezierCurve curve = listIt.next();
            double dist = KVector.distance(curve.start, curve.end);
            double distFst = KVector.distance(curve.start, curve.fstControlPnt);
            double distSnd = KVector.distance(curve.end, curve.sndControlPnt);
            // scale first ctrl point and therefore second of next curve
            if (distFst > dist * MAX_DISTANCE) {
                KVector v = KVector.diff(curve.fstControlPnt, curve.start);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.fstControlPnt = KVector.sum(curve.start, v);
                if (listIt.hasPrevious()) {
                    listIt.previous();
                    if (listIt.hasPrevious()) {
                        BezierCurve tempCurve = listIt.previous();
                        KVector v1 = KVector.diff(tempCurve.sndControlPnt, tempCurve.end);
                        v1.scaleToLength(dist * SMOOTHNESS_FACTOR);
                        tempCurve.sndControlPnt = KVector.sum(tempCurve.end, v1);
                        listIt.next();
                    }
                    listIt.next();
                }
            }
            // scale second ctrl point and therefore first of next curve
            if (distSnd > dist * MAX_DISTANCE) {
                KVector v = KVector.diff(curve.sndControlPnt, curve.end);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.sndControlPnt = KVector.sum(curve.end, v);
                if (listIt.hasNext()) {
                    BezierCurve tempCurve = listIt.next();
                    KVector v1 = KVector.diff(tempCurve.fstControlPnt, tempCurve.start);
                    v1.scaleToLength(dist * SMOOTHNESS_FACTOR);
                    tempCurve.fstControlPnt = KVector.sum(tempCurve.start, v1);
                    listIt.previous();
                }
            }
        }
    }

    /**
     * Generates a spline representation for straight edges.
     * 
     * @param q
     *            start point
     * @param s
     *            end point
     * @return BezierSpline representation.
     */
    public BezierSpline generateShortSpline(final KVector q, final KVector s) {

        // we choose width difference as distance for ctr points, as it showed good results.
        double widthdiff = Math.abs(q.x - s.x);
        KVector startTan = new KVector(widthdiff, 0);
        KVector endTan = new KVector(widthdiff, 0);

        BezierSpline spline = interpolator.interpolatePoints(new KVector[] { q, s }, startTan, endTan,
                false);
        return spline;
    }

}
