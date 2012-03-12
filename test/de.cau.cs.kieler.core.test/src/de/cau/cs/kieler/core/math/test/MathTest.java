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
import de.cau.cs.kieler.core.math.KielerMath;

/**
 * Mathematics utility class for the KIELER projects.
 * 
 * @author wah
 */
public class MathTest {

    KielerMath kielerMath;

    
    /**
     *
     * Tests some valid combinations 
     * of Factl from kielerMath class.
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
        assertEquals(kielerMath.factl(-50), 1);
    }

    /** Tests the Big argument exception of Factl from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactlBigIllegalArgumentException() {
        assertEquals(kielerMath.factl(21), 1);
    }

    
    /**
    *
    * Tests some valid combinations 
    * of Factd from kielerMath class.
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
        assertEquals(kielerMath.factd(-1), 1, 0);
    }
    
    /** Tests the Big argument exception of Factd from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactdBigIllegalArgumentException() {
        assertEquals(kielerMath.factd(27), 1,0);
    }
    
    /**
    *
    * Tests some valid combinations 
    * of binomiall from kielerMath class.
    *
    */
    
    /** Tests some valid combinations of binomiall from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testBinomiall() {
        assertEquals(kielerMath.binomiall(2,0), 1);
        assertEquals(kielerMath.binomiall(20,20), 1);
        assertEquals(kielerMath.binomiall(2,1), 2);
    }

    /** Tests the Little argument exception of binomiall from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testBinomialllLittleIllegalArgumentException() {
        assertEquals(kielerMath.binomiall(-1,1),0);
        assertEquals(kielerMath.binomiall(1,-1),0);
        assertEquals(kielerMath.binomiall(2,5),0);
    }
    
    /**
    *
    * Tests some valid combinations 
    * of binomiald from kielerMath class.
    *
    */
    
    /** Tests some valid combinations of binomiald from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test
    public void testBinomiald() {
        assertEquals(kielerMath.binomiald(2,0), 1,0);
        assertEquals(kielerMath.binomiald(20,20), 1,0);
        assertEquals(kielerMath.binomiald(2,1), 2,0);
    }

    /** Tests the Little argument exception of binomiald from kielerMath class. */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testBinomialdLittleIllegalArgumentException() {
        assertEquals(kielerMath.binomiald(-1,1),0,0);
        assertEquals(kielerMath.binomiald(1,-1),0,0);
        assertEquals(kielerMath.binomiald(2,5),0,0);
    }
    
    /**
    *
    * Tests some valid combinations 
    * of pow from kielerMath class.
    *
    */
    @SuppressWarnings("static-access")
    @Test
    public void testPow() {
        assertEquals(kielerMath.pow(20,0), 1,0);
        assertEquals(kielerMath.pow(10,2), 100,0);
        
    }
    
    /**
    *
    * Tests some valid combinations 
    * of CalcBezierPoints from kielerMath class.
    *
    */
    @SuppressWarnings("static-access")
    @Test
    public void testCalcBezierPoints() {
        /** some KVectors */
        KVector kvector1 = new KVector(10,10);
        KVector kvector2 = new KVector(20,20);
        KVector kvector3 = new KVector(30,30);
        KVector kvector4 = new KVector(50,50);
        /** test if the last KVector  of the result similar to kvector4*/
        KVector[] result = kielerMath.calcBezierPoints(20,kvector1,kvector2,kvector3,kvector4);
        assertEquals(result[result.length-1].x, kvector4.x,0.000000001);
        assertEquals(result[result.length-1].y, kvector4.y,0.000000001);
        
        /** some KVectors with y=10 */
        kvector1 = new KVector(50,10);
        kvector2 = new KVector(70,10);
        kvector3 = new KVector(80,10);
        kvector4 = new KVector(100,10);
        /** test if the all result-KVectors with y=10*/
        result = kielerMath.calcBezierPoints(20,kvector1,kvector2,kvector3,kvector4);
        for(KVector k:result){
            assertEquals(k.y, 10,0.000000001);
        }
        
    }
    
}
