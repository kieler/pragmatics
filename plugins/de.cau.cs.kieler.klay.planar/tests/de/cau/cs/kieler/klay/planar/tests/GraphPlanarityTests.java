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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.planar.alg.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.impl.BoyerMyrvoldPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.impl.LRPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * A JUnit test case that checks if the various planarity testing algorithms from the planarization
 * plug-in are working correctly.
 * 
 * @author ocl
 */
public class GraphPlanarityTests {

    // ======================== Constants ==========================================================

    /** The number of graphs to test. */
    private static final int NUMTESTS = 10000;

    /** The maximum number of nodes a graph can have. */
    private static final int MAXNODES = 10;

    /** The maximum number of edges a graph can have. */
    private static final int MAXEDGES = 20;

    /** A File system path pointing to the directory containing planar test graphs. */
    private static final String GRAPHDIRECTORY = "./graphs";

    // ======================== Attributes =========================================================

    /** The graph factory that generates graphs for the tests. */
    private IGraphFactory factory;

    /** The list of planarity testers used in the tests. */
    private LinkedList<IPlanarityTester> testers;

    // ======================== Tests ==============================================================

    /**
     * Initialize test attributes.
     */
    @Before
    public void initialize() {
        this.factory = new PGraphFactory();
        this.testers = new LinkedList<IPlanarityTester>();
        this.testers.add(new BoyerMyrvoldPlanarityTester());
        this.testers.add(new LRPlanarityTester());
    }

    /**
     * Create random graphs and test each one for planarity.
     */
    @Test
    public void testRandomGraphs() {
        for (int i = 0; i < NUMTESTS; i++) {
            int nodes = (int) Math.floor(Math.random() * MAXNODES) + 1;
            int edges = (int) Math.floor(Math.random() * MAXEDGES) + 1;
            IGraph graph = this.factory.createRandomGraph(nodes, edges);
            this.testGraph(graph);
        }
    }

    /**
     * Read planar graphs in DIMACS format from files and test each one for planarity.
     */
    // @Test
    public void testPlanarGraphsFromFile() {
        File directory = new File(GRAPHDIRECTORY);
        for (File file : directory.listFiles()) {
            try {
                IGraph graph = this.factory.createGraphFromDIMACS(file);
                boolean planar = this.testGraph(graph);
                assertTrue(planar);
            } catch (IOException e) {
                fail("Caught IOException: " + e.getMessage());
            }
        }
    }

    /**
     * Test if all planarity testing algorithms give the same result for a specific graph.
     * 
     * @param graph
     * @return the result of the testing algorithms
     */
    private boolean testGraph(final IGraph graph) {
        Boolean result = null;
        for (IPlanarityTester tester : this.testers) {
            IGraph copy = this.factory.createGraphCopy(graph);
            tester.reset();
            boolean planar = tester.testPlanarity(copy);
            if (result != null) {
                if (result != planar) {
                    for (IPlanarityTester t : this.testers) {
                        System.out.println(t.getClass().getName());
                    }
                    System.out.println(result);
                    System.out.println(planar);
                    System.out.println(graph);
                }
                assertTrue(result == planar);
            }
            result = planar;
        }
        return result;
    }
}
