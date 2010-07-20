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
 *
 * @author bdu
 *
 */
public class UniversalGene extends AbstractGene<Float> {
    @Override
    public String toString() {
        return this.getId() + ": " + this.getValue();
    }

    /**
     * Creates a new gene.
     *
     * @param theId
     *            an id
     * @param theValue
     *            a value
     * @param theTypeInfo
     *            a type info
     * @param theMutationInfo
     *            a mutation info
     */
    public UniversalGene(
            final Object theId,
            final Comparable<?> theValue,
            final TypeInfo<Float> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, (Float) theValue, theTypeInfo, theMutationInfo);

    }

    /**
     * {@inheritDoc}
     */
    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        final IGene<Float> result =
                new UniversalGene(template.getId(), template.getValue(), template.getTypeInfo(),
                        template.getMutationInfo());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Float> newMutation() {
        final IGene<Float> result = newMutation(getMutationInfo());
        return result;
    }

    /**
     * Mutates the value. The mutation details are specified in
     * <code>theMutationInfo</code>.
     *
     * @return a new IGeneData with the mutated value.
     */
    private IGene<Float> newMutation(final MutationInfo theMutationInfo) {
        final Class<?> clazz = getTypeInfo().getTypeClass();
        if (clazz == Boolean.class) {
            return new BooleanMutator().newMutation(this, theMutationInfo);
        } else if (clazz == Float.class) {
            return new FloatMutator().newMutation(this, theMutationInfo);
        }
        return null;
    }

    /**
     * Interface for a factory that creates mutated genes.
     *
     * @author bdu
     *
     */
    private interface IMutator {
        /**
         *
         * @param theTemplate
         *            the template
         * @param theMutationInfo
         *            the mutation info
         * @return a new gene
         */
        IGene<Float> newMutation(
                final IGene<Float> theTemplate, final MutationInfo theMutationInfo);
    }

    /**
     * A gene factory that creates boolean mutations.
     *
     * @author bdu
     *
     */
    private class BooleanMutator implements IMutator {
        public IGene<Float> newMutation(
                final IGene<Float> template, final MutationInfo theMutationInfo) {
            Assert.isNotNull(theMutationInfo);
            Assert.isNotNull(getTypeInfo());

            final Random r = getRandomGenerator();
            Assert.isNotNull(r);

            // get mutation parameters
            final double prob = theMutationInfo.getProbability();
            final double probGenuineMutation = theMutationInfo.getGenuineMutationProbability();
            final Distribution distr = theMutationInfo.getDistr();
            Assert.isTrue(distr == Distribution.UNIFORM);

            final Float newValue;
            if (r.nextDouble() < prob) {
                if (r.nextDouble() < probGenuineMutation) {
                    // enforce genuine mutation
                    newValue = (getBoolValue() ? 0.0f : 1.0f);
                } else {
                    // assign a random boolean value (may be the same as before)
                    newValue = (r.nextDouble() < BooleanGene.PROBABILITY_FOR_TRUE ? 1.0f : 0.0f);
                }
            } else {
                newValue = template.getValue();
            }
            return new UniversalGene(getId(), newValue, getTypeInfo(), theMutationInfo);
        }
    }

    /**
     * A gene factory that creates float mutations.
     *
     * @author bdu
     *
     */
    private class FloatMutator implements IMutator {
        public IGene<Float> newMutation(
                final IGene<Float> theTemplate, final MutationInfo theMutationInfo) {
            Assert.isLegal(getMutationInfo() != null);
            Assert.isNotNull(getTypeInfo());
            final Random r = getRandomGenerator();
            final double prob = getMutationInfo().getProbability();
            final double var = getMutationInfo().getVariance();
            final Distribution distr = getMutationInfo().getDistr();
            Assert.isTrue(distr == Distribution.GAUSSIAN);
            final TypeInfo<Float> typeInfo = getTypeInfo();
            final float value = getValue();
            float newValue = value;
            if (Math.random() < prob) {
                // produce a new value within the valid bounds.
                do {
                    final double gauss = r.nextGaussian() * Math.sqrt(var);
                    // TODO: regard genuineMutationProbability
                    newValue = (float) (value + gauss);
                } while (!typeInfo.isValueWithinBounds(newValue));
            }
            return new FloatGene(getId(), newValue, getTypeInfo(), getMutationInfo());
        }
    }

    /**
     *
     * @return an integer representation of the value.
     */
    public Integer getIntValue() {
        return Math.round(getValue());
    }

    /**
     *
     * @return a boolean representation of the value.
     */
    public Boolean getBoolValue() {
        final double diff = 1.0 - Math.abs(getValue());
        return ((Math.pow(diff, 2) < EPSILON));
    }

    private static final double EPSILON = 1.0e-5;

}
