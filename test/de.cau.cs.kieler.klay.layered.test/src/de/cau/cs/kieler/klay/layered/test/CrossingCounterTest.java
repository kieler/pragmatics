/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.p3order.counting.BarthJuengerMutzelCrossingsCounter;
import de.cau.cs.kieler.klay.layered.p3order.counting.HyperedgeCrossingsCounter;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.phases.SimplePhaseLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Tests straight line edge crossing counter vs. hyperedge crossing counter.
 *
 * @author csp
 */
public class CrossingCounterTest extends AbstractLayeredProcessorTest {

    /**
     * Crossings counter for normal edges.
     */
    private BarthJuengerMutzelCrossingsCounter normalCrossingsCounter;
    /**
     * Crossings counter for hyperedges.
     */
    private HyperedgeCrossingsCounter hyperedgeCrossingsCounter;

    /**
     * Instantiates a new crossing counter test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public CrossingCounterTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.CROSS_MIN,
                CrossingMinimizationStrategy.LAYER_SWEEP, LayerSweepCrossingMinimizer.class));

        return configs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = {
                new TestPath("misc/random", false, false, TestPath.Type.KGRAPH),
                new TestPath("klay_layered/crossing_counting", false, false, TestPath.Type.KGRAPH)};
        return testPaths;
    }

    /**
     * Run the layout algorithm up to the specified phase.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), false, state);
    }

    /**
     * Both straightline and hyperedge crossing counting should yield the same results.
     */
    @Test
    public void testEqualCrossingCounts() {
        for (LGraph g : state.getGraphs()) {
            LNode[][] nodeOrder = g.toNodeArray();
            try {
                initializeCrossingCounters(g);
                for (int i = 0; i < nodeOrder.length - 1; i++) {
                    LNode[] leftLayer = nodeOrder[i];
                    LNode[] rightLayer = nodeOrder[i + 1];
                    int sl = normalCrossingsCounter.countCrossings(leftLayer, rightLayer);
                    int he = hyperedgeCrossingsCounter.countCrossings(leftLayer, rightLayer);
                    assertTrue("Straightline count: " + sl + " Hyperedge count: " + he, sl == he);
                }
            } catch (IllegalStateException e) {
                System.err.println("Crossing counter test skipped connected component in file "
                        + graphObject.getFile().getAbsolutePath() + ": "
                        + e.getMessage());
            }
        }
    }
    
    /**
     * Initialize the crossing counter modules with the given graph.
     *
     * @param layeredGraph
     *            the graph to initialize the counters for.
     */
    private void initializeCrossingCounters(final LGraph layeredGraph) {
        int layerCount = layeredGraph.getLayers().size();

        int[] inLayerEdgeCount = new int[layerCount];
        boolean[] hasNorthSouthPorts = new boolean[layerCount];

        int portCount = 0;

        // Iterate through the layers, initializing port and node IDs
        ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator();
        while (layerIter.hasNext()) {
            Layer layer = layerIter.next();

            int layerIndex = layerIter.previousIndex();
            int layerNodeCount = layer.getNodes().size();
            // Empty layers are not allowed!
            assert layerNodeCount > 0;

            inLayerEdgeCount[layerIndex] = 0;
            hasNorthSouthPorts[layerIndex] = false;

            ListIterator<LNode> nodeIter = layer.getNodes().listIterator();
            while (nodeIter.hasNext()) {
                LNode node = nodeIter.next();

                // Count in-layer edges
                for (LPort port : node.getPorts()) {
                    // Check for hyperedges
                    if (port.getOutgoingEdges().size() + port.getIncomingEdges().size() > 1) {
                        throw new IllegalStateException(
                                "Test not applicable for graphs with hyperedges!");
                    }
                    port.id = portCount++;
                    for (LEdge edge : port.getOutgoingEdges()) {
                        if (edge.getTarget().getNode().getLayer() == layer) {
                            inLayerEdgeCount[layerIndex]++;
                        }
                    }
                }
                
                // Count north/south dummy nodes
                if (node.getType() == NodeType.NORTH_SOUTH_PORT) {
                    inLayerEdgeCount[layerIndex]++;
                    hasNorthSouthPorts[layerIndex] = true;
                }
            }
            
        }

        // Initialize the port positions array
        int[] portPos = new int[portCount];
        
        // Create the crossings counter modules
        normalCrossingsCounter =
                new BarthJuengerMutzelCrossingsCounter(inLayerEdgeCount, hasNorthSouthPorts, portPos);
        hyperedgeCrossingsCounter =
                new HyperedgeCrossingsCounter(inLayerEdgeCount, hasNorthSouthPorts, portPos);
    }

}
