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
import java.util.LinkedList;

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.CrossOverOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalNumberGene;

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

    /**
     * @param genomes
     *            the genomes to recombine
     * @return a recombination of the given genomes.
     */
    private static Genome newRecombination(final Genome... genomes) {
        Genome result = new Genome();
        Genome oldGenome = genomes[0];
        if (genomes.length == 1) {
            result = new Genome(oldGenome);
            result.setProperty(USER_RATING, oldGenome.getUserRating());
        } else {
            int size = genomes[0].getGenes().size();
            // iterate genes
            for (int g = 0; g < size; g++) {
                LinkedList<IGene<?>> geneList = new LinkedList<IGene<?>>();
                int gm = 0;
                for (final Genome genome : genomes) {
                    if (gm++ > 0) {
                        assert genome.getSize() == size;
                        geneList.add(genome.getGenes().get(g));
                    }
                }
                @SuppressWarnings("rawtypes" /* otherGenes is a mixture */)
                final IGene[] otherGenes = geneList.toArray(new IGene[geneList.size()]);
                final IGene<?> oldGene = oldGenome.getGenes().get(g);
                @SuppressWarnings("unchecked")
                final IGene<?> newGene = oldGene.recombineWith(otherGenes);
                result.getGenes().add(newGene);
            }
            // Use the average of the genomes' ratings as the new rating.
            double average = averageRating(genomes);
            result.setProperty(USER_RATING, average);
        }
        return result;
    }

    /**
     * @param genomes
     *            genomes
     * @return the average rating of the given genomes
     */
    private static double averageRating(final Genome... genomes) {
        double ratingSum = 0;
        for (final Genome genome : genomes) {
            ratingSum += genome.hasUserRating() ? genome.getUserRating() : 0.0;
        }
        double average = ratingSum / genomes.length;
        return average;
    }

    /**
     * Provide a cross over of this gene by randomly picking one of the given
     * genes or this gene with uniform probability. Extending classes may
     * override this method in order to provide a more sophisticated cross over.
     *
     * @param otherGenes
     *            some genes that are to be recombined with this instance.
     * @return a new gene that is a cross over of this instance and the given
     *         genes.
     * @deprecated TODO move this to {@link CrossOverOperation}
     */
    @Deprecated
    public IGene<T> recombineWith(final IGene<T>... otherGenes) {
        // TODO: discuss: rather use a static recombine() method?
        IGene<T> result;
        int pos = (int) (getRandomGenerator().nextDouble() * (otherGenes.length + 1));
        if (pos < otherGenes.length) {
            result = newInstance((Gene<T>) otherGenes[pos]);
        } else {
            result = newInstance(this);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated separation of data and operations #1716
     */
    @Deprecated
    @Override
    public IGene<Float> recombineWith(final IGene<Float>... theOtherGenes) {

        Class<?> clazz = getTypeInfo().getTypeClass();
        Float average = null;
        if ((clazz == Float.class) || (clazz == Integer.class)) {
            // return average of genes and this gene
            float sum = 0.0f;
            for (final IGene<Float> gene : theOtherGenes) {
                sum += gene.getValue().floatValue();
            }
            sum += getValue().floatValue();
            final int count = theOtherGenes.length + 1;
            average = Float.valueOf(sum / count);
            assert getTypeInfo().isValueWithinBounds(average);
        }

        if (average != null) {
            Float value;
            if (clazz == Integer.class) {
                value =
                        Float.valueOf(Integer.valueOf(Math.round(average.floatValue()))
                                .floatValue());
            } else {
                value = average;
            }

            IGene<Float> result =
                    new UniversalNumberGene(getId(), value, getTypeInfo(), getMutationInfo());

            return result;
        }

        return super.recombineWith(theOtherGenes);
    }


}
