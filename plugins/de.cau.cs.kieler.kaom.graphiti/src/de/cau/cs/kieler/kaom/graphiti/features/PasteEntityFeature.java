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
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

import de.cau.cs.kieler.kaom.Entity;

/**
 * 
 * @author atr
 * Class used to copy the Entity in the clip board to the graphical editor
 */
public class PasteEntityFeature extends AbstractPasteFeature {

    /**
     * 
     * @param fp
     *  Constructor
     */
    public PasteEntityFeature(final IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canPaste(final IPasteContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        if (pes.length != 1 || !(pes[0] instanceof Diagram)) {
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
     * 
     * {@inheritDoc}
     */
    public void paste(final IPasteContext context) {

        PictogramElement[] pes = context.getPictogramElements();
        Diagram diagram = (Diagram) pes[0];
        Object[] objects = getFromClipboard();

        for (Object object : objects) {
            AddContext ac = new AddContext();
            ac.setLocation(0, 0); // for simplicity paste at (0, 0)
            ac.setTargetContainer(diagram);
            addGraphicalRepresentation(ac, object);
        }
    }
}


