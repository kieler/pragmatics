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
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
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
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.model.gmf.GmfFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.KOption;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;

/**
 * A layout configuration that stores layout options in the notation model of GMF diagrams.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
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
            Boolean result = (Boolean) EclipseLayoutConfig.getOption(editPart,
                    ((IGraphicalEditPart) editPart).getNotationView().getElement(),
                    LayoutOptions.NO_LAYOUT);
            if (result != null) {
                return result;
            }
        }
        return false;
    }
    
    /** @deprecated TODO throw away after use */
    private void checkDeprecatedStyle(final View view) {
        ListIterator<Style> styleIter = view.getStyles().listIterator();
        LinkedList<Object[]> options = new LinkedList<Object[]>();
        while (styleIter.hasNext()) {
            Style style = styleIter.next();
            if (style instanceof LayoutOptionStyle) {
                LayoutDataService dataService = LayoutDataService.getInstance();
                for (KOption koption : ((LayoutOptionStyle) style).getOptions()) {
                    LayoutOptionData<?> optionData = dataService.getOptionData(koption.getKey());
                    if (optionData != null) {
                        String prefix = koption.isDefault() ? DEF_PREFIX : PREFIX;
                        if (getValue(optionData, prefix, view) == null) {
                            options.add(new Object[] { optionData, koption.getValue(), prefix });
                        }
                    }
                }
                styleIter.remove();
            }
        }
        for (Object[] option : options) {
            setValue((LayoutOptionData<?>) option[0], option[1], (String) option[2], view);
        }
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
        Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (editPart instanceof IGraphicalEditPart && !isNoLayout((EditPart) editPart)) {
            IGraphicalEditPart focusEditPart = (IGraphicalEditPart) editPart;
            if (focusEditPart instanceof CompartmentEditPart) {
                focusEditPart = (IGraphicalEditPart) focusEditPart.getParent();
                context.setProperty(LayoutContext.DIAGRAM_PART, focusEditPart);
            }
            
            View notationView = focusEditPart.getNotationView();
            context.setProperty(NOTATION_VIEW, notationView);
            // labels usually have no own domain model element, so they must not take the domain options
            if (!(focusEditPart instanceof LabelEditPart)) {
                EObject object = notationView.getElement();
                if (object != null) {
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
                Point size = focusEditPart.getViewer().getControl().getSize();
                if (size.x > 0 && size.y > 0) {
                    context.setProperty(EclipseLayoutConfig.ASPECT_RATIO,
                            Math.round(ASPECT_RATIO_ROUND * (float) size.x / size.y)
                            / ASPECT_RATIO_ROUND);
                }
            } catch (SWTException exception) {
                // ignore exception
            }
            
            if (context.getProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS)) {
                DiagramEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(focusEditPart);
                @SuppressWarnings("unchecked")
                LayoutOptionData<String> algorithmOptionData = (LayoutOptionData<String>)
                        LayoutDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM_ID);
                // get a layout hint for the content of the focused edit part
                String contentLayoutHint = getValue(algorithmOptionData, PREFIX, notationView);
                if (contentLayoutHint == null) {
                    contentLayoutHint = getValue(algorithmOptionData, DEF_PREFIX,
                            diagramEditPart.getNotationView());
                }
                if (contentLayoutHint != null) {
                    context.setProperty(DefaultLayoutConfig.CONTENT_HINT, contentLayoutHint);
                }
                
                // get a layout hint for the container edit part
                if (containerEditPart.get() != null) {
                    String containerLayoutHint = getValue(algorithmOptionData, PREFIX,
                            containerEditPart.get().getNotationView());
                    if (containerLayoutHint == null) {
                        containerLayoutHint = getValue(algorithmOptionData, DEF_PREFIX,
                                diagramEditPart.getNotationView());
                    }
                    if (containerLayoutHint != null) {
                        context.setProperty(DefaultLayoutConfig.CONTAINER_HINT, containerLayoutHint);
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
    private static Set<LayoutOptionData.Target> findTarget(final IGraphicalEditPart editPart,
            final Maybe<IGraphicalEditPart> containerEditPart,
            final Maybe<Boolean> hasPorts) {
        Set<LayoutOptionData.Target> partTarget = null;
        if (editPart instanceof AbstractBorderItemEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.PORTS);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent().getParent());
        } else if (editPart instanceof ShapeNodeEditPart) {
            // check whether the node is a parent
            partTarget = EnumSet.of(LayoutOptionData.Target.NODES);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            if (findContainingEditPart(editPart, hasPorts) != null) {
                partTarget.add(LayoutOptionData.Target.PARENTS);
            }
        } else if (editPart instanceof ConnectionEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.EDGES);
            containerEditPart.set((IGraphicalEditPart) ((ConnectionEditPart) editPart)
                    .getSource().getParent());
        } else if (editPart instanceof LabelEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.LABELS);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            if (containerEditPart.get() instanceof ConnectionEditPart) {
                containerEditPart.set((IGraphicalEditPart) ((ConnectionEditPart)
                        containerEditPart.get()).getSource().getParent());
            } else if (containerEditPart.get() instanceof AbstractBorderItemEditPart) {
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get()
                        .getParent().getParent());
            } else if (containerEditPart.get() instanceof ShapeNodeEditPart) {
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
            }
        } else if (editPart instanceof DiagramEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.PARENTS);
        }
        if (containerEditPart.get() instanceof CompartmentEditPart) {
            containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
        }
        return partTarget;
    }
    
    /**
     * Finds the edit part that contains layoutable children, if there are any. The returned
     * edit part is either the parent edit part itself or one of its compartments. 
     * 
     * @param editPart a node edit part
     * @param hasPorts if ports are found, this reference parameter is set to {@code true}
     * @return the edit part that contains other node edit parts, or {@code null} if there is none
     */
    private static IGraphicalEditPart findContainingEditPart(final IGraphicalEditPart editPart,
            final Maybe<Boolean> hasPorts) {
        hasPorts.set(Boolean.FALSE);
        for (Object child : editPart.getChildren()) {
            if (child instanceof AbstractBorderItemEditPart && !isNoLayout((EditPart) child)) {
                hasPorts.set(Boolean.TRUE);
            } else if (child instanceof ShapeNodeEditPart
                    && !(child instanceof AbstractBorderItemEditPart)
                    && !isNoLayout((EditPart) child)) {
                return editPart;
            } else if (child instanceof CompartmentEditPart
                    && !isNoLayout((EditPart) child)) {
                for (Object grandChild : ((CompartmentEditPart) child).getChildren()) {
                    if (grandChild instanceof ShapeNodeEditPart
                            && !isNoLayout((EditPart) grandChild)) {
                        return (IGraphicalEditPart) child;
                    }
                }
            }
        }
        return null;
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
            IGraphicalEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(
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
    public void transferValues(final KGraphData graphData, final LayoutContext context) {
        Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
        if (editPart instanceof IGraphicalEditPart) {
            // add user defined global layout options
            DiagramEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(
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
     * Add the options from the list of properties to the options map.
     * 
     * @param graphData a graph data instance that can hold layout options
     * @param prefix the prefix for the property key
     * @param view a notation view
     */
    private void transferValues(final KGraphData graphData, final String prefix,
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
            checkDeprecatedStyle(view);
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
            style.setName(prefix + optionData.getId());
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
        checkDeprecatedStyle(view);
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
            } else if (obj instanceof LayoutOptionStyle) {
                iter.remove();
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
