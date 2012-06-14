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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
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
public class CrossoverOperation extends AbstractAlgorithm implements IEvolutionaryOperation {

    /**
     * The selection ratio. Indicates the ratio of the population that shall be
     * selected for recombination.
     */
    private static final float SELECTION_RATIO = 0.70f;
    /** Minimum number of individuals to select. */
    private static final int MIN_SELECT = 2;
    /** Maximum number of individuals to select. */
    private static final int MAX_SELECT = 100;
    
    /**
     * The cross over ratio. Indicates how many offspring individuals shall be
     * created per selected individual.
     */
    private static final float CROSS_OVER_RATIO = 1.2f;
    /** Minimum number of individuals to create by cross over. */
    private static final int MIN_CROSS_OVERS = 1;
    /** Maximum number of individuals to create by cross over. */
    private static final int MAX_CROSS_OVERS = 30;
    
    /** factor by which the user rating fades for new crossovers. */
    private static final double USER_RATING_FADE = 0.8;

    /** the random number generator. */
    private Random random;

    /**
     * {@inheritDoc}
     */
    public final void setRandom(final Random therandom) {
        this.random = therandom;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final Population population) {
        getMonitor().begin("Crossover", 1);
        // We need a minimal number of individuals to do crossovers
        if (population.size() < MIN_SELECT) {
            getMonitor().done();
            return;
        }
        
        // Only some are allowed to generate offspring - these are selected by truncation
        int selectCount = KielerMath.limit(Math.round(population.size() * SELECTION_RATIO),
                MIN_SELECT, MAX_SELECT);
        int crossoverCount = KielerMath.limit(Math.round(selectCount * CROSS_OVER_RATIO),
                MIN_CROSS_OVERS, MAX_CROSS_OVERS);

        for (int i = 0; i < crossoverCount; i++) {
            Genome parent1 = population.get(random.nextInt(selectCount));
            Genome parent2;
            do {
                parent2 = population.get(random.nextInt(selectCount));
            } while (parent1 == parent2);

            Genome offspring = recombine(parent1, parent2);
            population.add(offspring);
        }
        
        getMonitor().done();
    }

    /**
     * Create a recombination of multiple genomes.
     * 
     * @param genomes
     *            the genomes to recombine
     * @return a recombination of the given genomes
     */
    public Genome recombine(final Genome... genomes) {
        assert genomes.length > 1;
        int size = genomes[0].getSize();
        Genome newGenome = new Genome(size);
        
        // iterate genes and create a recombination for each one
        LayoutTypeData layoutTypeData = null;
        int algoIndex = 0;
        for (int geneIndex = 0; geneIndex < size; geneIndex++) {
            Gene<?>[] genes = new Gene[genomes.length];
            for (int genomeIndex = 0; genomeIndex < genomes.length; genomeIndex++) {
                assert genomes[genomeIndex].getSize() == size;
                Gene<?> gene = genomes[genomeIndex].getGenes().get(geneIndex);
                // check whether the layout algorithm has the correct layout type
                if (layoutTypeData != null && gene.getTypeInfo().getGeneType() == GeneType.LAYOUT_ALGO
                        && !layoutTypeData.getId().equals(
                                ((LayoutAlgorithmData) gene.listValue()).getType())) {
                    genes[genomeIndex] = Gene.create(null, gene.getTypeInfo(), false);
                } else {
                    genes[genomeIndex] = gene;
                }
            }
            
            // create a recombination of the collected genes
            GeneType geneType = genes[algoIndex].getTypeInfo().getGeneType();
            boolean active = geneType == GeneType.LAYOUT_TYPE || geneType == GeneType.LAYOUT_ALGO
                    || genes[algoIndex].isActive();
            Gene<?> newGene = recombine(active, genes);
            newGenome.getGenes().add(newGene);
            
            if (geneType == GeneType.LAYOUT_TYPE) {
                // the layout type was crossed - get the new type to check the algorithm gene
                layoutTypeData = (LayoutTypeData) newGene.listValue();
            } else if (geneType == GeneType.LAYOUT_ALGO) {
                // the algorithm was crossed - get the index of the new algorithm
                for (int genomeIndex = 0; genomeIndex < genomes.length; genomeIndex++) {
                    if (genes[genomeIndex].equals(newGene)) {
                        algoIndex = genomeIndex;
                        break;
                    }
                }
            }
        }
        
        // Use the average of the genomes' ratings as the new rating.
        double ratingSum = 0;
        double weightSum = 0;
        for (final Genome genome : genomes) {
            double weight = genome.getProperty(Genome.USER_WEIGHT);
            ratingSum += genome.getProperty(Genome.USER_RATING) * weight;
            weightSum += weight;
        }
        if (weightSum > 0) {
            newGenome.setProperty(Genome.USER_RATING, ratingSum / weightSum);
            newGenome.setProperty(Genome.USER_WEIGHT, weightSum / genomes.length * USER_RATING_FADE);
        }
        return newGenome;
    }

    /**
     * Provide a cross over of genes. For number typed genes an average value is computed.
     * For others one of the given genes is randomly picked with uniform probability.
     * Some of the gene values may be {@code null}.
     *
     * @param active whether the new gene shall be active
     * @param genes genes that are to be recombined
     * @return a new gene that is a cross over of the given genes
     */
    public Gene<?> recombine(final boolean active, final Gene<?>... genes) {
        assert genes.length > 1;
        Gene<?> oldGene = genes[0];
        GeneType geneType = oldGene.getTypeInfo().getGeneType();
        switch (geneType) {
        case INTEGER:
        case FLOAT:
        {
            // return average of the genes
            float sum = 0;
            int count = 0;
            for (Gene<?> gene : genes) {
                if (gene.getValue() != null) {
                    sum += gene.floatValue();
                    count++;
                }
            }
            if (count > 0) {
                float average = sum / count;
                if (geneType == GeneType.INTEGER) {
                    return Gene.create(Math.round(average), oldGene.getTypeInfo(), active);
                } else {
                    return Gene.create(average, oldGene.getTypeInfo(), active);
                }
            }
            break;
        }
            
        default:
            int count = 0;
            for (Gene<?> gene : genes) {
                if (gene.getValue() != null) {
                    count++;
                }
            }
            if (count > 0) {
                // select a random gene that is active
                int randomPos = random.nextInt(count);
                int pos = 0;
                for (Gene<?> gene : genes) {
                    if (gene.getValue() != null) {
                        if (pos == randomPos) {
                            oldGene = gene;
                            break;
                        }
                        pos++;
                    }
                }
            }
        }
        
        if (oldGene.isActive() == active) {
            return oldGene;
        } else {
            return Gene.create(oldGene, active);
        }
    }


}
