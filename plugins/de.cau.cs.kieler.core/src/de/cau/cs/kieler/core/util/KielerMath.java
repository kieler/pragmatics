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
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class KielerMath {
    
    /**
     * Hidden constructor to avoid instantiation.
     */
    private KielerMath() {
    }
    
    /** Data class to store two coordinate values. */
    public static class Point {
        // CHECKSTYLEOFF VisibilityModifier
        /** x coordinate. */
        public double x;
        /** y coordinate. */
        public double y;
        // CHECKSTYLEON VisibilityModifier

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
        
        /**
         * {@inheritDoc}
         */
        public String toString() {
            return "(" + x + "," + y + ")";
        }
        
        /**
         * {@inheritDoc}
         */
        public boolean equals(final Object obj) {
            if (obj instanceof Point) {
                Point other = (Point) obj;
                return this.x == other.x && this.y == other.y;
            } else {
                return false;
            }
        }
    
        /**
         * {@inheritDoc}
         */
        public int hashCode() {
            return Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
        }
    }
    
    /** table of precomputed factorial values. */
    private static final long[] FACT_TABLE = {
        1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 362880L, 3628800L,
        39916800L, 479001600L, 6227020800L, 87178291200L, 1307674368000L
    };
    
    /**
     * The factorial of an integer x as long value. If x is negative the result is 1.
     * This method always returns the exact value, but may lead to overflow for
     * large input values.
     * 
     * @param x an integer
     * @return the factorial of x
     */
    public static long factl(final int x) { 
        long result = 1;
        for (int i = x; i > 1; i--) {
            if (i < FACT_TABLE.length) {
                return result * FACT_TABLE[i];
            }
            result *= i;
        }
        return result;
    }
    
    /**
     * The factorial of an integer x as double value. If x is negative the result is 1.
     * This method returns the exact value for small input values, and uses
     * Stirling's approximation for large input values.
     * 
     * @param x an integer
     * @return the factorial of x
     */
    public static double factd(final int x) {
        if (x < 0) {
            return 1;
        } else if (x < FACT_TABLE.length) {
            return FACT_TABLE[x];
        } else {
            return Math.sqrt(2.0 * Math.PI * x) * (pow(x, x) / pow(Math.E, x));
        }
    }
    
    /**
     * The binomial coefficient of integers n and k as long value. If n is not
     * positive or k is not between 0 and n the result is 1. This method
     * always returns the exact value, but may take very long for large
     * input values.
     * 
     * @param n the upper integer
     * @param k the lower integer
     * @return n choose k
     */
    public static long binomiall(final int n, final int k) {
        if (n <= 0 || k <= 0 || k >= n) {
            return 1;
        } else if (n < FACT_TABLE.length) {
            return factl(n) / (factl(k) * factl(n - k));
        } else {
            return binomiall(n - 1, k - 1) + binomiall(n - 1, k);
        }
    }
    
    /**
     * The binomial coefficient of integers n and k as double value. If n is not
     * positive or k is not between 0 and n the result is 1. This method returns
     * the exact value for small input values, and uses an approximation for
     * large input values.
     * 
     * @param n the upper integer
     * @param k the lower integer
     * @return n choose k
     */
    public static double binomiald(final int n, final int k) {
        if (n <= 0 || k <= 0 || k >= n) {
            return 1;
        } else {
            return factd(n) / (factd(k) * factd(n - k));
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
                double factor = binomiald(n, j) * pow(1 - t, n - j) * pow(t, j);
                x += p.x * factor;
                y += p.y * factor;
            }
            result[i] = new Point(x, y);
        }
        return result;
    }
    
    /**
     * Determines the maximum for an arbitrary number of integers.
     * 
     * @param values integer values
     * @return the maximum of the given values, or {@code MIN_VALUE} if no values are given
     */
    public static int maxi(final int ... values) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
    
    /**
     * Determines the minimum for an arbitrary number of integers.
     * 
     * @param values integer values
     * @return the minimum of the given values, or {@code MAX_VALUE} if no values are given
     */
    public static int mini(final int ... values) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }
    
    /**
     * Determines the maximum for an arbitrary number of floats.
     * 
     * @param values float values
     * @return the maximum of the given values, or {@code -MAX_VALUE} if no values are given
     */
    public static float maxf(final float ... values) {
        float max = -Float.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
    
    /**
     * Determines the minimum for an arbitrary number of floats.
     * 
     * @param values float values
     * @return the minimum of the given values, or {@code MAX_VALUE} if no values are given
     */
    public static float minf(final float ... values) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }
    
    /**
     * Determines the maximum for an arbitrary number of doubles.
     * 
     * @param values double values
     * @return the maximum of the given values, or {@code -MAX_VALUE} if no values are given
     */
    public static double maxd(final double ... values) {
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }
    
    /**
     * Determines the minimum for an arbitrary number of doubles.
     * 
     * @param values double values
     * @return the minimum of the given values, or {@code MAX_VALUE} if no values are given
     */
    public static double mind(final double ... values) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }

}
