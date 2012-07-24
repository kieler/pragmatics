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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * Creates a link object and passes this object to the {@link AddLinkFeature}.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class CreateLinkFeature extends AbstractCreateConnectionFeature {

    /**
     * the semantic provider used to fetch the top-level element of the current
     * diagram.
     */
    private SemanticProvider semanticProvider;

    /**
     * The constructor.
     * 
     * @param fp
     *            the feature provider
     * @param sp
     *            the semantic provider
     */
    public CreateLinkFeature(final IFeatureProvider fp,
            final SemanticProvider sp) {
        super(fp, "Link", "Create Link");
        this.semanticProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateConnectionContext context) {
        Object source = null, target = null;
        Anchor sourceAnchor = context.getSourceAnchor();
        if (sourceAnchor != null) {
            source =
                    getBusinessObjectForPictogramElement(sourceAnchor
                            .getParent());
        }
        Anchor targetAnchor = context.getTargetAnchor();
        if (targetAnchor != null) {
            target =
                    getBusinessObjectForPictogramElement(targetAnchor
                            .getParent());
        }

        return (sourceAnchor == null || source instanceof Linkable)
                && (targetAnchor == null || target instanceof Linkable);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canStartConnection(final ICreateConnectionContext context) {
        return (context.getSourceAnchor() != null && getBusinessObjectForPictogramElement(context
                .getSourceAnchor().getParent()) != null);
    }

    /**
     * {@inheritDoc}
     */
    public Connection create(final ICreateConnectionContext context) {
        Object source = null, target = null;
        Anchor sourceAnchor = context.getSourceAnchor();
        if (sourceAnchor instanceof BoxRelativeAnchor) {
            source =
                    getBusinessObjectForPictogramElement(context
                            .getSourceAnchor());
        } else if (sourceAnchor != null) {
            source =
                    getBusinessObjectForPictogramElement(context
                            .getSourceAnchor().getParent());
        }
        Anchor targetAnchor = context.getTargetAnchor();
        if (targetAnchor instanceof BoxRelativeAnchor) {
            target =
                    getBusinessObjectForPictogramElement(context
                            .getTargetAnchor());
        } else if (targetAnchor != null) {
            target =
                    getBusinessObjectForPictogramElement(context
                            .getTargetAnchor().getParent());
        }

        if (source instanceof Linkable && target instanceof Linkable) {
            Link link = KaomFactory.eINSTANCE.createLink();
            link.setSource((Linkable) source);
            link.setTarget((Linkable) target);
            Entity sourceParent = null, targetParent = null;
            if (source instanceof Port) {
                sourceParent =
                        (Entity) ((Port) source).eContainer().eContainer();
            } else {
                sourceParent = (Entity) ((EObject) source).eContainer();
            }
            if (target instanceof Port) {
                targetParent =
                        (Entity) ((Port) target).eContainer().eContainer();
            } else {
                targetParent = (Entity) ((EObject) target).eContainer();
            }
            Entity topEntity = null;
            if (targetParent == sourceParent) {
                topEntity = sourceParent;
            } else {
                topEntity = semanticProvider.fetchEntity(getDiagram());
            }
            topEntity.getChildLinks().add(link);

            AddConnectionContext addContext =
                    new AddConnectionContext(context.getSourceAnchor(),
                            context.getTargetAnchor());
            addContext.setNewObject(link);
            Connection connection =
                    (Connection) getFeatureProvider().addIfPossible(addContext);
            getFeatureProvider().getDirectEditingInfo().setActive(true);
            return connection;
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
