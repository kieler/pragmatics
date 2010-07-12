package de.cau.cs.kieler.kiml.evol.genetic;

public class UniversalGene extends AbstractGene<Float> {
    UniversalGene(
            final Object theId,
            final Comparable theValue,
            final TypeInfo theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, (Float) theValue, theTypeInfo, theMutationInfo);

    }

    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        final IGene<Float> result = null;
        return result;
    }

    public IGene<Float> newMutation() {
        final IGene<Float> result = null;
        return result;
    }

    /**
     *
     * @return
     */
    public int getIntValue() {
        return Math.round(getValue());
    }
}
