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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Tests Greedy Switcher in the plug-in context on real graphs.
 * 
 * @author alan
 *
 */
public class GreedySwitcherIntegrationTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";
    private final GreedySwitchType[] allOneSidedTypes = { GreedySwitchType.ONE_SIDED_COUNTER,
            GreedySwitchType.ONE_SIDED_CROSSING_MATRIX,
            GreedySwitchType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX };
    private final GreedySwitchType[] allTwoSidedTypes = { GreedySwitchType.TWO_SIDED_COUNTER,
            GreedySwitchType.TWO_SIDED_CROSSING_MATRIX,
            GreedySwitchType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX };
    private LGraph graph;
    private List<List<LNode>> originalOrder;

    // CHECKSTYLEOFF javadoc
    public GreedySwitcherIntegrationTest(final GraphTestObject testObject,
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
     * {@inheritDoc} TODO-alan ???
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
        layered.runLayoutTestUntil(GreedySwitchProcessor.class, false, state);
        graph = state.getGraphs().get(0);
    }

    @Test
    public void allOneSidedMethodsShouldResultInSameGraph() {
        testTypes(allOneSidedTypes);
    }

    private void testTypes(final GreedySwitchType[] types) {
        for (LGraph lGraph : state.getGraphs()) {
            graph = lGraph;
            originalOrder = copyNodeOrder(graph);
            assertAllTypesResultsInSameGraph(types);
        }
    }

    private List<List<LNode>> getNodeOrderAfterExecuting(final GreedySwitchType greedyType) {
        graph.setProperty(Properties.GREEDY_TYPE, greedyType);
        graph.setProperty(LayoutOptions.SEPARATE_CC, false);
        resetGraphToOriginalOrder();
        List<ILayoutProcessor> algorithms =
                state.getGraphs().get(0).getProperty(InternalProperties.PROCESSORS);
        ILayoutProcessor processor = getGreedyProcessor(algorithms);
        processor.process(graph, new BasicProgressMonitor());
        return copyNodeOrder(graph);
    }

    private ILayoutProcessor getGreedyProcessor(final List<ILayoutProcessor> algorithms) {
        for (ILayoutProcessor iLayoutProcessor : algorithms) {
            if (iLayoutProcessor instanceof GreedySwitchProcessor) {
                return iLayoutProcessor;
            }
        }
        throw new RuntimeException("Greedy processor not in configuration");
    }

    private void resetGraphToOriginalOrder() {// TODO-alan sort methods
        for (int i = 0; i < graph.getLayers().size(); i++) {
            Layer layer = graph.getLayers().get(i);
            for (int j = 0; j < layer.getNodes().size(); j++) {
                layer.getNodes().set(j, originalOrder.get(i).get(j));
            }
        }
    }

    private List<List<LNode>> copyNodeOrder(final LGraph firstGraph) {
        List<List<LNode>> copyList = new ArrayList<List<LNode>>();
        for (Layer layer : firstGraph) {
            List<LNode> layerList = new ArrayList<LNode>();
            copyList.add(layerList);
            for (LNode node : layer) {
                layerList.add(node);
            }
        }
        return copyList;
    }

    @Test
    public void allTwoSidedMethodsShouldResultInSameGraph() {
        testTypes(allTwoSidedTypes);
    }

    private void assertAllTypesResultsInSameGraph(final GreedySwitchType[] types) {
        List<List<LNode>> firstNodeOrder = getNodeOrderAfterExecuting(types[0]);
        for (GreedySwitchType greedyType : types) {
            List<List<LNode>> nodeOrder = getNodeOrderAfterExecuting(greedyType);
            assertThat(greedyType.toString(), firstNodeOrder, is(nodeOrder));
        }
    }

    @Test
    public void crossingNumbersShouldNotBeWorseAfterward() {
        LNode[][] graphLNodes = AllCrossingCounterTest.getAsLNodeArray(graph);
        AllCrossingCounter crossCounter = new AllCrossingCounter(graphLNodes);
        int oldCrossingCount = crossCounter.countAllCrossingsInGraphWithOrder(graphLNodes);

        for (GreedySwitchType oneSidedType : allOneSidedTypes) {
            assertFewerOrEqualCrossingsAfterSwitching(crossCounter, oldCrossingCount, oneSidedType);
        }

        for (GreedySwitchType oneSidedType : allTwoSidedTypes) {
            assertFewerOrEqualCrossingsAfterSwitching(crossCounter, oldCrossingCount, oneSidedType);
        }
    }

    private void assertFewerOrEqualCrossingsAfterSwitching(final AllCrossingCounter crossCounter,
            final int oldCrossingCount, final GreedySwitchType oneSidedType) {
        graph.setProperty(Properties.GREEDY_TYPE, oneSidedType);
        layered.runLayoutTestStep(state);
        LNode[][] newOrder = AllCrossingCounterTest.getAsLNodeArray(graph);
        int newCrossingCount = crossCounter.countAllCrossingsInGraphWithOrder(newOrder);
        assertThat(oneSidedType.toString(), newCrossingCount,
                is(lessThanOrEqualTo(oldCrossingCount)));
    }
}
