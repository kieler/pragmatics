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
package de.cau.cs.kieler.klay.planar.alg.impl;

import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.util.Property;
import de.cau.cs.kieler.klay.planar.alg.IOrthogonalizer;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IFace;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphElement;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

public class QuodOrthogonalizer extends AbstractAlgorithm implements IOrthogonalizer {

    // ======================== Constants ==========================================================

    /** Property to convert a node in the flow network to a node or face in the graph. */
    private final Property<IGraphElement> NETWORKTOGRAPH = new Property<IGraphElement>(
            "de.cau.cs.kieler.klay.planar.properties.networktograph");

    /** The maximal node degree in an orthogonal layout. */
    private final int MAXDEGREE = 4;

    // ======================== Attributes =========================================================

    /** The graph the algorithm works on. */
    private IGraph graph;

    // ======================== Algorithm ==========================================================

    /**
     * {@inheritDoc}
     */
    public void orthogonalize(final IGraph g) {
        this.graph = g;
        this.addCages();
        IGraph network = this.createFlowNetwork();
        // TODO solve modified tamassia algorithm
        // TODO replace cages with nodes
    }

    private void addCages() {
        for (INode node : this.graph.getNodes()) {
            // Check for node degree
            if (node.getAdjacentEdgeCount() > MAXDEGREE) {
                continue;
            }

            // Create temporary list to not break the iterator
            LinkedList<IEdge> edges = new LinkedList<IEdge>();
            for (IEdge edge : node.adjacentEdges()) {
                edges.add(edge);
            }

            // Create cage nodes
            INode first = null;
            INode previous = null;
            INode current = null;
            for (IEdge edge : edges) {
                previous = current;
                current = this.graph.addNode();
                if (first == null) {
                    first = current;
                }
                if (previous != null) {
                    this.graph.addEdge(previous, current);
                }
                edge.move(node.getAdjacentNode(edge), current);
            }
            if (first != null) {
                this.graph.addEdge(current, first);
            }
        }
    }

    private IGraph createFlowNetwork() {
        IGraph network = new PGraphFactory().createEmptyGraph();
        HashMap<IGraphElement, INode> map = new HashMap<IGraphElement, INode>();

        // Creating source nodes for every graph node
        for (INode node : this.graph.getNodes()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, node);
            // TODO node is source: 4
            map.put(node, newnode);
        }

        // Creating sink nodes for every graph face
        for (IFace face : this.graph.getFaces()) {
            INode newnode = network.addNode();
            newnode.setProperty(NETWORKTOGRAPH, face);
            // TODO node is sink: 2*degree-4 for internal, 2*degree+4 for external
            map.put(face, newnode);

            // Creating arcs for every node adjacent to the face
            for (INode node : face.getNodes()) {
                if (!map.containsKey(node)) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                network.addEdge(map.get(node), map.get(face), true);
                // TODO edges has lower bound 1, capacity 4, cost 0
            }
        }

        // TODO create edges for edges in dual graph

        return network;
    }
}
