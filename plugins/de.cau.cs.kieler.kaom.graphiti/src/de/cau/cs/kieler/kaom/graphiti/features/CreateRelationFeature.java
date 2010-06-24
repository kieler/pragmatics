package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

public class CreateRelationFeature extends AbstractCreateFeature {

    public CreateRelationFeature(IFeatureProvider fp, String name, String description) {
        super(fp, name, description);
        // TODO Auto-generated constructor stub
    }

    public boolean canCreate(ICreateContext context) {
        // TODO Auto-generated method stub   
            if(context.getTargetContainer() instanceof Diagram || context.getTargetContainer() instanceof ContainerShape)
                return true;
        
        return false;
    }
    

    public Object[] create(ICreateContext context) {
        // TODO Auto-generated method stub
        KaomFactory kaomFactory=KaomFactory.eINSTANCE;
        
        Relation relation=kaomFactory.createRelation();
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
      //  getDiagram().eResource().getContents().add(relation);
       // newEntity.setName(newClassName);
 
        // do the add
        addGraphicalRepresentation(context, relation);
 
        getFeatureProvider().getDirectEditingInfo().setActive(true);
        
                
        return new Object[] { relation };
    }

}
