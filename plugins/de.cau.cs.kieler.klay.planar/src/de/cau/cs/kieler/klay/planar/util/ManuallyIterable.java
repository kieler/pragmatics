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

import java.util.HashMap;
import java.util.Iterator;

/**
 * An iterable object, that allows iteration in over the contained elements in two directions, and
 * requires manual management of the order of iteration. Objects are added to the iterable structure
 * by manually setting their successors or predecessors in the iteration order. This way both cyclic
 * or non-cyclic structures can be generated. The structures can then be iterated in normal or
 * reversed direction.
 * 
 * @param <T>
 *            the type of the elements contained in the iterable object
 * @author ocl
 */
public class ManuallyIterable<T> implements Iterable<T> {

    /**
     * Defines direction in which the iterable structure may be traversed.
     * 
     * @author ocl
     */
    public enum Direction {
        /** Defines a forward direction. */
        FWD,

        /** Defines a backwards direction. */
        REV
    }

    /**
     * Interface for an iterator from a manually iterable structure. Provides methods to set the
     * currently iterated item and the iteration direction manually.
     * 
     * @param <T>
     *            the type of the elements in the iterator
     * @author ocl
     */
    public interface ManualIterator<T> extends Iterator<T> {

        /**
         * Get the current position of the iterator.
         * 
         * @return the current item in the iterator
         */
        T getCurrent();

        /**
         * Set the current position of the iterator.
         * 
         * @param item
         *            the new position
         */
        void setCurrent(T item);

        /**
         * Get the item that would be the item returned by the next call to {@code next()}.
         * 
         * @return the successor of the current item
         */
        T getNext();

        /**
         * Get the current traversal direction of the iterator.
         * 
         * @return the current direction
         */
        Direction getDirection();

        /**
         * Set the current traversal direction of the iterator.
         * 
         * @param dir
         *            the new direction
         */
        void setDirection(Direction dir);

    }

    // ======================== Attributes =========================================================

    /** The default load factor for hash maps. */
    private static final float LOADFACTOR = 0.75f;

    /** The successor of every element in the structure. */
    private HashMap<T, T> predecessors;

    /** The predecessor of every element in the structure. */
    private HashMap<T, T> successors;

    // ======================== Constructor ========================================================

    /**
     * Create a new manually iterable structure.
     */
    public ManuallyIterable() {
        this.predecessors = new HashMap<T, T>();
        this.successors = new HashMap<T, T>();
    }

    /**
     * Create a new manually iterable structure optimized for a maximal number of items. It is still
     * possible to add more items to the structure, this only sets the initial capacity for the
     * internal {@code HashMap}s.
     * 
     * @param items
     *            the maximal number of items
     */
    public ManuallyIterable(final int items) {
        int capacity = Math.round(items / LOADFACTOR);
        this.predecessors = new HashMap<T, T>(capacity);
        this.successors = new HashMap<T, T>(capacity);
    }

    // ======================== Getters and Setters ================================================

    /**
     * Get the item following the given item in a given direction.
     * 
     * @param item
     *            the item whose successor is wanted
     * @param dir
     *            the direction in which to search
     * @return the successor of {@code item} in direction {@code dir}
     */
    public T getSuccessor(final T item, final Direction dir) {
        switch (dir) {
        case FWD:
            return this.successors.get(item);
        case REV:
            return this.predecessors.get(item);
        default:
            return null;
        }
    }

    /**
     * Set the successor of an item in a given direction.
     * 
     * @param item
     *            the item whose successor is set
     * @param successor
     *            the new successor of {@code item} in direction {@code dir}
     * @param dir
     *            the direction in which the successor is set
     */
    public void setSuccessor(final T item, final T successor, final Direction dir) {
        switch (dir) {
        case FWD:
            this.successors.put(item, successor);
            return;
        case REV:
            this.predecessors.put(item, successor);
            return;
        default:
        }
    }

    /**
     * Iterate forward over the iterable structure. Note that it is not specified on which item the
     * iteration will start. The iteration will continue until it reaches either an item without
     * successor, or reaches the item where the iteration started (due to cyclic structure).
     * 
     * @return iterator over the structure
     */
    public Iterator<T> iterator() {
        return this.iterator(Direction.FWD);
    }

    /**
     * Iterate over the structure in a specific direction. Note that it is not specified on which
     * item the iteration will start. The iteration will continue until it reaches either an item
     * without successor, or reaches the item where the iteration started (due to cyclic structure).
     * 
     * @param dir
     *            the direction for iteration
     * @return iterator over the structure
     */
    public ManualIterator<T> iterator(final Direction dir) {
        Iterator<T> iter = this.successors.keySet().iterator();
        if (iter.hasNext()) {
            return this.iterator(iter.next(), dir);
        } else {
            return this.iterator(null, dir);
        }
    }

    /**
     * Iterate over the structure in a specific direction. The iteration will start on the given
     * start node, and then follow the manually constructed successor chain in the given direction,
     * until it reaches either an item without successor, or reaches the item where the iteration
     * started (due to cyclic structure).
     * 
     * @param item
     *            the start node for iteration
     * @param dir
     *            the direction for iteration
     * @return iterator over the structure
     */
    public ManualIterator<T> iterator(final T item, final Direction dir) {
        return new ManualIterator<T>() {

            // ---------------- Attributes ---------------------------------------------------------

            /** The start item of the iterator. */
            private T start = item;

            /** The current position of the iterator. */
            private T current = item;

            /** The current direction of the iterator. */
            private Direction direction = dir;

            // ---------------- Getters and Setters ------------------------------------------------

            /**
             * {@inheritDoc}
             */
            public T getCurrent() {
                return this.current;
            }

            /**
             * {@inheritDoc}
             */
            public void setCurrent(final T item) {
                this.current = item;
            }

            /**
             * {@inheritDoc}
             */
            public Direction getDirection() {
                return this.direction;
            }

            /**
             * {@inheritDoc}
             */
            public void setDirection(final Direction dir) {
                this.direction = dir;
            }

            // ---------------- Iteration ----------------------------------------------------------

            /**
             * {@inheritDoc}
             */
            public boolean hasNext() {
                return (this.current != this.start) && (getNext() != null);
            }

            /**
             * {@inheritDoc}
             */
            public T getNext() {
                switch (this.direction) {
                case FWD:
                    return successors.get(this.current);
                case REV:
                    return predecessors.get(this.current);
                default:
                    return null;
                }
            }

            /**
             * {@inheritDoc}
             */
            public T next() {
                this.current = this.getNext();
                return this.current;
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
