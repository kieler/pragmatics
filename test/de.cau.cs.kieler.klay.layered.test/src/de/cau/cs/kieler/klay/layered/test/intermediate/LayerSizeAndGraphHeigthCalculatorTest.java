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
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayerSizeAndGraphHeightCalculator;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link LayerSizeAndGraphHeightCalculator}.
 * 
 * @author uru
 */
public class LayerSizeAndGraphHeigthCalculatorTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public LayerSizeAndGraphHeigthCalculatorTest(final GraphTestObject testObject,
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
        layered.runLayoutTestUntil(LayerSizeAndGraphHeightCalculator.class, state);
    }

    /**
     * All nodes are contained within the y extend of the overall graph.
     */
    @Test
    public void testAllNodesContained() {
        for (LGraph g : state.getGraphs()) {

            double top = 0.0 - g.getOffset().y;
            double bottom = g.getSize().y - g.getOffset().y;
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    // top position
                    double nodeTop = node.getPosition().y - node.getMargin().top;
                    assertTrue(nodeTop > top || isCloseEnough(nodeTop, top));

                    // bottom position
                    double nodeBottom =
                            node.getPosition().y + node.getSize().y + node.getMargin().bottom;
                    assertTrue(nodeBottom < bottom || isCloseEnough(bottom, nodeBottom));
                }
            }
        }
    }

}
