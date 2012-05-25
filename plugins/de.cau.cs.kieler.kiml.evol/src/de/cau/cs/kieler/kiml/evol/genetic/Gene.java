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

import java.util.List;

import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * A gene is an immutable value container that is able to provide similar
 * versions of itself via mutation and recombination. It carries information
 * about the value it contains and about the mutation parameters.
 *
 * @param <T> type of data.
 * @author bdu
 * @author msp
 */
public final class Gene<T extends Comparable<? super T>> {

    /** The id of this gene. */
    private final String id;
    /** The value of this gene. */
    private final T value;
    /** The type info for this gene. */
    private final TypeInfo<T> typeInfo;
    /** Whether the gene is active or not. */
    private boolean active;
    
    /**
     * Creates a new gene. The gene is initially active.
     * 
     * @param id
     *            the identifier
     * @param value
     *            the value
     * @param typeInfo
     *            the type information
     * @param <S> type of contained values
     * @return a new gene
     */
    @SuppressWarnings("unchecked")
    public static <S extends Comparable<? super S>> Gene<S> create(final String id, final S value,
            final TypeInfo<?> typeInfo) {
        if (id == null || typeInfo == null) {
            throw new NullPointerException();
        }
        Gene<S> newGene = new Gene<S>(id, value, (TypeInfo<S>) typeInfo);
        newGene.active = true;
        return newGene;
    }
    
    /**
     * Creates a new gene from an existing one.
     * 
     * @param gene
     *            the gene from which properties shall be copied
     * @param <S> type of contained values
     * @return a new gene
     */
    public static <S extends Comparable<? super S>> Gene<S> create(final Gene<S> gene) {
        Gene<S> newGene = new Gene<S>(gene.id, gene.value, gene.typeInfo);
        newGene.active = gene.active;
        return newGene;
    }
    
    /**
     * Creates a new abstract gene instance.
     *
     * @param theId
     *            the identifier
     * @param theValue
     *            the value
     * @param theTypeInfo
     *            the type information
     */
    private Gene(final String theId, final T theValue, final TypeInfo<T> theTypeInfo) {
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
    public String getId() {
        return id;
    }

    /**
     * Returns the value that is encoded in the gene. If the gene is inactive the value may
     * be {@code null}.
     *
     * @return the value
     */
    public T getValue() {
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
     * Returns the value of the gene interpreted as list index. This is only applicable to
     * the gene types {@code LAYOUT_TYPE} and {@code LAYOUT_ALGO}.
     * 
     * @return the value as list item
     */
    public Object listValue() {
        List<?> list = (List<?>) typeInfo.getTypeParam();
        return list.get((Integer) value);
    }
    
    /**
     * Returns the value of the gene interpreted as enumeration constant. This is only
     * applicable to the gene type {@code ENUM}.
     * 
     * @return the value as enumeration constant
     */
    public Enum<?> enumValue() {
        LayoutOptionData<?> optionData = (LayoutOptionData<?>) typeInfo.getTypeParam();
        return optionData.getEnumValue((Integer) value);
    }

    /**
     * Returns the type info that is attached to this gene.
     *
     * @return the type info
     */
    public TypeInfo<T> getTypeInfo() {
        return typeInfo;
    }
    
    /**
     * Returns whether this gene is currently active. Inactive genes may have {@code null} values.
     * 
     * @return true if the gene is active
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Sets the active status.
     * 
     * @param theActive the new active status 
     */
    public void setActive(final boolean theActive) {
        this.active = theActive;
    }
    
}
