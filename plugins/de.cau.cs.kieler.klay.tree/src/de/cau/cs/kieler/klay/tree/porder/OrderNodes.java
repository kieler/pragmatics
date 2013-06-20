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
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.SortTNodeProperty;

/**
 * This phase put the nodes into an order, so that in each level the gaps between two nodes are
 * minimal in relation to the space needed for children. An
 * 
 * @author sor
 * @author sgu
 */
public class OrderNodes implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_2, EnumSet.of(
                    LayoutProcessorStrategy.ROOT_PROCESSOR,LayoutProcessorStrategy.FAN_PROCESSOR));

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final TGraph graph) {

        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor arrange node", 1);

        TNode root = null;
        LinkedList<TNode> roots = new LinkedList<TNode>();

        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }
        roots.add(root);
        arrangeLevel(roots, 0, progressMonitor.subTask(1.0f));

        progressMonitor.done();

    }

    private void arrangeLevel(LinkedList<TNode> currentLevel, int level,
            final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor arrange level", 1);

        int pos = 0;

        Collections.sort(currentLevel, new SortTNodeProperty(Properties.FAN));

        int lastOcc = currentLevel.size();

        ListIterator<TNode> it = currentLevel.listIterator(currentLevel.size());
        boolean notNull = true;
        while (notNull && it.hasPrevious()) {
            TNode tNode = (TNode) it.previous();
            if ((tNode.getProperty(Properties.FAN) == 0)) {
                lastOcc--;
            } else {
                notNull = false;
            }
        }

        List<TNode> tmp = currentLevel.subList(0, lastOcc);
        LinkedList<TNode> inners = new LinkedList<TNode>(tmp);
        tmp = currentLevel.subList(lastOcc, currentLevel.size());
        LinkedList<TNode> leaves = new LinkedList<TNode>(tmp);

        if (currentLevel.isEmpty()) {
            for (TNode tENode : leaves) {
                tENode.setProperty(Properties.POSITION, pos++);
            }
        } else {
            int size = inners.size();

            for (TNode tPNode : inners) {
                tPNode.setProperty(Properties.POSITION, pos++);
                arrangeLevel(tPNode.getChildren(), ++level, progressMonitor.subTask(1 / size));

                LinkedList<TNode> Children = tPNode.getChildren();

                Collections.sort(Children,
                        Collections.reverseOrder(new SortTNodeProperty(Properties.POSITION)));

                tPNode.setChildren(Children);

                it = leaves.listIterator(leaves.size());
                int fillGap = tPNode.getChildren().size();
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
