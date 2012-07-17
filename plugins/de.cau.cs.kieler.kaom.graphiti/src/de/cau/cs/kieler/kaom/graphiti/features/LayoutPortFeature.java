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
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;

import de.cau.cs.kieler.kaom.Port;

/**
 * Feature used to layout a port.
 * 
 * @author atr
 */
public class LayoutPortFeature extends AbstractLayoutFeature {

    /**
     * Create the feature.
     * 
     * @param fp the feature provider.
     */
    public LayoutPortFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canLayout(final ILayoutContext context) {
        Object object = getBusinessObjectForPictogramElement(context
                .getPictogramElement());
        return object instanceof Port;
    }

    /**
     * {@inheritDoc}
     */
    public boolean layout(final ILayoutContext context) {
        BoxRelativeAnchor boxAnchor = (BoxRelativeAnchor) context
                .getPictogramElement();
        float widthPercent = (float) boxAnchor.getRelativeWidth();
        float heightPercent = (float) boxAnchor.getRelativeHeight();
        float deltaY = heightPercent < (1.0f / 2.0f) ? heightPercent
                : 1 - heightPercent;
        float deltaX = widthPercent < (1.0f / 2.0f) ? widthPercent
                : 1 - widthPercent;
        if (deltaY < deltaX) {
            heightPercent = Math.round(heightPercent);
        } else {
            widthPercent = Math.round(widthPercent);
        }

        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);

        return true;
    }

}
