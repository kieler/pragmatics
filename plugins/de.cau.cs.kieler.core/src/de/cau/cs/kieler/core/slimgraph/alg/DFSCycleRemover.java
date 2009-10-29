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
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class DFSCycleRemover extends AbstractCycleRemover {

    /** next DFS number to use. */
    private int nextDfs;

    /*
     * (non-Javadoc)
     * 
     * @see de.cau.cs.kieler.core.graph.alg.ICycleRemover#removeCycles(de.cau.cs.
     *     kieler.core.graph.KGraph)
     */
    /** {@inheritDoc} */
    public void removeCycles(final KSlimGraph graph) {
        getMonitor().begin("DFS cycle removal", 1);
        reversedEdges = new LinkedList<KSlimEdge>();
        nextDfs = 1;

        // initialize node ranks
        for (KSlimNode node : graph.nodes) {
            node.rank = -1;
        }

        // mark back edges of the DFS run
        Iterator<KSlimNode> nodeIter = graph.nodes.iterator();
        while (nodeIter.hasNext()) {
            KSlimNode node = nodeIter.next();
            if (node.rank < 0) {
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
        node.rank = nextDfs++;

        // process all outgoing edges
        for (KSlimNode.IncEntry edgeEntry : node.incidence) {
            if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT) {
                KSlimNode targetNode = edgeEntry.edge.target;
                if (targetNode.rank >= 0) {
                    if (targetNode.rank > 0 && targetNode.id != node.id) {
                        // a cycle was found, break it
                        reversedEdges.add(edgeEntry.edge);
                    }
                } else {
                    // target node was not visited yet
                    dfsVisit(targetNode);
                    edgeEntry.edge.rank = 0;
                }
            }
        }
        // backtracking: set this node's number to 0
        node.rank = 0;
    }

}
