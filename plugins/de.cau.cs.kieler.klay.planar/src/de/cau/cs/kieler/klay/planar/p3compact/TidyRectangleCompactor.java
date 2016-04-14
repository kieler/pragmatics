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
package de.cau.cs.kieler.klay.planar.p3compact;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.properties.Property;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SimpleFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.planar.pathfinding.IPathFinder;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A compaction algorithm that minimizes the length of horizontal and vertical edge segments
 * separately. It only works on simple orthogonal representations, i.e. orthogonal representation
 * where every face is represented as a rectangle. General orthogonal representations have to be
 * reduced to a simple one prior to performing this algorithm. This implementation is based on the
 * chapter 5.4 of the Graph Drawing book of Di Battista, Eades, Tamassia and Tollis.
 * 
 * @author pkl
 *  @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public class TidyRectangleCompactor implements ILayoutPhase {

    // ======================== Constants ==========================================================

    /** indices of facesides: 0 for left, 1 for top, 2 for right and 3 for bottom. */
    private static final int FACE_SIDES_NUMBER = 4;

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORKTOGRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private PGraph graph;

    /** The external face of the current graph structure. */
    private PFace externalFace;

    /** The source node of the flow network during this algorithm. */
    private PNode source;

    /** The sink node of the flow network during this algorithm. */
    private PNode sink;

    /** Intermediate processing configuration with Quod high-degree strategy. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION_QUOD 
        = new IntermediateProcessingConfiguration(
            // Before Phase 1
            null,
            // Before Phase 2
            null,
            // Before Phase 3
            null,
            // Before Phase 4
            EnumSet.of(IntermediateProcessorStrategy.BEND_DUMMY,
                    IntermediateProcessorStrategy.RECT_SHAPE_DUMMY,
                    IntermediateProcessorStrategy.FACE_SIDES),
            // After Phase 4
            EnumSet.of(IntermediateProcessorStrategy.GRID_DRAWING,
                    IntermediateProcessorStrategy.RECT_SHAPE_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.BEND_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.QUOD_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.PLANAR_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.SELFLOOP_DUMMY_REMOVER));

    /** Intermediate processing configuration with Giotto high-degree strategy. */
    private static final IntermediateProcessingConfiguration 
        INTERMEDIATE_PROCESSING_CONFIGURATION_GIOTTO 
        = new IntermediateProcessingConfiguration(
            // Before Phase 1
            null,
            // Before Phase 2
            null,
            // Before Phase 3
            null,
            // Before Phase 4
            EnumSet.of(IntermediateProcessorStrategy.BEND_DUMMY,
                    IntermediateProcessorStrategy.RECT_SHAPE_DUMMY,
                    IntermediateProcessorStrategy.FACE_SIDES),
            // After Phase 4
            EnumSet.of(IntermediateProcessorStrategy.GRID_DRAWING,
                    IntermediateProcessorStrategy.RECT_SHAPE_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.BEND_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.GIOTTO_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.PLANAR_DUMMY_REMOVER,
                    IntermediateProcessorStrategy.SELFLOOP_DUMMY_REMOVER));

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingStrategy(final PGraph pgraph) {

        // check which high-degree node algorithm should be used
        if (pgraph.getProperty(Properties.HIGH_DEGREE_NODE_STRATEGY) == HighDegreeNodeStrategy.GIOTTO) {
            return new IntermediateProcessingConfiguration(
                    INTERMEDIATE_PROCESSING_CONFIGURATION_GIOTTO);
        } else {
            return new IntermediateProcessingConfiguration(
                    INTERMEDIATE_PROCESSING_CONFIGURATION_QUOD);
        }

    }

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph, final IElkProgressMonitor monitor) {
        monitor.begin("Tidy rectangle compaction", 3);  // SUPPRESS CHECKSTYLE MagicNumber

        this.graph = pgraph;

        // Used to create the flownetwork
        this.externalFace = pgraph.getExternalFace();
        // Create networks, start with side 0 for horizontal and 1 for vertical.
        IFlowNetworkSolver solver = new SimpleFlowSolver();

        // Side 0 is the left face side, thus it is vertical.
        PGraph verticalNetwork = createFlowNetwork(0);
        solver.calcFlow(verticalNetwork, monitor.subTask(1));
        // Assign edge length based on flow of the flow network
        addFlowAsLength(verticalNetwork);

        // Side 1 is the top face side, thus it is horizontal.
        PGraph horizontalNetwork = createFlowNetwork(1);
        solver.calcFlow(horizontalNetwork, monitor.subTask(1));
        // Assign edge length based on flow of the flow network
        addFlowAsLength(horizontalNetwork);

        monitor.done();
    }

    /**
     * Maps the flow of the flow network to the edges of the original graph.
     * 
     * @param network
     *            containing edge flow
     * @param isHorizontal
     *            determines whether the horizontal (true) or vertical (false) edge lengths of the
     *            graph are to be calculated
     * 
     */
    private void addFlowAsLength(final PGraph network) {

        for (PEdge arc : network.getEdges()) {
            PEdge sourceEdge = ((PEdge) arc.getProperty(NETWORKTOGRAPH));
            sourceEdge.setProperty(Properties.RELATIVE_LENGTH,
                    arc.getProperty(IFlowNetworkSolver.FLOW));
        }

    }

    /**
     * Creates the flow network. Create for all faces of the original graph nodes two additional
     * nodes that are the source and the sink of the flow network. Depending on the direction
     * (horizontal or vertical) the method generates edges from source to target over the
     * face-nodes.
     * 
     * @param startSide
     *            the beginning face side index
     * @return PGraph the resulting flownetwork
     */
    private PGraph createFlowNetwork(final int startSide) {
        PGraph flowNetwork = PGraphFactory.createEmptyGraph();

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
        // Is needed to check if an edge already exists to the target.
        Set<PFace> visited = Sets.newHashSet();

        PFace targetFace = null;

        // Doing a loopstep for the first face-side. Afterwards the while loop is used.
        // Creates edges for consecutive face-nodes.
        for (PEdge edge : currentSide) {
            targetFace = edge.getLeftFace() != currentFace ? edge.getLeftFace() : edge
                    .getRightFace();
            PEdge newEdge = flowNetwork.addEdge(source, faceMap.get(targetFace), true);
            newEdge.setProperty(NETWORKTOGRAPH, edge);
            sinks.put(targetFace, edge);
        }
        completedFaces.add(currentFace);
        // Choose new sourceFace
        for (PFace face : sinks.keySet()) {
            if (!completedFaces.contains(face)) {
                currentFace = face;
                currentSide = findOppositeEdges(currentFace, sinks.get(currentFace));
                break;
            }
        }

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
     * Gives the opposite edges of an edge in a face. Example: If the given edge is on the left side
     * of a face the result is a list of edges of the right side of the face. Attention: This works
     * only for faces in rectangular shape, otherwise it would make no sense to use this method.
     * 
     * @param face
     *            the surrounding face
     * @param edge
     *            the edge for which it should be the opposite found
     * @return the edges of the other face-side
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
