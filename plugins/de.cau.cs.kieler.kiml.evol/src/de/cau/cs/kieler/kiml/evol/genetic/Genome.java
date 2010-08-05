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
import java.util.Comparator;
import java.util.LinkedList;

import org.eclipse.core.runtime.Assert;

/**
 *
 * A genome is a List of Gene objects. It can be used as an individual in an
 * evolutionary algorithm.
 *
 * @author bdu
 *
 */
public class Genome extends ArrayList<IGene<?>> {

    /**
     *
     */
    private static final long serialVersionUID = 6338612803085690432L;

    /**
     * Descending rating comparator.
     */
    public static final Comparator<Genome> DESCENDING_RATING_COMPARATOR = new Comparator<Genome>() {
        public int compare(final Genome ind0, final Genome ind1) {
                    if (ind0.getUserRating() == ind1.getUserRating()) {
                        return 0;
                    } else if (ind0.getUserRating() < ind1.getUserRating()) {
                        return 1;
                    }
                    return -1;
                }
            };

    /**
     * Constructs an empty genome.
     */
    public Genome() {
        super();
        this.generation = 0;
    }

    /**
     * Copy constructor for a genome.
     *
     * @param genome
     *            template genome; must not be {@code null}
     */
    public Genome(final Genome genome) {
        this(null, genome, genome.generation);
    }

// /**
    // * Copy constructor for a genome with the given generation.
    // *
    // * @param theGenome
    // * template genome; must not be {@code null}
    // * @param theGeneration
    // * the generation
    // *
    // */
    // private Genome(final Genome theGenome, final int theGeneration) {
    // this(null, theGenome, theGeneration);
    // }

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
    public Genome(final String theId, final Genome theGenome, final int theGeneration) {

        Assert.isLegal(theGenome != null);
        if (theGenome != null) {
            for (final IGene<?> gene : theGenome) {
                if (gene != null) {
                    this.add(gene);
                }
            }
        }
        this.generation = theGeneration;
        System.out.println("Created individual " + toString());
    }

    /**
     * Constructor for a genome with the given generation.
     *
     * @param theGeneration
     *            the generation
     */
    public Genome(final int theGeneration) {
        super();
        this.generation = theGeneration;
    }

    /**
     * Downscales the rating. This makes the rating less relevant without
     * discarding it completely. This can be used for outdated ratings.
     */
    public void fadeUserRating() {
        // TODO: implement more sophisticated fading of ratings
        if (this.userRating != 0) {
            final double scalingFactor = .90;
            this.userRating = (int) (this.userRating * scalingFactor);
        }
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
        return Integer.toHexString(this.hashCode());
        // return this.id;
    }

    /**
     *
     * @return the user-defined rating. A higher value means a better rating.
     *         The value may be negative.
     */
    public int getUserRating() {
        return this.userRating;
    }

    /**
     *
     * @return {@code true} if this individual has been rated.
     */
    public boolean hasUserRating() {
        return (this.userRating != 0);
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
    public Genome newMutation(final double prob) {
        if (Math.random() < prob) {
            return newMutation();
        }
        System.out.println("-- skipped mutation for " + toString());
        return null;
    }

    /**
     * Create a new genome that is a cross over of this genome and the given
     * genome.
     *
     * @param otherGenome
     *            a genome
     * @return a new genome
     */
    public Genome newRecombination(final Genome otherGenome) {
        return newRecombination(this, otherGenome);
    }

    /**
     * Sets the user-defined rating.
     *
     * @param theRating
     *            An integer value (may be negative). A higher value means a
     *            better rating.
     */
    public void setUserRating(final int theRating) {
        System.out.println("Assign rating " + theRating + " to individual" + ": " + toString());
        // compare new rating to previous one
        if (hasUserRating()) {
            final int oldRating = getUserRating();
            if (oldRating < theRating) {
                System.out.println("Ind. was under-rated (" + oldRating + " -> " + theRating + ")");
            } else if (oldRating > theRating) {
                System.out.println("Ind. was over-rated (" + oldRating + " -> " + theRating + ")");
            }
        }

        this.userRating = theRating;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(this.generation);
        result.append(".");
        result.append(getId());
        result.append(" (");
        result.append(this.userRating);
        result.append(")");
        for (final IGene<?> gene : this) {
            result.append(" - ");
            result.append(gene.toString());
        }
        return result.toString();
    }

    /**
     * Mutate the genes of the individual. Every gene is asked to provide a
     * mutated version of itself.
     */
    private Genome newMutation() {
        final Genome newGenome = new Genome(this.generation);
        for (final IGene<?> gene : this) {
            Assert.isNotNull(gene);
            final IGene<?> newGene = gene.newMutation();
            Assert.isNotNull(newGene, "Invalid mutation of " + gene);
            newGenome.add(newGene);
        }
        newGenome.setUserRating(this.userRating);

        return newGenome;
    }

    // return a recombination of the given genomes.
    private static Genome newRecombination(final Genome... genomes) {
        Genome result = new Genome();
        final Genome oldGenome = genomes[0];
        if (genomes.length == 1) {
            result = new Genome(oldGenome);
            result.setUserRating(oldGenome.getUserRating());
        } else {
            final int size = genomes[0].size();
            // iterate genes
            for (int g = 0; g < size; g++) {
                final LinkedList<IGene<?>> geneList = new LinkedList<IGene<?>>();
                int gm = 0;
                for (final Genome genome : genomes) {
                    if (gm++ > 0) {
                        Assert.isTrue(genome.size() == size);
                        geneList.add(genome.get(g));
                    }
                }
                final IGene[] otherGenes = geneList.toArray(new IGene[geneList.size()]);
                final IGene<?> oldGene = oldGenome.get(g);
                final IGene<?> newGene = oldGene.recombineWith(otherGenes);
                result.add(newGene);
            }
            int ratingSum = 0;
            for (final Genome genome : genomes) {
                ratingSum += genome.getUserRating();
            }
            final int average = Math.round(ratingSum / (float) genomes.length);
            result.setUserRating(average);
        }
        return result;
    }

    // private fields
    private final int generation;
    private int userRating;
    private int automaticRating;

}
