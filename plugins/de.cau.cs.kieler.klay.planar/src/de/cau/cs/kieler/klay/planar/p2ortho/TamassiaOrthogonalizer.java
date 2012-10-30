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
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
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
 * @kieler.rating proposed yellow by pkl
 */
public class TamassiaOrthogonalizer extends AbstractAlgorithm implements ILayoutPhase {

    /** Sum of angles around a node. */
    private static final int SUM_OF_ANGLES = 4;

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

    private OrthogonalRepresentation orthogonal;

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION 
        = new IntermediateProcessingConfiguration(
            // Before Phase 1
            null,
            // Before Phase 2
            null,
            // Before Phase 3
            EnumSet.of(LayoutProcessorStrategy.EXT_FACE, LayoutProcessorStrategy.EXPANSION_CYCLE,
                    LayoutProcessorStrategy.FULL_ANGLE),
            // Before Phase 4
            EnumSet.of(LayoutProcessorStrategy.FULL_ANGLE_REMOVER),
            // After Phase 4
            null);

    // --------------------------------- Methods --------------------------------------------------

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingStrategy(final PGraph pgraph) {
        return new IntermediateProcessingConfiguration(INTERMEDIATE_PROCESSING_CONFIGURATION);
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
        this.orthogonal = new OrthogonalRepresentation();

        // Create flow network and solve it
        PGraph network = createFlowNetwork();
        new SuccessiveShortestPathFlowSolver().calcFlow(network);

        // special cutedge handling
        // handlingCutEdges(network);

        // compute bends and angles
        computeBends(network);
        computeAngles(network);
        pgraph.setProperty(Properties.ORTHO_REPRESENTATION, this.orthogonal);
        if (graph.getProperty(LayoutOptions.DEBUG_MODE)) {
            testOrthoRep();
        }
        getMonitor().done();
    }

    /**
     * @param network
     */
    private void handlingCutEdges(final PGraph network) {

        // internal faces containing at least one cutedge
        Map<PFace, PEdge> cutEdgeFaces = Maps.newHashMap();

        // search for a cutEdge within an internal face
        PFace externalFace = this.graph.getExternalFace();
        for (PEdge edge : this.graph.adjacentEdges()) {
            PFace leftFace = edge.getLeftFace();
            if (leftFace == edge.getRightFace() && externalFace != leftFace) {
                cutEdgeFaces.put(leftFace, edge);
            }
        }

        // increase flow along the an non cutedge of the face such that there is one more bend point
        // on the edge

    }

    /**
     * Create the flow network base upon the graph. The flow network contains a source node for
     * every node in the graph and a sink node for every face. It contains an arc for every node and
     * every face adjacent to a face in the graph. The minimum cost flow problem on the resulting
     * network describe the bend minimizing problem on the graph.
     * 
     * @return the flow network to compute the minimal number of bends
     */
    private PGraph createFlowNetwork() {
        PGraph network = PGraphFactory.createEmptyGraph();
        network.setProperty(Properties.ORIGIN, this.graph);
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
            newnode.setProperty(Properties.NETWORK_TO_GRAPH, node);
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
            newnode.setProperty(Properties.NETWORK_TO_GRAPH, face);
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
            // Same face needs no arc.
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

            // Expansion cycle faces should not contain any bendpoints at their bounding edges.
            // Thus, there are no arcs for that faces in the flow network.
            if (left.getProperty(Properties.EXPANSION_CYCLE_FACE) != null
                    || right.getProperty(Properties.EXPANSION_CYCLE_FACE) != null) {
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
     * Compute bend points on the flow of the flow network.
     * 
     * @param network
     */
    private void computeBends(final PGraph network) {
        // Flow in face arcs define bends in edges
        for (Pair<PEdge, PEdge> pair : this.faceArcs) {
            PFace face1 = (PFace) pair.getFirst().getSource()
                    .getProperty(Properties.NETWORK_TO_GRAPH);
            if (face1 != pair.getSecond().getTarget().getProperty(Properties.NETWORK_TO_GRAPH)) {
                throw new InconsistentGraphModelException(
                        "The flow network has not been build correctly.");
            }
            PFace face2 = (PFace) pair.getSecond().getSource()
                    .getProperty(Properties.NETWORK_TO_GRAPH);
            if (face2 != pair.getFirst().getTarget().getProperty(Properties.NETWORK_TO_GRAPH)) {
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
                        this.orthogonal.setBends(edge, bends1);
                    } else if ((face1 == edge.getLeftFace()) && (face2 == edge.getRightFace())) {
                        this.orthogonal.setBends(edge, bends2);
                    } else {
                        throw new InconsistentGraphModelException(
                                "The flow network has not been build correctly.");
                    }
                } else if (this.orthogonal.getBends(edge) == null) {
                    this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
                }
            }
        }
    }

