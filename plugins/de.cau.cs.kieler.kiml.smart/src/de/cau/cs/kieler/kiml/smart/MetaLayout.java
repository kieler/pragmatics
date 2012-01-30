/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.smart;

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.properties.IProperty;

/**
 * A meta layout provides a layout option mapping for graph elements.
 *
 * @author msp
 */
public class MetaLayout {
    
    private static final int INITIAL_MAP_SIZE = 3;
    
    /** the configuration mapping. */
    private Map<IProperty<?>, Object> config = Maps.newHashMapWithExpectedSize(INITIAL_MAP_SIZE);
    /** the time when the cache entry was created. */
    private long timestamp = System.currentTimeMillis();
    
    /**
     * Returns the layout configuration.
     * 
     * @return the layout configuration
     */
    public Map<IProperty<?>, Object> getConfig() {
        return config;
    }
    
    /**
     * Returns the timestamp.
     * 
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * Updates the timestamp.
     */
    public void updateTimestamp() {
        timestamp = System.currentTimeMillis();
    }

}
