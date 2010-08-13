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

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Use a Breadth First Search to find a path between two nodes in any graph.
 * 
 * @author ocl
 */
public class BFSPathFinder extends AbstractAlgorithm implements IPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<IEdge> findPath(final INode source, final INode target) {
        return this.findPath(source, target, new ICondition<Pair<INode, IEdge>>() {
            public boolean evaluate(final Pair<INode, IEdge> object) {
                return true;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public List<IEdge> findPath(final INode source, final INode target,
            final ICondition<Pair<INode, IEdge>> condition) {

        // Initialize arrays
        int size = source.getParent().getNodeCount();
        boolean[] visited = new boolean[size];
        IEdge[] edges = new IEdge[size];

        Queue<INode> queue = new LinkedList<INode>();
        queue.add(source);

        while (!queue.isEmpty()) {
            INode current = queue.remove();

            // Stop if the target is reached
            if (current == target) {
                LinkedList<IEdge> path = new LinkedList<IEdge>();
                INode pathNode = current;
                IEdge pathEdge = edges[pathNode.getID()];
                while (pathEdge != null) {
                    path.addFirst(pathEdge);
                    pathNode = pathNode.getAdjacentNode(pathEdge);
                    pathEdge = edges[pathNode.getID()];
                }
                return path;
            }

            // Add neighbors to queue
            for (IEdge edge : current.adjacentEdges()) {
                INode neighbor = current.getAdjacentNode(edge);

                // Ignore visited nodes
                if (visited[neighbor.getID()]) {
                    continue;
                }

                // Check edge condition
                if (!condition.evaluate(new Pair<INode, IEdge>(neighbor, edge))) {
                    continue;
                }

                queue.add(neighbor);
                edges[neighbor.getID()] = edge;
            }
            visited[current.getID()] = true;
        }

        // Finished without reaching the target
        return null;
    }

}
