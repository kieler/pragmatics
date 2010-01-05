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
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsFactory;
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
     * @return an object with the default value
     */
    public static Object getDefault(final LayoutOptionData optionData,
            final LayoutProviderData providerData, final EditPart editPart) {
        Object result = null;
        // check default option of diagram edit part
        DiagramEditPart diagramEditPart = getDiagramEditPart(editPart);
        if (diagramEditPart != null) {
            KOption koption = getKOption(diagramEditPart, optionData.getId());
            if (koption != null && koption.isDefault()) {
                return getValue(koption, optionData);
            }
        }

        // check default value set for the edit part
        result = editPart != null ? LayoutServices.getInstance().getOption(
                editPart.getClass(), optionData.getId()) : null;
        if (result != null) {
            if (result instanceof Enum<?>) {
                return ((Enum<?>)result).ordinal();
            } else {
                return result;
            }
        }

        // check default value of layout provider
        result = providerData != null ? providerData.getInstance().getDefault(
                optionData.getId()) : null;
        if (result != null) {
            if (result instanceof Enum<?>) {
                return ((Enum<?>)result).ordinal();
            } else {
                return result;
            }
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
     */
    public static void setLayoutOptions(final IGraphicalEditPart editPart,
            final KLayoutData layoutData) {
        // get preconfigured layout options
        Map<String, Object> options = new LinkedHashMap<String, Object>(
                LayoutServices.getInstance().getOptions(editPart.getClass()));
        
        // get global layout options
        DiagramEditPart diagramEditPart = getDiagramEditPart(editPart);
        if (diagramEditPart != editPart && diagramEditPart != null) {
            LayoutOptionStyle optionStyle = (LayoutOptionStyle) diagramEditPart.getNotationView()
                    .getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
            if (optionStyle != null) {
                for (KOption option : optionStyle.getOptions()) {
                    if (option.isDefault()) {
                        LayoutOptionData optionData = LayoutServices.getInstance()
                                .getLayoutOptionData(option.getKey());
                        options.put(option.getKey(), KimlUiUtil.getValue(option, optionData));
                    }
                }
            }
        }
        
        // get user defined layout options
        LayoutOptionStyle optionStyle = (LayoutOptionStyle) editPart.getNotationView().getStyle(
                LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption option : optionStyle.getOptions()) {
                LayoutOptionData optionData = LayoutServices.getInstance()
                        .getLayoutOptionData(option.getKey());
                options.put(option.getKey(), KimlUiUtil.getValue(option, optionData));                
            }
        }
        
        // add all options to the layout data instance
        for (Entry<String, Object> option : options.entrySet()) {
            LayoutOptionData optionData = LayoutServices.getInstance()
                    .getLayoutOptionData(option.getKey());
            optionData.setValue(layoutData, option.getValue());
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
        LayoutOptionStyle optionStyle = (LayoutOptionStyle)editPart.getNotationView()
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
     * Adds a layout option style to the given notation view or its domain element
     * by using the command stack.
     * 
     * @param notationView notation view of a graphical edit part
     * @param editingDomain the editing domain of the edit part
     * @return the new layout option style
     */
    public static LayoutOptionStyle addLayoutOptionStyle(final View notationView,
            final TransactionalEditingDomain editingDomain) {
        final Maybe<LayoutOptionStyle> optionStyleWrap = new Maybe<LayoutOptionStyle>();
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            @SuppressWarnings("unchecked")
            protected void doExecute() {
                optionStyleWrap.setObject(LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle());
//                EObject domainElement = notationView.getElement();
//                if (domainElement instanceof EModelElement) {
//                    optionStyleWrap.object.setSource(LayoutOptionStyle.class.getName());
//                    ((EModelElement)domainElement).getEAnnotations().add(optionStyleWrap.object);
//                }
//                else
                notationView.getStyles().add(optionStyleWrap.getObject());
            }
        });
        return optionStyleWrap.getObject();
    }
    
    /**
     * Adds a {@link KOption} to the given layout option style by using the command stack.
     * 
     * @param optionStyle layout option style of a notation view
     * @param optionData the layout option data for which the {@code KOption} shall be created
     * @param editingDomain the editing domain of the related edit part
     * @return the new {@code KOption}
     */
    public static KOption addKOption(final LayoutOptionStyle optionStyle,
            final LayoutOptionData optionData, final TransactionalEditingDomain editingDomain) {
        final Maybe<KOption> koptionWrap = new Maybe<KOption>();
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            protected void doExecute() {
                switch (optionData.getType()) {
                case STRING:
                    koptionWrap.setObject(KLayoutDataFactory.eINSTANCE.createKStringOption());
                    break;
                case BOOLEAN:
                    koptionWrap.setObject(KLayoutDataFactory.eINSTANCE.createKBooleanOption());
                    break;
                case ENUM:
                case INT:
                    koptionWrap.setObject(KLayoutDataFactory.eINSTANCE.createKIntOption());
                    break;
                case FLOAT:
                    koptionWrap.setObject(KLayoutDataFactory.eINSTANCE.createKFloatOption());
                    break;
                default:
                    return;
                }
                koptionWrap.getObject().setKey(optionData.getId());
                optionStyle.getOptions().add(koptionWrap.getObject());
            }
        });
        return koptionWrap.getObject();
    }

    /**
     * Returns the value of the given {@link KOption} as an {@code Object}.
     * 
     * @param koption the {@code KOption} for which the value shall be retrieved
     * @param optionData the layout option data related with the option
     * @return the current value of the option
     */
    public static Object getValue(final KOption koption, final LayoutOptionData optionData) {
        switch (optionData.getType()) {
        case STRING:
            return ((KStringOption)koption).getValue();
        case BOOLEAN:
            return Boolean.valueOf(((KBooleanOption)koption).isValue());
        case ENUM:
        case INT:
            return Integer.valueOf(((KIntOption)koption).getValue());
        case FLOAT:
            return Float.valueOf(((KFloatOption)koption).getValue());
        default:
            return null;
        }
    }
    
    /**
     * Sets the value of the given {@link KOption} by using the command stack.
     * 
     * @param koption the {@code KOption} for which the value shall be set
     * @param optionData the layout option data related with the option
     * @param value the new value of the option
     * @param editingDomain the editing domain of the related edit part
     */
    public static void setValue(final KOption koption, final LayoutOptionData optionData,
            final Object value, final TransactionalEditingDomain editingDomain) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            protected void doExecute() {
                switch (optionData.getType()) {
                case STRING:
                    ((KStringOption)koption).setValue((String)value);
                    break;
                case BOOLEAN:
                    ((KBooleanOption)koption).setValue(((Boolean)value).booleanValue());
                    break;
                case ENUM:
                case INT:
                    ((KIntOption)koption).setValue(((Integer)value).intValue());
                    break;
                case FLOAT:
                    ((KFloatOption)koption).setValue(((Float)value).floatValue());
                    break;
                }
            }
        });
    }
    
    /**
     * Sets the default status of the given {@link KOption} by using the command stack.
     * 
     * @param koption the {@code KOption} for which the status shall be set
     * @param isDefault the new default status
     * @param editingDomain the editing domain of the related edit part
     */
    public static void setDefault(final KOption koption, final boolean isDefault,
            final TransactionalEditingDomain editingDomain) {
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
           protected void doExecute() {
               koption.setDefault(isDefault);
           }
        });
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

}
