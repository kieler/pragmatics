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
package de.cau.cs.kieler.kiml.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutConfigService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.SizeConstraint;

/**
 * A layout configuration for extension point configurations and user preferences.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class EclipseLayoutConfig implements ILayoutConfig {
    
    /** the property for activation of the Eclipse layout config. */
    public static final Property<Boolean> ACTIVATION = new Property<Boolean>(
            "de.cau.cs.kieler.kiml.eclipse", true);
    
    /** the priority for the Eclipse layout configuration. */
    public static final int PRIORITY = 10;
    
    /** the currently tracked diagram editor. */
    public static final IProperty<IWorkbenchPart> WORKBENCH_PART = new Property<IWorkbenchPart>(
            "context.workbenchPart");
    
    /** the aspect ratio of the currently processed diagram viewer. */
    public static final IProperty<Float> ASPECT_RATIO = new Property<Float>(
            "context.aspectRatio");
    
    /**
     * Retrieves a layout option value for the given edit part and model element by querying the option
     * for the edit part's class name and its domain model name.
     * 
     * @param property the layout option data
     * @param diagramPart a diagram part such as an edit part
     * @param modelElement the corresponding domain model element
     * @return the current value for the given option, or {@code null}
     */
    public static Object getValue(final IProperty<?> property, final Object diagramPart,
            final EObject modelElement) {
        LayoutConfigService configService = LayoutConfigService.getInstance();
        String id = property.getId();
        if (diagramPart != null) {
            // get option for the edit part class
            String clazzName = diagramPart.getClass().getName();
            Object value = configService.getOptionValue(clazzName, id);
            if (value != null) {
                return value;
            }
        }
        if (modelElement != null) {
            // get option for the domain model element class
            EClass eclazz = modelElement.eClass();
            Object value = configService.getOptionValue(eclazz, id);
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

        // set diagram type for the content of the main edit part
        String diagramType = context.getProperty(DefaultLayoutConfig.CONTENT_DIAGT);
        if (diagramType == null) {
            diagramType = (String) getValue(LayoutOptions.DIAGRAM_TYPE, diagPart, domainElem);
            context.setProperty(DefaultLayoutConfig.CONTENT_DIAGT, diagramType);
        }
        
        if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {            
            // set layout algorithm or type identifier for the content
            String layoutHint = context.getProperty(DefaultLayoutConfig.CONTENT_HINT);
            if (layoutHint == null) {
                layoutHint = (String) getValue(LayoutOptions.ALGORITHM, diagPart, domainElem);
                if (layoutHint == null) {
                    layoutHint = (String) ExtensionLayoutConfigService.getInstance().getOptionValue(
                            diagramType, LayoutOptions.ALGORITHM.getId());
                }
                context.setProperty(DefaultLayoutConfig.CONTENT_HINT, layoutHint);
            }
            
            // get container edit part and domain model element
            Object containerDiagPart = context.getProperty(LayoutContext.CONTAINER_DIAGRAM_PART);
            EObject containerDomainElem = context.getProperty(LayoutContext.CONTAINER_DOMAIN_MODEL);
            if (containerDomainElem == null && containerDiagPart != null) {
                containerDomainElem = (EObject) LayoutManagersService.getInstance().getAdapter(
                        containerDiagPart, EObject.class);
                context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL, containerDomainElem);
            }
            
            // set diagram type for the container of the main edit part
            String containerDiagramType = context.getProperty(DefaultLayoutConfig.CONTAINER_DIAGT);
            if (containerDiagramType == null) {
                containerDiagramType = (String) getValue(LayoutOptions.DIAGRAM_TYPE,
                        containerDiagPart, containerDomainElem);
                context.setProperty(DefaultLayoutConfig.CONTAINER_DIAGT, containerDiagramType);
            }
            
            // set layout algorithm or type identifier for the container
            String containerLayoutHint = context.getProperty(DefaultLayoutConfig.CONTAINER_HINT);
            if (containerLayoutHint == null) {
                containerLayoutHint = (String) getValue(LayoutOptions.ALGORITHM,
                        containerDiagPart, containerDomainElem);
                if (containerLayoutHint == null) {
                    containerLayoutHint = (String) LayoutConfigService.getInstance().getOptionValue(
                            containerDiagramType, LayoutOptions.ALGORITHM.getId());
                }
                context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerLayoutHint);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        LayoutConfigService configService = LayoutConfigService.getInstance();
        Object result = null;
        
        // check default value set for the actual edit part or its model element
        result = getValue(optionData, context.getProperty(LayoutContext.DIAGRAM_PART),
                context.getProperty(LayoutContext.DOMAIN_MODEL));
        if (result != null) {
            return result;
        }
        
        if (optionData.getTargets().contains(LayoutOptionData.Target.PARENTS)) {
            // check default value for the diagram type of the selection's content
            result = configService.getOptionValue(context.getProperty(
                    DefaultLayoutConfig.CONTENT_DIAGT),
                    optionData.getId());
            if (result != null) {
                return result;
            }
        } else {
            // check default value for the diagram type of the selection's container
            result = configService.getOptionValue(context.getProperty(
                    DefaultLayoutConfig.CONTAINER_DIAGT),
                    optionData.getId());
            if (result != null) {
                return result;
            }
        }
        
        // fall back to dynamic default value of specific options
        if (LayoutOptions.SIZE_CONSTRAINT.equals(optionData)) {
            return getSizeConstraintValue(context);
        } else if (LayoutOptions.PORT_CONSTRAINTS.equals(optionData)) {
            return getPortConstraintsValue(context);
        } else if (LayoutOptions.ASPECT_RATIO.equals(optionData)) {
            return getAspectRatioValue(context);
        }
        
        return null;
    }
    
    /**
     * Return the dynamic value for the size constraint option.
     * 
     * @param context a context for layout configuration
     * @return {@code null} if the selected node has no children, and {@code MINIMUM_SIZE}
     *          / {@code PORTS} otherwise
     */
    private EnumSet<SizeConstraint> getSizeConstraintValue(final LayoutContext context) {
        Set<LayoutOptionData.Target> targets = context.getProperty(LayoutContext.OPT_TARGETS);
        if (targets != null && targets.contains(LayoutOptionData.Target.NODES)) {
            if (!targets.contains(LayoutOptionData.Target.PARENTS)) {
                return SizeConstraint.fixed();
            }
            Boolean hasPorts = context.getProperty(DefaultLayoutConfig.HAS_PORTS);
            if (hasPorts != null && hasPorts) {
                return SizeConstraint.minimumSizeWithPorts();
            }
            return EnumSet.of(SizeConstraint.MINIMUM_SIZE);
        }
        return null; // default value: SizeConstraint.fixed()
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
        if (targets != null && targets.contains(LayoutOptionData.Target.NODES) && hasPorts != null) {
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
    public Collection<IProperty<?>> getAffectedOptions(final LayoutContext context) {
        List<IProperty<?>> options = new LinkedList<IProperty<?>>();
        
        // specific options with dynamic values
        Set<LayoutOptionData.Target> targets = context.getProperty(LayoutContext.OPT_TARGETS);
        if (targets != null && targets.contains(LayoutOptionData.Target.NODES)) {
            options.add(LayoutOptions.SIZE_CONSTRAINT);
            Boolean hasPorts = context.getProperty(DefaultLayoutConfig.HAS_PORTS);
            if (hasPorts != null) {
                options.add(LayoutOptions.PORT_CONSTRAINTS);
            }
        }
        Float aspectRatio = context.getProperty(ASPECT_RATIO);
        if (aspectRatio != null && aspectRatio > 0) {
            options.add(LayoutOptions.ASPECT_RATIO);
        }

        LayoutConfigService configService = LayoutConfigService.getInstance();
        LayoutDataService dataService = LayoutDataService.getInstance();
        Object diagPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        EObject modelElement = context.getProperty(LayoutContext.DOMAIN_MODEL);

        // get default layout options for the diagram type
        String diagramType = context.getProperty(DefaultLayoutConfig.CONTENT_DIAGT);
        if (diagramType != null) {
            for (Entry<String, Object> entry : configService.getOptionValues(diagramType).entrySet()) {
                if (entry.getValue() != null) {
                    @SuppressWarnings("unchecked")
                    LayoutOptionData<Object> optionData = (LayoutOptionData<Object>)
                            dataService.getOptionData(entry.getKey());
                    if (optionData != null) {
                        options.add(optionData);
                    }
                }
            }
        }
        
        // get default layout options for the domain model element
        if (modelElement != null) {
            for (Entry<String, Object> entry : configService.getOptionValues(
                    modelElement.eClass()).entrySet()) {
                if (entry.getValue() != null) {
                    @SuppressWarnings("unchecked")
                    LayoutOptionData<Object> optionData = (LayoutOptionData<Object>)
                            dataService.getOptionData(entry.getKey());
                    if (optionData != null) {
                        options.add(optionData);
                    }
                }
            }
        }
        
        // get default layout options for the edit part
        if (diagPart != null) {
            String clazzName = diagPart.getClass().getName();
            for (Entry<String, Object> entry : configService.getOptionValues(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    @SuppressWarnings("unchecked")
                    LayoutOptionData<Object> optionData = (LayoutOptionData<Object>)
                            dataService.getOptionData(entry.getKey());
                    if (optionData != null) {
                        options.add(optionData);
                    }
                }
            }
        }
        return options;
    }
    
}