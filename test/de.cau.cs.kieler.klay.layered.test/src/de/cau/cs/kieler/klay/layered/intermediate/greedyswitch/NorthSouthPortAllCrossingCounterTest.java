package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.graph.LNode;

public class NorthSouthPortAllCrossingCounterTest {
    private TestGraphCreator creator;
    private NorthSouthPortAllCrossingCounter counter;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void noNorthSouthNode() {
        creator.getCrossFormedGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void southernNorthSouthNodeCrossing() {
        creator.getNorthSouthCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northernNorthSouthNodeCrossings() {
        creator.getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);
        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void oneNodeIsLongEdgeDummy() {
        creator.getSouthernNorthSouthDummyEdgeCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void oneNodeIsLongEdgeDummyNorthern() {
        creator.getNorthernNorthSouthDummyEdgeCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void noFixedOrderConstraint() {
        creator.getNorthSouthCrossingGraph();
        creator.getCurrentOrder()[0][0].setProperty(LayoutOptions.PORT_CONSTRAINTS,
                PortConstraints.FIXED_SIDE);
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void withNormalNode() {
        creator.getNorthSouthCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDontCross() {
        creator.getSouthernNorthSouthGraphEdgesFromEastAndWestNoCrossings();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void southernNorthSouthEdgesBothToEast() {
        creator.getSouthernNorthSouthEdgesBothToEast();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void crossingsWithNorthSouthPortsBelongingToDifferentNodesShouldNotBeCounted() {
        creator.getGraphWhereLayoutUnitPreventsSwitch();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDoCross() {
        creator.getNorthSouthEdgesFromEastAndWestAndCross();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));

    }

    @Test
    public void switchNodesAndRecount() {
        creator.getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southPortOndNormalNodeBelowLongEdgeDummy() {
        creator.getSouthPortOnNormalNodeBelowLongEdgeDummy();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void southernTwoWesternEdges() {
        creator.getNorthSouthSouthernTwoWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southernWesternPortToEastAndEasternPortToWest() {
        creator.getNorthSouthSouthernWesternPortToEastAndEasternPortToWest();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northernBothEdgesWestern() {
        creator.getNorthSouthNorthernWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northernEasternPortToWestWesternPortToEast() {
        creator.getNorthSouthNorthernEasternPortToWestWesternPortToEast();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void normalNodesNorthSouthEdgesHaveCrossingsToLongEdgeDummy() {
        creator.getNorthernNorthSouthDummyEdgeCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void normalNodesNorthSouthEdgesHaveCrossingsToLongEdgeDummyOnBothSides() {
        creator.getMultipleNorthSouthAndLongEdgeDummiesOnBothSides();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
    }

    @Test
    public void ignoresUnconnectedPortsForNormalNodeAndLongEdgeDummies() {
        creator.getLongEdgeDummyAndNormalNodeWithUnusedPortsOnSouthernSide();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));

    }

    private void initCounterForLayerWithIndex(final int layerIndex) {
        LNode[] layer = creator.getCurrentOrder()[layerIndex];
        counter = new NorthSouthPortAllCrossingCounter(layer);
    }

}
