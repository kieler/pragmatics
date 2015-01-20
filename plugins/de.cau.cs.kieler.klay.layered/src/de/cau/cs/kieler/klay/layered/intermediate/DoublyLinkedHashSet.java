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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alan
 *
 */
public class DoublyLinkedHashSet<T> {
    @Override
    public String toString() {
        return "DoublyLinkedHashSet [map=" + map + "]";
    }

    private final Map<T, Entry> map;
    private Entry youngestEntry;

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
        final Entry newEntry = new Entry();
        final Entry entryForElement = map.put(element, newEntry);
        boolean entryWasNotAlreadyInSet = entryForElement == null;
        if (entryWasNotAlreadyInSet) {
            newEntry.previousEntry = youngestEntry;
            if (youngestEntry != null) {
                youngestEntry.hasNext = true;
                youngestEntry.nextEntry = newEntry;
            }
        }
        youngestEntry = newEntry;
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
            currentEntry = currentEntry.nextEntry;
        }
        return amount;
    }

    private Entry removeEntry(final T element) {
        final Entry removedEntry = map.remove(element);

        if (removedEntry == null) {
            return null;
        }

        if (hasPreviousAndNextEntry(removedEntry)) {
            final Entry previousEntry = removedEntry.previousEntry;
            final Entry nextEntry = removedEntry.previousEntry;
            connect(previousEntry, nextEntry);
        } else if (isEndOfList(removedEntry)) {
            final Entry previousEntry = removedEntry.previousEntry;
            resetEndTo(previousEntry);
        } else if (isHead(removedEntry)) {
            final Entry nextEntry = removedEntry.nextEntry;
            resetHeadTo(nextEntry);
        }

        return removedEntry;
    }

    private boolean hasPreviousAndNextEntry(final Entry entry) {
        return entry.hasPrevious && entry.hasNext;
    }

    private void connect(final Entry previousEntry, final Entry nextEntry) {
        previousEntry.nextEntry = nextEntry;
    }

    private boolean isHead(final Entry entry) {
        return !entry.hasPrevious && entry.hasNext;
    }

    private void resetHeadTo(final Entry entry) {
        entry.previousEntry = null;
        entry.hasPrevious = false;
    }

    private boolean isEndOfList(final Entry removedEntry) {
        return removedEntry.hasPrevious && !removedEntry.hasNext;
    }

    private void resetEndTo(final Entry entry) {
        entry.nextEntry = null;
        entry.hasNext = false;
        youngestEntry = entry;
    }

    private static final class Entry {
        private boolean hasNext;
        private boolean hasPrevious;
        private Entry nextEntry;
        private Entry previousEntry;

        @Override
        public String toString() {
            return "Entry [hasNext=" + hasNext + ", hasPrevious=" + hasPrevious + "]";
        }
    }
}
