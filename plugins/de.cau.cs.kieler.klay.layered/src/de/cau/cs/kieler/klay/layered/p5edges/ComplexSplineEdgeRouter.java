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

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortType;
import de.cau.cs.kieler.klay.layered.Properties;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p5edges.ISplineGenerator.curvature;

/**
 * Edge router module that draws splines (hopefully).
 * 
 * @author car
 * @author uru
 */
public class ComplexSplineEdgeRouter extends AbstractAlgorithm implements IEdgeRouter {

    /** minimal spacing between objects. */
    private float spacing;
    /** Box Calculator. */
    // private IBoxCalculator boxCalculator = new BuffereImgBoxCalculator();
    private IBoxCalculator boxCalculator = new ObjectBoxCalculator();
    /** Spline Generator. */
    private ISplineGenerator splineGenerator = new SimpleSplineGenerator();
    /** Label placer. */
    private ILabelPlacer labelPlacer = new SimpleLabelPlacer();

    /** Spline being stored temporarily. */
    private BezierSpline globSpline = new BezierSpline();

    /** maximal numbers spline refining is produced. */
    private static final int MAX_ITERATIONS = 9;
    /**
     * precision the splineFits method is working with.
     * 
     * current result : curve length / Spline precision
     */
    private static final double SPLINE_PRECISION = 12.5d;

    private static final double TANGENT_SCALE = 0.25d;

    /** @TODO correspond with box generation */
    private static final int FITTING_TOLERANCE = 3;

    /**
     * {@inheritDoc}
     */
    public void routeEdges(final LayeredGraph layeredGraph) {
        getMonitor().begin("Complex spline routing", 1);
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
            
            //make space for long labels
            LLabel longestLabelHere = labelPlacer.longestLabel(layer);
            if (!consideredLabelsInLayerSize.contains(longestLabelHere)) {
                xpos += longestLabelHere.getSize().x;
                consideredLabelsInLayerSize.add(longestLabelHere);
            }
        }
        layeredGraph.getSize().x = xpos - spacing;

