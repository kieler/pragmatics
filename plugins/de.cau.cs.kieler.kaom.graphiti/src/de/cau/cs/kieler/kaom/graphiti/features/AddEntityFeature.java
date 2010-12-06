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

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

/**
 * Adds a new entity, which is contained within the parent entity.
 * 
 * @author atr
 */
public class AddEntityFeature extends AbstractAddShapeFeature {

    /** the style provider. */ 
    private IStyleProvider styleProvider;
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     * @param thestyleProvider the style provider
     */
    public AddEntityFeature(final IFeatureProvider fp, final IStyleProvider thestyleProvider) {
        super(fp);
        this.styleProvider = thestyleProvider;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return context.getNewObject() instanceof Entity;
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        ContainerShape parentContainerShape = context.getTargetContainer();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape entityShape = peCreateService.createContainerShape(parentContainerShape, true);
        peCreateService.createChopboxAnchor(entityShape);
        Entity entity = (Entity) context.getNewObject();
        link(entityShape, entity);
        
        // invisible rectangle created so that ports can be placed on the boundary
        IGaService gaService = Graphiti.getGaService();
        Rectangle portContainer = gaService.createInvisibleRectangle(entityShape);
        int width = context.getWidth() <= 0 ? 0 : context.getWidth();
        int height = context.getHeight() <= 0 ? 0 : context.getHeight();
        gaService.setLocationAndSize(portContainer,
                context.getX() - AddPortFeature.PORT_SIZE,
                context.getY() - AddPortFeature.PORT_SIZE,
                width + 2 * AddPortFeature.PORT_SIZE,
                height + 2 * AddPortFeature.PORT_SIZE);
        // rectangle added to port container
        Rectangle rectangleShape = gaService.createRectangle(portContainer);
        rectangleShape.setStyle(styleProvider.getStyle());

        // a separator line for the entity label
        Shape shape = peCreateService.createShape(entityShape, false);
        Polyline polyline = gaService.createPolyline(shape);
        polyline.setStyle(styleProvider.getStyle());

        // the entity label
        shape = peCreateService.createShape(entityShape, false);
        Text text = gaService.createDefaultText(shape, entity.getName());
        text.setStyle(styleProvider.getStyle());
        text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
        text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
        link(shape, entity);

        // set container shape for direct editing after object creation
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(entityShape);
        // set shape and graphics algorithm where the editor for
        // direct editing shall be opened after object creation
        directEditingInfo.setPictogramElement(shape);
        directEditingInfo.setGraphicsAlgorithm(text);

        layoutPictogramElement(entityShape);
        entityShape.setActive(true);
        return entityShape;
    }

}
