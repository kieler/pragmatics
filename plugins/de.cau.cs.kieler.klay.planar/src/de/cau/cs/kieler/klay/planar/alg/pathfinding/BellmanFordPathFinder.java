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
package de.cau.cs.kieler.klay.planar.alg.pathfinding;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.IPathFinder.IShortestPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

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
    public List<IEdge> findPath(final INode source, final INode target,
            final ICondition<Pair<INode, IEdge>> condition) {

        // Initialize array
        int size = source.getParent().getNodeCount();
        IEdge[] edges = new IEdge[size];

        source.setProperty(DISTANCE, 0);

        // Relax edges
        for (int i = 1; i < size; i++) {
            for (IEdge edge : source.getParent().getEdges()) {
                INode neighbor = edge.getTarget();
                int cost = edge.getProperty(PATHCOST);
                cost += edge.getProperty(DISTANCE);
                if (cost < neighbor.getProperty(DISTANCE)) {
                    neighbor.setProperty(DISTANCE, cost);
                    edges[neighbor.getID()] = edge;
                }
            }
        }

        // Detect negative cycles
        for (IEdge edge : source.getParent().getEdges()) {
            int cost = edge.getProperty(PATHCOST);
            cost += edge.getSource().getProperty(DISTANCE);
            if (cost < edge.getTarget().getProperty(DISTANCE)) {
                throw new IllegalArgumentException("The graph contains a negative cycle.");
            }
        }

        // Compute shortest path
        LinkedList<IEdge> path = new LinkedList<IEdge>();
        INode pathNode = target;
        IEdge pathEdge = edges[pathNode.getID()];
        while (pathEdge != null) {
            path.addFirst(pathEdge);
            pathNode = pathNode.getAdjacentNode(pathEdge);
            pathEdge = edges[pathNode.getID()];
        }
        if (pathNode == source) {
            return path;
        } else {
            return null;
        }
    }

}
