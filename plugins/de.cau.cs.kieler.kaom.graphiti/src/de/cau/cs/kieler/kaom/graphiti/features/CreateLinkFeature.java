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

import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;

/**
 * 
 * @author atr Creates a link object and passes this object to AddLinkFeature class.
 */
public class CreateLinkFeature extends AbstractCreateConnectionFeature {

    /**
     * 
     * @param fp
     * 
     *            Constructor.
     */
    public CreateLinkFeature(final IFeatureProvider fp) {
        super(fp, "Link", "Create Link");
    }

    /**
     * 
     * {@inheritDoc} Checks if the source and target elements are Linkable.
     */
    public boolean canCreate(final ICreateConnectionContext context) {

        Object source = null, target = null;
        source = getObject(context.getSourceAnchor());
        target = getObject(context.getTargetAnchor());

        if (source != null && target != null && source != target) {

            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public boolean canStartConnection(final ICreateConnectionContext context) {

        if (context.getSourceAnchor() != null) {

            if (getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent()) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc} Creates a new connection object .
     */
    public Connection create(final ICreateConnectionContext context) {

        Connection newConnection = null;

        Object source = null, target = null;

        source = getObject(context.getSourceAnchor());
        target = getObject(context.getTargetAnchor());

        if (source != null && target != null) {

            Link link = createLink(source, target);
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
                    context.getTargetAnchor());

            addContext.setNewObject(link);
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }

        return newConnection;

    }

    /**
     * 
     * Returns the Entity belonging to the anchor, or null if not available.
     * 
     * @param anchor
     * @return
     */
    private Object getObject(final Anchor anchor) {

        if (anchor != null) {

            Object obj = getBusinessObjectForPictogramElement(anchor.getParent());

            if (obj instanceof Linkable) {
                return (Linkable) obj;
            }
        }
        return null;
    }

    /**
     * 
     * Creates a Link between two Linkable Objects.
     */

    private Link createLink(final Object source, final Object target) {

        KaomFactory kaomFactory = KaomFactory.eINSTANCE;
        Link link = kaomFactory.createLink();

        if (source instanceof Linkable) {

            Linkable linkableSource = (Linkable) source;
            linkableSource.getOutgoingLinks().add(link);
        } else {
            JOptionPane.showMessageDialog(null, "Source object not linkable", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (target instanceof Linkable) {

            Linkable linkableTarget = (Linkable) target;
            linkableTarget.getIncomingLinks().add(link);
        } else {
            JOptionPane.showMessageDialog(null, "Target Object not linkable", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        addToDiagram(link, source, target);
        getFeatureProvider().getDirectEditingInfo().setActive(true);
        return link;

    }

    /**
     * 
     * @param newLink
     * @param source
     * @param target
     * 
     *            Adds the link as a child link to the top level entity
     */

    private void addToDiagram(final Link newLink, final Object source, final Object target) {
        List<EObject> contents = getDiagram().eResource().getContents();
        Entity topEntity = null;
        for (EObject obj : contents) {
            if (obj instanceof Entity) {
                topEntity = (Entity) obj;
                break;
            }
        }
        topEntity.getChildLinks().add(newLink);

    }

    /**
     * Gets the image for the link.
     * {@inheritDoc}
     */
    @Override
    public String getCreateImageId() {
        return ImageProvider.IMAGE_LINK;
    }

}
