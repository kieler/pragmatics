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
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
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
        PGraph pgraph = new PGraph();
        PNode[] nodeArray = new PNode[nodes];

        // Create nodes
        for (int i = 0; i < nodes; i++) {
            nodeArray[i] = pgraph.addNode();
        }

        // Create edges
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < i; j++) {
                pgraph.addEdge(nodeArray[i], nodeArray[j]);
            }
        }
        return pgraph;
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
        PGraph pgraph = new PGraph();
        PNode[] nodeArray = new PNode[nodes];

        // Create nodes
        for (int i = 0; i < nodes; i++) {
            nodeArray[i] = pgraph.addNode();
        }

        // Create edges
        for (int i = 0; i < edges; i++) {
            int i1 = (int) Math.floor(Math.random() * nodes);
            int i2 = (int) Math.floor(Math.random() * nodes);
            pgraph.addEdge(nodeArray[i1], nodeArray[i2]);
        }
        return pgraph;
    }

    /**
     * Create a graph base on an existing graph. This method does not guarantee an exact copy of the
     * graph (e.g. indices may be different), but the embedding should be equal.
     * 
     * @param pgraph
     *            the graph to copy
     * @return a copy of the given graph
     */
    public PGraph createGraphCopy(final PGraph pgraph) {
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes
        PGraph copy = new PGraph();
        HashMap<PNode, PNode> nodes = new HashMap<PNode, PNode>(pgraph.getNodeCount() * 2);

        // Adding Nodes
        for (PNode node : pgraph.getNodes()) {
            nodes.put(node, copy.addNode(node.getType()));
        }

        // Adding Edges
        for (PEdge edge : pgraph.getEdges()) {
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
     * @param pgraph
     *            the graph to create the dual graph of
     * @return the dual graph to this graph
     */
    public PGraph createDualGraph(final PGraph pgraph) {
        HashMap<PFace, PNode> map = new HashMap<PFace, PNode>(pgraph.getFaceCount() * 2);
        PGraph dual = new PGraph();

        // Add the dual nodes from graph faces
        for (PFace face : pgraph.getFaces()) {
            PNode node = dual.addNode();
            node.setProperty(Properties.TODUALGRAPH, face);
            face.setProperty(Properties.TODUALGRAPH, node);
            map.put(face, node);
        }

        // Build the edges based on the neighboring faces
        for (PEdge edge : pgraph.getEdges()) {
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

        // TODO the embedding of the graph is not the same as in the diagram.
        // See kite example as example. Should this be the same?
        PGraph pgraph = new PGraph();
        HashMap<KNode, PNode> map = new HashMap<KNode, PNode>(kgraph.getChildren().size() * 2);
        pgraph.setProperty(Properties.ORIGIN, kgraph);

        // Adding all user properties to the graph
        pgraph.copyProperties(kgraph.getData(KShapeLayout.class));

        // Adding Nodes
        for (KNode knode : kgraph.getChildren()) {
            PNode node = null;
            if (knode.getChildren().size() > 0) {
                node = pgraph.addNode(NodeType.COMPOUND);
            } else {
                node = pgraph.addNode(NodeType.NORMAL);
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
                    PEdge edge = pgraph.addEdge(source, target, true);
                    edge.setProperty(Properties.ORIGIN, kedge);
                }
            }
        }
        return pgraph;
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
        PGraph pgraph = new PGraph();
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
                    nodes[i] = pgraph.addNode();
                }
                break;

            case 'e': // Edge line contains edge description
                if (nodes == null) {
                    throw new InconsistentGraphModelException(
                            "DIMACS file does not describe a valid graph.");
                }
                int n1 = Integer.parseInt(items[1]);
                int n2 = Integer.parseInt(items[2]);
                pgraph.addEdge(nodes[n1], nodes[n2], false);
                break;

            default:
                throw new InconsistentGraphModelException(
                        "DIMACS file does not describe a valid graph.");
            }

            line = input.readLine();
        }
        input.close();
        return pgraph;
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
        GridRepresentation grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);
        // the pgraph must not contain any planarity dummynodes.

        float borderSpacing = pgraph.getProperty(Properties.BORDER_SPACING);

        spacing = pgraph.getProperty(Properties.SPACING);

        startX = borderSpacing;
        startY = grid.getHeight() * spacing + borderSpacing;

        // TODO introduce minimum spacing or use spacing above as minimum.
        float minSpacing = 40;

        // first determine original nodes coordinates.
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.get(x, y) != null) {
                    grid.get(x, y).setPostion(startX + x * spacing, startY - y * spacing);
                }
            }
        }

        // arrange nodes if minSpacing of nodes is not ensured for the horizontal and
        // vertical direction!
        // horizontal go through: take lower and upper nodes and go horizontal. check
        // all nodes for minspace condition.
        for (int y = 0; y < grid.getHeight() - 1; y++) {
            float y1;
            float y2;
            float sum;
            float minDistance = minSpacing;
            // recognize a too small distance between two nodes.
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.get(x, y) != null && grid.get(x, y).hasProperties()
                        && grid.get(x, y).getProperty(Properties.ORIGIN) != null) {
                    KNode property = (KNode) grid.get(x, y).getProperty(Properties.ORIGIN);
                    KShapeLayout data = property.getData(KShapeLayout.class);
                    y1 = data.getYpos() - data.getHeight() / 2;
                } else {
                    // bendpoint or no point
                    y1 = startY - y * spacing;
                }
                if (grid.get(x, y + 1) != null && grid.get(x, y + 1).hasProperties()
                        && grid.get(x, y + 1).getProperty(Properties.ORIGIN) != null) {
                    KNode property = (KNode) grid.get(x, y + 1).getProperty(Properties.ORIGIN);
                    KShapeLayout data = property.getData(KShapeLayout.class);
                    y2 = data.getYpos() + data.getHeight() / 2;
                } else {
                    // bendpoint or no point
                    y2 = startY - (y + 1) * spacing;
                }
                sum = y1 - y2;
                if (sum < minDistance) {
                    // save the biggest deviation of minSum.
                    minDistance = sum;
                }
            }
            if (minDistance < minSpacing) {

                float restDistance = (minSpacing - minDistance) / 2;

                // if too small distance is found, adjust the grid position according this distance.
                // move all grid segments in bottom direction.
                for (int i = 0; i <= y; i++) {
                    for (int x = 0; x < grid.getWidth(); x++) {
                        if (grid.get(x, i) != null) {
                            KShapeLayout nodeLayout = ((KNode) grid.get(x, i).getProperty(
                                    Properties.ORIGIN)).getData(KShapeLayout.class);
                            nodeLayout.setYpos(nodeLayout.getYpos() + restDistance);
                        }
                    }
                }

                // move all grid segments in top direction.
                for (int i = y + 1; i < grid.getHeight(); i++) {
                    for (int x = 0; x < grid.getWidth(); x++) {
                        if (grid.get(x, i) != null) {
                            KShapeLayout nodeLayout = ((KNode) grid.get(x, i).getProperty(
                                    Properties.ORIGIN)).getData(KShapeLayout.class);
                            nodeLayout.setYpos(nodeLayout.getYpos() - restDistance);
                        }
                    }

                }

            }

        }

        // horizontal go through.

        for (int x = 0; x < grid.getWidth() - 1; x++) {
            float x1;
            float x2;
            float sum;
            float minDistance = minSpacing;
            // recognize a too small distance between two nodes.
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.get(x, y) != null && grid.get(x, y).hasProperties()
                        && grid.get(x, y).getProperty(Properties.ORIGIN) != null) {
                    KNode property = (KNode) grid.get(x, y).getProperty(Properties.ORIGIN);
                    KShapeLayout data = property.getData(KShapeLayout.class);
                    x1 = data.getXpos() + data.getWidth() / 2;
                } else {
                    // bendpoint or no point
                    x1 = startX + x * spacing;
                }
                if (grid.get(x + 1, y) != null && grid.get(x + 1, y).hasProperties()
                        && grid.get(x + 1, y).getProperty(Properties.ORIGIN) != null) {
                    KNode property = (KNode) grid.get(x + 1, y).getProperty(Properties.ORIGIN);
                    KShapeLayout data = property.getData(KShapeLayout.class);
                    x2 = data.getXpos() - data.getWidth() / 2;
                } else {
                    // bendpoint or no point
                    x2 = startX + (x + 1) * spacing;
                }
                sum = x2 - x1;
                if (sum < minDistance) {
                    // adjust the grid.
                    minDistance = sum;
                }
            }
            if (minDistance < minSpacing) {
                float restDistance = (minSpacing - minDistance) / 2;

                // if too small distance is found, adjust the grid position according this distance.
                // move all grid segments in bottom direction.
                for (int i = 0; i <= x; i++) {
                    for (int y = 0; y < grid.getHeight(); y++) {
                        if (grid.get(i, y) != null) {
                            // TODO this is not enough, because the bendpoints aren't adjusted!!!!
                            KShapeLayout nodeLayout = ((KNode) grid.get(i, y).getProperty(
                                    Properties.ORIGIN)).getData(KShapeLayout.class);
                            nodeLayout.setYpos(nodeLayout.getXpos() - restDistance);
                        }
                    }
                }

                // move all grid segments in top direction.
                for (int i = x + 1; i < grid.getWidth(); i++) {
                    for (int y = 0; y < grid.getHeight(); y++) {
                        if (grid.get(i, y) != null) {
                            KShapeLayout nodeLayout = ((KNode) grid.get(i, y).getProperty(
                                    Properties.ORIGIN)).getData(KShapeLayout.class);
                            nodeLayout.setYpos(nodeLayout.getXpos() + restDistance);
                        }
                    }
                }
            }

        }

        // construct bendpoints for each node for which there is no original node.
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.get(x, y) != null) {
                    if (!grid.get(x, y).hasProperties()
                            || (grid.get(x, y).getProperty(Properties.BENDPOINT) != null)) {
                        constructBendPointEdge(x, y, grid);
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
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                if (grid.get(x, y) != null) {
                    if (grid.get(x, y).hasProperties()
                            && grid.get(x, y).getProperty(Properties.ORIGIN) instanceof KNode) {
                        // search for the pnodes that represents a knode.
                        KShapeLayout nodeLayout = ((KNode) grid.get(x, y).getProperty(
                                Properties.ORIGIN)).getData(KShapeLayout.class);
                        nodeLayout.setXpos(startX + x * spacing - nodeLayout.getWidth() / 2);
                        nodeLayout.setYpos(startY - y * spacing - nodeLayout.getHeight() / 2);
                    }
                }
            }
        }

    }

    /**
     * Removes all bend point nodes from grid and adds them as bendpoints to the original edge.
     * 
     * @param x
     *            , index of the grid.
     * @param y
     *            , index of the grid.
     * @param grid
     *            , the grid.
     */
    private void constructBendPointEdge(final int x, final int y, final GridRepresentation grid) {

        // it is enough to check if it has a kedge as origin, and if not, it must be a
        // bendpoint, because other dummynodes are removed in the last processors.
        PNode startNode = grid.get(x, y);
        Iterator<PEdge> startNodeIterator = startNode.getEdges().iterator();

        // search orginal nodes and edge.
        Pair<PNode, PEdge> pair1 = searchOriginals(startNode, startNodeIterator.next());
        Pair<PNode, PEdge> pair2 = searchOriginals(startNode, startNodeIterator.next());

        PNode originalNode1 = pair1.getFirst();
        PNode originalNode2 = pair2.getFirst();
        PEdge originalEdge = (pair1.getSecond() != null) ? pair1.getSecond() : pair2.getSecond();

        PNode currentNode;
        // original edge has to be adjacent to one of the original nodes.
        if (originalEdge.getSource() == originalNode1 || originalEdge.getTarget() == originalNode1) {
            currentNode = originalNode1;
        } else {
            currentNode = originalNode2;
        }

        PEdge currentEdge = originalEdge;

        List<int[]> removableNodes = new LinkedList<int[]>();

        // add bendpoints
        while (true) {
            currentNode = currentEdge.getOppositeNode(currentNode);
            if (currentNode == originalNode1 || currentNode == originalNode2) {
                // the path is finished.
                break;
            }

            int[] coordinates = grid.search(currentNode);
            originalEdge.getBendPoints().add(this.startX + coordinates[0] * this.spacing,
                    this.startY - coordinates[1] * spacing);

            // filter next edge.
            Iterator<PEdge> iterator = currentNode.adjacentEdges().iterator();
            PEdge possibleEdge = iterator.next();
            currentEdge = (possibleEdge == currentEdge) ? iterator.next() : possibleEdge;
            removableNodes.add(coordinates);
        }

        for (int[] point : removableNodes) {
            this.graph.removeNode(grid.get(point[0], point[1]));
            grid.set(point[0], point[1], null);
        }

        // change the direction of the original edge.
        if (originalEdge.getSource() == originalNode1 || originalEdge.getTarget() == originalNode2) {
            graph.changeEdge(originalEdge, originalNode1, originalNode2);
        } else if (originalEdge.getTarget() == originalNode1
                || originalEdge.getSource() == originalNode2) {
            graph.changeEdge(originalEdge, originalNode2, originalNode1);
        }
    }

    /**
     * Searches for the original node and if one original edge exists on the path, returns it, too.
     * 
     * @param startNode
     * @param edge
     * @return a {@link Pair} of original node and the properly found originalEdge.
     */
    private Pair<PNode, PEdge> searchOriginals(final PNode startNode, final PEdge edge) {
        PEdge currentEdge = edge;
        PNode currentNode = startNode;
        PEdge resultEdge = null;
        while (true) {
            currentNode = currentEdge.getOppositeNode(currentNode);
            if (currentEdge.hasProperties() && currentEdge.getProperty(Properties.ORIGIN) != null) {
                resultEdge = currentEdge;
            }
            if (currentNode.hasProperties() && currentNode.getProperty(Properties.ORIGIN) != null) {
                return new Pair<PNode, PEdge>(currentNode, resultEdge);
            }

            // filter next edge.
            Iterator<PEdge> iterator = currentNode.adjacentEdges().iterator();
            PEdge possibleEdge = iterator.next();
            currentEdge = (possibleEdge == currentEdge) ? iterator.next() : possibleEdge;

        }
    }
}
