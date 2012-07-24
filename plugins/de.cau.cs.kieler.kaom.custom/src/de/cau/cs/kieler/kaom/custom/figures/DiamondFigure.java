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
 *
 *****************************************************************************/
package de.cau.cs.kieler.kaom.custom.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class represents diamond figures.
 *
 * @author schm
 * @kieler.ignore (excluded from review process)
 */
public class DiamondFigure extends Shape {

    /**
     * Fill the diamond.
     *
     * @param graphics the graphics object
     */
    @Override
    protected final void fillShape(final Graphics graphics) {
        graphics.fillPolygon(getDiamond(this.getBounds()));
    }

     /**
     * Draw the outline of the diamond.
     *
     * @param graphics the graphics object
     */
    @Override
    protected final void outlineShape(final Graphics graphics) {
        graphics.drawPolygon(getDiamond(this.getBounds()));
    }

    /**
     * Calculate the diamond based on the given bounds.
     *
     * @param bounds the rectangular bounds of the resulting diamond
     * @return the points of the diamond
     */
    protected final PointList getDiamond(final Rectangle bounds) {
        PointList points = new PointList();
        points.addPoint(bounds.x + bounds.width / 2, bounds.y);
        points.addPoint(bounds.x + bounds.width, bounds.y + bounds.height / 2);
        points.addPoint(bounds.x + bounds.width / 2, bounds.y + bounds.height);
        points.addPoint(bounds.x, bounds.y + bounds.height / 2);
        return points;
    }

}
