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

import java.util.Collections;
import java.util.List;

/**
 * * Convenience integer type info. Use only for radio genes.
 *
 * @author bdu
 *
 */
public class RadioTypeInfo extends TypeInfo<Integer> {

    private final List<?> list;

    /**
     * Creates a new {@link RadioTypeInfo} instance.
     *
     * @param theDefaultValue
     *            the default value
     * @param theFormatter
     *            the formatter
     * @param theList
     *            the list
     */
    public RadioTypeInfo(
            final Integer theDefaultValue,
            final IValueFormatter theFormatter,
            final List<?> theList) {
        super(theDefaultValue, 0, theList.size() - 1, theFormatter, theList.getClass());
        this.list = theList;
    }

    /**
     * Creates a new {@link RadioTypeInfo} instance.
     * 
     * @param theDefaultValue
     *            the default value
     * @param theList
     *            the list
     */
    public RadioTypeInfo(final Integer theDefaultValue, final List<?> theList) {
        this(theDefaultValue, RadioGene.RADIO_GENE_FORMATTER, theList);
    }

    @Override
    public Class<List<?>> getTypeClass() {
        return (Class<List<?>>) super.getTypeClass();
    }

    /**
     * @return the list
     */
    public List<?> getList() {
        return Collections.unmodifiableList(this.list);
    }

}
