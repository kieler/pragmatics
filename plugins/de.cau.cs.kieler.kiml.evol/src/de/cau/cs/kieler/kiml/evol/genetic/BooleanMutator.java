package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.Random;

/**
 * A gene factory that creates boolean mutations.
 *
 * @author bdu
 *
 */
class BooleanMutator implements IMutator {
    private static final double PROBABILITY_FOR_TRUE = 0.5;

    /**
     * Creates a {@link BooleanMutator} instance.
     */
    public BooleanMutator() {
        // Nothing to do here.
    }

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
}