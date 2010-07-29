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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polygon;
//import org.eclipse.graphiti.mm.pictograms.Rectangle;
//import org.eclipse.graphiti.mm.pictograms.RoundedRectangle;
//import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.kaom.Entity;
//import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.util.StyleUtil;
import de.cau.cs.kieler.kaom.graphiti.util.DomainUtility;

/**
 * 
 * @author atr Class adds a new relation object
 */
public class AddRelationFeature extends AbstractAddShapeFeature {

    private static final int[] VERTICES_POSITION = { -9, 0, 0, 12, 9, 0, 0, -12 };
    private static final IColorConstant RELATION_BACKGROUND = new ColorConstant(70, 70, 70);

    /**
     * 
     * @param fp
     *            Constructor.
     */
    public AddRelationFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        Relation relation = (Relation) context.getNewObject();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        Diagram targetDiagram;
        ContainerShape containerShape;
        IGaService gaService = Graphiti.getGaService();
        if (context.getTargetContainer() instanceof Diagram) {
        //Target Container is the diagram
            targetDiagram = (Diagram) context.getTargetContainer();
            containerShape = peCreateService.createContainerShape(targetDiagram, true);

            Polygon polygon = gaService.createPolygon(containerShape, VERTICES_POSITION);

            polygon.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
            polygon.setBackground(manageColor(RELATION_BACKGROUND));
            Graphiti.getGaService().setLocation(containerShape.getGraphicsAlgorithm(),
                    context.getX(), context.getY(), false);

            addToDiagram(relation, context);

            peCreateService.createChopboxAnchor(containerShape);

            link(containerShape, relation);

        } else {
            //Target Container is an Entity
            containerShape = context.getTargetContainer();
            ContainerShape childcontainershape = peCreateService.createContainerShape(
                    containerShape, true);
            Polygon polygon = gaService.createPolygon(childcontainershape, VERTICES_POSITION);

            polygon.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
            polygon.setBackground(manageColor(RELATION_BACKGROUND));
            Graphiti.getGaService().setLocation(childcontainershape.getGraphicsAlgorithm(),
                    context.getX(), context.getY(), false);

            addToDiagram(relation, context);

            peCreateService.createChopboxAnchor(childcontainershape);

            link(childcontainershape, relation);

        }

        return containerShape;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        
        if (context.getNewObject() instanceof Relation) {

            if (context.getTargetContainer() instanceof ContainerShape
                    || context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param newRelation
     * @param context
     *            Adds the new RELATION formed to its container ENTITY
     */
    private void addToDiagram(final Relation newRelation, final IAddContext context) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        if (context.getTargetContainer() instanceof Diagram) {
            for (EObject obj : contents) {
                if (obj instanceof Entity) {
                    topEntity = (Entity) obj;
                    break;
                }
            }
        } else {
            Object ob = getBusinessObjectForPictogramElement(context.getTargetContainer());
            if (ob instanceof Entity) {
                topEntity = (Entity) ob;
            }

        }

        if (topEntity == null) {
            topEntity = DomainUtility.createParentEntity(getDiagram());
        }

        topEntity.getChildRelations().add(newRelation);
    }

}
