package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;

public class TwoNodeInLayerEdgeCrossingCounterTest {
    private TestGraphCreator creator;
    private LGraph graph;
    private TwoNodeInLayerEdgeCrossingCounter counter;
    private int lowerUpperCrossings;
    private int upperLowerCrossings;
    private LNode[] nodeOrder;

    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void ignoresInBetweenLayerEdges() {
        graph = creator.getCrossFormedGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void countInLayerEdgeWithNormalEdgeCrossing() {
        graph = creator.getInLayerEdgesGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test(expected = AssertionError.class)
    public void screwUpIdsAndCountInLayerEdgeWithNormalEdgeCrossing() { // TODO should throw
        graph = creator.getInLayerEdgesGraph();
        LNode[][] currentOrder = creator.getCurrentOrder();
        nodeOrder = currentOrder[1];
        setAllIdsTo(1);
        counter = new TwoNodeInLayerEdgeCrossingCounter(nodeOrder);
    }

    private void setAllIdsTo(final int id) {
        for (Layer layer : graph) {
            for (LNode node : layer) {
                node.id = id;
                for (LPort port : node.getPorts()) {
                    port.id = id;
                }
            }
        }
    }

    @Test
    public void crossingsWhenSwitched() {
        graph = creator.getInLayerEdgesGraphWhichResultsInCrossingsWhenSwitched();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void inLayerEdgeOnLowerNode() {
        graph = creator.getInLayerEdgesGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void inLayerEdgeTargetAndSourceAreSame() {
        graph = creator.getInLayerEdgesGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void switchNodeOrder() {
        graph = creator.getInLayerEdgesGraph();

        initCrossingCounterForLayerIndex(1);
        switchOrderAndNotifyCounter(1, 2);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderNodeToNode() {
        graph = creator.getInLayerEdgesGraphWithCrossingsToFixedPortOrder();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
    }

    @Test
    public void fixedPortOrderNodeToNodeLowerNode() {
        graph = creator.getInLayerEdgesGraphWithCrossingsToFixedPortOrder();

        initCrossingCounterForLayerIndex(1);
        switchOrderAndNotifyCounter(0, 1);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void fixedPortOrderCrossingsAndNormalEdgeCrossings() {
        graph = creator.getInLayerEdgesWithFixedPortOrderAndNormalEdgeCrossings();

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
        graph = creator.getCrossWithManySelfLoopsGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSet() {
        graph = creator.getInLayerEdgesCrossingsButNoFixedOrder();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSetNoEdgeBetweenUpperAndLower() {
        graph = creator.getInLayerEdgesCrossingsButNoFixedOrderNoEdgeBetweenUpperAndLower();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    @Test
    public void crossingsOnBothSides() {
        graph = creator.getInLayerCrossingsOnBothSides();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderInLayerNoCrossings() {
        graph = creator.getFixedPortOrderInLayerEdgesDontCrossEachOther();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderInLayerWithCrossings() {
        graph = creator.getFixedPortOrderInLayerEdgesWithCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 0);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void moreComplex() {
        graph = creator.getMoreComplexInLayerGraph();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(6));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(6));
    }

    @Test
    public void downwardInLayerEdgesOnLowerNode() {
        graph = creator.getInLayerEdgesDownwardGraph();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
    }

    private void countCrossingsInLayerForUpperNodeLowerNode(final int layerIndex,
            final int upperNodeIndex, final int lowerNodeIndex) {

        initCrossingCounterForLayerIndex(layerIndex);

        countCrossings(upperNodeIndex, lowerNodeIndex);
    }

    private void countCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        counter.countCrossings(upperNodeIndex, lowerNodeIndex);
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
        counter = new TwoNodeInLayerEdgeCrossingCounter(nodeOrder);
    }

    private void numberIdsAscendinglyIn(final LNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].id = i;
        }
    }

    private void switchOrderAndNotifyCounter(final int indexOne, final int indexTwo) {
        counter.nodesSwitched(nodeOrder[indexOne], nodeOrder[indexTwo]);
        LNode one = nodeOrder[indexOne];
        nodeOrder[indexOne] = nodeOrder[indexTwo];
        nodeOrder[indexTwo] = one;
    }
}
