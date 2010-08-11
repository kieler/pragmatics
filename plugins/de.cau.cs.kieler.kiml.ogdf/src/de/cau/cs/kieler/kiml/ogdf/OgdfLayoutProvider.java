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
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;

/**
 * The OGDF layout provider, that is the entry class used by KIML to call
 * individual layout algorithms.
 * 
 * @author msp
 * @author mri
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    /** Definition of available layout algorithms. */
    public enum LayoutAlgorithm {
        /** Sugiyama's layout algorithm. */
        SUGIYAMA,
        /** The planarization layout algorithm. */
        UMLPLANARIZATION,
        /** The fmmm layout algorithm. */
        FMMM,
        /** The fmmm layout algorithm with low level options. */
        FMMM_DETAIL,
        /** The Davidson-Harel layout algorithm. */
        DAVIDSON_HAREL,
        /** The Fruchterman-Reingold algorithm. */
        FRUCHTERMAN_REINGOLD,
        /** The circular layout algorithm. */
        CIRCULAR,
        /** The tree layout algorithm. */
        TREE,
        /** The radial tree layout algorithm. */
        RADIAL_TREE,
        /** The upward-planarization layout algorithm. */
        UPWARD_PLANARIZATION
    }

    /** the selected layouter. */
    private OgdfLayouter layoutAlgorithm;

    /**
     * {@inheritDoc}
     * 
     * @throws KielerException
     */
    @Override
    public void initialize(final String parameter) throws KielerException {
        if (parameter == null) {
            throw new KielerException("Could not initialize OGDF layouter.");
        }
        try {
            switch (LayoutAlgorithm.valueOf(parameter)) {
            case SUGIYAMA:
                layoutAlgorithm = new SugiyamaLayouter();
                break;
            case UMLPLANARIZATION:
                layoutAlgorithm = new PlanarizationLayouter();
                break;
            case FMMM:
                layoutAlgorithm = new FMMMLayouter();
                break;
            case FMMM_DETAIL:
                layoutAlgorithm = new FMMMDetailLayouter();
                break;
            case DAVIDSON_HAREL:
                layoutAlgorithm = new DavidsonHarelLayouter();
                break;
            case FRUCHTERMAN_REINGOLD:
                layoutAlgorithm = new SpringEmbedderFRLayouter();
                break;
            case CIRCULAR:
                layoutAlgorithm = new CircularLayouter();
                break;
            case TREE:
                layoutAlgorithm = new TreeLayouter();
                break;
            case RADIAL_TREE:
                layoutAlgorithm = new RadialTreeLayouter();
                break;
            case UPWARD_PLANARIZATION:
                layoutAlgorithm = new UpwardPlanarizationLayouter();
                break;
            default:
                layoutAlgorithm = null;
            }
        } catch (IllegalArgumentException exception) {
            throw new KielerException("Could not initialize OGDF layouter.",
                    exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void doLayout(final KNode layoutNode,
            final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (layoutAlgorithm == null) {
            throw new KielerException(
                    "The OGDF layout algorithm is not configured correctly."
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
