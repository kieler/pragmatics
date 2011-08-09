package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.Random;

/**
 * A gene factory that creates Enum mutations.
 *
 * @author bdu
 *
 */
final class EnumMutator {

    /**
     * Creates a new {@link EnumMutator} instance.
     *
     */
    private EnumMutator() {
        // Nothing to do here.
    }

    /**
     * Creates a new mutation of the given template.
     *
     * @param template
     *            the template
     * @param mutationInfo
     *            the mutation info
     * @return the new mutation
     */
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
}