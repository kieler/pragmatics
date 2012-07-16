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
package de.cau.cs.kieler.kiml.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;

/**
 * A layout configuration that can be used to generate on-the-fly layout options.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class VolatileLayoutConfig implements ILayoutConfig {
    
    /** the priority for volatile layout configurations. */
    public static final int PRIORITY = 100;

    /** map of focus objects and property identifiers to their values. */
    private final Map<Object, Map<IProperty<?>, Object>> optionMap
            = new HashMap<Object, Map<IProperty<?>, Object>>();
    private final Set<IProperty<?>> contextKeys = new HashSet<IProperty<?>>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "VolatileLayoutConfig:" + optionMap.toString();
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        // no properties to enrich for this layout configuration
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        for (IProperty<?> contextKey : contextKeys) {
            Object object = context.getProperty(contextKey);
            Map<IProperty<?>, Object> contextOptions = optionMap.get(object);
            if (contextOptions != null) {
                Object value = contextOptions.get(optionData);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }
    
    /**
     * Set a new value for a layout option in the context of the given object.
     * 
     * @param option a layout option property
     * @param contextObj the object to which the option value is attached,
     *          e.g. a domain model element
     * @param contextKey the layout context key related to the context object,
     *          e.g. {@link LayoutContext#DOMAIN_MODEL}
     * @param value the new layout option value
     */
    public void setValue(final IProperty<?> option, final Object contextObj,
            final IProperty<?> contextKey, final Object value) {
        contextKeys.add(contextKey);
        
        Map<IProperty<?>, Object> contextOptions = optionMap.get(contextObj);
        if (contextOptions == null) {
            contextOptions = new HashMap<IProperty<?>, Object>();
            optionMap.put(contextObj, contextOptions);
        }
        if (value == null) {
            contextOptions.remove(option);
        } else {
            contextOptions.put(option, value);
        }
    }
    
    /**
     * Copy all values from the given layout configuration into this one.
     * 
     * @param other another volatile layout configuration
     */
    public void copyValues(final VolatileLayoutConfig other) {
        this.contextKeys.addAll(other.contextKeys);
        this.optionMap.putAll(other.optionMap);
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        for (IProperty<?> contextKey : contextKeys) {
            Object object = context.getProperty(contextKey);
            Map<IProperty<?>, Object> contextOptions = optionMap.get(object);
            if (contextOptions != null) {
                for (Map.Entry<IProperty<?>, Object> option : contextOptions.entrySet()) {
                    graphData.setProperty(option.getKey(), option.getValue());
                }
            }
        }
    }

}
