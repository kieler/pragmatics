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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.rail.IPatterns;
import de.cau.cs.kieler.klay.rail.IPlacement;
import de.cau.cs.kieler.klay.rail.Properties;
import de.cau.cs.kieler.klay.rail.graph.RailLayer;
import de.cau.cs.kieler.klay.rail.graph.RailRow;
import de.cau.cs.kieler.klay.rail.options.NodeType;
import de.cau.cs.kieler.klay.rail.options.PortType;

/**
 * Helper class for railway node placement.
 * 
 * @author jjc
 * 
 */
public class NodePlacerHelper {

    /**
     * Empty default constructor.
     */
    public NodePlacerHelper() {
    }

    /**
     * Helper method for DFS, check whether all neighbors where visited.
     * 
     * @param node
     *            The node which neighbors have to be checked.
     * @param known
     *            The list of known nodes.
     * @return True if all neighbors are known, false else.
     */
    public boolean allNeighborsKnown(final LNode node, final List<LNode> known) {
        boolean result = true;
        for (LPort port : node.getPorts()) {
            result &= known.contains(port.getOutgoingEdges().get(0).getTarget().getNode());
        }
        return result;
    }

    /**
     * Placement method which placed nodes in a DFS like manner.
     * 
     * @param first
     *            The node to start from.
     * @param layerCount
     *            The amount of layers.
     * @param place
     *            The method for placement (e.g. real placement or simulated placement).
     */
    public void placeNodesDFS(final LNode first, final int layerCount, final IPlacement place) {
        LNode walker = first;
        List<LNode> known = new LinkedList<LNode>();
        Stack<LNode> nextNodes = new Stack<LNode>();

        nextNodes.push(walker);
        known.add(walker);
        List<LNode> trunk = new LinkedList<LNode>();
        boolean stillTrunk = true;

        while (!nextNodes.isEmpty()) {
            walker = nextNodes.pop();
            boolean found = false;
            if (allNeighborsKnown(walker, known)) {
                continue;
            }
            for (LPort port : walker.getPorts()) {
                LPort targetPort = port.getOutgoingEdges().get(0).getTarget();
                LNode target = targetPort.getNode();
                if ((port.getProperty(Properties.PORT_TYPE).equals(PortType.STRAIGHT) || port
                        .getProperty(Properties.PORT_TYPE).equals(PortType.STUMP))
                        && !known.contains(target)) {
                    found = true;
                    known.add(target);
                    if (!allNeighborsKnown(walker, known)) {
                        nextNodes.push(walker);
                    }
                    if (stillTrunk) {
                        trunk.add(walker);
                    }
                    nextNodes.push(target);
                    place.place(targetPort, port, 0);
                    break;
                }
            }
            if (!found) {
                for (LPort port : walker.getPorts()) {
                    LPort targetPort = port.getOutgoingEdges().get(0).getTarget();
                    LNode target = targetPort.getNode();
                    if (port.getProperty(Properties.PORT_TYPE).equals(PortType.BRANCH)
                            && !known.contains(target)) {
                        stillTrunk = false;
                        if (!known.contains(target)) {
                            known.add(target);
                            if (!allNeighborsKnown(walker, known)) {
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
                                    offset -= getConflictDistanceLeft(target, layerCount);
                                }
                            } else if (port.getNode().getProperty(Properties.NODE_TYPE)
                                    .equals(NodeType.SWITCH_RIGHT)) {
                                if (target.getProperty(Properties.NODE_TYPE).equals(
                                        NodeType.SWITCH_LEFT)
                                        || target.getProperty(Properties.NODE_TYPE).equals(
                                                NodeType.SWITCH_RIGHT)) {
                                    offset += getConflictDistanceRight(target, layerCount);
                                }
                            }
                            place.place(targetPort, port, offset);
                        }
                    }
                }
            }

        }
    }

    /**
     * Method for looking ahead the deepmost node if in a left branch.
     * 
     * @param first
     *            Node to start from.
     * @param layerCount
     *            Number of layers.
     * @return The highest conflict with already existing layers.
     */
    public int getConflictDistanceLeft(final LNode first, final int layerCount) {
        List<Integer> maxNewPosByLayer = getMaxNewPosByLayer(first, layerCount);
        List<Integer> minPosByLayer = getMinPosByLayer(first.getLayer().getGraph());
        int result = 0;
        for (int i = 0; i < maxNewPosByLayer.size(); i++) {
            if (maxNewPosByLayer.get(i) >= minPosByLayer.get(i)) {
                int conflict = maxNewPosByLayer.get(i) - minPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        System.out.println("Found a left conflict distance of " + result);
        return result;
    }

    /**
     * Method for looking ahead the topmost node if in a left branch.
     * 
     * @param first
     *            Node to start from.
     * @param layerCount
     *            Number of layers.
     * @return The highest conflict with already existing layers.
     */
    public int getConflictDistanceRight(final LNode first, final int layerCount) {
        List<Integer> minNewPosByLayer = getMinNewPosByLayer(first, layerCount);
        List<Integer> maxPosByLayer = getMaxPosByLayer(first.getLayer().getGraph());
        int result = 0;
        for (int i = 0; i < minNewPosByLayer.size(); i++) {
            if (minNewPosByLayer.get(i) <= maxPosByLayer.get(i)) {
                int conflict = maxPosByLayer.get(i) - minNewPosByLayer.get(i);
                if (conflict > result) {
                    result = conflict;
                }
            }
        }
        System.out.println("Found a right conflict distance of " + result);
        return result;
    }

    /**
     * Method for finding the minimal position of nodes in each layer.
     * 
     * @param layeredGraph
     *            The graph to investigate.
     * @return A list with all minimal positions by layer.
     */
    private List<Integer> getMinPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMinimalPosition());
            }
        }
        return result;
    }

    /**
     * Method for finding the maximal position of nodes in each layer.
     * 
     * @param layeredGraph
     *            The graph to investigate.
     * @return A list with all maximal positions by layer.
     */
    private List<Integer> getMaxPosByLayer(final LayeredGraph layeredGraph) {
        List<Integer> result = new ArrayList<Integer>(layeredGraph.getLayers().size());
        for (Layer layer : layeredGraph) {
            if (layer instanceof RailLayer) {
                result.add(((RailLayer) layer).getRowList().getMaximalPosition());
            }
        }
        return result;
    }

    /**
     * A method for looking ahead the possible minimal position of a branch in each layer.
     * 
     * @param first The node to start from.
     * @param layerCount The number of layers.
     * @return A list with all possible new minimal positions by layer.
     */
    private List<Integer> getMinNewPosByLayer(final LNode first, final int layerCount) {
        PlacementRight placement = new PlacementRight(first, layerCount);

        this.placeNodesDFS(first, layerCount, placement);

        return placement.getMinNewPosByLayer();
    }

    /**
     * A method for looking ahead the possible maximal position of a branch in each layer.
     * 
     * @param first The node to start from.
     * @param layerCount The number of layers.
     * @return A list with all possible new maximal positions by layer.
     */
    private List<Integer> getMaxNewPosByLayer(final LNode first, final int layerCount) {
        PlacementLeft placement = new PlacementLeft(first, layerCount);

        this.placeNodesDFS(first, layerCount, placement);

        return placement.getMaxNewPosByLayer();
    }

    /**
     * Gives a position for a node, given by a certain pattern.
     * 
     * @param targetPort The node which shall be placed.
     * @param sourcePort The predecessor node.
     * @param patterns A pattern which gives rules to place the nodes.
     * @return A position for the new node.
     */
    public static int getPositionForNode(final LPort targetPort, final LPort sourcePort,
            final IPatterns patterns) {
        return NodePlacerHelper.getPositionForNode(targetPort, sourcePort, Integer.MIN_VALUE,
                patterns);
    }

    /**
     * Gives a position for a node, given by a certain pattern.
     * The method also assumes an estimated position for the predecessor node.
     * 
     * @param targetPort The node which shall be placed.
     * @param sourcePort The predecessor node.
     * @param patterns A pattern which gives rules to place the nodes.
     * @param sourcePosition The estimated position (simulated placement) of the source node.
     * @return A position for the new node.
     */
    public static int getPositionForNode(final LPort targetPort, final LPort sourcePort,
            final int sourcePosition, final IPatterns patterns) {
        RailRow targetRow;
        RailRow sourceRow;
        LNode source = sourcePort.getNode();
        LNode target = targetPort.getNode();
        if (target.getLayer() instanceof RailLayer && source.getLayer() instanceof RailLayer) {
            targetRow = ((RailLayer) target.getLayer()).getRowList();
            sourceRow = ((RailLayer) source.getLayer()).getRowList();
        } else {
            throw new IllegalArgumentException("Only works with RailLayers!");
        }
        int sourcePos = sourcePosition;
        if (sourcePos == Integer.MIN_VALUE) {
            sourcePos = sourceRow.getPosition(source);
        }
        switch (source.getProperty(Properties.NODE_TYPE)) {
        case BREACH_OR_CLOSE:
            switch (target.getProperty(Properties.NODE_TYPE)) {
            case BREACH_OR_CLOSE:
                return patterns.breachBreach(targetRow.getOccupiedPositions(), sourcePos);
            case SWITCH_LEFT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchLeftBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchLeftStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchLeftStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            case SWITCH_RIGHT:
                switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                case BRANCH:
                    return patterns.breachSwitchRightBranch(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STRAIGHT:
                    return patterns.breachSwitchRightStraight(targetRow.getOccupiedPositions(),
                            sourcePos);
                case STUMP:
                    return patterns.breachSwitchRightStump(targetRow.getOccupiedPositions(),
                            sourcePos);
                }
                break;
            }
            break;
        case SWITCH_LEFT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStraightSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStraightSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchLeftStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchLeftStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchLeftStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchLeftStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            }
            break;
        case SWITCH_RIGHT:
            switch (sourcePort.getProperty(Properties.PORT_TYPE)) {
            case BRANCH:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightBranchBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightBranchSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STRAIGHT:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStraightBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStraightSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightBranchSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightBranchSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            case STUMP:
                switch (target.getProperty(Properties.NODE_TYPE)) {
                case BREACH_OR_CLOSE:
                    return patterns.switchRightStumpBreach(targetRow.getOccupiedPositions(),
                            sourcePos);
                case SWITCH_LEFT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchLeftBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchLeftStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchLeftStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                case SWITCH_RIGHT:
                    switch (targetPort.getProperty(Properties.PORT_TYPE)) {
                    case BRANCH:
                        return patterns.switchRightStumpSwitchRightBranch(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STRAIGHT:
                        return patterns.switchRightStumpSwitchRightStraight(
                                targetRow.getOccupiedPositions(), sourcePos);
                    case STUMP:
                        return patterns.switchRightStumpSwitchRightStump(
                                targetRow.getOccupiedPositions(), sourcePos);
                    }
                    break;
                }
                break;
            }
            break;
        }
        return 0;
    }

}
