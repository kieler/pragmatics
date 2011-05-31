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
package de.cau.cs.kieler.kiml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.util.Pair;

/**
 * A layout configuration that can be used to generate on-the-fly layout options.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class VolatileLayoutConfig implements ILayoutConfig {

    /** the elements that are currently in focus. */
    private List<Object> currentFocus = new LinkedList<Object>();
    /** map of focus objects and property identifiers to their values. */
    private Map<Pair<Object, IProperty<?>>, Object> propertyMap
            = new LinkedHashMap<Pair<Object, IProperty<?>>, Object>();
    
    /**
     * {@inheritDoc}
     */
    public void setFocus(final Object element) {
        if (element == null) {
            currentFocus.clear();
        } else {
            currentFocus.add(element);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void setProperty(final IProperty<?> property, final Object value) {
        for (Object object : currentFocus) {
            Pair<Object, IProperty<?>> key = new Pair<Object, IProperty<?>>(object, property); 
            if (value == null) {
                propertyMap.remove(key);
            } else {
                propertyMap.put(key, value);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public <T> T getProperty(final IProperty<T> property) {
        for (Object object : currentFocus) {
            Pair<Object, IProperty<?>> key = new Pair<Object, IProperty<?>>(object, property);
            @SuppressWarnings("unchecked")
            T value = (T) propertyMap.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void copyProperties(final IPropertyHolder holder) {
        if (holder instanceof VolatileLayoutConfig) {
            VolatileLayoutConfig other = (VolatileLayoutConfig) holder;
            this.propertyMap.putAll(other.propertyMap);
        } else {
            for (Map.Entry<IProperty<?>, Object> entry : holder.getAllProperties().entrySet()) {
                setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        Map<IProperty<?>, Object> options = new HashMap<IProperty<?>, Object>();
        for (Map.Entry<Pair<Object, IProperty<?>>, Object> entry : propertyMap.entrySet()) {
            if (currentFocus.contains(entry.getKey().getFirst())) {
                options.put(entry.getKey().getSecond(), entry.getValue());
            }
        }
        return options;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        for (Object object : currentFocus) {
            Pair<Object, IProperty<?>> key = new Pair<Object, IProperty<?>>(object, optionData);
            if (propertyMap.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public List<LayoutOptionData<?>> getOptionData() {
        List<LayoutOptionData<?>> dataList = new LinkedList<LayoutOptionData<?>>();
        for (Map.Entry<Pair<Object, IProperty<?>>, Object> entry : propertyMap.entrySet()) {
            if (currentFocus.contains(entry.getKey().getFirst())
                    && entry.getKey().getSecond() instanceof LayoutOptionData) {
                dataList.add((LayoutOptionData<?>) entry.getKey().getSecond());
            }
        }
        return dataList;
    }

    /**
     * {@inheritDoc}
     */
    public void clearProperties() {
        propertyMap.clear();
    }

    /**
     * Returns {@code null}, since this layout configuration is not connected to a layout algorithm.
     * 
     * @return {@code null}
     */
    public LayoutAlgorithmData getContentLayouterData() {
        return null;
    }

    /**
     * Returns {@code null}, since this layout configuration is not connected to a layout algorithm.
     * 
     * @return {@code null}
     */
    public LayoutAlgorithmData getContainerLayouterData() {
        return null;
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change diagram options.
     * 
     * @param optionData a layout option
     * @param value an option value
     */
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to handle layout providers.
     * 
     * @param layoutHint a layout hint
     * @param diagramType a diagram type
     * @return nothing
     */
    public LayoutAlgorithmData getLayouterData(final String layoutHint, final String diagramType) {
        throw new UnsupportedOperationException();
    }

}
