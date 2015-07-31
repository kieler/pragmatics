/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.BetweenLayerEdgeAllCrossingsCounter;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.BetweenLayerStraightEdgeAllCrossingsCounter;

/**
 * Tests straight edge all crossings counter.
 * 
 * @author alan
 *
 */
public class BetweenLayerStraightEdgeAllCrossingsCounterTest {
    private TestGraphCreator creator;
    private BetweenLayerEdgeAllCrossingsCounter crossingCounter;
    private LNode[][] nodeOrder;
    private LNode[] leftLayer;
    private LNode[] rightLayer;

    // CHECKSTYLEOFF Javadoc
    // CHECKSTYLEOFF MagicNumber
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
