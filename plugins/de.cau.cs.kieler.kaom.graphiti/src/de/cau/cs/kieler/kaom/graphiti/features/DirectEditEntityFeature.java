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
import de.cau.cs.kieler.kaom.Entity;

import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * Feature for enabling direct editing of the name of entities.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class DirectEditEntityFeature extends AbstractDirectEditingFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public DirectEditEntityFeature(final IFeatureProvider fp) {
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
        Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
        return bo instanceof Entity;
    }

    /**
     * {@inheritDoc}
     */
    public String getInitialValue(final IDirectEditingContext context) {
        Object object = getBusinessObjectForPictogramElement(context.getPictogramElement());
        if (object instanceof Entity) {
            return ((Entity) object).getName();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setValue(final String value, final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pe);
        if (obj instanceof Entity) {
            Entity entity = (Entity) obj;
            entity.setName(value);
            updatePictogramElement(pe);
        }
    } 

}
