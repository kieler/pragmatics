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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.IFilterable;

/**
 * A population has a list of individuals (genomes).
 *
 * @author bdu
 *
 */
public class Population extends MapPropertyHolder
 implements
            Iterable<Genome>,
            IFilterable<List<Genome>, Genome> {
    /** Property to mark individuals as selected. */
    public static final IProperty<Boolean> SELECTED = new Property<Boolean>("evol.selected",
            Boolean.FALSE);

    /**
     * Filter for rated individuals.
     */
    public static final ICondition<Genome> RATED_FILTER = new ICondition<Genome>() {
        public boolean evaluate(final Genome genome) {
            return genome.hasUserRating();
        }
    };

    /**
     * Filter for unrated individuals.
     */
    public static final ICondition<Genome> UNRATED_FILTER = new ICondition<Genome>() {
        public boolean evaluate(final Genome genome) {
            return !genome.hasUserRating();
        }
    };

    /**
     * Filter for selected individuals.
     */
    public static final ICondition<Genome> SELECTED_FILTER = new ICondition<Genome>() {
        public boolean evaluate(final Genome genome) {
            return genome.getProperty(SELECTED);
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
        this.genomes.addAll(individuals);
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
     *
     * @return the arithmetic mean of all individual ratings, or
     *         {@code Double.NaN} if there are no individuals.
     */
    public Double getAverageRating() {
        if (this.getGenomes().isEmpty()) {
            return Double.valueOf(Double.NaN);
        }
        double ratingSum = 0.0;
        for (final Genome ind : this.getGenomes()) {
            ratingSum += ind.hasUserRating() ? ind.getUserRating() : 0.0;
        }
        return ratingSum / this.size();
    }

    /**
     * @return a string containing detailed information about the population.
     */
    public String getDetails() {
        String newLine = EvolPlugin.LINE_DELIMITER;
        final int expectedLengthPerEntry = 70;
        StringBuilder result = new StringBuilder(this.size() * expectedLengthPerEntry);

        int i = 0;
        for (final Genome ind : this.getGenomes()) {
            // assuming individual is not null
            result.append("Individual #" + ++i + " (" + ind.getGeneration() + "." + ind.getId()
                    + "): " + ind.getUserRating() + newLine);
            for (final IGene<?> gene : ind.getGenes()) {
                result.append(gene.getId() + ": " + gene.toString() + newLine);
            }
            result.append(newLine);
            result.append("Features:" + newLine);
            result.append(ind.getFeatures() + newLine);
            result.append(newLine);
        }
        return result.toString();
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
     * Randomly chooses one of the individuals in the list.
     *
     * @return an individual that is in the list, or {@code null}, if the list
     *         is empty.
     */
    public final Genome pick() {
        Genome result = null;
        if (size() > 0) {
            int pos = (int) (Math.random() * size());
            result = this.genomes.get(pos);
        }
        return result;
    }

    @Override
    public String toString() {
        String newLine = EvolPlugin.LINE_DELIMITER;
        StringBuilder result = new StringBuilder();

        int i = 0;
        for (final Genome ind : this.getGenomes()) {
            result.append("#" + ++i + ": " + ind.toString() + newLine);
        }
        return result.toString();
    }

    private final List<Genome> genomes = new ArrayList<Genome>();

    /**
     * @return the genomes
     */
    public List<Genome> getGenomes() {
        return this.genomes;
    }

    // list interface implementations
    /**
     *
     * @return number of genomes in this population
     */
    public int size() {
        return this.getGenomes().size();
    }

    /**
     * Returns <tt>true</tt> if the genomes list contains no elements.
     *
     * @return <tt>true</tt> if the genomes list contains no elements
     */
    public boolean isEmpty() {
        return this.genomes.isEmpty();
    }

    /**
     * @param theInd
     */
    public boolean add(final Genome theInd) {
        return this.genomes.add(theInd);
    }

    /**
     * @param theInd
     */
    public void remove(final Genome theInd) {
        this.genomes.remove(theInd);
    }

    /**
     * @param theIndex
     * @param theOffspring
     */
    public boolean addAll(final int theIndex, final Collection<? extends Genome> theOffspring) {
        return this.genomes.addAll(theIndex, theOffspring);

    }

    /**
     * @param theIndividuals
     */
    public void toArray(final Genome[] theIndividuals) {
        this.genomes.toArray(theIndividuals);
    }

    /**
     * Returns a list iterator over the elements in the genomes list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in the genomes list (in proper
     *         sequence)
     */
    public ListIterator<Genome> listIterator() {
        return this.genomes.listIterator();
    }

    /* (non-Javadoc)
     * @see java.util.List#clear()
     */
    public void clear() {
        this.genomes.clear();

    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Genome> iterator() {
        return this.genomes.iterator();
    }

}
