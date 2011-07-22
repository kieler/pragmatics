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
        Population population = getPopulation();

        population.clear();
        population.addAll(0, thePopulation.getGenomes());

        setSelectionOperation(new SelectionOperation());
        setCrossOverOperation(new CrossOverOperation());
        setMutationOperation(new MutationOperation());
        setSurvivalOperation(new SurvivalOperation());

        reset();
        // System.out.println("Optimal surr:" + surv());
    }

    /**
     * Returns a shallow copy of the population. (The elements themselves are
     * not copied.)
     *
     * @return a shallow copy of the population
     * @deprecated
     */
    @Deprecated
    public final Population getPopulationCopy() {
        return new Population(this.getPopulation());
    }

    @Override
    protected final void determineFitness() {
        System.out.println("*** determineFitness");
        // fitness is determined by the rating value.
        System.out.println(this.getPopulation().getDetails());
    }

    @Override
    public final void reset() {
        System.out.println("*** initialize");
        super.reset();
        // obtain more initial diversity by performing some mutations
        final int some = 20;
        for (int i = 0; i < some; i++) {
            mutate();
        }
        System.out.println("  population: " + getPopulation());
    }

    @Override
    public final boolean isDone() {
        // no stop criterion here -- algorithm shall run forever
        return false;
    }


    // To obtain a constant population size, the following condition must hold:
    // ((POP_SIZE * SURV_R + (POP_SIZE * SEL_R * CRO_R)) * SURV_R) == POP_SIZE
    // private double surv() {
    // return (1 / (1 + (SELECTION_RATIO * CROSS_OVER_RATIO)));
    // }

}
