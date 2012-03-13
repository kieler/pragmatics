/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
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

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.math.KielerMath;

/**
 * Junit test-class for de.cau.cs.kieler.core.math: Mathematics utility class for the KIELER
 * projects.
 * 
 * @author wah
 */
public class MathTest {

    KielerMath kielerMath;

    /**
     * 
     * Tests some valid combinations of Factl from kielerMath class.
     * 
     */

    /** Tests some valid combinations of Factl from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testFactl() {
        assertEquals(kielerMath.factl(0), 1);
        assertEquals(kielerMath.factl(1), 1);
        assertEquals(kielerMath.factl(20), 2432902008176640000L);

    }

    /** Tests the Little argument exception of Factl from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactlLittleIllegalArgumentException() {
        kielerMath.factl(-50);
    }

    /** Tests the Big argument exception of Factl from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactlBigIllegalArgumentException() {
        kielerMath.factl(21);
    }

    /**
     * 
     * Tests some valid combinations of Factd from kielerMath class.
     * 
     */

    /** Tests some valid combinations of Factd from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testFactd() {
        assertEquals(kielerMath.factd(0), 1, 0.0);
        assertEquals(kielerMath.factd(1), 1, 0.0);
    }

    /** Tests the Little argument exception of Factd from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFacdlLittleIllegalArgumentException() {
        kielerMath.factd(-1);
    }

    /** Tests the Big argument exception of Factd from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactdBigIllegalArgumentException() {
        kielerMath.factd(27);
    }

    /**
     * 
     * Tests some valid combinations of binomiall from kielerMath class.
     * 
     */

    /** Tests some valid combinations of binomiall from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testBinomiall() {
        assertEquals(kielerMath.binomiall(2, 0), 1);
        assertEquals(kielerMath.binomiall(20, 20), 1);
        assertEquals(kielerMath.binomiall(2, 1), 2);
    }

    /** Tests the Little argument exception of binomiall from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testBinomialllLittleIllegalArgumentException() {
        kielerMath.binomiall(-1, 1);
    }

    /**
     * 
     * Tests some valid combinations of binomiald from kielerMath class.
     * 
     */

    /** Tests some valid combinations of binomiald from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testBinomiald() {
        assertEquals(kielerMath.binomiald(2, 0), 1, 0);
        assertEquals(kielerMath.binomiald(20, 20), 1, 0);
        assertEquals(kielerMath.binomiald(2, 1), 2, 0);
    }

    /** Tests the Little argument exception of binomiald from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testBinomialdLittleIllegalArgumentException() {
        kielerMath.binomiald(-1, 1);
    }

    /**
     * 
     * Tests some valid combinations of pow from kielerMath class.
     * 
     */
    @SuppressWarnings("static-access")
    @Test
    public void testPow() {
        double ad = 10;
        float af = 10;
        assertEquals(kielerMath.pow(ad, 0), 1, 0);
        assertEquals(kielerMath.pow(af, 0), 1, 0);
        assertEquals(kielerMath.pow(ad, 2), 100, 0);
        assertEquals(kielerMath.pow(af, 2), 100, 0);

    }

    /**
     * 
     * Tests some valid combinations of CalcBezierPoints from kielerMath class.
     * 
     */
    @SuppressWarnings("static-access")
    @Test
    public void testCalcBezierPoints() {
        /** some KVectors */
        KVector kvector1 = new KVector(10, 10);
        KVector kvector2 = new KVector(20, 20);
        KVector kvector3 = new KVector(30, 30);
        KVector kvector4 = new KVector(50, 50);
        /** test if the last KVector of the result similar to kvector4 */
        KVector[] result = kielerMath.calcBezierPoints(20, kvector1, kvector2, kvector3, kvector4);
        assertEquals(result[result.length - 1].x, kvector4.x, 0.000000001);
        assertEquals(result[result.length - 1].y, kvector4.y, 0.000000001);

        /** some KVectors with y=10 */
        kvector1 = new KVector(50, 10);
        kvector2 = new KVector(70, 10);
        kvector3 = new KVector(80, 10);
        kvector4 = new KVector(100, 10);
        /** test if the all result-KVectors with y=10 */
        result = kielerMath.calcBezierPoints(20, kvector1, kvector2, kvector3, kvector4);
        for (KVector k : result) {
            assertEquals(k.y, 10, 0.000000001);
        }

    }

    /**
     * 
     * Tests some valid combinations of appoximateSpline from kielerMath class.
     * 
     * 
     */

