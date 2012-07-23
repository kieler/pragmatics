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
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.kaom.Link;

/**
 * Updates any changes made to a link.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class UpdateLinkFeature extends AbstractUpdateFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public UpdateLinkFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(final IUpdateContext context) {
        Object obj = getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (obj instanceof Link);
    }

    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(final IUpdateContext context) {
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof Connection) {
            Connection con = (Connection) pictogramElement;
            for (ConnectionDecorator cd : con.getConnectionDecorators()) {
                if (cd.getGraphicsAlgorithm() instanceof Text) {
                    Text text = (Text) cd.getGraphicsAlgorithm();
                    pictogramName = text.getValue();
                    break;
                }
            }
        }

        String businessName = null;
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Link) {
            businessName = ((Link) obj).getName();
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

    /**
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
        String businessName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Link) {
            businessName = ((Link) obj).getName();
        }
        
        // sets the text to the business name
        if (pictogramElement instanceof Connection) {
            Connection cs = (Connection) pictogramElement;
            for (ConnectionDecorator cd : cs.getConnectionDecorators()) {

                if (cd.getGraphicsAlgorithm() instanceof Text) {
                    ((Text) cd.getGraphicsAlgorithm()).setValue(businessName);
                    return true;
                }
            }

        }

        return false;
    }

}
