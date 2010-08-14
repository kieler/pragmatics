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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder.IShortestPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Use the Bellman-Ford Algorithm to find the shortest path between two nodes in any graph. Contrary
 * to Dijkstra's Algorithm, the Bellman-Ford Algorithm allows negative edge costs and detects
 * negative cycles.
 * 
 * @author ocl
 */
public class BellmanFordPathFinder extends AbstractAlgorithm implements IShortestPathFinder {

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
        final int[] distance = new int[size];
        IEdge[] edges = new IEdge[size];

        Arrays.fill(distance, -1);
        distance[source.getID()] = 0;

        // Relax edges
        for (int i = 1; i < size; i++) {
            for (IEdge edge : source.getParent().getEdges()) {
                int iNeighbor = edge.getTarget().getID();
                Integer cost = edge.getProperty(PATHCOST);
                cost += distance[edge.getSource().getID()];
                if (cost < distance[iNeighbor]) {
                    distance[iNeighbor] = cost;
                    edges[iNeighbor] = edge;
                }

            }
        }

        // Detect negative cycles
        for (IEdge edge : source.getParent().getEdges()) {
            int iNeighbor = edge.getTarget().getID();
            Integer cost = edge.getProperty(PATHCOST);
            cost += distance[edge.getSource().getID()];
            if (cost < distance[iNeighbor]) {
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
