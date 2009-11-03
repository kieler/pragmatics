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
package de.cau.cs.kieler.klodd.orthogonal.impl.ec;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.orthogonal.modules.IPlanarizer;
import de.cau.cs.kieler.klodd.orthogonal.structures.TSMNode;

/**
 * Planarizer implementation that handles embedding constraints by inserting all
 * edges with an EC edge inserter.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class EdgeInsertionECPlanarizer extends AbstractAlgorithm implements IPlanarizer {

    /**
     * {@inheritDoc}
     */
    public void planarize(final KSlimGraph graph) {
        getMonitor().begin("Edge insertion planarization", graph.edges.size());
        // remove all edges from the incidence lists
        for (KSlimNode node : graph.nodes) {
            node.incidence.clear();
        }

        // insert the stored edges, one by one, and preserve planarity
        ECEdgeInserter edgeInserter = new ECEdgeInserter();
        edgeInserter.setGraph(graph);
        for (KSlimEdge edge : graph.edges) {
            EmbeddingConstraint sourceConstraint = ((TSMNode) edge.source).embeddingConstraint;
            EmbeddingConstraint targetConstraint = ((TSMNode) edge.target).embeddingConstraint;
            edgeInserter.setProgressMonitor(getMonitor().subTask(1));
            edgeInserter.insertEdge(edge, sourceConstraint, targetConstraint);
        }

        getMonitor().done();
    }

}
