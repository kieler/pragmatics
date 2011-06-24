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
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
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

import de.cau.cs.kieler.core.model.gmf.GmfFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
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
    
    /** Prefix for all layout options. */
    public static final String PREFIX = "layout:";

    /** Prefix for diagram defaults stored in the top-level edit part. */
    public static final String DIAG_PREFIX = "defaultLayout:";
    
    /**
     * Returns the {@link KOption} with given key that is stored for the edit part.
     * 
     * @param editPart the edit part for which the option shall be fetched
     * @param optionId the identifier of the option
     * @return the corresponding option, or {@code null} if there is no such option
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead
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
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead
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
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead
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
    
    /** the layout option style stored in the notation view.
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style.
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead */
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
        // labels usually have no own domain model element, so they must not take the domain options
        if (element instanceof IGraphicalEditPart && !(element instanceof LabelEditPart)) {
            EObject object = ((IGraphicalEditPart) element).getNotationView().getElement();
            if (object != null) {
                super.setFocus(object);
            }
        }
    }
    
    /**
     * Clear all stored layout options for the selected element.
     */
    @Override
    public void clearProperties() {
        IGraphicalEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(getEditPart());
        if (diagramEditPart != null) {
            Diagram diagram = (Diagram) diagramEditPart.getNotationView();
            for (Object edge : diagram.getPersistedEdges()) {
                clearChildOptions((View) edge);
            }
            clearChildOptions(diagram);
        }
    }
    
    /**
     * Removes all layout options from the given view and its children.
     * 
     * @param view a view from the notation model
     */
    private static void clearChildOptions(final View view) {
        Iterator<?> iter = view.getStyles().iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof StringValueStyle) {
                StringValueStyle style = (StringValueStyle) obj;
                String key = style.getName() == null ? "" : style.getName();
                if (key.startsWith(PREFIX) || key.startsWith(DIAG_PREFIX)) {
                    iter.remove();
                }
            }
        }
        
        removeOptionStyle(view);
        for (Object child : view.getPersistedChildren()) {
            clearChildOptions((View) child);
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
            String contentLayoutHint = getNotationOptions(focusEditPart, diagramEditPart);
            @SuppressWarnings("unchecked")
            LayoutOptionData<String> algorithmOptionData = (LayoutOptionData<String>)
                    LayoutDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM_ID);
            if (contentLayoutHint == null) {
                contentLayoutHint = getOption(algorithmOptionData, PREFIX,
                        focusEditPart.getNotationView());
            }
            if (contentLayoutHint == null) {
                contentLayoutHint = getOption(algorithmOptionData, DIAG_PREFIX,
                        diagramEditPart.getNotationView());
            }
            
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
                if (containerLayoutHint == null) {
                    containerLayoutHint = getOption(algorithmOptionData, PREFIX,
                            containerEditPart.getNotationView());
                }
                if (containerLayoutHint == null) {
                    containerLayoutHint = getOption(algorithmOptionData, DIAG_PREFIX,
                            diagramEditPart.getNotationView());
                }
                initialize(partTarget, containerEditPart, containerLayoutHint);
            }
            
            if (containmentEditPart != null) {
                initialize(LayoutOptionData.Target.PARENTS, containmentEditPart, contentLayoutHint);
                setChildren(true);
            } else {
                setChildren(false);
            }
            
            setPorts(hasPorts.get());
            
            // get aspect ratio for the current diagram
            try {
                Point size = editPart.getViewer().getControl().getSize();
                if (size.x > 0 || size.y > 0) {
                    setAspectRatio((float) size.x / size.y);
                }
            } catch (SWTException exception) {
                // ignore exception
            }
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
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead
     */
    private String getNotationOptions(final IGraphicalEditPart focusEditPart,
            final DiagramEditPart diagramEditPart) {
        koptionMap = new HashMap<LayoutOptionData<?>, KOption>();
        String partLayoutHint = null;
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.ALGORITHM_ID.equals(koption.getKey())) {
                    partLayoutHint = koption.getValue();
                }
                LayoutOptionData<?> optionData = LayoutDataService.getInstance()
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
        T result = null;
        // check option value from notation model
        EditPart editPart = getEditPart();
        if (editPart instanceof IGraphicalEditPart) {
            result = getOption(optionData, PREFIX,
                    ((IGraphicalEditPart) editPart).getNotationView());
            if (result != null) {
                return result;
            }
        }
        
        // TODO eliminate code based on LayoutOptionStyle
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
     * Get a property from the given notation view.
     * 
     * @param <T> the type of the value of the option
     * @param optionData the layout option
     * @param prefix the prefix for the style name
     * @param view the notation view
     * @return the value of the option, or {@code null}
     */
    private <T> T getOption(final LayoutOptionData<T> optionData, final String prefix,
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
     * Stores the given value in the notation view of the selected element. This requires
     * {@link #initialize(IGraphicalEditPart) initialize} to be called first in order to
     * work properly.
     * 
     * @param property a layout option
     * @param value an option value
     */
    @Override
    public void setProperty(final IProperty<?> property, final Object value) {
        if (property instanceof LayoutOptionData<?>) {
            LayoutOptionData<?> optionData = (LayoutOptionData<?>) property;
            EditPart editPart = getEditPart();
            if (editPart instanceof IGraphicalEditPart) {
                checkDeprecatedStyle(((IGraphicalEditPart) editPart).getNotationView());
                setOption(optionData, value, PREFIX,
                        ((IGraphicalEditPart) editPart).getNotationView());
            }
        } else {
            super.setProperty(property, value);
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
    private void setOption(final LayoutOptionData<?> optionData, final Object value,
            final String prefix, final View view) {
        if (value == null) {
            removeOption(optionData, prefix, view);
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
     */
    private void removeOption(final LayoutOptionData<?> optionData, final String prefix,
            final View view) {
        String optionKey = prefix + optionData.getId();
        Iterator<?> iter = view.getStyles().iterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            if (obj instanceof StringValueStyle
                    && optionKey.equals(((StringValueStyle) obj).getName())) {
                iter.remove();
            }
        }
    }

    /**
     * Recursively remove an option from the given view and all its children.
     * 
     * @param optionData layout option data
     * @param prefix the prefix for the property key
     * @param view the notation view
     */
    private void removeOptionRecursively(final LayoutOptionData<?> optionData, final String prefix,
            final View view) {
        removeOption(optionData, prefix, view);
        for (Object child : view.getPersistedChildren()) {
            View node = (View) child;
            checkDeprecatedStyle(node);
            removeOption(optionData, prefix, node);
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                checkDeprecatedStyle(edge);
                removeOption(optionData, prefix, edge);
                removeOptionRecursively(optionData, prefix, edge);
            }
            removeOptionRecursively(optionData, prefix, node);
        }
    }
    
    /** @deprecated TODO throw away after use */
    private void checkDeprecatedStyle(final View view) {
        LayoutOptionStyle style = (LayoutOptionStyle) view
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (style != null) {
            LayoutDataService dataService = LayoutDataService.getInstance();
            for (KOption koption : optionStyle.getOptions()) {
                LayoutOptionData<?> optionData = dataService.getOptionData(koption.getKey());
                if (optionData != null) {
                    String prefix = koption.isDefault() ? DIAG_PREFIX : PREFIX;
                    if (getOption(optionData, prefix, view) == null) {
                        setOption(optionData, koption.getValue(), prefix, view);
                    }
                }
            }
            removeOptionStyle(view);
            optionStyle = null;
            koptionMap = null;
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
        EditPart editPart = getEditPart();
        if (editPart instanceof IGraphicalEditPart) {
            View view = ((IGraphicalEditPart) editPart).getNotationView();
            String optionKey = PREFIX + optionData.getId();
            for (Object obj : view.getStyles()) {
                if (obj instanceof StringValueStyle) {
                    StringValueStyle style = (StringValueStyle) obj;
                    if (optionKey.equals(style.getName())) {
                        String value = style.getStringValue();
                        if (optionData.parseValue(value) != null) {
                            return false;
                        }
                    }
                }
            }
        }

        // TODO eliminate code based on LayoutOptionStyle
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
     * Returns the default value for the selected diagram.
     * 
     * @param <T> type of option
     * @param optionData a layout option
     * @return the current diagram-default value
     */
    public <T> T getDiagramDefault(final LayoutOptionData<T> optionData) {
        IGraphicalEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(getEditPart());
        if (diagramEditPart != null) {
            View diagram = diagramEditPart.getNotationView();
            T value = getOption(optionData, DIAG_PREFIX, diagram);

            // TODO eliminate code based on LayoutOptionStyle
            if (value != null) {
                return value;
            }
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
    public void setDiagramDefault(final LayoutOptionData<?> optionData, final Object value) {
        IGraphicalEditPart diagramEditPart = GmfFrameworkBridge.getDiagramEditPart(getEditPart());
        if (diagramEditPart != null) {
            View diagram = diagramEditPart.getNotationView();
            checkDeprecatedStyle(diagram);
            if (value != null) {
                removeOptionRecursively(optionData, PREFIX, diagram);
            }
            setOption(optionData, value, DIAG_PREFIX, diagram);
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
            // TODO eliminate code based on LayoutOptionStyle
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
            
            // add user defined global layout options
            if (diagramEditPart != null) {
                addOptions(options, DIAG_PREFIX, diagramEditPart.getNotationView());
            }
            // add user defined local layout options
            addOptions(options, PREFIX, editPart.getNotationView());
        }
    }

    /**
     * Add the options from the list of properties to the options map.
     * 
     * @param options map to add the options to
     * @param prefix the prefix for the property key
     * @param view a notation view
     */
    private static void addOptions(final Map<IProperty<?>, Object> options, final String prefix,
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
                            options.put(optionData, value);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Adds the layout options of the given layout options style to a map
     * of layout options.
     * 
     * @param options a map of layout options
     * @param optionStyle a layout options style
     * @param onlyDefault if true, only default options are taken
     * @deprecated use {@link org.eclipse.gmf.runtime.notation.StringValueStyle} instead
     */
    private static void addOptions(final Map<IProperty<?>, Object> options,
            final LayoutOptionStyle optionStyle, final boolean onlyDefault) {
        LayoutDataService layoutServices = LayoutDataService.getInstance();
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