    @SuppressWarnings("static-access")
    @Test
    public void testAppoximateSpline() {
        /** some KVectors */
        KVector kvector1 = new KVector(10, 10);
        KVector kvector2 = new KVector(20, 20);
        KVector kvector3 = new KVector(30, 30);
        KVector kvector4 = new KVector(50, 50);
        /** test if the last KVector of the result similar to kvector4 */
        KVector[] vectors = kielerMath.calcBezierPoints(20, kvector1, kvector2, kvector3, kvector4);
        KVectorChain controlPoints = new KVectorChain(vectors);
        KVectorChain result = KielerMath.appoximateSpline(controlPoints);
        KVector k = result.get(result.size() - 1);
        assertEquals(k.x, kvector4.x, 0.000000001);
        assertEquals(k.y, kvector4.y, 0.000000001);

        /** some KVectors with y=10 */
        kvector1 = new KVector(50, 10);
        kvector2 = new KVector(70, 10);
        kvector3 = new KVector(80, 10);
        kvector4 = new KVector(100, 10);
        /** test if the all result-KVectors with y=10 */
        vectors = kielerMath.calcBezierPoints(20, kvector1, kvector2, kvector3, kvector4);
        controlPoints = new KVectorChain(vectors);
        result = KielerMath.appoximateSpline(controlPoints);

        for (KVector kv : result) {
            assertEquals(kv.y, 10, 0.000000001);
        }

    }

    /**
     * 
     * Tests some valid combinations of distanceFromSpline from kielerMath class.
     * 
     * 
     */

    @Test
    public void testDistanceFromSpline() {
        /** some KVectors */
        KVector kvector1 = new KVector(10, 10);
        KVector kvector2 = new KVector(20, 20);
        KVector kvector3 = new KVector(30, 30);
        KVector kvector4 = new KVector(50, 50);
        /** test if the result is 0 when kvector4 = needle */
        KVector needle = kvector4;
        double result = KielerMath.distanceFromSpline(kvector1, kvector2, kvector3, kvector4,
                needle);
        assertEquals(result, 0, 0.01);
        /** test if the result is 0 when kvector3 = needle */
        needle = kvector3;
        result = KielerMath.distanceFromSpline(kvector1, kvector2, kvector3, kvector4, needle);
        assertEquals(result, 0, 0.01);
        /** test if the result is 0 when kvector2 = needle */
        needle = kvector2;
        result = KielerMath.distanceFromSpline(kvector1, kvector2, kvector3, kvector4, needle);
        assertEquals(result, 0, 0.01);
        /** test if the result is 0 when kvector1 = needle */
        needle = kvector1;
        result = KielerMath.distanceFromSpline(kvector1, kvector2, kvector3, kvector4, needle);
        assertEquals(result, 0, 0.01);

    }

    /**
     * 
     * crossingCount is a private function
     * 
     * 
     */

    /**
     * 
     * bezier is a private function
     * 
     * 
     */

    /**
     * 
     * Tests some valid combinations of maxi from kielerMath class.
     * 
     */
    @SuppressWarnings("static-access")
    @Test
    public void testMax() {
        /** test if the max is 7 */
        assertEquals(kielerMath.maxi(1, 7, 5, 6), 7);
        assertEquals(kielerMath.maxf(1, 7, 5, 6), 7, 0);
        assertEquals(kielerMath.maxd(1, 7, 5, 6), 7, 0);

    }

    /**
     * 
     * Tests some valid combinations of mini,minf,mind from kielerMath class.
     * 
     */
    @SuppressWarnings("static-access")
    @Test
    public void testMin() {
        /** test if the mini is 1 */
        assertEquals(kielerMath.mini(1, 7, 5, 6), 1);
        /** test if the mini is 0 */
        assertEquals(kielerMath.mini(8, 1, 9, 0), 0);
        /** test if the mini is 8 */
        assertEquals(kielerMath.mini(8, 8, 8, 8), 8);
        /** test if the minf is 1 */
        assertEquals(kielerMath.minf(1, 7, 5, 6), 1, 0);
        /** test if the minf is 0 */
        assertEquals(kielerMath.minf(8, 1, 9, 0), 0, 0);
        /** test if the minf is 8 */
        assertEquals(kielerMath.minf(8, 8, 8, 8), 8, 0);
        /** test if the mind is 1 */
        assertEquals(kielerMath.mind(1, 7, 5, 6), 1, 0);
        /** test if the mind is 0 */
        assertEquals(kielerMath.mind(8, 1, 9, 0), 0, 0);
        /** test if the mind is 8 */
        assertEquals(kielerMath.mind(8, 8, 8, 8), 8, 0);

    }

    /**
     * 
     * Tests some valid combinations of averagei from kielerMath class.
     * 
     */
    @SuppressWarnings("static-access")
    @Test
    public void testAverage() {
        /** test if the averagei is 4 */
        assertEquals(kielerMath.averagei((long) 5, (long) 8, (long) 2, (long) 1), 4);
        /** test if the averagei is 2 */
        assertEquals(kielerMath.averagei((long) 5, (long) 0, (long) 2, (long) 1), 2);
        /** test if the averagef is 4 */
        assertEquals(kielerMath.averagef(5, 8, 2, 1), 4, 0);
        /** test if the averagef is 2 */
        assertEquals(kielerMath.averagef(5, 0, 2, 1), 2, 0);
        /** test if the averaged is 4 */
        assertEquals(kielerMath.averaged(5, 8, 2, 1), 4, 0);
        /** test if the averaged is 2 */
        assertEquals(kielerMath.averaged(5, 0, 2, 1), 2, 0);

    }

}
