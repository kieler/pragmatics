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

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.impl.DefaultMoveAnchorFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.kaom.Entity;

/**
 * @author atr
 * Class used to move the Port
 */
public class MoveAnchorFeature extends DefaultMoveAnchorFeature {

    private static final int BOUNDARY_DISTANCE = 10;
    private static final int ADJUSTMENT_FACTOR = 10;
    /**
     * @param fp
     *                  Constructor.
     */
    public MoveAnchorFeature(final IFeatureProvider fp) {
        super(fp);

    }

   /**
    * 
    * {@inheritDoc}
    */
    @Override
    public boolean canMoveAnchor(final IMoveAnchorContext context) {

        if (context.getTargetContainer() instanceof ContainerShape) {
            ContainerShape containerShape = (ContainerShape) context.getTargetContainer();

            if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
               
                if (Math.abs(context.getX() - containerShape.getGraphicsAlgorithm()
                        .getWidth()) < (BOUNDARY_DISTANCE + ADJUSTMENT_FACTOR)
                        || Math.abs(context.getY()
                         - containerShape.getGraphicsAlgorithm().getHeight()) 
                         < (BOUNDARY_DISTANCE + ADJUSTMENT_FACTOR)
                        || context.getX() < (BOUNDARY_DISTANCE)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Used to move the Port.
     * {@inheritDoc}
     */
    @Override
    protected void moveAnchor(final Anchor anchor, final int posX, final int posY) {
        int x = (posX < 0) ? 0 : posX;
        int y = (posY < 0) ? 0 : posY;

        // TODO change to flexible anchor-size
        // GraphicalEditPart parent = (GraphicalEditPart) ep.getParent();
        // IFigure figure = parent.getFigure();
        // Rectangle clientArea = figure.getClientArea();
        // if ((x + 10) > clientArea.width) {
        // x = clientArea.width - 10;
        // }
        // if ((y + 10) > clientArea.height) {
        // y = clientArea.height - 10;
        // }

        if (anchor instanceof FixPointAnchor) {
            FixPointAnchor fpAnchor = (FixPointAnchor) anchor;
            fpAnchor.setLocation(Graphiti.getGaCreateService().createPoint(x, y));
        } else if (anchor instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor brAnchor = (BoxRelativeAnchor) anchor;
            AnchorContainer anchorContainer = brAnchor.getParent();
            GraphicsAlgorithm anchorContainerGa = anchorContainer.getGraphicsAlgorithm();
            IDimension sizeOfGraphicsAlgorithm = Graphiti.getGaService().calculateSize(
                    anchorContainerGa, false);
            int width = sizeOfGraphicsAlgorithm.getWidth();
            int height = sizeOfGraphicsAlgorithm.getHeight();

            float widthPercent, heightPercent;
            
            //percentage set according to the X and Y position of the Port
            //Either the width or the height percent has to be 1 or 0 
            
            if (posX < BOUNDARY_DISTANCE) {
                widthPercent = (float) 0;
            } else if (Math.abs(posX - (float) width) < BOUNDARY_DISTANCE
                    + ADJUSTMENT_FACTOR) {
                widthPercent = (float) 1;
            } else {
                widthPercent = ((float) posX) / width;
            }

            if (Math.abs(posY - (float) height) < BOUNDARY_DISTANCE
                    + ADJUSTMENT_FACTOR) {
                heightPercent = (float) 1;
            } else {
                heightPercent = ((float) posY) / height;
            }

            brAnchor.setRelativeWidth(widthPercent);
            brAnchor.setRelativeHeight(heightPercent);

            brAnchor.setActive(true);
        }
    }

}
