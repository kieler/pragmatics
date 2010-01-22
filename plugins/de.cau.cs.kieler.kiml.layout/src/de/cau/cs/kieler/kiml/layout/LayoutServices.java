/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.options.Shape;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Singleton class for access to the KIML layout services. This class is used
 * globally to retrieve data for automatic layout through KIML. The class cannot
 * be instantiated directly, but only through a subclass that calls
 * {@link createLayoutServices()}. The subclass is then responsible to add
 * appropriate data to the nested registry instance.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutServices {

    /** identifier of the 'general' diagram type, which applies to all diagrams. */
    public static final String DIAGRAM_TYPE_GENERAL = "de.cau.cs.kieler.layout.diagrams.general";
    /**
     * identifier of the 'nolayout' diagram type, for which no automatic layout
     * must be applied.
     */
    public static final String DIAGRAM_TYPE_NOLAYOUT = "de.cau.cs.kieler.layout.diagrams.nolayout";

    /** the singleton instance of the layout service. */
    private static LayoutServices instance = null;

    /** the instance of the registry class. */
    private Registry registry = null;
    /** the list of layout listeners that have been loaded at startup. */
    private List<ILayoutListener> listeners = new LinkedList<ILayoutListener>();
    /** mapping of layout provider identifiers to their data instances. */
    private Map<String, LayoutProviderData> layoutProviderMap
            = new LinkedHashMap<String, LayoutProviderData>();
    /** mapping of layout option identifiers to their data instances. */
    private Map<String, LayoutOptionData> layoutOptionMap
            = new LinkedHashMap<String, LayoutOptionData>();
    /** mapping of layout type identifiers to their names. */
    private Map<String, String> layoutTypeMap = new HashMap<String, String>();
    /** mapping of category identifiers to their names. */
    private Map<String, String> categoryMap = new HashMap<String, String>();
    /** mapping of diagram type identifiers to their names. */
    private Map<String, String> diagramTypeMap = new LinkedHashMap<String, String>();
    /** mapping of diagram element types to associated binding identifiers. */
    private Map<String, String> elementBindingMap = new LinkedHashMap<String, String>();
    /** mapping of object identifiers to associated options. */
    private Map<String, Map<String, Object>> id2OptionsMap
            = new HashMap<String, Map<String, Object>>();

    /**
     * The default constructor is hidden to prevent others from instantiating
     * this singleton class.
     */
    protected LayoutServices() {
        LayoutOptions.registerEnum(LayoutOptions.LAYOUT_DIRECTION, LayoutDirection.class);
        LayoutOptions.registerEnum(LayoutOptions.EDGE_LABEL_PLACEMENT, EdgeLabelPlacement.class);
        LayoutOptions.registerEnum(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.class);
        LayoutOptions.registerEnum(LayoutOptions.PORT_SIDE, PortSide.class);
        LayoutOptions.registerEnum(LayoutOptions.SHAPE, Shape.class);
    }

    /**
     * Creates an instance of the layout services and assigns the singleton
     * instance of the registry.
     */
    public static void createLayoutServices() {
        instance = new LayoutServices();
        instance.registry = instance.new Registry();
    }
    
    /**
     * Sets a layout services instance created by a specific subclass and assigns
     * the singleton instance of the registry.
     * 
     * @param subclassInstance an instance created by a subclass
     */
    protected static void createLayoutServices(final LayoutServices subclassInstance) {
        instance = subclassInstance;
        instance.registry = instance.new Registry();
    }

    /**
     * Returns the singleton instance of the layout services class.
     * 
     * @return the singleton instance
     */
    public static LayoutServices getInstance() {
        return instance;
    }

    /**
     * Returns the singleton instance of the registry class.
     * 
     * @return the singleton registry
     */
    public static final Registry getRegistry() {
        return instance.registry;
    }

    /**
     * Returns the associated instance of the registry class.
     * 
     * @return the associated registry
     */
    protected final Registry registry() {
        return registry;
    }

    /** Class used to register the layout services. */
    public final class Registry {

        /**
         * The default constructor is hidden to prevent others from
         * instantiating this singleton class.
         */
        private Registry() {
        }

        /** list of unprocessed layout options. */
        private List<String[]> unprocessedOptions = new LinkedList<String[]>();

        /**
         * Process all binding options that have been set up using
         * {@link #setupOption(String, String, String) setupOption}.
         */
        public void processOptions() {
            // process all layout option configurations
            for (String[] option : unprocessedOptions) {
                String optionId = option[1];
                LayoutOptionData optionData = layoutOptionMap.get(optionId);
                if (optionData != null) {
                    String valueString = option[2];
                    Object valueObj = optionData.parseValue(valueString);
                    if (valueObj != null) {
                        String bindingId = option[0];
                        Map<String, Object> optionsMap = id2OptionsMap.get(bindingId);
                        if (optionsMap != null) {
                            Object oldValue = optionsMap.get(optionId);
                            if (oldValue == null) {
                                optionsMap.put(optionId, valueObj);
                            }
                        }
                    }
                }
            }
        }

        /**
         * Adds the given layout listener to the list of registered listeners.
         * 
         * @param listener layout listener to register
         */
        public void addLayoutListener(final ILayoutListener listener) {
            listeners.add(listener);
        }

        /**
         * Removes the given layout listener from the list of registered
         * listeners.
         * 
         * @param listener layout listener to remove
         */
        public void removeLayoutListener(final ILayoutListener listener) {
            listeners.remove(listener);
        }

        /**
         * Registers the given layout provider. If there is already a registered
         * provider data instance with the same identifier, it is overwritten.
         * 
         * @param providerData data instance of the layout provider to register
         */
        public void addLayoutProvider(final LayoutProviderData providerData) {
            layoutProviderMap.put(providerData.getId(), providerData);
        }

        /**
         * Registers the given layout option. If there is already a registered
         * option data instance with the same identifier, it is overwritten.
         * 
         * @param optionData data instance of the layout option to register
         */
        public void addLayoutOption(final LayoutOptionData optionData) {
            layoutOptionMap.put(optionData.getId(), optionData);
        }

        /**
         * Registers the given layout type.
         * 
         * @param id identifier of the type
         * @param name user friendly name of the type
         */
        public void addLayoutType(final String id, final String name) {
            layoutTypeMap.put(id, name);
        }

        /**
         * Registers the given diagram element type with the binding identifier.
         * 
         * @param elementType class name of diagram element to register
         * @param bindingId identifier of the category of elements
         */
        public void addElementBinding(final String elementType, final String bindingId) {
            elementBindingMap.put(elementType, bindingId);
            if (!id2OptionsMap.containsKey(bindingId)) {
                id2OptionsMap.put(bindingId, new LinkedHashMap<String, Object>());
            }
        }

        /**
         * Registers the given category.
         * 
         * @param id identifier of the category
         * @param name user friendly name of the category
         */
        public void addCategory(final String id, final String name) {
            categoryMap.put(id, name);
        }

        /**
         * Registers the given diagram type. Diagram types with
         * {@link DIAGRAM_TYPE_NOLAYOUT} as identifier are rejected, since this
         * is a special value used to indicate that a diagram part shall not be
         * layouted at all.
         * 
         * @param id identifier of the diagram type
         * @param name user friendly name of the diagram type
         */
        public void addDiagramType(final String id, final String name) {
            if (!DIAGRAM_TYPE_NOLAYOUT.equals(id)) {
                diagramTypeMap.put(id, name);
            }
        }

        /**
         * Registers a layout option configuration for the given diagram setup.
         * The {@link #processOptions()} method must be called after all options have
         * been set up.
         * 
         * @param bindingId identifier of the category of diagram elements
         * @param optionId identifier of a layout option
         * @param value serialized value for the layout option
         */
        public void setupOption(final String bindingId, final String optionId,
                final String value) {
            unprocessedOptions.add(new String[] {bindingId, optionId, value});
        }
        
        /**
         * Adds the given option as default for a diagram element type.
         * 
         * @param elementType class name of a diagram element
         * @param optionId identifier of a layout option
         * @param value value for the layout option
         */
        public void addBindingOption(final String elementType, final String optionId,
                final Object value) {
            String bindingId = elementBindingMap.get(elementType);
            if (bindingId == null) {
                bindingId = elementType + ".binding";
                elementBindingMap.put(elementType, bindingId);
            }
            addOption(bindingId, optionId, value);
        }
        
        /**
         * Adds the given option as default for an object identifier.
         * 
         * @param id identifier of the object to register
         * @param optionId identifier of a layout option
         * @param value value for the layout option
         */
        public void addOption(final String id, final String optionId,
                final Object value) {
            Map<String, Object> optionsMap = id2OptionsMap.get(id);
            if (optionsMap == null) {
                optionsMap = new LinkedHashMap<String, Object>();
                id2OptionsMap.put(id, optionsMap);
            }
            optionsMap.put(optionId, value);
        }

    }

    /**
     * Calls the {@link ILayoutListener#layoutRequested layoutRequested} method
     * of all registered layout listeners.
     * 
     * @param layoutGraph layout graph for which layout is requested
     */
    public final void layoutRequested(final KNode layoutGraph) {
        // find the root node if the given layout node is not the root
        KNode rootGraph = layoutGraph;
        while (rootGraph.getParent() != null) {
            rootGraph = rootGraph.getParent();
        }
        for (ILayoutListener listener : listeners) {
            listener.layoutRequested(rootGraph);
        }
    }

    /**
     * Calls the {@link ILayoutListener#layoutPerformed layoutPerformed} method
     * of all registered layout listeners.
     * 
     * @param layoutGraph layout graph for which layout was performed
     * @param monitor progress monitor containing execution time results
     */
    public final void layoutPerformed(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
        // find the root node if the given layout node is not the root
        KNode rootGraph = layoutGraph;
        while (rootGraph.getParent() != null) {
            rootGraph = rootGraph.getParent();
        }
        for (ILayoutListener listener : listeners) {
            listener.layoutPerformed(rootGraph, monitor);
        }
    }

    /**
     * Returns the layout provider data associated with the given identifier.
     * 
     * @param id layout provider identifier
     * @return the corresponding layout provider data, or {@code null} if there
     *         is no provider with the given identifier
     */
    public final LayoutProviderData getLayoutProviderData(final String id) {
        return layoutProviderMap.get(id);
    }

    /**
     * Returns a data collection for all registered layout providers. The collection
     * is unmodifiable.
     * 
     * @return collection of registered layout providers
     */
    public final Collection<LayoutProviderData> getLayoutProviderData() {
        return Collections.unmodifiableCollection(layoutProviderMap.values());
    }

    /**
     * Returns the layout provider with highest priority for the given layout
     * hint and diagram type.
     * 
     * @param layoutHint identifier of either a layout provider or a layout type
     * @param diagramType identifier of a diagram type
     * @return the layout provider with highest priority, or {@code null} if
     *         there is no registered layout provider
     */
    public final LayoutProviderData getLayoutProviderData(final String layoutHint,
            final String diagramType) {
        // try to get a specific provider for the given node
        LayoutProviderData providerData = layoutProviderMap.get(layoutHint);
        if (providerData != null) {
            return providerData;
        }
        // find the most appropriate provider from the layout type and diagram type
        return findAppropriateProvider(layoutHint, diagramType);
    }

    /**
     * Returns the most appropriate layout provider for the given node.
     * 
     * @param layoutNode node for which a layout provider is requested
     * @return a layout provider instance that fits the layout hints for the
     *         given node
     * @throws KielerException if there is no registered layout provider
     */
    public final AbstractLayoutProvider getLayoutProvider(final KNode layoutNode)
            throws KielerException {
        KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        String layoutHint = LayoutOptions.getString(nodeLayout, LayoutOptions.LAYOUT_HINT);
        String diagramType = LayoutOptions.getString(nodeLayout, LayoutOptions.DIAGRAM_TYPE);
        LayoutProviderData providerData = getLayoutProviderData(layoutHint, diagramType);
        if (providerData != null) {
            return providerData.getInstance();
        } else {
            throw new KielerException("No registered layout provider is available.");
        }
    }

    /**
     * Returns the layout option data associated with the given identifier.
     * 
     * @param id layout option identifier
     * @return the corresponding layout option data, or {@code null} if there is
     *         no option with the given identifier
     */
    public final LayoutOptionData getLayoutOptionData(final String id) {
        return layoutOptionMap.get(id);
    }

    /**
     * Returns a data collection for all registered layout options. The collection is
     * unmodifiable.
     * 
     * @return collection of registered layout options
     */
    public final Collection<LayoutOptionData> getLayoutOptionData() {
        return Collections.unmodifiableCollection(layoutOptionMap.values());
    }

    /**
     * Returns a list of layout options that are suitable for the given layout
     * provider and layout option target. The layout provider must know the
     * layout options and at the target must be active for each option.
     * 
     * @param providerData layout provider data
     * @param target layout option target
     * @return list of suitable layout options
     */
    public final List<LayoutOptionData> getLayoutOptions(final LayoutProviderData providerData,
            final LayoutOptionData.Target target) {
        List<LayoutOptionData> optionDataList = new ArrayList<LayoutOptionData>();
        for (LayoutOptionData optionData : layoutOptionMap.values()) {
            if (providerData.knowsOption(optionData.getId())
                    || LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                if (optionData.hasTarget(target)) {
                    optionDataList.add(optionData);
                }
            }
        }
        return optionDataList;
    }

    /**
     * Returns the name of the layout type with given identifier.
     * 
     * @param id identifier of the type
     * @return user friendly name of the type, or {@code null} if the layout
     *         type is not registered
     */
    public final String getLayoutTypeName(final String id) {
        return layoutTypeMap.get(id);
    }
    
    /**
     * Returns a list of layout type identifiers and names. The first string in each
     * entry is the identifier, and the second string is the name.
     * 
     * @return a list of all layout types
     */
    public final List<Pair<String, String>> getLayoutTypes() {
        return Pair.toList(layoutTypeMap);
    }

    /**
     * Returns the name of the given category.
     * 
     * @param id identifier of the category
     * @return user friendly name of the category, or {@code null} if there
     *         is no category with the given identifier
     */
    public final String getCategoryName(final String id) {
        return categoryMap.get(id);
    }

    /**
     * Returns the name of the given diagram type.
     * 
     * @param id identifier of the diagram type
     * @return user friendly name of the diagram type, or {@code null} if there
     *         is no diagram type with the given identifier
     */
    public final String getDiagramTypeName(final String id) {
        return diagramTypeMap.get(id);
    }

    /**
     * Returns a collection of registered diagram types. The first element of each
     * returned entry is a diagram type identifier, the second element is the
     * corresponding name.
     * 
     * @return the registered diagram types
     */
    public final List<Pair<String, String>> getDiagramTypes() {
        return Pair.toList(diagramTypeMap);
    }
    
    /**
     * Returns a map that contains all layout options for a diagram element type.
     * 
     * @param elementType a type of diagram element
     * @return a map of layout option identifiers to their values
     */
    public final Map<String, Object> getBindingOptions(final String elementType) {
        String bindingId = elementBindingMap.get(elementType);
        return getOptions(bindingId);
    }

    /**
     * Retrieves the layout option with given identifier for a diagram element type.
     * 
     * @param elementType a type of diagram element
     * @param optionId the layout option identifier
     * @return the preconfigured value of the option, or {@code null} if the
     *         option is not set for the given diagram element
     */
    public final Object getBindingOption(final String elementType, final String optionId) {
        String bindingId = elementBindingMap.get(elementType);
        return getOption(bindingId, optionId);
    }
    
    /**
     * Returns a map that contains all layout options for an object identifier.
     * 
     * @param id an object identifier
     * @return a map of layout option identifiers to their values
     */
    public final Map<String, Object> getOptions(final String id) {
        Map<String, Object> optionsMap = id2OptionsMap.get(id);
        if (optionsMap != null) {
            return Collections.unmodifiableMap(optionsMap);
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * Retrieves the layout option with given identifier for an object identifier.
     * 
     * @param id an object identifier
     * @param optionId the layout option identifier
     * @return the preconfigured value of the option, or {@code null} if the
     *         option is not set for the given diagram type
     */
    public final Object getOption(final String id, final String optionId) {
        Map<String, Object> optionsMap = id2OptionsMap.get(id);
        if (optionsMap != null) {
            return optionsMap.get(optionId);
        } else {
            return null;
        }
    }

    /**
     * Checks whether the diagram type configured for the given element is a
     * "no layout" diagram.
     * 
     * @param elementType class of diagram element
     * @return true if no layout must be performed for the given element
     */
    public final boolean isNolayout(final String elementType) {
        String diagramType = (String) getBindingOption(elementType, LayoutOptions.DIAGRAM_TYPE);
        return DIAGRAM_TYPE_NOLAYOUT.equals(diagramType);
    }

    /**
     * Determines an appropriate layout provider for the given layout type and
     * diagram type.
     * 
     * @param layoutType hint about the layout type to choose, or {@code null}
     *            if no specific layout type is selected
     * @param diagramType identifier of the diagram type that is to be layouted
     * @return data instance for the most appropriate layout provider, or
     *         {@code null} if there is no layout provider
     */
    private LayoutProviderData findAppropriateProvider(final String layoutType,
            final String diagramType) {
        Iterator<LayoutProviderData> providerIter = layoutProviderMap.values().iterator();
        LayoutProviderData bestProvider = null;
        int bestPrio = LayoutProviderData.MIN_PRIORITY;
        boolean matchesLayoutType = false, matchesDiagramType = false, matchesGeneralDiagram = false;
        // look for an appropriate provider and return the best one
        while (providerIter.hasNext()) {
            LayoutProviderData providerData = providerIter.next();
            int currentPrio = providerData.getSupportedPriority(diagramType);
            if (matchesLayoutType) {
                if (providerData.getType().equals(layoutType)) {
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
                            currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
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
                if (providerData.getType().equals(layoutType)) {
                    bestProvider = providerData;
                    matchesLayoutType = true;
                    if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                        bestPrio = currentPrio;
                        matchesDiagramType = true;
                        matchesGeneralDiagram = false;
                    } else {
                        matchesDiagramType = false;
                        currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
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
                            currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
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

}
