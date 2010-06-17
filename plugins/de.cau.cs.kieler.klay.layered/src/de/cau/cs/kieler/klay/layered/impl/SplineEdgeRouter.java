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

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.draw2d.geometry.Point;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.core.ui.util.SplineUtilities;
import de.cau.cs.kieler.kiml.layout.options.PortType;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.Coord;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.impl.edges.LongEdge;
import de.cau.cs.kieler.klay.layered.impl.edges.ObjectBoxCalculator;
import de.cau.cs.kieler.klay.layered.impl.edges.SimpleLabelPlacer;
import de.cau.cs.kieler.klay.layered.impl.edges.SimpleSplineGenerator;
import de.cau.cs.kieler.klay.layered.modules.IBoxCalculator;
import de.cau.cs.kieler.klay.layered.modules.IEdgeRouter;
import de.cau.cs.kieler.klay.layered.modules.ILabelPlacer;
import de.cau.cs.kieler.klay.layered.modules.ISplineGenerator;
import de.cau.cs.kieler.klay.layered.modules.ISplineGenerator.curvature;

/**
 * Edge router module that draws splines (hopefully).
 * 
 * @author car
 * @author uru
 */
public class SplineEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {

    /** minimal spacing between objects. */
    private float spacing = LayeredLayoutProvider.DEF_SPACING;
    /** Box Calculator. */
    // private IBoxCalculator boxCalculator = new BuffereImgBoxCalculator();
    private IBoxCalculator boxCalculator = new ObjectBoxCalculator();
    /** Spline Generator. */
    private ISplineGenerator splineGenerator = new SimpleSplineGenerator();
    /** Label placer. */
    private ILabelPlacer labelPlacer = new SimpleLabelPlacer();
    /** the DebugCanvas to use for debug-drawings. **/
    private DebugCanvas debugCanvas;

    /** Spline being stored temporarily. */
    private BezierSpline globSpline = new BezierSpline();

    /** maximal numbers spline refining is produced. */
    private static final int MAX_ITERATIONS = 9;
    /** precision the splineFits method is working with. */
    private static final int SPLINE_PRECISION = 20;

    private static final double TANGENT_SCALE = 0.25d;

