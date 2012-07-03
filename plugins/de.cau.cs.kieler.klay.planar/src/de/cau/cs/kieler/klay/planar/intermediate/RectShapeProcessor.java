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
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * Brings the faces of the graph in rectangular shape. Meaning that after this step there are exact
 * 4 bends per face, all in right or left order, depending from the start point.
 * 
 * @author pkl
 */
public class RectShapeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    private PGraph graph;

    private OrthogonalRepresentation orthogonal;

    private final int index3 = 3;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Rectangular shaping", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);

        // Add a node for every bend in the orthogonal representation
        List<PEdge> edges = new LinkedList<PEdge>();
        edges.addAll(pgraph.getEdges());

        for (PEdge edge : edges) {
            addBends(edge);
        }

        // Decompose faces into rectangles
        addVirtuals();

        getMonitor().done();
    }

    /**
     * 
     */
    private void addVirtuals() {
        // choose a arbitrary start edge and start node then go along that edge and check the
        // angles.
        // for r,l,l or l,r,r. if one of them is found add a new edge to the graph.
        boolean wantsAgain = true;
        // prepare face list.

        while (wantsAgain) {
            wantsAgain = false;
            out: for (PFace face : graph.getFaces()) {
                // go along the edges and check for pattern right left left or left right right
                // to determine a non rectangular face.
                PEdge currentEdge = face.adjacentEdges().iterator().next();

                // arbitrary node.
                PNode startNode = currentEdge.getTarget();
                PNode currentNode = startNode;
                // stores the edge from the first matching edge to the last.

                // TODO extend for more compaction.
                // use the path to check how many edges are on the side at at first time l or r is
                // found, and then at the finalnode check the number of edges of the side and
                // determine depending on the startside edge number where the new edge should be
                // placed.

                Pair<LinkedList<PEdge>, Integer> lrrPatternPath = new Pair<LinkedList<PEdge>, Integer>();
                lrrPatternPath.setFirst(new LinkedList<PEdge>());
                lrrPatternPath.setSecond(0);
                PNode lrrPatternNode = null;

                Pair<LinkedList<PEdge>, Integer> rllPatternPath = new Pair<LinkedList<PEdge>, Integer>();
                rllPatternPath.setFirst(new LinkedList<PEdge>());
                rllPatternPath.setSecond(0);
                PNode rllPatternNode = null;

                // Flag that ensures that a face split is done before leaving the while loop.
                boolean toSplit = false;

                do {

                    Pair<PEdge, OrthogonalAngle> pair = nextCCWEdge(currentNode, currentEdge, face);

                    // Try to add the new edge to a pattern path.
                    boolean lrrStart = tryPutInLRR(lrrPatternPath, pair);
                    boolean rllStart = tryPutInRLL(rllPatternPath, pair);

                    // If a new path is started, save the currentNode.
                    if (lrrStart) {
                        lrrPatternNode = currentNode;
                    } else if (rllStart) {
                        rllPatternNode = currentNode;
                    }

                    // If a path is complete (last angle has been found), add artificial edge.
                    if (lrrPatternPath.getSecond() == index3) {
                        addArtificial(lrrPatternNode, lrrPatternPath, false);
                        wantsAgain = true;
                        break out;
                    } else if (rllPatternPath.getSecond() == index3) {
                        addArtificial(rllPatternNode, rllPatternPath, true);
                        wantsAgain = true;
                        break out;
                    }

                    if (lrrPatternNode != null && rllPatternNode != null) {
                        // the face isn't in rectangular shape, because left and right angles exist.
                        toSplit = true;
                    }

                    // set next edges.
                    currentEdge = pair.getFirst();
                    currentNode = currentEdge.getOppositeNode(currentNode);

                } while (startNode != currentNode || toSplit);
            }
        }
    }

    /**
     * @param patternStartNode
     * @param patternPath
     * @param isRll
     */
    private void addArtificial(final PNode patternStartNode,
            final Pair<LinkedList<PEdge>, Integer> patternPath, final boolean isRll) {
        LinkedList<PEdge> path = patternPath.getFirst();
        PEdge lastPathEdge = path.getLast();
        PEdge secondLastPathEdge = path.get(path.size() - 2);

        // determine the correct final path node. Choose the opposite of the connected
        // previousPathEdge and lastPathEdge node to get the final node.
        PNode finalNode = null;
        PNode secondLastNode = null;
        if (lastPathEdge.getTarget() == secondLastPathEdge.getTarget()
                || lastPathEdge.getTarget() == secondLastPathEdge.getSource()) {
            finalNode = lastPathEdge.getSource();
            secondLastNode = lastPathEdge.getTarget();
        } else {
            finalNode = lastPathEdge.getTarget();
            secondLastNode = lastPathEdge.getSource();
        }

        // add new node and new edge and set the orthogonal representation!
        Pair<PNode, PEdge> virtualPair = this.graph.addNode(lastPathEdge);

        // add rect shape dummy property to determine later the dummy elements.
        virtualPair.getFirst().setProperty(Properties.RECT_SHAPE_DUMMY, true);

        PNode virtualNode = virtualPair.getFirst();
        PEdge newEdge = this.graph.addEdge(patternStartNode, virtualNode);
        newEdge.setProperty(Properties.RECT_SHAPE_DUMMY, true);

        // Fix embedding and/or angles of startNode
        fixStartNode(patternStartNode, path.getFirst(), newEdge, isRll);

        // Fix embedding and/or angles of virtual node
        fixVirtualNode(isRll, lastPathEdge, virtualPair.getSecond(), virtualNode, newEdge);

        // Fix embedding and/or angles of before node
        fixBeforeNode(finalNode, secondLastNode, virtualPair.getSecond());

        // Fix embedding and/or angles of successor node
        // There is nothing to do, because the angles don't change and the embedding is
        // done implicitly by the graph.addEdge(...).

        // triggers a new face determination.
        this.graph.getFaces();

    }

    private void fixBeforeNode(PNode successorNode, PNode beforeNode, PEdge virtualEdge) {
        // Fix angles of before node, the embedding is implicitly fixed by graph.addEdge(...).

        // Find the old wrong edge and replace the new one with that
        Pair<PEdge, OrthogonalAngle> chosenPair = null;
        for (Pair<PEdge, OrthogonalAngle> pair : this.orthogonal.getAngles(beforeNode)) {
            if (pair.getFirst().getTarget() == successorNode
                    || pair.getFirst().getSource() == successorNode) {
                chosenPair = pair;
                chosenPair.setFirst(virtualEdge);
                return;
            }
        }
    }

    private void fixStartNode(final PNode startNode, final PEdge startNodeEdge,
            final PEdge newEdge, final boolean isRll) {
        // fix node embedding: is implicit done by graph.addEdge(..)

        // fix angles
        // patternStartNode to be a node with exact 2 edges.
        List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(startNode);
        Pair<PEdge, OrthogonalAngle> successor = null;
        Pair<PEdge, OrthogonalAngle> before = null;
        // choose the other edge as startEdge because it is not in the path and thus before
        // the startNodeEdge.
        if (startNodeEdge == angles.get(0).getFirst()) {
            before = angles.get(1);
            successor = angles.get(0);
        } else {
            before = angles.get(0);
            successor = angles.get(1);
        }
        angles.clear();
        if (isRll) {
            before.setSecond(OrthogonalAngle.STRAIGHT);
            angles.add(before);
            // add new node
            angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.LEFT));
            angles.add(successor);
        } else {
            // is lrr
            angles.add(before);
            successor.setSecond(OrthogonalAngle.LEFT);
            angles.add(successor);
            // add new angles
            angles.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.STRAIGHT));
        }
    }

    private void fixVirtualNode(final boolean isRll, PEdge lastPathEdge, PEdge virtualEdge,
            PNode virtualNode, PEdge newEdge) {
        List<Pair<PEdge, OrthogonalAngle>> list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
        if (isRll) {
            newEdge.move(virtualNode, virtualNode);
            lastPathEdge.move(virtualNode, virtualNode);
            virtualEdge.move(virtualNode, virtualNode);
            list.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.LEFT));
            list.add(new Pair<PEdge, OrthogonalAngle>(lastPathEdge, OrthogonalAngle.STRAIGHT));
            list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
        } else {
            newEdge.move(virtualNode, virtualNode);
            virtualEdge.move(virtualNode, virtualNode);
            lastPathEdge.move(virtualNode, virtualNode);
            list.add(new Pair<PEdge, OrthogonalAngle>(newEdge, OrthogonalAngle.LEFT));
            list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.STRAIGHT));
            list.add(new Pair<PEdge, OrthogonalAngle>(lastPathEdge, OrthogonalAngle.LEFT));
        }

        this.orthogonal.setAngles(virtualNode, list);
        this.orthogonal.setBends(newEdge, new OrthogonalAngle[0]);
        this.orthogonal.setBends(virtualEdge, new OrthogonalAngle[0]);

    }

    /**
     * @param lrrPatternPath
     * @param currentEdge
     * @return true, if the path is complete, meaning if the currentEdge has a right angle and index
     *         is 2
     */
    private boolean tryPutInLRR(final Pair<LinkedList<PEdge>, Integer> lrrPatternPath,
            final Pair<PEdge, OrthogonalAngle> pair) {
        int index = lrrPatternPath.getSecond();

        OrthogonalAngle angle = pair.getSecond();
        boolean startNewPath = false;
        switch (index) {
        case 0:
            // check if angle is right => start filling the path.
            if (angle == OrthogonalAngle.LEFT) {
                startNewPath = true;
            }
            break;
        case 1:
            if (angle == OrthogonalAngle.RIGHT) {
                lrrPatternPath.setSecond(2);
                lrrPatternPath.getFirst().add(pair.getFirst());
            } else if (angle == OrthogonalAngle.LEFT) {
                // a new left angle has been found, we have to start a new path.
                startNewPath = true;
            }
            break;
        case 2:
            if (angle == OrthogonalAngle.RIGHT) {
                lrrPatternPath.setSecond(index3);
                lrrPatternPath.getFirst().add(pair.getFirst());
            } else if (angle == OrthogonalAngle.LEFT) {
                startNewPath = true;
            }
            break;
        default:
            throw new InconsistentGraphModelException(
                    "RectShapeProcessor: the index of lrr pattern is not defined!");
        }

        if (startNewPath) {
            lrrPatternPath.setSecond(1);
            lrrPatternPath.getFirst().clear();
            lrrPatternPath.getFirst().add(pair.getFirst());
            return true;
        }

        return false;
    }

    /**
     * @param rllPatternPath
     * @param currentEdge
     * @return true, if the path is complete, meaning if the currentEdge has a left angle and index
     *         is 2
     */
    private boolean tryPutInRLL(final Pair<LinkedList<PEdge>, Integer> rllPatternPath,
            final Pair<PEdge, OrthogonalAngle> pair) {
        int index = rllPatternPath.getSecond();
        boolean startNewPath = false;
        OrthogonalAngle angle = pair.getSecond();
        switch (index) {
        case 0:
            // check if angle is right => start filling the path.
            if (angle == OrthogonalAngle.RIGHT) {
                rllPatternPath.setSecond(1);
                rllPatternPath.getFirst().add(pair.getFirst());
                return true;
            }
            break;
        case 1:
            if (angle == OrthogonalAngle.LEFT) {
                rllPatternPath.setSecond(2);
                rllPatternPath.getFirst().add(pair.getFirst());
            } else if (angle == OrthogonalAngle.RIGHT) {
                startNewPath = true;
            }
            break;
        case 2:
            if (angle == OrthogonalAngle.LEFT) {
                rllPatternPath.setSecond(index3);
                rllPatternPath.getFirst().add(pair.getFirst());
            } else if (angle == OrthogonalAngle.RIGHT) {
                startNewPath = true;
            }
            break;
        default:
            throw new InconsistentGraphModelException(
                    "RectShapeProcessor: the index of rll pattern is not defined!");
        }

        if (startNewPath) {
            rllPatternPath.setSecond(1);
            rllPatternPath.getFirst().clear();
            rllPatternPath.getFirst().add(pair.getFirst());
            return true;
        }

        return false;
    }

    private void addBends(final PEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        List<Pair<PEdge, OrthogonalAngle>> list;
        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<PNode, PEdge> pair = this.graph.addNode(edge);
            pair.getFirst().setProperty(Properties.BENDPOINT, bends[i]);
            PEdge newedge = pair.getSecond();
            OrthogonalAngle b1 = bends[i];
            OrthogonalAngle b2 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
            list.add(new Pair<PEdge, OrthogonalAngle>(edge, b1));
            list.add(new Pair<PEdge, OrthogonalAngle>(newedge, b2));
            this.orthogonal.setAngles(pair.getFirst(), list);
            this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
            for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal
                    .getAngles(newedge.getTarget())) {
                if (entry.getFirst() == edge) {
                    entry.setFirst(newedge);
                }
            }
        }
        this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
    }

    // /**
    // * Adds virtual nodes to the graph. That nodes are dummy nodes to bring the faces in
    // rectangular
    // * shape.
    // *
    // * @param edge
    // * @param node
    // */
    // private void addVirtuals(final PEdge edge, final PNode node) {
    // PEdge nextEdge = edge;
    // PNode nextNode = node;
    // Pair<PEdge, OrthogonalAngle> firstPair = null;
    // int bends = 0;
    //
    // do {
    // Pair<PEdge, OrthogonalAngle> pair = nextCCWEdge(nextNode, nextEdge);
    // if (firstPair == null) {
    // firstPair = pair;
    // }
    // OrthogonalAngle angle = pair.getSecond();
    //
    // switch (angle) {
    // case LEFT:
    // if (nextEdge == edge) {
    // return;
    // } else {
    // bends += 1;
    // }
    // break;
    // case STRAIGHT:
    // if (nextEdge == edge) {
    // nextEdge = pair.getFirst();
    // nextNode = nextNode.getAdjacentNode(nextEdge);
    // this.addVirtuals(nextEdge, nextNode);
    // return;
    // }
    // break;
    // case RIGHT:
    // bends -= 1;
    // break;
    // case FULL:
    // bends -= 2;
    // break;
    // default:
    // return;
    // }
    // nextEdge = pair.getFirst();
    // nextNode = nextNode.getAdjacentNode(nextEdge);
    // System.out.println("rect: " + nextNode);
    // if (bends == 1) {
    // Pair<PNode, PEdge> newPair = this.graph.addNode(nextEdge);
    // PNode virtualNode = newPair.getFirst();
    // PEdge newedge = newPair.getSecond();
    // PEdge virtualEdge = this.graph.addEdge(node, virtualNode, false);
    // this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
    // this.orthogonal.setBends(virtualEdge, new OrthogonalAngle[0]);
    // List<Pair<PEdge, OrthogonalAngle>> list;
    //
    // // Fix embedding of virtual node neighbor
    // PNode neighbor = virtualNode.getAdjacentNode(newedge);
    // for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal.getAngles(neighbor)) {
    // if (entry.getFirst() == nextEdge) {
    // entry.setFirst(newedge);
    // }
    // }
    //
    // // Fix embedding in virtual nodes
    // nextEdge.move(virtualNode, virtualNode);
    // virtualEdge.move(virtualNode, virtualNode);
    // newedge.move(virtualNode, virtualNode);
    // list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
    // list.add(new Pair<PEdge, OrthogonalAngle>(nextEdge, OrthogonalAngle.LEFT));
    // list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
    // list.add(new Pair<PEdge, OrthogonalAngle>(newedge, OrthogonalAngle.STRAIGHT));
    // this.orthogonal.setAngles(virtualNode, list);
    //
    // // Fix embedding in old node
    // boolean found = false;
    // List<PEdge> toMove = new LinkedList<PEdge>();
    // for (PEdge e : node.adjacentEdges()) {
    // if (found) {
    // toMove.add(e);
    // } else {
    // found = (e == edge);
    // }
    // }
    // for (PEdge e : toMove) {
    // e.move(node, node);
    // }
    // list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
    // list.add(new Pair<PEdge, OrthogonalAngle>(edge, OrthogonalAngle.STRAIGHT));
    // list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
    // list.add(new Pair<PEdge, OrthogonalAngle>(firstPair.getFirst(),
    // OrthogonalAngle.LEFT));
    // this.orthogonal.setAngles(node, list);
    // return;
    // }
    // } while (nextEdge != edge);
    // }

    /**
     * Get the next edge adjacent to a given node from an edge in counter clockwise order. Returns
     * {@code null} if the given edge is not adjacent to the node, and the given edge if it is the
     * only one adjacent to the node.
     * 
     * @param node
     *            the node
     * @param edge
     *            the edge
     * @return the next edge after the given edge
     */
    private Pair<PEdge, OrthogonalAngle> nextCCWEdge(final PNode node, final PEdge startEdge,
            final PFace face) {

        int previousIndex = 0;
        int currentIndex = 0;
        int directionCounter = 0;

        // get startedge index.
        List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(node);
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                currentIndex = i;
                break;
            }
        }

        // if a edge of an other face has detected, we have to sum over all angles until
        // a face-edge is reached.
        boolean containsForeignEdge = false;
        Pair<PEdge, OrthogonalAngle> pair = null;
        // determine the directions of the next corner face-edge

        do {
            previousIndex = currentIndex;
            currentIndex = (currentIndex + 1) < angles.size() ? currentIndex + 1 : 0;
            pair = angles.get(currentIndex);

            if (!containsForeignEdge && face.isAdjacent(pair.getFirst())) {
                // hasFound
                directionCounter = angles.get(previousIndex).getSecond().ordinal();
                break;
            } else {
                containsForeignEdge = true;
                // look at the direction of the previous edge to determine the direction
                directionCounter += angles.get(previousIndex).getSecond().ordinal() + 1;
            }
        } while (!face.isAdjacent(pair.getFirst()));
        return new Pair<PEdge, OrthogonalAngle>(pair.getFirst(),
                OrthogonalAngle.map(containsForeignEdge ? directionCounter - 1 : directionCounter));
    }

}
