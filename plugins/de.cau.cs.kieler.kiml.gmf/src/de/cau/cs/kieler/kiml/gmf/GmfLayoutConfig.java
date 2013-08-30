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
package de.cau.cs.kieler.kiml.gmf;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.StringValueStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;

/**
 * A layout configuration that stores layout options in the notation model of GMF diagrams.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2013-07-01 review KI-38 by cds, uru
 */
public class GmfLayoutConfig implements IMutableLayoutConfig {
    
    /** the priority for the GMF layout configuration. */
    public static final int PRIORITY = 30;
    
    /** Prefix for all layout options. */
    public static final String PREFIX = "layout:";
    /** Prefix for diagram defaults stored in the top-level edit part. */
    public static final String DEF_PREFIX = "defaultLayout:";
    
    /** the notation view for the graph element in focus. */
    public static final IProperty<View> NOTATION_VIEW = new Property<View>("context.notationView");
    
    /**
     * Determines whether the given edit part should not be layouted.
     * 
     * @param editPart an edit part
     * @return true if no layout should be performed for the edit part
     */
    public static boolean isNoLayout(final EditPart editPart) {
        if (editPart instanceof IGraphicalEditPart) {
            Boolean result = (Boolean) EclipseLayoutConfig.getValue(LayoutOptions.NO_LAYOUT,
                    editPart, ((IGraphicalEditPart) editPart).getNotationView().getElement());
            if (result != null) {
                return result;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public int getPriority() {
        return PRIORITY;
    }
    
    /** The aspect ratio is rounded at two decimal places. */
    private static final float ASPECT_RATIO_ROUND = 100;

    /**
     * {@inheritDoc}
     */
    public void enrich(final LayoutContext context) {
        Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (editPart instanceof IGraphicalEditPart && !isNoLayout((EditPart) editPart)) {
            IGraphicalEditPart focusEditPart = (IGraphicalEditPart) editPart;
            if (focusEditPart instanceof CompartmentEditPart) {
                // if the selected object is a compartment, put its parent element into the context
                focusEditPart = (IGraphicalEditPart) focusEditPart.getParent();
                context.setProperty(LayoutContext.DIAGRAM_PART, focusEditPart);
            }
            
            View notationView = focusEditPart.getNotationView();
            context.setProperty(NOTATION_VIEW, notationView);
            if (context.getProperty(LayoutContext.DOMAIN_MODEL) == null) {
                EObject object = notationView.getElement();
                // put the EObject into the context only if the edit part has its own model element,
                // otherwise we would wrongly receive options that are meant for the parent element
                if (focusEditPart.getParent() == null
                        || !(focusEditPart.getParent().getModel() instanceof View)
                        || ((View) focusEditPart.getParent().getModel()).getElement() != object) {
                    context.setProperty(LayoutContext.DOMAIN_MODEL, object);
                }
            }
            
            // determine the target type and container / containment edit parts
            Maybe<IGraphicalEditPart> containerEditPart = Maybe.create();
            Maybe<Boolean> hasPorts = Maybe.create();
            Set<LayoutOptionData.Target> partTargets = findTarget(focusEditPart,
                    containerEditPart, hasPorts);
            if (partTargets != null) {
                context.setProperty(LayoutContext.OPT_TARGETS, partTargets);
            }
            
            // set whether the selected element is a node that contains ports
            if (hasPorts.get() != null) {
                context.setProperty(DefaultLayoutConfig.HAS_PORTS, hasPorts.get());
            }
            
            // get aspect ratio for the current diagram
            try {
                EditPartViewer viewer = focusEditPart.getViewer();
                if (viewer != null) {
                    Control control = viewer.getControl();
                    if (control != null) {
                        Point size = control.getSize();
                        if (size.x > 0 && size.y > 0) {
                            context.setProperty(EclipseLayoutConfig.ASPECT_RATIO,
                                    Math.round(ASPECT_RATIO_ROUND * (float) size.x / size.y)
                                    / ASPECT_RATIO_ROUND);
                        }
                    }
                }
            } catch (SWTException exception) {
                // ignore exception
            }
            
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                DiagramEditPart diagramEditPart = GmfDiagramLayoutManager.getDiagramEditPart(
                        focusEditPart);
                @SuppressWarnings("unchecked")
                LayoutOptionData<String> algorithmOptionData = (LayoutOptionData<String>)
                        LayoutDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM.getId());
                if (context.getProperty(DefaultLayoutConfig.CONTENT_HINT) == null
                        && algorithmOptionData != null) {
                    // get a layout hint for the content of the focused edit part
                    String contentLayoutHint = getValue(algorithmOptionData, PREFIX, notationView);
                    if (contentLayoutHint == null) {
                        contentLayoutHint = getValue(algorithmOptionData, DEF_PREFIX,
                                diagramEditPart.getNotationView());
                    }
                    if (contentLayoutHint != null) {
                        context.setProperty(DefaultLayoutConfig.CONTENT_HINT, contentLayoutHint);
                    }
                }
                
                // get a layout hint for the container edit part
                if (containerEditPart.get() != null) {
                    if (context.getProperty(DefaultLayoutConfig.CONTAINER_HINT) == null) {
                        String containerLayoutHint = getValue(algorithmOptionData, PREFIX,
                                containerEditPart.get().getNotationView());
                        if (containerLayoutHint == null) {
                            containerLayoutHint = getValue(algorithmOptionData, DEF_PREFIX,
                                    diagramEditPart.getNotationView());
                        }
                        if (containerLayoutHint != null) {
                            context.setProperty(DefaultLayoutConfig.CONTAINER_HINT,
                                    containerLayoutHint);
                        }
                    }
                    context.setProperty(LayoutContext.CONTAINER_DIAGRAM_PART,
                            containerEditPart.get());
                    EObject object = containerEditPart.get().getNotationView().getElement();
                    if (object != null) {
                        context.setProperty(LayoutContext.CONTAINER_DOMAIN_MODEL, object);
                    }
                }
            }
        }
    }
    
    /**
     * Determines the type of edit part target for the layout options.
     * 
     * @param editPart an edit part
     * @param containerEditPart if a container edit part is found, this reference
     *          parameter is set to that edit part
     * @param hasPorts if contained ports are found, this reference parameter is
     *          set to {@code true}
     * @return the layout option targets
     */
    protected Set<LayoutOptionData.Target> findTarget(final IGraphicalEditPart editPart,
            final Maybe<IGraphicalEditPart> containerEditPart,
            final Maybe<Boolean> hasPorts) {
        Set<LayoutOptionData.Target> partTarget = null;
        if (editPart instanceof AbstractBorderItemEditPart) {
            // this is a border item, i.e. a port 
            partTarget = EnumSet.of(LayoutOptionData.Target.PORTS);
            // the container is the parent of the port's containing node
            containerEditPart.set((IGraphicalEditPart) editPart.getParent().getParent());
            
        } else if (editPart instanceof ShapeNodeEditPart) {
            // this is a node
            partTarget = EnumSet.of(LayoutOptionData.Target.NODES);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            // check whether the node is a parent
            if (findContainingEditPart(editPart, hasPorts) != null) {
                partTarget.add(LayoutOptionData.Target.PARENTS);
            }
            
        } else if (editPart instanceof ConnectionEditPart) {
            // this is a connection, i.e. an edge
            partTarget = EnumSet.of(LayoutOptionData.Target.EDGES);
            EditPart sourcePart = ((ConnectionEditPart) editPart).getSource();
            EditPart parentPart;
            if (sourcePart instanceof AbstractBorderItemEditPart) {
                // the source element is a port
                parentPart = sourcePart.getParent().getParent();
            } else {
                // the source element is a node
                parentPart = sourcePart.getParent();
            }
            if (parentPart instanceof IGraphicalEditPart) {
                containerEditPart.set((IGraphicalEditPart) parentPart);
            }
            
        } else if (editPart instanceof LabelEditPart) {
            // this is a label
            partTarget = EnumSet.of(LayoutOptionData.Target.LABELS);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            if (containerEditPart.get() instanceof ConnectionEditPart) {
                // we have an edge label, so apply the same container rule as for edges
                EditPart sourcePart = ((ConnectionEditPart) containerEditPart.get()).getSource();
                if (sourcePart instanceof AbstractBorderItemEditPart) {
                    containerEditPart.set((IGraphicalEditPart) sourcePart.getParent().getParent());
                } else {
                    containerEditPart.set((IGraphicalEditPart) sourcePart.getParent());
                }
            } else if (containerEditPart.get() instanceof AbstractBorderItemEditPart) {
                // we have a port label, so apply the same container rule as for ports
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get()
                        .getParent().getParent());
            } else if (containerEditPart.get() instanceof ShapeNodeEditPart) {
                // we have a node label
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
            }
            
        } else if (editPart instanceof DiagramEditPart) {
            // this is a diagram
            partTarget = EnumSet.of(LayoutOptionData.Target.PARENTS);
        }
        
        if (containerEditPart.get() instanceof CompartmentEditPart) {
            containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
        }
        return partTarget;
    }
    
