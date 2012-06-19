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
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PFace;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.p2ortho.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * 
 * @author pkl
 */
public class RectShapeProcessor extends AbstractAlgorithm implements ILayoutProcessor {

    private PGraph graph;

    private OrthogonalRepresentation orthogonal;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pgraph) {
        getMonitor().begin("Rectangular shaping", 1);
        this.graph = pgraph;
        this.orthogonal = pgraph.getProperty(Properties.ORTHO_REPRESENTATION);
        List<PEdge> edges;

        // FIXME use addAll instead

        // Add a node for every bend in the orthogonal representation
        edges = new LinkedList<PEdge>();
        for (PEdge edge : pgraph.getEdges()) {
            edges.add(edge);
        }
        for (PEdge edge : edges) {
            addBends(edge);
        }

        // Decompose faces into rectangles
        edges = new LinkedList<PEdge>();
        for (PEdge edge : pgraph.getEdges()) {
            edges.add(edge);
        }
        addVirtuals();

        getMonitor().done();
    }

    /**
     * 
     */
    private void addVirtuals() {
        // choose a arbitrary startedge edge and node then go along that edge and check the angles
        // for r,l,l or l,r,r. if one of them is found add a new edge to the graph.

        for (PFace face : graph.getFaces()) {
            // go along the edges and check for pattern right left left or left right right
            // to determine a non rectangular face.
            PEdge currentEdge = face.adjacentEdges().iterator().next();

            // arbitrary node.
            PNode startNode = currentEdge.getTarget();
            PNode currentNode = startNode;
            // stores the edge from the first matching edge to the last.

            // FIXME the last element should be enough, we do not need to build up a path list.
            // no it does not, because when we have from beginning node more straight edge,
            // we have to differ how many edges that are, this is a extension!

            Pair<LinkedList<PEdge>, Integer> lrrPatternPath = new Pair<LinkedList<PEdge>, Integer>();
            lrrPatternPath.setFirst(new LinkedList<PEdge>());
            lrrPatternPath.setSecond(0);
            PNode lrrPatternNode = null;

            Pair<LinkedList<PEdge>, Integer> rllPatternPath = new Pair<LinkedList<PEdge>, Integer>();
            rllPatternPath.setFirst(new LinkedList<PEdge>());
            rllPatternPath.setSecond(0);
            PNode rllPatternNode = null;

            while (true) {

                Pair<PEdge, OrthogonalAngle> pair = nextCCWEdge(currentNode, currentEdge, face);

                // try to add the new edge to a pattern path.
                boolean lrrStart = tryPutInLRR(lrrPatternPath, pair);
                boolean rllStart = tryPutInRLL(rllPatternPath, pair);

                // if a new path is started, save the currentNode
                if (lrrStart) {
                    lrrPatternNode = currentNode;
                } else if (rllStart) {
                    rllPatternNode = currentNode;
                }

                // if a path is complete (last angle has been found) , add artificial edge.
                if (lrrPatternPath.getSecond() == 3) {
                    addArtificial(lrrPatternNode, lrrPatternPath);
                    // reset path
                    lrrPatternPath.getFirst().clear();
                    lrrPatternNode = null;
                } else if (rllPatternPath.getSecond() == 3) {
                    addArtificial(rllPatternNode, rllPatternPath);
                    // reset path
                    rllPatternPath.getFirst().clear();
                    rllPatternNode = null;
                }

                // set next edges.
                currentEdge = pair.getFirst();
                currentNode = currentEdge.getOppositeNode(currentNode);

                if (startNode == currentNode) {
                    break;
                }

            }

        }

    }

    /**
     * @param patternStartNode
     * @param patternPath
     */
    private void addArtificial(PNode patternStartNode, Pair<LinkedList<PEdge>, Integer> patternPath) {
        LinkedList<PEdge> path = patternPath.getFirst();
        PEdge lastPathEdge = path.getLast();
        PEdge previousPathEdge = path.get(path.size() - 2);

        // determine the correct final path node.
        PNode finalNode = null;
        if (lastPathEdge.getTarget() == previousPathEdge.getTarget()
                || lastPathEdge.getTarget() == previousPathEdge.getSource()) {
            finalNode = lastPathEdge.getSource();
        } else {
            finalNode = lastPathEdge.getTarget();
        }

        // counts the angle of the finalNodes, meaning gets the biggest angle from the lastPathEdge
        // to one of the other node edges.
        int angleCount = countAngles(finalNode, lastPathEdge);

        if (angleCount > 2) {
            // if the node has a edge in the direction of the lrrPatternStart,
            // we have to split the last edge and to insert a new node there.
            Pair<PNode, PEdge> virtualPair = this.graph.addNode(lastPathEdge);
            PNode virtualNode = virtualPair.getFirst();
            PEdge newEdge = this.graph.addEdge(patternStartNode, virtualNode);
            this.orthogonal.setBends(newEdge, new OrthogonalAngle[0]);
            this.orthogonal.setBends(virtualPair.getSecond(), new OrthogonalAngle[0]);

            // TODO is that correct
            // Fix embedding of virtual node neighbor
            PNode neighbor = virtualNode.getAdjacentNode(newEdge);
            for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal.getAngles(neighbor)) {
                // if (entry.getFirst() == nextEdge) {
                // entry.setFirst(newedge);
                // }
            }

            // Fix embedding in virtual nodes
            // nextEdge.move(virtualNode, virtualNode);
            // virtualEdge.move(virtualNode, virtualNode);
            // newedge.move(virtualNode, virtualNode);
            // list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
            // list.add(new Pair<PEdge, OrthogonalAngle>(nextEdge, OrthogonalAngle.LEFT));
            // list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
            // list.add(new Pair<PEdge, OrthogonalAngle>(newedge, OrthogonalAngle.STRAIGHT));
            // this.orthogonal.setAngles(virtualNode, list);

            // Fix embedding in virtual nodes
            // nextEdge.move(virtualNode, virtualNode);
            // virtualEdge.move(virtualNode, virtualNode);
            // newedge.move(virtualNode, virtualNode);
            // list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
            // list.add(new Pair<PEdge, OrthogonalAngle>(nextEdge, OrthogonalAngle.LEFT));
            // list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
            // list.add(new Pair<PEdge, OrthogonalAngle>(newedge, OrthogonalAngle.STRAIGHT));
            // this.orthogonal.setAngles(virtualNode, list);

            // Fix embedding in old node
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

        } else {
            // otherwise we can use the final path node to add the edge there.
            PEdge newEdge = this.graph.addEdge(patternStartNode, finalNode);
            this.orthogonal.setBends(newEdge, new OrthogonalAngle[0]);
            // TODO do the correct embedding.
        }
    }

    /**
     * @param node
     * @return
     */
    private int countAngles(final PNode node, final PEdge startEdge) {
        List<Pair<PEdge, OrthogonalAngle>> angles = this.orthogonal.getAngles(node);

        int currentIndex = 0;

        // get edge index.
        for (int i = 0; i < angles.size(); i++) {
            if (angles.get(i).getFirst() == startEdge) {
                currentIndex = i;
                break;
            }
        }

        int startIndex = currentIndex;
        int previousIndex = currentIndex;
        currentIndex = currentIndex + 1 % angles.size();

        int result = 0;
        // check of what angle structure is the node.
        while (startIndex != currentIndex) {
            result += angles.get(previousIndex).getSecond().ordinal();
            previousIndex = currentIndex;
            currentIndex = currentIndex + 1 % angles.size();
        }

        return result;
    }

    /**
     * @param lrrPatternPath
     * @param currentEdge
     * @return true, if the path is complete, meaning if the currentEdge has a right angle and index
     *         is 2
     */
    private boolean tryPutInLRR(Pair<LinkedList<PEdge>, Integer> lrrPatternPath,
            Pair<PEdge, OrthogonalAngle> pair) {
        int index = lrrPatternPath.getSecond();

        OrthogonalAngle angle = pair.getSecond();
        switch (index) {
        case 0:
            // check if angle is right => start filling the path.
            if (angle == OrthogonalAngle.LEFT) {
                index = 1;
                lrrPatternPath.getFirst().add(pair.getFirst());
                return true;
            }
            break;
        case 1:
            if (angle == OrthogonalAngle.RIGHT) {
                index = 2;
            }
            lrrPatternPath.getFirst().add(pair.getFirst());
            break;
        case 2:
            if (angle == OrthogonalAngle.RIGHT) {
                index = 3;
            }
            lrrPatternPath.getFirst().add(pair.getFirst());
            break;
        default:
            throw new IllegalStateException();
        }
        return false;
    }

    /**
     * @param rllPatternPath
     * @param currentEdge
     * @return true, if the path is complete, meaning if the currentEdge has a left angle and index
     *         is 2
     */
    private boolean tryPutInRLL(Pair<LinkedList<PEdge>, Integer> rllPatternPath,
            Pair<PEdge, OrthogonalAngle> pair) {
        int index = rllPatternPath.getSecond();

        OrthogonalAngle angle = pair.getSecond();
        switch (index) {
        case 0:
            // check if angle is right => start filling the path.
            if (angle == OrthogonalAngle.RIGHT) {
                index = 1;
                rllPatternPath.getFirst().add(pair.getFirst());
                return true;
            }
            break;
        case 1:
            if (angle == OrthogonalAngle.LEFT) {
                index = 2;
            }
            rllPatternPath.getFirst().add(pair.getFirst());
            break;
        case 2:
            if (angle == OrthogonalAngle.LEFT) {
                index = 3;
            }
            rllPatternPath.getFirst().add(pair.getFirst());
            break;
        default:
            throw new IllegalStateException();
        }
        return false;
    }

    private void addBends(final PEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        List<Pair<PEdge, OrthogonalAngle>> list;
        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<PNode, PEdge> pair = this.graph.addNode(edge);
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
                directionCounter += angles.get(previousIndex).getSecond().ordinal();

                if (face.isAdjacent(pair.getFirst())) {
                    // hasFound
                    break;
                }
            }
        } while (true);
        return new Pair<PEdge, OrthogonalAngle>(pair.getFirst(),
                OrthogonalAngle.map(directionCounter));
    }

}
