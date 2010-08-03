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
            return new EnumGene(template.getId(), template.getValue(), this.enumClass,
                    getMutationInfo().getProbability());
        }
        // incompatible template
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newMutation() {
        final EnumGene result = this.newMutation(this, getMutationInfo());
        // final IGene<Integer> result =
        // new EnumGene(template.getId(), template.getValue(),
        // template.enumClass,
        // getMutationInfo().getProbability());
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
        super(theId, theValue, new TypeInfo<Integer>(0, 0, choicesCount(theEnumClass) - 1,
                ENUM_FORMATTER, theEnumClass), new MutationInfo(theMutationProbability,
                Distribution.UNIFORM));
        Assert.isLegal(theEnumClass != null);
        setEnumClass(theEnumClass);
    }

    /**
     *
     * @return A string representation if this instance.
     */
    @Override
    public String toString() {
        Assert.isNotNull(this.enumClass);
        final Enum<?>[] constants = this.enumClass.getEnumConstants();
        if (constants == null) {
            return "";
        }
        final int value = getValue();
        Assert.isTrue((value >= 0) && (value < constants.length));
        final String result = constants[value].toString();
        return result;
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

            final TypeInfo<Integer> typeInfo = template.getTypeInfo();
            Assert.isNotNull(typeInfo);

            final Random r = template.getRandomGenerator();
            Assert.isNotNull(r);

            final double prob = mutationInfo.getProbability();
            final double var = mutationInfo.getVariance();

            final Distribution distr = mutationInfo.getDistr();
            final Class<? extends Enum<?>> clazz =
                    (Class<? extends Enum<?>>) typeInfo.getTypeClass();
            final Integer lowerBound = typeInfo.getLowerBound().intValue();
            final Integer upperBound = typeInfo.getUpperBound().intValue();
            final Integer defaultValue = typeInfo.getDefaultValue().intValue();
            final Integer value = template.getValue().intValue();

            Integer newInt = value;
            if (r.nextDouble() < prob) {
                // TODO: regard genuineMutationProbability
                switch (distr) {
                case GAUSSIAN:
                    do {
                        // produce a new value within the valid bounds.
                        final double gauss = r.nextGaussian() * Math.sqrt(var);
                        final double newValue = (value + gauss);
                        newInt = (int) Math.round(newValue);
                    } while (!typeInfo.isValueWithinBounds(newInt));
                    break;
                case UNIFORM:
                    newInt =
                            (r.nextInt((upperBound.intValue() - lowerBound.intValue() + 1)) + lowerBound
                                    .intValue());
                    break;
                default:
                    // execution should never reach this line.
                    Assert.isTrue(false);
                    newInt = defaultValue;
                }
            }
            return new EnumGene(template.getId(), newInt, clazz, prob);
        }
    }

    /**
     * Determines the number of possible choices in the given enumeration.
     *
     * @param theEnumClass
     *            an enumeration class
     * @return number of choices in the given enumeration.
     */
    private static int choicesCount(final Class<? extends Enum<?>> theEnumClass) {
        Assert.isLegal(theEnumClass != null);
        if (theEnumClass == null) {
            return 0;
        }
        if (theEnumClass.getEnumConstants() == null) {
            return 0;
        }
        return theEnumClass.getEnumConstants().length;
    }

    private void setEnumClass(final Class<? extends Enum<?>> theEnumClass) {
        this.enumClass = theEnumClass;
    }

    // private fields
    private Class<? extends Enum<?>> enumClass;

    private static final IValueFormatter ENUM_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof EnumGene) {
                Assert.isNotNull(((EnumGene) o).enumClass);
                final Enum<?>[] constants = ((EnumGene) o).enumClass.getEnumConstants();
                if (constants == null) {
                    return "";
                }
                final int value = ((EnumGene) o).getValue();
                Assert.isTrue((value >= 0) && (value < constants.length));
                final String result = constants[value].toString();
                return result;
            } else if (o instanceof UniversalGene) {
                // TODO: get enum string
                final Integer intValue = ((UniversalGene) o).getIntValue();
                return intValue.toString();
            }
            return null;
        }
    };
}
