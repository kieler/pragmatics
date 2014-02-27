/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.phases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic crossing minimization tests.
 * 
 * @author uru
 */
public class CrossingMinimizationTest extends AbstractLayeredProcessorTest {

    private Multimap<Layer, LNode> layerNodesMap = HashMultimap.create();

    /**
     * Instantiates a new layer assignment test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public CrossingMinimizationTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
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
     * Run the layout algorithm until the specified crossing minimization strategy finished.
     */
    @Before
    public void runUntil() {
        // first run until layerer finished, remember a layer's nodes
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), false, state);
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    layerNodesMap.put(layer, node);
                }
            }
        }

        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), true, state);
    }

    /**
     * All nodes have to remain in their layer.
     */
    @Test
    public void testSameLayer() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    // node in the same layer?
                    assertTrue(layerNodesMap.get(layer).contains(node));
                }
            }
        }
    }

}
