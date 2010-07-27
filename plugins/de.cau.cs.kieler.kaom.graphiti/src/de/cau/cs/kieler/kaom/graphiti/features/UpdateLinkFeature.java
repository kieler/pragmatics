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
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.mm.pictograms.Text;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Relation;

/**
 * @author atr
 *
 */
public class UpdateLinkFeature extends AbstractUpdateFeature {

    /**
     * @param fp
     */
    public UpdateLinkFeature(final IFeatureProvider fp) {
        super(fp);
     }

    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(final IUpdateContext context) {
        Object obj = getBusinessObjectForPictogramElement(context.getPictogramElement());
        System.out.println("Heeellelelelelelellelelelelellllllllllllllllll");
        return (obj instanceof Link);
    }

    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(final IUpdateContext context) {
        // TODO Auto-generated method stub
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof Connection) {
                Connection con = (Connection) pictogramElement;
                for (int i = 0; i < con.getConnectionDecorators().size(); i++) {
                                        
                    if (con.getConnectionDecorators().get(i).getGraphicsAlgorithm() instanceof Text) {
                        Text text = (Text) con.getConnectionDecorators().get(i).getGraphicsAlgorithm();
                        pictogramName = text.getValue();
                   //    System.out.println("The pictogram name is " + pictogramName);
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
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
        String businessName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        Object obj = getBusinessObjectForPictogramElement(pictogramElement);
        if (obj instanceof Link) {
           businessName = ((Link) obj).getName();
        }
        if (pictogramElement instanceof Connection) {
            Connection cs = (Connection)  pictogramElement;
      //      for(Iterator iter = cs.getChildren().iterator();iter.hasNext())
            for (int i = 0; i < cs.getConnectionDecorators().size(); i++) {
              
     
                if (cs.getConnectionDecorators().get(i).getGraphicsAlgorithm() instanceof Text) {
                    ((Text) cs.getConnectionDecorators().get(i)
                            .getGraphicsAlgorithm()).setValue(businessName);
                    return true;
                }
            }
               
        }
                        
        return false;
    }

   
}
