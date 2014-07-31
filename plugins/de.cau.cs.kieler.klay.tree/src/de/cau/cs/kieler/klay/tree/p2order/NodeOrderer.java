/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.p2order;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.properties.PropertyHolderComparator;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * This phase orders the nodes of each level by separating the nodes into leaves and inner nodes.
 * It then fills whitespaces in the levels with corresponding leaves.
 * 
 * @author sor
 * @author sgu
 */
public class NodeOrderer implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION
            = new IntermediateProcessingConfiguration(
                    IntermediateProcessingConfiguration.BEFORE_PHASE_2,
                    EnumSet.of(
                            IntermediateProcessorStrategy.ROOT_PROC,
                            IntermediateProcessorStrategy.FAN_PROC));

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final TGraph graph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor arrange node", 1);

        // find the root of the component
        // expected only one root exists
        TNode root = null;
        LinkedList<TNode> roots = new LinkedList<TNode>();
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }
        // order each level
        roots.add(root);
        orderLevel(roots, progressMonitor.subTask(1.0f));

        progressMonitor.done();

    }

    /**
     * Order each level by separating the nodes into leaves and inner nodes. And then fill gaps with
     * corresponding leaves.
     * 
     * @param currentLevel
     * @param progressMonitor
     */
    private void orderLevel(final LinkedList<TNode> currentLevel,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor arrange level", 1);

        int pos = 0;

        // sort all nodes in this level by their fan out
        // so the leaves are at the end of the list
        Collections.sort(currentLevel, PropertyHolderComparator.with(Properties.FAN));

        // find the first occurence of a leave in the list
        int firstOcc = currentLevel.size();
        ListIterator<TNode> it = currentLevel.listIterator(currentLevel.size());
        boolean notNull = true;
        while (notNull && it.hasPrevious()) {
            TNode tNode = (TNode) it.previous();
            if ((tNode.getProperty(Properties.FAN) == 0)) {
                firstOcc--;
            } else {
                notNull = false;
            }
        }

        // seperate the level into leaves and inner nodes
        List<TNode> tmp = currentLevel.subList(0, firstOcc);
        LinkedList<TNode> inners = new LinkedList<TNode>(tmp);
        tmp = currentLevel.subList(firstOcc, currentLevel.size());
        LinkedList<TNode> leaves = new LinkedList<TNode>(tmp);

        // check if their are inner nodes left
        if (inners.isEmpty()) {
            // leave the leaves in their order
            for (TNode tENode : leaves) {
                tENode.setProperty(Properties.POSITION, pos++);
            }
        } else {

            // order each level of descendants of the inner nodes
            int size = inners.size();
            for (TNode tPNode : inners) {
                tPNode.setProperty(Properties.POSITION, pos++);

                // set the position of the children and set them in order
                LinkedList<TNode> children = tPNode.getChildrenCopy();
                orderLevel(children, progressMonitor.subTask(1 / size));

                // order the children by their reverse position
                Collections.sort(children,
                        Collections.reverseOrder(PropertyHolderComparator.with(Properties.POSITION)));

                // reset the list of children with the new order
                List<TEdge> sortedOutEdges = new LinkedList<TEdge>();

                for (TNode tNode : children) {
                    for (TEdge tEdge : tPNode.getOutgoingEdges()) {
                        if (tEdge.getTarget() == tNode) {
                            sortedOutEdges.add(tEdge);
                        }
                    }
                }
                tPNode.getOutgoingEdges().clear();
                tPNode.getOutgoingEdges().addAll(sortedOutEdges);

                // fill gaps with leafs
                it = leaves.listIterator(leaves.size());
                int fillGap = tPNode.getOutgoingEdges().size();
                notNull = true;
                while ((0 < fillGap) && notNull && it.hasPrevious()) {
                    TNode tNode = (TNode) it.previous();
                    if ((tNode.getProperty(Properties.FAN) == 0)) {
                        tNode.setProperty(Properties.POSITION, pos++);
                        fillGap--;
                        it.remove();
                    } else {
                        notNull = false;
                    }
                }
            }
        }
        progressMonitor.done();
    }
}
