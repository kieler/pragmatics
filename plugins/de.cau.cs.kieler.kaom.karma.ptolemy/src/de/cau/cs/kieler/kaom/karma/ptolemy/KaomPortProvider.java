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
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.ScalableImageFigure;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.impl.PortImpl;
import de.cau.cs.kieler.karma.IRenderingProvider;
import de.cau.cs.kieler.karma.util.CustomPortLocator;

/**
 * @author ckru
 * 
 *         {@inheritDoc}
 */
public class KaomPortProvider implements IRenderingProvider {

    /**
     * {@inheritDoc}
     */
    public IFigure getFigureByString(final String input, final IFigure oldFigure,
            final EObject object) {
        /*
        if (object instanceof Annotatable) {
            Annotatable ann = (Annotatable)object;
            StringAnnotation stringAnnotation = AnnotationsFactory.eINSTANCE.createStringAnnotation();
            stringAnnotation.setName("ptolemyClass");            
            stringAnnotation.setValue("ptolemy.kernel.Port");
            ann.getAnnotations().add(stringAnnotation);
            */
        //IFigure figure = KaomFigureProvider.createFigureFromIconDescription(object);
        //return figure;
        
        
        return null;
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
