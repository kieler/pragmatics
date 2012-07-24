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
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.graphiti.diagram.ImageProvider;

/**
 * Creates a port object and passes it to the add port feature.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class CreatePortFeature extends AbstractCreateFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public CreatePortFeature(final IFeatureProvider fp) {
        super(fp,  "Port", "Create Port");
    }

    /**
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {
        return (getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof Entity)
                && !(context.getTargetContainer() instanceof Diagram);
    }

    /**
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {
        Entity entity = (Entity) getBusinessObjectForPictogramElement(context.getTargetContainer());
        Port port = KaomFactory.eINSTANCE.createPort();
        entity.getChildPorts().add(port);

        addGraphicalRepresentation(context, port);
        return new Object[] { port };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateImageId() {
        return ImageProvider.IMAGE_PORT;
    }

}
