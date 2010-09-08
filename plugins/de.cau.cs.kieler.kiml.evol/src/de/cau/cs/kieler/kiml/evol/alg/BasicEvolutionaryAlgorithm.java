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

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.evol.EvolPlugin;
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
        final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        this.isParthenogenesisAllowed =
                store.getBoolean(EvolPlugin.PREF_IS_PARTHENOGENESIS_ALLOWED);

        initialize();
        System.out.println("Optimal surr:" + surv());
    }

    /**
     * Indicates whether parthenogenesis (reproduction from only one parent) may
     * take place.
     */
    private final boolean isParthenogenesisAllowed;

    /**
     * Returns a shallow copy of the population. (The elements themselves are
     * not copied.)
     *
     * @return a shallow copy of the population
     */
    public Population getPopulation() {
        return new Population(this.population);
    }

    @Override
    protected void crossOver() {
        System.out.println("*** cross over");
        if (!selection.isEmpty()) {
            final int proposal = (int) Math.round(selection.size() * CROSS_OVER_RATIO);
            final int min = MIN_CROSS_OVERS;
            final int max = MAX_CROSS_OVERS;
            final int crossOvers = ((proposal < min) ? min : (proposal > max) ? max : proposal);
            offspring = new Population();
            System.out.println(" -- generate " + crossOvers + " out of " + selection.size());

            for (int i = 0; i < crossOvers; i++) {

                if ((selection.size() < 2) && !isParthenogenesisAllowed) {
                    // Selection too small -- No crossover possible.
                    System.err.println("Selection too small.");
                    break;
                }

                Genome parent1;
                Genome parent2;
                do {
                    parent1 = selection.pick();
                    parent2 = selection.pick();
                    // If parthenogenesis is allowed, it is not guaranteed that
                    // both parents are different.
                } while ((parent1 == parent2) && !isParthenogenesisAllowed);

                final Genome newGenome = parent1.newRecombination(parent2);
                newGenome.setUserRating(0);
                System.out.println(" -- cross over of " + parent1);
                System.out.println("              and " + parent2);
                offspring.add(new Genome(newGenome, getGeneration()));
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
        // obtain more initial diversity by performing some mutations
        final int some = 20;
        for (int i = 0; i < some; i++) {
            mutate();
        }
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
        final Population mutations = new Population();
        for (final Genome ind : population) {
            final Genome mutation = ind.newMutation(prob);
            if (mutation != null) {
                // individual has mutated --> rating is outdated
                mutation.fadeUserRating();
                mutations.add(mutation);
                System.out.println("-- Mutation performed: " + ind);
            } else {
                mutations.add(ind);
            }
        }
        population = new Population(mutations);
    }

    @Override
    protected void select() {
        System.out.println("*** select");
        selection = new Population();
        final int count = population.size();
        final Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // only some are allowed to generate offspring
        final int min = MIN_SELECT;
        final int max = MAX_SELECT;
        final int proposal = (int) Math.round(individuals.length * SELECTION_RATIO);
        final int select = ((proposal < min) ? min : (proposal > max ? max : proposal));

        System.out.println(" -- select " + select + " of " + count);
        for (final Genome ind : individuals) {
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
        Assert.isTrue(count > 0);
        final Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // only some survive
        final int min = MIN_SURVIVORS;
        final int max = MAX_SURVIVORS;
        final int proposal = (int) Math.round((count * SURVIVAL_RATIO));
        final int keep = ((proposal < min) ? min : (proposal > max ? max : proposal));
        final double minDist = individuals[0].size() * .2;
        final Population survivors = new Population();
        final Population victims = new Population(population);
        System.out.println(" -- keep " + keep + " of " + count);
        for (final Genome ind : individuals) {
            if (survivors.size() < keep) {
                final Genome comp = survivors.pick();
                final Genome comp2 = survivors.pick();
                if ((comp == null)
                        || ((Genome.distance(ind, comp) > minDist) && ((comp == comp2) || (Genome
                                .distance(ind, comp2) > minDist)))) {
                    survivors.add(ind);
                    victims.remove(ind);
                    System.out.println(" -- keep: " + ind.toString());
                }
            } else {
                break;
            }
        }
        if (survivors.size() < keep) {
            System.out.println(survivors.size());
        }
        population = new Population(survivors);
        System.out.println(" -- dying out: ");
        System.out.println(victims);
    }

    // private fields
    private Population population;
    private Population selection;
    private Population offspring;
    private static final double SELECTION_RATIO = .70;
    private static final double CROSS_OVER_RATIO = 1.2;
    private static final double MUTATION_APPLICATION_PROBABILITY = .6;
    private static final double SURVIVAL_RATIO = .54;

    // To obtain a constant population size, the following condition must hold:
    // ((POP_SIZE * SURV_R + (POP_SIZE * SEL_R * CRO_R)) * SURV_R) == POP_SIZE
    private double surv() {
        return (1 / (1 + SELECTION_RATIO * CROSS_OVER_RATIO));
    }

    private static final int MIN_SELECT = 2;
    private static final int MIN_CROSS_OVERS = 1;
    private static final int MIN_SURVIVORS = 5;
    private static final int MAX_SELECT = 1000;
    private static final int MAX_CROSS_OVERS = 1000;
    private static final int MAX_SURVIVORS = 1000;
}
