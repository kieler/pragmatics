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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder.IShortestPathFinder;

/**
 * Use Dijkstra's Algorithm to find the shortest path between two nodes in any graph.
 * 
 * @author ocl
 */
public class DijkstraPathFinder extends AbstractPathFinder implements IShortestPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<PEdge> findPath(final PNode source, final PNode target,
            final ICondition<Pair<PNode, PEdge>> condition) {

        // Initialize array
        int size = source.getParent().getNodeCount();
        PEdge[] edges = new PEdge[size];

        // Initialize set of nodes
        Set<PNode> nodes = new HashSet<PNode>(size * 2);
        for (PNode n : source.getParent().getNodes()) {
            n.setProperty(DISTANCE, Integer.MAX_VALUE);
            nodes.add(n);
        }
        source.setProperty(DISTANCE, 0);

        // Comparator to find node of smallest distance value
        Comparator<PNode> comp = new Comparator<PNode>() {
            public int compare(final PNode arg0, final PNode arg1) {
                return arg0.getProperty(DISTANCE) - arg1.getProperty(DISTANCE);
            }
        };

        // Main loop
        while (!nodes.isEmpty()) {
            PNode current = Collections.min(nodes, comp);

            // Remaining nodes are unreachable
            if (current.getProperty(DISTANCE) == Integer.MAX_VALUE) {
                break;
            }

            // Target node found, Compute shortest path
            if (current == target) {
                LinkedList<PEdge> path = new LinkedList<PEdge>();
                PNode pathNode = target;
                PEdge pathEdge = edges[pathNode.id];
                while (pathEdge != null) {
                    path.addFirst(pathEdge);
                    pathNode = pathNode.getAdjacentNode(pathEdge);
                    pathEdge = edges[pathNode.id];
                }
                return path;
            }

            nodes.remove(current);

            // Traverse all neighbors
            for (PEdge edge : current.adjacentEdges()) {
                PNode neighbor = current.getAdjacentNode(edge);

                // Skip already visited nodes
                if (!nodes.contains(neighbor)) {
                    continue;
                }

                // Check edge condition
                if (!condition.evaluate(new Pair<PNode, PEdge>(neighbor, edge))) {
                    continue;
                }

                // Get edge cost property
                int cost = edge.getProperty(PATHCOST);
                cost += current.getProperty(DISTANCE);

                if (cost < neighbor.getProperty(DISTANCE)) {
                    neighbor.setProperty(DISTANCE, cost);
                    edges[neighbor.id] = edge;
                }
            }
        }

        // Target node not reached
        return null;
    }

}
