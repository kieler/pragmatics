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

import org.eclipse.core.runtime.Assert;

/**
 * A type info describes the parameters of a type.
 *
 * @author bdu
 *
 * @param <T>
 */
public abstract class TypeInfo<T extends Comparable<? super T>> {
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
            final T theLowerBound,
            final T theUpperBound,
            final IValueFormatter theFormatter,
            final Class<?> theClass) {

        // arguments must not be null
        Assert.isLegal((theDefaultValue != null) && (theLowerBound != null)
                && (theUpperBound != null) && (theFormatter != null));

        Assert.isLegal(theLowerBound.compareTo(theUpperBound) <= 0, "lower bound > upper bound");

        Assert
                .isLegal(theLowerBound.compareTo(theDefaultValue) <= 0,
                        "default value < lower bound");

        Assert.isLegal(theDefaultValue.compareTo(theUpperBound) <= 0, "default value > upper bound");

        this.defaultValue = theDefaultValue;
        this.lowerBound = theLowerBound;
        this.upperBound = theUpperBound;
        this.valueFormatter = theFormatter;
        this.clazz = theClass;
    }

    // TODO: "degenericalize" this class and use clazz for casting the value
    /**
     * @return the default value
     */
    public T getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @return the lower bound
     */
    public T getLowerBound() {
        return this.lowerBound;
    }

    /**
     * @return the upper bound
     */
    public T getUpperBound() {
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
        final boolean result;
        result =
                (this.lowerBound.compareTo(theValue) <= 0)
                        && (theValue.compareTo(this.upperBound) <= 0);
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
    private final T defaultValue;
    private final T lowerBound;
    private final T upperBound;
    private final IValueFormatter valueFormatter;
    private final Class<?> clazz;
}
