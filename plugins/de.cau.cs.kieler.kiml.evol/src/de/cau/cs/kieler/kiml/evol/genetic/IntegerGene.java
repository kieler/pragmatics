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
 * Use this gene for integer data.
 * 
 * @author bdu
 * @deprecated
 */
@Deprecated
public class IntegerGene extends AbstractGene<Integer> {
    /**
     *
     * @param theId
     *            the id
     * @param theValue
     *            the value
     * @param theMutationProbability
     *            the mutation probability
     * @param theMutationVariance
     *            the mutation variance
     */
    public IntegerGene(
            final Object theId,
            final int theValue,
            final double theMutationProbability,
            final double theMutationVariance) {
        super(theId, theValue, DEFAULT_TYPE_INFO, new MutationInfo(theMutationProbability,
                theMutationVariance, Distribution.GAUSSIAN));
    }

    /**
     * Constructor for an IntegerGene instance with the given field values.
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
    protected IntegerGene(
            final Object theId,
            final int theValue,
            final TypeInfo<Integer> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
    }

    /**
     * Returns a copy of the given template instance, or {@code null}, if the
     * types are incompatible.
     *
     * @param template
     *            an instance. Must be of the same type as this instance,
     *            otherwise {@code null} is returned.
     * @return copy of the template, or {@code null}.
     */
    public IGene<Integer> newInstance(final AbstractGene<Integer> template) {
        if (template instanceof IntegerGene) {
            return new IntegerGene(template.getId(), template.getValue(), template.getTypeInfo(),
                    template.getMutationInfo());
        }
        // incompatible template
        return null;
    }

    /**
     * Return a new IGene instance that has a changed value with a certain
     * probability.
     *
     * @return new IGene with possibly mutated value.
     */
    public IGene<Integer> newMutation() {
        final IGene<Integer> result = newMutation(getMutationInfo());
        Assert.isNotNull(result);
        return result;
    }

    @Override
    public String toString() {
        // return getValue().toString();
        return getTypeInfo().getValueFormatter().getString(getValue());
    }

    /**
     * Formatter for integer values.
     */
    public static final IValueFormatter INTEGER_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Integer) {
                return o.toString();
            }
            return null;
        }
    };

    /**
     *
     * @param theMutationInfo
     *            a mutation info object that provides parameters for the
     *            mutation.
     * @return a new instance that is a mutation of this instance.
     */
    private IGene<Integer> newMutation(final MutationInfo theMutationInfo) {
        Assert.isLegal(theMutationInfo != null);
        Assert.isNotNull(getTypeInfo());
        final Random r = getRandomGenerator();
        final double prob = theMutationInfo.getProbability();
        final double var = theMutationInfo.getVariance();
        final Distribution distr = theMutationInfo.getDistr();
        final int lowerBound = getTypeInfo().getLowerBound();
        final int upperBound = getTypeInfo().getUpperBound();
        final int defaultValue = getTypeInfo().getDefaultValue();
        final int value = getValue();
        int newInt = value;
        if (r.nextDouble() < prob) {
            // TODO: regard genuineMutationProbability
            switch (distr) {
            case GAUSSIAN:
                do {
                    final double gauss = r.nextGaussian() * Math.sqrt(var);
                    final double newValue = (value + gauss);
                    newInt = (int) Math.round(newValue);
                } while ((newInt > upperBound) || (newInt < lowerBound));
                break;
            case UNIFORM:
                newInt = r.nextInt(upperBound - lowerBound + 1) + lowerBound;
                break;
            default:
                // execution should never reach this line.
                Assert.isTrue(false);
                newInt = defaultValue;
            }
        }
        return new IntegerGene(getId(), newInt, getTypeInfo(), theMutationInfo);
    }

    /**
     * Default type info for an Integer gene.
     */
    private static final TypeInfo<Integer> DEFAULT_TYPE_INFO = new TypeInfo<Integer>(0,
            Integer.MIN_VALUE, Integer.MAX_VALUE, INTEGER_FORMATTER, Integer.class);
}
