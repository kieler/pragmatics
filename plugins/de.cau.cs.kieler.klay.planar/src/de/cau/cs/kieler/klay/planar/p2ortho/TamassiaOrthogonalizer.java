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
package de.cau.cs.kieler.klay.planar.p2ortho;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * Orthogonalizer based on the bend minimizing algorithm for 4-planar graphs by R. Tamassia. Note
 * that this orthogonalizer works only on planar graphs with a degree of at most 4. The algorithm
 * first computes a network flow model. Additionally a minimum cost flow calculation is done on that
 * model such that the number of bends is minimal. Finally the angles and bends are derived from the
 * minimum cost flow model.
 * 
 * @author ocl
 * @author pkl
 */
public class TamassiaOrthogonalizer extends AbstractAlgorithm implements ILayoutPhase {

    /** Sum of angles around a node. */
    private static final int SUM_OF_ANGLES = 4;

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORK_TO_GRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    /** The maximal node degree in an orthogonal layout. */
    public static final int MAX_DEGREE = 4;

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private PGraph graph;

    /**
     * The arcs in the flow network from a node to an adjacent face. The flow in these arcs specify
     * the sum of angles at the source node in the target face.
     */
    private LinkedList<PEdge> nodeArcs;

    /**
     * The arcs in the flow network from a face to an adjacent face. The flow in these arcs specify
     * the number of bends along the edge between the two faces. For every two faces, there are two
     * arcs, one for each direction.
     */
    private LinkedList<Pair<PEdge, PEdge>> faceArcs;

    /**
     * Create the flow network base upon the graph. The flow network contains a source node for
     * every node in the graph and a sink node for every face. It contains an arc for every node and
     * every face adjacent to a face in the graph. The minimum cost flow problem on the resulting
     * network describe the bend minimizing problem on the graph.
     * 
     * @return the flow network to compute the minimal number of bends
     */
    private PGraph createFlowNetwork() {
        PGraph network = new PGraphFactory().createEmptyGraph();

        // Contains original nodes as keys and network nodes as value.
        Map<PNode, PNode> nodeMapping = Maps.newHashMap();

        // Contains original face and the representing node in the network.
        Map<PFace, PNode> faceMapping = Maps.newHashMap();

        this.nodeArcs = Lists.newLinkedList();
        this.faceArcs = Lists.newLinkedList();

        // Creating source nodes for every graph node
        for (PNode node : this.graph.getNodes()) {
            int supply = MAX_DEGREE - node.getAdjacentEdgeCount();
            // Check if node has a valid degree
            if (supply < 0) {
                throw new InconsistentGraphModelException("The node " + node.id
                        + ") has a higher degree than the maximal allowed " + MAX_DEGREE);
            }

            PNode newnode = network.addNode(NodeType.NORMAL);
            newnode.setProperty(NETWORK_TO_GRAPH, node);
            newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply);
            nodeMapping.put(node, newnode);
        }

        // Add node and face mappings.
        Iterable<PFace> faces = this.graph.getFaces();
        for (PFace face : faces) {
            int supply = -1 * face.getAdjacentEdgeCount();
            if (face == graph.getExternalFace()) {
                supply -= MAX_DEGREE;
            } else {
                supply += MAX_DEGREE;
            }
            PNode newnode = network.addNode(NodeType.FACE);
            newnode.setProperty(NETWORK_TO_GRAPH, face);
            newnode.setProperty(IFlowNetworkSolver.SUPPLY, supply);
            faceMapping.put(face, newnode);

            // Creating arcs for every node adjacent to the face
            for (PNode node : face.adjacentNodes()) {
                if (!nodeMapping.containsKey(node)) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                PEdge newedge = network.addEdge(nodeMapping.get(node), faceMapping.get(face), true);
                newedge.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
                newedge.setProperty(IFlowNetworkSolver.FLOW, 1);
                newedge.setProperty(IPathFinder.PATHCOST, 0);
                this.nodeArcs.add(newedge);
            }
        }

