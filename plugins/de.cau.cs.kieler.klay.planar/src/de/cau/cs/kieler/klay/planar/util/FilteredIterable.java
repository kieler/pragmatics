/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import com.google.common.base.Predicate;

/**
 * An iterable object that wraps another iterable. It uses a condition to filter certain elements
 * from the original iterable. 
 * 
 * @param <T>
 *            the type of the elements
 * @author ocl
 * @kieler.rating proposed yellow by pkl
 */
public class FilteredIterable<T> implements Iterable<T> {

    // ======================== Attributes =========================================================

    /** The wrapped iterable object. */
    private Iterable<T> iterable;

    /** The filter to use on each element. */
    private Predicate<T> filter;

    // ======================== Constructor ========================================================

    /**
     * Creates a wrapped iterable using a filter. Every element in the iterable not matching the
     * filter will not occur in the resulting iterable.
     * 
     * @param iter
     *            the iterable object that will be wrapped
     * @param cond
     *            the condition used to filter elements
     */
    public FilteredIterable(final Iterable<T> iter, final Predicate<T> cond) {
        this.iterable = iter;
        this.filter = cond;
    }

    // ======================== Iterator ===========================================================

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /** The iterator of the wrapped iterable object. */
            private Iterator<T> iter = iterable.iterator();

            /** The next element, if there is such. */
            private T next;

            /**
             * {@inheritDoc}
             */
            public boolean hasNext() {
                if (this.next == null) {
                    T elem = null;
                    while (this.iter.hasNext()) {
                        elem = this.iter.next();
                        if (filter.apply(elem)) {
                            break;
                        } else {
                            elem = null;
                        }
                    }
                    this.next = elem;
                }
                return this.next != null;
            }

            /**
             * {@inheritDoc}
             */
            public T next() {
                if (this.hasNext()) {
                    T newNext = this.next;
                    this.next = null;
                    return newNext;
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * {@inheritDoc}
             */
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
