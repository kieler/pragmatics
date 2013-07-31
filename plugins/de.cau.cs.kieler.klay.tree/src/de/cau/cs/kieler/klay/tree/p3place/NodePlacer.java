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
package de.cau.cs.kieler.klay.tree.p3place;

import java.util.EnumSet;
import java.util.LinkedList;
import com.google.common.collect.Iterables;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.tree.ILayoutPhase;
import de.cau.cs.kieler.klay.tree.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.intermediate.LayoutProcessorStrategy;
import de.cau.cs.kieler.klay.tree.properties.Properties;
import de.cau.cs.kieler.klay.tree.util.FindNode;

/**
 * The algorithm comes from "A Node-Positioning Algorithm for General Trees, John Q.Walker II" with
 * some small fixes in the actual code.
 * 
 * This algorithm utilizes two concepts developed in previous positioning algorithms. First is the
 * concept of building subtrees as rigid units. When a node is moved, all of its descendants (if it
 * has any) are also moved--the entire subtree being thus treated as a rigid unit. A general tree is
 * positioned by building it up recursively from its leaves toward its root.
 * 
 * Second is the concept of using two fields for the positioning of each node. These two fields are:
 * 
 * • a preliminary x-coordinate • a modifier field.
 * 
 * Two tree traversals are used to produce the final x-coordinate of a node. The first traversal
 * assigns the preliminary x-coordinate and modifier fields for each node; the second traversal
 * computes the final x-coordinate of each node by summing the node's preliminary x-coordinate with
 * the modifier fields of all of its ancestors. The final y-coordinate of the node is the height of
 * the node's ancestors levels and the height nodes's level and the adjust of the root location.
 * 
 * @author sor
 * @author sgu
 * @author John Q. Walker II
 * 
 */
public class NodePlacer implements ILayoutPhase {

    private double spacing = 20f;

    /** Determine how to adjust all the nodes with respect to the location of the root. */
    private double xTopAdjustment = 0d;
    private double yTopAdjustment = 0d;

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(TGraph tGraph) {
        return INTERMEDIATE_PROCESSING_CONFIGURATION;
    }

    /**
     * intermediate processing configuration. The neighbors processor needs to run again right
     * before the phase to get the actual order
     */
    private static final IntermediateProcessingConfiguration INTERMEDIATE_PROCESSING_CONFIGURATION = new IntermediateProcessingConfiguration(
            null, EnumSet.of(LayoutProcessorStrategy.ROOT_PROC), EnumSet.of(
                    LayoutProcessorStrategy.LEVEL_HEIGHT, LayoutProcessorStrategy.NEIGHBORS_PROC),
            EnumSet.of(LayoutProcessorStrategy.NODE_POSITION_PROC));

    /**
     * {@inheritDoc}
     */
    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Processor order nodes", 1f);

        /** set the spacing according to the user inputs */
        spacing = tGraph.getProperty(Properties.SPACING);

        /** find the root node of this component */
        LinkedList<TNode> roots = new LinkedList<TNode>();
        for (TNode tNode : tGraph.getNodes()) {
            if (tNode.getProperty(Properties.ROOT))
                roots.add(tNode);
        }
        TNode root = roots.getFirst();

        /** Do the preliminary positioning with a postorder walk. */
        firstWalk(root, 0, progressMonitor.subTask(.5f));

        /** Do the final positioning with a preorder walk. */
        secondWalk(root, root.getProperty(Properties.LEVELHEIGHT) + yTopAdjustment, xTopAdjustment, progressMonitor.subTask(.5f));

