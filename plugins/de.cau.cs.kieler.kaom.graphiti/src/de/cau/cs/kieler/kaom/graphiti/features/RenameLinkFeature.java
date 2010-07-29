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

import org.eclipse.graphiti.examples.common.SampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.kaom.Link;

/**
 * @author atr
 * Class used to rename the link.
 */
public class RenameLinkFeature extends AbstractCustomFeature {

    /**
     * @param fp
     *            Constructor.
     */
    public RenameLinkFeature(final IFeatureProvider fp) {
        super(fp);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Rename Link";
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Change the name of the Link";
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute(final ICustomContext context) {
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Link) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void execute(final ICustomContext context) {
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Link) {
                Link link = (Link) bo;
                String currentName = link.getName();
                String newName = SampleUtil.askString(getName(), getDescription(), currentName);

                if (newName != null) {
                    link.setName(newName);
                }

            }

        }

    }

}
