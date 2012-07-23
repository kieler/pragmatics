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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * Feature for moving  entities.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class MoveEntityFeature extends DefaultMoveShapeFeature {

    /** the semantic provider used to fetch the top-level element of the current diagram. */
    private SemanticProvider semanticProvider;
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     * @param sp the semantic provider
     */
    public MoveEntityFeature(final IFeatureProvider fp, final SemanticProvider sp) {
        super(fp);
        this.semanticProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMoveShape(final IMoveShapeContext context) {
        return (context.getSourceContainer() != null
                && (getBusinessObjectForPictogramElement(context.getTargetContainer()) 
                        instanceof Entity));
    }

    /**
     *  {@inheritDoc}
     */
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

        if (oldContainerShape == newContainerShape) {
            // move within the same container
            if (shapeToMove.getGraphicsAlgorithm() != null) {
                Graphiti.getGaService().setLocation(shapeToMove.getGraphicsAlgorithm(), x, y,
                        avoidNegativeCoordinates());
            }
        } else {
            // check if the source container is the diagram and get the parent Entity
            // in which this element is contained
            oldParentEntity = semanticProvider.fetchEntity(oldContainerShape);

            // check if the target container is the diagram and get the parent Entity
            // in which this element is contained
            newParentEntity = semanticProvider.fetchEntity(newContainerShape);

            Entity en = (Entity) getBusinessObjectForPictogramElement(shapeToMove);

            // removes the element from the diagram and the XML code
            PictogramElement[] currentSelection = getDiagramEditor().getSelectedPictogramElements();
            if (oldContainerShape != null) {
                oldContainerShape.getChildren().remove(shapeToMove);
                if (oldParentEntity != null) {
                    oldParentEntity.getChildEntities().remove(en);
                }
            }

            // Adds the element to the diagram and the XML code
            shapeToMove.setContainer(newContainerShape);
            if (newParentEntity != null) {
                newParentEntity.getChildEntities().add(en);
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
