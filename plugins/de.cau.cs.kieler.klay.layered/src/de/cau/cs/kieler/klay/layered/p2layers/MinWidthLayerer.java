/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * TODO: Check for all comments, if they still apply and add comments for the new pieces of Code!
 * 
 * Implementation of the heuristic MinWidth for solving the NP-hard minimum-width layering problem
 * with consideration of dummy nodes. MinWidth is based on the longest-path algorithm, which finds
 * layerings with the minimum height, but doesn't consider the width of the graph. MinWidth also
 * considers an upper bound on the width of a given graph.
 * 
 * The algorithm is described in
 * <ul>
 * <li>Nikola S. Nikolov, Alexandre Tarassov, and Jürgen Branke. 2005. In search for efficient
 * heuristics for minimum-width graph layering with consideration of dummy nodes. J. Exp.
 * Algorithmics 10, Article 2.7 (December 2005). DOI=10.1145/1064546.1180618
 * http://doi.acm.org/10.1145/1064546.1180618</li>
 * </ul>
 * 
 * MinWidth takes two additional parameters, which can be configured as a property:
 * <ul>
 * <li>Upper Bound On Width {@link Properties#UPPER_BOUND_ON_WIDTH} – Defines a loose upper bound on
 * the width of the MinWidth layerer. Defaults to 4, lower bound is 1.</li>
 * <li>Upper Layer Estimation Scaling Factor
 * {@link Properties#UPPER_LAYER_ESTIMATION_SCALING_FACTOR} – Multiplied with
 * {@link Properties#UPPER_BOUND_ON_WIDTH} for defining an upper bound on the width of layers which
 * haven't been determined yet, but whose maximum width had been (roughly) estimated by the MinWidth
 * algorithm. Compensates for too high estimations. Defaults to 2, lower bound is 1.</li>
 * </ul>
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>the graph has no cycles, but might contain self-loops</dd>
 * <dt>Postcondition:</dt>
 * <dd>all nodes have been assigned a layer such that edges connect only nodes from layers with
 * increasing indices</dd>
 * </dl>
 *
 * @author mic
 */
public final class MinWidthLayerer implements ILayoutPhase {

    /**
     * TODO: Document ranges.
     * 
     */
    private static final Range<Integer> UPPERBOUND_ON_WIDTH_RANGE = Range.closed(1, 4);
    private static final Range<Integer> COMPENSATOR_RANGE = Range.closed(1, 2);

    // TODO: COMMENT
    private SelfLoopPredicate isSelfLoopTest = new SelfLoopPredicate();

    /**
     * {@inheritDoc}
     */
    public IntermediateProcessingConfiguration getIntermediateProcessingConfiguration(
            final LGraph graph) {
        return IntermediateProcessingConfiguration
                .createEmpty()
                .addBeforePhase1(
                        IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
                .addBeforePhase3(IntermediateProcessorStrategy.LAYER_CONSTRAINT_PROCESSOR);
    }

    /**
     * {@inheritDoc}
     */
    public void process(final LGraph layeredGraph, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("MinWidth layering", 1);

        List<Layer> layers = layeredGraph.getLayers();
        List<LNode> notInserted = layeredGraph.getLayerlessNodes();

        // The algorithm requires DAG G = (V, E). In this version self-loops are allowed.
        // Additional properties as described above (called UBW and c in the original paper):
        final int upperBoundOnWidth = layeredGraph.getProperty(Properties.UPPER_BOUND_ON_WIDTH);
        final int compensator =
                layeredGraph.getProperty(Properties.UPPER_LAYER_ESTIMATION_SCALING_FACTOR);
        // TODO: Explain this new property.
        final Boolean ignoreDummys =
                layeredGraph.getProperty(Properties.IGNORE_DUMMY_NODES_FOR_WIDTH);

        // final int upperBoundOnWidth = -1;
        // final int compensator = -1;
        // final Boolean ignoreDummys = true;

        // Guarantee ConditionSelect from the paper, which states that nodes with maximum out-degree
        // should be preferred during layer placement, by ordering the nodes by descending maximum
        // out-degree in advance.
        // We're using the nodes id property to store the out-degree (so it's not an id anymore in
        // the strict sense, as it might not be unique).
        for (LNode node : notInserted) {
            node.id = countEdgesExceptSelfLoops(node.getOutgoingEdges());
        }
        Collections.sort(notInserted, Collections.reverseOrder(new MinOutgoingEdgesComparator()));

        // This implementation uses sets, just like in the papers pseudocode. A LinkedHashSet is
        // used
        // for the set of all real nodes of the graph in order to maintain the ordering by
        // out-degree.
        // Set<LNode> unplacedNodes = Sets.newLinkedHashSet(notInserted);

        // TODO: Comment why we precalculate + Warning that this will change the nodes' id again.
        List<Set<LNode>> nodeSuccessors = precalcSuccessors(notInserted);

        // TODO: Comment … a lot.
        // layers.addAll(computeMinWidthLayering(layeredGraph, upperBoundOnWidth, compensator,
        // notInserted, nodeSuccessors).getSecond());
        // TODO: Reduce code duplication?
        int minWidth = Integer.MAX_VALUE;
        // initializing with null might be dirty, Exception?
        List<List<LNode>> candidateLayering = null;

        int ubwStart = upperBoundOnWidth;
        int ubwEnd = upperBoundOnWidth;
        int cStart = compensator;
        int cEnd = compensator;

        // Do all recommended settings for negative parameters
        if (upperBoundOnWidth < 0) {
            ubwStart = UPPERBOUND_ON_WIDTH_RANGE.lowerEndpoint();
            ubwEnd = UPPERBOUND_ON_WIDTH_RANGE.upperEndpoint();
        }
        if (compensator < 0) {
            cStart = COMPENSATOR_RANGE.lowerEndpoint();
            cEnd = COMPENSATOR_RANGE.upperEndpoint();
        }

        for (int ubw = ubwStart; ubw <= ubwEnd; ubw++) {
            for (int c = cStart; c <= cEnd; c++) {
                // Pair<Integer, List<Layer>> result =
                // computeMinWidthLayering(layeredGraph, ubw, c, notInserted, nodeSuccessors);
                Pair<Integer, List<List<LNode>>> result =
                        computeMinWidthLayering(ubw, c, notInserted, nodeSuccessors);
                int newWidth = result.getFirst();

                // TODO: explain more. If you should only consider real nodes for the width…
                if (ignoreDummys) {
                    newWidth = 0;
                    for (List<LNode> layer : result.getSecond()) {
                        newWidth = Math.max(newWidth, layer.size());
                    }
                }

                if (newWidth < minWidth) {
                    minWidth = newWidth;
                    candidateLayering = result.getSecond();
                    //System.out.println("Candidate updated: UBW: " + ubw + "\tc: " + c
                    //        + "\tmaxWidth: " + minWidth);
                }
            }
        }

        // Finally Add the winning layering to the Klay layered data structures.
        for (List<LNode> layerList : candidateLayering) {
            Layer currentLayer = new Layer(layeredGraph);

            for (LNode node : layerList) {
                node.setLayer(currentLayer);
            }

            layers.add(currentLayer);
        }

        // The algorithm constructs the layering bottom up, but KlayLayered expects the list of
        // layers to be ordered top down.
        Collections.reverse(layers);
        // After the algorithm, there should be no nodes left to be put in a layer.
        notInserted.clear();

        progressMonitor.done();
    }

    /**
     * TODO: comment.
     * 
     * @param nodes
     * @return
     */
    private List<Set<LNode>> precalcSuccessors(final Collection<LNode> nodes) {
        List<Set<LNode>> successors = Lists.newArrayListWithCapacity(nodes.size());

        int index = 0;
        for (LNode node : nodes) {
            // WARNING: The id is being redefined here as an actual id!
            node.id = index;

            Set<LNode> outNodes = Sets.newHashSet();
            Iterable<LEdge> outEdges = node.getOutgoingEdges();

            for (LEdge edge : outEdges) {
                if (!isSelfLoopTest.apply(edge)) {
                    outNodes.add(edge.getTarget().getNode());
                }
            }

            successors.add(outNodes);
            index++;
        }

        return successors;
    }

    /**
     * TODO: Comment.
     * 
     * 
     */
    private Pair<Integer, List<List<LNode>>> computeMinWidthLayering(
            // final LGraph layeredGraph,
            final int upperBoundOnWidth, final int compensator, final Iterable<LNode> nodes,
            final List<Set<LNode>> nodeSuccessors) {

        // List<Layer> layers = Lists.newArrayList();
        List<List<LNode>> layers = Lists.newArrayList();
        Set<LNode> unplacedNodes = Sets.newLinkedHashSet(nodes);

        // in- and out-degree of the currently considered node, see while-loop below
        int inDegree = 0;
        int outDegree = 0;

        // The actual algorithm from the paper begins here:
        // In the Paper the first Set contains all nodes, which have already been placed, and the
        // second contains all nodes already placed in layers which have been determined before the
        // currentLayer.
        Set<LNode> alreadyPlacedInCurrentLayer = Sets.newHashSet();
        Set<LNode> alreadyPlacedInOtherLayers = Sets.newHashSet();

        // Set up the first layer (algorithm is bottom up, so the List layer is going to be reversed
        // at
        // the end.
        // Layer currentLayer = new Layer(layeredGraph);
        List<LNode> currentLayer = Lists.newArrayList();
        layers.add(currentLayer);

        // Initial values for the width of the current layer and the estimated width of the coming
        // layers
        int widthCurrent = 0;
        int widthUp = 0;

        // TODO: Comment.
        int maxWidth = 0;
        int dummyNodeCount = 0;
        int realWidth = 0;
        Set<LEdge> dummysInNextLayer = Sets.newHashSet();
        List<LEdge> goingOutFromThisLayer = Lists.newArrayList();
        List<LEdge> comingIntoThisLayer = Lists.newArrayList();

        //System.out.println("Layerwidths rtl for ubw: " + upperBoundOnWidth + "\tc: " + compensator);

        while (!unplacedNodes.isEmpty()) {
            // Find a node, whose edges only point to nodes in the Set alreadyPlacedInOtherLayers;
            // will
            // return {@code null} if such a node doesn't exist.
            LNode currentNode =
                    selectNode(unplacedNodes, nodeSuccessors, alreadyPlacedInOtherLayers);

            // If you found a node in the previous step.
            if (currentNode != null) {
                unplacedNodes.remove(currentNode);
                // Please do this after choosing a layering TODO: Rewrite algorithm
                // currentNode.setLayer(currentLayer);
                currentLayer.add(currentNode);
                alreadyPlacedInCurrentLayer.add(currentNode);

                outDegree = currentNode.id;
                widthCurrent += 1 - outDegree;

                inDegree = countEdgesExceptSelfLoops(currentNode.getIncomingEdges());
                widthUp += inDegree;

                // TODO: New for exact dummy node count:
                Iterables.addAll(
                        goingOutFromThisLayer,
                        Iterables.filter(currentNode.getOutgoingEdges(),
                                Predicates.not(isSelfLoopTest)));
                Iterables.addAll(
                        comingIntoThisLayer,
                        Iterables.filter(currentNode.getIncomingEdges(),
                                Predicates.not(isSelfLoopTest)));
            }

            // Go to the next layer if,
            // 1) no current node has been selected,
            // TODO: comment if empty
            // 2) The conditionGoUp from the paper is satisfied, i.e.
            // 2.1) the width of the current layer is greater than the upper bound on the width and
            // the amount of dummy nodes in the layer can't be reduced, as only nodes with no
            // outgoing edges are left for being considered for the current layer; or:
            // 2.2) The estimated width of the not yet determined layers is greater than the
            // scaling factor/compensator times the upper bound on the width.
            if (currentNode == null || unplacedNodes.isEmpty()
                    || (widthCurrent >= upperBoundOnWidth && outDegree < 1)
                    || widthUp >= compensator * upperBoundOnWidth) {
                // currentLayer = new Layer(layeredGraph);
                layers.add(currentLayer);
                realWidth = currentLayer.size();
                currentLayer = Lists.newArrayList();
                alreadyPlacedInOtherLayers.addAll(alreadyPlacedInCurrentLayer);
                alreadyPlacedInCurrentLayer.clear();

                // TODO: Is this the right way to determine the maximum width of the layering?
                // Moreover: Comment
                dummysInNextLayer.removeAll(goingOutFromThisLayer);
                dummyNodeCount = dummysInNextLayer.size();
                maxWidth = Math.max(maxWidth, dummyNodeCount + realWidth);
                dummysInNextLayer.addAll(comingIntoThisLayer);

                widthCurrent = widthUp;
                widthUp = 0;

                //System.out.print((dummyNodeCount + realWidth) + "\t");
            }
        }

        //System.out.println();
        //System.out.println("maxWidth: " + maxWidth);

        return Pair.of(maxWidth, layers);
    }

    /**
     * TODO: Change comment. Returns the first {@link LNode} in the given Set, whose outgoing edges
     * end only in nodes of the Set {@code targets}. Self-loops are ignored.
     * 
     * Warning: Returns {@code null}, if such a node doesn't exist.
     * 
     * @param nodes
     *            Set to choose {@link LNode} from
     * @param targets
     *            Set of {@link LNode}
     * @return chosen {@link LNode} from {@code nodes}, whose outgoing edges all end in a node
     *         contained in {@code targets}. Returns {@code null}, if such a node doesn't exist.
     */
    private LNode selectNode(final Set<LNode> nodes, final List<Set<LNode>> successors,
            final Set<LNode> targets) {

        for (LNode node : nodes) {
            if (targets.containsAll(successors.get(node.id))) {
                return node;
            }
        }
        return null;
    }

    /**
     * Returns the number of {@link LEdge} edges in the given Iterable, but ignores self-loops.
     * 
     * @param edges
     *            Iterable whose edges without self-loops are to be counted
     * @return number of {@link LEdge} edges without self-loops
     */
    private int countEdgesExceptSelfLoops(final Iterable<LEdge> edges) {
        int i = 0;
        for (LEdge edge : edges) {
            if (!isSelfLoopTest.apply(edge)) {
                i++;
            }
        }
        return i;
    }

    // /**
    // * Checks whether an edge is a self-loop (i.e. source node == target node).
    // *
    // * @param edge
    // * {@link LEdge} to be tested
    // * @return true if edge is self-loop, false otherwise
    // */
    // private static boolean isSelfLoop(final LEdge edge) {
    // return edge.getSource().getNode().equals(edge.getTarget().getNode());
    // }

    /**
     * Checks whether an edge is a self-loop (i.e. source node == target node).
     * 
     * @author mic
     */
    private class SelfLoopPredicate implements Predicate<LEdge> {

        /**
         * {@inheritDoc}
         */
        public boolean apply(final LEdge input) {
            return input.getSource().getNode().equals(input.getTarget().getNode());
        }

    }

    /**
     * Comparator for determining whether a {@link LNode} has less outgoing edges than another one.
     * Requires the LNode property {@link LNode#id} to be set to the number of outgoing edges of the
     * node.
     * 
     * @author mic
     */
    private class MinOutgoingEdgesComparator implements Comparator<LNode> {
        /**
         * {@inheritDoc}
         */
        public int compare(final LNode o1, final LNode o2) {
            int outs1 = o1.id;
            int outs2 = o2.id;

            if (outs1 < outs2) {
                return -1;
            }
            if (outs1 == outs2) {
                return 0;
            }
            return 1;
        }
    }

}
