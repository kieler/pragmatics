package de.cau.cs.kieler.kiml.evol.genetic;

/**
 *
 * @author bdu
 *
 */
public class UniversalGene extends AbstractGene<Float> {
    UniversalGene(
            final Object theId,
            final Comparable<?> theValue,
            final TypeInfo<Float> theTypeInfo,
            final MutationInfo theMutationInfo) {
        super(theId, (Float) theValue, theTypeInfo, theMutationInfo);

    }

    /**
     * {@inheritDoc}
     */
    public IGene<Float> newInstance(final AbstractGene<Float> template) {
        final IGene<Float> result = null;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Float> newMutation() {
        final IGene<Float> result = null;
        return result;
    }

    /**
     *
     * @return
     */
    public Integer getIntValue() {
        return Math.round(getValue());
    }

    /**
     *
     * @return
     */
    public Boolean getBoolValue() {
        final double epsilon = 1e-5;
        final double diff = 1.0 - Math.abs(getValue());
        return ((Math.pow(diff, 2) < epsilon));
    }
}
