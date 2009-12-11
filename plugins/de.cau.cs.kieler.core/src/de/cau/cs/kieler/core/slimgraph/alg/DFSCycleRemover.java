/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.Iterator;
import java.util.LinkedList;

import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;

/**
 * Cycle remover that uses a depth first search to remove cycles.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class DFSCycleRemover extends AbstractCycleRemover {

    /** next DFS number to use. */
    private int nextDfs;

    /**
     * {@inheritDoc}
     */
    public void removeCycles(final KSlimGraph graph) {
        getMonitor().begin("DFS cycle removal", 1);
        setReversedEdges(new LinkedList<KSlimEdge>());
        nextDfs = 1;

        // initialize node ranks
        for (KSlimNode node : graph.getNodes()) {
            node.setRank(-1);
        }

        // mark back edges of the DFS run
        Iterator<KSlimNode> nodeIter = graph.getNodes().iterator();
        while (nodeIter.hasNext()) {
            KSlimNode node = nodeIter.next();
            if (node.getRank() < 0) {
                // node was not visited yet
                dfsVisit(node);
            }
        }
        // reverse all marked edges
        reverseEdges();

        getMonitor().done();
    }

    /**
     * Visits a node in the DFS algorithm. This method is recursive.
     * 
     * @param node node to visit
     */
    private void dfsVisit(final KSlimNode node) {
        // put DFS mark on the new node
        node.setRank(nextDfs++);

        // process all outgoing edges
        for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
            if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                KSlimNode targetNode = edgeEntry.getEdge().getTarget();
                if (targetNode.getRank() >= 0) {
                    if (targetNode.getRank() > 0 && targetNode.getId() != node.getId()) {
                        // a cycle was found, break it
                        getReversedEdges().add(edgeEntry.getEdge());
                    }
                } else {
                    // target node was not visited yet
                    dfsVisit(targetNode);
                    edgeEntry.getEdge().setRank(0);
                }
            }
        }
        // backtracking: set this node's number to 0
        node.setRank(0);
    }

}
