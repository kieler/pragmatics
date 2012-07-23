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
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;

/**
 * Adds a new relation object.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class AddRelationFeature extends AbstractAddShapeFeature {

    /** the style provider. */
    private IStyleProvider styleProvider;

    /**
     * The constructor.
     * 
     * @param fp
     *            the feature provider
     * @param sp
     *            the style provider
     */
    public AddRelationFeature(final IFeatureProvider fp, final IStyleProvider sp) {
        super(fp);
        this.styleProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canAdd(final IAddContext context) {
        return (context.getNewObject() instanceof Relation);
    }

    private static final int VERTEX_RADIUS = 8;

    /**
     * {@inheritDoc}
     */
    public PictogramElement add(final IAddContext context) {
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        IGaService gaService = Graphiti.getGaService();
        Shape relationShape =
                peCreateService.createShape(context.getTargetContainer(), true);
        Polygon polygon =
                gaService.createPolygon(relationShape, new int[] { 0,
                        VERTEX_RADIUS, VERTEX_RADIUS, 2 * VERTEX_RADIUS,
                        2 * VERTEX_RADIUS, VERTEX_RADIUS, VERTEX_RADIUS, 0 });
        polygon.setStyle(styleProvider.getStyle(StyleProvider.RELATION_STYLE));
        gaService.setLocationAndSize(polygon, context.getX(), context.getY(),
                2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS, false);
        peCreateService.createChopboxAnchor(relationShape);

        link(relationShape, context.getNewObject());
        return relationShape;
    }

}
