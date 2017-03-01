/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
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
        public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
                final IElkProgressMonitor progressMonitor) {
            
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
