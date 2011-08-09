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

import de.cau.cs.kieler.core.properties.IProperty;


/**
 * A type info describes the parameters of a type that is used in an
 * {@link IGene}.
 *
 * @author bdu
 *
 * @param <T>
 */
public abstract class TypeInfo<T extends Comparable<? super T>>
 implements IProperty<T> {
    /**
     * Constructor for a type info.
     *
     * @param theDefaultValue
     *            the default value
     * @param theLowerBound
     *            the lower bound
     * @param theUpperBound
     *            the upper bound
     * @param theFormatter
     *            a value formatter
     * @param theClass
     *            the class of the value
     */
    public TypeInfo(
            final T theDefaultValue,
            final Comparable<T> theLowerBound,
            final Comparable<T> theUpperBound,
            final IValueFormatter theFormatter,
            final Class<?> theClass) {

        // arguments must not be null
        if ((theDefaultValue == null) || (theLowerBound == null) || (theUpperBound == null)
                || (theFormatter == null)) {
            throw new IllegalArgumentException();
        }

        if (theLowerBound.compareTo(theDefaultValue) > 0) {
            throw new IllegalArgumentException("default value < lower bound");
        } else if (theUpperBound.compareTo(theDefaultValue) <= 0) {
            throw new IllegalArgumentException("default value > upper bound");
        }

        this.defaultValue = theDefaultValue;
        this.lowerBound = theLowerBound;
        this.upperBound = theUpperBound;
        this.valueFormatter = theFormatter;
        this.clazz = theClass;
    }

    /**
     * @return the default value
     */
    public T getDefault() {
        return this.defaultValue;
    }

    /**
     * @return the lower bound
     */
    public Comparable<T> getLowerBound() {
        return this.lowerBound;
    }

    /**
     * @return the upper bound
     */
    public Comparable<T> getUpperBound() {
        return this.upperBound;
    }

    /**
     * Return true iff the given value is within the valid range.
     *
     * @param theValue
     *            a values
     * @return true iff the given value is within the valid range
     */
    public boolean isValueWithinBounds(final T theValue) {
        boolean result =
                (this.lowerBound.compareTo(theValue) <= 0)
                        && (this.upperBound.compareTo(theValue) > 0);
        return result;
    }

    /**
     *
     * @return the class of the value.
     */
    public Class<?> getTypeClass() {
        return this.clazz;
    }

    /**
     *
     * @return the value formatter
     */
    public IValueFormatter getValueFormatter() {
        return this.valueFormatter;
    }

    // private fields
    /** The default value. */
    private final T defaultValue;
    /** The lower bound. */
    private final Comparable<T> lowerBound;
    /** The upper bound. */
    private final Comparable<T> upperBound;
    /** The default value formatter. */
    private final IValueFormatter valueFormatter;
    /** The class of the value. */
    private final Class<?> clazz;
}
