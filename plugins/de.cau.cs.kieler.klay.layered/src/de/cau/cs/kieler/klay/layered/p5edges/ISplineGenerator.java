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

import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;

/**
 * @author car
 * @author uru
 */
public interface ISplineGenerator {

    /**
     * defines wheter curvature should be incresed or decreased.
     */
    public enum curvature {
        /** increase. */
        increase,
        /** decrease. */
        decrease
    }

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
    BezierSpline generateSpline(final LinkedList<KVector> pArray, final KVector vectorQ,
            final KVector vectorS);

    /**
     * generates a simple piecewise bezier curve for given points.
     * 
     * @param pArray
     *            array with points which should be lay on the spline
     * @return created spline
     */
    BezierSpline generateSpline(final LinkedList<KVector> pArray);

    /**
     * Generates a spline representation for straight edges.
     * 
     * @param q
     *            start point
     * @param s
     *            end point
     * @return BezierSpline representation.
     */
    BezierSpline generateShortSpline(final KVector q, final KVector s);

    /**
     * straighten_spline adjusts the control points of the spline to reduce the curvature. the
     * method is only used if there's only one bezier segment in the spline
     * 
     * @param spline
     *            spline to be straightened
     * @return true if successful otherwise false
     */
    boolean straightenSpline(final BezierSpline spline);

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
    boolean refineSpline(final LinkedList<KVector> pArray, final BezierSpline ospline,
            final curvature mode);
}
