/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.slimgraph.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KGraphSection;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.core.util.ConcatenableList;

/**
 * Implementation of the Hopcroft & Tarjan planarity test.
 * <p>
 * <b>WARNING:</b> This implementation was not tested yet.
 * 
 * @author msp
 */
public class HopcroftTarjanPlanarityTester extends AbstractAlgorithm
        implements IPlanarityTester {

    /** rank value used for tree edges. */
    private static final int TREE_EDGE = 1;
    /** rank value used for back edges. */
    private static final int BACK_EDGE = 0;

    /** the biconnected section that is being processed. */
    private KGraphSection biconnectedSection;
    /** the next DFS number that is assigned. */
    private int nextDfsnum = 0;
    /** the lowest point values. */
    private int[] lowpt;
    /** the second lowest point values. */
    private int[] lowpt2;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
        nextDfsnum = 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPlanar(final KGraphSection thebiconnectedSection) {
        int sectionSize = thebiconnectedSection.getNodes().size();
        this.biconnectedSection = thebiconnectedSection;
        this.lowpt = new int[sectionSize];
        this.lowpt2 = new int[sectionSize];

        // initialize DFS numbers of each node
        for (KSlimNode node : thebiconnectedSection.getNodes()) {
            node.setRank(-1);
        }
        // perform DFS on the biconnected section
        KSlimNode node0 = thebiconnectedSection.getNodes().get(0);
        int edgeCount = dfsVisit(node0);

        // CHECKSTYLEOFF MagicNumber
        // check number of edges: if the graph is planar, then m <= 3*n - 6
        if (edgeCount > 3 * thebiconnectedSection.getNodes().size() - 6) {
            return false;
        }
        // CHECKSTYLEON MagicNumber

        // reorder all edges of the biconnected section according to lowpt
        // values
        reorderEdges();

        // the first edge of the first DFS node is used as start for
        // the recursive subroutine
        KSlimEdge edge0 = node0.getIncidence().get(0).getEdge();
        List<KSlimNode> attachments = stronglyPlanar(edge0, node0);

        return attachments != null;
    }

    /**
     * Performs a DFS starting with the given node. Each edge is declared as
     * tree edge or back edge.
     * 
     * @param node node to processed
     * @return number of edges found in the subgraph starting at {@code node}
     */
    private int dfsVisit(final KSlimNode node) {
        int edgeCount = 0;
        node.setRank(nextDfsnum++);
        lowpt[node.getRank()] = node.getRank();
        lowpt2[node.getRank()] = node.getRank();
        List<KSlimNode.IncEntry> edgesToRemove = null;
        for (KSlimNode.IncEntry edgeEntry : node.getIncidence()) {
            KSlimNode endpoint = edgeEntry.endpoint();
            if (biconnectedSection.contains(endpoint)) {
                if (endpoint.getRank() < 0) {
                    edgeEntry.getEdge().setRank(TREE_EDGE);
                    edgeCount = dfsVisit(endpoint) + 1;
                    if (lowpt[endpoint.getRank()] < lowpt[node.getRank()]) {
                        lowpt2[node.getRank()] = lowpt[node.getRank()];
                        lowpt[node.getRank()] = lowpt[endpoint.getRank()];
                    }
                } else if (endpoint.getRank() >= node.getRank()) {
                    edgeEntry.getEdge().setRank(BACK_EDGE);
                    edgeCount++;
                    if (node.getRank() < lowpt[endpoint.getRank()]) {
                        lowpt2[endpoint.getRank()] = lowpt[endpoint.getRank()];
                        lowpt[endpoint.getRank()] = node.getRank();
                    }
                }
            } else {
                if (edgesToRemove == null) {
                    edgesToRemove = new LinkedList<KSlimNode.IncEntry>();
                }
                edgesToRemove.add(edgeEntry);
            }
        }
        // remove marked edges
        if (edgesToRemove != null) {
            for (KSlimNode.IncEntry edgeEntry : edgesToRemove) {
                biconnectedSection.removeEdge(edgeEntry);
            }
        }
        return edgeCount;
    }

    /**
     * Reorders all edges of the biconnected section according to some rules on
     * the <code>lowpt</code> and <code>lowpt2</code> values.
     */
    private void reorderEdges() {
        for (final KSlimNode node : biconnectedSection.getNodes()) {
            Collections.sort(node.getIncidence(), new Comparator<KSlimNode.IncEntry>() {
                public int compare(final KSlimNode.IncEntry edge1, final KSlimNode.IncEntry edge2) {
                    int value1 = value(edge1);
                    int value2 = value(edge2);
                    return value1 > value2 ? 1 : (value1 < value2 ? -1 : 0);
                }

                private int value(final KSlimNode.IncEntry edgeEntry) {
                    KSlimNode endpoint = edgeEntry.endpoint();
                    if (edgeEntry.getEdge().getRank() == TREE_EDGE) {
                        if (node.getRank() < endpoint.getRank()) {
                            if (lowpt2[endpoint.getRank()] >= node.getRank()) {
                                return 2 * lowpt[endpoint.getRank()];
                            } else {
                                return 2 * lowpt[endpoint.getRank()] + 1;
                            }
                        } else {
                            return Integer.MAX_VALUE;
                        }
                    } else {
                        assert edgeEntry.getEdge().getRank() == BACK_EDGE;
                        if (node.getRank() >= endpoint.getRank()) {
                            return 2 * endpoint.getRank();
                        } else {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
            });
        }
    }

    /**
     * Object representing a connected component of the interlacing graph
     * associated with the current step in the algorithm.
     */
    private static final class InterlacingBlock {
        private ConcatenableList<KSlimNode> left, right;

        private InterlacingBlock(final ConcatenableList<KSlimNode> theleft,
                final ConcatenableList<KSlimNode> theright) {
            this.left = theleft;
            this.right = theright;
        }
    }

    /**
     * Checks whether the segment S(edge0) is strongly planar. This algorithm is
     * taken from chapter 4 of
     * <p>
     * K. Mehlhorn, <i>Data Structures and Efficient Algorithms</i>, Springer
     * Verlag, EATCS Monographs, 1984
     * 
     * @param edge0 the edge from which the next cycle is constructed
     * @param x0 the first endpoint of the edge <code>edge0 = (x0, y0)</code>
     * @return ordered list of attachments to the new cycle, or null if the
     *         current segment is not strongly planar
     */
    private ConcatenableList<KSlimNode> stronglyPlanar(final KSlimEdge edge0,
            final KSlimNode x0) {
        KSlimNode y0;
        if (edge0.getSource().getId() == x0.getId()) {
            y0 = edge0.getTarget();
        } else {
            assert edge0.getTarget().getId() == x0.getId();
            y0 = edge0.getSource();
        }
        // construct the spine of a cycle that starts at (x0, y0)
        LinkedList<KSlimNode> spine = buildSpine(x0, y0);
        spine.addFirst(x0);

        Stack<InterlacingBlock> blockStack = new Stack<InterlacingBlock>();
        ListIterator<KSlimNode> spineIter = spine.listIterator(spine.size());
        while (spineIter.previousIndex() > 0) {
            KSlimNode spineNode = spineIter.previous();
            ListIterator<KSlimNode.IncEntry> edgeIter = spineNode.getIncidence().listIterator(1);
            while (edgeIter.hasNext()) {
                KSlimNode.IncEntry emanatingEdge = edgeIter.next();
                KSlimNode nextNode = emanatingEdge.endpoint();
                // check whether the current edge is taken in the proper
                // direction
                if (emanatingEdge.getEdge().getRank() == TREE_EDGE
                        && nextNode.getRank() > spineNode.getRank()
                        || emanatingEdge.getEdge().getRank() == BACK_EDGE
                        && nextNode.getRank() <= spineNode.getRank()) {
                    // recursive check of strong planarity
                    ConcatenableList<KSlimNode> attachments = stronglyPlanar(emanatingEdge.getEdge(),
                            spineNode);
                    if (attachments == null) {
                        return null;
                    }
                    // update the stack of interlacing blocks
                    int lowpte;
                    if (emanatingEdge.getEdge().getRank() == BACK_EDGE) {
                        lowpte = nextNode.getRank();
                    } else {
                        lowpte = lowpt[nextNode.getRank()];
                    }
                    attachments.remove(spineNode);
                    boolean nonPlanar = updateBlockStack(blockStack, attachments, lowpte);
                    if (nonPlanar) {
                        return null;
                    }
                } else {
                    // all non-proper edges were put to the end of the incidence list
                    break;
                }
            }
            KSlimNode previousNode = spineIter.previous();
            while (!blockStack.isEmpty()) {
                InterlacingBlock block = blockStack.peek();
                int leftMax = block.left.isEmpty() ? -1 : block.left.getLast().getRank();
                int rightMax = block.right.isEmpty() ? -1 : block.right.getLast().getRank();
                if (Math.max(leftMax, rightMax) != previousNode.getRank()) {
                    break;
                }
                block.left.removeLast();
                block.right.removeLast();
                if (block.left.isEmpty() && block.right.isEmpty()) {
                    blockStack.pop();
                }
            }
            spineIter.next();
        }

        // compute list of attachments for the given edge
        ConcatenableList<KSlimNode> attachments = new ConcatenableList<KSlimNode>();
        int w1 = lowpt[y0.getRank()] + 1;
        for (InterlacingBlock block : blockStack) {
            int leftMax = block.left.isEmpty() ? -1 : block.left.getLast().getRank();
            int rightMax = block.right.isEmpty() ? -1 : block.right.getLast().getRank();
            if (leftMax >= w1 && rightMax >= w1) {
                return null;
            }
            if (leftMax >= w1) {
                attachments.addAll(block.right);
                attachments.addAll(block.left);
            } else {
                attachments.addAll(block.left);
                attachments.addAll(block.right);
            }
        }
        return attachments;
    }

    /**
     * Builds the spine of a cycle on the edge (x0, y0).
     * 
     * @param x0 first node of the edge on which a spine is built
     * @param y0 second node of the edge on which a spine is built
     * @return list of nodes in the spine of the created cycle
     */
    private LinkedList<KSlimNode> buildSpine(final KSlimNode x0, final KSlimNode y0) {
        LinkedList<KSlimNode> spine = new LinkedList<KSlimNode>();
        KSlimNode nextNode = y0;
        while (nextNode.getRank() > x0.getRank()) {
            spine.addLast(nextNode);
            nextNode = nextNode.getIncidence().get(0).endpoint();
        }
        return spine;
    }

    /**
     * Updates the block stack for the strong planarity algorithm.
     * 
     * @param blockStack stack of connected components of the interlacing graph
     * @param attachments list of attachment nodes found in the last recursive
     *            call
     * @param lowpte lowpt value of the last used edge
     * @return true if evidence for non-planarity was found
     */
    private boolean updateBlockStack(final Stack<InterlacingBlock> blockStack,
            final ConcatenableList<KSlimNode> attachments, final int lowpte) {
        LinkedList<InterlacingBlock> poppedBlocks = new LinkedList<InterlacingBlock>();
        while (!blockStack.isEmpty()) {
            InterlacingBlock block = blockStack.peek();
            int leftMax = block.left.isEmpty() ? -1 : block.left.getLast().getRank();
            int rightMax = block.right.isEmpty() ? -1 : block.right.getLast().getRank();
            if (Math.max(leftMax, rightMax) <= lowpte) {
                break;
            }
            if (leftMax > lowpte) {
                ConcatenableList<KSlimNode> temp = block.left;
                block.left = block.right;
                block.right = temp;
            }
            if (leftMax > lowpte) {
                return true;
            }
            poppedBlocks.addLast(blockStack.pop());
        }
        ConcatenableList<KSlimNode> newLeft = new ConcatenableList<KSlimNode>();
        ConcatenableList<KSlimNode> newRight = new ConcatenableList<KSlimNode>();
        for (InterlacingBlock block : poppedBlocks) {
            newLeft.concatenate(block.left);
            newRight.concatenate(block.right);
        }
        newLeft.concatenate(attachments);
        blockStack.push(new InterlacingBlock(newLeft, newRight));
        return false;
    }

}
