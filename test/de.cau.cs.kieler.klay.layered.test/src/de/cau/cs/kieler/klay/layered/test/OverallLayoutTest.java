/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;

/**
 * Test the overall layout process including graph import and layout transfer.
 *
 * @author msp
 */
public class OverallLayoutTest {
    
    /**
     * Create a simple test graph. The graph has at least two nodes and an edge, the nodes have
     * predefined sizes with fixed size constraint, but neither nodes nor edges have predefined
     * coordinates, i.e. they are all set to (0,0). The graph size is initially 0 as well.
     * 
     * @return a simple test graph
     */
    private static final KNode createSimpleGraph() {
        KNode parentNode = KimlUtil.createInitializedNode();
        
        KNode node1 = KimlUtil.createInitializedNode();
        node1.setParent(parentNode);
        node1.getData(KShapeLayout.class).setWidth(30);
        node1.getData(KShapeLayout.class).setHeight(30);
        node1.getData(KShapeLayout.class).setProperty(LayoutOptions.SIZE_CONSTRAINT,
                SizeConstraint.FIXED);
        
        KNode node2 = KimlUtil.createInitializedNode();
        node2.setParent(parentNode);
        node2.getData(KShapeLayout.class).setWidth(30);
        node2.getData(KShapeLayout.class).setHeight(30);
        node2.getData(KShapeLayout.class).setProperty(LayoutOptions.SIZE_CONSTRAINT,
                SizeConstraint.FIXED);
        
        KEdge edge1 = KimlUtil.createInitializedEdge();
        edge1.setSource(node1);
        edge1.setTarget(node2);
        
        return parentNode;
    }
    
    
    private KNode simpleGraph;
    private IKielerProgressMonitor simpleMonitor;
    
    @Before
    public void setUp() {
        // create the layered layout provider
        LayeredLayoutProvider layoutProvider = new LayeredLayoutProvider();
        // create a simple test graph and the corresponding progress monitor
        simpleGraph = createSimpleGraph();
        simpleMonitor = new BasicProgressMonitor();
        // perform layout on the simple test graph
        layoutProvider.doLayout(simpleGraph, simpleMonitor);
    }

    @Test
    public void testNodeCoordinates() {
        for (KNode node : simpleGraph.getChildren()) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            assertTrue(nodeLayout.getXpos() > 0);
            assertTrue(nodeLayout.getYpos() > 0);
        }
    }
    
    @Test
    public void testEdgeCoordinates() {
        for (KNode node : simpleGraph.getChildren()) {
            for (KEdge edge : node.getOutgoingEdges()) {
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                assertTrue(edgeLayout.getSourcePoint().getX() > 0);
                assertTrue(edgeLayout.getSourcePoint().getY() > 0);
                assertTrue(edgeLayout.getTargetPoint().getX() > 0);
                assertTrue(edgeLayout.getTargetPoint().getY() > 0);
            }
        }
    }
    
    @Test
    public void testGraphSize() {
        KShapeLayout parentLayout = simpleGraph.getData(KShapeLayout.class);
        assertTrue(parentLayout.getWidth() > 0);
        assertTrue(parentLayout.getHeight() > 0);
    }

}
