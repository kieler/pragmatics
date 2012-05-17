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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
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

    /** indices of facesides: 0 for left, 1 for top, 2 for right and 3 for bottom */
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
    public IntermediateProcessingStrategy getIntermediateProcessingStrategy(final PGraph graph) {
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
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);
        findExternalFace();
        defineFaceSideEdges();
        calcEdgeLength();
        // FIXME use tidy-rectangle-compact instead of own way
        // own way idea: go through every face, determine how long the left and right edges of
        // that face should be, give them the size and
        // Create networks and solve
        // PGraph horizontalNetwork = createHorizontalFlowNetwork();
        // IFlowNetworkSolver solver = new SuccessiveShortestPathFlowSolver();
        // solver.findFlow(horizontalNetwork);
        // solver.findFlow(networks.getSecond());
        // TODO Assign coordinates based on flow

    }

    /**
     * 
     */
    private void calcEdgeLength() {
        // TODO think, should we use concrete values from the graphproperties.
        // or should that step be done in the applay layout step!?!?
    }

    // TODO needs a check, if it works for all possible input variants.
    /**
     * This method iterates over all faces of the graph and stores them in the left, top, right and
     * bottom side. This can be used, to determine how long a edge has to be. Meaning the opposite
     * edges have to have the same length. This works because of the rectangular shape of the input
     * faces.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void defineFaceSideEdges() {

        for (PFace face : graph.getFaces()) {
            System.out.println("Face: " + face.id);
            // 0 for left, 1 for top, 2 for right, 3 for bottom.
            ArrayList[] faceSides = new ArrayList[FACE_SIDES_NUMBER];

            for (int i = 0; i < faceSides.length; i++) {
                faceSides[i] = new ArrayList<PEdge>();
            }

            int listPointer = 0;

            // store the visited edges, this is needed for uniqueness in the facesides.
            List<PEdge> visitedEdges = new ArrayList<PEdge>();
            // choose a arbitrary edge of the face.
            PEdge startEdge = face.adjacentEdges().iterator().next();
            PEdge currentEdge = startEdge;
            PNode previousNode = null;
            boolean finish = false;
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
                            // add the edge to the current faceSide.
                            break;
                        default:
                            // can only be right or left and doesn`t matter which of them, because
                            // of the invariant of rectangle shape faces.
                            // if the direction isn't straight, there is a faceside change!
                            listPointer = (listPointer == 3) ? 0 : (listPointer + 1);
                            break;
                        }
                        visitedEdges.add(currentEdge);
                        faceSides[listPointer].add(currentEdge);
                        System.out.println(currentEdge);
                        hasFound = true;
                    }
                }

                if (currentEdge == startEdge) {
                    finish = true;
                }
                // for (Pair<PEdge, OrthogonalAngle> pair : angles) {
                // // if (pair.getFirst().equals(startEdge)) {
                // // then we've finished the journey around the face and are able to
                // // finish = true;
                // // break;
                // // }
                // if (pair.getFirst().equals(currentEdge)) {
                // continue;
                // }
                // if (face.isAdjacent(pair.getFirst())) {
                // switch (pair.getSecond()) {
                // case STRAIGHT:
                // currentEdge = pair.getFirst();
                // faceSides[listPointer].add(currentEdge);
                // break;
                // case LEFT:
                // if (direction == null) {
                // direction = OrthogonalAngle.LEFT;
                // } else if (direction == OrthogonalAngle.RIGHT) {
                // // should be a problem check this .
                // } else if (direction == OrthogonalAngle.LEFT) {
                //
                // }
                //
                // listPointer++;
                // currentEdge = pair.getFirst();
                // faceSides[listPointer].add(currentEdge);
                // case RIGHT:
                // if (direction == null) {
                // direction = OrthogonalAngle.RIGHT;
                // } else if (direction == OrthogonalAngle.LEFT) {
                // // should be a problem check this . it might be possible if
                // // there are differences between 0,1 and 1,0 meaning first LEFT and
                // // second RIGHT is the same.
                // } else if (direction == OrthogonalAngle.RIGHT) {
                // listPointer++;
                // currentEdge = pair.getFirst();
                // faceSides[listPointer].add(currentEdge);
                // }
                // }
                // } else {
                //
                // }
                // }
                //
            }

            // put faceside to face.
            face.setProperty(Properties.FACESIDES, faceSides);
        }

    }

    /**
     * searches for the external face of the given graph.
     * 
     * @return
     */
    private void findExternalFace() {
        for (PFace face : this.graph.getFaces()) {
            // Direction for edges in a node.

            // TODO implement, at first we assume, that the face with the most edges is the
            // external face, but in general that does not hold.!!!
            if (this.externalFace == null) {
                this.externalFace = face;
            } else if (face.getAdjacentNodeCount() > externalFace.getAdjacentNodeCount()) {
                this.externalFace = face;
            }
        }

        // for(PNode node : face.adjacentNodes()){
        // if(node.getAdjacentEdgeCount() > 3){
        // // then the using face is an innerface.
        // break;
        // } else if (node.getAdjacentEdgeCount() == 3){
        //
        // } else if (node.getAdjacentEdgeCount() == 2){
        // // check for the correct direction and
        // } else if (node.getAdjacentEdgeCount() == 1){
        //
        // }
        // }
        // for (PEdge edge : face.adjacentEdges()) {
        // face.adjacentNodes()
        // orthogonal.getAngles(node)
        // if(edge)
        // }

        // }
    }

    private PGraph createHorizontalFlowNetwork() {
        PGraph horizontal = new PGraphFactory().createEmptyGraph();

        // Face-map to store all nodes of a face.
        Map<PFace, PNode> horizontalMap = new HashMap<PFace, PNode>();

        // Create nodes for every graph face
        for (PFace face : this.graph.getFaces()) {
            PNode newnode;
            newnode = horizontal.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            horizontalMap.put(face, newnode);
        }

        PNode source = horizontal.addNode();
        source.setProperty(NETWORKTOGRAPH, this.externalFace);
        horizontalMap.put(this.externalFace, source);

        PNode target = horizontal.addNode();
        target.setProperty(NETWORKTOGRAPH, this.externalFace);
        horizontalMap.put(this.externalFace, target);

        // Create arcs for horizontal edges
        // Traverse graph with DFS
        Set<PEdge> visited = new HashSet<PEdge>(this.graph.getEdgeCount() * 2);
        Stack<PEdge> edges = new Stack<PEdge>();
        Stack<Pair<Boolean, Boolean>> direction = new Stack<Pair<Boolean, Boolean>>();
        for (PEdge edge : this.graph.getEdges()) {
            if (!visited.contains(edge)) {
                edges.push(edge);
                direction.push(new Pair<Boolean, Boolean>(false, false));
                while (!edges.isEmpty()) {
                    // TODO create arcs
                    // PEdge current = edges.pop();
                    // Pair<Boolean, Boolean> dir = direction.pop();

                    List<PNode> list = new LinkedList<PNode>();
                    list.add(edge.getSource());
                    list.add(edge.getTarget());
                    for (PNode n : list) {
                        for (Pair<PEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(n)) {
                            if (!visited.contains(pair.getFirst())) {
                                edges.push(pair.getFirst());
                                // TODO determine directions
                                direction.push(new Pair<Boolean, Boolean>(false, false));
                            }
                        }
                    }
                }
            }
        }
        return horizontal;
    }

    /**
     * Create the flow networks for vertical and horizontal metrics.
     * 
     * @return the flow network
     */
    private Pair<PGraph, PGraph> createFlowNetworks() {
        PGraphFactory factory = new PGraphFactory();
        PGraph vertical = factory.createEmptyGraph();
        PGraph horizontal = factory.createEmptyGraph();

        // Face-map to store all nodes of a face.
        Map<PFace, PNode> verticalMap = new HashMap<PFace, PNode>();
        Map<PFace, PNode> horizontalMap = new HashMap<PFace, PNode>();

        // Create nodes for every graph face
        for (PFace face : this.graph.getFaces()) {
            PNode newnode;
            newnode = vertical.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            verticalMap.put(face, newnode);
            newnode = horizontal.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            horizontalMap.put(face, newnode);
        }

        // Create arcs for vertical or horizontal edges
        // Traverse graph with DFS
        Set<PEdge> visited = new HashSet<PEdge>(this.graph.getEdgeCount() * 2);
        Stack<PEdge> edges = new Stack<PEdge>();
        Stack<Pair<Boolean, Boolean>> direction = new Stack<Pair<Boolean, Boolean>>();
        for (PEdge edge : this.graph.getEdges()) {
            if (!visited.contains(edge)) {
                edges.push(edge);
                direction.push(new Pair<Boolean, Boolean>(false, false));
                while (!edges.isEmpty()) {
                    // TODO create arcs
                    // PEdge current = edges.pop();
                    // Pair<Boolean, Boolean> dir = direction.pop();

                    List<PNode> list = new LinkedList<PNode>();
                    list.add(edge.getSource());
                    list.add(edge.getTarget());
                    for (PNode n : list) {
                        for (Pair<PEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(n)) {
                            if (!visited.contains(pair.getFirst())) {
                                edges.push(pair.getFirst());
                                // TODO determine directions
                                direction.push(new Pair<Boolean, Boolean>(false, false));
                            }
                        }
                    }
                }
            }
        }

        return new Pair<PGraph, PGraph>(vertical, horizontal);
    }

}
