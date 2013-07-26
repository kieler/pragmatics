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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TEdge;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.OrderWeighting;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FindNode;
import de.cau.cs.kieler.klay.tree.util.SortTEdgeTargetProperty;

/**
 * This phase orders the nodes of each level by separating the children of nodes into leaves and
 * inner nodes. It then fill whitespaces in the levels with corresponding leaves.
 * 
 * It starts two levels above the deepest level, because the deepest level contains only nodes and
 * therefore no reordering is necessary. And the level above the deepest level contains only
 * children of the level above, which are ordered by the their parents.
 * 
 * @author sor
 * @author sgu
 */
public class OrderBalance implements ILayoutPhase {

    /**
     * Tells the node order which weighting it should use.
     */
    IProperty<Integer> weighting;

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

        /** get the weighting from the userinterface */
        if (tGraph.getProperty(Properties.WEIGHTING).equals(OrderWeighting.DESCENDANTS)) {
            weighting = Properties.DESCENDANTS;
        } else {
            weighting = Properties.FAN;
        }

        /** find the root of the component expected only one root exists */
        TNode root = null;
        Iterator<TNode> it = tGraph.getNodes().iterator();
        while (root == null && it.hasNext()) {
            TNode tNode = it.next();
            if (tNode.getProperty(Properties.ROOT)) {
                root = tNode;
            }
        }

        /** check if a root is there */
        if (root != null) {

            /** start two levels above the deepest level at the leftmost node */
            TNode lM = FindNode.getLeftMost(root.getChildren());

            /** if there are only the root and one level or less no reordering is necessary */
            if (lM != null && lM.getParent() != root) {
                TNode parent = lM.getParent().getParent();
                TNode leftMost = parent;
                /** go to the leftmost node in this level */
                while (leftMost.getProperty(Properties.LEFTNEIGHBOR) != null) {
                    leftMost = leftMost.getProperty(Properties.LEFTNEIGHBOR);
                }
                /** start the order at the leftmost node */
                orderLevel(leftMost, false);

                /**
                 * reset the structure properties of each node to null, because the order of the
                 * graph has changed
                 */
                for (TNode tNode : tGraph.getNodes()) {
                    tNode.setProperty(Properties.RIGHTNEIGHBOR, null);
                    tNode.setProperty(Properties.LEFTNEIGHBOR, null);
                    tNode.setProperty(Properties.RIGHTSIBLING, null);
                    tNode.setProperty(Properties.LEFTSIBLING, null);
                }
            }
        }

    }

    /**
     * Order each level by seperating the children of the nodes into leaves and inner nodes. And
     * then fill gaps with corresponding leaves.
     * 
     * @param leftMost
     *            the leftmost node in a level
     */
    private void orderLevel(TNode leftMost, boolean odd) {
        if (leftMost != null) {

            /** copy current to iterate over the copy */
            TNode currentNode = leftMost;

            while (currentNode != null) {
                /**
                 * sort all children of this node by their fan out so the leaves are at the end of
                 * the list
                 */
                List<TEdge> outgoing = currentNode.getOutgoingEdges();

                Collections.sort(outgoing, new SortTEdgeTargetProperty(weighting));

                /**
                 * Add each child to a balanced list where the fat child are in the middle and the
                 * thin child are at the borders. Leaves fill the places between the inner child,
                 * also starting at fattest node in the middle.
                 * 
                 * eg. bigger number means fatter nodes, zero means leaf
                 * 
                 * unbalanced: 0 1 0 0 4 0 0 9 0 7 2 0 3 0 0 2 3 6 0 0 
                 * balanced  : 2 3 4 6 0 0 0 0 0 9 0 0 0 0 0 7 0 3 2 1
                 */
                List<TEdge> balanced = new LinkedList<TEdge>();

                boolean innerOdd = odd;
                while (!outgoing.isEmpty()) {
                    int gaps = outgoing.get(0).getTarget().getProperty(weighting);
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
                    boolean leavesOdd = odd;
                    while (0 < gaps && 0 < indexEnd) {
                        indexEnd--;
                        if (outgoing.get(indexEnd).getTarget().isLeaf()) {
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

                /** reset the list of children with the new order */
                currentNode.getOutgoingEdges().addAll(balanced);

                /** go on with the next node to the right */
                currentNode = currentNode.getProperty(Properties.RIGHTNEIGHBOR);
            }
            /** this level has been ordered, go on with the next level above */
            orderLevel(leftMost.getParent(), !odd);
        }
    }
}