        progressMonitor.done();
    }

    /**
     * In this first postorder walk, every node of the tree is assigned a preliminary x-coordinate
     * (held in property PRELIM). In addition, internal nodes are given modifiers, which will be
     * used to move their offspring to the right (held in property MODIFIER).
     * 
     * @param cN
     *            the root level of the tree
     * @param level
     *            the index of the passed level
     * @param progressMonitor
     *            the current progress monitor
     */
    private void firstWalk(TNode cN, int level, final IKielerProgressMonitor progressMonitor) {
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
                firstWalk(child, level + 1, progressMonitor.subTask(1f));
            }

            /**
             * Set the prelim and modifer for this node by determine the midpoint of its offsprings
             * and the middle node size of the node and its left sibling
             */
            TNode lM = Iterables.getFirst(cN.getChildren(), null);
            TNode rM = Iterables.getLast(cN.getChildren(), null);
            double midPoint = (rM.getProperty(Properties.PRELIM) + lM
                    .getProperty(Properties.PRELIM)) / 2f;

            if (lS != null) {
                /** This Node has a left sibling so its offsprings must be shifted to the right */
                double p = lS.getProperty(Properties.PRELIM) + spacing + meanNodeWidth(lS, cN);
                cN.setProperty(Properties.PRELIM, p);
                cN.setProperty(Properties.MODIFIER, cN.getProperty(Properties.PRELIM) - midPoint);
                /** shift the offsprings of this node to the right */
                apportion(cN, level);
            } else {
                /** No sibling on the left to worry about. */
                cN.setProperty(Properties.PRELIM, midPoint);
            }
        }
    }

    /**
     * This method cleans up the positioning of small sibling subtrees, thus fixing the
     * "left-to-right gluing" problem evident in earlier algorithms. When moving a new subtree
     * farther and farther to the right, gaps may open up among smaller subtrees that were
     * previously sandwiched between larger subtrees. Thus, when moving the new, larger subtree to
     * the right, the distance it is moved is also apportioned to smaller, interior subtrees,
     * creating a pleasing aesthetic placement.
     * 
     * @param cN
     *            the root of the subtree
     * @param level
     *            the level of the root in the global tree
     */
    private void apportion(TNode cN, int level) {
        /** Initialize the leftmost and neighbor corresponding to the root of the subtree */
        TNode leftmost = Iterables.getFirst(cN.getChildren(), null);
        TNode neighbor = leftmost != null ? leftmost.getProperty(Properties.LEFTNEIGHBOR) : null;
        int compareDepth = 1;
        /**
         * until this node and the neighbor to the left have nodes in the current level we have to
         * shift the current subtree
         */
        while (leftmost != null && neighbor != null) {
            /** Compute the location of Leftmost and where it should be with respect to Neighbor. */
            double leftModSum = 0;
            double rightModSum = 0;
            TNode ancestorLeftmost = leftmost;
            TNode ancestorNeighbor = neighbor;
            /** sum the modifiers of all ancestors according to the current level */
            for (int i = 0; i < compareDepth; i++) {
                ancestorLeftmost = ancestorLeftmost.getParent();
                ancestorNeighbor = ancestorNeighbor.getParent();
                rightModSum += ancestorLeftmost.getProperty(Properties.MODIFIER);
                leftModSum += ancestorNeighbor.getProperty(Properties.MODIFIER);
            }
            /**
             * Find the MoveDistance, and apply it to Node's subtree. Add appropriate portions to
             * smaller interior subtrees.
             */
            double prN = neighbor.getProperty(Properties.PRELIM);
            double prL = leftmost.getProperty(Properties.PRELIM);
            double mean = meanNodeWidth(leftmost, neighbor);
            double moveDistance = prN + leftModSum + spacing + mean - prL - rightModSum;

            if (0 < moveDistance) {
                /** Count interior sibling subtrees in LeftSiblings */
                TNode leftSibling = cN;
                int leftSiblings = 0;
                while (leftSibling != null && leftSibling != ancestorNeighbor) {
                    leftSiblings++;
                    leftSibling = leftSibling.getProperty(Properties.LEFTSIBLING);
                }
                /** Apply portions to appropriate leftsibling subtrees. */
                if (leftSibling != null) {
                    double portion = moveDistance / (double) leftSiblings;
                    leftSibling = cN;
                    while (leftSibling != ancestorNeighbor) {
                        double newPr = leftSibling.getProperty(Properties.PRELIM) + moveDistance;
                        leftSibling.setProperty(Properties.PRELIM, newPr);
                        double newMod = leftSibling.getProperty(Properties.MODIFIER) + moveDistance;
                        leftSibling.setProperty(Properties.MODIFIER, newMod);
                        moveDistance -= portion;
                        leftSibling = leftSibling.getProperty(Properties.LEFTSIBLING);
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
     * During a second preorder walk, each node is given a final x-coordinate by summing its
     * preliminary x-coordinate and the modifiers of all the node's ancestors. The y-coordinate
     * depends on the height of the node's ancestors levels. If the actual position of an interior
     * node is right of its preliminary place, the subtree rooted at the node must be moved right to
     * center the sons around the father. Rather than immediately readjust all the nodes in the
     * subtree, each node remembers the distance to the provisional place in a modifier field
     * (property MODIFIER). In this second pass down the tree, modifiers are accumulated and applied
     * to every node.
     * 
     * @param tNode
     *            the root of the tree
     * @param yCoor
     *            the y coordinate of previous level
     * @param modsum
     *            the modifiers of all the node's ancestors
     * @param progressMonitor
     *            the current progress monitor
     */
    private void secondWalk(TNode tNode, double yCoor, double modsum,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 1f);
        if (tNode != null) {
            /**
             * The x-position of the node is the sum of its prev x-coordinate and the modifiers of
             * all the node's ancestors and the adjust of the root location.
             */
            double xTemp = tNode.getProperty(Properties.PRELIM) + modsum;
            /**
             * The y-position of the node is the height of the node's ancestors levels and the
             * height nodes's level and the adjust of the root location.
             */
            double yTemp = yCoor + spacing + tNode.getProperty(Properties.LEVELHEIGHT);
            /**
             * We do not check to see that xTemp and yTemp are of the proper size, because the
             * kieler framework will take care of this.
             */
            tNode.setProperty(Properties.XCOOR, (int) Math.round(xTemp));
            tNode.setProperty(Properties.YCOOR, (int) Math.round(yTemp));
            /** Apply the modifier value for this node to all its offspring. */
            if (!tNode.isLeaf()) {
                /** This node got offsprings so we will step a level down and take care of them. */
                secondWalk(Iterables.getFirst(tNode.getChildren(), null), yTemp,
                        modsum + tNode.getProperty(Properties.MODIFIER),
                        progressMonitor.subTask(1f));
            }
            /**
             * Go ahead with the sibling to the right. This is a dfs so we just layout the current
             * subtree.
             */
            if (tNode.getProperty(Properties.RIGHTSIBLING) != null) {
                secondWalk(tNode.getProperty(Properties.RIGHTSIBLING), yCoor, modsum,
                        progressMonitor.subTask(1f));
            }
        }
        progressMonitor.done();
    }
}
