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

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.PortType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Determines the node order of a given free layer. Uses heuristic methods to find an ordering that
 * minimizes edge crossings between the given free layer and a neighboring layer with fixed node
 * order. The barycenter heuristic is used here.
 * 
 * @author msp
 * @author cds
 * @author ima
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class BarycenterHeuristic implements ICrossingMinimizationHeuristic {

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
    public int minimizeCrossings(final List<NodeGroup> layer, final int layerIndex,
            final boolean preOrdered, final boolean randomize, final boolean forward) {
        if (layer.size() == 1) {
            NodeGroup nodeGroup = layer.get(0);
            if (nodeGroup.getNodes().length == 1) {
                nodeGroup.degree = 0;
                for (LPort port : nodeGroup.getNode().getPorts(
                        forward ? PortType.INPUT : PortType.OUTPUT)) {
                    nodeGroup.degree += port.getDegree();
                }
                return nodeGroup.degree;
            }
        } else if (layer.size() > 1) {
            int totalEdges = 0;
            
            if (randomize) {
                // Randomize barycenters (we don't need to update the edge count in this case;
                // there are no edges of interest since we're only concerned with this one
                // layer anyway)
                randomizeBarycenters(layer);
            } else {
                // Calculate barycenters and assign barycenters to barycenterless vertices
                totalEdges = calculateBarycenters(layer, forward);
                fillInUnknownBarycenters(layer, preOrdered);
            }
    
            // Sort the vertices according to their barycenters
            Collections.sort(layer);
    
            // Resolve ordering constraints
            constraintResolver.processConstraints(layer, layerIndex);
            
            return totalEdges;
        }

        return 0;
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
     * Try to find appropriate barycenter values for vertices whose barycenter is < 0.
     * 
     * @param nodeGroups
     *            the nodeGroups to fill in barycenters for.
     * @param preOrdered
     *            whether the nodeGroups have been ordered in a previous run.
     */
    private void fillInUnknownBarycenters(final List<NodeGroup> nodeGroups, final boolean preOrdered) {
        // Determine placements for vertices with undefined position value
        if (preOrdered) {
            float lastValue = -1;
            ListIterator<NodeGroup> nodeGroupIterator = nodeGroups.listIterator();

            while (nodeGroupIterator.hasNext()) {
                NodeGroup nodeGroup = nodeGroupIterator.next();
                Float value = nodeGroup.barycenter;

                if (value == null) {
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
                }

                lastValue = value;
            }
        } else {
            // No previous ordering - determine random placement for new nodes
            float maxBary = 0;
            for (NodeGroup nodeGroup : nodeGroups) {
                Float value = nodeGroup.barycenter;
                if (value != null) {
                    maxBary = Math.max(maxBary, value);
                }
            }

            maxBary += 2;
            for (NodeGroup vertex : nodeGroups) {
                if (vertex.barycenter == null) {
                    vertex.barycenter = random.nextFloat() * maxBary - 1;
                }
            }
        }
    }

    /**
     * Calculates the barycenters of the given vertices.
     * 
     * @param nodeGroups
     *            the nodeGroups
     * @param forward
     *            {@code true} if the current sweep moves forward
     * @return the total number of encountered edges.
     */
    private int calculateBarycenters(final List<NodeGroup> nodeGroups, final boolean forward) {
        Set<NodeGroup> workingSet = Sets.newHashSetWithExpectedSize(nodeGroups.size());

        int totalEdges = 0;
        for (NodeGroup nodeGroup : nodeGroups) {
            if (nodeGroup.getNodes().length == 1) {
                // Calculate the nodeGroups's new barycenter (may be null)
                calculateBarycenter(nodeGroup, forward, workingSet);
            }
            // TODO Discuss whether next line should be dependent on the condition as well.
            totalEdges += nodeGroup.degree;
        }

        return totalEdges;
    }
    
    /** the amount of random value to add to each calculated barycenter. */
    private static final float RANDOM_AMOUNT = 0.07f;

    /**
     * Calculates the barycenter of the given single-node-vertex.
     * 
     * @param nodeGroup
     *            the nodeGroup, consisting of a single node
     * @param forward
     *            {@code true} if the current sweep moves forward
     * @param workingSet
     *            a set where vertices whose values are being computed are put into. When this
     *            method is called on a nodeGroup that's already in the set, it immediately returns.
     * @param portPos
     *            position array
     * @return a pair containing the summed port positions of the connected ports as the first, and
     *         the number of connected edges as the second entry.
     */
    private void calculateBarycenter(final NodeGroup nodeGroup, final boolean forward,
            final Set<NodeGroup> workingSet) {

        // Check if the vertex's barycenter was already computed
        if (workingSet.contains(nodeGroup)) {
            return;
        } else {
            workingSet.add(nodeGroup);
        }

        nodeGroup.degree = 0;
        nodeGroup.summedWeight = 0.0f;
        nodeGroup.barycenter = null;
        LNode node = nodeGroup.getNode();

        for (LPort freePort : node.getPorts(forward ? PortType.INPUT : PortType.OUTPUT)) {
            for (LPort fixedPort : freePort.getConnectedPorts()) {
                // If the node the fixed port belongs to is part of the free layer (thus, if
                // we have an in-layer edge), use that node's barycenter calculation instead
                LNode fixedNode = fixedPort.getNode();

                if (fixedNode.getLayer() == node.getLayer()) {
                    // Find the fixed node's vertex and calculate its barycenter
                    NodeGroup fixedNodeGroup = fixedNode.getProperty(Properties.NODE_GROUP);
                    calculateBarycenter(fixedNodeGroup, forward, workingSet);

                    // Update this vertex's values
                    nodeGroup.degree += Math.max(0, fixedNodeGroup.degree - 1);
                    nodeGroup.summedWeight += fixedNodeGroup.summedWeight;
                } else {
                    nodeGroup.summedWeight += portRanks[fixedPort.id];
                }
            }

            nodeGroup.degree += freePort.getDegree();
        }

        // Iterate over the node's barycenter associates
        List<LNode> barycenterAssociates = node.getProperty(Properties.BARYCENTER_ASSOCIATES);
        if (barycenterAssociates != null) {
            for (LNode associate : barycenterAssociates) {
                // Make sure the associate is in the same layer as this node
                if (node.getLayer() == associate.getLayer()) {
                    // Find the associate's vertex and calculate its barycenter
                    NodeGroup associateVertex = associate.getProperty(Properties.NODE_GROUP);
                    calculateBarycenter(associateVertex, forward, workingSet);
    
                    // Update this vertex's values
                    nodeGroup.degree += Math.max(0, associateVertex.degree);
                    nodeGroup.summedWeight += associateVertex.summedWeight;
                }
            }
        }

        if (nodeGroup.degree > 0) {
            float barycenter = nodeGroup.summedWeight / nodeGroup.degree;
            // add a small random perturbation in order to avoid dead ends
            barycenter += random.nextFloat() * RANDOM_AMOUNT - RANDOM_AMOUNT / 2;
            nodeGroup.barycenter = barycenter;
        }
    }

}
