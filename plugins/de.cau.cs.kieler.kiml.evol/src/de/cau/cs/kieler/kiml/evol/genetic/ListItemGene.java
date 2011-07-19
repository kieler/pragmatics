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

/**
 * A gene that can mutate over the elements of a list.
 *
 * @author bdu
 *
 */
public class ListItemGene extends AbstractGene<Integer> {

    /**
     * Default formatter for integer values.
     */
    public static final IValueFormatter LIST_ITEM_GENE_FORMATTER = new IValueFormatter() {
        /**
         * {@inheritDoc}
         */
        public String getString(final Object o) {
            if (o instanceof ListItemGene) {
                ListItemGene gene = (ListItemGene) o;
                ListItemTypeInfo typeInfo = gene.getTypeInfo();
                List<?> list = typeInfo.getList();
                assert list != null;

                int value = gene.getValue().intValue();
                assert (value >= 0) && (value < list.size());
                String result = list.get(value).toString();
                return result;
            }
            return null;
        }
    };

    /**
     * Creates a new {@link ListItemGene} instance. The type info object must
     * contain the list.
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
    public ListItemGene(
            final String theId,
            final Integer theValue,
            final ListItemTypeInfo theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, theValue, theTypeInfo, theMutationInfo);
    }

    /** The cached hash value. */
    private Integer cachedHash;

    @Override
    public ListItemTypeInfo getTypeInfo() {
        return (ListItemTypeInfo) super.getTypeInfo();
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newInstance(final AbstractGene<Integer> template) {
        if (!(template instanceof ListItemGene)) {
            return null;
        }
        ListItemGene listItemTemplate = (ListItemGene) template;
        return new ListItemGene(listItemTemplate.getId(), listItemTemplate.getValue(),
                listItemTemplate.getTypeInfo(), listItemTemplate.getMutationInfo());
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newMutation() {
        ListItemGene result;

        Random random = this.getRandomGenerator();
        MutationInfo mutationInfo = this.getMutationInfo();
        double prob = mutationInfo.getProbability();

        // must be uniform distribution
        Distribution distr = mutationInfo.getDistr();
        assert distr == Distribution.UNIFORM;

        TypeInfo<Integer> typeInfo = this.getTypeInfo();
        Integer lowerBound = typeInfo.getLowerBound();
        Integer upperBound = typeInfo.getUpperBound();

        Integer value = this.getValue().intValue();
        int newInt = value.intValue();
        if (random.nextDouble() < prob) {
            newInt = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
        }

        result =
                new ListItemGene(this.getId(), newInt, this.getTypeInfo(), this.getMutationInfo());

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

    @Override
    public int hashCode() {
        if (this.cachedHash == null) {
            final int f1 = 17881;
            final int f2 = 41;
            this.cachedHash =
                    (this.getTypeInfo().getList().hashCode() * f1) + (this.getId().hashCode() * f2)
                            + this.getValue().hashCode();
        }

        return this.cachedHash;
    }

    @Override
    public boolean equals(final Object theObj) {
        if (!(theObj instanceof ListItemGene)) {
            return false;
        }

        final ListItemGene other = (ListItemGene) theObj;

        return other.getTypeInfo().getList().equals(this.getTypeInfo().getList())
                && (other.getId().equals(this.getId()))
                && other.getValue().equals(this.getValue());
    }
}
