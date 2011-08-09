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
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.genetic;

/**
 * Convenience class for float type info.
 *
 * @author bdu
 *
 */
public final class FloatTypeInfo extends TypeInfo<Float> {

    /**
     * Creates a new {@link FloatTypeInfo} instance.
     *
     * @param theDefaultValue
     *            default value; must be within the specified bounds
     * @param theLowerBound
     *            lower bound
     * @param theUpperBound
     *            upper bound
     * @param theFormatter
     *            a value formatter
     * @param theClass
     *            the class of the values
     */
    public FloatTypeInfo(
            final Float theDefaultValue,
            final Float theLowerBound,
            final Float theUpperBound,
            final IValueFormatter theFormatter,
            final Class<?> theClass) {
        super(theDefaultValue, theLowerBound, theUpperBound, theFormatter, theClass);
    }
    
    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.properties.IProperty#getId()
     */
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
