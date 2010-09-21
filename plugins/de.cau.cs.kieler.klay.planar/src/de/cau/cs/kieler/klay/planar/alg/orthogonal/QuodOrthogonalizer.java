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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;

/**
 * Implementation of the Quod orthogonilazation algorithm, based on the paper "Quasi-Orthogonal
 * Drawing of Planar Graphs", by Gunnar W. Klau and Petra Mutzel. Uses the bend minimizing algorithm
 * for 4-planar graphs by Tamassia, and extends it to work for high-degree nodes by replacing these
 * with cages.
 * 
 * @author ocl
 */
public class QuodOrthogonalizer extends AbstractAlgorithm implements IOrthogonalizer {

    // ======================== Helper Classes =====================================================

    /**
     * A cage in the working graph. Since the underlying flow network can only solve
     * orthogonalization problems with a maximum node degree of 4, evey node with a higher degree is
     * replaced by a cage. A cage consists of a face replacing the original node, surrounded by a
     * ring with a node for every edge the original node was adjacent to. This cage class is used to
     * keep track of a cage in the graph for a clean transformation.
     */
    private class Cage {

        // -------------------- Attributes ---------------------------------------------------------

        /** The graph the cage is a part of. */
        private IGraph graph;

        /** The original node replaced by the cage. */
        private INode cageNode;

        /** A mapping from the ring nodes to the corresponding edges. */
        private LinkedHashMap<INode, IEdge> map;

        // -------------------- Constructor --------------------------------------------------------

        /**
         * Replace a node in the graph by a cage.
         * 
         * @param node
         *            the node to replace
         */
        public Cage(final INode node) {
            this.cageNode = node;
            this.graph = this.cageNode.getParent();
            this.map = new LinkedHashMap<INode, IEdge>(this.cageNode.getAdjacentEdgeCount() * 2);

            // Create temporary list to not break the iterator
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : this.cageNode.adjacentEdges()) {
                edges.add(edge);
            }

            // Create cage nodes
            INode first = null;
            INode previous = null;
            INode current = null;
            for (IEdge edge : edges) {
                previous = current;
                current = this.graph.addNode();
                this.map.put(current, edge);
                if (first == null) {
                    first = current;
                }
                if (previous != null) {
                    this.graph.addEdge(previous, current);
                }
                edge.move(this.cageNode.getAdjacentNode(edge), current);
            }
            if (first != null) {
                this.graph.addEdge(current, first);
            }
        }

        /**
         * Remove the cage from the graph.
         */
        public void remove() {
            // Since we use a linked hash map, the edges are added in the original order
            for (Map.Entry<INode, IEdge> entry : this.map.entrySet()) {
                INode node = entry.getKey();
                IEdge edge = entry.getValue();
                edge.move(node, this.cageNode);
                this.graph.removeNode(node);
            }
        }
    }

    // ======================== Algorithm ==========================================================
    // TODO does leaving the cage nodes in the graph influence the flow network?

    /**
     * {@inheritDoc}
     */
    public IOrthogonalRepresentation orthogonalize(final IGraph graph) {

        // Replace high-degree nodes with cages
        LinkedList<Cage> cages = new LinkedList<Cage>();
        for (INode node : graph.getNodes()) {
            if (node.getAdjacentEdgeCount() > TamassiaOrthogonalizer.MAXDEGREE) {
                cages.add(new Cage(node));
            }
        }

        IOrthogonalizer orthogonalizer = new TamassiaOrthogonalizer();
        IOrthogonalRepresentation orthogonal = orthogonalizer.orthogonalize(graph);

        // TODO force cages as rectangles

        // Remove the cages from the graph
        for (Cage cage : cages) {
            cage.remove();
        }

        return orthogonal;
    }

}
