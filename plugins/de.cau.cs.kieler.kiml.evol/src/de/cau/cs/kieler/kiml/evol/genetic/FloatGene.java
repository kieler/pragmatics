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
 * Implementation of IGene for Float values.
 *
 * @author bdu
 * @deprecated
 */
@Deprecated
public class FloatGene extends AbstractGene<Float> {
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
    public FloatGene(
            final Object theId,
            final float theValue,
            final double theMutationProbability,
            final double theMutationVariance) {
        this(theId, theValue, DEFAULT_TYPE_INFO, new MutationInfo(theMutationProbability,
                theMutationVariance, Distribution.GAUSSIAN));
    }

    /**
     * Create a new IGene that with a certain probability has a changed value.
     *
     * @return new IGene with possibly mutated value.
     */
    public IGene<Float> newMutation() {
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

    /**
     * Returns a copy of the given template instance.
     *
     * @param template
     *            an instance
     * @return copy of the template
     */
    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        if (template instanceof FloatGene) {
            return new FloatGene(template.getId(), template.getValue(), template.getTypeInfo(),
                    template.getMutationInfo());
        }
        // incompatible template
        return null;
    }

    @Override
    public IGene<Float> recombineWith(final IGene<Float>... otherGenes) {
        // return average of other genes and this gene
        Float sum = 0.0f;
        for (final IGene<Float> gene : otherGenes) {
            sum += gene.getValue();
        }
        sum += getValue();
        final int count = otherGenes.length + 1;
        final float value = sum / count;
        // XXX must ensure that the value is not out of bounds
        Assert.isTrue(getTypeInfo().isValueWithinBounds(value));
        final IGene<Float> result = new FloatGene(getId(), value, getTypeInfo(), getMutationInfo());
        return result;
    }

    @Override
    public String toString() {
        return getTypeInfo().getValueFormatter().getString(getValue());
    }

    /**
     * Constructor for a float gene.
     * @param theId the id
     * @param theValue the value
     * @param theTypeInfo the type info
     * @param theMutationInfo the mutation info
     */
    protected FloatGene(
            final Object theId,
            final Float theValue,
            final TypeInfo<Float> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
    }

    private static final IValueFormatter FLOAT_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Float) {
                return (Math.signum((Float) o) <= 0 ? "-" : "+") + o + "f";
            }
            return null;
        }
    };

    public static final IValueFormatter STRICTLY_POSITIVE_FLOAT_FORMATTER = new IValueFormatter() {
        public String getString(final Object o) {
            if (o instanceof Float) {
                return o + "f";
            } else if (o instanceof UniversalGene) {
                return ((UniversalGene) o).getValue() + "f";
            }
            return null;
        }
    };
    /**
     * Universal type info for a float gene.
     */
    public static final TypeInfo<Float> UNIVERSAL_TYPE_INFO = new TypeInfo<Float>(.0f,
            Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, FLOAT_FORMATTER, Float.class);


    /**
     * Default type info for a float gene.
     */
    private static final TypeInfo<Float> DEFAULT_TYPE_INFO = UNIVERSAL_TYPE_INFO;

}
