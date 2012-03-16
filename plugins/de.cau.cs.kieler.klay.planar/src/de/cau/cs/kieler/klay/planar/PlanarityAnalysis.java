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

import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;
import de.cau.cs.kieler.klay.planar.planarity.BoyerMyrvoldPlanarityTester;
import de.cau.cs.kieler.klay.planar.planarity.IPlanarityTester;

/**
 * A graph analysis that uses planarity testing algorithms to check whether a graph is planar.
 * If the graph is planar, the result is 0. Otherwise it is a hint on the number of edges that
 * need to be removed in order to obtain a planar subgraph.
 * 
 * @author ocl
 */
public class PlanarityAnalysis implements IAnalysis {

    // ======================== Attributes =========================================================

    /** Graph factory. */
    private IGraphFactory factory = new PGraphFactory();

    /** Algorithm for planar testing. */
    private IPlanarityTester tester = new BoyerMyrvoldPlanarityTester();

    // ======================== Analysis ===========================================================

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Planarity testing", 1);

        // KGraph -> PGraph conversion
        IGraph graph = factory.createGraphFromKGraph(parentNode);

        // Planarity Testing
        tester.reset();
        List<IEdge> edges = tester.planarSubgraph(graph);

        progressMonitor.done();
        return edges.size();
    }
}
