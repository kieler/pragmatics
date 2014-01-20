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
package de.cau.cs.kieler.klay.layered.p3order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Determines the node order of a given free layer. Uses heuristic methods to find an ordering that
 * minimizes edge crossings between the given free layer and a neighboring layer with fixed node
 * order. The barycenter heuristic is used here.
 * 
 * @author msp
 * @author cds
 * @author ima
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class BarycenterHeuristic implements ICrossingMinimizationHeuristic {

    /** the array of port ranks. */
    private float[] portRanks;
    /** the random number generator. */
    private Random random;
    /** the constraint resolver for ordering constraints. */
    private IConstraintResolver constraintResolver;

    /**
     * Constructs a Barycenter heuristic for crossing minimization between two layers.
     * 
     * @param constraintResolver
     *            the constraint resolver
     * @param graphRandom
     *            the random number generator
     * @param portRanks
     *            the array of port ranks
     */
    public BarycenterHeuristic(final IConstraintResolver constraintResolver, final Random graphRandom,
            final float[] portRanks) {
        this.constraintResolver = constraintResolver;
        this.random = graphRandom;
        this.portRanks = portRanks;
    }

    /**
     * {@inheritDoc}
     */
    public boolean minimizeCrossings(final List<NodeGroup> layer, final int layerIndex,
            final boolean preOrdered, final boolean randomize, final boolean forward) {

        if (randomize) {
            // Randomize barycenters (we don't need to update the edge count in this case;
            // there are no edges of interest since we're only concerned with one layer)
            randomizeBarycenters(layer);
        } else {
            // Calculate barycenters and assign barycenters to barycenterless node groups
            calculateBarycenters(layer, forward);
            fillInUnknownBarycenters(layer, preOrdered);
        }
    
        // adjust barycenter values to avoid edge/node crossings in presence of big nodes 
        boolean valid = adjustBigNodeBarycenters(layer, forward);
        if (!valid) {
            return false; 
        }
        
        if (layer.size() > 1) {
            // Sort the vertices according to their barycenters
            Collections.sort(layer);
    
            // Resolve ordering constraints
            constraintResolver.processConstraints(layer, layerIndex);
        }
        
        return true;
    }
    
    /**
     * 
     * TODO 
     *  - can bignodes be in node groups with other nodes?
     *  - consider the initial bignode properly
     * 
     * @param nodeGroups
     * @param forward
     */
    private boolean adjustBigNodeBarycenters(final List<NodeGroup> nodeGroups, final boolean forward) {

        // sort the list temporarily so that we can easily access a
        // big nodes predecessor (in terms of barycenter value)
        ArrayList<NodeGroup> tmpNodeGroups = Lists.newArrayList(nodeGroups);
        Collections.sort(tmpNodeGroups);
        // System.out.println("New Layer (" + (forward ? "forward" : "backward") + ") " +
        // tmpNodeGroups);

        // treat all bignodes of the current layer
        for (int i = 0; i < tmpNodeGroups.size(); i++) {
            NodeGroup nodeGroup = tmpNodeGroups.get(i);

            boolean isBignode =
                    (nodeGroup.getNode().getProperty(Properties.NODE_TYPE) == NodeType.BIG_NODE);
            boolean isInitialBignode = nodeGroup.getNode().getProperty(Properties.BIG_NODE_INITIAL);

            // all bignode groups will contain only one node
            if (nodeGroup.getNodes().length == 1
                    // during backward sweep also consider the initial big node
                    && (isBignode || (isInitialBignode && !forward))) {

                LNode bigNode = nodeGroup.getNode();

                // get the opposite element of the big node chain
                Iterable<LEdge> bigNodeEdges =
                        forward ? bigNode.getIncomingEdges() : bigNode.getOutgoingEdges();
                if (Iterables.isEmpty(bigNodeEdges)) {
                    continue;
                }
                LEdge bigNodeEdge = Iterables.get(bigNodeEdges, 0);

                // the two node groups of the big node
                NodeGroup bigNodeLayerGroup = nodeGroup;
                NodeGroup bigNodePreGroup =
                        (forward ? bigNodeEdge.getSource().getNode() : bigNodeEdge.getTarget()
                                .getNode()).getProperty(Properties.NODE_GROUP);

                // during a backward sweep, don't treat the last node  
                if (!forward && bigNodePreGroup.getNode().getProperty(Properties.NODE_TYPE) 
                        != NodeType.BIG_NODE) {
                    continue;
                }
                
                // now compare all edges with the big node edge for interleaving
                for (NodeGroup innerGroup : nodeGroups) {
                    if (nodeGroup.equals(innerGroup)) {
                        continue; // not with ourself
                    }

                    LNode layerNode = innerGroup.getNode();
                    NodeGroup layerGroup = innerGroup;

                    // TODO i have to get all edges of all nodes contained in the node group here
                    // get all edges for the current node of the current layer
                    Iterable<LEdge> layerEdges =
                            forward ? layerNode.getIncomingEdges() : layerNode.getOutgoingEdges();

                    double delta = 0;

                    for (LEdge layerEdge : layerEdges) {

                        // ignore in-layer edges
                        if (layerEdge.getSource().getNode().getLayer().getIndex() == layerEdge
                                .getTarget().getNode().getLayer().getIndex()) {
                            continue;
                        }

                        // the edge's attached node group on the other layer
                        NodeGroup preLayerGroup =
                                (forward ? layerEdge.getSource().getNode() : layerEdge.getTarget()
                                        .getNode()).getProperty(Properties.NODE_GROUP);

                        // interleaving?
                        if ((bigNodeLayerGroup.barycenter > layerGroup.barycenter 
                                && bigNodePreGroup.barycenter < preLayerGroup.barycenter)) {
                            // CASE 1. big node's barycenter is higher

                            // barycenter value diff of n_i and n_i+1
                            float dp = 2f; // a default diff in case this is the layer's first node
                            if (tmpNodeGroups.size() > i + 1) {
                                dp =
                                        1 + tmpNodeGroups.get(i + 1).barycenter
                                                - bigNodeLayerGroup.barycenter;
                            }
                            float di = 1 + bigNodeLayerGroup.barycenter - layerGroup.barycenter;

                            // simple test
                            // layerGroup.barycenter +=
                            // Math.abs(layerGroup.barycenter - bigNodeLayerGroup.barycenter) +
                            // 0.02f;

                            // add to big node's barycenter
                            // double old = layerGroup.barycenter;
                            layerGroup.barycenter = bigNodeLayerGroup.barycenter + (dp / di);

                            if (bigNodeLayerGroup.barycenter - layerGroup.barycenter > delta) {
                                // couldn't find a valid alteration for this node
                                return false;
                            }

                            // System.out.println("Adapting1: " + bigNode + " " + layerNode + " "
                            // + bigNodeLayerGroup.barycenter + " " + layerGroup.barycenter +
                            // " old(" + old + ")");
                            // System.out.println("\t\tComparing: PreLayer ("
                            // + bigNodePreGroup.getNode() + ", " + preLayerGroup.getNode()
                            // + ") Values " + bigNodePreGroup.barycenter + " "
                            // + preLayerGroup.barycenter);
                            // System.out.println("\t\tRelevant Edges " + layerEdge + " "
                            // + layerEdge.getSource().getNode().getLayer().id + " "
                            // + layerEdge.getTarget().getNode().getLayer().id);

                            // remember the delta between the big node and the new node position
                            delta += bigNodeLayerGroup.barycenter - layerGroup.barycenter;

                        } else if (bigNodeLayerGroup.barycenter < layerGroup.barycenter
                                && bigNodePreGroup.barycenter > preLayerGroup.barycenter) {
                            // CASE 2. big node's barycenter is smaller

                            float dp = 2f; // a default diff in case this is the layer's last node
                            if (i > 0) {
                                dp =
                                        1 + bigNodeLayerGroup.barycenter
                                                - tmpNodeGroups.get(i - 1).barycenter;
                            }
                            float di = 1 + layerGroup.barycenter - bigNodeLayerGroup.barycenter;

                            // simple test
                            // layerGroup.barycenter -=
                            // Math.abs(layerGroup.barycenter - bigNodeLayerGroup.barycenter) -
                            // 0.02f;

                            // subtract from big node's barycenter
                            layerGroup.barycenter = bigNodeLayerGroup.barycenter - (dp / di);

                            if (bigNodeLayerGroup.barycenter - layerGroup.barycenter < delta) {
                                // couldn't find a valid alteration for this node
                                return false;
                            }

                            // System.out.println("Adapting2: " + bigNode + " " + layerNode + " " +
                            // layerGroup.barycenter);

                            // remember the delta between the big node and the new node position
                            delta -= bigNodeLayerGroup.barycenter - layerGroup.barycenter;
                        }
                        // }

                        // System.out.println("DELTA " +delta);

                    }

                }

            }
        }

        return true;
    }

    /**
     * Randomize the order of nodes for the given layer.
     * 
     * @param nodeGroups
     *            a layer
     */
    private void randomizeBarycenters(final List<NodeGroup> nodeGroups) {
        for (NodeGroup nodeGroup : nodeGroups) {
            // Set barycenters only for nodeGroups containing a single node.
            if (nodeGroup.getNodes().length == 1) {
                nodeGroup.barycenter = random.nextFloat();
                nodeGroup.summedWeight = nodeGroup.barycenter;
                nodeGroup.degree = 1;
            }
        }
    }

    /**
     * Try to find appropriate barycenter values for node groups whose barycenter is undefined.
     * 
     * @param nodeGroups
     *            the nodeGroups to fill in barycenters for.
     * @param preOrdered
     *            whether the nodeGroups have been ordered in a previous run.
     */
    private void fillInUnknownBarycenters(final List<NodeGroup> nodeGroups, final boolean preOrdered) {
        // Determine placements for nodes with undefined barycenter value
        if (preOrdered) {
            float lastValue = -1;
            ListIterator<NodeGroup> nodeGroupIterator = nodeGroups.listIterator();

            while (nodeGroupIterator.hasNext()) {
                NodeGroup nodeGroup = nodeGroupIterator.next();
                Float value = nodeGroup.barycenter;

                if (value == null) {
                    // The barycenter is undefined - take the center of the previous value
                    // and the next defined value in the list
                    float nextValue = lastValue + 1;

                    ListIterator<NodeGroup> nextNodeGroupIterator = nodeGroups
                            .listIterator(nodeGroupIterator.nextIndex());
                    while (nextNodeGroupIterator.hasNext()) {
                        Float x = nextNodeGroupIterator.next().barycenter;
                        if (x != null) {
                            nextValue = x;
                            break;
                        }
                    }

                    value = (lastValue + nextValue) / 2;
                    nodeGroup.barycenter = value;
                    nodeGroup.summedWeight = value;
                    nodeGroup.degree = 1;
                }

                lastValue = value;
            }
        } else {
            // No previous ordering - determine random placement for new nodes
            float maxBary = 0;
            for (NodeGroup nodeGroup : nodeGroups) {
                if (nodeGroup.barycenter != null) {
                    maxBary = Math.max(maxBary, nodeGroup.barycenter);
                }
            }

            maxBary += 2;
            for (NodeGroup nodeGroup : nodeGroups) {
                if (nodeGroup.barycenter == null) {
                    float value = random.nextFloat() * maxBary - 1;
                    nodeGroup.barycenter = value;
                    nodeGroup.summedWeight = value;
                    nodeGroup.degree = 1;
                }
            }
        }
    }

    /**
     * Calculate the barycenters of the given node groups.
     * 
     * @param nodeGroups
     *            the nodeGroups
     * @param forward
     *            {@code true} if the current sweep moves forward
     */
    private void calculateBarycenters(final List<NodeGroup> nodeGroups, final boolean forward) {
        // Set all visited flags to false
        for (NodeGroup nodeGroup : nodeGroups) {
            nodeGroup.visited = false;
        }

        for (NodeGroup nodeGroup : nodeGroups) {
            if (nodeGroup.getNodes().length == 1) {
                // Calculate the node groups's new barycenter (may be null)
                calculateBarycenter(nodeGroup, forward);
            }
        }
    }
    
    /** the amount of random value to add to each calculated barycenter. */
    private static final float RANDOM_AMOUNT = 0.07f;

    /**
     * Calculate the barycenter of the given single-node node group. This method is able to
     * handle in-layer edges, but it may give incorrect results if the in-layer edges form
     * a cycle. However, such cases do not occur in the present implementation.
     * 
     * @param nodeGroup
     *            a node group consisting of a single node
     * @param forward
     *            {@code true} if the current sweep moves forward
     * @param portPos
     *            position array
     * @return a pair containing the summed port positions of the connected ports as the first, and
     *         the number of connected edges as the second entry.
     */
    private void calculateBarycenter(final NodeGroup nodeGroup, final boolean forward) {

        // Check if the node group's barycenter was already computed
        if (nodeGroup.visited) {
            return;
        } else {
            nodeGroup.visited = true;
        }

        nodeGroup.degree = 0;
        nodeGroup.summedWeight = 0.0f;
        nodeGroup.barycenter = null;
        LNode node = nodeGroup.getNode();

        for (LPort freePort : node.getPorts()) {
            Iterable<LPort> portIterable = forward ? freePort.getPredecessorPorts()
                    : freePort.getSuccessorPorts();
            for (LPort fixedPort : portIterable) {
                // If the node the fixed port belongs to is part of the free layer (thus, if
                // we have an in-layer edge), use that node's barycenter calculation instead
                LNode fixedNode = fixedPort.getNode();

                if (fixedNode.getLayer() == node.getLayer()) {
                    // Self-loops are ignored
                    if (fixedNode != node) {
                        // Find the fixed node's node group and calculate its barycenter
                        NodeGroup fixedNodeGroup = fixedNode.getProperty(Properties.NODE_GROUP);
                        calculateBarycenter(fixedNodeGroup, forward);
    
                        // Update this node group's values
                        nodeGroup.degree += fixedNodeGroup.degree;
                        nodeGroup.summedWeight += fixedNodeGroup.summedWeight;
                    }
                } else {
                    nodeGroup.summedWeight += portRanks[fixedPort.id];
                    nodeGroup.degree++;
                }
            }
        }

        // Iterate over the node's barycenter associates
        List<LNode> barycenterAssociates = node.getProperty(Properties.BARYCENTER_ASSOCIATES);
        if (barycenterAssociates != null) {
            for (LNode associate : barycenterAssociates) {
                // Make sure the associate is in the same layer as this node
                if (node.getLayer() == associate.getLayer()) {
                    // Find the associate's node group and calculate its barycenter
                    NodeGroup associateNodeGroup = associate.getProperty(Properties.NODE_GROUP);
                    calculateBarycenter(associateNodeGroup, forward);
    
                    // Update this vertex's values
                    nodeGroup.degree += associateNodeGroup.degree;
                    nodeGroup.summedWeight += associateNodeGroup.summedWeight;
                }
            }
        }

        if (nodeGroup.degree > 0) {
            // add a small random perturbation in order to increase diversity of solutions
            nodeGroup.summedWeight += random.nextFloat() * RANDOM_AMOUNT - RANDOM_AMOUNT / 2;
            nodeGroup.barycenter = nodeGroup.summedWeight / nodeGroup.degree;
        }
    }

}
