/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of doubly linked list that permits concatenation of two lists.
 * 
 * @param <E> type of contained objects
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ConcatenableList<E> extends AbstractSequentialList<E> {

    /**
     * Entry class used to store data.
     */
    private static class Entry<E> {
        E element;
        Entry<E> next;
        Entry<E> previous;

        Entry(final E theelement, final Entry<E> thenext, final Entry<E> theprevious) {
            this.element = theelement;
            this.next = thenext;
            this.previous = theprevious;
        }
    }

    /**
     * Iterator implementation for this list type.
     */
    private class ConcatenableListIterator implements ListIterator<E> {
        private Entry<E> lastReturned = header;
        private Entry<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        /**
         * Creates a list iterator for the given index.
         * 
         * @param index starting index of the new iterator
         */
        ConcatenableListIterator(final int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            if (index < (size >> 1)) {
                next = header.next;
                for (nextIndex = 0; nextIndex < index; nextIndex++) {
                    next = next.next;
                }
            } else {
                next = header;
                for (nextIndex = size; nextIndex > index; nextIndex--) {
                    next = next.previous;
                }
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#hasNext()
         */
        public boolean hasNext() {
            return nextIndex != size;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#next()
         */
        public E next() {
            if (nextIndex == size) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#hasPrevious()
         */
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#previous()
         */
        public E previous() {
            if (nextIndex == 0) {
                throw new NoSuchElementException();
            }

            next = next.previous;
            lastReturned = next;
            nextIndex--;
            return lastReturned.element;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#nextIndex()
         */
        public int nextIndex() {
            return nextIndex;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#previousIndex()
         */
        public int previousIndex() {
            return nextIndex - 1;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#remove()
         */
        public void remove() {
            Entry<E> lastNext = lastReturned.next;
            try {
                ConcatenableList.this.remove(lastReturned);
            } catch (NoSuchElementException e) {
                throw new IllegalStateException();
            }
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = header;
            expectedModCount++;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#set(java.lang.Object)
         */
        public void set(final E e) {
            if (lastReturned == header) {
                throw new IllegalStateException();
            }
            lastReturned.element = e;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.ListIterator#add(java.lang.Object)
         */
        public void add(final E e) {
            lastReturned = header;
            ConcatenableList.this.addBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }
    }

    /** the header entry. */
    private Entry<E> header = new Entry<E>(null, null, null);
    /** the current size of this list. */
    private int size = 0;

    /**
     * Constructs an empty list.
     */
    public ConcatenableList() {
        header.previous = header;
        header.next = header;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractSequentialList#listIterator(int)
     */
    @Override
    public ListIterator<E> listIterator(final int index) {
        return new ConcatenableListIterator(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractCollection#size()
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.AbstractList#add(java.lang.Object)
     */
    @Override
    public boolean add(final E e) {
        addBefore(e, header);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator. The behavior of this operation is undefined if the
     * specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and
     * it's nonempty.)
     * 
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     */
    public boolean addAll(final Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * Inserts all of the elements in the specified collection into this list,
     * starting at the specified position. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (increases
     * their indices). The new elements will appear in the list in the order
     * that they are returned by the specified collection's iterator.
     * 
     * @param index index at which to insert the first element from the
     *            specified collection
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     */
    @SuppressWarnings("unchecked")
    public boolean addAll(final int index, final Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        modCount++;

        Entry<E> successor = (index == size ? header : entry(index));
        Entry<E> predecessor = successor.previous;
        for (int i = 0; i < numNew; i++) {
            Entry<E> e = new Entry<E>((E) a[i], successor, predecessor);
            predecessor.next = e;
            predecessor = e;
        }
        successor.previous = predecessor;

        size += numNew;
        return true;
    }

    /**
     * Adds a new element before the given entry.
     * 
     * @param e element to add
     * @param entry entry used as reference
     * @return the new entry
     */
    private Entry<E> addBefore(final E e, final Entry<E> entry) {
        Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        modCount++;
        return newEntry;
    }

    /**
     * Removes a given entry.
     * 
     * @param e entry to remove
     * @return the element contained in the removed entry
     */
    private E remove(final Entry<E> e) {
        if (e == header) {
            throw new NoSuchElementException();
        }

        E result = e.element;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.next = null;
        e.previous = null;
        e.element = null;
        size--;
        modCount++;
        return result;
    }

    /**
     * Returns the indexed entry.
     */
    private Entry<E> entry(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Entry<E> e = header;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++) {
                e = e.next;
            }
        } else {
            for (int i = size; i > index; i--) {
                e = e.previous;
            }
        }
        return e;
    }

    /**
     * Concatenates the receiver with the given list. The new elements are
     * appended at the end of this list. The given list is cleared to avoid any
     * inconsistencies.
     * 
     * @param other list to concatenate
     */
    public void concatenate(final ConcatenableList<E> other) {
        if (other.size != 0) {
            if (this.size == 0) {
                this.header = other.header;
                this.size = other.size;
            } else {
                // update head and tail links
                Entry<E> oldTail = this.header.previous;
                Entry<E> newTail = other.header.previous;
                oldTail.next = other.header;
                other.header.previous = oldTail;
                newTail.next = this.header;
                this.header.previous = newTail;
                // update size
                this.size += other.size;
                // empty the other list
                other.header = new Entry<E>(null, null, null);
                other.header.next = header;
                other.header.previous = header;
                other.size = 0;
            }
        }
    }

    /**
     * Returns the first element in this list.
     * 
     * @return the first element in this list
     */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.next.element;
    }

    /**
     * Returns the last element in this list.
     * 
     * @return the last element in this list
     */
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return header.previous.element;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     * 
     * @param e the element to add
     */
    public void addFirst(final E e) {
        addBefore(e, header.next);
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * <p>
     * This method is equivalent to {@link #add}.
     * 
     * @param e the element to add
     */
    public void addLast(final E e) {
        addBefore(e, header);
    }

    /**
     * Removes and returns the first element from this list.
     * 
     * @return the first element from this list
     */
    public E removeFirst() {
        return remove(header.next);
    }

    /**
     * Removes and returns the last element from this list.
     * 
     * @return the last element from this list
     */
    public E removeLast() {
        return remove(header.previous);
    }

}
