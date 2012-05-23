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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Random;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;

/**
 * Generates offspring by recombining selected parent individuals. The genetic
 * material of two or more parent individuals is put together to produce one or
 * more offspring.
 *
 * @author bdu
 * @author msp
 */
public class CrossoverOperation implements IEvolutionaryOperation {

    /**
     * The selection ratio. Indicates the ratio of the population that shall be
     * selected for recombination.
     */
    private static final float SELECTION_RATIO = 0.70f;
    /** Minimum number of individuals to select. */
    private static final int MIN_SELECT = 2;
    /** Maximum number of individuals to select. */
    private static final int MAX_SELECT = 1000;
    
    /**
     * The cross over ratio. Indicates how many offspring individuals shall be
     * created per selected individual.
     */
    private static final float CROSS_OVER_RATIO = 1.2f;
    /** Minimum number of individuals to create by cross over. */
    private static final int MIN_CROSS_OVERS = 1;
    /** Maximum number of individuals to create by cross over. */
    private static final int MAX_CROSS_OVERS = 1000;

    /** the random number generator. */
    private Random random;

    /**
     * {@inheritDoc}
     */
    public void setRandom(final Random therandom) {
        this.random = therandom;
    }

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {
        // We need a minimal number of individuals to do crossovers
        if (population.getSize() < MIN_SELECT) {
            return;
        }
        
        // Only some are allowed to generate offspring - these are selected by truncation
        int selectCount = KielerMath.limit(Math.round(population.getSize() * SELECTION_RATIO),
                MIN_SELECT, MAX_SELECT);
        int crossoverCount = KielerMath.limit(Math.round(selectCount * CROSS_OVER_RATIO),
                MIN_CROSS_OVERS, MAX_CROSS_OVERS);

        Genome[] offspring = new Genome[crossoverCount];
        for (int i = 0; i < crossoverCount; i++) {
            Genome parent1 = population.getGenomes().get(random.nextInt(selectCount));
            Genome parent2;
            do {
                parent2 = population.getGenomes().get(random.nextInt(selectCount));
            } while (parent1 == parent2);

            offspring[i] = recombine(parent1, parent2);
        }

        // add offspring to current population (old survivors)
        for (int i = 0; i < crossoverCount; i++) {
            population.getGenomes().add(offspring[i]);
        }
    }

    /**
     * Create a recombination of multiple genomes.
     * 
     * @param genomes
     *            the genomes to recombine
     * @return a recombination of the given genomes
     */
    public Genome recombine(final Genome... genomes) {
        Genome result = new Genome();
        Genome oldGenome = genomes[0];
        if (genomes.length == 1) {
            result = new Genome(oldGenome);
            result.setProperty(Genome.USER_RATING, oldGenome.getProperty(Genome.USER_RATING));
        } else {
            int size = oldGenome.getGenes().size();
            // iterate genes
            for (int gn = 0; gn < size; gn++) {
                Gene<?>[] genes = new Gene[genomes.length];
                for (int gm = 0; gm < genomes.length; gm++) {
                    assert genomes[gm].getSize() == size;
                    genes[gm] = genomes[gm].getGenes().get(gn);
                }
                Gene<?> newGene = recombine(genes);
                result.getGenes().add(newGene);
            }
            // Use the average of the genomes' ratings as the new rating.
            double ratingSum = 0;
            for (final Genome genome : genomes) {
                Double rating = genome.getProperty(Genome.USER_RATING);
                if (rating != null) {
                    ratingSum += rating;
                }
            }
            double average = ratingSum / genomes.length;
            result.setProperty(Genome.USER_RATING, average);
        }
        return result;
    }

    /**
     * Provide a cross over of genes. For number typed genes an average value is computed.
     * For others one of the given genes is randomly picked with uniform probability.
     *
     * @param genes
     *            genes that are to be recombined
     * @return a new gene that is a cross over of the given genes
     */
    public Gene<?> recombine(final Gene<?>... genes) {
        final Gene<?> oldGene = genes[0];
        GeneType geneType = oldGene.getTypeInfo().getGeneType();
        switch (geneType) {
        case INTEGER:
        case FLOAT:
            // return average of the genes
            float sum = 0;
            for (Gene<?> gene : genes) {
                sum += gene.floatValue();
            }
            int count = genes.length;
            float average = sum / count;
            if (geneType == GeneType.INTEGER) {
                return Gene.create(oldGene.getId(), Math.round(average), oldGene.getTypeInfo());
            } else {
                return Gene.create(oldGene.getId(), average, oldGene.getTypeInfo());
            }
            
        default:
            int pos = random.nextInt(genes.length);
            return Gene.create(genes[pos]);
        }
    }


}
