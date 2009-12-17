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
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.List;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;

/**
 * Interface for algorithms for removal of cycles in a layout graph.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public interface ICycleRemover extends IAlgorithm {

    /** rank value for edges that were reversed. */
    int REVERSED = 1;

    /**
     * Remove cycles in a given graph. Loops on a single node are ignored and
     * have to be processed properly in later steps. Each edge is assigned a
     * rank with value <code>REVERSED</code> if and only if the edge was
     * reversed for cycle removal.
     * 
     * @param graph graph to be processed
     */
    void removeCycles(KSlimGraph graph);

    /**
     * Restore the original graph after cycles have been removed.
     */
    void restoreGraph();

    /**
     * Returns the list of edges that were reversed for cycle removal.
     * 
     * @return list of reversed edges
     */
    List<KSlimEdge> getReversedEdges();

}
