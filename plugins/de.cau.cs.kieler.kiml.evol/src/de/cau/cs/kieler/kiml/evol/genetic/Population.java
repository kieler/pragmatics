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
import java.util.List;

/**
 * A population is a list of individuals (genomes).
 * 
 * @author bdu
 * 
 */
public class Population extends ArrayList<Genome> {
    /**
     *
     */
    private static final long serialVersionUID = -5511104369758838181L;

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
     * Randomly chooses one of the individuals in the list.
     *
     * @return an individual that is in the list, or {@code null}, if the list
     *         is empty.
     */
    public Genome pick() {
        Genome result = null;
        if (size() > 0) {
            final int pos = (int) (Math.random() * size());
            result = get(pos);
        }
        return result;
    }

    @Override
    public String toString() {
        final String newLine = System.getProperty("line.separator");
        final StringBuilder result = new StringBuilder();
        int i = 0;
        for (final Genome ind : this) {
            result.append("#" + ++i + ": " + ind.toString() + newLine);
        }
        return result.toString();
    }

    /**
     *
     * @return the arithmetic mean of all individual ratings, or
     *         {@code Double.NaN} if there are no individuals.
     */
    public Double getAverageRating() {
        if (this.isEmpty()) {
            return Double.NaN;
        }
        int ratingSum = 0;
        for (final Genome ind : this) {
            ratingSum += ind.getRating();
        }
        return (double) (ratingSum / (double) this.size());
    }
}
