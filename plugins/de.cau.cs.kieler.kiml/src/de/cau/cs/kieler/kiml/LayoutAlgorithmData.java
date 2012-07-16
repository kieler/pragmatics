/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;

import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.alg.InstancePool;
import de.cau.cs.kieler.kiml.options.GraphFeature;

/**
 * Data type used to store information for a layout algorithm.
 * 
 * @kieler.design 2011-02-01 reviewed by cmot, soh
 * @kieler.rating 2012-07-10 proposed yellow msp
 * @author msp
 */
public class LayoutAlgorithmData implements ILayoutData {

    /** The minimal allowed priority value. Values less or equal to this value
     *  are treated as 'not supported'. */
    public static final int MIN_PRIORITY = Integer.MIN_VALUE >> 2;
    /** default name for layout algorithms for which no name is given. */
    public static final String DEFAULT_LAYOUTER_NAME = "<Unnamed Layouter>";
    
    /** identifier of the layout provider. */
    private String id = "";
    /** user friendly name of the layout algorithm. */
    private String name = "";
    /** runtime instance of the layout algorithm. */
    private InstancePool<AbstractLayoutProvider> providerPool;
    /** layout type identifier. */
    private String type = "";
    /** category identifier. */
    private String category = "";
    /** detail description. */
    private String description = "";
    /** an object holding preview image data. */
    private Object imageData;
    
    /** Map of known layout options. Keys are option data, values are the default values. */
    private final Map<LayoutOptionData<?>, Object> knownOptions = Maps.newHashMap();
    /** map of supported diagrams. */
    private final Map<String, Integer> supportedDiagrams = Maps.newHashMap();
    /** map of supported graph features. */
    private final Map<GraphFeature, Integer> supportedFeatures = Maps.newEnumMap(GraphFeature.class);
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof LayoutAlgorithmData) {
            return this.id.equals(((LayoutAlgorithmData) obj).id);
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (name != null && name.length() > 0) {
            String categoryName = LayoutDataService.getInstance().getCategoryName(category);
            if (categoryName == null) {
                return name;
            } else {
                return name + " (" + categoryName + ")";
            }
        } else {
            return DEFAULT_LAYOUTER_NAME;
        }
    }
    
    /**
     * Sets the knowledge status of the given layout option.
     * 
     * @param optionData layout option data
     * @param defaultValue the default value, or {@code null} if none is specified
     */
    public void setOption(final LayoutOptionData<?> optionData, final Object defaultValue) {
        if (optionData != null) {
            knownOptions.put(optionData, defaultValue);
        }
    }
    
    /**
     * Determines whether the layout algorithm knows the given layout option.
     * 
     * @param optionData layout option data
     * @return true if the associated layout algorithm knows the option
     */
    public boolean knowsOption(final LayoutOptionData<?> optionData) {
        return knownOptions.containsKey(optionData);
    }
    
    /**
     * Returns the layout algorithm's default value for the given option.
     * 
     * @param optionData layout option data
     * @param <T> the layout option type
     * @return the associated default value, or {@code null} if there is none
     */
    @SuppressWarnings("unchecked")
    public <T> T getDefaultValue(final LayoutOptionData<T> optionData) {
        return (T) knownOptions.get(optionData);
    }
    
    /**
     * Sets support for the given diagram type. If the priority is less or equal to
     * {@link MIN_PRIORITY}, the type is treated as not supported.
     * 
     * @param diagramType identifier of diagram type
     * @param priority priority value, or {@code MIN_PRIORITY} if the diagram type is not supported
     */
    public void setDiagramSupport(final String diagramType, final int priority) {
        if (priority > MIN_PRIORITY) {
            supportedDiagrams.put(diagramType, priority);
        } else {
            supportedDiagrams.remove(diagramType);
        }
    }
    
    /**
     * Returns the supported priority for the given diagram type. If the type is not supported,
     * {@link MIN_PRIORITY} is returned.
     * 
     * @param diagramType diagram type identifier
     * @return associated priority, or {@code MIN_PRIORITY} if the diagram type is not supported
     */
    public int getSupportedPriority(final String diagramType) {
        Integer result = supportedDiagrams.get(diagramType);
        if (result != null) {
            return result;
        }
        return MIN_PRIORITY;
    }
    
    /**
     * Sets support for the given graph feature. If the priority is less or equal to
     * {@link MIN_PRIORITY}, the feature is treated as not supported.
     * 
     * @param graphFeature the graph feature
     * @param priority priority value, or {@code MIN_PRIORITY} if the feature is not supported
     */
    public void setFeatureSupport(final GraphFeature graphFeature, final int priority) {
        if (priority > MIN_PRIORITY) {
            supportedFeatures.put(graphFeature, priority);
        } else {
            supportedFeatures.remove(graphFeature);
        }
    }
    
    /**
     * Returns the supported priority for the given graph feature. If the feature is not supported,
     * {@link MIN_PRIORITY} is returned.
     * 
     * @param graphFeature the graph feature
     * @return associated priority, or {@code MIN_PRIORITY} if the feature is not supported
     */
    public int getSupportedPriority(final GraphFeature graphFeature) {
        Integer result = supportedFeatures.get(graphFeature);
        if (result != null) {
            return result;
        }
        return MIN_PRIORITY;
    }

    /**
     * Sets the id.
     *
     * @param theid the id to set
     */
    public void setId(final String theid) {
        assert theid != null;
        this.id = theid;
    }

    /**
     * Returns the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the name.
     *
     * @param thename the name to set
     */
    public void setName(final String thename) {
        if (thename == null || thename.length() == 0) {
            this.name = DEFAULT_LAYOUTER_NAME;
        } else {
            this.name = thename;
        }
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the description.
     * 
     * @param thedescription the description to set
     */
    public void setDescription(final String thedescription) {
        if (thedescription == null) {
            this.description = "";
        } else {
            this.description = thedescription;
        }
    }
    
    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Create a pool for instances of the layout provider.
     *
     * @param providerFactory a factory for layout providers
     */
    public void createPool(final IFactory<AbstractLayoutProvider> providerFactory) {
        this.providerPool = new InstancePool<AbstractLayoutProvider>(providerFactory);
    }

    /**
     * Returns an instance pool for layout providers.
     *
     * @return a layout provider instance pool
     */
    public InstancePool<AbstractLayoutProvider> getProviderPool() {
        return providerPool;
    }

    /**
     * Sets the type.
     *
     * @param thetype the type to set
     */
    public void setType(final String thetype) {
        this.type = thetype;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the category.
     *
     * @param thecategory the category to set
     */
    public void setCategory(final String thecategory) {
        if (thecategory == null) {
            this.category = "";
        } else {
            this.category = thecategory;
        }
    }

    /**
     * Returns the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the preview image data.
     * 
     * @return the preview image
     */
    public Object getPreviewImage() {
        return imageData;
    }

    /**
     * Sets the preview image data.
     * 
     * @param thepreviewImage the preview image to set
     */
    public void setPreviewImage(final Object thepreviewImage) {
        this.imageData = thepreviewImage;
    }
    
}
