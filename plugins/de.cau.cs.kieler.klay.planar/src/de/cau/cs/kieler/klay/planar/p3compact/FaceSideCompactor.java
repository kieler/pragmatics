/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.p3compact;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutPhase;
import de.cau.cs.kieler.klay.planar.IntermediateProcessingStrategy;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PGraphElement;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.intermediate.IntermediateLayoutProcessor;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * It is a try of my own. The idea is to calculate the number of edges of every side of a face and
 * then calculate the edge-length depending of the face -side-edge-number.
 * 
 * @author pkl
 */
public class FaceSideCompactor extends AbstractAlgorithm implements ILayoutPhase {
    // ======================== Constants ==========================================================

    /** Indices of facesides: 0 for left, 1 for top, 2 for right and 3 for bottom. */
    private static final int FACE_SIDES_NUMBER = 4;

    /** Property to convert a node in the flow network to a node or face in the graph. */
    public static final Property<PGraphElement> NETWORKTOGRAPH = new Property<PGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private PGraph graph;

    /** The orthogonal representation of the graph. */
    private OrthogonalRepresentation orthogonal;

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
     * 
     * @param pgraph
     *            the pgraph has to be in normalized shape meaning the faces have to be in
     *            rectangular shape.
     */
    public void process(final PGraph pgraph) {
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);
        findExternalFace();
        defineFaceSideEdges();
        calcEdgeLength();
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
                            // add the edge to the current face-side.
                            break;
                        default:
                            // can only be right or left and doesn`t matter which of them, because
                            // of the invariant of rectangle shape faces.
                            // if the direction isn't straight, there is a face-side change!
                            listPointer = (listPointer == faceSides.length - 1) ? 0
                                    : (listPointer + 1);
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
            }

            // put faceside to face.
            face.setProperty(Properties.FACE_SIDES, faceSides);
        }

    }

    // TODO make a bit faster!
    // TODO needs a check: does this work for all examples?
    // TODO does it work with the current version of the face-side generation method.
    // Because a edge e1 of face 1 can be on face-side top and on face 2 on face-side right, it's
    // arbitrary!!!
    /**
     * To filter the external face it is enough to check, if all nodes which have kink (his edge
     * with 90° angle) only have two edges of a face. Because of the invariant, that all faces are
     * rectangles is that sufficient. If so, we've found the external face.
     * 
     * @return
     */
    private void findExternalFace() {
        for (PFace face : graph.getFaces()) {
            System.out.println("Face: " + face.id);

            // store the visited edges, this is needed for uniqueness in the facesides.
            List<PEdge> visitedEdges = new ArrayList<PEdge>();
            // choose a arbitrary edge of the face.
            PEdge startEdge = face.adjacentEdges().iterator().next();
            PEdge currentEdge = startEdge;
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
                this.graph.setExternalFace(face);
                break;
            }
        }
    }
}
