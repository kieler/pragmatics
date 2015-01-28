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
package de.cau.cs.kieler.klay.planar;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.p1planar.BoyerMyrvoldPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A graph analysis that uses planarity testing algorithms to check whether a graph is planar. If
 * the graph is planar, the result is 0. Otherwise it is a hint on the number of edges that need to
 * be removed in order to obtain a planar subgraph.
 * 
 * @author ocl
 * @kieler.rating proposed yellow by pkl
 */
public class PlanarityAnalysis implements IAnalysis {

    // ======================== Analysis ====================================================

    /**
     * {@inheritDoc}
     */
        public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
                final IKielerProgressMonitor progressMonitor) {
            
        progressMonitor.begin("Planarity testing", 1);

        // KGraph -> PGraph conversion
        PGraph graph = PGraphFactory.createGraphFromKGraph(parentNode);

        ILayoutPhase tester = new BoyerMyrvoldPlanarSubgraphBuilder();

        // Planarity Testing
        tester.process(graph, progressMonitor.subTask(1));
        progressMonitor.done();
        return graph.getProperty(Properties.INSERTABLE_EDGES).size();
    }
}
