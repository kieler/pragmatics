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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract holder class for properties that uses a hash map.
 *
 * @author msp
 */
public abstract class MapPropertyHolder implements IPropertyHolder {

    /** map of property identifiers to their values. */
    private Map<IProperty<?>, Object> propertyMap = null;
    
    /**
     * {@inheritDoc}
     */
    public void setProperty(final IProperty<?> property, final Object value) {
        if (propertyMap == null) {
            propertyMap = new HashMap<IProperty<?>, Object>();
        }
        if (value == null) {
            propertyMap.remove(property);
        } else {
            propertyMap.put(property, value);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public <T> T getProperty(final IProperty<T> property) {
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
     * {@inheritDoc}
     */
    public void copyProperties(final IPropertyHolder holder) {
        if (holder instanceof MapPropertyHolder) {
            MapPropertyHolder other = (MapPropertyHolder) holder;
            if (other.propertyMap != null) {
                if (this.propertyMap == null) {
                    propertyMap = new HashMap<IProperty<?>, Object>(other.propertyMap);
                } else {
                    this.propertyMap.putAll(other.propertyMap);
                }
            }
        } else {
            this.propertyMap.putAll(holder.getAllProperties());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        if (propertyMap == null) {
            return Collections.emptyMap();
        } else {
            return propertyMap;
        }
    }
    
}
