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
 * Convenience integer type info. Use only for enums.
 *
 * @author bdu
 *
 */
public final class EnumTypeInfo extends TypeInfo<Integer> {

    /**
     * Creates a new {@link EnumTypeInfo} instance.
     *
     * @param theDefaultValue
     *            the default value
     * @param theFormatter
     *            the formatter
     * @param theEnumClass
     *            the enum class
     */
    public EnumTypeInfo(
            final Integer theDefaultValue,
            final IValueFormatter theFormatter,
            final Class<? extends Enum<?>> theEnumClass) {
        super(theDefaultValue, Integer.valueOf(0), Integer.valueOf(choicesCount(theEnumClass) - 1),
                theFormatter, theEnumClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends Enum<?>> getTypeClass() {
        final Class<? extends Enum<?>> typeClass =
                (Class<? extends Enum<?>>) super.getTypeClass();
        return typeClass;
    }

    /**
     * Determines the number of possible choices in the given enumeration.
     *
     * @param theEnumClass
     *            an enumeration class
     * @return number of choices in the given enumeration.
     */
    private static int choicesCount(final Class<? extends Enum<?>> theEnumClass) {
        if (theEnumClass == null) {
            throw new IllegalArgumentException();
        }

        if (theEnumClass.getEnumConstants() == null) {
            return 0;
        }
        return theEnumClass.getEnumConstants().length;
    }
    
    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.properties.IProperty#getId()
     */
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }
}
