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
    
    
}
