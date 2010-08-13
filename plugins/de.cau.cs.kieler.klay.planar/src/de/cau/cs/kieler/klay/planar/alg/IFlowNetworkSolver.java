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

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.util.IFunction;

/**
 * Interface for algorithms to solve problems in flow networks. Uses the Strategy design pattern to
 * provide a common interface for various implementation of algorithms working on flow networks.
 * 
 * @author ocl
 */
public interface IFlowNetworkSolver extends IAlgorithm {

    /**
     * Solve a problem on a flow network. The method takes a function that assigns a supply or
     * demand value to every node in the network and another function that assigns capacity (i.e.
     * the maximal flow possible) to every arc. It returns a function that assigns the computed flow
     * value to every arc in the network.
     * 
     * @param network
     *            the network to work on
     * @param supply
     *            a function that assigns supply and demand values to nodes
     * @param capacity
     *            a function that assigns a capacity to all edges
     * @return a function that assigns flow values to nodes
     */
    IFunction<IEdge, Integer> findFlow(IGraph network, IFunction<INode, Integer> supply,
            IFunction<IEdge, Integer> capacity);

    /**
     * Interface for algorithms to solve the maximum flow problem in a flow network. The maximum
     * flow problem attempts to find the maximum possible flow through a flow network.
     */
    public interface IMaximumFlowSolver extends IFlowNetworkSolver {
    }

    /**
     * Interface for algorithms to solve the minimum cost flow problem in a flow network. The
     * minimum cost flow problem attempts to find the cheapest way to send a given flow through a
     * flow network.
     */
    public interface IMinimumCostFlowSolver extends IFlowNetworkSolver {
    }

}
