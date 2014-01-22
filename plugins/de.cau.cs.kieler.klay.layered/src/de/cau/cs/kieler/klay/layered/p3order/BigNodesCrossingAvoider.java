/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
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
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * This class contains methods that can be used during the crossing minimization phase to avoid
 * node/edge overlaps in the presence of big nodes.
 * 
 * @author uru
 */
public class BigNodesCrossingAvoider {

    private boolean debug = false;

    // CHECKSTYLEOFF MethodLength
    /**
     * TODO document
     * 
     * Precondition: Layer is sorted according to barycenter values.
     * 
     * Postcondition: If true is returned, the layer is sorted according to the possibly altered
     * barycenter values.
     * 
     * 
     * @param layer
     *            the current layer's node groups.
     * @param forward
     *            whether the current sweep is a forward sweep.
     * @return true if either no or a valid adjustment could be made
     */
    public boolean adjustBigNodeBarycenters(final List<NodeGroup> layer, final boolean forward) {

        // use an array list so we can quickly access a big node's predecessor (in terms of
        // barycenter value)
        ArrayList<NodeGroup> intNodeGroups = Lists.newArrayList(layer);
        Collections.sort(intNodeGroups);

        boolean containsBignodes = false;

        if (debug) {
            System.out.println("Processing Layer (" + (forward ? "forward" : "backward") + ") "
                    + intNodeGroups);
        }

        // treat all big nodes of the current layer
        for (int i = 0; i < intNodeGroups.size(); i++) {
            NodeGroup nodeGroup = intNodeGroups.get(i);
            // during backward sweep also consider the initial big node
            if (isBigNodeGroup(nodeGroup) || (!forward && isInitialBigNodeGroup(nodeGroup))) {

                containsBignodes = true;
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
                if (!forward && !isBigNodeGroup(bigNodePreGroup)) {
                    continue;
                }

                // now compare all edges with the big node edge for an interleaving
                for (NodeGroup innerGroup : layer) {
                    if (nodeGroup.equals(innerGroup)) {
                        continue; // not with ourself
                    }
                    LNode layerNode = innerGroup.getNode();
                    NodeGroup layerGroup = innerGroup;

                    // get all edges for the current node in the current layer either for a forward
                    // or backward sweep
                    Iterable<LEdge> layerEdges =
                            forward ? layerNode.getIncomingEdges() : layerNode.getOutgoingEdges();
                    // System.out.println("\tChecking edges: " + layerEdges);

                    // delta, di and dp are used to check invalid cases,
                    // where a crossing cannot be resolved
                    double delta = 0;

                    for (LEdge layerEdge : layerEdges) {

                        // ignore in-layer edges (handled separately later)
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
                            if (intNodeGroups.size() > i + 1) {
                                dp = 1 + intNodeGroups.get(i + 1).barycenter 
                                        - bigNodeLayerGroup.barycenter;
                            }
                            float di = 1 + bigNodeLayerGroup.barycenter - layerGroup.barycenter;

                            // add to big node's barycenter
                            double old = layerGroup.barycenter;
                            layerGroup.barycenter = bigNodeLayerGroup.barycenter + (dp / di);

                            if (debug) {
                                System.out.println("Adapting1: " + bigNode + " " + layerNode + " "
                                        + bigNodeLayerGroup.barycenter + " "
                                        + layerGroup.barycenter + " old(" + old + ")");
                                System.out.println("\t\tComparing: PreLayer ("
                                        + bigNodePreGroup.getNode() + ", "
                                        + preLayerGroup.getNode() + ") Values "
                                        + bigNodePreGroup.barycenter + " "
                                        + preLayerGroup.barycenter);
                                System.out.println("\t\tRelevant Edges " + layerEdge + " "
                                        + layerEdge.getSource().getNode().getLayer().id + " "
                                        + layerEdge.getTarget().getNode().getLayer().id);
                            }

                            if (bigNodeLayerGroup.barycenter - layerGroup.barycenter > delta) {
                                // couldn't find a valid alteration for this node
                                if (debug) {
                                    System.out.println("\tUnsatisfiable Case 1.");
                                }
                                return false;
                            }

                            // remember the delta between the big node and the new node position
                            delta += bigNodeLayerGroup.barycenter - layerGroup.barycenter;

                        } else if (bigNodeLayerGroup.barycenter < layerGroup.barycenter
                                && bigNodePreGroup.barycenter > preLayerGroup.barycenter) {
                            // CASE 2. big node's barycenter is smaller

                            float dp = 2f; // a default diff in case this is the layer's last node
                            if (i > 0) {
                                dp = 1 + bigNodeLayerGroup.barycenter 
                                        - intNodeGroups.get(i - 1).barycenter;
                            }
                            float di = 1 + layerGroup.barycenter - bigNodeLayerGroup.barycenter;

                            // subtract from big node's barycenter
                            layerGroup.barycenter = bigNodeLayerGroup.barycenter - (dp / di);

                            if (bigNodeLayerGroup.barycenter - layerGroup.barycenter < delta) {
                                // couldn't find a valid alteration for this node
                                if (debug) {
                                    System.out.println("\tUnsatisfiable Case 1.");
                                }
                                return false;
                            }

                            if (debug) {
                                System.out.println("Adapting2: " + bigNode + " " + layerNode + " "
                                        + layerGroup.barycenter);
                            }

                            // remember the delta between the big node and the new node position
                            delta -= bigNodeLayerGroup.barycenter - layerGroup.barycenter;
                        }
                    }
                }

            }
        }

