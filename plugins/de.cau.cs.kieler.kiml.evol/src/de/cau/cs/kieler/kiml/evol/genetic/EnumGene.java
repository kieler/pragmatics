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
package de.cau.cs.kieler.kiml.evol.genetic;


/**
 *
 * @author bdu
 */
public final class EnumGene extends AbstractGene<Integer> {

    /**
     *
     * @param theId
     *            the id
     * @param theValue
     *            the value
     * @param theEnumClass
     *            the enumeration class; must not be null.
     * @param theMutationProbability
     *            mutation probability
     */
    public EnumGene(
            final String theId,
            final int theValue,
            final Class<? extends Enum<?>> theEnumClass,
            final double theMutationProbability) {
        super(theId, Integer.valueOf(theValue),
                new EnumTypeInfo(0, ENUM_FORMATTER, theEnumClass), new MutationInfo(
                        theMutationProbability, Distribution.UNIFORM));
        if (theEnumClass == null) {
            throw new IllegalArgumentException();
        }
    }

    // private fields
    /** The cashed hash value. */
    private Integer cachedHash;

    /**
     * A formatter for Enum valued genes.
     */
    private static final IValueFormatter ENUM_FORMATTER = new IValueFormatter() {

        /**
         * {@inheritDoc}
         */
        public String getString(final Object o) {
            if (o instanceof EnumGene) {
                final Class<? extends Enum<?>> enumClass =
                        ((EnumGene) o).getTypeInfo().getTypeClass();

                final Enum<?>[] constants = enumClass.getEnumConstants();
                if (constants == null) {
                    return "";
                }
                final int value = ((EnumGene) o).getValue().intValue();
                assert (value >= 0) && (value < constants.length);
                final String result = constants[value].toString();
                return result;
            } else if (o instanceof UniversalNumberGene) {
                final Class<?> enumClass = ((UniversalNumberGene) o).getTypeInfo().getTypeClass();
                @SuppressWarnings("unchecked")
                final Enum<?>[] constants =
                        ((Class<? extends Enum<?>>) enumClass).getEnumConstants();

                final Integer value = Integer.valueOf(((UniversalNumberGene) o).getIntValue());

                if (constants == null) {
                    return value.toString();
                }
                assert (value.intValue() >= 0) && (value.intValue() < constants.length);
                final String result = constants[value.intValue()].toString();

                return result;
            }
            return null;
        }
    };

    @Override
    public boolean equals(final Object theObj) {
        if (!(theObj instanceof EnumGene)) {
            return false;
        }

        EnumGene other = (EnumGene) theObj;

        return other.getEnumClass().equals(this.getEnumClass())
                && (other.getId().equals(this.getId())) && other.getValue().equals(
this.getValue());
    }

    @Override
    public EnumTypeInfo getTypeInfo() {
        return (EnumTypeInfo) super.getTypeInfo();
    }

    @Override
    public int hashCode() {
        if (this.cachedHash == null) {
            final int f1 = 17881;
            final int f2 = 41;
            this.cachedHash =
                    this.getEnumClass().hashCode() * f1 + this.getId().hashCode() * f2
                            + this.getValue().hashCode();
        }

        return this.cachedHash;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newInstance(final AbstractGene<Integer> template) {
        if (template instanceof EnumGene) {
            return new EnumGene(template.getId(), template.getValue().intValue(),
                    this.getEnumClass(), getMutationInfo().getProbability());
        }
        // incompatible template
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IGene<Integer> newMutation() {
        EnumGene result = this.newMutation(this, getMutationInfo());
        return result;
    }


    /**
     *
     * @return A string representation of this instance.
     */
    @Override
    public String toString() {
        assert getEnumClass() != null;
        Enum<?>[] constants = getEnumClass().getEnumConstants();
        if (constants == null) {
            return "";
        }
        int value = getValue().intValue();
        assert (value >= 0) && (value < constants.length);

        String result = constants[value].toString();
        return result;
    }

    /**
     *
     * @return the Enum class
     */
    private Class<? extends Enum<?>> getEnumClass() {
        return getTypeInfo().getTypeClass();
    }

    /**
     * Returns a new mutated version of the given template, using the given
     * mutation info.
     *
     * @param template
     *            the template
     * @param mutationInfo
     *            the mutation info
     * @return the new mutation
     * @deprecated
     */
    @Deprecated
    private EnumGene newMutation(final EnumGene template, final MutationInfo mutationInfo) {
        return EnumMutator.newMutation(template, mutationInfo);
    }

}
