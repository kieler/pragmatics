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
package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.InvertedPortProcessor;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.layered.test.config.OrthogonalEdgeRoutingLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link InvertedPortProcessor}.
 * 
 * @author uru
 */
public class InvertedPortProcessorTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public InvertedPortProcessorTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> list = Lists.newArrayList();

        // assure that the processor is added to the layout configuration
        list.add(new OrthogonalEdgeRoutingLayoutConfigurator());
        return list;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths =
                { new TestPath("north_south_ports", false, false, TestPath.Type.GMF) };
        return testPaths;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        System.out.println(layered.getLayoutTestGraphs());
        System.out.println(layered.getLayoutTestConfiguration());
        lgraphs = layered.runLayoutTestUntil(InvertedPortProcessor.class);
    }

    /**
     * Outgoing edges from WEST (left) ports have dummy node in the same layer as target. Analog:
     * incoming edges from EAST (right) ports.
     */
    @Test
    public void testSameLayerDummies() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {

                    // outgoing edges left
                    for (LEdge edge : node.getOutgoingEdges()) {
                        if (edge.getSource().getSide() == PortSide.WEST) {
                            LNode target = edge.getTarget().getNode();
                            // dummy in same layer
                            assertTrue(target.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE);
                            assertTrue(target.getLayer().getIndex() == layer.getIndex());
                        }
                    }

                    // incoming edges right
                    for (LEdge edge : node.getIncomingEdges()) {
                        if (edge.getTarget().getSide() == PortSide.EAST) {
                            LNode source = edge.getSource().getNode();
                            // dummy in same layer
                            assertTrue(source.getProperty(Properties.NODE_TYPE) == NodeType.LONG_EDGE);
                            assertTrue(source.getLayer().getIndex() == layer.getIndex());
                        }
                    }
                }
            }
        }
    }
}
