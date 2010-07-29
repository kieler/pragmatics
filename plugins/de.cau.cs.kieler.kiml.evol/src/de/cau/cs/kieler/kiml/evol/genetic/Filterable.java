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

import java.util.List;

/**
 * An interface for lists that can filter their content.
 * 
 * @param <ListType>
 *            a list type
 * @param <T>
 *            the type of elements in the list
 * @author bdu
 * 
 */
public interface Filterable<ListType extends List<T>, T> {
    /**
     * Filters the list by the given filter.
     *
     * @param filter
     *            an {@link IItemFilter}
     * @return a new list that contains all the items of the list that pass the
     *         given filter.
     */
    ListType select(final IItemFilter<T> filter);
}
