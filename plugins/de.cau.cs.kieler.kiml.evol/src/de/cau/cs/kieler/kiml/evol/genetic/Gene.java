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
 * A gene is an immutable value container that is able to provide similar
 * versions of itself via mutation and recombination. It carries information
 * about the value it contains and about the mutation parameters.
 *
 * @param <T> type of data.
 * @author bdu
 * @author msp
 */
public class Gene<T extends Comparable<? super T>> {

    /** The id of this gene. */
    private final String id;
    /** The value of this gene. */
    private final T value;
    /** The type info for this gene. */
    private final TypeInfo<T> typeInfo;
    
    /**
     * Creates a new abstract gene instance.
     *
     * @param theId
     *            the ID
     * @param theValue
     *            the value
     * @param theTypeInfo
     *            the type info
     */
    Gene(final String theId, final T theValue, final TypeInfo<T> theTypeInfo) {
        if (theId == null || theValue == null || theTypeInfo == null) {
            throw new NullPointerException();
        }

        // is the value within bounds?
        if (!theTypeInfo.isWithinBounds(theValue)) {
            throw new IllegalArgumentException("Value out of bounds: " + theValue);
        }

        this.id = theId;
        this.value = theValue;
        this.typeInfo = theTypeInfo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof Gene) {
            Gene<?> other = (Gene<?>) object;
            return this.id.equals(other.id) && this.value.equals(other.value);
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return id.hashCode() + value.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return id + "=" + value.toString();
    }

    /**
     * Return the identifier of the gene.
     *
     * @return the identifier
     */
    public final String getId() {
        return id;
    }

    /**
     * Returns the value that is encoded in the gene.
     *
     * @return the value
     */
    public final T getValue() {
        return value;
    }
    
    /**
     * Returns the value of the gene transformed to a floating point number.
     * 
     * @return the value as floating point number
     */
    public float floatValue() {
        if (value instanceof Float) {
            return (Float) value;
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        }
        return 0;
    }

    /**
     * Returns the type info that is attached to this gene.
     *
     * @return the type info
     */
    public final TypeInfo<T> getTypeInfo() {
        return typeInfo;
    }
    
}
