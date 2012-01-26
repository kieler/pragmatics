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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Multimap;

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
 */
public class BarycenterHeuristic implements ICrossingMinimizationHeuristic {

    private IPortDistributor portDistributor;

    private Random random;

    /**
     * Constructs a BarycenterHeuristic with the given portDistributor, selected by the
     * LayerSweepCrossingMinimizer.
     * 
     * @param selPortDistributor
     *            the port distributor chosen by the LayerSweepCrossingMinimizer.
     * @param graphRandom
     *            the random number generator.
     */
    public BarycenterHeuristic(final IPortDistributor selPortDistributor, final Random graphRandom) {
        portDistributor = selPortDistributor;
        random = graphRandom;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public int minimizeCrossings(final List<NodeGroup> layerNodeGroups,
            final Multimap<LNode, LNode> layoutUnits, final int layerIndex,
            final boolean preOrdered, final boolean randomize, final boolean forward,
            final float[] portPos, final Map<LNode, NodeGroup>[] singleNodeNodeGroups) {

        // Ignore empty free layers
        if (layerNodeGroups.isEmpty()) {
            return 0;
        }

        // Barycenters!
        int totalEdges = 0;
        if (randomize) {
            // Randomize barycenters (we don't need to update the edge count in this case;
            // there are no edges of interest since we're only concerned with this one
            // layer anyway)
            randomizeBarycenters(layerNodeGroups);
        } else {
            // Calculate barycenters and assign barycenters to barycenterless vertices
            totalEdges = calculateBarycenters(layerNodeGroups, singleNodeNodeGroups[layerIndex],
                    forward, portPos);
            fillInUnknownBarycenters(layerNodeGroups, preOrdered);
        }

        // Sort the vertices according to their barycenters
        Collections.sort(layerNodeGroups);

        IConstraintResolver constraintResolver = new ForsterConstraintResolver();

        constraintResolver.processConstraints(layerNodeGroups, layerIndex, random,
                singleNodeNodeGroups, layoutUnits);

        // Prepare an array of the nodes of the single-node-NodeGroups for the port rank
        // calculation.
        LinkedList<LNode> layerSingles = new LinkedList<LNode>();
        for (NodeGroup nodeGroup : layerNodeGroups) {
            List<LNode> nodeGroupNodes = nodeGroup.getNodes();
            if (nodeGroupNodes.size() == 1) {
                layerSingles.add(nodeGroupNodes.get(0));
            }
        }
        LNode[] layerSingleNodes = new LNode[layerSingles.size()];
        for (int i = 0; i < layerSingles.size(); i++) {
            layerSingleNodes[i] = layerSingles.get(i);
        }
        portDistributor.calculatePortRanks(layerSingleNodes);

        return totalEdges;
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
            if (nodeGroup.getNodes().size() < 2) {
                nodeGroup.setBarycenter(random.nextFloat());
                nodeGroup.setSummedWeight(nodeGroup.getBarycenter());
                nodeGroup.setDegree(1);
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
                float value = nodeGroup.getBarycenter();

                if (value < 0) {
                    float nextValue = lastValue + 1;

                    if (nodeGroupIterator.hasNext()) {
                        Iterator<NodeGroup> nextNodeGroupIterator = nodeGroups
                                .listIterator(nodeGroupIterator.nextIndex());

                        while (nextNodeGroupIterator.hasNext()) {
                            float x = nextNodeGroupIterator.next().getBarycenter();
                            if (x >= 0) {
                                nextValue = x;
                                break;
                            }
                        }
                    }

                    value = (lastValue + nextValue) / 2;
                    nodeGroup.setBarycenter(value);
                }

                lastValue = value;
            }
        } else {
            // No previous ordering - determine random placement for new nodes
            float maxBary = 0;
            for (NodeGroup nodeGroup : nodeGroups) {
                maxBary = Math.max(maxBary, nodeGroup.getBarycenter());
            }

            maxBary += 2;
            for (NodeGroup vertex : nodeGroups) {
                if (vertex.getBarycenter() < 0) {
                    vertex.setBarycenter(random.nextFloat() * maxBary - 1);
                }
            }
        }
    }

