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
import de.cau.cs.kieler.klay.layered.properties.Properties;

@RunWith(Parameterized.class)
public class GreedySwitchTest {
    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private final GreedySwitchProcessor greedySwitcher;
    private final IKielerProgressMonitor monitor;
    private final GreedyType greedyType;

    public GreedySwitchTest(final GreedyType greedyType) {
        this.greedyType = greedyType;
        greedySwitcher = new GreedySwitchProcessor();
        monitor = setUpDummyMonitor();
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedyType.ONE_SIDED_COUNTER, },
                { GreedyType.ONE_SIDED_CROSSING_MATRIX },
                { GreedyType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX },
                { GreedyType.TWO_SIDED_COUNTER }, { GreedyType.TWO_SIDED_CROSSING_MATRIX },
                { GreedyType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX } });
    }

    @Test
    public void shouldSwitchCross() {
        graph = creator.getCrossFormedGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    void startGreedySwitcherWithCurrentType() {
        graph.setProperty(Properties.GREEDY_TYPE, greedyType);
        greedySwitcher.process(graph, monitor);
    }

    @Test
    public void constraintsPreventSwitchInSecondLayer() {
        graph = creator.getCrossFormedGraphWithConstraintsInSecondLayer();

        List<LNode> expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1);

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void constraintsPreventAnySwitch() {
        graph = creator.getCrossFormedGraphConstraintsPreventAnySwitch();

        List<LNode> expectedOrderLayerOne = getNodesInLayer(0);
        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1);

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void layoutUnitConstraintPreventsSwitch() {
        graph = creator.getNodesInSameLayoutUnitPreventSwitch();
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

        startGreedySwitcherWithCurrentType();

        assertThat(getNodesInLayer(layerIndex), is(expectedOrder));
    }

    @Test
    public void emptyGraphShouldResultInException() {
        graph = creator.getEmptyGraph();

        try {
            startGreedySwitcherWithCurrentType();
            fail("Did not cause AssertionError");
        } catch (AssertionError e) {
        }

    }

    @Test
    public void multipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
    }

    @Test
    public void selfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = getNodesInLayer(0);
            expectedOrderLayerTwo = switchOrderOfNodesInLayer(0, 1, 1);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(0, 1, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
        }

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
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

        startGreedySwitcherWithCurrentType();
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

        List<LNode> expectedOrderLayerOne;
        List<LNode> expectedOrderLayerTwo;
        List<LNode> expectedOrderLayerThree;
        if (greedyType.isOneSided()) {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(1, 2, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
            expectedOrderLayerThree = switchOrderOfNodesInLayer(0, 1, 2);
        } else {
            expectedOrderLayerOne = switchOrderOfNodesInLayer(1, 2, 0);
            expectedOrderLayerTwo = getNodesInLayer(1);
            expectedOrderLayerThree = switchOrderOfNodesInLayer(0, 1, 2);
        }
        startGreedySwitcherWithCurrentType();
        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderLayerOne));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderLayerTwo));
        assertThat("Layer three", getNodesInLayer(2), is(expectedOrderLayerThree));
    }

    @Test
    public void switchOnlyForOneSided() {
        graph = creator.getSwitchOnlyOneSided();

        int layerIndex = 1;
        List<LNode> expectedOrderOneSided = switchOrderOfNodesInLayer(0, 1, layerIndex);
        List<LNode> expectedOrderTwoSided = new ArrayList<LNode>(getNodesInLayer(layerIndex));

        startGreedySwitcherWithCurrentType();
        if (greedyType.isOneSided()) {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderOneSided));
        } else {
            assertThat(getNodesInLayer(layerIndex), is(expectedOrderTwoSided));
        }
    }

    @Test
    public void doesNotWorsenCrossAmount() {
        graph = creator.getGraphWhichCouldBeWorsenedBySwitch();
        List<LNode> expectedOrderFirstLayer = new ArrayList<LNode>(getNodesInLayer(0));
        List<LNode> expectedOrderSecondLayer = new ArrayList<LNode>(getNodesInLayer(1));

        startGreedySwitcherWithCurrentType();

        assertThat("Layer one", getNodesInLayer(0), is(expectedOrderFirstLayer));
        assertThat("Layer two", getNodesInLayer(1), is(expectedOrderSecondLayer));
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
