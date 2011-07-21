package de.cau.cs.kieler.kiml.evol.alg;

import java.util.Arrays;

import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * @author bdu
 *
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
    private static final double SELECTION_RATIO = 0.70;

    /**
     * {@inheritDoc}
     */
    public void process(final Population population) {

        Population selection = new Population();
        int count = population.size();
        Genome[] individuals = new Genome[count];
        population.toArray(individuals);
        Arrays.sort(individuals, Genome.DESCENDING_RATING_COMPARATOR);

        // Only some are allowed to generate offspring
        // These are selected by truncation.
        int select =
                BasicEvolutionaryAlgorithm.boundMultiple(individuals.length, SELECTION_RATIO,
                        MIN_SELECT, MAX_SELECT);

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
}