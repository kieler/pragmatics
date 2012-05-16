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

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Operation that determines individuals that survive.
 * 
 * @author bdu
 */
public class SurvivalOperation implements IEvolutionaryOperation {

    /**
     * The survival ratio. This indicates the ratio of surviving individuals,
     * relative to the population size.
     */
    private static final float SURVIVAL_RATIO = 0.54f;

    /** Minimum number of individuals that must survive. */
    private static final int MIN_SURVIVORS = 5;

    /** Maximum number of individuals that may survive. */
    private static final int MAX_SURVIVORS = 1000;

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
        int keep = KielerMath.limit(Math.round(count * SURVIVAL_RATIO), MIN_SURVIVORS, MAX_SURVIVORS);
        assert keep > 0;
        double minDist = individuals[0].size() * 0.2;
        Population survivors = new Population();
        Population victims = new Population(population);

        for (final Genome ind : individuals) {
            // prevent too similar genomes from surviving
            if (survivors.size() == 0) {
                survivors.add(ind);
                victims.remove(ind);
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
                }
            } else {
                break;
            }
        }

        population.clear();
        population.addAll(0, survivors.getGenomes());
    }

}
