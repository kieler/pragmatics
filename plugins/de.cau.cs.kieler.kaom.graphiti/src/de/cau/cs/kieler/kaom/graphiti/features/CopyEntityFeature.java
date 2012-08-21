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
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;

import de.cau.cs.kieler.kaom.Entity;

/**
 * Feature for copying an entity to the clip board.
 * 
 * @author atr
 * @kieler.ignore (excluded from review process)
 */
public class CopyEntityFeature extends AbstractCopyFeature {

    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public CopyEntityFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * {@inheritDoc}
     */
    public boolean canCopy(final ICopyContext context) {
        final PictogramElement[] pes = context.getPictogramElements();
        if (pes == null || pes.length == 0) {
            return false;
        }
        // return true if all selected elements are entities
        for (PictogramElement pe : pes) {
            final Object bo = getBusinessObjectForPictogramElement(pe);
            if (!(bo instanceof Entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void copy(final ICopyContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        Object[] bos = new Object[pes.length];
        for (int i = 0; i < pes.length; i++) {
            PictogramElement pe = pes[i];
            bos[i] = getBusinessObjectForPictogramElement(pe);
        }

        putToClipboard(bos);
    }

}
