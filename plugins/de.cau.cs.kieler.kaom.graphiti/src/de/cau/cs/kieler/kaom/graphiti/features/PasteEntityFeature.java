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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.graphiti.diagram.SemanticProvider;

/**
 * Feature for pasting entities in the clip board into the graphical editor.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class PasteEntityFeature extends AbstractPasteFeature {

    /** the semantic provider used to fetch the top-level element of the current diagram. */
    private SemanticProvider semanticProvider;
    
    /**
     * The Constructor.
     * 
     * @param fp the feature provider
     * @param sp the semantic provider
     */
    public PasteEntityFeature(final IFeatureProvider fp, final SemanticProvider sp) {
        super(fp);
        this.semanticProvider = sp;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canPaste(final IPasteContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes.length != 1 || !(pes[0] instanceof ContainerShape)) {
            return false;
        }

        Object[] fromClipboard = getFromClipboard();
        if (fromClipboard == null || fromClipboard.length == 0) {
            return false;
        }
        for (Object object : fromClipboard) {
            if (!(object instanceof Entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void paste(final IPasteContext context) {
        ContainerShape container = (ContainerShape) context.getPictogramElements()[0];
        for (Object object : getFromClipboard()) {
            // create a copy of the entity
            Entity newEntity = EcoreUtil.copy((Entity) object);
            Entity parentEntity = semanticProvider.fetchEntity(container);
            parentEntity.getChildEntities().add(newEntity);
            
            AddContext ac = new AddContext();
            ac.setLocation(container.getGraphicsAlgorithm().getWidth() / 2,
                    container.getGraphicsAlgorithm().getHeight() / 2);
            ac.setTargetContainer(container);
            addGraphicalRepresentation(ac, newEntity);
        }
    }
    
}
