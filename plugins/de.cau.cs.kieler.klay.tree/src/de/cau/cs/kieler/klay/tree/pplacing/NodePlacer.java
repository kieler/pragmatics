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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
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
            EnumSet.of(LayoutProcessorStrategy.NEIGHBORS_PROC),
            EnumSet.of(LayoutProcessorStrategy.COORDINATE_PROC));

    /**
     * @param tNode
     * @param level
     * @param modsum
     * @param progressMonitor
     */
    public void secondWalk(TNode tNode, int level, double modsum,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Processor place nodes - second walk", 1f);
        if (tNode != null) {
            double xTemp = xTopAdjustment + prelim.get(tNode) + modsum;
            double yTemp = yTopAdjustment + level * spacing;
            /** Check to see that xTemp and yTemp are of the proper size for your application. */
            if (!(xTemp < 0) && !(yTemp < 0)) {
                tNode.setProperty(Properties.XCOOR, (int) Math.round(xTemp));
                tNode.setProperty(Properties.YCOOR, (int) Math.round(yTemp));
                /** Apply the modifier value for this node to all its offspring. */
                if (!tNode.isLeaf()) {
                    secondWalk(Iterables.getFirst(tNode.getChildren(), null), level + 1, modsum
                            + modifier.get(tNode), progressMonitor.subTask(1f));
                }
                if (tNode.getProperty(Properties.RIGHTSIBLING) != null) {
                    secondWalk(tNode.getProperty(Properties.RIGHTSIBLING), level, modsum,
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

        prelim.clear();
        modifier.clear();

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

        secondWalk(root, 0, 0d, progressMonitor.subTask(1f));
        
        progressMonitor.done();

    }

    public void firstWalk(TNode cN, int level, final IKielerProgressMonitor progressMonitor) {
        modifier.put(cN, 0d);
        TNode lS = cN.getProperty(Properties.LEFTSIBLING);

        if (cN.isLeaf()) {
            if (lS != null) {
                /**
                 * Determine the preliminary x-coordinate based on: the preliminary x-coordinate of
                 * the left sibling, the separation between sibling nodes, and tHe mean size of left
                 * sibling and current node.
                 */
                double p = prelim.get(lS) + spacing + meanNodeWidth(lS, cN);
                prelim.put(cN, p);
            } else {
                /** No sibling on the left to worry about. */
                prelim.put(cN, 0d);
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
            double midPoint = (prelim.get(rM) + prelim.get(lM)) / 2;

            if (lS != null) {
                double p = prelim.get(lS) + spacing + meanNodeWidth(lS, cN);
                prelim.put(cN, p);
                modifier.put(cN, prelim.get(cN) - midPoint);
                apportion(cN, level);
            } else {
                prelim.put(cN, midPoint);
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
                rightModSum += modifier.get(ancestorLeftmost);
                leftModSum += modifier.get(ancestorNeighbor);
            }
            /**
             * (* Find the MoveDistance, and apply it to Node's subtree. Add appropriate portions to
             * smaller interior subtrees.
             */
            double prN = prelim.get(neighbor);
            double prL = prelim.get(leftmost);
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
                        prelim.put(tempPtr, prelim.get(tempPtr) + moveDistance);
                        modifier.put(tempPtr, modifier.get(tempPtr) + moveDistance);
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
                leftmost = getLeftMost(cN.getChildren(), compareDepth);
            } else {
                leftmost = Iterables.getFirst(leftmost.getChildren(), null);
            }
            neighbor = leftmost != null ? leftmost.getProperty(Properties.LEFTNEIGHBOR) : null;
        }
    }

    /**
     * This method returns the leftmost node at the given level. This is implemented using a
     * postorder walk of the subtree under given level, depth levels down. Depth here refers to the
     * level below where the leftmost descendant is being found.
     * 
     * @param currentlevel
     *            a list of nodes at level one
     * @param depth
     *            the depth to search for
     * @return the leftmost descendant at depth levels down
     */
    private TNode getLeftMost(final Iterable<TNode> currentlevel, int depth) {

        if (1 < depth) {
            depth--;
            // build empty iterator
            Iterable<TNode> nextLevel = new Iterable<TNode>() {

                public Iterator<TNode> iterator() {
                    return Iterators.emptyIterator();
                }
            };

            for (TNode cN : currentlevel) {
                // append the children of the current node to the next level
                nextLevel = Iterables.concat(nextLevel, cN.getChildren());
            }

            return getLeftMost(nextLevel, depth);
        }
        return Iterables.getFirst(currentlevel, null);
    }

}
