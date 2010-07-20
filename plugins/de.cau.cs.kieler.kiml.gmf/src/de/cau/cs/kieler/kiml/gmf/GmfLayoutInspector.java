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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionsFactory;
import de.cau.cs.kieler.kiml.gmf.layoutoptions.LayoutOptionsPackage;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 *
 * @author msp
 */
public class GmfLayoutInspector implements ILayoutInspector {

    /** list of layout option data. */
    private List<LayoutOptionData> optionDataList;
    /** the layout option style stored in the notation view. */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style. */
    private Map<LayoutOptionData, KOption> koptionMap = new HashMap<LayoutOptionData, KOption>();
    /** layout provider data of the shown edit part. */
    private LayoutProviderData partProviderData;
    /** layout provider data of the containing edit part. */
    private LayoutProviderData containerProviderData;
    /** the edit part for which options are shown. */
    private IGraphicalEditPart focusEditPart;
    /** the edit part that contains the sub-diagram with the shown edit part. */
    private IGraphicalEditPart containerEditPart;
    /** the edit part that specifies options for the children of the shown edit part. */
    private IGraphicalEditPart containmentEditPart;

    /**
     * Finds the diagram edit part of an edit part.
     * 
     * @param editPart an edit part
     * @return the diagram edit part, or {@code null} if there is no containing diagram
     *     edit part
     */
    public static DiagramEditPart getDiagramEditPart(final EditPart editPart) {
        EditPart ep = editPart;
        while (ep != null && !(ep instanceof DiagramEditPart) && !(ep instanceof RootEditPart)) {
            ep = ep.getParent();
        }
        if (ep instanceof RootEditPart) {
            RootEditPart root = (RootEditPart) ep;
            ep = null;
            for (Object child : root.getChildren()) {
                if (child instanceof DiagramEditPart) {
                    ep = (EditPart) child;
                }
            }
        }
        return (DiagramEditPart) ep;
    }
    
    /**
     * Sets all predefined and user defined layout options for the given edit part.
     * 
     * @param editPart edit part for which options are set
     * @param layoutData layout data where options are written
     * @param setUserOptions if true, the user defined options are also set
     */
    public static void setLayoutOptions(final IGraphicalEditPart editPart,
            final KLayoutData layoutData, final boolean setUserOptions) {
        LayoutServices layoutServices = LayoutServices.getInstance();

        // get default layout options for the diagram type
        String diagramType = (String) KimlUiUtil.getOption(editPart, LayoutOptions.DIAGRAM_TYPE);
        Map<String, Object> options = new LinkedHashMap<String, Object>(
                layoutServices.getOptions(diagramType));
        
        // get default layout options for the edit part and its domain model
        EObject element = editPart.getNotationView().getElement();
        if (element != null) {
            String clazzName = element.eClass().getInstanceTypeName();
            for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
                if (entry.getValue() != null) {
                    options.put(entry.getKey(), entry.getValue());
                }
            }
        }
        String clazzName = editPart.getClass().getName();
        for (Entry<String, Object> entry : layoutServices.getOptions(clazzName).entrySet()) {
            if (entry.getValue() != null) {
                options.put(entry.getKey(), entry.getValue());
            }
        }
        
        if (setUserOptions) {
            // get user defined global layout options
            DiagramEditPart diagramEditPart = getDiagramEditPart(editPart);
            if (diagramEditPart != editPart && diagramEditPart != null) {
                LayoutOptionStyle optionStyle = (LayoutOptionStyle) diagramEditPart.getNotationView()
                        .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                addOptions(options, optionStyle, true);
            }
            
            // get user defined local layout options
            LayoutOptionStyle optionStyle = (LayoutOptionStyle) editPart.getNotationView().getStyle(
                    LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            addOptions(options, optionStyle, false);
        }
        
        // add all options to the layout data instance
        for (Entry<String, Object> option : options.entrySet()) {
            LayoutOptionData optionData = layoutServices.getLayoutOptionData(option.getKey());
            if (option.getValue() != null) {
                optionData.setValue(layoutData, option.getValue());
            }
        }
    }
    
