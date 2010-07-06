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
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Relation;

/**
 * 
 * @author atr
 * Creates a relation object and passes it to the AddRelationFeature
 */
public class CreateRelationFeature extends AbstractCreateFeature {

    /**
    
     * @param fp .
     * @param name .
     * @param description .
     * Constructor .
     */
    public CreateRelationFeature(final IFeatureProvider fp, final String name, 
            final String description) {
        super(fp, name, description);
    
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {
      
            if (context.getTargetContainer() instanceof ContainerShape 
                    || context.getTargetContainer() instanceof Diagram) {
                
                return true;
    }
        
        return false;
    }
    

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {
       
        KaomFactory kaomFactory = KaomFactory.eINSTANCE;
        
        Relation relation = kaomFactory.createRelation();
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
        //  getDiagram().eResource().getContents().add(relation);
        // newEntity.setName(newClassName);
 
        // do the add
        addGraphicalRepresentation(context, relation);
                       
        return new Object[] { relation };
    }

}
