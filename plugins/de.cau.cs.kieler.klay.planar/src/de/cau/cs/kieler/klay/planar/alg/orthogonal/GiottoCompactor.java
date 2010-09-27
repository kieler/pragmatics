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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
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
    // TODO rename: this is not giotto

    // ======================== Attributes =========================================================

    /** The compaction algorithm to compact the simple orthogonal representation. */
    private TidyRectangleCompactor compactor;

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
    }

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void compact(final IGraph g, final OrthogonalRepresentation orthogonal) {

        // Add a node for every bend in the orthogonal representation
        for (IEdge edge : g.getEdges()) {
            OrthogonalAngle[] bends = orthogonal.getBends(edge);
            for (int i = 0; i < bends.length; i++) {
                INode virtual = g.addNode(edge);
                for (IEdge e : virtual.adjacentEdges()) {
                    orthogonal.setBends(e, new OrthogonalAngle[0]);
                }
                // TODO order in new vertex: inEdge, bend, outEdge, anti-bend
            }
        }

        // Decompose faces into rectangles
        for (IEdge edge : g.getEdges()) {

        }

        // Compact reduced orthogonal representation
        this.compactor.compact(g, orthogonal);
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
    private IEdge nextEdge(final INode node, final IEdge edge) {
        Iterator<IEdge> iter = node.adjacentEdges().iterator();
        IEdge current = null;
        while (iter.hasNext() && current != edge) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            // Get the next edge on the node
            return iter.next();
        } else {
            // Reached the last node, get the first
            return node.adjacentEdges().iterator().next();
        }
    }

}
