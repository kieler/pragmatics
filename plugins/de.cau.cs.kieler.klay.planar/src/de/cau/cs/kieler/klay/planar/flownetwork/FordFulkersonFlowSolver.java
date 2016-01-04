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
package de.cau.cs.kieler.klay.planar.flownetwork;

import java.util.List;

import com.google.common.base.Predicate;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver.IMaximumFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.BFSPathFinder;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;

/**
 * Solve the maximum flow problem in flow networks using the Ford-Fulkerson algorithm.
 * 
 * @author ocl
 * @author pkl
 * @kieler.rating proposed yellow by msp
 */
public class FordFulkersonFlowSolver implements IMaximumFlowSolver {

    /**
     * {@inheritDoc}
     */
    public void calcFlow(final PGraph network, final IKielerProgressMonitor monitor) {
        monitor.begin("Ford-Fulkerson maximum flow", 1);

        // Add source and sink nodes
        PNode source = network.addNode();
        PNode sink = network.addNode();
        for (PNode node : network.getNodes()) {
            int s = node.getProperty(IFlowNetworkSolver.SUPPLY);
            PEdge arc = null;
            int cap = 0;
            if (s > 0) {
                arc = network.addEdge(source, node, true);
                for (PEdge edge : node.adjacentEdges()) {
                    cap += edge.getProperty(IFlowNetworkSolver.CAPACITY);
                }
            } else if (s < 0) {
                arc = network.addEdge(node, sink, true);
                for (PEdge edge : node.adjacentEdges()) {
                    cap += edge.getProperty(IFlowNetworkSolver.CAPACITY);
                }
            }
            if (arc != null) {
                arc.setProperty(IFlowNetworkSolver.CAPACITY, cap);
                arc.setProperty(IPathFinder.PATHCOST, 1);
                arc.setProperty(IFlowNetworkSolver.LOWER_BOUND, 1);
            }
        }

        // Construct to residual-network

        // Initialize path finder and path condition
        IPathFinder pathFinder = new BFSPathFinder();
       
        Predicate<Pair<PNode, PEdge>> cond = new Predicate<Pair<PNode, PEdge>>() {
            public boolean apply(final Pair<PNode, PEdge> object) {
                PNode node = object.getFirst();
                PEdge edge = object.getSecond();
                int cap = 0;
                if (edge.isDirected() && (node == edge.getTarget())) {
                    cap = edge.getProperty(CAPACITY) - edge.getProperty(FLOW);
                } else if (edge.isDirected() && (node == edge.getSource())) {
                    cap = edge.getProperty(FLOW);
                }
                edge.setProperty(RESIDUAL_CAPACITY, cap);
                return cap > 0;
            }
        };

        List<PEdge> path = pathFinder.findPath(source, sink, cond);
        while (path != null) {
            // Get minimal capacity along path
            int value = Integer.MAX_VALUE;
            for (PEdge edge : path) {
                int cap = edge.getProperty(RESIDUAL_CAPACITY);
                if (cap < value) {
                    value = cap;
                }
            }

            // Update flow along path and change the residual-network
            for (PEdge edge : path) {
                int flow = edge.getProperty(FLOW);
                edge.setProperty(FLOW, flow + value);
            }

            path = pathFinder.findPath(source, sink, cond);
        }

        // Remove source and sink nodes
        network.removeNode(source);
        network.removeNode(sink);
        monitor.done();
    }

}
