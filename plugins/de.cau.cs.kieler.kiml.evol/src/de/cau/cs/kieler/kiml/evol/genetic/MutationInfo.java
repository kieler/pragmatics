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
package de.cau.cs.kieler.kiml.evol.genetic;

import org.eclipse.core.runtime.Assert;

/**
 * This class comprises the mutation parameters.
 * 
 * @author bdu
 * 
 */
public class MutationInfo {
    /**
     * Default probability for an enforced genuine mutation.
     */
    public static final double DEFAULT_GENUINE_MUTATION_PROBABILITY = 0.0;

    /**
     * Default mutation probability.
     */
    public static final double DEFAULT_PROBABILITY = .1;

    /**
     * Default mutation variance for Gaussian distribution.
     */
    public static final double DEFAULT_VARIANCE = 1.0;

    /**
     * Constructor for a mutation info.
     * 
     * @param prob
     *            the probability that a mutation occurs.
     * @param distr
     *            the probability distribution
     */
    public MutationInfo(final double prob, final Distribution distr) {
        this(prob, DEFAULT_VARIANCE, DEFAULT_GENUINE_MUTATION_PROBABILITY, distr);
    }

    /**
     * Constructor for a mutation info.
     * 
     * @param prob
     *            the probability that a mutation occurs.
     * @param var
     *            the variance (used for Gaussian distribution).
     * @param distr
     *            the probability distribution
     */
    public MutationInfo(final double prob, final double var, final Distribution distr) {
        this(prob, var, DEFAULT_GENUINE_MUTATION_PROBABILITY, distr);
    }

    /**
     * Constructor for a mutation info.
     * 
     * @param prob
     *            the probability that a mutation occurs.
     * @param var
     *            the variance (used for Gaussian distribution).
     * @param gprob
     *            The probability for an enforced genuine mutation. This
     *            determines how likely a genuine mutation shall be enforced,
     *            compared to a completely random mutation. Must be within
     *            {@code 0.0} and {@code 1.0}. An value of {@code 0.0} for this
     *            argument means that every time a mutation occurs, it might
     *            result in the same value as before. A value of {@code 1.0} for
     *            this argument means that every time a mutation occurs, it
     *            shall be ensured that it differs from the previous value.
     * @param distr
     *            the probability distribution.
     */
    public MutationInfo(final double prob, final double var, final double gprob,
            final Distribution distr) {
        Assert.isLegal(distr != null);
        Assert.isLegal((prob >= 0.0) && (prob <= 1.0));
        Assert.isLegal((gprob >= 0.0) && (gprob <= 1.0));
        Assert.isLegal(var >= 0.0);
        this.probability = prob;
        this.variance = var;
        this.genuineMutationProbability = gprob;
        this.distribution = distr;
    }

    private final Distribution distribution;

    private final double genuineMutationProbability;

    private final double probability;

    private final double variance;

    /**
     * 
     * @return the distribution.
     */
    public Distribution getDistr() {
        Assert.isNotNull(this.distribution);
        return this.distribution;
    }

    /**
     * 
     * @return the genuine mutation probability.
     */
    public double getGenuineMutationProbability() {
        return this.genuineMutationProbability;
    }

    /**
     * 
     * @return the mutation application probability.
     */
    public double getProbability() {
        return this.probability;
    }

    /**
     * 
     * @return the mutation variance.
     */
    public double getVariance() {
        return this.variance;
    }
}
