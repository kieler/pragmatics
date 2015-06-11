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
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeJoiner;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link LongEdgeJoiner}.
 * 
 * @author uru
 */
public class LongEdgeJoinerTest extends AbstractLayeredProcessorTest {

    private int noOverallNodes = 0;
    private int noTypeNodes = 0;

    // CHECKSTYLEOFF javadoc
    public LongEdgeJoinerTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
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
        layered.runLayoutTestUntil(LongEdgeJoiner.class, false, state);

        // count the number of overall nodes and of the tested type
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    noOverallNodes++;
                    if (node.getNodeType() == NodeType.LONG_EDGE) {
                        noTypeNodes++;
                    }
                }
            }
        }

        layered.runLayoutTestUntil(LongEdgeJoiner.class, true, state);

    }

    /**
     * No node exists with {@link NodeType} {@link NodeType#LONG_EDGE}. Only such nodes were
     * deleted.
     */
    @Test
    public void testRemovedNodes() {
        int noNodesAfter = 0;
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    assertTrue(node.getNodeType() != NodeType.LONG_EDGE);
                    noNodesAfter++;
                }
            }
        }

        assertTrue(noNodesAfter == (noOverallNodes - noTypeNodes));
    }

}
