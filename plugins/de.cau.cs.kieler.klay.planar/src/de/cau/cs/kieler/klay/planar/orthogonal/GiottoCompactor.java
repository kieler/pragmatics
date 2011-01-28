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
package de.cau.cs.kieler.klay.planar.orthogonal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.orthogonal.OrthogonalRepresentation.OrthogonalAngle;

/**
 * A compaction algorithm that reduces the orthogonal representation to a simple orthogonal
 * representation by adding virtual vertices and edges.
 * 
 * @author ocl
 */
public class GiottoCompactor extends AbstractAlgorithm implements ICompactor {

    // ======================== Attributes =========================================================

    /** The compaction algorithm to compact the simple orthogonal representation. */
    private TidyRectangleCompactor compactor;

    private IGraph graph;

    private OrthogonalRepresentation orthogonal;

    // ======================== Constructor ========================================================

    /**
     * Create a new compaction algorithm.
     */
    public GiottoCompactor() {
        this.compactor = new TidyRectangleCompactor();
    }

    @Override
    public void reset() {
        super.reset();
        this.compactor.reset();
        this.graph = null;
        this.orthogonal = null;
    }

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void compact(final IGraph g, final OrthogonalRepresentation o) {
        this.graph = g;
        this.orthogonal = o;
        List<IEdge> edges;

        // Add a node for every bend in the orthogonal representation
        edges = new LinkedList<IEdge>();
        for (IEdge edge : g.getEdges()) {
            edges.add(edge);
        }
        for (IEdge edge : edges) {
            this.addBends(edge);
        }

        // Decompose faces into rectangles
        edges = new LinkedList<IEdge>();
        for (IEdge edge : g.getEdges()) {
            edges.add(edge);
        }
        for (IEdge edge : edges) {
            this.addVirtuals(edge, edge.getSource());
            this.addVirtuals(edge, edge.getTarget());
        }

        // Compact reduced orthogonal representation
        // this.compactor.compact(g, orthogonal);
    }

    private void addBends(final IEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        List<Pair<IEdge, OrthogonalAngle>> list;
        for (int i = bends.length - 1; i >= 0; i--) {
            Pair<INode, IEdge> pair = this.graph.addNode(edge);
            IEdge newedge = pair.getSecond();
            OrthogonalAngle b1 = bends[i];
            OrthogonalAngle b2 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            list = new LinkedList<Pair<IEdge, OrthogonalAngle>>();
            list.add(new Pair<IEdge, OrthogonalAngle>(edge, b1));
            list.add(new Pair<IEdge, OrthogonalAngle>(newedge, b2));
            this.orthogonal.setAngles(pair.getFirst(), list);
            this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
            for (Pair<IEdge, OrthogonalAngle> entry : this.orthogonal
                    .getAngles(newedge.getTarget())) {
                if (entry.getFirst() == edge) {
                    entry.setFirst(newedge);
                }
            }
        }
        this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
    }

    private void addVirtuals(final IEdge edge, final INode node) {
        IEdge nextEdge = edge;
        INode nextNode = node;
        Pair<IEdge, OrthogonalAngle> firstPair = null;
        int bends = 0;

        do {
            Pair<IEdge, OrthogonalAngle> pair = this.nextEdge(nextNode, nextEdge);
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

            if (bends == 1) {
                Pair<INode, IEdge> newPair = this.graph.addNode(nextEdge);
                INode virtualNode = newPair.getFirst();
                IEdge newedge = newPair.getSecond();
                IEdge virtualEdge = this.graph.addEdge(node, virtualNode, false);
                this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
                this.orthogonal.setBends(virtualEdge, new OrthogonalAngle[0]);
                List<Pair<IEdge, OrthogonalAngle>> list;

                // Fix embedding of virtual node neighbor
                INode neighbor = virtualNode.getAdjacentNode(newedge);
                for (Pair<IEdge, OrthogonalAngle> entry : this.orthogonal.getAngles(neighbor)) {
                    if (entry.getFirst() == nextEdge) {
                        entry.setFirst(newedge);
                    }
                }

                // Fix embedding in virtual nodes
                nextEdge.move(virtualNode, virtualNode);
                virtualEdge.move(virtualNode, virtualNode);
                newedge.move(virtualNode, virtualNode);
                list = new LinkedList<Pair<IEdge, OrthogonalAngle>>();
                list.add(new Pair<IEdge, OrthogonalAngle>(nextEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<IEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<IEdge, OrthogonalAngle>(newedge, OrthogonalAngle.STRAIGHT));
                this.orthogonal.setAngles(virtualNode, list);

                // Fix embedding in old node
                boolean found = false;
                List<IEdge> toMove = new LinkedList<IEdge>();
                for (IEdge e : node.adjacentEdges()) {
                    if (found) {
                        toMove.add(e);
                    } else {
                        found = (e == edge);
                    }
                }
                for (IEdge e : toMove) {
                    e.move(node, node);
                }
                list = new LinkedList<Pair<IEdge, OrthogonalAngle>>();
                list.add(new Pair<IEdge, OrthogonalAngle>(edge, OrthogonalAngle.STRAIGHT));
                list.add(new Pair<IEdge, OrthogonalAngle>(virtualEdge, OrthogonalAngle.LEFT));
                list.add(new Pair<IEdge, OrthogonalAngle>(firstPair.getFirst(),
                        OrthogonalAngle.LEFT));
                this.orthogonal.setAngles(node, list);
                return;
            }
        } while (nextEdge != edge);
    }

    /**
     * Get the next edge adjacent to a given node from an edge in clockwise order. Returns {@code
     * null} if the given edge is not adjacent to the node, and the given edge if it is the only one
     * adjacent to the node.
     * 
     * @param node
     *            the node
     * @param edge
     *            the edge
     * @return the next edge after the given edge
     */
    private Pair<IEdge, OrthogonalAngle> nextEdge(final INode node, final IEdge edge) {
        Iterator<Pair<IEdge, OrthogonalAngle>> iter = this.orthogonal.getAngles(node).iterator();
        Pair<IEdge, OrthogonalAngle> pair = new Pair<IEdge, OrthogonalAngle>(null, null);
        IEdge currentEdge = null;
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
        return new Pair<IEdge, OrthogonalAngle>(currentEdge, currentAngle);
    }

}
