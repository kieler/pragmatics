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
package de.cau.cs.kieler.klay.planar.util;

import de.cau.cs.kieler.core.util.Property;
import de.cau.cs.kieler.core.util.PropertyHolder;

/**
 * Interface for a property holder class. This interface can be extended by other interfaces to
 * force the implementing classes to extend the abstract property holder.
 * 
 * @see de.cau.cs.kieler.core.util.PropertyHolder {@code PropertyHolder}
 * 
 * @author ocl
 */
public interface IPropertyHolder {

    /**
     * Sets a property value.
     * 
     * @param property
     *            the property to set
     * @param value
     *            the new value
     */
    void setProperty(Property<?> property, Object value);

    /**
     * Retrieves a property value.
     * 
     * @param <T>
     *            type of property
     * @param property
     *            the property to get
     * @return the current value, or the default value if the property is not set
     */
    <T> T getProperty(Property<T> property);

    /**
     * Copy all properties from another property holder to this one.
     * 
     * @param other
     *            another property holder
     */
    void copyProperties(PropertyHolder other);

}
