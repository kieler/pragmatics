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

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import de.cau.cs.kieler.core.model.gmf.figures.SplineConnection;

/**
 * A spline connection with an arrow head.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class DirectedConnection extends SplineConnection {

    /**
     * The constructor.
     */
    public DirectedConnection() {
        super();

        RotatableDecoration arrowDeco = createArrowDecoration();
        setTargetDecoration(arrowDeco);
    }

    /**
     * Create the arrow decoration.
     * 
     * @return The decoration.
     */
    // CHECKSTYLEOFF MagicNumber
    private RotatableDecoration createArrowDecoration() {
        PolygonDecoration arrowDecoration = new PolygonDecoration();
        PointList arrowDecorationPoints = new PointList();
        arrowDecorationPoints.addPoint(new Point(-2, 1));
        arrowDecorationPoints.addPoint(new Point(0, 0));
        arrowDecorationPoints.addPoint(new Point(-2, -1));
        arrowDecorationPoints.addPoint(new Point(-1, 0));
        arrowDecoration.setTemplate(arrowDecorationPoints);
        return arrowDecoration;
    }
    // CHECKSTYLEON MagicNumber
}
