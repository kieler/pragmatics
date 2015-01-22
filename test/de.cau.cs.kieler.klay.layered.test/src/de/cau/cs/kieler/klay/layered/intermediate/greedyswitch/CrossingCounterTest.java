package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;

public class CrossingCounterTest {

    private TestGraphCreator testGraphCreator;
    private LGraph graph;

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
    public void countNorthSouthCrossingInOneLayer() {
        graph = testGraphCreator.getNorthSouthCrossingGraph();
        int amountOfCrossings = whenCountingNorthSouthCrossingsInLayer(0);
        assertThat(amountOfCrossings, is(1));
    }

    private int whenCountingNorthSouthCrossingsInLayer(final int layerIndex) {
        return new CrossingCounter(getAsLNodeArray(graph)).countNorthSouthPortCrossings(layerIndex);
    }

    private LNode[][] getAsLNodeArray(final LGraph graph2) {
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

    private int whenCountingAllCrossings() {
        return new CrossingCounter(getAsLNodeArray(graph)).countAllCrossingsInGraph();
    }
}
