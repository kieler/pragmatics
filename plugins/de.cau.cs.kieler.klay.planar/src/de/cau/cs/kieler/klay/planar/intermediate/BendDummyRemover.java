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

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * Removes the dummy bend nodes of the graph and updates the grid representation.
 * 
 * @author pkl
 */
public class BendDummyRemover extends AbstractAlgorithm implements ILayoutProcessor {

    /** The graph which is changed during the processor. */
    private PGraph graph;

    /** The grid that is changed during the processor. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph) {
        getMonitor().begin("Remove dummynodes", 1);

        this.graph = pGraph;
        this.grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        // construct bendpoints for each node for which there is no original node.
        for (int x = 0; x < this.grid.getWidth(); x++) {
            for (int y = 0; y < this.grid.getHeight(); y++) {
                if (this.grid.get(x, y) != null && this.grid.get(x, y).hasProperties()
                        && (this.grid.get(x, y).getProperty(Properties.BENDPOINT) != null)) {
                    constructBendPointEdge(x, y);
                }
            }
        }
        getMonitor().done();
    }

    /**
     * Removes a bend point dummy from the grid and adds it as bend point the edge, that is original
     * meaning contains a {@link KEdge} as property, otherwise choose a arbitrary one.
     * 
     * @param x
     *            index of the grid of the bend point dummy.
     * @param y
     *            index of the grid of the bend point dummy.
     */
    private void constructBendPointEdge(final int x, final int y) {
        PNode currentNode = this.grid.get(x, y);
        Iterator<PEdge> nodeIterator = currentNode.getEdges().iterator();

        PEdge first = nodeIterator.next();
        PNode firstNode = first.getOppositeNode(currentNode);
        PEdge second = nodeIterator.next();
        PNode secondNode = second.getOppositeNode(currentNode);

        if (first.hasProperties() && first.getProperty(Properties.ORIGIN) != null) {
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
