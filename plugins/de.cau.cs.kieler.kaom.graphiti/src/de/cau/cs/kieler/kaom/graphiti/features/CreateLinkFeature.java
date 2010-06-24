package de.cau.cs.kieler.kaom.graphiti.features;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Rectangle;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

public class CreateLinkFeature extends AbstractCreateConnectionFeature {

    public CreateLinkFeature(IFeatureProvider fp)//, String name, String description) {
    {
        super(fp,"Link","Create Link");// name, description);
        // TODO Auto-generated constructor stub
    }

    public boolean canCreate(ICreateConnectionContext context) {
    /*    Entity target=null,source=null;
        if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
       { 
           if(getBusinessObjectForPictogramElement(context.getTargetPictogramElement()) instanceof Entity)         
           { 
           target =(Entity)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
           source =(Entity)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
           }
        }
        else
        {
           target=getEntity(context.getSourceAnchor());
            source=getEntity(context.getTargetAnchor());
        }
      */
        Object source=null,target=null;
            source=getObject(context.getSourceAnchor());
            target=getObject(context.getTargetAnchor());
                    
        if (source != null && target != null && source != target) {
            return true;
        }

        return false;
    }

 

    public boolean canStartConnection(ICreateConnectionContext context) {
        System.out.println("I cam here111111111!!!!!");
       // if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
     //      return true;
    /*  if(context.getSourcePictogramElement() instanceof AnchorContainer )
      {
            System.out.println("Hellooooo111111111111111111");
            AnchorContainer anchorContainer=(AnchorContainer)context.getSourcePictogramElement();
           )
      */  
        if(getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent()) != null) {
            return true;
        }
        return false;
    }


    public Connection create(ICreateConnectionContext context) {
        System.out.println("I cam here222222222222222222!!!!!");
        Connection newConnection = null;
     /*   Entity target=null,source=null;
        if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
        { 
            if(getBusinessObjectForPictogramElement(context.getTargetPictogramElement()) instanceof Entity)         
            { 
            target =(Entity)getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
            source =(Entity)getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
            }
        }
        else
        {
            target=getEntity(context.getSourceAnchor());
            source=getEntity(context.getTargetAnchor());
        }
        */
        
        Object source=null,target=null;
        
        source=getObject(context.getSourceAnchor());
        target=getObject(context.getTargetAnchor());
        
        
        if (source != null && target != null) {

            Link link = createLink(source, target);
            System.out.println("Link has been correctly formed");
            AddConnectionContext addContext =new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
          //  AddConnectionContext addContext =new AddConnectionContext(context.getSourcePictogramElement().getGraphicsAlgorithm()., context.getTargetAnchor());

            addContext.setNewObject(link);
            newConnection =(Connection) getFeatureProvider().addIfPossible(addContext);
        }

       

        return newConnection;

    }

     private Object getObject(Anchor anchor) {
          
         if (anchor!=null) {
             
             Object obj=getBusinessObjectForPictogramElement(anchor);
             if(obj instanceof Entity)
                 return (Entity)obj;
             else if(obj instanceof Port)
                 return (Port)obj;
             else if(obj instanceof Relation)
                 return (Relation)obj;
             
         }
         return null;
     }

    /**
     * Returns the EClass belonging to the anchor, or null if not available.
     */

    private Entity getEntity(Anchor anchor) {

        if (anchor != null) {
            
            Object object =getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof Entity) {
                return (Entity) object;
                
            }
        }
        else
        {
            System.out.println("Common this is true");  
        }
        return null;
    }

 

    /**

    * Creates a EReference between two EClasses.

    */

    private Link createLink(Object source,Object target) {

        KaomFactory kaomFactory=KaomFactory.eINSTANCE;
        Link link=kaomFactory.createLink();
        link.setName("new Link");
        
        
        
        
        System.out.println("Entity has been identified correctly:Source"+source.getName()+
                "Target"+target.getName());
        link.setSource(source);
        link.setTarget(target);
        source.getOutgoingLinks().add(link);
        target.getIncomingLinks().add(link);
        
       // source.getEStructuralFeatures().add(link);
        return link;

   }
}
