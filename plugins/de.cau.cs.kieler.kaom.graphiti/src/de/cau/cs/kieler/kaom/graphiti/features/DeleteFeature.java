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
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

/**
 * @author atr
 * Class is called to delete any element added in the diagram
 */
public class DeleteFeature extends DefaultDeleteFeature {

    /**
     * @param fp .
     */
    public DeleteFeature(final IFeatureProvider fp) {
        super(fp);
    }
    
    /**
     * This method is called when the delete button is pressed
     * It basically removes the element selected both from the diagram and
     * from the domain model.
     * {@inheritDoc}
     */
    @Override
    public void delete(final IDeleteContext context) {
    PictogramElement pe = context.getPictogramElement();
    Object[] businessObjectsForPictogramElement = getAllBusinessObjectsForPictogramElement(pe);
   
    preDelete(context);

    IRemoveContext rc = new RemoveContext(pe);
    IFeatureProvider featureProvider = getFeatureProvider();
    IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
    if (removeFeature != null) {
            removeFeature.remove(rc);
    }

    deleteBusinessObjects(businessObjectsForPictogramElement);

    postDelete(context);
    }
}
