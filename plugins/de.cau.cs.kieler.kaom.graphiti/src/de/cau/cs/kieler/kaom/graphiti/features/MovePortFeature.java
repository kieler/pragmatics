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
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.kaom.Entity;

/**
 * Feature for moving ports.
 * 
 * @author atr
 */
public class MovePortFeature extends DefaultMoveAnchorFeature {
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public MovePortFeature(final IFeatureProvider fp) {
        super(fp);
    }

   /**
    * {@inheritDoc}
    */
    @Override
    public boolean canMoveAnchor(final IMoveAnchorContext context) {
        return (getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof Entity)
                && !(context.getTargetContainer() instanceof Diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void moveAnchor(final Anchor anchor, final int posX, final int posY) {
        if (anchor instanceof BoxRelativeAnchor) {
            BoxRelativeAnchor boxAnchor = (BoxRelativeAnchor) anchor;
            GraphicsAlgorithm anchorContainerGa = boxAnchor.getParent().getGraphicsAlgorithm();
            IDimension nodeSize = Graphiti.getGaService().calculateSize(anchorContainerGa, false);
            int nodeWidth = nodeSize.getWidth();
            int nodeHeight = nodeSize.getHeight();

            float widthPercent = (float) posX / nodeWidth;
            float heightPercent = (float) posY / nodeHeight;
            if (widthPercent + heightPercent <= 1 && widthPercent - heightPercent <= 0) {
                // port is put to the left
                widthPercent = 0;
            } else if (widthPercent + heightPercent >= 1 && widthPercent - heightPercent >= 0) {
                // port is put to the right
                widthPercent = 1;
            } else if (heightPercent < 1.0f / 2) {
                // port is put to the top
                heightPercent = 0;
            } else {
                // port is put to the bottom
                heightPercent = 1;
            }

            boxAnchor.setRelativeWidth(widthPercent);
            boxAnchor.setRelativeHeight(heightPercent);

            boxAnchor.setActive(true);
        } else {
            super.moveAnchor(anchor, posX, posY);
        }
    }

}
