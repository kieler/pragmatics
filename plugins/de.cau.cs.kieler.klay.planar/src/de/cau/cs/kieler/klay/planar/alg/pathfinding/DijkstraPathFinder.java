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

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.IPathFinder.IShortestPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Use Dijkstra's Algorithm to find the shortest path between two nodes in any graph.
 * 
 * @author ocl
 */
public class DijkstraPathFinder extends AbstractAlgorithm implements IShortestPathFinder {

    /**
     * {@inheritDoc}
     */
    public List<IEdge> findPath(final INode source, final INode target) {
        return this.findPath(source, target, new ICondition<Pair<INode, IEdge>>() {
            public boolean evaluate(final Pair<INode, IEdge> object) {
                INode node = object.getFirst();
                IEdge edge = object.getSecond();
                return !(edge.isDirected() && (node == edge.getTarget()));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public List<IEdge> findPath(final INode source, final INode target,
            final ICondition<Pair<INode, IEdge>> condition) {

        // Initialize array
        int size = source.getParent().getNodeCount();
        IEdge[] edges = new IEdge[size];

        source.setProperty(DISTANCE, 0);

        // Initialize set of nodes
        Set<INode> nodes = new HashSet<INode>(size * 2);
        for (INode n : source.getParent().getNodes()) {
            nodes.add(n);
        }

        // Comparator to find node of smallest distance value
        Comparator<INode> comp = new Comparator<INode>() {
            public int compare(final INode arg0, final INode arg1) {
                return arg0.getProperty(DISTANCE) - arg1.getProperty(DISTANCE);
            }
        };

        // Main loop
        while (!nodes.isEmpty()) {
            INode current = Collections.min(nodes, comp);

            // Remaining nodes are unreachable
            if (current.getProperty(DISTANCE) == Integer.MAX_VALUE) {
                break;
            }

            // Target node found, compute shortest path
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

            nodes.remove(current);

            // Traverse all neighbors
            for (IEdge edge : current.adjacentEdges()) {
                INode neighbor = current.getAdjacentNode(edge);

                // Skip already visited nodes
                if (!nodes.contains(neighbor)) {
                    continue;
                }

                // Check edge condition
                if (!condition.evaluate(new Pair<INode, IEdge>(neighbor, edge))) {
                    continue;
                }

                // Get edge cost property
                int cost = edge.getProperty(PATHCOST);
                cost += edge.getProperty(DISTANCE);

                if (cost < neighbor.getProperty(DISTANCE)) {
                    neighbor.setProperty(DISTANCE, cost);
                    edges[neighbor.getID()] = edge;
                }
            }
        }

        // Finished without reaching the target
        return null;
    }

}
