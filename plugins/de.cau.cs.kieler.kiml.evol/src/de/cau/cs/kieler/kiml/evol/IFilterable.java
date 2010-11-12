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
package de.cau.cs.kieler.kiml.evol;

import java.util.Collection;

import de.cau.cs.kieler.core.util.FilteredIterator;
import de.cau.cs.kieler.core.util.ICondition;

/**
 * An interface for collections that can provide a filtered iterator of their
 * content.
 * 
 * @param <CollectionType>
 *            a collection type
 * @param <T>
 *            the type of elements in the collection
 * @author bdu
 * 
 */
public interface IFilterable<CollectionType extends Collection<T>, T> {
    /**
     * Returns an iterator that filters the collection by the given filter.
     *
     * @param filter
     *            an {@link ICondition}
     * @return an iterator that traverses the collection and returns only the
     *         items that pass the given filter.
     */
    FilteredIterator<T> filteredIterator(final ICondition<T> filter);
}
