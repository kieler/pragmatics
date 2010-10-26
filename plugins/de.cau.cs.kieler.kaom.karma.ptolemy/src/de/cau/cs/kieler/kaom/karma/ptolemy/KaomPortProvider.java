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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.kaom.karma.ptolemy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;

import de.cau.cs.kieler.karma.IRenderingProvider;

/**
 * @author ckru
 * 
 * {@inheritDoc}
 */
public class KaomPortProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object) {
        // TODO Auto-generated method stub
        return null;//this.createSvg(getPortSvgString());
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDefaultFigure() {
        RectangleFigure defaultFigure = new RectangleFigure();
        defaultFigure.setLineWidth(1);
        defaultFigure.setForegroundColor(ColorConstants.black);
        defaultFigure.setBackgroundColor(ColorConstants.black);
        return defaultFigure;
    }
    
    private String getPortSvgString() {
        return "<svg width=\"7\" height=\"7\">"
            + "<polygon points=\"0,6 0,0 6,3 0,6\" style=\"fill:black;stroke:black;stroke-width:1\" />"
            + "</svg>";
    }
    
    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param file
     *            the file holding the svg image
     * @return a scalable image figure
     */
    private IFigure createSvg(final String file) {
        RenderedImage img = RenderedImageFactory.getInstance(file.getBytes());
        ScalableImageFigure fig = new ScalableImageFigure(img, false, true, true);
        return fig;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        // TODO Auto-generated method stub
        return null; // new KaomPortLayout();
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getDefaultLayoutManager() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public BorderItemLocator getBorderItemLocatorByString(final String input,
            final IFigure parentFigure, final Object locator, final EObject object) {
        if (locator instanceof BorderItemLocator) {
            BorderItemLocator borderItemLocator = (BorderItemLocator) locator;
        if (input.equals("NORTH")) {            
            if (borderItemLocator.getCurrentSideOfParent() != PositionConstants.NORTH) {
                BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                    PositionConstants.NORTH);
                return newlocator;
            }
           borderItemLocator.setPreferredSideOfParent(PositionConstants.NORTH);
           return borderItemLocator;
            
        } else if (input.equals("EAST")) {
            if (borderItemLocator.getCurrentSideOfParent() != PositionConstants.EAST) {
                BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                    PositionConstants.EAST);
                return newlocator;
            }
            return borderItemLocator;
        } else if (input.equals("SOUTH")) {
            if (borderItemLocator.getCurrentSideOfParent() != PositionConstants.SOUTH) {
                BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                    PositionConstants.SOUTH);
                return newlocator;
            }
            return borderItemLocator;
        } else if (input.equals("WEST")) {
            
            if (borderItemLocator.getCurrentSideOfParent() != PositionConstants.WEST) {
                BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                    PositionConstants.WEST);
                return newlocator;
            }
            return borderItemLocator;
        }

        }
        return null;
    }

}
