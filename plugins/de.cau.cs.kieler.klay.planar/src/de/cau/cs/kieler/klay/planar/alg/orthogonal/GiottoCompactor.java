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
package de.cau.cs.kieler.klay.planar.alg.orthogonal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.OrthogonalRepresentation.OrthogonalAngle;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;

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

        // Add a node for every bend in the orthogonal representation
        List<IEdge> edges = new LinkedList<IEdge>();
        for (IEdge edge : g.getEdges()) {
            edges.add(edge);
        }
        for (IEdge edge : edges) {
            this.addBends(edge);
        }

        // Decompose faces into rectangles
        for (IEdge edge : g.getEdges()) {
            // this.addVirtuals(edge, edge.getSource());
            // this.addVirtuals(edge, edge.getTarget());
        }

        // Compact reduced orthogonal representation
        // this.compactor.compact(g, orthogonal);
    }

    private void addBends(final IEdge edge) {
        OrthogonalAngle[] bends = this.orthogonal.getBends(edge);
        for (int i = bends.length - 1; i >= 0; i--) {
            INode virtual = this.graph.addNode(edge);
            List<Pair<IEdge, OrthogonalAngle>> list = new LinkedList<Pair<IEdge, OrthogonalAngle>>();
            OrthogonalAngle b1 = (bends[i] == OrthogonalAngle.LEFT) ? OrthogonalAngle.RIGHT
                    : OrthogonalAngle.LEFT;
            OrthogonalAngle b2 = bends[i];
            for (IEdge e : virtual.adjacentEdges()) {
                if (edge == e) {
                    list.add(new Pair<IEdge, OrthogonalAngle>(e, b1));
                } else {
                    this.orthogonal.setBends(e, new OrthogonalAngle[0]);
                    list.add(new Pair<IEdge, OrthogonalAngle>(e, b2));
                }
            }
        }
        this.orthogonal.setBends(edge, new OrthogonalAngle[0]);
    }

    private void addVirtuals(final IEdge edge, final INode node) {
        IEdge nextEdge = edge;
        INode nextNode = node;
        int bends = 0;

        do {
            Pair<IEdge, OrthogonalAngle> pair = this.nextEdge(nextNode, nextEdge);
            nextEdge = pair.getFirst();
            nextNode = nextNode.getAdjacentNode(nextEdge);
            OrthogonalAngle angle = pair.getSecond();

            switch (angle) {
            case RIGHT:
                if (nextEdge == edge) {
                    return;
                } else {
                    bends += 1;
                }
            case STRAIGHT:
                if (nextEdge == edge) {
                    this.addVirtuals(nextEdge, nextNode);
                    return;
                }
                break;
            case LEFT:
                bends -= 1;
                break;
            case FULL:
                bends -= 2;
                break;
            default:
                return;
            }

            if (bends == 1) {
                INode newnode = this.graph.addNode(nextEdge);
                IEdge newedge = this.graph.addEdge(node, newnode, false);
                this.orthogonal.setBends(newedge, new OrthogonalAngle[0]);
                // TODO order in node: .., edge, 180, newedge, 90, ?oldedge?, ..
                // TODO order in newnode: .., nextEdgeIn, 90, newedge, 90, nextEdgeOut, ..
                // TODO same orders in embedding of node/newnode
                return;
            }
        } while (nextEdge != edge);
        // TODO external face?
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
        Pair<IEdge, OrthogonalAngle> current = null;
        while (iter.hasNext() && current.getFirst() != edge) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            // Get the next edge on the node
            return iter.next();
        } else {
            // Reached the last node, get the first
            return this.orthogonal.getAngles(node).iterator().next();
        }
    }

}
