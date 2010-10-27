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
            return getOption(inspector, optionId);
        }
        return null;
    }
    
    /**
     * Retrieves a layout option from the given layout inspector by querying the option
     * for the edit part's class name and its domain model name. 
     * 
     * @param inspector a layout inspector for an edit part
     * @param optionId layout option identifier
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final ILayoutInspector inspector, final String optionId) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        EditPart editPart = inspector.getFocusPart();
        String clazzName = editPart == null ? null : editPart.getClass().getName();
        Object value = layoutServices.getOption(clazzName, optionId);
        if (value != null) {
            return value;
        } else {
            EObject model = inspector.getFocusModel();
            clazzName = model == null ? null : model.eClass().getInstanceTypeName();
            return layoutServices.getOption(clazzName, optionId);
        }        
    }
    
    /**
     * Determines whether the given edit part should not be layouted.
     * 
     * @param editPart an edit part
     * @return true if no layout should be performed for the edit part
     */
    public static boolean isNoLayout(final EditPart editPart) {
        Boolean result = (Boolean) getOption(editPart, LayoutOptions.NO_LAYOUT_ID);
        if (result != null) {
            return result;
        } else {
            return false;
        }
    }

    /** the edit part that contains the content of the selected element. */
    private EditPart containmentEditPart;
    /** the edit part that contains the selected element. */
    private EditPart containerEditPart;
    
    /**
     * Initialize the configuration with a layout hint and an edit part for the
     * <i>content</i> of the selected element.
     * 
     * @param thecontainmentEditPart the edit part that holds the content of the selected element 
     * @param contentLayoutHint layout hint for the content
     */
    public final void initialize(final EditPart thecontainmentEditPart,
            final String contentLayoutHint) {
        this.containmentEditPart = thecontainmentEditPart;
        String contentDiagramType = (String) getOption(thecontainmentEditPart,
                LayoutOptions.DIAGRAM_TYPE_ID);
        String layoutHint = contentLayoutHint;
        if (layoutHint == null) {
            layoutHint = (String) getOption(thecontainmentEditPart, LayoutOptions.LAYOUTER_HINT_ID);
        }
        initialize(layoutHint, contentDiagramType);
    }
    
    /**
     * Initialize the configuration with a layout hint and an edit part for the
     * <i>container</i> of the selected element.
     * 
     * @param targetType type of the selected element (node, edge, port, etc.)
     * @param thecontainerEditPart the edit part that contains the selected element
     * @param containerLayoutHint layout hint for the container
     */
    public final void initialize(final LayoutOptionData.Target targetType,
            final EditPart thecontainerEditPart, final String containerLayoutHint) {
        this.containerEditPart = thecontainerEditPart;
        String containerDiagramType = (String) getOption(thecontainerEditPart,
                LayoutOptions.DIAGRAM_TYPE_ID);
        String layoutHint = containerLayoutHint;
        if (layoutHint == null) {
            layoutHint = (String) getOption(thecontainerEditPart, LayoutOptions.LAYOUTER_HINT_ID);
        }
        initialize(targetType, layoutHint, containerDiagramType);
    }
    
    /**
     * Retrieve the pre-configured or user defined default value for a layout option.
     * 
     * @param <T> type of option
     * @param property a layout option
     * @return the default value for the layout option
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getProperty(final IProperty<T> property) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<T> optionData = (LayoutOptionData<T>) property;
            EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();
            Object result = null;
            
            if (containmentEditPart != null) {
                // check default value set for the actual edit part or its model element
                result = getOption(containmentEditPart, optionData.getId());
                if (result != null) {
                    return (T) result;
                }
            }
    
            if (containerEditPart != null) {
                // check default option of the diagram type
                String diagramType = (String) getOption(containerEditPart,
                        LayoutOptions.DIAGRAM_TYPE_ID);
                result = layoutServices.getOption(diagramType, optionData.getId());
                if (result != null) {
                    return (T) result;
                }
        
                // check default value for the container edit part
                result = getOption(containerEditPart, optionData.getId());
                if (result != null) {
                    return (T) result;
                }
            }
            
            // fall back to dynamic default value of specific options
            result = getDynamicValue(optionData);
            if (result != null) {
                return (T) result;
            }
        }
        // fall back to the default value for the property
        return super.getProperty(property);
    }
    
    /**
     * Determine a dynamic option value for specific options.
     * 
     * @param optionData a layout option
     * @return the dynamic value, or {@code null}
     */
    private Object getDynamicValue(final LayoutOptionData<?> optionData) {
        if (LayoutOptions.FIXED_SIZE_ID.equals(optionData.getId())) {
            return Boolean.valueOf(containmentEditPart == null);
        } else if (LayoutOptions.PORT_CONSTRAINTS_ID.equals(optionData.getId())) {
            if (containmentEditPart != null) {
                return PortConstraints.FREE.ordinal();
            } else {
                return PortConstraints.FIXED_POS.ordinal();
            }
        }
        return null;
    }
    
    /**
     * Compute a map with all pre-configured or user-defined layout options. This does
     * not include the default values of the layout providers or layout options.
     * 
     * @param editPart an edit part
     * @param element the corresponding model element
     * @return a map of layout option identifiers to their values
     */
    public Map<String, Object> getDefaultOptions(final EditPart editPart, final EObject element) {
        LayoutServices layoutServices = LayoutServices.getInstance();

        // get default layout options for the diagram type
        String diagramType = (String) getOption(editPart, LayoutOptions.DIAGRAM_TYPE_ID);
        Map<String, Object> options = new LinkedHashMap<String, Object>(
                layoutServices.getOptions(diagramType));
        
        // get default layout options for the domain model element
        if (element != null) {
            String clazzName = element.eClass().getInstanceTypeName();
            for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    options.put(entry.getKey(), entry.getValue());
                }
            }
        }
        
        // get default layout options for the edit part
        String clazzName = editPart.getClass().getName();
        for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
            if (entry.getValue() != null) {
                options.put(entry.getKey(), entry.getValue());
            }
        }
        
        return options;
    }

    /**
     * Returns the edit part that holds the content of the selected element.
     * 
     * @return the containment edit part, or {@code null}
     */
    public EditPart getContainmentEditPart() {
        return containmentEditPart;
    }

    /**
     * Returns the edit part that contains the selected element.
     * 
     * @return the container edit part, or {@code null}
     */
    public EditPart getContainerEditPart() {
        return containerEditPart;
    }
    
}
