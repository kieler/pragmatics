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
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FindNode;
import de.cau.cs.kieler.klay.tree.util.SortTNodeProperty;

/**
 * This phase orders the nodes of each level by seperating the nodes into leaves and inner nodes.
 * And then fill whitespace in the levels with corresponding leaves.
 * 
 * @author sor
 * @author sgu
 */
public class OrderBalance implements ILayoutPhase {

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            IntermediateProcessingConfiguration.BEFORE_PHASE_2, EnumSet.of(
                    LayoutProcessorStrategy.ROOT_PROC, LayoutProcessorStrategy.NEIGHBORS_PROC));

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

        TNode lM = FindNode.getLeftMost(currentLevel);
        
        TNode parent= lM.getParent().getParent();
        
        
        List<TEdge> childBalanced = new LinkedList<TEdge>();
        
        for (TNode child : parent.getChildren()) {
            
        }
 
        
        // reset the list of children with the new order
        List<TEdge> sortedOutEdges = new LinkedList<TEdge>();

        for (TNode child : parent.getChildren()) {
            for (TEdge tEdge : parent.getOutgoingEdges()) {
                if (tEdge.getTarget() == child) {
                    sortedOutEdges.add(tEdge);
                }
            }
        }
        parent.getOutgoingEdges().clear();
        parent.getOutgoingEdges().addAll(sortedOutEdges);

        
        progressMonitor.done();
    }
}
