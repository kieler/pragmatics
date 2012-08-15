/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.test;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.Util;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.graph.PNode.NodeType;
import de.cau.cs.kieler.klay.planar.p1planar.BoyerMyrvoldPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.p1planar.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.p1planar.LRPlanarSubgraphBuilder;
import de.cau.cs.kieler.klay.planar.properties.Properties;

/**
 * 
 * @author pkl
 */
public class PlanarizationTest {

    private int nodeCount = -1;

    private PNode[] nodes;

    private int nodeLength;

    // TODO use examples from the book and from other references to ensure the correctness of these
    // examples.

    @Test
    public void test() {

        testBookExample();
        // testK5();
        // testK3_3();
    }

    /**
     * example graph from "graph drawing" book of the orthogonalization chapter.
     */
    private void testBookExample() {
        PGraph pgraph = createBookExample();
        for (PNode node : pgraph.getNodes()) {
            if (node.id == 4) {
                PEdge edge = node.getEdge(10);
                node.getEdges().remove(edge);
                node.getEdges().add(edge);
            }
        }
        System.out.println(pgraph.toString());
        Util.storeGraph(pgraph, 0, false);
        System.out.println(pgraph.getFaceCount() + " faces after step " + 0);
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        final PGraph bmSubgraph = testSubgraphBuilder(new BoyerMyrvoldPlanarSubgraphBuilder(),
                pgraph, 1);
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        // final PGraph lrSubgraph = testSubgraphBuilder(new LRPlanarSubgraphBuilder(), createK5(),
        // 1);
        System.out.println(bmSubgraph.toString());
        System.out.println(bmSubgraph.getFaceCount() + " faces after step " + 1);
        Util.storeGraph(bmSubgraph, 1, false);
        testEdgeInserter(bmSubgraph, 1);
        Util.storeGraph(bmSubgraph, 2, false);
        System.out.println(bmSubgraph.toString());
        System.out.println(bmSubgraph.getFaceCount() + " faces after step " + 2);
        // testEdgeInserter(lrSubgraph, 1);
        // Util.storeGraph(bmSubgraph, 0, false);

    }

    private void testK5() {
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        final PGraph bmSubgraph = testSubgraphBuilder(new BoyerMyrvoldPlanarSubgraphBuilder(),
                createK5(), 1);
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        final PGraph lrSubgraph = testSubgraphBuilder(new LRPlanarSubgraphBuilder(), createK5(), 1);

        testEdgeInserter(bmSubgraph, 1);

        testEdgeInserter(lrSubgraph, 1);
    }

    /**
     * 
     */
    private void testK3_3() {
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        final PGraph bmSubgraph = testSubgraphBuilder(new BoyerMyrvoldPlanarSubgraphBuilder(),
                createK3_3(), 1);
        // checks if the implementation of BoyerMyrvold approach generates correct planar subgraphs.
        final PGraph lrSubgraph = testSubgraphBuilder(new LRPlanarSubgraphBuilder(), createK3_3(),
                1);

        testEdgeInserter(bmSubgraph, 1);

        testEdgeInserter(lrSubgraph, 1);

    }

    /**
     * @param graph
     * 
     */
    private void testEdgeInserter(final PGraph graph, final int additionalNodeCount) {
        EdgeInsertionPlanarization edgeInserter = new EdgeInsertionPlanarization();
        edgeInserter.process(graph);
        // Object insertable_edges = graph.getProperty(Properties.INSERTABLE_EDGES);
        // if (insertable_edges instanceof LinkedList) {
        // @SuppressWarnings("unchecked")
        // LinkedList<PEdge> missingEdges = (LinkedList<PEdge>) insertable_edges;
        // if (missingEdges.size() > 0) {
        // fail("after the edge insertation step, all edge should be added to the original graph"
        // + " by inserting of nodes, but the missingEdgeCound is "
        // + missingEdges.size() + ".");
        // }
        // }
        int newNodeCount = this.nodeCount + additionalNodeCount;
        if (this.nodeCount + additionalNodeCount != graph.getNodeCount()) {
            fail("the edge insertion step should add " + newNodeCount
                    + " new nodes, but the current graph node count is " + graph.getNodeCount()
                    + ".");
        }

    }

