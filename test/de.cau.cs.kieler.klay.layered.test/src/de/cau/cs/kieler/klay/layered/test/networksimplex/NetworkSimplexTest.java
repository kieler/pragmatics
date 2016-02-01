/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klay.layered.test.networksimplex;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.klay.layered.networksimplex.NEdge;
import de.cau.cs.kieler.klay.layered.networksimplex.NGraph;
import de.cau.cs.kieler.klay.layered.networksimplex.NNode;
import de.cau.cs.kieler.klay.layered.networksimplex.NetworkSimplex;

/**
 * @author uru
 */
public class NetworkSimplexTest {

    /** Random with a fixed seed for determinism. */
    private Random random = new Random(1);
    
    @Test
    public void testDeltas() {
        
        for (int j = 0; j < 5; ++j) {
            @SuppressWarnings("unused")
            long start = System.currentTimeMillis();
            int n = 5; // Integer.MAX_VALUE
            for (int i = 0; i < n; ++i) {
                NGraph graph = generateRandomGraph();

                Assert.assertTrue(graph.isAcyclic());

                NetworkSimplex.forGraph(graph).execute(new BasicProgressMonitor());

                for (NNode node : graph.nodes) {
                    for (NEdge e : node.getOutgoingEdges()) {
                        Assert.assertTrue("Valid delta",
                                e.getTarget().layer - e.getSource().layer >= e.delta);
                    }
                }
            }

            // System.out.println("Took: " + (System.currentTimeMillis() - start) + "ms.");
        }
    }
    
    private NGraph generateRandomGraph() {
        
        NGraph graph = new NGraph();

        final int n = 4000;
        final int e = 8000;
        
        // create nodes
        for (int i = 0; i < n; ++i) {
            NNode.of().id(i).create(graph);
        }
        
        // create edges
        for (int i = 0; i < e; ++i) {
            
            int src = random.nextInt(n);
            int tgt = random.nextInt(n);
            // no self loops
            while (src == tgt) {
                tgt = random.nextInt(n);
            }
            
            NEdge.of()
                .delta(random.nextInt(50))
                .weight(random.nextDouble() * 50)
                .source(graph.nodes.get(src))
                .target(graph.nodes.get(tgt))
                .create();
        }
        
        // assert connectedness
        for (int i = 0; i < n - 1; ++i) {
            NEdge.of()
                .delta(random.nextInt(50))
                .weight(random.nextDouble() * 50)
                .source(graph.nodes.get(i))
                .target(graph.nodes.get(i+1))
                .create();
        }
        
        // assert acyclic
        for (NNode node : graph.nodes) {
            for (NEdge edge : Lists.newArrayList(node.getOutgoingEdges())) {
                if (edge.source.id > edge.target.id) {
                    edge.reverse();
                }
            }
        }
        
        return graph;
    }
}
