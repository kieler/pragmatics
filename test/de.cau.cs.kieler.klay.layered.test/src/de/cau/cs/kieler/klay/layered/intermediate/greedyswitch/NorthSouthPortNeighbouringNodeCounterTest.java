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
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * @author alan
 *
 */
public class NorthSouthPortNeighbouringNodeCounterTest {
    private TestGraphCreator creator;
    private NorthSouthPortNeighbouringNodeCounter counter;

    @Before
    public void setUp() {
        creator = new TestGraphCreator();
    }

    @Test
    public void noNorthSouthNode() {
        creator.getCrossFormedGraph();
        countCrossingsInLayerBetween(0, 0, 1);
        assertThat(counter.getUpperLowerCrossings(), is(0));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void southernNorthSouthNodeCrossing() {
        creator.getNorthSouthCrossingGraph();
        countCrossingsInLayerBetween(0, 1, 2);
        assertThat(counter.getUpperLowerCrossings(), is(1));
        assertThat(counter.getLowerUpperCrossings(), is(0));
    }

    @Test
    public void northernNortSouthNodeCrossings() {
        fail("Test not yet implemented.");// TODO-alan

    }

    @Test
    public void oneNodeIsLongEdgeDummy() {
        fail("Test not yet implemented.");// TODO-alan

    }

    @Test
    public void noFixedOrderConstraint() {
        fail("Test not yet implemented.");// TODO-alan

    }

    @Test
    public void withNormalNode() {
        fail("Test not yet implemented.");// TODO-alan

    }

    private void countCrossingsInLayerBetween(final int layerIndex, final int upperNodeIndex,
            final int lowerNodeIndex) {
        LNode[] layer = creator.getCurrentOrder()[layerIndex];
        counter = new NorthSouthPortNeighbouringNodeCounter(layer);
        counter.countCrossings(layer[upperNodeIndex], layer[lowerNodeIndex]);
    }
}
