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

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeSplitter;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link LongEdgeSplitter}.
 * 
 * @author uru
 */
public class LongEdgeSplitterTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public LongEdgeSplitterTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
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
        lgraphs = layered.runLayoutTestUntil(LongEdgeSplitter.class);
    }

    /**
     * All edges connect directly adjacent layer where the source layer's index is smaller than the
     * target layer's index.
     */
    @Test
    public void testEdgesBetweenAdjacentLayers() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    for (LEdge edge : node.getOutgoingEdges()) {
                        // increasing layer index
                        Assert.assertTrue(layer.getIndex() < edge.getTarget().getNode().getLayer()
                                .getIndex());
                        // directly adjacent index
                        Assert.assertTrue(layer.getIndex() + 1 == edge.getTarget().getNode()
                                .getLayer().getIndex());
                    }
                }
            }
        }
    }

}
