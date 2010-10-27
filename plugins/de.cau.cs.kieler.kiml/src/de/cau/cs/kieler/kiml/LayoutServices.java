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
package de.cau.cs.kieler.kiml;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.Shape;

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

    /** the singleton instance of the layout service. */
    private static LayoutServices instance = null;

    /** the instance of the registry class. */
    private Registry registry = null;
    /** mapping of layout provider identifiers to their data instances. */
    private Map<String, LayoutProviderData> layoutProviderMap
            = new LinkedHashMap<String, LayoutProviderData>();
    /** mapping of layout option identifiers to their data instances. */
    private Map<String, LayoutOptionData<?>> layoutOptionMap
            = new LinkedHashMap<String, LayoutOptionData<?>>();
    /** mapping of layout type identifiers to their names. */
    private Map<String, String> layoutTypeMap = new HashMap<String, String>();
    /** mapping of category identifiers to their names. */
    private Map<String, String> categoryMap = new HashMap<String, String>();
    /** mapping of diagram type identifiers to their names. */
    private Map<String, String> diagramTypeMap = new LinkedHashMap<String, String>();
    /** mapping of object identifiers to associated options. */
    private Map<String, Map<String, Object>> id2OptionsMap
            = new HashMap<String, Map<String, Object>>();
    /** map of enumeration classes to layout option identifiers. */
    private static Map<Class<? extends Enum<?>>, String> enum2idMap =
            new HashMap<Class<? extends Enum<?>>, String>();

    /**
     * The default constructor is hidden to prevent others from instantiating
     * this singleton class.
     */
    protected LayoutServices() {
        enum2idMap.put(EdgeLabelPlacement.class, LayoutOptions.EDGE_LABEL_PLACEMENT_ID);
        enum2idMap.put(PortSide.class, LayoutOptions.PORT_SIDE_ID);
        enum2idMap.put(Shape.class, LayoutOptions.SHAPE_ID);
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
        @SuppressWarnings("unchecked")
        public void addLayoutOption(final LayoutOptionData<?> optionData) {
            layoutOptionMap.put(optionData.getId(), optionData);
            Class<?> clazz = optionData.getOptionClass();
            if (clazz != null && clazz.isEnum()) {
                enum2idMap.put((Class<Enum<?>>) clazz, optionData.getId());
            }
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
         * Registers the given category.
         * 
         * @param id identifier of the category
         * @param name user friendly name of the category
         */
        public void addCategory(final String id, final String name) {
            categoryMap.put(id, name);
        }

        /**
         * Registers the given diagram type.
         * 
         * @param id identifier of the diagram type
         * @param name user friendly name of the diagram type
         */
        public void addDiagramType(final String id, final String name) {
            diagramTypeMap.put(id, name);
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
     * Returns the layout option data associated with the given identifier.
     * 
     * @param id layout option identifier
     * @return the corresponding layout option data, or {@code null} if there is
     *         no option with the given identifier
     */
    public final LayoutOptionData<?> getLayoutOptionData(final String id) {
        return layoutOptionMap.get(id);
    }
    
    /**
     * Returns a layout option data for the given enumeration class.
     * 
     * @param <T> enumeration class type
     * @param clazz the enumeration class
     * @return a corresponding layout option data, or {@code null} if there is no such
     *     option data
     */
    @SuppressWarnings("unchecked")
    public final <T extends Enum<?>> LayoutOptionData<T> getLayoutOptionData(final Class<T> clazz) {
        String optionId = enum2idMap.get(clazz);
        if (optionId != null) {
            LayoutOptionData<?> optionData = layoutOptionMap.get(optionId);
            if (optionData != null) {
                return (LayoutOptionData<T>) optionData;
            }
        }
        return null;
    }

    /**
     * Returns a data collection for all registered layout options. The collection is
     * unmodifiable.
     * 
     * @return collection of registered layout options
     */
    public final Collection<LayoutOptionData<?>> getLayoutOptionData() {
        return Collections.unmodifiableCollection(layoutOptionMap.values());
    }

    /**
     * Returns a list of layout options that are suitable for the given layout
     * provider and layout option target. The layout provider must know the
     * layout options and at the target must be active for each option.
     * 
     * @param providerData layout provider data
     * @param targetType type of layout option target
     * @return list of suitable layout options
     */
    public final List<LayoutOptionData<?>> getLayoutOptions(final LayoutProviderData providerData,
            final LayoutOptionData.Target targetType) {
        List<LayoutOptionData<?>> optionDataList = new LinkedList<LayoutOptionData<?>>();
        for (LayoutOptionData<?> optionData : layoutOptionMap.values()) {
            if (providerData.knowsOption(optionData.getId())
                    || LayoutOptions.LAYOUTER_HINT_ID.equals(optionData.getId())) {
                if (optionData.hasTarget(targetType)) {
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
     * @param objectId an object identifier
     * @param optionId the layout option identifier
     * @return the preconfigured value of the option, or {@code null} if the
     *         option is not set for the given object
     */
    public final Object getOption(final String objectId, final String optionId) {
        Map<String, Object> optionsMap = id2OptionsMap.get(objectId);
        if (optionsMap != null) {
            return optionsMap.get(optionId);
        } else {
            return null;
        }
    }

}