    /**
     * {@inheritDoc}
     */
    public void setSpacing(final float theSpacing) {
        this.spacing = theSpacing;
    }

    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph, final DebugCanvas theDebugCanvas) {
        this.debugCanvas = theDebugCanvas;
        routeEdges(layeredGraph);
    }

    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        System.out.println("routeEdges()");

        // contains nodes from which long edges are starting
        LinkedList<LEdge> longEdges = new LinkedList<LEdge>();
        LinkedList<LongEdge> realLongEdges = new LinkedList<LongEdge>();
        LinkedList<LEdge> shortEdges = new LinkedList<LEdge>();
        // set horizontal positioning for each layer and add bend points
        float xpos = 0.0f;
        // not used at the moment
        // TODO add this value to the correct layers, not to all!
        float longestLabel = 0.0f;
        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                node.getPos().x = xpos;
                // filter out start points of long edges
                if (node.getProperty(Properties.NODE_TYPE) != Properties.NodeType.LONG_EDGE) {
                    for (LPort port : node.getPorts(PortType.OUTPUT)) {
                        for (LEdge edge : port.getEdges()) {
                            if (edge.getTarget()
                                    .getNode().getProperty(Properties.NODE_TYPE) 
                                    == Properties.NodeType.LONG_EDGE) {
                                longEdges.add(edge);
                                realLongEdges.add(new LongEdge(edge));
                            } else {
                                shortEdges.add(edge);
                            }
                            for (LLabel label : edge.getLabels()) {
                                if (label.getSize().x > longestLabel) {
                                    longestLabel = label.getSize().x;
                                }
                            }
                        }
                    }
                }
            }
            xpos += layer.getSize().x + spacing;
        }
        layeredGraph.getSize().x = xpos - spacing;

        boxCalculator.initialize(layeredGraph, debugCanvas);

        // handle every long edge
        for (LongEdge longEdge : realLongEdges) {

            LPort source = longEdge.getEdge().getSource();
            LPort target = longEdge.getEdge().getTarget();

            // initialize this long edge
            longEdge.initialize();
            // globBarray = boxCalculator.getBoxes(longEdge.getEdge());
            target = longEdge.getTarget();
            LinkedList<Rectangle2D.Double> boxes = boxCalculator.getBoxes(longEdge.getEdge());

            computeSpline(boxes, new KVector(source.getNode().getPos().x + source.getPos().x,
                    source.getNode().getPos().y + source.getPos().y), new KVector(target.getNode()
                    .getPos().x
                    + target.getPos().x, target.getNode().getPos().y + target.getPos().y));

            if (globSpline != null) {
                // add calculated bend points
                for (KVector v : globSpline.getInnerPoints()) {
                    longEdge.getEdge().getBendPoints().add(new Coord((float) v.x, (float) v.y));
                }
                boxCalculator.addEdge(globSpline);
            }
        }

        /**
         * LABEL PLACING
         */
        // place labels
        labelPlacer.placeLabels(layeredGraph);

    }

    /**
     * Computes a spline from q to s, completely lying within the boxes. If a straight path is
     * possible, a flat spline will be constructed.
     * 
     * @param boxes
     *            boxes constraining the spline path
     * @param q
     *            start point
     * @param s
     *            end point
     */
    private void computeSpline(final LinkedList<Rectangle2D.Double> boxes, final KVector q,
            final KVector s) {

        // create a clean new spline
        globSpline = new BezierSpline();

        // compute the lines between boxes
        LinkedList<Line2D.Double> lines = boxCalculator.getLines(boxes);

        // calculate a linear curve/path lying entirely in the region
        LinkedList<KVector> points = new LinkedList<KVector>();
        // create
        computePoints(boxes, lines, q, s, points);

        // if there are only 2 points, draw a straight line instead of creating a spline
        if (points.size() == 2) {
            KVector direction = KVector.sub(points.getLast(), points.getFirst());
            direction.scale(TANGENT_SCALE);
            globSpline.addCurve(points.getFirst(), KVector.add(points.getFirst(), direction),
                    KVector.add(points.getLast(), direction.negate()), points.getLast());
            return;
        }

        // create tangents for start and endpoint, currently they head to their neighbour point
        KVector startTan = KVector.sub(points.get(1), points.getFirst()).normalize();
        KVector endTan = KVector.sub(points.getLast(), points.get(points.size() - 2)).normalize();

        // compute the spline
        computeSarray(boxes, lines, points, startTan, endTan);
    }

    /**
     * computes a piecewise Bezier spline that connects q and s and lies entirely inside the passed
     * region.
     * 
     * This method will be called recursively if the spline is split and each part is handled
     * separately in order to fit the region.
     * 
     * @param boxes
     * @param lines
     * @param points
     * @param vectorQ
     * @param vectorS
     * @return
     */
    private BezierSpline computeSarray(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final LinkedList<KVector> points,
            final KVector vectorQ, final KVector vectorS) {

        // create a temporary instance of a spline connecting the passed points
        BezierSpline spline = splineGenerator.generateSpline(points, vectorQ, vectorS);

        if (points.size() == 2) {
            // in this case we know a straight connection exists, so try straightening until the
            // spline fits
            while (!splineFits(spline, boxes, lines)) {
                splineGenerator.straightenSpline(spline);
            }
        } else if (!splineFits(spline, boxes, lines)) {

            // ############## TODO
            boolean splinesplitting = false;
            // ###############

            if (splinesplitting) {
                int count = 0;
                boolean fits = false;

                // try to scale the control points a bit until the spline fits
                while (!fits && count <= MAX_ITERATIONS) {
                    splineGenerator.refineSpline(points, spline, mode(count, MAX_ITERATIONS));
                    fits = splineFits(spline, boxes, lines);
                    count++;
                }

                // split the spline and handle separately if it still does not fit
                LinkedList<Rectangle2D.Double> newBarray1 = new LinkedList<Rectangle2D.Double>();
                LinkedList<Rectangle2D.Double> newBarray2 = new LinkedList<Rectangle2D.Double>();
                LinkedList<Line2D.Double> newLarray1 = new LinkedList<Line2D.Double>();
                LinkedList<Line2D.Double> newLarray2 = new LinkedList<Line2D.Double>();

                // TODO !!!!
                KVector[][] p = computeSplineSplit(spline, boxes, lines, newBarray1, newLarray1,
                        newBarray2, newLarray2);

                if (p != null) {
                    KVector v1 = p[1][1];
                    KVector v2 = p[0][p[0].length - 2];
                    KVector vectorP = KVector.sub(v1, v2).normalize();
                    LinkedList<KVector> p0 = new LinkedList<KVector>();
                    for (KVector v : p[0]) {
                        p0.add(v);
                    }
                    LinkedList<KVector> p1 = new LinkedList<KVector>();
                    for (KVector v : p[1]) {
                        p1.add(v);
                    }
                    // insert two new piecewise bezier curves corresponding to p
                    if (p[0].length > 1 && p[1].length > 1) {
                        computeSarray(newBarray1, newLarray1, p0, vectorQ, vectorP);
                        computeSarray(newBarray2, newLarray2, p1, vectorP, vectorS);
                        return null;
                    }
                }
            }
        }

        globSpline.addSpline(spline, false);
        return spline;

    }

    /**
     * computes points p0 , . . . , pn defining a feasible path that connects q and s.
     * 
     * @param boxes
     *            area bounding the path
     * @param lArray
     *            lines which need to be intersected
     * @param q
     *            start point
     * @param s
     *            end point
     * @param points
     *            bucket list, cannot be null!
     */
    private void computePoints(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lArray, final KVector q, final KVector s,
            final LinkedList<KVector> points) {

        // add first and last point
        if (points.size() == 0) {
            points.add(q);
            points.add(s);
        }

        // if the line fits we are happy
        if (lineFits(boxes, lArray, q, s)) {
            return;
        }

        // else split
        LinkedList<Rectangle2D.Double> newBarray1 = new LinkedList<Rectangle2D.Double>();
        LinkedList<Rectangle2D.Double> newBarray2 = new LinkedList<Rectangle2D.Double>();
        LinkedList<Line2D.Double> newLarray1 = new LinkedList<Line2D.Double>();
        LinkedList<Line2D.Double> newLarray2 = new LinkedList<Line2D.Double>();

        // calculate the splitting point
        KVector p = computeLineSplit(boxes, lArray, q, s, newBarray1, newLarray1, newBarray2,
                newLarray2);

        // @ TODO
        // some cases that shouldn't occur ...
        if (p == null) {
            return;
        }
        if (p.x == 0 && p.y == 0) {
            return;
        }

        // add found point to the list of points
        points.add(points.indexOf(s), p);
        // handle the new sections recursively
        computePoints(newBarray1, newLarray1, q, p, points);
        computePoints(newBarray2, newLarray2, p, s, points);
    }

    /**
     * finds the line segment that is the farthest from the (q, s) line and subdivides the boxes and
     * lines along that segment.
     * 
     * @param boxes
     * @param lines
     * @param q
     * @param s
     * @param newBoxes1
     * @param newLines1
     * @param newBoxes2
     * @param newLines2
     * @return
     */
    private KVector computeLineSplit(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final KVector q, final KVector s,
            final LinkedList<Rectangle2D.Double> newBoxes1,
            final LinkedList<Line2D.Double> newLines1,
            final LinkedList<Rectangle2D.Double> newBoxes2,
            final LinkedList<Line2D.Double> newLines2) {

        // @ TODO !! verify this !!
        // @ TODO there's something wrong with the line/box splitting ...
        // cant be that there's a (0,0) coming back
        // for some reason the method doesnt find ANY line that is away from (q,s) ...

        // check randfaelle
        if (lines.size() == 0) {
            return null;
        }

        if (lines.size() < 1 || boxes.size() < 2) {
            return null;
        }

        // create function parameters for the line q->s
        double a = (s.y - q.y) / (s.x - q.x);
        double b = q.y - a * q.x;

        // temporary maximal distance
        double maxDist = 0f;
        // position of maximal distance
        int maxDistPos = -1;
        // new point being returned in the end
        KVector newPoint = new KVector();

        // calculate maximal distance
        int index = 0;
        for (Line2D.Double currLine : lines) {
            // first check if we are in range
            if (currLine != null && currLine.getX1() > s.x) {
                break;
            }
            if (currLine != null && currLine.getX1() > q.x) {
                // these lines are hopefully top down
                double qsY = a * currLine.getX1() + b;
                if (!(qsY > currLine.getY1() && qsY < currLine.getY2())) {
                    double dist1 = Math.abs(currLine.getY1() - qsY);
                    double dist2 = Math.abs(currLine.getY2() - qsY);
                    // minimal distance from the line with the maximum distance!
                    double currMaxDist = Math.ceil(Math.min(dist1, dist2));
                    if (currMaxDist > maxDist) {
                        maxDist = currMaxDist;
                        maxDistPos = index;
                        newPoint.x = currLine.x1;
                        newPoint.y = (dist1 <= dist2) ? currLine.y1 : currLine.y2;
                    }
                }
            }
            index++;
        }

        // copy new lArrays
        // "throw" away the line at which we are splitting
        index = 0;
        for (Line2D.Double currLine : lines) {
            if (index < maxDistPos) {
                newLines1.add(currLine);
            } else if (index > maxDistPos) {
                newLines2.add(currLine);
            }
            index++;
        }

        // copy new bArrays
        // as there is one box "before" the first line, the box at maxDistPos belongs to the "left"
        // side.
        index = 0;
        for (Rectangle2D.Double currRect : boxes) {
            if (index <= maxDistPos) {
                newBoxes1.add(currRect);
            }
            if (index > maxDistPos) {
                newBoxes2.add(currRect);
            }
            index++;
        }

        return newPoint;
    }

    /**
     * line_fits checks if the line defined by q and s lies entirely inside the feasible region. The
     * line is clipped to each box; if the line intersects a box, it must do so along the
     * corresponding L segments.
     * 
     * @param boxes
     * @param lines
     * @param q
     * @param s
     * @return
     */
    private boolean lineFits(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final KVector q, final KVector s) {

        // @ TODO verify this !!

        Iterator<Line2D.Double> lineIt = lines.iterator();
        Line2D.Double line = null;

        // construct the line
        double a = (s.y - q.y) / (s.x - q.x);
        double b = q.y - a * q.x;

        for (Rectangle2D.Double box : boxes) {

            double qsYStart = a * box.x + b;
            double qsYEnd = a * box.x + box.width + b;

            // if the line intersects the box completely
            if ((box.y <= qsYStart) && (box.y + box.height >= qsYStart) && (box.y <= qsYEnd)
                    && (box.y + box.height >= qsYEnd)) {
                // check the lines too
                // for the last box there is no further line
                if (!(box == boxes.getLast())) {
                    line = lineIt.next();
                    // we check the line "on the right" of the box
                    if (!(line.y1 <= qsYEnd && line.y2 >= qsYEnd)) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * mode returns a flag to indicate if the curvature is to be increased or decreased. first
     * decreases
     * 
     * @param count
     * @param maxIterations
     * @return
     */
    private curvature mode(final int count, final int maxIterations) {
        // @ TODO CHANGE
        // first decrease 1/3 of the time, then 1/3 get old spline and increase for 1/3
        if (count < maxIterations / (2 + 1)) {
            return curvature.decrease;
        }
        return curvature.increase;
    }

    /**
     * compute_splinesplit finds the endpoint of a segment on the path that is the furthest from the
     * spline and subdivides the box and path arrays along that point.
     * 
     * @param spline
     * @param pArray
     * @return
     */
    private KVector[][] computeSplineSplit(final BezierSpline spline,
            final LinkedList<Rectangle2D.Double> boxes, final LinkedList<Line2D.Double> lArray,
            final LinkedList<Rectangle2D.Double> newBarry1,
            final LinkedList<Line2D.Double> newLarray1,
            final LinkedList<Rectangle2D.Double> newBarry2,
            final LinkedList<Line2D.Double> newLarray2) {

        // @ TODO it seems this isn't working at all currently

        double maxDist = 0;

        int maxDistPos = -1;
        KVector newPoint = new KVector();

        int index = 0;
        // check all curves for the point being the furthest from a line
        for (BezierCurve curve : spline.getCurves()) {

            double minX = curve.start.x;
            double maxX = curve.end.x;
            for (Line2D.Double line : lArray) {

                // first check if we are in range
                if (line != null && line.x1 < minX) {
                    // if not do nothing
                } else if (line != null && line.x1 < maxX) {
                    double dist1 = Math.abs(SplineUtilities.distanceFromSpline(new Point(
                            curve.start.x, curve.start.y), new Point(curve.fstControlPnt.x,
                            curve.fstControlPnt.y), new Point(curve.sndControlPnt.x,
                            curve.sndControlPnt.y), new Point(curve.end.x, curve.end.y), new Point(
                            line.getP1().getX(), line.getP1().getY())));
                    double dist2 = Math.abs(SplineUtilities.distanceFromSpline(new Point(
                            curve.start.x, curve.start.y), new Point(curve.fstControlPnt.x,
                            curve.fstControlPnt.y), new Point(curve.sndControlPnt.x,
                            curve.sndControlPnt.y), new Point(curve.end.x, curve.end.y), new Point(
                            line.getP2().getX(), line.getP2().getY())));
                    // minimal distance from the line with the maximum distance!
                    double currMaxDist = Math.min(dist1, dist2);
                    if (currMaxDist > maxDist) {
                        maxDist = currMaxDist;
                        maxDistPos = index;
                        newPoint.x = line.x1;
                        newPoint.y = (dist1 <= dist2) ? line.y1 : line.y2;
                    }
                } else if (line != null) {
                    // finished
                    break;
                }
            }
            index++;
        }

        // nothing found error!!
        if (maxDistPos == -1) {
            return null;
        }

        // split arrays
        // copy new lArrays
        index = 0;
        for (Line2D.Double currLine : lArray) {
            if (index < maxDistPos) {
                newLarray1.add(currLine);
            } else if (index > maxDistPos) {
                newLarray2.add(currLine);
            }
            index++;
        }

        // copy new bArrays
        index = 0;
        for (Rectangle2D.Double currRect : boxes) {
            if (index <= maxDistPos) {
                newBarry1.add(currRect);
            }
            if (index > maxDistPos) {
                newBarry2.add(currRect);
            }
            index++;
        }

        // add new curve
        index = maxDistPos;
        int length = spline.getBasePoints().length + 1;
        KVector[][] pts = new KVector[2][];
        pts[0] = new KVector[index + 2];
        pts[1] = new KVector[(length - index) - 1];

        int i = 0;
        for (KVector v : spline.getBasePoints()) {
            if (i <= index) {
                pts[0][i] = v;
            } else if (i == index + 1) {
                pts[0][i] = newPoint;
                pts[1][0] = newPoint;
                pts[1][i - index] = v;
            } else {
                pts[1][i - index] = v;
            }
            i++;
        }

        return pts;
    }

    /**
     * spline_fits checks if the spline lies entirely inside the region. The spline is sampled along
     * its length and these samples are then clipped as a linear path against the box region.
     * 
     * @param spline
     * @param boxes
     * @param lines
     * @return
     */
    private boolean splineFits(final BezierSpline spline,
            final LinkedList<Rectangle2D.Double> boxes, final LinkedList<Line2D.Double> lines) {

        for (BezierCurve curve : spline.getCurves()) {
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), SPLINE_PRECISION);
            for (KVector p : pts) {
                if (!pointInBox(p, boxes)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * tests whether point is in this area of boxes.
     * 
     * @TODO rewrite this, it possesses very evil performance.
     * 
     * @param pnt
     * @param boxes
     * @return
     */
    private boolean pointInBox(final KVector pnt, final LinkedList<Rectangle2D.Double> boxes) {

        double ceilY = Math.ceil(pnt.y);
        double floorY = Math.floor(pnt.y);
        for (Rectangle2D.Double box : boxes) {
            if (pnt.x >= box.x && pnt.x <= box.x + box.width) {
                if ((ceilY >= box.y) && (ceilY <= box.y + box.height)) {
                    return true;
                }
                if ((floorY >= box.y) && (floorY <= box.y + box.height)) {
                    return true;
                }
            }
        }
        return false;
    }

}
