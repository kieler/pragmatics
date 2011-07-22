/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.alg;

/**
 * @author  bdu
 */
public class BoundMultipleCalculator {

    /**
     * Scaling factor.
     */
    private final double factor;

    /**
     * Lower bound.
     */
    private final int min;

    /**
     * Upper bound.
     */
    private final int max;

    /**
     * Creates a new {@link BoundMultipleCalculator} instance.
     *
     * @param theFactor
     *            scaling factor
     * @param theMin
     *            lower bound
     * @param theMax
     *            upper bound
     */
    public BoundMultipleCalculator(final double theFactor, final int theMin, final int theMax) {
        assert theMin <= theMax;
        this.factor = theFactor;
        this.min = theMin;
        this.max = theMax;
    }

    /**
     * Calculates a rounded multiple of the given number, and ensures that it is
     * within the given bounds.
     *
     * @param number
     *            the number to scale
     *
     * @return rounded fraction of the number, if it is within the bounds.
     *         Otherwise, the nearest of the bounds is returned.
     */
    public final int scale(final int number) {

        int proposal = (int) Math.round(number * this.factor);
        int result =
                (proposal < this.min) ? this.min : (proposal > this.max ? this.max : proposal);

        return result;
    }
}
