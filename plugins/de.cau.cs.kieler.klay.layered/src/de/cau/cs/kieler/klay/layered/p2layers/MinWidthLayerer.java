/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
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
 * Implementation of the heuristic MinWidth for solving the NP-hard minimum-width layering problem
 * with consideration of dummy nodes. MinWidth is based on the longest-path algorithm, which finds
 * layerings with the minimum height, but doesn't consider the width of the graph. MinWidth also
 * considers an upper bound on the width of a given graph. The upper bound isn't a "bound" in a
 * strict sense, as some layers might exceed its limit, if certain conditions are met.
 * 
 * Details are described in
 * <ul>
 * <li>Nikola S. Nikolov, Alexandre Tarassov, and Jürgen Branke. 2005. In search for efficient
 * heuristics for minimum-width graph layering with consideration of dummy nodes. J. Exp.
 * Algorithmics 10, Article 2.7 (December 2005). DOI=10.1145/1064546.1180618
 * http://doi.acm.org/10.1145/1064546.1180618.</li>
 * </ul>
 * 
 * MinWidth takes two additional parameters, which can be configured as a property:
 * <ul>
 * <li>Upper Bound On Width {@link Properties#UPPER_BOUND_ON_WIDTH} – Defines a loose upper bound on
 * the width of the MinWidth layerer. Defaults to -1 (special value for using both 1, 2, 3 and 4 as
 * values and choosing the narrowest layering afterwards), lower bound is 1.</li>
 * <li>Upper Layer Estimation Scaling Factor
 * {@link Properties#UPPER_LAYER_ESTIMATION_SCALING_FACTOR} – Multiplied with
 * {@link Properties#UPPER_BOUND_ON_WIDTH} for defining an upper bound on the width of layers which
 * haven't been placed yet, but whose maximum width had been (roughly) estimated by the MinWidth
 * algorithm. Compensates for too high estimations. Defaults to -1 (special value for using both 1
 * and 2 as values and choosing the narrowest layering afterwards), lower bound is 1.</li>
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
     * Recommended values for the algorithm suggested bei Nikolov et al. after a parameter study,
     * see:
     * 
     * <ul>
     * <li>Alexandre Tarassov, Nikola S. Nikolov, and Jürgen Branke. 2004. A Heuristic for
     * Minimum-Width Graph Layering with Consideration of Dummy Nodes. Experimental and Efficient
     * Algorithms, Third International Workshop, WEA 2004, Lecture Notes in Computer Science 3059.
     * Springer-Verlag, New York, 570-583. DOI=10.1007/978-3-540-24838-5_42
     * http://dx.doi.org/10.1007/978-3-540-24838-5_42.</li>
     * </ul>
     */
    private static final Range<Integer> UPPERBOUND_ON_WIDTH_RANGE = Range.closed(1, 4);
    private static final Range<Integer> COMPENSATOR_RANGE = Range.closed(1, 2);

    /**
     * Checks whether an edge is a self-loop (i.e. source node == target node). Used internally, as
     * KLay Layered doesn't remove self-loops in its cycle breaking phase, but MinWidth expects only
     * real directected acyclic graphs (DAGs).
     */
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

        // The algorithm requires DAG G = (V, E). In this version self-loops are allowed (as we're
        // going to filter them). Additional properties as described above (called UBW and c in the
        // original paper):
        final int upperBoundOnWidth = layeredGraph.getProperty(Properties.UPPER_BOUND_ON_WIDTH);
        final int compensator =
                layeredGraph.getProperty(Properties.UPPER_LAYER_ESTIMATION_SCALING_FACTOR);

        // Guarantee ConditionSelect from the paper, which states that nodes with maximum out-degree
        // should be preferred during layer placement, by ordering the nodes by descending maximum
        // out-degree in advance.
        // We're using the nodes' id property to store the out-degree (so it's not an id anymore in
        // the strict sense, as it might not be unique).
        for (LNode node : notInserted) {
            // Warning: LNode.id is being redefined here!
            node.id = countEdgesExceptSelfLoops(node.getOutgoingEdges());
        }
        notInserted.sort(Collections.reverseOrder(new MinOutgoingEdgesComparator()));

        // Precalculate the successors of all nodes (as a Set) and put them in a list. The id of a
        // node is changed again to the index of its successor-set in the resulting list (thus being
        // an unique id) for easier access later on. Beware: The id has two different meanings in
        // this class:
        // 1.) At first it represents the number of outgoing edges of a node (without self-loops),
        // see above.
        // 2.) From now on and till the end of the algorithm it will be an index for easy access of
        // the successor-set of the node.
        List<Set<LNode>> nodeSuccessors = precalcSuccessors(notInserted);

        // minimum width of a layer of maximum size in a computed layering (primary criterion used
        // for comparison, if more than one layering is computed).
        int minWidth = Integer.MAX_VALUE;
        // minimum number of layers in a computed layering {@code minWidth} (secondary
        // criterion used for comparison, if more than one layering is computed).
        int minNumOfLayers = Integer.MAX_VALUE;
        // holding the currently chosen candidate for the final layering as a List
        List<List<LNode>> candidateLayering = null;

        // At first blindly set the parameters for the loose upper bound and the compensator to the
        // exact values, which have been configured via their respective properties, so that only
        // one layering will be computed …
        int ubwStart = upperBoundOnWidth;
        int ubwEnd = upperBoundOnWidth;
        int cStart = compensator;
        int cEnd = compensator;

        // … then check, whether any special values (i.e. negative values, which aren't valid) have
        // been used for the properties. In that case use the recommended ranges described above …
        if (upperBoundOnWidth < 0) {
            ubwStart = UPPERBOUND_ON_WIDTH_RANGE.lowerEndpoint();
            ubwEnd = UPPERBOUND_ON_WIDTH_RANGE.upperEndpoint();
        }
        if (compensator < 0) {
            cStart = COMPENSATOR_RANGE.lowerEndpoint();
            cEnd = COMPENSATOR_RANGE.upperEndpoint();
        }

        // … Depending on the start- and end-values, this nested for-loop will last for up to 8
        // iterations resulting in one, two, four or eight different layerings.
        for (int ubw = ubwStart; ubw <= ubwEnd; ubw++) {
            for (int c = cStart; c <= cEnd; c++) {
                Pair<Integer, List<List<LNode>>> result =
                        computeMinWidthLayering(ubw, c, notInserted, nodeSuccessors);
                int newWidth = result.getFirst();
                List<List<LNode>> layering = result.getSecond();

                // Important if more than one layering is computed: replace the current candidate
                // layering with a newly computed one, if it is narrower or has the same maximum
                // width but less layers.
                int newNumOfLayers = layering.size();
                if (newWidth < minWidth
                        || (newWidth == minWidth && newNumOfLayers < minNumOfLayers)) {
                    minWidth = newWidth;
                    minNumOfLayers = newNumOfLayers;
                    candidateLayering = layering;
                }
            }
        }

        // Finally, add the winning layering to the Klay layered data structures.
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
        // After the algorithm, there should be no nodes left to be put in a layer, so we're gonna
        // delete them.
        notInserted.clear();

        progressMonitor.done();
    }

    /**
     * Calculates for a given Collection of {@link LNode} all its successors (i.e. a Set of
     * {@link LNode}s) without self-loops.
     * 
     * Warning: Changes {@link LNode#id}! After execution its value will be the index of the
     * respective Set of successor-nodes in the returned List.
     * 
     * @param nodes
     *            a Collection of {@link LNode}
     * @return List of Set of successor {@link LNode}s in order of the given nodes
     */
    private List<Set<LNode>> precalcSuccessors(final Collection<LNode> nodes) {
        List<Set<LNode>> successors = Lists.newArrayListWithCapacity(nodes.size());

        int index = 0;
        for (LNode node : nodes) {
            // Warning: LNode.id is being redefined here!
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
     * Computes a layering (as a List of Lists) for a given Iterable of {@link LNode} according to
     * the MinWidth-heuristic.
     * 
     * @param upperBoundOnWidth
     *            Defines a loose upper bound on the width of the MinWidth layerer.
     * @param compensator
     *            Multiplied with {@code upperBoundOnWidth} for defining an upper bound on the width
     *            of layers which haven't been determined yet, but whose maximum width had been
     *            (roughly) estimated by the MinWidth algorithm. Compensates for too high
     *            estimations.
     * @param nodes
     *            Iterable of all nodes of the Graph. The {@code id} of the nodes have to be set to
     *            the index where the respective Set of successor-nodes are stored in the List
     *            {@code nodeSuccessors}.
     * @param nodeSuccessors
     *            precomputed List of Set of successor-nodes of the elements in the Iterable
     *            {@code nodes}.
     * @return
     */
    private Pair<Integer, List<List<LNode>>> computeMinWidthLayering(final int upperBoundOnWidth,
            final int compensator, final Iterable<LNode> nodes,
            final List<Set<LNode>> nodeSuccessors) {

        List<List<LNode>> layers = Lists.newArrayList();
        Set<LNode> unplacedNodes = Sets.newLinkedHashSet(nodes);

        // in- and out-degree of the currently considered node, see while-loop below
        int inDegree = 0;
        int outDegree = 0;

        // The actual algorithm from the paper begins here:
        // In the Paper the first Set contains all nodes, which have already been placed (in this
        // version we consider only the nodes already placed in the current layer), and the
        // second contains all nodes already placed in layers which have been determined before the
        // currentLayer.
        Set<LNode> alreadyPlacedInCurrentLayer = Sets.newHashSet();
        Set<LNode> alreadyPlacedInOtherLayers = Sets.newHashSet();

        // Set up the first layer (algorithm is bottom up, so the List layer is going to be reversed
        // at the end.
        List<LNode> currentLayer = Lists.newArrayList();
        layers.add(currentLayer);

        // Initial values for the width of the current layer and the estimated width of the coming
        // layers
        int widthCurrent = 0;
        int widthUp = 0;

        // Parameters needed for computing the width of a layering including dummy nodes:
        int maxWidth = 0;
        int dummyNodeCount = 0;
        int realWidth = 0;
        Set<LEdge> dummysInLayer = Sets.newHashSet();
        List<LEdge> goingOutFromThisLayer = Lists.newArrayList();
        List<LEdge> comingIntoThisLayer = Lists.newArrayList();

        while (!unplacedNodes.isEmpty()) {
            // Find a node, whose edges only point to nodes in the Set alreadyPlacedInOtherLayers;
            // will return {@code null} if such a node doesn't exist.
            LNode currentNode =
                    selectNode(unplacedNodes, nodeSuccessors, alreadyPlacedInOtherLayers);

            // If a node is found in the previous step:
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

                // For counting the dummy nodes we count all the outgoing edges from and incoming
                // edges to the current layer.
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
            // 2) there are no unplaced nodes left (last iteration of the while-loop),
            // 3) The conditionGoUp from the paper is satisfied, i.e.
            // 3.1) the width of the current layer is greater than the upper bound on the width and
            // the number of dummy nodes in the layer can't be reduced, as only nodes with no
            // outgoing edges are left for being considered for the current layer; or:
            // 3.2) The estimated width of the not yet determined layers is greater than the
            // scaling factor/compensator times the upper bound on the width.
            if (currentNode == null || unplacedNodes.isEmpty()
                    || (widthCurrent >= upperBoundOnWidth && outDegree < 1)
                    || widthUp >= compensator * upperBoundOnWidth) {
                layers.add(currentLayer);
                realWidth = currentLayer.size();
                currentLayer = Lists.newArrayList();
                alreadyPlacedInOtherLayers.addAll(alreadyPlacedInCurrentLayer);
                alreadyPlacedInCurrentLayer.clear();

                // Remove all edges from the dummy node count, which are starting at a node placed
                // in this layer …
                dummysInLayer.removeAll(goingOutFromThisLayer);
                // … Now we have the actual dummy node count for this layer and can add it to the
                // real nodes for comparing the width.
                dummyNodeCount = dummysInLayer.size();
                maxWidth = Math.max(maxWidth, dummyNodeCount + realWidth);
                // In the next iteration we have to consider new dummy nodes from edges coming into
                // the
                // layer we've just finished.
                dummysInLayer.addAll(comingIntoThisLayer);

                widthCurrent = widthUp;
                widthUp = 0;
            }
        }

        return Pair.of(maxWidth, layers);
    }

    /**
     * Returns the first {@link LNode} in the given Set, whose outgoing edges end only in nodes of
     * the Set {@code targets}. Self-loops are ignored.
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

    /**
     * Checks whether an edge is a self-loop (i.e. source node == target node).
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
