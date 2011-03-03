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

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.model.GmfFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.KOption;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionsFactory;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionsPackage;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutConfig;

/**
 * A layout configuration that stores layout options in the notation model of GMF diagrams.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class GmfLayoutConfig extends EclipseLayoutConfig {
    
    /**
     * Returns the {@link KOption} with given key that is stored for the edit part.
     * 
     * @param editPart the edit part for which the option shall be fetched
     * @param optionId the identifier of the option
     * @return the corresponding option, or {@code null} if there is no such option
     */
    public static KOption getKOption(final IGraphicalEditPart editPart, final String optionId) {
        LayoutOptionStyle style = (LayoutOptionStyle) editPart.getNotationView()
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (style != null) {
            for (KOption koption : style.getOptions()) {
                if (koption.getKey().equals(optionId)) {
                    return koption;
                }
            }
        }
        return null;
    }
    
    /**
     * Removes an option from the given style container.
     * 
     * @param style a layout option style
     * @param optionData a layout option data
     */
    public static void removeKOption(final LayoutOptionStyle style,
            final LayoutOptionData<?> optionData) {
        ListIterator<KOption> optionIter = style.getOptions().listIterator();
        while (optionIter.hasNext()) {
            if (optionIter.next().getKey().equals(optionData.getId())) {
                optionIter.remove();
                break;
            }
        }
    }

    /**
     * Removes the {@link LayoutOptionStyle} from the notation view, if it exists.
     * 
     * @param view the notation view from which to remove the layout option style
     */
    public static void removeOptionStyle(final View view) {
        EClass optionClass = LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle();
        @SuppressWarnings("unchecked")
        ListIterator<Style> styleIter = view.getStyles().listIterator();
        while (styleIter.hasNext()) {
            Style style = styleIter.next();
            if (style.eClass() == optionClass) {
                styleIter.remove();
                break;
            }
        }
    }
    
    /**
     * Determines whether the given edit part should not be layouted.
     * 
     * @param editPart an edit part
     * @return true if no layout should be performed for the edit part
     */
    public static boolean isNoLayout(final EditPart editPart) {
        if (editPart instanceof IGraphicalEditPart) {
            Boolean result = (Boolean) getOption(editPart,
                    ((IGraphicalEditPart) editPart).getNotationView().getElement(),
                    LayoutOptions.NO_LAYOUT);
            if (result != null) {
                return result;
            }
        }
        return false;
    }
    
    /** the layout option style stored in the notation view. */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style. */
    private Map<LayoutOptionData<?>, KOption> koptionMap;
    
    /**
     * Create a stand-alone layout configuration for GMF.
     */
    public GmfLayoutConfig() {
        super();
    }
    
    /**
     * Create a layout configuration for GMF with embedded external configuration.
     * 
     * @param externalConfig an external configuration
     */
    public GmfLayoutConfig(final ILayoutConfig externalConfig) {
        super(externalConfig);
    }
    
    /**
     * Set the focus of the layout configuration on a specific edit part. The domain model element
     * of the edit part is passed to the super-class as well.
     * This can be done without initializing the layout configuration in order to use
     * {@link #getAllProperties()} efficiently, since the same configuration instance can
     * be reused multiple times.
     * 
     * @param element an instance of {@link IGraphicalEditPart}
     */
    @Override
    public void setFocus(final Object element) {
        if (element != null) {
            // first clear the current focus
            super.setFocus(null);
        }
        super.setFocus(element);
        if (element instanceof IGraphicalEditPart) {
            super.setFocus(((IGraphicalEditPart) element).getNotationView().getElement());
        }
    }
    
    /**
     * Initialize the configuration for a graphical edit part.
     * 
     * @param editPart an edit part
     */
    public final void initialize(final IGraphicalEditPart editPart) {
        optionStyle = null;
        koptionMap = null;
        // find an appropriate property source and set the layout option targets
        IGraphicalEditPart focusEditPart = editPart;
        if (focusEditPart instanceof CompartmentEditPart) {
            focusEditPart = (IGraphicalEditPart) focusEditPart.getParent();
        }
        setFocus(focusEditPart);
        if (isNoLayout(focusEditPart)) {
            return;
        }
        
        // determine the target type and container / containment edit parts
        Pair<IGraphicalEditPart, IGraphicalEditPart> targetEditParts
                = new Pair<IGraphicalEditPart, IGraphicalEditPart>();
        Maybe<Boolean> hasPorts = new Maybe<Boolean>(Boolean.FALSE);
        LayoutOptionData.Target partTarget = findTarget(focusEditPart, targetEditParts, hasPorts);
        IGraphicalEditPart containmentEditPart = targetEditParts.getFirst();
        IGraphicalEditPart containerEditPart = targetEditParts.getSecond();

        if (partTarget != null) {
            DiagramEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(focusEditPart);
            // get default options from the notation view
            optionStyle = (LayoutOptionStyle) focusEditPart.getNotationView()
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            String contentLayoutHint = getNotationOptions(diagramEditPart);
            
            if (containerEditPart != null) {
                // look for a layout hint for the container element
                KOption containerLayoutHintOption = getKOption(containerEditPart,
                        LayoutOptions.ALGORITHM_ID);
                if (containerLayoutHintOption == null && diagramEditPart != null) {
                    KOption koption = getKOption(diagramEditPart, LayoutOptions.ALGORITHM_ID);
                    if (koption != null && koption.isDefault()) {
                        containerLayoutHintOption = koption;
                    }
                }
                String containerLayoutHint = containerLayoutHintOption == null ? null
                        : containerLayoutHintOption.getValue();
                initialize(partTarget, containerEditPart, containerLayoutHint);
            }
            
            if (containmentEditPart != null) {
                initialize(LayoutOptionData.Target.PARENTS, containmentEditPart, contentLayoutHint);
                setChildren(true);
            } else {
                setChildren(false);
            }
            
            setPorts(hasPorts.get());
        }
    }
    
    /**
     * Determines the type of edit part target for the layout options.
     * 
     * @param editPart an edit part
     * @param hasPorts if contained ports are found, this reference parameter is
     *          set to {@code true}
     * @return the edit part target
     */
    private LayoutOptionData.Target findTarget(final IGraphicalEditPart editPart,
            final Pair<IGraphicalEditPart, IGraphicalEditPart> targetEditParts,
            final Maybe<Boolean> hasPorts) {
        IGraphicalEditPart containerEditPart = null, containmentEditPart = null;
        LayoutOptionData.Target partTarget = null;
        if (editPart instanceof AbstractBorderItemEditPart) {
            partTarget = LayoutOptionData.Target.PORTS;
            containerEditPart = (IGraphicalEditPart) editPart.getParent().getParent();
        } else if (editPart instanceof ShapeNodeEditPart) {
            // check whether the node is a parent
            partTarget = LayoutOptionData.Target.NODES;
            containerEditPart = (IGraphicalEditPart) editPart.getParent();
            if (findContainingEditPart(editPart, hasPorts) != null) {
                containmentEditPart = editPart;
            }
        } else if (editPart instanceof ConnectionEditPart) {
            partTarget = LayoutOptionData.Target.EDGES;
            containerEditPart = (IGraphicalEditPart) ((ConnectionEditPart) editPart)
                    .getSource().getParent();
        } else if (editPart instanceof LabelEditPart) {
            partTarget = LayoutOptionData.Target.LABELS;
            containerEditPart = (IGraphicalEditPart) editPart.getParent();
            if (containerEditPart instanceof ConnectionEditPart) {
                containerEditPart = (IGraphicalEditPart) ((ConnectionEditPart) containerEditPart)
                        .getSource().getParent();
            } else if (containerEditPart instanceof AbstractBorderItemEditPart) {
                containerEditPart =  (IGraphicalEditPart) containerEditPart.getParent().getParent();
            } else if (containerEditPart instanceof ShapeNodeEditPart) {
                containerEditPart = (IGraphicalEditPart) containerEditPart.getParent();
            }
        } else if (editPart instanceof DiagramEditPart) {
            partTarget = LayoutOptionData.Target.PARENTS;
            containmentEditPart = editPart;
        }
        if (containerEditPart instanceof CompartmentEditPart) {
            containerEditPart = (IGraphicalEditPart) containerEditPart.getParent();
        }
        
        targetEditParts.setFirst(containmentEditPart);
        targetEditParts.setSecond(containerEditPart);
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
    private IGraphicalEditPart findContainingEditPart(final IGraphicalEditPart editPart,
            final Maybe<Boolean> hasPorts) {
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
     * Retrieves the options from the notation view of the selected edit part.
     * 
     * @param diagramEditPart the diagram edit part
     * @return the layout hint for the selected edit part's content
     */
    private String getNotationOptions(final DiagramEditPart diagramEditPart) {
        koptionMap = new HashMap<LayoutOptionData<?>, KOption>();
        String partLayoutHint = null;
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.ALGORITHM_ID.equals(koption.getKey())) {
                    partLayoutHint = koption.getValue();
                }
                LayoutOptionData<?> optionData = LayoutServices.getInstance()
                        .getOptionData(koption.getKey());
                if (optionData != null) {
                    koptionMap.put(optionData, koption);
                }
            }
        }
        if (partLayoutHint == null && diagramEditPart != null) {
            KOption koption = getKOption(diagramEditPart,
                    LayoutOptions.ALGORITHM_ID);
            if (koption != null && koption.isDefault()) {
                partLayoutHint = koption.getValue();
            }
        }
        return partLayoutHint;
    }
    
    /**
     * Retrieve the value that is stored in the notation model or the default
     * value for a layout option.
     * 
     * @param <T> type of option
     * @param optionData a layout option
     * @return the stored or default value for the layout option
     */
    @Override
    protected <T> T doGetProperty(final LayoutOptionData<T> optionData) {
        T result;
        // check option value from notation model
        if (koptionMap != null) {
            KOption koption = koptionMap.get(optionData);
            if (koption != null) {
                result = optionData.parseValue(koption.getValue());
                if (result != null) {
                    return result;
                }
            }
        }
        
        // check default option of diagram edit part
        result = getDiagramDefault(optionData);
        if (result != null) {
            return result;
        }
        
        // fall back to the user-stored or preconfigured configuration
        return super.doGetProperty(optionData);
    }
    
    /**
     * Stores the given value in the notation view of the selected element. This requires
     * {@link #initialize(IGraphicalEditPart) initialize} to be called first in order to
     * work properly.
     * 
     * @param property a layout option
     * @param value an option value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) property;
            if (value == null) {
                if (optionStyle != null) {
                    removeKOption(optionStyle, optionData);
                    koptionMap.remove(optionData);
                }
            } else {
                KOption koption = koptionMap.get(optionData);
                if (koption == null) {
                    if (optionStyle == null) {
                        if (getEditPart() instanceof IGraphicalEditPart) {
                            IGraphicalEditPart focusEditPart = (IGraphicalEditPart) getEditPart();
                            optionStyle = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
                            focusEditPart.getNotationView().getStyles().add(optionStyle);
                        } else {
                            super.setProperty(property, value);
                            return;
                        }
                    }
                    koption = LayoutOptionsFactory.eINSTANCE.createKOption();
                    koption.setKey(optionData.getId());
                    optionStyle.getOptions().add(koption);
                    koptionMap.put(optionData, koption);
                }
                koption.setValue(value.toString());
            }
        } else {
            super.setProperty(property, value);
        }
    }
    
    /**
     * Determines whether the given layout option is already stored in the notation view
     * of the selected element. This requires
     * {@link #initialize(IGraphicalEditPart) initialize} to be called first in order to
     * work properly.
     * 
     * @param optionData a layout option
     * @return whether the option has its default value or not
     */
    @Override
    public boolean isDefault(final LayoutOptionData<?> optionData) {
        // check option value from notation model
        if (koptionMap != null) {
            KOption koption = koptionMap.get(optionData);
            if (koption != null) {
                if (optionData.parseValue(koption.getValue()) != null) {
                    return false;
                }
            }
        }
        return super.isDefault(optionData);
    }
    
    /**
     * Clear all stored layout options for the selected element.
     */
    @Override
    public void clearProperties() {
        if (getEditPart() instanceof IGraphicalEditPart) {
            IGraphicalEditPart focusEditPart = (IGraphicalEditPart) getEditPart();
            View view = focusEditPart.getNotationView();
            if (view instanceof Diagram) {
                Diagram diagram = (Diagram) view;
                for (Object edge : diagram.getPersistedEdges()) {
                    removeChildOptions((View) edge);
                }
            }
            removeChildOptions(view);
        }
    }

    /**
     * Returns the default value for the selected diagram.
     * 
     * @param <T> type of option
     * @param optionData a layout option
     * @return the current diagram-default value
     */
    public <T> T getDiagramDefault(final LayoutOptionData<T> optionData) {
        IGraphicalEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(getEditPart());
        if (diagramEditPart != null) {
            LayoutOptionStyle style = (LayoutOptionStyle) diagramEditPart.getNotationView()
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (style != null) {
                for (KOption koption : style.getOptions()) {
                    if (koption.getKey().equals(optionData.getId())
                            && koption.isDefault()) {
                        return optionData.parseValue(koption.getValue());
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Sets a default value for the selected diagram.
     * 
     * @param optionData a layout option
     * @param value the new default value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        if (getEditPart() instanceof IGraphicalEditPart) {
            IGraphicalEditPart focusEditPart = (IGraphicalEditPart) getEditPart();
            View notationView = focusEditPart.getNotationView();
            
            // get the layout option style
            LayoutOptionStyle style = (LayoutOptionStyle) notationView
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (style == null) {
                style = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
                notationView.getStyles().add(style);
            }
            
            if (value == null) {
                GmfLayoutConfig.removeKOption(style, optionData);
            } else {
                // get the layout option
                KOption koption = null;
                for (KOption opt : style.getOptions()) {
                    if (opt.getKey().equals(optionData.getId())) {
                        koption = opt;
                        break;
                    }
                }
                if (koption == null) {
                    koption = LayoutOptionsFactory.eINSTANCE.createKOption();
                    koption.setKey(optionData.getId());
                    style.getOptions().add(koption);
                }
                
                // set the new option value
                koption.setValue(value.toString());
                koption.setDefault(true);
                
                // remove the option from all children
                removeChildOptions(notationView, optionData);
            }
        }
    }
    
    /**
     * Removes all layout options from the given view and its children.
     * 
     * @param view a view from the notation model
     */
    private void removeChildOptions(final View view) {
        removeOptionStyle(view);
        for (Object child : view.getPersistedChildren()) {
            removeChildOptions((View) child);
        }
    }
    
    /**
     * Removes all options with given id from the children of the notation view recursively.
     * 
     * @param notationView a notation view
     * @param optionData layout option data
     */
    private void removeChildOptions(final View notationView, final LayoutOptionData<?> optionData) {
        for (Object child : notationView.getPersistedChildren()) {
            View node = (View) child;
            LayoutOptionStyle style = (LayoutOptionStyle) node.getStyle(
                    LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (style != null) {
                GmfLayoutConfig.removeKOption(style, optionData);
            }
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                style = (LayoutOptionStyle) edge.getStyle(
                        LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                if (style != null) {
                    GmfLayoutConfig.removeKOption(style, optionData);
                }
                removeChildOptions(edge, optionData);
            }
            removeChildOptions(node, optionData);
        }
    }
    
    /**
     * Add all notation model values to the given map of layout options.
     * 
     * @param options a map of layout option values
     */
    protected void addProperties(final Map<IProperty<?>, Object> options) {
        super.addProperties(options);
        
        if (super.getEditPart() instanceof IGraphicalEditPart) {
            // add user defined global layout options
            IGraphicalEditPart editPart = (IGraphicalEditPart) super.getEditPart();
            DiagramEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(editPart);
            if (diagramEditPart != editPart && diagramEditPart != null) {
                LayoutOptionStyle style = (LayoutOptionStyle) diagramEditPart.getNotationView()
                        .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                addOptions(options, style, true);
            }
            
            // add user defined local layout options
            LayoutOptionStyle style = (LayoutOptionStyle) editPart.getNotationView().getStyle(
                    LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            addOptions(options, style, false);
        }
    }
    
    /**
     * Adds the layout options of the given layout options style to a map
     * of layout options.
     * 
     * @param options a map of layout options
     * @param optionStyle a layout options style
     * @param onlyDefault if true, only default options are taken
     */
    private static void addOptions(final Map<IProperty<?>, Object> options,
            final LayoutOptionStyle optionStyle, final boolean onlyDefault) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        if (optionStyle != null) {
            for (KOption option : optionStyle.getOptions()) {
                if (!onlyDefault || option.isDefault()) {
                    LayoutOptionData<?> optionData = layoutServices.getOptionData(
                            option.getKey());
                    if (optionData != null) {
                        Object value = optionData.parseValue(option.getValue());
                        if (value != null) {
                            options.put(optionData, value);
                        }
                    }
                }
            }
        }
    }
    
}
