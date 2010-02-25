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
 * The OGDF layout provider, that is the entry class used by KIML to call individual layout
 * algorithms.
 * 
 * @author msp
 * @author mri
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    /** Definition of available layout algorithms. */
    public enum LayoutAlgorithm {
        /** Sugiyama's layout algorithm.  */
        SUGIYAMA,
        /** The planarization layout algorithm. */
        UMLPLANARIZATION
    }
    
    /** the selected layouter. */
    private OgdfLayouter layoutAlgorithm;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final String parameter) {
        switch (LayoutAlgorithm.valueOf(parameter)) {
        case SUGIYAMA:
            layoutAlgorithm = new SugiyamaLayouter();
            break;
        case UMLPLANARIZATION:
            layoutAlgorithm = new PlanarizationLayouter();
            break;
        default:
            layoutAlgorithm = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (layoutAlgorithm == null) {
            throw new KielerException("The OGDF layout algorithm is not configured correctly."
                    + " Please check the parameter in the extension point");
        }

        // layout the graph with the selected algorithm
        layoutAlgorithm.doLayout(layoutNode, progressMonitor);
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (layoutAlgorithm == null) {
            return null;
        } else {
            return layoutAlgorithm.getDefault(optionId);
        }
    }
}
