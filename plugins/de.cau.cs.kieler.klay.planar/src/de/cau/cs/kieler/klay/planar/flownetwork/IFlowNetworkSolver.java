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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;

/**
 * Interface for algorithms to solve problems in flow networks. Uses the Strategy design pattern to
 * provide a common interface for various implementations of algorithms working on flow networks.
 * 
 * @author ocl
 * @author pkl
 * @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public interface IFlowNetworkSolver {

    /**
     * A property assigning a supply or demand value to a node. A positive value denotes a supply
     * and a node with a supply is considered a source in the network. A negative value denotes a
     * demand and a node with a demand is considered a sink in the network. A supply of 0 does not
     * affect anything.
     */
    Property<Integer> SUPPLY = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.networksupply", 0);

    /**
     * A property assigning a capacity to an edge. The capacity of an edge is the maximum flow
     * allowed to be transferred through the edge.
     */
    Property<Integer> CAPACITY = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.networkcapacity", 0);

    /**
     * A property assigning a lower bound to an edge. The lower bound of an edge is the minimum flow
     * allowed to be transferred through the edge.
     */
    Property<Integer> LOWER_BOUND = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.networklowerbound", 0);

    /**
     * A property assigning a capacity to an edge in the residual network. The capacity in the
     * residual network is the initial capacity minus the flow in edge direction, or the flow
     * against edge direction.
     */
    Property<Integer> RESIDUAL_CAPACITY = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.networkresidualcapacity", 0);

    /** A property assigning an adjacent edge of two face nodes in the flow network.
     * There are arcs for each neighbored faces. Faces are neighbored when they are separated by an edge.
     * This edge of the original graph is stored in this property.*/
    Property<PEdge> CROSSING_EDGE = new Property<PEdge>(
            "de.cau.cs.kieler.klay.planar.properties.crossingedge");

    /**
     * A property assigning a flow to an edge. This property is usually set during the network
     * solving algorithm.
     */
    Property<Integer> FLOW = new Property<Integer>(
            "de.cau.cs.kieler.klay.planar.properties.networkflow", 0);

    /**
     * Solve a problem on a flow network. The method digests the supply and capacity properties on
     * graph elements, and computes a flow property for every arc in the network
     * 
     * @param network
     *            the network to work on
     * @param progressMonitor
     *            a progress monitor to track algorithm progress
     */
    void calcFlow(PGraph network, IKielerProgressMonitor progressMonitor);

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
