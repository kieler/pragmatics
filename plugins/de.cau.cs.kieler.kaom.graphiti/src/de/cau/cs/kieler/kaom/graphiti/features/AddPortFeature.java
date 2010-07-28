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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;

import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import org.eclipse.graphiti.mm.pictograms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.graphiti.util.StyleUtil;

/**
 * 
 * @author atr Class adds a port to the parent entity
 */
public class AddPortFeature extends AbstractAddShapeFeature {

    /**
     * Port created at a distance.
     */
    public static final int INVISIBLE_RECTANGLE_WIDTH = 6;
    private static final int BOUNDARY_DISTANCE = 10;

    /**
     * 
     * @param fp
     *            Constructor
     */
    public AddPortFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {

        ContainerShape containerShape = context.getTargetContainer();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        peCreateService.createChopboxAnchor(containerShape);

        Port port = (Port) context.getNewObject();

        float widthPercent;
        float heightPercent;

        BoxRelativeAnchor boxAnchor = peCreateService.createBoxRelativeAnchor(containerShape);
        if (context.getX() < BOUNDARY_DISTANCE) {
            widthPercent = (float) 0;
        } else if (Math.abs(context.getX()
                - (float) containerShape.getGraphicsAlgorithm().getWidth()) < BOUNDARY_DISTANCE) {
            widthPercent = (float) 1;
        } else {
            widthPercent = ((float) context.getX())
                    / containerShape.getGraphicsAlgorithm().getWidth();
        }

        if (Math.abs(context.getY() - (float) containerShape.getGraphicsAlgorithm().getHeight())
                < BOUNDARY_DISTANCE) {
            heightPercent = (float) 1;
        } else {
            heightPercent = ((float) context.getY())
                    / containerShape.getGraphicsAlgorithm().getHeight();
        }

        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);

        boxAnchor.setActive(true);

        IGaService gaService = Graphiti.getGaService();

        PictogramElement pe = (PictogramElement) containerShape.getLink().getPictogramElement();

        if (pe.getGraphicsAlgorithm() instanceof Rectangle) {
            Rectangle invisibleRectangle = (Rectangle) pe.getGraphicsAlgorithm();
            for (GraphicsAlgorithm ga : invisibleRectangle.getGraphicsAlgorithmChildren()) {

                if (ga instanceof RoundedRectangle) {
                    boxAnchor.setReferencedGraphicsAlgorithm((RoundedRectangle) ga);
                    // System.out.println("Hello I cam here!!");
                }
            }
        }

        Ellipse boxEllipse = gaService.createEllipse(boxAnchor);
        boxEllipse.setFilled(true);
        boxEllipse.setStyle(StyleUtil.getStyleForEClass(getDiagram()));

        final int w = INVISIBLE_RECTANGLE_WIDTH;
        gaService.setLocationAndSize(boxEllipse, -w, -w, 2 * w, 2 * w);

        Entity entity = (Entity) getBusinessObjectForPictogramElement(context.getTargetContainer());
        entity.getChildPorts().add(port);

        link(boxAnchor, port);
        return containerShape;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        if (context.getNewObject() instanceof Port) {
            if (context.getTargetContainer() instanceof ContainerShape) {
                ContainerShape containerShape = context.getTargetContainer();

                if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
                    if (Math.abs(context.getX() - containerShape.getGraphicsAlgorithm().getWidth()) 
                            < BOUNDARY_DISTANCE || Math.abs(context.getY()
                                    - containerShape.getGraphicsAlgorithm().getHeight()) 
                                    < BOUNDARY_DISTANCE || context.getX() < BOUNDARY_DISTANCE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
