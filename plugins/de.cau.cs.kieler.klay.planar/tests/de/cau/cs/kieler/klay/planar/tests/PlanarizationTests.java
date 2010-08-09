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
package de.cau.cs.kieler.klay.planar.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.planar.alg.IPlanarizer;
import de.cau.cs.kieler.klay.planar.alg.impl.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * A JUnit test case that checks if the edge insertion planarization algorithm from the
 * planarization plug-in is working correctly.
 * 
 * @author ocl
 */
public class PlanarizationTests {
    // TODO this is just a copy of cku's test class - no proper junit test yet.

    // ======================== Attributes =========================================================

    /** The graph factory that generates graphs for the tests. */
    private IGraphFactory factory;

    private IPlanarizer planarizer;

    // ======================== Tests ==============================================================

    /**
     * Initialize test attributes.
     */
    @Before
    public void initialize() {
        this.factory = new PGraphFactory();
        this.planarizer = new EdgeInsertionPlanarization();
    }

    /**
     * Create an example graph and run the algorithm.
     */
    @Test
    public void testExampleGraph() {
        // build test graph
        IGraph testGraph = this.factory.createEmptyGraph();
        IGraph testGraph2 = this.factory.createEmptyGraph();

        INode nodeZero = testGraph.addNode();
        INode nodeOne = testGraph.addNode();
        INode nodeTwo = testGraph.addNode();
        INode nodeThree = testGraph.addNode();
        INode nodeFour = testGraph.addNode();
        INode nodeFive = testGraph.addNode();
        INode nodeSix = testGraph.addNode();
        INode nodeSeven = testGraph.addNode();

        INode nodeZero2 = testGraph2.addNode();
        INode nodeOne2 = testGraph2.addNode();
        INode nodeTwo2 = testGraph2.addNode();

        testGraph.addEdge(nodeZero, nodeOne);
        testGraph.addEdge(nodeZero, nodeFour);
        testGraph.addEdge(nodeZero, nodeThree);
        testGraph.addEdge(nodeZero, nodeTwo);

        testGraph.addEdge(nodeOne, nodeFive);
        testGraph.addEdge(nodeOne, nodeFour);

        testGraph.addEdge(nodeFive, nodeSeven);

        testGraph.addEdge(nodeSeven, nodeSix);
        testGraph.addEdge(nodeSeven, nodeFour);

        testGraph.addEdge(nodeSix, nodeTwo);
        testGraph.addEdge(nodeSix, nodeThree);

        testGraph.addEdge(nodeFour, nodeSix);

        testGraph2.addEdge(nodeZero2, nodeOne2);
        testGraph2.addEdge(nodeOne2, nodeTwo2);

        System.out.println("Eingabe Graph \n\n");
        System.out.println(testGraph.toString());
        System.out.println("----------------------\n");

        List<IEdge> edges = new ArrayList<IEdge>();
        edges.add(testGraph.addEdge(nodeFour, nodeTwo));

        this.planarizer.reset();
        this.planarizer.planarize(testGraph, edges);

        System.out.println("NEUER GRAPH");
        System.out.println(testGraph.toString());

        // System.out.println("Eingabe Graph \n\n");
        // System.out.println(testGraph2.toString());
        // System.out.println("----------------------\n");
        //
        // IGraph dualgraph = testGraph2.createDualGraph();
        // System.out.println("----------------------\n");
        // System.out.println("DualerGraph \n\n");
        // System.out.println(dualgraph.toString());
        // System.out.println("----------------------\n");

        // System.out.println("parent-Array:");
        // int[] test = searchParents(nodeThree, testGraph);
        // for (int i = 0; i < test.length; i++) {
        // System.out.println(test[i] + ", ");
        // }

        // System.out.println("The matching Face:");
        // IFace face = findInherentFace(nodeOne, testGraph);
        // System.out.println(face.toString());

        // INode newNode = planrizer.splitUpEdge(testGraph.getEdges().iterator()
        // .next(), testGraph);
        // System.out.println("neuerGraph:");
        // System.out.println(testGraph);
    }

}
