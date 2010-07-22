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

import java.util.Random;

import org.eclipse.core.runtime.Assert;

/**
 * Implementation of IGene for Boolean values.
 *
 * @author bdu
 * @deprecated
 */
@Deprecated
public final class BooleanGene extends AbstractGene<Boolean> {
    /**
     * Returns a copy of the given template instance.
     *
     * @param template
     *            an instance
     * @return copy of the template
     */
    public IGene<Boolean> newInstance(final AbstractGene<Boolean> template) {
        if (template instanceof BooleanGene) {
            return new BooleanGene(template.getId(), template.getValue(), template
                    .getMutationInfo().getProbability());
        }
        // incompatible template
        return null;
    }

    /**
     * Return a new IGene that has a changed value with a certain probability.
     *
     * @return new IGene with possibly mutated value.
     */
    public IGene<Boolean> newMutation() {
        final IGene<Boolean> result = newMutation(getMutationInfo());
        return result;
    }

    /**
     *
     * @param theId
     *            the id for the gene
     * @param theValue
     *            the value to be stored in the gene
     * @param theMutationProbability
     *            the mutation probability
     */
    public BooleanGene(
            final Object theId,
            final boolean theValue,
            final double theMutationProbability) {
        super(theId, theValue, DEFAULT_TYPE_INFO, new MutationInfo(theMutationProbability,
                MutationInfo.DEFAULT_VARIANCE, PROBABILITY_FOR_GENUINE_MUTATION,
                Distribution.UNIFORM));
    }

    /**
     *
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return getValue().toString();
    }

    /**
     * Mutates the boolean value. The mutation details are specified in
     * <code>theMutationInfo</code>.
     *
     * @param prob
     *            Probability that a mutation occurs.
     * @param probGenuineMutation
     *            Probability that a genuine mutation is enforced.
     * @return a new IGeneData with the mutated value.
     */
    private IGene<Boolean> newMutation(final MutationInfo theMutationInfo) {
        Assert.isLegal(theMutationInfo != null);
        final Random r = getRandomGenerator();
        Assert.isNotNull(r);
        Assert.isNotNull(getTypeInfo());
        final boolean value = getValue();
        Boolean newValue = value;
        final double prob = theMutationInfo.getProbability();
        final double probGenuineMutation = theMutationInfo.getGenuineMutationProbability();
        final Distribution distr = theMutationInfo.getDistr();
        Assert.isTrue(distr == Distribution.UNIFORM);
        if (r.nextDouble() < prob) {
            if (r.nextDouble() < probGenuineMutation) {
                // enforce genuine mutation
                newValue = !value;
            } else {
                // assign a random boolean value (may be the same as before)
                newValue = (r.nextDouble() < PROBABILITY_FOR_TRUE);
            }
        }
        return new BooleanGene(getId(), newValue, getTypeInfo(), theMutationInfo);
    }

    /**
     *
     * @param theId
     *            the id
     * @param theValue
     *            the value
     * @param theTypeInfo
     *            the type info
     * @param theMutationInfo
     *            the mutation info
     */
    private BooleanGene(
            final Object theId,
            final boolean theValue,
            final TypeInfo<Boolean> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
    }

    private static final double PROBABILITY_FOR_TRUE = 0.5;
    private static final double PROBABILITY_FOR_GENUINE_MUTATION = 0.0;

    private static final IValueFormatter BOOLEAN_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Boolean) {
                return o.toString();
            } else if (o instanceof UniversalGene) {
                return ((UniversalGene) o).getBoolValue().toString();
            }
            return null;
        }
    };
    private static final TypeInfo<Boolean> DEFAULT_TYPE_INFO = new TypeInfo<Boolean>(Boolean.FALSE,
            Boolean.FALSE, Boolean.TRUE, BOOLEAN_FORMATTER, Boolean.class);

}
