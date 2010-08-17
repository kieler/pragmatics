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
 * Orthogonalizer based on the bend minimizing algorithm for 4-planar graphs by R. Tamassia. Note
 * that this orthogonalizer works only on planar graphs with a degree of at most 4.
 * 
 * @author ocl
 */
public class TamassiaOrthogonalizer extends AbstractAlgorithm implements IOrthogonalizer {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<IGraphElement> NETWORKTOGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    /** The maximal node degree in an orthogonal layout. */
    public static final int MAXDEGREE = 4;

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

    /**
     * {@inheritDoc}
     */
    public void orthogonalize(final IGraph g) {
        getMonitor().begin("Orthogonalization", 1);

        // Initialization
        this.graph = g;
        this.graph.reindex();

        // Solve flow network
        IGraph network = this.createFlowNetwork();
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        solver.findFlow(network);

        // Create orthogonal representation based on flow
        // TODO
        for (IEdge arc : this.faceArcs) {
            IFace face1 = (IFace) arc.getSource().getProperty(NETWORKTOGRAPH);
            IFace face2 = (IFace) arc.getTarget().getProperty(NETWORKTOGRAPH);
            IEdge edge = face1.getEdge(face2);
            // TODO check face1 == face2
            if (face1 == edge.getLeftFace()) {
                int leftbends = arc.getProperty(IFlowNetworkSolver.FLOW);
            } else if (face1 == edge.getRightFace()) {
                int rightbends = arc.getProperty(IFlowNetworkSolver.FLOW);
            } else {
                throw new InconsistentGraphModelException("The face (" + face1.getID()
                        + ") is not adjacent to the face (" + face2.getID() + ").");
            }
        }
        for (IEdge arc : this.nodeArcs) {
            INode node = (INode) arc.getSource().getProperty(NETWORKTOGRAPH);
            IFace face = (IFace) arc.getTarget().getProperty(NETWORKTOGRAPH);
            int angleInNode = arc.getProperty(IFlowNetworkSolver.FLOW);
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
            // Check if node has a valid degree
            if (node.getAdjacentEdgeCount() > MAXDEGREE) {
                throw new IllegalArgumentException("The node " + node.getID()
                        + ") has a higher degree than the maximal allowed " + MAXDEGREE);
            }

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
                newedge.setProperty(IFlowNetworkSolver.LOWERBOUND, 1);
                newedge.setProperty(IPathFinder.PATHCOST, 0);
                this.nodeArcs.add(newedge);
            }

            // Creating arcs for every face adjacent to the face
            for (IFace adj : face.adjacentFaces()) {
                IEdge newedge = network.addEdge(map.get(adj), newnode, true);
                newedge.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
                newedge.setProperty(IFlowNetworkSolver.LOWERBOUND, 0);
                newedge.setProperty(IPathFinder.PATHCOST, 1);
                this.faceArcs.add(newedge);
            }

            // Set demand property of the node
            int supply = -1 * 2 * newnode.getAdjacentEdgeCount();
            if (internal) {
                newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply + MAXDEGREE);
            } else {
                internal = true;
                newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply - MAXDEGREE);
            }
        }

        return network;
    }

}
