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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.EdgeType;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Removes all dummy edges and dummy nodes apart from upper compound border dummies from the
 * LayeredGraph. Determines positioning and size of the compound nodes according to the positioning
 * of their dummy nodes. The compound nodes are represented by their compound border dummies.
 * Connects edges to the dummy nodes respecting the positioning of the dummy nodes for ports of the
 * original node.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>A layered graph with fixed node positioning and edge routing. Long edges are joined.</dd>
 * <dt>Postcondition:</dt>
 * <dd>The layered graph contains no more compound side or compound port dummy nodes and no compound
 * dummy or compound side edges either. Position and size for each compound node is set. Edges
 * to/from compound nodes are set.</dd>
 * <dt>Slots:</dt>
 * <dd>After phase 5.</dd>
 * <dt>Same-slot dependencies:</dt>
 * <dd>LongEdgeJoiner.</dd>
 * </dl>
 * 
 * @author ima
 */
public class CompoundGraphRestorer extends AbstractAlgorithm implements ILayoutProcessor {

    /**
     * {@inheritDoc}
     */
    public void process(final LayeredGraph layeredGraph) {
        getMonitor().begin(
                "Remove dummy edges and nodes, set node and edge positions"
                        + "and node size for compound nodes", 1);

        // sort dummy nodes into lists for further processing. One list for the upper compound dummy
        // nodes, which are kept to represent the compound nodes, one for all other dummy nodes,
        // which are to be removed. Fetch all edges on the way and store in a List for further
        // processing.
        List<LNode> compoundNodeList = new LinkedList<LNode>();
        List<LNode> removables = new LinkedList<LNode>();
        List<LEdge> edgeList = new LinkedList<LEdge>();

        for (Layer layer : layeredGraph.getLayers()) {
            for (LNode lnode : layer.getNodes()) {
                NodeType nodeType = lnode.getProperty(Properties.NODE_TYPE);
                if (nodeType == NodeType.UPPER_COMPOUND_BORDER) {
                    compoundNodeList.add(lnode);
                } else {
                    if (nodeType == NodeType.LOWER_COMPOUND_BORDER
                            || nodeType == NodeType.COMPOUND_SIDE
                            || nodeType == NodeType.LOWER_COMPOUND_PORT
                            || nodeType == NodeType.UPPER_COMPOUND_PORT) {
                        removables.add(lnode);
                    }
                }
                for (LEdge edge : lnode.getOutgoingEdges()) {
                    edgeList.add(edge);
                }
            }
        }

        // process compound nodes to determine size and position
        for (LNode compoundNode : compoundNodeList) {

            // get insets and border spacing
            KInsets insets = compoundNode.getProperty(Properties.ORIGINAL_INSETS);
            float ownBorderSpacing = compoundNode.getProperty(Properties.BORDER_SPACING);

            // get positions of the COMPOUND_SIDE dummy nodes at the borders.
            KVector posLeftUpper = findSideNodePos(compoundNode, false, true, layeredGraph);
            KVector posRightUpper = findSideNodePos(compoundNode, false, false, layeredGraph);
            KVector posLeftLower = findSideNodePos(compoundNode, true, true, layeredGraph);

            // set position of compound node (upper left corner)
            compoundNode.getPosition().x = posLeftUpper.x;
            compoundNode.getPosition().y = posLeftUpper.y;

            // set width and height of compound node
            compoundNode.getSize().x = (posRightUpper.x - posLeftUpper.x 
                    + insets.getRight() + ownBorderSpacing);
            compoundNode.getSize().y = (posLeftLower.y - posLeftUpper.y 
                    + insets.getBottom() + ownBorderSpacing);
        }

        // iterate through all edges
        for (LEdge ledge : edgeList) {
            EdgeType edgeType = ledge.getProperty(Properties.EDGE_TYPE);

            // remove dummy edges
            if (edgeType == EdgeType.COMPOUND_DUMMY || edgeType == EdgeType.COMPOUND_SIDE) {
                ledge.getSource().getOutgoingEdges().remove(ledge);
                ledge.getTarget().getIncomingEdges().remove(ledge);
            } else {
                // connect all others to the possibly associated compound node(s)

                // process Source
                // determine source port
                LPort sourcePort = ledge.getSource();
                // determine source node
                LNode sourceNode = sourcePort.getNode();
                // determine nodeType of source
                NodeType sourceNodeType = sourceNode.getProperty(Properties.NODE_TYPE);
                LNode compoundNodeSource = sourceNode.getProperty(Properties.COMPOUND_NODE);

                // process according to source node type. Translate ports of dummy nodes to ports of
                // the
                // compound node.
                switch (sourceNodeType) {
                case LOWER_COMPOUND_BORDER:
                    LPort newPort = transferPort(sourcePort, compoundNodeSource);
                    ledge.setSource(newPort);
                    break;

                case UPPER_COMPOUND_PORT:
                case LOWER_COMPOUND_PORT:
                    LPort newPort2 = transferPort(sourcePort, compoundNodeSource);
                    // in this case, we have to keep the port's origin in mind.
                    newPort2.setProperty(Properties.ORIGIN,
                            sourcePort.getProperty(Properties.ORIGIN));
                    ledge.setSource(newPort2);
                    break;

                // Nothing to be done in case of UPPER_COMPOUND_BORDER or NORMAL
                default:
                    break;
                }

                // process Target
                // determine target port
                LPort targetPort = ledge.getTarget();
                // determine source node
                LNode targetNode = targetPort.getNode();
                // determine nodeType of source
                NodeType targetNodeType = targetNode.getProperty(Properties.NODE_TYPE);
                LNode compoundNodeTarget = targetNode.getProperty(Properties.COMPOUND_NODE);

                // process according to target node type. Translate ports of dummy nodes to ports of
                // the
                // compound node.
                switch (targetNodeType) {
                case LOWER_COMPOUND_BORDER:
                    LPort newPort = transferPort(targetPort, compoundNodeTarget);
                    ledge.setSource(newPort);
                    break;

                case UPPER_COMPOUND_PORT:
                case LOWER_COMPOUND_PORT:
                    LPort newPort2 = transferPort(targetPort, compoundNodeTarget);
                    // in this case, we have to keep the port's origin in mind.
                    newPort2.setProperty(Properties.ORIGIN,
                            targetPort.getProperty(Properties.ORIGIN));
                    ledge.setTarget(newPort2);
                    break;

                // Nothing to be done in case of UPPER_COMPOUND_BORDER or NORMAL
                default:
                    break;
                }
            }
        }
        // remove the now dispensable dummy nodes
        for (LNode removable : removables) {
            List<LNode> layerNodes = removable.getLayer().getNodes();
            layerNodes.remove(removable);
        }

        getMonitor().done();
    }

