package de.cau.cs.kieler.kaom.graphiti.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.datatypes.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.util.TopParentEntity;

public class MoveRelationFeature extends DefaultMoveShapeFeature {

    public MoveRelationFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        boolean canMove = context.getSourceContainer() != null;
        if(canMove)
            canMove = context.getTargetContainer()!=null;
        
    return canMove;
    }
    
    @Override
    protected void internalMove(final IMoveShapeContext context) {
        if (!getUserDecision()) {
                return;
        }
      Shape shapeToMove = context.getShape();
       if(shapeToMove==null)
           System.out.println("Thank You");
       if(context.getSourceContainer() instanceof Diagram)
            System.out.println("Heeeello");
        else
            System.out.println("ya hoon");
      //  ContainerShape oldContainerShape = context.getSourceContainer();
   //     ContainerShape newContainerShape = context.getTargetContainer();
        ContainerShape oldContainerShape = null, newContainerShape = null;
        Entity oldParentEntity = null, newParentEntity = null;
        int x = context.getX();
        int y = context.getY();
        
        System.out.println("yaaaa ihave come here");
        
        if (context.getSourceContainer() == context.getTargetContainer()) { 
            // move within the same container
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                    Graphiti.getGaService()
                                        .setLocation(shapeToMove.getGraphicsAlgorithm(), 
                                                x, y, avoidNegativeCoordinates());
                }
        }
        else {
            
      if (context.getSourceContainer() instanceof Diagram 
                && context.getTargetContainer() instanceof ContainerShape) {
  
          // the following is a workaround due to an MMR bug    
          Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
          System.out.println("yaaaa ihave come here2222222222222");
          oldContainerShape = context.getSourceContainer();
          newContainerShape = context.getTargetContainer();
          oldParentEntity = TopParentEntity.parentEntity;
          newParentEntity = 
            (Entity) getBusinessObjectForPictogramElement(newContainerShape);
          Collection<Shape> children = context.getTargetContainer().getChildren();
          if (children != null) {
              children.remove(shapeToMove);
               if (oldParentEntity != null) {
                    oldParentEntity.getChildRelations().remove(relation);
                                  }
          }
        }
      else if (context.getSourceContainer() instanceof ContainerShape 
        && context.getTargetContainer() instanceof Diagram) {    
          
          System.out.println("yaaaa ihave come here333333333333333");
          Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
          oldContainerShape = context.getSourceContainer();
          newContainerShape = context.getTargetContainer();
          newParentEntity = TopParentEntity.parentEntity;
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
        }
      else if (context.getSourceContainer() instanceof ContainerShape 
              && context.getTargetContainer() instanceof ContainerShape) {  
          System.out.println("yaaaa ihave come here111111111111");
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



  
    // move bendpoints within a container shape
@Override
protected void moveAllBendpoints(final IMoveShapeContext context) {

        if (!(context.getShape() instanceof ContainerShape || context.getShape() instanceof Shape)) {
      //      System.out.println("I came here and went away!!!");
                return;
        }
        ContainerShape shapeToMove = null;
        //Shape shapeshapeToMove;

        if (context.getShape() instanceof ContainerShape) {
        shapeToMove = (ContainerShape) context.getShape();
        }
       
       
        int x = context.getX();
        int y = context.getY();
    //    System.out.println("x:"+x+"y:"+y+"::Earlier x:"+shapeToMove.getGraphicsAlgorithm().getX()+"::Earlier y:"+shapeToMove.getGraphicsAlgorithm().getY());
        int deltaX = x - shapeToMove.getGraphicsAlgorithm().getX();
        int deltaY = y - shapeToMove.getGraphicsAlgorithm().getY();

        if (deltaX != 0 || deltaY != 0) {

                List<Anchor> anchorsFrom = getAnchors(shapeToMove);
                List<Anchor> anchorsTo = new ArrayList<Anchor>(anchorsFrom);

                for (Anchor anchorFrom : anchorsFrom) {

                        Collection<Connection> outgoingConnections = anchorFrom.getOutgoingConnections();

                        for (Connection connection : outgoingConnections) {
                                for (Anchor anchorTo : anchorsTo) {

                                        Collection<Connection> incomingConnections = 
                                            anchorTo.getIncomingConnections();
                                        if (incomingConnections.contains(connection)) {
                                                if (connection instanceof FreeFormConnection) {
                                                        FreeFormConnection ffc = 
                                                            (FreeFormConnection) connection;
                                                        List<Point> points = ffc.getBendpoints();
                                                        for (int i = 0; i < points.size(); i++) {
                                                                Point point = points.get(i);
                                                                int oldX = point.getX();
                                                                int oldY = point.getY();
                                                                points.set(i, Graphiti.
                                                                        getGaCreateService().
                                                                        createPoint(oldX 
                                                                        + deltaX, oldY + deltaY));
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
}
    
    
    private List<Anchor> getAnchors(final ContainerShape containerShape) {
        List<Anchor> ret = new ArrayList<Anchor>();
        ret.addAll(containerShape.getAnchors());

        List<Shape> children = containerShape.getChildren();
        for (Shape shape : children) {
                if (shape instanceof ContainerShape) {
                        ret.addAll(getAnchors((ContainerShape) shape));
                } else {
                        ret.addAll(shape.getAnchors());
                }
        }
        return ret;
    }
    
 /*   private List<Anchor> getAnchors(final Shape containerShape) {
        List<Anchor> ret = new ArrayList<Anchor>();
        ret.addAll(containerShape.getAnchors());

        List<Shape> children = containerShape.getChildren();
        for (Shape shape : children) {
                if (shape instanceof ContainerShape) {
                        ret.addAll(getAnchors((ContainerShape) shape));
                } else {
                        ret.addAll(shape.getAnchors());
                }
        }
        return ret;
    }*/
    
}
