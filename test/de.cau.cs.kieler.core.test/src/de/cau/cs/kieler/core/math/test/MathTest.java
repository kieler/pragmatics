/**
 * 
 */
package de.cau.cs.kieler.core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.cau.cs.kieler.core.math.KielerMath;

/**
 * @author wah
 * 
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