        // successfully, either no or only valid alterations were made to the layer
        if (containsBignodes) {
            Collections.sort(layer);
        }

        return true;
    }

    /**
     * Precondition: The layer has to be sorted according to the barycenter values.
     * 
     * This method iterates through all nodes of the layer top-down. In-layer edges are recorded as
     * soon as they 'open'. As soon as a part of a big node is reached (big node dummy or first big
     * node element) the 'open' edges are checked. For each open edge, two constraints are
     * introduced assuring that both nodes of the in-layer edge are either placed above or below the
     * big node within the layer. The decision of where to place them is based on the current
     * position of the 'normal' node (one node of an in-layer edge is always a dummy node).
     * 
     * Remark: Special care has to be taken for the following: 
     *  - The big node itself is part of the in-layer edge. 
     *  - In-layer edges 'around' the first or last part of a big node might not
     *    require constraints (WEST OUT, first; EAST IN, last).
     * 
     * @param layer
     *            the layer to handle.
     */
    public void generateInLayerConstraints(final List<NodeGroup> layer) {

        Set<LNode> oldNodes = Sets.newHashSet();
        Set<LEdge> openInLayerEdges = Sets.newHashSet();

        // clear old constraints
        // we have to clear old constraints, as constraints from two successive runs might
        // contradict each other. This is due to the fact that we consider the current ordering
        // within the layer to calculate the constraints
        for (NodeGroup group : layer) {
            for (LNode current : group.getNodes()) {
                current.getProperty(Properties.BIG_NODE_IN_LAYER_SUCCESSOR_CONSTRAINTS).clear();
            }
        }

        // collect in-layer edges
        for (NodeGroup group : layer) {
            for (LNode current : group.getNodes()) {
                for (LPort p : current.getPorts()) {
                    for (LEdge e : p.getConnectedEdges()) {

                        // close edges marked open
                        openInLayerEdges.remove(e);

                        // collect all not yet handled in-layer edges
                        if (isInLayerEdge(e)) {
                            LNode other = getOther(e, current);
                            if (!oldNodes.contains(other)) {
                                // if the edges opposite node is not yet handled, mark it as open
                                openInLayerEdges.add(e);
                            }
                        }
                    }
                }
            }

            // if the current node is a bignode
            if (isBigNodeGroup(group)) {

                // add constraints for open edges
                LNode bignode = group.getNode();

                for (LEdge e : openInLayerEdges) {
                    LNode src = e.getSource().getNode();
                    LNode tgt = e.getTarget().getNode();

                    // if the bignode is part of the in-layer-edge we don't need a constraint
                    if (src.equals(bignode) || tgt.equals(bignode)) {
                        continue;
                    }

                    // which one of the two was above the bignode?
                    boolean srcIsKnown = oldNodes.contains(src);

                    // which one of the two is the normal node
                    boolean srcIsNormal = isNormalNode(src);

                    // special treatment for the first and last big node
                    if (srcIsNormal) {
                        // means an outgoing edge on the west side
                        // thus ignore the initial big node, as the in-layer edge will be routed
                        // 'left' of the first big node dummy and won't introduce a crossing
                        if (isFirstBigNode(bignode)) {
                            continue;
                        }
                    } else {
                        // means incoming edge on the east side
                        // thus ignore the last big node, as the in-layer edge will be routed
                        // 'right' of the last node dummy and won't introduce a crossing
                        if (isLastBigNode(bignode)) {
                            continue;
                        }
                    }

                    // the node and its in-layer dummy are constrained
                    // to the regular node's relative position to the bignode
                    if ((srcIsKnown && srcIsNormal) || (!srcIsKnown && !srcIsNormal)) {
                        // nodes above bignode
                        addInLayerConstraint(src, bignode);
                        addInLayerConstraint(tgt, bignode);
                    } else {
                        // bignode above nodes
                        addInLayerConstraint(bignode, src);
                        addInLayerConstraint(bignode, tgt);
                    }

                    if (debug) {
                        System.out.println("\tIntroducing constraint: " + bignode + " " + src + " "
                                + tgt + " above "
                                + ((srcIsKnown && srcIsNormal) || (!srcIsKnown && !srcIsNormal)));
                    }

                }
            }

            // remember the nodes
            for (LNode node : group.getNodes()) {
                oldNodes.add(node);
            }

        }
    }

    /*---------------------------------------------------
     * Internal Convenience Methods
     */

    private boolean isInLayerEdge(final LEdge e) {
        return e.getSource().getNode().getLayer().getIndex() == e.getTarget().getNode().getLayer()
                .getIndex();
    }

    private LNode getOther(final LEdge edge, final LNode current) {
        LNode src = edge.getSource().getNode();
        if (src.equals(current)) {
            return edge.getTarget().getNode();
        } else {
            return src;
        }
    }

    private boolean isBigNodeGroup(final NodeGroup group) {
        return group.getNodes().length == 1 && isBigNode(group.getNode());
    }

    private boolean isInitialBigNodeGroup(final NodeGroup group) {
        return isBigNodeGroup(group) && isFirstBigNode(group.getNode());
    }

    private boolean isBigNode(final LNode node) {
        return (node.getProperty(Properties.NODE_TYPE) == NodeType.BIG_NODE)
                || node.getProperty(Properties.BIG_NODE_INITIAL);
    }

    private boolean isFirstBigNode(final LNode node) {
        return node.getProperty(Properties.BIG_NODE_INITIAL);
    }

    private boolean isLastBigNode(final LNode node) {
        boolean nextIsBignode = false;
        for (LEdge e : node.getOutgoingEdges()) {
            if (e.getTarget().getNode().getProperty(Properties.NODE_TYPE) == NodeType.BIG_NODE) {
                nextIsBignode = true;
                break;
            }
        }
        return !nextIsBignode;
    }

    private boolean isNormalNode(final LNode node) {
        return node.getProperty(Properties.NODE_TYPE) == NodeType.NORMAL;
    }

    private void addInLayerConstraint(final LNode above, final LNode below) {
        above.getProperty(Properties.BIG_NODE_IN_LAYER_SUCCESSOR_CONSTRAINTS).add(below);
    }
}
