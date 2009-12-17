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

import java.io.Serializable;
import java.util.Comparator;

/**
 * A simple pair implementation.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @param <F> type of first contained object
 * @param <S> type of second contained object
 * @author msp
 */
public class Pair<F, S> {

    /** the first element. */
    private F first;
    /** the second element. */
    private S second;

    /**
     * Constructs a pair given both elements.
     * 
     * @param thefirst the first element
     * @param thesecond the second element
     */
    public Pair(final F thefirst, final S thesecond) {
        this.setFirst(thefirst);
        this.setSecond(thesecond);
    }

    /**
     * Comparator that uses the first element as base.
     */
    public static class FirstComparator<F extends Comparable<F>, S> implements Comparator<Pair<F, S>>,
            Serializable {
        private static final long serialVersionUID = 1;

        /**
         * {@inheritDoc}
         */
        public int compare(final Pair<F, S> o1, final Pair<F, S> o2) {
            return o1.getFirst().compareTo(o2.getFirst());
        }
    }

    /**
     * Comparator that uses the second element as base.
     */
    public static class SecondComparator<F, S extends Comparable<S>> implements Comparator<Pair<F, S>>,
            Serializable {
        private static final long serialVersionUID = 1;

        /**
         * {@inheritDoc}
         */
        public int compare(final Pair<F, S> o1, final Pair<F, S> o2) {
            return o1.getSecond().compareTo(o2.getSecond());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair<?, ?>) {
            Pair<?, ?> other = (Pair<?, ?>) obj;
            return this.getFirst() == null ? other.getFirst() == null
                    && (this.getSecond() == null ? other.getSecond() == null
                    : this.getSecond().equals(other.getSecond()))
                    : this.getFirst().equals(other.getFirst())
                    && (this.getSecond() == null ? other.getSecond() == null
                    : this.getSecond().equals(other.getSecond()));
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (getFirst() == null && getSecond() == null) {
            return 0;
        } else if (getFirst() == null) {
            return getSecond().hashCode();
        } else if (getSecond() == null) {
            return getFirst().hashCode();
        } else {
            return getFirst().hashCode() + getSecond().hashCode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (getFirst() == null && getSecond() == null) {
            return "pair(null,null)";
        } else if (getFirst() == null) {
            return "pair(null," + getSecond().toString() + ")";
        } else if (getSecond() == null) {
            return "pair(" + getFirst().toString() + ",null)";
        } else {
            return "pair(" + getFirst().toString() + "," + getSecond().toString() + ")";
        }
    }

    /**
     * Sets the first element.
     *
     * @param thefirst the first element to set
     */
    public void setFirst(final F thefirst) {
        this.first = thefirst;
    }

    /**
     * Returns the first element.
     *
     * @return the first element
     */
    public F getFirst() {
        return first;
    }

    /**
     * Sets the second element.
     *
     * @param thesecond the second element to set
     */
    public void setSecond(final S thesecond) {
        this.second = thesecond;
    }

    /**
     * Returns the second element.
     *
     * @return the second element
     */
    public S getSecond() {
        return second;
    }

}
