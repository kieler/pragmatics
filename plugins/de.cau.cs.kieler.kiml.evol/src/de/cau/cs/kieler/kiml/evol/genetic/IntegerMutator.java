package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.Random;

/**
 * A gene factory that creates integer mutations.
 *
 * @author bdu
 *
 */
class IntegerMutator implements IMutator {

    /**
     * Creates a new {@link IntegerMutator} instance.
     *
     */
    public IntegerMutator() {
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