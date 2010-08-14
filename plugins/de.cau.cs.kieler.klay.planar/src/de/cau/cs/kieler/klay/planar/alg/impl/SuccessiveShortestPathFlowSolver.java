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
package de.cau.cs.kieler.klay.planar.alg.impl;

import java.util.Arrays;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver.IMinimumCostFlowSolver;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder.IShortestPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Solve the minimum cost flow problem on flow networks using the successive shortest path
 * algorithm.
 * 
 * @author ocl
 */
public class SuccessiveShortestPathFlowSolver extends AbstractAlgorithm implements
        IMinimumCostFlowSolver {
    // TODO residual network: directed/undirected, multiedges?

    /**
     * {@inheritDoc}
     */
    public void findFlow(final IGraph network) {

        // Add source and sink nodes
        INode source = network.addNode();
        INode sink = network.addNode();
        for (INode node : network.getNodes()) {
            int s = node.getProperty(SUPPLY);
            if (s > 0) {
                IEdge edge = network.addEdge(source, node, true);
                edge.setProperty(CAPACITY, s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
            } else if (s < 0) {
                IEdge edge = network.addEdge(node, sink, true);
                edge.setProperty(CAPACITY, -s);
                edge.setProperty(IPathFinder.PATHCOST, 0);
            }
        }

        // Initialize node potentials using Bellman-Ford-Algorithm
        int size = network.getNodeCount();
        final int[] potential = new int[size];
        Arrays.fill(potential, -1);
        potential[source.getID()] = 0;

        for (int i = 1; i < size; i++) {
            for (IEdge edge : source.getParent().getEdges()) {
                int iNeighbor = edge.getTarget().getID();
                Integer cost = edge.getProperty(IPathFinder.PATHCOST);
                cost += potential[edge.getSource().getID()];
                if (cost < potential[iNeighbor]) {
                    potential[iNeighbor] = cost;
                }
            }
        }

        // TODO reduce cost

        // Initialize path finder and path condition
        IShortestPathFinder pathFinder = new DijkstraPathFinder();
        ICondition<Pair<INode, IEdge>> cond = new ICondition<Pair<INode, IEdge>>() {
            public boolean evaluate(final Pair<INode, IEdge> object) {
                INode node = object.getFirst();
                IEdge edge = object.getSecond();
                int cap = edge.getProperty(CAPACITY);
                if (edge.isDirected() && (node == edge.getSource())) {
                    return cap > 0;
                } else if (edge.isDirected() && (node == edge.getTarget())) {
                    return cap < 0;
                } else {
                    return false;
                }
            }
        };

        List<IEdge> path = pathFinder.findPath(source, sink, cond);
        while (path != null) {
            // TODO reduce cost

            // Get minimal capacity along path
            int value = Integer.MAX_VALUE;
            for (IEdge edge : path) {
                int cap = edge.getProperty(CAPACITY);
                if (cap < value) {
                    value = cap;
                }
            }

            // Update flow along path
            for (IEdge edge : path) {
                int flow = edge.getProperty(FLOW);
                edge.setProperty(FLOW, flow + value);
            }

            path = pathFinder.findPath(source, sink, cond);
        }

        // Remove source and sink nodes
        network.removeNode(source);
        network.removeNode(sink);
    }

}
