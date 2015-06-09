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
 * Test for extracted and modified AllCrossingsCounter.
 * 
 * @author alan
 *
 */
public class AllCrossingsCounterTest {

    private TestGraphCreator testGraphCreator;
    private LGraph graph;
    private AllCrossingsCounter counter;

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
    public void countInLayerCrossingAndSwitch() {
        graph = testGraphCreator.getInLayerEdgesGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countNorthSouthCrossing() {
        graph = new NorthSouthEdgeTestGraphCreator().getNorthSouthDownwardCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void countNorthernNorthSouthCrossing() {
        graph = new NorthSouthEdgeTestGraphCreator().getNorthSouthUpwardCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void northSouthDummyEdgeCrossing() {
        graph = new NorthSouthEdgeTestGraphCreator().getSouthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
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
        graph = new InLayerEdgeTestGraphCreator().getInLayerOneLayerNoCrossings();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
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
    public void ignoresCrossingsWithoutFixedPortOrder() {
        graph = testGraphCreator.getGraphNoCrossingsDueToPortOrderNotFixed();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(0));
    }

    @Test
    public void oneNodeIsLongEdgeDummy() {
        graph = new NorthSouthEdgeTestGraphCreator().getSouthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void oneNodeIsLongEdgeDummyNorthern() {
        graph = new NorthSouthEdgeTestGraphCreator().getNorthernNorthSouthDummyEdgeCrossingGraph();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(1));
    }

    @Test
    public void multipleNorthSouthAndLongEdgeDummiesOnBothSides() {
        graph =
                new NorthSouthEdgeTestGraphCreator()
                        .getMultipleNorthSouthAndLongEdgeDummiesOnBothSides();
        int amountOfCrossings = whenCountingAllCrossings();
        assertThat(amountOfCrossings, is(4));
    }

    private int whenCountingAllCrossings() {
        counter = new AllCrossingsCounter(getAsLNodeArray(graph));
        return counter.countAllCrossingsInGraph();
    }
}