    /**
     * Returns the {@link KOption} with given key that is stored for the edit part.
     * 
     * @param editPart the edit part for which the option shall be fetched
     * @param optionId the identifier of the option
     * @return the corresponding option, or {@code null} if there is no such option
     */
    private static KOption getKOption(final IGraphicalEditPart editPart, final String optionId) {
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
     * Adds the layout options of the given layout options style to a map
     * of layout options.
     * 
     * @param options a map of layout options
     * @param optionStyle a layout options style
     * @param onlyDefault if true, only default options are taken
     */
    private static void addOptions(final Map<String, Object> options,
            final LayoutOptionStyle optionStyle, final boolean onlyDefault) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        if (optionStyle != null) {
            for (KOption option : optionStyle.getOptions()) {
                if (!onlyDefault || option.isDefault()) {
                    LayoutOptionData optionData = layoutServices.getLayoutOptionData(
                            option.getKey());
                    Object value = KimlLayoutUtil.getValue(option, optionData);
                    if (value != null) {
                        options.put(option.getKey(), value);
                    }
                }
            }
        }
    }
    
    /**
     * Adds a {@link KOption} to the given layout option style. This operation must be run in
     * a safe context; use {@link #runModelChange} to achieve this.
     * 
     * @param optionStyle layout option style of a notation view
     * @param optionData the layout option data for which the {@code KOption} shall be created
     * @return the new {@code KOption}
     */
    private static KOption addKOption(final LayoutOptionStyle optionStyle,
            final LayoutOptionData optionData) {
        KOption koption = null;
        switch (optionData.getType()) {
        case STRING:
            koption = KLayoutDataFactory.eINSTANCE.createKStringOption();
            break;
        case BOOLEAN:
            koption = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
            break;
        case ENUM:
        case INT:
            koption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            break;
        case FLOAT:
            koption = KLayoutDataFactory.eINSTANCE.createKFloatOption();
            break;
        default:
            return null;
        }
        koption.setKey(optionData.getId());
        optionStyle.getOptions().add(koption);
        return koption;
    }

