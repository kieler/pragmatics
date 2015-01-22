/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO-alan.
 * 
 * @author alan
 * @param <T>
 *            Type in set.
 */
class DoublyLinkedHashSet<T> {
    @Override
    public String toString() {
        return "DoublyLinkedHashSet [map=" + map + "]";
    }

    private final Map<T, Entry> map;
    private Entry youngest;

    public DoublyLinkedHashSet() {
        this.map = new HashMap<T, Entry>();
    }

    public int size() {
        return map.size();
    }

    public boolean contains(final T element) {
        return map.containsKey(element);
    }

    public void add(final T element) {
        if (!map.containsKey(element)) {
            final Entry newEntry = new Entry();
            map.put(element, newEntry);
            setNewYoungest(newEntry);
        }
    }

    private void setNewYoungest(final Entry newEntry) {
        newEntry.previous = youngest;
        if (newEntry.previous != null) {
            newEntry.previous.hasNext = true;
            newEntry.previous.next = newEntry;
            newEntry.hasPrevious = true;
        }
        youngest = newEntry;
    }

    public void remove(final T element) {
        removeEntry(element);
    }

    public int removeAndGetAmountOfEntriesAfter(final T element) {
        Entry startEntry = removeEntry(element);

        if (startEntry == null) {
            return 0;
        }

        return countAllNextEntries(startEntry);
    }

    public void clear() {
        this.map.clear();
    }

    private int countAllNextEntries(final Entry startEntry) {
        int amount = 0;
        Entry currentEntry = startEntry;
        while (currentEntry.hasNext) {
            amount++;
            currentEntry = currentEntry.next;
        }
        return amount;
    }

    private Entry removeEntry(final T element) {
        final Entry removedEntry = map.remove(element);

        if (removedEntry == null) {
            return null;
        }

        if (hasPreviousAndNextEntry(removedEntry)) {
            final Entry previousEntry = removedEntry.previous;
            final Entry nextEntry = removedEntry.previous;
            connect(previousEntry, nextEntry);
        } else if (isYoungest(removedEntry)) {
            final Entry previousEntry = removedEntry.previous;
            resetYoungestTo(previousEntry);
        } else if (isEldest(removedEntry)) {
            final Entry nextEntry = removedEntry.next;
            resetEldest(nextEntry);
        }

        return removedEntry;
    }

    private boolean hasPreviousAndNextEntry(final Entry entry) {
        return entry.hasPrevious && entry.hasNext;
    }

    private void connect(final Entry previousEntry, final Entry nextEntry) {
        previousEntry.next = nextEntry;
    }

    private boolean isEldest(final Entry entry) {
        return !entry.hasPrevious && entry.hasNext;
    }

    private boolean isYoungest(final Entry removedEntry) {
        return removedEntry.hasPrevious && !removedEntry.hasNext;
    }

    private void resetYoungestTo(final Entry entry) {
        entry.next = null;
        entry.hasNext = false;
        youngest = entry;
    }

    private void resetEldest(final Entry entry) {
        entry.previous = null;
        entry.hasPrevious = false;
    }

    /**
     * Private inner class for linking entries. Currently only can be used to count. Could easily be
     * upgraded to also have Pointers to next and previous entries.
     * 
     * @author alan
     *
     */
    private static final class Entry {
        private boolean hasNext = false;
        private boolean hasPrevious = false;
        private Entry next = null;
        private Entry previous = null;

        @Override
        public String toString() {
            return "Entry [hasNext=" + hasNext + ", hasPrevious=" + hasPrevious + "]";
        }
    }
}
