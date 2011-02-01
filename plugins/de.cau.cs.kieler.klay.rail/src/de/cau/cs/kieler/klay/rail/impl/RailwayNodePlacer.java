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
package de.cau.cs.kieler.klay.rail.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.p4nodes.INodePlacer;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * Node placer for railway layout.
 * 
 * @author jjc
 */
public class RailwayNodePlacer extends AbstractAlgorithm implements INodePlacer {

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    public void placeNodes(final LayeredGraph layeredGraph) {
        NodePlacerHelper helper = new NodePlacerHelper(new SimplePatternsImpl());
        getMonitor().begin("Linear segments node placement", 1);
        int layerCount = layeredGraph.getLayers().size();

        // determine starting position for straight-first DFS
        LNode walker = null;
        List<LNode> known = new LinkedList<LNode>();
        Stack<LNode> nextNodes = new Stack<LNode>();
        findStart: for (Layer layer : layeredGraph.getLayers()) {
            for (LNode node : layer.getNodes()) {
                if (node.getProperty(Properties.ENTRY_POINT)) {
                    walker = node;
                    break findStart;
                }
            }
        }
        if (walker == null) {
            throw new IllegalArgumentException("No entry point found!");
        }
        if (walker.getLayer() instanceof RailLayer) {
            ((RailLayer) walker.getLayer()).getRowList().addNodeAtPosition(walker, 0);
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }

        // start straight first DFS
        nextNodes.push(walker);
        known.add(walker);
        List<LNode> trunk = new LinkedList<LNode>();
        boolean stillTrunk = true;

        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (helper.allNeighborsKnown(walker, known)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!helper.allNeighborsKnown(walker, known)) {
                        nextNodes.push(walker);
                    }
                    if (stillTrunk) {
                        trunk.add(walker);
                    }
                    nextNodes.push(target);
                    putTargetInGrid(target, helper.getPositionForNode(targetPort, port));
                    break;
                }
            }
            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !known.contains(target)) {
                        stillTrunk = false;
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!helper.allNeighborsKnown(walker, known)) {
                                nextNodes.push(walker);
                            }
                            int offset = 0;
                            if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_LEFT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset -= helper.getConflictDistanceLeft(target, layerCount);
                                }
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset += helper.getConflictDistanceRight(target, layerCount);
                                }
                            }
                            System.out.println("Placing " + target.getName() + " at pos "
                                    + (offset + helper.getPositionForNode(targetPort, port)));
                            putTargetInGrid(target,
                                    offset + helper.getPositionForNode(targetPort, port));
                            if (walker.getProperty(Properties.NODE_TYPE).equals(
                                    NodeType.SWITCH_LEFT)) {
                                placeLeftFromTrunk(target, walker, layerCount);
                            }
                        }
                    }
                }
            }

        }

        // apply grid to graph
        for (Layer layer : layeredGraph.getLayers()) {
            if (layer instanceof RailLayer) {
                gridToAbsolutePosition((RailLayer) layer, getMinimalPosition(layeredGraph));
            } else {
                throw new IllegalArgumentException("Only works with RailLayers!");
            }
        }
        getMonitor().done();
    }

    private void placeLeftFromTrunk(final LNode first, final LNode pre, final int layerCount) {
        List<LNode> known = new LinkedList<LNode>();
        Stack<LNode> nextNodes = new Stack<LNode>();
        NodePlacerHelper helper = new NodePlacerHelper(new SimplePatternsImpl());
        LNode walker = first;
        nextNodes.push(walker);
        known.add(walker);
        known.add(pre);

        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (helper.allNeighborsKnown(walker, known)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LNode target = port.getEdges().get(0).getTarget().getNode();
                LPort targetPort = port.getEdges().get(0).getTarget();
                if ((port.getNode().getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_RIGHT)
                        && port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!helper.allNeighborsKnown(walker, known)) {
                        nextNodes.push(walker);
                    }
                    nextNodes.push(target);
                    int offset = 0;
                    if (port.getNode().getProperty(Properties.NODE_TYPE)
                            .equals(NodeType.SWITCH_LEFT)) {
                        if (target.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)
                                || target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_RIGHT)) {
                            offset -= helper.getConflictDistanceLeft(target, layerCount);
                        }
                    } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                            .equals(NodeType.SWITCH_RIGHT)) {
                        if (target.getProperty(Properties.NODE_TYPE).equals(NodeType.SWITCH_LEFT)
                                || target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_RIGHT)) {
                            offset += helper.getConflictDistanceRight(target, layerCount);
                        }
                    }
                    System.out.println("Placing " + target.getName() + " at pos "
                            + (offset + helper.getPositionForNode(targetPort, port)));
                    putTargetInGrid(target, offset + helper.getPositionForNode(targetPort, port));
                    break;
                }
            }
            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LNode target = port.getEdges().get(0).getTarget().getNode();
                    LPort targetPort = port.getEdges().get(0).getTarget();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)) {
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!helper.allNeighborsKnown(walker, known)) {
                                nextNodes.push(walker);
                            }
                            nextNodes.push(target);
                            int offset = 0;
                            if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_LEFT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset -= helper.getConflictDistanceLeft(target, layerCount);
                                }
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset += helper.getConflictDistanceRight(target, layerCount);
                                }
                            }
                            System.out.println("Placing " + target.getName() + " at pos "
                                    + (offset + helper.getPositionForNode(targetPort, port)));
                            putTargetInGrid(target,
                                    offset + helper.getPositionForNode(targetPort, port));
                        }
                    } else {
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!helper.allNeighborsKnown(walker, known)) {
                                nextNodes.push(walker);
                            }
                            nextNodes.push(target);
                            putTargetInGrid(target, helper.getPositionForNode(targetPort, port));
                            break;
                        }
                    }
                }
            }

        }
    }

    private void putTargetInGrid(final LNode target, final int position) {
        RailRow targetRow;
        if (target.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        targetRow.addNodeAtPosition(target, position);
    }

    private int getMinimalPosition(final LayeredGraph graph) {
        int result = Integer.MAX_VALUE;
        for (Layer layer : graph.getLayers()) {
            if (layer instanceof RailLayer) {
                int i = ((RailLayer) layer).getRowList().getMinimalPosition();
                if (i < result) {
                    result = i;
                }
            } else {
                // TODO: maybe validate once, gets annoying
            }
        }
        return result;
    }

    private void gridToAbsolutePosition(final RailLayer layer, final int minPos) {
        float spacing = layer.getGraph().getProperty(Properties.OBJ_SPACING);
        float borspacing = layer.getGraph().getProperty(Properties.BOR_SPACING);
        double value = borspacing;
        for (LNode node : layer.getRowList().getNodesOrderedByPosition()) {
            int position = layer.getRowList().getPosition(node);
            int offset = (-1) * minPos;
            value = offset * (node.getSize().y + spacing) + position * (node.getSize().y + spacing);
            node.getPos().y = value;
            System.out.println("Setting y of " + node.toString() + " to " + value);
            System.out.println("Position was " + position);
        }
    }

}
