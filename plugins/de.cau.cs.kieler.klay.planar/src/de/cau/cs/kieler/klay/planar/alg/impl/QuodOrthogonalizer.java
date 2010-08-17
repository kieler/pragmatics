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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Property;
import de.cau.cs.kieler.klay.planar.alg.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.alg.IOrthogonalizer;
import de.cau.cs.kieler.klay.planar.alg.IPathFinder;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * Implementation of the Quod orthogonilazation algorithm, based on the paper "Quasi-Orthogonal
 * Drawing of Planar Graphs", by Gunnar W. Klau and Petra Mutzel.
 * 
 * TODO improved javadoc
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

    // ======================== Helper Classes =====================================================

    /**
     * A cage in the working graph. Since the underlying flow network can only solve
     * orthogonalization problems with a maximum node degree of 4, evey node with a higher degree is
     * replaced by a cage. A cage consists of a face replacing the original node, surrounded by a
     * ring with a node for every edge the original node was adjacent to. This cage class is used to
     * keep track of a cage in the graph for a clean transformation.
     */
    private class Cage {

        // -------------------- Attributes ---------------------------------------------------------

        /** The graph the cage is a part of. */
        private IGraph graph;

        /** The original node replaced by the cage. */
        private INode cageNode;

        /** A mapping from the ring nodes to the corresponding edges. */
        private LinkedHashMap<INode, IEdge> map;

        // -------------------- Constructor --------------------------------------------------------

        /**
         * Replace a node in the graph by a cage.
         * 
         * @param node
         *            the node to replace
         */
        public Cage(final INode node) {
            this.cageNode = node;
            this.graph = this.cageNode.getParent();
            this.map = new LinkedHashMap<INode, IEdge>(this.cageNode.getAdjacentEdgeCount() * 2);

            // Create temporary list to not break the iterator
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : this.cageNode.adjacentEdges()) {
                edges.add(edge);
            }

            // Create cage nodes
            INode first = null;
            INode previous = null;
            INode current = null;
            for (IEdge edge : edges) {
                previous = current;
                current = this.graph.addNode();
                this.map.put(current, edge);
                if (first == null) {
                    first = current;
                }
                if (previous != null) {
                    this.graph.addEdge(previous, current);
                }
                edge.move(this.cageNode.getAdjacentNode(edge), current);
            }
            if (first != null) {
                this.graph.addEdge(current, first);
            }
        }

        /**
         * Remove the cage from the graph.
         */
        public void remove() {
            // Since we use a linked hash map, the edges are added in the original order
            for (Map.Entry<INode, IEdge> entry : this.map.entrySet()) {
                INode node = entry.getKey();
                IEdge edge = entry.getValue();
                edge.move(node, this.cageNode);
                this.graph.removeNode(node);
            }
        }
    }

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private IGraph graph;

    /**
     * The arcs in the flow network from a node to an adjacent face. The flow in these arcs specify
     * the sum of angles at the source node in the target face.
     */
    private LinkedList<IEdge> nodeArcs;

    /**
     * The arcs in the flow network from a face to an adjacent face. The flow in these arcs specify
     * the number of bends along the edge between the two faces.
     */
    private LinkedList<IEdge> faceArcs;

    // ======================== Algorithm ==========================================================
    // TODO does leaving the cage nodes in the graph influence the flow network?

    /**
     * {@inheritDoc}
     */
    public void orthogonalize(final IGraph g) {
        getMonitor().begin("Orthogonalization", 1);

        // Initialization
        this.graph = g;
        this.graph.reindex();

        // Replace high-degree nodes with cages
        LinkedList<Cage> cages = new LinkedList<Cage>();
        for (INode node : this.graph.getNodes()) {
            if (node.getAdjacentEdgeCount() > MAXDEGREE) {
                cages.add(new Cage(node));
            }
        }

        // Solve flow network
        IGraph network = this.createFlowNetwork();
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        solver.findFlow(network);

        // TODO create orthogonal representation based on flow
        // TODO force cages as rectangles

        // Remove the cages from the graph
        for (Cage cage : cages) {
            cage.remove();
        }
        getMonitor().done();
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
        IGraph network = factory.createEmptyGraph();
        HashMap<IGraphElement, INode> map = new HashMap<IGraphElement, INode>();
        this.nodeArcs = new LinkedList<IEdge>();
        this.faceArcs = new LinkedList<IEdge>();

        // Creating source nodes for every graph node
        for (INode node : this.graph.getNodes()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, node);
            newnode.setProperty(IFlowNetworkSolver.SUPPLY, MAXDEGREE);
            map.put(node, newnode);
        }

        // Creating sink nodes for every graph face
        for (IFace face : this.graph.getFaces()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            map.put(face, newnode);
        }

        // Linking nodes
        boolean internal = false;
        for (IFace face : this.graph.getFaces()) {
            INode newnode = map.get(face);

            // Creating arcs for every node adjacent to the face
            for (INode node : face.adjacentNodes()) {
                if (!map.containsKey(node)) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                IEdge newedge = network.addEdge(map.get(node), newnode, true);
                newedge.setProperty(IFlowNetworkSolver.CAPACITY, MAXDEGREE);
                newedge.setProperty(IPathFinder.PATHCOST, 0);
                // TODO edge has lower bound 1
                this.nodeArcs.add(newedge);
            }

            // Creating arcs for every face adjacent to the face
            for (IFace adj : face.adjacentFaces()) {
                IEdge newedge = network.addEdge(map.get(adj), newnode, true);
                newedge.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
                newedge.setProperty(IPathFinder.PATHCOST, 1);
                // TODO edge has lower bound 0
                this.faceArcs.add(newedge);
            }

            // Set demand property of the node
            int supply = -2 * newnode.getAdjacentEdgeCount();
            if (internal) {
                newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply + 4);
            } else {
                internal = true;
                newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply - 4);
            }
        }

        return network;
    }
}
