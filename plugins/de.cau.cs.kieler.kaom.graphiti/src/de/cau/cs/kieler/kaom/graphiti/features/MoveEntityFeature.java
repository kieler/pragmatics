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
 * Class used to move the entity and adjust the XML code accordingly
 */
public class MoveEntityFeature extends DefaultMoveShapeFeature {

    /**
     * 
     * @param fp
     * Constructor
     */
    public MoveEntityFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        boolean canMove = context.getSourceContainer() != null;
        if (canMove 
                && !(getBusinessObjectForPictogramElement(context.getTargetContainer()) 
                    instanceof Relation)) {
            canMove = true;
    }
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
        Entity oldParentEntity, newParentEntity;
        int x = context.getX();
        int y = context.getY();

        if (oldContainerShape != newContainerShape) {
               
            if (context.getSourceContainer() instanceof Diagram) {
                oldParentEntity = TopParentEntity.getParentEntity();   
            } else {
               oldParentEntity = 
                    (Entity) getBusinessObjectForPictogramElement(oldContainerShape);
            } 
            
            if (context.getTargetContainer() instanceof Diagram) {
                  newParentEntity = TopParentEntity.getParentEntity();
               } else {
                  newParentEntity = 
                    (Entity) getBusinessObjectForPictogramElement(newContainerShape);
              }
                
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


    
    
}
