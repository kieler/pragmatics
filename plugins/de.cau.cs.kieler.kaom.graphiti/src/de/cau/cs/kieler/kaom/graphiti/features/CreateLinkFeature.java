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


import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
/**
 * 
 * @author atr
 * Creates a link object and passes this object to AddLinkFeature class
 */
public class CreateLinkFeature extends AbstractCreateConnectionFeature {

    /**
    
     * @param fp
     
     * Constructor

     */
    public CreateLinkFeature(final IFeatureProvider fp)//, String name, String description) {
    {
        super(fp , "Link" , "Create Link"); // name, description);
         }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateConnectionContext context) {
    /*    Entity target=null,source=null;
        if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
       { 
           if(getBusinessObjectForPictogramElement(
           context.getTargetPictogramElement()) instanceof Entity)         
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
        Object source = null , target = null;
            source = getObject(context.getSourceAnchor());
            target = getObject(context.getTargetAnchor());
                    
            
        if (source != null && target != null && source != target) {
          
      //      System.out.println("helelelelllllo     it is here111111111111111");
            return true;
        }

        return false;
    }

 
    /**
     * {@inheritDoc}
     */
    public boolean canStartConnection(final ICreateConnectionContext context) {
    //    System.out.println("I cam here111111111!!!!!");
       // if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
     //      return true;
    /*  if(context.getSourcePictogramElement() instanceof AnchorContainer )
      {
            System.out.println("Hellooooo111111111111111111");
            AnchorContainer anchorContainer=(AnchorContainer)context.getSourcePictogramElement();
           )
      */  
       
        if (context.getSourceAnchor() != null) {
      //      System.out.println("sourceAnchor is not null");
            if (getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent()) != null) {
            return true;
        }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Connection create(final ICreateConnectionContext context) {
     //   System.out.println("I cam here222222222222222222!!!!!");
        Connection newConnection = null;
     /*   Entity target=null,source=null;
        if(getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof Entity)
        { 
            if(getBusinessObjectForPictogramElement(
            context.getTargetPictogramElement()) instanceof Entity)         
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
        
        Object source = null, target = null;
        
        source = getObject(context.getSourceAnchor());
        target = getObject(context.getTargetAnchor());
        
        
        if (source != null && target != null) {

            Link link = createLink(source, target);
        //    System.out.println("Link has been correctly formed");
            AddConnectionContext addContext = 
                new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());

            addContext.setNewObject(link);
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }

       

        return newConnection;

    }

    /**
  
     * Returns the Entity belonging to the anchor, or null if not available.
   
     * @param anchor
     * @return
     */
     private Object getObject(final Anchor anchor) {
          
         if (anchor != null) {
             
             Object obj = getBusinessObjectForPictogramElement(anchor.getParent());
         /*    if (obj instanceof Entity) {
                 return (Entity) obj;  }
             else if (obj instanceof Port) {
                 return (Port) obj; }
             else if (obj instanceof Relation) {
                 return (Relation) obj; }
           */
             if (obj instanceof Linkable) {
                 return (Linkable) obj;
             }
         }
         return null;
     }

    /**

    * Creates a EReference between two EClasses.

    */

    private Link createLink(final Object source, final Object target) {

        KaomFactory kaomFactory = KaomFactory.eINSTANCE;
        Link link = kaomFactory.createLink();
        link.setName("new Link");
        
      /*
        if (source instanceof Entity) {
       
            
            Entity entitySource = (Entity) source;
            link.setSource(entitySource);
            entitySource.getOutgoingLinks().add(link);
        }
        else if (source instanceof Port) {
            Port portSource = (Port) source;
            link.setSource(portSource);
            portSource.getOutgoingLinks().add(link);
        }
        else {
            Relation relationSource = (Relation) source;
            link.setSource(relationSource);
            relationSource.getOutgoingLinks().add(link);
          }
  */
        if (source instanceof Linkable) {
                  
            Linkable linkableSource = (Linkable) source;
            linkableSource.getOutgoingLinks().add(link);
        } else {
            JOptionPane.showMessageDialog(null, "Source object not linkable",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
           
        if (target instanceof Linkable) {
            
            Linkable linkableTarget = (Linkable) target;
            linkableTarget.getIncomingLinks().add(link);
        } else {
            JOptionPane.showMessageDialog(null, "Target Object not linkable",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }     
        
      /*  if (target instanceof Entity) {
            Entity entityTarget = (Entity) target;
            link.setTarget(entityTarget);
            entityTarget.getIncomingLinks().add(link);
        }
        else if (target instanceof Port) {
            Port portTarget = (Port) target;
            link.setTarget(portTarget);
            portTarget.getIncomingLinks().add(link);
        }
        else {
            Relation relationTarget = (Relation) target;
            link.setTarget(relationTarget);
            relationTarget.getIncomingLinks().add(link);
          }
          */
              
        
    //    System.out.println("Entity has been identified correctly:Source"+source.getName()+
      //          "Target"+target.getName());
     //   link.setSource(source);
     //   link.setTarget(target);
        
      //  target.getIncomingLinks().add(link);
        
       // source.getEStructuralFeatures().add(link);
        addToDiagram(link, source, target);
        return link;

   }
    
    /**
     * 
     * @param newLink
     * @param source
     * @param target
     * 
     * Adds the link as a child link to the top level entity
     */
    
    private void addToDiagram(final Link newLink, final Object source, final Object target) {
    //  boolean flag = false;
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                break;
            }
        }
        topEntity.getChildLinks().add(newLink);
               /* 
                if (target instanceof Entity) {
                    Entity targetEntity = (Entity) target;
                  if ((Entity) targetEntity.eContainer() == (topEntity)) {
                     if ((Entity) topEntity.eContainer() == null) {
                         topEntity.getChildLinks().add(newLink);
                          flag = true;
                    }
                  }
                }
                else if (target instanceof Port) {
                    Port port = (Port) target;
                  if ((Entity) port.eContainer() == (topEntity)) {
                      if ((Entity) topEntity.eContainer() == null) {
                       topEntity.getChildLinks().add(newLink);
                      flag = true;
                      }
                  }
                }
                else {
                    Relation relation = (Relation) target;
                  if ((Entity) relation.eContainer() == (topEntity)) {
                      if ((Entity) topEntity.eContainer() == null) {
                      topEntity.getChildLinks().add(newLink);
                      flag = true;
                      }
                }
                
           }    
        
        Entity parentEntity;
          
        if(flag == false) {
                            
                if (source instanceof Entity) {
                    Entity sourceEntity = (Entity) source;
                    parentEntity=(Entity) sourceEntity.eContainer();
                    parentEntity.getChildLinks().add(newLink);
                 
                }
                else if (source instanceof Port) {
                    Port port = (Port) source;
                    parentEntity=(Entity) port.eContainer();
                    parentEntity.getChildLinks().add(newLink);
                     
                }
                else {
                    Relation relation = (Relation) source;
                    parentEntity=(Entity) relation.eContainer();
                    parentEntity.getChildLinks().add(newLink);
                      
                }
                 
            }             
        */
        }
        
}

