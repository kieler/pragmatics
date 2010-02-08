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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * Utility methods used for the KIML UI.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class KimlUiUtil {
    
    /**
     * Hidden constructor.
     */
    private KimlUiUtil() {
    }
    
    /**
     * Determines the insets for a parent figure, relative to the given child.
     * 
     * @param parent the figure of a parent edit part
     * @param child the figure of a child edit part
     * @return the insets to add to the relative coordinates of the child
     */
    public static Insets calcInsets(final IFigure parent, final IFigure child) {
        Insets result = new Insets(0);
        IFigure currentChild = child;
        IFigure currentParent = child.getParent();
        Point coordsToAdd = null;
        boolean isRelative = false;
        while (currentChild != parent && currentParent != null) {
            if (currentParent.isCoordinateSystem()) {
                isRelative = true;
                result.add(currentParent.getInsets());
                if (coordsToAdd != null) {
                    result.left += coordsToAdd.x;
                    result.top += coordsToAdd.y;
                }
                coordsToAdd = currentParent.getBounds().getLocation();
            } else if (currentParent == parent && coordsToAdd != null) {
                Point parentCoords = parent.getBounds().getLocation();
                result.left += coordsToAdd.x - parentCoords.x;
                result.top += coordsToAdd.y - parentCoords.y;
            }
            currentChild = currentParent;
            currentParent = currentChild.getParent();
        }
        if (!isRelative) {
            Rectangle parentBounds = parent.getBounds();
            Rectangle containerBounds = child.getParent().getBounds();
            result.left = containerBounds.x - parentBounds.x;
            result.top = containerBounds.y - parentBounds.y;
        }
        result.right = result.left;
        result.bottom = result.left;
        return result;
    }
    
    /**
     * Determines whether the position of the given child figure is
     * relative to the position of the given parent figure. 
     * 
     * @param parent the figure of a parent edit part
     * @param child the figure of a child edit part
     * @return true if the child position is relative to the parent
     */
    public static boolean isRelative(final IFigure parent, final IFigure child) {
        IFigure currentChild = child;
        IFigure currentParent = child.getParent();
        while (currentChild != parent && currentParent != null) {
            if (currentParent.isCoordinateSystem()) {
                return true;
            }
            currentChild = currentParent;
            currentParent = currentChild.getParent();
        }
        return false;
    }
    
    /**
     * Retrieves the default value for the given layout option.
     * 
     * @param optionData a layout option data
     * @param providerData the active layout provider data
     * @param editPart the current edit part
     * @param containerEditPart the edit part that contains the objects for
     *     which options are set
     * @param hasChildren indicates whether the given edit part has children
     *     in the layout graph
     * @return an object with the default value
     */
    public static Object getDefault(final LayoutOptionData optionData,
            final LayoutProviderData providerData, final EditPart editPart,
            final EditPart containerEditPart, final boolean hasChildren) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        Object result = null;
        // check default option of diagram edit part
        DiagramEditPart diagramEditPart = getDiagramEditPart(containerEditPart);
        if (diagramEditPart != null) {
            KOption koption = getKOption(diagramEditPart, optionData.getId());
            if (koption != null && koption.isDefault()) {
                return KimlLayoutUtil.getValue(koption, optionData);
            }
        }
        
        if (editPart != null) {
            // check default value set for the actual edit part or its model element
            result = getOption(editPart, optionData.getId());
            if (result != null) {
                return result;
            }
        }

        if (containerEditPart != null) {
            // check default option of the diagram type
            String diagramType = (String) getOption(containerEditPart, LayoutOptions.DIAGRAM_TYPE);
            result = layoutServices.getOption(diagramType, optionData.getId());
            if (result != null) {
                return result;
            }
    
            // check default value for the container edit part
            result = getOption(containerEditPart, optionData.getId());
            if (result != null) {
                return result;
            }
        }
        
        // fall back to default value of specific options
        if (LayoutOptions.FIXED_SIZE.equals(optionData.getId())) {
            return Boolean.valueOf(!hasChildren);
        } else if (LayoutOptions.PORT_CONSTRAINTS.equals(optionData.getId())) {
            if (hasChildren) {
                return PortConstraints.FREE_PORTS.ordinal();
            } else {
                return PortConstraints.FIXED_POS.ordinal();
            }
        }

        // fall back to default value of layout provider
        result = providerData != null ? providerData.getInstance().getDefault(
                optionData.getId()) : null;
        if (result != null) {
            return result;
        }
        
        // fall back to default-default value
        switch (optionData.getType()) {
        case STRING:
            return "";
        case BOOLEAN:
            return Boolean.FALSE;
        case ENUM:
        case INT:
            return Integer.valueOf(0);
        case FLOAT:
            return Float.valueOf(0.0f);
        default:
            return null;
        }
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
        String diagramType = (String) getOption(editPart, LayoutOptions.DIAGRAM_TYPE);
        Map<String, Object> options = new LinkedHashMap<String, Object>(
                layoutServices.getOptions(diagramType));
        
        // get default layout options for the edit part and its domain model
        for (Entry<String, Object> entry : layoutServices.getOptions(
                getClassName(editPart, true)).entrySet()) {
            if (entry.getValue() != null) {
                options.put(entry.getKey(), entry.getValue());
            }
        }
        for (Entry<String, Object> entry : layoutServices.getOptions(
                getClassName(editPart, false)).entrySet()) {
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
     * Returns the {@link KOption} with given key that is stored for the edit part.
     * 
     * @param editPart the edit part for which the option shall be fetched
     * @param optionId the identifier of the option
     * @return the corresponding option, or {@code null} if there is no such option
     */
    public static KOption getKOption(final IGraphicalEditPart editPart, final String optionId) {
        LayoutOptionStyle optionStyle = (LayoutOptionStyle) editPart.getNotationView()
                .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (koption.getKey().equals(optionId)) {
                    return koption;
                }
            }
        }
        return null;
    }
    
    /**
     * Performs the model changes specified in the given runnable in a safe context.
     * 
     * @param runnable a runnable that performs model changes
     * @param editingDomain the editing domain for the changes
     * @param label a user friendly label shown for the undo action
     */
    public static void runModelChange(final Runnable runnable,
            final TransactionalEditingDomain editingDomain, final String label) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain, label) {
            protected void doExecute() {
                runnable.run();
            }
        });
    }
    
    /**
     * Adds a {@link KOption} to the given layout option style. This operation must be run in
     * a safe context; use {@link #runModelChange} to achieve this.
     * 
     * @param optionStyle layout option style of a notation view
     * @param optionData the layout option data for which the {@code KOption} shall be created
     * @return the new {@code KOption}
     */
    public static KOption addKOption(final LayoutOptionStyle optionStyle,
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
     * Removes the {@link KOption} with given identifier from the notation view. This operation
     * must be run in a safe context; use {@link #runModelChange} to achieve this.
     * 
     * @param optionStyle layout option style of a notation view
     * @param optionId the layout option identifier for which the {@code KOption} shall be removed
     */
    public static void removeKOption(final LayoutOptionStyle optionStyle, final String optionId) {
        ListIterator<KOption> optionIter = optionStyle.getOptions().listIterator();
        while (optionIter.hasNext()) {
            if (optionIter.next().getKey().equals(optionId)) {
                optionIter.remove();
                break;
            }
        }
    }

    /**
     * Removes the {@link LayoutOptionStyle} from the notation view, if it exists. This
     * operation must be run in a safe context; use {@link #runModelChange} to achieve this.
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
     * Returns the class name for which to store layout options for the given edit part.
     * 
     * @param editPart an edit part
     * @param forDomainModel if true the class of the edit part's domain model is taken,
     *         else the edit part class itself is taken
     * @return the class name for layout options, or {@code null} if there is no such class name
     */
    public static String getClassName(final EditPart editPart, final boolean forDomainModel) {
        if (forDomainModel) {
            Object model = editPart.getModel();
            EObject emodel = null;
            if (model instanceof View) {
                emodel = ((View) model).getElement();
            } else if (model instanceof EObject) {
                emodel = (EObject) model;
            }
            if (emodel != null) {
                return emodel.eClass().getInstanceTypeName();
            } else {
                return null;
            }
        } else {
            return editPart.getClass().getName();
        }
    }
    
    /**
     * Retrieves a layout option from the given edit part by querying the option
     * for the edit part's class name and its domain model name.
     * 
     * @param editPart an edit part
     * @param optionId layout option identifier
     * @return the current value for the given option
     */
    public static Object getOption(final EditPart editPart, final String optionId) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        Object value = layoutServices.getOption(getClassName(editPart, false), optionId);
        if (value != null) {
            return value;
        } else {
            return layoutServices.getOption(getClassName(editPart, true), optionId);
        }
    }
    
    /**
     * Determines whether the given edit part should not be layouted.
     * 
     * @param editPart an edit part
     * @return true if no layout should be performed for the edit part
     */
    public static boolean isNoLayout(final EditPart editPart) {
        String diagramType = (String) getOption(editPart, LayoutOptions.DIAGRAM_TYPE);
        if (diagramType != null) {
            return diagramType.equals(LayoutServices.DIAGRAM_TYPE_NOLAYOUT);
        }
        return false;
    }

}
