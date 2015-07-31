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

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.NodeMarginCalculator;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link NodeMarginCalculator}.
 * 
 * @author uru
 */
public class NodeMarginCalculatorTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public NodeMarginCalculatorTest(final GraphTestObject testObject,
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
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(NodeMarginCalculator.class, state);
    }

    /**
     * Every node as an assigned margin unequal to {@code null} and greater or equal to 0.
     */
    @Test
    public void testNodeMargins() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    assertTrue(node.getMargin() != null);
                    assertTrue(node.getMargin().top >= 0);
                    assertTrue(node.getMargin().right >= 0);
                    assertTrue(node.getMargin().bottom >= 0);
                    assertTrue(node.getMargin().left >= 0);
                }
            }
        }
    }

}
