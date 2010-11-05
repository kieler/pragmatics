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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;

/**
 * A layout configuration for extension point configurations and user preferences.
 *
 * @author msp
 */
public class EclipseLayoutConfig extends DefaultLayoutConfig {

    /**
     * Retrieves a layout option from the given edit part by using the layout inspector
     * associated with the edit part type.
     * 
     * @param editPart an edit part
     * @param optionId layout option identifier
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final EditPart editPart, final String optionId) {
        ILayoutInspector inspector = EclipseLayoutServices.getInstance().getInspector(editPart);
        if (inspector != null) {
            return getOption(inspector.getFocusPart(), inspector.getFocusModel(), optionId);
        }
        return null;
    }
    
    /**
     * Retrieves a layout option for the given edit part and model element by querying the option
     * for the edit part's class name and its domain model name. 
     * 
     * @param editPart an edit part
     * @param modelElement the corresponding model element
     * @param optionId layout option identifier
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final EditPart editPart, final EObject modelElement,
            final String optionId) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        String clazzName = editPart == null ? null : editPart.getClass().getName();
        Object value = layoutServices.getOption(clazzName, optionId);
        if (value != null) {
            return value;
        } else {
            clazzName = modelElement == null ? null : modelElement.eClass().getInstanceTypeName();
            return layoutServices.getOption(clazzName, optionId);
        }    
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
    
    /**
     * Create a stand-alone Eclipse layout configuration.
     */
    public EclipseLayoutConfig() {
    }
    
    /**
     * Create an Eclipse layout configuration embedding an external configuration.
     * 
     * @param theexternalConfig an external layout configuration
     */
    public EclipseLayoutConfig(final ILayoutConfig theexternalConfig) {
        this.externalConfig = theexternalConfig;
    }
    
    /**
     * Set the edit part or domain model element as focus for this layout configuration.
     * This can be done without initializing the layout configuration in order to use
     * {@link #getAllProperties()} efficiently, since the same configuration instance can
     * be reused multiple times.
     * 
     * @param element an {@link EditPart} or {@link EObject}
     */
    @Override
    public void setFocus(final Object element) {
        if (element instanceof EditPart) {
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
     * Initialize the configuration with a layout hint and an edit part for the
     * content or the container of the selected element.
     * 
     * @param targetType type of the selected element (parent, node, edge, port, etc.)
     * @param editPart the edit part
     * @param theLayoutHint a layout hint, or {@code null}
     */
    public final void initialize(final LayoutOptionData.Target targetType,
            final EditPart editPart, final String theLayoutHint) {
        String diagramType = (String) getOption(editPart, LayoutOptions.DIAGRAM_TYPE_ID);
        if (targetType == LayoutOptionData.Target.PARENTS) {
            contentDiagramType = diagramType;
        } else {
            containerDiagramType = diagramType;
        }
        String layoutHint = theLayoutHint;
        if (layoutHint == null) {
            layoutHint = (String) getOption(editPart, LayoutOptions.LAYOUTER_HINT_ID);
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
        result = getOption(focusEditPart, modelElement, optionData.getId());
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
            return Boolean.valueOf(contentDiagramType == null);
        } else if (LayoutOptions.PORT_CONSTRAINTS_ID.equals(optionData.getId())) {
            if (contentDiagramType != null) {
                return PortConstraints.FREE.ordinal();
            } else {
                return PortConstraints.FIXED_POS.ordinal();
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

        // get default layout options for the diagram type
        String diagramType = (String) getOption(focusEditPart, modelElement,
                LayoutOptions.DIAGRAM_TYPE_ID);
        if (diagramType != null) {
            for (Entry<String, Object> entry : layoutServices.getOptions(diagramType).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getLayoutOptionData(entry.getKey());
                    options.put(optionData, entry.getValue());
                }
            }
        }
        
        // get default layout options for the domain model element
        if (modelElement != null) {
            String clazzName = modelElement.eClass().getInstanceTypeName();
            for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getLayoutOptionData(entry.getKey());
                    options.put(optionData, entry.getValue());
                }
            }
        }
        
        // get default layout options for the edit part
        if (focusEditPart != null) {
            String clazzName = focusEditPart.getClass().getName();
            for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = layoutServices.getLayoutOptionData(entry.getKey());
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
