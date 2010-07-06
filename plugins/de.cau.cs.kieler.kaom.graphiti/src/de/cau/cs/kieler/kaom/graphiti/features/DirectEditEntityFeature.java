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
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * 
 * @author atr
 * Class used to enable direct edit of the name of the entity
 */
public class DirectEditEntityFeature extends AbstractDirectEditingFeature {

    /**
     * 
     * @param fp
     
     *Constructor
     */
    public DirectEditEntityFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getEditingType() {
        // TODO Auto-generated method stub
        return TYPE_TEXT;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public String getInitialValue(final IDirectEditingContext context) {
        // TODO Auto-generated method stub
     PictogramElement pe = context.getPictogramElement();
     return ((Entity) getBusinessObjectForPictogramElement(pe)).getName();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setValue(final String value, final IDirectEditingContext context) {
        // TODO Auto-generated method stub
        PictogramElement pe = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pe);
        if (obj instanceof Entity) {
        Entity entity = (Entity) obj;
        entity.setName(value);
        }
        updatePictogramElement(((Shape) pe).getContainer());
    }
    

    @Override
    public String checkValueValid(final String value, final IDirectEditingContext context) {
        if (value.length() < 1) {
            return "Please enter any text as class name."; 
            }
        if (value.contains(" ")) {
            return "Spaces are not allowed in class names."; 
            }
        if (value.contains("\n")) {
            return "Line breakes are not allowed in class names.";
            }

        return null;
    }

}
