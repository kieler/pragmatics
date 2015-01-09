package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.intermediate.CrossingCounter;

public class CrossingCounterTest {
    @Test
    public void countOneCrossing() {
        LGraph graph = givenCrossFormedGraph();
        int amountOfCrossings = whenCountingAmountOfCrossings(graph);
        assertThat(amountOfCrossings, is(1));
    }

    private LGraph givenCrossFormedGraph() {
        TestGraphCreator testGraphCreator = new TestGraphCreator();
        return testGraphCreator.getCrossFormedGraph();
    }

    private int whenCountingAmountOfCrossings(final LGraph graph) {
        return new CrossingCounter(graph).countAllCrossingsInGraph();
    }

    @Test
    public void countInLayerCrossing() {
        LGraph graph = givenGraphWithInLayerCrossings();
        int amountOfCrossings = whenCountingAmountOfCrossings(graph);
        assertThat(amountOfCrossings, is(1));
    }

    private LGraph givenGraphWithInLayerCrossings() {
        TestGraphCreator testGraphCreator = new TestGraphCreator();
        return testGraphCreator.getInLayerEdgesGraph();
    }

    @Test
    public void countNorthSouthCrossing() {
        LGraph graph = givenGraphWithNorthSouthCrossing();
        int amountOfCrossings = whenCountingAmountOfCrossings(graph);
        assertThat(amountOfCrossings, is(1));
    }

    private LGraph givenGraphWithNorthSouthCrossing() {
        TestGraphCreator testGraphCreator = new TestGraphCreator();
        return testGraphCreator.getNorthSouthCrossingGraph();
    }

    @Test
    public void countCrossingsWithMultipleEdgesBetweenSameNodes() {
        LGraph graph = givenGraphWithMultipleEdgesBetweenSameNodes();
        int amountOfCrossings = whenCountingAmountOfCrossings(graph);
        assertThat(amountOfCrossings, is(4));
    }

    private LGraph givenGraphWithMultipleEdgesBetweenSameNodes() {
        TestGraphCreator testGraphCreator = new TestGraphCreator();
        return testGraphCreator.getMultipleEdgesBetweenSameNodesGraph();
    }

    @Test
    public void countCrossingsInEmptyGraph() {
        LGraph graph = givenEmptyGraph();
        int amountOfCrossings = whenCountingAmountOfCrossings(graph);
        assertThat(amountOfCrossings, is(0));
    }

    private LGraph givenEmptyGraph() {
        TestGraphCreator testGraphCreator = new TestGraphCreator();
        return testGraphCreator.getEmptyGraph();
    }
}
