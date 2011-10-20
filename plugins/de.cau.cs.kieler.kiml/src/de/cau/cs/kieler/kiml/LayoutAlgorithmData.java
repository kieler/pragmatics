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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.alg.InstancePool;

/**
 * Data type used to store information for a layout algorithm.
 * 
 * @kieler.rating 2011-02-01 yellow
 *     reviewed by cmot, soh
 * @author msp
 */
public class LayoutAlgorithmData implements ILayoutData {

    /** The minimal allowed priority value. Values less or equal to this value
     *  are treated as 'not supported'. */
    public static final int MIN_PRIORITY = Integer.MIN_VALUE >> 2;
    /** default name for layout algorithms for which no name is given. */
    public static final String DEFAULT_LAYOUTER_NAME = "<Unnamed Layouter>";

    /** internal data type for storage of supported diagram information. */
    private static final class SupportedDiagram {
        /** identifier of the diagram type. */
        private String type;
        /** associated priority value, must be greater than MIN_PRIORITY. */
        private int priority = 0;
        
        private SupportedDiagram() {
        }
    }
    
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
    private Map<LayoutOptionData<?>, Object> knownOptions = Maps.newHashMap();
    /** list of supported diagrams. */
    private List<SupportedDiagram> supportedDiagrams = new LinkedList<SupportedDiagram>();
    
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
     * @return the associated default value, or {@code null} if there is none
     */
    public Object getDefaultValue(final LayoutOptionData<?> optionData) {
        return knownOptions.get(optionData);
    }
    
    /**
     * Sets support for the given diagram type. If the priority is less or
     * equal to {@link MIN_PRIORITY}, the type is treated as not supported.
     * 
     * @param diagramType identifier of diagram type
     * @param priority priority value, or {@code MIN_PRIORITY} if the diagram type
     *     is not supported
     */
    public void setDiagramSupport(final String diagramType, final int priority) {
        if (priority > MIN_PRIORITY) {
            SupportedDiagram supportedDiagram0 = null;
            for (SupportedDiagram supportedDiagram1 : supportedDiagrams) {
                if (diagramType.equals(supportedDiagram1.type)) {
                    supportedDiagram0 = supportedDiagram1;
                    break;
                }
            }
            if (supportedDiagram0 == null) {
                supportedDiagram0 = new SupportedDiagram();
                supportedDiagram0.type = diagramType;
                supportedDiagrams.add(supportedDiagram0);
            }
            supportedDiagram0.priority = priority;
        } else {
            ListIterator<SupportedDiagram> suppdIter = supportedDiagrams.listIterator();
            while (suppdIter.hasNext()) {
                SupportedDiagram supportedDiagram = suppdIter.next();
                if (diagramType.equals(supportedDiagram.type)) {
                    suppdIter.remove();
                    break;
                }
            }
        }
    }
    
    /**
     * Returns the supported priority for the given diagram type. If the
     * type is not supported, {@link MIN_PRIORITY} is returned.
     * 
     * @param diagramType diagram type identifier
     * @return associated priority, or {@code MIN_PRIORITY} if the diagram
     *     type is not supported
     */
    public int getSupportedPriority(final String diagramType) {
        if (diagramType != null) {
            for (SupportedDiagram supportedDiagram : supportedDiagrams) {
                if (diagramType.equals(supportedDiagram.type)) {
                    return supportedDiagram.priority;
                }
            }
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
     * Sets the layout provider that can execute the associated algorithm.
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
