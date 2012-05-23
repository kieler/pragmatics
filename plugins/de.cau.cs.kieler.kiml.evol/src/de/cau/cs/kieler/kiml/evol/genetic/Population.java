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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.core.util.ICondition;

/**
 * A population has a list of individuals (genomes).
 *
 * @author bdu
 * @author msp
 */
public class Population extends MapPropertyHolder implements Iterable<Genome> {

    /** the genomes that constitute this population. */
    private final List<Genome> genomes;

    /**
     * Constructs an empty population.
     */
    public Population() {
        genomes = new ArrayList<Genome>();
    }

    /**
     * Constructs a new Population instance using the given list of individuals.
     *
     * @param individuals
     *            List of individuals
     */
    public Population(final List<Genome> individuals) {
        genomes = new ArrayList<Genome>(individuals.size());
        genomes.addAll(individuals);
    }

    /**
     * Creates a new {@link Population} instance using the given instance.
     *
     * @param thePopulation
     *            a population
     */
    public Population(final Population thePopulation) {
        this(thePopulation.getGenomes());
    }

    /**
     * Returns the number of individuals.
     *
     * @return number of genomes in this population
     */
    public int getSize() {
        return genomes.size();
    }

    /**
     * Returns the individuals that constitute this population.
     * 
     * @return the genomes
     */
    public List<Genome> getGenomes() {
        return genomes;
    }

    /**
     * Determine the average of individual ratings.
     *
     * @return the arithmetic mean of all individual ratings, or
     *         {@code Double.NaN} if there are no individuals.
     */
    public Double getAverageRating() {
        if (genomes.isEmpty()) {
            return Double.valueOf(Double.NaN);
        }
        double ratingSum = 0.0;
        for (Genome ind : genomes) {
            Double rating = ind.getProperty(Genome.USER_RATING);
            if (rating != null) {
                ratingSum += rating;
            }
        }
        return ratingSum / genomes.size();
    }

    /**
     * Get a filtered iterator over this population, using the specified filter.
     *
     * @param filter
     *            the filter
     * @return a filtered iterator over this population
     */
    public FilteredIterator<Genome> filteredIterator(final ICondition<Genome> filter) {
        FilteredIterator<Genome> result =
                new FilteredIterator<Genome>(this.getGenomes().listIterator(), filter);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (final Genome ind : this.getGenomes()) {
            result.append("#").append(++i).append(": ").append(ind.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Genome> iterator() {
        return genomes.iterator();
    }
    
}
