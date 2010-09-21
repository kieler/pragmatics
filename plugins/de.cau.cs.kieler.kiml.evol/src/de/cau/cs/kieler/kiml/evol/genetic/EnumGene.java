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
        final EnumGene result = this.newMutation(this, getMutationInfo());
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
        super(theId, Integer.valueOf(theValue), new EnumTypeInfo(Integer.valueOf(0),
                ENUM_FORMATTER, theEnumClass), new MutationInfo(theMutationProbability,
                Distribution.UNIFORM));
        Assert.isLegal(theEnumClass != null);
    }

    /**
     *
     * @return A string representation of this instance.
     */
    @Override
    public String toString() {
        Assert.isNotNull(getEnumClass());
        final Enum<?>[] constants = getEnumClass().getEnumConstants();
        if (constants == null) {
            return "";
        }
        final int value = getValue().intValue();
        Assert.isTrue((value >= 0) && (value < constants.length));
        final String result = constants[value].toString();
        return result;
    }

    @Override
    public boolean equals(final Object theObj) {
        if (!(theObj instanceof EnumGene)) {
            return false;
        }

        final EnumGene eg2 = (EnumGene) theObj;

        return (eg2.getEnumClass().equals(this.getEnumClass())
                && (eg2.getId().equals(this.getId())) && eg2.getValue().equals(this.getValue()));
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
                    Integer.valueOf((this.getEnumClass().hashCode() * f1
                            + this.getId().hashCode() * f2 + this.getValue().hashCode()));
        }

        return this.cachedHash.intValue();
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
            Assert.isLegal(template != null);
            Assert.isLegal(mutationInfo != null);

            if ((template == null) || (mutationInfo == null)) {
                return null;
            }

            final EnumTypeInfo typeInfo = template.getTypeInfo();
            Assert.isNotNull(typeInfo);

            final Random r = template.getRandomGenerator();
            Assert.isNotNull(r);

            final double prob = mutationInfo.getProbability();

            final Distribution distr = mutationInfo.getDistr();
            Assert.isTrue(distr == Distribution.UNIFORM);

            final Class<? extends Enum<?>> enumClass = typeInfo.getTypeClass();
            final Integer lowerBound = typeInfo.getLowerBound();
            final Integer upperBound = typeInfo.getUpperBound();
            final Integer value = template.getValue();

            int newInt = value.intValue();
            if (r.nextDouble() < prob) {
                // TODO: regard genuineMutationProbability
                // Uniform distribution
                newInt =
                        (r.nextInt((upperBound.intValue() - lowerBound.intValue() + 1)) + lowerBound
                                .intValue());
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
            } else if (o instanceof UniversalGene) {
                final Class<?> enumClass = ((UniversalGene) o).getTypeInfo().getTypeClass();
                @SuppressWarnings("unchecked")
                final Enum<?>[] constants =
                        ((Class<? extends Enum<?>>) enumClass).getEnumConstants();

                final Integer value = Integer.valueOf(((UniversalGene) o).getIntValue());

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
