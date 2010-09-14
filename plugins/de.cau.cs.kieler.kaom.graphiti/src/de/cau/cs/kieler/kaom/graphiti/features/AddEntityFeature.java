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

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.diagram.KaomDiagramEditor;
import de.cau.cs.kieler.kaom.graphiti.util.PropertyUtil;
import de.cau.cs.kieler.kaom.graphiti.util.StyleUtil;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

/**
 * 
 * @author atr Adds a new Entity which is contained within the parent Entity
 * 
 */
public class AddEntityFeature extends AbstractAddShapeFeature {

    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 50;
    private static final int RECTANGLE_CURVE = 5;
    private static final int BOUNDARY_DISTANCE = 6;
    private static final int DISTANCE_FROM_TOP = 20;

    /**
     * @param fp
     *            Constructor.
     */
    public AddEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {

        Entity entity = (Entity) context.getNewObject();
        ContainerShape containerShape;
        IPeCreateService peCreateService = Graphiti.getPeCreateService();

        addToDiagram(entity, context);

        ContainerShape parentContainerShape = context.getTargetContainer();
        containerShape = peCreateService.createContainerShape(parentContainerShape, true);
        PropertyUtil.setEClassShape(containerShape);

        int width = context.getWidth() <= 0 ? MIN_WIDTH : context.getWidth();
        int height = context.getHeight() <= 0 ? MIN_HEIGHT : context.getHeight();
        IGaService gaService = Graphiti.getGaService();
        
        // InvisibleRectangle created so that port can be placed on the boundary edges
        Rectangle invisibleRectangle = gaService.createInvisibleRectangle(containerShape);

        gaService.setLocationAndSize(invisibleRectangle, context.getX(), context.getY(), width
                + 2 * AddPortFeature.INVISIBLE_RECTANGLE_WIDTH, height
                + AddPortFeature.INVISIBLE_RECTANGLE_WIDTH);

        // Rounded rectangle added to invisible rectangle
        RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(
                invisibleRectangle, RECTANGLE_CURVE, RECTANGLE_CURVE);
        gaService.setLocationAndSize(roundedRectangle, BOUNDARY_DISTANCE, 0, width, height);
        roundedRectangle.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
        roundedRectangle.setParentGraphicsAlgorithm(invisibleRectangle);

        peCreateService.createChopboxAnchor(containerShape);
        link(containerShape, entity);

        Shape shape = peCreateService.createShape(containerShape, false);
        Polyline polyline = gaService.createPolyline(shape, new int[] { BOUNDARY_DISTANCE,
                DISTANCE_FROM_TOP, width, DISTANCE_FROM_TOP });
        polyline.setStyle(StyleUtil.getStyleForEClass(getDiagram()));

        shape = peCreateService.createShape(containerShape, false);
        Text text = gaService.createDefaultText(shape, entity.getName());
        text.setStyle(StyleUtil.getStyleForEClassText(getDiagram()));
        text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
        text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
        text.getFont().setBold(true);
        gaService.setLocationAndSize(text, BOUNDARY_DISTANCE, 0, width, DISTANCE_FROM_TOP);

        link(shape, entity);

        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();

        // set container shape for direct editing after object creation

        directEditingInfo.setMainPictogramElement(containerShape);

        // set shape and graphics algorithm where the editor for
        // direct editing shall be opened after object creation

        directEditingInfo.setPictogramElement(shape);
        directEditingInfo.setGraphicsAlgorithm(text);

        layoutPictogramElement(containerShape);
        containerShape.setActive(true);

        return containerShape;

    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        if (context.getNewObject() instanceof Entity) {

            if (context.getTargetContainer() instanceof Diagram
                    || context.getTargetContainer() instanceof ContainerShape) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the new entity formed to its container.
     * 
     * @param newEntity
     * @param context
     */
    private void addToDiagram(final Entity newEntity, final IAddContext context) {
        ContainerShape container = context.getTargetContainer();
        Entity entity = (Entity) getBusinessObjectForPictogramElement(container);
        if (entity == null && container instanceof Diagram) {
            entity = ((KaomDiagramEditor) getDiagramEditor()).findTopEntity((Diagram) container);
        }
        entity.getChildEntities().add(newEntity);
    }

}
