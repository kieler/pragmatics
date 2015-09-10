/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.test.math;

import static org.junit.Assert.*;

import org.junit.Test;

import de.cau.cs.kieler.core.math.BezierSpline;
import de.cau.cs.kieler.core.math.CubicSplineInterpolator;
import de.cau.cs.kieler.core.math.KVector;

/**
 * Junit test-class for CubicSplineInterpolator from de.cau.cs.kieler.core.math: Mathematics utility
 * class for the KIELER projects.
 * 
 * @author wah
 */
public class CubicSplineInterpolatorTest {

    /**
     * Test calculateClosedBezierSpline from CubicSplineInterpolatorTest class.
     */
    @Test
    public void calculateClosedBezierSplineTest() {

        // test if the endpoint of the returned BezierSpline equal to the startpoint
        KVector v0 = new KVector(5, 50);
        KVector v1 = new KVector(10, 70);
        KVector v2 = new KVector(30, 100);
        KVector v3 = new KVector(60, 70);
        KVector v4 = new KVector(70, 40);
        KVector[] vectors = { v0, v1, v2, v3, v4 };
        BezierSpline b = new CubicSplineInterpolator().calculateClosedBezierSpline(vectors);
        assertTrue(v0.equals(b.getEndPoint()));

        // test horizontal aligned points
        v0 = new KVector(5, 50);
        v1 = new KVector(10, 50);
        v2 = new KVector(30, 50);
        v3 = new KVector(60, 50);
        v4 = new KVector(70, 50);
        KVector[] vectors_h = { v0, v1, v2, v3, v4 };
        b = new CubicSplineInterpolator().calculateClosedBezierSpline(vectors_h);
        assertEquals(50, b.getStartPoint().y, 0);
        assertEquals(50, b.getEndPoint().y, 0);
        for (KVector v : b.getInnerPoints()) {
            assertEquals(50, v.y, 0);
        }

        // test vertical aligned points
        v0 = new KVector(100, 10);
        v1 = new KVector(100, 50);
        v2 = new KVector(100, 100);
        v3 = new KVector(100, 120);
        v4 = new KVector(100, 200);
        KVector[] vectors_v = { v0, v1, v2, v3, v4 };
        b = new CubicSplineInterpolator().calculateClosedBezierSpline(vectors_v);
        assertEquals(100, b.getStartPoint().x, 0);
        assertEquals(100, b.getEndPoint().x, 0);
        for (KVector v : b.getInnerPoints()) {
            assertEquals(100, v.x, 0);
        }

    }

}
