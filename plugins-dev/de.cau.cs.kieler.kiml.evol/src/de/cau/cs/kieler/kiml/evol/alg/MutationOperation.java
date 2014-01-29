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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
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
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class MutationOperation implements IEvolutionaryOperation {

    /**
     * The mutation application probability. This is the probability for each
     * individual to be subject to mutation.
     */
    private static final double MUTATION_APPLICATION_PROBABILITY = 0.6;

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
    public void process(final Population population, final IKielerProgressMonitor monitor) {
        monitor.begin("Mutation", 1);
        ILayoutConfig layoutConfig = population.getProperty(Population.DEFAULT_CONFIG);
        
        ListIterator<Genome> genomeIter = population.listIterator();
        while (genomeIter.hasNext()) {
            Genome individual = genomeIter.next();
            if (random.nextDouble() < MUTATION_APPLICATION_PROBABILITY) {
                Genome mutation = mutate(individual, layoutConfig, 1);
                GenomeFactory.checkGenome(mutation);
                genomeIter.set(mutation);
            }
        }
        
        monitor.done();
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
     * @param mutationFactor factor for mutation probability of genes
     * @return mutated copy of the given genome
     */
    public Genome mutate(final Genome genome, final ILayoutConfig layoutConfig,
            final double mutationFactor) {
        Genome newGenome = new Genome();
        for (LayoutContext context : genome.getContexts()) {
            mutate(genome, newGenome, layoutConfig, context, mutationFactor);
        }
        return newGenome;
    }

    /**
     * Mutate the genes of the given individual into a new genome.
     *
     * @param oldGenome the original genome
     * @param newGenome the new mutated genome
     * @param layoutConfig the layout configuration used to obtain default values
     * @param layoutContext the layout context used to obtain default values
     * @param mutationFactor factor for mutation probability of genes
     * @return mutated copy of the given genome
     */
    private void mutate(final Genome oldGenome, final Genome newGenome,
            final ILayoutConfig layoutConfig, final LayoutContext layoutContext,
            final double mutationFactor) {
        LayoutTypeData newLayoutType = null;
        LayoutAlgorithmData newLayoutAlgo = null;
        boolean noAlgorithmInSelectedType = false;
        newGenome.addContext(layoutContext, oldGenome.getSize(layoutContext));
        for (Gene<?> gene : oldGenome.getGenes(layoutContext)) {
            TypeInfo<?> typeInfo = gene.getTypeInfo();
            GeneType geneType = typeInfo.getGeneType();
            
            if (newLayoutAlgo != null) {
                // update the active flag of the gene in order to match the new algorithm
                LayoutOptionData optionData = (LayoutOptionData) typeInfo.getTypeParam();
                if (newLayoutAlgo.knowsOption(optionData)) {
                    if (gene.getValue() == null) {
                        // the gene previously had no assigned value - generate one
                        gene = GenomeFactory.createDefaultGene(newLayoutAlgo, optionData,
                                typeInfo, layoutConfig, layoutContext);
                    } else if (!gene.isActive()) {
                        gene = Gene.create(gene, true);
                    }
                } else if (gene.isActive()) {
                    gene = Gene.create(gene, false);
                }
            } else if (noAlgorithmInSelectedType) {
                // the layout type has mutated, but the new type has no active algorithm
                if (gene.isActive()) {
                    gene = Gene.create(gene, false);
                }
            }

            Gene<?> newGene = gene;
            if (newLayoutType != null && geneType == GeneType.LAYOUT_ALGO) {
                // the layout type has changed, so we are forced to choose a different algorithm
                newGene = GenomeFactory.createAlgorithmGene(newLayoutType, random);
                if (newGene.getValue() != null) {
                    newLayoutAlgo = (LayoutAlgorithmData) newGene.listValue();
                } else {
                    noAlgorithmInSelectedType = true;
                }
                
            } else if (gene.getValue() != null
                    && random.nextDouble() < typeInfo.getProbability() * mutationFactor) {
                // unleash the mutant
                newGene = mutate(gene);
                
                if (geneType == GeneType.LAYOUT_TYPE) {
                    // we mutated the layout type 
                    newLayoutType = (LayoutTypeData) newGene.listValue();
                } else if (geneType == GeneType.LAYOUT_ALGO) {
                    // we mutated the layout algorithm
                    newLayoutAlgo = (LayoutAlgorithmData) newGene.listValue();
                }
            }
            
            newGenome.getGenes(layoutContext).add(newGene);
        }
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
        GeneType geneType = typeInfo.getGeneType();
        switch (geneType) {
        case INTEGER:
        {
            // produce a new integer value within the valid bounds
            Comparable<Integer> lowerBound = (Comparable<Integer>) typeInfo.getLowerBound();
            Comparable<Integer> upperBound = (Comparable<Integer>) typeInfo.getUpperBound();
            double variance = typeInfo.getVariance();
            double oldValue = gene.floatValue();
            double newValue = oldValue + random.nextGaussian() * variance;
            double hv = variance / 2;
            if (newValue < oldValue
                    && lowerBound.compareTo((int) Math.floor(newValue - hv)) > 0) {
                // the value is too close to the lower bound - scale it up
                double lb = (Integer) lowerBound;
                newValue = scaleToLowerBound(newValue, Math.min(oldValue, lb + hv), lb);
            } else if (newValue > oldValue
                    && upperBound.compareTo((int) Math.ceil(newValue + hv)) < 0) {
                // the value is too close to the upper bound - scale it down
                double ub = (Integer) upperBound;
                newValue = scaleToUpperBound(newValue, Math.max(oldValue, ub - hv), ub);
            }
            return Gene.create((int) Math.round(newValue), typeInfo, gene.isActive());
        }
            
        case FLOAT:
        {
            // produce a new floating point value within the valid bounds
            Comparable<Float> lowerBound = (Comparable<Float>) typeInfo.getLowerBound();
            Comparable<Float> upperBound = (Comparable<Float>) typeInfo.getUpperBound();
            double variance = typeInfo.getVariance();
            double oldValue = gene.floatValue();
            double newValue = oldValue + random.nextGaussian() * variance;
            double hv = variance / 2;
            if (newValue < oldValue && lowerBound.compareTo((float) (newValue - hv)) > 0) {
                // the value is too close to the lower bound - scale it up
                double lb = (Float) lowerBound;
                newValue = scaleToLowerBound(newValue, Math.min(oldValue, lb + hv), lb);
            } else if (newValue > oldValue && upperBound.compareTo((float) (newValue + hv)) < 0) {
                // the value is too close to the upper bound - scale it down
                double ub = (Float) upperBound;
                newValue = scaleToUpperBound(newValue, Math.max(oldValue, ub - hv), ub);
            }
            return Gene.create((float) newValue, typeInfo, gene.isActive());
        }
            
        default:
            // assign a random value index different from the previous one
            int lowerBound = (Integer) typeInfo.getLowerBound();
            int upperBound = (Integer) typeInfo.getUpperBound();
            int oldValue = gene.intValue();
            int newValue;
            if (upperBound - lowerBound <= 1) {
                // at most two values are allowed - flip the current value
                newValue = upperBound - oldValue + lowerBound;
            } else {
                newValue = random.nextInt(upperBound - lowerBound) + lowerBound;
                if (newValue >= oldValue) {
                    newValue++;
                }
            }
            return Gene.create(newValue, typeInfo, gene.isActive());
        }
    }
    
    /**
     * Scale a value towards a lower bound.
     * 
     * @param value the value
     * @param lowerRef the reference value for scaling
     * @param lowerBound the lower bound
     * @return a value that is greater or equal to the lower bound
     */
    private double scaleToLowerBound(final double value, final double lowerRef,
            final double lowerBound) {
        double newValue;
        if (lowerRef > lowerBound) {
            double excess = (lowerRef - value) / (lowerRef - lowerBound);
            double normalized = 1 - 1 / (excess + 1);
            newValue = lowerRef - normalized * (lowerRef - lowerBound);
            assert newValue >= lowerBound;
        } else {
            newValue = lowerBound;
        }
        return newValue;
    }
    
    /**
     * Scale a value towards an upper bound.
     * 
     * @param value the value
     * @param upperRef the reference value for scaling
     * @param upperBound the upper bound
     * @return a value that is less or equal to the upper bound
     */
    private double scaleToUpperBound(final double value, final double upperRef,
            final double upperBound) {
        double newValue;
        if (upperRef < upperBound) {
            double excess = (value - upperRef) / (upperBound - upperRef);
            double normalized = 1 - 1 / (excess + 1);
            newValue = normalized * (upperBound - upperRef) + upperRef;
            assert newValue <= upperBound;
        } else {
            newValue = upperBound;
        }
        return newValue;
    }

}
