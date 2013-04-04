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
package de.cau.cs.kieler.papyrus.sequence;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.graph.SMessage;

/**
 * Heuristic implementation of cycle breaking.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public class SCycleBreaker {
    /** The list of nodes that have to be split. */
    private HashSet<LNode> split;
    /** The list of nodes that were already visited in the current iteration. */
    private List<LNode> chain;

    /**
     * Break the cycles in the given layered graph. Cycles are broken by splitting one of the
     * affected nodes (which represent messages). With that, this message is not drawn horizontally
     * anymore.
     * 
     * @param lgraph
     *            the layered graph
     * @param progressMonitor
     *            the progress monitor
     */
    public void breakCycles(final LGraph lgraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Cycle Breaking", 1);

        // The set of edges to be split after the cycle detecting phase
        split = new HashSet<LNode>();
        chain = new LinkedList<LNode>();

        // Use node IDs to indicate if a node was already visited
        // 0 = node was not visited at all
        // 1 = node was visited before, but not in current path
        // 2 = node was visited in current path
        for (LNode node : lgraph.getLayerlessNodes()) {
            node.id = 0;
        }

        // Start a dfs only when the node was not visited by any other earlier dfs
        for (LNode node : lgraph.getLayerlessNodes()) {
            if (node.id == 0) {
                dfs(node);
            }
        }

        // split all nodes in the hashSet
        for (LNode node : split) {
            splitNode(lgraph, node);
        }

        progressMonitor.done();
    }

    /**
     * Split the given node into two nodes for each of the corresponding lifelines. Rearrange edges
     * in order to have only edges showing the order at one lifeline.
     * 
     * @param node
     *            the node to be split
     */
    private void splitNode(final LGraph lgraph, final LNode node) {
        // Create new LNode in the LayeredGraph
        LNode newNode = new LNode(lgraph);
        lgraph.getLayerlessNodes().add(newNode);

        SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
        SLifeline sourceLL = message.getSource();
        SLifeline targetLL = message.getTarget();
        Iterator<LEdge> oEdges = node.getConnectedEdges().iterator();
        while (oEdges.hasNext()) {
            LEdge edge = oEdges.next();
            SLifeline belongsTo = edge.getProperty(SequenceDiagramProperties.BELONGS_TO_LIFELINE);
            if (belongsTo == targetLL) {
                // if edge belongs to targetLifeline, rebase it to newNode
                if (edge.getSource().getNode() == node) {
                    edge.getSource().setNode(newNode);
                } else if (edge.getTarget().getNode() == node) {
                    edge.getTarget().setNode(newNode);
                }
            }
            // if edge belongs to sourceLifeline, leave it as it was
        }
        node.setProperty(SequenceDiagramProperties.BELONGS_TO_LIFELINE, sourceLL);
        newNode.setProperty(SequenceDiagramProperties.BELONGS_TO_LIFELINE, targetLL);
        newNode.setProperty(Properties.ORIGIN, message);
    }

    /**
     * Process a depth first search starting with the given node and check for cycles.
     * 
     * @param node
     *            the node to start with
     */
    private void dfs(final LNode node) {
        if (node.id == 2) {
            // This node was already visited in current path
            // Find uppermost LNode in current chain and add it to split
            addUppermostNode(node);
        } else {
            // This node has not been visited in current path
            chain.add(node);
            // Mark as visited
            node.id = 2;

            // Process successors
            for (LEdge edge : node.getOutgoingEdges()) {
                dfs(edge.getTarget().getNode());
            }
            // Mark as visited in previous path
            node.id = 1;
            chain.remove(chain.size() - 1);
        }
    }

    /**
     * Find uppermost LNode in the current cyclic chain and add it to the set of LNodes to be split.
     * 
     * @param foundNode
     *            the uppermost node in the current chain
     */
    private void addUppermostNode(final LNode foundNode) {
        LNode uppermost = foundNode;
        float uppermostPos = Float.MAX_VALUE;
        int foundIndex = chain.indexOf(foundNode);
        for (int i = foundIndex; i < chain.size(); i++) {
            LNode node = chain.get(i);
            SMessage message = (SMessage) node.getProperty(Properties.ORIGIN);
            KEdge edge = (KEdge) message.getProperty(Properties.ORIGIN);
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            // Compare only sourcePositions since messages can only lead downwards or horizontal
            float sourceYPos = edgeLayout.getSourcePoint().getY();
            if (sourceYPos < uppermostPos) {
                uppermostPos = sourceYPos;
                uppermost = node;
            }
        }
        split.add(uppermost);
    }
}
