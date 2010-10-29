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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.kiml.evol.IFilterable;
import de.cau.cs.kieler.kiml.evol.IItemFilter;

/**
 * A population is a list of individuals (genomes).
 *
 * @author bdu
 *
 */
public class Population extends ArrayList<Genome> implements IFilterable<Population, Genome> {

    /**
     * For {@link Serializable}.
     */
    private static final long serialVersionUID = -5511104369758838181L;

    /**
     * Filter for rated individuals.
     */
    public static final ICondition<Genome> RATED_FILTER = new ICondition<Genome>() {
        public boolean evaluate(final Genome item) {
            return item.hasUserRating();
        }
    };

    /**
     * Filter for unrated individuals.
     */
    public static final ICondition<Genome> UNRATED_FILTER = new ICondition<Genome>() {
        public boolean evaluate(final Genome item) {
            return !item.hasUserRating();
        }
    };

    /**
     * Constructs an empty population.
     */
    public Population() {
        super();
    }

    /**
     * Constructs a new Population instance using the given list of individuals.
     *
     * @param individuals
     *            List of individuals. Must not be {@code null}, otherwise a
     *            {@link NullPointerException} is thrown.
     */
    public Population(final List<Genome> individuals) {
        this.addAll(individuals);
    }

    /**
     *
     * @return the arithmetic mean of all individual ratings, or
     *         {@code Double.NaN} if there are no individuals.
     */
    public Double getAverageRating() {
        if (this.isEmpty()) {
            return Double.valueOf(Double.NaN);
        }
        double ratingSum = 0.0;
        for (final Genome ind : this) {
            ratingSum += ind.hasUserRating() ? ind.getUserRating() : 0.0;
        }
        return ratingSum / this.size();
    }

    /**
     * Randomly chooses one of the individuals in the list.
     *
     * @return an individual that is in the list, or {@code null}, if the list
     *         is empty.
     */
    public Genome pick() {
        Genome result = null;
        if (size() > 0) {
            int pos = (int) (Math.random() * size());
            result = get(pos);
        }
        return result;
    }

    /**
     *
     * @param filter
     *            an {@link IItemFilter} for {@link Genome} objects
     * @return a new {@link Population} containing the {@link Genome} objects
     *         that pass the filter.
     */
    public Population select(final ICondition<Genome> filter) {
        Population result = new Population();
        for (final Genome g : this) {
            // TODO: use FilteredIterator from kieler.core(.util)
            if (filter.evaluate(g)) {
                result.add(g);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (final Genome ind : this) {
            result.append("#" + ++i + ": " + ind.toString() + newLine);
        }
        return result.toString();
    }

    /**
     * @return a string containing detailed information about the population.
     */
    public String getDetails() {
        String newLine = System.getProperty("line.separator");
        final int expectedLengthPerEntry = 70;
        StringBuilder result = new StringBuilder(this.size() * expectedLengthPerEntry);

        int i = 0;
        for (final Genome ind : this) {
            result.append("Individual #" + ++i + " (" + ind.getGeneration() + "." + ind.getId()
                    + "): " + ind.getUserRating() + newLine);
            for (final IGene<?> gene : ind) {
                result.append(gene.getId() + ": " + gene.toString() + newLine);
            }
            result.append(newLine);
            result.append("Features:" + newLine);
            result.append(ind.getFeatures() + newLine);
            result.append(newLine);
        }
        return result.toString();
    }
}
