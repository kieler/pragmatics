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
package de.cau.cs.kieler.klay.planar.alg.orthogonal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.alg.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.IOrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.alg.pathfinding.IPathFinder;
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
     * the number of bends along the edge between the two faces. For every two faces, there are two
     * arcs, one for each direction.
     */
    private LinkedList<Pair<IEdge, IEdge>> faceArcs;

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public IOrthogonalRepresentation orthogonalize(final IGraph g) {
        getMonitor().begin("Orthogonalization", 1);

        // Initialization
        this.graph = g;
        this.graph.reindex();

        // Solve flow network and compute orthogonal representation
        IGraph network = this.createFlowNetwork();
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        solver.findFlow(network);
        IOrthogonalRepresentation orthogonal = this.computeAngles(network);

        getMonitor().done();
        return orthogonal;
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
        this.faceArcs = new LinkedList<Pair<IEdge, IEdge>>();

        // Creating source nodes for every graph node
        for (INode node : this.graph.getNodes()) {
            int supply = MAXDEGREE - node.getAdjacentEdgeCount();

            // Check if node has a valid degree
            if (supply < 0) {
                throw new IllegalArgumentException("The node " + node.getID()
                        + ") has a higher degree than the maximal allowed " + MAXDEGREE);
            }

            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, node);
            newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply);
            map.put(node, newnode);
        }

        // Creating sink nodes for every graph face
        boolean internal = false;
        for (IFace face : this.graph.getFaces()) {
            int supply = -1 * face.getAdjacentNodeCount();
            if (internal) {
                supply += MAXDEGREE;
            } else {
                internal = true;
                supply -= MAXDEGREE;
            }

            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply);
            map.put(face, newnode);
        }

        // Creating arcs for every node adjacent to the face
        for (IFace face : this.graph.getFaces()) {
            INode newnode = map.get(face);
            for (INode node : face.adjacentNodes()) {
                if (!map.containsKey(node)) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                IEdge newedge = network.addEdge(map.get(node), newnode, true);
                newedge.setProperty(IFlowNetworkSolver.CAPACITY, MAXDEGREE);
                newedge.setProperty(IFlowNetworkSolver.FLOW, 1);
                newedge.setProperty(IPathFinder.PATHCOST, 0);
                this.nodeArcs.add(newedge);
            }
        }

        // Creating arcs for every face adjacent to the face
        for (IEdge edge : this.graph.getEdges()) {
            IFace left = edge.getLeftFace();
            IFace right = edge.getRightFace();

            if (!map.containsKey(left) || !map.containsKey(right)) {
                throw new InconsistentGraphModelException(
                        "Attempted to link non-existent nodes by an edge.");
            }

            IEdge edgeLeft = network.addEdge(map.get(left), map.get(right), true);
            edgeLeft.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
            edgeLeft.setProperty(IPathFinder.PATHCOST, 1);
            IEdge edgeRight = network.addEdge(map.get(right), map.get(left), true);
            edgeRight.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
            edgeRight.setProperty(IPathFinder.PATHCOST, 1);
            this.faceArcs.add(new Pair<IEdge, IEdge>(edgeLeft, edgeRight));
        }

        return network;
    }

    /**
     * Compute bend points and edge angles based on flow in flow network.
     * 
     * @return an orthogonal representation encoding the graph shape
     */
    private IOrthogonalRepresentation computeAngles(final IGraph network) {
        final Map<IEdge, OrthogonalAngle[]> bendData = new HashMap<IEdge, OrthogonalAngle[]>();
        final Map<INode, List<Pair<IEdge, OrthogonalAngle>>> angleData = new HashMap<INode, List<Pair<IEdge, OrthogonalAngle>>>();

        // Flow in face arcs define bends in edges
        for (Pair<IEdge, IEdge> pair : this.faceArcs) {
            IFace face1 = (IFace) pair.getFirst().getSource().getProperty(NETWORKTOGRAPH);
            if (face1 != pair.getSecond().getTarget().getProperty(NETWORKTOGRAPH)) {
                throw new InconsistentGraphModelException(
                        "The flow network has not been build correctly.");
            }
            IFace face2 = (IFace) pair.getSecond().getSource().getProperty(NETWORKTOGRAPH);
            if (face2 != pair.getFirst().getTarget().getProperty(NETWORKTOGRAPH)) {
                throw new InconsistentGraphModelException(
                        "The flow network has not been build correctly.");
            }

            OrthogonalAngle[] bends1;
            OrthogonalAngle[] bends2;
            int flow1 = pair.getFirst().getProperty(IFlowNetworkSolver.FLOW);
            int flow2 = pair.getSecond().getProperty(IFlowNetworkSolver.FLOW);
            if (face1 == face2) {
                bends1 = new OrthogonalAngle[flow1];
                bends2 = new OrthogonalAngle[flow2];
                Arrays.fill(bends1, OrthogonalAngle.RIGHT);
                Arrays.fill(bends2, OrthogonalAngle.LEFT);
            } else {
                bends1 = new OrthogonalAngle[flow1 + flow2];
                for (int i = 0; i < flow1; i++) {
                    bends1[i] = OrthogonalAngle.RIGHT;
                }
                for (int i = flow1; i < bends1.length; i++) {
                    bends1[i] = OrthogonalAngle.LEFT;
                }
                bends2 = new OrthogonalAngle[flow2 + flow1];
                for (int i = 0; i < flow2; i++) {
                    bends2[i] = OrthogonalAngle.RIGHT;
                }
                for (int i = flow2; i < bends2.length; i++) {
                    bends2[i] = OrthogonalAngle.LEFT;
                }
            }

            boolean first = true;
            for (IEdge edge : face1.getEdges(face2)) {
                if (first) {
                    first = false;

                    if ((face1 == edge.getRightFace()) && (face2 == edge.getLeftFace())) {
                        bendData.put(edge, bends1);
                    } else if ((face1 == edge.getLeftFace()) && (face2 == edge.getRightFace())) {
                        bendData.put(edge, bends2);
                    } else {
                        throw new InconsistentGraphModelException(
                                "The flow network has not been build correctly.");
                    }
                } else {
                    OrthogonalAngle[] bends = new OrthogonalAngle[0];
                    bendData.put(edge, bends);
                }
            }
        }

        // Flow in node arcs define angles in nodes
        for (IEdge arc : this.nodeArcs) {
            INode node = (INode) arc.getSource().getProperty(NETWORKTOGRAPH);
            IFace face = (IFace) arc.getTarget().getProperty(NETWORKTOGRAPH);

            List<Pair<IEdge, OrthogonalAngle>> nodeList;
            if (!angleData.containsKey(node)) {
                nodeList = new LinkedList<Pair<IEdge, OrthogonalAngle>>();
                for (IEdge edge : node.adjacentEdges()) {
                    nodeList.add(new Pair<IEdge, OrthogonalAngle>(edge, null));
                }
                angleData.put(node, nodeList);
            } else {
                nodeList = angleData.get(node);
            }

            int numEdges = 0;
            for (Pair<IEdge, OrthogonalAngle> pair : nodeList) {
                IEdge edge = pair.getFirst();
                if (((node == edge.getSource()) && (face == edge.getRightFace()))
                        || ((node == edge.getTarget()) && (face == edge.getLeftFace()))) {
                    numEdges++;
                }
            }

            int i = numEdges;
            for (Pair<IEdge, OrthogonalAngle> pair : nodeList) {
                IEdge edge = pair.getFirst();
                if (((node == edge.getSource()) && (face == edge.getRightFace()))
                        || ((node == edge.getTarget()) && (face == edge.getLeftFace()))) {
                    i--;
                    if (i > 0) {
                        pair.setSecond(OrthogonalAngle.RIGHT);
                    } else {
                        int angle = arc.getProperty(IFlowNetworkSolver.FLOW) - numEdges;
                        pair.setSecond(OrthogonalAngle.values()[angle]);
                    }
                }
            }
        }

        return new IOrthogonalRepresentation() {
            public OrthogonalAngle[] getBends(final IEdge edge) {
                return bendData.get(edge);
            }

            public List<Pair<IEdge, OrthogonalAngle>> getAngle(final INode node) {
                return angleData.get(node);
            }
        };
    }

}
