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

package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Arrays;

import de.cau.cs.kieler.kiml.evol.Individual;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Implementation of an evolutionary algorithm.
 * 
 * @author bdu
 * 
 */
public class BasicEvolutionaryAlgorithm extends AbstractEvolutionaryAlgorithm {

    /**
     * Constructor for an evolutionary algorithm with the given initial
     * population. Creates and initializes the algorithm instance.
     * 
     * @param thePopulation
     *            initial population
     */
    public BasicEvolutionaryAlgorithm(final Population thePopulation) {
        this.population = new Population(thePopulation);
        this.offspring = null;
        this.selection = null;
        initialize();
    }
    
    /**
     * Returns a shallow copy of the population. (The elements themselves are
     * not copied.)
     * 
     * @return a shallow copy of the population
     */
    public Population getPopulation() {
        return new Population(population);
    }
    
    @Override
    protected void crossOver() {
        System.out.println("*** cross over");
        if (!selection.isEmpty()) {
            final int proposal = (int) (selection.size() * CROSS_OVER_RATIO);
            final int min = MIN_CROSS_OVERS;
            final int crossOvers = ((proposal < min) ? min : proposal);
            offspring = new Population();
            System.out.println(" -- generate " + crossOvers + " out of " + selection.size());
            for (int i = 0; i < crossOvers; i++) {
                Individual parent1 = selection.pick();
                Individual parent2 = selection.pick();
                Genome genome1 = parent1.getGenome();
                Genome genome2 = parent2.getGenome();
                // it is not ensured that both parents are different
                Genome newGenome = genome1.newRecombination(genome2);
                System.out.println(" -- cross over of " + parent1);
                System.out.println("              and " + parent2);
                offspring.add(new Individual(newGenome, getGeneration()));
            }
            // add offspring to survivors
            population.addAll(0, offspring);
        } else {
            System.out.println("Selection is EMPTY");
        }
    }
    
    @Override
    protected void determineFitness() {
        System.out.println("*** determineFitness");
        // fitness is determined by the rating value.
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("*** initialize");
        System.out.println("  population: " + population);
    }
    
    @Override
    public boolean isDone() {
        // no stop criterion -- algorithm shall run forever
        return false;
    }
    
    @Override
    protected void mutate() {
        System.out.println("*** mutate");
        final double prob = MUTATION_APPLICATION_PROBABILITY;
        System.out.println(" -- mutate all " + population.size() + ", each with probability of "
                + prob);
        for (Individual ind : population) {
            if (ind.mutate(prob)) {
                // individual has mutated --> slowly "forget" its rating
                ind.fadeRating();
                System.out.println("-- Mutation performed: " + ind);
            }
        }
    }
    
    @Override
    protected void select() {
        System.out.println("*** select");
        selection = new Population();
        final int count = population.size();
        Individual[] individuals = new Individual[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Individual.DESCENDING_RATING_COMPARATOR);
        // only some are allowed to generate offspring

        final int min = MIN_SELECT;
        final int proposal = (int) (individuals.length * SELECTION_RATIO);
        final int select = ((proposal > min) ? proposal : min);

        System.out.println(" -- select " + select + " of " + count);
        for (Individual ind : individuals) {
            if (selection.size() < select) {
                selection.add(ind);
                System.out.println(" -- select: " + ind);
            } else {
                break;
            }
        }
    }
    
    @Override
    protected void survive() {
        System.out.println("*** survive");
        final int count = population.size();
        final Individual[] individuals = new Individual[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Individual.DESCENDING_RATING_COMPARATOR);
        // only some survive
        final int min = MIN_SURVIVORS;
        final int proposal = (int) (count * SURVIVAL_RATIO);
        final int lim = ((proposal < min) ? min : proposal);
        final Population survivors = new Population();
        System.out.println(" -- keep " + lim + " of " + count);
        for (Individual ind : individuals) {
            if (survivors.size() < lim) {
                survivors.add(ind);
                System.out.println(" -- keep: " + ind.toString());
            } else {
                break;
            }
        }
        population = new Population(survivors);
    }
    
    // private fields
    private Population population;
    private Population selection;
    private Population offspring;
    private static final double SELECTION_RATIO = .9;
    private static final double CROSS_OVER_RATIO = .95;
    private static final double MUTATION_APPLICATION_PROBABILITY = .6;
    private static final double SURVIVAL_RATIO = .55;
    private static final int MIN_SELECT = 2;
    private static final int MIN_CROSS_OVERS = 1;
    private static final int MIN_SURVIVORS = 5;
}
