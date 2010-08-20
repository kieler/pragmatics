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
package de.cau.cs.kieler.klay.planar.alg;

import java.util.List;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Interface for algorithms to find a path in a graph. Uses the Strategy design pattern to provide a
 * common interface for implementations of path finding algorithms.
 * 
 * @author ocl
 */
public interface IPathFinder extends IAlgorithm {

    /**
     * A property assigning a cost to an edge. This property is used when computing a shortest path
     * in a graph.
     */
    Property<Integer> PATHCOST = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.pathcost", 1);

    /**
     * Find a path in a graph between two nodes. The algorithm looks for a valid path in the parent
     * graph of the source node. If the target node is not reachable from the source node, the
     * algorithm returns {@code null}. This default version ignores edge directions and traverses
     * edges always in both ways.
     * 
     * @param source
     *            the source node of the path
     * @param target
     *            the target node of the path
     * @return the list of edges defining a path between {@code source} and {@code target}
     */
    List<IEdge> findPath(INode source, INode target);

    /**
     * Find a path in a graph between two node. The algorithm looks for a valid path in the parent
     * graph of the source node. If the target node is not reachable from the source node, the
     * algorithm returns {@code null}. An additional condition defines in which cases an edge can be
     * entered from a specific node. This condition can be used to explicitly handle directed edges.
     * 
     * @param source
     *            the source node of the path
     * @param target
     *            the target node of the path
     * @param condition
     *            a condition that defines if an edge can be entered from a specific node
     * @return the list of edges defining a path between {@code source} and {@code target}
     */
    List<IEdge> findPath(INode source, INode target, ICondition<Pair<INode, IEdge>> condition);

    /**
     * Interface for algorithms to find the shortest path in a graph.
     */
    public interface IShortestPathFinder extends IPathFinder {
    }

}
