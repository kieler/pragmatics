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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.util.Pair;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Detects and resolves violated constraints. Inspired by
 * <ul>
 * <li>Michael Forster. A fast and simple heuristic for constrained two-level crossing reduction. In
 * <i>Graph Drawing</i>, volume 3383 of LNCS, pp. 206-216. Springer, 2005.</li>
 * </ul>
 * 
 * @author cds
 * @author ima
 */
public class ForsterConstraintResolver implements IConstraintResolver {

    /** the layout units for handling dummy nodes for north / south ports. */
    private Multimap<LNode, LNode> layoutUnits;
    /** the node groups containing only single nodes. */
    private Map<LNode, NodeGroup>[] singleNodeNodeGroups;
    
    /**
     * Constructs a Forster constraint resolver.
     * 
     * @param layoutUnits
     *            a map associating layout units with their respective members
     * @param oneNodeNodeGroups
     *            map of single node vertices for each layer
     */
    public ForsterConstraintResolver(final Multimap<LNode, LNode> layoutUnits,
            final Map<LNode, NodeGroup>[] oneNodeNodeGroups) {
        this.layoutUnits = layoutUnits;
        this.singleNodeNodeGroups = oneNodeNodeGroups;
    }
    
    /**
     * {@inheritDoc}
     */
    public void processConstraints(final List<NodeGroup> nodeGroups, final int layerIndex) {

        // Build the constraints graph
        buildConstraintsGraph(nodeGroups, singleNodeNodeGroups[layerIndex]);

        // Find violated vertices
        Pair<NodeGroup, NodeGroup> violatedConstraint = null;
        while ((violatedConstraint = findViolatedConstraint(nodeGroups)) != null) {
            handleViolatedConstraint(violatedConstraint, nodeGroups);
        }
    }

    /**
     * Builds the vertex graph for the given vertices.
     * 
     * @param nodeGroups
     *            the array of single-node vertices sorted by their barycenter values.
     * @param layerNodeGroups
     *            map mapping the nodes in the current layer to their vertices.
     */
    private void buildConstraintsGraph(final List<NodeGroup> nodeGroups,
            final Map<LNode, NodeGroup> layerNodeGroups) {

        // Reset the constraint fields
        for (NodeGroup nodeGroup : nodeGroups) {
            nodeGroup.getOutgoingConstraints().clear();
            nodeGroup.setIncomingConstraintsCount(0);
        }

        // Iterate through the vertices, adding the necessary constraints
        LNode lastNonDummyNode = null;
        for (NodeGroup nodeGroup : nodeGroups) {
            LNode vertexNode = nodeGroup.getNodes().get(0);

            // Add the constraints given by the vertex's node
            LNode successor = vertexNode.getProperty(Properties.IN_LAYER_SUCCESSOR_CONSTRAINT);
            if (successor != null) {
                NodeGroup successorNodeGroup = layerNodeGroups.get(successor);
                nodeGroup.getOutgoingConstraints().add(successorNodeGroup);
                successorNodeGroup.setIncomingConstraintsCount(successorNodeGroup
                        .getIncomingConstraintsCount() + 1);
            }

            // Check if we're processing a a normal, none-dummy node
            if (vertexNode.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL) {
                // If we already processed another normal, non-dummy node, we need to add
                // constraints from all of that other node's layout unit's vertices to this
                // node's layout unit's vertices
                if (lastNonDummyNode != null) {
                    for (LNode lastUnitNode : layoutUnits.get(lastNonDummyNode)) {
                        NodeGroup lastUnitNodeGroup = layerNodeGroups.get(lastUnitNode);

                        for (LNode currentUnitNode : layoutUnits.get(vertexNode)) {
                            NodeGroup currentUnitNodeGroup = layerNodeGroups.get(currentUnitNode);
                            lastUnitNodeGroup.getOutgoingConstraints().add(currentUnitNodeGroup);
                            currentUnitNodeGroup.setIncomingConstraintsCount(currentUnitNodeGroup
                                    .getIncomingConstraintsCount() + 1);
                        }
                    }
                }

                lastNonDummyNode = vertexNode;
            }
        }
    }

