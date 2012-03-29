/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.math.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.KVector;

/**
 * Junit test-class for BezierSpline from de.cau.cs.kieler.core.math: Mathematics utility class for
 * the KIELER projects.
 * 
 * @author wah
 */
public class BezierSplineTest {

    private BezierSpline bs;
    private KVector startPnt;
    private KVector fstCtrPnt;
    private KVector sndCtrPnt;
    private KVector endPnt;

    /**
     * initialize a new BezierSpline with Orizantal ranged KVectors
     */
    private void initBezierCurvehOrizontal() {
        startPnt = new KVector(0, 50);
        fstCtrPnt = new KVector(10, 50);
        sndCtrPnt = new KVector(20, 50);
        endPnt = new KVector(30, 50);
        bs = new BezierSpline();
        bs.addCurve(startPnt, fstCtrPnt, sndCtrPnt, endPnt);
    }

    /**
     * initialize a new BezierSpline with Vertical ranged KVectors
     */
    private void initBezierCurveVertical() {
        startPnt = new KVector(50, 0);
        fstCtrPnt = new KVector(50, 20);
        sndCtrPnt = new KVector(50, 40);
        endPnt = new KVector(50, 50);
        bs = new BezierSpline();
        bs.addCurve(startPnt, fstCtrPnt, sndCtrPnt, endPnt);
    }

    /**
     * 
     * Test getStartPoint from BezierSpline class.
     * 
     */
    @Test
    public void testGetStartPoint() {
        this.initBezierCurveVertical();
        KVector v = bs.getStartPoint();
        assertEquals(v.x, startPnt.x, 0);
        assertEquals(v.y, startPnt.y, 0);
        this.initBezierCurvehOrizontal();
        v = bs.getStartPoint();
        assertEquals(v.x, startPnt.x, 0);
        assertEquals(v.y, startPnt.y, 0);
    }

    /**
     * 
     * Test getEndPoint from BezierSpline class.
     * 
     */
    @Test
    public void testGetEndPoint() {
        this.initBezierCurveVertical();
        KVector v = bs.getEndPoint();
        assertEquals(v.x, endPnt.x, 0);
        assertEquals(v.y, endPnt.y, 0);
        this.initBezierCurvehOrizontal();
        v = bs.getEndPoint();
        assertEquals(v.x, endPnt.x, 0);
        assertEquals(v.y, endPnt.y, 0);

    }

    /**
     * 
     * Test getInnerPoints from BezierSpline class.
     * 
     */
    @Test
    public void testGetInnerPoints() {
        // test if all InnerPoints have the same x when a Vertical Curve
        this.initBezierCurveVertical();
        KVector[] vectors = bs.getInnerPoints();
        for (KVector v : vectors) {
            assertEquals(v.x, startPnt.x, 0.00000001);
        }
        // test if all InnerPoints have the same y when a Orizontal Curve
        this.initBezierCurvehOrizontal();
        vectors = bs.getInnerPoints();
        for (KVector v : vectors) {
            assertEquals(v.y, startPnt.y, 0.00000001);
        }

    }

    /**
     * 
     * Test getBasePoints from BezierSpline class.
     * 
     */
    @Test
    public void testGetBasePoints() {
        // test if all BasePoints have the same x when a Vertical Curve
        this.initBezierCurveVertical();
        KVector[] vectors = bs.getBasePoints();
        for (KVector v : vectors) {
            assertEquals(v.x, startPnt.x, 0.00000001);
        }
        // test if all InnerPoints have the same y when a Orizontal Curve
        this.initBezierCurvehOrizontal();
        vectors = bs.getBasePoints();
        for (KVector v : vectors) {
            assertEquals(v.y, startPnt.y, 0.00000001);
        }
    }

    /**
     * 
     * Test getPolylineApprx from BezierSpline class.
     * 
     */
    @Test
    public void testGetPolylineApprx() {
        // test if all PolylineApprx have the same x when a Vertical Curve
        this.initBezierCurveVertical();
        KVector[] vectors = bs.getPolylineApprx(50);
        for (KVector v : vectors) {
            assertEquals(v.x, startPnt.x, 0.00000001);
        }
        // test if all PolylineApprx have the same y when a Orizontal Curve
        this.initBezierCurvehOrizontal();
        vectors = bs.getPolylineApprx(50);
        for (KVector v : vectors) {
            assertEquals(v.y, startPnt.y, 0.00000001);
        }
    }

}
