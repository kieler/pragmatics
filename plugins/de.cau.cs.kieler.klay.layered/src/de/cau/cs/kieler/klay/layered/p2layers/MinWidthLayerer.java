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
package de.cau.cs.kieler.klay.layered.p2layers;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.layered.ILayoutPhase;
import de.cau.cs.kieler.klay.layered.IntermediateProcessingConfiguration;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.intermediate.IntermediateProcessorStrategy;

/**
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
 * <li>Upper Bound On Width (de.cau.cs.kieler.klay.layered.minWidthUpperBoundOnWidth) – Defines a
 * loose upper bound on the width of the MinWidth layerer. Defaults to 4, lower bound is 1.</li>
 * <li>Upper Layer Estimation Scaling Factor
 * (de.cau.cs.kieler.klay.layered.minWidthUpperLayerEstimationScalingFactor) – Multiplied with Upper
 * Bound On Width for defining an upper bound on the width of layers which haven't been determined
 * yet, but whose maximum width had been (roughly) estimated by the MinWidth algorithm. Compensates
 * for too high estimations. Defaults to 2, lower bound is 1.</li>
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

        // Some local variables we're going to use in the while-loop of the MinWidth algorithm down
        // below
        boolean noNodeSelected = false;
        int inDegree = 0;
        int outDegree = 0;

        // Requires: DAG G = (V, E). In this version self-loops are allowed.
        // Additional properties as described above (called UBW and c in the original paper):
        final int upperBoundOnWidth = layeredGraph.getProperty(Properties.UPPER_BOUND_ON_WIDTH);
        final int compensator =
                layeredGraph.getProperty(Properties.UPPER_LAYER_ESTIMATION_SCALING_FACTOR);

        // Guarantee ConditionSelect from the paper, which states that nodes with maximum outdegree
        // should be preferred during layer placement, by ordering the nodes by descending maximum
        // outdegree in advance.
        // We're using the nodes id property to store the outdegree (so it's not an id anymore in
        // the strict sense, as it might not be unique).
        for (LNode node : notInserted) {
            node.id = countEdgesExceptSelfLoops(node.getOutgoingEdges());
        }
        
        Collections.sort(notInserted, new MaxOutgoingEdgesComparator());

        // This naive implementation uses sets, just like in the papers pseudocode. We use a
        // LinkedHashSet for the set of al real nodes of the graph in order to maintain the ordering
        // by maximum outdegree.
        Set<LNode> allNodes = new LinkedHashSet<LNode>(notInserted);

        // The actual algorithm from the paper begins here:
        // In the Paper the Set U contains all nodes, which have already been placed, and the set Z
        // contains all nodes already placed in layers which have been determined before the
        // currentLayer.
        Set<LNode> alreadyPlacedNodes = new HashSet<LNode>();
        Set<LNode> alreadyPlacedInOtherLayers = new HashSet<LNode>();

        // Set up the first layers
        Layer currentLayer = new Layer(layeredGraph);
        layers.add(currentLayer);

        // Intial values for the width of the current layer and the estimated width of the coming
        // layers
        int widthCurrent = 0;
        int widthUp = 0;

        // As long as not all nodes have been placed in a layer, do:
        while (!alreadyPlacedNodes.equals(allNodes)) {
            // Creates a view of allNodes minus the alreadyPlacedNodes without modifying the given
            // sets.
            SetView<LNode> unplacedNodes = Sets.difference(allNodes, alreadyPlacedNodes);

            // If you can find a node, whose edges only point to nodes in the Set
            // alreadyPlacedInOtherLayers, the try block won't fail …
            try {
                // This block of code will only be completed, if a node can be found by the
                // following instruction; see definition of selectNode(nodes, targets).
                LNode currentNode = this.selectNode(unplacedNodes, alreadyPlacedInOtherLayers);
                currentNode.setLayer(currentLayer);
                alreadyPlacedNodes.add(currentNode);

                outDegree = currentNode.id;
                widthCurrent += 1 - outDegree;

                inDegree = countEdgesExceptSelfLoops(currentNode.getIncomingEdges());
                widthUp += inDegree;
            } catch (NoElementLeftException e) { // In case no node can be found
                // … otherwise make sure you're going to the next layer
                noNodeSelected = true;
            }
            // Go to the next layer if,
            // 1) no node has been selected,
            // 2) The conditionGoUp from the paper is satisfied, i.e.
            // 2.1) the width of the current layer is greater than the upper bound on the width and
            // the amount of dummy nodes in the layer can't be reduced, as only nodes with no
            // outgoing edges are left for being considered for the current layer; or:
            // 2.2) The estimated width of the not yet determined layers is greater than the
            // scaling factor/compensator times the upper bound on the width.
            if (noNodeSelected || (widthCurrent >= upperBoundOnWidth && outDegree < 1)
                    || widthUp >= compensator * upperBoundOnWidth) {
                noNodeSelected = false;
                currentLayer = new Layer(layeredGraph);
                layers.add(currentLayer);
                alreadyPlacedInOtherLayers.addAll(alreadyPlacedNodes);
                widthCurrent = widthUp;
                widthUp = 0;
            }
        }

        // The algorithm constructs the layering bottom up, but KlayLayered expects the list of
        // layers to be ordered top down.
        java.util.Collections.reverse(layers);
        // After the algorithm, there should be no nodes left to be put in a layer.
        notInserted.clear();

        System.out.println(layeredGraph.getLayers());
        progressMonitor.done();
    }

    /**
     * Returns the first LNode in the given SetView, whose outgoing edges end only in nodes of the
     * Set targets. Self-loops are ignored.
     * 
     * @param nodes
     *            SetView to choose LNode from
     * @param targets
     *            Set of LNode
     * @return chosen LNode from nodes, whose outgoing edges all end in a node contained in targets
     * @throws NoElementLeftException
     *             when no node satisfies the condition.
     */
    private LNode selectNode(final SetView<LNode> nodes, final Set<LNode> targets)
            throws NoElementLeftException {
        Set<LNode> outNodes = new HashSet<LNode>();

        for (LNode node : nodes) {
            outNodes.clear();
            Iterable<LEdge> outEdges = node.getOutgoingEdges();

            for (LEdge edge : outEdges) {
                if (!isSelfLoop(edge)) {
                    outNodes.add(edge.getTarget().getNode());
                }
            }

            if (targets.containsAll(outNodes)) {
                return node;
            }
        }
        throw new NoElementLeftException();
    }

    /**
     * Returns the amount of LEdge edges in the given Iterable, but ignores self-loops.
     * 
     * @param edges
     *            Iterable whose edges without self-loops are to be counted
     * @return amount of LEdge edges without self-loops
     */
    private static int countEdgesExceptSelfLoops(final Iterable<LEdge> edges) {
        int i = 0;
        for (LEdge edge : edges) {
            if (!isSelfLoop(edge)) {
                i++;
            }
        }
        return i;
    }

    /**
     * Checks whether an edge is a self-loop (i.e. source node == target node).
     * 
     * @param edge
     *            LEdge to be tested
     * @return true if edge is self-loop, false otherwise
     */
    private static boolean isSelfLoop(final LEdge edge) {
        return edge.getSource().getNode().equals(edge.getTarget().getNode());
    }

    /**
     * Comparator for determining whether a LNode has more outgoing edges than another one. Requires
     * the LNode property "id" to be set to the number of outgoing edges of the node.
     * 
     * @author mic
     */
    private class MaxOutgoingEdgesComparator implements Comparator<LNode> {
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

    /**
     * Exception for indicating that a Collection has unexpectedly ended before an element has been
     * chosen to return.
     * 
     * @author mic
     */
    private class NoElementLeftException extends Exception {
        private static final long serialVersionUID = -7510397180858968135L;
    }

}
