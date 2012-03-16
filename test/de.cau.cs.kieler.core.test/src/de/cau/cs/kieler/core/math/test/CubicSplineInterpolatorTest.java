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
 * 
 */
public class CubicSplineInterpolatorTest {

    /**
     * 
     * Test calculateClosedBezierSpline from CubicSplineInterpolatorTest class.
     * 
     */
    @Test
    public void calculateClosedBezierSplineTest() {
        //test if the endpoint of the returned BezierSpline equal to the startpoint
        KVector v0 = new KVector(5, 50);
        KVector v1 = new KVector(10, 70);
        KVector v2 = new KVector(30, 100);
        KVector v3 = new KVector(60, 70);
        KVector v4 = new KVector(70, 40);
        KVector[] vectors = {v0,v1,v2,v3,v4};
        BezierSpline b = new CubicSplineInterpolator().calculateClosedBezierSpline(vectors);
        assertTrue(v0.equals(b.getEndPoint()));
        
    }

}
