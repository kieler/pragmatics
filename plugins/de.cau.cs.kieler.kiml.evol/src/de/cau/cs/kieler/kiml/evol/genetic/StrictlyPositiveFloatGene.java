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

import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo.Distribution;

/**
 * Implementation of a gene with strictly positive float values.
 * 
 * @author bdu
 * 
 */
public class StrictlyPositiveFloatGene extends FloatGene {
    @Override
    public IGene<Float> newMutation() {
        final IGene<Float> template = super.newMutation();
        final Float newValue = template.getValue();
        final IGene<Float> result = new StrictlyPositiveFloatGene(template.getId(), newValue,
                getTypeInfo(), getMutationInfo());
        return result;
    }

    @Override
    public IGene<Float> recombineWith(final IGene<Float>... otherGenes) {
        for (IGene<Float> gene : otherGenes) {
            if (!(gene instanceof StrictlyPositiveFloatGene)) {
                // cannot recombine incompatible genes
                return null;
            }
        }
        final IGene<Float> template = super.recombineWith(otherGenes);
        final IGene<Float> result = new StrictlyPositiveFloatGene(template.getId(), template
                .getValue(), getTypeInfo(), getMutationInfo());
        return result;
    }

    @Override
    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        if (template instanceof StrictlyPositiveFloatGene) {
            final IGene<Float> result = new StrictlyPositiveFloatGene(template.getId(), template
                    .getValue(), template.getTypeInfo(), template.getMutationInfo());
            return result;
        }
        // incompatible template
        return null;
    }

    /**
     * Constructor for a StrictlyPositiveFloatGene.
     * 
     * @param theId
     *            the id
     * @param theValue
     *            the value
     * @param theMutationProbability
     *            the mutation probability
     * @param theMutationVariance
     *            the variance
     */
    public StrictlyPositiveFloatGene(final Object theId, final float theValue,
            final double theMutationProbability, final double theMutationVariance) {
        this(theId, theValue, TYPE_INFO, new MutationInfo(theMutationProbability,
                theMutationVariance, Distribution.GAUSSIAN));
    }

    @Override
    public String toString() {
        return getValue() + "f";
    }

    /** A constructor. */
    private StrictlyPositiveFloatGene(final Object theId, final float theValue,
            final TypeInfo<Float> theTypeInfo, final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
    }

    /**
     * Type info for a strictly positive float gene.
     */
    private static final TypeInfo<Float> TYPE_INFO = new TypeInfo<Float>(1.0f, Float.MIN_VALUE,
            Float.MAX_VALUE, Float.class);
}
