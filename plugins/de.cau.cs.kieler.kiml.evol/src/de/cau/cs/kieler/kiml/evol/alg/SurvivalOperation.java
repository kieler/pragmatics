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

import java.util.Arrays;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * @author bdu
 *
 */
public class SurvivalOperation implements IEvolutionaryOperation {
    
    /**
     * Default value for the survival ratio.
     */
    private static final double SURVIVAL_RATIO_DEFAULT = 0.54;

    /**
     * The survival ratio. This indicates the ratio of surviving individuals,
     * relative to the population size.
     */
    private static final IProperty<Double> SURVIVAL_RATIO = new Property<Double>(
            "evol.survivalRatio", SURVIVAL_RATIO_DEFAULT);

    /**
     * Default value for minimum number of survivors.
     */
    private static final int MIN_SURVIVORS_DEFAULT = 5;

    /** Minimum number of individuals that must survive. */
    private static final IProperty<Integer> MIN_SURVIVORS = new Property<Integer>(
            "evol.minSurvivors", MIN_SURVIVORS_DEFAULT);

    /**
     * Default value for maximum number of survivors.
     */
    private static final int MAX_SURVIVORS_DEFAULT = 1000;

    /** Maximum number of individuals that may survive. */
    private static final IProperty<Integer> MAX_SURVIVORS = new Property<Integer>(
            "evol.maxSurvivors", MAX_SURVIVORS_DEFAULT);

    private static final BoundMultipleCalculator BOUND_MULTIPLE_CALCULATOR =
            new BoundMultipleCalculator(SURVIVAL_RATIO_DEFAULT, MIN_SURVIVORS_DEFAULT,
                    MAX_SURVIVORS_DEFAULT);

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {
        int count = population.size();
        assert count > 0;
        Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // only some survive
        int keep = BOUND_MULTIPLE_CALCULATOR.scale(count);
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

        population.clear();
        population.addAll(0, survivors.getGenomes());

        System.out.println(" -- dying out: ");
        System.out.println(victims);

    }

}
