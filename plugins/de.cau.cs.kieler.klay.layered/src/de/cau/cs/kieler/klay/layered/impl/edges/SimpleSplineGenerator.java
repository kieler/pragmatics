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

import java.util.LinkedList;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.CubicSplineInterpolator;
import de.cau.cs.kieler.core.math.ISplineInterpolator;
import de.cau.cs.kieler.core.math.BezierSpline.BezierCurve;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.modules.ISplineGenerator;

/**
 * Simple implementation for the spline calculator.
 * 
 * @author uru
 * 
 */
public class SimpleSplineGenerator extends AbstractAlgorithm implements ISplineGenerator {

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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public BezierSpline generateSpline(final LinkedList<KVector> pArray) {
        return generateSpline(pArray, null, null);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public boolean refineSpline(final LinkedList<KVector> pArray, final BezierSpline ospline,
            final curvature mode) {

        for (BezierCurve curve : ospline.getCurves()) {
            KVector fstdir = KVector.sub(curve.fstControlPnt, curve.start).normalize();
            KVector snddir = KVector.sub(curve.sndControlPnt, curve.end).normalize();
            double dist = KVector.distance(curve.start, curve.fstControlPnt);
            double dist2 = KVector.distance(curve.end, curve.sndControlPnt);
            if (mode == curvature.decrease) {
                curve.fstControlPnt.sub(fstdir.scale(DECREASE_FACTOR * dist));
                curve.sndControlPnt.sub(snddir.scale(DECREASE_FACTOR * dist2));
            } else if (mode == curvature.increase) {
                curve.fstControlPnt.add(fstdir.scale(INCREASE_FACTOR * dist));
                curve.sndControlPnt.add(snddir.scale(INCREASE_FACTOR * dist2));
            } else {
                return false;
            }

            removeFunnyCycles(ospline);
        }

        return true;
    }

    /**
     * {@inheritDoc}
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
