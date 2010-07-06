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
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;

/**
 * 
 * @author atr
 * Creates a port object and passes it to the AddPortFeature
 */
public class CreatePortFeature extends AbstractCreateFeature {

    private static final int BOUNDARY_DISTANCE = 10;
    /**

     * @param fp .
     * @param name .
     * @param description .
     
     *  Constructor.
     */
    public CreatePortFeature(final IFeatureProvider fp , final String name, final String description) {
        super(fp, name, description);
     
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {
        // TODO Auto-generated method stub
       if (context.getTargetContainer() instanceof ContainerShape) {
           ContainerShape containerShape = context.getTargetContainer();
           if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
               if (Math.abs(context.getX() 
                       - containerShape.getGraphicsAlgorithm().getWidth()) < BOUNDARY_DISTANCE 
                   || Math.abs(context.getY() - containerShape.getGraphicsAlgorithm().getHeight()) 
                   < BOUNDARY_DISTANCE || context.getX() < BOUNDARY_DISTANCE) {
                              
               return true;
               }
            }
       }
           return false;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {
        // TODO Auto-generated method stub
        Port port = KaomFactory.eINSTANCE.createPort();
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
        //getDiagram().eResource().getContents().add(port);
       // newEntity.setName(newClassName);
 
        // do the add
        addGraphicalRepresentation(context, port);
 
     //   getFeatureProvider().getDirectEditingInfo().setActive(true);     
        
        return new Object[] { port };
    }

}
