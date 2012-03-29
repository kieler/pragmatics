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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

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
            currentParent = child.getParent();
            Rectangle containerBounds = currentParent.getBounds();
//            while (currentParent != parent
//                    && (containerBounds.width + result.left > parentBounds.width
//                    || containerBounds.height + result.top > parentBounds.height)) {
//                currentParent = currentParent.getParent();
//                containerBounds = currentParent.getBounds();
//            }
            result.left = containerBounds.x - parentBounds.x;
            result.top = containerBounds.y - parentBounds.y;
        }
        // FIXME in theory it would be better to get the bottom and right insets from the size;
        // however, due to the inpredictability of Draw2D layout managers, this leads to
        // bad results in many cases, so a fixed insets value is better
        result.right = result.left;
        result.bottom = result.left;
        return result;
    }
    
    /**
     * Calculates the absolute bounds of the given figure.
     * 
     * @param figure a figure
     * @return the absolute bounds
     */
    public static Rectangle getAbsoluteBounds(final IFigure figure) {
        Rectangle bounds = new Rectangle(figure.getBounds()) {
            static final long serialVersionUID = 1;
            @Override
            public void performScale(final double factor) {
                // don't perform any scaling to avoid distortion by the zoom level
            }
        };
        figure.translateToAbsolute(bounds);
        return bounds;
    }
    
    /**
     * Calculates an absolute position for one of the bend points of the given connection.
     * 
     * @param connection a connection figure
     * @param index the index in the point list
     * @return the absolute point
     */
    public static Point getAbsolutePoint(final Connection connection, final int index) {
        Point point = new Point(connection.getPoints().getPoint(index)) {
            static final long serialVersionUID = 1;
            @Override
            public void performScale(final double factor) {
                // don't perform any scaling to avoid distortion by the zoom level
            }
        };
        connection.translateToAbsolute(point);
        return point;
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
     * Returns the layout option data that matches the given user-friendly name and is known by the
     * given layout provider.
     * 
     * @param providerData a layout provider data
     * @param optionName user-friendly name of a layout option
     * @return the corresponding layout option data
     */
    public static LayoutOptionData<?> getOptionData(final LayoutAlgorithmData providerData,
            final String optionName) {
        for (LayoutOptionData<?> data : LayoutDataService.getInstance().getOptionData()) {
            if (data.getName().equals(optionName) && providerData.knowsOption(data)) {
                return data;
            }
        }
        return null;
    }

    /**
     * Retrieves a suitable layout option data instance that matches the given
     * user friendly display name.
     * 
     * @param providerDataArray array of applicable layout provider data
     * @param displayName display name of the layout option as seen by the user
     * @return the most suitable layout option data
     */
    public static LayoutOptionData<?> getOptionData(final LayoutAlgorithmData[] providerDataArray,
            final String displayName) {
        for (LayoutAlgorithmData providerData : providerDataArray) {
            LayoutOptionData<?> optionData = getOptionData(providerData, displayName);
            if (optionData != null) {
                return optionData;
            }
        }
        // the only option data that is added without explicit support by layouters is layout hint
        return LayoutDataService.getInstance().getOptionData(LayoutOptions.ALGORITHM.getId());
    }

}
