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

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.EdgeAndLayerConstraintEdgeReverser;
import de.cau.cs.kieler.klay.layered.intermediate.GreedySwitchOnDemandCrossingMatrixProcessor;
import de.cau.cs.kieler.klay.layered.intermediate.IncidentEdgeCrossCounter;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link EdgeAndLayerConstraintEdgeReverser}.
 * 
 * @author uru
 */
public class GreedySwitcherTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";

    // CHECKSTYLEOFF javadoc
    public GreedySwitcherTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
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
     * {@inheritDoc} TODOALAN ???
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    private GreedySwitchOnDemandCrossingMatrixProcessor greedyProcessor;

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(GreedySwitchOnDemandCrossingMatrixProcessor.class, false, state);
        // TODOALAN change nodeposition westnode etc. to reflection
        List<ILayoutProcessor> processors = layered.getLayoutTestConfiguration(state);
        greedyProcessor =
                (GreedySwitchOnDemandCrossingMatrixProcessor) processors.get(state.getStep());

    }

    @Test
    public void noEntryInCrossingMatrixShouldBeNegative() {
        greedyProcessor.initialize(state.getGraphs().get(0), state.getGraphs().get(0).getLayers()
                .size());
        // forward sweep
        for (LGraph graph : state.getGraphs()) {
            for (int i = 1; i < graph.getLayers().size(); i++) {
                Layer layer = graph.getLayers().get(i);
                int[] nodePositions = greedyProcessor.getNodePositions()[i];
                int[] nodeDegrees = greedyProcessor.getWestNodeDegrees()[i];
                for (LNode nodeOne : layer) {
                    for (LNode nodeTwo : layer) {
                        IncidentEdgeCrossCounter crossCounter =
                                new IncidentEdgeCrossCounter(nodeOne, nodeTwo, true, nodeDegrees,
                                        nodePositions);
                        crossCounter.calculateCrossingNumber();
                        assertTrue(crossCounter.getCrossingsForOrderIJ() >= 0);
                        assertTrue(crossCounter.getCrossingsForOrderJI() >= 0);
                    }
                }
            }
        }
        // backward sweep
        for (LGraph graph : state.getGraphs()) {
            for (int i = 1; i < graph.getLayers().size(); i++) {
                Layer layer = graph.getLayers().get(i);
                int[] nodePositions = greedyProcessor.getNodePositions()[i];
                int[] nodeDegrees = greedyProcessor.getEastNodeDegrees()[i];
                for (LNode nodeOne : layer) {
                    for (LNode nodeTwo : layer) {
                        IncidentEdgeCrossCounter crossCounter =
                                new IncidentEdgeCrossCounter(nodeOne, nodeTwo, false, nodeDegrees,
                                        nodePositions);
                        crossCounter.calculateCrossingNumber();
                        assertTrue(crossCounter.getCrossingsForOrderIJ() >= 0);
                        assertTrue(crossCounter.getCrossingsForOrderJI() >= 0);
                    }
                }
            }
        }
    }

    @Test
    public void exampleCrossingMatricesShouldBePlausible() {
        greedyProcessor.initialize(state.getGraphs().get(0), state.getGraphs().get(0).getLayers()
                .size());

        int[][][] simpleGraphForwardCrossingMatrices =
                {
                        { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } },
                        { { 0, 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 }, { 1, 1, 0, 1, 0, 0 },
                                { 1, 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0 }, { 1, 1, 1, 1, 0, 0 } },
                        { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 2, 1, 1, 1, 0, 0, 0 },
                                { 1, 1, 1, 2, 1, 1, 1, 1, 0, 0 }, { 1, 1, 1, 2, 1, 1, 1, 1, 1, 0 } },
                        { { 0, 0, 0, 0 }, { 6, 0, 0, 0 }, { 18, 2, 0, 0 }, { 6, 1, 3, 0 } },
                        { { 0 } } };

        LGraph simpleGraph = state.getGraphs().get(0);

        for (int i = 1; i < simpleGraph.getLayers().size(); i++) {
            Layer layer = simpleGraph.getLayers().get(i);
            int[] nodePositions = greedyProcessor.getNodePositions()[i];
            int[] nodeDegrees = greedyProcessor.getEastNodeDegrees()[i];
            for (int j = 0; j < layer.getNodes().size(); j++) {
                LNode nodeOne = layer.getNodes().get(j);
                for (int k = 0; k < layer.getNodes().size(); k++) {
                    LNode nodeTwo = layer.getNodes().get(k);
                    IncidentEdgeCrossCounter crossCounter =
                            new IncidentEdgeCrossCounter(nodeOne, nodeTwo, true, nodeDegrees,
                                    nodePositions);
                    crossCounter.calculateCrossingNumber();
                    int actualEntry = crossCounter.getCrossingsForOrderIJ();
                    String failureMessage = "Failed in layer " + i + " nodes " + j + " above " + k;
                    assertEquals(failureMessage, simpleGraphForwardCrossingMatrices[i - 1][j][k],
                            actualEntry);
                    
                    int otherOrder = crossCounter.getCrossingsForOrderJI();
                    failureMessage = "Failed in layer " + i + " nodes " + k + " above " + j + " order ji ";
                    assertEquals(failureMessage, simpleGraphForwardCrossingMatrices[i - 1][k][j], otherOrder);
                }
            }
        }

        int[][][] inLayerEdgesBackwardCrossingMatrices = { { {} } };

    }
}
