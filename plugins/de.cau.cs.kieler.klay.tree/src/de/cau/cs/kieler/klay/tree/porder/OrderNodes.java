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
package de.cau.cs.kieler.klay.tree.porder;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.SortTNodeProperty;

/**
 * This phase orders the nodes of each level by seperating the nodes into leaves and inner nodes.
 * And then fill whitespace in the levels with corresponding leaves.
 * 
 * @author sor
 * @author sgu
 */
public class OrderNodes implements ILayoutPhase {

    Comparator<TNode> comparator = new Comparator<TNode>() {
        public int compare(TNode t1, TNode t2) {
            if (t1.getLabel().length() < t2.getLabel().length()) {
                return -1;
            } else {
                if (t1.getLabel().length() > t2.getLabel().length()) {
                    return 1;
                } else {
                    return t1.getLabel().compareTo(t2.getLabel());
                }
            }
        }
    };

    private final TreeMap<TNode, Integer> debug = new TreeMap<TNode, Integer>(comparator);
    
    
    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_2, EnumSet.of(
                    LayoutProcessorStrategy.ROOT_PROC, LayoutProcessorStrategy.FAN_PROC));

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
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

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
        orderLevel(roots, 0, progressMonitor.subTask(1.0f));
        
        progressMonitor.done();

    }

    /**
     * Order each level by seperating the nodes into leaves and inner nodes. And then fill gaps with
     * corresponding leaves.
     * 
     * @param currentLevel
     * @param level
     * @param progressMonitor
     */
    private void orderLevel(LinkedList<TNode> currentLevel, int level,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor arrange level", 1);

        int pos = 0;

        // sort all nodes in this level by their fan out
        // so the leaves are at the end of the list
        Collections.sort(currentLevel, new SortTNodeProperty(Properties.FAN));

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
                debug.put(tENode, pos);
                tENode.setProperty(Properties.POSITION, pos++);
            }
        } else {
            
            // order each level of descendants of the inner nodes
            int size = inners.size();
            for (TNode tPNode : inners) {
                debug.put(tPNode, pos);
                tPNode.setProperty(Properties.POSITION, pos++);
                
                // set the position of the children and set them in order
                LinkedList<TNode> Children = tPNode.getChildrenCopy();
                orderLevel(Children, ++level, progressMonitor.subTask(1 / size));
                
                // order the children by their reverse position
                Collections.sort(Children,
                        Collections.reverseOrder(new SortTNodeProperty(Properties.POSITION)));

                // reset the list of children with the new order
                tPNode.setChildren(Children);

                // fill gaps with leaves
                it = leaves.listIterator(leaves.size());
                int fillGap = tPNode.getOutgoingEdges().size();
                notNull = true;
                while ((0 < fillGap) && notNull && it.hasPrevious()) {
                    TNode tNode = (TNode) it.previous();
                    if ((tNode.getProperty(Properties.FAN) == 0)) {
                        debug.put(tNode, pos);
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
