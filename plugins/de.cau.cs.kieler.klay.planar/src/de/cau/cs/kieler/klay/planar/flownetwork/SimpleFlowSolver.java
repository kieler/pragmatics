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
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.DijkstraPathFinder;
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * This flow solver iterates over all edges and updates the flow of each node according to the sum
 * of incoming and outgoing edge flows. If the sum of the incoming edge flow is higher than the sum
 * of the outgoing edge flow of a node, the shortest edge path to the sink node is increased with
 * the flow gap in order to ensure the mass balancing. This ensures that each sum of incoming flow
 * == sum of outgoing flow of each node. if the sum outgoing flow is higher than the sum of the
 * incoming, the shortest edge path to the source node is increased, respectively. Works only with
 * the assumption that the lower bound of every edge is 1 and edge cost = 1. No flow is already set
 * or if set it isn't considered. The capacity of each edge is dealt as infinite. Additionally
 * source and target node are needed, which contains only outgoing or incoming edges respectively.
 * 
 * @author pkl
 * @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public class SimpleFlowSolver extends AbstractAlgorithm implements IFlowNetworkSolver {

    /**
     * {@inheritDoc}
     */
    public void calcFlow(final PGraph network) {

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

        // by default set flow of edges to 1
        for (PEdge edge : network.getEdges()) {
            edge.setProperty(IFlowNetworkSolver.FLOW, 1);
        }

        // Perform a bfs. Each found element is stored in the bfsNodeList, it is started with
        // the source node.
        List<PNode> bfsNodeList = PUtil.bfsNodes(network, source);
        for (PNode node : bfsNodeList) {

            if (node == source || node == sink) {
                continue;
            }

            // count incoming flow;
            int incomingFlow = countFlow(node.incomingEdges().iterator());
            // count outgoing flow;
            int outgoingFlow = countFlow(node.outgoingEdges().iterator());

            int gap = outgoingFlow - incomingFlow;
            if (gap > 0) {
                // calc shortest path to source.
                List<PEdge> reversePath = new DijkstraPathFinder().findReversePath(node, source);
                // add additional flow to all flow edges along that path
                for (PEdge pEdge : reversePath) {
                    pEdge.setProperty(IFlowNetworkSolver.FLOW,
                            pEdge.getProperty(IFlowNetworkSolver.FLOW) + gap);
                }

            } else if (gap < 0) {
                // calc shortest path to sink.
                List<PEdge> path = new DijkstraPathFinder().findPath(node, sink);
                // add additional flow to all flow edges along that path
                for (PEdge pEdge : path) {
                    // gap is negative so we have to subtract it to add it.
                    pEdge.setProperty(IFlowNetworkSolver.FLOW,
                            pEdge.getProperty(IFlowNetworkSolver.FLOW) - gap);
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
