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

    /** The type info for this gene. */
    private final TypeInfo<T> typeInfo;
    /** The value of this gene. */
    private final T value;
    /** Whether the gene is active or not. */
    private final boolean active;
    
    /**
     * Creates a new gene. The gene is initially active.
     * 
     * @param value the value
     * @param typeInfo the type information
     * @param active whether the new gene shall be active
     * @param <S> type of contained values
     * @return a new gene
     */
    @SuppressWarnings("unchecked")
    public static <S extends Comparable<? super S>> Gene<S> create(final S value,
            final TypeInfo<?> typeInfo, final boolean active) {
        if (typeInfo == null) {
            throw new NullPointerException();
        }
        Gene<S> newGene = new Gene<S>(value, (TypeInfo<S>) typeInfo, active);
        return newGene;
    }
    
    /**
     * Creates a new gene from an existing one.
     * 
     * @param gene the gene from which properties shall be copied
     * @param active whether the new gene shall be active
     * @param <S> type of contained values
     * @return a new gene
     */
    public static <S extends Comparable<? super S>> Gene<S> create(final Gene<S> gene,
            final boolean active) {
        Gene<S> newGene = new Gene<S>(gene.value, gene.typeInfo, active);
        return newGene;
    }
    
    /**
     * Creates a new abstract gene instance.
     *
     * @param theValue the value
     * @param theTypeInfo the type information
     * @param theactive the active status
     */
    private Gene(final T theValue, final TypeInfo<T> theTypeInfo, final boolean theactive) {
        this.value = theValue;
        this.typeInfo = theTypeInfo;
        this.active = theactive;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Gene) {
            Gene<?> other = (Gene<?>) object;
            return this.typeInfo.getId().equals(other.typeInfo.getId())
                    && this.value == null ? other.value == null
                    : this.value.equals(other.value);
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (value != null) {
            return typeInfo.getId().hashCode() + value.hashCode();
        }
        return typeInfo.getId().hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (value != null) {
            return typeInfo.getId() + "=" + value.toString();
        }
        return typeInfo.getId() + "=null";
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
     * Returns the value of the gene transformed to an integer number.
     * 
     * @return the value as integer number
     */
    public int intValue() {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        } else if (value instanceof Float) {
            return Math.round((Float) value);
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
        if (value instanceof Integer) {
            List<?> list = (List<?>) typeInfo.getTypeParam();
            return list.get((Integer) value);
        }
        return null;
    }
    
    /**
     * Returns the value of the gene interpreted as enumeration constant. This is only
     * applicable to the gene type {@code ENUM}.
     * 
     * @return the value as enumeration constant
     */
    public Enum<?> enumValue() {
        if (value instanceof Integer) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) typeInfo.getTypeParam();
            return optionData.getEnumValue((Integer) value);
        }
        return null;
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
    
}
