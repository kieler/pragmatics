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
 * JUnit test for Mathematics utility class for the KIELER projects.
 * 
 * @author wah
 */
public class MathTest {

    KielerMath kielerMath;

    /**
     * Tests some valid combinations of Factl from kielerMath class.
     */
    @SuppressWarnings("static-access")
    @Test
    public void testFactl() {
        assertEquals(kielerMath.factl(0), 1);
        assertEquals(kielerMath.factl(1), 1);
        assertEquals(kielerMath.factl(20), 2432902008176640000L);

    }

    /**
     * Tests some the Little argument exception of Factl from kielerMath class.
     */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactlLittleIllegalArgumentException() {
        assertEquals(kielerMath.factl(-50), 1);
    }

    /**
     * Tests some the Big argument exception of Factl from kielerMath class.
     */
    @SuppressWarnings("static-access")
    @Test(expected = IllegalArgumentException.class)
    public void testFactlBigIllegalArgumentException() {
        assertEquals(kielerMath.factl(21), 1);
    }

}
