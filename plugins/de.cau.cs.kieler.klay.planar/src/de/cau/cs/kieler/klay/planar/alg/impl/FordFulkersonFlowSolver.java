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
import de.cau.cs.kieler.klay.planar.alg.IPathFinder;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver.IMaximumFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.util.IFunction;

public class FordFulkersonFlowSolver extends AbstractAlgorithm implements IMaximumFlowSolver {

    // ======================== Algorithm ==========================================================

    public IFunction<IEdge, Integer> findFlow(final IGraph network,
            final IFunction<INode, Integer> supply) {

        // Initialize arrays
        int size = network.getEdgeCount();
        final int[] flow = new int[size];
        final int[] capacity = new int[size]; // TODO get capacities

        // Initialize path finder and path condition
        IPathFinder pathFinder = new BFSPathFinder();
        ICondition<Pair<INode, IEdge>> cond = new ICondition<Pair<INode, IEdge>>() {
            public boolean evaluate(Pair<INode, IEdge> object) {
                INode node = object.getFirst();
                IEdge edge = object.getSecond();
                int cap = capacity[edge.getID()] - flow[edge.getID()];
                if (edge.isDirected() && (node == edge.getSource())) {
                    return cap > 0;
                } else if (edge.isDirected() && (node == edge.getTarget())) {
                    return cap < 0;
                } else {
                    return false;
                }
            }
        };

        // TODO create source and sink nodes
        List<IEdge> path = pathFinder.findPath(null, null, cond);
        while (!path.isEmpty()) {
            // Get minimal capacity along path TODO
            int value = Integer.MAX_VALUE;
            for (IEdge edge : path) {
                if (capacity[edge.getID()] < value) {
                    value = capacity[edge.getID()];
                }
            }

            // Update flow along path
            for (IEdge edge : path) {
                flow[edge.getID()] += value;
            }

            path = pathFinder.findPath(null, null, cond);
        }
        // TODO remove source and sink nodes

        return new IFunction<IEdge, Integer>() {
            public Integer evaluate(final IEdge element) {
                return flow[element.getID()];
            }
        };
    }
}
