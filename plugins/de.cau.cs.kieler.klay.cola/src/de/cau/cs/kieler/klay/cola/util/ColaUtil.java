/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.util;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * @author uru
 */
public final class ColaUtil {

    private ColaUtil() {
    }

    /**
     * @param graph
     *            the graph for which to find the strongly connected components.
     * @return a set containing all strongly connected components of the graph.
     */
    public static Set<Set<KNode>> findStronglyConnectedComponents(final KNode graph) {
        return new TarjanAlg(graph).getStronglyConnectedComponents();
    }

    /**
     * Performs Tarjan's algorithm to find strongly connected components.
     */
    private static class TarjanAlg {

        private Map<KNode, Integer> indexMap = Maps.newHashMap();
        private Map<KNode, Integer> lowlinkMap = Maps.newHashMap();
        private int index = 0;
        private Stack<KNode> stack = new Stack<KNode>();
        private Set<Set<KNode>> sccs = Sets.newHashSet();

        public TarjanAlg(final KNode graph) {
            for (KNode n : graph.getChildren()) {
                if (!indexMap.containsKey(n)) {
                    strongConnect(n);
                }
            }
        }

        public Set<Set<KNode>> getStronglyConnectedComponents() {
            return sccs;
        }

        private void strongConnect(final KNode node) {
            indexMap.put(node, index);
            lowlinkMap.put(node, index);
            index++;
            stack.push(node);

            // successors of current node
            for (KEdge e : node.getOutgoingEdges()) {
                KNode target = e.getTarget();
                
                // ignore cross hierarchy edges
                if (!node.getParent().equals(target.getParent())) {
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
                Set<KNode> scc = Sets.newHashSet();
                KNode sn = null;
                do {
                    sn = stack.pop();
                    scc.add(sn);
                } while (sn != node);
                sccs.add(scc);
            }
        }
    }
}
