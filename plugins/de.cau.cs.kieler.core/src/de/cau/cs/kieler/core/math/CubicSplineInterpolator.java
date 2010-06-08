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
package de.cau.cs.kieler.core.math;

import java.util.LinkedList;

/**
 * Provides a technique to calculate a piece-wise bezier spline for a list of given points.
 * 
 * As described in "Graphic Gems, Andrew Glassner (editor), Academic Press, 1990".
 * 
 * @author uru
 * 
 */
public class CubicSplineInterpolator implements ISplineInterpolator {

    /**
     * Interpolation Coefficients for even n. Address like : [m-1][k-1] for m > 7 the values for 1
     * <= k <= 7 have converged to constant values independent of m. thus, fur n >= 15 one can use
     * the values of m = 7
     */
    private static final double[][] INTERP_COOF_EVEN = new double[][] { { 0.25d },
            { 0.2677, -0.0667 }, { 0.2679, -0.0714, 0.0179 }, { 0.2679, -0.0718, 0.0192, -0.0048 },
            { 0.2679, -0.0718, 0.0192, -0.0051, 0.0013 },
            { 0.2679, -0.0718, 0.0192, -0.0052, 0.0014, -0.0003 },
            { 0.2679, -0.0718, 0.0192, -0.0052, 0.0014, -0.0004, 0.0001 } };

    /**
     * Interpolation Coefficients for odd n. Address like : [m-1][k-1]. for m > 7 the values for 1
     * <= k <= 7 have converged to constant values independent of m. thus, fur n >= 15 one can use
     * the values of m = 7
     */
    private static final double[][] INTERP_COOF_ODD = new double[][] { { 0.3333d },
            { 0.2727, -0.0909 }, { 0.2683, -0.0732, 0.0244 }, { 0.2680, -0.0719, 0.0196, -0.0065 },
            { 0.2680, -0.0719, 0.0196, -0.0053, 0.0018 },
            { 0.2679, -0.0718, 0.0192, -0.0052, 0.0014, -0.0005 },
            { 0.2679, -0.0718, 0.0192, -0.0052, 0.0014, -0.0004, 0.0001 } };

    /** maximal k value which is "interesting". */
    private static final int MAX_K = 7;

    /** factor describing the length a in/outgoing vector is scaled. */
    private static final double TANGENT_SCALE = 0.25d;

    /**
     * Calculates a closed piecewise bezier spline where the first point is start and end.
     * 
     * @param points
     *            points being passed by the spline
     * @return piecewise bezier spline
     */
    public BezierSpline calculateClosedBezierSpline(final KVector[] points) {
        BezierSpline spline = new BezierSpline();

        int n = points.length;
        boolean even = (n % 2 == 0);
        // set m depending on n even or odd.
        int m = (even) ? (n - 2) / 2 : (n - 1) / 2;
        m = Math.min(m, MAX_K);

        double a = 0;
        KVector[] d = new KVector[n];

        for (int i = 0; i < n; i++) {
            d[i] = new KVector();

            // calculate sum for every Di
            for (int k = 1; k <= m; k++) {
                a = (even) ? INTERP_COOF_ODD[Math.min(m - 1, MAX_K - 1)][Math.min(k - 1, MAX_K - 1)]
                        : INTERP_COOF_EVEN[Math.min(m - 1, MAX_K - 1)][Math.min(k - 1, MAX_K - 1)];
                d[i].x += a * (points[(i + k) % n].x - points[(i - k + n) % n].x);
                d[i].y += a * (points[(i + k) % n].y - points[(i - k + n) % n].y);
            }
        }

        // add all pieces to the piecewise bezier spline
        for (int i = 0; i < n; i++) {
            spline.addCurve(points[i], KVector.add(d[i], points[i]), KVector.sub(
                    points[(i + 1) % n], d[(i + 1) % n]), points[(i + 1) % n]);
        }

        return spline;
    }