    /**
     * @return
     */
    private PGraph createBookExample() {
        // Testgraph:
        PGraph graph = new PGraph();
        // Adding Nodes
        PNode node0 = graph.addNode(NodeType.NORMAL);
        PNode node1 = graph.addNode(NodeType.NORMAL);
        PNode node2 = graph.addNode(NodeType.NORMAL);
        PNode node3 = graph.addNode(NodeType.NORMAL);
        PNode node4 = graph.addNode(NodeType.NORMAL);
        PNode node5 = graph.addNode(NodeType.NORMAL);
        PNode node6 = graph.addNode(NodeType.NORMAL);
        this.nodeCount = graph.getNodeCount();

        // Adding Edges
        graph.addEdge(node0, node1);
        graph.addEdge(node6, node0);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node5);
        graph.addEdge(node2, node4);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node4);
        graph.addEdge(node5, node3);
        graph.addEdge(node6, node5);
        graph.addEdge(node6, node4);

        return graph;
    }

    /**
     * 
     */
    private PGraph createK5() {
        // TODO check for directed/undirected edges
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes

        // Testgraph: K_5
        PGraph graph = new PGraph();
        // Adding Nodes
        PNode node1 = graph.addNode(NodeType.NORMAL);
        PNode node2 = graph.addNode(NodeType.NORMAL);
        PNode node3 = graph.addNode(NodeType.NORMAL);
        PNode node4 = graph.addNode(NodeType.NORMAL);
        PNode node5 = graph.addNode(NodeType.NORMAL);
        this.nodeCount = graph.getNodeCount();

        // Adding Edges
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node4);
        graph.addEdge(node1, node5);
        graph.addEdge(node2, node3);
        graph.addEdge(node2, node4);
        graph.addEdge(node2, node5);
        graph.addEdge(node3, node4);
        graph.addEdge(node3, node5);
        graph.addEdge(node4, node5);

        return graph;
    }

    /**
     * 
     */
    private PGraph createK3_3() {
        // TODO check for directed/undirected edges
        // TODO check for embedding constraints (ports)
        // TODO recurse over children in compound nodes

        // Testgraph: K3_3
        PGraph graph = new PGraph();
        // Adding Nodes
        PNode node0 = graph.addNode(NodeType.NORMAL);
        PNode node1 = graph.addNode(NodeType.NORMAL);
        PNode node2 = graph.addNode(NodeType.NORMAL);
        PNode node3 = graph.addNode(NodeType.NORMAL);
        PNode node4 = graph.addNode(NodeType.NORMAL);
        PNode node5 = graph.addNode(NodeType.NORMAL);

        // Adding Edges
        graph.addEdge(node0, node1);
        graph.addEdge(node0, node3);
        graph.addEdge(node0, node5);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node4);
        graph.addEdge(node2, node3);
        graph.addEdge(node2, node5);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node5);

        return graph;
    }

    /**
     * 
     * Take a look in the used subgraph builder to get more information.
     * 
     * @param removeEdgeCount
     * 
     * @param pGraph
     * 
     * @return the planar subgraph.
     * 
     */
    private PGraph testSubgraphBuilder(ILayoutProcessor subgraphBuilder, PGraph testGraph,
            int removeEdgeCount) {
        subgraphBuilder.process(testGraph);
        Object insertable_edges = testGraph.getProperty(Properties.INSERTABLE_EDGES);
        // checks also against null
        if (insertable_edges instanceof LinkedList) {
            @SuppressWarnings("unchecked")
            LinkedList<PEdge> missingEdges = (LinkedList<PEdge>) insertable_edges;
            if (missingEdges.size() != removeEdgeCount) {
                fail("The planar subgraph builder should remove exactly " + removeEdgeCount
                        + " edges, but does " + missingEdges.size() + ".");
            }
        } else {
            fail("The planar subgraph builder should remove exactly " + removeEdgeCount
                    + " edges, but does " + 0 + ".");
        }
        return testGraph;
    }

    /**
     * Sorts the nodes by their index.
     * 
     * @param theNodes
     */
    public void sort(final Collection<PNode> theNodes) {
        // TODO complete this function as a supporter for the component spliter problem!
        // Check for empty or null array
        if (theNodes == null || theNodes.size() == 0) {
            return;
        }
        this.nodes = (PNode[]) theNodes.toArray();
        nodeLength = theNodes.size();
        int id = 0;
        PNode smallestNode = null;
        boolean stopLoop = false;
        while (true) {
            if (nodeLength == id) {
                break;
            }
            while (!stopLoop) {
                stopLoop = false;
                for (PNode node : nodes) {
                    if (smallestNode == null) {
                        smallestNode = node;
                    }
                    if (node.id < smallestNode.id) {
                        smallestNode = node;
                        stopLoop = true;
                    }
                }
            }
            smallestNode.id += 1;
        }

    }

}
