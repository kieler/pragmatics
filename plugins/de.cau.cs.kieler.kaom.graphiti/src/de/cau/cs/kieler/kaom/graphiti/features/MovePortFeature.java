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
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import de.cau.cs.kieler.kaom.Entity;

/**
 * @author atr
 * 
 */
public class MovePortFeature extends DefaultMoveShapeFeature {

    private static final int BOUNDARY_DISTANCE = 10;

    /**
     * @param fp
     *                  Constructor.
     */
    public MovePortFeature(final IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        boolean canMove = context.getSourceContainer() != null;
        if (canMove) {
            if (context.getTargetContainer() instanceof ContainerShape) {
                ContainerShape containerShape = context.getTargetContainer();

                if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
                    if (Math.abs(context.getX() 
                            - containerShape.getGraphicsAlgorithm().getWidth()) < BOUNDARY_DISTANCE
                            || Math.abs(context.getY()
                              - containerShape.getGraphicsAlgorithm().getHeight()) < BOUNDARY_DISTANCE
                            || context.getX() < BOUNDARY_DISTANCE) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void internalMove(final IMoveShapeContext context) {
        super.internalMove(context);

    }

}
