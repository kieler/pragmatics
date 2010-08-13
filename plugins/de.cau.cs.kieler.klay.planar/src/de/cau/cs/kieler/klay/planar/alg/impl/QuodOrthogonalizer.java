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

import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Property;
import de.cau.cs.kieler.klay.planar.alg.IOrthogonalizer;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver.IMinimumCostFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;
import de.cau.cs.kieler.klay.planar.util.IFunction;

/**
 * Implementation of the Quod orthogonilazation algorithm, based on the paper "Quasi-Orthogonal
 * Drawing of Planar Graphs", by Gunnar W. Klau and Petra Mutzel.
 * 
 * @author ocl
 */
public class QuodOrthogonalizer extends AbstractAlgorithm implements IOrthogonalizer {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    private static final Property<IGraphElement> NETWORKTOGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    /** The maximal node degree in an orthogonal layout. */
    private static final int MAXDEGREE = 4;

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private IGraph graph;

    /** The supply and demand values of every node in the flow network. */
    private int[] supply;

    /** The capacity values of every arc in the flow network. */
    private int[] capacity;

    /** The cost values of every arc in the flow network. */
    private int[] cost;

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void orthogonalize(final IGraph g) {
        getMonitor().begin("Orthogonalization", 1);

        // Initialization
        this.graph = g;
        this.graph.reindex();
        this.addCages();

        // Solve flow network to get minimum flows
        IGraph network = this.createFlowNetwork();
        IMinimumCostFlowSolver solver = new SuccessiveShortestPathFlowSolver();

        IFunction<INode, Integer> getSupply = new IFunction<INode, Integer>() {
            public Integer evaluate(final INode element) {
                return supply[element.getID()];
            }
        };

        IFunction<IEdge, Integer> getCapacity = new IFunction<IEdge, Integer>() {
            public Integer evaluate(final IEdge element) {
                return capacity[element.getID()];
            }
        };

        IFunction<IEdge, Integer> toFlow = solver.findFlow(network, getSupply, getCapacity);

        // TODO create orthogonal representation based on flow
        // TODO force cages as rectangles
        // TODO replace cages with nodes
        getMonitor().done();
    }

    /**
     * Replace every node with more than 4 adjacent edges with a ring of nodes. The ring contains a
     * node for every edge adjacent to the original node.
     */
    private void addCages() {
        for (INode node : this.graph.getNodes()) {
            // Check for node degree
            if (node.getAdjacentEdgeCount() > MAXDEGREE) {
                continue;
            }

            // Create temporary list to not break the iterator
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : node.adjacentEdges()) {
                edges.add(edge);
            }

            // Create cage nodes
            INode first = null;
            INode previous = null;
            INode current = null;
            for (IEdge edge : edges) {
                previous = current;
                current = this.graph.addNode();
                if (first == null) {
                    first = current;
                }
                if (previous != null) {
                    this.graph.addEdge(previous, current);
                }
                edge.move(node.getAdjacentNode(edge), current);
            }
            if (first != null) {
                this.graph.addEdge(current, first);
            }
        }
    }

    /**
     * Create the flow network base upon the graph. The flow network contains a source node for
     * every node in the graph and a sink node for every face. It contains an arc for every node and
     * every face adjacent to a face in the graph. The minimum cost flow problem on the resulting
     * network describe the bend minimizing problem on the graph
     * 
     * @return the flow network to compute the minimal number of bends
     */
    private IGraph createFlowNetwork() {
        IGraphFactory factory = new PGraphFactory();
        IGraph dualgraph = factory.createDualGraph(this.graph);
        IGraph network = factory.createEmptyGraph();
        HashMap<IGraphElement, INode> map = new HashMap<IGraphElement, INode>();

        this.supply = new int[this.graph.getNodeCount() + this.graph.getFaceCount()];
        int size = 0; // TODO get number of edges in network
        this.capacity = new int[size];
        this.cost = new int[size];

        // Creating source nodes for every graph node
        for (INode node : this.graph.getNodes()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, node);
            this.supply[newnode.getID()] = 4;
            map.put(node, newnode);
        }

        // Creating sink nodes for every graph face
        for (IFace face : this.graph.getFaces()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            // TODO node is sink: 2*degree-4 for internal, 2*degree+4 for external
            map.put(face, newnode);

            // Creating arcs for every node adjacent to the face
            for (INode node : face.getNodes()) {
                if (!map.containsKey(node)) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                IEdge newedge = network.addEdge(map.get(node), map.get(face), true);
                this.capacity[newedge.getID()] = 4;
                this.cost[newedge.getID()] = 0;
                // TODO edge has lower bound 1
            }
        }

        // Create arcs for edges in the dual graph
        for (IEdge edge : dualgraph.getEdges()) {
            IFace source = (IFace) edge.getSource().getProperty(IGraphFactory.TODUALGRAPH);
            IFace target = (IFace) edge.getTarget().getProperty(IGraphFactory.TODUALGRAPH);
            IEdge newedge = network.addEdge(map.get(source), map.get(target), true);
            this.capacity[newedge.getID()] = Integer.MAX_VALUE;
            this.cost[newedge.getID()] = 1;
            // TODO edge has lower bound 0
        }

        return network;
    }

}
