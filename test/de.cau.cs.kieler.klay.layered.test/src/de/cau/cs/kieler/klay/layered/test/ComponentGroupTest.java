/*
a * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test;

import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_EAST_SOUTH;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_EAST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_EAST_SOUTH;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_EAST_SOUTH_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_EAST_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NONE;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_EAST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_EAST_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_SOUTH;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_SOUTH_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_NORTH_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_SOUTH;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_SOUTH_WEST;
import static de.cau.cs.kieler.kiml.options.PortSide.SIDES_WEST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.components.ComponentGroup;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

/**
 * Tests the {@link de.cau.cs.kieler.klay.layered.components.ComponentGroup} class.
 * 
 * @author cds
 */
public class ComponentGroupTest {
    /**
     * Tests some valid combinations of layered graphs in a single component group.
     */
    @Test
    public void testValidConstraints() {
        // Add two N, E, W, S, and C components each to a single compound group
        ComponentGroup group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_NORTH)));
        assertTrue(group.add(generateGraph(SIDES_NORTH)));
        assertTrue(group.add(generateGraph(SIDES_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_WEST)));
        assertTrue(group.add(generateGraph(SIDES_WEST)));
        assertTrue(group.add(generateGraph(SIDES_EAST)));
        assertTrue(group.add(generateGraph(SIDES_EAST)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
        
        // Add a horizontal and two E, W, and C components each to a single compound group
        group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_EAST_WEST)));
        assertTrue(group.add(generateGraph(SIDES_EAST_WEST)));
        assertTrue(group.add(generateGraph(SIDES_WEST)));
        assertTrue(group.add(generateGraph(SIDES_WEST)));
        assertTrue(group.add(generateGraph(SIDES_EAST)));
        assertTrue(group.add(generateGraph(SIDES_EAST)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
        
        // Add a vertical and two N, S, and C components each to a single compound group
        group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_NORTH_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_NORTH_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_NORTH)));
        assertTrue(group.add(generateGraph(SIDES_NORTH)));
        assertTrue(group.add(generateGraph(SIDES_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_SOUTH)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
        assertTrue(group.add(generateGraph(SIDES_NONE)));
    }
    
    /**
     * Tests the invalid combinations of layered graphs in a single component group.
     */
    @Test
    public void testInvalidConstraints() {
        // Add a horizontal and a vertical component to a single component group
        ComponentGroup group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_EAST_WEST)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_SOUTH)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_SOUTH_WEST)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_EAST_SOUTH)));
        
        // Do the reverse
        group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_NORTH_SOUTH)));
        assertFalse(group.add(generateGraph(SIDES_EAST_WEST)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_EAST_WEST)));
        assertFalse(group.add(generateGraph(SIDES_EAST_SOUTH_WEST)));
        
        // Add graphs to each corner
        group = new ComponentGroup();
        assertTrue(group.add(generateGraph(SIDES_NORTH_WEST)));
        assertTrue(group.add(generateGraph(SIDES_NORTH_EAST)));
        assertTrue(group.add(generateGraph(SIDES_SOUTH_WEST)));
        assertTrue(group.add(generateGraph(SIDES_EAST_SOUTH)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_EAST_SOUTH)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_EAST_WEST)));
        assertFalse(group.add(generateGraph(SIDES_EAST_SOUTH_WEST)));
        assertFalse(group.add(generateGraph(SIDES_NORTH_SOUTH_WEST)));
    }
    
    
    /**
     * Creates a new layered graph with its external port side connections property set to the
     * given set of connections.
     * 
     * @param connections the set of connections.
     * @return the layered graph.
     */
    private static LGraph generateGraph(final Set<PortSide> connections) {
        LGraph graph = new LGraph();
        graph.setProperty(InternalProperties.EXT_PORT_CONNECTIONS, connections);
        
        return graph;
    }
    
}
