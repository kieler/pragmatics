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

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract holder class for properties.
 *
 * @author msp
 */
public abstract class PropertyHolder {

    /** map of property identifiers to their values. */
    private Map<Property<?>, Object> propertyMap = null;
    
    /**
     * Sets a property value.
     * 
     * @param property the property to set
     * @param value the new value
     */
    public void setProperty(final Property<?> property, final Object value) {
        if (propertyMap == null) {
            propertyMap = new HashMap<Property<?>, Object>();
        }
        propertyMap.put(property, value);
    }
    
    /**
     * Retrieves a property value.
     * 
     * @param <T> type of property
     * @param property the property to get
     * @return the current value, or the default value if the property is not set
     */
    public <T> T getProperty(final Property<T> property) {
        if (propertyMap != null) {
            @SuppressWarnings("unchecked")
            T value = (T) propertyMap.get(property);
            if (value != null) {
                return value;
            }
        }
        return property.getDefault();
    }
    
    /**
     * Copy all properties from another property holder to this one.
     * 
     * @param other another property holder
     */
    public void copyProperties(final PropertyHolder other) {
        if (other.propertyMap != null) {
            if (this.propertyMap == null) {
                propertyMap = new HashMap<Property<?>, Object>(other.propertyMap);
            } else {
                this.propertyMap.putAll(other.propertyMap);
            }
        }
    }
    
}
