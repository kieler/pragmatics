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
package de.cau.cs.kieler.klay.planar.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * This factory provides some methods to create a {@code PGraph}.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author pkl
 */
public class PGraphFactory {

    /**
     * Create an empty graph instance. The resulting graph will not contain any edges or nodes.
     * 
     * @return an empty graph
     */

    public PGraph createEmptyGraph() {
        return new PGraph();
    }

    /**
     * Create a full graph. In a full graph, every node is connected to every other node via an
     * edge.
     * 
     * @param nodes
     *            the number of nodes in the graph
     * @return a full graph with the given number of nodes
     */
    public PGraph createFullGraph(final int nodes) {
        PGraph graph = new PGraph();
        PNode[] nodeArray = new PNode[nodes];

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
     * Create a random graph with a given number of nodes and edges.
     * 
     * @param nodes
     *            the number of nodes in the random graph
     * @param edges
     *            the number of edges in the random graph
     * @return a random graph
     */
    public PGraph createRandomGraph(final int nodes, final int edges) {
        PGraph graph = new PGraph();
        PNode[] nodeArray = new PNode[nodes];

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
     * Create a graph base on an existing graph. This method does not guarantee an exact copy of the
     * graph (e.g. indices may be different), but the embedding should be equal.
     * 
     * @param graph
     *            the graph to copy
     * @return a copy of the given graph
     */
    public PGraph createGraphCopy(final PGraph graph) {
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes
        PGraph copy = new PGraph();
        HashMap<PNode, PNode> nodes = new HashMap<PNode, PNode>(graph.getNodeCount() * 2);

        // Adding Nodes
        for (PNode node : graph.getNodes()) {
            nodes.put(node, copy.addNode(node.getType()));
        }

        // Adding Edges
        for (PEdge edge : graph.getEdges()) {
            if (!nodes.containsKey(edge.getSource()) || !nodes.containsKey(edge.getTarget())) {
                throw new InconsistentGraphModelException(
                        "Attempted to link non-existent nodes by an edge.");
            }
            PNode source = nodes.get(edge.getSource());
            PNode target = nodes.get(edge.getTarget());
            copy.addEdge(source, target, edge.isDirected());
        }
        return copy;
    }

    /**
     * Generate the dual graph of another graph. The dual graph is a graph, that has a node for
     * every face in the original graph, and edges between neighboring faces.
     * 
     * @param graph
     *            the graph to create the dual graph of
     * @return the dual graph to this graph
     */
    public PGraph createDualGraph(final PGraph graph) {
        HashMap<PFace, PNode> map = new HashMap<PFace, PNode>(graph.getFaceCount() * 2);
        PGraph dual = new PGraph();

        // Add the dual nodes from graph faces
        for (PFace face : graph.getFaces()) {
            PNode node = dual.addNode();
            node.setProperty(Properties.TODUALGRAPH, face);
            face.setProperty(Properties.TODUALGRAPH, node);
            map.put(face, node);
        }

        // Build the edges based on the neighboring faces
        for (PEdge edge : graph.getEdges()) {
            PNode source = map.get(edge.getLeftFace());
            PNode target = map.get(edge.getRightFace());
            PEdge e = dual.addEdge(source, target, false);
            e.setProperty(Properties.TODUALGRAPH, edge);
            edge.setProperty(Properties.TODUALGRAPH, e);
        }

        return dual;
    }

    /**
     * Create a graph from a {@code KGraph} instance.
     * 
     * @param kgraph
     *            a {@code KNode} to base the graph upon
     * @return a graph corresponding to {@code kgraph}
     */
    public PGraph createGraphFromKGraph(final KNode kgraph) {
        // TODO check for directed/undirected edges
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes
        PGraph graph = new PGraph();
        HashMap<KNode, PNode> map = new HashMap<KNode, PNode>(kgraph.getChildren().size() * 2);
        graph.setProperty(Properties.ORIGIN, kgraph);

        // Adding all user properties to the graph
        graph.copyProperties(kgraph.getData(KShapeLayout.class));

        // Adding Nodes
        for (KNode knode : kgraph.getChildren()) {
            PNode node = null;
            if (knode.getChildren().size() > 0) {
                node = graph.addNode(NodeType.COMPOUND);
            } else {
                node = graph.addNode(NodeType.NORMAL);
            }
            node.setProperty(Properties.ORIGIN, knode);
            map.put(knode, node);
        }

        // Adding Edges
        for (KNode knode : kgraph.getChildren()) {
            for (KEdge kedge : knode.getOutgoingEdges()) {
                if (!map.containsKey(kedge.getSource()) || !map.containsKey(kedge.getTarget())) {
                    throw new InconsistentGraphModelException(
                            "Attempted to link non-existent nodes by an edge.");
                }
                PNode source = map.get(kedge.getSource());
                PNode target = map.get(kedge.getTarget());
                // FIXME planarization fails if there are multi-edges or
                // self-loops, so suppress them
                if (!source.isAdjacent(target) && source != target) {
                    PEdge edge = graph.addEdge(source, target, true);
                    edge.setProperty(Properties.ORIGIN, kedge);
                }
            }
        }
        return graph;
    }

    /**
     * Create a graph based on a file in the DIMACS format.
     * 
     * @param dimacs
     *            the file containing the graph info
     * @return a graph based on the DIMACS file
     * @throws IOException
     *             if problems during file reading occur
     */
    public PGraph createGraphFromDIMACS(final File dimacs) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(dimacs));
        PGraph graph = new PGraph();
        PNode[] nodes = null;

        String line = input.readLine();
        while (line != null) {
            String[] items = line.trim().split("\\s+");
            switch (items[0].charAt(0)) {
            case 'c': // Skip comment line
                break;

            case 'p': // Problem line contains number of nodes
                int n = Integer.parseInt(items[2]);
                nodes = new PNode[n + 1];
                for (int i = 0; i < nodes.length; i++) {
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

    /**
     * Apply the computed layout of a planar graph to the original graph.
     * 
     * @param pgraph
     *            the graph for which layout is applied
     */
    public void applyLayout(final PGraph pgraph) {
        KNode kgraph = (KNode) pgraph.getProperty(Properties.ORIGIN);
        // determine the border spacing, which influences the offset
        KShapeLayout parentLayout = kgraph.getData(KShapeLayout.class);
        Float spacing = parentLayout.getProperty(LayoutOptions.SPACING);

        // float borderSpacing = pgraph.getProperty(LayoutOptions.BORDER_SPACING);
        // if (borderSpacing < 0) {
        // borderSpacing = Properties.DEF_SPACING;
        // }
        // pgraph.setProperty(LayoutOptions.BORDER_SPACING, borderSpacing);

        // calculate the offset from border spacing and node distribution
        // double minXPos = Integer.MAX_VALUE, minYPos = Integer.MAX_VALUE;
        // double maxXPos = Integer.MIN_VALUE, maxYPos = Integer.MIN_VALUE;
        // for (PNode node : pgraph.getNodes()) {
        // KVector pos = node.getPosition();
        // KVector size = node.getSize();
        // minXPos = Math.min(minXPos, pos.x - size.x / 2);
        // minYPos = Math.min(minYPos, pos.y - size.y / 2);
        // maxXPos = Math.max(maxXPos, pos.x + size.x / 2);
        // maxYPos = Math.max(maxYPos, pos.y + size.y / 2);
        // }
        // KVector offset = new KVector(borderSpacing - minXPos, borderSpacing - minYPos);

        // process the nodes
        // for (PNode pnode : pgraph.getNodes()) {
        // Object object = pnode.getProperty(Properties.ORIGIN);
        //
        // if (object instanceof KNode) {
        // // set the node position
        // KNode knode = (KNode) object;
        // KShapeLayout nodeLayout = knode.getData(KShapeLayout.class);
        // KVector nodePos = pnode.getPosition().add(offset);
        // nodeLayout.setXpos((float) nodePos.x - nodeLayout.getWidth() / 2);
        // nodeLayout.setYpos((float) nodePos.y - nodeLayout.getHeight() / 2);
        // }
        // }
        //
        // // process the edges
        // for (PEdge pedge : pgraph.getEdges()) {
        // KEdge kedge = (KEdge) pedge.getProperty(Properties.ORIGIN);
        // if (kedge != null) {
        // KEdgeLayout edgeLayout = kedge.getData(KEdgeLayout.class);
        // // edgeLayout.getBendPoints().clear();
        // edgeLayout.getSourcePoint().applyVector(pedge.getSourcePoint());
        // edgeLayout.getTargetPoint().applyVector(pedge.getTargetPoint());
        // }
        // }

        // process the labels
        // for (PLabel plabel : pgraph.getLabels()) {
        // KLabel klabel = (KLabel) flabel.getProperty(Properties.ORIGIN);
        // KShapeLayout klabelLayout = klabel.getData(KShapeLayout.class);
        // KVector labelPos = flabel.getPosition().add(offset);
        // klabelLayout.applyVector(labelPos);
        // }

        // set up the parent node
        // KInsets insets = parentLayout.getInsets();
        // float width = (float) (maxXPos - minXPos) + 2 * borderSpacing + insets.getLeft()
        // + insets.getRight();
        // float height = (float) (maxYPos - minYPos) + 2 * borderSpacing + insets.getTop()
        // + insets.getBottom();
        // KimlUtil.resizeNode(kgraph, width, height, false);
    }

}
