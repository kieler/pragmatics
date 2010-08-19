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

import java.text.DecimalFormat;
import java.util.Random;

import org.eclipse.core.runtime.Assert;

/**
 * An implementation of {@link IGene}, fitting for various number formats.
 *
 * @author bdu
 *
 */
public class UniversalGene extends AbstractGene<Float> {
    /**
     * Default formatter for boolean values.
     */
    public static final IValueFormatter BOOLEAN_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Boolean) {
                return o.toString();
            } else if (o instanceof UniversalGene) {
                return (Boolean.valueOf(((UniversalGene) o).getBoolValue()).toString());
            }
            return null;
        }
    };

    /**
     * Default formatter for strictly positive float values.
     */
    public static final IValueFormatter STRICTLY_POSITIVE_FLOAT_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            final DecimalFormat df = new DecimalFormat("0.00");
            if (o instanceof Float) {
                return df.format(o) + "f";
            } else if (o instanceof UniversalGene) {
                return df.format(((UniversalGene) o).getValue()) + "f";
            }
            return null;
        }
    };

    /**
     * Default formatter for float values.
     */
    public static final IValueFormatter FLOAT_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Float) {
                return ((Math.signum(((Float) o).floatValue()) <= 0 ? "-" : "+") + o + "f");
            }
            return null;
        }
    };

    /**
     * Default formatter for integer values.
     */
    public static final IValueFormatter INTEGER_FORMATTER = new IValueFormatter() {

        public String getString(final Object o) {
            if (o instanceof Integer) {
                return ((Integer) o).toString();
            } else if (o instanceof UniversalGene) {
                return (Math.round(((UniversalGene) o).getValue().floatValue()) + "");
            }

            return null;
        }
    };

    /**
     * Type info for a boolean gene.
     */
    public static final FloatTypeInfo BOOLEAN_TYPE_INFO = new FloatTypeInfo(Float.valueOf(0.0f),
            Float.valueOf(0.0f), Float.valueOf(1.0f), BOOLEAN_FORMATTER, Boolean.class);

    /**
     * Universal type info for a strictly positive float gene.
     */
    public static final FloatTypeInfo STRICTLY_POSITIVE_FLOAT_TYPE_INFO = new FloatTypeInfo(
            Float.valueOf(1.0f), Float.valueOf(Float.MIN_VALUE),
            Float.valueOf(Float.POSITIVE_INFINITY),
            STRICTLY_POSITIVE_FLOAT_FORMATTER,
            Float.class);

    /**
     * Universal type info for a float gene.
     */
    public static final FloatTypeInfo FLOAT_TYPE_INFO = new FloatTypeInfo(Float.valueOf(0.0f),
            Float.valueOf(Float.NEGATIVE_INFINITY), Float.valueOf(Float.POSITIVE_INFINITY),
            FLOAT_FORMATTER, Float.class);

    @Override
    public String toString() {
        String result = getTypeInfo().getValueFormatter().getString(this);
        if (result == null) {
            result = this.getId() + ": " + this.getValue();
        }
        return result;
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
            final Float theValue,
            final TypeInfo<Float> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);

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
        } else if (clazz == Integer.class) {
            return new IntegerMutator().newMutation(this, theMutationInfo);
        }
        return null;
    }

    /**
     * Interface for a factory that creates mutated genes.
     *
     * @author bdu
     *
     */
    public static interface IMutator {
        /**
         *
         * @param template
         *            the template; may not be {@code null}
         * @param mutationInfo
         *            the mutation info; may not be {@code null}
         * @return a new gene
         */
        IGene<Float> newMutation(final UniversalGene template, final MutationInfo mutationInfo);
    }

    /**
     * A gene factory that creates boolean mutations.
     *
     * @author bdu
     *
     */
    private static class BooleanMutator implements IMutator {
        private static final double PROBABILITY_FOR_TRUE = 0.5;

        /**
         * Creates a {@link BooleanMutator} instance.
         */
        public BooleanMutator() {
            // Nothing to do here.
        }

        public IGene<Float> newMutation(
                final UniversalGene template, final MutationInfo mutationInfo) {
            Assert.isLegal(template != null);
            Assert.isLegal(mutationInfo != null);

            if ((template == null) || (mutationInfo == null)) {
                return null;
            }

            final TypeInfo<Float> typeInfo = template.getTypeInfo();
            Assert.isNotNull(typeInfo);

            final Random r = template.getRandomGenerator();
            Assert.isNotNull(r);

            // get mutation parameters
            final double prob = mutationInfo.getProbability();
            final double probGenuineMutation = mutationInfo.getGenuineMutationProbability();
            final Distribution distr = mutationInfo.getDistr();
            Assert.isTrue(distr == Distribution.UNIFORM);

            final Float newValue;
            if (r.nextDouble() < prob) {
                if (r.nextDouble() < probGenuineMutation) {
                    // enforce genuine mutation
                    newValue = Float.valueOf(template.getBoolValue() ? 0.0f : 1.0f);
                } else {
                    // assign a random boolean value (may be the same as before)
                    newValue = Float.valueOf(r.nextDouble() < PROBABILITY_FOR_TRUE ? 1.0f : 0.0f);
                }
            } else {
                newValue = template.getValue();
            }
            return new UniversalGene(template.getId(), newValue, typeInfo, mutationInfo);
        }
    }

    /**
     * A gene factory that creates float mutations.
     *
     * @author bdu
     *
     */
    private static class FloatMutator implements IMutator {
        /**
         * Creates a {@link FloatMutator} instance.
         */
        public FloatMutator() {
            // Nothing to do here.
        }

        public IGene<Float> newMutation(
                final UniversalGene template, final MutationInfo mutationInfo) {
            Assert.isLegal(template != null);
            Assert.isLegal(mutationInfo != null);

            if ((template == null) || (mutationInfo == null)) {
                return null;
            }

            final Random r = template.getRandomGenerator();
            Assert.isNotNull(r);

            final TypeInfo<Float> typeInfo = template.getTypeInfo();
            Assert.isNotNull(typeInfo);

            final double prob = mutationInfo.getProbability();
            final double var = mutationInfo.getVariance();
            final Distribution distr = mutationInfo.getDistr();
            Assert.isTrue(distr == Distribution.GAUSSIAN);

            final Float value = template.getValue();
            Float newValue = value;
            if (Math.random() < prob) {
                // produce a new value within the valid bounds.
                do {
                    final double gauss = r.nextGaussian() * Math.sqrt(var);
                    // TODO: regard genuineMutationProbability
                    newValue = Float.valueOf((float) (value.doubleValue() + gauss));
                } while (!typeInfo.isValueWithinBounds(newValue));
            }
            return new UniversalGene(template.getId(), newValue, typeInfo, mutationInfo);
        }
    }

    /**
     * A gene factory that creates integer mutations.
     *
     * @author bdu
     *
     */
    private static class IntegerMutator implements IMutator {

        /**
         * Creates a new {@link IntegerMutator} instance.
         *
         */
        public IntegerMutator() {
            // Nothing to do here.
        }

        public IGene<Float> newMutation(
                final UniversalGene template, final MutationInfo mutationInfo) {
            Assert.isLegal(template != null);
            Assert.isLegal(mutationInfo != null);

            if ((template == null) || (mutationInfo == null)) {
                return null;
            }

            final TypeInfo<Float> typeInfo = template.getTypeInfo();
            Assert.isNotNull(typeInfo);

            final Random r = template.getRandomGenerator();
            Assert.isNotNull(r);

            final double prob = mutationInfo.getProbability();
            final double var = mutationInfo.getVariance();
            final Distribution distr = mutationInfo.getDistr();
            final Integer lowerBound = Integer.valueOf(typeInfo.getLowerBound().intValue());
            final Integer upperBound = Integer.valueOf(typeInfo.getUpperBound().intValue());
            final Integer defaultValue = Integer.valueOf(typeInfo.getDefaultValue().intValue());
            final Integer value = Integer.valueOf(template.getValue().intValue());
            Integer newInt = value;
            if (r.nextDouble() < prob) {
                // TODO: regard genuineMutationProbability
                switch (distr) {
                case GAUSSIAN:
                    do {
                        // produce a new value within the valid bounds.
                        final double gauss = r.nextGaussian() * Math.sqrt(var);
                        final double newValue = (value.doubleValue() + gauss);
                        newInt = Integer.valueOf((int) Math.round(newValue));
                    } while (!typeInfo.isValueWithinBounds((Float.valueOf(newInt.floatValue()))));
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
            return new UniversalGene(template.getId(), Float.valueOf(newInt.floatValue()),
                    typeInfo, mutationInfo);
        }
    }

    /**
     *
     * @return an integer representation of the value.
     */
    public int getIntValue() {
        return Math.round(getValue().floatValue());
    }

    /**
     *
     * @return a boolean representation of the value.
     */
    public boolean getBoolValue() {
        final double diff = 1.0 - Math.abs(getValue().floatValue());
        return (Math.pow(diff, 2) < EPSILON);
    }

    private static final double EPSILON = 1.0e-5;

}
