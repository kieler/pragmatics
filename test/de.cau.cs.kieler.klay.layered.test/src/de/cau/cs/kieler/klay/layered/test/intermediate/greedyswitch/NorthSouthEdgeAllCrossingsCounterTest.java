/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2014 by
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

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.NorthSouthEdgeAllCrossingsCounter;

/**
 * Tests {@link NorthSouthEdgeAllCrossingsCounter}.
 * 
 * @author alan
 *
 */
public class NorthSouthEdgeAllCrossingsCounterTest {
    private NorthSouthEdgeTestGraphCreator creator;
    private NorthSouthEdgeAllCrossingsCounter counter;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber
    @Before
    public void setUp() {
        creator = new NorthSouthEdgeTestGraphCreator();
    }

    @Test
    public void northernNorthSouthNodeSingleCrossing() {
        creator.getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northernNorthSouthNodeMultipleCrossings() {
        creator.getNorthSouthUpwardMultipleCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void southernTowEdgeEastCrossing() {
        creator.getNorthSouthDownwardCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southernNorthSouthMultipleNodeCrossing() {
        creator.getNorthSouthDownwardMultipleCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void southernTwoWesternEdges() {
        creator.getNorthSouthSouthernTwoWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southernThreeWesternEdges() {
        creator.getNorthSouthSouthernThreeWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDontCross() {
        creator.getSouthernNorthSouthGraphEdgesFromEastAndWestNoCrossings();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void southernNorthSouthEdgesBothToEastDontCross() {
        creator.getSouthernNorthSouthEdgesBothToEast();
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
    public void allSidesMultipleCrossings() {
        creator.getNorthSouthAllSidesMultipleCrossings();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
    }

    @Test
    public void noFixedOrderConstraint() {
        creator.getNorthSouthDownwardCrossingGraph();
        creator.getCurrentOrder()[0][0].setProperty(LayoutOptions.PORT_CONSTRAINTS,
                PortConstraints.FIXED_SIDE);
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void oneEdgeDummyIsCrossedByOneSouthernNorthSouthPortEdge() {
        creator.getSouthernNorthSouthDummyEdgeCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void oneEdgeDummyIsCrossedByTwoSouthernNorthSouthPortEdges() {
        creator.getSouthernNorthSouthDummyEdgeTwoCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(2));
    }

    @Test
    public void southernTwoDummyEdgeAndTwoNorthSouthShouldCrossFourTimes() {
        creator.getSouthernTwoDummyEdgeAndNorthSouthCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
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

    @Test
    public void noNorthSouthNode() {
        creator.getCrossFormedGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northSouthCrossingRemovedAfterSwitch() {
        creator.getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);
        LNode[] layer = creator.getCurrentOrder()[0];

        switchNodes(layer, 0, 1);
        counter.notifyNodeSwitch(layer[0], layer[1]);
        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    private void switchNodes(final LNode[] layer, final int i, final int j) {
        LNode firstNode = layer[i];
        layer[i] = layer[j];
        layer[j] = firstNode;
    }

    private void initCounterForLayerWithIndex(final int layerIndex) {
        LNode[] layer = creator.getCurrentOrder()[layerIndex];
        counter = new NorthSouthEdgeAllCrossingsCounter(layer);
    }

}
