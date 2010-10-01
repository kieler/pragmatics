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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IFigure getDefaultFigure() {
        // TODO Auto-generated method stub
        return null;
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

        return null;
    }

}
