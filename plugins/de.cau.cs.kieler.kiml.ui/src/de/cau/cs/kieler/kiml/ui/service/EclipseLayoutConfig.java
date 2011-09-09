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
package de.cau.cs.kieler.kiml.ui.service;

import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;

/**
 * A layout configuration for extension point configurations and user preferences.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class EclipseLayoutConfig implements ILayoutConfig {
    
    /** the priority for the Eclipse layout configuration. */
    public static final int PRIORITY = 10;
    
    /** the currently tracked diagram editor. */
    public static final IProperty<IWorkbenchPart> WORKBENCH_PART = new Property<IWorkbenchPart>(
            "context.workbenchPart");
    
    /** the aspect ratio of the currently processed diagram viewer. */
    public static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            "context.aspectRatio");

    /**
     * Retrieves a layout option from the given edit part by using the framework bridge
     * associated with the edit part type.
     * 
     * @param diagramPart a diagram part such as an edit part
     * @param optionData layout option data
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final Object diagramPart, final IProperty<?> optionData) {
        IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.
                getInstance().getBridge(diagramPart);
        if (bridge != null) {
            return getOption(bridge.getEditPart(diagramPart),
                    bridge.getElement(diagramPart), optionData);
        }
        return null;
    }
    
    /**
     * Retrieves a layout option for the given edit part and model element by querying the option
     * for the edit part's class name and its domain model name. 
     * 
     * @param diagramPart a diagram part such as an edit part
     * @param modelElement the corresponding model element
     * @param property layout option data
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final Object diagramPart, final EObject modelElement,
            final IProperty<?> property) {
        EclipseLayoutInfoService infoService = EclipseLayoutInfoService.getInstance();
        String id = property.getId();
        if (diagramPart != null) {
            // get option for the edit part class
            String clazzName = diagramPart.getClass().getName();
            Object value = infoService.getOptionValue(clazzName, id);
            if (value != null) {
                return value;
            }
        }
        if (modelElement != null) {
            // get option for the domain model element class
            EClass eclazz = modelElement.eClass();
            Object value = infoService.getOptionValue(eclazz, id);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        // get main edit part and domain model element
        Object diagPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        EObject domainElem = context.getProperty(LayoutContext.DOMAIN_MODEL);
        /* Don't try to find the domain model element here, since another layout config
         * could have disabled it on purpose.
         */

        // set diagram type for the content of the main edit part
        String diagramType = (String) getOption(diagPart, domainElem, LayoutOptions.DIAGRAM_TYPE);
        if (diagramType != null) {
            context.setProperty(DefaultLayoutConfig.CONTENT_DIAGT, diagramType);
        }
        
        if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {            
            // set layout algorithm or type identifier for the content
            String layoutHint = context.getProperty(DefaultLayoutConfig.CONTENT_HINT);
            if (layoutHint == null) {
                layoutHint = (String) getOption(diagPart, domainElem, LayoutOptions.ALGORITHM);
                context.setProperty(DefaultLayoutConfig.CONTENT_HINT, layoutHint);
            }
            
            // get container edit part and domain model element
            Object containerDiagPart = context.getProperty(LayoutContext.CONTAINER_DIAGRAM_PART);
            EObject containerDomainElem = context.getProperty(LayoutContext.CONTAINER_DOMAIN_MODEL);
            if (containerDomainElem == null && containerDiagPart != null) {
                IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance()
                        .getBridge(containerDiagPart);
                containerDomainElem = bridge.getElement(containerDiagPart);
                context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL, containerDomainElem);
            }
            
            // set diagram type for the container of the main edit part
            String containerDiagramType = (String) getOption(containerDiagPart, containerDomainElem,
                    LayoutOptions.DIAGRAM_TYPE);
            if (containerDiagramType != null) {
                context.setProperty(DefaultLayoutConfig.CONTAINER_DIAGT, containerDiagramType);
            }
            
            // set layout algorithm or type identifier for the container
            String containerLayoutHint = context.getProperty(DefaultLayoutConfig.CONTAINER_HINT);
            if (containerLayoutHint == null) {
                containerLayoutHint = (String) getOption(containerDiagPart, containerDomainElem,
                        LayoutOptions.ALGORITHM);
                context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerLayoutHint);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        EclipseLayoutInfoService infoService = EclipseLayoutInfoService.getInstance();
        Object result = null;
        
        // check default value set for the actual edit part or its model element
        result = getOption(context.getProperty(LayoutContext.DIAGRAM_PART),
                context.getProperty(LayoutContext.DOMAIN_MODEL), optionData);
        if (result != null) {
            return result;
        }
        
        if (optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
            // check default value for the diagram type of the selection's content
            result = infoService.getOptionValue(context.getProperty(
                    DefaultLayoutConfig.CONTENT_DIAGT),
                    optionData.getId());
            if (result != null) {
                return result;
            }
        } else {
            // check default value for the diagram type of the selection's container
            result = infoService.getOptionValue(context.getProperty(
                    DefaultLayoutConfig.CONTAINER_DIAGT),
                    optionData.getId());
            if (result != null) {
                return result;
            }
        }
        
        // fall back to dynamic default value of specific options
        if (LayoutOptions.FIXED_SIZE_ID.equals(optionData.getId())) {
            return getFixedSizeValue(context);
        } else if (LayoutOptions.PORT_CONSTRAINTS_ID.equals(optionData.getId())) {
            return getPortConstraintsValue(context);
        } else if (LayoutOptions.ASPECT_RATIO_ID.equals(optionData.getId())) {
            return getAspectRatioValue(context);
        }
        
        return null;
    }
    
    /**
     * Return the dynamic value for the fixed size option.
     * 
     * @param context a context for layout configuration
     * @return {@code true} if the selected node has no children, and {@code false} otherwise
     */
    private Boolean getFixedSizeValue(final LayoutContext context) {
        Set<LayoutOptionData.Target> targets = context.getProperty(LayoutContext.OPT_TARGETS);
        if (targets != null) {
            return !targets.contains(LayoutOptionData.Target.PARENTS);
        }
        return null;
    }
    
    /**
     * Return the dynamic value for the port constraints option.
     * 
     * @param context a context for layout configuration
     * @return {@code FIXED_POS} if the selected node has ports and no children,
     *          and {@code FREE} otherwise
     */
    private PortConstraints getPortConstraintsValue(final LayoutContext context) {
        Set<LayoutOptionData.Target> targets = context.getProperty(LayoutContext.OPT_TARGETS);
        Boolean hasPorts = context.getProperty(DefaultLayoutConfig.HAS_PORTS);
        if (targets != null && hasPorts != null) {
            if (!targets.contains(LayoutOptionData.Target.PARENTS) && hasPorts) {
                return PortConstraints.FIXED_POS;
            } else {
                return PortConstraints.FREE;
            }
        }
        return null;
    }
    
    /**
     * Return the dynamic value for the aspect ratio option.
     * 
     * @param context a context for layout configuration
     * @return the aspect ratio, if it has been configured in the context
     */
    private Float getAspectRatioValue(final LayoutContext context) {
        Float aspectRatio = context.getProperty(ASPECT_RATIO);
        if (aspectRatio != null && aspectRatio > 0) {
             return aspectRatio;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        EclipseLayoutInfoService infoService = EclipseLayoutInfoService.getInstance();
        LayoutDataService dataService = LayoutDataService.getInstance();
        Object value;
        
        // get dynamic values for specific options
        value = getFixedSizeValue(context);
        if (value != null) {
            graphData.setProperty(LayoutOptions.FIXED_SIZE, value);
        }
        value = getPortConstraintsValue(context);
        if (value != null) {
            graphData.setProperty(LayoutOptions.PORT_CONSTRAINTS, value);
        }
        value = getAspectRatioValue(context);
        if (value != null) {
            graphData.setProperty(LayoutOptions.ASPECT_RATIO, value);
        }

        Object diagPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        EObject modelElement = context.getProperty(LayoutContext.DOMAIN_MODEL);

        // get default layout options for the diagram type
        String diagramType = context.getProperty(DefaultLayoutConfig.CONTENT_DIAGT);
        if (diagramType != null) {
            for (Entry<String, Object> entry : infoService.getOptionValues(diagramType).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = dataService.getOptionData(entry.getKey());
                    graphData.setProperty(optionData, entry.getValue());
                }
            }
        }
        
        // get default layout options for the domain model element
        if (modelElement != null) {
            for (Entry<String, Object> entry : infoService.getOptionValues(
                    modelElement.eClass()).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = dataService.getOptionData(entry.getKey());
                    graphData.setProperty(optionData, entry.getValue());
                }
            }
        }
        
        // get default layout options for the edit part
        if (diagPart != null) {
            String clazzName = diagPart.getClass().getName();
            for (Entry<String, Object> entry : infoService.getOptionValues(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    LayoutOptionData<?> optionData = dataService.getOptionData(entry.getKey());
                    graphData.setProperty(optionData, entry.getValue());
                }
            }
        }
    }
    
}
