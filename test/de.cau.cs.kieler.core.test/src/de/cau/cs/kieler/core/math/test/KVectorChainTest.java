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
import de.cau.cs.kieler.core.math.KVectorChain;

/**
 * Junit test-class for KVectorChain from de.cau.cs.kieler.core.math: Mathematics utility class for
 * the KIELER projects.
 * 
 */
public class KVectorChainTest {

    /**
     * 
     * Test parse from KVectorChain class.
     * 
     */
    @Test
    public void testParse() {
        KVector v0 = new KVector(5, 50);
        KVector v1 = new KVector(10, 50);
        KVector v2 = new KVector(30, 50);
        KVectorChain kv = new KVectorChain();
        kv.parse("{(5,50),(10,50),(30,50)}");

        assertTrue(v0.equals(kv.get(0)));
        assertTrue(v1.equals(kv.get(1)));
        assertTrue(v2.equals(kv.get(2)));

    }

    /** Tests the IllegalArgumentException of parse from KVectorChain class. */
    @Test(expected = IllegalArgumentException.class)
    public void testParseIllegalArgumentException() {
        KVectorChain kv = new KVectorChain();
        kv.parse("{(5,a),(10,50),(30,50)}");
    }

    /**
     * 
     * Test getLength from KVectorChain class. This function test if the length result ist 0 when
     * KVectorChain composed of 3 overlapping KVectors. And test for 3 differing KVectors.
     * 
     * 
     */
    @Test
    public void testGetLength() {
        // 3 overlaped KVectors
        KVectorChain kv = new KVectorChain();
        kv.parse("{(10,50),(10,50),(10,50)}");
        assertEquals(kv.getLength(), 0, 0);
        // 3 differing KVecors
        kv.parse("{(10,0),(10,20),(10,30)}");
        assertEquals(kv.getLength(), 30, 0);

    }

    /**
     * 
     * Test getPointOnLine from KVectorChain class.
     * 
     */
    @Test
    public void testGetPointOnLine() {
        KVector v0 = new KVector(5, 50);
        KVector v1 = new KVector(10, 50);
        KVector v2 = new KVector(30, 50);
        KVector[] vectors = { v0, v1, v2 };
        KVectorChain kv = new KVectorChain(vectors);
        // test if resturns v0 for distance = 0
        assertTrue(v0.equals(kv.getPointOnLine(0)));
        // test if resturns v1 for distance = 5
        assertTrue(v1.equals(kv.getPointOnLine(5)));
        // test if resturns endpoint  for distance > KVectorChain's length
        assertTrue(v2.equals(kv.getPointOnLine(40)));

    }

}
