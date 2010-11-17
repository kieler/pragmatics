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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;

import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.util.CustomPortLocator;

/**
 * @author ckru        
 */
public class KaomPortProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object) {
        if (input.equals("UP")) {
            return createSvg(getBlackUpwardsPortSvgString());
        } else if (input.equals("DOWN")) {
            return createSvg(getWhiteDownwardsPortSvgString());
        } else if (input.equals("white")) {
            return createSvg(getWhitePortSvgString());
        } else if (input.equals("gray")) {
            return createSvg(getGrayPortSvgString());
        } else {       
            return createSvg(getBlackPortSvgString());
        }
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

    private String getWhitePortSvgString() {
        return "<svg width=\"5\" height=\"5\">"
                + "<polygon points=\"0,4.5 0,0 4.5,2.2 0,4.5\" style=\"fill:white;stroke:black;stroke-width:1\" />"
                + "</svg>";
    }
    
    private String getBlackPortSvgString() {
        return "<svg width=\"5\" height=\"5\">"
                + "<polygon points=\"0,4.5 0,0 4.5,2.2 0,4.5\" style=\"fill:black;stroke:black;stroke-width:1\" />"
                + "</svg>";
    }
    
    private String getGrayPortSvgString() {
        return "<svg width=\"5\" height=\"5\">"
                + "<polygon points=\"0,4.5 0,0 4.5,2.2 0,4.5\" style=\"fill:gray;stroke:black;stroke-width:1\" />"
                + "</svg>";
    }

    private String getWhiteDownwardsPortSvgString() {
        return "<svg width=\"5\" height=\"5\">"
                + "<polygon points=\"0,0 4.5,0 2.2,4.5 0,0\" style=\"fill:white;stroke:black;stroke-width:1\" />"
                + "</svg>";
    }
    
    private String getBlackUpwardsPortSvgString() {
        return "<svg width=\"5\" height=\"5\">"
                + "<polygon points=\"0,4.5 4.5,4.5 2.2,0 0,4.5\" style=\"fill:black;stroke:black;stroke-width:1\" />"
                + "</svg>";
    }
    
    /**
     * method for generating a scalable image figure from a file.
     * 
     * @param svgString
     *            the string representing the svg image
     * @return a scalable image figure
     */
    private IFigure createSvg(final String svgString) {
        RenderedImage img = RenderedImageFactory.getInstance(svgString.getBytes());
        ScalableImageFigure fig = new ScalableImageFigure(img, false, true, true);
        return fig;
    }

    /**
     * {@inheritDoc}
     */
    public LayoutManager getLayoutManagerByString(final String input,
            final LayoutManager oldLayoutManager, final EObject object) {
        // TODO Auto-generated method stub
        return null;
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
        if (locator instanceof CustomPortLocator) {
            CustomPortLocator borderItemLocator = (CustomPortLocator) locator;
            if (borderItemLocator.getPublicConstraint().x == 0) {
                if (input.equals("NORTH")) {
                    BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                            PositionConstants.NORTH);
                    return newlocator;
                } else if (input.equals("EAST")) {
                    BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                            PositionConstants.EAST);
                    return newlocator;
                } else if (input.equals("SOUTH")) {

                    BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                            PositionConstants.SOUTH);
                    return newlocator;
                } else if (input.equals("WEST")) {
                    BorderItemLocator newlocator = new BorderItemLocator(parentFigure,
                            PositionConstants.WEST);
                    return newlocator;
                }
            }

        }
        return null;
    }
}
