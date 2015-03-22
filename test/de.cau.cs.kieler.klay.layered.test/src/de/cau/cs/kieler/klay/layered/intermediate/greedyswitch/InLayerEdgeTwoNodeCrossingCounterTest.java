/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Tests {@link InLayerEdgeTwoNodeCrossingCounter}.
 * 
 * @author alan
 *
 */
public class InLayerEdgeTwoNodeCrossingCounterTest {
    private InLayerEdgeTestGraphCreator creator;
    private InLayerEdgeTwoNodeCrossingCounter counter;
    private int lowerUpperCrossings;
    private int upperLowerCrossings;
    private LNode[] nodeOrder;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setUp() {
        creator = new InLayerEdgeTestGraphCreator();
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
    public void switchNodeOrder() {
        creator.getInLayerEdgesGraph();

        initCrossingCounterForLayerIndex(1);
        switchOrderAndNotifyCounter(1, 2);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderCrossingToInBetweenLayerEdge() {
        creator.getInLayerEdgesGraphWithCrossingsToBetweenLayerEdgeWithFixedPortOrder();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
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

        switchOrderAndNotifyCounter(0, 1);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
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
        creator.getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLower();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

    }

    /**
     * 
     * <pre>
     *      *
     *     /____
     *     \|  |
     *    //|  | <-- count between this node
     * *-++-|  |
     *   || |__|
     *   ||
     * *-++-*    <-- and this node
     *    \\
     *      *
     * .
     * </pre>
     * 
     * Port order not fixed.
     * 
     */
    @Test
    public void ignoresCrossingsWhenPortOrderNotSetNoEdgeBetweenUpperAndLowerLowerUpsideDown() {
        creator.getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLowerUpsideDown();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));

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
    public void fixedPortOrderInLayerWithAlwaysRemaingCrossingsAreNotCounted() {
        creator.getFixedPortOrderInLayerEdgesWithCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
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
        creator.getInLayerEdgesFixedPortOrderInLayerAndInBetweenLayerCrossing();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(2));
    }

    @Test
    public void oneLayerInLayerCrossingShouldDisappearAfterAnySwitch() {
        creator.getOneLayerWithInLayerCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        countCrossingsInLayerForUpperNodeLowerNode(0, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        countCrossingsInLayerForUpperNodeLowerNode(0, 2, 3);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        switchOrderAndNotifyCounter(0, 1);

        countCrossings(0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));

        switchOrderAndNotifyCounter(0, 1);
        switchOrderAndNotifyCounter(1, 2);

        countCrossings(1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));

        switchOrderAndNotifyCounter(1, 2);
        switchOrderAndNotifyCounter(2, 3);

        countCrossings(2, 3);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void moreThanOneEdgeIntoAPort() {
        creator.getInLayerEdgesMultipleEdgesIntoSinglePort();

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrder() {
        creator.multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrder();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrderCauseCrossings() {
        creator.multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrderCauseCrossings();

        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));

        countCrossingsInLayerForUpperNodeLowerNode(1, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(2));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void inLayerEdgesPassEachOther() {
        creator.getInLayerOneLayerNoCrossings();
        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
        countCrossingsInLayerForUpperNodeLowerNode(0, 1, 2);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
        countCrossingsInLayerForUpperNodeLowerNode(0, 2, 3);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(1));
    }

    @Test
    public void noPortOrderConstraintShouldResolveCrossing() {
        creator.getInLayerEdgesDownwardGraphNoFixedOrder();
        countCrossingsInLayerForUpperNodeLowerNode(1, 0, 1);
        assertThat("upperLowerCrossings", upperLowerCrossings, is(0));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderCrossingToInLayerEdge() {
        creator.getInLayerEdgesFixedPortOrderInLayerCrossing();

        countCrossingsInLayerForUpperNodeLowerNode(0, 1, 2);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Test
    public void fixedPortOrderTwoInLayerEdgesCrossEachOther() {
        creator.getFixedPortOrderTwoInLayerEdgesCrossEachOther();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    @Ignore
    // this case is not supported by the current algorithm
    public void multipleEdgesIntoOnePortAndFreePortOrderCausesCrossings() {
        creator.multipleEdgesIntoOnePortAndFreePortOrder();

        countCrossingsInLayerForUpperNodeLowerNode(0, 0, 1);

        assertThat("upperLowerCrossings", upperLowerCrossings, is(1));
        assertThat("lowerUpperCrossings", lowerUpperCrossings, is(0));
    }

    private void countCrossingsInLayerForUpperNodeLowerNode(final int layerIndex,
            final int upperNodeIndex, final int lowerNodeIndex) {

        initCrossingCounterForLayerIndex(layerIndex);

        countCrossings(upperNodeIndex, lowerNodeIndex);
    }

    private void countCrossings(final int upperNodeIndex, final int lowerNodeIndex) {
        counter.countCrossingsBetweenNodes(nodeOrder[upperNodeIndex],
                nodeOrder[lowerNodeIndex]);
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
        counter = new InLayerEdgeTwoNodeCrossingCounter(nodeOrder);
    }

    private void numberIdsAscendinglyIn(final LNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].id = i;
        }
    }

    private void switchOrderAndNotifyCounter(final int indexOne, final int indexTwo) {
        counter.notifyOfSwitch(nodeOrder[indexOne], nodeOrder[indexTwo]);
        LNode one = nodeOrder[indexOne];
        nodeOrder[indexOne] = nodeOrder[indexTwo];
        nodeOrder[indexTwo] = one;
    }
}
