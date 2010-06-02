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
package de.cau.cs.kieler.kiml.evol;

import java.util.Random;

import org.eclipse.core.runtime.Assert;

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
     * 
     * @param theId
     * @param theValue
     */
    AbstractGene(
            final Object theId,
            final T theValue,
            final TypeInfo<T> theTypeInfo,
            final MutationInfo theMutationInfo) {
        // arguments must not be null
        Assert.isLegal(theId != null && theValue != null && theTypeInfo != null
                && theMutationInfo != null);
        // is value within bounds?
        Assert.isLegal(theTypeInfo.isValueWithinBounds(theValue), "value out of bounds.");
        this.id = theId;
        this.value = theValue;
        this.typeInfo = theTypeInfo;
        this.mutationInfo = theMutationInfo;
    }
    
    /**
     * The id of this gene.
     */
    private final Object id;
    
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
     * 
     * @return the id
     */
    public Object getId() {
        return this.id;
    }
    
    /**
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
     * 
     * @return the mutation info
     */
    protected MutationInfo getMutationInfo() {
        Assert.isNotNull(this.mutationInfo);
        return this.mutationInfo;
    }

    /**
     * Return the random generator instance.
     * 
     * @return the random generator.
     */
    protected Random getRandomGenerator() {
        Assert.isNotNull(this.randomGenerator);
        return this.randomGenerator;
    }

    /**
     * 
     * @return the type info
     */
    protected TypeInfo<T> getTypeInfo() {
        Assert.isNotNull(this.typeInfo);
        return this.typeInfo;
    }
}
