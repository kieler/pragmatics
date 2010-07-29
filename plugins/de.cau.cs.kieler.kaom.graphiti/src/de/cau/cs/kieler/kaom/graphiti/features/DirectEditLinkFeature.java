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
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Text;

import de.cau.cs.kieler.kaom.Link;

/**
 * @author atr
 * Class used to enable direct edit of the name of the link
 */
public class DirectEditLinkFeature extends AbstractDirectEditingFeature {

    /**
     * @param fp
     *            Constructor.
     */
    public DirectEditLinkFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     *  Used to select the UI to be shown where editing will take place.
     * {@inheritDoc}
     */
    public int getEditingType() {
        // TODO Auto-generated method stub
        return TYPE_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    public String getInitialValue(final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        return ((Link) getBusinessObjectForPictogramElement(pe)).getName();
    }

    /**
     * Sets the new value written.
     * {@inheritDoc}
     */
    public void setValue(final String value, final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pe);
        if (obj instanceof Link) {
            Link link = (Link) obj;
            link.setName(value);
        }
        updatePictogramElement(((ConnectionDecorator) pe).getContainer());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canDirectEdit(final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pe);
        GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
        // support direct editing, if it is a EClass, and the user clicked
        // directly on the text and not somewhere else in the rectangle
        if (bo instanceof Link && ga instanceof Text) {
            return true;
        }
        // direct editing not supported in all other cases
        return false;

    }
    
    /**
     * Used to keep a check on the new value entered.
     * {@inheritDoc}
     */
    @Override
    public String checkValueValid(final String value, final IDirectEditingContext context) {
        if (value.length() < 1) {
          return "Please enter any text as entity name.";
        }
        if (value.contains("\n")) {
            return "Line breakes are not allowed in class names.";
        }
        // null means, that the value is valid
        return null;

    }
}
