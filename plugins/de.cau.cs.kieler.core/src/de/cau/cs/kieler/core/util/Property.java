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
package de.cau.cs.kieler.core.util;

/**
 * A property that can be stored in a hash map.
 *
 * @param <T> type of the property
 * @author msp
 */
public class Property<T> {
    
    /** identifier of this property. */
    private String id;
    /** the default value of this property. */
    private T defaultValue;
    
    /**
     * Creates a property with given identifier and {@code null} as default value.
     * 
     * @param theid the identifier
     */
    public Property(final String theid) {
        this.id = theid;
        this.defaultValue = null;
    }
    
    /**
     * Creates a property with given identifier and default value.
     * 
     * @param theid the identifier
     * @param thedefaultValue the default value
     */
    public Property(final String theid, final T thedefaultValue) {
        this.id = theid;
        this.defaultValue = thedefaultValue;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof Property<?>) {
            return this.id.equals(((Property<?>) obj).id);
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
     * Returns the default value of this property.
     * 
     * @return the default value
     */
    public T getDefault() {
        return defaultValue;
    }

}
