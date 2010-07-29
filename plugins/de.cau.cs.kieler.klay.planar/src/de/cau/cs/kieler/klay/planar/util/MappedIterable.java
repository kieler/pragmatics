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
package de.cau.cs.kieler.klay.planar.util;

import java.util.Iterator;

/**
 * An iterable object that wraps another iterable and performs a transformation on every element.
 * 
 * @param <T>
 *            the type of elements in the original iterable
 * @param <E>
 *            the type of elements in the resulting iterable
 * @author ocl
 */
public class MappedIterable<T, E> implements Iterable<E> {

    // ======================== Attributes =========================================================

    /** The wrapped iterable object. */
    private Iterable<T> iterable;

    /** The function to use on each element. */
    private IFunction<T, E> function;

    // ======================== Constructor ========================================================

    /**
     * Creates a wrapped iterable using a function. The function is performed on each element in the
     * iterable.
     * 
     * @param iter
     *            the iterable object that will be wrapped
     * @param func
     *            the function that is evaluated on each element
     */
    public MappedIterable(final Iterable<T> iter, final IFunction<T, E> func) {
        this.iterable = iter;
        this.function = func;
    }

    // ======================== Iterator ===========================================================

    /**
     * {@inheritDoc}
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /** The iterator of the wrapped iterable object. */
            private Iterator<T> iter = iterable.iterator();

            /**
             * {@inheritDoc}
             */
            public boolean hasNext() {
                return iter.hasNext();
            }

            /**
             * {@inheritDoc}
             */
            public E next() {
                return function.evaluate(iter.next());
            }

            /**
             * {@inheritDoc}
             */
            public void remove() {
                iter.remove();
            }
        };
    }

}
