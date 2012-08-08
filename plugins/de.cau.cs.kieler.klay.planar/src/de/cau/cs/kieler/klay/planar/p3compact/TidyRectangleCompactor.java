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
package de.cau.cs.kieler.klay.planar.p3compact;

import java.util.List;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SimpleFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * A compaction algorithm that minimizes the length of horizontal and vertical edge segments
 * separately. It only works on simple orthogonal representations, i.e. orthogonal representation
 * where every face is represented as a rectangle. General orthogonal representations have to
 * reduced to a simple one prior to performing this algorithm. These compaction step results from
 * the chapter 5.4 of the Graph Drawing book of Di Battista, Eades, Tamassia and Tollis.
 * 
 * @author pkl
 */
public class TidyRectangleCompactor extends AbstractAlgorithm implements ILayoutPhase {

    // ======================== Constants ==========================================================

    /** indices of facesides: 0 for left, 1 for top, 2 for right and 3 for bottom. */
    private static final int FACE_SIDES_NUMBER = 4;

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORKTOGRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private PGraph graph;

    private PFace externalFace;

    private PNode source;

    private PNode sink;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph pgraph) {
        IntermediateProcessingStrategy strategy = new IntermediateProcessingStrategy();
        strategy.addLayoutProcessor(IntermediateProcessingStrategy.BEFORE_PHASE_4,
                IntermediateLayoutProcessor.RECT_SHAPE);
        strategy.addLayoutProcessor(IntermediateProcessingStrategy.AFTER_PHASE_4,
                IntermediateLayoutProcessor.GRID_DRAWING);
        strategy.addLayoutProcessor(IntermediateProcessingStrategy.AFTER_PHASE_4,
                IntermediateLayoutProcessor.DUMMYNODE_REMOVING_PROCESSOR);
        return strategy;
    }

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {

        this.graph = pgraph;
        // FIXME think about the deletion of the orthogonal representation and put it instead
        // on the graph direct and the bend-point nodes can be marked with a
        // it is definitively the better way. But give a info at the docu what happens with
        // the orthogonal representation of the book!!!

            // TODO think about: the input graph has to have at least 4 nodes, otherwise
            // it would not make any sense to do the flownetwork step.
            // Then it would be meaningful to set the edge-sizes to the same value.
            // x -- x -- x
            // Think about other exceptions and try to work on them.

            // used to create the flownetwork
            // findExternalFace();
            this.externalFace = pgraph.getExternalFace();
            // helps to create the flow network
            PUtil.defineFaceSideEdges(graph);
            // Create networks, start with side 0 for horizontal and 1 for vertical.
            IFlowNetworkSolver solver = new SimpleFlowSolver();

            // side 0 is the left face side, thus it is vertical.
            PGraph verticalNetwork = createFlowNetwork(0);
            solver.findFlow(verticalNetwork);
            addFlowAsLength(verticalNetwork);

            // side 1 is the top face side, thus it is horizontal.
            PGraph horizontalNetwork = createFlowNetwork(1);
            solver.findFlow(horizontalNetwork);
            addFlowAsLength(horizontalNetwork);
            // Assign coordinates based on flow
            // filter edges meaning using the horizontal and vertical segments to
            // determine the edge size.
            // faceside
    }

    /**
     * Maps the flow of the flow network to the edges of the original graph.
     * 
     * @param network
     *            , containing edge flow
     * @param isHorizontal
     *            , meaning if the horizontal edge length of a graph has to determine, otherwise
     *            vertical is assumed. And the vertical edge length of the original graph are
     *            determined.
     * 
     */
    private void addFlowAsLength(final PGraph network) {

        for (PEdge edge : network.getEdges()) {
            PEdge sourceEdge = ((PEdge) edge.getProperty(NETWORKTOGRAPH));
            sourceEdge.setProperty(Properties.RELATIVE_LENGTH,
                    edge.getProperty(IFlowNetworkSolver.FLOW));
        }

    }

    /**
     * Creates the flow network. Create for all faces of the original graph nodes additionally two
     * nodes are source and sink of the flow network. Depending on the direction (horizontal or
     * vertical) the method generates edges from source to target over the face-nodes.
     * 
     * @param startSide
     * @return PGraph, the resulting flownetwork
     */
    private PGraph createFlowNetwork(final int startSide) {
        PGraph flowNetwork = new PGraphFactory().createEmptyGraph();

        BiMap<PFace, PNode> faceMap = HashBiMap.create();

        // Create nodes for every graph face
        for (PFace face : this.graph.getFaces()) {
            if (face != this.externalFace) {
                PNode newNode;
                newNode = flowNetwork.addNode();
                newNode.setProperty(NETWORKTOGRAPH, face);
                newNode.setProperty(IFlowNetworkSolver.SUPPLY, 0);
                faceMap.put(face, newNode);
            }
        }

        // we can't put the source to the face map, because the source and sink node points
        // to the same value namely the external face!
        this.source = flowNetwork.addNode();
        this.source.setProperty(NETWORKTOGRAPH, this.externalFace);
        this.source.setProperty(IFlowNetworkSolver.SUPPLY, 1);

        this.sink = flowNetwork.addNode();
        this.sink.setProperty(NETWORKTOGRAPH, this.externalFace);
        this.sink.setProperty(IFlowNetworkSolver.SUPPLY, -1);
        faceMap.put(this.externalFace, this.sink);

        PFace currentFace = this.externalFace;
        List<PEdge> currentSide = currentFace.getProperty(Properties.FACE_SIDES)[startSide];

        // Start to build up the flowNetwork
        // beginning at the bottom to the top.

        // After all outgoing edges of a face are set, the face is added to completedFaces.
        List<PFace> completedFaces = Lists.newLinkedList();

        // Contains the edge over which the sink has been found.
        // Is needed to store all faces/nodes that have been visited from any face.
        Map<PFace, PEdge> sinks = Maps.newHashMap();

        // Store the visited target-nodes / faces from the current face/node.
        // Is needed to check if a edge already exists to the target.
        List<PFace> visited = Lists.newLinkedList();

        PFace targetFace = null;

        // --------------------------------------------------------------------------------------------
        // Doing a loopstep for the first face-side. Afterwards the while loop is used.
        // Creates edges for consecutive face-nodes.
        for (PEdge edge : currentSide) {
            targetFace = edge.getLeftFace() != currentFace ? edge.getLeftFace() : edge
                    .getRightFace();
            PEdge newEdge = flowNetwork.addEdge(source, faceMap.get(targetFace), true);
            newEdge.setProperty(NETWORKTOGRAPH, edge);
            if (!visited.contains(targetFace)) {
                visited.add(targetFace);
            }
            sinks.put(targetFace, edge);

        }
        visited.clear();
        completedFaces.add(currentFace);
        // Choose new sourceFace
        for (PFace face : sinks.keySet()) {
            if (!completedFaces.contains(face)) {
                currentFace = face;
                currentSide = findOppositeEdges(currentFace, sinks.get(currentFace));
                break;
            }
        }

        // --------------------------------------------------------------------------------------------
        // Traverse the graph by running through the faces and join consecutive face by an edge.
        // two faces are consecutive if they share a horizontal or vertical edge.

        boolean isRunning = true;
        while (isRunning) {
            for (PEdge edge : currentSide) {
                targetFace = edge.getLeftFace() != currentFace ? edge.getLeftFace() : edge
                        .getRightFace();
                PEdge newEdge = flowNetwork.addEdge(faceMap.get(currentFace),
                        faceMap.get(targetFace), true);
                newEdge.setProperty(NETWORKTOGRAPH, edge);
                if (!visited.contains(targetFace)) {
                    visited.add(targetFace);
                    if (!sinks.containsKey(targetFace)) {
                        sinks.put(targetFace, edge);
                    }
                }
            }
            visited.clear();
            completedFaces.add(currentFace);
            isRunning = false;
            // choose new sourceFace
            for (PFace face : sinks.keySet()) {
                if (!completedFaces.contains(face)) {
                    currentFace = face;
                    currentSide = findOppositeEdges(currentFace, sinks.get(currentFace));
                    isRunning = true;
                    break;
                }
            }
        }

        // set networkflow arcs (edges) properties.
        for (PEdge arc : flowNetwork.getEdges()) {
            // capacity is infinite.
            arc.setProperty(IFlowNetworkSolver.CAPACITY, Integer.MAX_VALUE);
            arc.setProperty(IPathFinder.PATHCOST, 1);
            arc.setProperty(IFlowNetworkSolver.LOWER_BOUND, 1);
        }

        return flowNetwork;
    }

    /**
     * Gives the opposite edges of a edge in a face. Example: If the given edge is on the left side
     * of a face the result is a list of edges of the right side of the face. Attention: This works
     * only for faces in rectangular shape, otherwise it would make no sense to use this method.
     * 
     * @param face
     *            the surrounding face
     * @param edge
     * @return the edges of the other face-side.
     */
    private List<PEdge> findOppositeEdges(final PFace face, final PEdge edge) {
        List<PEdge>[] faceSides = face.getProperty(Properties.FACE_SIDES);
        for (int i = 0; i < faceSides.length; i++) {
            if (faceSides[i].contains(edge)) {
                // gets the opposite side of the face.
                return faceSides[(i + 2) % FACE_SIDES_NUMBER];

            }
        }
        throw new IllegalArgumentException("CompactionStep: Edge " + edge.id
                + " is not part of the face " + face.toString() + "!");
    }

}
