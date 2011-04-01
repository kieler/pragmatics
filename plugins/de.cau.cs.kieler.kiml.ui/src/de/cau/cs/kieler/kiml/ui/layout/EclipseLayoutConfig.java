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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.SemanticLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;

/**
 * A layout configuration for extension point configurations and user preferences.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class EclipseLayoutConfig extends DefaultLayoutConfig {

    /**
     * Retrieves a layout option from the given edit part by using the framework bridge
     * associated with the edit part type.
     * 
     * @param editPart an edit part
     * @param optionData layout option data
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final EditPart editPart, final IProperty<?> optionData) {
        IGraphicalFrameworkBridge bridge = EclipseLayoutServices.getInstance()
                .getFrameworkBridge(editPart);
        if (bridge != null) {
            return getOption(bridge.getEditPart(editPart),
                    bridge.getElement(editPart), optionData);
        }
        return null;
    }
    
    /**
     * Retrieves a layout option for the given edit part and model element by querying the option
     * for the edit part's class name and its domain model name. 
     * 
     * @param editPart an edit part
     * @param modelElement the corresponding model element
     * @param property layout option data
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final EditPart editPart, final EObject modelElement,
            final IProperty<?> property) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        String id = (String) property.getIdentifier();
        if (editPart != null) {
            // get option for the edit part class
            String clazzName = editPart.getClass().getName();
            Object value = layoutServices.getOption(clazzName, id);
            if (value != null) {
                return value;
            }
        }
        if (modelElement != null) {
            // get option for the domain model element class
            EClass eclazz = modelElement.eClass();
            Object value = layoutServices.getOption(eclazz, id);
            if (value != null) {
                return value;
            }
            LayoutOptionData<?> optionData = layoutServices.getOptionData(id);
            if (optionData != null) {
                // get option from the semantic layout configuration
                for (SemanticLayoutConfig config : layoutServices.getSemanticConfigs(eclazz)) {
                    config.setFocus(modelElement);
                    value = config.getProperty(optionData);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    /** the edit part for the selected element. */
    private EditPart focusEditPart;
    /** the selected model element. */
    private EObject modelElement;
    /** diagram type for the content of the selected element. */ 
    private String contentDiagramType;
    /** diagram type for the container of the selected element. */
    private String containerDiagramType;
    /** external layout configuration embedded in this one. */
    private ILayoutConfig externalConfig;
    /** indicates whether the selected node contains any children. */
    private Boolean hasChildren;
    /** indicates whether the selected node contains any ports. */
    private Boolean hasPorts;
    
    /**
     * Create a stand-alone Eclipse layout configuration.
     */
    public EclipseLayoutConfig() {
    }
    
    /**
     * Create an Eclipse layout configuration embedding an external configuration.
     * 
     * @param externalConfig an external layout configuration
     */
    public EclipseLayoutConfig(final ILayoutConfig externalConfig) {
        this.externalConfig = externalConfig;
    }
    
    /**
     * Set the edit part or domain model element as focus for this layout configuration.
     * This can be done without initializing the layout configuration in order to use
     * {@link #getAllProperties()} efficiently, since the same configuration instance can
     * be reused multiple times. Passing {@code null} clears the current focus and resets
     * information about children and ports.
     * 
     * @param element an {@link EditPart} or {@link EObject}
     */
    @Override
    public void setFocus(final Object element) {
        if (element == null) {
            this.focusEditPart = null;
            this.modelElement = null;
            this.hasChildren = null;
            this.hasPorts = null;
        } else if (element instanceof EditPart) {
            this.focusEditPart = (EditPart) element;
        } else if (element instanceof EObject) {
            this.modelElement = (EObject) element;
        }
        // pass the new focus element on to the external configuration
        if (externalConfig != null) {
            externalConfig.setFocus(element);
        }
    }
    
    /**
     * Set whether the node in focus has any children.
     * 
     * @param children whether the selected node has children
     */
    public void setChildren(final boolean children) {
        this.hasChildren = children;
    }
    
    /**
     * Set whether the node in focus has any ports.
     * 
     * @param ports whether the selected node has ports
     */
    public void setPorts(final boolean ports) {
        this.hasPorts = ports;
    }
    
    /**
     * Initialize the configuration with a layout hint and an edit part for the
     * content (if target type is {@link LayoutOptionData.Target#PARENTS}) or
     * the container of the selected element.
     * 
     * @param targetType type of the selected element (parent, node, edge, port, etc.)
     * @param editPart the edit part
     * @param theLayoutHint a layout hint, or {@code null}
     */
    public final void initialize(final LayoutOptionData.Target targetType,
            final EditPart editPart, final String theLayoutHint) {
        String diagramType = (String) getOption(editPart, LayoutOptions.DIAGRAM_TYPE);
        if (diagramType == null) {
            diagramType = LayoutServices.DIAGRAM_TYPE_GENERAL;
        }
        if (targetType == LayoutOptionData.Target.PARENTS) {
            contentDiagramType = diagramType;
        } else {
            containerDiagramType = diagramType;
        }
        String layoutHint = theLayoutHint;
        if (layoutHint == null) {
            layoutHint = (String) getOption(editPart, LayoutOptions.ALGORITHM);
        }
        initialize(targetType, layoutHint, diagramType);
    }
    
    /**
     * Retrieve the pre-configured or user defined default value for a layout option.
     * This implementation requires
     * {@link #initialize(de.cau.cs.kieler.kiml.LayoutOptionData.Target, EditPart, String) initialize}
     * and {@link #setFocus(Object) setFocus} to be called first in order to give correct results.
     * 
     * @param <T> type of option
     * @param property a layout option
     * @return the default value for the layout option
     */
    @Override
    public final <T> T getProperty(final IProperty<T> property) {
        T result;
        if (externalConfig != null) {
            result = externalConfig.getProperty(property);
            if (result != null) {
                return result;
            }
        }
        
        if (property instanceof LayoutOptionData<?>) {
            result = doGetProperty((LayoutOptionData<T>) property);
            if (result != null) {
                return result;
            }
        }
        // fall back to the default value for the property
        return super.getProperty(property);
    }
    
    /**
     * Retrieve the pre-configured or user defined default value for a layout option.
     * 
     * @param <T> type of option
     * @param optionData a layout option
     * @return the default value for the layout option
     */
    @SuppressWarnings("unchecked")
    protected <T> T doGetProperty(final LayoutOptionData<T> optionData) {
        EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();
        Object result = null;
        
        // check default value set for the actual edit part or its model element
        result = getOption(focusEditPart, modelElement, optionData);
        if (result != null) {
            return (T) result;
        }
        
        if (optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
            // check default value for the diagram type of the selection's content
            result = layoutServices.getOption(contentDiagramType, optionData.getId());
            if (result != null) {
                return (T) result;
            }
        } else {
            // check default value for the diagram type of the selection's container
            result = layoutServices.getOption(containerDiagramType, optionData.getId());
            if (result != null) {
                return (T) result;
            }
        }
        
        // fall back to dynamic default value of specific options
        result = getDynamicValue(optionData);
        if (result != null) {
            return (T) result;
        }
        
        return null;
    }
    
    /**
     * Determine a dynamic option value for specific options.
     * 
     * @param optionData a layout option
     * @return the dynamic value, or {@code null}
     */
    private Object getDynamicValue(final LayoutOptionData<?> optionData) {
        if (LayoutOptions.FIXED_SIZE_ID.equals(optionData.getId())) {
            return getFixedSizeValue();
        } else if (LayoutOptions.PORT_CONSTRAINTS_ID.equals(optionData.getId())) {
            return getPortConstraintsValue();
        }
        return null;
    }
    
    /**
     * Return the dynamic value for the fixed size option.
     * 
     * @return {@code true} if the selected node has no children, and {@code false} otherwise
     */
    private Boolean getFixedSizeValue() {
        if (hasChildren != null) {
            return Boolean.valueOf(!hasChildren);
        }
        return null;
    }
    
    /**
     * Return the dynamic value for the port constraints option.
     * 
     * @return {@code FIXED_POS} if the selected node has ports and no children,
     *          and {@code FREE} otherwise
     */
    private PortConstraints getPortConstraintsValue() {
        if (hasChildren != null && hasPorts != null) {
            if (!hasChildren && hasPorts) {
                return PortConstraints.FIXED_POS;
            } else {
                return PortConstraints.FREE;
            }
        }
        return null;
    }
    
    /**
     * Returns a map of all options that have pre-configured or user defined default values.
     * 
     * @return a map of layout option values
     */
    @Override
    public final Map<IProperty<?>, Object> getAllProperties() {
        Map<IProperty<?>, Object> options = new LinkedHashMap<IProperty<?>, Object>();
        addProperties(options);
        if (externalConfig != null) {
            options.putAll(externalConfig.getAllProperties());
        }
        return options;
    }
    
    /**
     * Add all options that have pre-configured or user defined default values to the given map.
     * 
     * @param options a map of layout option values
     */
    protected void addProperties(final Map<IProperty<?>, Object> options) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        Object value;
        
        // get dynamic values for specific options
        value = getFixedSizeValue();
        if (value != null) {
            options.put(LayoutOptions.FIXED_SIZE, value);
        }
        value = getPortConstraintsValue();
        if (value != null) {
            options.put(LayoutOptions.PORT_CONSTRAINTS, value);
        }

        // get default layout options for the diagram type
        String diagramType = (String) getOption(focusEditPart, modelElement,
                LayoutOptions.DIAGRAM_TYPE);
        if (diagramType != null) {
            for (Entry<String, Object> entry : layoutServices.getOptions(diagramType).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getOptionData(entry.getKey());
                    options.put(optionData, entry.getValue());
                }
            }
        }
        
        if (modelElement != null) {
            // get layout options from the semantic layout configurations
            for (SemanticLayoutConfig config : layoutServices.getSemanticConfigs(
                    modelElement.eClass())) {
                config.setFocus(modelElement);
                for (LayoutOptionData<?> optionData : config.getOptionData()) {
                    value = config.getProperty(optionData);
                    if (value != null) {
                        options.put(optionData, value);
                    }
                }
            }
            
            // get default layout options for the domain model element
            for (Entry<String, Object> entry : layoutServices.getOptions(
                    modelElement.eClass()).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getOptionData(entry.getKey());
                    options.put(optionData, entry.getValue());
                }
            }
        }
        
        // get default layout options for the edit part
        if (focusEditPart != null) {
            String clazzName = focusEditPart.getClass().getName();
            for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getOptionData(entry.getKey());
                    options.put(optionData, entry.getValue());
                }
            }
        }
    }

    /**
     * Returns the edit part for the selected element.
     * 
     * @return the edit part, or {@code null} if none is selected
     */
    public final EditPart getEditPart() {
        return focusEditPart;
    }
    
    /**
     * Returns the external layout configuration embedded in this configuration, if present.
     * 
     * @return an external layout configuration, or {@code null}
     */
    public final ILayoutConfig getExternalConfig() {
        return externalConfig;
    }
    
}
