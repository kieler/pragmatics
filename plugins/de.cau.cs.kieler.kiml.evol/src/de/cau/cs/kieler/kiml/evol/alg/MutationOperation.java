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

import java.util.ListIterator;
import java.util.Random;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.GenomeFactory;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;

/**
 * Operation for mutation of individuals.
 * 
 * @author bdu
 * @author msp
 */
public class MutationOperation implements IEvolutionaryOperation {

    /**
     * The mutation application probability. This is the probability for each
     * individual to be subject to mutation.
     */
    private static final double MUTATION_APPLICATION_PROBABILITY = 0.6;
    /**
     * Factor by which the user rating weight fades after a mutation has been performed.
     */
    private static final double USER_WEIGHT_FADE = 0.9;

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
        ILayoutConfig layoutConfig = population.getProperty(Population.DEFAULT_CONFIG);
        LayoutContext layoutContext = population.getProperty(Population.DEFAULT_CONTEXT);
        
        ListIterator<Genome> genomeIter = population.listIterator();
        while (genomeIter.hasNext()) {
            Genome individual = genomeIter.next();
            if (random.nextDouble() < MUTATION_APPLICATION_PROBABILITY) {
                Genome mutation = mutate(individual, layoutConfig, layoutContext);
                // individual has mutated -- the user rating is outdated
                mutation.setProperty(Genome.USER_WEIGHT, individual.getProperty(Genome.USER_WEIGHT)
                        * USER_WEIGHT_FADE);
                genomeIter.set(mutation);
            }
        }
    }

    /**
     * Mutate the genes of the given individual.
     * <p>
     * NOTE: This implementation relies on the correct ordering of genes in the given genome.
     * If a layout type gene is present, it must precede all other genes, and if a layout
     * algorithm gene is present, it must precede all genes except the layout type gene.
     *
     * @param genome a genome
     * @param layoutConfig the layout configuration used to obtain default values
     * @param layoutContext the layout context used to obtain default values
     * @return mutated copy of the given genome
     */
    public Genome mutate(final Genome genome, final ILayoutConfig layoutConfig,
            final LayoutContext layoutContext) {
        LayoutTypeData newLayoutType = null;
        LayoutAlgorithmData newLayoutAlgo = null;
        Genome newGenome = new Genome(genome.getSize());
        for (final Gene<?> gene : genome.getGenes()) {
            Gene<?> newGene;
            GeneType geneType = gene.getTypeInfo().getGeneType();
            if (newLayoutType != null && geneType == GeneType.LAYOUT_ALGO) {
                
                // the layout type has changed, so we are forced to choose a different algorithm
                newGene = GenomeFactory.createAlgorithmGene(newLayoutType, random);
                newLayoutAlgo = (LayoutAlgorithmData) newGene.listValue();
                
            } else if (newLayoutAlgo != null) {
                // we mutated the layout algorithm, so all other genes must be validated
                LayoutOptionData<?> optionData = (LayoutOptionData<?>) gene.getTypeInfo()
                        .getTypeParam();
                if (newLayoutAlgo.knowsOption(optionData)) {
                    if (gene.getValue() != null) {
                        newGene = mutate(gene);
                    } else  {
                        // the gene previously had no assigned value - generate one
                        newGene = GenomeFactory.createDefaultGene(newLayoutAlgo, optionData,
                                gene.getTypeInfo(), layoutConfig, layoutContext);
                    }
                    newGene.setActive(true);
                } else {
                    newGene = Gene.create(gene);
                    newGene.setActive(false);
                }
                
            } else if (gene.getValue() != null) {
                newGene = mutate(gene);
                
                if (geneType == GeneType.LAYOUT_TYPE) {
                    // check whether we mutated the layout type 
                    if (!gene.equals(newGene)) {
                        newLayoutType = (LayoutTypeData) newGene.listValue();
                    }
                } else if (geneType == GeneType.LAYOUT_ALGO) {
                    // check whether we mutated the layout algorithm
                    if (!gene.equals(newGene)) {
                        newLayoutAlgo = (LayoutAlgorithmData) newGene.listValue();
                    }
                }
            } else {
                newGene = Gene.create(gene);
            }
            newGenome.getGenes().add(newGene);
        }
        return newGenome;
    }

    /**
     * Mutate a single gene.
     * 
     * @param gene a gene
     * @return a new mutated gene
     */
    @SuppressWarnings("unchecked")
    public Gene<?> mutate(final Gene<?> gene) {
        TypeInfo<?> typeInfo = gene.getTypeInfo();
        if (random.nextDouble() >= typeInfo.getProbability()) {
            // mutation probability has not passed - do not mutate
            return Gene.create(gene);
        }
        
        GeneType geneType = typeInfo.getGeneType();
        Integer intValue = 0;
        switch (geneType) {
        case INTEGER:
        {
            // produce a new integer value within the valid bounds
            Comparable<Integer> lowerBound = (Comparable<Integer>) typeInfo.getLowerBound();
            Comparable<Integer> upperBound = (Comparable<Integer>) typeInfo.getUpperBound();
            double variance = typeInfo.getVariance();
            double value = gene.floatValue();
            boolean inBounds = false;
            do {
                value += random.nextGaussian() * variance;
                if (lowerBound.compareTo((int) Math.floor(value)) > 0) {
                    value = (Integer) lowerBound;
                } else  if (upperBound.compareTo((int) Math.ceil(value)) < 0) {
                    value = (Integer) upperBound;
                } else {
                    inBounds = true;
                }
            } while (!inBounds);
            intValue = (int) Math.round(value);
            return Gene.create(intValue, typeInfo);
        }
            
        case FLOAT:
        {
            Float floatValue;
            // produce a new floating point value within the valid bounds
            Comparable<Float> lowerBound = (Comparable<Float>) typeInfo.getLowerBound();
            Comparable<Float> upperBound = (Comparable<Float>) typeInfo.getUpperBound();
            double variance = typeInfo.getVariance();
            double value = gene.floatValue();
            boolean inBounds = false;
            do {
                value += random.nextGaussian() * variance;
                if (lowerBound.compareTo((float) value) > 0) {
                    value = (Float) lowerBound;
                } else if (upperBound.compareTo((float) value) < 0) {
                    value = (Float) upperBound;
                } else {
                    inBounds = true;
                }
            } while (!inBounds);
            floatValue = (float) value;
            return Gene.create(floatValue, typeInfo);
        }
            
        default:
            // assign a random value index (may be the same as before)
            int lowerBound = (Integer) typeInfo.getLowerBound();
            int upperBound = (Integer) typeInfo.getUpperBound();
            intValue = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
            return Gene.create(intValue, typeInfo);
        }
    }

}
