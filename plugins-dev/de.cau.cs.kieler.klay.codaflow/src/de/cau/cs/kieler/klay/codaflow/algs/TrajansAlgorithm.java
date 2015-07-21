/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.algs;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.adaptagrams.cgraph.CEdge;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;

/**
 * Tarjan's algorithm to find strongly connected components.
 * 
 * @author uru
 */
public final class TrajansAlgorithm {

    private Map<CNode, Integer> indexMap = Maps.newHashMap();
    private Map<CNode, Integer> lowlinkMap = Maps.newHashMap();
    private int index = 0;
    private Stack<CNode> stack = new Stack<CNode>();
    private Set<Set<CNode>> sccs = Sets.newHashSet();

    /**
     * Use the static utility method to execute the algorithm.
     */
    private TrajansAlgorithm(final CGraph graph) {
        for (CNode n : graph.getChildren()) {
            if (!indexMap.containsKey(n)) {
                strongConnect(n);
            }
        }
    }

    /**
     * @param graph
     *            the graph for which to calculate the strongly connected components.
     * @return a set with all strongly connected components.
     */
    public static Set<Set<CNode>> getStronglyConnectedComponents(final CGraph graph) {
        return new TrajansAlgorithm(graph).sccs;
    }

    private void strongConnect(final CNode node) {
        indexMap.put(node, index);
        lowlinkMap.put(node, index);
        index++;
        stack.push(node);

        // successors of current node
        for (CEdge e : node.getOutgoingEdges()) {
            CNode target = e.getTarget();

            // ignore cross hierarchy edges
            if (!node.graph.equals(target.graph)) {
                continue;
            }

            if (!indexMap.containsKey(target)) {
                // successor not been visited
                strongConnect(target);
                lowlinkMap.put(node, Math.min(lowlinkMap.get(node), lowlinkMap.get(target)));
            } else if (stack.contains(target)) {
                // successor is in the current scc
                lowlinkMap.put(node, Math.min(lowlinkMap.get(node), indexMap.get(target)));
            }
        }

        // if current node is a root node, generate scc
        if (lowlinkMap.get(node) == indexMap.get(node)) {
            Set<CNode> scc = Sets.newHashSet();
            CNode sn = null;
            do {
                sn = stack.pop();
                scc.add(sn);
            } while (sn != node);
            sccs.add(scc);
        }
    }
}
