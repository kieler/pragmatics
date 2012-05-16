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

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * Generates offspring by recombining selected parent individuals. The genetic
 * material of two or more parent individuals is put together to produce one or
 * more offspring.
 *
 * @author bdu
 */
public class CrossOverOperation implements IEvolutionaryOperation {

    /**
     * The cross over ratio. Indicates how many offspring individuals shall be
     * created per selected individual.
     */
    private static final float CROSS_OVER_RATIO = 1.2f;

    /** Minimum number of individuals to create by cross over. */
    private static final int MIN_CROSS_OVERS = 1;

    /** Maximum number of individuals to create by cross over. */
    private static final int MAX_CROSS_OVERS = 1000;

    /**
     * Indicates whether parthenogenesis (reproduction from only one parent) may
     * take place.
     */
    private final boolean isParthenogenesisAllowed;

    /**
     * Creates a new crossover operation.
     */
    public CrossOverOperation() {
        IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        this.isParthenogenesisAllowed =
                store.getBoolean(EvolPlugin.PREF_IS_PARTHENOGENESIS_ALLOWED);
    }

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {

        Population selection = selected(population);

        if (!selection.isEmpty()) {
            // TODO: ensure minimum population is preserved

            int crossOvers = KielerMath.limit(Math.round(selection.size() * CROSS_OVER_RATIO),
                    MIN_CROSS_OVERS, MAX_CROSS_OVERS);

            Population offspring = new Population();

            for (int i = 0; i < crossOvers; i++) {
                if ((selection.size() < 2) && !isParthenogenesisAllowed) {
                    // Selection too small -- No crossover possible.
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

                int generationNumber = 0;
                // FIXME: we need the current generation number
                offspring.add(new Genome(newGenome, generationNumber));
            }

            // deselect all
            for (Genome genome : selection) {
                genome.setProperty(Population.SELECTED, Boolean.FALSE);
            }

            // add offspring to current population (old survivors)
            population.addAll(0, offspring.getGenomes());
        }
    }

    /**
     * Filter the genomes that are marked as selected.
     *
     * @param population
     *            the population
     * @return selected genomes
     */
    private Population selected(final Population population) {
        Iterator<Genome> iter = population.filteredIterator(Population.SELECTED_FILTER);

        Population selection = new Population();
        while (iter.hasNext()) {
            selection.add(iter.next());
        }
        return selection;
    }


}
