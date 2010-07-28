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
package de.cau.cs.kieler.kaom.graphiti.features;

import de.cau.cs.kieler.kaom.Entity;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.datatypes.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polygon;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

/**
 * 
 * @author atr Class used to layout the entity object and adjust its components after resizing has
 *         occurred
 */
public class LayoutEntityFeature extends AbstractLayoutFeature {

    private static final int MIN_HEIGHT = 30;
    private static final int MIN_WIDTH = 50;

    /**
     * 
     * @param fp
     *            Constructor
     */
    public LayoutEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canLayout(final ILayoutContext context) {
        PictogramElement pe = context.getPictogramElement();
        if (pe instanceof ContainerShape) {
            EList<EObject> ob = pe.getLink().getBusinessObjects();
            return ob.size() == 1 && (ob.get(0) instanceof Entity);
        }

        return false;

    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean layout(final ILayoutContext context) {

        boolean changed = false;
        ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
        GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
        IGaService gaService = Graphiti.getGaService();

        if (containerGa.getHeight() < MIN_HEIGHT) {
            containerGa.setHeight(MIN_HEIGHT);
            changed = true;
        }

        if (containerGa.getWidth() < MIN_WIDTH) {
            containerGa.setWidth(MIN_WIDTH);
            changed = true;
        }

        int containerWidth = containerGa.getWidth() - 2 * AddPortFeature.INVISIBLE_RECTANGLE_WIDTH;
        if (containerGa instanceof Rectangle) {

            Rectangle rectangle = (Rectangle) containerGa;
            gaService.setLocationAndSize(rectangle.getGraphicsAlgorithmChildren().get(0),
                    AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, 0, containerWidth,
                    rectangle.getHeight() - AddPortFeature.INVISIBLE_RECTANGLE_WIDTH);
            changed = true;
        }

        Iterator iter = containerShape.getChildren().iterator();
        while (iter.hasNext()) {
            Shape shape = (Shape) iter.next();
            GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();

            IDimension size = gaService.calculateSize(ga);
            if (size.getWidth() != containerWidth) {

                if (ga instanceof Polygon) {
                    System.out.println();
                } else if (ga instanceof Polyline) {
                    Polyline polyline = (Polyline) ga;
                    Point firstPoint = polyline.getPoints().get(0);
                    Point newfirstPoint = gaService.createPoint(
                            AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, firstPoint.getY());
                    Point secondpoint = polyline.getPoints().get(1);
                    Point newsecondpoint = gaService.createPoint(
                            AddPortFeature.INVISIBLE_RECTANGLE_WIDTH + containerWidth,
                            secondpoint.getY());
                    polyline.getPoints().set(0, newfirstPoint);
                    polyline.getPoints().set(1, newsecondpoint);
                    changed = true;
                } else if (ga instanceof Text) {
                    Text text = (Text) ga;
                    gaService.setLocationAndSize(ga, AddPortFeature.INVISIBLE_RECTANGLE_WIDTH,
                            text.getY(), containerWidth, text.getHeight()); // ,
                                                                            // avoidNegativeCoordinates)
                    changed = true;
                }

            }
        }
        return changed;
    }

}
