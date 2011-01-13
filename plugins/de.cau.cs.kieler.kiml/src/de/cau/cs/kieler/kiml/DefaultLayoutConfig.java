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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Default implementation of the layout configuration interface. This configuration handles the
 * default values of layout providers and layout options.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
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
     * Initialize the configuration with a layout hint. This is only done if the given
     * object is a string.
     * 
     * @param element a layout hint of type {@link String}
     */
    public void setFocus(final Object element) {
        if (element instanceof String) {
            initialize(LayoutOptionData.Target.PARENTS, (String) element, null);
        }
    }
    
    /**
     * Initialize the configuration with a layout hint and diagram type for the
     * content or the container of the selected element.
     * 
     * @param targetType type of the selected element (parent, node, edge, port, etc.)
     * @param layoutHint a layout hint, or {@code null}
     * @param diagramType a diagram type, or {@code null}
     */
    public final void initialize(final LayoutOptionData.Target targetType,
            final String layoutHint, final String diagramType) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        LayoutProviderData layouterData = getLayouterData(layoutHint, diagramType);
        if (targetType == LayoutOptionData.Target.PARENTS) {
            contentLayouterData = layouterData;
        } else {
            containerLayouterData = layouterData;
        }
        if (layouterData != null) {
            List<LayoutOptionData<?>> options = layoutServices.getLayoutOptions(
                    layouterData, targetType);
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
     * Returns a map of all layout options that are available for the selected element
     * to their default values.
     * 
     * @return a map of all layout options to their default values 
     */
    public Map<IProperty<?>, Object> getAllProperties() {
        if (optionDataList == null) {
            return Collections.emptyMap();
        }
        Map<IProperty<?>, Object> optionMap = new HashMap<IProperty<?>, Object>();
        for (LayoutOptionData<?> optionData : optionDataList) {
            optionMap.put(optionData, getPropertyInternal(optionData));
        }
        return optionMap;
    }

    /**
     * Retrieve the default value for a layout option. This implementation requires
     * {@link #initialize(de.cau.cs.kieler.kiml.LayoutOptionData.Target, String, String) initialize}
     * to be called first in order to work properly.
     * 
     * @param <T> type of option
     * @param property a layout option
     * @return the default value for the layout option
     */
    public <T> T getProperty(final IProperty<T> property) {
        if (property instanceof LayoutOptionData<?>) {
            return getPropertyInternal((LayoutOptionData<T>) property);
        }
        return null;
    }
    
    /**
     * Retrieve the default value for a layout option.
     * 
     * @param <T> type of option
     * @param property a layout option
     * @return the default value for the layout option
     */
    private <T> T getPropertyInternal(final LayoutOptionData<T> optionData) {
        T result = null;
        
        // check default value of the content layout provider
        if (contentLayouterData != null && optionData != null
                && optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
            result = contentLayouterData.getInstance().getProperty(optionData);
            if (result != null) {
                return result;
            }
        }

        // check default value of the container layout provider
        if (containerLayouterData != null) {
            result = containerLayouterData.getInstance().getProperty(optionData);
            if (result != null) {
                return result;
            }
        }
        
        // fall back to default value of the option itself
        result = optionData.getDefault();
        if (result != null) {
            return result;
        }
        
        // fall back to default-default value
        return optionData.getDefaultDefault();
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
     * {@inheritDoc}
     */
    public LayoutProviderData getLayouterData(final String theLayoutHint, final String diagramType) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        String layoutHint = theLayoutHint;
        // check whether a specific provider is registered for the diagram type
        if (layoutHint == null && diagramType != null) {
            layoutHint = (String) layoutServices.getOption(diagramType,
                    LayoutOptions.LAYOUTER_HINT_ID);
        }
        
        // try to get a specific provider for the given hint
        LayoutProviderData directHitData = layoutServices.getLayoutProviderData(layoutHint);
        if (directHitData != null) {
            return directHitData;
        }

        // look for the provider with highest priority, interpreting the hint as layout type
        LayoutProviderData bestProvider = null;
        int bestPrio = LayoutProviderData.MIN_PRIORITY;
        boolean matchesLayoutType = false, matchesDiagramType = false, matchesGeneralDiagram = false;
        for (LayoutProviderData providerData : layoutServices.getLayoutProviderData()) {
            int currentPrio = providerData.getSupportedPriority(diagramType);
            if (matchesLayoutType) {
                if (providerData.getType().equals(layoutHint)) {
                    if (matchesDiagramType) {
                        if (currentPrio > bestPrio) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                        }
                    } else {
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                            matchesDiagramType = true;
                            matchesGeneralDiagram = false;
                        } else {
                            currentPrio = providerData.getSupportedPriority(
                                    LayoutServices.DIAGRAM_TYPE_GENERAL);
                            if (matchesGeneralDiagram) {
                                if (currentPrio > bestPrio) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                }
                            } else {
                                if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                    matchesGeneralDiagram = true;
                                } else if (bestProvider == null) {
                                    bestProvider = providerData;
                                }
                            }
                        }
                    }
                }
            } else {
                if (providerData.getType().equals(layoutHint)) {
                    bestProvider = providerData;
                    matchesLayoutType = true;
                    if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                        bestPrio = currentPrio;
                        matchesDiagramType = true;
                        matchesGeneralDiagram = false;
                    } else {
                        matchesDiagramType = false;
                        currentPrio = providerData.getSupportedPriority(
                                LayoutServices.DIAGRAM_TYPE_GENERAL);
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestPrio = currentPrio;
                            matchesGeneralDiagram = true;
                        } else {
                            matchesGeneralDiagram = false;
                        }
                    }
                } else {
                    if (matchesDiagramType) {
                        if (currentPrio > bestPrio) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                        }
                    } else {
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                            matchesDiagramType = true;
                            matchesGeneralDiagram = false;
                        } else {
                            currentPrio = providerData.getSupportedPriority(
                                    LayoutServices.DIAGRAM_TYPE_GENERAL);
                            if (matchesGeneralDiagram) {
                                if (currentPrio > bestPrio) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                }
                            } else {
                                if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                    matchesGeneralDiagram = true;
                                } else if (bestProvider == null) {
                                    bestProvider = providerData;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bestProvider;
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
