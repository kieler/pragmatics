/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Arrays;
import java.util.Random;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Operation to select parent individuals for recombination.
 *
 * @author bdu
 * @author msp
 */
public final class SelectionOperation implements IEvolutionaryOperation {

    /** Minimum number of individuals to select. */
    private static final int MIN_SELECT = 2;

    /** Maximum number of individuals to select. */
    private static final int MAX_SELECT = 1000;

    /**
     * The selection ratio. Indicates the ratio of the population that shall be
     * selected for recombination.
     */
    private static final float SELECTION_RATIO = 0.70f;

    /**
     * {@inheritDoc}
     */
    public void process(final Population population) {
        int count = population.getSize();
        Genome[] candidates = new Genome[count];
        population.getGenomes().toArray(candidates);
        Arrays.sort(candidates, Genome.DESCENDING_RATING_COMPARATOR);

        // Only some are allowed to generate offspring - these are selected by truncation

        int select = KielerMath.limit(Math.round(candidates.length * SELECTION_RATIO),
                MIN_SELECT, MAX_SELECT);

        int selected = 0;
        for (final Genome candidate : candidates) {
            if (selected < select) {
                candidate.setProperty(Population.SELECTED, true);
                selected++;
            } else {
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setRandom(final Random random) {
        // no random number generator required in this operation
    }
    
}
