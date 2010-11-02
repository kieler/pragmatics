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

    /** the edit part for the selected element. */
    private EditPart focusEditPart;
    /** layout inspector for the selected element. */
    private ILayoutInspector layoutInspector;
    /** diagram type for the content of the selected element. */ 
    private String contentDiagramType;
    /** diagram type for the container of the selected element. */
    private String containerDiagramType;
    
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
            
            if (focusEditPart != null) {
                // check default value set for the actual edit part or its model element
                result = getOption(layoutInspector, optionData.getId());
                if (result != null) {
                    return (T) result;
                }
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
            return Boolean.valueOf(focusEditPart == null);
        } else if (LayoutOptions.PORT_CONSTRAINTS_ID.equals(optionData.getId())) {
            if (focusEditPart != null) {
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
     * @param inspector a layout inspector for an edit part
     * @return a map of layout option identifiers to their values
     */
    public Map<String, Object> getDefaultOptions(final ILayoutInspector inspector) {
        LayoutServices layoutServices = LayoutServices.getInstance();

        // get default layout options for the diagram type
        String diagramType = (String) getOption(inspector,
                LayoutOptions.DIAGRAM_TYPE_ID);
        Map<String, Object> options = new LinkedHashMap<String, Object>(
                layoutServices.getOptions(diagramType));
        
        // get default layout options for the domain model element
        String clazzName = inspector.getFocusModel().eClass().getInstanceTypeName();
        for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
            if (entry.getValue() != null) {
                options.put(entry.getKey(), entry.getValue());
            }
        }
        
        // get default layout options for the edit part
        clazzName = inspector.getFocusPart().getClass().getName();
        for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
            if (entry.getValue() != null) {
                options.put(entry.getKey(), entry.getValue());
            }
        }
        
        return options;
    }

    /**
     * Returns the edit part for the selected element.
     * 
     * @return the edit part, or {@code null} if none is selected
     */
    public EditPart getEditPart() {
        return focusEditPart;
    }
    
    /**
     * Sets the edit part for the selected element.
     * 
     * @param editPart the edit part
     */
    public void setEditPart(final EditPart editPart) {
        this.focusEditPart = editPart;
        this.layoutInspector = EclipseLayoutServices.getInstance().getInspector(editPart);
    }
    
}
