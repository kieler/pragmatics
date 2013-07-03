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
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FindNode;
import de.cau.cs.kieler.klay.tree.util.SortTEdgeTargetProperty;

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
                    LayoutProcessorStrategy.ROOT_PROC, LayoutProcessorStrategy.FAN_PROC,
                    LayoutProcessorStrategy.NEIGHBORS_PROC));

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
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }
        // order each level

        TNode lM = FindNode.getLeftMost(root.getChildren());

        if (lM.getParent() != null && lM.getParent() != root) {
            TNode parent = lM.getParent().getParent();
            TNode leftMost = parent;
            while (leftMost.getProperty(Properties.LEFTNEIGHBOR) != null) {
                leftMost = leftMost.getProperty(Properties.LEFTNEIGHBOR);
            }
            orderLevel(leftMost);
        }

        for (TNode tNode : tGraph.getNodes()) {
            tNode.setProperty(Properties.RIGHTNEIGHBOR, null);
            tNode.setProperty(Properties.LEFTNEIGHBOR, null);
            tNode.setProperty(Properties.RIGHTSIBLING, null);
            tNode.setProperty(Properties.LEFTSIBLING, null);
        }
        
     // TODO DEBUG
//        System.out.println();
//        System.out.println();
//        progressMonitor.done();

    }

    /**
     * Order each level by seperating the nodes into leaves and inner nodes. And then fill gaps with
     * corresponding leaves.
     * 
     * @param currentLevel
     * @param level
     */
    private void orderLevel(TNode leftMost) {
        if (leftMost != null) {

            TNode currentNode = leftMost;

            while (currentNode != null) {
                // sort all nodes in this level by their fan out
                // so the leaves are at the end of the list
                List<TEdge> outgoing = currentNode.getOutgoingEdges();

                Collections.sort(outgoing, new SortTEdgeTargetProperty(Properties.FAN));

                List<TEdge> balanced = new LinkedList<TEdge>();

                boolean innerOdd = false;
                while (!outgoing.isEmpty()) {
                    int gaps = outgoing.get(0).getTarget().getProperty(Properties.FAN);
                    int index;

                    if (innerOdd) {
                        index = balanced.size();
                        balanced.add(outgoing.get(0));
                    } else {
                        index = 0;
                        balanced.add(index, outgoing.get(0));
                    }
                    outgoing.remove(0);
                    innerOdd = !innerOdd;

                    int indexEnd = outgoing.size();
                    boolean leavesOdd = false;
                    while (0 < gaps && 0 < indexEnd) {
                        indexEnd--;
                        if (outgoing.get(indexEnd).getTarget().getProperty(Properties.FAN) == 0) {
                            gaps--;
                            if (leavesOdd) {
                                balanced.add(outgoing.get(indexEnd));
                            } else {
                                balanced.add(index, outgoing.get(indexEnd));
                            }
                            outgoing.remove(indexEnd);
                            leavesOdd = !leavesOdd;
                        } else {
                            gaps = 0;
                        }
                    }
                }

                // reset the list of children with the new order
                
                // TODO DEBUG
//                System.out.print("\n"+currentNode.getLabel()+":");
//                for (TEdge tEdge : balanced) {
//                    System.out.print(" "+tEdge.getTarget().getLabel());
//                }
                
                currentNode.getOutgoingEdges().addAll(balanced);

                currentNode = currentNode.getProperty(Properties.RIGHTNEIGHBOR);
            }

            orderLevel(leftMost.getParent());
        }

    }
}
