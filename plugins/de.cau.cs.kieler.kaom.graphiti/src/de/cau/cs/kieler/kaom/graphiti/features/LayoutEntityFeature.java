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

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.core.model.graphiti.GraphitiUtil;
import de.cau.cs.kieler.kaom.Entity;

/**
 * Feature used to layout an entity and adjust its components after resizing has
 * occurred.
 * 
 * @author atr
 */
public class LayoutEntityFeature extends AbstractLayoutFeature {

    /** the fixed height of the entity name. */
    public static final int TEXT_HEIGHT = 10;
    /** the fixed distance of the entity name. */
    public static final int TEXT_DIST = 2;
    /** minimal width for entities. */
    public static final int MIN_WIDTH = 25;
    /** minimal width for the port container shapes. */
    public static final int MIN_CONTAINER_WIDTH = MIN_WIDTH
            + AddPortFeature.PORT_SIZE;
    /** minimal height for entities. */
    public static final int MIN_HEIGHT = 25;
    /** minimal height for the port container shapes. */
    public static final int MIN_CONTAINER_HEIGHT = MIN_HEIGHT
            + AddPortFeature.PORT_SIZE + TEXT_DIST + TEXT_HEIGHT;

    /**
     * The constructor.
     * 
     * @param fp
     *            the feature provider
     */
    public LayoutEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canLayout(final ILayoutContext context) {
        Object object = getBusinessObjectForPictogramElement(context
                .getPictogramElement());
        return object instanceof Entity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean layout(final ILayoutContext context) {
        boolean changed = false;
        ContainerShape containerShape = (ContainerShape) context
                .getPictogramElement();
        GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();

        if (containerGa.getHeight() < MIN_CONTAINER_HEIGHT) {
            containerGa.setHeight(MIN_CONTAINER_HEIGHT);
            changed = true;
        }
        if (containerGa.getWidth() < MIN_CONTAINER_WIDTH) {
            containerGa.setWidth(MIN_CONTAINER_WIDTH);
            changed = true;
        }

        // container width initially of the invisible rectangle
        // now adjusted to the width of the normal inner rectangle
        int entityWidth = containerGa.getWidth() - AddPortFeature.PORT_SIZE;
        int entityHeight = containerGa.getHeight()
                - (AddPortFeature.PORT_SIZE + TEXT_DIST + TEXT_HEIGHT);
        for (GraphicsAlgorithm child : containerGa
                .getGraphicsAlgorithmChildren()) {
            changed |= GraphitiUtil.setBounds(child,
                    AddPortFeature.PORT_SIZE / 2, AddPortFeature.PORT_SIZE / 2,
                    entityWidth, entityHeight);
        }

        // position of each child shape of the entity adjusted
        for (Shape shape : containerShape.getChildren()) {
            GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
            if (ga instanceof AbstractText) {
                changed |= GraphitiUtil.setBounds(ga, 0,
                        AddPortFeature.PORT_SIZE + entityHeight + TEXT_DIST,
                        containerGa.getWidth(), TEXT_HEIGHT);
            }
        }

        // layout ports to be in a valid position
        List<Anchor> anchors = containerShape.getAnchors();
        for (Anchor anchor : anchors) {
            if (anchor instanceof BoxRelativeAnchor) {
                super.getFeatureProvider().layoutIfPossible(
                        new LayoutContext(anchor));
            }
        }

        return changed;
    }

}
