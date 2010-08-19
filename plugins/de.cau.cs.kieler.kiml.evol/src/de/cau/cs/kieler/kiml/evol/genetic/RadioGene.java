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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.List;
import java.util.Random;

import org.eclipse.core.runtime.Assert;

/**
 * A gene mutation over the elements of a list.
 *
 * @author bdu
 *
 */
public class RadioGene extends AbstractGene<Integer> {

    /**
     * Default formatter for integer values.
     */
    public static final IValueFormatter RADIO_GENE_FORMATTER = new IValueFormatter() {
        /**
         * {@inheritDoc}
         */
        public String getString(final Object o) {
            if (o instanceof RadioGene) {
                final RadioGene gene = (RadioGene) o;
                final RadioTypeInfo typeInfo = gene.getTypeInfo();
                final List<?> list = typeInfo.getList();
                Assert.isNotNull(list);
                if (list == null) {
                    return "";
                }
                final int value = gene.getValue().intValue();
                Assert.isTrue((value >= 0) && (value < list.size()));
                final String result = list.get(value).toString();
                return result;

            }
            return null;
        }

    };

// private final List<?> list;

    /**
     * Creates a new {@link RadioGene} instance.
     *
     * @param theId
     * @param theValue
     * @param theTypeInfo
     * @param theMutationInfo
     */
    public RadioGene(
            final Object theId,
            final Integer theValue,
            final TypeInfo<Integer> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
        // this.list = theList;
    }

    @Override
    public RadioTypeInfo getTypeInfo() {
        return (RadioTypeInfo) super.getTypeInfo();
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newInstance(final AbstractGene<Integer> template) {
        if (!(template instanceof RadioGene)) {
            return null;
        }
        final RadioGene radioTemplate = (RadioGene) template;
        return new RadioGene(radioTemplate.getId(), radioTemplate.getValue(),
                radioTemplate.getTypeInfo(), radioTemplate.getMutationInfo());
    }

// private List<?> getList() {
    // return Collections.unmodifiableList(getTypeInfo().getList());
    // }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newMutation() {
        final RadioGene result;

        final Random r = this.getRandomGenerator();
        final MutationInfo mutationInfo = this.getMutationInfo();
        final double prob = mutationInfo.getProbability();

        final Distribution distr = mutationInfo.getDistr();
        Assert.isTrue(distr == Distribution.UNIFORM);

        final TypeInfo<Integer> typeInfo = this.getTypeInfo();
        final Integer lowerBound = typeInfo.getLowerBound();
        final Integer upperBound = typeInfo.getUpperBound();

        final Integer value = Integer.valueOf(this.getValue().intValue());
        int newInt = value.intValue();
        if (r.nextDouble() < prob) {
            // TODO: regard genuineMutationProbability
            // uniform distribution:
            newInt =
                    (r.nextInt((upperBound.intValue() - lowerBound.intValue() + 1)) + lowerBound
                            .intValue());
        }

        result = new RadioGene(this.getId(), newInt, this.getTypeInfo(), this.getMutationInfo());

        return result;
    }

    @Override
    public String toString() {
        String result = getTypeInfo().getValueFormatter().getString(this);
        if (result == null) {
            result = this.getId() + ": " + this.getValue();
        }
        return result;
    }
}
