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

import javax.swing.text.html.parser.Entity;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.impl.DefaultResizeShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * 
 * @author atr Class used to resize the Entity
 */
public class ResizeEntityFeature extends DefaultResizeShapeFeature {

    /**
     * 
     * @param fp
     *            Constructor.
     */
    public ResizeEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canResizeShape(final IResizeShapeContext context) {
        boolean canResize = super.canResizeShape(context);
       if (canResize) {
            Shape shape = context.getShape();
            Object obj = getBusinessObjectForPictogramElement(shape);
            if (obj instanceof Entity) {
                Entity en = (Entity) obj;
                if (en.getName() != null || en.getName().length() == 1) {
                    canResize = false;
                }
            }
        }

        return canResize;
    }

}
