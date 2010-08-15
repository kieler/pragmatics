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

import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver.IMaximumFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Solve the maximum flow problem in flow networks using the Ford-Fulkerson algorithm.
 * 
 * @author ocl
 */
public class FordFulkersonFlowSolver extends AbstractAlgorithm implements IMaximumFlowSolver {

    /**
     * {@inheritDoc}
     */
    public void findFlow(final IGraph network) {

        // Add source and sink nodes
        INode source = network.addNode();
        INode sink = network.addNode();
        for (INode node : network.getNodes()) {
            int s = node.getProperty(IFlowNetworkSolver.SUPPLY);
            if (s > 0) {
                network.addEdge(source, node, true);
            } else if (s < 0) {
                network.addEdge(node, sink, true);
            }
        }

        // Initialize path finder and path condition
        IPathFinder pathFinder = new BFSPathFinder();
        ICondition<Pair<INode, IEdge>> cond = new ICondition<Pair<INode, IEdge>>() {
            public boolean evaluate(final Pair<INode, IEdge> object) {
                INode node = object.getFirst();
                IEdge edge = object.getSecond();
                int cap = 0;
                if (edge.isDirected() && (node == edge.getSource())) {
                    cap = edge.getProperty(CAPACITY) - edge.getProperty(FLOW);
                } else if (edge.isDirected() && (node == edge.getTarget())) {
                    cap = edge.getProperty(FLOW);
                }
                edge.setProperty(RESIDUALCAPACITY, cap);
                return cap > 0;
            }
        };

        List<IEdge> path = pathFinder.findPath(source, sink, cond);
        while (path != null) {
            // Get minimal capacity along path
            int value = Integer.MAX_VALUE;
            for (IEdge edge : path) {
                int cap = edge.getProperty(RESIDUALCAPACITY);
                if (cap < value) {
                    value = cap;
                }
            }

            // Update flow along path
            for (IEdge edge : path) {
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
