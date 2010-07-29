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
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;

/**
 * 
 * @author atr Creates an entity object and passes this object to the AddEntityFeature class
 */
public class CreateEntityFeature extends AbstractCreateFeature {

    /**
     * @param fp
     *            Constructor.
     */
    public CreateEntityFeature(final IFeatureProvider fp) {
        super(fp, "Entity", "Create Entity");
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {

        return (context.getTargetContainer() instanceof Diagram
                || context.getTargetContainer() instanceof ContainerShape);

    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {

        // create Entity
        Entity newEntity = KaomFactory.eINSTANCE.createEntity();
        addGraphicalRepresentation(context, newEntity);
        getFeatureProvider().getDirectEditingInfo().setActive(true);

        return new Object[] { newEntity };
    }

    /**
     * 
     * {@inheritDoc}
     * Gets the image for the entity.
     * Used in tool palette.
     */
    @Override
    public String getCreateImageId() {
        return ImageProvider.IMAGE_ENTITY;
    }

}
