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
        this.population = new Population(thePopulation.getGenomes());
        this.offspring = null;
        this.selection = null;
        IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        this.isParthenogenesisAllowed =
                store.getBoolean(EvolPlugin.PREF_IS_PARTHENOGENESIS_ALLOWED);

        reset();
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
    public Population getPopulationCopy() {
        return new Population(this.population);
    }

    @Override
    protected void crossOver() {
        System.out.println("*** cross over");
        if (!selection.isEmpty()) {

            // pad minimum population
            int lacking =
                    (population.size() < MIN_SURVIVORS) ? MIN_SURVIVORS - population.size() : 0;

            int crossOvers =
                    boundMultiple(selection.size(), CROSS_OVER_RATIO, MIN_CROSS_OVERS + lacking,
                            MAX_CROSS_OVERS);

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

                Genome newGenome = parent1.newRecombination(parent2);
                newGenome.setUserRating(null);
                System.out.println(" -- cross over of " + parent1);
                System.out.println("              and " + parent2);
                offspring.add(new Genome(newGenome, getGenerationNumber()));
            }

            // add offspring to old survivors
            population.addAll(0, offspring);
        } else {
            System.out.println("Selection is EMPTY");
        }
    }

    @Override
    protected void determineFitness() {
        System.out.println("*** determineFitness");
        // fitness is determined by the rating value.
        System.out.println(this.population.getDetails());
    }

    @Override
    public void reset() {
        System.out.println("*** initialize");
        super.reset();
        // obtain more initial diversity by performing some mutations
        final int some = 20;
        for (int i = 0; i < some; i++) {
            mutate();
        }
        System.out.println("  population: " + population);
    }

    @Override
    public boolean isDone() {
        // no stop criterion here -- algorithm shall run forever
        return false;
    }

    @Override
    protected void mutate() {
        System.out.println("*** mutate");
        final double prob = MUTATION_APPLICATION_PROBABILITY;
        System.out.println(" -- mutate all " + population.size() + ", each with probability of "
                + prob);
        Population mutations = new Population();
        for (final Genome ind : population.getGenomes()) {
            Genome mutation = ind.newMutation(prob);
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
        int count = population.size();
        Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // Only some are allowed to generate offspring
        // These are selected by truncation.
        int select = boundMultiple(individuals.length, SELECTION_RATIO, MIN_SELECT, MAX_SELECT);

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
        int count = population.size();
        assert count > 0;
        Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // only some survive
        int keep = boundMultiple(count, SURVIVAL_RATIO, MIN_SURVIVORS, MAX_SURVIVORS);
        assert keep > 0;
        double minDist = individuals[0].size() * 0.2;
        Population survivors = new Population();
        Population victims = new Population(population);

        System.out.println(" -- keep " + keep + " of " + count);
        for (final Genome ind : individuals) {
            // prevent too similar genomes from surviving
            if (survivors.size() == 0) {
                survivors.add(ind);
                victims.remove(ind);
                System.out.println(" -- keep: " + ind.toString());
            } else if (survivors.size() < keep) {
                // compare individual to random samples from other survivors
                Genome sample1 = survivors.pick();
                Genome sample2 = survivors.pick();

                double dist1 = Genome.distance(ind, sample1);
                double dist2;
                if (sample1 == sample2) {
                    dist2 = dist1;
                } else {
                    dist2 = Genome.distance(ind, sample2);
                }

                if ((dist1 > minDist) && ((dist2 > minDist))) {
                    survivors.add(ind);
                    victims.remove(ind);
                    System.out.println(" -- keep: " + ind.toString());
                }
            } else {
                break;
            }
        }
        if (survivors.size() < keep) {
            System.out.println("Only " + survivors.size() + " survive");
        }
        population = new Population(survivors);
        System.out.println(" -- dying out: ");
        System.out.println(victims);
    }

    /**
     * Calculates a rounded multiple of the given number, and ensures that it is
     * within the given bounds.
     *
     * @param number
     *            the number
     * @param factor
     *            the factor
     * @param min
     *            lower bound
     * @param max
     *            upper bound. Must be greater or equal to lower bound.
     * @return rounded fraction of the number, if it is within the bounds.
     *         Otherwise, the nearest of the bounds is returned.
     */
    private int boundMultiple(final int number, final double factor, final int min, final int max) {
        assert min <= max;

        int proposal = (int) Math.round(number * factor);
        int result = (proposal < min) ? min : (proposal > max ? max : proposal);

        return result;
    }

    // private fields
    /**
     * The current population.
     *
     * @deprecated
     * */
    @Deprecated
    private Population population;

    /**
     * The selected individuals.
     *
     * @deprecated
     * */
    @Deprecated
    private Population selection;

    /**
     * The offspring individuals.
     *
     * @deprecated
     * */
    @Deprecated
    private Population offspring;

    /**
     * The selection ratio. Indicates the ratio of the population that shall be
     * selected for recombination.
     */
    private static final double SELECTION_RATIO = 0.70;

    /**
     * The cross over ratio. Indicates how many offspring individuals shall be
     * created per selected individual.
     */
    private static final double CROSS_OVER_RATIO = 1.2;

    /**
     * The mutation application probability. This is the probability for each
     * individual to be subject to mutation.
     */
    private static final double MUTATION_APPLICATION_PROBABILITY = 0.6;

    /**
     * The survival ratio. This indicates the ratio of surviving individuals,
     * relative to the population size.
     */
    private static final double SURVIVAL_RATIO = 0.54;

    // To obtain a constant population size, the following condition must hold:
    // ((POP_SIZE * SURV_R + (POP_SIZE * SEL_R * CRO_R)) * SURV_R) == POP_SIZE
    private double surv() {
        return (1 / (1 + (SELECTION_RATIO * CROSS_OVER_RATIO)));
    }

    /** Minimum number of individuals to select. */
    private static final int MIN_SELECT = 2;

    /** Minimum number of individuals to create by cross over. */
    private static final int MIN_CROSS_OVERS = 1;

    /** Minimum number of individuals that must survive. */
    private static final int MIN_SURVIVORS = 5;

    /** Maximum number of individuals to select. */
    private static final int MAX_SELECT = 1000;

    /** Maximum number of individuals the create by cross over. */
    private static final int MAX_CROSS_OVERS = 1000;

    /** Maximum number of individuals that may survive. */
    private static final int MAX_SURVIVORS = 1000;
}
