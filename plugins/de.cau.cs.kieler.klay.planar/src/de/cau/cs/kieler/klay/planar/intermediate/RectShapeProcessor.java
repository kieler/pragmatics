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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
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

        for (PEdge edge : edges) {
            addVirtuals(edge, edge.getSource());
            addVirtuals(edge, edge.getTarget());
        }

        // TODO the addVirtuals don't work. use your own implementation.

        getMonitor().done();
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

    /**
     * Adds virtual nodes to the graph. That nodes are dummy nodes to bring the faces in rectangular
     * shape.
     * 
     * @param edge
     * @param node
     */
    private void addVirtuals(final PEdge edge, final PNode node) {
        PEdge nextEdge = edge;
        PNode nextNode = node;
        Pair<PEdge, OrthogonalAngle> firstPair = null;
        int bends = 0;

        do {
            Pair<PEdge, OrthogonalAngle> pair = nextCWEdge(nextNode, nextEdge);
            if (firstPair == null) {
                firstPair = pair;
            }
            OrthogonalAngle angle = pair.getSecond();

            switch (angle) {
            case LEFT:
                if (nextEdge == edge) {
                    return;
                } else {
                    bends += 1;
                }
                break;
            case STRAIGHT:
                if (nextEdge == edge) {
                    nextEdge = pair.getFirst();
                    nextNode = nextNode.getAdjacentNode(nextEdge);
                    this.addVirtuals(nextEdge, nextNode);
                    return;
                }
                break;
            case RIGHT:
                bends -= 1;
                break;
            case FULL:
                bends -= 2;
                break;
            default:
                return;
            }
            nextEdge = pair.getFirst();
            nextNode = nextNode.getAdjacentNode(nextEdge);
            System.out.println("rect: " + nextNode);
            if (bends == 1) {
                Pair<PNode, PEdge> newPair = this.graph.addNode(nextEdge);
                PNode virtualNode = newPair.getFirst();
                PEdge newedge = newPair.getSecond();
                PEdge virtualEdge = this.graph.addEdge(node, virtualNode, false);
                this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
                this.orthogonal.setBends(virtualEdge, new OrthogonalAngle[0]);
                List<Pair<PEdge, OrthogonalAngle>> list;

                // Fix embedding of virtual node neighbor
                PNode neighbor = virtualNode.getAdjacentNode(newedge);
                for (Pair<PEdge, OrthogonalAngle> entry : this.orthogonal.getAngles(neighbor)) {
                    if (entry.getFirst() == nextEdge) {
                        entry.setFirst(newedge);
                    }
                }

                // Fix embedding in virtual nodes
                nextEdge.move(virtualNode, virtualNode);
                virtualEdge.move(virtualNode, virtualNode);
                newedge.move(virtualNode, virtualNode);
                list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
                list.add(new Pair<PEdge, OrthogonalAngle>(nextEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<PEdge, OrthogonalAngle>(newedge, OrthogonalAngle.STRAIGHT));
                this.orthogonal.setAngles(virtualNode, list);

                // Fix embedding in old node
                boolean found = false;
                List<PEdge> toMove = new LinkedList<PEdge>();
                for (PEdge e : node.adjacentEdges()) {
                    if (found) {
                        toMove.add(e);
                    } else {
                        found = (e == edge);
                    }
                }
                for (PEdge e : toMove) {
                    e.move(node, node);
                }
                list = new LinkedList<Pair<PEdge, OrthogonalAngle>>();
                list.add(new Pair<PEdge, OrthogonalAngle>(edge, OrthogonalAngle.STRAIGHT));
                list.add(new Pair<PEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<PEdge, OrthogonalAngle>(firstPair.getFirst(),
                        OrthogonalAngle.LEFT));
                this.orthogonal.setAngles(node, list);
                return;
            }
        } while (nextEdge != edge);
    }

    /**
     * Get the next edge adjacent to a given node from an edge in clockwise order. Returns
     * {@code null} if the given edge is not adjacent to the node, and the given edge if it is the
     * only one adjacent to the node.
     * 
     * @param node
     *            the node
     * @param edge
     *            the edge
     * @return the next edge after the given edge
     */
    private Pair<PEdge, OrthogonalAngle> nextCWEdge(final PNode node, final PEdge edge) {
        // TODO uses the invariant, that edges around a node are in counterclockwise order.
        // Hence we can use the edge with previous index to get the cw edge.

        // old code
        Iterator<Pair<PEdge, OrthogonalAngle>> iter = this.orthogonal.getAngles(node).iterator();
        Pair<PEdge, OrthogonalAngle> pair = new Pair<PEdge, OrthogonalAngle>(null, null);
        PEdge currentEdge = null;
        OrthogonalAngle currentAngle = null;
        while (iter.hasNext() && currentEdge != edge) {
            currentAngle = pair.getSecond();
            pair = iter.next();
            currentEdge = pair.getFirst();
        }
        if (iter.hasNext()) {
            currentAngle = pair.getSecond();
            pair = iter.next();
            currentEdge = pair.getFirst();
        } else {
            currentAngle = pair.getSecond();
            pair = this.orthogonal.getAngles(node).iterator().next();
            currentEdge = pair.getFirst();
        }
        return new Pair<PEdge, OrthogonalAngle>(currentEdge, currentAngle);

        // List<Pair<PEdge, OrthogonalAngle>> angles = orthogonal.getAngles(node);
        // for (int i = 0; i < angles.size(); i++) {
        // if (angles.get(i).getFirst() == edge) {
        //
        // // pre previous
        // int value = 0;
        // if (i == 0) {
        // value = angles.size() - 2;
        // } else if (i == 1) {
        // value = angles.size() - 1;
        // } else {
        // value = i - 2;
        // }
        // // return previous edge with pre previous angle.
        // return new Pair<PEdge, OrthogonalAngle>(angles.get(
        // (i == 0) ? angles.size() - 1 : i - 1).getFirst(), angles.get(value)
        // .getSecond());
        // }
        // }
        // return null;
    }

}
