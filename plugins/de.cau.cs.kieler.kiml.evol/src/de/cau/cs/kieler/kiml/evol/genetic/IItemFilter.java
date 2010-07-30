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
 * This interface can be used to implement a custom filter for lists that
 * implement {@link IFilterable}.
 *
 * @author bdu
 *
 * @param <T>
 */
public interface IItemFilter<T> {
    /**
     * Checks whether the given item satisfies the filter criteria. Implement
     * this to encapsulate the filter criteria.
     *
     * @param item
     *            an item to be checked
     * @return {@code true} if the given item satisfies the filter criteria,
     *         otherwise {@code false}.
     */
    boolean isMatch(T item);
}
