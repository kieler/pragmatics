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

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p4nodes.BKNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.LinearSegmentsNodePlacer;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic node placement phase tests.
 * 
 * @author uru
 * 
 */
public class NodePlacerTest extends AbstractLayeredProcessorTest {

    /**
     * Instantiates a new layer assignment test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public NodePlacerTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.LINEAR_SEGMENTS, LinearSegmentsNodePlacer.class));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_PLACER,
                NodePlacementStrategy.BRANDES_KOEPF, BKNodePlacer.class));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified node placement strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), state);
    }

    /**
     * The y-coordinates of the nodes have to be strictly ordered.
     */
    @Test
    public void testStrictlyOrdered() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                Double lastY = null;
                for (LNode n : layer.getNodes()) {
                    if (lastY != null) {
                        assertTrue(lastY < n.getPosition().y);
                    }
                    lastY = n.getPosition().y;
                }
            }
        }
    }

    /**
     * Nodes are not allowed to overlap with their boundaries (including margins).
     */
    @Test
    public void testNonOverlapping() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                LNode last = null;
                for (LNode n : layer.getNodes()) {
                    // check
                    if (last != null) {
                        checkOverlapInHeight(last, n);
                    }
                }
            }
        }
    }

    /**
     * @param fst
     *            the upper node
     * @param the
     *            lower node
     */
    private void checkOverlapInHeight(final LNode fst, final LNode snd) {

        // consider margin as well
        double fstLowerLeft = fst.getPosition().y + fst.getSize().y + fst.getMargin().bottom;
        double sndUpperLeft = snd.getPosition().y - snd.getMargin().top;

        assertTrue(sndUpperLeft > fstLowerLeft);
    }

}
