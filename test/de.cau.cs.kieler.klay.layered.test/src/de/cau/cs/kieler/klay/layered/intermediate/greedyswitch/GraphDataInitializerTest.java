package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.intermediate.GraphDataInitializer;

public class GraphDataInitializerTest {

    @Test
    public void noInitializedValueShouldBeNull() {
        LGraph graph = givenOneNodeGraph();
        GraphDataInitializer graphDataInitializer = whenGraphIsInitialized(graph);

        assertNotNull(graphDataInitializer.getLGraph());
        assertNotEquals(graphDataInitializer.getAmountOfLayers(), 0);
        assertNotEquals(graphDataInitializer.getLengthOfLayer(0), 0);
        assertNotNull(graphDataInitializer.getCurrentNodeOrder());
    }

    @Test
    public void simpleCountingShouldBeCorrect() {
        LGraph graph = givenOneNodeGraph();
        GraphDataInitializer graphDataInitializer = whenGraphIsInitialized(graph);

        assertThat(graphDataInitializer.getAmountOfLayers(), is(1));
        assertThat(graphDataInitializer.getLengthOfLayer(0), is(1));
    }

    @Test
    public void shouldHaveOneCrossingForCrossGraph() {
        LGraph graph = givenCrossFormedGraph();
        GraphDataInitializer graphDataInitializer = whenGraphIsInitialized(graph);

        assertThat(graphDataInitializer.getAmountOfCrossings(), is(1));
    }

    private LGraph givenOneNodeGraph() {
        TestGraphCreator graphCreator = new TestGraphCreator();
        return graphCreator.getOneNodeGraph();
    }

    private LGraph givenCrossFormedGraph() {
        TestGraphCreator graphCreator = new TestGraphCreator();
        return graphCreator.getCrossFormedGraph();
    }

    private GraphDataInitializer whenGraphIsInitialized(final LGraph graph) {
        GraphDataInitializer graphDataInitializer = new GraphDataInitializer(graph);
        graphDataInitializer.initialize();
        return graphDataInitializer;
    }

}