    /**
     * Compute edge angles based on flow in flow network. Flow in node arcs define angles in nodes.
     * 
     * @return an orthogonal representation encoding the graph shape.
     */
    private void computeAngles(final PGraph network) {

        for (PEdge arc : this.nodeArcs) {
            PNode node = (PNode) arc.getSource().getProperty(Properties.NETWORK_TO_GRAPH);
            PFace face = (PFace) arc.getTarget().getProperty(Properties.NETWORK_TO_GRAPH);

            List<Pair<PEdge, OrthogonalAngle>> nodeList = this.orthogonal.getAngles(node);
            if (nodeList == null) {
                nodeList = Lists.newLinkedList();
                for (PEdge edge : node.adjacentEdges()) {
                    nodeList.add(new Pair<PEdge, OrthogonalAngle>(edge, null));
                }
                this.orthogonal.setAngles(node, nodeList);
            }

            // Map normally calculated flow of arc to the corresponding edge angle.
            for (Pair<PEdge, OrthogonalAngle> pair : nodeList) {
                PEdge edge = pair.getFirst();
                if (((node == edge.getSource()) && (face == edge.getRightFace()))
                        || ((node == edge.getTarget()) && (face == edge.getLeftFace()))) {
                    int angle = arc.getProperty(IFlowNetworkSolver.FLOW) - 1;
                    pair.setSecond(OrthogonalAngle.map(angle));
                    break;
                }
            }
        }

        // Cutedges are only count once by the flow network, but we need to set it two times, so
        // that in some cases maximal one angle per angle-data is null. Hence, we need a post
        // processing step, that complete the angle data, depending on the before calculated.
        Collection<PNode> nodes = graph.getNodes();
        for (PNode node : nodes) {
            List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(node);
            Pair<PEdge, OrthogonalAngle> missingAnglePair = null;
            int missingCounter = 0;
            for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                if (pair.getSecond() == null) {
                    missingAnglePair = pair;
                    missingCounter++;
                }

            }

            if (missingAnglePair != null) {
                if (missingCounter > 1) {
                    // 4 edges -> every edge needs a left angle.
                    switch (angles.size()) {
                    case SUM_OF_ANGLES:
                        for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                            pair.setSecond(OrthogonalAngle.LEFT);
                        }
                        break;
                    // SUPPRESS CHECKSTYLE NEXT MagicNumber
                    case 3:
                        Pair<PEdge, OrthogonalAngle> knownAnglePair = null;
                        for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                            if (pair.getSecond() != null) {
                                knownAnglePair = pair;
                                break;
                            }
                        }
                        if (knownAnglePair == null) {
                            throw new IllegalStateException(
                                    "TamassiaOrthogonalizer, computeAngles(): the "
                                            + "object knownAnglePair must not be null.");
                        }
                        if (knownAnglePair.getSecond() == OrthogonalAngle.STRAIGHT) {
                            // set all others to left because 4 - 2 = 2, a left angle for the
                            // remaining angle-data.
                            for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                                if (pair.getSecond() == null) {
                                    pair.setSecond(OrthogonalAngle.LEFT);
                                }
                            }
                        } else {
                            // the one has to have a left angle.
                            Pair<PEdge, OrthogonalAngle> unknownAnglePair = null;
                            Pair<PEdge, OrthogonalAngle> unknownAnglePair2 = null;

                            for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                                if (pair.getSecond() == null) {
                                    if (unknownAnglePair != null) {
                                        unknownAnglePair2 = pair;
                                    } else {
                                        unknownAnglePair = pair;
                                    }
                                }
                            }
                            // more or less arbitrary set.
                            unknownAnglePair.setSecond(OrthogonalAngle.LEFT);
                            unknownAnglePair2.setSecond(OrthogonalAngle.STRAIGHT);
                        }

                        break;
                    default:
                        throw new IllegalStateException("TamassiaOrthogonalizer, computeAngles(): "
                                + "Missing angles may only occure if the sum "
                                + "of angles around a node" + "is 3 or 4 but, it is "
                                + angles.size() + "!");
                    }
                    continue;
                } else {
                    // count all other angles and set 360° - their angles to the missing entry.
                    int angleCounter = 0;
                    for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                        if (pair != missingAnglePair) {
                            angleCounter += pair.getSecond().ordinal() + 1;
                        }

                    }
                    missingAnglePair.setSecond(OrthogonalAngle
                            .map(SUM_OF_ANGLES - angleCounter - 1));
                }
            }

            if (angles.size() == 1) {
                Pair<PEdge, OrthogonalAngle> pair = angles.get(0);
                pair.setSecond(OrthogonalAngle.FULL);
            }

        }
    }

    /**
     * Checks the orthogoanalization of correctness, meaning if the sum of all angles is equal to
     * the the constant SUM_OF_ANGLES = 4 (360 degree).
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

}
