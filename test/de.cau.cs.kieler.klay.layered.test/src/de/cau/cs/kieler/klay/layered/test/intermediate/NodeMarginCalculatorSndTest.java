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

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.NodeMarginCalculator;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link NodeMarginCalculator}.
 * 
 * @author uru
 */
public class NodeMarginCalculatorSndTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public NodeMarginCalculatorSndTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath("node_margins", false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        lgraphs = layered.runLayoutTestUntil(NodeMarginCalculator.class);

    }

    /**
     * Every node as an assigned margin unequal to {@code null} and greater or equal to 0.
     */
    @Test
    public void testNodeMargins() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    for (LPort port : node.getPorts()) {
                        assertWithinMargins(port, node);
                    }
                }
            }
        }
    }

    /**
     * Ports are positioned relative to their parent node. The margins of a node are non negative
     * values to either direction, starting from the node's original bounding box (size).
     * 
     * @param port
     * @param node
     */
    private void assertWithinMargins(final LPort port, final LNode node) {
        LInsets margin = node.getMargin();
        double x = port.getPosition().x;
        double y = port.getPosition().y;
        double w = port.getSize().x;
        double h = port.getSize().y;

        if (x < 0) {
            assertTrue(Math.abs(x) <= margin.left);
        } else {
            assertTrue(x + w <= node.getSize().x + margin.right);
        }

        if (y < 0) {
            assertTrue(Math.abs(y) <= margin.bottom);
        } else {
            assertTrue(y + h <= node.getSize().y + margin.bottom);
        }

    }
}
