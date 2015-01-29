package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

public class GreedySwitcherIntegrationTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";
    private final GreedyType[] allOneSidedTypes = { GreedyType.ONE_SIDED_COUNTER,
            GreedyType.ONE_SIDED_CROSSING_MATRIX, GreedyType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX };
    private final GreedyType[] allTwoSidedTypes = { GreedyType.TWO_SIDED_COUNTER,
            GreedyType.TWO_SIDED_CROSSING_MATRIX, GreedyType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX };
    private LGraph firstGraph;

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
        firstGraph = state.getGraphs().get(0);
    }

    @Test
    public void allOneSidedMethodsShouldResultInSameGraph() {
        assertAllTypesResultsInSameGraph(allOneSidedTypes);
    }

    private List<List<LNode>> getNodeOrderAfterExecuting(final GreedyType greedyType) {
        firstGraph.setProperty(Properties.GREEDY_TYPE, greedyType);
        layered.runLayoutTestStep(state);
        return copyNodeOrder(firstGraph);
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
        assertAllTypesResultsInSameGraph(allTwoSidedTypes);
    }

    private void assertAllTypesResultsInSameGraph(final GreedyType[] types) {
        List<List<LNode>> firstNodeOrder = getNodeOrderAfterExecuting(types[0]);
        for (GreedyType greedyType : types) {
            List<List<LNode>> nodeOrder = getNodeOrderAfterExecuting(greedyType);
            assertThat(firstNodeOrder, is(nodeOrder));
        }
    }

    @Test
    public void crossingNumbersShouldNotBeWorseAfterward() {
        LNode[][] graphLNodes = getAsLNodeArray(firstGraph);
        CrossingCounter crossCounter = new CrossingCounter(graphLNodes);
        int oldCrossingCount = crossCounter.countAllCrossingsInGraphWithOrder(graphLNodes);

        for (GreedyType oneSidedType : allOneSidedTypes) {
            assertFewerOrEqualCrossingsAfterSwitching(crossCounter, oldCrossingCount, oneSidedType);
        }

        for (GreedyType oneSidedType : allTwoSidedTypes) {
            assertFewerOrEqualCrossingsAfterSwitching(crossCounter, oldCrossingCount, oneSidedType);
        }
    }

    private LNode[][] getAsLNodeArray(final LGraph graph) {
        LNode[][] result = new LNode[graph.getLayers().size()][];
        for (Layer layer : graph) {
            result[layer.getIndex()] = layer.getNodes().toArray(new LNode[0]);
        }
        return result;
    }

    private void assertFewerOrEqualCrossingsAfterSwitching(final CrossingCounter crossCounter,
            final int oldCrossingCount, final GreedyType oneSidedType) {
        firstGraph.setProperty(Properties.GREEDY_TYPE, oneSidedType);
        layered.runLayoutTestStep(state);
        LNode[][] newOrder = getAsLNodeArray(firstGraph);
        int newCrossingCount = crossCounter.countAllCrossingsInGraphWithOrder(newOrder);
        assertThat(newCrossingCount, is(lessThanOrEqualTo(oldCrossingCount)));
    }
}
