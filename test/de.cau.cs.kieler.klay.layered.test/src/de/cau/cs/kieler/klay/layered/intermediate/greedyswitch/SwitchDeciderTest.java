package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.SwitchDecider.CrossingCountSide;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

@RunWith(Parameterized.class)
public class SwitchDeciderTest {

    private final GreedyType greedyType;

    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedyType.ONE_SIDED_COUNTER, },
                { GreedyType.TWO_SIDED_COUNTER }, { GreedyType.ONE_SIDED_CROSSING_MATRIX },
                { GreedyType.TWO_SIDED_CROSSING_MATRIX },
                { GreedyType.ONE_SIDED_ON_DEMAND_CROSSING_MATRIX },
                { GreedyType.TWO_SIDED_ON_DEMAND_CROSSING_MATRIX } });
    }

    public SwitchDeciderTest(final GreedyType greedyType) {
        this.greedyType = greedyType;
        factory = new SwitchDeciderFactory(greedyType);
    }

    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private SwitchDecider decider;
    private final SwitchDeciderFactory factory;

    @Test
    public void crossFormed() {
        graph = creator.getCrossFormedGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 0), is(false));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 0), is(false));
    }

    /**
     * <pre>
     *   --* 
     *   |  
     * *-+-*-* 
     *   | 
     *   --*
     * </pre>
     * 
     * @ * *
     */
    @Test
    public void inLayerSwitchable() {
        graph = creator.getInLayerEdgesGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void emptyGraphShouldResultInException() {
        graph = creator.getEmptyGraph();

        try {
            decider = givenDeciderForFreeLayer(0, CrossingCountSide.WEST);
            fail("Did not cause AssertionError");
        } catch (AssertionError e) {
        }

        try {
            decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
            fail("Did not cause AssertionError");
        } catch (AssertionError e) {
        }
    }

    @Test
    public void multipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void selfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    /**
     * Graph:
     * 
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
     * 
     * 
     */
    @Ignore
    // TODO-alan implement for crossing matrix counters
    public void northSouthPortCrossing() {
        graph = creator.getThreeLayerNorthSouthCrossingGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void moreComplex() {
        graph = creator.getMoreComplexThreeLayerGraph();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        decider = givenDeciderForFreeLayer(2, CrossingCountSide.WEST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(false));

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        decider = givenDeciderForFreeLayer(0, CrossingCountSide.EAST);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));

        assertThat(decider.doesSwitchReduceCrossings(1, 2), is(true));
    }

    @Test
    public void switchOnlyTrueForOneSided() {
        graph = creator.getSwitchOnlyOneSided();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.WEST);
        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        } else {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        }
    }

    @Test
    public void switchOnlyTrueForOneSidedEasternSide() {
        graph = creator.getSwitchOnlyEastOneSided();

        decider = givenDeciderForFreeLayer(1, CrossingCountSide.EAST);
        if (greedyType.isOneSided()) {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
        } else {
            assertThat(decider.doesSwitchReduceCrossings(0, 1), is(false));
        }
    }

    private SwitchDecider givenDeciderForFreeLayer(final int freeLayerIndex,
            final CrossingCountSide direction) {
        LNode[][] currentNodeOrder = getCurrentNodeOrder();
        return factory.getNewSwitchDecider(freeLayerIndex, currentNodeOrder, direction);
    }

    private LNode[][] getCurrentNodeOrder() {
        LNode[][] currentNodeOrder = new LNode[graph.getLayers().size()][];
        List<Layer> layers = graph.getLayers();
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            List<LNode> nodes = layer.getNodes();
            currentNodeOrder[i] = new LNode[nodes.size()];
            for (int j = 0; j < nodes.size(); j++) {
                currentNodeOrder[i][j] = nodes.get(j);
            }
        }
        return currentNodeOrder;
    }
}