    /**
     * Returns a violated constraint, if any is left.
     * 
     * @param nodeGroups
     *            list of vertices.
     * @return the two vertices whose constraint is violated, or {@code null} if none could be
     *         found. The two vertices are returned in the order they should appear in, not in the
     *         order that violates their constraint.
     */
    private Pair<NodeGroup, NodeGroup> findViolatedConstraint(final List<NodeGroup> nodeGroups) {
        List<NodeGroup> activeNodeGroups = new LinkedList<NodeGroup>();
        Map<NodeGroup, List<NodeGroup>> incoming = new HashMap<NodeGroup, List<NodeGroup>>();

        // Iterate through the constrained vertices
        for (NodeGroup nodeGroup : nodeGroups) {
            // Ignore unconstrained vertices
            if (nodeGroup.getIncomingConstraintsCount() == 0
                    && nodeGroup.getOutgoingConstraints().isEmpty()) {
                continue;
            }

            incoming.put(nodeGroup, new LinkedList<NodeGroup>());
            if (nodeGroup.getIncomingConstraintsCount() == 0) {
                activeNodeGroups.add(nodeGroup);
            }
        }

        // Iterate through the active vertices to find one with violated constraints
        while (!activeNodeGroups.isEmpty()) {
            NodeGroup nodeGroup = activeNodeGroups.remove(0);

            // See if we can find a violated constraint
            for (NodeGroup predecessor : incoming.get(nodeGroup)) {
                if (predecessor.getBarycenter() >= nodeGroup.getBarycenter()) {
                    return new Pair<NodeGroup, NodeGroup>(predecessor, nodeGroup);
                }
            }

            // No violated constraints; add outgoing constraints to the respective incoming list
            for (NodeGroup successor : nodeGroup.getOutgoingConstraints()) {
                List<NodeGroup> successorIncomingList = incoming.get(successor);
                successorIncomingList.add(0, nodeGroup);

                if (successor.getIncomingConstraintsCount() == successorIncomingList.size()) {
                    activeNodeGroups.add(successor);
                }
            }
        }

        // No violated constraints found
        return null;
    }

    /**
     * Handles the case of a violated constraint.
     * 
     * @param violatedConstraint
     *            the violated constraint.
     * @param nodeGroups
     *            the array of vertices.
     */
    private void handleViolatedConstraint(final Pair<NodeGroup, NodeGroup> violatedConstraint,
            final List<NodeGroup> nodeGroups) {

        NodeGroup firstNodeGroup = violatedConstraint.getFirst();
        NodeGroup secondNodeGroup = violatedConstraint.getSecond();

        // Create a new vertex from the two constrain-violating vertices; this also
        // automatically calculates the new vertex's barycenter value
        NodeGroup newNodeGroup = new NodeGroup(violatedConstraint.getFirst(),
                violatedConstraint.getSecond());

        // Iterate through the vertices. Remove the old vertices. Insert the new one
        // according to the barycenter value, thereby keeping the list sorted. Along
        // the way, constraint relationships will be updated
        ListIterator<NodeGroup> nodeGroupIterator = nodeGroups.listIterator();
        boolean alreadyInserted = false;
        while (nodeGroupIterator.hasNext()) {
            NodeGroup nodeGroup = nodeGroupIterator.next();

            if (nodeGroup == firstNodeGroup || nodeGroup == secondNodeGroup) {
                // If the vertex is either the first or the second vertex, remove it
                nodeGroupIterator.remove();
            } else if (!alreadyInserted && nodeGroup.getBarycenter() > newNodeGroup.getBarycenter()) {
                // If we haven't inserted the new vertex into the list already, do that now. Note:
                // we're not calling next() again. This means that during the next iteration, we
                // will again be looking at the current vertex. But then, alreadyInserted will be
                // true and we can look at vertex's outgoing constraints.
                nodeGroupIterator.previous();
                nodeGroupIterator.add(newNodeGroup);

                alreadyInserted = true;
            } else {
                // Check if the vertex has any constraints with the former two vertices
                boolean firstNodeGroupConstraint = nodeGroup.getOutgoingConstraints().remove(
                        firstNodeGroup);
                boolean secondNodeGroupConstraint = nodeGroup.getOutgoingConstraints().remove(
                        secondNodeGroup);

                if (firstNodeGroupConstraint || secondNodeGroupConstraint) {
                    nodeGroup.getOutgoingConstraints().add(newNodeGroup);
                    newNodeGroup.setIncomingConstraintsCount(newNodeGroup
                            .getIncomingConstraintsCount() + 1);
                }
            }
        }

        // If we haven't inserted the new vertex already, do that now
        if (!alreadyInserted) {
            nodeGroups.add(newNodeGroup);
        }
    }

}
