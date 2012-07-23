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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * Creates an entity object and passes this object to the {@link AddEntityFeature}.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class CreateEntityFeature extends AbstractCreateFeature {

    /** the semantic provider used to fetch the top-level element of the current diagram. */
    private SemanticProvider semanticProvider;
    
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     * @param sp the semantic provider
     */
    public CreateEntityFeature(final IFeatureProvider fp, final SemanticProvider sp) {
        super(fp, "Entity", "Create Entity");
        this.semanticProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {
        Entity newEntity = KaomFactory.eINSTANCE.createEntity();
        
        Entity parentEntity = semanticProvider.fetchEntity(context.getTargetContainer());
        parentEntity.getChildEntities().add(newEntity);
        
        addGraphicalRepresentation(context, newEntity);
        getFeatureProvider().getDirectEditingInfo().setActive(true);
        return new Object[] { newEntity };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateImageId() {
        return ImageProvider.IMAGE_ENTITY;
    }

}
