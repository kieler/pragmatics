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

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.layered.intermediate.PortSideProcessor;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link PortSideProcessor}.
 * 
 * @author uru
 */
public class PortSideProcessorTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public PortSideProcessorTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
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
        layered.getLayoutTestConfiguration().add(
                LayoutProcessorStrategy.PORT_SIDE_PROCESSOR.create());
        lgraphs = layered.runLayoutTestUntil(PortSideProcessor.class);
    }

    /**
     * No node exists with {@link PortConstraints} set to {@link PortConstraints#FREE} or
     * {@link PortConstraints#UNDEFINED}.
     */
    @Test
    public void testNodeConstraints() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    Assert.assertTrue(node.getProperty(LayoutOptions.PORT_CONSTRAINTS) 
                            != PortConstraints.FREE);
                    Assert.assertTrue(node.getProperty(LayoutOptions.PORT_CONSTRAINTS) 
                            != PortConstraints.UNDEFINED);
                }
            }
        }
    }

    /**
     * Every port has a specified side.
     */
    @Test
    public void testPortSides() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    for (LPort port : node.getPorts()) {
                        Assert.assertTrue(port.getSide() != PortSide.UNDEFINED);
                    }
                }
            }
        }
    }

}
