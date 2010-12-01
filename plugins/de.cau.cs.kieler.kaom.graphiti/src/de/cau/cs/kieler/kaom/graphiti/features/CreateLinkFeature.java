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
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;
import de.cau.cs.kieler.kaom.graphiti.diagram.KaomDiagramEditor;

/**
 * Creates a link object and passes this object to the {@link AddLinkFeature}.
 * 
 * @author atr
 */
public class CreateLinkFeature extends AbstractCreateConnectionFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public CreateLinkFeature(final IFeatureProvider fp) {
        super(fp, "Link", "Create Link");
    }

    /**
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateConnectionContext context) {
        Object source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());
        Object target = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());

        return (source instanceof Linkable && target instanceof Linkable && source != target);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canStartConnection(final ICreateConnectionContext context) {
        return (context.getSourceAnchor() != null
                && getBusinessObjectForPictogramElement(
                context.getSourceAnchor().getParent()) != null);
    }

    /**
     * {@inheritDoc}
     */
    public Connection create(final ICreateConnectionContext context) {
        Object source = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());
        Object target = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());

        if (source instanceof Linkable && target instanceof Linkable) {
            KaomFactory kaomFactory = KaomFactory.eINSTANCE;
            Link link = kaomFactory.createLink();
            link.setSource((Linkable) source);
            link.setTarget((Linkable) target);
            Entity topEntity = ((KaomDiagramEditor) getDiagramEditor()).fetchEntity(getDiagram());
            topEntity.getChildLinks().add(link);
            
            getFeatureProvider().getDirectEditingInfo().setActive(true);
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                    context.getTargetAnchor());
            addContext.setNewObject(link);
            return (Connection) getFeatureProvider().addIfPossible(addContext);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateImageId() {
        return ImageProvider.IMAGE_LINK;
    }

}
