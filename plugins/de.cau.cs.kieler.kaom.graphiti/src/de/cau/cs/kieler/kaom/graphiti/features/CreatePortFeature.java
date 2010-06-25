package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;

public class CreatePortFeature extends AbstractCreateFeature {

    public CreatePortFeature(IFeatureProvider fp, String name, String description) {
        super(fp, name, description);
        // TODO Auto-generated constructor stub
    }

    public boolean canCreate(ICreateContext context) {
        // TODO Auto-generated method stub
       if (context.getTargetContainer() instanceof ContainerShape) {
           ContainerShape containerShape = context.getTargetContainer();
           if (getBusinessObjectForPictogramElement(containerShape) instanceof Entity) {
               if (Math.abs(context.getX() - containerShape.getGraphicsAlgorithm().getWidth()) < 10 
                   || Math.abs(context.getY() - containerShape.getGraphicsAlgorithm().getHeight()) < 10
                       || context.getX() < 10) {
                              
               return true;
               }
            }
       }
           return false;
    }

    public Object[] create(ICreateContext context) {
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
