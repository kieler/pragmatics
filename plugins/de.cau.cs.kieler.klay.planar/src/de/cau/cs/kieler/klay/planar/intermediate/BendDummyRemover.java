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
package de.cau.cs.kieler.klay.planar.intermediate;

import java.util.Iterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * Removes the dummy bend nodes of the graph and updates the grid representation.
 * 
 * <dl>
 *   <dt>Precondition:</dt><dd>Edge bendpoint dummies exists.</dd>
 *   <dt>Postcondition:</dt><dd>Removed the dummies and added the bends to the .</dd>
 *   <dt>Slots:</dt><dd>After the compaction phase.</dd>
 * </dl>
 * 
 * @author pkl
 * @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public class BendDummyRemover implements ILayoutProcessor {

    /** The graph which is changed during the processor. */
    private PGraph graph;

    /** The grid that is changed during the processor. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Remove dummynodes", 1);

        this.graph = pGraph;
        this.grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        // construct bendpoints for each node for which there is no original node.
        for (int x = 0; x < this.grid.getWidth(); x++) {
            for (int y = 0; y < this.grid.getHeight(); y++) {
                PNode gridNode = this.grid.get(x, y);
                if (gridNode != null && gridNode.hasProperties()
                        && (gridNode.getProperty(Properties.BENDPOINT) != null)) {
                    constructBendPointEdge(x, y);
                }
            }
        }
        
        // release resources
        graph = null;
        grid = null;
        monitor.done();
    }

    /**
     * Removes a bend point dummy of the grid and adds it as a bend point to the edge. If the edge
     * is original meaning it contains a {@link KEdge} as property, otherwise choose a arbitrary
     * edge.
     * 
     * @param x
     *            index of the grid of the bend point dummy.
     * @param y
     *            index of the grid of the bend point dummy.
     */
    private void constructBendPointEdge(final int x, final int y) {

        // get a node for grid coordinates
        PNode currentNode = this.grid.get(x, y);
        Iterator<PEdge> edgeIterator = currentNode.getEdges().iterator();

        // an edge is divided by a bend, thus there are exact two connected edges
        PEdge first = edgeIterator.next();
        PNode firstNode = first.getOppositeNode(currentNode);
        PEdge second = edgeIterator.next();
        PNode secondNode = second.getOppositeNode(currentNode);

        // Add the bends to the first if it is a original edge. Otherwise take the other edge.
        if (first.getProperty(Properties.ORIGIN) != null) {
            first.getBendPoints().add(x, y);
            this.graph.bridgeOverEdge(first, firstNode, secondNode);
            PUtil.addBendsToEdge(first, second.getBendPoints(), null, this.grid);
        } else {
            second.getBendPoints().add(x, y);
            this.graph.bridgeOverEdge(second, secondNode, firstNode);
            PUtil.addBendsToEdge(second, first.getBendPoints(), null, this.grid);
        }
        this.graph.removeNode(currentNode);
        this.grid.remove(currentNode);
    }

}
