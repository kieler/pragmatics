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
package de.cau.cs.kieler.klay.tree.pplacing;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * @author sor
 * @author sgu
 * 
 */
public class NodePlacer implements ILayoutPhase {

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

    private final TreeMap<TNode, Double> prelim = new TreeMap<TNode, Double>(comparator);
    private final TreeMap<TNode, Double> modifier = new TreeMap<TNode, Double>(comparator);
    private double spacing = 20f;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, EnumSet.of(LayoutProcessorStrategy.ROOT_PROC),
            EnumSet.of(LayoutProcessorStrategy.NEIGHBORS_PROC),
            EnumSet.of(LayoutProcessorStrategy.COORDINATE_PROC));

    // recursive method, that adds modifier of ancestors to nodes
    public void secondWalk(TNode tNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 3);
        if (!tNode.isLeaf()) {
            double xCoor = prelim.get(tNode);
            for (TNode tmp : tNode.getChildren()) {
                secondWalk(tmp, progressMonitor.subTask(3.0f));
                xCoor += modifier.get(tmp);
            }
            tNode.setProperty(Properties.XCOOR, (int) Math.round(xCoor));
        }
        progressMonitor.done();
    }

    /**
     * This function returns the mean width of the two passed nodes. It adds the width of the right
     * half of lefthand node to the left half of righthand node. If all nodes are the same width,
     * this is a trivial calculation.
     * 
     * @param leftNode
     *            the lefthand node
     * @param rightNode
     *            the rightthand node
     * @return the sum of the width
     */
    private double meanNodeWidth(TNode leftNode, TNode rightNode) {
        double nodeWidth = 0d;
        if (leftNode != null) {
            nodeWidth += leftNode.getSize().x / 2d;
        }
        if (rightNode != null) {
            nodeWidth += rightNode.getSize().x / 2d;
        }
        return nodeWidth;
    }

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor order nodes", 5f);

        prelim.clear();
        modifier.clear();

        spacing = tGraph.getProperty(Properties.SPACING);

        LinkedList<TNode> roots = new LinkedList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }

        TNode root = roots.getFirst();

        firstWalk(root, progressMonitor.subTask(2f));

        Set<Entry<TNode, Double>> modSet = modifier.entrySet();
        for (Entry<TNode, Double> entry : modSet) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("first walk");

        Set<Entry<TNode, Double>> prelimSet = prelim.entrySet();

        for (Entry<TNode, Double> entry : prelimSet) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        secondWalk(root, progressMonitor.subTask(3f));

        System.out.println("secodn walk");

        for (Entry<TNode, Double> entry : prelimSet) {
            System.out
                    .println(entry.getKey() + ": " + entry.getKey().getProperty(Properties.XCOOR));
        }

        // for (Entry<TNode, Double> entry : prelimSet) {
        // KVector pos = entry.getKey().getPosition();
        // pos.x = entry.getValue();
        // }
        progressMonitor.done();

    }

    public void firstWalk(TNode cN, final IKielerProgressMonitor progressMonitor) {
        modifier.put(cN, 0d);
        TNode lN = cN.getLeftNeighbour();

        if (cN.isLeaf()) {
            if (lN != null) {
                /**
                 * Determine the preliminary x-coordinate based on: the preliminary x-coordinate of
                 * the left sibling, the separation between sibling nodes, and tHe mean size of left
                 * sibling and current node.
                 */
                double p = prelim.get(lN) + spacing + meanNodeWidth(lN, cN);
                prelim.put(cN, p);
            } else {
                prelim.put(cN, 0d);
            }
        } else {
            /**
             * This Node is not a leaf, so call this procedure recursively for each of its
             * offspring.
             */
            for (TNode child : cN.getChildren()) {
                firstWalk(child, progressMonitor.subTask(2.0f));
            }

            TNode lM = Iterables.getFirst(cN.getChildren(), null);
            TNode rM = Iterables.getLast(cN.getChildren(), null);
            double midPoint = (prelim.get(rM) + prelim.get(lM)) / 2;

            if (lN != null) {
                double p = prelim.get(lN) + spacing + meanNodeWidth(lN, cN);
                prelim.put(cN, p);
                modifier.put(cN, prelim.get(cN) - midPoint);
            } else {
                prelim.put(cN, midPoint);
            }
        }
    }

    public void apportion(TNode cN, int level, final IKielerProgressMonitor progressMonitor) {
        TNode leftmost = Iterables.getFirst(cN.getChildren(), null);
        TNode neighbor = leftmost != null ? leftmost.getLeftNeighbour() : null;
        int compareDepth = 1;
        while (leftmost != null && neighbor != null) {
            /** Compute the location of Leftmost and where it should be with respect to Neighbor. */
            double leftModSum = 0;
            double rightModSum = 0;
            TNode ancestorLeftmost = leftmost;
            TNode ancestorNeighbor = neighbor;
            for (int i = 0; i < compareDepth; i++) {
                ancestorLeftmost = ancestorLeftmost.getParent();
                ancestorNeighbor = ancestorNeighbor.getParent();
                rightModSum = rightModSum + modifier.get(ancestorLeftmost);
                leftModSum = leftModSum + modifier.get(ancestorNeighbor);
            }
            /**
             * (* Find the floveDistance, and apply it to Node's subtree. Add appropriate portions
             * to smaller interior subtrees.
             */
            double moveDistance = prelim.get(neighbor) + leftModSum + spacing
                    + meanNodeWidth(leftmost, neighbor) - prelim.get(leftmost) - rightModSum;

            if (moveDistance > 0) {
                /** Count interior sibling subtrees in LeftSiblings */
                TNode tempPtr = cN;
                int leftSiblings = 0;
                while (tempPtr != null && ancestorNeighbor != null) {
                    leftSiblings++;
                    tempPtr = tempPtr.getLeftNeighbour();
                }
                /** Apply portions to appropriate leftsibling subtrees. */
                if (tempPtr != null) {
                    double portion = moveDistance / leftSiblings;
                    tempPtr = cN;
                    while (tempPtr == ancestorNeighbor) {
                        prelim.put(tempPtr, prelim.get(tempPtr) + moveDistance);
                        modifier.put(tempPtr, modifier.get(tempPtr) + moveDistance);
                        tempPtr = tempPtr.getLeftNeighbour();
                    }
                } else {
                    /**
                     * Don't need to move anything--it needs to be done by an ancestor because
                     * AncestorNeighbor and AncestorLeftmost are not siblings of each other.
                     */
                    return;
                }
            }
            /**
             * Determine the leftmost descendant of Node at the next lower level to compare its
             * positioning against that of its Neighbor.
             */
        }
        compareDepth++;
        if (leftmost.isLeaf()) {
            // TODO Leftmost <- GETLEFTMOST(Node, 0, CompareDepth);
            leftmost = null;
        } else {
            leftmost = Iterables.getFirst(leftmost.getChildren(), null);
        }
    }

}
