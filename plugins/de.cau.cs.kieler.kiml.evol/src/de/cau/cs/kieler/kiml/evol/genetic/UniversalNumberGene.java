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

/**
 * An implementation of {@link IGene}, fitting for various number formats.
 *
 * @author bdu
 *
 */
public class UniversalNumberGene extends AbstractGene<Float> {
    /**
     * Default formatter for boolean values.
     */
    public static final IValueFormatter BOOLEAN_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Boolean) {
                return o.toString();
            } else if (o instanceof UniversalNumberGene) {
                return Boolean.valueOf(((UniversalNumberGene) o).getBoolValue()).toString();
            }
            return null;
        }
    };

    /**
     * Default formatter for strictly positive float values.
     */
    public static final IValueFormatter STRICTLY_POSITIVE_FLOAT_FORMATTER =
            new IValueFormatter() {
                public String getString(final Object o) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    if (o instanceof Float) {
                        return df.format(o) + "f";
                    } else if (o instanceof UniversalNumberGene) {
                        return df.format(((UniversalNumberGene) o).getValue()) + "f";
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
            } else if (o instanceof UniversalNumberGene) {
                return Math.round(((UniversalNumberGene) o).getValue().floatValue()) + "";
            }

            return null;
        }
    };

    /**
     * Type info for a boolean gene.
     */
    public static final FloatTypeInfo BOOLEAN_TYPE_INFO = new FloatTypeInfo(0.0f, 0.0f, 1.0f,
            BOOLEAN_FORMATTER, Boolean.class);

    /**
     * Universal type info for a strictly positive float gene.
     */
    public static final FloatTypeInfo STRICTLY_POSITIVE_FLOAT_TYPE_INFO = new FloatTypeInfo(
1.0f,
            Float.MIN_VALUE, Float.POSITIVE_INFINITY, STRICTLY_POSITIVE_FLOAT_FORMATTER,
            Float.class);

    /**
     * Universal type info for a float gene.
     */
    public static final FloatTypeInfo FLOAT_TYPE_INFO = new FloatTypeInfo(0.0f,
            Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
            FLOAT_FORMATTER, Float.class);

    /** Cached hash value. */
    private Integer cachedHash;

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
    public UniversalNumberGene(
            final String theId,
            final Float theValue,
            final TypeInfo<Float> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);

    }

    /**
     * {@inheritDoc}
     */
    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        IGene<Float> result =
                new UniversalNumberGene(template.getId(), template.getValue(), template.getTypeInfo(),
                        template.getMutationInfo());
        return result;
    }

    /**
     * {@inheritDoc}
     * @deprecated separation of data and operations #1716
     */
    @Deprecated
    public final IGene<Float> newMutation() {
        final IGene<Float> result = newMutation(getMutationInfo());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated separation of data and operations #1716
     */
    @Deprecated
    @Override
    public IGene<Float> recombineWith(final IGene<Float>... theOtherGenes) {

        Class<?> clazz = getTypeInfo().getTypeClass();
        Float average = null;
        if ((clazz == Float.class) || (clazz == Integer.class)) {
            // return average of genes and this gene
            float sum = 0.0f;
            for (final IGene<Float> gene : theOtherGenes) {
                sum += gene.getValue().floatValue();
            }
            sum += getValue().floatValue();
            final int count = theOtherGenes.length + 1;
            average = Float.valueOf(sum / count);
            assert getTypeInfo().isValueWithinBounds(average);
        }

        if (average != null) {
            Float value;
            if (clazz == Integer.class) {
                value =
                        Float.valueOf(Integer.valueOf(Math.round(average.floatValue()))
                                .floatValue());
            } else {
                value = average;
            }

            IGene<Float> result =
                    new UniversalNumberGene(getId(), value, getTypeInfo(), getMutationInfo());

            return result;
        }

        return super.recombineWith(theOtherGenes);
    }
    
    /**
     * Mutates the value. The mutation details are specified in
     * <code>theMutationInfo</code>.
     * 
     * @return a new IGeneData with the mutated value.
     * @deprecated
     */
    @Deprecated
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
        double diff = 1.0 - Math.abs(getValue().floatValue());
        return diff < EPSILON;
    }

    /** A very small value. */
    private static final double EPSILON = 1.0e-5;

    @Override
    public int hashCode() {
        if (this.cachedHash == null) {
            final int f1 = 17881;
            final int f2 = 41;
            this.cachedHash =
                    this.getTypeInfo().getTypeClass().hashCode() * f1 + this.getId().hashCode()
                            * f2 + this.getValue().hashCode();
        }

        return this.cachedHash;

    }

    @Override
    public boolean equals(final Object theObj) {
        if (!(theObj instanceof UniversalNumberGene)) {
            return false;
        }

        final UniversalNumberGene other = (UniversalNumberGene) theObj;

        if (this.getTypeInfo().getTypeClass() != other.getTypeInfo().getTypeClass()) {
            return false;
        }

        if (this.getTypeInfo().getTypeClass() == Float.class) {
            if (!other.getId().equals(this.getId())) {
                return false;
            }
            final float diff = Math.abs(other.getValue() - this.getValue());
            return diff < EPSILON;
        }

        return (other.getId().equals(this.getId())) && other.getValue().equals(this.getValue());
    }

}
