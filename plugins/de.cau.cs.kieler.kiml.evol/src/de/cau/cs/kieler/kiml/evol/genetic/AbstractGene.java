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

import java.util.Random;

/**
 * A gene is an immutable value container that is able to provide similar
 * versions of itself via mutation and recombination. It carries information
 * about the value it contains and about the mutation parameters.
 *
 * @author bdu
 *
 * @param <T>
 */
public abstract class AbstractGene<T extends Comparable<? super T>> implements IGene<T> {
    /**
     * Creates a new {@link AbstractGene} instance.
     *
     * @param theId
     *            the ID
     * @param theValue
     *            the value
     * @param theTypeInfo
     *            the type info
     * @param theMutationInfo
     *            the mutation info
     */
    AbstractGene(
            final String theId,
            final T theValue,
            final TypeInfo<T> theTypeInfo,
            final MutationInfo theMutationInfo) {
        // arguments must not be null
        if ((theId == null) || (theValue == null) || (theTypeInfo == null)
                || (theMutationInfo == null)) {
            throw new IllegalArgumentException();
        }

        // is value within bounds?
        if (!theTypeInfo.isValueWithinBounds(theValue)) {
            // discuss: OutOfBoundsException extends IllegalArgumentException to
            // provide type info?
            throw new IllegalArgumentException("value out of bounds: " + theValue);
        }

        // TODO discuss: make constructor private + factory method instead?
        this.id = theId;
        this.value = theValue;
        this.typeInfo = theTypeInfo;
        this.mutationInfo = theMutationInfo;
    }

    /**
     * The id of this gene.
     */
    private final String id;

    /**
     * The value of this gene.
     */
    private final T value;

    /**
     * The type info for this gene.
     */
    private final TypeInfo<T> typeInfo;

    /**
     * The mutation info for this gene.
     */
    private final MutationInfo mutationInfo;

    /**
     * The random number generator.
     */
    private final Random randomGenerator = new Random();

    /**
     * Returns the ID that is assigned to the gene.
     *
     * @return the ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the value that is encoded in the gene.
     *
     * @return the value
     */
    public T getValue() {
        return this.value;
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
     */
    public IGene<T> recombineWith(final IGene<T>... otherGenes) {
        // TODO: discuss: rather use a static recombine() method?
        IGene<T> result;
        int pos = (int) (getRandomGenerator().nextDouble() * (otherGenes.length + 1));
        if (pos < otherGenes.length) {
            result = newInstance((AbstractGene<T>) otherGenes[pos]);
        } else {
            result = newInstance(this);
        }
        return result;
    }

    /**
     * Returns the mutation info that is associated to this gene.
     *
     * @return the mutation info
     */
    protected MutationInfo getMutationInfo() {
        assert this.mutationInfo != null;
        return this.mutationInfo;
    }

    /**
     * Return the random generator instance that is used by the gene.
     *
     * @return the random generator.
     */
    protected Random getRandomGenerator() {
        assert this.randomGenerator != null;
        return this.randomGenerator;
    }

    /**
     * Returns the type info that is attached to this gene.
     *
     * @return the type info
     */
    protected TypeInfo<T> getTypeInfo() {
        assert this.typeInfo != null;
        return this.typeInfo;
    }
}
