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
import java.util.Iterator;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
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

    private PNode[][] grid;
    private float startX;
    private float startY;
    private float spacing;
    private PGraph graph;

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
     * Apply the computed layout of a planar graph to the original graph. Builds implicit the bend
     * points of the edges.
     * 
     * @param pgraph
     *            the graph for which layout is applied
     */
    public void applyLayout(final PGraph pgraph) {
        this.graph = pgraph;
        grid = pgraph.getProperty(Properties.GRID_DRAWING);
        // the pgraph must not contain any planarity dummynodes.

        float borderSpacing = pgraph.getProperty(Properties.BORDER_SPACING);

        spacing = pgraph.getProperty(Properties.SPACING);

        startX = borderSpacing;

        startY = grid.length * spacing + borderSpacing;

        // first determine original nodes coordinates.
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] != null) {
                    if (grid[x][y].hasProperties()
                            && grid[x][y].getProperty(Properties.ORIGIN) instanceof KNode) {
                        // if there is a original knode for that pnode, then set it to the diagram,
                        // depending on spacing and border_spacing.
                        KShapeLayout nodeLayout = ((KNode) grid[x][y]
                                .getProperty(Properties.ORIGIN)).getData(KShapeLayout.class);
                        nodeLayout.setXpos(startX + x * spacing);
                        nodeLayout.setYpos(startY - y * spacing);
                    }
                }
            }
        }

        // TODO determine the maximal segment width for the horizontal and vertical direction!
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {

            }
        }

        // construct bendpoints for each node for which there is no original node.
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] != null) {
                    if (!grid[x][y].hasProperties()
                            || !(grid[x][y].getProperty(Properties.ORIGIN) instanceof KNode)) {
                        constructBendPointEdge(x, y);
                    }
                }
            }
        }

        // map all PNode edges with bendpoints and source and target coordinates
        // to the original kedges
        for (PEdge edge : pgraph.getEdges()) {
            KEdge originEdge = (KEdge) edge.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = originEdge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = edge.getBendPoints();

            // Now every edge contains of a original start and end node.
            // Thus we do not need to check, whether the node has a original knode.
            KNode source = (KNode) edge.getSource().getProperty(Properties.ORIGIN);
            KNode target = (KNode) edge.getTarget().getProperty(Properties.ORIGIN);

            // add source and target
            bendPoints.addFirst(source.getData(KShapeLayout.class).createVector());
            bendPoints.addLast(target.getData(KShapeLayout.class).createVector());

            // apply the bend points
            edgeLayout.applyVectorChain(bendPoints);
        }

        // moves all original nodes to the left/top to let the edges walk to the center
        // of the nodes.
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] != null) {
                    if (grid[x][y].hasProperties()
                            && grid[x][y].getProperty(Properties.ORIGIN) instanceof KNode) {
                        // search for the pnodes that represents a knode.
                        KShapeLayout nodeLayout = ((KNode) grid[x][y]
                                .getProperty(Properties.ORIGIN)).getData(KShapeLayout.class);
                        nodeLayout.setXpos(startX + x * spacing - nodeLayout.getWidth() / 2);
                        nodeLayout.setYpos(startY - y * spacing - nodeLayout.getHeight() / 2);
                    }
                }
            }
        }

    }

    /**
     * Goes through the grid, and if a bendpoint is found (meaning a point with no knode as origin),
     * it is merged with the original edge that goes into that point. Afterwards it is a bendpoint
     * of the original edge.
     * 
     * @param x
     *            , index of the grid.
     * @param y
     *            , index of the grid.
     */
    private void constructBendPointEdge(final int x, final int y) {

        // it is enough to check if it has a kedge as origin, and if not, it must be a
        // bendpoint, because other dummynodes are removed in the last processors.

        Iterator<PEdge> iterator = grid[x][y].getEdges().iterator();
        PEdge nodeEdge1 = iterator.next();
        PNode node1 = nodeEdge1.getOppositeNode(grid[x][y]);
        PEdge nodeEdge2 = iterator.next();
        PNode node2 = nodeEdge2.getOppositeNode(grid[x][y]);

        if (nodeEdge1.hasProperties() && nodeEdge1.getProperty(Properties.ORIGIN) instanceof KEdge) {
            // then that is the original edge. So the other edge has to be removed
            // remove edge 2 and add a bendpoint to edge 1.

            // TODO think of the embedding, I guess this goes lost.
            if (nodeEdge1.getSource() == node1) {
                graph.changeEdge(nodeEdge1, null, node2);
            } else {
                // then it has to be the target
                graph.changeEdge(nodeEdge1, node2, null);
            } // TODO check if node1 can be removed!
            nodeEdge1.getBendPoints().add(startX + x * spacing, startY - y * spacing);
        } else if (nodeEdge2.hasProperties()
                && nodeEdge2.getProperty(Properties.ORIGIN) instanceof KEdge) {
            if (nodeEdge2.getSource() == node2) {
                graph.changeEdge(nodeEdge2, null, node1);
            } else {
                graph.changeEdge(nodeEdge2, node1, null);
                // then it has to be the target
            } // TODO check if node1 can be removed!
            nodeEdge2.getBendPoints().add(startX + x * spacing, startY - y * spacing);

        } else {
            boolean found = false;
            while (!found) {
                found = true;
                // go along edge one until a original edge is found otherwise go in the other
                // direction
            }
            // node2 has properties as origin a knode.
            // the bend point is surrounded by two dummy edges. we have to go around until not
            // such a case is found!
            // I guess here it would be sufficient to check if node1 or node2 is a original node.
        }
        this.graph.removeNode(grid[x][y]);
        grid[x][y] = null;
    }
}
