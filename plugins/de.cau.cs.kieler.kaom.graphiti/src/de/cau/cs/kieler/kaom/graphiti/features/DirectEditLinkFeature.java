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
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.kaom.Link;

/**
 * Feature for enabling direct editing of the name of links.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class DirectEditLinkFeature extends AbstractDirectEditingFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public DirectEditLinkFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public int getEditingType() {
        return TYPE_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDirectEdit(final IDirectEditingContext context) {
        if (context.getPictogramElement() instanceof ConnectionDecorator) {
            Object bo = getBusinessObjectForPictogramElement(
                    ((ConnectionDecorator) context.getPictogramElement()).getConnection());
            return bo instanceof Link;
        } else {
            Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
            return bo instanceof Link;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getInitialValue(final IDirectEditingContext context) {
        Object object;
        if (context.getPictogramElement() instanceof ConnectionDecorator) {
            object = getBusinessObjectForPictogramElement(
                    ((ConnectionDecorator) context.getPictogramElement()).getConnection());
        } else {
            object = getBusinessObjectForPictogramElement(context.getPictogramElement());
        }
        if (object instanceof Link) {
            return ((Link) object).getName();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String value, final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object object;
        if (pe instanceof ConnectionDecorator) {
            object = getBusinessObjectForPictogramElement(((ConnectionDecorator) pe).getConnection());
        } else {
            object = getBusinessObjectForPictogramElement(pe);
        }
        if (object instanceof Link) {
            Link link = (Link) object;
            link.setName(value);
            updatePictogramElement(pe);
        }
    }
    
}
