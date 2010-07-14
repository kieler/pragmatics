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

import java.util.Comparator;

import org.eclipse.core.runtime.Assert;


// TODO: discuss whether this class should be merged with Genome.
/**
 * Implementation of an individual used in an evolutionary algorithm.
 *
 * @author bdu
 */
public class Individual {
    /**
     * Descending rating comparator.
     */
    public static final Comparator<Individual> DESCENDING_RATING_COMPARATOR =
            new Comparator<Individual>() {
                public int compare(final Individual ind0, final Individual ind1) {
                    if (ind0.getRating() == ind1.getRating()) {
                        return 0;
                    } else if (ind0.getRating() < ind1.getRating()) {
                        return 1;
                    }
                    return -1;
                }
            };

    /**
     *
     * @return the user-defined rating. A higher value means a better rating.
     *         The value may be negative.
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Sets the user-defined rating.
     *
     * @param theRating
     *            An integer value (may be negative). A higher value means a
     *            better rating.
     */
    public void setRating(final int theRating) {
        // System.out.println("Assign rating " + theRating + " to individual" +
        // ": " + toString());
        this.rating = theRating;
    }

    /**
     * Downscales the rating. This makes the rating less relevant without
     * discarding it completely. This can be used for outdated ratings.
     */
    public void fadeRating() {
        // TODO: implement more sophisticated fading of ratings
        if (this.rating != 0) {
            final double scalingFactor = .90;
            this.rating = (int) (this.rating * scalingFactor);
        }
    }

    /**
     *
     * @return {@code true} if this individual has been rated.
     */
    public boolean hasRating() {
        return (this.rating != 0);
    }

    /**
     * Generation the individual belongs to.
     *
     * @return Generation.
     */
    public int getGeneration() {
        return this.generation;
    }

    /**
     * Returns the id of the individual.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Constructor for an Individual with the given id and the given genome.
     *
     * @param theId
     *            an id for this individual
     * @param theGenome
     *            initial genome for this individual
     * @param theGeneration
     *            the generation
     *
     */
    public Individual(final String theId, final Genome theGenome, final int theGeneration) {
        if (theId == null) {
            this.id = Integer.toHexString(this.hashCode());
        } else {
            this.id = theId;
        }
        this.genome = new Genome(theGenome);
        this.generation = theGeneration;
        System.out.println("Created individual " + toString());
    }

    /**
     * Constructor for an Individual with the given genome and the given
     * generation.
     *
     * @param theGenome
     *            initial genome for the individual.
     * @param theGeneration
     *            the generation
     *
     */
    public Individual(final Genome theGenome, final int theGeneration) {
        this(null, theGenome, theGeneration);
    }

    /**
     * Mutate the genes of the individual. Every gene is asked to provide a
     * mutated version of itself.
     */
    private void mutate() {
        final Genome newGenome = new Genome(null);
        if (this.genome != null) {
            for (final IGene<?> gene : this.genome) {
                Assert.isNotNull(gene);
                final IGene<?> newGene = gene.newMutation();
                Assert.isNotNull(newGene, "Invalid mutation of " + gene);
                newGenome.add(newGene);
            }
        }
        this.genome = newGenome;
    }

    /**
     * Performs a mutation step with the given probability. If a mutation step
     * is performed, this does not necessarily mean that any values are changed.
     *
     * @param prob
     *            Probability for the application of mutation.
     *
     * @return {@code true} if the mutation step was performed, {@code false} if
     *         the step was skipped.
     *
     */
    public boolean mutate(final double prob) {
        if (Math.random() < prob) {
            mutate();
            return true;
        }
        System.out.println("-- skipped mutation for " + toString());
        return false;
    }

    /**
     * Returns a copy of the genome of the individual, or <code>null</code> if
     * no genome is set.
     *
     * @return a copy of the genome (may be <code>null</code>).
     */
    public Genome getGenome() {
        if (this.genome != null) {
            return new Genome(this.genome);
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(this.generation);
        result.append(".");
        result.append(this.id);
        result.append(" (");
        result.append(this.rating);
        result.append(")");
        for (final IGene<?> gene : this.genome) {
            result.append(" - ");
            result.append(gene.toString());
        }
        return result.toString();
    }

    // private fields
    private final int generation;
    private String id;
    private int rating;
    private Genome genome; // TODO: make genome final and immutable
}
