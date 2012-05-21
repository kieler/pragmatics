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

import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.math.KielerMath;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;

/**
 * Generates offspring by recombining selected parent individuals. The genetic
 * material of two or more parent individuals is put together to produce one or
 * more offspring.
 *
 * @author bdu
 * @author msp
 */
public class CrossoverOperation implements IEvolutionaryOperation {

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
    /** the random number generator. */
    private Random random;

    /**
     * Creates a new crossover operation.
     */
    public CrossoverOperation() {
        IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();
        this.isParthenogenesisAllowed =
                store.getBoolean(EvolPlugin.PREF_IS_PARTHENOGENESIS_ALLOWED);
    }

    /**
     * {@inheritDoc}
     */
    public void setRandom(final Random therandom) {
        this.random = therandom;
    }

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {

        Population selection = selected(population);

        if (!selection.getGenomes().isEmpty()) {
            // TODO: ensure minimum population is preserved

            int crossOvers = KielerMath.limit(Math.round(selection.getSize() * CROSS_OVER_RATIO),
                    MIN_CROSS_OVERS, MAX_CROSS_OVERS);

            Population offspring = new Population();

            for (int i = 0; i < crossOvers; i++) {
                if ((selection.getSize() < 2) && !isParthenogenesisAllowed) {
                    // Selection too small -- No crossover possible.
                    break;
                }

                Genome parent1;
                Genome parent2;
                do {
                    parent1 = selection.pick(random);
                    parent2 = selection.pick(random);
                    // If parthenogenesis is allowed, it is not guaranteed that
                    // both parents are different.
                } while ((parent1 == parent2) && !isParthenogenesisAllowed);

                Genome newGenome = recombine(parent1, parent2);
                newGenome.setProperty(Genome.USER_RATING, null);

                offspring.getGenomes().add(new Genome(newGenome));
            }

            // deselect all
            for (Genome genome : selection) {
                genome.setProperty(Population.SELECTED, Boolean.FALSE);
            }

            // add offspring to current population (old survivors)
            population.getGenomes().addAll(0, offspring.getGenomes());
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
            selection.getGenomes().add(iter.next());
        }
        return selection;
    }

    /**
     * Create a recombination of multiple genomes.
     * 
     * @param genomes
     *            the genomes to recombine
     * @return a recombination of the given genomes
     */
    public Genome recombine(final Genome... genomes) {
        Genome result = new Genome();
        Genome oldGenome = genomes[0];
        if (genomes.length == 1) {
            result = new Genome(oldGenome);
            result.setProperty(Genome.USER_RATING, oldGenome.getProperty(Genome.USER_RATING));
        } else {
            int size = oldGenome.getGenes().size();
            // iterate genes
            for (int gn = 0; gn < size; gn++) {
                Gene<?>[] genes = new Gene[genomes.length];
                for (int gm = 0; gm < genomes.length; gm++) {
                    assert genomes[gm].getSize() == size;
                    genes[gm] = genomes[gm].getGenes().get(gn);
                }
                Gene<?> newGene = recombine(genes);
                result.getGenes().add(newGene);
            }
            // Use the average of the genomes' ratings as the new rating.
            double ratingSum = 0;
            for (final Genome genome : genomes) {
                Double rating = genome.getProperty(Genome.USER_RATING);
                if (rating != null) {
                    ratingSum += rating;
                }
            }
            double average = ratingSum / genomes.length;
            result.setProperty(Genome.USER_RATING, average);
        }
        return result;
    }

    /**
     * Provide a cross over of genes. For number typed genes an average value is computed.
     * For others one of the given genes is randomly picked with uniform probability.
     *
     * @param genes
     *            genes that are to be recombined
     * @return a new gene that is a cross over of the given genes
     */
    public Gene<?> recombine(final Gene<?>... genes) {
        final Gene<?> oldGene = genes[0];
        GeneType geneType = oldGene.getTypeInfo().getGeneType();
        switch (geneType) {
        case INTEGER:
        case FLOAT:
            // return average of the genes
            float sum = 0;
            for (Gene<?> gene : genes) {
                sum += gene.floatValue();
            }
            int count = genes.length;
            float average = sum / count;
            if (geneType == GeneType.INTEGER) {
                return Gene.create(oldGene.getId(), Math.round(average), oldGene.getTypeInfo());
            } else {
                return Gene.create(oldGene.getId(), average, oldGene.getTypeInfo());
            }
            
        default:
            int pos = random.nextInt(genes.length);
            return Gene.create(genes[pos]);
        }
    }


}
