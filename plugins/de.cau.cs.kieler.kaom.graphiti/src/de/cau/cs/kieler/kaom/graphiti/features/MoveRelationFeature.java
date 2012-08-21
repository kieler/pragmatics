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
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * Feature for moving relations.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class MoveRelationFeature extends DefaultMoveShapeFeature {

    /** the semantic provider used to fetch the top-level element of the current diagram. */
    private SemanticProvider semanticProvider;
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     * @param sp the semantic provider
     */
    public MoveRelationFeature(final IFeatureProvider fp, final SemanticProvider sp) {
        super(fp);
        this.semanticProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        return (context.getSourceContainer() != null && context.getTargetContainer() != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalMove(final IMoveShapeContext context) {
        if (!getUserDecision()) {
            return;
        }
        Shape shapeToMove = context.getShape();

        ContainerShape oldContainerShape = null, newContainerShape = null;
        Entity oldParentEntity = null, newParentEntity = null;
        int x = context.getX();
        int y = context.getY();

        if (context.getSourceContainer() == context.getTargetContainer()) {
            // move within the same container
            if (shapeToMove.getGraphicsAlgorithm() != null) {
                Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y,
                        avoidNegativeCoordinates());
            }
        } else {
            // move when source is the diagram and target is a containerShape
            if (context.getSourceContainer() instanceof Diagram
                    && context.getTargetContainer() instanceof ContainerShape) {

                Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
                oldContainerShape = context.getSourceContainer();
                newContainerShape = context.getTargetContainer();
                oldParentEntity = semanticProvider.fetchEntity(oldContainerShape);
                newParentEntity = semanticProvider.fetchEntity(newContainerShape);
                Collection<Shape> children = context.getTargetContainer().getChildren();
                if (children != null) {
                    children.remove(shapeToMove);
                    if (oldParentEntity != null) {
                        oldParentEntity.getChildRelations().remove(relation);
                    }
                }

            } else if (context.getSourceContainer() instanceof ContainerShape
                    && context.getTargetContainer() instanceof Diagram) {
                // move when target is the diagram and source is a containerShape

                Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);
                oldContainerShape = context.getSourceContainer();
                newContainerShape = context.getTargetContainer();
                newParentEntity = semanticProvider.fetchEntity(newContainerShape);
                oldParentEntity = semanticProvider.fetchEntity(oldContainerShape);

                shapeToMove.setContainer(newContainerShape);
                if (newParentEntity != null) {
                    newParentEntity.getChildRelations().add(relation);
                }
                if (shapeToMove.getGraphicsAlgorithm() != null) {
                    Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y,
                            avoidNegativeCoordinates());
                }
            } else if (context.getSourceContainer() instanceof ContainerShape
                    && context.getTargetContainer() instanceof ContainerShape) {

                oldContainerShape = context.getSourceContainer();
                newContainerShape = context.getTargetContainer();

                if (oldContainerShape != newContainerShape) {

                    oldParentEntity = (Entity) getBusinessObjectForPictogramElement(oldContainerShape);
                    newParentEntity = (Entity) getBusinessObjectForPictogramElement(newContainerShape);

                }
            }

            // remember selection, because it is lost when temporarily removing the shapes.
            PictogramElement[] currentSelection = getDiagramEditor().getSelectedPictogramElements();

            Relation relation = (Relation) getBusinessObjectForPictogramElement(shapeToMove);

            // removes from the container and the parent Entity
            if (oldContainerShape != null) {
                Collection<Shape> children = oldContainerShape.getChildren();
                if (children != null) {
                    children.remove(shapeToMove);
                    if (oldParentEntity != null) {
                        oldParentEntity.getChildRelations().remove(relation);
                    }
                }
            }

            // Adds to the required container and the parent Entity
            shapeToMove.setContainer(newContainerShape);
            if (newParentEntity != null) {
                newParentEntity.getChildRelations().add(relation);
            }
            if (shapeToMove.getGraphicsAlgorithm() != null) {
                Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y,
                        avoidNegativeCoordinates());
            }
            // restore selection
            getDiagramEditor().setPictogramElementsForSelection(currentSelection);
        }
    }

}
