/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.phases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LongestPathLayerer;
import de.cau.cs.kieler.klay.layered.p2layers.NetworkSimplexLayerer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the layering phase.
 * 
 * @author uru
 */
public class LayeringTest extends AbstractLayeredProcessorTest {

    // used for tests
    private String randomPrefix = String.valueOf(random.nextDouble());
    private Set<String> existingLabelsSet = Sets.newHashSet();

    // predicate that filters out randomly generated labels
    private final Predicate<KLabel> hasLabelPredicate = new Predicate<KLabel>() {
        /**
         * {@inheritDoc}
         */
        public boolean apply(final KLabel label) {
            return label.getText().startsWith(randomPrefix);
        }
    };

    // collect the KNodes to check if they exist in the resulting LGraph
    private final Function<KNode, KNode> addRandomLabelsFun = new Function<KNode, KNode>() {
        public KNode apply(final KNode root) {
            for (KNode n : root.getChildren()) {

                // dont do it for every test
                if (!Iterables.isEmpty(Iterables.filter(n.getLabels(), hasLabelPredicate))) {
                    break;
                }

                String randomString = randomPrefix + random.nextInt();
                existingLabelsSet.add(randomString);
                // create a label and add it to the node
                KLabel randomLabel = KimlUtil.createInitializedLabel(n);
                randomLabel.setText(randomString);
            }
            return root;
        }
    };

    /**
     * Instantiates a new layer assignment test and set the graphObject to the current graph to
     * test.
     * 
     * @param testObject
     *            the test object
     * @param config
     *            layout configurator
     */
    public LayeringTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {

        List<ILayoutConfigurator> configs = Lists.newArrayList();

        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_LAYERING,
                LayeringStrategy.LONGEST_PATH, LongestPathLayerer.class, addRandomLabelsFun));
        configs.add(new SimplePhaseLayoutConfigurator(Properties.NODE_LAYERING,
                LayeringStrategy.NETWORK_SIMPLEX, NetworkSimplexLayerer.class, addRandomLabelsFun));

        return configs;
    }

    /**
     * Run the layout algorithm until the specified layer assignment strategy finished.
     * 
     * Collect all initial nodes and assign random strings to them in order to test whether all
     * nodes are assigned to a certain layer.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(getAndCheckSimpleConfig().getStrategyImpl(), state);
    }

    /**
     * All nodes need to be assigned to layers. The 'layerlessNodes' collection has to be empty.
     */
    @Test
    public void testNoLayerlessNodes() {
        for (LGraph g : state.getGraphs()) {
            assertTrue(g.getLayerlessNodes().isEmpty());
        }
    }

    /**
     * Every layer has to contain at least one node.
     */
    @Test
    public void testNoEmptyLayers() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                assertTrue(layer.getNodes() != null);
                assertTrue(!layer.getNodes().isEmpty());
            }
        }
    }

    /**
     * Every node of the initial KGraph has to be placed on a layer. New (dummy) nodes might be
     * introduced, hence no 1-to-1 validation is possible.
     */
    @Test
    public void testAllKNodesExist() {

        // find all labels and remove randomly generated ones from the set
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode n : layer.getNodes()) {
                    for (LLabel label : n.getLabels()) {
                        if (label.getText().startsWith(randomPrefix)) {
                            // remove it from the set
                            existingLabelsSet.remove(label.getText());
                        }
                    }
                }
            }
        }

        // assert that all labels made it into the LGraph
        assertTrue(existingLabelsSet.isEmpty());
    }

    /**
     * Every edge's has do direct from a node to another node with the index of the first layer
     * being lower than the index of the second layer.
     */
    @Test
    public void testEdgesIncreasing() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode n : layer.getNodes()) {
                    for (LEdge e : n.getOutgoingEdges()) {
                        int targetIndex = e.getTarget().getNode().getLayer().getIndex();
                        assertTrue(layer.getIndex() < targetIndex);
                    }
                }
            }
        }
    }

}
