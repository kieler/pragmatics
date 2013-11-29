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

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;

/**
 * A layout configurator that can be used to generate on-the-fly layout options.
 * Use {@link #setValue(IProperty, Object, IProperty, Object)} to set a layout option value for a
 * particular context. Use {@link #setValue(IProperty, Object)} to set a layout option value
 * globally for all graph elements to which the option can be applied. All configured values are
 * held in a hash map that is queried when the configurator is applied to the layout graph.
 * The configuration is non-persistent (hence the name <em>volatile</em>).
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2013-07-01 review KI-38 by cds, uru
 */
public class VolatileLayoutConfig implements ILayoutConfig {
    
    /** the default priority for volatile layout configurators. */
    public static final int DEFAULT_PRIORITY = 100;

    /** map of focus objects and property identifiers to their values. */
    private final Map<Object, Map<IProperty<?>, Object>> focusOptionMap = Maps.newHashMap();
    /** the layout context keys managed by this configurator. */
    private final Set<IProperty<?>> contextKeys = new HashSet<IProperty<?>>();
    
    /** the map of layout options set for this configurator. */
    private final Map<LayoutOptionData<?>, Object> globalOptionMap = Maps.newHashMap();
    
    /** the priority of this configurator. */
    private int priority;
    
    /**
     * Creates a volatile layout configurator with default priority.
     */
    public VolatileLayoutConfig() {
        priority = DEFAULT_PRIORITY;
    }
    
    /**
     * Creates a volatile layout configurator with given priority.
     * 
     * @param prio the priority to apply for this configurator
     */
    public VolatileLayoutConfig(final int prio) {
        this.priority = prio;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "VolatileLayoutConfig:" + focusOptionMap.toString() + globalOptionMap.toString();
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * Clear all stored values.
     */
    public void clear() {
        focusOptionMap.clear();
        globalOptionMap.clear();
    }
    
    /**
     * Copy all values from the given layout configurator into this one.
     * 
     * @param other another volatile layout configurator
     */
    public void copyValues(final VolatileLayoutConfig other) {
        this.contextKeys.addAll(other.contextKeys);
        this.focusOptionMap.putAll(other.focusOptionMap);
        this.globalOptionMap.putAll(other.globalOptionMap);
    }
    
    /**
     * Returns the stored global value for the given layout option, if any.
     * 
     * @param option a layout option
     * @return the global value for the option
     */
    public Object getGlobalValue(final IProperty<?> option) {
        return globalOptionMap.get(option);
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
            // retrieve the object stored under this key from the context
            Object object = context.getProperty(contextKey);
            // retrieve the map of options that have been set for that object
            Map<IProperty<?>, Object> contextOptions = focusOptionMap.get(object);
            if (contextOptions != null) {
                // get the value set for the given layout option, if any
                Object value = contextOptions.get(optionData);
                if (value != null) {
                    return value;
                }
            }
        }
        
        // retrieve the value stored globally for the given option
        Object value = globalOptionMap.get(optionData);
        if (value != null) {
            KGraphElement graphElem = context.getProperty(LayoutContext.GRAPH_ELEM);
            boolean isGlobal = context.getProperty(LayoutContext.GLOBAL);
            if (isGlobal || matchesTargetType(optionData, graphElem)) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * Set a new value for a layout option in the context of the given object.
     * 
     * @param option a layout option
     * @param contextObj the object to which the option value is attached,
     *          e.g. a domain model element
     * @param contextKey the layout context key related to the context object,
     *          e.g. {@link LayoutContext#DOMAIN_MODEL}
     * @param value the new layout option value
     * @return the instance on which the method was called, for chaining multiple method calls
     * @param <T> the type of the layout option
     */
    public <T> VolatileLayoutConfig setValue(final IProperty<? super T> option, final Object contextObj,
            final IProperty<?> contextKey, final T value) {
        contextKeys.add(contextKey);
        
        Map<IProperty<?>, Object> contextOptions = focusOptionMap.get(contextObj);
        if (contextOptions == null) {
            contextOptions = new HashMap<IProperty<?>, Object>();
            focusOptionMap.put(contextObj, contextOptions);
        }
        if (value == null) {
            contextOptions.remove(option);
        } else {
            contextOptions.put(option, value);
        }
        return this;
    }
    
    /**
     * Set the given layout option value globally. This value has lower priority than values
     * set for a specific context via {@link #setValue(IProperty, Object, IProperty, Object)}.
     * 
     * @param option a layout option
     * @param value the new global value for the option
     * @return the instance on which the method was called, for chaining multiple method calls
     * @param <T> the type of the layout option
     */
    public <T> VolatileLayoutConfig setValue(final IProperty<? super T> option, final T value) {
        if (option instanceof LayoutOptionData<?>) {
            globalOptionMap.put((LayoutOptionData<?>) option, value);
        } else {
            LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData(
                    option.getId());
            if (optionData != null) {
                globalOptionMap.put(optionData, value);
            } else {
                throw new IllegalArgumentException(
                        "The given property is not registered as a layout option");
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KLayoutData graphData, final LayoutContext context) {
        // transfer globally defined options
        KGraphElement graphElem = context.getProperty(LayoutContext.GRAPH_ELEM);
        boolean isGlobal = context.getProperty(LayoutContext.GLOBAL);
        for (Map.Entry<LayoutOptionData<?>, Object> entry : globalOptionMap.entrySet()) {
            if (isGlobal || matchesTargetType(entry.getKey(), graphElem)) {
                graphData.setProperty(entry.getKey(), entry.getValue());
            }
        }
        
        // transfer options for the focus object
        for (IProperty<?> contextKey : contextKeys) {
            Object object = context.getProperty(contextKey);
            Map<IProperty<?>, Object> contextOptions = focusOptionMap.get(object);
            if (contextOptions != null) {
                for (Map.Entry<IProperty<?>, Object> option : contextOptions.entrySet()) {
                    graphData.setProperty(option.getKey(), option.getValue());
                }
            }
        }
    }
    
    /**
     * Check whether the given diagram part matches the target type of the layout option.
     * 
     * @param optionData a layout option data instance
     * @param graphElem a graph element
     * @return true if the layout option can be applied to the graph element
     */
    private boolean matchesTargetType(final LayoutOptionData<?> optionData,
            final KGraphElement graphElem) {
        Set<LayoutOptionData.Target> optionTargets = optionData.getTargets();
        switch (graphElem.eClass().getClassifierID()) {
        case KGraphPackage.KNODE:
            if (optionTargets.contains(LayoutOptionData.Target.NODES)
                    || !((KNode) graphElem).getChildren().isEmpty()
                    && optionTargets.contains(LayoutOptionData.Target.PARENTS)) {
                return true;
            }
            break;
        case KGraphPackage.KEDGE:
            if (optionTargets.contains(LayoutOptionData.Target.EDGES)) {
                return true;
            }
            break;
        case KGraphPackage.KPORT:
            if (optionTargets.contains(LayoutOptionData.Target.PORTS)) {
                return true;
            }
            break;
        case KGraphPackage.KLABEL:
            if (optionTargets.contains(LayoutOptionData.Target.LABELS)) {
                return true;
            }
            break;
        }
        return false;
    }

}
