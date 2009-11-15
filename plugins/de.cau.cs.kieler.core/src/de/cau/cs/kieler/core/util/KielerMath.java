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
package de.cau.cs.kieler.core.util;

import java.util.List;

/**
 * Mathematics utility class for the KIELER projects.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public final class KielerMath {
    
    /**
     * Hidden constructor to avoid instantiation.
     */
    private KielerMath() {
    }
    
    /** Data class to store two coordinate values. */
    // CHECKSTYLEOFF VisibilityModifier
    public static class Point {
        /** x coordinate. */
        public double x;
        /** y coordinate. */
        public double y;

        /**
         * Creates a point given two coordinates.
         * 
         * @param thex the x coordinate
         * @param they the y coordinate
         */
        public Point(final double thex, final double they) {
            this.x = thex;
            this.y = they;
        }
    }
    // CHECKSTYLEON VisibilityModifier
    
    /** table of precomputed factorial values. */
    private static final long[] FACT_TABLE = {
        1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L,
        39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L
    };
    
    /**
     * The factorial of an integer x.
     * 
     * @param x an integer
     * @return the factorial of x
     */
    public static long fact(final int x) { 
        long result = 1;
        for (int i = x; i > 1; i--) {
            if (i < FACT_TABLE.length && i >= 0) {
                return result * FACT_TABLE[i];
            }
            result *= i;
        }
        return result;
    }
    
    /**
     * The binomial coefficient of integers n and k.
     * 
     * @param n the upper integer
     * @param k the lower integer
     * @return n choose k
     */
    public static int binomial(final int n, final int k) {
        if (n < FACT_TABLE.length) {
            long result = fact(n) / (fact(k) * fact(n - k));
            return (int) result;
        } else {
            return binomial(n - 1, k - 1) + binomial(n - 1, k);
        }
    }
    
    /**
     * The first argument raised to the power of the second argument.
     * 
     * @param a the base
     * @param b the exponent
     * @return a to the power of b
     */
    public static double pow(final double a, final int b) {
        double result = 1.0, base = a;
        int exp = (b >= 0 ? b : -b);
        while (exp > 0) {
            if (exp % 2 == 0) {
                base *= base;
                exp /= 2;
            } else {
                result *= base;
                exp -= 1;
            }
        }
        if (b < 0) {
            return 1.0 / result;
        } else {
            return result;
        }
    }
    
    /**
     * Calculates a number of points on the Bezier curve defined by the given control points.
     * The degree of the curve is derived from the number of control points. The array of
     * resulting curve points includes the target point, but does not include the source point
     * of the curve.
     * 
     * @param controlPoints list of control points
     * @param resultSize number of returned curve points
     * @return points on the curve defined by the given control points
     */
    public static Point[] calcBezierPoints(final List<Point> controlPoints, final int resultSize) {
        if (resultSize <= 0) {
            return new Point[0];
        }
        Point[] result = new Point[resultSize];
        int n = controlPoints.size() - 1;
        double dt = (1.0 / resultSize), t = 0;
        for (int i = 0; i < resultSize; i++) {
            t += dt;
            double x = 0, y = 0;
            for (int j = 0; j <= n; j++) {
                Point p = controlPoints.get(j);
                double factor = binomial(n, j) * pow(1 - t, n - j) * pow(t, j);
                x += p.x * factor;
                y += p.y * factor;
            }
            result[i] = new Point(x, y);
        }
        return result;
    }

}
