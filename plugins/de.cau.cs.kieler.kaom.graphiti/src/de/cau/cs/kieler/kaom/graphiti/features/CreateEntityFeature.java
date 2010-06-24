package de.cau.cs.kieler.kaom.graphiti.features;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.examples.common.SampleUtil;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;

public class CreateEntityFeature extends AbstractCreateFeature {

    private static final  String TITLE = "Create entity";

    private static final String USER_QUESTION = "Enter new entity name";

    public CreateEntityFeature(IFeatureProvider fp) {
        super(fp, "Entity", "Create Entity");
    }

 
    public boolean canCreate(ICreateContext context) {
        if(context.getTargetContainer() instanceof Diagram)
            return true;
        else if(context.getTargetContainer() instanceof ContainerShape)
            return true;
        else
            return false;
              
    }

 
    public Object[] create(ICreateContext context) {
     
     //   String newClassName = SampleUtil.askString(TITLE, USER_QUESTION, "");
     //   if (newClassName == null || newClassName.trim().length() == 0) {
     //       return null;

     //   }


        // create EClass
        Entity newEntity = KaomFactory.eINSTANCE.createEntity();
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
        addToDiagram(newEntity);
       // newEntity.setName(newClassName);
 
        // do the add
        addGraphicalRepresentation(context, newEntity);
 
        getFeatureProvider().getDirectEditingInfo().setActive(true);
        
        return new Object[] { newEntity };
    }
    
    private void addToDiagram(final Entity newEntity) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                break;
            }
        }
        if (topEntity == null) {
            topEntity = KaomFactory.eINSTANCE.createEntity();
            contents.add(topEntity);
        }
        topEntity.getChildEntities().add(newEntity);
    }
    
}  