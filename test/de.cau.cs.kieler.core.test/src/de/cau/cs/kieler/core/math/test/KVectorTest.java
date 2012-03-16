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

import de.cau.cs.kieler.core.math.KVector;

/**
 * Junit test-class for KVector from de.cau.cs.kieler.core.math: Mathematics utility class for the
 * KIELER projects.
 * 
 * @author wah
 * 
 */
public class KVectorTest {

    /**
     * 
     * Test equals from KVector class.
     * 
     */
    @Test
    public void testEquals() {
        // init 2 KVectors
        KVector kvector1;
        KVector kvector2;
        kvector1 = new KVector(10, 10);
        kvector2 = new KVector();
        kvector2.x = 10;
        kvector2.y = 10;
        // test if kvector1 equals to kvector2
        assertTrue(kvector1.equals(kvector2));
        // init obj
        kvector1 = new KVector();
        Object obj = new Object();
        // test if kvector1 not equals obj
        assertFalse(kvector1.equals(obj));

    }

    /**
     * 
     * Test add and sub from KVector class.
     * 
     */
    @Test
    public void testAddAndSub() {
        // init 2 KVectors
        KVector kvector1;
        KVector kvector2;
        kvector1 = new KVector(12, 70);
        kvector2 = new KVector(15, 17);
        // test Add and Sub functions
        // adding and subtracting the kvector2 to kvector1 = kvector1
        assertTrue(kvector1.equals(kvector1.add(kvector2).sub(kvector2)));

    }

    /**
     * 
     * Test scale from KVector class.
     * 
     */
    @Test
    public void testScale() {
        // init KVectors
        KVector a = new KVector(12, 70);
        KVector b = new KVector(12, 70);

        KVector a_temp = new KVector(12, 70);

        a = a.add(a_temp).add(a_temp);
        b = b.scale(3);
        // test Scale functions
        //
        assertTrue(a.equals(b));

    }

    /**
     * 
     * Test translate from KVector class.
     * 
     */
    @Test
    public void testTranslate() {
        // init KVector
        KVector v = new KVector(10, 30);
        KVector b = new KVector(50, 50);
        assertTrue(b.equals(v.translate(40, 20)));

    }

    /**
     * 
     * Test normalize from KVector class.
     * 
     */
    // @Test
    // public void testNormalize() {
    // // init a KVectors
    // KVector v = new KVector(1, 2);
    // KVector n = new KVector(1, 2).normalize();
    // assertTrue(v.equals(n));
    //
    // }

    /**
     * 
     * Test toDegrees from KVector class.
     * 
     */
    @Test
    public void testToDegrees() {
        // init a KVector
        // test the 45Degree
        KVector v = new KVector(10, 10);
        assertEquals(v.toDegrees(), 45, 0.00001);
        // test the 0 Degree
        v = new KVector(0, 50);
        assertEquals(v.toDegrees(), 0, 0.00001);
        // test the 90 Degree
        v = new KVector(50, 0);
        assertEquals(v.toDegrees(), 90, 0.00001);
        // test the 180 Degree
        v = new KVector(0, -50);
        assertEquals(v.toDegrees(), 180, 0.00001);
        // test the 270 Degree
        v = new KVector(-50, 0);
        assertEquals(v.toDegrees(), 270, 0.00001);

    }

    /**
     * 
     * Test distance from KVector class.
     * 
     */
    @Test
    public void testDistance() {
        // init 2 KVectors
        KVector v1 = new KVector(5, 50);
        KVector v2 = new KVector(5, 50);
        assertEquals(v1.distance(v2), 0, 0);
        v1 = new KVector(0, 20);
        v2 = new KVector(0, 50);
        assertEquals(v1.distance(v2), 30, 0);
    }

    /**
     * 
     * Test parse from KVector class.
     * 
     */
    @Test
    public void testParse() {
        // init KVector
        KVector v1 = new KVector(5, 50);
        KVector v2 = new KVector();
        v2.parse("(5,50)");
        v2.parse("{5,50}");
        assertTrue(v1.equals(v2));
        v2.parse("[5,50]");
        assertTrue(v1.equals(v2));
        v2.parse("{(5,50)}");
        assertTrue(v1.equals(v2));
        assertTrue(v1.equals(v2));
        v2.parse("[(5,50)]");
        assertTrue(v1.equals(v2));
        v2.parse("[{5,50}]");
        assertTrue(v1.equals(v2));
    }

    /**
     * 
     * Test applyBounds from KVector class.
     * 
     */
    @Test
    public void testApplyBounds() {
        /**
         * 
         * test if vt.x > lowx and vt.y > lowy (the result must be the same vt)
         * 
         */
        // init KVector v
        KVector v = new KVector(30, 30);
        // init KVector vt = v
        KVector vt = v;
        // init lower bound KVector v_lower_bound
        KVector v_lower_bound = new KVector(10, 10);
        // init upper bound KVector v_upper_bound
        KVector v_upper_bound = new KVector(40, 40);
        // test if vt.x > lowx (the result must be the same vt)
        vt.applyBounds(v_lower_bound.x, v_lower_bound.y, v_upper_bound.x, v_upper_bound.y);
        assertTrue(vt.equals(v));
        /**
         * 
         * test if vt.x < lowx and vt.y < lowy (the result must be the vt(lowx,lowy))
         * 
         */
        // reinitialize vt
        vt = v;
        // init lower bound KVector v_lower_bound
        v_lower_bound = new KVector(40, 40);
        // init upper bound KVector v_upper_bound
        v_upper_bound = new KVector(60, 60);
        // test if vt.x < lowx and vt.y < lowy (the result must be the vt(lowx,lowy))
        vt.applyBounds(v_lower_bound.x, v_lower_bound.y, v_upper_bound.x, v_upper_bound.y);
        assertTrue(vt.equals(v_lower_bound));
        /**
         * 
         * test if vt.x > highx and vt.y > highy (the result must be the vt(highx,highy))
         * 
         */
        // reinitialize vt
        vt = v;
        // init lower bound KVector v_lower_bound
        v_lower_bound = new KVector(20, 20);
        // init upper bound KVector v_upper_bound
        v_upper_bound = new KVector(30, 30);
        // test if vt.x > highx and vt.y > highy (the result must be the vt(highx,highy))
        vt.applyBounds(v_lower_bound.x, v_lower_bound.y, v_upper_bound.x, v_upper_bound.y);
        assertTrue(vt.equals(v_upper_bound));
    }

}
