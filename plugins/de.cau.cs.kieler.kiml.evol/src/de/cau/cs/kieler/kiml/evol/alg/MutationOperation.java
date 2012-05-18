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

import java.util.Random;

import de.cau.cs.kieler.kiml.evol.genetic.Distribution;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.EnumTypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.ListItemGene;
import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo;
import de.cau.cs.kieler.kiml.evol.genetic.MutationOperation;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalNumberGene;

/**
 * Operation for mutation of individuals.
 * 
 * @author bdu
 */
public class MutationOperation implements IEvolutionaryOperation {

    /**
     * The mutation application probability. This is the probability for each
     * individual to be subject to mutation.
     */
    private static final double MUTATION_APPLICATION_PROBABILITY = 0.6;

    /**
     * {@inheritDoc}
     */
    public final void process(final Population population) {
        Population mutations = new Population();
        for (final Genome ind : population) {
            Genome mutation = ind.newMutation(MUTATION_APPLICATION_PROBABILITY);
            if (mutation != null) {
                // individual has mutated --> rating is outdated
                mutation.fadeUserRating();
                mutations.add(mutation);
            } else {
                mutations.add(ind);
            }
        }
        population.clear();

        population.addAll(0, mutations.getGenomes());
    }

    /**
     * Performs a mutation step with the given probability. If a mutation step
     * is performed, this does not necessarily mean that any values are changed.
     *
     * @param prob
     *            Probability for the application of mutation.
     *
     * @return {@code true} if the mutation step was performed, {@code false} if
     *         the step was skipped.
     * @deprecated
     */
    @Deprecated
    public Genome newMutation(final double prob) {
        if (Math.random() < prob) {
            return newMutation();
        }
        System.out.println("-- skipped mutation for " + toString());
        return null;
    }

    /**
     * Mutate the genes of the individual. Every gene is asked to provide a
     * mutated version of itself.
     *
     * @return mutated copy of this genome
     * @deprecated TODO move this into {@link MutationOperation}
     */
    @Deprecated
    private Genome newMutation() {
        Genome newGenome = new Genome(this.generationNumber);
        for (final IGene<?> gene : this.getGenes()) {
            // presuming gene != null
            IGene<?> newGene = gene.newMutation();
            assert newGene != null : "Invalid mutation of " + gene;
            newGenome.getGenes().add(newGene);
        }
        newGenome.setUserRating(this.userRating);

        return newGenome;
    }

    /**
     * LIST ITEM
     *
     * @deprecated
     */
    @Deprecated
    public IGene<Integer> newMutation() {
        ListItemGene result;

        Random random = this.getRandomGenerator();
        MutationInfo mutationInfo = this.getMutationInfo();
        double prob = mutationInfo.getProbability();

        // must be uniform distribution
        Distribution distr = mutationInfo.getDistr();
        assert distr == Distribution.UNIFORM;

        TypeInfo<Integer> typeInfo = this.getTypeInfo();
        Comparable<Integer> lowerBound = typeInfo.getLowerBound();
        Comparable<Integer> upperBound = typeInfo.getUpperBound();

        Integer value = this.getValue().intValue();
        int newInt = value.intValue();
        if (random.nextDouble() < prob) {
            // FIXME need values of bounds
            // newInt = random.nextInt((upperBound - lowerBound) + 1) +
            // lowerBound;
        }

        result =
                new ListItemGene(this.getId(), newInt, this.getTypeInfo(), this.getMutationInfo());

        return result;
    }
    
    // BOOLEAN
    public IGene<Float> newMutation(
            final UniversalNumberGene template, final MutationInfo mutationInfo) {

        if ((template == null) || (mutationInfo == null)) {
            throw new IllegalArgumentException();
        }

        TypeInfo<Float> typeInfo = template.getTypeInfo();
        assert typeInfo != null;

        Random random = template.getRandomGenerator();
        assert random != null;

        // get mutation parameters
        double prob = mutationInfo.getProbability();

        Distribution distr = mutationInfo.getDistr();
        assert distr == Distribution.UNIFORM;

        Float newValue;
        if (random.nextDouble() < prob) {
            // assign a random boolean value (may be the same as before)
            newValue =
                    Float.valueOf(random.nextDouble() < PROBABILITY_FOR_TRUE ? 1.0f : 0.0f);

        } else {
            newValue = template.getValue();
        }
        return new UniversalNumberGene(template.getId(), newValue, typeInfo, mutationInfo);
    }
    
