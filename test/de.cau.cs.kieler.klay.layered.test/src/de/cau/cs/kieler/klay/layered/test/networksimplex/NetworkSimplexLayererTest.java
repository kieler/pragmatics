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

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.layered.test.phases.SimplePhaseLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * The test compares the current version of the {@link NetworkSimplexLayerer} to an old
 * implementation from ancient times {@link OldNetworkSimplexLayerer}. Both versions should produce
 * the same number of dummy nodes. Layerings do not have to be identical though.
 * 
 * During continuous integration the test is only run on a small set of graphs, there are some lines
 * to be commented in below to test further graphs.
 * 
 * @author uru
 */
public class NetworkSimplexLayererTest extends AbstractLayeredProcessorTest {

    /**
     * Instantiates a new layerer test and set the graphObject to the current graph to test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public NetworkSimplexLayererTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * Add a configurator for following strategies.
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_LAYERING,
                LayeringStrategy.NETWORK_SIMPLEX, NetworkSimplexLayerer.class));
        // we don't have an ID for this strategy since it's only part of the 
        // test plugin. 
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_LAYERING,
                null, NetworkSimplexLayerer.class));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified cycle breaking strategy finished.
     */
    @Before
    public void runUntil() {
        if (getAndCheckSimpleConfig().getStrategy() == null) {
            // run exclusive layering phase
            layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), false, state);
            for (LGraph g : state.getGraphs()) {
                new OldNetworkSimplexLayerer().process(g, new BasicProgressMonitor());
            }
        } else {
            layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), state);
        }
    }
    
    protected TestPath[] getBundleTestPath() {
        // some default test graphs that should be run by every test
        TestPath[] testPaths = { 
                new TestPath("misc/random", false, false, TestPath.Type.KGRAPH),
                // more tests!
                // new TestPath("ptolemy_flat/xmi", true, false, TestPath.Type.KGRAPH)
        };
        return testPaths;
    }

    // For subsequent layerers the graph object is the same. However, the test instances are different.
    // TODO is there a better way than a static variable?
    private static Map<GraphTestObject, Integer> dummyCounts = Maps.newHashMap();
    
    /**
     * Tests whether both network simpley layering methods yield the same number of dummy nodes
     * (which they should do!).
     */
    @Test
    public void testDummyNodeCount() {
        
        int dummies = 0;
        for (LGraph graph : state.getGraphs()) {
            for (Layer layer : graph) {
                for (LNode n : layer) {
                    for (LEdge e : n.getOutgoingEdges()) {

                        dummies += e.getTarget().getNode().getLayer().getIndex()
                                   - e.getSource().getNode().getLayer().getIndex() - 1;

                    }
                }
            }
        }
        
        if (!dummyCounts.containsKey(graphObject)) {
            dummyCounts.put(graphObject, dummies);
        } else {
            Assert.assertEquals("Same number of dummy nodes", (int) dummyCounts.get(graphObject), dummies); 
        }
    }
    
    private static Map<GraphTestObject, Multiset<Pair<Integer, Integer>>> layerings = Maps.newHashMap();
    
    /**
     * Tests whether two layerings are identical.
     * 
     * Remark: This is _not_ necessarily required when aiming for a minimum number of dummy nodes!
     */
    //@Test
    public void testIdenticalLayering() {

        Multiset<Pair<Integer, Integer>> aLayering = HashMultiset.create();
        
        for (LGraph graph : state.getGraphs()) {
            for (Layer layer : graph) {
                for (LNode n : layer) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        
                        Pair<Integer, Integer> pair =
                                Pair.of(e.getTarget().getNode().getLayer().getIndex(), e
                                        .getSource().getNode().getLayer().getIndex());
                        aLayering.add(pair);
                    }
                }
            }
        }
        
        if (!layerings.containsKey(graphObject)) {
            layerings.put(graphObject, aLayering);
        } else {
            Multiset<Pair<Integer, Integer>> referenceLayering = layerings.get(graphObject);
            Assert.assertEquals("Identical layerings", referenceLayering, aLayering);
        }
    }
}
