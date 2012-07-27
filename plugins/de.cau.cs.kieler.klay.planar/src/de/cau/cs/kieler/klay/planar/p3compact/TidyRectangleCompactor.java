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
import de.cau.cs.kieler.core.util.Pair;
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
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
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

    /** The orthogonal representation of the graph. */
    private OrthogonalRepresentation orthogonal;

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
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

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

    // TODO make it faster: have a look at bendpoints should be enough!
    // TODO needs a check: does this work for all examples?
    // the following example does not work with these method, because
    // it is not enough to check only for the edges of bendpoints.
    //
    // f1
    // x -- x
    // | f2 |
    // x -- x
    // SOLUTION: this doesn't matter which face you choose because both have the same edge
    /**
     * To filter the external face it is enough to check, if all bend-nodes only have two edges of a
     * face. Because of the invariant, that all faces are rectangles is that sufficient. If so,
     * we've found the external face.
     * 
     * @return
     */
    private void findExternalFace() {
        for (PFace currentFace : graph.getFaces()) {

            // choose a arbitrary edge of the face.
            PEdge startEdge = currentFace.adjacentEdges().iterator().next();
            PEdge currentEdge = startEdge;

            // needed for avoid duplicate direction searches.
            // if a edge has a node as target and a other edge the node as source there can
            // become confusion. This variables helps the wrong directions.
            PNode previousNode = null;
            PNode currentNode = null;
            List<Pair<PEdge, OrthogonalAngle>> angles;
            boolean finish = false;
            boolean isExternal = true;
            while (!finish) {
                // choose a arbitrary node of the edge, which is not visited before.
                currentNode = currentEdge.getTarget() == previousNode ? currentEdge.getSource()
                        : currentEdge.getTarget();
                previousNode = currentNode;
                angles = this.orthogonal.getAngles(currentNode);
                // first get the current edge to determine the direction of the next edge,
                // if the next edge is a face edge handle edge convenient,

                int currentIndex = -1;
                int previousIndex = -1;

                // find the currentEdge and store the index.
                for (int i = 0; i < angles.size(); i++) {
                    if (angles.get(i).getFirst() == currentEdge) {
                        currentIndex = i;
                        break;
                    }
                }
                // filter next edge of the face.
                boolean otherface = false;
                int directionCounter = 0;

                Pair<PEdge, OrthogonalAngle> pair;
                while (true) {
                    previousIndex = currentIndex;
                    currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
                    pair = angles.get(currentIndex);
                    if (!otherface && currentFace.isAdjacent(pair.getFirst())) {
                        // TODO is the currentEdge a bend edge, use the previous index to filter it
                        currentEdge = pair.getFirst();
                        if (angles.get(previousIndex).getSecond() != OrthogonalAngle.STRAIGHT) {
                            if (currentNode.getAdjacentEdgeCount() > 2) {
                                finish = true;
                                isExternal = false;
                            }
                        }
                        break;
                    } else {
                        otherface = true;
                        if (angles.get(previousIndex).getSecond() == OrthogonalAngle.STRAIGHT) {
                            directionCounter += 2;
                        } else {
                            // right is not possible, because than the first if statement would
                            // used!
                            directionCounter += 1;
                        }

                        if (currentFace.isAdjacent(pair.getFirst())) {
                            if (directionCounter == 2) {
                                // straight line, go along this edge
                                currentEdge = pair.getFirst();

                            } else {
                                // then there is a bendpoint with more than 2 edges,
                                // and the external face has only bendpoints with 2 edges.
                                finish = true;
                                isExternal = false;
                            }
                            break;
                        }
                    }
                }

                if (currentEdge == startEdge) {
                    finish = true;
                }
            }
            if (isExternal) {
                this.graph.setExternalFace(currentFace);
                this.externalFace = currentFace;
                break;
            }
        }
    }
}
