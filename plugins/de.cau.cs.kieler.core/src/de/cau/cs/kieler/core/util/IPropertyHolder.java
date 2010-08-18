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

import java.util.List;

/**
 * Interface for holders of property values.
 *
 * @author msp
 */
public interface IPropertyHolder {
    
    /**
     * Sets a property value.
     * 
     * @param property the property to set
     * @param value the new value
     */
    void setProperty(Property<?> property, final Object value);
    
    /**
     * Retrieves a property value.
     * 
     * @param <T> type of property
     * @param property the property to get
     * @return the current value, or the default value if the property is not set
     */
    <T> T getProperty(Property<T> property);
    
    /**
     * Copy all properties from another property holder to this one.
     * 
     * @param other another property holder
     */
    void copyProperties(IPropertyHolder other);
    
    /**
     * Returns a list of all assigned properties with associated values.
     * 
     * @return a list of all properties
     */
    List<Pair<Property<?>, Object>> getAllProperties();

}
