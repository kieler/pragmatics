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
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Tests if crossing counter counts all in-layer crossings in layer correctly.
 * 
 * @author alan
 *
 */
public class AllInLayerEdgeCrossingCounterTest {

    private TestGraphCreator creator;
    private LNode[] nodeOrder;
    private InLayerEdgeAllCrossingsCounter counter;

    // CHECKSTYLEOFF MagicNumber
    // CHECKSTYLEOFF javadoc
    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void ignoresInBetweenLayerEdges() {
        creator.getCrossFormedGraph();
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void countInLayerEdgeWithNormalEdgeCrossing() {
        creator.getInLayerEdgesGraph();
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void crossingsWhenSwitched() {
        creator.getInLayerEdgesGraphWhichResultsInCrossingsWhenSwitched();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));

        switchOrder(1, 2);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void fixedPortOrderNodeToNode() {
        creator.getInLayerEdgesGraphWithCrossingsToBetweenLayerEdgeWithFixedPortOrder();
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void fixedPortOrderNodeToNodeLowerNode() {
        creator.getInLayerEdgesGraphWithCrossingsToBetweenLayerEdgeWithFixedPortOrder();

        initCrossingCounterForLayerIndex(1);
        switchOrder(0, 1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void fixedPortOrderCrossingsAndNormalEdgeCrossings() {
        creator.getInLayerEdgesWithFixedPortOrderAndNormalEdgeCrossings();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));

        switchOrder(0, 1);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void ignoresSelfLoops() {
        creator.getCrossWithManySelfLoopsGraph();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSet() {
        creator.getInLayerEdgesCrossingsButNoFixedOrder();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));

    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSetNoEdgeBetweenUpperAndLower() {
        creator.getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLower();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));

    }

    @Test
    public void ignoresCrossingsWhenPortOrderNotSetNoEdgeBetweenUpperAndLowerLowerUpsideDown() {
        creator.getInLayerEdgesCrossingsNoFixedOrderNoEdgeBetweenUpperAndLowerUpsideDown();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));

    }

    @Test
    public void crossingsOnBothSides() {
        creator.getInLayerCrossingsOnBothSides();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void fixedPortOrderInLayerNoCrossings() {
        creator.getFixedPortOrderInLayerEdgesDontCrossEachOther();

        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void fixedPortOrderInLayerWithCrossings() {
        creator.getFixedPortOrderInLayerEdgesWithCrossings();

        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void oneLayerInLayerSwitchWouldReduceCrossings() {
        creator.getOneLayerWithInLayerCrossings();

        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(1));
    }

    @Test
    public void oneLayerInLayerNowCrossingReduction() {
        creator.getInLayerOneLayerNoCrossings();

        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void oneNode() {
        creator.getOneNodeGraph();

        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void moreComplex() {
        creator.getMoreComplexInLayerGraph();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(6));

        // set port order as not fixed for layer in question.
        LGraph graph = creator.getMoreComplexInLayerGraph();
        Layer secondLayer = graph.getLayers().get(1);
        for (LNode lNode : secondLayer) {
            lNode.setProperty(LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FIXED_SIDE);
        }
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void downwardInLayerEdgesOnLowerNode() {
        creator.getInLayerEdgesFixedPortOrderInLayerAndInBetweenLayerCrossing();
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void moreThanOneEdgeIntoAPort() {
        creator.getInLayerEdgesMultipleEdgesIntoSinglePort();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrder() {
        creator.multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrder();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrderCauseCrossings() {
        creator.multipleInBetweenLayerEdgesIntoNodeWithNoFixedPortOrderCauseCrossings();

        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(2));
    }

    @Test
    public void oneLayerShouldNotCauseCrossings() {
        creator.getInLayerOneLayerNoCrossings();
        initCrossingCounterForLayerIndex(0);

        assertThat(counter.countCrossings(), is(0));
    }

    @Test
    public void noPortOrderConstraintShouldResolveCrossing() {
        creator.getInLayerEdgesDownwardGraphNoFixedOrder();
        initCrossingCounterForLayerIndex(1);

        assertThat(counter.countCrossings(), is(0));
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
        counter = new InLayerEdgeAllCrossingsCounter(nodeOrder);
    }

    private void switchOrder(final int indexOne, final int indexTwo) {
        LNode one = nodeOrder[indexOne];
        nodeOrder[indexOne] = nodeOrder[indexTwo];
        nodeOrder[indexTwo] = one;
    }
}
