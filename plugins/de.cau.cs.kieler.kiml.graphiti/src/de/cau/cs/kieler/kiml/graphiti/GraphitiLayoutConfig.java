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
package de.cau.cs.kieler.kiml.graphiti;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.util.PictogramsSwitch;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.EclipseLayoutConfig;

/**
 * Layout option configuration for Graphiti.
 * 
 * @author soh
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
@SuppressWarnings("restriction")
public class GraphitiLayoutConfig implements IMutableLayoutConfig {
    
    /** the priority for the Graphiti layout configuration. */
    public static final int PRIORITY = 30;

    /** Prefix for all layout options. */
    public static final String PREFIX = "layout:";
    /** Prefix for diagram defaults stored in the top-level edit part. */
    public static final String DEF_PREFIX = "defaultLayout:";
    
    /** the pictogram element for the graph element in the context. */
    public static final IProperty<PictogramElement> PICTO_ELEM
            = new de.cau.cs.kieler.core.properties.Property<PictogramElement>(
                    "context.pictogramElement");
    
    /** the layout manager for which this configurator was created. */
    private GraphitiDiagramLayoutManager layoutManager;

    /**
     * Create a Graphiti layout configurator for the given layout manager.
     * 
     * @param layoutManager a Graphiti layout manager
     */
    public GraphitiLayoutConfig(final GraphitiDiagramLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }
    
    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    private static final float ASPECT_RATIO_ROUND = 100;

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        Object diagramPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        PictogramElement pictogramElem = null;
        Diagram diagram = null;
        if (diagramPart instanceof IPictogramElementEditPart) {
            IPictogramElementEditPart focusEditPart = (IPictogramElementEditPart) diagramPart;
            pictogramElem = focusEditPart.getPictogramElement();
            diagram = focusEditPart.getConfigurationProvider().getDiagram();
        } else if (diagramPart instanceof PictogramElement) {
            pictogramElem = (PictogramElement) diagramPart;
        }
        if (pictogramElem != null) {
            // add pictogram element and domain model element to the context
            context.setProperty(PICTO_ELEM, pictogramElem);
            if (context.getProperty(LayoutContext.DOMAIN_MODEL) == null
                    && pictogramElem.getLink() != null
                    && pictogramElem.getLink().getBusinessObjects().size() > 0) {
                context.setProperty(LayoutContext.DOMAIN_MODEL,
                        pictogramElem.getLink().getBusinessObjects().get(0));
            }
            
            // determine the target type and container / containment edit parts
            Maybe<PictogramElement> containerPe = Maybe.create();
            Maybe<Boolean> hasPorts = Maybe.create();
            Set<LayoutOptionData.Target> partTargets = findTarget(pictogramElem,
                    containerPe, hasPorts);
            if (partTargets != null) {
                context.setProperty(LayoutContext.OPT_TARGETS, partTargets);
            }
            
            // set whether the selected element is a node that contains ports
            if (hasPorts.get() != null) {
                context.setProperty(DefaultLayoutConfig.HAS_PORTS, hasPorts.get());
            }
            
            // get aspect ratio for the current diagram
            try {
                Control control = null;
                if (diagramPart instanceof EditPart) {
                    control = ((EditPart) diagramPart).getViewer().getControl();
                } else {
                    IWorkbenchPart workbenchPart = context.getProperty(
                            EclipseLayoutConfig.WORKBENCH_PART);
                    if (workbenchPart instanceof DiagramEditor) {
                        control = ((DiagramEditor) workbenchPart).getGraphicalViewer().getControl();
                    }
                }
                if (control != null) {
                    Point size = control.getSize();
                    if (size.x > 0 && size.y > 0) {
                        context.setProperty(EclipseLayoutConfig.ASPECT_RATIO,
                                Math.round(ASPECT_RATIO_ROUND * (float) size.x / size.y)
                                / ASPECT_RATIO_ROUND);
                    }
                }
            } catch (SWTException exception) {
                // ignore exception
            }
            
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                // find diagram pictogram element
                if (diagram == null) {
                    PictogramElement pe = pictogramElem;
                    while (pe != null && !(pe instanceof Diagram)) {
                        pe = (PictogramElement) pe.eContainer();
                    }
                    if (pe != null) {
                        diagram = (Diagram) pe;
                    }
                }
                
                @SuppressWarnings("unchecked")
                LayoutOptionData<String> algorithmOptionData = (LayoutOptionData<String>)
                        LayoutDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM.getId());
                if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null
                        && algorithmOptionData != null) {
                    // get a layout hint for the content of the focused pictogram element
                    String contentLayoutHint = getValue(algorithmOptionData, PREFIX, pictogramElem);
                    if (contentLayoutHint == null && diagram != null) {
                        contentLayoutHint = getValue(algorithmOptionData, DEF_PREFIX, diagram);
                    }
                    if (contentLayoutHint != null) {
                        context.setProperty(DefaultLayoutConfig.CONTENT_HINT, contentLayoutHint);
                    }
                }
                
                if (containerPe.get() != null) {
                    if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null
                            && algorithmOptionData != null) {
                        // get a layout hint for the container edit part
                        String containerLayoutHint = getValue(algorithmOptionData, PREFIX,
                                containerPe.get());
                        if (containerLayoutHint == null && diagram != null) {
                            containerLayoutHint = getValue(algorithmOptionData, DEF_PREFIX, diagram);
                        }
                        if (containerLayoutHint != null) {
                            context.setProperty(DefaultLayoutConfig.CONTAINER_HINT,
                                    containerLayoutHint);
                        }
                    }
                    PictogramLink link = containerPe.get().getLink();
                    if (link != null && link.getBusinessObjects().size() > 0) {
                        context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL,
                                link.getBusinessObjects().get(0));
                    }
                }
            }
        }   
    }
    
    /**
     * Determine the type of edit part target for layout options.
     * 
     * @param pe a pictogram element
     * @param containerPe if a container pictogram element is found, this reference parameter
     *         is set to that element
     * @param hasPorts if contained ports are found, this reference parameter is
     *          set to {@code true}
     * @return the layout option targets
     */
    private Set<LayoutOptionData.Target> findTarget(final PictogramElement pe,
            final Maybe<PictogramElement> containerPe, final Maybe<Boolean> hasPorts) {
        PictogramsSwitch<Set<LayoutOptionData.Target>> pictogramsSwitch
                = new PictogramsSwitch<Set<LayoutOptionData.Target>>() {
            public Set<LayoutOptionData.Target> caseDiagram(final Diagram diagram) {
                containerPe.set(diagram);
                return EnumSet.of(LayoutOptionData.Target.PARENTS);
            }
            public Set<LayoutOptionData.Target> caseShape(final Shape shape) {
                containerPe.set(shape.getContainer());
                Set<LayoutOptionData.Target> targets = EnumSet.of(LayoutOptionData.Target.NODES);
                if (pe instanceof ContainerShape) {
                    // the same check for relevant children as in the layout manager must be made here
                    for (Shape child : ((ContainerShape) pe).getChildren()) {
                        if (layoutManager.isNodeShape(child)) {
                            targets.add(LayoutOptionData.Target.PARENTS);
                            break;
                        }
                    }
                }
                // the same check for ports as in the layout manager must be made here
                for (Anchor anchor : ((Shape) pe).getAnchors()) {
                    if (layoutManager.isPortAnchor(anchor)) {
                        hasPorts.set(Boolean.TRUE);
                        break;
                    }
                }
                return targets;
            }
            public Set<LayoutOptionData.Target> caseConnection(final Connection connection) {
                AnchorContainer ac = connection.getStart().getParent();
                if (ac instanceof Shape) {
                    containerPe.set(((Shape) ac).getContainer());
                    return EnumSet.of(LayoutOptionData.Target.EDGES);
                }
                return null;
            }
            public Set<LayoutOptionData.Target> caseAnchor(final Anchor anchor) {
                AnchorContainer ac = anchor.getParent();
                if (ac instanceof Shape) {
                    containerPe.set(((Shape) ac).getContainer());
                    return EnumSet.of(LayoutOptionData.Target.PORTS);
                }
                return null;
            }
        };
        return pictogramsSwitch.doSwitch(pe);
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        PictogramElement pe = context.getProperty(PICTO_ELEM);
        if (pe != null) {
            Object result = getValue(optionData, PREFIX, pe);
            if (result != null) {
                return result;
            }
            
            // check default option of diagram
            PictogramElement parent = pe;
            while (parent.eContainer() instanceof PictogramElement) {
                parent = (PictogramElement) parent.eContainer();
            }
            return getValue(optionData, DEF_PREFIX, parent);
        }
        return null;
    }

    /**
     * Get a property from the given pictogram element.
     * 
     * @param <T>
     *            the type of the value of the option
     * @param optionData
     *            the layout option
     * @param prefix
     *            the prefix for the property key
     * @param pictogramElement
     *            a pictogram element
     * @return the value of the option, or {@code null}
     */
    private <T> T getValue(final LayoutOptionData<T> optionData, final String prefix,
            final PictogramElement pictogramElement) {
        String optionKey = prefix + optionData.getId();
        for (Property p : pictogramElement.getProperties()) {
            if (optionKey.equals(p.getKey())) {
                T result = optionData.parseValue(p.getValue());
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<IProperty<?>> getAffectedOptions(final LayoutContext context) {
        PictogramElement pe = context.getProperty(PICTO_ELEM);
        List<IProperty<?>> options = new LinkedList<IProperty<?>>();
        if (pe != null) {
            // add user defined global layout options
            PictogramElement parent = pe;
            while (parent.eContainer() instanceof PictogramElement) {
                parent = (PictogramElement) parent.eContainer();
            }
            transferValues(options, DEF_PREFIX, parent);
            // add user defined local layout options
            transferValues(options, PREFIX, pe);
        }
        return options;
    }

    /**
     * Add the options from the list of properties to the given list.
     * 
     * @param options list to which options are added
     * @param prefix the prefix for the property key
     * @param pe a pictogram element
     */
    private void transferValues(final List<IProperty<?>> options, final String prefix,
            final PictogramElement pe) {
        LayoutDataService layoutServices = LayoutDataService.getInstance();
        for (Property prop : pe.getProperties()) {
            String key = prop.getKey();
            if (key != null && key.startsWith(prefix)) {
                @SuppressWarnings("unchecked")
                LayoutOptionData<Object> optionData = (LayoutOptionData<Object>)
                        layoutServices.getOptionData(key.substring(prefix.length()));
                if (optionData != null) {
                    options.add(optionData);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final LayoutOptionData<?> optionData, final LayoutContext context,
            final Object value) {
        PictogramElement pe = context.getProperty(PICTO_ELEM);
        if (pe != null) {
            if (context.getProperty(IMutableLayoutConfig.OPT_RECURSIVE)) {
                if (value != null) {
                    removeValue(optionData, PREFIX, pe, true);
                }
                setValue(optionData, value, DEF_PREFIX, pe);
            } else {
                setValue(optionData, value, PREFIX, pe);
            }
        }  
    }

    /**
     * Set the option for the given pictogram element. Adds a new property to the property list
     * unless the given key already exists.
     * 
     * @param optionData layout option data
     * @param value the value
     * @param prefix the prefix for the property key
     * @param pictogramElement the pictogram element
     */
    private void setValue(final LayoutOptionData<?> optionData, final Object value,
            final String prefix, final PictogramElement pictogramElement) {
        if (value == null) {
            removeValue(optionData, prefix, pictogramElement, false);
        } else {
            String optionKey = prefix + optionData.getId();
            for (Property p : pictogramElement.getProperties()) {
                if (optionKey.equals(p.getKey())) {
                    p.setValue(value.toString());
                    return;
                }
            }

            Property p = MmFactory.eINSTANCE.createProperty();
            p.setKey(prefix + optionData.getId());
            p.setValue(value.toString());
            pictogramElement.getProperties().add(p);
        }
    }

    /**
     * Remove an option from the given pictogram element.
     * 
     * @param optionData layout option data
     * @param prefix the prefix for the property key
     * @param pictogramElement the pictogram element
     * @param recursive whether options should also be removed from children
     */
    private void removeValue(final LayoutOptionData<?> optionData, final String prefix,
            final PictogramElement pictogramElement, final boolean recursive) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();
        String optionKey = prefix + optionData.getId();
        while (iter.hasNext()) {
            Property p = iter.next();
            if (optionKey.equals(p.getKey())) {
                iter.remove();
            }
        }
        
        if (recursive) {
            if (pictogramElement instanceof ContainerShape) {
                ContainerShape cs = (ContainerShape) pictogramElement;
                for (Shape shape : cs.getChildren()) {
                    removeValue(optionData, prefix, shape, true);
                }
                for (Anchor anchor : cs.getAnchors()) {
                    removeValue(optionData, prefix, anchor, true);
                }
                if (cs instanceof Diagram) {
                    for (Connection connection : ((Diagram) cs).getConnections()) {
                        removeValue(optionData, prefix, connection, true);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clearValues(final LayoutContext context) {
        PictogramElement pe = context.getProperty(PICTO_ELEM);
        if (pe != null) {
            boolean recursive = context.getProperty(IMutableLayoutConfig.OPT_RECURSIVE);
            clearValues(pe, recursive);
        }
    }

    /**
     * Clear all layout options from the given pictogram element.
     * 
     * @param pictogramElement a pictogram element
     * @param recursive whether the children should also be processed
     */
    private static void clearValues(final PictogramElement pictogramElement,
            final boolean recursive) {
        Iterator<Property> iter = pictogramElement.getProperties().iterator();
        while (iter.hasNext()) {
            Property p = iter.next();
            String key = p.getKey() == null ? "" : p.getKey();
            if (key.startsWith(PREFIX) || key.startsWith(DEF_PREFIX)) {
                iter.remove();
            }
        }

        if (recursive) {
            if (pictogramElement instanceof ContainerShape) {
                ContainerShape cs = (ContainerShape) pictogramElement;
                for (Shape shape : cs.getChildren()) {
                    clearValues(shape, true);
                }
                for (Anchor anchor : cs.getAnchors()) {
                    clearValues(anchor, true);
                }
                if (cs instanceof Diagram) {
                    for (Connection connection : ((Diagram) cs).getConnections()) {
                        clearValues(connection, true);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSet(final LayoutOptionData<?> optionData, final LayoutContext context) {
        PictogramElement pe = context.getProperty(PICTO_ELEM);
        if (pe != null) {
            Object result = getValue(optionData, PREFIX, pe);
            return result != null;
        }
        return false;
    }

}
