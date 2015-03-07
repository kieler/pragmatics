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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * Tests counting crosses of edges in between layers for neighbouring nodes.
 * 
 * @author alan
 *
 */
public class InBetweenLayerEdgeTwoNodeCrossingCounterTest {
    private TestGraphCreator creator;
    private InBetweenLayerEdgeTwoNodeCrossingCounter crossingCounter;
    private LGraph graph;
    private LNode upperNode;
    private LNode lowerNode;
    private Layer layerToCountIn;
    private LNode[][] nodeOrder;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void twoNodeNoEdges() {
        graph = creator.getTwoNodesNoConnectionGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(1);

        assertBothSideUpperLowerCrossingsIs(0);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void crossFormed() {
        graph = creator.getCrossFormedGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertBothSideUpperLowerCrossingsIs(1);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(1);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void oneNode() {
        graph = creator.getOneNodeGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(0);

        assertBothSideUpperLowerCrossingsIs(0);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void crossFormedMultipleEdgesBetweenSameNodes() {
        graph = creator.getMultipleEdgesBetweenSameNodesGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertBothSideUpperLowerCrossingsIs(4);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(4);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void crossWithExtraEdgeInBetween() {
        graph = creator.getCrossWithExtraEdgeInBetweenGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(2);

        assertBothSideUpperLowerCrossingsIs(1);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(1);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void ignoreInLayerEdges() {
        graph = creator.getInLayerEdgesGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(2);

        assertBothSideUpperLowerCrossingsIs(0);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void ignoreSelfLoops() {
        graph = creator.getCrossWithManySelfLoopsGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertBothSideUpperLowerCrossingsIs(1);
        assertBothSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(1);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
    }

    //@formatter:off
    /**
     * *\  --*
     *   \/ /
     * *-*===*
     *  + /
     * * * --*
     * @return
     */
    //@formatter:on
    @Test
    public void moreComplexThreeLayerGraph() {
        graph = creator.getMoreComplexThreeLayerGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertWesternSideUpperLowerCrossingsIs(1);
        assertWesternSideLowerUpperCrossingsIs(1);
        assertEasternSideUpperLowerCrossingsIs(2);
        assertEasternSideLowerUpperCrossingsIs(3);
        assertBothSideUpperLowerCrossingsIs(3);
        assertBothSideLowerUpperCrossingsIs(4);
    }

    @Test
    public void fixedPortOrder() {
        graph = creator.getFixedPortOrderGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(1);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(1);
        assertBothSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void switchThreeTimes() {
        graph = creator.shouldSwitchThreeTimesGraph();

        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(3);
        assertEasternSideLowerUpperCrossingsIs(2);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(3);
        assertBothSideLowerUpperCrossingsIs(2);
    }

    @Test
    public void intoSamePort() {
        graph = creator.twoEdgesIntoSamePort();
        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(1);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(2);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(2);
        assertBothSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void intoSamePortCausesCrossingsOnSwitch() {
        graph = creator.twoEdgesIntoSamePortCrossesWhenSwitched();
        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(1);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(0);
        assertBothSideLowerUpperCrossingsIs(1);
    }

    @Test
    public void intoSamePortReducesCrossingsOnSwitch() {
        graph = creator.twoEdgesIntoSamePortResolvesCrossingWhenSwitched();
        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(1);
        assertEasternSideLowerUpperCrossingsIs(0);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(1);
        assertBothSideLowerUpperCrossingsIs(0);
    }

    @Test
    public void intoSamePortFromEastSwitchWithFixedPortOrder() {
        graph = creator.twoEdgesIntoSamePortFromEastWithFixedPortOrder();
        nodeOrder = creator.getCurrentOrder();
        setLayerToCountIn(0);
        setUpperNode(0);
        setLowerNode(1);

        assertEasternSideUpperLowerCrossingsIs(0);
        assertEasternSideLowerUpperCrossingsIs(1);
        assertWesternSideUpperLowerCrossingsIs(0);
        assertWesternSideLowerUpperCrossingsIs(0);
        assertBothSideUpperLowerCrossingsIs(0);
        assertBothSideLowerUpperCrossingsIs(1);
    }

    private void assertEasternSideLowerUpperCrossingsIs(final int expectedCrossings) {
        crossingCounter.countEasternEdgeCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" east, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertEasternSideUpperLowerCrossingsIs(final int expectedCrossings) {
        crossingCounter.countEasternEdgeCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" east, upper lower "), crossings, is(expectedCrossings));
    }

    private void assertWesternSideLowerUpperCrossingsIs(final int expectedCrossings) {
        crossingCounter.countWesternEdgeCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" west, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertWesternSideUpperLowerCrossingsIs(final int expectedCrossings) {
        crossingCounter.countWesternEdgeCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" west, upper lower "), crossings, is(expectedCrossings));
    }

    private void assertBothSideLowerUpperCrossingsIs(final int expectedCrossings) {
        crossingCounter.countBothSideCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" both, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertBothSideUpperLowerCrossingsIs(final int expectedCrossings) {
        crossingCounter.countBothSideCrossings(upperNode, lowerNode);
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" both, upper lower "), crossings, is(expectedCrossings));
    }

    private void setLayerToCountIn(final int layerIndex) {
        layerToCountIn = graph.getLayers().get(layerIndex);
        initCrossingCounter(layerIndex);
    }

    private void setUpperNode(final int nodeIndex) {
        List<LNode> nodes = layerToCountIn.getNodes();
        upperNode = nodes.get(nodeIndex);
    }

    private void setLowerNode(final int nodeIndex) {
        List<LNode> nodes = layerToCountIn.getNodes();
        lowerNode = nodes.get(nodeIndex);
    }

    private void initCrossingCounter(final int layerIndex) {
        crossingCounter = new InBetweenLayerEdgeTwoNodeCrossingCounter(nodeOrder, layerIndex);
    }

    private String failMessage(final String configuration) {
        return "With configuration: " + configuration + " in Layer " + layerToCountIn
                + " between the nodes: Upper node " + upperNode.getIndex() + " and lower Node "
                + lowerNode.getIndex();
    }
}
