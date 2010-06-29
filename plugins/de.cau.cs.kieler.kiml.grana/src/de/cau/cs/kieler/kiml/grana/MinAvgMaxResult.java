/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

/**
 * A helper class to store minimum, average and maximum in one object. This is
 * commonly used as result for analyses.
 * 
 * @author mri
 * 
 * @param <T>
 *            any class for minimum and maximum
 * @param <S>
 *            any class for the average
 */
public class MinAvgMaxResult<T, S> {

    /** the minimum. */
    private T min;
    /** the average. */
    private S avg;
    /** the maximum. */
    private T max;

    /**
     * Constructs a minimum/average/maximum result.
     * 
     * @param mi
     *            the minimum
     * @param av
     *            the average
     * @param ma
     *            the maximum
     */
    public MinAvgMaxResult(final T mi, final S av, final T ma) {
        min = mi;
        avg = av;
        max = ma;
    }

    /**
     * Returns the minimum.
     * 
     * @return the minimum
     */
    public T getMin() {
        return min;
    }

    /**
     * Returns the average.
     * 
     * @return the average
     */
    public S getAvg() {
        return avg;
    }

    /**
     * Returns the maximum.
     * 
     * @return the maximum
     */
    public T getMax() {
        return max;
    }
}