    /**
     * Creates a new port of the compound node for a given dummy node port.
     * 
     * @param dummyPort
     *            the port to be translated into a compound node port.
     * @param sourceNode
     *            the no
     * @param compoundNode
     * @return
     */
    private LPort transferPort(final LPort dummyPort, final LNode compoundNode) {
        // get node of dummyPort and its node type
        LNode dummyNode = dummyPort.getNode();
        NodeType dummyNodeType = dummyNode.getProperty(Properties.NODE_TYPE);

        // get port side of dummyPort
        PortSide dummyPortSide = dummyPort.getSide();

        // get Layout of the compound node
        KShapeLayout compoundLayout = ((KNode) (compoundNode.getProperty(Properties.ORIGIN)))
                .getData(KShapeLayout.class);

        LPort newPort = new LPort();
        newPort.setNode(compoundNode);

        newPort.copyProperties(dummyPort);
        newPort.getSize().x = dummyPort.getSize().x;
        newPort.getSize().y = dummyPort.getSize().y;

        PortSide newPortSide;

        if (dummyNodeType == NodeType.LOWER_COMPOUND_BORDER
                || dummyNodeType == NodeType.LOWER_COMPOUND_PORT) {
            newPortSide = PortSide.EAST;

        } else {
            // in case of upper compound port
            newPortSide = PortSide.WEST;
        }
        if (newPortSide == PortSide.EAST) {
            // translate from relative position with respect to dummyNode to relative position with
            // respect to compoundNode
            double nodeDiffX = dummyNode.getPosition().x - compoundNode.getPosition().x;
            newPort.getPosition().x = dummyPort.getPosition().x + nodeDiffX;
            if (dummyPortSide == PortSide.EAST) {
                newPort.getPosition().x -= dummyNode.getSize().x;
                newPort.getPosition().x += (compoundLayout.getInsets().getRight() + compoundLayout
                        .getProperty(Properties.BORDER_SPACING));
            }
        } else {
            newPort.getPosition().x = -(dummyPort.getSize().x / 2);
        }
        // as position of dummyPort is relative to dummyNode, compute new relative value with
        // respect to compoundNode.
        double nodeDiffY = dummyNode.getPosition().y - compoundNode.getPosition().y;
        newPort.getPosition().y = dummyPort.getPosition().y + nodeDiffY;

        newPort.setSide(newPortSide);

        return newPort;
    }

    /**
     * Finds and returns the position of the COMPOUND_SIDE dummy node with the upper resp. lower
     * rightmost resp. leftmost position.
     * 
     * @param lnode
     *            left compound border dummy node that starts the compound node in question.
     * @param lower
     *            if true, the lower position is found, if false, the upper one.
     * @param left
     *            if true, the leftmost position is searched for.
     * @param lGraph
     *            the complete LGraph.
     * @return returns the position of the side dummy node with the right- resp. leftmost position,
     *         upper resp. lower.
     */
    private KVector findSideNodePos(final LNode lnode, final boolean lower, final boolean left,
            final LayeredGraph lGraph) {
        Layer layer;
        if (left) {
            layer = lnode.getLayer();
        } else {
            List<Layer> layerList = lGraph.getLayers();
            layer = CompoundSideProcessor.findSpanEnd(lnode, layerList);
        }

        // initialize according to value of lower
        int index = layer.getNodes().size() - 1;
        if (lower) {
            index = 0;
        }

        // find maximum (minimum) index of a compound side node owned by this compound node
        for (LNode layerNode : layer.getNodes()) {
            if (layerNode.getProperty(Properties.NODE_TYPE) == NodeType.COMPOUND_SIDE
                    && layerNode.getProperty(Properties.SIDE_OWNER) == lnode) {
                int test = layerNode.getIndex();
                if (lower) {
                    if (test > index) {
                        index = test;
                    }
                } else {
                    if (test < index) {
                        index = test;
                    }
                }
            }
        }
        KVector ret = layer.getNodes().get(index).getPosition();
        return ret;
    }
}
