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
package de.cau.cs.kieler.klay.planar.pathfinding;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder.IShortestPathFinder;

/**
 * Use the Bellman-Ford Algorithm to find the shortest path between two nodes in any graph. Contrary
 * to Dijkstra's Algorithm, the Bellman-Ford Algorithm allows negative edge costs and detects
 * negative cycles.
 * 
 * @author ocl
 */
public class BellmanFordPathFinder extends AbstractPathFinder implements IShortestPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<PEdge> findPath(final PNode source, final PNode target,
            final ICondition<Pair<PNode, PEdge>> condition) {

        // Initialize array
        int size = source.getParent().getNodeCount();
        PEdge[] edges = new PEdge[size];

        source.setProperty(DISTANCE, 0);

        // Relax edges
        for (int i = 1; i < size; i++) {
            for (PEdge edge : source.getParent().getEdges()) {
                PNode src = edge.getSource();
                PNode dst = edge.getTarget();
                int cost = edge.getProperty(PATHCOST);
                int dist = src.getProperty(DISTANCE);
                if ((dist != Integer.MAX_VALUE) && (dist + cost < dst.getProperty(DISTANCE))) {
                    dst.setProperty(DISTANCE, cost + dist);
                    edges[dst.id] = edge;
                }
            }
        }

        // Detect negative cycles
        for (PEdge edge : source.getParent().getEdges()) {
            int cost = edge.getProperty(PATHCOST);
            cost += edge.getSource().getProperty(DISTANCE);
            if (cost < edge.getTarget().getProperty(DISTANCE)) {
                throw new IllegalArgumentException("The graph contains a negative cycle.");
            }
        }

        // Compute shortest path
        LinkedList<PEdge> path = new LinkedList<PEdge>();
        PNode pathNode = target;
        PEdge pathEdge = edges[pathNode.id];
        while (pathEdge != null) {
            path.addFirst(pathEdge);
            pathNode = pathNode.getAdjacentNode(pathEdge);
            pathEdge = edges[pathNode.id];
        }
        if (pathNode == source) {
            return path;
        } else {
            return null;
        }
    }

}
