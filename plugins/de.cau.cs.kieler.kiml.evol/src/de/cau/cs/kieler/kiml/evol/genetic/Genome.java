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
                    if (ind0.getRating() == ind1.getRating()) {
                        return 0;
                    } else if (ind0.getRating() < ind1.getRating()) {
                        return 1;
                    }
                    return -1;
                }
            };

    // return a recombination of the given genomes.
    private static Genome newRecombination(final Genome... genomes) {
        Genome result = new Genome();
        if (genomes.length == 1) {
            result = new Genome(genomes[0]);
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
                final IGene<?> oldGene = genomes[0].get(g);
                final IGene<?> newGene = oldGene.recombineWith(otherGenes);
                result.add(newGene);
            }
        }
        return result;
    }

    /**
     * Constructs an empty genome.
     */
    public Genome() {
        super();
        this.id = Integer.toHexString(hashCode());
        this.generation = 0;
    }

    /**
     * Copy constructor for a genome.
     *
     * @param genome
     *            template genome. Must not be {@code null}.
     */
    public Genome(final Genome genome) {
        this(genome, genome.generation);
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
    public Genome(final Genome theGenome, final int theGeneration) {
        this(null, theGenome, theGeneration);
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
    public Genome(final String theId, final Genome theGenome, final int theGeneration) {
        if (theId == null) {
            this.id = Integer.toHexString(hashCode());
        } else {
            this.id = theId;
        }
        // this.genome = new Genome(theGenome);

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

    // private fields
    private final int generation;

    private String id;

    private int rating;

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
    public int getRating() {
        return this.rating;
    }

    /**
     *
     * @return {@code true} if this individual has been rated.
     */
    public boolean hasRating() {
        return (this.rating != 0);
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
    public Genome mutate(final double prob) {
        if (Math.random() < prob) {
            return createMutation();
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
    public void setRating(final int theRating) {
        // System.out.println("Assign rating " + theRating + " to individual" +
        // ": " + toString());
        this.rating = theRating;
    }
    // /**
    // * Returns a copy of the genome of the individual, or <code>null</code> if
    // * no genome is set.
    // *
    // * @return a copy of the genome (may be <code>null</code>).
    // */
    // public Genome getGenome() {
    // if (this.genome != null) {
    // return new Genome(this);
    // }
    // return null;
    // }
    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append(this.generation);
        result.append(".");
        result.append(getId());
        result.append(" (");
        result.append(this.rating);
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
    private Genome createMutation() {
        final Genome newGenome = new Genome();
        for (final IGene<?> gene : this) {
            Assert.isNotNull(gene);
            final IGene<?> newGene = gene.newMutation();
            Assert.isNotNull(newGene, "Invalid mutation of " + gene);
            newGenome.add(newGene);
        }
        newGenome.setRating(this.rating);
        return newGenome;
    }

}
