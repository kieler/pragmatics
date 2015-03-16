package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LNode;

public class BetweenLayerStraightEdgeAllCrossingsCounterTest {
    private TestGraphCreator creator;
    private BetweenLayerEdgeAllCrossingsCounter crossingCounter;
    private LNode[][] nodeOrder;
    private LNode[] leftLayer;
    private LNode[] rightLayer;

    @Test
    public void twoNodeNoEdges() {
        creator.getTwoNodesNoConnectionGraph();
        setLeftAndRightLayerAndInitialize(0, 0);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(0));
    }

    @Test
    public void crossFormed() {
        creator.getCrossFormedGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(1));
    }

    @Test
    public void oneNode() {
        creator.getOneNodeGraph();
        setLeftAndRightLayerAndInitialize(0, 0);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(0));
    }

    @Test
    public void crossFormedMultipleEdgesBetweenSameNodes() {
        creator.getMultipleEdgesBetweenSameNodesGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(4));
    }

    @Test
    public void crossWithExtraEdgeInBetween() {
        creator.getCrossWithExtraEdgeInBetweenGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(3));
    }

    @Test
    public void ignoreInLayerEdges() {
        creator.getInLayerEdgesGraph();
        setLeftAndRightLayerAndInitialize(0, 0);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(0));
    }

    @Test
    public void ignoreSelfLoops() {
        creator.getCrossWithManySelfLoopsGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(1));
    }

    @Test
    public void moreComplexThreeLayerGraph() {
        creator.getMoreComplexThreeLayerGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(1));

        setLeftAndRightLayerAndInitialize(1, 2);

        crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(2));
    }

    @Test
    public void fixedPortOrder() {
        creator.getFixedPortOrderGraph();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(1));
    }

    @Test
    public void intoSamePort() {
        creator.twoEdgesIntoSamePort();
        setLeftAndRightLayerAndInitialize(0, 1);

        int crossingCount = crossingCounter.countCrossings(leftLayer, rightLayer);

        assertThat(crossingCount, is(2));
    }

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    private void setLeftAndRightLayerAndInitialize(final int leftLayerIndex,
            final int rightLayerIndex) {
        nodeOrder = creator.getCurrentOrder();
        leftLayer = nodeOrder[leftLayerIndex];
        rightLayer = nodeOrder[rightLayerIndex];
        initCrossingCounter();
    }

    private void initCrossingCounter() {
        crossingCounter = new BetweenLayerStraightEdgeAllCrossingsCounter(nodeOrder);
    }

}
