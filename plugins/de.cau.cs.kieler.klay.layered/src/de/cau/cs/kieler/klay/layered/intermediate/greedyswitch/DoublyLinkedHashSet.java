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
            addNewYoungest(newEntry);
        }
    }

    private void addNewYoungest(final Entry newEntry) {
        newEntry.previous = youngest;
        if (newEntry.previous != null) {
            newEntry.previous.hasNext = true;
            newEntry.previous.next = newEntry;
            newEntry.hasPrevious = true;
        }
        youngest = newEntry;
    }

    public void remove(final T element) {
        final Entry removedEntry = map.remove(element);

        if (removedEntry == null) {
            youngest = null;
            return;
        }

        if (removedEntry.hasPrevious && removedEntry.hasNext) { // connect
            removedEntry.previous.next = removedEntry.next;
            removedEntry.next.previous = removedEntry.previous;
        } else if (removedEntry == youngest) { // is youngest
            youngest = removedEntry.previous;
            if (removedEntry.hasPrevious) {
                final Entry previousEntry = removedEntry.previous;
                previousEntry.next = null;
                previousEntry.hasNext = false;
            }
        } else if (!removedEntry.hasPrevious && removedEntry.hasNext) {
            final Entry nextEntry = removedEntry.next;
            nextEntry.previous = null;
            nextEntry.hasPrevious = false;
        }
    }

    public int getAmountOfEntriesAfter(final T element) {
        int amount = 0;
        Entry currentEntry = map.get(element);
        if (currentEntry == null) {
            return 0;
        }
        while (currentEntry.hasNext) {
            amount++;
            currentEntry = currentEntry.next;
        }
        return amount;
    }

    public void clear() {
        youngest = null;
        this.map.clear();
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
