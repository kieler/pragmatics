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
package de.cau.cs.kieler.keg.custom;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * The figure provider for KEG figures.
 * 
 * @author mri
 */
public class KEGFigureProvider implements IRenderingProvider {
    
    private static final int ROUNDED_RECTANGLE_CORNER_WIDTH = 40;
    private static final int ROUNDED_RECTANGLE_CORNER_HEIGHT = 40;
    
    private static final Color BG_COLOR = new Color(null, 80, 230, 154);

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input,
            final IFigure oldFigure, final EObject object, final EditPart editPart) {
        if (input.equals("noPorts")) {
            return createNodeWithoutPortsFigure();
        }
        return createNodeFigure();
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDefaultFigure() {
        return createNodeFigure();
    }

    /**
     * Creates the figure for nodes with ports.
     * 
     * @return figure for nodes with ports
     */
    private IFigure createNodeFigure() {
        RectangleFigure figure = new RectangleFigure();
        figure.setOpaque(false);
        figure.setLineWidth(1);
        figure.setBackgroundColor(BG_COLOR);
        figure.setForegroundColor(ColorConstants.black);
        return figure;
    }

    /**
     * Creates the figure for nodes without ports.
     * 
     * @return figure for nodes without ports
     */
    private IFigure createNodeWithoutPortsFigure() {
        RoundedRectangle figure = new RoundedRectangle();
        figure.setCornerDimensions(new Dimension(
                ROUNDED_RECTANGLE_CORNER_WIDTH, ROUNDED_RECTANGLE_CORNER_HEIGHT));
        figure.setOpaque(false);
        figure.setLineWidth(1);
        figure.setBackgroundColor(BG_COLOR);
        figure.setForegroundColor(ColorConstants.black);
        return figure;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IBorderItemLocator getBorderItemLocatorByString(final String input,
            final IFigure parentFigure, final Object locator,
            final EObject object) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Dimension getSizeByString(final String input, final EObject object, final EditPart part) {
        return null;
    }

}