        // Creating arcs for every face adjacent to the face
        // Meaning for every node in the original graph add edges to the adjacent faces.
        for (PEdge edge : this.graph.getEdges()) {
            PFace left = edge.getLeftFace();
            PFace right = edge.getRightFace();

            if (!faceMapping.containsKey(left) || !faceMapping.containsKey(right)) {
                throw new InconsistentGraphModelException(
                        "Attempted to link non-existent face by an edge.");
            }
            // Same face need no arc.
            if (left == right) {
                PNode faceNode = faceMapping.get(left);
                int supply = faceNode.getProperty(IFlowNetworkSolver.SUPPLY).intValue();
                if (left == graph.getExternalFace()) {
                    faceNode.setProperty(IFlowNetworkSolver.SUPPLY, supply - 1);
                } else {
                    faceNode.setProperty(IFlowNetworkSolver.SUPPLY, supply + 1);
                }
                continue;
            }
            PEdge edgeLeft = network.addEdge(faceMapping.get(left), faceMapping.get(right), true);
            edgeLeft.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
            edgeLeft.setProperty(IPathFinder.PATHCOST, 1);
            edgeLeft.setProperty(IFlowNetworkSolver.CROSSING_EDGE, edge);

            PEdge edgeRight = network.addEdge(faceMapping.get(right), faceMapping.get(left), true);
            edgeRight.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
            edgeRight.setProperty(IPathFinder.PATHCOST, 1);
            edgeRight.setProperty(IFlowNetworkSolver.CROSSING_EDGE, edge);
            this.faceArcs.add(new Pair<PEdge, PEdge>(edgeLeft, edgeRight));

        }

