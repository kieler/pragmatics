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

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Pair;

import com.google.common.base.Predicate;

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
 * http://coral.ie.lehigh.edu/~ted/files/ie411/lectures/Lecture14.pdf.
 * 
 * @author ocl
 * @author pkl
 * @kieler.rating yellow 2012-10-09 review KI-16 by ckru, cds
 */
public class SuccessiveShortestPathFlowSolver implements
        IMinimumCostFlowSolver {

    /**
     * {@inheritDoc}
     */
    public void calcFlow(final PGraph network, final IElkProgressMonitor monitor) {
        monitor.begin("Successive shortest path minimum cost flow", 1);
        
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

        // Initialize node potentials using Bellman-Ford-Algorithm, secondly make negative cycles
        // in the network positive. Potential are used to ensure non negative path cycles and
        // to calculate the reduced costs.
        IPathFinder pathFinder = new BellmanFordPathFinder();
        pathFinder.findPath(source, sink);
        int[] potentials = new int[network.getNodeCount()];
        for (PNode node : network.getNodes()) {
            potentials[node.id] = node.getProperty(IPathFinder.DISTANCE);
        }
        // Set the reduced costs.
        for (PEdge edge : network.getEdges()) {
            int cost = edge.getProperty(IPathFinder.PATHCOST);
            cost += potentials[edge.getSource().id];
            cost -= potentials[edge.getTarget().id];
            edge.setProperty(IPathFinder.PATHCOST, cost);
        }

        // Initialize more efficient Dijkstra path finder.
        // Condition describes residual network.
        pathFinder = new DijkstraPathFinder();

        Predicate<Pair<PNode, PEdge>> cond = new Predicate<Pair<PNode, PEdge>>() {
            public boolean apply(final Pair<PNode, PEdge> object) {
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
                cost -= potentials[edge.getSource().id];
                cost += potentials[edge.getTarget().id];
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

            path = pathFinder.findPath(source, sink, cond);
        }
        // Remove source and sink nodes
        network.removeNode(source);
        network.removeNode(sink);
        
        monitor.done();
    }
    
}
