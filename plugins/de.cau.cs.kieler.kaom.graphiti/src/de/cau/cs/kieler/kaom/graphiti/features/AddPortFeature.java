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
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

/**
 * Class adds a port to the parent entity.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class AddPortFeature extends AbstractAddShapeFeature {

    /** the default size of ports. */
    public static final int PORT_SIZE = 8;

    /** the style provider. */
    private IStyleProvider styleProvider;

    /**
     * The constructor.
     * 
     * @param fp
     *            the feature provider
     * @param sp
     *            the style provider
     */
    public AddPortFeature(final IFeatureProvider fp, final IStyleProvider sp) {
        super(fp);
        this.styleProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return (context.getNewObject() instanceof Port);
    }

    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        PictogramElement newElement = null;
        if (!(context.getTargetContainer() instanceof Diagram)) {
            newElement = createBoundPort(context.getTargetContainer(),
                    context.getX(), context.getY());
            link(newElement, context.getNewObject());
        }
        return newElement;
    }

    /**
     * Create a port that is bound to an entity's boundary.
     * 
     * @param container
     *            the container shape of the parent entity
     * @param xpos
     *            the x position
     * @param ypos
     *            the y position
     * @return a new pictogram element for the port
     */
    private PictogramElement createBoundPort(final ContainerShape container,
            final int xpos, final int ypos) {

        int nodeWidth = container.getGraphicsAlgorithm().getWidth();
        int nodeHeight = container.getGraphicsAlgorithm().getHeight();

        float widthPercent = (float) (xpos - 2) / nodeWidth;
        float heightPercent = (float) (ypos - 2) / nodeHeight;
        float deltaY = heightPercent < (1.0f / 2.0f) ? heightPercent
                : 1 - heightPercent;
        float deltaX = widthPercent < (1.0f / 2.0f) ? widthPercent
                : 1 - widthPercent;
        if (deltaY < deltaX) {
            heightPercent = Math.round(heightPercent);
        } else {
            widthPercent = Math.round(widthPercent);
        }

        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        BoxRelativeAnchor boxAnchor = peCreateService
                .createBoxRelativeAnchor(container);
        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);
        boxAnchor.setActive(true);

        IGaService gaService = Graphiti.getGaService();
        // look for the actual rectangle that represents the parent entity
        for (GraphicsAlgorithm ga : container.getGraphicsAlgorithm()
                .getGraphicsAlgorithmChildren()) {
            if (ga instanceof Rectangle) {
                boxAnchor.setReferencedGraphicsAlgorithm(ga);
                break;
            }
        }

        Rectangle rectangleShape = gaService.createRectangle(boxAnchor);
        rectangleShape.setStyle(styleProvider
                .getStyle(StyleProvider.SOLID_STYLE));
        gaService.setLocationAndSize(rectangleShape, -PORT_SIZE / 2,
                -PORT_SIZE / 2, PORT_SIZE, PORT_SIZE);

        return boxAnchor;
    }

}
