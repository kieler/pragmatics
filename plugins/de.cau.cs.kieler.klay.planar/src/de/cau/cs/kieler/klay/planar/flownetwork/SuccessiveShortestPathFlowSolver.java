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
package de.cau.cs.kieler.klay.planar.flownetwork;

import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver.IMinimumCostFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.BellmanFordPathFinder;
import de.cau.cs.kieler.klay.planar.pathfinding.DijkstraPathFinder;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;

/**
 * Solve the minimum cost flow problem on flow networks using the successive shortest path
 * algorithm. A good reference for constructing the Successive Shortest Path algorithm is
 * http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=minimumCostFlow2
 * 
 * @author ocl
 * @author pkl
 */
public class SuccessiveShortestPathFlowSolver extends AbstractAlgorithm implements
        IMinimumCostFlowSolver {
    // TODO Handle multi edges in network
    // TODO flow can move in both directions in residual network
    // TODO different path costs for forward and backward edges in residual network

    /**
     * {@inheritDoc}
     */
    public void findFlow(final PGraph network) {

        // Add source and sink nodes
        PNode source = network.addNode();
        PNode sink = network.addNode();
        // Add some edges as follows
        for (PNode node : network.getNodes()) {
            int s = node.getProperty(SUPPLY);
            // for each node i with bi > 0, add a source arc (s,i) with
            // capacity bi and cost 0.
            if (s > 0) {
                PEdge edge = network.addEdge(source, node, true);
                edge.setProperty(CAPACITY, s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
                // for each node i with bi < 0, add a sink arc (i,t) with
                // capacity -bi and cost 0.
            } else if (s < 0) {
                PEdge edge = network.addEdge(node, sink, true);
                edge.setProperty(CAPACITY, -s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
            }
        }

        // Initialize node potentials using Bellman-Ford-Algorithm
        IPathFinder pathFinder = new BellmanFordPathFinder();
        pathFinder.findPath(source, sink);
        int[] potentials = new int[network.getNodeCount()];
        for (PNode node : network.getNodes()) {
            potentials[node.id] = 0 /*node.getProperty(IPathFinder.DISTANCE)*/;
        }
        for (PEdge edge : network.getEdges()) {
            int cost = edge.getProperty(IPathFinder.PATHCOST);
            cost += potentials[edge.getSource().id];
            cost -= potentials[edge.getTarget().id];
            edge.setProperty(IPathFinder.PATHCOST, cost);
        }

        // Initialize path finder
        // Condition describes residual network
        pathFinder = new DijkstraPathFinder();
        ICondition<Pair<PNode, PEdge>> cond = new ICondition<Pair<PNode, PEdge>>() {
            public boolean evaluate(final Pair<PNode, PEdge> object) {
                PNode node = object.getFirst();
                PEdge edge = object.getSecond();
                int cap = 0;
                if (edge.isDirected() && (node == edge.getTarget())) {
                    cap = edge.getProperty(CAPACITY) - edge.getProperty(FLOW);
                    edge.setProperty(RESIDUAL_CAPACITY, cap);
                    return cap > 0;
                }
                return false;
            }
        };

        List<PEdge> path = pathFinder.findPath(source, sink, cond);
        while (path != null) {

            // Update path costs based on potentials
            for (PEdge edge : network.getEdges()) {
                int cost = edge.getProperty(IPathFinder.PATHCOST);
                cost += potentials[edge.getSource().id];
                cost -= potentials[edge.getTarget().id];
                edge.setProperty(IPathFinder.PATHCOST, cost);
            }

            // Get minimal capacity along path
            int value = Integer.MAX_VALUE;
            for (PEdge edge : path) {
                int cap = edge.getProperty(RESIDUAL_CAPACITY);
                if (cap < value) {
                    value = cap;
                }
            }

            // Update flow along path
            for (PEdge edge : path) {
                int flow = edge.getProperty(FLOW);
                edge.setProperty(FLOW, flow + value);
            }

            pathFinder.reset();
            path = pathFinder.findPath(source, sink, cond);
        }
        // Remove source and sink nodes
        network.removeNode(source);
        network.removeNode(sink);
    }

}
