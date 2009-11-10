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

import java.util.LinkedList;

import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;

/**
 * Cycle remover implementation that uses a greedy algorithm.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GreedyCycleRemover extends AbstractCycleRemover {

    /** indegree values for the nodes. */
    private int[] indeg;
    /** outdegree values for the nodes. */
    private int[] outdeg;
    /** list of source nodes. */
    private final LinkedList<KSlimNode> sources = new LinkedList<KSlimNode>();
    /** list of sink nodes. */
    private final LinkedList<KSlimNode> sinks = new LinkedList<KSlimNode>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        sources.clear();
        sinks.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void removeCycles(final KSlimGraph graph) {
        getMonitor().begin("Greedy cycle removal", 1);
        setReversedEdges(new LinkedList<KSlimEdge>());

        // initialize values for the algorithm
        int unprocessedNodes = graph.getNodes().size();
        indeg = new int[unprocessedNodes];
        outdeg = new int[unprocessedNodes];
        int nextRight = -1, nextLeft = 1;
        for (KSlimNode node : graph.getNodes()) {
            node.setRank(0);
            for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
                if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                    outdeg[node.getId()]++;
                } else {
                    indeg[node.getId()]++;
                }
            }
            if (outdeg[node.getId()] == 0) {
                sinks.add(node);
            } else if (indeg[node.getId()] == 0) {
                sources.add(node);
            }
        }

        // assign ranks to all nodes
        while (unprocessedNodes > 0) {
            while (!sinks.isEmpty()) {
                KSlimNode sink = sinks.removeFirst();
                sink.setRank(nextRight--);
                updateNeighbors(sink);
                unprocessedNodes--;
            }
            while (!sources.isEmpty()) {
                KSlimNode source = sources.removeFirst();
                source.setRank(nextLeft++);
                updateNeighbors(source);
                unprocessedNodes--;
            }
            if (unprocessedNodes != 0) {
                int maxOutflow = Integer.MIN_VALUE;
                KSlimNode maxNode = null;
                for (KSlimNode node : graph.getNodes()) {
                    if (node.getRank() == 0) {
                        int outflow = outdeg[node.getId()] - indeg[node.getId()];
                        if (outflow > maxOutflow) {
                            maxOutflow = outflow;
                            maxNode = node;
                        }
                    }
                }
                maxNode.setRank(nextLeft++);
                updateNeighbors(maxNode);
                unprocessedNodes--;
            }
        }

        // shift negative ranks
        int shiftBase = graph.getNodes().size() + 1;
        for (KSlimNode node : graph.getNodes()) {
            if (node.getRank() < 0) {
                node.setRank(node.getRank() + shiftBase);
            }
        }

        // mark edges that point left
        for (KSlimEdge edge : graph.getEdges()) {
            if (edge.getSource().getRank() > edge.getTarget().getRank()) {
                getReversedEdges().add(edge);
            } else {
                edge.setRank(0);
            }
        }

        // reverse all marked edges
        reverseEdges();

        getMonitor().done();
    }

    /**
     * Updates indegree and outdegree values of the neighbors of the given node,
     * simulating its removal from the graph. the sources and sinks lists are
     * also updated.
     * 
     * @param node node for which neighbors are updated
     */
    private void updateNeighbors(final KSlimNode node) {
        for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
            KSlimNode endpoint = edgeEntry.endpoint();
            if (endpoint.getRank() == 0) {
                if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                    indeg[endpoint.getId()]--;
                    if (indeg[endpoint.getId()] == 0 && outdeg[endpoint.getId()] != 0) {
                        sources.add(endpoint);
                    }
                } else {
                    outdeg[endpoint.getId()]--;
                    if (outdeg[endpoint.getId()] == 0 && indeg[endpoint.getId()] != 0) {
                        sinks.add(endpoint);
                    }
                }
            }
        }
    }

}
