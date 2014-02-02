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
package de.cau.cs.kieler.core.properties;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import de.cau.cs.kieler.core.math.KVector;

/**
 * A property that uses a string for identification.
 *
 * @kieler.design 2011-01-17 reviewed by haf, cmot, soh
 * @kieler.rating proposed yellow 2012-07-10 msp
 * @param <T> type of the property
 * @author msp
 */
public class Property<T> implements IProperty<T>, Comparable<IProperty<?>> {
    
    /** the default lower bound, which is smaller than everything else. */
    public static final Comparable<Object> NEGATIVE_INFINITY = new Comparable<Object>() {
        public int compareTo(final Object other) {
            // Ignore FindBugs warning
            return -1;
        }
    };
    /** the default upper bound, which is greater than everything else. */
    public static final Comparable<Object> POSITIVE_INFINITY = new Comparable<Object>() {
        public int compareTo(final Object other) {
            // Ignore FindBugs warning
            return 1;
        }
    };
    
    /** identifier of this property. */
    private String id;
    /** the default value of this property. */
    private T defaultValue;
    /** the lower bound of this property. */
    private Comparable<? super T> lowerBound = NEGATIVE_INFINITY;
    /** the upper bound of this property. */
    private Comparable<? super T> upperBound = POSITIVE_INFINITY;
    
    /**
     * Creates a property with given identifier and {@code null} as default value.
     * 
     * @param theid the identifier
     */
    public Property(final String theid) {
        this.id = theid;
    }
    
    /**
     * Creates a property with given identifier and default value.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     */
    public Property(final String theid, final T thedefaultValue) {
        this(theid);
        this.defaultValue = thedefaultValue;
    }
    
    /**
     * Creates a property with given identifier, default value, and lower bound.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     * @param thelowerBound the lower bound
     */
    public Property(final String theid, final T thedefaultValue,
            final Comparable<? super T> thelowerBound) {
        this(theid, thedefaultValue);
        if (thelowerBound != null) {
            this.lowerBound = thelowerBound;
        }
    }
    
    /**
     * Creates a property with given identifier, default value, and lower and upper bound.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     * @param thelowerBound the lower bound, or {@code null} if the default lower bound shall be taken
     * @param theupperBound the upper bound
     */
    public Property(final String theid, final T thedefaultValue,
            final Comparable<? super T> thelowerBound, final Comparable<? super T> theupperBound) {
        this(theid, thedefaultValue, thelowerBound);
        if (theupperBound != null) {
            this.upperBound = theupperBound;
        }
    }
    
    /**
     * Creates a property using another property as identifier, but changing
     * the default value.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     */
    public Property(final IProperty<T> other, final T thedefaultValue) {
        this(other.getId(), thedefaultValue, other.getLowerBound(),
                other.getUpperBound());
    }
    
    /**
     * Creates a property using another property as identifier, but changing the
     * default value and lower bound.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     * @param thelowerBound the new lower bound
     */
    public Property(final IProperty<T> other, final T thedefaultValue,
            final Comparable<? super T> thelowerBound) {
        this(other.getId(), thedefaultValue, thelowerBound, other.getUpperBound());
    }
    
    /**
     * Creates a property using another property as identifier, but changing the
     * default value, lower bound, and upper bound.
     * 
     * @param other another property
     * @param thedefaultValue the new default value
     * @param thelowerBound the new lower bound
     * @param theupperBound the new upper bound
     */
    public Property(final IProperty<T> other, final T thedefaultValue,
            final Comparable<? super T> thelowerBound, final Comparable<? super T> theupperBound) {
        this(other.getId(), thedefaultValue, thelowerBound, theupperBound);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof IProperty<?>) {
            return this.id.equals(((IProperty<?>) obj).getId());
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return id.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    public String toString() {
        return id;
    }
    
    /**
     * Returns the default value of this property.
     * 
     * @return the default value.
     */
    @SuppressWarnings("unchecked")
    public T getDefault() {
        // Clone the default value if it's a Cloneable. We need to use reflection for this to work
        // properly (classes implementing Cloneable are not required to make their clone() method
        // public, so we need to check if they have such a method and invoke it via reflection, which
        // results in ugly and unchecked type casting)
        if (defaultValue instanceof KVector) {
            return (T) ((KVector) defaultValue).clone();
        } else if (defaultValue instanceof EnumSet<?>) {
            return (T) ((EnumSet<?>) defaultValue).clone();
        } else if (defaultValue instanceof HashSet<?>) {
            return (T) ((HashSet<?>) defaultValue).clone();
        } else if (defaultValue instanceof TreeSet<?>) {
            // GWT's TreeSet does not support clone()
            // As in Java everything is a subtype of Object and during runtime
            // the type is not known anyway, this should be fine.
            TreeSet<Object> clone = new TreeSet<Object>();
            Iterator<Object> it = ((TreeSet<Object>) defaultValue).iterator();
            while (it.hasNext()) {
                clone.add(it.next());
            }
            return (T) clone;
        } else if (defaultValue instanceof ArrayList<?>) {
            return (T) ((ArrayList<?>) defaultValue).clone();
        } else if (defaultValue instanceof LinkedList<?>) {
            // GWT's LinkedList does not support clone()
            LinkedList<Object> clone = new LinkedList<Object>();
            Iterator<Object> it = ((LinkedList<Object>) defaultValue).iterator();
            while (it.hasNext()) {
                clone.add(it.next());
            }
            return (T) clone;
        } else {
            return defaultValue;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Comparable<? super T> getLowerBound() {
        return lowerBound;
    }

    /**
     * {@inheritDoc}
     */
    public Comparable<? super T> getUpperBound() {
        return upperBound;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final IProperty<?> other) {
        return id.compareTo((String) other.getId());
    }

}
