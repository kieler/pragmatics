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
package de.cau.cs.kieler.klay.planar.graph.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.INode.NodeType;

/**
 * An instance of a graph factory for {@code PGraph} creation. This class implements the factory
 * method design pattern to create instances of graphs.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 */
public class PGraphFactory implements IGraphFactory {

    /**
     * {@inheritDoc}
     */
    public IGraph createEmptyGraph() {
        return new PGraph();
    }

    /**
     * {@inheritDoc}
     */
    public IGraph createFullGraph(final int nodes) throws InconsistentGraphModelException {
        IGraph graph = new PGraph();
        INode[] nodeArray = new INode[nodes];

        // Create nodes
        for (int i = 0; i < nodes; i++) {
            nodeArray[i] = graph.addNode();
        }

        // Create edges
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < i; j++) {
                graph.addEdge(nodeArray[i], nodeArray[j]);
            }
        }
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public IGraph createRandomGraph(final int nodes, final int edges)
            throws InconsistentGraphModelException {
        IGraph graph = new PGraph();
        INode[] nodeArray = new INode[nodes];

        // Create nodes
        for (int i = 0; i < nodes; i++) {
            nodeArray[i] = graph.addNode();
        }

        // Create edges
        for (int i = 0; i < edges; i++) {
            int i1 = (int) Math.floor(Math.random() * nodes);
            int i2 = (int) Math.floor(Math.random() * nodes);
            graph.addEdge(nodeArray[i1], nodeArray[i2]);
        }
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public IGraph createGraphFromKGraph(final KNode kgraph) throws InconsistentGraphModelException {
        // TODO check for hyper nodes
        // TODO check for directed/undirected edges
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes
        PGraph graph = new PGraph();
        HashMap<KNode, INode> map = new HashMap<KNode, INode>(kgraph.getChildren().size() * 2);

        // Adding Nodes
        for (KNode knode : kgraph.getChildren()) {
            INode node = null;
            if (knode.getChildren().size() > 0) {
                node = graph.addNode(NodeType.COMPOUND);
            } else {
                node = graph.addNode(NodeType.NORMAL);
            }
            node.setProperty(IGraph.TOKGRAPH, knode);
            map.put(knode, node);
        }

        // Adding Edges
        for (KNode knode : kgraph.getChildren()) {
            for (KEdge kedge : knode.getOutgoingEdges()) {
                if (!map.containsKey(kedge.getSource()) || !map.containsKey(kedge.getTarget())) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                INode source = map.get(kedge.getSource());
                INode target = map.get(kedge.getTarget());
                IEdge edge = graph.addEdge(source, true, target, true, true);
                edge.setProperty(IGraph.TOKGRAPH, kedge);
            }
        }

        // Adding Faces
        graph.generateFaces();
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public IGraph createGraphFromDIMACS(final File dimacs) throws IOException,
            InconsistentGraphModelException {
        BufferedReader input = new BufferedReader(new FileReader(dimacs));
        IGraph graph = new PGraph();
        INode[] nodes = null;

        String line = input.readLine();
        while (line != null) {
            String[] items = line.trim().split("\\s+");
            switch (items[0].charAt(0)) {
            case 'c': // Skip comment line
                continue;

            case 'p': // Problem line contains number of nodes
                int n = Integer.parseInt(items[2]);
                nodes = new INode[n];
                for (int i = 0; i < n; i++) {
                    nodes[i] = graph.addNode();
                }
                break;

            case 'e': // Edge line contains edge description
                if (nodes == null) {
                    throw new InconsistentGraphModelException(
                            "DIMACS file does not describe a valid graph.");
                }
                int n1 = Integer.parseInt(items[1]);
                int n2 = Integer.parseInt(items[2]);
                graph.addEdge(nodes[n1], nodes[n2], false);
                break;

            default:
                throw new InconsistentGraphModelException(
                        "DIMACS file does not describe a valid graph.");
            }

            line = input.readLine();
        }
        input.close();
        return graph;
    }

}
