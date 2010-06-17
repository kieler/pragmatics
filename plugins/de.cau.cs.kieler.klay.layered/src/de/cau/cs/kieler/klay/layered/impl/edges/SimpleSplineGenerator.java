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

    private static final double INCREASE_FACTOR = 0.5d;
    private static final double DECREASE_FACTOR = 0.5d;

    /** factor being used when straightening a bezier segment. */
    private static final double STRAIGHTENING_FACTOR = 0.75;

    /** factor with which a control point is moved closer to the start/end point. */
    private static final double SMOOTHNESS_FACTOR = 0.5d;
    /**
     * how long may the distance between the fst/snd ctr point and the start/end point be,
     * considering the distance between start and end.
     */
    private static final int MAX_DISTANCE = 2;

    /**
     * {@inheritDoc}
     */
    public BezierSpline generateSpline(final LinkedList<KVector> pArray, final KVector vectorQ,
            final KVector vectorS) {

        BezierSpline spline;
        if (vectorQ == null || vectorS == null) {
            spline = interp.interpolatePoints(pArray);
        } else {
            spline = interp.interpolatePoints(pArray, vectorQ, vectorS);
        }

        // as the generated spline can contain loops etc, we try to remove those, by adjusting the
        // control points
        for (BezierCurve curve : spline.getCurves()) {
            double dist = KVector.distance(curve.start, curve.end);
            double distFst = KVector.distance(curve.start, curve.fstControlPnt);
            double distSnd = KVector.distance(curve.end, curve.sndControlPnt);

            // scale the control points to a certain distance from the start/end point
            if (distFst > dist / MAX_DISTANCE) {
                KVector v = KVector.sub(curve.fstControlPnt, curve.start);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.fstControlPnt = KVector.add(curve.start, v);
            }
            if (distSnd > dist / MAX_DISTANCE) {
                KVector v = KVector.sub(curve.sndControlPnt, curve.end);
                v.scaleToLength(dist * SMOOTHNESS_FACTOR);
                curve.sndControlPnt = KVector.add(curve.end, v);
            }
        }

        return spline;
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
    public boolean refineSpline(final LinkedList<KVector> pArray, final BezierSpline ospline,
            final curvature mode) {

        // @ TODO get this working!

        for (BezierCurve curve : ospline.getCurves()) {

            // calculate directions, to keep the C1 and C2 derive ability
            KVector fstdir = KVector.sub(curve.fstControlPnt, curve.start).normalize();
            KVector snddir = KVector.sub(curve.sndControlPnt, curve.end).normalize();
            double dist = KVector.distance(curve.start, curve.fstControlPnt);
            double dist2 = KVector.distance(curve.end, curve.sndControlPnt);
            if (mode == curvature.decrease) {
                curve.fstControlPnt.sub(fstdir.scale(DECREASE_FACTOR * dist));
                curve.sndControlPnt.sub(snddir.scale(DECREASE_FACTOR * dist2));
                // curve.fstControlPnt.scale(0.9d);
                // curve.sndControlPnt.scale(0.9d);
            } else if (mode == curvature.increase) {
                curve.fstControlPnt.add(fstdir.scale(INCREASE_FACTOR * dist));
                curve.sndControlPnt.add(snddir.scale(INCREASE_FACTOR * dist2));
                // curve.fstControlPnt.scale(1.1d);
                // curve.sndControlPnt.scale(1.1d);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean straightenSpline(final BezierSpline spline) {
        if (spline.getCurves().size() != 1) {
            // ERROR
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
