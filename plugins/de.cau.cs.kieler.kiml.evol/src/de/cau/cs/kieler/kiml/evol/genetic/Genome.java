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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
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
public class Genome extends MapPropertyHolder {

    /** Property for the user defined rating. */
    public static final IProperty<Double> USER_RATING = new Property<Double>("evol.userRating");
    /** Property for the generation number. */
    public static final IProperty<Integer> GEN_NUMBER = new Property<Integer>("evol.generationNumber");

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
     */
    public Genome() {
        genes = new LinkedList<Gene<?>>();
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
        for (final Gene<?> gene : genes) {
            if (gene.getId().equals(theId)) {
                return gene;
            }
        }
        return null;
    }

    /**
     * Returns the id of the individual.
     *
     * @return the id
     */
    public String getId() {
        return Integer.toHexString(this.hashCode());
    }

    /**
     * Create a list of gene identifiers.
     * 
     * @return a list of the identifiers occurring in this genome.
     */
    public List<String> getGeneIds() {
        List<String> result = new ArrayList<String>(genes.size());
        for (final Gene<?> gene : genes) {
            result.add(gene.getId());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getProperty(GEN_NUMBER)).append(".").append(getId());
        result.append(" (").append(getProperty(USER_RATING)).append(")");
        for (final Gene<?> gene : genes) {
            result.append(" - ");
            result.append(gene.toString());
        }
        return result.toString();
    }

    /** Default distance for list item genes. */
    private static final double LIST_ITEM_GENE_DISTANCE = 2.5;
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
        if (genome0.genes.size() != genome1.genes.size()) {
            throw new IllegalArgumentException();
        }

        Iterator<?> iter0 = genome0.getGenes().iterator();
        Iterator<?> iter1 = genome1.getGenes().iterator();
        double dist = 0.0;
        while (iter0.hasNext() && iter1.hasNext()) {
            Gene<?> gene0 = (Gene<?>) iter0.next();
            Gene<?> gene1 = (Gene<?>) iter1.next();

            if (!gene0.equals(gene1)) {
                switch (gene0.getTypeInfo().getGeneType()) {
                case LIST_ITEM:
                    dist += LIST_ITEM_GENE_DISTANCE;
                    break;
                case FLOAT:
                case INTEGER:
                    // Distance of float genes is analogous to the absolute difference,
                    // related to the variance in order to make it more comparable.
                    double var0 = gene0.getTypeInfo().getVariance();
                    double var1 = gene1.getTypeInfo().getVariance();
                    double var = (var0 + var1) / 2;
                    float absDiff = Math.abs(gene0.floatValue() - gene1.floatValue());
                    dist += (absDiff * FLOAT_GENE_SCALE) / var;
                    break;
                default:
                    dist += DEFAULT_GENE_DISTANCE;
                }
            }
        }
        return dist;
    }

    /**
     * Descending rating comparator.
     */
    public static final Comparator<Genome> DESCENDING_RATING_COMPARATOR = new Comparator<Genome>() {
                /**
                 * Small value for floating-point comparison.
                 */
                private static final double EPSILON = 1.0E-6;

                public int compare(final Genome ind0, final Genome ind1) {
                    Double rating0 = ind0.getProperty(USER_RATING);
                    Double rating1 = ind1.getProperty(USER_RATING);
                    double v0 = rating0 == null ? 0.0 : rating0;
                    double v1 = rating1 == null ? 0.0 : rating1;

                    if (Math.abs(v0 - v1) < EPSILON) {
                        return 0;
                    } else if (v0 < v1) {
                        return 1;
                    }
                    return -1;
                }
            };
    
}