    /**
     * Removes the {@link LayoutOptionStyle} from the notation view, if it exists. This
     * operation must be run in a safe context; use {@link #runModelChange} to achieve this.
     * 
     * @param view the notation view from which to remove the layout option style
     */
    private static void removeOptionStyle(final View view) {
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
     * Creates a layout inspector for a GMF graphical edit part.
     * 
     * @param editPart a graphical edit part
     */
    public GmfLayoutInspector(final IGraphicalEditPart editPart) {
        if (editPart == null) {
            throw new NullPointerException("editPart");
        }
        focusEditPart = editPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public void initOptions() {
        // find an appropriate property source and set the layout option targets
        focusEditPart = getShownEditPart((IGraphicalEditPart) focusEditPart);
        LayoutOptionData.Target partTarget = findTarget(focusEditPart);
        // check if the selected edit part is not supported
        if (partTarget == null) {
            optionDataList = Collections.emptyList();
            return;
        }
        
        // get default options from the notation view
        optionStyle = (LayoutOptionStyle) focusEditPart.getNotationView().getStyle(
                LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        String partLayoutHint = getNotationOptions();
        
        // create the list of shown layout options
        createShownOptions(partTarget, partLayoutHint);
    }
    
    /**
     * Returns the edit part for which layout options would be shown by this property
     * source.
     * 
     * @param sourceEditPart the source edit part
     * @return the shown edit part, or {@code null} if there is none
     */
    private IGraphicalEditPart getShownEditPart(final IGraphicalEditPart sourceEditPart) {
        if (sourceEditPart instanceof CompartmentEditPart) {
            return (IGraphicalEditPart) sourceEditPart.getParent();
        }
        if (sourceEditPart instanceof ShapeNodeEditPart) {
            if (KimlUiUtil.isNoLayout(sourceEditPart)
                    || KimlUiUtil.isNoLayout(sourceEditPart.getParent())) {
                return null;
            }
        }
        return sourceEditPart;
    }
    
    /**
     * Determines the type of edit part target for the layout options.
     * 
     * @param editPart an edit part
     * @return the edit part target
     */
    private LayoutOptionData.Target findTarget(final IGraphicalEditPart editPart) {
        LayoutOptionData.Target partTarget = null;
        if (editPart instanceof AbstractBorderItemEditPart) {
            partTarget = LayoutOptionData.Target.PORTS;
            containerEditPart = (IGraphicalEditPart) editPart.getParent().getParent();
        } else if (editPart instanceof ShapeNodeEditPart) {
            // check whether the node is a parent
            partTarget = LayoutOptionData.Target.NODES;
            containerEditPart = (IGraphicalEditPart) editPart.getParent();
            if (findContainingEditPart(editPart) != null) {
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
            containerEditPart = editPart;
            containmentEditPart = editPart;
        }
        if (containerEditPart instanceof CompartmentEditPart) {
            containerEditPart = (IGraphicalEditPart) containerEditPart.getParent();
        }
        return partTarget;
    }
    
    /**
     * Finds the edit part that contains layoutable children, if there are any. The returned
     * edit part is either the parent edit part itself or one of its compartments. 
     * 
     * @param editPart a node edit part
     * @return the edit part that contains other node edit parts, or {@code null} if there is none
     */
    private IGraphicalEditPart findContainingEditPart(final IGraphicalEditPart editPart) {
        for (Object child : editPart.getChildren()) {
            if (child instanceof ShapeNodeEditPart
                    && !(child instanceof AbstractBorderItemEditPart)
                    && !KimlUiUtil.isNoLayout((EditPart) child)) {
                return editPart;
            } else if (child instanceof CompartmentEditPart
                    && !KimlUiUtil.isNoLayout((EditPart) child)) {
                for (Object grandChild : ((CompartmentEditPart) child).getChildren()) {
                    if (grandChild instanceof ShapeNodeEditPart
                            && !KimlUiUtil.isNoLayout((EditPart) grandChild)) {
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
     * @return the layout hint for the selected edit part
     */
    private String getNotationOptions() {
        String partLayoutHint = null;
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.LAYOUT_HINT.equals(koption.getKey())) {
                    partLayoutHint = ((KStringOption) koption).getValue();
                }
                LayoutOptionData optionData = LayoutServices.getInstance()
                        .getLayoutOptionData(koption.getKey());
                koptionMap.put(optionData, koption);
            }
        }
        if (partLayoutHint == null) {
            DiagramEditPart diagramEditPart = getDiagramEditPart(containerEditPart);
            if (diagramEditPart != null) {
                KOption koption = getKOption(diagramEditPart,
                        LayoutOptions.LAYOUT_HINT);
                if (koption != null && koption.isDefault()) {
                    partLayoutHint = ((KStringOption) koption).getValue();
                }
            }
        }
        return partLayoutHint;
    }
    
    /**
     * Create the layout options to show in the layout view for this property source.
     * 
     * @param partTarget the edit part target
     * @param partLayoutHint the layout hint for the selected edit part
     */
    private void createShownOptions(final LayoutOptionData.Target partTarget,
            final String partLayoutHint) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        KOption containerLayoutHintOption = getKOption(
                (containerEditPart instanceof CompartmentEditPart
                        ? (IGraphicalEditPart) containerEditPart.getParent() : containerEditPart),
                LayoutOptions.LAYOUT_HINT);
        if (containerLayoutHintOption == null) {
            DiagramEditPart diagramEditPart = getDiagramEditPart(containerEditPart);
            if (diagramEditPart != null) {
                KOption koption = getKOption(diagramEditPart,
                        LayoutOptions.LAYOUT_HINT);
                if (koption != null && koption.isDefault()) {
                    containerLayoutHintOption = koption;
                }
            }
        }
        String containerLayoutHint = containerLayoutHintOption instanceof KStringOption
                ? ((KStringOption) containerLayoutHintOption).getValue() : null;
        String containerDiagramType = (String) KimlUiUtil.getOption(containerEditPart,
                LayoutOptions.DIAGRAM_TYPE);
        containerProviderData = layoutServices.getLayoutProviderData(
                containerLayoutHint, containerDiagramType);
        LayoutViewPart layoutView = LayoutViewPart.findView();
        if (containerProviderData == null) {
            optionDataList = Collections.emptyList();
            if (layoutView != null) {
                layoutView.setCurrentProviderData(new LayoutProviderData[0]);
            }
        } else {
            optionDataList = layoutServices.getLayoutOptions(containerProviderData, partTarget);
            if (partTarget == LayoutOptionData.Target.PARENTS) {
                partProviderData = containerProviderData;
            } else if (containmentEditPart != null) {
                String childCompartmentDiagramType = (String) KimlUiUtil.getOption(
                        containmentEditPart, LayoutOptions.DIAGRAM_TYPE);
                partProviderData = layoutServices.getLayoutProviderData(
                        partLayoutHint, childCompartmentDiagramType);
                optionDataList.addAll(layoutServices.getLayoutOptions(partProviderData,
                        LayoutOptionData.Target.PARENTS));
            }
            if (layoutView != null) {
                if (partProviderData == containerProviderData) {
                    layoutView.setCurrentProviderData(new LayoutProviderData[]
                             {containerProviderData});
                } else {
                    layoutView.setCurrentProviderData(new LayoutProviderData[]
                             {containerProviderData, partProviderData});
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<LayoutOptionData> getOptionData() {
        return optionDataList;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public KOption getKOption(final LayoutOptionData optionData, final boolean create) {
        KOption koption = koptionMap.get(optionData);
        if (koption == null && create) {
            if (optionStyle == null) {
                optionStyle = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
                focusEditPart.getNotationView().getStyles().add(optionStyle);
            }
            koption = addKOption(optionStyle, optionData);
            koptionMap.put(optionData, koption);
        }
        return koption;
    }
    
    /**
     * {@inheritDoc}
     */
    public void removeKOption(final LayoutOptionData optionData) {
        removeKOption(optionStyle, optionData);
        koptionMap.remove(optionData);
    }
    
    /**
     * Removes an option from the given style container.
     * 
     * @param style a layout option style
     * @param optionData a layout option data
     */
    private void removeKOption(final LayoutOptionStyle style, final LayoutOptionData optionData) {
        ListIterator<KOption> optionIter = style.getOptions().listIterator();
        while (optionIter.hasNext()) {
            if (optionIter.next().getKey().equals(optionData.getId())) {
                optionIter.remove();
                break;
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void removeAllKOptions() {
        View view = focusEditPart.getNotationView();
        if (view instanceof Diagram) {
            Diagram diagram = (Diagram) view;
            for (Object edge : diagram.getPersistedEdges()) {
                removeOptionStyle((View) edge);
            }
        }
        removeChildOptions(view);
    }
    
    /**
     * Removes all layout options from the given node and its children.
     * 
     * @param node a node from the notation model
     */
    private void removeChildOptions(final View node) {
        removeOptionStyle(node);
        for (Object child : node.getPersistedChildren()) {
            removeChildOptions((View) child);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public EditPart getFocusPart() {
        return focusEditPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public EObject getFocusModel() {
        return focusEditPart.getNotationView().getElement();
    }
    
    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getFocusLayouterData() {
        return partProviderData;
    }
    
    /**
     * {@inheritDoc}
     */
    public EditPart getContainerPart() {
        return containerEditPart;
    }
    
    /**
     * {@inheritDoc}
     */
    public LayoutProviderData getContainerLayouterData() {
        return containerProviderData;
    }
    
    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain getEditingDomain() {
        return focusEditPart.getEditingDomain();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void setDefault(final LayoutOptionData optionData, final Object value) {
        View notationView = focusEditPart.getNotationView();
        
        // get the layout option style
        LayoutOptionStyle style = (LayoutOptionStyle) notationView
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (style == null) {
            style = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
            notationView.getStyles().add(style);
        }
        
        // get the layout option
        KOption koption = null;
        for (KOption opt : style.getOptions()) {
            if (opt.getKey().equals(optionData.getId())) {
                koption = opt;
                break;
            }
        }
        if (koption == null) {
            koption = addKOption(style, optionData);
        }
        
        // set the new option value
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
            KimlLayoutUtil.setValue(koption, optionData,
                    LayoutPropertySource.getLayoutHint((String) value));
        } else {
            KimlLayoutUtil.setValue(koption, optionData, value);
        }
        koption.setDefault(true);
        
        // remove the option from all children
        removeChildOptions(notationView, optionData);
    }
    
    /**
     * Removes all options with given id from the children of the notation view recursively.
     * 
     * @param notationView a notation view
     * @param optionData layout option data
     */
    private void removeChildOptions(final View notationView, final LayoutOptionData optionData) {
        for (Object child : notationView.getPersistedChildren()) {
            View node = (View) child;
            LayoutOptionStyle style = (LayoutOptionStyle) node.getStyle(
                    LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (style != null) {
                removeKOption(style, optionData);
            }
            for (Object edgeObj : node.getTargetEdges()) {
                View edge = (View) edgeObj;
                style = (LayoutOptionStyle) edge.getStyle(
                        LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
                if (style != null) {
                    removeKOption(style, optionData);
                }
                removeChildOptions(edge, optionData);
            }
            removeChildOptions(node, optionData);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final LayoutOptionData optionData) {
        IGraphicalEditPart diagramEditPart = getDiagramEditPart(focusEditPart);
        LayoutOptionStyle style = (LayoutOptionStyle) diagramEditPart.getNotationView()
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (style != null) {
            for (KOption koption : style.getOptions()) {
                if (koption.getKey().equals(optionData.getId())
                        && koption.isDefault()) {
                    return KimlLayoutUtil.getValue(koption, optionData);
                }
            }
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public IFigure getDrawingLayer() {
        return (getDiagramEditPart(focusEditPart))
                .getLayer(DiagramRootEditPart.PAGE_BREAKS_LAYER);
    }
    
}
