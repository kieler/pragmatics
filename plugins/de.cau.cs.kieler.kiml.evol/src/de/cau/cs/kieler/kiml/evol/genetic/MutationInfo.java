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
     * Default mutation probability.
     */
    public static final double DEFAULT_PROBABILITY = .1;

    /**
     * Default distribution.
     */
    private static final Distribution DEFAULT_DISTRIBUTION = Distribution.UNIFORM;

    /**
     * Default mutation variance for Gaussian distribution.
     */
    public static final double DEFAULT_VARIANCE = 1.0;

    /**
     * Creates a new {@link MutationInfo} instance with the given mutation
     * probability and default distribution.
     *
     * @param prob
     *            the probability that a mutation occurs.
     */
    public MutationInfo(final double prob) {
        this(prob, DEFAULT_VARIANCE, DEFAULT_DISTRIBUTION);
    }

    /**
     * Constructor for a mutation info.
     *
     * @param prob
     *            the probability that a mutation occurs.
     * @param distr
     *            the probability distribution
     */
    public MutationInfo(final double prob, final Distribution distr) {
        this(prob, DEFAULT_VARIANCE, distr);
    }

    /**
     * Constructor for a mutation info.
     * 
     * @param prob
     *            the probability that a mutation occurs.
     * @param var
     *            the variance (used for Gaussian distribution).
     * @param distr
     *            the probability distribution.
     */
    public MutationInfo(final double prob, final double var, final Distribution distr) {
        Assert.isLegal(distr != null);
        Assert.isLegal((prob >= 0.0) && (prob <= 1.0));
        Assert.isLegal(var >= 0.0);
        this.probability = prob;
        this.variance = var;
        this.distribution = distr;
    }

    private final Distribution distribution;

    private final double probability;
    // discuss: encapsulate parameters of distribution
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
