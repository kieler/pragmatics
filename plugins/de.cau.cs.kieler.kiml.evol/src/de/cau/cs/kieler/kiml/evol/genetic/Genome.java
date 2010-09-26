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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
                    final double v0 = (ind0.getUserRating() == null ? 0.0 : ind0.getUserRating());
                    final double v1 = (ind1.getUserRating() == null ? 0.0 : ind1.getUserRating());

                    if (v0 == v1) {
                        return 0;
                    } else if (v0 < v1) {
                        return 1;
                    }
                    return -1;
                }
            };

    private static final double LIST_ITEM_GENE_DISTANCE = 2.5;

    private static final double DEFAULT_GENE_DISTANCE = 1.0;

    private static final float FLOAT_GENE_SCALE = 0.8f;

    /**
     * Returns the distance between the given genomes. The genomes must be
     * compatible, i.e. have the same gene types in the same order.
     *
     * @param genome0
     *            a {@link Genome}
     * @param genome1
     *            another {@link Genome}
     * @return the distance between the genomes
     */
    public static final double distance(final Genome genome0, final Genome genome1) {

        Assert.isLegal((genome0 != null) && (genome1 != null)
                && (genome0.size() == genome1.size()));
        if ((genome0 == null) || (genome1 == null)) {
            return 0.0;
        }

        Iterator<?> iter0;
        Iterator<?> iter1;
        double dist = 0.0;
        for (iter0 = genome0.iterator(), iter1 = genome1.iterator(); iter0.hasNext()
                && iter1.hasNext();) {
            final IGene<?> gene0 = (IGene<?>) iter0.next();
            final IGene<?> gene1 = (IGene<?>) iter1.next();

            if (!gene0.equals(gene1)) {
                System.out.println(gene0 + " !equals " + gene1);
                if (gene0 instanceof ListItemGene) {
                    dist += LIST_ITEM_GENE_DISTANCE;
                } else if ((gene0 instanceof UniversalGene)
                        && (((UniversalGene) gene0).getTypeInfo().getTypeClass() == Float.class)) {
                    // Distance of float genes is analogous to the absolute
                    // difference, related to the variance in order to make it
                    // more comparable.
                    final UniversalGene g0 = ((UniversalGene) gene0);
                    final UniversalGene g1 = ((UniversalGene) gene1);
                    final double var0 = g0.getMutationInfo().getVariance();
                    final double var1 = g1.getMutationInfo().getVariance();
                    final double var = (var0 + var1) * .5;
                    final float absDiff =
                            Math.abs(g0.getValue().floatValue() - g1.getValue().floatValue());
                    dist += absDiff * FLOAT_GENE_SCALE / var;

                } else {
                    dist += DEFAULT_GENE_DISTANCE;
                }
            }
        }
        System.out.println(dist + " differences.");
        return dist;
    }

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
        this(genome, genome.generation);
    }

    /**
     * Constructor for an individual with the given genome and the given
     * generation.
     *
     * @param theGenome
     *            initial genome for this individual
     * @param theGeneration
     *            the generation
     *
     */
    public Genome(final Genome theGenome, final int theGeneration) {

        Assert.isLegal(theGenome != null);
        if (theGenome != null) {
            for (final IGene<?> gene : theGenome) {
                if (gene != null) {
                    this.add(gene);
                }
            }
            this.userRating = theGenome.getUserRating();
            this.features = theGenome.getFeatures();
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
        if (hasUserRating()) {
            final double scalingFactor = .90;
            this.userRating =
 Double.valueOf((int) Math.round((this.userRating * scalingFactor)));
        }
    }

    /**
     * The features are a collection of measurable properties that the
     * individual sports in its phenotype. Automatic rating of the individual is
     * based on these features.
     *
     * @return an unmodifiable view of the map of features
     */
    public Map<String, Object> getFeatures() {
        return Collections.unmodifiableMap(this.features);
    }

    /**
     * Sets the features.
     *
     * @param theFeatures
     *            the features to set.
     */
    public void setFeatures(final Map<String, Object> theFeatures) {
        this.features = theFeatures;
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
    public synchronized Double getUserRating() {
        return this.userRating;
    }

    /**
     *
     * @return {@code true} if this individual has been rated.
     */
    public boolean hasUserRating() {
        return (this.userRating != null);
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
     *            An Integer (may be negative). A higher value means a better
     *            rating.
     */
    public synchronized void setUserRating(final Double theRating) {
        System.out.println("Assign rating " + theRating + " to individual" + ": " + getId());

        final Double oldRating = getUserRating();

        // compare new rating to previous one
        if (hasUserRating() && (theRating != null)) {
            if (oldRating.intValue() < theRating.intValue()) {
                System.out.println("Ind. was under-rated (" + oldRating + " -> " + theRating + ")");
            } else if (oldRating.intValue() > theRating.intValue()) {
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
                @SuppressWarnings("rawtypes" /* otherGenes is a mixture */)
                final IGene[] otherGenes = geneList.toArray(new IGene[geneList.size()]);
                final IGene<?> oldGene = oldGenome.get(g);
                @SuppressWarnings("unchecked")
                final IGene<?> newGene = oldGene.recombineWith(otherGenes);
                result.add(newGene);
            }
            // determine the rating
            double ratingSum = 0;
            for (final Genome genome : genomes) {
                ratingSum += (genome.hasUserRating() ? genome.getUserRating() : 0.0);
            }
            final double average = ratingSum / genomes.length;
            result.setUserRating(Double.valueOf(average));
        }
        return result;
    }

    // private fields
    private final int generation;
    private Double userRating = null;

    private Map<String, Object> features = Collections.emptyMap();

    /**
     * Find a gene with the given ID.
     *
     * @param theId
     *            an ID
     * @return a gene with the given ID; or {@code null} if none can be found
     */
    public IGene<?> find(final String theId) {
        for (final IGene<?> gene : this) {
            if (gene.getId().equals(theId)) {
                return gene;
            }
        }
        return null;
    }

    /**
     * @return a list of the IDs occurring in this genome.
     */
    public List<String> getIds() {
        final List<String> result = new LinkedList<String>();
        for (final IGene<?> gene : this) {
            result.add((String) gene.getId());
        }
        return result;
    }

    /**
     * Adds a feature to the map of features.
     *
     * @param key
     *            the key; must not be {@code null}
     * @param value
     *            the value of the feature
     * @return the previous value associated with key, or {@code null} if there
     *         was no mapping for key.
     */
    public Object addFeature(final String key, final Object value) {
        Assert.isLegal(key != null);
        Assert.isNotNull(this.features);

        if (this.features != null) {
            this.features = new HashMap<String, Object>(this.features);

            return this.features.put(key, value);
        }

        return null;
    }

}
