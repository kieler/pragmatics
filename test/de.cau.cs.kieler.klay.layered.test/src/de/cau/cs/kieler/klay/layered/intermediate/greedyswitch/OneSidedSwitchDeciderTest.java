package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDecider;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDecider.SweepDirection;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDeciderException;
import de.cau.cs.kieler.klay.layered.intermediate.SwitchDeciderFactory;
import de.cau.cs.kieler.klay.layered.properties.GreedyType;

@RunWith(Parameterized.class)
public class OneSidedSwitchDeciderTest {

    @Parameters(name = "{0}")
    public static Iterable<Object[]> greedyTypes() {
        return Arrays.asList(new Object[][] { { GreedyType.ONE_SIDED_COUNTER } });
    }

    public OneSidedSwitchDeciderTest(final GreedyType greedyType) {
        factory = new SwitchDeciderFactory(greedyType);
    }

    private final TestGraphCreator creator = new TestGraphCreator();
    private LGraph graph;
    private SwitchDecider decider;
    private final SwitchDeciderFactory factory;

    @Test
    public void crossFormed() throws SwitchDeciderException {
        graph = creator.getCrossFormedGraph();

        decider = givenDeciderForFreeLayer(1, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, SweepDirection.BACKWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void oneNode() throws SwitchDeciderException {
        graph = creator.getOneNodeGraph();

        decider = givenDeciderForFreeLayer(0, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 0), is(false));

        decider = givenDeciderForFreeLayer(0, SweepDirection.BACKWARD);
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
     * @throws SwitchDeciderException
     *             *
     */
    @Test
    public void inLayerSwitchable() throws SwitchDeciderException {
        graph = creator.getInLayerEdgesGraph();

        decider = givenDeciderForFreeLayer(1, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(1, SweepDirection.BACKWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void emptyGraphShouldResultInException() {
        graph = creator.getEmptyGraph();

        try {
            decider = givenDeciderForFreeLayer(0, SweepDirection.FORWARD);
            fail("Did not cause SwitchDecider exception");
        } catch (SwitchDeciderException e) {
            assertThat(e.getMessage(), is("0 is out of graph bounds for graph."));
        }

        try {
            decider = givenDeciderForFreeLayer(0, SweepDirection.BACKWARD);
            fail("Did not cause SwitchDecider exception");
        } catch (SwitchDeciderException e) {
            assertThat(e.getMessage(), is("0 is out of graph bounds for graph."));
        }
    }

    @Test
    public void multipleEdgesBetweenSameNodes() throws SwitchDeciderException {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        decider = givenDeciderForFreeLayer(1, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, SweepDirection.BACKWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    @Test
    public void selfLoops() throws SwitchDeciderException {
        graph = creator.getCrossWithManySelfLoopsGraph();

        decider = givenDeciderForFreeLayer(1, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));

        decider = givenDeciderForFreeLayer(0, SweepDirection.BACKWARD);
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
     * @throws SwitchDeciderException
     */
    @Test
    public void northSouthPortCrossing() throws SwitchDeciderException {
        graph = creator.getThreeLayerNorthSouthCrossingGraph();

        decider = givenDeciderForFreeLayer(1, SweepDirection.FORWARD);
        assertThat(decider.doesSwitchReduceCrossings(0, 1), is(true));
    }

    private SwitchDecider givenDeciderForFreeLayer(final int freeLayerIndex,
            final SweepDirection direction) throws SwitchDeciderException {
        LNode[][] currentNodeOrder = getCurrentNodeOrder();
        return factory.getNewOneSidedSwitchDecider(freeLayerIndex, currentNodeOrder, direction);
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
