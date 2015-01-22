package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

@RunWith(Parameterized.class)
public class GreedySwitchTest {

    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private final GreedySwitchProcessor greedySwitcher;
    private final IKielerProgressMonitor monitor;
    private final GreedyType greedyType;

    public GreedySwitchTest(final GreedyType greedyType) {
        this.greedyType = greedyType;
        greedySwitcher = new GreedySwitchProcessor(greedyType);
        monitor = setUpDummyMonitor();
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedyType.ONE_SIDED_COUNTER, },
                { GreedyType.ONE_SIDED_CROSSING_MATRIX },
                { GreedyType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX },
        // { GreedyType.TWO_SIDED_COUNTER },
        // { GreedyType.TWO_SIDED_CROSSING_MATRIX },
        // { GreedyType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX }
                });
    }

    @Test
    public void shouldSwitchCross() {
        graph = creator.getCrossFormedGraph();

        int layerIndex = 1;
        List<LNode> expectedOrder = switchOrderOfNodesInLayer(0, 1, layerIndex);

        greedySwitcher.process(graph, monitor);

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();
        int layerIndex = 0;
        switchOrderOfNodesInLayer(0, 0, layerIndex);
        // should cause no errors
    }

    @Test
    public void inLayerSwitchable() {
        graph = creator.getInLayerEdgesGraph();

        int layerIndex = 1;
        List<LNode> expectedOrder = switchOrderOfNodesInLayer(0, 1, layerIndex);

        greedySwitcher.process(graph, monitor);

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    @Test
    public void emptyGraphShouldResultInException() {
        graph = creator.getEmptyGraph();

        try {
            greedySwitcher.process(graph, monitor);
            fail("Did not cause AssertionError");
        } catch (AssertionError e) {
        }

    }

    @Test
    public void multipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        int layerIndex = 1;
        List<LNode> expectedOrder = switchOrderOfNodesInLayer(0, 1, layerIndex);

        greedySwitcher.process(graph, monitor);

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    @Test
    public void selfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        int layerIndex = 1;
        List<LNode> expectedOrder = switchOrderOfNodesInLayer(0, 1, layerIndex);

        greedySwitcher.process(graph, monitor);

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    /**
     * <pre>
     *     ______
     * *---|____|
     *      |  |  ____
     *      *--+--|  |
     *         |  |  |
     *         *--|__|
     * </pre>
     * 
     * The one-side decider should switch north-south port crossings in the center layer.
     */
    @Ignore
    // TODO-alan implement for crossing matrix counters
    public void northSouthPortCrossing() {
        graph = creator.getThreeLayerNorthSouthCrossingGraph();

        int layerIndex = 1;
        List<LNode> expectedOrderOneSided = switchOrderOfNodesInLayer(0, 1, layerIndex);
        List<LNode> expectedOrderTwoSided = new ArrayList<LNode>(getNodesInLayer(layerIndex));

        greedySwitcher.process(graph, monitor);
        if (greedyType.isOneSided()) {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderOneSided));
        } else {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderTwoSided));
        }
    }

    /**
     * <pre>
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * Port order not fixed.
     * </pre>
     */
    @Test
    public void moreComplex() {
        graph = creator.getMoreComplexThreeLayerGraph();
        List<LNode> expectedOrderLayerOne = null;
        List<LNode> expectedOrderLayerThree = null;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(1, 2, 0);
            expectedOrderLayerThree = switchOrderOfNodesInLayer(0, 1, 2);
            greedySwitcher.process(graph, monitor);
            assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
            assertThat("Layer three", getNodesInLayer(2), is(expectedOrderLayerThree));
        } else {
            fail();
        }

    }

    @Test
    public void switchOnlyTrueForOneSided() {
        graph = creator.getSwitchOnlyOneSided();

        int layerIndex = 1;
        List<LNode> expectedOrderOneSided = switchOrderOfNodesInLayer(0, 1, layerIndex);
        List<LNode> expectedOrderTwoSided = new ArrayList<LNode>(getNodesInLayer(layerIndex));

        greedySwitcher.process(graph, monitor);
        if (greedyType.isOneSided()) {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderOneSided));
        } else {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderTwoSided));
        }
    }

    private List<LNode> getNodesInLayer(final int layerIndex) {
        return graph.getLayers().get(layerIndex).getNodes();
    }

    private List<LNode> switchOrderOfNodesInLayer(final int nodeOne, final int nodeTwo,
            final int layerIndex) {
        List<LNode> layer = getNodesInLayer(layerIndex);
        LNode firstNode = layer.get(nodeOne);
        LNode secondNode = layer.get(nodeTwo);
        List<LNode> switchedList = new ArrayList<LNode>(layer);
        switchedList.set(nodeOne, secondNode);
        switchedList.set(nodeTwo, firstNode);
        return switchedList;
    }

    private IKielerProgressMonitor setUpDummyMonitor() {
        return new IKielerProgressMonitor() {
            public void worked(final float work) {
            }

            public IKielerProgressMonitor subTask(final float work) {
                return this;
            }

            public boolean isRunning() {
                return true;
            }

            public boolean isCanceled() {
                return false;
            }

            public String getTaskName() {
                return "test task";
            }

            public List<IKielerProgressMonitor> getSubMonitors() {
                return new ArrayList<IKielerProgressMonitor>();
            }

            public IKielerProgressMonitor getParentMonitor() {
                return this;
            }

            public double getExecutionTime() {
                return 0.1d;
            }

            public void done() {
            }

            public boolean begin(final String name, final float totalWork) {
                return true;
            }
        };
    }
}
