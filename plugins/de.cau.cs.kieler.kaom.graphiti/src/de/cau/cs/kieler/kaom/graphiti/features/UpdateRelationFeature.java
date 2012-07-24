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
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;

import de.cau.cs.kieler.kaom.Relation;

/**
 * Updates any changes made to a relation.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class UpdateRelationFeature extends AbstractUpdateFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public UpdateRelationFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canUpdate(final IUpdateContext context) {
        Object obj = getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (obj instanceof Relation);
    }

    /**
     * {@inheritDoc}
     */
    public IReason updateNeeded(final IUpdateContext context) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final IUpdateContext context) {
        return true;
    }

}