    /**
     * Calculates a piecewise bezier spline, hereby assumes, that the "head in" tangent corresponds
     * to the line from the starting point to the first point and the "head out" tangent from the
     * n-1th point to the end point.
     * 
     * @param points
     *            points being passed by the spline
     * @return piecewise bezier spline
     */
    private BezierSpline calculateOpenBezierSpline(final KVector[] points) {
        return calculateOpenBezierSpline(points, KVector.sub(points[1], points[0]).normalize(),
                KVector.sub(points[points.length - 2], points[points.length - 1]).normalize());
    }

    /**
     * Calculates a piecewise bezier spline.
     * 
     * @param points
     *            points being passed by the spline
     * 
     * @param startTan
     *            vector describing into which direction to head out of the initial point
     * @param endTan
     *            vector describing direction to head into the final node
     * @return piecewise bezier spline
     */
    private BezierSpline calculateOpenBezierSpline(final KVector[] points, final KVector startTan,
            final KVector endTan) {

        // in this case the paper talks about n-1 points, therefore it's kind of inconsistent to the
        // closed approach
        BezierSpline spline = new BezierSpline();

        int n = points.length - 1;
        // t is the "extended curve" degenerating the points to a closed loop
        KVector[] t = new KVector[2 * n];
        KVector[] d = new KVector[n + 1];

        // set initial and final tangent vectors
        double startScale = 0;
        double endScale = 0;
        if (points.length == 2) {
            startScale = Math.abs(points[0].x - points[n].x) * TANGENT_SCALE;
            endScale = startScale;
        } else {
            startScale = Math.abs(points[0].x - points[1].x) * TANGENT_SCALE;
            endScale = Math.abs(points[n - 1].x - points[n].x) * TANGENT_SCALE;
        }
        d[0] = startTan.scaledCreate(startScale);
        d[n] = endTan.scaledCreate(endScale);

        // set first and last t
        t[0] = KVector.add(points[0], d[0]);
        t[n] = KVector.sub(points[n], d[n]);
        
        // extend t
        for (int i = 1; i < n; i++) {
            t[i] = points[i];
            t[2 * n - i] = t[i];
        }

        // n in this case always even
        int m = Math.min(n - 1, MAX_K);
        double a = 0;

        // calculate all Di's using the Ti's
        for (int i = 1; i < n; i++) {
            d[i] = new KVector();
            for (int k = 1; k <= Math.min(m, MAX_K); k++) {
                a = INTERP_COOF_EVEN[Math.min(m - 1, MAX_K - 1)][Math.min(k - 1, MAX_K - 1)];

                d[i].x += a * (t[i + k].x - t[Math.abs((i - k))].x);
                d[i].y += a * (t[i + k].y - t[Math.abs((i - k))].y);
            }
        }

        // create all bezier spline segments
        for (int i = 0; i < n; i++) {
            KVector bend1 = KVector.add(d[i], points[i]);
            KVector bend2 = KVector.sub(points[(i + 1) % (n + 1)], d[(i + 1) % (n + 1)]);
            spline.addCurve(points[i], bend1, bend2, points[i + 1]);
        }

        return spline;
    }

    /**
     * {@inheritDoc}
     */
    public BezierSpline interpolatePoints(final KVector[] points) {
        return calculateOpenBezierSpline(points);
    }

    /**
     * {@inheritDoc}
     */
    public BezierSpline interpolatePoints(final LinkedList<KVector> points) {
        return calculateOpenBezierSpline(points.toArray(new KVector[points.size()]));
    }

    /**
     * {@inheritDoc}
     */
    public BezierSpline interpolatePoints(final KVector[] points, final KVector startVec,
            final KVector endVec) {
        return calculateOpenBezierSpline(points, startVec, endVec);
    }

    /**
     * {@inheritDoc}
     */
    public BezierSpline interpolatePoints(final LinkedList<KVector> points, final KVector startVec,
            final KVector endVec) {
        return calculateOpenBezierSpline(points.toArray(new KVector[points.size()]), startVec,
                endVec);
    }
}