    // ENUM
    public static EnumGene newMutation(
            final EnumGene template, final MutationInfo mutationInfo) {
        if ((template == null) || (mutationInfo == null)) {
            throw new IllegalArgumentException();
        }

        EnumTypeInfo typeInfo = template.getTypeInfo();
        assert typeInfo != null;

        Random random = template.getRandomGenerator();
        assert random != null;

        double prob = mutationInfo.getProbability();

        Distribution distr = mutationInfo.getDistr();
        assert distr == Distribution.UNIFORM;

        Class<? extends Enum<?>> enumClass = typeInfo.getTypeClass();
        Comparable<Integer> lowerBound = typeInfo.getLowerBound();
        Comparable<Integer> upperBound = typeInfo.getUpperBound();
        Integer value = template.getValue();

        int newInt = value.intValue();
        if (random.nextDouble() < prob) {
            // Uniform distribution
            // FIXME need values of bounds
            // int lim = upperBound - lowerBound + 1;
            // newInt = random.nextInt(lim) + lowerBound;
        }
        return new EnumGene(template.getId(), newInt, enumClass, prob);
    }
    
    // FLOAT
    public IGene<Float> newMutation(
            final UniversalNumberGene template, final MutationInfo mutationInfo) {

        if ((template == null) || (mutationInfo == null)) {
            throw new IllegalArgumentException();
        }

        Random random = template.getRandomGenerator();
        assert random != null;

        TypeInfo<Float> typeInfo = template.getTypeInfo();
        assert typeInfo != null;

        double prob = mutationInfo.getProbability();
        double var = mutationInfo.getVariance();
        Distribution distr = mutationInfo.getDistr();
        assert distr == Distribution.GAUSSIAN;

        Float value = template.getValue();
        Float newValue = value;
        if (Math.random() < prob) {
            // produce a new value within the valid bounds.
            do {
                double gauss = random.nextGaussian() * Math.sqrt(var);
                newValue = Float.valueOf((float) (value.doubleValue() + gauss));
            } while (!typeInfo.isValueWithinBounds(newValue));
        }
        return new UniversalNumberGene(template.getId(), newValue, typeInfo, mutationInfo);
    }
    
    // INTEGER
    public IGene<Float> newMutation(
            final UniversalNumberGene template, final MutationInfo mutationInfo) {

        if ((template == null) || (mutationInfo == null)) {
            throw new IllegalArgumentException();
        }

        TypeInfo<Float> typeInfo = template.getTypeInfo();
        assert typeInfo != null;

        Random random = template.getRandomGenerator();
        assert random != null;

        double prob = mutationInfo.getProbability();
        double var = mutationInfo.getVariance();
        Distribution distr = mutationInfo.getDistr();
        Comparable<Float> lowerBound = typeInfo.getLowerBound();
        Comparable<Float> upperBound = typeInfo.getUpperBound();

        Integer value = Integer.valueOf(template.getValue().intValue());
        Integer newInt = value;
        if (random.nextDouble() < prob) {
            switch (distr) {
            case GAUSSIAN:
                // produce a new value within the valid bounds.
                do {
                    double gauss = random.nextGaussian() * Math.sqrt(var);
                    double newValue = value.doubleValue() + gauss;
                    newInt = Integer.valueOf((int) Math.round(newValue));
                } while (!typeInfo.isValueWithinBounds(Float.valueOf(newInt.floatValue())));
                break;
            case UNIFORM:
                do {
                    // produce a new value within the valid bounds.
                    // FIXME: need to get the values of the bounds to do
                    // <code>newInt = random.nextInt(upperBound - lowerBound +
                    // 1) +
                    // lowerBound;</code>
                    newInt = random.nextInt();
                } while (!typeInfo.isValueWithinBounds(Float.valueOf(newInt.floatValue())));
                break;
            default:
                // execution should never reach this line.
                throw new AssertionError("Unknown distribution in switch: " + distr);
            }
        }
        return new UniversalNumberGene(template.getId(), newInt.floatValue(),
                typeInfo, mutationInfo);
    }

}
