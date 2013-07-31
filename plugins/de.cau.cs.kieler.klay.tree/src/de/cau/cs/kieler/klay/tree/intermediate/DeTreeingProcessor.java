/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.intermediate;

import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * This processor should run as a post-processor after the first phase. It should run through 
 * the list of edges that once destroyed the tree property and simply insert that edges directly after
 * layouting the graph.
 * 
 * @author sor
 * @author sgu
 * 
 */
public class DeTreeingProcessor implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        List<TEdge>  edges = tGraph.getProperty(Properties.REMOVABLE_EDGES);

        for (TEdge tEdge : edges) {
            tEdge.getSource().getOutgoingEdges().add(tEdge);
            tEdge.getTarget().getInComingEdges().add(tEdge);
        }

    }
}
