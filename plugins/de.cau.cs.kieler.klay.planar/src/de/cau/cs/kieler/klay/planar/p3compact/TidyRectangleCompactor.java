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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.flownetwork.IFlowNetworkSolver;
import de.cau.cs.kieler.klay.planar.flownetwork.SuccessiveShortestPathFlowSolver;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * A compaction algorithm that minimizes the length of horizontal and vertical edge segments
 * separately. It only works on simple orthogonal representations, i.e. orthogonal representation
 * where every face is represented as a rectangle. General orthogonal representations have to
 * reduced to a simple one prior to performing this algorithm. These compaction step results from
 * the chapter 5.4 of the Graph Drawing book of Di Battista, Eades, Tamassia and Tollis.
 * 
 * @author ocl
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

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph pgraph) {
        IntermediateProcessingStrategy strategy = new IntermediateProcessingStrategy();
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

        if (!checkPremise()) {
            // TODO think about: the input graph has to have at least 4 nodes, otherwise
            // it would not make any sense to do the flownetwork step.
            // Then it would be meaningful to set the edge-sizes to the same value.
            // x -- x -- x
            // Think about other exceptions and try to work on them.
            assignSimpleCooridnates();
            return;
        }

        // used to create the flownetwork
        findExternalFace();

        // helps to create the flow network
        defineFaceSideEdges();
        // Create networks, start with side 0 for horizontal and 1 for vertical.
        PGraph horizontalNetwork = createFlowNetwork(0);
        PGraph verticalNetwork = createFlowNetwork(1);
        IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        // solver.findFlow(horizontalNetwork);
        // solver.findFlow(networks.getSecond());
        // TODO Assign coordinates based on flow

    }

    /**
     * 
     */
    private void assignSimpleCooridnates() {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * @return
     */
    private boolean checkPremise() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Creates the flownetwork. Create for all faces of the origin-graph nodes and to nodes for the
     * external-face. These two nodes are source and sink of the flownetwork. Depending on the
     * direction (horizontal or vertical) the method generates edges from source to target over the
     * face-nodes.
     * 
     * @param startSide
     * @return PGraph, the resulting flownetwork
     */
    private PGraph createFlowNetwork(final int startSide) {
        PGraph flowNetwork = new PGraphFactory().createEmptyGraph();

        // Face-map to store all nodes of a face.
        Map<PFace, PNode> faceMap = new HashMap<PFace, PNode>();

        // Create nodes for every graph face
        for (PFace face : this.graph.getFaces()) {
            if (face != this.externalFace) {
                PNode newnode;
                newnode = flowNetwork.addNode();
                newnode.setProperty(NETWORKTOGRAPH, face);
                faceMap.put(face, newnode);
            }
        }

        // A source node for the external face.
        // Attention: double key problem, therefore the first iteration is beyond the while loop.
        PNode source = flowNetwork.addNode();
        source.setProperty(NETWORKTOGRAPH, this.externalFace);

        // A target node for the external face.
        PNode target = flowNetwork.addNode();
        target.setProperty(NETWORKTOGRAPH, this.externalFace);
        faceMap.put(this.externalFace, target);

        PFace currentFace = this.externalFace;
        @SuppressWarnings("unchecked")
        List<PEdge> currentSide = currentFace.getProperty(Properties.FACE_SIDES)[startSide];

        // Start to build up the flowNetwork
        // beginning at the bottom to the top.

        // After all outgoing edges of a face are set, the face is added to completedFaces.
        List<PFace> completedFaces = new ArrayList<PFace>();

        // Contains the edge over which the sink has been found.
        // Is needed to store all faces/nodes that have been visited from any face.
        Map<PFace, PEdge> sinks = new HashMap<PFace, PEdge>();

        // Store the visited target-nodes / faces from the current face/node.
        // Is needed to check if a edge already exists to the target.
        List<PFace> visited = new LinkedList<PFace>();

        PFace targetFace = null;

        // --------------------------------------------------------------------------------------------
        // Doing a loopstep for the first face-side. Afterwards the while loop is used.
        // Creates edges for consecutive face-nodes.
        for (PEdge edge : currentSide) {
            targetFace = edge.getLeftFace() != currentFace ? edge.getLeftFace() : edge
                    .getRightFace();
            if (!visited.contains(targetFace)) {
                flowNetwork.addEdge(source, faceMap.get(targetFace), true);
                visited.add(targetFace);
                sinks.put(targetFace, edge);
            }
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
                if (!visited.contains(targetFace)) {
                    flowNetwork.addEdge(faceMap.get(currentFace), faceMap.get(targetFace), true);
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
        return flowNetwork;
    }

    // TODO needs a check, if it works for all possible input variants.
    /**
     * This method iterates over all faces of the graph and stores them in the left, top, right and
     * bottom side. This can be used, to determine how long a edge has to be. Meaning the opposite
     * edges have to have the same length. This works because of the rectangular shape of the input
     * faces. Attention: This works only for graphs with rectangular face-shapes.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void defineFaceSideEdges() {

        for (PFace face : graph.getFaces()) {
            // 0 for left, 1 for top, 2 for right, 3 for bottom.
            List[] faceSides = new ArrayList[FACE_SIDES_NUMBER];

            for (int i = 0; i < faceSides.length; i++) {
                faceSides[i] = new ArrayList<PEdge>();
            }

            int listPointer = 0;

            // Choose a arbitrary edge of the face.
            // It is enough to store the edge on the face sides in arbitrary order,
            // here are no dependency to other faces stored. E.g. it is possible, that
            // e1 at f1 is on the left but the same edge e1 at f2 on the top.
            PEdge startEdge = face.adjacentEdges().iterator().next();
            // The currently processed edge, starting with a arbitrary chosen one.
            PEdge currentEdge = startEdge;

            PNode previousNode = null;
            PNode currentNode = null;

            // starts with the startEdge and runs around the chosen face until the startEdge has
            // found. Then all edges of the face are set to a face-side.
            do {
                // choose a arbitrary node of the edge, which is not visited before.
                currentNode = currentEdge.getTarget() == previousNode ? currentEdge.getSource()
                        : currentEdge.getTarget();
                previousNode = currentNode;
                List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(currentNode);
                // first get the current edge to determine the direction of the next edge,
                // if the next edge a face edge handle edge convenient,
                int currentIndex = -1;
                int previousIndex = -1;

                // find the currentEdge and store the index.
                for (int i = 0; i < angles.size(); i++) {
                    if (angles.get(i).getFirst().equals(currentEdge)) {
                        currentIndex = i;
                        break;
                    }
                }

                int directionCounter = 0;
                boolean otherface = false;
                Pair<PEdge, OrthogonalAngle> pair = null;
                // Goes around a node and checks if a face-edge is straight to an other face-edge
                // or if there is a face-side change. Thereby have to regard the other node-edges
                // and that's why there is a direction-counter (1 for left, 2 for straight and 3 for
                // right), although left and right are both count as knee. That is possible, because
                // of the rectangular face-shape.

                while (true) {
                    previousIndex = currentIndex;
                    currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
                    pair = angles.get(currentIndex);

                    if (!otherface && face.isAdjacent(pair.getFirst())) {
                        currentEdge = pair.getFirst();
                        if (angles.get(previousIndex).getSecond() != OrthogonalAngle.STRAIGHT) {
                            listPointer = (listPointer == faceSides.length - 1) ? 0
                                    : (listPointer + 1);
                        }
                        faceSides[listPointer].add(currentEdge);
                        // hasFound
                        break;
                    } else {
                        otherface = true;
                        // look at the direction of the previous edge to determine the direction
                        if (angles.get(previousIndex).getSecond() == OrthogonalAngle.STRAIGHT) {
                            directionCounter += 2;
                        } else {
                            directionCounter += 1;
                        }
                        if (face.isAdjacent(pair.getFirst())) {
                            currentEdge = pair.getFirst();
                            if (directionCounter != 2) {
                                listPointer = (listPointer == faceSides.length - 1) ? 0
                                        : (listPointer + 1);
                            }
                            faceSides[listPointer].add(currentEdge);
                            // hasFound
                            break;
                        }
                    }
                }
            } while (currentEdge == startEdge);

            // put face-sides to the current face.
            face.setProperty(Properties.FACE_SIDES, faceSides);
        }

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
        @SuppressWarnings("unchecked")
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

    // TODO make a faster: have a look at bendpoints should be enough!
    // TODO needs a check: does this work for all examples?
    // the following example does not work with these method, because
    // it is not enough to check only for the edges of bendpoints.
    //
    // f1
    // x -- x
    // | f2 |
    // x -- x

    /**
     * To filter the external face it is enough to check, if all bend-nodes only have two edges of a
     * face. Because of the invariant, that all faces are rectangles is that sufficient. If so,
     * we've found the external face.
     * 
     * @return
     */
    private void findExternalFace() {
        for (PFace face : graph.getFaces()) {

            // store the visited edges, this is needed for uniqueness in the facesides.
            List<PEdge> visitedEdges = new ArrayList<PEdge>();
            // choose a arbitrary edge of the face.
            PEdge startEdge = face.adjacentEdges().iterator().next();
            PEdge currentEdge = startEdge;

            // needed for avoid duplicate direction searches.
            // if a edge has a node as target and a other edge the node as source there can
            // become confusion. This variables helps the wrong directions.
            PNode previousNode = null;
            boolean finish = false;
            boolean isExternal = true;
            while (!finish) {
                // choose a arbitrary node of the edge, which is not visited before.
                PNode currentNode = currentEdge.getTarget() == previousNode ? currentEdge
                        .getSource() : currentEdge.getTarget();
                previousNode = currentNode;
                List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(currentNode);
                // first get the current edge to determine the direction of the next edge,
                // if the next edge a face edge handle edge convenient,
                int currentIndex = -1;
                int previousIndex = -1;

                // find the currentEdge and store the index.
                for (int i = 0; i < angles.size(); i++) {
                    if (angles.get(i).getFirst().equals(currentEdge)) {
                        currentIndex = i;
                        break;
                    }
                }
                // filter next edge of the face.
                boolean hasFound = false;
                while (!hasFound) {
                    previousIndex = currentIndex;
                    currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
                    Pair<PEdge, OrthogonalAngle> pair = angles.get(currentIndex);
                    if (face.isAdjacent(pair.getFirst()) && !visitedEdges.contains(pair.getFirst())) {
                        currentEdge = pair.getFirst();
                        // look at the direction of the previous edge to determine the direction
                        switch (angles.get(previousIndex).getSecond()) {
                        case STRAIGHT:
                            break;
                        default:
                            // can only be right or left and doesn`t matter which of them, because
                            // of the invariant of rectangle shape faces.
                            // if the direction isn't straight, there is a faceside change!
                            if (currentNode.getAdjacentEdgeCount() > 2) {
                                finish = true;
                                isExternal = false;
                            }
                            break;
                        }
                        visitedEdges.add(currentEdge);
                        System.out.println(currentEdge);
                        hasFound = true;
                    }

                }

                if (currentEdge == startEdge) {
                    finish = true;
                }
            }
            if (isExternal) {
                face.setProperty(Properties.IS_EXTERNAL, true);
                this.externalFace = face;
                break;
            }
        }
    }

}
