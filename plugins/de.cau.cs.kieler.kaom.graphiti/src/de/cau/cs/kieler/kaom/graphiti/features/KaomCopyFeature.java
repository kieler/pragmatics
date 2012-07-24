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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;

/**
 * @author soh
 * @kieler.ignore (excluded from review process)
 */
public class KaomCopyFeature extends AbstractCopyFeature {

    /**
     *
     */
    private KaomCopyPasteUtil util;

    /**
     * Creates a new KaomCopyFeature.java.
     * 
     * @param fp
     *            the feature provider
     */
    public KaomCopyFeature(final IFeatureProvider fp) {
        super(fp);
        util = new KaomCopyPasteUtil();
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void copy(final ICopyContext context) {
        List<EObject> objects = util.getTopLevelElements(context);
        super.putToClipboard(objects.toArray());
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean canCopy(final ICopyContext context) {
        List<EObject> objects = util.getTopLevelElements(context);
        return objects.size() > 0;
    }
}
