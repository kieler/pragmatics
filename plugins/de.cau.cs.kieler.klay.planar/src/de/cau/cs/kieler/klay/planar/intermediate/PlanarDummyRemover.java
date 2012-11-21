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
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.planar.ILayoutProcessor;
import de.cau.cs.kieler.klay.planar.graph.PEdge;
import de.cau.cs.kieler.klay.planar.graph.PGraph;
import de.cau.cs.kieler.klay.planar.graph.PNode;
import de.cau.cs.kieler.klay.planar.properties.Properties;
import de.cau.cs.kieler.klay.planar.util.PUtil;

/**
 * This processor removes all inserted planarization dummy nodes. Planar dummy nodes are adjacent to
 * four edges. These are formed to two crossing edges.
 * 
 * @author pkl
 * @kieler.rating yellow 2012-11-01 review KI-30 by ima, cds
 */
public class PlanarDummyRemover implements ILayoutProcessor {

    /** The graph which is changed during the processor. */
    private PGraph graph;

    /** The grid that is changed during the processor. */
    private GridRepresentation grid;

    /**
     * {@inheritDoc}
     */
    public void process(final PGraph pGraph, final IKielerProgressMonitor monitor) {
        monitor.begin("Remove planar dummy nodes", 1);

        this.graph = pGraph;
        this.grid = graph.getProperty(Properties.GRID_REPRESENTATION);

        removePlanarDummies();

        // release resources
        graph = null;
        grid = null;
        monitor.done();
    }

    /**
     * Searches for planar dummy nodes. Bridges the edges around that node and removes the node from
     * graph and grid.
     */
    private void removePlanarDummies() {
        List<PNode> planarDummynodes = new LinkedList<PNode>();
        for (PNode node : graph.getNodes()) {
            if (node.getProperty(Properties.PLANAR_DUMMY_NODE) != null) {
                planarDummynodes.add(node);
            }
        }

        for (PNode dummyNode : planarDummynodes) {
            Iterator<PEdge> iterator = dummyNode.adjacentEdges().iterator();
            PEdge first = iterator.next();
            PEdge second = iterator.next();
            PEdge third = iterator.next();
            PEdge fourth = iterator.next();

            // move the original edge over the dummy node from source to new target or vice versa
            // according to the edge direction. Edge 1 and 3 are straight to each other so that
            // we can move the edge from source 1 to target 3 or vice versa.
            doPlanarRemoveStep(dummyNode, first, third);

            // do the same to the edges 2 and 4.
            doPlanarRemoveStep(dummyNode, second, fourth);

            graph.removeNode(dummyNode);
            grid.remove(dummyNode);
        }
    }

    /**
     * Bridge the edges of a dummy node. Thereby one of the edges is deleted and the other edge is
     * moved around the dummy node. The both parameter edges have to be straight to each other.
     * 
     * @param dummyNode
     *            {@link PNode}
     * @param first
     *            {@link PEdge} adjacent to dummy node
     * @param second
     *            {@link PEdge} adjacent to dummy node
     */
    private void doPlanarRemoveStep(final PNode dummyNode, final PEdge first, final PEdge second) {
        if (first.getProperty(Properties.ORIGIN) != null) {
            first.getBendPoints().addAll(second.getBendPoints());
            graph.bridgeOverEdge(first, first.getOppositeNode(dummyNode),
                    second.getOppositeNode(dummyNode));
        } else if (second.getProperty(Properties.ORIGIN) != null) {
            graph.bridgeOverEdge(second, second.getOppositeNode(dummyNode),
                    first.getOppositeNode(dummyNode));
            PUtil.addBendsToEdge(second, first.getBendPoints(), dummyNode, this.grid);
        } else {
            graph.bridgeOverEdge(first, first.getOppositeNode(dummyNode),
                    second.getOppositeNode(dummyNode));
            PUtil.addBendsToEdge(first, second.getBendPoints(), dummyNode, this.grid);

            List<PEdge> insertableEdges = graph.getProperty(Properties.INSERTABLE_EDGES);
            for (PEdge pEdge : insertableEdges) {
                if (first.getSource() == pEdge.getSource()
                        && first.getTarget() == pEdge.getTarget()) {
                    first.setProperty(Properties.ORIGIN, pEdge.getProperty(Properties.ORIGIN));
                    break;
                }
            }
        }
    }
}
