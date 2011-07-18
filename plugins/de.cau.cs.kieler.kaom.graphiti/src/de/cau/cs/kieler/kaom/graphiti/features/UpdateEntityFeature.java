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
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.kaom.Entity;

/**
 * Update any changes made to an entity.
 * 
 * @author atr
 */
public class UpdateEntityFeature extends AbstractUpdateFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public UpdateEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(final IUpdateContext context) {
        Object obj = getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (obj instanceof Entity);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Entity) {
            String businessName = ((Entity) obj).getName();
            // updating the name of the pictogram element
            if (pictogramElement instanceof ContainerShape) {
                ContainerShape cs = (ContainerShape) pictogramElement;
                for (Shape shape : cs.getChildren()) {
                    if (shape.getGraphicsAlgorithm() instanceof AbstractText) {
                        ((AbstractText) shape.getGraphicsAlgorithm()).setValue(businessName);
                        return true;
                    }
                }

            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(final IUpdateContext context) {
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof AbstractText) {
                    AbstractText text = (AbstractText) shape.getGraphicsAlgorithm();
                    pictogramName = text.getValue();
                }
            }
        }

        String businessName = null;
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Entity) {
            businessName = ((Entity) obj).getName();
        }

        boolean updateRequired;
        if (businessName == null || businessName.length() == 0) {
            updateRequired = pictogramName != null && pictogramName.length() > 0;
        } else {
            updateRequired = !businessName.equals(pictogramName);
        }
        if (updateRequired) {
            return Reason.createTrueReason("Name is out of Date");
        } else {
            return Reason.createFalseReason();
        }
    }

}
