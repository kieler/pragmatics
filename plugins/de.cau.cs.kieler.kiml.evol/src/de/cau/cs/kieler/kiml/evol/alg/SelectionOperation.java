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

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Operation to select parent individuals for recombination.
 *
 * @author bdu
 * 
 */
public final class SelectionOperation implements IEvolutionaryOperation {

    /** Default value for minimum number of individuals to select. */
    private static final int MIN_SELECT_DEFAULT = 2;

    /** Minimum number of individuals to select. */
    private static final IProperty<Integer> MIN_SELECT = new Property<Integer>("evol.minSelect",
            MIN_SELECT_DEFAULT);

    /** Default value for maximum number of individuals to select. */
    private static final int MAX_SELECT_DEFAULT = 1000;

    /** Maximum number of individuals to select. */
    private static final IProperty<Integer> MAX_SELECT = new Property<Integer>("evol.maxSelect",
            MAX_SELECT_DEFAULT);

    /**
     * Default value for the selection ratio.
     */
    private static final double SELECTION_RATIO_DEFAULT = 0.70;

    /**
     * The selection ratio. Indicates the ratio of the population that shall be
     * selected for recombination.
     */
    private static final IProperty<Double> SELECTION_RATIO = new Property<Double>(
            "evol.selectionRatio", SELECTION_RATIO_DEFAULT);

    /**
     *
     */
    private static final BoundMultipleCalculator BOUND_MULTIPLE_CALCULATOR =
            new BoundMultipleCalculator(SELECTION_RATIO.getDefault(), MIN_SELECT.getDefault(),
                    MAX_SELECT.getDefault());

    /**
     * {@inheritDoc}
     */
    public void process(final Population population) {

        Population selection = new Population();
        int count = population.size();
        Genome[] candidates = new Genome[count];
        population.toArray(candidates);
        Arrays.sort(candidates, Genome.DESCENDING_RATING_COMPARATOR);

        // Only some are allowed to generate offspring
        // These are selected by truncation.

        int select = BOUND_MULTIPLE_CALCULATOR.scale(candidates.length);

        System.out.println(" -- select " + select + " of " + count);
        for (final Genome candidate : candidates) {
            if (selection.size() < select) {
                selection.add(candidate);
                candidate.setProperty(Population.SELECTED, true);
                System.out.println(" -- select: " + candidate);
            } else {
                break;
            }
        }
    }
}
