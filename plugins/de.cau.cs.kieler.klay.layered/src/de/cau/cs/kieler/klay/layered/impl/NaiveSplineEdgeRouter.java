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
package de.cau.cs.kieler.klay.layered.impl;

import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.CubicSplineInterpolator;
import de.cau.cs.kieler.core.math.ISplineInterpolator;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.edges.LongEdge;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;

/**
 * Implements a naive way of routing the edges with splines. Uses the dummy nodes as reference
 * points for a spline calculation. This results in the bend points actually lying on the curve,
 * being the dummy nodes.
 * 
 * @author uru
 * 
 */
public class NaiveSplineEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {

    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;

    /** spline interpolator. */
    private ISplineInterpolator splineInterp = new CubicSplineInterpolator();

    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        // contains nodes from which long edges are starting
        LinkedList<LEdge> longEdges = new LinkedList<LEdge>();
        LinkedList<LongEdge> realLongEdges = new LinkedList<LongEdge>();
        // set horizontal positioning for each layer and add bend points
        float xpos = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPos().x = xpos;
                // filter out start points of long edges
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    for (LPort port : node.getPorts(PortType.OUTPUT)) {
                        for (LEdge edge : port.getEdges()) {
                            if (edge.getTarget().getNode().getProperty(Properties.NODE_TYPE) == Properties.NodeType.LONG_EDGE) {
                                longEdges.add(edge);
                                realLongEdges.add(new LongEdge(edge));
                            }
                        }
                    }
                }
            }
            xpos += layer.getSize().x + spacing;
        }
        layeredGraph.getSize().x = xpos - spacing;

        // handle every long edge
        for (LongEdge longEdge : realLongEdges) {
            // initialize this long edge
            longEdge.initialize();
            // get angles for heading out and heading in
            KVector startAngle = longEdge.getStartTangent();
            KVector endAngle = longEdge.getEndTangent().negate();

            BezierSpline spline = splineInterp.interpolatePoints(longEdge.getPoints(), startAngle,
                    endAngle);

            spline = optimizeSpline(spline);
            if (spline != null) {
                // add calculated bend points
                for (KVector v : spline.getInnerPoints()) {
                    longEdge.getEdge().getBendPoints().add(new Coord((float) v.x, (float) v.y));
                }
            }

        }

    }

    private BezierSpline optimizeSpline(BezierSpline spline) {
        if (spline.getBasePoints().length >= 4 && isLongStraightSpline(spline)) {

            KVector[] basePoints = spline.getBasePoints();
            int n = basePoints.length - 1;

            int offset = (n >= 7) ? 3 : 2;
            if (n < 5)
                offset = 1;

            KVector start = spline.getStartPoint();
            KVector end = spline.getEndPoint();

            LinkedList<KVector> newPoints = new LinkedList<KVector>();
            newPoints.add(start);

            if (n >= 5) {
                KVector intermediateLeft = basePoints[1].clone();
                intermediateLeft.y += (start.y - intermediateLeft.y) / 4;
                newPoints.add(intermediateLeft);
            }

            KVector bendLeft = null;
            if (n >= 5) {
                 bendLeft = basePoints[offset].clone();
                newPoints.add(bendLeft);
            }
            KVector bendRight = basePoints[n - offset].clone();
            newPoints.add(bendRight);

            if (n >= 5) {
                KVector intermediateRight = basePoints[n - 1].clone();
                intermediateRight.y += (end.y - intermediateRight.y) / 4;
                newPoints.add(intermediateRight);
            }
            newPoints.add(end);

            // KVector startTangent = KVector.sub(intermediateLeft, start).normalize();
            // KVector endTangent = KVector.sub(intermediateRight, end).normalize().negate();

            KVector test = null;
            if(bendLeft == null){
                test = bendRight.clone();
//                test.y = start.y - bendRight.y;
//                test.x -= (bendRight.x - start.x);
//                test.normalize();
//                test.negate();
            }
            KVector startTangent = KVector.sub((bendLeft == null) ? test : bendLeft, start).normalize();
            KVector endTangent = KVector.sub(bendRight, end).normalize().negate();

            return splineInterp.interpolatePoints(newPoints, startTangent, endTangent);

        } else {
            return spline;
        }
    }

    private boolean isLongStraightSpline(BezierSpline spline) {
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
     * {@inheritDoc}
     */
    public void setSpacing(final float theSpacing) {
        this.spacing = theSpacing;
    }

}
