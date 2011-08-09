package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.Random;

/**
 * A gene factory that creates float mutations.
 *
 * @author bdu
 *
 */
class FloatMutator implements IMutator {
    /**
     * Creates a {@link FloatMutator} instance.
     */
    public FloatMutator() {
        // Nothing to do here.
    }

    /**
     * {@inheritDoc}
     */
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
}