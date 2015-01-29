package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LNode;

public class InLayerEdgeNeighboringNodeCrossingCounterTest {
    private TestGraphCreator creator;
    private InLayerEdgeNeighboringNodeCrossingCounter counter;
    private int lowerUpperCrossings;
    private int upperLowerCrossings;
    private LNode[] nodeOrder;

    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void ignoresInBetweenLayerEdges() {
        creator.getCrossFormedGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void countInLayerEdgeWithNormalEdgeCrossing() {
        creator.getInLayerEdgesGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void crossingsWhenSwitched() {
        creator.getInLayerEdgesGraphWhichResultsInCrossingsWhenSwitched();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void inLayerEdgeOnLowerNode() {
        creator.getInLayerEdgesGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void inLayerEdgeTargetAndSourceAreSame() {
        creator.getInLayerEdgesGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void switchNodeOrder() {
        creator.getInLayerEdgesGraph();

        initCrossingCounterForLayerIndex(1);
        switchOrderAndNotifyCounter(1, 2);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderNodeToNode() {
        creator.getInLayerEdgesGraphWithCrossingsToFixedPortOrder();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
    }

    @Test
    public void fixedPortOrderNodeToNodeLowerNode() {
        creator.getInLayerEdgesGraphWithCrossingsToFixedPortOrder();

        initCrossingCounterForLayerIndex(1);
        switchOrderAndNotifyCounter(0, 1);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void fixedPortOrderCrossingsAndNormalEdgeCrossings() {
        creator.getInLayerEdgesWithFixedPortOrderAndNormalEdgeCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
        // assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        // assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        switchOrderAndNotifyCounter(0, 1);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
        // assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        // assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void ignoresSelfLoops() {
        creator.getCrossWithManySelfLoopsGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSet() {
        creator.getInLayerEdgesCrossingsButNoFixedOrder();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSetNoEdgeBetweenUpperAndLower() {
        creator.getInLayerEdgesCrossingsButNoFixedOrderNoEdgeBetweenUpperAndLower();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    @Test
    public void crossingsOnBothSides() {
        creator.getInLayerCrossingsOnBothSides();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderInLayerNoCrossings() {
        creator.getFixedPortOrderInLayerEdgesDontCrossEachOther();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderInLayerWithCrossings() {
        creator.getFixedPortOrderInLayerEdgesWithCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void oneLayerInLayerSwitchWouldReduceCrossings() {
        creator.getOneLayerWithInLayerCrossings();
    }

    @Test
    public void oneNode() {
        creator.getOneNodeGraph();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 0);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void moreComplex() {
        creator.getMoreComplexInLayerGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(6));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(6));
    }

    @Test
    public void downwardInLayerEdgesOnLowerNode() {
        creator.getInLayerEdgesDownwardGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
    }

    @Test
    public void oneLayerInLayerShouldSwitch() {
        creator.getOneLayerWithInLayerCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        countCrossingsInLayerForUpperNodeLowerNode(0, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    @Test
    public void moreThanOneEdgeIntoAPort() {
        creator.getInLayerEdgesMultipleEdgesIntoSinglePort();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    private void countCrossingsInLayerForUpperNodeLowerNode(final int layerIndex,
            final int upperNodeIndex, final int lowerNodeIndex) {

        initCrossingCounterForLayerIndex(layerIndex);

        countCrossings(upperNodeIndex, lowerNodeIndex);
    }

    private void countCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        counter.countCrossings(nodeOrder[upperNodeIndex], nodeOrder[lowerNodeIndex]);
        upperLowerCrossings = counter.getUpperLowerCrossings();
        lowerUpperCrossings = counter.getLowerUpperCrossings();
    }

    /**
     * Initializes Crossing counter, sets nodeOrder with the nodes from the layer being considered
     * and numbers its in ascending form as required by the counter.
     * 
     * @param layerIndex
     */
    private void initCrossingCounterForLayerIndex(final int layerIndex) {
        LNode[][] currentOrder = creator.getCurrentOrder();
        nodeOrder = currentOrder[layerIndex];
        numberIdsAscendinglyIn(nodeOrder);
        counter = new InLayerEdgeNeighboringNodeCrossingCounter(nodeOrder);
    }

    private void numberIdsAscendinglyIn(final LNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].id = i;
        }
    }

    private void switchOrderAndNotifyCounter(final int indexOne, final int indexTwo) {
        counter.notifyNodeSwitch(nodeOrder[indexOne], nodeOrder[indexTwo]);
        LNode one = nodeOrder[indexOne];
        nodeOrder[indexOne] = nodeOrder[indexTwo];
        nodeOrder[indexTwo] = one;
    }
}
