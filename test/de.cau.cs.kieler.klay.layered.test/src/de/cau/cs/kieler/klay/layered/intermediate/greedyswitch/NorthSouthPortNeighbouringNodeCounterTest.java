/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
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

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
public class NorthSouthPortNeighbouringNodeCounterTest {
    private TestGraphCreator creator;
    private NorthSouthPortNeighbouringNodeCounter counter;
    private LNode[] layer;

    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void noNorthSouthNode() {
        creator.getCrossFormedGraph();
        countCrossingsInLayerBetweenNodes(0, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void southernNorthSouthNodeCrossing() {
        creator.getNorthSouthCrossingGraph();
        countCrossingsInLayerBetweenNodes(0, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void northernNorthSouthNodeCrossings() {
        creator.getNorthSouthUpwardCrossingGraph();
        countCrossingsInLayerBetweenNodes(0, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void oneNodeIsLongEdgeDummy() {
        creator.getNorthSouthDummyEdgeCrossingGraph();
        countCrossingsInLayerBetweenNodes(1, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void oneNodeIsLongEdgeDummyNorthern() {
        creator.getNorthernNorthSouthDummyEdgeCrossingGraph();
        countCrossingsInLayerBetweenNodes(1, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void noFixedOrderConstraint() {
        creator.getNorthSouthCrossingGraph();
        creator.getCurrentOrder()[0][0].setProperty(LayoutOptions.PORT_CONSTRAINTS,
                PortConstraints.FIXED_SIDE);
        countCrossingsInLayerBetweenNodes(0, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void withNormalNode() {
        creator.getNorthSouthCrossingGraph();
        countCrossingsInLayerBetweenNodes(0, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDontCross() {
        creator.getNorthSouthCrossingGraphEdgesFromEastAndWestNoCrossings();
        countCrossingsInLayerBetweenNodes(1, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void northSouthEdgesComeFromBothSidesDoCross() {
        creator.getNorthSouthCrossingGraphEdgesFromEastAndWestAndCross();
        countCrossingsInLayerBetweenNodes(1, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(1));

    }

    @Test
    public void switchNodesAndRecount() {
        creator.getNorthSouthUpwardCrossingGraph();
        countCrossingsInLayerBetweenNodes(0, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
        switchNodes(0, 1);
        counter.notifyNodeSwitch(layer[0], layer[1]);
        counter.countCrossings(layer[0], layer[1]);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(1));
    }

    private void switchNodes(final int upper, final int lower) {
        LNode upperNode = layer[upper];
        LNode lowerNode = layer[lower];
        layer[upper] = lowerNode;
        layer[lower] = upperNode;
    }

    private void countCrossingsInLayerBetweenNodes(final int layerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {
        layer = creator.getCurrentOrder()[layerIndex];
        counter = new NorthSouthPortNeighbouringNodeCounter(layer);
        counter.countCrossings(layer[upperNodeIndex], layer[lowerNodeIndex]);

    }
}
