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
package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.PartitionPostprocessor;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link PartitionConstraintProcessor.
 * 
 * @author csp
 */
public class PartitionConstraintProcessorTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/partitioned_graphs";

    // CHECKSTYLEOFF javadoc
    public PartitionConstraintProcessorTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath(TEST_FOLDER, false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(PartitionPostprocessor.class, state);
    }

    /**
     * After constraint processing no empty layers should be left behind.
     */
    @Test
    public void noEmptyLayerTest() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                assertTrue(layer.getNodes() != null);
                assertTrue(!layer.getNodes().isEmpty());
            }
        }
    }

    /**
     * For each layer, every node in this layer has the same partition and and this partition is greater
     * or equal to the partition of the next left layer.
     */
    @Test
    public void testPartitionOrder() {
        int currentPartition = 0;
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                // Set to invalid partition
                int layerPartition = -1;
                for (LNode node : layer) {
                    if (node.getType() == NodeType.NORMAL) {
                        int nodePartition = node.getProperty(LayoutOptions.PARTITION);
                        if (layerPartition == -1) {
                            layerPartition = nodePartition;
                        }
                        assertTrue(layerPartition == nodePartition);
                    }
                }
                assertTrue(currentPartition <= layerPartition);
                currentPartition = layerPartition;
            }
        }
    }

}
