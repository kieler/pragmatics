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


import java.util.Collection;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;

import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.util.TopParentEntity;

/**
 * 
 * @author atr
 * Constructor to move relation ana make changes accordibgly
 */
public class MoveRelationFeature extends DefaultMoveShapeFeature {

    /**
     * 
     * @param fp
     * Constructor
     */
    public MoveRelationFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        boolean canMove = context.getSourceContainer() != null;
        if (canMove) {
            canMove = context.getTargetContainer() != null;
        }
    return canMove;
    }
    
    @Override
    protected void internalMove(final IMoveShapeContext context) {
        if (!getUserDecision()) {
                return;
        }
      Shape shapeToMove = context.getShape();
     
      //  ContainerShape oldContainerShape = context.getSourceContainer();
   //     ContainerShape newContainerShape = context.getTargetContainer();
        ContainerShape oldContainerShape = null, newContainerShape = null;
        Entity oldParentEntity = null, newParentEntity = null;
        int x = context.getX();
        int y = context.getY();
        
        if (context.getSourceContainer() == context.getTargetContainer()) { 
            // move within the same container
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                    Graphiti.getGaService()
                                        .setLocation(shapeToMove.getGraphicsAlgorithm(), 
                                                x, y, avoidNegativeCoordinates());
                }
        } else {
            
      if (context.getSourceContainer() instanceof Diagram 
                && context.getTargetContainer() instanceof ContainerShape) {
  
          // the following is a workaround due to an MMR bug    
          Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
     //     System.out.println("yaaaa ihave come here2222222222222");
          oldContainerShape = context.getSourceContainer();
          newContainerShape = context.getTargetContainer();
          oldParentEntity = TopParentEntity.getParentEntity();
          newParentEntity = 
            (Entity) getBusinessObjectForPictogramElement(newContainerShape);
          Collection<Shape> children = context.getTargetContainer().getChildren();
          if (children != null) {
              children.remove(shapeToMove);
               if (oldParentEntity != null) {
                    oldParentEntity.getChildRelations().remove(relation);
                                  }
          }
        } else if (context.getSourceContainer() instanceof ContainerShape 
        && context.getTargetContainer() instanceof Diagram) {    
          
       //   System.out.println("yaaaa ihave come here333333333333333");
          Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
          oldContainerShape = context.getSourceContainer();
          newContainerShape = context.getTargetContainer();
          newParentEntity = TopParentEntity.getParentEntity();
          oldParentEntity = (Entity) getBusinessObjectForPictogramElement(oldContainerShape);
         // shapeToMove.setContainer(context.getTargetContainer());
         // shapeToMove.setContainer((Diagram)context.getTargetContainer());
        //  IPeCreateService peCreateService = Graphiti.getPeCreateService();

        
         shapeToMove.setContainer(newContainerShape);
         if (newParentEntity != null) {
              newParentEntity.getChildRelations().add(relation);
          }
          if (shapeToMove.getGraphicsAlgorithm() != null) {
                  Graphiti.getGaService()
                                  .setLocation(shapeToMove.getGraphicsAlgorithm(), 
                                          x, y, avoidNegativeCoordinates());
          }
        } else if (context.getSourceContainer() instanceof ContainerShape 
              && context.getTargetContainer() instanceof ContainerShape) {  
    //      System.out.println("yaaaa ihave come here111111111111");
          oldContainerShape = context.getSourceContainer();
          newContainerShape = context.getTargetContainer();
       
         
           if (oldContainerShape != newContainerShape) {
          
               oldParentEntity = (Entity) getBusinessObjectForPictogramElement(oldContainerShape);
               newParentEntity = (Entity) getBusinessObjectForPictogramElement(newContainerShape);
        
           }
      }
      
             
        
        // remember selection, because it is lost when temporarily removing the shapes.
        PictogramElement[] currentSelection = getDiagramEditor().getSelectedPictogramElements();
      
        // the following is a workaround due to an MMR bug    
        Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
                
        if (oldContainerShape != null) {
             Collection<Shape> children = oldContainerShape.getChildren();
             if (children != null) {
                children.remove(shapeToMove);
                 if (oldParentEntity != null) {
                      oldParentEntity.getChildRelations().remove(relation);
                                    }
                        }
                }

                shapeToMove.setContainer(newContainerShape);
                if (newParentEntity != null) {
                    newParentEntity.getChildRelations().add(relation);
                }
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                        Graphiti.getGaService()
                                        .setLocation(shapeToMove.getGraphicsAlgorithm(), 
                                                x, y, avoidNegativeCoordinates());
                }
                // restore selection
                getDiagramEditor().setPictogramElementsForSelection(currentSelection);
            }   
        }



  
   
    
}
