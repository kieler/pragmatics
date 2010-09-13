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
 * @author atr
 * Updates any changes made to a Link
 */
public class UpdateLinkFeature extends AbstractUpdateFeature {

    /**
     * @param fp
     *            Constructor.
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
     * Checks if the name has been modified and returns a reason accordingly.
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
                }
            }
        }

        String businessName = null;
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Link) {
            businessName = ((Link) obj).getName();
        }

        boolean updateRequired = false;
        if ((pictogramName == null && !(businessName == null))
                || (!(pictogramName == null) && !pictogramName.equals(businessName))) {
            updateRequired = true;
        }
        if (updateRequired) {
            return Reason.createTrueReason("Name is out of Date");
        } else {
            return Reason.createFalseReason();
        }
    }

    /**
     * Updates the link.
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
        String businessName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Link) {
            businessName = ((Link) obj).getName();
        }
        
        //sets the text to the businessname
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
