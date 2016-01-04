/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.compaction.recthull;

import java.util.Objects;

import de.cau.cs.kieler.core.math.KVector;

/**
 * A very special point, used by the {@link RectilinearConvexHull} algorithm.
 * 
 * @author uru
 */
public class Point {

    // SUPPRESS CHECKSTYLE NEXT 10 VisibilityModifier
    /** The x coordinate. */
    public double x;
    /** The y coordinate. */
    public double y;

    /** The quadrant this point is located in. */
    public Quadrant quadrant;
    /** Whether the point represents a convex point in the rectilinear convex hull. */
    public boolean convex = true;

    /**
     * Constructs a new point.
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new point with is associated with a quadrant.
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     * @param quadrant
     *            one of Q1 to Q4
     */
    public Point(final double x, final double y, final Quadrant quadrant) {
        this(x, y);
        this.quadrant = quadrant;
    }

    /**
     * @param v
     *            a {@link KVector}
     * @return for the passed vector v returns a new point (v.x, v.y)
     */
    public static Point from(final KVector v) {
        return new Point(v.x, v.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + (convex ? "cx" : "") + quadrant + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Point p2 = (Point) obj;
        return Objects.equals(x, p2.x) && Objects.equals(y, p2.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Represents a quadrant of a plane split into four parts.
     * 
     * <pre>
     *  Q1 | Q2 
     * --------- 
     *  Q4 | Q3
     * </pre>
     */
    public enum Quadrant {

        // SUPPRESS CHECKSTYLE NEXT 30 Javadoc
        // order importatnt!
        Q1, Q4, Q2, Q3;

        public boolean isUpper() {
            return this == Q1 || this == Q2;
        }

        public boolean isLeft() {
            return this == Q1 || this == Q4;
        }

        /**
         * @return true if both q1 and q2 are either located in Q1 and Q4 or are located in Q2 and
         *         Q3.
         */
        public static boolean isBothLeftOrBothRight(final Quadrant q1, final Quadrant q2) {
            return (q1 == Quadrant.Q1 && q2 == Quadrant.Q4)
                    || (q1 == Quadrant.Q4 && q2 == Quadrant.Q1)
                    || (q1 == Quadrant.Q3 && q2 == Quadrant.Q2)
                    || (q1 == Quadrant.Q2 && q2 == Quadrant.Q3);
        }

        /**
         * @return true if one of q1 and q2 is located in Q1 or Q4 and the other one is located in
         *         Q2 or Q3.
         */
        public static boolean isOneLeftOneRight(final Quadrant q1, final Quadrant q2) {
            return (q1 == Quadrant.Q1 && q2 == Quadrant.Q2)
                    || (q1 == Quadrant.Q1 && q2 == Quadrant.Q3)
                    || (q1 == Quadrant.Q4 && q2 == Quadrant.Q3)
                    || (q1 == Quadrant.Q4 && q2 == Quadrant.Q2);
        }
    }
}
