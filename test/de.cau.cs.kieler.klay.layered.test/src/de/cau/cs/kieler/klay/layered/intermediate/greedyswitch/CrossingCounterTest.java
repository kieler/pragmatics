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
 * Test for extracted and modified CrossingCounter.
 * 
 * @author alan
 *
 */
public class CrossingCounterTest {

    private TestGraphCreator testGraphCreator;
    private LGraph graph;
    private CrossingCounter counter;
    private LNode[][] graphAsLNodeArray;

    // CHECKSTYLEOFF Javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setup() {
        testGraphCreator = new TestGraphCreator();

    }

    @Test
    public void countOneCrossing() {
        graph = testGraphCreator.getCrossFormedGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countInLayerCrossing() {
        graph = testGraphCreator.getInLayerEdgesGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countNorthSouthCrossing() {
        graph = testGraphCreator.getNorthSouthCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countNorthernNorthSouthCrossing() {
        graph = testGraphCreator.getNorthSouthUpwardCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void northSouthDummyEdgeCrossing() {
        graph = testGraphCreator.getSouthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countNorthSouthCrossingInOneLayer() {
        graph = testGraphCreator.getNorthSouthCrossingGraph();
        int amountOfCrossings = whenCountingNorthSouthCrossingsInLayer(0);
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void switchAndCountTwice() {
        graph = testGraphCreator.getCrossFormedGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
        switchNodesInLayer(0, 1, 1);
        amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
    }

    @Test
    public void switchAndCountInLayer() {
        graph = testGraphCreator.getCrossFormedGraph();
        counter = new CrossingCounter(getAsLNodeArray(graph));

        LNode[][] nodeOrder = getAsLNodeArray(graph);
        int amountOfCrossings =
                counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(1));
        nodeOrder = switchNodesInLayer(0, 1, 1);
        amountOfCrossings = counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(0));

        nodeOrder = switchNodesInLayer(0, 1, 1);
        amountOfCrossings = counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void southernTwoWesternEdges() {
        graph = testGraphCreator.getNorthSouthSouthernTwoWesternEdges();
        int amountOfCrossings = countNorthSouthCrossingsInLayer(1);
        assertThat(amountOfCrossings, is(1));
        amountOfCrossings = counter.countNorthSouthPortCrossings(switchNodesInLayer(1, 2, 1)[1]);
        assertThat(amountOfCrossings, is(0));
    }

    private int countNorthSouthCrossingsInLayer(final int layerIndex) {
        graphAsLNodeArray = getAsLNodeArray(graph);
        counter = new CrossingCounter(graphAsLNodeArray);
        return counter.countNorthSouthPortCrossings(layerIndex);
    }

    @Test
    public void southernWesternPortToEastAndEasternPortToWest() {
        graph = testGraphCreator.getNorthSouthSouthernWesternPortToEastAndEasternPortToWest();
        int amountOfCrossings = countNorthSouthCrossingsInLayer(1);
        assertThat(amountOfCrossings, is(1));
        amountOfCrossings = counter.countNorthSouthPortCrossings(switchNodesInLayer(1, 2, 1)[1]);
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void northernBothEdgesWestern() {
        graph = testGraphCreator.getNorthSouthNorthernWesternEdges();
        int amountOfCrossings = countNorthSouthCrossingsInLayer(1);
        assertThat(amountOfCrossings, is(0));
        amountOfCrossings = counter.countNorthSouthPortCrossings(switchNodesInLayer(0, 1, 1)[1]);
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void northernEasternPortToWestWesternPortToEast() {
        graph = testGraphCreator.getNorthSouthNorthernEasternPortToWestWesternPortToEast();
        int amountOfCrossings = countNorthSouthCrossingsInLayer(1);
        assertThat(amountOfCrossings, is(1));
        amountOfCrossings = counter.countNorthSouthPortCrossings(switchNodesInLayer(0, 1, 1)[1]);
        assertThat(amountOfCrossings, is(1));
    }

    private LNode[][] switchNodesInLayer(final int upperNodeIndex, final int lowerNodeIndex,
            final int layerIndex) {
        Layer layer = graph.getLayers().get(layerIndex);
        List<LNode> nodes = layer.getNodes();
        LNode upperNode = nodes.get(upperNodeIndex);
        // counter.notifyOfSwitch(upperNode, nodes.get(lowerNodeIndex), layerIndex);

        nodes.set(upperNodeIndex, nodes.get(lowerNodeIndex));
        nodes.set(lowerNodeIndex, upperNode);
        return getAsLNodeArray(graph);
    }

    @Test
    public void tooManyInLayerCrossingsWithTheOldMethod() {
        graph = testGraphCreator.getInLayerOneLayerNoCrossings();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
    }

    private int whenCountingNorthSouthCrossingsInLayer(final int layerIndex) {
        return new CrossingCounter(getAsLNodeArray(graph)).countNorthSouthPortCrossings(layerIndex);
    }

    static LNode[][] getAsLNodeArray(final LGraph graph) {
        LNode[][] result = new LNode[graph.getLayers().size()][];
        for (Layer layer : graph) {
            result[layer.getIndex()] = layer.getNodes().toArray(new LNode[0]);
        }
        return result;
    }

    @Test
    public void countCrossingsWithMultipleEdgesBetweenSameNodes() {
        graph = testGraphCreator.getMultipleEdgesBetweenSameNodesGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(4));
    }

    @Test
    public void countCrossingsInEmptyGraph() {
        graph = testGraphCreator.getEmptyGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
    }

    @Test
    public void switchResolvesOneCrossing() {
        graph = testGraphCreator.shouldSwitchThreeTimesGraph();
        counter = new CrossingCounter(getAsLNodeArray(graph));

        LNode[][] nodeOrder = getAsLNodeArray(graph);
        int amountOfCrossings =
                counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(3));

        nodeOrder = switchNodesInLayer(0, 1, 1);
        amountOfCrossings = counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(2));

        nodeOrder = switchNodesInLayer(2, 3, 1);
        amountOfCrossings = counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(1));

        nodeOrder = switchNodesInLayer(1, 2, 1);
        amountOfCrossings = counter.countCrossingsBetweenLayersInOrder(nodeOrder[0], nodeOrder[1]);
        assertThat(amountOfCrossings, is(0));
    }

    @Test
    public void ignoresCrossingsWithoutFixedPortOrder() {
        graph = testGraphCreator.getGraphNoCrossingsDueToPortOrderNotFixed();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
    }

    @Test
    public void oneNodeIsLongEdgeDummy() {
        graph = testGraphCreator.getSouthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void oneNodeIsLongEdgeDummyNorthern() {
        graph = testGraphCreator.getNorthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void multipleNorthSouthAndLongEdgeDummiesOnBothSides() {
        graph = testGraphCreator.getMultipleNorthSouthAndLongEdgeDummiesOnBothSides();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(4));
    }

    private int whenCountingAllCrossings() {
        counter = new CrossingCounter(getAsLNodeArray(graph));
        return counter.countAllCrossingsInGraph();
    }
}
