package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

///*
// * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
// *
// * http://www.informatik.uni-kiel.de/rtsys/kieler/
// * 
// * Copyright 2013 by
// * + Christian-Albrechts-University of Kiel
// *   + Department of Computer Science
// *     + Real-Time and Embedded Systems Group
// * 
// * This code is provided under the terms of the Eclipse Public License (EPL).
// * See the file epl-v10.html for the license text.
// */
//package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.google.common.collect.Lists;
//
//import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
//import de.cau.cs.kieler.klay.layered.graph.LGraph;
//import de.cau.cs.kieler.klay.layered.graph.LNode;
//import de.cau.cs.kieler.klay.layered.graph.LPort;
//import de.cau.cs.kieler.klay.layered.graph.Layer;
//import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.GreedySwitchProcessor;
//import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.InLayerEdgeNeighboringNodeCrossingCounter;
//import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.InBetweenLayerEdgeTwoNodeCrossingCounter;
//import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
//import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
//import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
//import de.cau.cs.kieler.klay.test.utils.TestPath;
//
///**
// * TODO-alan make sensible somehow.
// * 
// * @author alan
// */
//public class AlansTwoNodeCrossingCounterTest extends AbstractLayeredProcessorTest {
//
//    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";
//
//    // CHECKSTYLEOFF javadoc
//    public AlansTwoNodeCrossingCounterTest(final GraphTestObject testObject,
//            final ILayoutConfigurator config) {
//        super(testObject, config);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected TestPath[] getBundleTestPath() {
//        TestPath[] testPaths = { new TestPath(TEST_FOLDER, false, false, TestPath.Type.KGRAPH) };
//        return testPaths;
//    }
//
//    /**
//     * {@inheritDoc} TODO-alan ???
//     */
//    @Override
//    protected List<ILayoutConfigurator> getConfigurators() {
//        return Lists.newArrayList();
//    }
//
//    private GreedySwitchProcessor greedyProcessor;
//
//    /**
//     * Run the layout algorithm until the specified strategy finished.
//     */
//    @Before
//    public void runUntil() {
//        layered.runLayoutTestUntil(GreedySwitchProcessor.class, true, state);
//        List<ILayoutProcessor> processors = layered.getLayoutTestConfiguration(state);
//        greedyProcessor = (GreedySwitchProcessor) processors.get(state.getStep() - 1);
//    }
//
//    @Test
//    public void noEntryInCrossingMatrixShouldBeNegative() {
//        // forward sweep
//        Map<LPort, Integer> portIndices = greedyProcessor.getPortIndices();
//        for (LGraph graph : state.getGraphs()) {
//            for (int i = 1; i < graph.getLayers().size(); i++) {
//                Layer layer = graph.getLayers().get(i);
//                int[] nodePositions = greedyProcessor.getNodePositions()[i];
//                int[] nodeDegrees = greedyProcessor.getWestNodeDegrees()[i];
//                for (LNode nodeOne : layer) {
//                    for (LNode nodeTwo : layer) {
//                        InBetweenLayerEdgeTwoNodeCrossingCounter crossCounter =
//                                new InBetweenLayerEdgeTwoNodeCrossingCounter(nodeOne, nodeTwo, nodeDegrees,
//                                        nodePositions, portIndices);
//                        crossCounter.countEasternCrossings();
//                        assertTrue(crossCounter.getCrossingsForOrderUpperLower() >= 0);
//                        assertTrue(crossCounter.getCrossingsForOrderLowerUpper() >= 0);
//                    }
//                }
//            }
//        }
//        // backward sweep
//        for (LGraph graph : state.getGraphs()) {
//            for (int i = 1; i < graph.getLayers().size(); i++) {
//                Layer layer = graph.getLayers().get(i);
//                int[] nodePositions = greedyProcessor.getNodePositions()[i];
//                int[] nodeDegrees = greedyProcessor.getEastNodeDegrees()[i];
//                for (LNode nodeOne : layer) {
//                    for (LNode nodeTwo : layer) {
//                        InBetweenLayerEdgeTwoNodeCrossingCounter crossCounter =
//                                new InBetweenLayerEdgeTwoNodeCrossingCounter(nodeOne, nodeTwo, nodeDegrees,
//                                        nodePositions, portIndices);
//                        crossCounter.countEasternCrossings();
//                        assertTrue(crossCounter.getCrossingsForOrderUpperLower() >= 0);
//                        assertTrue(crossCounter.getCrossingsForOrderLowerUpper() >= 0);
//                    }
//                }
//            }
//        }
//    }
//
//    @Test
//    public void simpleGraphCrossingMatricesShouldBePlausible() {
//
//        int[][][] simpleGraphForwardCrossingMatrices =
//                {
//                        { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
//                                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } },
//                        { { 0, 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 }, { 1, 1, 0, 1, 0, 0 },
//                                { 1, 1, 1, 0, 0, 0 }, { 1, 1, 1, 1, 0, 0 }, { 1, 1, 1, 1, 0, 0 } },
//                        { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
//                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
//                                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 2, 1, 1, 1, 0, 0, 0 },
//                                { 1, 1, 1, 2, 1, 1, 1, 1, 0, 0 }, { 1, 1, 1, 2, 1, 1, 1, 1, 1, 0 } },
//                        { { 0, 0, 0, 0 }, { 6, 0, 0, 0 }, { 18, 2, 0, 0 }, { 6, 1, 3, 0 } },
//                        { { 0 } } };
//
//        LGraph simpleGraph = state.getGraphs().get(0);
//
//        checkCrossingMatrixForEquality(simpleGraphForwardCrossingMatrices, simpleGraph, true);
//    }
//
//    /**
//     * @param expectedMatrices
//     * @param simpleGraph
//     * @param isForwardSweep
//     */
//    private void checkCrossingMatrixForEquality(final int[][][] expectedMatrices,
//            final LGraph simpleGraph, final boolean isForwardSweep) {
//
//        int startIndex = isForwardSweep ? 1 : 0;
//        int endIndex =
//                isForwardSweep ? simpleGraph.getLayers().size()
//                        : simpleGraph.getLayers().size() - 1;
//
//        for (int i = startIndex; i < endIndex; i++) {
//            Layer layer = simpleGraph.getLayers().get(i);
//            int[] nodePositions = greedyProcessor.getNodePositions()[i];
//            int[] nodeDegrees = greedyProcessor.getEastNodeDegrees()[i];
//            for (int j = 0; j < layer.getNodes().size(); j++) {
//                LNode nodeOne = layer.getNodes().get(j);
//                for (int k = 0; k < layer.getNodes().size(); k++) {
//                    LNode nodeTwo = layer.getNodes().get(k);
//                    Map<LPort, Integer> portIndices = greedyProcessor.getPortIndices();
//                    InBetweenLayerEdgeTwoNodeCrossingCounter crossCounter =
//                            new InBetweenLayerEdgeTwoNodeCrossingCounter(nodeOne, nodeTwo, nodeDegrees,
//                                    nodePositions, portIndices);
//                    crossCounter.countEasternCrossings();
//                    int actualEntry = crossCounter.getCrossingsForOrderUpperLower();
//
//                    InLayerEdgeNeighboringNodeCrossingCounter inLayerEdgeCrossCounter =
//                            new InLayerEdgeNeighboringNodeCrossingCounter(nodePositions, nodeDegrees,
//                                    false, portIndices);
//                    inLayerEdgeCrossCounter.countCrossings(nodeOne, nodeTwo);
//                    actualEntry += inLayerEdgeCrossCounter.getCrossingsForOrderUpperLower();
//
//                    String failureMessage = "Failed in layer " + i + " nodes " + j + " above " + k;
//                    int currentEntryInExpectedMatrix = isForwardSweep ? i - 1 : i;
//                    assertEquals(failureMessage,
//                            expectedMatrices[currentEntryInExpectedMatrix][j][k], actualEntry);
//
//                    int otherOrder = crossCounter.getCrossingsForOrderLowerUpper();
//                    otherOrder += inLayerEdgeCrossCounter.getCrossingsForOrderLowerUpper();
//                    failureMessage =
//                            "Failed in layer " + i + " nodes " + k + " above " + j + " order ji ";
//                    assertEquals(failureMessage,
//                            expectedMatrices[currentEntryInExpectedMatrix][k][j], otherOrder);
//                }
//            }
//        }
//    }
//
//    @Test
//    public void inLayerEdgeCrossingMatrixShouldBePlausible() {
//
//        int[][][] inLayerEdgesBackwardCrossingMatrices =
//                {
//                        { { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 1, 1, 0, 1, 0 },
//                                { 0, 0, 1, 0, 1 }, { 0, 1, 1, 0, 0 } }, { { 0 } } };
//        LGraph simpleGraph = state.getGraphs().get(0);
//
//        for (int i = 1; i < simpleGraph.getLayers().size(); i++) {
//            Layer layer = simpleGraph.getLayers().get(i);
//            int[] nodePositions = greedyProcessor.getNodePositions()[i];
//            int[] nodeDegrees = greedyProcessor.getEastNodeDegrees()[i];
//            for (int j = 0; j < layer.getNodes().size(); j++) {
//                LNode nodeOne = layer.getNodes().get(j);
//                for (int k = 0; k < layer.getNodes().size(); k++) {
//                    LNode nodeTwo = layer.getNodes().get(k);
//                    Map<LPort, Integer> portIndices = greedyProcessor.getPortIndices();
//                    InBetweenLayerEdgeTwoNodeCrossingCounter crossCounter =
//                            new InBetweenLayerEdgeTwoNodeCrossingCounter(nodeOne, nodeTwo, nodeDegrees,
//                                    nodePositions, portIndices);
//                    crossCounter.countEasternCrossings();
//                    int actualEntry = crossCounter.getCrossingsForOrderUpperLower();
//
//                    InLayerEdgeNeighboringNodeCrossingCounter inLayerEdgeCrossCounter =
//                            new InLayerEdgeNeighboringNodeCrossingCounter(nodePositions, nodeDegrees,
//                                    false, portIndices);
//                    inLayerEdgeCrossCounter.countCrossings(nodeOne, nodeTwo);
//                    actualEntry += inLayerEdgeCrossCounter.getCrossingsForOrderUpperLower();
//
//                    String failureMessage =
//                            "Failed in layer " + i + " nodes " + j + " above " + k + "Entry was "
//                                    + inLayerEdgesBackwardCrossingMatrices[i - 1][j][k]
//                                    + " should have been below " + actualEntry;
//                    assertTrue(failureMessage,
//                            inLayerEdgesBackwardCrossingMatrices[i - 1][j][k] <= actualEntry);
//
//                    int otherOrderActualEntry = crossCounter.getCrossingsForOrderLowerUpper();
//                    otherOrderActualEntry +=
//                            inLayerEdgeCrossCounter.getCrossingsForOrderLowerUpper();
//
//                    failureMessage =
//                            "Failed in layer " + i + " nodes " + k + " above " + j
//                                    + " order ji.  Entry was "
//                                    + inLayerEdgesBackwardCrossingMatrices[i - 1][k][j]
//                                    + " should have been below " + otherOrderActualEntry;
//                    assertTrue(
//                            failureMessage,
//                            inLayerEdgesBackwardCrossingMatrices[i - 1][k][j] <= otherOrderActualEntry);
//                }
//            }
//        }
//    }
//
//    @Test
//    public void highCardinalityEdgesCrossingMatrixShouldBePlausible() {
//
//        int[][][] expectedCrossingMatrices =
//                {
//                        { { 0, 0, 0 }, { 12, 0, 8 }, { 24, 50, 0 } },
//                        { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
//                                { 12, 0, 0, 0, 0, 0, 0 }, { 6, 0, 0, 0, 0, 0, 0 },
//                                { 6, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
//                                { 6, 0, 0, 0, 0, 0, 0 } }, { { 0, 0 }, { 0, 0 } } };
//
//        LGraph simpleGraph = state.getGraphs().get(0);
//
//        checkCrossingMatrixForEquality(expectedCrossingMatrices, simpleGraph, false);
//
//    }
// }
