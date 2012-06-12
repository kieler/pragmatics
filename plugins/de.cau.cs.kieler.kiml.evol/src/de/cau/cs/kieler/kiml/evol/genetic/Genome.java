/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;

/**
 * A genome has a list of Gene objects. It can be used as an individual in an
 * evolutionary algorithm.
 *
 * @kieler.rating 2011-07-08 yellow reviewed by swe, ima, msp
 * @author bdu
 * @author msp
 */
public class Genome extends MapPropertyHolder implements Comparable<Genome> {

    /** property for the user defined rating value. */
    public static final IProperty<Double> USER_RATING = new Property<Double>("evol.userRating", 0.0);
    /** property for the weight of the user rating. */
    public static final IProperty<Double> USER_WEIGHT = new Property<Double>("evol.userWeight", 0.0);
    /** property for the automatically determined rating value. */
    public static final IProperty<Double> AUTO_RATING = new Property<Double>("evol.autoRating");
    /** property for the overall fitness composed of user rating and auto rating. */
    public static final IProperty<Double> FITNESS = new Property<Double>("evol.fitness");

    /** The genes of this genome. */
    private final List<Gene<?>> genes;

    /**
     * Returns the number of genes in this genome.
     *
     * @return the number of genes
     */
    public final int getSize() {
        return getGenes().size();
    }

    /**
     * Returns the genes contained in this genome.
     * 
     * @return the genes
     */
    public List<Gene<?>> getGenes() {
        return genes;
    }

    /**
     * Constructs an empty genome.
     * 
     * @param size
     *            the expected number of genes that will be added
     */
    public Genome(final int size) {
        genes = new ArrayList<Gene<?>>(size);
    }

    /**
     * Copy constructor for a genome.
     *
     * @param genome
     *            the template genome
     */
    public Genome(final Genome genome) {
        this.genes = new ArrayList<Gene<?>>(genome.genes.size());
        this.genes.addAll(genome.genes);
        this.copyProperties(genome);
    }

    /**
     * Find a gene with the given identifier.
     *
     * @param theId
     *            a gene identifier
     * @return a gene with the given ID, or {@code null} if none can be found
     */
    public Gene<?> find(final String theId) {
        for (Gene<?> gene : genes) {
            if (gene.getTypeInfo().getId().equals(theId)) {
                return gene;
            }
        }
        return null;
    }

    /**
     * Generate an identifier string for the individual.
     *
     * @return an identifier
     */
    public String getId() {
        return Integer.toHexString(this.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getId()).append(" (");
        result.append(getProperty(USER_RATING) * getProperty(USER_WEIGHT)).append(")");
        for (Gene<?> gene : genes) {
            result.append(" - ");
            result.append(gene.toString());
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final Genome other) {
        Double fitness1 = this.getProperty(FITNESS);
        Double fitness2 = other.getProperty(FITNESS);
        if (fitness1 != null && fitness2 != null) {
            return fitness2.compareTo(fitness1);
        } else if (fitness1 != null) {
            return -1;
        } else if (fitness2 != null) {
            return 1;
        }
        return 0;
    }

    /** Default distance for layout type genes. */
    private static final double TYPE_GENE_DISTANCE = 7;
    /** Default distance for layout algorithm genes. */
    private static final double ALGO_GENE_DISTANCE = 2.5;
    /** Default gene distance. */
    private static final double DEFAULT_GENE_DISTANCE = 1.0;
    /** Scaling factor for float gene distances. */
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
    public static double distance(final Genome genome0, final Genome genome1) {
        assert genome0.genes.size() == genome1.genes.size();

        Iterator<?> iter0 = genome0.getGenes().iterator();
        Iterator<?> iter1 = genome1.getGenes().iterator();
        double dist = 0.0;
        while (iter0.hasNext() && iter1.hasNext()) {
            Gene<?> gene0 = (Gene<?>) iter0.next();
            Gene<?> gene1 = (Gene<?>) iter1.next();

            if (gene0.isActive() && gene1.isActive() && !gene0.equals(gene1)) {
                switch (gene0.getTypeInfo().getGeneType()) {
                case LAYOUT_TYPE:
                    dist += TYPE_GENE_DISTANCE;
                    break;
                case LAYOUT_ALGO:
                    dist += ALGO_GENE_DISTANCE;
                    break;
                case FLOAT:
                case INTEGER:
                    // Distance of float genes is analogous to the absolute difference,
                    // related to the variance in order to make it more comparable.
                    double var0 = gene0.getTypeInfo().getVariance();
                    double var1 = gene1.getTypeInfo().getVariance();
                    double var = (var0 + var1) / 2;
                    float absDiff = Math.abs(gene0.floatValue() - gene1.floatValue());
                    dist += absDiff * FLOAT_GENE_SCALE / var;
                    break;
                default:
                    dist += DEFAULT_GENE_DISTANCE;
                }
            }
        }
        return dist;
    }
    
}
