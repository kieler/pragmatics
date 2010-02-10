/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.AbstractLayoutProvider;

/**
 * The OGDF layout provider.
 * 
 * @author msp
 * @author mri
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    /** The selected layouter. */
    private OgdfLayouter layoutAlgorithm;

    /**
     * {@inheritDoc}
     */
    public void initialize(final String parameter) {
        // let the factory create the selected layouter
        layoutAlgorithm = OgdfLayouterFactory.getInstance().createLayouter(
                parameter);
    }

    /**
     * {@inheritDoc}
     */
    public void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {

        progressMonitor.begin("OGDF layout", 1);

        if (layoutAlgorithm == null) {
            throw new KielerException(
                    "No valid OGDF layout algorithm was selected.");
        }

        // layout the graph
        layoutAlgorithm.doLayout(layoutNode, progressMonitor);

        progressMonitor.done();
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (layoutAlgorithm == null) {
            return null;
        }
        return layoutAlgorithm.getDefault(optionId);
    }
}
