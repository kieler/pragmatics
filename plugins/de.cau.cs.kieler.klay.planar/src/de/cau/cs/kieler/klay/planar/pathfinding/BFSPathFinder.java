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
import java.util.Queue;

import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PNode;

/**
 * Use a Breadth First Search to find a path between two nodes in any graph.
 * 
 * @author ocl
 */
public class BFSPathFinder extends AbstractPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<PEdge> findPath(final PNode source, final PNode target,
            final ICondition<Pair<PNode, PEdge>> condition) {

        // Initialize arrays
        int size = source.getParent().getNodeCount();
        boolean[] visited = new boolean[size];
        PEdge[] edges = new PEdge[size];

        Queue<PNode> queue = new LinkedList<PNode>();
        queue.add(source);

        while (!queue.isEmpty()) {
            PNode current = queue.remove();

            // Stop if the target is reached
            if (current == target) {
                LinkedList<PEdge> path = new LinkedList<PEdge>();
                PNode pathNode = current;
                PEdge pathEdge = edges[pathNode.id];
                while (pathEdge != null) {
                    path.addFirst(pathEdge);
                    pathNode = pathNode.getAdjacentNode(pathEdge);
                    pathEdge = edges[pathNode.id];
                }
                return path;
            }

            // Add neighbors to queue
            for (PEdge edge : current.adjacentEdges()) {
                PNode neighbor = current.getAdjacentNode(edge);

                // Ignore visited nodes
                if (visited[neighbor.id]) {
                    continue;
                }

                // Check edge condition
                if (!condition.evaluate(new Pair<PNode, PEdge>(neighbor, edge))) {
                    continue;
                }

                queue.add(neighbor);
                edges[neighbor.id] = edge;
            }
            visited[current.id] = true;
        }

        // Finished without reaching the target
        return null;
    }

}
