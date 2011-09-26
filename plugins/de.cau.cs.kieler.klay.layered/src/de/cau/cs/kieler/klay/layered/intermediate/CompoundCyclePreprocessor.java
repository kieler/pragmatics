/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.HashMap;
import java.util.LinkedList;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Detects cyclic dependencies between compound nodes and reverts edges to remove them before simple
 * cycle removal and layering. Two compound nodes are regarded to be in cyclic dependency, if there
 * are cyclic adjacencies between the compound nodes themselves or between any two of their
 * descendants or between one of the compound nodes and any descendant of the other.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>A layered graph. All nodes have the ORIGIN-Property set to the KNode in the original graph
 * that is represented by them.</dd>
 * <dt>Postcondition:</dt>
 * <dd>The layered graph contains no more cyclic dependencies.</dd>
 * <dt>Slots:</dt>
 * <dd>Before phase 1.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>none.</dd>
 * </dl>
 * 
 * @author ima
 */
public class CompoundCyclePreprocessor extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin("Revert edges to remove cyclic dependencies between compound nodes", 1);

        // Initialize a hashmap in which edgeLists for a pair of KNodes can be stored. Pairs
        // expressed as LinkedLists to allow expressing edge directions.
        HashMap<LinkedList<KNode>, LinkedList<LEdge>> hierarchyCrossingEdges 
                = new HashMap<LinkedList<KNode>, LinkedList<LEdge>>();

        // For each edge crossing the borders of a compound node, make an insertion into the
        // corresponding List in the Hashmap. To find the Pair of KNodes that is the correct key,
        // walk up the nesting tree of the compound graph until a pair of ancestors is found that
        // shares the same parent.
        for (LNode lnode : layeredGraph.getLayerlessNodes()) {
            for (LEdge edge : lnode.getConnectedEdges()) {
                KNode sourceOriginal = (KNode) edge.getSource().getNode()
                        .getProperty(Properties.ORIGIN);
                KNode targetOriginal = (KNode) edge.getTarget().getNode()
                        .getProperty(Properties.ORIGIN);
                KNode sourceOriginalParent = sourceOriginal.getParent();
                KNode targetOriginalParent = targetOriginal.getParent();
                KNode currentSourceAncestor = sourceOriginalParent;
                KNode currentTargetAncestor = targetOriginalParent;

                if (currentSourceAncestor != currentTargetAncestor) {
                    int depthSourceAncestor = getDepth(currentSourceAncestor);
                    int depthTargetAncestor = getDepth(currentTargetAncestor);
                    if (currentSourceAncestor != currentTargetAncestor) {
                        // crawl up the nesting tree on the deep side to reach even depth level
                        for (int i = depthSourceAncestor; i > depthTargetAncestor; i--) {
                            currentSourceAncestor = currentSourceAncestor.getParent();
                        }
                        for (int j = depthTargetAncestor; j > depthSourceAncestor; j--) {
                            currentTargetAncestor = currentTargetAncestor.getParent();
                        }
                        // find two Ancestors with the same Parent
                        KNode nextSourceAncestor = currentSourceAncestor.getParent();
                        KNode nextTargetAncestor = currentTargetAncestor.getParent();
                        while (nextSourceAncestor != nextTargetAncestor) {
                            currentSourceAncestor = currentSourceAncestor.getParent();
                            currentTargetAncestor = currentTargetAncestor.getParent();
                            nextSourceAncestor = currentSourceAncestor.getParent();
                            nextTargetAncestor = currentTargetAncestor.getParent();
                        }
                        // Make the entry to the HashMap. Create the tupel that is to be the key and
                        // find the corresponding List. If no list is stored yet, create one.
                        LinkedList<KNode> keyTupel = new LinkedList<KNode>();
                        keyTupel.add(currentSourceAncestor);
                        keyTupel.add(currentTargetAncestor);
                        if (hierarchyCrossingEdges.containsKey(keyTupel)) {
                            hierarchyCrossingEdges.get(keyTupel).add(edge);
                        } else {
                            LinkedList<LEdge> newList = new LinkedList<LEdge>();
                            newList.add(edge);
                            hierarchyCrossingEdges.put(keyTupel, newList);
                        }

                    }
                }
            }

            revertCyclicEdges(hierarchyCrossingEdges);

            getMonitor().done();
        }
    }

    /**
     * Removes cyclic dependencies between compound nodes by reverting edges.
     * 
     * @param hierarchyCrossingEdges
     *            HashMap that contains lists of edges that constitute a dependency between two
     *            compound nodes. The pairs of nodes serve as keys. They are represented by
     *            LinkedLists with two KNode-Elements.
     */
    private void revertCyclicEdges(
            final HashMap<LinkedList<KNode>, LinkedList<LEdge>> hierarchyCrossingEdges) {
        // TODO Auto-generated method stub

    }

    /**
     * Returns the depth of the given KNode in the nesting tree of the compound graph.
     * 
     * @param knode
     *            The KNode, whose depth is to be calculated.
     * @return The depth of the KNode. The layoutNode is regarded to have depth 0.
     */
    private int getDepth(final KNode knode) {
        // TODO Auto-generated method stub
        return 0;
    }
}