    /**
     * Calculates the barycenters of the given vertices.
     * 
     * @param nodeGroups
     *            the nodeGroups.
     * @param layerNodeGroups
     *            map of the layer's nodes to their single-node nodeGroups.
     * @param forward
     *            {@code true} if the current sweep moves forward.
     * @param portPos
     * @return the total number of encountered edges.
     */
    private int calculateBarycenters(final List<NodeGroup> nodeGroups,
            final Map<LNode, NodeGroup> layerNodeGroups, final boolean forward,
            final float[] portPos) {

        Set<NodeGroup> workingSet = new HashSet<NodeGroup>();

        int totalEdges = 0;
        for (NodeGroup nodeGroup : nodeGroups) {
            if (nodeGroup.getNodes().size() < 2) {
                // Calculate the nodeGroups's new barycenter (may be -1)
                calculateBarycenter(nodeGroup, layerNodeGroups, forward, workingSet, portPos);
            }
            // TODO: Discuss, if next line should be dependent on the condition as well.
            totalEdges += nodeGroup.getDegree();
        }

        return totalEdges;
    }

    /**
     * Calculates the barycenter of the given single-node-vertex.
     * 
     * @param nodeGroup
     *            the nodeGroup, consisting of a single node.
     * @param layerNodeGroups
     *            map of the layer's nodes to their single-node nodeGroups.
     * @param forward
     *            {@code true} if the current sweep moves forward.
     * @param workingSet
     *            a set where vertices whose values are being computed are put into. When this
     *            method is called on a nodeGroup that's already in the set, it immediately returns.
     * @param portPos
     *            position array.
     * @return a pair containing the summed port positions of the connected ports as the first, and
     *         the number of connected edges as the second entry.
     */
    private void calculateBarycenter(final NodeGroup nodeGroup,
            final Map<LNode, NodeGroup> layerNodeGroups, final boolean forward,
            final Set<NodeGroup> workingSet, final float[] portPos) {

        // Check if the vertex's barycenter was already computed
        if (workingSet.contains(nodeGroup)) {
            return;
        } else {
            workingSet.add(nodeGroup);
        }

        nodeGroup.setDegree(0);
        nodeGroup.setSummedWeight(0.0f);
        nodeGroup.setBarycenter(-1.0f);
        LNode node = nodeGroup.getNodes().get(0);

        for (LPort freePort : node.getPorts(forward ? PortType.INPUT : PortType.OUTPUT)) {
            for (LPort fixedPort : freePort.getConnectedPorts()) {
                // If the node the fixed port belongs to is part of the free layer (thus, if
                // we have an in-layer edge), use that node's barycenter calculation instead
                LNode fixedNode = fixedPort.getNode();

                if (fixedNode.getLayer() == node.getLayer()) {
                    // Find the fixed node's vertex and calculate its barycenter
                    NodeGroup fixedNodeGroup = layerNodeGroups.get(fixedNode);
                    calculateBarycenter(fixedNodeGroup, layerNodeGroups, forward, workingSet,
                            portPos);

                    // Update this vertex's values
                    nodeGroup.setDegree(nodeGroup.getDegree()
                            + Math.max(0, fixedNodeGroup.getDegree() - 1));
                    nodeGroup.setSummedWeight(nodeGroup.getSummedWeight()
                            + fixedNodeGroup.getSummedWeight());
                } else {
                    nodeGroup.setSummedWeight(nodeGroup.getSummedWeight() + portPos[fixedPort.id]);
                }
            }

            nodeGroup.setDegree(nodeGroup.getDegree() + freePort.getDegree());
        }

        // Iterate over the node's barycenter associates
        List<LNode> barycenterAssociates = node.getProperty(Properties.BARYCENTER_ASSOCIATES);
        if (barycenterAssociates != null) {
            for (LNode associate : barycenterAssociates) {
                // Make sure the associate is in the same layer as this node
                if (node.getLayer() != associate.getLayer()) {
                    continue;
                }

                // Find the associate's vertex and calculate its barycenter
                NodeGroup associateVertex = layerNodeGroups.get(associate);
                calculateBarycenter(associateVertex, layerNodeGroups, forward, workingSet, portPos);

                // Update this vertex's values
                nodeGroup.setDegree(nodeGroup.getDegree()
                        + Math.max(0, associateVertex.getDegree()));
                nodeGroup.setSummedWeight(nodeGroup.getSummedWeight()
                        + associateVertex.getSummedWeight());
            }
        }

        if (nodeGroup.getDegree() > 0) {
            nodeGroup.setBarycenter(nodeGroup.getSummedWeight() / nodeGroup.getDegree());
        }
    }

}
