package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.GraphDataInitializer;
import de.cau.cs.kieler.klay.layered.intermediate.TwoNodeTwoLayerCrossingCounter;

public class TwoNodeTwoLayerCrossingCounterTest {
    private TestGraphCreator creator;
    private TwoNodeTwoLayerCrossingCounter crossingCounter;
    private LGraph graph;
    private LNode upperNode;
    private LNode lowerNode;
    private Layer layerToCountIn;

    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void twoNodeNoEdges() {
        graph = creator.getTwoNodesNoConnectionGraph();
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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
        initializeGraph();

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

    private void assertEasternSideLowerUpperCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countEasternEdgeCrossings();
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" east, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertEasternSideUpperLowerCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countEasternEdgeCrossings();
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" east, upper lower "), crossings, is(expectedCrossings));
    }

    private void assertWesternSideLowerUpperCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countWesternEdgeCrossings();
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" west, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertWesternSideUpperLowerCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countWesternEdgeCrossings();
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" west, upper lower "), crossings, is(expectedCrossings));
    }

    private void assertBothSideLowerUpperCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countBothSideCrossings();
        int crossings = crossingCounter.getLowerUpperCrossings();
        assertThat(failMessage(" both, lower upper "), crossings, is(expectedCrossings));
    }

    private void assertBothSideUpperLowerCrossingsIs(final int expectedCrossings) {
        initCrossingCounter();
        crossingCounter.countBothSideCrossings();
        int crossings = crossingCounter.getUpperLowerCrossings();
        assertThat(failMessage(" both, upper lower "), crossings, is(expectedCrossings));
    }

    private void setLayerToCountIn(final int layerIndex) {
        layerToCountIn = graph.getLayers().get(layerIndex);
    }

    private void setUpperNode(final int nodeIndex) {
        List<LNode> nodes = layerToCountIn.getNodes();
        upperNode = nodes.get(nodeIndex);
    }

    private void setLowerNode(final int nodeIndex) {
        List<LNode> nodes = layerToCountIn.getNodes();
        lowerNode = nodes.get(nodeIndex);
    }

    private void initCrossingCounter() {
        crossingCounter = new TwoNodeTwoLayerCrossingCounter(upperNode, lowerNode);
    }

    private void initializeGraph() {
        GraphDataInitializer initializer = new GraphDataInitializer(graph);
        initializer.initialize();
        graph = initializer.getLGraph();
    }

    private String failMessage(final String configuration) {
        return "With configuration: " + configuration + " in Layer " + layerToCountIn
                + " between the nodes: Upper node " + upperNode.getIndex() + " and lower Node "
                + lowerNode.getIndex();
    }
}
