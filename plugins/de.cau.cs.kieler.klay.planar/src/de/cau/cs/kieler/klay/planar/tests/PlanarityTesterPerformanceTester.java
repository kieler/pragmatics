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

import de.cau.cs.kieler.klay.planar.alg.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.impl.BoyerMyrvoldPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.impl.LRPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraph;

/**
 * A small class to test the performance of planarity testing algorithms.
 * 
 * @author ocl
 */
public class PlanarityTesterPerformanceTester {

    /** The planarity tester to use. */
    private IPlanarityTester tester;

    /** Determines if only planarity checks are made or if a planar subgraph is computed. */
    private boolean testOnly;

    /**
     * Create a new tester class.
     * 
     * @param t
     *            the planarity tester to use
     * @param test
     *            determines if only planarity testing or subgraph finding is tested.
     */
    public PlanarityTesterPerformanceTester(final IPlanarityTester t, final boolean test) {
        this.tester = t;
        this.testOnly = test;
    }

    /**
     * Start the test.
     */
    public void test() {
        // CHECKSTYLEOFF Magic Numbers
        int nodeMult = 10;
        int edgeMult = 10;
        int iterations = 1000;
        // CHECKSTYLEOFF Magic Numbers

        for (int nodes = 0; nodes < nodeMult; nodes++) {
            for (int edges = 0; edges < edgeMult; edges++) {
                int n = 1 << nodes;
                int m = 1 << edges;
                long time = 0;
                int fails = 0;
                for (int i = 0; i < iterations; i++) {
                    try {
                        time += this.measurement(this.randomGraph(n, m));
                    } catch (InconsistentGraphModelException e) {
                        fails++;
                    }
                }
                time /= (iterations > fails) ? (iterations - fails) : 1;
                fails /= 10;
                System.out.println("Nodes = " + n + ", Edges = " + m + ", Time = " + time
                        + ", Fails = " + fails + "%");
            }
        }
    }

    /**
     * Measure the time the planarity tester needs to test the given graph.
     * 
     * @param graph
     *            the graph to check for planarity
     * @return the time the algorithm needed
     * @throws InconsistentGraphModelException
     *             if anything goes wrong
     */
    private long measurement(final IGraph graph) throws InconsistentGraphModelException {
        long time = 0;
        this.tester.reset();
        if (this.testOnly) {
            time = System.nanoTime();
            this.tester.testPlanarity(graph);
            time = System.nanoTime() - time;
        } else {
            time = System.nanoTime();
            this.tester.planarSubgraph(graph);
            time = System.nanoTime() - time;
        }
        return time;
    }

    /**
     * Create a random graph.
     * 
     * @param n
     *            the number of nodes
     * @param m
     *            the number of edges
     * @return a random graph
     * @throws InconsistentGraphModelException
     *             if any inconsistencies occur
     */
    public IGraph randomGraph(final int n, final int m) throws InconsistentGraphModelException {
        IGraph graph = new PGraph();
        INode[] nodes = new INode[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = graph.addNode();
        }

        for (int i = 0; i < m; i++) {
            int i1 = (int) Math.floor(Math.random() * n);
            int i2 = (int) Math.floor(Math.random() * n);
            graph.addEdge(nodes[i1], nodes[i2]);
        }
        return graph;
    }

    /**
     * Main program starting point.
     * 
     * @param args
     *            ignored
     */
    public static void main(final String[] args) {
        IPlanarityTester t1 = new BoyerMyrvoldPlanarityTester();
        IPlanarityTester t2 = new LRPlanarityTester();

        // Perform tests
        System.out.println("\n Boyer-Myrvold Planarity Tester:");
        new PlanarityTesterPerformanceTester(t1, true).test();
        System.out.println("\n Left-Right Planarity Tester Tester:");
        new PlanarityTesterPerformanceTester(t2, true).test();
        System.out.println("\n Boyer-Myrvold Planar Subgraph:");
        new PlanarityTesterPerformanceTester(t1, false).test();
        System.out.println("\n Left-Right Planar Subgraph:");
        new PlanarityTesterPerformanceTester(t2, false).test();
    }

}
