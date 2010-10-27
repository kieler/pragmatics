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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Default implementation of the layout configuration interface. This configuration handles the
 * default values of layout providers and layout options.
 *
 * @author msp
 */
public class DefaultLayoutConfig implements ILayoutConfig {

    /** list of layout option data. */
    private List<LayoutOptionData<?>> optionDataList;
    /** layout provider data for the element's content. */
    private LayoutProviderData contentLayouterData;
    /** layout provider data of the containing element. */
    private LayoutProviderData containerLayouterData;
    
    /**
     * Initialize the configuration with a layout hint and diagram type for the
     * <i>content</i> of the selected element.
     * 
     * @param contentLayoutHint layout hint for the content
     * @param contentDiagramType diagram type for the content
     */
    public final void initialize(final String contentLayoutHint, final String contentDiagramType) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        contentLayouterData = layoutServices.getLayoutProviderData(
                contentLayoutHint, contentDiagramType);
        if (contentLayouterData != null) {
            List<LayoutOptionData<?>> options = layoutServices.getLayoutOptions(
                    contentLayouterData, LayoutOptionData.Target.PARENTS);
            if (optionDataList == null) {
                optionDataList = options;
            } else {
                optionDataList.addAll(options);
            }
        }
    }
    
    /**
     * Initialize the configuration with a layout hint and diagram type for the
     * <i>container</i> of the selected element.
     * 
     * @param targetType type of the selected element (node, edge, port, etc.)
     * @param containerLayoutHint layout hint for the container
     * @param containerDiagramType diagram type for the container
     */
    public final void initialize(final LayoutOptionData.Target targetType,
            final String containerLayoutHint, final String containerDiagramType) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        containerLayouterData = layoutServices.getLayoutProviderData(
                containerLayoutHint, containerDiagramType);
        if (containerLayouterData != null) {
            List<LayoutOptionData<?>> options = layoutServices.getLayoutOptions(
                    containerLayouterData, targetType);
            if (optionDataList == null) {
                optionDataList = options;
            } else {
                optionDataList.addAll(options);
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LayoutOptionData<?>> getOptionData() {
        if (optionDataList == null) {
            optionDataList = new LinkedList<LayoutOptionData<?>>();
        }
        return optionDataList;
    }

    /**
     * {@inheritDoc}
     */
    public List<Pair<IProperty<?>, Object>> getAllProperties() {
        if (optionDataList == null) {
            return Collections.emptyList();
        }
        List<Pair<IProperty<?>, Object>> list = new ArrayList<Pair<IProperty<?>, Object>>(
                optionDataList.size());
        for (LayoutOptionData<?> optionData : optionDataList) {
            list.add(new Pair<IProperty<?>, Object>(optionData, getProperty(optionData)));
        }
        return list;
    }

    /**
     * Retrieve the default value for a layout option.
     * 
     * @param <T> type of option
     * @param property a layout option
     * @return the default value for the layout option
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(final IProperty<T> property) {
        LayoutOptionData<T> optionData = property instanceof LayoutOptionData<?>
                ? (LayoutOptionData<T>) property : null;
        T result = null;
        
        // check default value of the content layout provider
        if (contentLayouterData != null && optionData != null
                && optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
            result = contentLayouterData.getInstance().getProperty(property);
            if (result != null) {
                return result;
            }
        }

        // check default value of the container layout provider
        if (containerLayouterData != null) {
            result = containerLayouterData.getInstance().getProperty(property);
            if (result != null) {
                return result;
            }
        }
        
        if (optionData != null) {
            // fall back to default value of the option itself
            result = optionData.getDefault();
            if (result != null) {
                return result;
            }
            
            // fall back to default-default value
            Object value = null;
            switch (optionData.getType()) {
            case STRING:
                value = "";
            case BOOLEAN:
                value = Boolean.FALSE;
            case ENUM:
            case INT:
                value = Integer.valueOf(0);
            case FLOAT:
                value = Float.valueOf(0.0f);
            }
            return (T) value;
        }
        return null;
    }

    /**
     * Since this configuration handles only default values, this always returns true.
     * 
     * @param optionData layout option data
     * @return true
     */
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getContentLayouterData() {
        return contentLayouterData;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getContainerLayouterData() {
        return containerLayouterData;
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change default values.
     * 
     * @param property a layout option
     * @param value an option value
     */
    public void setProperty(final IProperty<?> property, final Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change default values.
     * 
     * @param holder a property holder
     */
    public void copyProperties(final IPropertyHolder holder) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change default values. 
     */
    public void clearProperties() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws an {@link UnsupportedOperationException}, since this configuration cannot be
     * used to change default values.
     * 
     * @param optionData a layout option
     * @param value an option value
     */
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        throw new UnsupportedOperationException();
    }

}