        boxCalculator.initialize(layeredGraph);

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
                    BezierSpline spline = splineGenerator.generateShortSpline(startVec, endVec);
                    for (KVector v : spline.getInnerPoints()) {
                        if (getMonitor().isCanceled()) {
                            break;
                        }
                        edge.getBendPoints().add(v);
                    }
                    boxCalculator.addEdge(spline);
                } else {
                    boxCalculator.addEdge(edge);
                }

            }
        }

        double cumBoxTime = 0;
        double cumSplineTime = 0;
        int counter = 0;

        // handle every long edge
        for (LongEdge longEdge : realLongEdges) {
            if (getMonitor().isCanceled()) {
                break;
            }
            if (getMonitor().isCanceled()) {
                return;
            }

            LPort source = longEdge.getEdge().getSource();
            LPort target = longEdge.getEdge().getTarget();

            // initialize this long edge
            longEdge.initialize();
            // globBarray = boxCalculator.getBoxes(longEdge.getEdge());
            target = longEdge.getTarget();

            IKielerProgressMonitor subMon = getMonitor().subTask(1);

            subMon.begin("Box Calculation " + counter, 1);
            LinkedList<Rectangle2D.Double> boxes = boxCalculator.getBoxes(longEdge.getEdge());
            subMon.done();
            cumBoxTime += subMon.getExecutionTime();

            subMon = getMonitor().subTask(1);
            subMon.begin("Spline Calculation " + counter++, 1);
            if (source.getNode().toString().equals("n_n3")
                    && target.getNode().toString().equals("n_n4")) {
                double d = 0;
                d++;
            }
            // compute the actual spline
            computeSpline(boxes, new KVector(source.getNode().getPos().x + source.getPos().x,
                    source.getNode().getPos().y + source.getPos().y), new KVector(target.getNode()
                    .getPos().x
                    + target.getPos().x, target.getNode().getPos().y + target.getPos().y));
            subMon.done();
            cumSplineTime += subMon.getExecutionTime();

            if (globSpline != null) {
                // add calculated bend points
                for (KVector v : globSpline.getInnerPoints()) {
                    longEdge.getEdge().getBendPoints().add(v);
                }
                boxCalculator.addEdge(globSpline);
            }

        }

        IKielerProgressMonitor labelMon = getMonitor().subTask(1);
        labelMon.begin("Label placing", 1);
        /**
         * LABEL PLACING
         */
        // place labels
        labelPlacer.placeLabels(layeredGraph);

        labelMon.done();
        getMonitor().done();
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

        // create tangents for start and end point, currently they head to their neighbor point
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
     * @return calculated spline
     */
    private BezierSpline computeSarray(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final LinkedList<KVector> points,
            final KVector vectorQ, final KVector vectorS) {

        // create a temporary instance of a spline connecting the passed points
        BezierSpline spline = splineGenerator.generateSpline(points, vectorQ, vectorS);

        if (points.size() == 2) {
            // in this case we know a straight connection exists, so try straightening until the
            // spline fits
            // TODO remove the count, as soon as the line splits (0,0) problem is fixed
            int count = 0;
            while (!splineFits(spline, boxes, lines) && count <= MAX_ITERATIONS) {
                splineGenerator.straightenSpline(spline);
                count++;
            }
        } else if (!splineFits(spline, boxes, lines)) {

            int count = 0;
            boolean fits = false;

            // try to scale the control points a bit until the spline fits
            while (!fits && count <= MAX_ITERATIONS) {
                splineGenerator.refineSpline(points, spline, mode(count, MAX_ITERATIONS));
                fits = splineFits(spline, boxes, lines);
                count++;
            }

            if (!fits) {
                // split the spline and handle separately if it still does not fit
                LinkedList<Rectangle2D.Double> newBoxes1 = new LinkedList<Rectangle2D.Double>();
                LinkedList<Rectangle2D.Double> newBoxes2 = new LinkedList<Rectangle2D.Double>();
                LinkedList<Line2D.Double> newLines1 = new LinkedList<Line2D.Double>();
                LinkedList<Line2D.Double> newLines2 = new LinkedList<Line2D.Double>();

                // compute a spline split, pass all empty buckets needed afterwards
                KVector[][] p = computeSplineSplit(spline, boxes, lines, newBoxes1, newLines1,
                        newBoxes2, newLines2);

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
                        computeSarray(newBoxes1, newLines1, p0, vectorQ, vectorP);
                        computeSarray(newBoxes2, newLines2, p1, vectorP, vectorS);
                        return null;
                    }
                }
            }

        }

        globSpline.addSpline(spline, false);
        return spline;

    }

    /**
     * Computes a list of points defining a path from q to s, that is lying entirely insite the
     * region defined by the boxes and lines.
     * 
     * @param boxes
     *            area bounding the path
     * @param lines
     *            lines which need to be intersected
     * @param q
     *            start point
     * @param s
     *            end point
     * @param points
     *            bucket list, cannot be null!
     */
    private void computePoints(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final KVector q, final KVector s,
            final LinkedList<KVector> points) {

        // add first and last point
        if (points.size() == 0) {
            points.add(q);
            points.add(s);
        }

        // if the line fits we are happy
        if (lineFits(boxes, lines, q, s)) {
            return;
        }

        // else split
        LinkedList<Rectangle2D.Double> newBoxes1 = new LinkedList<Rectangle2D.Double>();
        LinkedList<Rectangle2D.Double> newBoxes2 = new LinkedList<Rectangle2D.Double>();
        LinkedList<Line2D.Double> newLines1 = new LinkedList<Line2D.Double>();
        LinkedList<Line2D.Double> newLines2 = new LinkedList<Line2D.Double>();

        // calculate the splitting point
        KVector p = computeLineSplit(boxes, lines, q, s, newBoxes1, newLines1, newBoxes2, newLines2);

        // @ FIXME
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
        computePoints(newBoxes1, newLines1, q, p, points);
        computePoints(newBoxes2, newLines2, p, s, points);
    }

    /**
     * Splits the path (q,s) at the point lying inside the box region, that is the furthest away
     * from the path itself. Splits the boxes and lines at this point.
     * 
     */
    private KVector computeLineSplit(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final KVector q, final KVector s,
            final LinkedList<Rectangle2D.Double> newBoxes1,
            final LinkedList<Line2D.Double> newLines1,
            final LinkedList<Rectangle2D.Double> newBoxes2,
            final LinkedList<Line2D.Double> newLines2) {

        // @ FIXME
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
                    double currMaxDist = Math.min(dist1, dist2);
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
     * checks if the path defined by (q,s) entirely lies inside the passed region and intersects all
     * passed lines.
     */
    private boolean lineFits(final LinkedList<Rectangle2D.Double> boxes,
            final LinkedList<Line2D.Double> lines, final KVector q, final KVector s) {

        Iterator<Line2D.Double> lineIt = lines.iterator();
        Line2D.Double line = null;

        // construct the line
        double a = (s.y - q.y) / (s.x - q.x);
        double b = q.y - a * q.x;

        for (Rectangle2D.Double box : boxes) {

            // position where the box need to be intersected
            double qsYStart = a * (box.x) + b;
            double qsYEnd = a * (box.x + box.width) + b;

            // if the line intersects the box completely
            if ((box.y - FITTING_TOLERANCE <= qsYStart)
                    && (box.y + box.height + FITTING_TOLERANCE >= qsYStart)
                    && (box.y - FITTING_TOLERANCE <= qsYEnd)
                    && (box.y + box.height + FITTING_TOLERANCE >= qsYEnd)) {
                // check the lines too
                // for the last box there is no further line
                if (!(box == boxes.getLast())) {
                    line = lineIt.next();
                    // we check the line "on the right" of the box
                    if (!((line.y1 - FITTING_TOLERANCE <= qsYEnd) 
                            && (line.y2 + FITTING_TOLERANCE >= qsYEnd))) {
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
     */
    private curvature mode(final int count, final int maxIterations) {
        // first decrease 1/3 of the time, then 1/3 get old spline and increase for 1/3
        if (count < maxIterations / (2 + 1)) {
            return curvature.decrease;
        }
        return curvature.increase;
    }

    /**
     * compute_splinesplit finds the endpoint of a segment on the path that is the furthest from the
     * spline and subdivides the box and path arrays along that point.
     */
    private KVector[][] computeSplineSplit(final BezierSpline spline,
            final LinkedList<Rectangle2D.Double> boxes, final LinkedList<Line2D.Double> lArray,
            final LinkedList<Rectangle2D.Double> newBarry1,
            final LinkedList<Line2D.Double> newLines1,
            final LinkedList<Rectangle2D.Double> newBarry2,
            final LinkedList<Line2D.Double> newLines2) {

        double maxDist = 0;
        int maxDistPos = -1;
        KVector newPoint = new KVector();
        int index = 0;

        // check all curves for the point being the farthest from a line
        for (BezierCurve curve : spline.getCurves()) {
            double maxX = curve.end.x;
            index = 0;
            for (Line2D.Double line : lArray) {
                // first check if we are in range
                if (line != null && line.x1 < maxX) {
                    double dist1 = Math.abs(KielerMath.distanceFromSpline(curve.start,
                            curve.fstControlPnt, curve.sndControlPnt, curve.end,
                            new KVector(line.getP1().getX(), line.getP1().getY())));
                    double dist2 = Math.abs(KielerMath.distanceFromSpline(curve.start,
                            curve.fstControlPnt, curve.sndControlPnt, curve.end,
                            new KVector(line.getP2().getX(), line.getP2().getY())));
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
                index++;
            }
        }

        // nothing found error!!
        if (maxDistPos == -1) {
            return null;
        }

        // split arrays
        // copy new lines
        index = 0;
        for (Line2D.Double currLine : lArray) {
            if (index < maxDistPos) {
                newLines1.add(currLine);
            } else if (index > maxDistPos) {
                newLines2.add(currLine);
            }
            index++;
        }

        // copy new boxes
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

        // split the current curve
        KVector[] basePoints = spline.getBasePoints();
        // determine new points position
        int detPos = -1;
        for (int i = 0; i < basePoints.length; i++) {
            if (basePoints[i].x > newPoint.x) {
                detPos = i;
                break;
            }
        }

        assert detPos != -1;

        // create two new point arrays
        KVector[][] pts = new KVector[2][];
        pts[0] = new KVector[detPos + 1];
        pts[1] = new KVector[basePoints.length - detPos + 1];
        int i = 0;
        for (KVector v : spline.getBasePoints()) {
            if (i < detPos) {
                pts[0][i] = v;
            } else if (i == detPos) {
                pts[0][i] = newPoint;
                pts[1][0] = newPoint;
                pts[1][i - detPos + 1] = v;
            } else {
                pts[1][i - detPos + 1] = v;
            }
            i++;
        }

        return pts;
    }

    /**
     * spline_fits checks if the spline lies entirely inside the region. The spline is sampled along
     * its length and these samples are then clipped as a linear path against the box region.
     */
    private boolean splineFits(final BezierSpline spline,
            final LinkedList<Rectangle2D.Double> boxes, final LinkedList<Line2D.Double> lines) {
        return splineFits(spline, boxes.toArray(new Rectangle2D.Double[boxes.size()]), lines);
    }

    /**
     * Checks if the passed spline fits into the box region. Currently no checking for the lines! We
     * take a number of points on the spline, defined by {@code SPLINE_PRECISION}, and check if all
     * of those are within the box region. To fasten this process we use a guessing method, to guess
     * the box corresponding to the current point to check.
     * 
     * Maybe try: (spline_fits checks if the spline lies entirely inside the region. The spline is
     * sampled along its length and these samples are then clipped as a linear path against the box
     * region.)
     */
    private boolean splineFits(final BezierSpline spline, final Rectangle2D.Double[] boxes,
            final LinkedList<Line2D.Double> lines) {

        // boxes are left right .. so start is at first box
        double boxstart = boxes[0].x;
        // we guess all boxes have the same distance
        double boxwidth = boxes[0].width;

        for (BezierCurve curve : spline.getCurves()) {
            // get number of curve points depending on its length
            double length = KVector.distance(curve.start, curve.end);
            int numPoints = new Double(length / SPLINE_PRECISION).intValue();
            KVector[] pts = KielerMath.calcBezierPoints(curve.asVectorList(), numPoints);

            for (KVector p : pts) {
                if (p == pts[pts.length - 1]) {
                    break;
                }
                int guessIndex = (int) ((p.x - boxstart) / boxwidth);
                // most of the time we hit!
                // test show, that every ~20th box we miss guess
                guessIndex = Math.max(0, guessIndex);
                int offset = 0;
                boolean match = false;
                if (guessIndex == boxes.length) {
                    // try one smaller as we may have miss guessed
                    guessIndex--;
                }
                while (!match) {
                    if (guessIndex + offset < 0 || guessIndex + offset >= boxes.length) {
                        return false;
                    }
                    Rectangle2D.Double box = boxes[guessIndex + offset];
                    if ((box.x <= p.x) && (box.x + box.width >= p.x)) {
                        match = true;
                        if (box.y - FITTING_TOLERANCE > Math.ceil(p.y)
                                || box.y + box.height + FITTING_TOLERANCE < Math.floor(p.y)) {
                            return false;
                        }
                        // TODO if box fits, we check the corresponding line
                    } else if (box.x > p.x) {
                        // try one to the left
                        offset--;
                    } else if (box.x + box.width < p.x) {
                        // try one to the right
                        offset++;
                    }

                }
            }
        }
        return true;
    }

}
