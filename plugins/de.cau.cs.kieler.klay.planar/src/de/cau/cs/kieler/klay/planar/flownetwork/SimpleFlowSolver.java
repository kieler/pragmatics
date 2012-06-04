/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.flownetwork;

import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.Util;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.DijkstraPathFinder;

/**
 * 
 * @author pkl
 */
public class SimpleFlowSolver extends AbstractAlgorithm implements IFlowNetworkSolver {

    /**
     * {@inheritDoc} Works only with the assumption that the lower bound of every edge is 1 and edge
     * cost = 1. No flow is already set or if set it isn't considered. The capacity of each edge is
     * dealt as infinite. Additionally a source and a target node are needed, which contains onyl
     * outgoing or incoming edges respectively.
     */
    public void findFlow(final PGraph network) {

        PNode source = null;
        PNode sink = null;

        for (PNode node : network.getNodes()) {
            if (!node.incomingEdges().iterator().hasNext()) {
                // filter source node, is so if node has no incoming edges.
                source = node;
            } else if (!node.outgoingEdges().iterator().hasNext()) {
                // filter sink node, is so if node has no outgoing edges.
                sink = node;
            }
        }

        // TODO Maybe check if source and sink are set or do it in a unit test
        // Additionally check if the source.edges.flowcount == target.edges.flowcount!
        // - only one source and target node are allowed to exist.

        // by default set flow of edges to 1
        for (PEdge edge : network.getEdges()) {
            edge.setProperty(IFlowNetworkSolver.FLOW, 1);
        }

        // perform a bfs to order after bfs starting with the source.
        List<PNode> bfsNodeList = Util.bfsNodes(network, source);
        for (PNode node : bfsNodeList) {

            Iterator<PEdge> incomingIterator = node.incomingEdges().iterator();
            Iterator<PEdge> outgoingIterator = node.outgoingEdges().iterator();

            // source or target node has not to check!
            if (!incomingIterator.hasNext() || !outgoingIterator.hasNext()) {
                continue;
            }

            // count incoming flow;
            int incomingFlow = countFlow(incomingIterator);
            // count outgoing flow;
            int outgoingFlow = countFlow(outgoingIterator);

            int additionalFlow = outgoingFlow - incomingFlow;
            if (additionalFlow > 0) {
                // calc shortest path to source.
                List<PEdge> reversePath = new DijkstraPathFinder().findReversePath(node, source);
                // add additional flow to all flow edges along that path
                for (PEdge pEdge : reversePath) {
                    pEdge.setProperty(IFlowNetworkSolver.FLOW,
                            pEdge.getProperty(IFlowNetworkSolver.FLOW) + additionalFlow);
                }

            } else if (additionalFlow < 0) {
                // calc shortest path to sink.
                List<PEdge> reversePath = new DijkstraPathFinder().findPath(node, sink);
                // add additional flow to all flow edges along that path
                for (PEdge pEdge : reversePath) {
                    pEdge.setProperty(IFlowNetworkSolver.FLOW,
                            pEdge.getProperty(IFlowNetworkSolver.FLOW) + additionalFlow);
                }

            } else {
                // meaning: outgoingFlow == incomingFlow, e.g. nothing to do!
                continue;
            }

        }

    }

    /**
     * Counts the flow of the edges of the iterator.
     * 
     * @param it
     *            , iterator over edges.
     * @return the counted flow
     */
    private int countFlow(final Iterator<PEdge> it) {
        int count = 0;
        while (it.hasNext()) {
            count += it.next().getProperty(IFlowNetworkSolver.FLOW);
        }
        return count;
    }

}
