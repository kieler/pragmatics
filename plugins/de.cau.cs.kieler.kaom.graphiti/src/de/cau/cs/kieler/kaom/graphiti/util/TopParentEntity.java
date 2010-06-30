package de.cau.cs.kieler.kaom.graphiti.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.graphiti.diagram.DiagramTypeProvider;

public class TopParentEntity {
    
    public static Entity parentEntity=null;
    
    public static Entity createParentEntity(Diagram diag)
    {
        if (diag == null) {
            System.out.println("This is not possible");
            }
        if(parentEntity==null)
        {
        List<EObject> contents = diag.eResource().getContents();
      /*  for (EObject obj : contents) {
            if (obj instanceof Entity) {
                parentEntity = (Entity) obj;
             //   topEntityFlag = false;
                break;
            }
        }*/
     //   if(parentEntity==null) {
            parentEntity = KaomFactory.eINSTANCE.createEntity();
            contents.add(parentEntity);
           
       
        }
        return parentEntity;     
                
    }
  
}
    
    