        return network;
    }

    /**
     * Compute bend points and edge angles based on flow in flow network.
     * 
     * @return an orthogonal representation encoding the graph shape
     */
    private OrthogonalRepresentation computeAngles(final PGraph network) {
        OrthogonalRepresentation orthogonal = new OrthogonalRepresentation();

        // Flow in face arcs define bends in edges
        for (Pair<PEdge, PEdge> pair : this.faceArcs) {
            PFace face1 = (PFace) pair.getFirst().getSource().getProperty(NETWORK_TO_GRAPH);
            if (face1 != pair.getSecond().getTarget().getProperty(NETWORK_TO_GRAPH)) {
                throw new InconsistentGraphModelException(
                        "The flow network has not been build correctly.");
            }
            PFace face2 = (PFace) pair.getSecond().getSource().getProperty(NETWORK_TO_GRAPH);
            if (face2 != pair.getFirst().getTarget().getProperty(NETWORK_TO_GRAPH)) {
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

            // Adds the bends to the orthogonal representation. The direction of the bend depends on
            // hand the face is placed relative to the edge.
            PEdge crossingEdge = pair.getFirst().getProperty(IFlowNetworkSolver.CROSSING_EDGE);
            for (PEdge edge : face1.getEdges(face2)) {
                if (crossingEdge == edge) {
                    if ((face1 == edge.getRightFace()) && (face2 == edge.getLeftFace())) {
                        orthogonal.setBends(edge, bends1);
                    } else if ((face1 == edge.getLeftFace()) && (face2 == edge.getRightFace())) {
                        orthogonal.setBends(edge, bends2);
                    } else {
                        throw new InconsistentGraphModelException(
                                "The flow network has not been build correctly.");
                    }
                } else if (orthogonal.getBends(edge) == null) {
                    orthogonal.setBends(edge, new OrthogonalAngle[0]);
                }
            }
        }

        // Flow in node arcs define angles in nodes.
        for (PEdge arc : this.nodeArcs) {
            PNode node = (PNode) arc.getSource().getProperty(NETWORK_TO_GRAPH);
            PFace face = (PFace) arc.getTarget().getProperty(NETWORK_TO_GRAPH);

            List<Pair<PEdge, OrthogonalAngle>> nodeList = orthogonal.getAngles(node);
            if (nodeList == null) {
                nodeList = Lists.newLinkedList();
                for (PEdge edge : node.adjacentEdges()) {
                    nodeList.add(new Pair<PEdge, OrthogonalAngle>(edge, null));
                }
                orthogonal.setAngles(node, nodeList);
            }

            // Identifying a cutvertex while checking if left and right face of a adjacent edge
            // is the same.
            boolean isCutVertex = false;
            for (PEdge edge : node.adjacentEdges()) {
                if (edge.getLeftFace() == edge.getRightFace()) {
                    isCutVertex = true;
                    break;
                }
            }

            if (!isCutVertex) {
                // Map normally calculated flow of arc to the corresponding edge angle.
                for (Pair<PEdge, OrthogonalAngle> pair : nodeList) {
                    PEdge edge = pair.getFirst();
                    if (((node == edge.getSource()) && (face == edge.getRightFace()))
                            || ((node == edge.getTarget()) && (face == edge.getLeftFace()))) {
                        int angle = arc.getProperty(IFlowNetworkSolver.FLOW) - 1;
                        if (angle < 0) {
                            // TODO if happen, inspect!!! Error prone.
                            angle = 0;
                        }
                        pair.setSecond(OrthogonalAngle.map(angle));
                    }
                }
            }
        }

        // Flow in node arcs define angles in nodes, handling cut vertices.
        for (PEdge arc : this.nodeArcs) {
            PNode node = (PNode) arc.getSource().getProperty(NETWORK_TO_GRAPH);
            PFace face = (PFace) arc.getTarget().getProperty(NETWORK_TO_GRAPH);

            List<Pair<PEdge, OrthogonalAngle>> nodeList = orthogonal.getAngles(node);
            if (nodeList == null) {
                nodeList = Lists.newLinkedList();
                for (PEdge edge : node.adjacentEdges()) {
                    nodeList.add(new Pair<PEdge, OrthogonalAngle>(edge, null));
                }
                orthogonal.setAngles(node, nodeList);
            }

            // Identifying a cutvertex while checking if left and right face of a adjacent edge
            // is the same.
            boolean isCutVertex = false;
            for (PEdge edge : node.adjacentEdges()) {
                if (edge.getLeftFace() == edge.getRightFace()) {
                    isCutVertex = true;
                    break;
                }
            }

            if (isCutVertex) {
                int totalEdgeCount = node.getAdjacentEdgeCount();

                switch (totalEdgeCount) {
                case 4:
                    for (Pair<PEdge, OrthogonalAngle> pair : nodeList) {
                        pair.setSecond(OrthogonalAngle.LEFT);
                    }
                    break;
                    
                case 3:
                    // Count the adjacent face edges.
                    int adjacentFaceEdgeCount = 0;
                    for (PEdge edge : node.adjacentEdges()) {
                        if (edge.getLeftFace() == face || edge.getRightFace() == face) {
                            adjacentFaceEdgeCount++;
                        }
                    }
                    if (adjacentFaceEdgeCount == totalEdgeCount) {
                        // More or less arbitrary added these angles.
                        nodeList.get(0).setSecond(OrthogonalAngle.STRAIGHT);
                        nodeList.get(1).setSecond(OrthogonalAngle.LEFT);
                        nodeList.get(2).setSecond(OrthogonalAngle.LEFT);
                    } else {
                        // Assuming that only one edge is cutedge.
                        int angleCount = 0;
                        int cutEdgeIndex = -1;
                        for (int i = 0; i < nodeList.size(); i++) {
                            // Count the angles of the other edges.
                            if (nodeList.get(i).getFirst().getLeftFace() != nodeList.get(i)
                                    .getFirst().getRightFace()) {
                                angleCount += nodeList.get(i).getSecond().ordinal() + 1;
                            } else {
                                cutEdgeIndex = i;
                            }
                        } 
                        
                        // Since all other angles of the adjacent 
                        nodeList.get(cutEdgeIndex).setSecond(
                                OrthogonalAngle.map((4 - angleCount) - 1));
                    }
                    break;
                    
                case 2:
                    for (Pair<PEdge, OrthogonalAngle> pair : nodeList) {
                        pair.setSecond(OrthogonalAngle.STRAIGHT);
                    }
                    break;
                    
                case 1:
                    nodeList.get(0).setSecond(OrthogonalAngle.FULL);
                    break;
                    
                default:
                    throw new InconsistentGraphModelException("ladida du bist doof!");
                }
            }
        }
        return orthogonal;
    }

    /**
     * {@inheritDoc}
     * 
     * This takes a planar graph and computes an orthogonal representation defining the shape of the
     * orthogonal graph.
     * 
     * @param pgraph
     *            the graph to draw as orthogonal graph
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Orthogonalization", 1);

        // Initialization
        this.graph = pgraph;

        // Solve flow network and compute orthogonal representation
        PGraph network = createFlowNetwork();
        new SuccessiveShortestPathFlowSolver().findFlow(network);
        pgraph.setProperty(Properties.ORTHO_REPRESENTATION, computeAngles(network));
        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            testOrthoRep();
        }
        getMonitor().done();
    }

    /**
     * 
     */
    private void testOrthoRep() {
        OrthogonalRepresentation ortho = graph.getProperty(Properties.ORTHO_REPRESENTATION);
        int directionCount = 0;
        for (PNode node : graph.getNodes()) {
            List<Pair<PEdge, OrthogonalAngle>> angles = ortho.getAngles(node);
            directionCount = 0;
            for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                directionCount += pair.getSecond().ordinal() + 1;
            }
            if (directionCount != SUM_OF_ANGLES) {
                throw new InconsistentGraphModelException(
                        "TamassiaOrthogonalizer, testOrthoRep: the sum of orthogonal angles around node"
                                + node.toString() + "is not 4.");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingStrategy(final PGraph pgraph) {
        IntermediateProcessingConfiguration strategy = new IntermediateProcessingConfiguration();
        strategy.addLayoutProcessor(IntermediateProcessingConfiguration.BEFORE_PHASE_3,
                LayoutProcessorStrategy.EXT_FACE);
        return strategy;
    }

}
