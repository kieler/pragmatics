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
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.planar.PConstants;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.intermediate.GridRepresentation;
import de.cau.cs.kieler.klay.planar.p3compact.HighDegreeNodeStrategy;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * This factory provides some methods to create a {@code PGraph}.
 * 
 * @see de.cau.cs.kieler.klay.planar.graph.PGraph {@code PGraph}
 * 
 * @author ocl
 * @author pkl
 * 
 */
public class PGraphFactory {

    /** Minimum distance between two nodes. */
    private static final int MIN_DIST = 10;

    /** Start position of the horizontal direction. */
    private static float startX;

    /** Start position of the vertical direction. */
    private static float startY;

    /** Spacing between the nodes. */
    private static float spacing;

    /**
     * Create an empty graph instance. The resulting graph will not contain any edges or nodes.
     * 
     * @return an empty graph
     */

    public static PGraph createEmptyGraph() {
        return new PGraph();
    }

    /**
     * Create a full graph. In a full graph, every node is connected to every other node via an
     * edge.
     * 
     * @param nodesCount
     *            the number of nodes in the graph
     * @return a full graph with the given number of nodes
     */
    public static PGraph createFullGraph(final int nodesCount) {
        PGraph graph = new PGraph();
        PNode[] nodeArray = new PNode[nodesCount];

        // Create nodes
        for (int i = 0; i < nodesCount; i++) {
            nodeArray[i] = graph.addNode();
        }

        // Create edges
        for (int i = 0; i < nodesCount; i++) {
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
    public static PGraph createRandomGraph(final int nodes, final int edges) {
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
    public static PGraph createGraphCopy(final PGraph pgraph) {
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
    public static PGraph createDualGraph(final PGraph pgraph) {
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
    public static PGraph createGraphFromKGraph(final KNode kgraph) {
        PGraph pgraph = new PGraph();
        HashMap<KNode, PNode> map = new HashMap<KNode, PNode>(kgraph.getChildren().size() * 2);
        pgraph.copyProperties(kgraph.getData(KShapeLayout.class));
        pgraph.setProperty(Properties.ORIGIN, kgraph);

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

                if (!source.isAdjacent(target)) {
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
    public static void applyLayout(final PGraph pgraph) {

        GridRepresentation grid = pgraph.getProperty(Properties.GRID_REPRESENTATION);
        float borderSpacing = pgraph.getProperty(Properties.BORDER_SPACING);
        HighDegreeNodeStrategy hdStrategy = pgraph
                .getProperty(Properties.HIGH_DEGREE_NODE_STRATEGY);
        PGraphFactory.determineSpacing(pgraph);

        PGraphFactory.startX = borderSpacing;
        PGraphFactory.startY = borderSpacing;

        // handle high degree nodes!
        PGraphFactory.performHDNodes(pgraph, grid);

        // first determine nodes coordinates
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                PNode pNode = grid.get(x, y);
                if (pNode != null) {
                    if (pNode.getProperty(Properties.EXPANSION_CYCLE) == null
                            || hdStrategy == HighDegreeNodeStrategy.QUOD) {
                        pNode.setPostion(((float) PGraphFactory.mapNodeToRealX(x)),
                                ((float) PGraphFactory.mapNodeToRealY(y)));
                    } else if (hdStrategy == HighDegreeNodeStrategy.GIOTTO) {
                        // set position to the middle point.
                        float xCoordinate = (float) PGraphFactory.mapNodeToRealX(x);
                        float yCoordinate = (float) PGraphFactory.mapNodeToRealY(y);
                        KShapeLayout nodeLayout = ((KNode) pNode.getProperty(Properties.ORIGIN))
                                .getData(KShapeLayout.class);
                        pNode.setPostion(xCoordinate + nodeLayout.getWidth() / 2, yCoordinate
                                + nodeLayout.getHeight() / 2);
                    }

                }
            }
        }

        // update coordinates of each row and col with respect to the width and height of
        // each node
        PGraphFactory.updateCoordinates();

        // map all PNode edges with bendpoints and source and target coordinates
        // to the original kedges
        for (PEdge edge : pgraph.getEdges()) {

            // Set bend-points in correct
            for (KVector vec : edge.getBendPoints()) {
                vec.x = PGraphFactory.mapEdgeToRealX(vec.x);
                vec.y = PGraphFactory.mapEdgeToRealY(vec.y);
            }

            KEdge originEdge = (KEdge) edge.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = originEdge.getData(KEdgeLayout.class);
            KVectorChain bendPoints = edge.getBendPoints();

            // set source and target position
            if (pgraph.getProperty(Properties.HIGH_DEGREE_NODE_STRATEGY) 
                    == HighDegreeNodeStrategy.GIOTTO) {
                Pair<Integer, Integer> startPos = edge.getProperty(Properties.START_POSITION);
                if (startPos != null) {
                    bendPoints.addFirst(
                            PGraphFactory.mapEdgeToRealX(startPos.getFirst().intValue()),
                            PGraphFactory.mapEdgeToRealY(startPos.getSecond().intValue()));
                } else {
                    KNode source = (KNode) edge.getSource().getProperty(Properties.ORIGIN);
                    bendPoints.addFirst(source.getData(KShapeLayout.class).createVector());
                }

                Pair<Integer, Integer> targetPos = edge.getProperty(Properties.TARGET_POSITION);
                if (targetPos != null) {
                    bendPoints.addLast(
                            PGraphFactory.mapEdgeToRealX(targetPos.getFirst().intValue()),
                            PGraphFactory.mapEdgeToRealY(targetPos.getSecond().intValue()));
                } else {
                    KNode target = (KNode) edge.getTarget().getProperty(Properties.ORIGIN);
                    bendPoints.addLast(target.getData(KShapeLayout.class).createVector());
                }

            } else {
                PNode source = edge.getSource();
                bendPoints.addFirst(((KNode) source.getProperty(Properties.ORIGIN)).getData(
                        KShapeLayout.class).createVector());

                PNode target = edge.getTarget();

                bendPoints.addLast(((KNode) target.getProperty(Properties.ORIGIN)).getData(
                        KShapeLayout.class).createVector());
            }
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
                        nodeLayout.setXpos(((float) PGraphFactory.mapNodeToRealX(x))
                                - nodeLayout.getWidth() / 2);
                        nodeLayout.setYpos(((float) PGraphFactory.mapNodeToRealY(y))
                                - nodeLayout.getHeight() / 2);
                    }
                }
            }
        }

        if (pgraph.getProperty(Properties.HIGH_DEGREE_NODE_STRATEGY) == HighDegreeNodeStrategy.GIOTTO) {
            for (PNode node : pgraph.getNodes()) {
                if (node.getProperty(Properties.EXPANSION_CYCLE) != null
                        && hdStrategy == HighDegreeNodeStrategy.GIOTTO) {
                    KShapeLayout nodeLayout = ((KNode) node.getProperty(Properties.ORIGIN))
                            .getData(KShapeLayout.class);
                    nodeLayout.setXpos(nodeLayout.getXpos() + nodeLayout.getWidth() / 2);
                    nodeLayout.setYpos(nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
                }
            }
        }

        if (pgraph.getProperty(LayoutOptions.DEBUG_MODE) == Boolean.TRUE) {
            for (PEdge edge : pgraph.getEdges()) {
                KEdge originEdge = (KEdge) edge.getProperty(Properties.ORIGIN);
                KEdgeLayout edgeLayout = originEdge.getData(KEdgeLayout.class);
                System.out.println(edge.toString() + ": " + edgeLayout.toString());
            }

            for (PNode node : pgraph.getNodes()) {
                KShapeLayout nodeLayout = ((KNode) node.getProperty(Properties.ORIGIN))
                        .getData(KShapeLayout.class);
                System.out.println(node.toString() + ": " + nodeLayout.toString());
            }
        }
    }

    private static void performHDNodes(final PGraph pgraph, final GridRepresentation grid) {
        for (PNode node : pgraph.getNodes()) {
            List<Integer> hdPos = node.getProperty(Properties.HIGH_DEGREE_POSITIONS);
            if (hdPos != null) {
                int x = hdPos.get(PConstants.X_COR);
                int y = hdPos.get(PConstants.Y_COR);
                int widthX = hdPos.get(PConstants.WIDTH_POS) + 1 - x;
                int heightY = hdPos.get(PConstants.HEIGHT_POS) + 1 - y;

                // Remove high-degree node dummies from grid.
                grid.removeHDNode(node);

                // Sets the hdnode to a single position.
                grid.set(x, y, node);

                KShapeLayout nodeLayout = ((KNode) node.getProperty(Properties.ORIGIN))
                        .getData(KShapeLayout.class);
                nodeLayout.setXpos(((float) PGraphFactory.mapNodeToRealX(x))
                        - nodeLayout.getWidth() / 2);
                nodeLayout.setYpos(((float) PGraphFactory.mapNodeToRealY(y))
                        - nodeLayout.getHeight() / 2);
                float gap = spacing - nodeLayout.getWidth();
                nodeLayout.setWidth(nodeLayout.getWidth() * widthX + (widthX - 1) * gap);
                gap = spacing - nodeLayout.getHeight();
                nodeLayout.setHeight(nodeLayout.getHeight() * heightY + (heightY - 1) * gap);

            }
        }
    }

    private static void determineSpacing(final PGraph pgraph) {
        // check whether minimum spacing is smaller than the size of each node,
        // if so adjust spacing

        Float userSpacing = pgraph.getProperty(Properties.SPACING);
        PGraphFactory.spacing = (userSpacing == null) ? 0 : userSpacing.floatValue();

        for (PNode node : pgraph.getNodes()) {
            KNode knode = (KNode) node.getProperty(Properties.ORIGIN);
            KShapeLayout data = knode.getData(KShapeLayout.class);
            if (PGraphFactory.spacing < data.getWidth() + MIN_DIST) {
                PGraphFactory.spacing = data.getWidth() + MIN_DIST;
            }
            if (PGraphFactory.spacing < data.getHeight() + MIN_DIST) {
                PGraphFactory.spacing = data.getHeight() + MIN_DIST;
            }
        }
    }

    /**
     * Increases only the involved rows and columns containing bigger nodes. The remaining grid is
     * not adjusted.
     */
    private static void updateCoordinates() {
        // set graph elements coordinates

        // arrange nodes if minSpacing of nodes is not ensured for the horizontal and
        // vertical direction!
        // horizontal go through: take lower and upper nodes and go horizontal. check
        // all nodes for minspace condition.
        // for (int y = 0; y < grid.getHeight() - 1; y++) {
        // float y1;
        // float y2;
        // float sum;
        // float minDistance = minSpacing;
        // // recognize a too small distance between two nodes.
        // for (int x = 0; x < grid.getWidth(); x++) {
        // if (grid.get(x, y) != null && grid.get(x, y).hasProperties()
        // && grid.get(x, y).getProperty(Properties.ORIGIN) != null) {
        // KNode property = (KNode) grid.get(x, y).getProperty(Properties.ORIGIN);
        // KShapeLayout data = property.getData(KShapeLayout.class);
        // y1 = data.getYpos() - data.getHeight() / 2;
        // } else {
        // // bendpoint or no point
        // y1 = startY - y * spacing;
        // }
        // if (grid.get(x, y + 1) != null && grid.get(x, y + 1).hasProperties()
        // && grid.get(x, y + 1).getProperty(Properties.ORIGIN) != null) {
        // KNode property = (KNode) grid.get(x, y + 1).getProperty(Properties.ORIGIN);
        // KShapeLayout data = property.getData(KShapeLayout.class);
        // y2 = data.getYpos() + data.getHeight() / 2;
        // } else {
        // // bendpoint or no point
        // y2 = startY - (y + 1) * spacing;
        // }
        // sum = y1 - y2;
        // if (sum < minDistance) {
        // // save the biggest deviation of minSum.
        // minDistance = sum;
        // }
        // }
        // if (minDistance < minSpacing) {
        //
        // float restDistance = (minSpacing - minDistance) / 2;
        //
        // // if too small distance is found, adjust the grid position according this distance.
        // // move all grid segments in bottom direction.
        // for (int i = 0; i <= y; i++) {
        // for (int x = 0; x < grid.getWidth(); x++) {
        // if (grid.get(x, i) != null) {
        // KShapeLayout nodeLayout = ((KNode) grid.get(x, i).getProperty(
        // Properties.ORIGIN)).getData(KShapeLayout.class);
        // nodeLayout.setYpos(nodeLayout.getYpos() + restDistance);
        // }
        // }
        // }
        //
        // // move all grid segments in top direction.
        // for (int i = y + 1; i < grid.getHeight(); i++) {
        // for (int x = 0; x < grid.getWidth(); x++) {
        // if (grid.get(x, i) != null) {
        // KShapeLayout nodeLayout = ((KNode) grid.get(x, i).getProperty(
        // Properties.ORIGIN)).getData(KShapeLayout.class);
        // nodeLayout.setYpos(nodeLayout.getYpos() - restDistance);
        // }
        // }
        //
        // }
        //
        // }
        //
        // }
        //
        // // horizontal go through.
        //
        // for (int x = 0; x < grid.getWidth() - 1; x++) {
        // float x1;
        // float x2;
        // float sum;
        // float minDistance = minSpacing;
        // // recognize a too small distance between two nodes.
        // for (int y = 0; y < grid.getHeight(); y++) {
        // if (grid.get(x, y) != null && grid.get(x, y).hasProperties()
        // && grid.get(x, y).getProperty(Properties.ORIGIN) != null) {
        // KNode property = (KNode) grid.get(x, y).getProperty(Properties.ORIGIN);
        // KShapeLayout data = property.getData(KShapeLayout.class);
        // x1 = data.getXpos() + data.getWidth() / 2;
        // } else {
        // // bendpoint or no point
        // x1 = startX + x * spacing;
        // }
        // if (grid.get(x + 1, y) != null && grid.get(x + 1, y).hasProperties()
        // && grid.get(x + 1, y).getProperty(Properties.ORIGIN) != null) {
        // KNode property = (KNode) grid.get(x + 1, y).getProperty(Properties.ORIGIN);
        // KShapeLayout data = property.getData(KShapeLayout.class);
        // x2 = data.getXpos() - data.getWidth() / 2;
        // } else {
        // // bendpoint or no point
        // x2 = startX + (x + 1) * spacing;
        // }
        // sum = x2 - x1;
        // if (sum < minDistance) {
        // // adjust the grid.
        // minDistance = sum;
        // }
        // }
        // if (minDistance < minSpacing) {
        // float restDistance = (minSpacing - minDistance) / 2;
        //
        // // if too small distance is found, adjust the grid position according this distance.
        // // move all grid segments in bottom direction.
        // for (int i = 0; i <= x; i++) {
        // for (int y = 0; y < grid.getHeight(); y++) {
        // if (grid.get(i, y) != null) {
        // // to do: this is not enough, because the bendpoints aren't adjusted!!!!
        // KShapeLayout nodeLayout = ((KNode) grid.get(i, y).getProperty(
        // Properties.ORIGIN)).getData(KShapeLayout.class);
        // nodeLayout.setYpos(nodeLayout.getXpos() - restDistance);
        // }
        // }
        // }
        //
        // // move all grid segments in top direction.
        // for (int i = x + 1; i < grid.getWidth(); i++) {
        // for (int y = 0; y < grid.getHeight(); y++) {
        // if (grid.get(i, y) != null) {
        // KShapeLayout nodeLayout = ((KNode) grid.get(i, y).getProperty(
        // Properties.ORIGIN)).getData(KShapeLayout.class);
        // nodeLayout.setYpos(nodeLayout.getXpos() + restDistance);
        // }
        // }
        // }
        // }
        //
        // }

    }

    /**
     * Maps the relative coordinates to real pixel coordinates on the screen.
     * 
     * @param x
     *            relative coordinate
     * @return real coordinate
     */
    private static double mapNodeToRealX(final double x) {
        return PGraphFactory.startX + x * PGraphFactory.spacing;
    }

    /**
     * Maps the relative coordinates to real pixel coordinates on the screen. Edges needs a point to
     * the middle of a node.
     * 
     * @param x
     *            relative coordinate
     * @return real coordinate
     */
    private static double mapEdgeToRealX(final double x) {
        return PGraphFactory.startX + x * PGraphFactory.spacing;
    }

    /**
     * Maps the relative coordinates to real pixel coordinates on the screen.
     * 
     * @param y
     *            relative coordinate
     * @return real coordinate
     */
    private static double mapNodeToRealY(final double y) {
        return PGraphFactory.startY + y * PGraphFactory.spacing;
    }

    /**
     * Maps the relative coordinates to real pixel coordinates on the screen. Edges needs a point to
     * the middle of a node.
     * 
     * @param y
     *            relative coordinate
     * @return real coordinate
     */
    private static double mapEdgeToRealY(final double y) {
        return PGraphFactory.startY + y * PGraphFactory.spacing;
    }

}
