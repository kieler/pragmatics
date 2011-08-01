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

import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.CubicSplineInterpolator;
import de.cau.cs.kieler.core.math.ISplineInterpolator;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.core.math.KVector;

/**
 * Simple implementation for the spline calculator.
 * 
 * @author uru
 */
public class SplineGenerator {

    /**
     * Defines whether curvature should be increased or decreased.
     */
    public enum Curvature {
        /** increase. */
        increase,
        /** decrease. */
        decrease;
    }

    private ISplineInterpolator interp = new CubicSplineInterpolator();

    /** factors for increasing/decreasing curvature. */
    private static final double INCREASE_FACTOR = 0.5d;
    private static final double DECREASE_FACTOR = 0.5d;

    /** factor being used when straightening a bezier segment. */
    private static final double STRAIGHTENING_FACTOR = 0.75;

    /** factor with which a control point is moved closer to the start/end point. */
    private static final double SMOOTHNESS_FACTOR = 0.3d;
    /**
     * how long may the distance between the fst/snd ctr point and the start/end point be,
     * considering the distance between start and end.
     */
    private static final double MAX_DISTANCE = 0.75d;

    /**
     * generates a simple piecewise bezier curve for given points.
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
            spline = interp.interpolatePoints(pArray);
        } else {
            spline = interp.interpolatePoints(pArray, vectorQ, vectorS, true);
        }

        // as the generated spline can contain loops etc, we try to remove those, by adjusting the
        // control points
        if (pArray.size() > 2) {
            removeFunnyCycles(spline);
        }

        return spline;
    }

    /**
     * twiddles some ctr points, as there may be situations where they are inappropriate. function
     * ensures that C1 derive ability is preserved.
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
            // scale fst ctr point and therefore snd of next curve
            if (distFst > dist * MAX_DISTANCE) {
                KVector v = KVector.sub(curve.fstControlPnt, curve.start);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.fstControlPnt = KVector.add(curve.start, v);
                if (listIt.hasPrevious()) {
                    listIt.previous();
                    if (listIt.hasPrevious()) {
                        BezierCurve tempCurve = listIt.previous();
                        KVector v1 = KVector.sub(tempCurve.sndControlPnt, tempCurve.end);
                        v1.scaleToLength(dist * SMOOTHNESS_FACTOR);
                        tempCurve.sndControlPnt = KVector.add(tempCurve.end, v1);
                        listIt.next();
                    }
                    listIt.next();
                }
            }
            // scale snd ctr point and therefore first of next curve
            if (distSnd > dist * MAX_DISTANCE) {
                KVector v = KVector.sub(curve.sndControlPnt, curve.end);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.sndControlPnt = KVector.add(curve.end, v);
                if (listIt.hasNext()) {
                    BezierCurve tempCurve = listIt.next();
                    KVector v1 = KVector.sub(tempCurve.fstControlPnt, tempCurve.start);
                    v1.scaleToLength(dist * SMOOTHNESS_FACTOR);
                    tempCurve.fstControlPnt = KVector.add(tempCurve.start, v1);
                    listIt.previous();
                }
            }
        }
    }

    /**
     * generates a simple piecewise bezier curve for given points.
     * 
     * @param pArray
     *            array with points which should be lay on the spline
     * @return created spline
     */
    public BezierSpline generateSpline(final LinkedList<KVector> pArray) {
        return generateSpline(pArray, null, null);
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

        BezierSpline spline = interp.interpolatePoints(new KVector[] { q, s }, startTan, endTan,
                false);
        return spline;
    }

    /**
     * perturb the control points of the spline in an attempt to make the spline fit. The approach
     * is similar to the straightening approach. We try to decrease the curvature of the spline. If
     * this does not seem to improve the fit, we try to increase the curvature. Since this process
     * may never terminate, max_iterations controls how many times to try.
     * 
     * @param pArray
     *            array with points which should be lay on the spline
     * @param ospline
     *            spline to be refined
     * @param mode
     *            either decrease or increase the curvature
     * @return created spline
     */
    public boolean refineSpline(final LinkedList<KVector> pArray, final BezierSpline ospline,
            final Curvature mode) {

        for (BezierCurve curve : ospline.getCurves()) {
            KVector fstdir = KVector.sub(curve.fstControlPnt, curve.start).normalize();
            KVector snddir = KVector.sub(curve.sndControlPnt, curve.end).normalize();
            double dist = KVector.distance(curve.start, curve.fstControlPnt);
            double dist2 = KVector.distance(curve.end, curve.sndControlPnt);
            switch (mode) {
            case decrease:
                curve.fstControlPnt.sub(fstdir.scale(DECREASE_FACTOR * dist));
                curve.sndControlPnt.sub(snddir.scale(DECREASE_FACTOR * dist2));
                break;
            case increase:
                curve.fstControlPnt.add(fstdir.scale(INCREASE_FACTOR * dist));
                curve.sndControlPnt.add(snddir.scale(INCREASE_FACTOR * dist2));
                break;
            }

            removeFunnyCycles(ospline);
        }

        return true;
    }

    /**
     * straighten_spline adjusts the control points of the spline to reduce the curvature. the
     * method is only used if there's only one bezier segment in the spline
     * 
     * @param spline
     *            spline to be straightened
     * @return true if successful otherwise false
     */
    public boolean straightenSpline(final BezierSpline spline) {
        if (spline.getCurves().size() != 1) {
            return false;
        }
        BezierCurve curve = spline.getCurves().getFirst();

        KVector fstdir = KVector.sub(curve.fstControlPnt, curve.start).scale(STRAIGHTENING_FACTOR);
        KVector snddir = KVector.sub(curve.sndControlPnt, curve.end).scale(STRAIGHTENING_FACTOR);

        curve.fstControlPnt.sub(fstdir);
        curve.sndControlPnt.sub(snddir);
        return true;
    }

}
