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
import de.cau.cs.kieler.kiml.evol.genetic.Distribution;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;

/**
 * Operation for mutation of individuals.
 * 
 * @author bdu
 */
public class MutationOperation implements IEvolutionaryOperation {

    /**
     * The mutation application probability. This is the probability for each
     * individual to be subject to mutation.
     */
    private static final double MUTATION_APPLICATION_PROBABILITY = 0.6;
    
    /**
     * Factor by which the user rating fades after a mutation has been performed.
     */
    private static final double USER_RATING_FADE = 0.9;

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
        Population mutations = new Population();
        for (Genome ind : population) {
            if (random.nextDouble() < MUTATION_APPLICATION_PROBABILITY) {
                Genome mutation = mutate(ind);
                // individual has mutated -- rating is outdated
                mutation.setProperty(Genome.USER_RATING, ind.getProperty(Genome.USER_RATING)
                        * USER_RATING_FADE);
                mutations.getGenomes().add(mutation);
            } else {
                mutations.getGenomes().add(ind);
            }
        }
        population.getGenomes().clear();
        population.getGenomes().addAll(mutations.getGenomes());
    }

    /**
     * Mutate the genes of the given individual.
     *
     * @param genome a genome
     * @return mutated copy of the given genome
     */
    public Genome mutate(final Genome genome) {
        Genome newGenome = new Genome();
        for (final Gene<?> gene : genome.getGenes()) {
            Gene<?> newGene = mutate(gene);
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
    public Gene<?> mutate(final Gene<?> gene) {
        TypeInfo<?> typeInfo = gene.getTypeInfo();
        if (random.nextDouble() >= typeInfo.getProbability()) {
            // mutation probability has not passed - do not mutate
            return Gene.create(gene);
        }
        
        GeneType geneType = typeInfo.getGeneType();
        Integer intValue = 0;
        switch (geneType) {
        case LIST_ITEM:
        case BOOLEAN:
        case ENUM:
        {
            assert typeInfo.getDistr() == Distribution.UNIFORM;
            // assign a random value index (may be the same as before)
            int lowerBound = (Integer) typeInfo.getLowerBound();
            int upperBound = (Integer) typeInfo.getUpperBound();
            intValue = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
            return Gene.create(gene.getId(), intValue, typeInfo);
        }
            
        case INTEGER:
        {
            // produce a new value within the valid bounds.
            int lowerBound = (Integer) typeInfo.getLowerBound();
            int upperBound = (Integer) typeInfo.getUpperBound();
            switch (typeInfo.getDistr()) {
            case GAUSSIAN:
                double variance = typeInfo.getVariance();
                double value = gene.floatValue();
                do {
                    value = KielerMath.limit(value, lowerBound, upperBound);
                    value += random.nextGaussian() * Math.sqrt(variance);
                } while (value < lowerBound || value > upperBound);
                intValue = (int) Math.round(value);
                break;
            case UNIFORM:
                intValue = random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
                break;
            }
            return Gene.create(gene.getId(), intValue, typeInfo);
        }
            
        case FLOAT:
        {
            assert typeInfo.getDistr() == Distribution.GAUSSIAN;
            Float floatValue;
            // produce a new value within the valid bounds.
            float lowerBound = (Float) typeInfo.getLowerBound();
            float upperBound = (Float) typeInfo.getUpperBound();
            double variance = typeInfo.getVariance();
            double value = gene.floatValue();
            do {
                value = KielerMath.limit(value, lowerBound, upperBound);
                value += random.nextGaussian() * Math.sqrt(variance);
            } while (value < lowerBound || value > upperBound);
            floatValue = (float) value;
            return Gene.create(gene.getId(), floatValue, typeInfo);
        }
            
        default:
            return null;
        }
    }

}
