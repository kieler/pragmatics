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

import org.junit.Test;

import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.intermediate.greedyswitch.NorthSouthEdgeAllCrossingsCounter;

/**
 * Tests {@link NorthSouthEdgeAllCrossingsCounter}.
 * 
 * @author alan
 *
 */
public class NorthSouthEdgeAllCrossingsCounterTest extends NorthSouthEdgeTestGraphCreator {
    private NorthSouthEdgeAllCrossingsCounter counter;

    // CHECKSTYLEOFF javadoc
    // CHECKSTYLEOFF MagicNumber

    @Test
    public void northernNorthSouthNodeSingleCrossing() {
        getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northernNorthSouthNodeMultipleCrossings() {
        getNorthSouthUpwardMultipleCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void southernTowEdgeEastCrossing() {
        getNorthSouthDownwardCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southernNorthSouthMultipleNodeCrossing() {
        getNorthSouthDownwardMultipleCrossingGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void southernTwoWesternEdges() {
        getNorthSouthSouthernTwoWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void southernThreeWesternEdges() {
        getNorthSouthSouthernThreeWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(3));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDontCross() {
        getSouthernNorthSouthGraphEdgesFromEastAndWestNoCrossings();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void southernNorthSouthEdgesBothToEastDontCross() {
        getSouthernNorthSouthEdgesBothToEast();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDoCross() {
        getNorthSouthEdgesFromEastAndWestAndCross();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void northernBothEdgesWestern() {
        getNorthSouthNorthernWesternEdges();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northernEasternPortToWestWesternPortToEast() {
        getNorthSouthNorthernEasternPortToWestWesternPortToEast();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void allSidesMultipleCrossings() {
        getNorthSouthAllSidesMultipleCrossings();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
    }

    @Test
    public void noFixedOrderConstraint() {
        getNorthSouthDownwardCrossingGraph();
        getCurrentOrder()[0][0].setProperty(LayoutOptions.PORT_CONSTRAINTS,
                PortConstraints.FIXED_SIDE);
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void oneEdgeDummyIsCrossedByOneSouthernNorthSouthPortEdge() {
        getSouthernNorthSouthDummyEdgeCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(1));
    }

    @Test
    public void oneEdgeDummyIsCrossedByTwoSouthernNorthSouthPortEdges() {
        getSouthernNorthSouthDummyEdgeTwoCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(2));
    }

    @Test
    public void southernTwoDummyEdgeAndTwoNorthSouthShouldCrossFourTimes() {
        getSouthernTwoDummyEdgeAndNorthSouthCrossingGraph();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
    }

    @Test
    public void normalNodesNorthSouthEdgesHaveCrossingsToLongEdgeDummyOnBothSides() {
        getMultipleNorthSouthAndLongEdgeDummiesOnBothSides();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(4));
    }

    @Test
    public void ignoresUnconnectedPortsForNormalNodeAndLongEdgeDummies() {
        getLongEdgeDummyAndNormalNodeWithUnusedPortsOnSouthernSide();
        initCounterForLayerWithIndex(1);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void noNorthSouthNode() {
        getCrossFormedGraph();
        initCounterForLayerWithIndex(0);

        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    @Test
    public void northSouthCrossingRemovedAfterSwitch() {
        getNorthSouthUpwardCrossingGraph();
        initCounterForLayerWithIndex(0);
        LNode[] layer = getCurrentOrder()[0];

        switchNodes(layer, 0, 1);
        counter.notifyNodeSwitch(layer[0], layer[1]);
        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(0));
    }

    /**
     * <pre>
     * 
     * *----
     *    /+--* 
     *   --+--*
     *   | |
     *  _|_|_
     *  |   |
     *  |___|
     *  .
     * </pre>
     * 
     */
    @Test
    public void givenPolylineRoutingWhenMoreThanOneEdgeIntoNSNode_countsTheseToo() {
        LNode leftNode = addNodeToLayer(makeLayer());
        LNode[] middleNodes = addNodesToLayer(3, makeLayer());
        LNode[] rightNodes = addNodesToLayer(2, makeLayer());

        setFixedOrderConstraint(middleNodes[2]);

        // ports are added in clockwise fashion!
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[1], rightNodes[0], false);
        addNorthSouthEdge(PortSide.NORTH, middleNodes[2], middleNodes[0], leftNode, true);
        // second edge on middle node
        LPort middleNodePort = middleNodes[1].getPorts().get(0);
        eastWestEdgeFromTo(middleNodePort, rightNodes[1]);
        getGraph().setProperty(LayoutOptions.EDGE_ROUTING, EdgeRouting.POLYLINE);

        initCounterForLayerWithIndex(1);
        int crossingCount = counter.countCrossings();

        assertThat(crossingCount, is(2));
    }

    private void switchNodes(final LNode[] layer, final int i, final int j) {
        LNode firstNode = layer[i];
        layer[i] = layer[j];
        layer[j] = firstNode;
    }

    private void initCounterForLayerWithIndex(final int layerIndex) {
        LNode[] layer = getCurrentOrder()[layerIndex];
        counter = new NorthSouthEdgeAllCrossingsCounter(layer);
    }

}
