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
 */
public final class EnumGene extends AbstractGene<Integer> {

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newInstance(final AbstractGene<Integer> template) {
        if (template instanceof EnumGene) {
            return new EnumGene(template.getId(), template.getValue().intValue(),
                    this.getEnumClass(), getMutationInfo().getProbability());
        }
        // incompatible template
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newMutation() {
        EnumGene result = this.newMutation(this, getMutationInfo());
        return result;
    }

    /**
     *
     * @param theId
     *            the id
     * @param theValue
     *            the value
     * @param theEnumClass
     *            the enumeration class; must not be null.
     * @param theMutationProbability
     *            mutation probability
     */
    public EnumGene(
            final Object theId,
            final int theValue,
            final Class<? extends Enum<?>> theEnumClass,
            final double theMutationProbability) {
        super(theId, Integer.valueOf(theValue),
                new EnumTypeInfo(0, ENUM_FORMATTER, theEnumClass), new MutationInfo(
                        theMutationProbability, Distribution.UNIFORM));
        if (theEnumClass == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @return A string representation of this instance.
     */
    @Override
    public String toString() {
        Assert.isNotNull(getEnumClass());
        Enum<?>[] constants = getEnumClass().getEnumConstants();
        if (constants == null) {
            return "";
        }
        int value = getValue().intValue();
        assert (value >= 0) && (value < constants.length);

        String result = constants[value].toString();
        return result;
    }

    @Override
    public boolean equals(final Object theObj) {
        if (!(theObj instanceof EnumGene)) {
            return false;
        }

        EnumGene other = (EnumGene) theObj;

        return (other.getEnumClass().equals(this.getEnumClass())
                && (other.getId().equals(this.getId())) && other.getValue().equals(
                this.getValue()));
    }

    @Override
    public EnumTypeInfo getTypeInfo() {
        return (EnumTypeInfo) super.getTypeInfo();
    }

    @Override
    public int hashCode() {
        if (this.cachedHash == null) {
            final int f1 = 17881;
            final int f2 = 41;
            this.cachedHash =
                    (this.getEnumClass().hashCode() * f1 + this.getId().hashCode() * f2 + this
                            .getValue().hashCode());
        }

        return this.cachedHash;
    }

    private EnumGene newMutation(final EnumGene template, final MutationInfo mutationInfo) {
        return new EnumMutator().newMutation(template, mutationInfo);
    }

    /**
     * A gene factory that creates Enum mutations.
     *
     * @author bdu
     *
     */
    private static class EnumMutator {

        /**
         * Creates a new {@link EnumMutator} instance.
         *
         */
        public EnumMutator() {
            // Nothing to do here.
        }

        public EnumGene newMutation(final EnumGene template, final MutationInfo mutationInfo) {
            if ((template == null) || (mutationInfo == null)) {
                throw new IllegalArgumentException();
            }

            EnumTypeInfo typeInfo = template.getTypeInfo();
            assert typeInfo != null;

            Random random = template.getRandomGenerator();
            assert random != null;

            double prob = mutationInfo.getProbability();

            Distribution distr = mutationInfo.getDistr();
            assert (distr == Distribution.UNIFORM);

            Class<? extends Enum<?>> enumClass = typeInfo.getTypeClass();
            Integer lowerBound = typeInfo.getLowerBound();
            Integer upperBound = typeInfo.getUpperBound();
            Integer value = template.getValue();

            int newInt = value.intValue();
            if (random.nextDouble() < prob) {
                // Uniform distribution
                newInt = (random.nextInt((upperBound - lowerBound + 1)) + lowerBound);
            }
            return new EnumGene(template.getId(), newInt, enumClass, prob);
        }
    }


    private Class<? extends Enum<?>> getEnumClass() {
        return getTypeInfo().getTypeClass();
    }

    // private fields
    private Integer cachedHash;

    private static final IValueFormatter ENUM_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof EnumGene) {
                final Class<? extends Enum<?>> enumClass =
                        ((EnumGene) o).getTypeInfo().getTypeClass();

                final Enum<?>[] constants = enumClass.getEnumConstants();
                if (constants == null) {
                    return "";
                }
                final int value = ((EnumGene) o).getValue().intValue();
                Assert.isTrue((value >= 0) && (value < constants.length));
                final String result = constants[value].toString();
                return result;
            } else if (o instanceof UniversalNumberGene) {
                final Class<?> enumClass = ((UniversalNumberGene) o).getTypeInfo().getTypeClass();
                @SuppressWarnings("unchecked")
                final Enum<?>[] constants =
                        ((Class<? extends Enum<?>>) enumClass).getEnumConstants();

                final Integer value = Integer.valueOf(((UniversalNumberGene) o).getIntValue());

                if (constants == null) {
                    return value.toString();
                }
                Assert.isTrue((value.intValue() >= 0) && (value.intValue() < constants.length));
                final String result = constants[value.intValue()].toString();

                return result;
            }
            return null;
        }
    };

}
