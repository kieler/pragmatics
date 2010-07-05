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
package de.cau.cs.kieler.kiml.ui.util;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;

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
     * Retrieves a layout option from the given edit part by using the layout inspector
     * associated with the edit part type.
     * 
     * @param editPart an edit part
     * @param optionId layout option identifier
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final EditPart editPart, final String optionId) {
        ILayoutInspector inspector = EclipseLayoutServices.getInstance().getInspector(editPart);
        return getOption(inspector, optionId);
    }

    /**
     * Retrieves a layout option from the given layout inspector by querying the option
     * for the edit part's class name and its domain model name. 
     * 
     * @param inspector a layout inspector for an edit part
     * @param optionId layout option identifier
     * @return the current value for the given option, or {@code null}
     */
    public static Object getOption(final ILayoutInspector inspector, final String optionId) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        EditPart editPart = inspector.getFocusPart();
        String clazzName = editPart == null ? null : editPart.getClass().getName();
        Object value = layoutServices.getOption(clazzName, optionId);
        if (value != null) {
            return value;
        } else {
            EObject model = inspector.getFocusModel();
            clazzName = model == null ? null : model.eClass().getInstanceTypeName();
            return layoutServices.getOption(clazzName, optionId);
        }        
    }
    
    /**
     * Determines whether the given edit part should not be layouted.
     * 
     * @param editPart an edit part
     * @return true if no layout should be performed for the edit part
     */
    public static boolean isNoLayout(final EditPart editPart) {
        Boolean result = (Boolean) getOption(editPart, LayoutOptions.NO_LAYOUT);
        if (result != null) {
            return result;
        } else {
            return false;
        }
    }

    /**
     * Retrieves a suitable layout option data instance that matches the given
     * user friendly display name.
     * 
     * @param providerDataArray array of applicable layout provider data
     * @param displayName display name of the layout option as seen by the user
     * @return the most suitable layout option data
     */
    public static LayoutOptionData getOptionData(final LayoutProviderData[] providerDataArray,
            final String displayName) {
        for (LayoutProviderData providerData : providerDataArray) {
            LayoutOptionData optionData = EclipseLayoutServices.getInstance().getOptionData(
                    providerData, displayName);
            if (optionData != null) {
                return optionData;
            }
        }
        // the only option data that is added without explicit support by layouters is layout hint
        return LayoutServices.getInstance().getLayoutOptionData(LayoutOptions.LAYOUT_HINT);
    }

}