    /**
     * Finds the edit part that contains layoutable children, if there are any. The returned
     * edit part is either the parent edit part itself or one of its compartments. While looking
     * for layoutable children, the existence of contained ports is also checked.
     * 
     * @param editPart a node edit part
     * @param hasPorts if the node contains ports, this reference parameter is set to {@code true}
     * @return the edit part that contains other node edit parts, or {@code null} if there is none
     */
    private static IGraphicalEditPart findContainingEditPart(final IGraphicalEditPart editPart,
            final Maybe<Boolean> hasPorts) {
        hasPorts.set(Boolean.FALSE);
        IGraphicalEditPart result = null;
        for (Object child : editPart.getChildren()) {
            if (!isNoLayout((EditPart) child)) {
                if (child instanceof AbstractBorderItemEditPart) {
                    hasPorts.set(Boolean.TRUE);
                    if (result != null) {
                        break;
                    }
                } else if (child instanceof ShapeNodeEditPart) {
                    result = editPart;
                    if (hasPorts.get()) {
                        break;
                    }
                } else if (child instanceof CompartmentEditPart) {
                    for (Object grandChild : ((CompartmentEditPart) child).getChildren()) {
                        if (grandChild instanceof ShapeNodeEditPart
                                && !isNoLayout((EditPart) grandChild)) {
                            result = (IGraphicalEditPart) child;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Object getValue(final LayoutOptionData<?> optionData, final LayoutContext context) {
        View view = context.getProperty(NOTATION_VIEW);
        if (view != null) {
            // check option value from notation model
            Object result = getValue(optionData, PREFIX, view);
            if (result != null) {
                return result;
            }
        }

        // check default option of diagram edit part
        Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (editPart instanceof EditPart) {
            IGraphicalEditPart diagramEditPart = GmfDiagramLayoutManager.getDiagramEditPart(
                    (EditPart) editPart);
            if (diagramEditPart != null) {
                return getValue(optionData, DEF_PREFIX, diagramEditPart.getNotationView());
            }
        }
        
        return null;
    }

    /**
     * Get a property from the given notation view.
     * 
     * @param optionData the layout option
     * @param prefix the prefix for the style name
     * @param view the notation view
     * @return the value of the option, or {@code null}
     */
    private <T> T getValue(final LayoutOptionData<T> optionData, final String prefix,
            final View view) {
        String optionKey = prefix + optionData.getId();
        for (Object obj : view.getStyles()) {
            if (obj instanceof StringValueStyle) {
                StringValueStyle style = (StringValueStyle) obj;
                if (optionKey.equals(style.getName())) {
                    T result = optionData.parseValue(style.getStringValue());
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void transferValues(final KLayoutData graphData, final LayoutContext context) {
        Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (editPart instanceof IGraphicalEditPart) {
            // add user defined global layout options
            DiagramEditPart diagramEditPart = GmfDiagramLayoutManager.getDiagramEditPart(
                    (EditPart) editPart);
            if (diagramEditPart != null) {
                transferValues(graphData, DEF_PREFIX, diagramEditPart.getNotationView());
            }
            // add user defined local layout options
            transferValues(graphData, PREFIX, ((IGraphicalEditPart) editPart).getNotationView());
        } else {
            View view = context.getProperty(NOTATION_VIEW);
            if (view != null) {
                transferValues(graphData, PREFIX, view);
            }
        }
    }

    /**
     * Add the options from the list of properties to the options map. Only properties whose key
     * starts with the given prefix are considered.
     * 
     * @param graphData a graph data instance that can hold layout options
     * @param prefix the expected prefix of the property keys
     * @param view a notation view
     */
    private void transferValues(final KLayoutData graphData, final String prefix,
            final View view) {
        LayoutDataService layoutServices = LayoutDataService.getInstance();
        for (Object obj : view.getStyles()) {
            if (obj instanceof StringValueStyle) {
                StringValueStyle style = (StringValueStyle) obj;
                String key = style.getName();
                if (key != null && key.startsWith(prefix)) {
                    LayoutOptionData<?> optionData = layoutServices.getOptionData(
                            key.substring(prefix.length()));
                    if (optionData != null) {
                        Object value = optionData.parseValue(style.getStringValue());
                        if (value != null) {
                            graphData.setProperty(optionData, value);
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final LayoutOptionData<?> optionData, final LayoutContext context,
            final Object value) {
        View view = context.getProperty(NOTATION_VIEW);
        if (view != null) {
            if (context.getProperty(IMutableLayoutConfig.OPT_RECURSIVE)) {
                if (value != null) {
                    removeValue(optionData, PREFIX, view, true);
                }
                setValue(optionData, value, DEF_PREFIX, view);
            } else {
                setValue(optionData, value, PREFIX, view);
            }
        }
    }

    /**
     * Set the option for the given notation view. Adds a new style to the view unless the given
     * key already exists.
     * 
     * @param optionData layout option data
     * @param value the value
     * @param prefix the prefix for the property key
     * @param view the notation view
     */
    @SuppressWarnings("unchecked")
    private void setValue(final LayoutOptionData<?> optionData, final Object value,
            final String prefix, final View view) {
        if (value == null) {
            removeValue(optionData, prefix, view, false);
        } else {
            String optionKey = prefix + optionData.getId();
            for (Object obj : view.getStyles()) {
                if (obj instanceof StringValueStyle) {
                    StringValueStyle style = (StringValueStyle) obj;
                    if (optionKey.equals(style.getName())) {
                        style.setStringValue(value.toString());
                        return;
                    }
                }
            }
    
            StringValueStyle style = NotationFactory.eINSTANCE.createStringValueStyle();
            style.setName(optionKey);
            style.setStringValue(value.toString());
            view.getStyles().add(style);
        }
    }

    /**
     * Remove an option from the given notation view.
     * 
     * @param optionData layout option data
     * @param prefix the prefix for the property key
     * @param view the notation view
     * @param recursive whether options should also be removed from children
     */
    private void removeValue(final LayoutOptionData<?> optionData, final String prefix,
            final View view, final boolean recursive) {
        String optionKey = prefix + optionData.getId();
        Iterator<?> iter = view.getStyles().iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof StringValueStyle
                    && optionKey.equals(((StringValueStyle) obj).getName())) {
                iter.remove();
            }
        }
        
        if (recursive) {
            for (Object child : view.getPersistedChildren()) {
                removeValue(optionData, prefix, (View) child, true);
                if (child instanceof Diagram) {
                    for (Object edge : ((Diagram) child).getPersistedEdges()) {
                        removeValue(optionData, prefix, (View) edge, true);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clearValues(final LayoutContext context) {
        View view = context.getProperty(NOTATION_VIEW);
        if (view != null) {
            boolean recursive = context.getProperty(IMutableLayoutConfig.OPT_RECURSIVE);
            clearValues(view, recursive);
        }
    }
    
    /**
     * Removes all layout options from the given view and its children.
     * 
     * @param view a view from the notation model
     * @param recursive whether the children should also be processed
     */
    private void clearValues(final View view, final boolean recursive) {
        Iterator<?> iter = view.getStyles().iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof StringValueStyle) {
                StringValueStyle style = (StringValueStyle) obj;
                String key = style.getName() == null ? "" : style.getName();
                if (key.startsWith(PREFIX) || key.startsWith(DEF_PREFIX)) {
                    iter.remove();
                }
            }
        }
        
        if (recursive) {
            for (Object child : view.getPersistedChildren()) {
                clearValues((View) child, true);
            }
            if (view instanceof Diagram) {
                for (Object child : ((Diagram) view).getPersistedEdges()) {
                    clearValues((View) child, true);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSet(final LayoutOptionData<?> optionData, final LayoutContext context) {
        View view = context.getProperty(NOTATION_VIEW);
        if (view != null) {
            // check option value from notation model
            Object result = getValue(optionData, PREFIX, view);
            return result != null;
        }
        return false;
    }
    
}
