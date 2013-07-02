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

import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FindNode;

/**
 * @author sor
 * @author sgu
 * 
 */
public class NodePlacer implements ILayoutPhase {

    private double spacing = 20f;

    private double xTopAdjustment;
    private double yTopAdjustment;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    /** intermediate processing configuration. */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, EnumSet.of(LayoutProcessorStrategy.ROOT_PROC),
            EnumSet.of(LayoutProcessorStrategy.LEVEL_HEIGHT,LayoutProcessorStrategy.NEIGHBORS_PROC),
            EnumSet.of(LayoutProcessorStrategy.COORDINATE_PROC));

    /**
     * @param tNode
     * @param yCoor
     * @param modsum
     * @param progressMonitor
     */
    public void secondWalk(TNode tNode, double yCoor, double modsum,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 1f);
        if (tNode != null) {
            double xTemp = xTopAdjustment + tNode.getProperty(Properties.PRELIM) + modsum;
            double yTemp = yCoor + spacing + tNode.getProperty(Properties.LEVELHEIGHT);
            /** Check to see that xTemp and yTemp are of the proper size for your application. */
            if (!(xTemp < 0) && !(yTemp < 0)) {
                tNode.setProperty(Properties.XCOOR, (int) Math.round(xTemp));
                tNode.setProperty(Properties.YCOOR, (int) Math.round(yTemp));
                /** Apply the modifier value for this node to all its offspring. */
                if (!tNode.isLeaf()) {
                    secondWalk(Iterables.getFirst(tNode.getChildren(), null), yTemp,
                            modsum + tNode.getProperty(Properties.MODIFIER),
                            progressMonitor.subTask(1f));
                }
                if (tNode.getProperty(Properties.RIGHTSIBLING) != null) {
                    secondWalk(tNode.getProperty(Properties.RIGHTSIBLING), yCoor, modsum,
                            progressMonitor.subTask(1f));
                }
            }
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

        progressMonitor.begin("Processor order nodes", 1f);

        spacing = tGraph.getProperty(Properties.SPACING);

        LinkedList<TNode> roots = new LinkedList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }

        TNode root = roots.getFirst();

        /** Do the preliminary positioning with a postorder walk. */
        firstWalk(root, 0, progressMonitor.subTask(0f));

        /** Determine how to adjust all the nodes with respect to the location of the root. */
        // TODO adjust root position
        root.getPosition().x = 0d;
        root.getPosition().y = 0d;
        xTopAdjustment = root.getPosition().x;
        yTopAdjustment = root.getPosition().y;

        secondWalk(root, root.getProperty(Properties.LEVELHEIGHT), 0d, progressMonitor.subTask(1f));

        progressMonitor.done();

    }

    public void firstWalk(TNode cN, int level, final IKielerProgressMonitor progressMonitor) {
        cN.setProperty(Properties.MODIFIER, 0d);
        TNode lS = cN.getProperty(Properties.LEFTSIBLING);

        if (cN.isLeaf()) {
            if (lS != null) {
                /**
                 * Determine the preliminary x-coordinate based on: the preliminary x-coordinate of
                 * the left sibling, the separation between sibling nodes, and tHe mean size of left
                 * sibling and current node.
                 */
                double p = lS.getProperty(Properties.PRELIM) + spacing + meanNodeWidth(lS, cN);
                cN.setProperty(Properties.PRELIM, p);
            } else {
                /** No sibling on the left to worry about. */
                cN.setProperty(Properties.PRELIM, 0d);
            }
        } else {
            /**
             * This Node is not a leaf, so call this procedure recursively for each of its
             * offspring.
             */
            for (TNode child : cN.getChildren()) {
                // TODO work units
                firstWalk(child, level + 1, progressMonitor.subTask(1f));
            }

            TNode lM = Iterables.getFirst(cN.getChildren(), null);
            TNode rM = Iterables.getLast(cN.getChildren(), null);
            double midPoint = (rM.getProperty(Properties.PRELIM) + lM
                    .getProperty(Properties.PRELIM)) / 2;

            if (lS != null) {
                double p = lS.getProperty(Properties.PRELIM) + spacing + meanNodeWidth(lS, cN);
                cN.setProperty(Properties.PRELIM, p);
                cN.setProperty(Properties.MODIFIER, cN.getProperty(Properties.PRELIM) - midPoint);
                apportion(cN, level);
            } else {
                cN.setProperty(Properties.PRELIM, midPoint);
            }
        }
    }

    public void apportion(TNode cN, int level) {
        TNode leftmost = Iterables.getFirst(cN.getChildren(), null);
        TNode neighbor = leftmost != null ? leftmost.getProperty(Properties.LEFTNEIGHBOR) : null;
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
                rightModSum += ancestorLeftmost.getProperty(Properties.MODIFIER);
                leftModSum += ancestorNeighbor.getProperty(Properties.MODIFIER);
            }
            /**
             * (* Find the MoveDistance, and apply it to Node's subtree. Add appropriate portions to
             * smaller interior subtrees.
             */
            double prN = neighbor.getProperty(Properties.PRELIM);
            double prL = leftmost.getProperty(Properties.PRELIM);
            double mean = meanNodeWidth(leftmost, neighbor);

            double moveDistance = prN + leftModSum + spacing + mean - prL - rightModSum;

            if (moveDistance > 0) {
                /** Count interior sibling subtrees in LeftSiblings */
                TNode tempPtr = cN;
                int leftSiblings = 0;
                while (tempPtr != null && tempPtr != ancestorNeighbor) {
                    leftSiblings++;
                    tempPtr = tempPtr.getProperty(Properties.LEFTSIBLING);
                }
                /** Apply portions to appropriate leftsibling subtrees. */
                if (tempPtr != null) {
                    double portion = moveDistance / leftSiblings;
                    tempPtr = cN;
                    while (tempPtr != ancestorNeighbor) {
                        tempPtr.setProperty(Properties.PRELIM,
                                tempPtr.getProperty(Properties.PRELIM) + moveDistance);
                        tempPtr.setProperty(Properties.MODIFIER,
                                tempPtr.getProperty(Properties.MODIFIER) + moveDistance);
                        moveDistance -= portion;
                        tempPtr = tempPtr.getProperty(Properties.LEFTSIBLING);
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
            compareDepth++;
            if (leftmost.isLeaf()) {
                leftmost = FindNode.getLeftMost(cN.getChildren(), compareDepth);
            } else {
                leftmost = Iterables.getFirst(leftmost.getChildren(), null);
            }
            neighbor = leftmost != null ? leftmost.getProperty(Properties.LEFTNEIGHBOR) : null;
        }
    }
}
