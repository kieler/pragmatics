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
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.kaom.Entity;

public class MoveEntityFeature extends DefaultMoveShapeFeature {

    public MoveEntityFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        boolean canMove = context.getSourceContainer() != null;
        
     //   if(canMove)
     //   {
      //      Shape shape=context.getShape();
      // obj=getBusinessObjectForPictogramElement(shape);
         //   if(obj instanceof Entity)
        //    {
         //       Entity en=(Entity)obj;
           //     if(en.getName()==null || en.getName().length()==1)
           //         canMove=false;
           // }
      //  }
        
        return canMove;
    }
    
    @Override
    protected void internalMove(final IMoveShapeContext context) {
        if (!getUserDecision()) {
                return;
        }
        Shape shapeToMove = context.getShape();
        ContainerShape oldContainerShape = context.getSourceContainer();
        ContainerShape newContainerShape = context.getTargetContainer();
       
        int x = context.getX();
        int y = context.getY();

        if (oldContainerShape != newContainerShape) {
               
                Entity oldParentEntity = 
                    (Entity) getBusinessObjectForPictogramElement(oldContainerShape);
                Entity newParentEntity = 
                    (Entity) getBusinessObjectForPictogramElement(newContainerShape);
                Entity en = (Entity) getBusinessObjectForPictogramElement(shapeToMove);           
              
                // remember selection, because it is lost when temporarily removing the shapes.
                PictogramElement[] currentSelection = getDiagramEditor().getSelectedPictogramElements();
                // the following is a workaround due to an MMR bug
                if (oldContainerShape != null) {
                        Collection<Shape> children = oldContainerShape.getChildren();
                        if (children != null) {
                             children.remove(shapeToMove);
                             if (oldParentEntity != null) {
                             oldParentEntity.getChildEntities().remove(en);
                             }
                        }
                }

                shapeToMove.setContainer(newContainerShape);
                if (newParentEntity != null) {
                newParentEntity.getChildEntities().add(en);
                }
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                        Graphiti.getGaService().setLocation(
                                shapeToMove.getGraphicsAlgorithm(), x, y, avoidNegativeCoordinates());
                }
                // restore selection
                getDiagramEditor().setPictogramElementsForSelection(currentSelection);
        } else { // move within the same container
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                        Graphiti.getGaService().setLocation(
                                shapeToMove.getGraphicsAlgorithm(), x, y, avoidNegativeCoordinates());
                }
        }
}

    // move bendpoints within a container shape
    /**
     * Move all bendpoints.
     * 
     * @param context
     *            the context
     */
    protected void moveAllBendpoints(final IMoveShapeContext context) {
        
           if (!(context.getShape() instanceof ContainerShape)) {
                return;
        }

        ContainerShape shapeToMove = (ContainerShape) context.getShape();

        int x = context.getX();
        int y = context.getY();

        int deltaX = x - shapeToMove.getGraphicsAlgorithm().getX();
        int deltaY = y - shapeToMove.getGraphicsAlgorithm().getY();

        if (deltaX != 0 || deltaY != 0) {

                List<Anchor> anchorsFrom = getAnchors(shapeToMove);
                List<Anchor> anchorsTo = new ArrayList<Anchor>(anchorsFrom);

                for (Anchor anchorFrom : anchorsFrom) {

                        Collection<Connection> outgoingConnections = anchorFrom.getOutgoingConnections();

                        for (Connection connection : outgoingConnections) {
                                for (Anchor anchorTo : anchorsTo) {

                                        Collection<Connection> incomingConnections = anchorTo.getIncomingConnections();
                                        if (incomingConnections.contains(connection)) {
                                                if (connection instanceof FreeFormConnection) {
                                                        FreeFormConnection ffc = 
                                                            (FreeFormConnection) connection;
                                                        List<Point> points = ffc.getBendpoints();
                                                        for (int i = 0; i < points.size(); i++) {
                                                                Point point = points.get(i);
                                                                int oldX = point.getX();
                                                                int oldY = point.getY();
                                                                points.set(i, Graphiti.getGaCreateService().createPoint(oldX + deltaX, oldY + deltaY));
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
    
    
}
