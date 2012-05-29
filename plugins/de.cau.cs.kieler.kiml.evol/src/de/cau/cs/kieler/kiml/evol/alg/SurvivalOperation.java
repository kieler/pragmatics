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

import java.util.Iterator;
import java.util.Random;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Operation that determines individuals that survive.
 * 
 * @author bdu
 * @author msp
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
    /** factor for minimal distance between surviving individuals. */
    private static final double MIN_DIST_FACTOR = 0.1;

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
        if (population.size() <= MIN_SURVIVORS) {
            return;
        }
        
        // only some survive
        int surviveCount = KielerMath.limit(Math.round(population.size() * SURVIVAL_RATIO),
                MIN_SURVIVORS, MAX_SURVIVORS);

        Genome[] survivors = new Genome[surviveCount];
        Iterator<Genome> genomeIter = population.iterator();
        for (int i = 0; i < MIN_SURVIVORS; i++) {
            survivors[i] = genomeIter.next();
        }
        
        double minDist = survivors[0].getSize() * MIN_DIST_FACTOR;
        for (int i = MIN_SURVIVORS; i < surviveCount; i++) {
            Genome individual = null;
            int sampleCount = (int) Math.log(i) + 1;
            while (genomeIter.hasNext()) {
                Genome genome = genomeIter.next();
                // compare individual to random samples from other survivors
                boolean distinct = true;
                for (int j = 0; j < sampleCount; j++) {
                    Genome sample = survivors[random.nextInt(i)];
                    double distance = Genome.distance(genome, sample);
                    if (distance < minDist) {
                        distinct = false;
                        break;
                    }
                }
                if (distinct) {
                    individual = genome;
                    break;
                }
            }

            if (individual == null) {
                surviveCount = i;
                break;
            } else {
                survivors[i] = individual;
            }
        }

        population.clear();
        for (int i = 0; i < surviveCount; i++) {
            population.add(survivors[i]);
        }
    }

}
